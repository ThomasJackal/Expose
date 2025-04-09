package fr.eql.ai116.duron.thomas.art.connect.controller;

import fr.eql.ai116.duron.thomas.art.connect.entity.dto.EventDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.SearchInputDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.SearchOutputDto;
import fr.eql.ai116.duron.thomas.art.connect.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/rest/event/")
@CrossOrigin("${front.url}")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("{eventId}")
    public ResponseEntity<EventDto> fetchEvent(@PathVariable long eventId) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getEvent(eventId));
    }

    @PostMapping("search")
    public ResponseEntity<List<SearchOutputDto>> searchEvent(@RequestBody SearchInputDto dto) {
        System.out.println(dto.latitude());
        System.out.println(dto.longitude());

        return ResponseEntity.status(HttpStatus.OK).body(eventService.searchEvent(dto));
    }
}

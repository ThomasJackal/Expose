package fr.eql.ai116.duron.thomas.art.connect.controller;

import fr.eql.ai116.duron.thomas.art.connect.entity.dto.EventDto;
import fr.eql.ai116.duron.thomas.art.connect.service.EventService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

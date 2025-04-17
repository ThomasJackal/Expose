package fr.eql.ai116.duron.thomas.art.connect.controller;

import fr.eql.ai116.duron.thomas.art.connect.entity.Artist;
import fr.eql.ai116.duron.thomas.art.connect.entity.Event;
import fr.eql.ai116.duron.thomas.art.connect.entity.User;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.EventCreationDto;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.BearerToken;
import fr.eql.ai116.duron.thomas.art.connect.security.service.IdentifierService;
import fr.eql.ai116.duron.thomas.art.connect.service.EventService;
import jakarta.websocket.server.PathParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rest/a/")
@CrossOrigin("${front.url}")
public class ArtistController {


    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private EventService eventService;

    @Autowired
    private IdentifierService identifierService;

    @PostMapping("event/create")
    public ResponseEntity<Event> createEvent(@RequestBody EventCreationDto dto) {
        logger.info("Entering endpoint: createEvent({})", dto);
        Artist requester = (Artist) identifierService.getUser();
        logger.info("Requester: {}", requester.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(eventService.createEvent(requester, dto));
    }
}

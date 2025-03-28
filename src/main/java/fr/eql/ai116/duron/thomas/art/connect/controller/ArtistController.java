package fr.eql.ai116.duron.thomas.art.connect.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rest/a/")
@CrossOrigin("${front.url}")
public class ArtistController {

    @PostMapping("event/new")
    public String createEvent() {

        return "oui";
    }
}

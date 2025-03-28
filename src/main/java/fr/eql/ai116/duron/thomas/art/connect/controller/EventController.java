package fr.eql.ai116.duron.thomas.art.connect.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rest/event/")
@CrossOrigin("${front.url}")
public class EventController {
}

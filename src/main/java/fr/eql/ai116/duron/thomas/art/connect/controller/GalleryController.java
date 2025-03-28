package fr.eql.ai116.duron.thomas.art.connect.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rest/gallery/")
@CrossOrigin("${front.url}")
public class GalleryController {
}

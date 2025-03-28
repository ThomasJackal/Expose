package fr.eql.ai116.duron.thomas.art.connect.controller;

import fr.eql.ai116.duron.thomas.art.connect.security.service.IdentifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rest/")
@CrossOrigin("${front.url}")
public class TempController {

    @Autowired
    IdentifierService identifierService;

    @GetMapping("free")
    public String free() {
        return "free";
    }

    @GetMapping("authenticated")
    public String authenticated() {
        return "authenticated";
    }

    @GetMapping("o/{username}")
    public String owned() {
        return "owned";
    }


}

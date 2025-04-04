package fr.eql.ai116.duron.thomas.art.connect.controller;

import fr.eql.ai116.duron.thomas.art.connect.entity.Artist;
import fr.eql.ai116.duron.thomas.art.connect.entity.Event;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.EventCreationDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.UserCreationDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.exceptions.UsernameAlreadyExistException;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.BearerToken;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.dto.RegistrationDto;
import fr.eql.ai116.duron.thomas.art.connect.security.repository.SecuredUserRepository;
import fr.eql.ai116.duron.thomas.art.connect.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rest/user")
@CrossOrigin("${front.url}")
public class UserController {

    @Autowired
    private SecurityService securityService;

    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody UserCreationDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(securityService.register(dto));
        } catch (UsernameAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Identifiant déjà utilisé");
        }
    }
}

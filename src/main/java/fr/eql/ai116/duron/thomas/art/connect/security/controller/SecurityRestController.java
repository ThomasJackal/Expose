package fr.eql.ai116.duron.thomas.art.connect.security.controller;

import fr.eql.ai116.duron.thomas.art.connect.security.entity.dto.AuthenticationDto;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.dto.RegistrationDto;
import fr.eql.ai116.duron.thomas.art.connect.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rest/security")
@CrossOrigin("${front.url}")
public class SecurityRestController {

    private SecurityService securityService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody RegistrationDto registrationDto) {
        return securityService.register(registrationDto);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationDto authenticationDto) {
        return securityService.authenticate(authenticationDto);
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}

package fr.eql.ai116.duron.thomas.art.connect.security.service;

import fr.eql.ai116.duron.thomas.art.connect.security.entity.dto.AuthenticationDto;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.dto.RegistrationDto;
import org.springframework.http.ResponseEntity;

public interface SecurityService {

    ResponseEntity<Object> register(RegistrationDto registrationDto);
    ResponseEntity<Object> authenticate(AuthenticationDto authenticationDto);
}

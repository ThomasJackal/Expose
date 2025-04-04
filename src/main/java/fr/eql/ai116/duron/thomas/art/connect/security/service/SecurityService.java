package fr.eql.ai116.duron.thomas.art.connect.security.service;

import fr.eql.ai116.duron.thomas.art.connect.entity.exceptions.UsernameAlreadyExistException;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.BearerToken;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.dto.AuthenticationDto;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.dto.RegistrationDto;
import org.springframework.http.ResponseEntity;

public interface SecurityService {

    BearerToken register(RegistrationDto registrationDto) throws UsernameAlreadyExistException;
    ResponseEntity<Object> authenticate(AuthenticationDto authenticationDto);
}

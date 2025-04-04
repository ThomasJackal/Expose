package fr.eql.ai116.duron.thomas.art.connect.security.service.impl;

import fr.eql.ai116.duron.thomas.art.connect.entity.exceptions.UsernameAlreadyExistException;
import fr.eql.ai116.duron.thomas.art.connect.security.JwtUtilities;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.BearerToken;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.Role;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.SecuredUser;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.dto.AuthenticationDto;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.dto.RegistrationDto;
import fr.eql.ai116.duron.thomas.art.connect.security.repository.RoleRepository;
import fr.eql.ai116.duron.thomas.art.connect.security.repository.SecuredUserRepository;
import fr.eql.ai116.duron.thomas.art.connect.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SecurityServiceImpl implements SecurityService {

    private SecuredUserRepository securedUserRepository;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder ;
    private JwtUtilities jwtUtilities;

    @Override
    public BearerToken register(RegistrationDto registrationDto) throws UsernameAlreadyExistException {
        if (securedUserRepository.existsByUsername(registrationDto.getUsername())) {
            throw new UsernameAlreadyExistException("Username already exist");
        } else {
            Role role = roleRepository.findByRoleName(registrationDto.getRoleName());
            String token = jwtUtilities.generateToken(registrationDto.getUsername(), Collections.singletonList(role.getRoleName().toString()));

            SecuredUser user = registrationDto.instanciateNewUser();
            user.setUsername(registrationDto.getUsername());
            user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
            user.setRoles(Collections.singletonList(role));
            securedUserRepository.save(user);

            return new BearerToken(token , "Bearer ");
        }
    }

    @Override
    public ResponseEntity<Object> authenticate(AuthenticationDto authenticationDto) {
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationDto.getUsername(),
                        authenticationDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        SecuredUser securedUser = securedUserRepository.findByUsername(authentication.getName());
        Role role = roleRepository.findByRoleName(securedUser.getRoles().get(0).getRoleName());
        String token = jwtUtilities.generateToken(authenticationDto.getUsername(), Collections.singletonList(role.getRoleName().toString()));
        return ResponseEntity.status(HttpStatus.OK).body(new BearerToken(token , "Bearer "));
    }

    //region Getters/Setters
    @Autowired
    public void setSecuredUserRepository(SecuredUserRepository securedUserRepository) {
        this.securedUserRepository = securedUserRepository;
    }
    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    public void setJwtUtilities(JwtUtilities jwtUtilities) {
        this.jwtUtilities = jwtUtilities;
    }
    //endregion
}

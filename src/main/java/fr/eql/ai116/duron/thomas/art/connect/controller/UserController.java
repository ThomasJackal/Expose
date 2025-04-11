package fr.eql.ai116.duron.thomas.art.connect.controller;

import fr.eql.ai116.duron.thomas.art.connect.entity.Artist;
import fr.eql.ai116.duron.thomas.art.connect.entity.User;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.UserCreationDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.UserPublicInfosDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.exceptions.UsernameAlreadyExistException;
import fr.eql.ai116.duron.thomas.art.connect.security.service.IdentifierService;
import fr.eql.ai116.duron.thomas.art.connect.security.service.SecurityService;
import fr.eql.ai116.duron.thomas.art.connect.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private IdentifierService identifierService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody UserCreationDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(securityService.register(dto));
        } catch (UsernameAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Identifiant déjà utilisé");
        }
    }

    @GetMapping("/me/infos")
    public ResponseEntity<UserPublicInfosDto> getRequesterInfos() {
        User requester = (User) identifierService.getUser();
        if (requester != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new UserPublicInfosDto(requester.getUsername(), (requester instanceof Artist), requester.getProfilePicture().getImageLink()));
        } else return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }

    @GetMapping("/{username}/infos")
    public ResponseEntity<UserPublicInfosDto> getUserInfos(@PathVariable("username") String username) {

        User user = userService.getUserByUsername(username);
        System.out.println("FETCHED USER " + username);
        System.out.println(user);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new UserPublicInfosDto(user.getUsername(), (user instanceof Artist), user.getProfilePicture().getImageLink()));
        } else return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }
}

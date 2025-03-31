package fr.eql.ai116.duron.thomas.art.connect.controller;

import fr.eql.ai116.duron.thomas.art.connect.entity.User;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.UpgradeDto;
import fr.eql.ai116.duron.thomas.art.connect.security.service.IdentifierService;
import fr.eql.ai116.duron.thomas.art.connect.service.OwnedService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rest/o/")
@CrossOrigin("${front.url}")
public class OwnedController {

    @Autowired
    private OwnedService ownedService;

    @Autowired
    private IdentifierService identifierService;

    @PostMapping("{username}/upgrade")
    public String upgradeToArtist(@PathParam("username") String username, @RequestBody UpgradeDto dto) {
        ownedService.upgrade((User) identifierService.getUser(), dto);
        return "oui";
    }
}

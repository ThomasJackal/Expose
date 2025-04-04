package fr.eql.ai116.duron.thomas.art.connect.entity.dto;

import fr.eql.ai116.duron.thomas.art.connect.entity.Artist;
import fr.eql.ai116.duron.thomas.art.connect.entity.User;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.RoleName;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.SecuredUser;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.dto.RegistrationDto;

import java.util.ArrayList;

public record UserCreationDto(
        String email,
        String username,
        String profilePictureLink,
        String password,
        ArtistCreationDto artistInfos
) implements RegistrationDto {

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public RoleName getRoleName() {
        return artistInfos == null ? RoleName.USER : RoleName.ARTIST;
    }

    @Override
    public SecuredUser instanciateNewUser() {

        User user = new User(
                "",
                "",
                new ArrayList<>(),
                email,
                profilePictureLink
        );

        if (getRoleName() == RoleName.ARTIST) {
            return new Artist(
                    user,
                    artistInfos().displayed_name(),
                    "",
                    "",
                    "",
                    artistInfos.profession(),
                    artistInfos.accountType()
                    );
        } else {
            return user;
        }
    }
}

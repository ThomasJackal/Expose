package fr.eql.ai116.duron.thomas.art.connect.security.entity.dto;

import fr.eql.ai116.duron.thomas.art.connect.security.entity.RoleName;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.SecuredUser;

public interface RegistrationDto {

    String getUsername();
    String getPassword();
    RoleName getRoleName();

    SecuredUser instanciateNewUser();
}

package fr.eql.ai116.duron.thomas.art.connect.security.repository;

import fr.eql.ai116.duron.thomas.art.connect.security.entity.SecuredUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecuredUserRepository extends JpaRepository<SecuredUser, Long> {
    SecuredUser findByUsername(String username);
    Boolean existsByUsername(String username);
}

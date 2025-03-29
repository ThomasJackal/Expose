package fr.eql.ai116.duron.thomas.art.connect.repository;

import fr.eql.ai116.duron.thomas.art.connect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    @Modifying
    @NativeQuery("UPDATE `art_connect_db`.`users` SET `user_type`='Artist' WHERE `id` = :userId;")
    void upgrade(@Param("userId") Long userId);
}

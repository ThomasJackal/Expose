package fr.eql.ai116.duron.thomas.art.connect;

import fr.eql.ai116.duron.thomas.art.connect.entity.User;
import fr.eql.ai116.duron.thomas.art.connect.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class DatabaseTests {

    @Autowired
    private UserRepository userRepo;

    @Test
    void addUser() {
        User user = new User(
                "thomasd",
                "allo",
                null,
                "t.d@g.c",
                "Thomas",
                "Duron"
        );

        User fetchedUser = userRepo.save(user);
        System.out.println(fetchedUser);
        Assertions.assertEquals(user.getUsername(),fetchedUser.getUsername());
    }
}

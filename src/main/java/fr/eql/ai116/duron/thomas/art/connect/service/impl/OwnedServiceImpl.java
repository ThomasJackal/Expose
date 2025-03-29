package fr.eql.ai116.duron.thomas.art.connect.service.impl;

import fr.eql.ai116.duron.thomas.art.connect.entity.AccountType;
import fr.eql.ai116.duron.thomas.art.connect.entity.Artist;
import fr.eql.ai116.duron.thomas.art.connect.entity.User;
import fr.eql.ai116.duron.thomas.art.connect.repository.UserRepository;
import fr.eql.ai116.duron.thomas.art.connect.service.OwnedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnedServiceImpl implements OwnedService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void upgrade(User user) {
        userRepository.upgrade(user.getId());
        Artist artist = new Artist(user,
                "",
                "",
                "",
                "",
                "",
                AccountType.SINGLE
        );
        userRepository.save(artist);
        System.out.println("******************");
        System.out.println(user);
        System.out.println(artist);
    }
}

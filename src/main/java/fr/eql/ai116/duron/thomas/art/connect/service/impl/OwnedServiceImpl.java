package fr.eql.ai116.duron.thomas.art.connect.service.impl;

import fr.eql.ai116.duron.thomas.art.connect.entity.AccountType;
import fr.eql.ai116.duron.thomas.art.connect.entity.Artist;
import fr.eql.ai116.duron.thomas.art.connect.entity.User;
import fr.eql.ai116.duron.thomas.art.connect.repository.ArtistRepository;
import fr.eql.ai116.duron.thomas.art.connect.repository.UserRepository;
import fr.eql.ai116.duron.thomas.art.connect.service.OwnedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OwnedServiceImpl implements OwnedService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public void upgrade(User user) {
        Artist artist = new Artist(user,
                "",
                "",
                "",
                "",
                "",
                AccountType.SINGLE
        );
        //userRepository.delete(user);
        //artist.setId(user.getId());
        System.out.println("******************");
        System.out.println(user);
        System.out.println(artist);
        artistRepository.save(artist);
        artistRepository.flush();
    }
}

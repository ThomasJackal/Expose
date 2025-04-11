package fr.eql.ai116.duron.thomas.art.connect.service.impl;

import fr.eql.ai116.duron.thomas.art.connect.entity.User;
import fr.eql.ai116.duron.thomas.art.connect.repository.UserRepository;
import fr.eql.ai116.duron.thomas.art.connect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

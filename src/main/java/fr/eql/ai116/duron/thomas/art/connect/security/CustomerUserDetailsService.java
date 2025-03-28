package fr.eql.ai116.duron.thomas.art.connect.security;

import fr.eql.ai116.duron.thomas.art.connect.security.repository.SecuredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomerUserDetailsService implements UserDetailsService {

    private SecuredUserRepository securedUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return securedUserRepository.findByUsername(username);
    }

    @Autowired
    public void setSecuredUserRepository(SecuredUserRepository securedUserRepository) {
        this.securedUserRepository = securedUserRepository;
    }
}

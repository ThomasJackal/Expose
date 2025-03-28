package fr.eql.ai116.duron.thomas.art.connect.security.service.impl;

import fr.eql.ai116.duron.thomas.art.connect.security.entity.SecuredUser;
import fr.eql.ai116.duron.thomas.art.connect.security.repository.SecuredUserRepository;
import fr.eql.ai116.duron.thomas.art.connect.security.service.IdentifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IdentifierServiceImpl implements IdentifierService {

    private SecuredUserRepository securedUserRepository;

    @Override
    public SecuredUser getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) return null;
        else return securedUserRepository.findByUsername(authentication.getName());
    }

    @Autowired
    public void setSecuredUserRepository(SecuredUserRepository securedUserRepository) {
        this.securedUserRepository = securedUserRepository;
    }
}

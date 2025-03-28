package fr.eql.ai116.duron.thomas.art.connect.security;

import fr.eql.ai116.duron.thomas.art.connect.security.service.IdentifierService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OwnershipAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Autowired
    IdentifierService identifierService;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {

        if (authentication.get() instanceof AnonymousAuthenticationToken) return new AuthorizationDecision(false);

        String ownerUsername = getOwnerUsername(context.getRequest());
        String accessorUsername = authentication.get().getName();

        return new AuthorizationDecision(accessorUsername.equals(ownerUsername));
    }

    private String getOwnerUsername(HttpServletRequest request) {
        String path = request.getRequestURI();

        String regex = "/o/([^/]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(path);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}

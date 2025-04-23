package fr.eql.ai116.duron.thomas.art.connect.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LogManager.getLogger();

   private JwtUtilities jwtUtilities ;
   private CustomerUserDetailsService customerUserDetailsService ;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
                                    throws ServletException, IOException {

        //printRequest(request);
        String token = jwtUtilities.getToken(request) ;
        if (token != null && jwtUtilities.validateToken(token)) {
            String login = jwtUtilities.extractUsername(token);
            UserDetails userDetails = customerUserDetailsService.loadUserByUsername(login);
            if (userDetails != null) {
                UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername() ,null , userDetails.getAuthorities());
                logger.info("Utilisateur authentifié avec l'identifiant : {}", login);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (authorities != null) {
                for (GrantedAuthority authority : authorities) {
                    authority.getAuthority();
                }
            }
        }
        */

        filterChain.doFilter(request,response);
    }

    @Autowired
    public void setJwtUtilities(JwtUtilities jwtUtilities) {
        this.jwtUtilities = jwtUtilities;
    }
    @Autowired
    public void setCustomerUserDetailsService(CustomerUserDetailsService customerUserDetailsService) {
        this.customerUserDetailsService = customerUserDetailsService;
    }

    private static void printRequest(final HttpServletRequest httpServletRequest) {
        if (httpServletRequest == null) {
            return;
        }
        logger.info("----------------------------------------");
        logger.info("W4 HttpServletRequest");
        logger.info("\tRequestURL : {}", httpServletRequest.getRequestURL());
        logger.info("\tRequestURI : {}", httpServletRequest.getRequestURI());
        logger.info("\tScheme : {}", httpServletRequest.getScheme());
        logger.info("\tAuthType : {}", httpServletRequest.getAuthType());
        logger.info("\tEncoding : {}", httpServletRequest.getCharacterEncoding());
        logger.info("\tContentLength : {}", httpServletRequest.getContentLength());
        logger.info("\tContentType : {}", httpServletRequest.getContentType());
        logger.info("\tContextPath : {}", httpServletRequest.getContextPath());
        logger.info("\tMethod : {}", httpServletRequest.getMethod());
        logger.info("\tPathInfo : {}", httpServletRequest.getPathInfo());
        logger.info("\tProtocol : {}", httpServletRequest.getProtocol());
        logger.info("\tQuery : {}", httpServletRequest.getQueryString());
        logger.info("\tRemoteAddr : {}", httpServletRequest.getRemoteAddr());
        logger.info("\tRemoteHost : {}", httpServletRequest.getRemoteHost());
        logger.info("\tRemotePort : {}", httpServletRequest.getRemotePort());
        logger.info("\tRemoteUser : {}", httpServletRequest.getRemoteUser());
        logger.info("\tSessionID : {}", httpServletRequest.getRequestedSessionId());
        logger.info("\tServerName : {}", httpServletRequest.getServerName());
        logger.info("\tServerPort : {}", httpServletRequest.getServerPort());
        logger.info("\tServletPath : {}", httpServletRequest.getServletPath());

        logger.info("");

        logger.info("\tHeaders");
        int j = 0;
        final Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            final String headerName = headerNames.nextElement();
            final String header = httpServletRequest.getHeader(headerName);
            logger.info("\tHeader[{}].name={}", j, headerName);
            logger.info("\tHeader[{}].value={}", j, header);
            j++;
        }

        logger.info("\tLocalAddr : {}", httpServletRequest.getLocalAddr());
        logger.info("\tLocale : {}", httpServletRequest.getLocale());
        logger.info("\tLocalPort : {}", httpServletRequest.getLocalPort());

        logger.info("");
        logger.info("\tParameters");
        int k = 0;
        final Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            final String paramName = parameterNames.nextElement();
            final String paramValue = httpServletRequest.getParameter(paramName);
            logger.info("\tParam[{}].name={}", k, paramName);
            logger.info("\tParam[{}].value={}", k, paramValue);
            k++;
        }

        logger.info("----------------------------------------");
    }
}

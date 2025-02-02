package eu.foobarssgamesmithy.chessmanager.auth.impl;

import eu.foobarssgamesmithy.chessmanager.auth.AuthenticationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationFacadeImpl.class);

    @Override
    public String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        LOG.debug("User name spring SecurityContext: {}.", userName);
        Object rawPrincipal = authentication.getPrincipal();
        if(Jwt.class.isAssignableFrom(rawPrincipal.getClass())){
            Jwt jwtPrincipal = (Jwt) rawPrincipal;
            userName = jwtPrincipal.getClaim("preferred_username");
            LOG.debug("User name from JWT token {}.", userName);
        }
        return userName;
    }

}

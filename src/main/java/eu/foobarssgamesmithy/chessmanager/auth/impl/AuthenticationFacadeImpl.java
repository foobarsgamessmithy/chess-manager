package eu.foobarssgamesmithy.chessmanager.auth.impl;

import eu.foobarssgamesmithy.chessmanager.auth.AuthenticationFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Object rawPrincipal = authentication.getPrincipal();
        if(Jwt.class.isAssignableFrom(rawPrincipal.getClass())){
            Jwt jwtPrincipal = (Jwt) rawPrincipal;
            userName = jwtPrincipal.getClaim("preferred_username");
        }
        return userName;
    }

}

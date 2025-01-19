package eu.foobarssgamesmithy.chessmanager.auth.impl;

import eu.foobarssgamesmithy.chessmanager.auth.AuthenticationFacade;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public String getUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}

package ru.kurma.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest
            , HttpServletResponse httpServletResponse
            , Authentication authentication) throws IOException, ServletException {

        boolean admin = false;

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("admin".equals(auth.getAuthority())){
                admin = true;
            }
        }

        if(admin){
            httpServletResponse.sendRedirect("/admin");
        }else{
            httpServletResponse.sendRedirect("/user");
        }

    }
}
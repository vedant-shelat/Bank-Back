package com.bank.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        String jwtExpired = (String) httpServletRequest.getAttribute("jwtExpired");
        String badCredentials = (String) httpServletRequest.getAttribute("badCredentials");

        if(jwtExpired != null) {
            httpServletResponse.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "JWT_EXPIRED");
        } else if(badCredentials != null) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "BAD_CREDENTIALS");
        } else{
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
        }
    }
}

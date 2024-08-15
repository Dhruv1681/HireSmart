package com.hiresmart.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final View error;

    public CustomAuthenticationSuccessHandler(View error) {
        this.error = error;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("Authentication Success Handler invoked");
        authentication.getAuthorities().forEach(authority -> {
            System.out.println("Authority: " + authority.getAuthority());
            if (authority.getAuthority().equals("ROLE_STUDENT")) {
                try {
                    System.out.println("Student Authority: " + authority.getAuthority());
                    response.sendRedirect("/student/home");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (authority.getAuthority().equals("ROLE_EMPLOYER")) {
                try {
                    System.out.println("Employee Authority: " + authority.getAuthority());
                    response.sendRedirect("/employer/home");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                try{
                    System.out.println("Error Authority:");
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}

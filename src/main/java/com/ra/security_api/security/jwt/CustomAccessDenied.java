package com.ra.security_api.security.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class CustomAccessDenied implements AccessDeniedHandler {
    private final Logger logger = LoggerFactory.getLogger(CustomAccessDenied.class);
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        logger.error("CustomAccessDenied{}", accessDeniedException.getMessage());
        response.setStatus(403);
        response.getWriter().write("CustomAccessDenied");
    }
}

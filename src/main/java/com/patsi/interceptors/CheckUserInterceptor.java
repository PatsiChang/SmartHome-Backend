package com.patsi.interceptors;

import com.patsi.annotations.RequireLoginSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CheckUserInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RequireLoginSession annotation = handlerMethod.getMethod().getAnnotation(RequireLoginSession.class);
            if (annotation != null) {
                String bearerTokenHeader = request.getHeader(AUTHORIZATION_HEADER);
                if (bearerTokenHeader != null && bearerTokenHeader.startsWith("Bearer ")) {
                    String token = bearerTokenHeader.substring(7);
                    request.setAttribute("token", token);
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Unauthorized: Missing or invalid token");
                    return false;
                }
            }
        }
        return true;
    }
}

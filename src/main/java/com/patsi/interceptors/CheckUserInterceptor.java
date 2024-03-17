package com.patsi.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

public class CheckUserInterceptor implements HandlerInterceptor {
    Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

    //Check if user is logged in
    public boolean checkUser(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        log.info("In checkUser Interceptor");
        Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("ssoToken"))
            .findFirst().get();
        return true;
    }
}

package com.patsi.service;

import com.patsi.api.UserProfileAPI;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserProfileService {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserProfileAPI userProfileAPI;

    public String getUidFromToken() {
        return userProfileAPI.getUid(getLoginToken());
    }

    private String getLoginToken() {
        String token = request.getAttribute("token").toString();
        log.info("getLoginToken: " + token);
        return token;
    }
}

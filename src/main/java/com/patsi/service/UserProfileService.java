package com.patsi.service;

import com.patsi.api.UserProfileAPI;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserProfileService {
    private final HttpServletRequest request;
    private final UserProfileAPI userProfileAPI;

    public String getUidFromToken() {
        return userProfileAPI.getUid(getLoginToken());
    }

    private String getLoginToken() {
        return request.getAttribute("token").toString();
    }
}

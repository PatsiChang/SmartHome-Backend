package com.patsi.api;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(
    name = "user-profile-api",
    url = "${com.patsi.login.services.api.url}"
)
public interface UserProfileAPI {

    @GetMapping("logInSession/getPersonUid")
    @Cacheable
    String getUid(
        @RequestHeader(AUTHORIZATION) String token
//        @RequestHeader(SERVICE_AUTHORIZATION) String serviceAuthorization,
    );
}

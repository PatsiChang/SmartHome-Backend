package com.patsi.api;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(
    url = "${com.patsi.login.services.api.url}"
)
public interface UserProfileAPI {

    @PostMapping("logInSession")
    @Cacheable
    String getUid(
        @RequestHeader(AUTHORIZATION) String token
//        @RequestHeader(SERVICE_AUTHORIZATION) String serviceAuthorization,
    );
}

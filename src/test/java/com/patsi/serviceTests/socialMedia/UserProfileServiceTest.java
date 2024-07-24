package com.patsi.serviceTests.socialMedia;

import com.patsi.api.UserProfileAPI;
import com.patsi.service.UserProfileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
@SpringBootTest
public class UserProfileServiceTest {

    @InjectMocks
    private UserProfileService userProfileService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private UserProfileAPI userProfileAPI;

    @Test
    public void testGetUidFromToken() {
        when(request.getAttribute("token")).thenReturn("dummyToken");
        when(userProfileAPI.getUid("dummyToken")).thenReturn("dummyUser");
        assertEquals("dummyUser", userProfileService.getUidFromToken());
    }

}

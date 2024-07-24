package com.patsi.controllerTests.socialMedia;

import com.common.validation.service.ValidatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patsi.bean.SocialMediaUser;
import com.patsi.service.UserProfileService;
import com.patsi.service.socialMedia.SocialMediaService;
import com.patsi.serviceTests.socialMedia.ConstForSocialMediaUnitTest;
import com.patsi.validator.SocialMediaRegistrationValidator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class SocialMediaUserControllerTest extends ConstForSocialMediaUnitTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
        private SocialMediaService socialMediaService;
    @MockBean
    private UserProfileService userProfileService;
    @MockBean
    private ValidatorService validatorService;
    @MockBean
    private SocialMediaRegistrationValidator socialMediaRegistrationValidator;

    void setUpSocialMediaUserControllerTest() {
        when(userProfileService.getUidFromToken())
            .thenReturn(String.valueOf(validUidForSocialMediaUnitTest));
        when(socialMediaRegistrationValidator.validateAccountExisting(validUidForSocialMediaUnitTest))
            .thenReturn(true);
        when(socialMediaService.createSocialMediaAccount(any(SocialMediaUser.class)))
            .thenReturn(socialMediaUserForUnitTest);
    }

    @Test
    void createSocialMediaAccountTestWithValidUid() throws Exception {
        setUpSocialMediaUserControllerTest();
        when(validatorService.checkAnnotation(socialMediaUserForUnitTest))
            .thenReturn(List.of());

        mockMvc.perform(post("/socialMedia")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(socialMediaUserForUnitTest))
                .header("Authorization", "Bearer " + validUidForSocialMediaUnitTest))
            .andExpectAll(
                status().isOk(),
                jsonPath("$.uid").value(String.valueOf(validUidForSocialMediaUnitTest))
            );
    }

    @Test
    void createSocialMediaAccountTestWitInvalidEmail() throws Exception {
        setUpSocialMediaUserControllerTest();
        when(validatorService.checkAnnotation(socialMediaUserForUnitTest))
            .thenReturn(List.of("Error"));

        mockMvc.perform(post("/socialMedia")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(socialMediaUserForUnitTest))
                .header("Authorization", "Bearer " + validUidForSocialMediaUnitTest))
            .andExpectAll(
                status().isOk(),
                content().string("")
            );
    }

    @Test
    void getUserByIdTestWithValidUid() throws Exception {
        setUpSocialMediaUserControllerTest();
        when(socialMediaService.getUserByUid(validUidForSocialMediaUnitTest))
            .thenReturn(socialMediaUserForUnitTest);

        mockMvc.perform(get("/socialMedia/getUserByToken")
                .header("Authorization", "Bearer " + validUidForSocialMediaUnitTest))
            .andExpectAll(
                status().isOk(),
                jsonPath("$.uid").value(String.valueOf(validUidForSocialMediaUnitTest))
            );
    }

    @Test
    void getUserByIdTestWithInvalidUid() throws Exception {
        setUpSocialMediaUserControllerTest();
        when(socialMediaService.getUserByUid(validUidForSocialMediaUnitTest))
            .thenReturn(null);

        mockMvc.perform(get("/socialMedia/getUserByToken")
                .header("Authorization", "Bearer " + validUidForSocialMediaUnitTest))
            .andExpectAll(
                status().isOk(),
                content().string("")
            );
    }

    @Test
    void changeUserNameTestWithValidNameChangedCount() throws Exception {
        setUpSocialMediaUserControllerTest();
        when(socialMediaRegistrationValidator.validateNameChangeCount(validUidForSocialMediaUnitTest))
            .thenReturn(true);
        when(socialMediaService.changeUserName(socialMediaUserForUnitTest))
            .thenReturn(socialMediaUserForUnitTest);

        mockMvc.perform(post("/socialMedia/changeUserName")
                .header("Authorization", "Bearer " + validUidForSocialMediaUnitTest)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(socialMediaUserForUnitTest)))
            .andExpectAll(
                status().isOk(),
                jsonPath("$.uid").value(String.valueOf(validUidForSocialMediaUnitTest))
            );
    }
    @Test
    void changeUserNameTestWithInvalidNameChangedCount() throws Exception {
        setUpSocialMediaUserControllerTest();
        when(socialMediaRegistrationValidator.validateNameChangeCount(validUidForSocialMediaUnitTest))
            .thenReturn(false);

        mockMvc.perform(post("/socialMedia/changeUserName")
                .header("Authorization", "Bearer " + validUidForSocialMediaUnitTest)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(socialMediaUserForUnitTest)))
            .andExpectAll(
                status().isOk(),
                content().string("")
            );
    }

    @Test
    void activateAccountTest() throws Exception {
        setUpSocialMediaUserControllerTest();
        when(socialMediaService.activateAccount(validUidForSocialMediaUnitTest))
            .thenReturn(socialMediaActiveUserI);

        mockMvc.perform(put("/socialMedia/activateAccount")
                .header("Authorization", "Bearer " + validUidForSocialMediaUnitTest))
            .andExpectAll(
                status().isOk(),
                jsonPath("$.accountStatus").value("Active")
            );
    }
    @Test
    void deactivateAccountTest() throws Exception {
        setUpSocialMediaUserControllerTest();
        when(socialMediaService.deactivateAccount(validUidForSocialMediaUnitTest))
            .thenReturn(socialMediaDeactivatedUserI);

        mockMvc.perform(put("/socialMedia/deactivateAccount")
                .header("Authorization", "Bearer " + validUidForSocialMediaUnitTest))
            .andExpectAll(
                status().isOk(),
                jsonPath("$.accountStatus").value("Deactivate")
            );
    }

    @Test
    void deleteAccountTest() throws Exception{
        setUpSocialMediaUserControllerTest();

        mockMvc.perform(delete("/socialMedia")
                .header("Authorization", "Bearer " + validUidForSocialMediaUnitTest))
            .andExpectAll(
                status().isOk(),
                content().string("")
            );

    }

}

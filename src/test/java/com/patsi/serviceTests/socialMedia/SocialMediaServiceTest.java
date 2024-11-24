package com.patsi.serviceTests.socialMedia;

import com.common.validation.service.MaskingService;
import com.patsi.bean.SocialMediaUser;
import com.patsi.database.repository.SocialMediaRepository;
import com.patsi.enums.AccountStatus;
import com.patsi.service.socialMedia.SocialMediaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class SocialMediaServiceTest extends ConstForSocialMediaUnitTest {

    @InjectMocks
    private SocialMediaService socialMediaService;
    @Mock
    private SocialMediaRepository socialMediaRepository;
    @Mock
    private MaskingService maskingService;

    SocialMediaUser socialMediaUserForUnitTestTmp = socialMediaUserForUnitTest;

    private void setUpSocialMediaServiceTest() {
        when(socialMediaRepository.findByUid(validUidForSocialMediaUnitTest))
            .thenReturn(Optional.ofNullable(socialMediaUserForUnitTest));
        when(socialMediaRepository.findByUid(invalidUidForSocialMediaUnitTest))
            .thenReturn(Optional.empty());
    }

    @Test
    void testCreateSocialMediaAccount() {
        socialMediaUserForUnitTestTmp.setAccountStatus(AccountStatus.Active);
        socialMediaUserForUnitTestTmp.setUid(null);
        assertEquals(socialMediaUserForUnitTestTmp, socialMediaService.createSocialMediaAccount(socialMediaUserForUnitTest));
    }

    @Test
    void testGetUserByUid() {
        when(maskingService.maskSensitiveFields(socialMediaUserForUnitTest))
            .thenReturn(socialMediaUserForUnitTest);
        setUpSocialMediaServiceTest();
        assertEquals(socialMediaUserForUnitTest, socialMediaService.getUserByUid(validUidForSocialMediaUnitTest));
        assertEquals(null, socialMediaService.getUserByUid(invalidUidForSocialMediaUnitTest));
    }

    @Test
    void testGetUserByUserName() {
        when(maskingService.maskSensitiveFields(socialMediaUserForUnitTest))
            .thenReturn(socialMediaUserForUnitTest);
        when(socialMediaRepository.findByUserName(validUserNameForSocialMediaUnitTest))
            .thenReturn(Optional.ofNullable(socialMediaUserForUnitTest));
        when(socialMediaRepository.findByUserName(invalidUserNameForSocialMediaUnitTest))
            .thenReturn(Optional.empty());
        assertEquals(socialMediaUserForUnitTest,
            socialMediaService.getUserByUserName(validUserNameForSocialMediaUnitTest));
        assertEquals(null, socialMediaService.getUserByUserName(invalidUserNameForSocialMediaUnitTest));
    }

    @Test
    void testGetAllActiveUsers() {
        when(socialMediaRepository.findAll())
            .thenReturn(listOfUserForTest);
        when(maskingService.maskSensitiveFields(socialMediaActiveUserI))
            .thenReturn(socialMediaActiveUserI);
        when(maskingService.maskSensitiveFields(socialMediaActiveUserII))
            .thenReturn(socialMediaActiveUserII);
        assertEquals(List.of(socialMediaActiveUserI, socialMediaActiveUserII), socialMediaService.getAllActiveUsers());
    }

    @Test
    void testUpdateSocialMediaAccount() {
        socialMediaActiveUserI.setUid(null);
        assertEquals(socialMediaActiveUserI, socialMediaService.updateSocialMediaAccount(socialMediaActiveUserI));
    }

    @Test
    void testChangeUserName() {
        socialMediaActiveUserI.setUid(null);
        assertEquals(0, socialMediaActiveUserI.getUserNameChangeCount());
        assertEquals(1, socialMediaService.changeUserName(socialMediaActiveUserI).getUserNameChangeCount());
    }

    @Test
    void testActivateAccount() {
        UUID uidToBeTested = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d477");
        when(socialMediaRepository.findByUid(uidToBeTested))
            .thenReturn(Optional.ofNullable(socialMediaInactiveUserI));
        assertEquals(AccountStatus.Inactive, socialMediaInactiveUserI.getAccountStatus());
        assertEquals(AccountStatus.Active, socialMediaService.activateAccount(uidToBeTested).getAccountStatus());
    }

    @Test
    void testDeactivateAccount() {
        UUID uidToBeTested = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d478");
        when(socialMediaRepository.findByUid(uidToBeTested))
            .thenReturn(Optional.ofNullable(socialMediaActiveUserI));
        assertEquals(AccountStatus.Active, socialMediaActiveUserI.getAccountStatus());
        assertEquals(AccountStatus.Deactivate, socialMediaService.deactivateAccount(uidToBeTested).getAccountStatus());
    }


}

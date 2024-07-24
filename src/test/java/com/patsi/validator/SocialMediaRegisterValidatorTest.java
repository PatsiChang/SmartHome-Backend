package com.patsi.validator;

import com.patsi.MainApplication;
import com.patsi.database.repository.SocialMediaRepository;
import com.patsi.serviceTests.socialMedia.ConstForSocialMediaUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = MainApplication.class)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class SocialMediaRegisterValidatorTest extends ConstForSocialMediaUnitTest {

    @InjectMocks
    private SocialMediaRegistrationValidator socialMediaRegistrationValidator;

    @Mock
    private SocialMediaRepository socialMediaRepository;

    @BeforeEach
    void setUpSocialMediaRegisterValidatorTest() {
        MockitoAnnotations.openMocks(this);
        when(socialMediaRepository.findByUid(validUidForSocialMediaUnitTest))
            .thenReturn(Optional.ofNullable(socialMediaUserForUnitTest));
        when(socialMediaRepository.findByUid(invalidUidForSocialMediaUnitTest))
            .thenReturn(Optional.empty());
    }

    @Test
    void validateAccountExistingTest() {
        assertFalse(socialMediaRegistrationValidator.validateAccountExisting(validUidForSocialMediaUnitTest));
        assertTrue(socialMediaRegistrationValidator.validateAccountExisting(invalidUidForSocialMediaUnitTest));

    }

    @Test
    void validateNameChangeCountTest() {
        socialMediaUserForUnitTest.setUserNameChangeCount(1);
        assertFalse(socialMediaRegistrationValidator.validateNameChangeCount(validUidForSocialMediaUnitTest));
        socialMediaUserForUnitTest.setUserNameChangeCount(0);
        assertTrue(socialMediaRegistrationValidator.validateNameChangeCount(validUidForSocialMediaUnitTest));
        assertFalse(socialMediaRegistrationValidator.validateNameChangeCount(invalidUidForSocialMediaUnitTest));

    }
}

package com.patsi.serviceTests.socialMedia;

import com.patsi.bean.SocialMediaUser;
import com.patsi.enums.AccountStatus;
import com.patsi.enums.AccountType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

@ActiveProfiles("test")
public class ConstForSocialMediaUnitTest {
    public final UUID validUidForSocialMediaUnitTest = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479");
    public final UUID invalidUidForSocialMediaUnitTest = UUID.fromString("e47ac10b-58cc-4372-a567-0e02b2c3d479");
    public final String validUserNameForSocialMediaUnitTest = "socialMediaUserForUnitTest";
    public final String invalidUserNameForSocialMediaUnitTest = "invalidUserName";


    public SocialMediaUser socialMediaUserForUnitTest =
        SocialMediaUser.builder()
            .uid(validUidForSocialMediaUnitTest)
            .email("socialMediaRegisterValidatorTest@gmail.com")
            .displayName("socialMediaUserForUnitTest")
            .userName(validUserNameForSocialMediaUnitTest)
            .accountType(AccountType.privateAccount)
            .build();

    public SocialMediaUser socialMediaActiveUserI =
        SocialMediaUser.builder()
            .uid(UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d478"))
            .email("socialMediaTestI@gmail.com")
            .displayName("socialMediaUserForUnitTestI")
            .userName("socialMediaUserForUnitTestI")
            .accountStatus(AccountStatus.Active)
            .accountType(AccountType.privateAccount)
            .build();
    public SocialMediaUser socialMediaDeactivatedUserI =
        SocialMediaUser.builder()
            .uid(UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d478"))
            .email("socialMediaTestI@gmail.com")
            .displayName("socialMediaUserForUnitTestI")
            .userName("socialMediaUserForUnitTestI")
            .accountStatus(AccountStatus.Deactivate)
            .accountType(AccountType.privateAccount)
            .build();

    public SocialMediaUser socialMediaActiveUserII =
        SocialMediaUser.builder()
            .uid(UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d477"))
            .email("socialMediaUserTestII@gmail.com")
            .displayName("socialMediaUserForUnitTestII")
            .userName("socialMediaUserForUnitTestII")
            .accountStatus(AccountStatus.Active)
            .accountType(AccountType.publicAccount)
            .build();
    public SocialMediaUser socialMediaInactiveUserI =
        SocialMediaUser.builder()
            .uid(UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d477"))
            .email("socialMediaInactiveUserTest@gmail.com")
            .displayName("socialMediaUserForUnitTest")
            .userName("socialMediaUserForUnitTest")
            .accountStatus(AccountStatus.Inactive)
            .accountType(AccountType.publicAccount)
            .build();

    public final List<SocialMediaUser> listOfUserForTest =
        List.of(socialMediaActiveUserI, socialMediaActiveUserII, socialMediaInactiveUserI);

//    public final MockMultipartFile mockMultipartFile = new MockMultipartFile(
//        "profilePicture",
//        "test.jpg",
//        "image/jpeg",
//        "Test Image Content".getBytes()
//    );

}

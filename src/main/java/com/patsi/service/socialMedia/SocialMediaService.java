package com.patsi.service.socialMedia;

import com.common.validation.service.MaskingService;
import com.patsi.bean.SocialMediaUser;
import com.patsi.configuration.SocialMediaEnvValueConfig;
import com.patsi.database.repository.SocialMediaRepository;
import com.patsi.enums.AccountStatus;
import com.patsi.utils.FileHelper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SocialMediaService {

    @Autowired
    private SocialMediaRepository socialMediaRepository;

    @Autowired
    private SocialMediaEnvValueConfig socialMediaEnvValueConfig;

    @Autowired
    private MaskingService maskingService;

    private SocialMediaUser maskUid(SocialMediaUser user) {
        if (user != null) {
            user.setUid(null);
            return user;
        } else
            return null;
    }

    private SocialMediaUser saveUserToDb(SocialMediaUser user) {
        socialMediaRepository.save(user);
        return maskUid(user);
    }

    //One time for each account
    public SocialMediaUser createSocialMediaAccount(SocialMediaUser user) {
        log.info("Creating User in Social Media");
        return saveUserToDb(SocialMediaUser.builder()
            .uid(user.getUid())
            .userName(user.getUserName())
            .email(user.getEmail())
            .displayName(user.getDisplayName())
            .biography(user.getBiography())
            .accountStatus(AccountStatus.Active)
            .accountType(user.getAccountType())
            .build());
    }

    //Todo: Add Image Filtering
    public void changeProfilePicture(String profilePictureID, byte[] profilePicture)
        throws IOException {
        log.info("In SocialMedia Service: change ProfilePicture");
        FileHelper.newFile(socialMediaEnvValueConfig.profilePicturePath(),
            profilePictureID, profilePicture);
    }

    //Todo: Fix method after banner field is created on front end, Add Image Filtering
    public SocialMediaUser changeBannerPicture(String userName, byte[] bannerPicture)
        throws IOException {
        log.info("In SocialMedia Service: change BannerPicture");
        FileHelper.newFile(socialMediaEnvValueConfig.bannerPicturePath(), userName, bannerPicture);
        SocialMediaUser user = socialMediaRepository.findByUserName(userName).orElse(null);
        user.setBannerPicture(user.getUserName());
        return saveUserToDb(user);
    }

    public SocialMediaUser getUserByUid(UUID uid) {
        return maskUid((SocialMediaUser) maskingService.maskSensitiveFields(
            socialMediaRepository.findByUid(uid).orElse(null)));
    }

    public SocialMediaUser getUserByUserName(String userName) {
        return maskUid((SocialMediaUser) maskingService.maskSensitiveFields(
            socialMediaRepository.findByUserName(userName).orElse(null)));
    }

    //Get All Existing Users
    public List<SocialMediaUser> getAllActiveUsers() {
        System.out.println("In getAllActiveUsers");
        return socialMediaRepository.findAll().stream()
            .filter(user -> user.getAccountStatus().equals(AccountStatus.Active))
            .map(user -> (SocialMediaUser) maskingService.maskSensitiveFields(user))
            .peek(user -> maskUid(user))
            .collect(Collectors.toList());
    }

    //UpdateUserAccount
    public SocialMediaUser updateSocialMediaAccount(SocialMediaUser user) {
        log.info("In service: update SocialMediaAccount");
        SocialMediaUser.builder()
            .uid(user.getUid())
            .displayName(user.getDisplayName())
            .biography(user.getBiography())
            .accountType(user.getAccountType())
            .build();
        return saveUserToDb(user);
    }

    public SocialMediaUser changeUserName(SocialMediaUser user) {
        user.setUserNameChangeCount(1);
        return saveUserToDb(user);
    }

    public SocialMediaUser activateAccount(UUID uid) {
        SocialMediaUser user = socialMediaRepository.findByUid(uid).orElse(null);
        if (user != null) {
            user.setAccountStatus(AccountStatus.Active);
            return saveUserToDb(user);
        } else
            return null;
    }

    public SocialMediaUser deactivateAccount(UUID uid) {
        SocialMediaUser user = socialMediaRepository.findByUid(uid).orElse(null);
        if (user != null) {
            user.setAccountStatus(AccountStatus.Deactivate);
            return saveUserToDb(user);
        } else
            return null;
    }

    //Todo: Add more business logic
    @Transactional
    public void deleteAccount(UUID uid) {
        socialMediaRepository.deleteByUid(uid);
    }

}

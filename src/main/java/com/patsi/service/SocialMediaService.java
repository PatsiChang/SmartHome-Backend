package com.patsi.service;

import com.patsi.bean.SocialMediaUser;
import com.patsi.database.repository.SocialMediaRepository;
import com.patsi.enums.AccountStatus;
import com.patsi.enums.AccountType;
import com.patsi.interceptors.LoggingInterceptor;
import com.patsi.utils.FileHelper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class SocialMediaService {
    Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Autowired
    private SocialMediaRepository socialMediaRepository;

    @Value("${com.patsi.socialMedia.profilePicture.path}")
    private String IMAGE_PATH_ProfilePicture;
    @Value("${com.patsi.socialMedia.bannerPicture.path}")
    private String IMAGE_PATH_BannerPicture;

    public UUID getUserUid() {
        UUID uid = UUID.randomUUID();
        return uid;
    }

    public SocialMediaUser createSocialMediaAccount(SocialMediaUser user) {
        log.info("In service: createSocialMediaAccount");
        SocialMediaUser.builder()
            .uid(user.getUid())
            .displayName(user.getDisplayName())
            .biography(user.getBiography())
            .accountStatus(AccountStatus.Active)
            .accountType(user.getAccountType())
            .build();
        socialMediaRepository.save(user);
        user.setUid(null);
        return user;
    }

    public void changeProfilePicture(String profilePictureID, byte[] profilePicture)
        throws IOException {
        log.info("In Service: changeProfilePicture");
        FileHelper.newFile(IMAGE_PATH_ProfilePicture, profilePictureID, profilePicture);
    }

    //Todo: Fix method after banner field is created on front end
    public SocialMediaUser changeBannerPicture(String userName, byte[] bannerPicture)
        throws IOException {
        log.info("In Service: changeBannerPicture");
        FileHelper.newFile(IMAGE_PATH_ProfilePicture, userName, bannerPicture);
        SocialMediaUser user = socialMediaRepository.findByUserName(userName).orElse(null);
        user.setBannerPicture(user.getUserName());
        socialMediaRepository.save(user);
        return user;
    }

    //Get one Existing User
    public SocialMediaUser getUser(SocialMediaUser user) {
        return socialMediaRepository.findByUid(user.getUid()).orElse(null);
    }

    public SocialMediaUser getUserByUid(UUID uid) {
        return socialMediaRepository.findByUid(uid).orElse(null);
    }

    //Get All Existing Users
    public List<SocialMediaUser> getAllUser() {
        return socialMediaRepository.findAll();
    }

    public AccountStatus deactivateAccount(SocialMediaUser user) {
        user.setAccountStatus(AccountStatus.Deactivate);
        return user.getAccountStatus();
    }

    @Transactional
    public void deleteAccount(UUID uid) {
        socialMediaRepository.deleteByUid(uid);
    }

}

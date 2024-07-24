package com.patsi.controller;


import com.common.validation.service.ValidatorService;
import com.patsi.annotations.RequireLoginSession;
import com.patsi.bean.SocialMediaUser;
import com.patsi.service.socialMedia.SocialMediaService;
import com.patsi.service.UserProfileService;
import com.patsi.validator.SocialMediaRegistrationValidator;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/socialMedia")
@CrossOrigin
public class SocialMediaUserController {
    Logger log = LoggerFactory.getLogger(SocialMediaUserController.class);

    @Autowired
    private SocialMediaService socialMediaService;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private SocialMediaRegistrationValidator socialMediaRegistrationValidator;
    @Autowired
    private ValidatorService validatorService;

    //Allows only if account has never been created
    @PostMapping
    @RequireLoginSession
    public SocialMediaUser createSocialMediaAccount(@RequestBody @Valid SocialMediaUser user) {
        String userUid = userProfileService.getUidFromToken();
        List<String> errList =  validatorService.checkAnnotation(user);
        user.setUid(UUID.fromString(userUid));
        if (errList.size() == 0 && socialMediaRegistrationValidator.validateAccountExisting(UUID.fromString(userUid))) {
            return socialMediaService.createSocialMediaAccount(user);
        } else {
            return null;
        }
    }

    @GetMapping("/getUserByToken")
    @RequireLoginSession
    public SocialMediaUser getUserById() {
        String userUid = userProfileService.getUidFromToken();
        return socialMediaService
            .getUserByUid(UUID.fromString(userProfileService.getUidFromToken()));
    }

    //Todo: Add Business Logics
    @PutMapping("/updateProfilePicture")
    public String changeProfilePicture(@RequestParam("profilePicture") MultipartFile profilePicture)
        throws IOException {
        String profilePictureID = UUID.randomUUID().toString();
        socialMediaService.changeProfilePicture(profilePictureID, profilePicture.getBytes());
        return profilePictureID;
    }

    //Todo: Add Business Logics
    @PutMapping("/updateBannerPicture")
    public ResponseEntity<?> changeBannerPicture(@RequestParam("userName") String userName,
                                                 @RequestParam("bannerPicture") MultipartFile bannerPicture) {
        try {
            SocialMediaUser user = socialMediaService.changeBannerPicture(userName, bannerPicture.getBytes());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not logged in");
        }
    }

    @PostMapping("/changeUserName")
    @RequireLoginSession
    public SocialMediaUser changeUserName(@RequestBody @Valid SocialMediaUser user) {
        String userUid = userProfileService.getUidFromToken();
        System.out.println("userUid...."+userUid);
        return socialMediaRegistrationValidator.validateNameChangeCount(UUID.fromString(userUid)) ?
            socialMediaService.changeUserName(user) : null;
    }

    @RequireLoginSession
    @PutMapping("/activateAccount")
    public SocialMediaUser activateAccount() {
        String userUid = userProfileService.getUidFromToken();
        return socialMediaService.activateAccount(UUID.fromString(userUid));

    }

    @RequireLoginSession
    @PutMapping("/deactivateAccount")
    public SocialMediaUser deactivateAccount() {
        String userUid = userProfileService.getUidFromToken();
        return socialMediaService.deactivateAccount(UUID.fromString(userUid));
    }

    @RequireLoginSession
    @DeleteMapping
    public void deleteAccount() {
        String userUid = userProfileService.getUidFromToken();
        socialMediaService.deleteAccount(UUID.fromString(userUid));
    }

}

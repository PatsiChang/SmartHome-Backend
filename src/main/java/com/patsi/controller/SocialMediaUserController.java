package com.patsi.controller;


import com.patsi.bean.SocialMediaUser;
import com.patsi.enums.AccountStatus;
import com.patsi.service.SocialMediaService;
import com.patsi.service.UserProfileService;
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
    Logger log = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private SocialMediaService socialMediaService;
    @Autowired
    private UserProfileService userProfileService;

    @PostMapping
    public SocialMediaUser createSocialMediaAccount(@RequestBody SocialMediaUser user,
                                                    @RequestHeader("Authorization") String token) {
        user.setUid(UUID.fromString(userProfileService.getUidFromToken(token)));
        return socialMediaService.createSocialMediaAccount(user);
    }

    @PostMapping("/getUserByToken")
    public SocialMediaUser getUserById(@RequestHeader("Authorization") String token) {
        return socialMediaService.getUserByUid(UUID.fromString(userProfileService.getUidFromToken(token)));
    }

    @PutMapping("/updateProfilePicture")
    public String changeProfilePicture(@RequestParam("profilePicture") MultipartFile profilePicture)
        throws IOException {
        String profilePictureID = UUID.randomUUID().toString();
        socialMediaService.changeProfilePicture(profilePictureID, profilePicture.getBytes());
        return profilePictureID;
    }

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


    @GetMapping("/getAllUser")
    public List<SocialMediaUser> getAllUserById() {
        return socialMediaService.getAllUser();
    }

    @PutMapping("/deactivateAccount")
    public AccountStatus deactivateAccount(@RequestParam SocialMediaUser user) {
        return socialMediaService.deactivateAccount(user);
    }

    @DeleteMapping
    public void deleteAccount(@RequestParam UUID uid) {
        socialMediaService.deleteAccount(uid);
    }


}

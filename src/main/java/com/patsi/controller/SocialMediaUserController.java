package com.patsi.controller;


import com.patsi.annotations.RequireLoginSession;
import com.patsi.bean.SocialMediaUser;
import com.patsi.enums.AccountStatus;
import com.patsi.service.socialMedia.SocialMediaService;
import com.patsi.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/socialMedia")
@CrossOrigin
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SocialMediaUserController {

    private final SocialMediaService socialMediaService;
    private final UserProfileService userProfileService;

    @PostMapping
    @RequireLoginSession
    public SocialMediaUser createSocialMediaAccount(@RequestBody SocialMediaUser user) {
        user.setUid(UUID.fromString(userProfileService.getUidFromToken()));
        return socialMediaService.createSocialMediaAccount(user);
    }

    @GetMapping("/getUserByToken")
    @RequireLoginSession
    public SocialMediaUser getUserById() {
        return socialMediaService.getUserByUid(UUID.fromString(userProfileService.getUidFromToken()));
    }

    @PutMapping("/updateProfilePicture")
    public String changeProfilePicture(@RequestParam("profilePicture") MultipartFile profilePicture)
        throws IOException {
        String profilePictureID = UUID.randomUUID().toString();
        socialMediaService.changeProfilePicture(profilePictureID, profilePicture.getBytes());
        return profilePictureID;
    }

    @PutMapping("/updateBannerPicture")
    public ResponseEntity<Object> changeBannerPicture(@RequestParam("userName") String userName,
                                                      @RequestParam("bannerPicture") MultipartFile bannerPicture) {
        try {
            return ResponseEntity.ok(socialMediaService.changeBannerPicture(userName, bannerPicture.getBytes()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not logged in");
        }
    }

    @PutMapping("/deactivateAccount")
    public SocialMediaUser deactivateAccount(@RequestParam SocialMediaUser user) {
        return socialMediaService.deactivateAccount(user.getUid());
    }

    @DeleteMapping
    public void deleteAccount(@RequestParam UUID uid) {
        socialMediaService.deleteAccount(uid);
    }

}

package com.patsi.controller;


import com.patsi.bean.SocialMediaUser;
import com.patsi.enums.AccountStatus;
import com.patsi.service.SocialMediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @PostMapping
    public void createSocialMediaAccount(@RequestBody SocialMediaUser user) {
        log.info("Inside Controller Edit user Profile");
        log.info("Inside Controller Edit user Profile User: "+ user);
        user.setUid(socialMediaService.getUserUid());
        socialMediaService.createSocialMediaAccount(user);
    }

    @PutMapping("updateProfilePicture")
    public ResponseEntity<?> changeProfilePicture(@RequestParam("userName") String userName,
                                                  @RequestParam("profilePicture") MultipartFile profilePicture) {
        try {
            SocialMediaUser user = socialMediaService.changeProfilePicture(userName, profilePicture.getBytes());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not logged in");
        }
    }

    @PutMapping("updateBannerPicture")
    public ResponseEntity<?> changeBannerPicture(@RequestParam("userName") String userName,
                                                 @RequestParam("bannerPicture") MultipartFile bannerPicture) {
        try {
            SocialMediaUser user = socialMediaService.changeBannerPicture(userName, bannerPicture.getBytes());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not logged in");
        }
    }

    @GetMapping
    public SocialMediaUser getUserById(@RequestParam SocialMediaUser user) {
        return socialMediaService.getUser(user);
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
    public void deleteAccount(@RequestParam UUID uid){
        socialMediaService.deleteAccount(uid);
    }


}

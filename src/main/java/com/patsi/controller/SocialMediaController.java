package com.patsi.controller;


import com.patsi.bean.Recipe;
import com.patsi.bean.SocialMediaUser;
import com.patsi.database.repository.SocialMediaRepository;
import com.patsi.service.SocialMediaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/socialMedia")
@CrossOrigin
public class SocialMediaController {
    Logger log = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private SocialMediaService socialMediaService;

    @PostMapping
    public void registerRecipe(@RequestBody SocialMediaUser user) {
        log.info("Inside Controller Edit user Profile");
        socialMediaService.editSocialMediaAccount(user);
    }

    @GetMapping
    public SocialMediaUser getUserById(@RequestParam SocialMediaUser user) {
        return socialMediaService.getUser(user);
    }

    @PutMapping
    public boolean deactivateAccount(@RequestParam SocialMediaUser user) {
        return socialMediaService.deactivateAccount(user);
    }


}

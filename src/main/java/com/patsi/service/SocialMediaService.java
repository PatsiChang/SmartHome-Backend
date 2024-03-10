package com.patsi.service;

import com.patsi.bean.SocialMediaUser;
import com.patsi.database.repository.SocialMediaRepository;
import com.patsi.interceptors.LoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialMediaService {
    Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Autowired
    private SocialMediaRepository socialMediaRepository;

    //Edit User Profile Picture
    public void editSocialMediaAccount(SocialMediaUser user) {
        socialMediaRepository.save(user);
    }

    //Get one Existing User
    public SocialMediaUser getUser(SocialMediaUser user) {
        return socialMediaRepository.findByUid(user.getUid()).orElse(null);
    }

    //Get All Existing Users
    public List<SocialMediaUser> getAllUser() {
        return socialMediaRepository.findAll();

    }

    //Delete GroceryItem
    public boolean deactivateAccount(SocialMediaUser user) {
        user.setAccountStatus(false);
        return user.isAccountStatus();
    }

}

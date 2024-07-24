package com.patsi.validator;

import com.patsi.bean.SocialMediaUser;
import com.patsi.database.repository.SocialMediaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class SocialMediaRegistrationValidator {

    @Autowired
    private SocialMediaRepository socialMediaRepository;

    public boolean validateAccountExisting(UUID uid) {
        System.out.println("in validation");
        return socialMediaRepository.findByUid(uid).isEmpty();
    }

    public boolean validateNameChangeCount(UUID uid) {
        SocialMediaUser user = socialMediaRepository.findByUid(uid).orElse(null);
        if (user != null && user.getUserNameChangeCount() == 0) {
            return true;
        }
        return false;
    }


}

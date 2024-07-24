package com.patsi.service.socialMedia;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SocialMediaEnvValueService {
    @Value("${com.patsi.socialMedia.profilePicture.path}")
    private String IMAGE_PATH_ProfilePicture;
    @Value("${com.patsi.socialMedia.bannerPicture.path}")
    private String IMAGE_PATH_BannerPicture;

    public String getSocialMediaProfilePicturePath() {
        return IMAGE_PATH_ProfilePicture;
    }
    public String getSocialMediaBannerPicturePath() {
        return IMAGE_PATH_BannerPicture;
    }
}

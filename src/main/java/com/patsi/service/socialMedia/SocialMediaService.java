package com.patsi.service.socialMedia;

import com.common.validation.service.MaskingService;
import com.patsi.bean.SocialMediaUser;
import com.patsi.configuration.SocialMediaEnvValueConfig;
import com.patsi.database.repository.SocialMediaRepository;
import com.patsi.enums.AccountStatus;
import com.patsi.exceptions.UserNotFoundException;
import com.patsi.utils.FileHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SocialMediaService {
    private final SocialMediaRepository socialMediaRepository;
    private final SocialMediaEnvValueConfig socialMediaEnvValueConfig;
    private final MaskingService maskingService;

    //Todo: Perhaps not return null/ change to Optional??
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
        FileHelper.newFile(socialMediaEnvValueConfig.profilePicturePath(),
            profilePictureID, profilePicture);
    }

    //Todo: Fix method after banner field is created on front end, Add Image Filtering
    public SocialMediaUser changeBannerPicture(String userName, byte[] bannerPicture)
        throws IOException {
        if (userName == null || userName.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank.");
        }
        if (bannerPicture == null || bannerPicture.length == 0) {
            throw new IllegalArgumentException("Banner picture data cannot be null or empty.");
        }
        FileHelper.newFile(socialMediaEnvValueConfig.bannerPicturePath(), userName, bannerPicture);
        return socialMediaRepository
            .findByUserName(userName)
            .map(user -> {
                user.setBannerPicture(userName);
                return saveUserToDb(user);
            })
            .orElseThrow(() -> new UserNotFoundException("User" + userName + "not found!"));
    }

    public SocialMediaUser getUserByUid(UUID uid) {
        return maskUid((SocialMediaUser) maskingService.maskSensitiveFields(
            socialMediaRepository
                .findByUid(uid)
                .orElse(null)));
    }

    public SocialMediaUser getUserByUserName(String userName) {
        return maskUid((SocialMediaUser) maskingService.maskSensitiveFields(
            socialMediaRepository
                .findByUserName(userName)
                .orElse(null)));
    }

    //Get All Existing Users
    public List<SocialMediaUser> getAllActiveUsers() {
        return socialMediaRepository.findAll().stream()
            .filter(user -> user.getAccountStatus().equals(AccountStatus.Active))
            .map(user -> (SocialMediaUser) maskingService.maskSensitiveFields(user))
            .map(this::maskUid)
            .toList();
    }

    //UpdateUserAccount
    public SocialMediaUser updateSocialMediaAccount(SocialMediaUser user) {
        return saveUserToDb(SocialMediaUser.builder()
            .uid(user.getUid())
            .displayName(user.getDisplayName())
            .biography(user.getBiography())
            .accountType(user.getAccountType())
            .build());
    }

    public SocialMediaUser changeUserName(SocialMediaUser user) {
        user.setUserNameChangeCount(1);
        return saveUserToDb(user);
    }

    public SocialMediaUser activateAccount(UUID uid) {
        return socialMediaRepository
            .findByUid(uid)
            .map(user -> {
                user.setAccountStatus(AccountStatus.Active);
                return saveUserToDb(user);
            })
            .orElseThrow(() -> new UserNotFoundException("Uid " + uid + " not found!"));
    }

    public SocialMediaUser deactivateAccount(UUID uid) {
        return socialMediaRepository
            .findByUid(uid)
            .map(user -> {
                user.setAccountStatus(AccountStatus.Deactivate);
                return saveUserToDb(user);
            })
            .orElseThrow(() -> new UserNotFoundException("Uid " + uid + " not found!"));
    }

    //Todo: Add more business logic
    @Transactional
    public void deleteAccount(UUID uid) {
        socialMediaRepository.deleteByUid(uid);
    }

}

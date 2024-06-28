package com.patsi.bean;

import com.patsi.enums.AccountStatus;
import com.patsi.enums.AccountType;
import com.patsi.enums.RecipeCategories;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SocialMediaUser implements Serializable {
    @Id
    private UUID uid;
    //For people to search
    private String userName;
    private int userNameChangeCount;
    private String displayName;
    private String email;
    private String profilePicture;
    private String bannerPicture;
    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private AccountStatus accountStatus = AccountStatus.Inactive;
    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private AccountType accountType = AccountType.privateAccount;
    private String biography;
    @Builder.Default
    private int followersCount = 0;
    @Builder.Default
    private int followingCount = 0;
    private List<Recipe> displayedRecipes;
    private List<Recipe> showcasedRecipes;
    private List<Recipe> savedRecipes;
    @Enumerated(EnumType.ORDINAL)
    private List<RecipeCategories> userInterest;

}

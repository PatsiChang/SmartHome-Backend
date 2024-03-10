package com.patsi.bean;

import com.patsi.enums.AccountStatus;
import com.patsi.enums.AccountType;
import com.patsi.enums.RecipeCategories;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
public class SocialMediaUser {
    @Id
    private UUID uid;
    //For people to search
    private String userName;
    private int userNameChangeCount;
    private String displayName;
    private String profilePicture;
    private String BannerPicture;
    @Enumerated(EnumType.ORDINAL)
    private AccountStatus accountStatus = AccountStatus.Inactive;
    @Enumerated(EnumType.ORDINAL)
    private AccountType accountType = AccountType.privateAccount;
    private String biography;
    private int followersCount;
    private int followingCount;
    private List<Recipe> displayedRecipes;
    private List<Recipe> showcasedRecipes;
    private List<Recipe> savedRecipes;
    @Enumerated(EnumType.ORDINAL)
    private List<RecipeCategories> userInterest;

}

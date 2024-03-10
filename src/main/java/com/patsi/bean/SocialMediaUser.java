package com.patsi.bean;

import com.patsi.enums.AccountType;
import com.patsi.enums.RecipeCategories;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialMediaUser {
    @Id
    private UUID uid;
    //For people to search
    private String userName;
    private String displayName;
    private String profilePicture;
    private String BannerPicture;
    private boolean accountStatus = false;
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

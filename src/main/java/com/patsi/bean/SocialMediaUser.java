package com.patsi.bean;

import com.common.validation.annotations.IsDisplayFields;
import com.common.validation.annotations.IsEmail;
import com.patsi.enums.AccountStatus;
import com.patsi.enums.AccountType;
import com.patsi.enums.RecipeCategories;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull
    private String userName;
    private int userNameChangeCount;
    @Size(min = 4, max = 30)
    @NotNull
    private String displayName;
    @IsEmail
    private String email;
    private String profilePicture;
    private String bannerPicture;
    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private AccountStatus accountStatus = AccountStatus.Inactive;
    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private AccountType accountType = AccountType.privateAccount;
    @IsDisplayFields
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

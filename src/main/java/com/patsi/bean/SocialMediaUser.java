package com.patsi.bean;

import com.patsi.enums.RecipeCategories;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private boolean accountType;
    private String biography;
    //Stores the userName of the followers accounts
    private List<String> followersUsers;
    //Stores the userName of the followed accounts
    private List<String> followedUsers;
    private List<Recipe> displayedRecipes;
    private List<Recipe> showcasedRecipes;
    private List<Recipe> savedRecipes;
    private List<RecipeCategories> userInterest;

}

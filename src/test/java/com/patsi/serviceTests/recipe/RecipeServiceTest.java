package com.patsi.serviceTests.recipe;

import com.common.validation.service.MaskingService;
import com.patsi.MainApplication;
import com.patsi.bean.Ingredient;
import com.patsi.bean.Recipe;
import com.patsi.database.repository.RecipeRepository;
import com.patsi.enums.RecipeType;
import com.patsi.service.recipe.RecipeEnvValueService;
import com.patsi.service.recipe.RecipeService;
import com.patsi.service.UserProfileService;
import com.patsi.utils.FileHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.Assert.assertEquals;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = MainApplication.class)
@ActiveProfiles("test")
public class RecipeServiceTest {

    @InjectMocks
    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private UserProfileService userProfileService;
    @Mock
    private RecipeEnvValueService recipeEnvValueService;
    @Mock
    private MaskingService maskingService;

    final String validUid = "validUid";
    final UUID validRecipeId = UUID.randomUUID();
    final String invalidUid = "invalidUid";
    final String IMAGE_STAGED_PATH = "MOCK_IMAGE_STAGED_PATH";
    final String IMAGE_PATH = "MOCK_IMAGE_PATH";
    final UUID validUUID = UUID.randomUUID();

    final List<Ingredient> validIngredientList = new ArrayList<>();
    final List<String> validStepList = List.of("firstStep", "secondStep");
    final Recipe validRecipe = new Recipe("RecipeName", RecipeType.DESSERT,
        validIngredientList, validStepList);
    final List<Recipe> validRecipeList = List.of(validRecipe);
    final List<Recipe> emptyRecipeList = List.of();

    @BeforeEach
    void setUp() {
        when(recipeRepository.findByUid(validUid))
            .thenReturn(validRecipeList);
        when(recipeRepository.findByUid(invalidUid))
            .thenReturn(emptyRecipeList);
        when(recipeRepository.save(any()))
            .thenReturn(validRecipe);
        when(recipeEnvValueService.getRecipeFileFlag())
            .thenReturn(false);
        when(maskingService.maskSensitiveFields(validRecipe))
            .thenReturn(validRecipe);
    }

    //getRecipe()
    @Test
    void testGetRecipeWithValidUid() {
        assertEquals(validRecipeList, recipeService.getRecipe(validUid));
    }

    @Test
    void testGetRecipeWithInvalidUid() {
        assertEquals(emptyRecipeList, recipeService.getRecipe(invalidUid));
        verify(recipeRepository).findByUid(invalidUid);
    }

    //registerRecipe
    @Test
    void testRegisterRecipe() throws IOException {
        RecipeService recipeServiceSpy = spy(new RecipeService());
        validRecipe.setRecipeID(validUUID);
        Object result = recipeService.registerRecipe(validRecipe, validUid);
        assertEquals(true, result instanceof UUID);
        verify(recipeRepository, times(1)).save(validRecipe);
    }

    //updateRecipe
    @Test
    void testUpdateRecipe() {
        recipeService.updateRecipe(validRecipe, validUid);
        verify(recipeRepository).save(validRecipe);
    }

    //deleteRecipe
    @Test
    void testDeleteRecipeWithValidToken() {
        when(userProfileService.getUidFromToken())
            .thenReturn(validUid);
        validRecipe.setRecipeID(validRecipeId);
        validRecipe.setUid(validUUID.toString());
        validRecipe.setImgURL(validRecipeId.toString());
        when(recipeRepository.findByRecipeID(validRecipe.getRecipeID()))
            .thenReturn(Optional.of(validRecipe));
        recipeService.updateRecipe(validRecipe, validUid);
        recipeService.deleteRecipe(validRecipe);
        verify(userProfileService).getUidFromToken();
        verify(recipeRepository).deleteById(validRecipeId);
//        try (MockedStatic<FileHelper> fileHelper = Mockito.mockStatic(FileHelper.class)) {
//            fileHelper.when(() -> FileHelper.deleteFile(IMAGE_PATH, validRecipe.getImgURL())).thenReturn(true);
//            verify(FileHelper.class, times(1));
//        }

    }

    @Test
    void testDeleteRecipeWithInvalidToken() {
        when(userProfileService.getUidFromToken())
            .thenReturn(invalidUid);
        validRecipe.setRecipeID(validRecipeId);
        validRecipe.setUid(validUUID.toString());
        validRecipe.setImgURL(validRecipeId.toString());
        when(recipeRepository.findByRecipeID(validRecipe.getRecipeID()))
            .thenReturn(Optional.of(validRecipe));
        recipeService.updateRecipe(validRecipe, validUid);
        recipeService.deleteRecipe(validRecipe);
        verify(userProfileService, times(1)).getUidFromToken();
        verify(recipeRepository, never()).deleteById(validRecipeId);

    }
}

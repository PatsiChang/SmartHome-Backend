import com.patsi.MainApplication;
import com.patsi.bean.Recipe;
import com.patsi.database.repository.RecipeRepository;
import com.patsi.enums.RecipeType;
import com.patsi.service.RecipeService;
import com.patsi.service.UserProfileService;
import com.patsi.utils.FileHelper;
import com.patsi.utils.ListHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.junit.Assert.assertEquals;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
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

    private MockedStatic<ListHelper> mockDateHelper = mockStatic(ListHelper.class);
    private MockedStatic<FileHelper> mockFileHelper = mockStatic(FileHelper.class);


    final String validUid = "validUid";
    final UUID validRecipeId = UUID.randomUUID();
    final String invalidUid = "invalidUid";
    final String IMAGE_STAGED_PATH = "MOCK_IMAGE_STAGED_PATH";
    final String IMAGE_PATH = "MOCK_IMAGE_PATH";
    final UUID validUUID = UUID.randomUUID();

    final List<Recipe.Ingredient> validIngredientList = ListHelper.newListWithParam(
                                                    new Recipe.Ingredient("ingredientName",
                                                    "ingredientAmount"));
    final List<String> validStepList = ListHelper.newListWithParam("firstStep", "secondStep");
    final Recipe validRecipe = new Recipe("RecipeName", RecipeType.DESSERT,
        validIngredientList, validStepList);
    final List<Recipe> validRecipeList = ListHelper.newListWithParam(validRecipe);
    final List<Recipe> emptyRecipeList = ListHelper.newList();

    @BeforeEach
    void setUp(){
        when(recipeRepository.findByUid(validUid))
            .thenReturn(validRecipeList);
        when(recipeRepository.findByUid(invalidUid))
            .thenReturn(validRecipeList);
        when(recipeRepository.save(any()))
            .thenReturn(validRecipe);
    }

    //getRecipe()
    @Test
    void testGetRecipeWithValidUid() {
        assertEquals(validRecipeList, recipeService.getRecipe(validUid));
        verify(recipeRepository).findByUid(validUid);
    }
    @Test
    void testGetRecipeWithInvalidUid() {
        assertEquals(emptyRecipeList, recipeService.getRecipe(invalidUid));
        verify(recipeRepository).findByUid(invalidUid);
    }

    //registerRecipe
    @Test
    void testRegisterRecipe() throws IOException {
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
        verify(FileHelper.class, times(1));
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
        verify(userProfileService).getUidFromToken();
        verify(recipeRepository, never()).deleteById(validRecipeId);
        verify(FileHelper.class, never());
    }
}

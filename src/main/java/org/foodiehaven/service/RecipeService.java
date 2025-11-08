package org.foodiehaven.service;

import org.foodiehaven.dto.RecipeDTO;
import org.foodiehaven.dto.RecipeResponse;
import org.foodiehaven.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private SpoonacularService spoonacularService;
    private RecipeRepository recipeRepository; // For caching later

    public RecipeService(SpoonacularService spoonacularService, RecipeRepository recipeRepository) {
        this.spoonacularService = spoonacularService;
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeResponse> searchAndAnalyze(String query) {
        List<RecipeDTO> apiRecipes = spoonacularService.searchRecipes(query);
        return apiRecipes.stream()
                .map(this::analyzeAndMap)
                .collect(Collectors.toList());
    }

    private RecipeResponse analyzeAndMap(RecipeDTO recipe) {
        List<String> analysis = analyzeTasteProfile(recipe.getIngredients());

        // We could also save this to our database (caching)
        // Recipe recipeEntity = new Recipe();
        // recipeEntity.setTitle(recipe.getTitle());
        // recipeEntity.setIngredients(recipe.getIngredients());
        // recipeRepository.save(recipeEntity);

        return new RecipeResponse(recipe.getTitle(), analysis, recipe.getDescription(), recipe.getCookingTime());
    }

    // This is the placeholder for your core idea!
    private List<String> analyzeTasteProfile(List<String> ingredients) {
        // Simple example based on your "umami" idea
        List<String> analysis = new ArrayList<>();
        boolean hasUmami = ingredients.stream().anyMatch(ing ->
                ing.contains("mushroom") ||
                        ing.contains("soy sauce") ||
                        ing.contains("parmesan")
        );

        if (hasUmami) {
            analysis.add("Positive: High in Umami") ; // This is the "green bubble"
        } else {
            analysis.add("Neutral: Standard Flavor Profile"); // This is the "red/grey bubble"
        }
        return analysis;
    }
}
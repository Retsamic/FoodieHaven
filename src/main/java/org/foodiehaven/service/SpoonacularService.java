package org.foodiehaven.service;

import org.foodiehaven.dto.RecipeDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate; // Or WebClient
import java.util.Collections;
import java.util.List;

@Service
public class SpoonacularService {

    @Value("${spoonacular.api.key}")
    private String apiKey;

    public List<RecipeDTO> searchRecipes(String query) {
        System.out.println("Pretending to call Spoonacular API for: " + query);

        // --- Placeholder Logic ---
        // In a real app, you'd use RestTemplate or WebClient to call:
        // "https://api.spoonacular.com/recipes/complexSearch?query=" + query + "&apiKey=" + apiKey

        // For now, let's return a fake list to test our logic
        RecipeDTO fakeRecipe1 = new RecipeDTO();
        fakeRecipe1.setTitle(query + " with Mushrooms");
        fakeRecipe1.setIngredients(List.of("chicken", "mushrooms", "cream", "soy sauce"));

        RecipeDTO fakeRecipe2 = new RecipeDTO();
        fakeRecipe2.setTitle(query + " (Plain)");
        fakeRecipe2.setIngredients(List.of("chicken", "salt", "pepper"));

        return List.of(fakeRecipe1, fakeRecipe2);
    }
}
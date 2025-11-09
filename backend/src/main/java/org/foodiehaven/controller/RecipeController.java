package org.foodiehaven.controller;

// --- MAKE SURE THESE ARE IMPORTED ---
import org.foodiehaven.dto.RecipeResponse;
import org.foodiehaven.dto.FullRecipeInfoDTO;
import org.foodiehaven.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<RecipeResponse>> searchRecipes(@RequestParam String query) {
        List<RecipeResponse> results = recipeService.searchAndAnalyze(query);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullRecipeInfoDTO> getRecipeDetails(@PathVariable Long id) {
        FullRecipeInfoDTO details = recipeService.getRecipeDetails(id);
        if (details != null) {
            return ResponseEntity.ok(details);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
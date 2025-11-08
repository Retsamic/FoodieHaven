package org.foodiehaven.controller;

import org.foodiehaven.dto.RecipeResponse;
import org.foodiehaven.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/recipes")
// @CrossOrigin(origins = "http://localhost:4200") // Uncomment this when you add your Angular frontend
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
}
package org.foodiehaven.service;

import org.foodiehaven.dto.FullRecipeInfoDTO;
import org.foodiehaven.dto.RecipeResponse;
import org.foodiehaven.dto.SpoonacularRecipeDTO;
import org.foodiehaven.dto.TasteWidgetDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private SpoonacularService spoonacularService;

    public RecipeService(SpoonacularService spoonacularService) {
        this.spoonacularService = spoonacularService;
    }

    public List<RecipeResponse> searchAndAnalyze(String query) {

        List<SpoonacularRecipeDTO> apiRecipes = spoonacularService.searchRecipes(query);

        return apiRecipes.stream()
                .map(this::analyzeAndMap)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private RecipeResponse analyzeAndMap(SpoonacularRecipeDTO recipe) {
        TasteWidgetDTO taste = spoonacularService.getTasteProfile(recipe.getId());

        if (taste == null) {
            return null;
        }

        List<String> analysis = analyzeTasteProfile(taste);

        return new RecipeResponse(recipe.getTitle(), analysis, recipe.getSummary(), recipe.getId());
    }

    private List<String> analyzeTasteProfile(TasteWidgetDTO taste) {
        List<String> analysis = new ArrayList<>();

        if (taste.getSavoriness() > 25) {
            analysis.add("Positive: High in Umami!");
        }

        if (taste.getSpiciness() > 500) {
            analysis.add("Spicy: Kicks a punch!");
        }

        if (taste.getSweetness() > 30) {
            analysis.add("Positive: Pleasantly Sweet");
        }

        if (taste.getFattiness() > 60 && taste.getSaltiness() > 20) {
            analysis.add("Positive: Rich & Savory");
        }

        if (taste.getSourness() > 20) {
            analysis.add("Positive: Bright & Sour");
        }

        if (analysis.isEmpty()) {
            analysis.add("Neutral: Balanced Profile");
        }

        return analysis;
    }

    public FullRecipeInfoDTO getRecipeDetails(Long id) {
        FullRecipeInfoDTO fullInfo = spoonacularService.getFullRecipeInfo(id);

        if (fullInfo == null) {
            return null;
        }
        return fullInfo;
    }


}
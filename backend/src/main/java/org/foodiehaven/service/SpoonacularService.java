package org.foodiehaven.service;

import org.foodiehaven.dto.FullRecipeInfoDTO;
import org.foodiehaven.dto.SpoonacularRecipeDTO;
import org.foodiehaven.dto.SpoonacularSearchResponse;
import org.foodiehaven.dto.TasteWidgetDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class SpoonacularService {

    @Value("${spoonacular.api.key}")
    private String apiKey;
    private RestTemplate restTemplate;

    public SpoonacularService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String SEARCH_URL = "https://api.spoonacular.com/recipes/complexSearch";
    private static final String TASTE_URL = "https://api.spoonacular.com/recipes/{id}/tasteWidget.json";


    public List<SpoonacularRecipeDTO> searchRecipes(String query) {
        System.out.println("Calling REAL Spoonacular API for: " + query);

        String urlToCall = UriComponentsBuilder.fromHttpUrl(SEARCH_URL)
                .queryParam("apiKey", apiKey)
                .queryParam("query", query)
                .queryParam("addRecipeInformation", true)
                .queryParam("addRecipeNutrition", true)
                .toUriString();

        try {
            SpoonacularSearchResponse response = restTemplate.getForObject(urlToCall, SpoonacularSearchResponse.class);

            if (response == null || response.getResults() == null) {
                return Collections.emptyList();
            }

            // Return the full, rich list
            return response.getResults();

        } catch (Exception e) {
            System.err.println("Error calling Spoonacular API: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public TasteWidgetDTO getTasteProfile(Long recipeId) {
        System.out.println("Getting taste profile for ID: " + recipeId);

        String urlToCall = UriComponentsBuilder.fromHttpUrl(TASTE_URL)
                .queryParam("apiKey", apiKey)
                .buildAndExpand(recipeId) // This inserts the recipeId into the {id} placeholder
                .toUriString();

        try {
            return restTemplate.getForObject(urlToCall, TasteWidgetDTO.class);
        } catch (Exception e) {
            System.err.println("Error getting taste widget for ID " + recipeId + ": " + e.getMessage());
            return null;
        }
    }
    private static final String INFO_URL = "https://api.spoonacular.com/recipes/{id}/information";

    public FullRecipeInfoDTO getFullRecipeInfo(Long recipeId) {
        System.out.println("Getting full information for ID: " + recipeId);

        String urlToCall = UriComponentsBuilder.fromHttpUrl(INFO_URL)
                .queryParam("apiKey", apiKey)
                .queryParam("includeNutrition", true)
                .buildAndExpand(recipeId)
                .toUriString();

        try {
            return restTemplate.getForObject(urlToCall, FullRecipeInfoDTO.class);
        } catch (Exception e) {
            System.err.println("Error getting full info for ID " + recipeId + ": " + e.getMessage());
            return null;
        }
    }
}
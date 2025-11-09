package org.foodiehaven.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpoonacularRecipeDTO {
    private Long id;
    private String title;
    private String image;
    private Integer readyInMinutes;
    private Integer servings;
    private String summary;

    private SpoonacularNutritionDTO nutrition;
    private List<SpoonacularIngredientDTO> extendedIngredients;
}
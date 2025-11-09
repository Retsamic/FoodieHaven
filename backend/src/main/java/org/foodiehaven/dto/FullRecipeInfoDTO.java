package org.foodiehaven.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FullRecipeInfoDTO {
    private Long id;
    private String title;
    private String summary;
    private String instructions;
    private List<org.foodiehaven.dto.AnalyzedInstructionDTO> analyzedInstructions;
    private List<org.foodiehaven.dto.ExtendedIngredientDTO> extendedIngredients;
    private SpoonacularNutritionDTO nutrition;
}
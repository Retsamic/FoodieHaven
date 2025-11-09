package org.foodiehaven.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpoonacularNutrientDTO {
    private String name;
    private double amount;
    private String unit;
}
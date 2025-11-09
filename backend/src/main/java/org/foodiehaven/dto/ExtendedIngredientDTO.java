package org.foodiehaven.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtendedIngredientDTO {
    private Long id;
    private String original;
    private String name;
    private double amount;
    private String unit;
}
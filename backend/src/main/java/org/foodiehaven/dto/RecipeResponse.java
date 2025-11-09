package org.foodiehaven.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecipeResponse {
    private String title;
    private List<String> tasteProfileAnalysis;
    private String description;
    private long id;
}
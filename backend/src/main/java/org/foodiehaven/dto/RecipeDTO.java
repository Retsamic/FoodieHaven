package org.foodiehaven.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class RecipeDTO {
    @NotNull(message = "Title cannot be empty")
    private String title;
    @NotNull(message = "Ingredients cannot be empty")
    private List<String> ingredients;
    @NotNull(message = "Flavor cannot be empty")
    private List<String> flavor;
    private String description;
}
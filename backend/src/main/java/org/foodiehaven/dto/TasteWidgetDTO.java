package org.foodiehaven.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TasteWidgetDTO {
    private double sweetness;
    private double saltiness;
    private double sourness;
    private double bitterness;
    private double savoriness;
    private double fattiness;
    private double spiciness;
}
package org.foodiehaven.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalyzedInstructionDTO {
    private List<org.foodiehaven.dto.AnalyzedStepDTO> steps;
}
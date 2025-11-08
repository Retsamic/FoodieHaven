package org.foodiehaven.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data // Creates getters, setters, toString(), equals(), and hashCode()
@NoArgsConstructor // Creates a no-argument constructor
@AllArgsConstructor // Creates a constructor with all arguments
@Entity // Tells Spring this is a database entity
@Table(name = "recipes") // Optional: specifies the table name
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the ID
    private Long id;
    @Column(nullable = false)
    private String title;
    private String description;
    private Integer prepTimeMinutes;
    @ElementCollection
    private List<String> ingredients;
    private String flavorComponent;
    private String imageUrl;
    private String cookingTime;
}
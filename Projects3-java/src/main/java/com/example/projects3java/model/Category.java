package com.example.projects3java.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    @NotEmpty(message = " Id Category must not be empty")
    private String id;
    @NotEmpty(message = "Name Category must not be empty")
    @Size(min = 3)
    private String nameCategory;
}

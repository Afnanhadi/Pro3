package com.example.projects3java.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @NotEmpty(message = "Id product must not be empty")
    private String id;
    @NotEmpty(message = " Name product must not be empty")
    @Size(min = 3)
    private String name;
    @NotNull(message = "Price must not be empty")
    @Positive
    private double price;
    @NotNull(message = "Category id must not be empty")
    private  String categoryID;
}

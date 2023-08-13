package com.example.projects3java.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {
    @NotEmpty(message = "Id Merchant must not be empty")
    private String id;
    @NotEmpty(message = "Name Merchant must not be empty")
    @Size(min = 3)
    private String nameMerchant;
}

package com.example.projects3java.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotEmpty(message = "Id MerchantStock must not empty")
    private String idMerchantStock;

    @NotEmpty(message = "Product id must not empty")
    private String productId;

    @NotEmpty(message = "Merchant id must not empty")
    private String merchantId;


    @NotNull(message = "Stock must not empty")
    @Min(10)
    private Integer stock;
}

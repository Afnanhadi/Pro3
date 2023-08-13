package com.example.projects3java.Controller;

import com.example.projects3java.ApiResponse.ApiResponse;
import com.example.projects3java.Service.ProductService;
import com.example.projects3java.model.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/Product")
@RequiredArgsConstructor
public class ControllerProduct {
    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getaProduct() {
        ArrayList<Product> products = productService.getProducts();
        return ResponseEntity.status(200).body(products);

    }
    @PostMapping("/add")
    private ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        productService.addProduct(product);
        return ResponseEntity.status(200).body(new ApiResponse("add product"));
    }
    @PutMapping("/update/{id}")
    private  ResponseEntity UpdateProduct(@PathVariable String id, @RequestBody Product product,@Valid Errors errors){
        if (errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return  ResponseEntity.status(400).body(message);
        }
       boolean isupdateProduct=productService.updateProduct(id,product);
        if (isupdateProduct){
        return ResponseEntity.status(200).body(new ApiResponse("update id"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found id"));
    }
    @DeleteMapping("/delete/{id}")
    private  ResponseEntity deleteProduct(@PathVariable String id){
     boolean isdeleteProduct= productService.deleteProduct(id);
     if(isdeleteProduct){
         return ResponseEntity.status(200).body(new ApiResponse("delete Product"));
     }
     return ResponseEntity.status(400).body(new ApiResponse("not found id"));
    }
}

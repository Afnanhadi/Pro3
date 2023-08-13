package com.example.projects3java.Controller;

import com.example.projects3java.ApiResponse.ApiResponse;
import com.example.projects3java.Service.MerchantStockService;
import com.example.projects3java.model.MerchantStock;
import com.example.projects3java.model.Product;
import com.example.projects3java.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Stack;

@RestController
@RequestMapping("/api/v1/MerchanStock")
@RequiredArgsConstructor
public class ControllerMerchanStock {

    private final MerchantStockService merchantStockService;
    @GetMapping("/get")
    public ResponseEntity getMerchanStock(){
        ArrayList<MerchantStock> merchantStocks=merchantStockService.getMerchantStocks();
        return ResponseEntity.status(200).body(merchantStocks);
    }
    @PostMapping("/add")
   public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body(new ApiResponse("add MerchantStock"));
   }
   @DeleteMapping("/delete/{id}")
   public ResponseEntity deleteMerchantStock(@PathVariable String id){
        boolean isdelete= merchantStockService.deleteMerchantStock(id);
        if (isdelete){
        return ResponseEntity.status(200).body(new ApiResponse("delete MerchantStock"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found id"));
   }
   @PutMapping("/update/{id}")
   public ResponseEntity updateMerchantStock(@PathVariable String id, @RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if (errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);

        }
        boolean isUpdate= merchantStockService.updateMerchantStock(id,merchantStock);
        if (isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("update MerchantStock"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found id"));
   }


    @PutMapping("/check/{merchantId}/{productId}/{amount}")
    public ResponseEntity check(@PathVariable String merchantId, @PathVariable String productId,@PathVariable Integer amount){
        boolean isCeck=merchantStockService.check(merchantId,productId,amount);
        if (isCeck){
            return ResponseEntity.status(200).body(new ApiResponse("Stock added successfully "));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The inventory was not added successfully"));
    }

}

package com.example.projects3java.Controller;

import com.example.projects3java.ApiResponse.ApiResponse;
import com.example.projects3java.Service.MerchantService;
import com.example.projects3java.model.Merchant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/Merchant")
@RequiredArgsConstructor
public class ControllerMerchant {

    public final MerchantService merchantService;
 @GetMapping("/get")
    public ResponseEntity getMerchant() {
        ArrayList<Merchant> merchants = merchantService.getMerchants();
        return ResponseEntity.status(200).body(merchants);
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody  @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("add merchant"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable String id, @RequestBody Merchant merchant, @Valid Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isupdateMerchant = merchantService.updateMerchant(id, merchant);
        if (isupdateMerchant) {
            return ResponseEntity.status(200).body(new ApiResponse("update Merchant"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found id"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id) {
        boolean isdelete = merchantService.deleteMerchant(id);
        if (isdelete) {
            return ResponseEntity.status(200).body(new ApiResponse("Delete Merchant"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found id"));
    }

}


package com.example.projects3java.Controller;

import com.example.projects3java.ApiResponse.ApiResponse;
import com.example.projects3java.Service.MerchantService;
import com.example.projects3java.Service.MerchantStockService;
import com.example.projects3java.Service.ProductService;
import com.example.projects3java.Service.UserService;
import com.example.projects3java.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class ControllerUser {

    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getAllUser() {
        ArrayList<User> users = userService.getAllUser();
        return ResponseEntity.status(200).body(users);

    }

    @PostMapping("/add")
    public ResponseEntity addUser (@RequestBody @Valid User user , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
}
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@RequestBody @Valid User user,@PathVariable String id, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdateUser=userService.updateUser(user,id);
        if (isUpdateUser){
            return ResponseEntity.status(200).body(new ApiResponse("Update User"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found id"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        boolean isDelete=userService.delete(id);
        if (isDelete){
            return ResponseEntity.status(200).body(new ApiResponse("delete User"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("not found user"));
    }
    @PostMapping("/buyProduct/{UserId}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@PathVariable String UserId, @PathVariable  String productId ,@PathVariable String merchantId,Integer Stock) {
        boolean isbuyProduct = userService.buyProduct(UserId);
          if (isbuyProduct) {
              return ResponseEntity.status(200).body(new ApiResponse("Purchase completed successfully"));
          }
          return ResponseEntity.status(400).body(new ApiResponse("The purchase is not completed"));




      }
  }

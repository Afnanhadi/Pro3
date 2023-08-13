package com.example.projects3java.Controller;

import com.example.projects3java.ApiResponse.ApiResponse;
import com.example.projects3java.Service.CategoryService;
import com.example.projects3java.model.Category;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Category")
public class ControllerCategory {
    public final CategoryService categoryService;
    public ResponseEntity getCategory(){
        ArrayList<Category> categories= categoryService.getCategories();
        return ResponseEntity.status(200).body(categories);
    }
    public ResponseEntity addCategory(@RequestBody  @Valid  Category category, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);

        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("Add Category"));

    }
    public ResponseEntity updateCategory(@RequestBody  @Valid Category category,String id,Errors errors ) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate = categoryService.updateCategory(id, category);
        if (isUpdate) {
            return ResponseEntity.status(200).body(new ApiResponse("Update category"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found id"));
    }
    public ResponseEntity deleteCategory(String id,Category category){
        boolean isdelete=categoryService.deleteCategory(id);
        if (isdelete){
            return ResponseEntity.status(200).body(new ApiResponse("delete category"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found id"));
    }

}

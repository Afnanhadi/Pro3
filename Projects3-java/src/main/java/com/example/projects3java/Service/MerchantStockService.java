package com.example.projects3java.Service;

import com.example.projects3java.model.MerchantStock;
import com.example.projects3java.model.Product;
import com.example.projects3java.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Stack;

@Service
public class MerchantStockService {
    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStock merchantStock) {
        merchantStocks.add(merchantStock);
    }

    public boolean deleteMerchantStock(String id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getIdMerchantStock().equals(id)) {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateMerchantStock(String id, MerchantStock merchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getIdMerchantStock().equals(id)) {
                merchantStocks.set(i, merchantStock);
                return true;
            }
        }
        return false;
    }

   public boolean check(String merchantId, String productId, Integer amount) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getMerchantId().equals(merchantId) && merchantStocks.get(i).getProductId().equals(productId)) {
                merchantStocks.get(i).setStock(merchantStocks.get(i).getStock() + amount);
                return true;
            }
        }
        return false;
    }

   public boolean buyProduct2(String productId , String merchantId ,Integer Stock ){
        for (int i= 0 ; i<merchantStocks.size(); i++){
            if (merchantStocks.get(i).getMerchantId().equals(merchantId) && merchantStocks.get(i).equals(productId)){
                merchantStocks.get(i).setStock(merchantStocks.get(i).getStock()-1);
                return true;
            }
        } return false;
    }
    public boolean getMerchantId(String merchantId){
        for (int i= 0; i<merchantStocks.size(); i++){
            if (merchantStocks.get(i).getMerchantId().equals(merchantId)){
                return true;
            }
        } return false;
    }
}
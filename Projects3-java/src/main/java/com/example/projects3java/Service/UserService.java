package com.example.projects3java.Service;



import com.example.projects3java.model.Merchant;
import com.example.projects3java.model.Product;
import com.example.projects3java.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
  private final ProductService productService;
  private final MerchantService merchantService;
  private final MerchantStockService merchantStockService;

    ArrayList<User>users=new ArrayList<>();

    public ArrayList<User> getAllUser() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);

    }
    public boolean updateUser(User user, String id){
        for (int i= 0; i<users.size(); i++){
            if (users.get(i).getUserId().equals(id)){
                users.set(i,user);
                return true;
            }
        }
        return false;
    }
    public boolean delete(String id){
        for (int i= 0; i<users.size(); i++){
            if (users.get(i).getUserId().equals(id)){
                users.remove(i);
                return true;
            }
        } return false;
    }



    public boolean buyProduct(String UserId){
        for (int i=0; i<users.size(); i++){
            if (users.get(i).getUserId().equals(UserId) ){
                if(users.get(i).getBalance() >= productService.getProducts().get(i).getPrice()){
                    users.get(i).setBalance(users.get(i).getBalance()- productService.getProducts().get(i).getPrice());
                    merchantStockService.getMerchantStocks().get(i).setStock(merchantStockService.getMerchantStocks().get(i).getStock()-1);
                    return true;
                }
            }
        }
        return false;
    }
    public  boolean getUserId(String UserId){
        for (int i= 0 ; i<users.size() ; i++){
            if (users.get(i).getUserId().equals(UserId)){
                return true;
            }
        }return false;
    }
}

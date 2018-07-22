/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.util.ArrayList;
/**
 *
 * @author sumi3
 */
public class CartDataDTO {
    private ArrayList<Integer> cartID = new ArrayList<Integer>(); 
    private int userID;//参照
    private ArrayList<String> itemCode = new ArrayList<String>();
    private ArrayList<Integer> itemNumber = new ArrayList<Integer>();
    
    public void setCartID(int cartID){
        this.cartID.add(cartID);
    }
    public int getCartID(int number){
        return cartID.get(number);
    }
    public void setUserID(int userID){
        this.userID = userID;
    }
    public int getUserID(){
        return userID;
    }
    public void setItemCode(String itemCode){
        this.itemCode.add(itemCode);
    }
    public String getItemCode(int number){
        return itemCode.get(number);
    }
    public void setItemNumber(int itemNumber){
        this.itemNumber.add(itemNumber);
    }
    public int getItemNumber(int number){
        return itemNumber.get(number);
    }
    public int getItemSize(){
        return itemCode.size();//ない場合0が返る
    }    
}

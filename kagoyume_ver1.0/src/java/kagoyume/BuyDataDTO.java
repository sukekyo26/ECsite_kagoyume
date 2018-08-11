 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author sumi3
 */
public class BuyDataDTO {
    private int userID;//ユーザーID
    private ArrayList<Integer> buyID = new ArrayList<Integer>();//購入ID
    private ArrayList<String> itemCode = new ArrayList<String>();//商品コード
    private ArrayList<Integer> type = new ArrayList<Integer>();//発送方法
    private ArrayList<Timestamp> buyDate = new ArrayList<Timestamp>();//購入日時
    private ArrayList<Integer> itemNumber = new ArrayList<Integer>();//商品個数
    
    
    public void setBuyID(int buyID){
        this.buyID.add(buyID);
    }
    public int getBuyID(int number){
       return buyID.get(number);
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
    public void setType(int type){
        this.type.add(type);
    }
    public int getType(int number){
        return type.get(number);
    }
    public void setBuyDate(Timestamp buyDate){
        this.buyDate.add(buyDate);
    }
    public Timestamp getBuyDate(int number){
        return buyDate.get(number);
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

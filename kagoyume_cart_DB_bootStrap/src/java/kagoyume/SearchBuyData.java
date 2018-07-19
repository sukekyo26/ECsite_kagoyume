/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * 購入履歴画面でデータを表示する為に使うクラス
 * @author sumi3
 */
public class SearchBuyData implements Serializable{
    private ArrayList<Integer> buyID = new ArrayList<Integer>();//購入ID(BuyDataDTOから)
    private ArrayList<String> itemCode = new ArrayList<String>();//商品コード(BuyDataDTOから)
    private ArrayList<Integer> type = new ArrayList<Integer>();//発送方法(BuyDataDTOから)
    private ArrayList<Timestamp> buyDate = new ArrayList<Timestamp>();//購入日時(BuyDataDTOから)
    private ArrayList<Integer> itemNumber = new ArrayList<Integer>();//商品個数(BuyDataDTOから)
    private ArrayList<String> itemName = new ArrayList<String>();//商品名(SearchYahooApiから)
    private ArrayList<String> thumnail = new ArrayList<String>();//サムネイル画像(SearchYahooApiから)
    private ArrayList<Integer> itemPrice = new ArrayList<Integer>();//商品価格(SearchYahooApiから)
    
    public SearchBuyData(){}
    
    public void setBuyID(int buyID){
        this.buyID.add(buyID);
    }
    public int getBuyID(int number){
        return buyID.get(number);
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
    public void setBuyDate(Timestamp buyData){
        this.buyDate.add(buyData);
    }
    public String getBuyDate(int number){//表示用にString変換
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分ss秒");
        return sdf.format(this.buyDate.get(number));
    }
    public void setItemNumber(int itemNumber){
        this.itemNumber.add(itemNumber);
    }
    public int getItemNumber(int number){
        return itemNumber.get(number);
    }
    public void setItemName(String itemName){
        this.itemName.add(itemName);
    }
    public String getItemName(int number){
        return itemName.get(number);
    }
    public void setThumnail(String thumnail){
        this.thumnail.add(thumnail);
    }
    public String getThumnail(int number){
        return thumnail.get(number);
    }
    public void setItemPrice(int itemPrice){
        this.itemPrice.add(itemPrice);
    }
    public int getItemPrice(int number){
        return itemPrice.get(number);
    }
    public int getItemSize(){
        return itemCode.size();
    }
    
    public void BDD2SBDMapping(BuyDataDTO bdd){
        for(int count = 0; count < bdd.getItemSize(); count++){
            this.buyID.add(bdd.getBuyID(count));
            this.itemCode.add(bdd.getItemCode(count));
            this.type.add(bdd.getType(count));
            this.buyDate.add(bdd.getBuyDate(count));
            this.itemNumber.add(bdd.getItemNumber(count));
        }
    }
}

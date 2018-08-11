/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.Serializable;

/**
 * カートや商品表示画面で選択された商品のデータを保持するクラス
 * @author sumi3
 */
public class SelectItem implements Serializable{
    private String name;//商品名
    private String description;//商品説明
    private String thumnail;//商品サムネイル画像
    private String largeThumnail;//商品画像300
    private Integer price;//商品価格
    private String rate;//評価点
    private String itemCode;//商品コード
    
    public SelectItem(){};
    
    public void allSet(String name, String description, String thumnail, String largeThumnail, int price, String rate, String itemCode){
        this.name = name;
        this.description = description;
        this.thumnail = thumnail;
        this.largeThumnail = largeThumnail;
        this.price = price;
        this.rate = rate;
        this.itemCode = itemCode;
    }
    

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setDescription(String des){
        this.description = des;
    }
    public String getDescription(){
        return description;
    }
    public void setThumnail(String thum){
        this.thumnail = thum;
    }
    public String getThumnail(){
        return thumnail;
    }
    public void setLargeThumnail(String lthum){
        this.largeThumnail = lthum;
    }
    public String getLargeThumnail(){
        return largeThumnail;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public int getPrice(){
        return price;
    }
    public void setRate(String rate){
        this.rate = rate;
    }
    public String getRate(){
        return rate;
    }
    public void setItemCode(String code){
       this.itemCode = code;
    }
    public String getItemCode(){
        return itemCode;
    }
}

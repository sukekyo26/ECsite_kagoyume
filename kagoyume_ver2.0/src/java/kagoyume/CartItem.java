/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.util.ArrayList;

/**
 * データベースの商品コードをCartDataDTOクラスが受け取り、SearchYahooApiクラスでその商品コードから検索し、検索した結果を格納するクラス
 * また、ゲスト(ログイン状態でない場合)がカートにアイテムを入れた場合一時的に情報を保持するクラス
 * @author sumi3
 */
public class CartItem {
    private ArrayList<Integer> userID = new ArrayList<Integer>();//商品をカートに入れたユーザーID
    private ArrayList<String> names = new ArrayList<String>();//商品名
    private ArrayList<String> thumnails = new ArrayList<String>();//商品画像
    private ArrayList<String> largeThumnail = new ArrayList<String>();//商品画像300px
    private ArrayList<String> description = new ArrayList<String>();//商品説明
    private ArrayList<Integer> price = new ArrayList<Integer>();//商品価格
    private ArrayList<String> rate = new ArrayList<String>();//商品評価点
    private ArrayList<Integer> itemNumber = new ArrayList<Integer>();//商品個数
    private ArrayList<String> itemCode = new ArrayList<String>();//商品コード
    private ArrayList<Integer> cartID = new ArrayList<Integer>();//カートID(CartDataDTOから受け取る。ゲストがカートにアイテムを入れた場合は0固定)
    
    public CartItem(){}
    public CartItem(String name, String thumnail, String lthum, String description, int price, String rate, int number, String itemCode){
    //（商品名、サムネイル画像、サムネイル画像300、説明、価格、評価点、個数、商品コード）ログインしていない場合このメソッドを使う
        this.userID.add(0);//初期ユーザーIDを0にセット
        this.names.add(name);
        this.thumnails.add(thumnail);
        this.largeThumnail.add(lthum);
        this.description.add(description);
        this.price.add(price);
        this.rate.add(rate);
        this.itemNumber.add(number);
        this.itemCode.add(itemCode);
        this.cartID.add(0);//カートIDは0でセット
    }
    public void allSet(int userID, String name, String thumnail, String lthum, String description, int price, String rate, int number, String itemCode){
    //（ユーザー名、商品名、サムネイル画像、サムネイル画像300、説明、価格、評価点、個数、商品コード）
        this.userID.add(userID);
        this.names.add(name);
        this.thumnails.add(thumnail);
        this.largeThumnail.add(lthum);
        this.description.add(description);
        this.price.add(price);
        this.rate.add(rate);
        this.itemNumber.add(number);
        this.itemCode.add(itemCode);
        this.cartID.add(0);//カートIDは0で固定
    }
    public void setUserID (int userID){
        this.userID.add(userID);
    }
    public void updateUserID(int num, int UserID){//ID:0(ユーザーログインなし)を別のIDに変える用
        this.userID.set(num, UserID);
    }
    public int getUserID(int number){
        return userID.get(number);
    }
    public void setName(String name){
        this.names.add(name);
    }
    public String getName(int number){
        return names.get(number);
    }
    public void setThumnail(String thum){
        this.thumnails.add(thum);
    }
    public String getThumnail(int number){
        return thumnails.get(number);
    }
    public void setLargeThumnail(String lthum){
        this.largeThumnail.add(lthum);
    }
    public String getLargeThumnail(int number){
        return largeThumnail.get(number);
    }
    public void setDescription(String description){
        this.description.add(description);
    }
    public String getDescription(int number){
        return description.get(number);
    }
    public void setPrice(int price){
        this.price.add(price);
    }
    public int getPrice(int number){
        return price.get(number);
    }
    public void setRate(String rate){
        this.rate.add(rate);
    }
    public String getRate(int number){
        return rate.get(number);
    }
    public void setItemNumber(int num){
        this.itemNumber.add(num);
    }
    public int getItemNumber(int number){
        return itemNumber.get(number);
    }
    public void setItemCode(String code){
        this.itemCode.add(code);
    }
    public String getItemCode(int number){
        return itemCode.get(number);
    }
    public void setCartID(int cartID){
        this.cartID.add(cartID);
    }
    public int getCartID(int number){
        return cartID.get(number);
    }
    public int getItemSize(){
        //カート内のアイテムの個数を返す
        return names.size();
    }
    public void removeItem(int number){
        //カートからアイテムを削除したいときに使う
        this.userID.remove(number);
        this.names.remove(number);
        this.thumnails.remove(number);
        this.largeThumnail.remove(number);
        this.description.remove(number);
        this.price.remove(number);
        this.rate.remove(number);
        this.itemNumber.remove(number);
        this.itemCode.remove(number);
        this.cartID.remove(number);
    }
    public boolean chkSameItem(String name, int newNumber, int userID){//引数(商品名、追加した個数、ユーザーID)
        //ユーザーIDが同じユーザーが同じ商品をカートに入れた場合、既にカートにあるアイテムの個数を足す。戻り値はboolean
        if(!names.isEmpty()){
            for(int num = 0; num < names.size(); num++){
                if(this.names.get(num).equals(name) && this.userID.get(num) == userID){
                    this.itemNumber.set(num, this.itemNumber.get(num) + newNumber);
                    return false;//同じ商品名かつ同じユーザー名の商品があった時false
                }
            }
        }
        return true;//ない場合true
    }
    
    public int getSumPrice(UserDataDTO udd){
        //カート内のアイテムの合計金額を返す
        int sumPrice = 0;
        for(int a = 0; a < names.size(); a++){
            if(userID.get(a) == udd.getUserID()){
                sumPrice += this.price.get(a) * this.getItemNumber(a);
            } 
        }
        return sumPrice;
    }
    public boolean cartContents(){
        return !names.isEmpty();
    }
    public int getAllItemCount(){
        int itemNum = 0;
        for(int count = 0; count < names.size(); count++){
            itemNum += itemNumber.get(count);
        }
        return itemNum;
    }
}

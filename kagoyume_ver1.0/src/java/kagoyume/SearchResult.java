/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 検索した値、結果を保持するクラス
 * @author sumi3
 */
public class SearchResult implements Serializable{
    private String keyword;//検索したキーワード(検索結果画面でも表示)
    private String categoryId;//カテゴリID(intでも良いと思うが一応String)(検索結果画面でも表示)
    private String sort;//ソート順(検索結果画面でも表示)
    private String highPrice;//最高金額で検索した数字(String)(検索結果画面でも表示)
    private String lowPrice;//最低金額で検索した数字(String)(検索結果画面でも表示)
    private int hitCount;//検索結果にヒットしたアイテム数(検索結果画面で表示)
    private ArrayList<String> names = new ArrayList<String>();//商品名
    private ArrayList<String> description = new ArrayList<String>();//商品説明
    private ArrayList<String> thumnail = new ArrayList<String>();//商品サムネイル画像
    private ArrayList<String> largeThumnail = new ArrayList<String>();//商品画像300
    private ArrayList<Integer> price = new ArrayList<Integer>();//商品価格
    private ArrayList<String> rate = new ArrayList<String>();//商品評価点
    private ArrayList<String> itemCode = new ArrayList<String>();//商品コード
    
    
    
    public SearchResult(){
        this.highPrice = "";
        this.lowPrice = "";
    }
    

    public void setKeyword(String keyword){
        this.keyword = keyword;
    }
    public String getKeyword(){
        return keyword;
    }
    public void setCategoryId(String categoryId){
        this.categoryId = categoryId;
    }
    public String getCategoryId(){
        return categoryId;
    }

    public void setSort(String sort){
        this.sort = sort;
    }
    public String getSort(){
        return sort;
    }
    public void setHighPrice(String price){
        this.highPrice = price;
    }
    public String getHighPrice(){
        return highPrice;
    }
    public void setLowPrice(String price){
        this.lowPrice = price;
    }
    public String getLowPrice(){
        return lowPrice;
    }
    public void setHitCount(int count){
        this.hitCount = count;
    }
    public int getHitCount(){
        return hitCount;
    }
    public void setName(String name){
        this.names.add(name);
    }
    public String getName(int number){
        return names.get(number);
    }
    public void setDescription(String des){
        this.description.add(des);
    }
    public String getDescription(int number){
        return description.get(number);
    }
    public void setThumnail(String thumnail){
        this.thumnail.add(thumnail);
    }
    public String getThumnail(int number){
        return thumnail.get(number);
    }
    public void setLargeThumnail(String lthum){
        this.largeThumnail.add(lthum);
    }
    public String getLargeThumnail(int number){
        return largeThumnail.get(number);
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
    public void setItemCode(String code){
        this.itemCode.add(code);
    }
    public String getItemCode(int number){
        return itemCode.get(number);
    }
    public int getItemSize(){
        return this.itemCode.size();
    }
    public String getCategoryName(){
        switch(this.categoryId){
            case "1":
                return "すべてのカテゴリ";
            case "13457":
                return "ファッション";
            case "2498":
                return "食品";
            case "2513":
                return "アウトドア、釣り、旅行用品";
            case "2500":
                return "ダイエット、健康";
            case "2501":
                return "コスメ、美容、ヘアケア";
            case "2502":
                return "スマホ、タブレット、パソコン";
            case "2504":
                return "テレビ、オーディオ、カメラ";
            case "2505":
                return "家電";
            case "2506":
                return "家具、インテリア";
            case "2507":
                return "花、ガーデニング";
            case "2508":
                return "キッチン、日用品、文具";
            case "2503":
                return "DIY、工具、ガーデニング";
            case "2509":
                return "ペット用品、生き物";
            case "2510":
                return "楽器、手芸、コレクション";
            case "2511":
                return "ゲーム、おもちゃ";
            case "2497":
                return "ベビー、キッズ、マタニティ";
            case "2512":
                return "スポーツ";
            case "2514":
                return "車、バイク、自転車";
            case "2516":
                return "CD、音楽ソフト";
            case "2517":
                return "DVD、映像ソフト";
            case "10002":
                return "本、雑誌、コミック";
            case "47727":
                return "レンタル、各種サービス";
        }             
        return "すべてのカテゴリ";
    }
    public String getSortName(){
        switch(this.sort){
            case "-score":
                return "おすすめ順";
            case "+price":
                return "価格安い順";
            case "-price":
                return "価格高い順";
        }
        return "おすすめ順";
    }
}

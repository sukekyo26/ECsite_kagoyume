/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author sumi3
 */
public class UserData implements Serializable{
    private boolean addressCheck = true;//住所の値不正チェック
    private boolean markCheck = true;//記入チェック初期値true
    private boolean mailCheck = true;//メールアドレスのデータベースチェック
    private String name;
    private String password;//パスワードは英数字なのでintじゃない
    private String mail;
    private String address;//住所フォーム4つの値を結合した物
    private String addressLevel1;//フォームで入力された値を格納する用
    private String addressLevel2;//フォームで入力された値を格納する用
    private String addressLine1;//フォームで入力された値を格納する用
    private String addressLine2;//フォームで入力された値を格納する用
    
    public UserData(){
    }
    
    public void setAddressCheck(boolean adCheck){
        this.addressCheck = adCheck;
    }
    public boolean getAddressCheck(){
        return addressCheck;
    }
    public void setMarkCheck(boolean search){
        this.markCheck = search;
    }
    public boolean getMarkCheck(){
        return markCheck;
    }
    public void setMailCheck(boolean mail){
        this.mailCheck = mail;
    }
    public boolean getMailCheck(){
        return mailCheck;
    }
    public void setName(String name){
        if(name.trim().length() == 0){//trim()メソッド[半角の空欄を無くす]　フォームで入力された値の空白をなくす
            this.name = "";
        } else {
            this.name = name;
        }
    }
    public String getName(){
        return name;
    }
    public void setPassword(String password){
        if(password.trim().length() == 0){
            this.password = "";
        } else {
            this.password = password;
        }
    }
    public String getPassword(){
        return password;
    }
    public void setMail(String mail){
        if(mail.trim().length() == 0){
            this.mail = "";
        } else {
            this.mail = mail;
        }
    }
    public String getMail(){
        return mail;
    }
    public void setAddress(String address){
        if(address.trim().length() == 0){
            this.address = "";
        } else {
            this.address = address;
        }
    }
    public String getAddress(){
        return address;
    }
    public void setAddressLevel1(String adsLv1){
        if(adsLv1.trim().length() == 0){
            this.addressLevel1 = "";
        } else {
            this.addressLevel1 = adsLv1;
        }
    }
    public String getAddressLevel1(){
        return addressLevel1;
    }
    public void setAddressLevel2(String adsLv2){
        if(adsLv2.trim().length() == 0){
            this.addressLevel2 = "";
        } else {
            this.addressLevel2 = adsLv2;
        }
    }
    public String getAddressLevel2(){
        return addressLevel2;
    }
    public void setAddressLine1(String adsLi1){
        if(adsLi1.trim().length() == 0){
            this.addressLine1 = "";
        } else {
            this.addressLine1 = adsLi1;
        }
    }
    public String getAddressLine1(){
        return addressLine1;
    }
    public void setAddressLine2(String adsLi2){
        if(adsLi2.trim().length() == 0){
            this.addressLine2 = "";
        } else {
            this.addressLine2 = adsLi2;
        }
    }
    public String getAddressLine2(){
        return addressLine2;
    }
    public void setAddressLeveL1byUDD(UserDataDTO udd){
        String ad = udd.getAddress();
        if(ad.contains("鹿児島県") || ad.contains("和歌山県") || ad.contains("神奈川県")){
            this.addressLevel1 = ad.substring(0, 4);
        } else {
            this.addressLevel1 = ad.substring(0, 3);
        }
    }
    public void setAddressLevel23byUDD(UserDataDTO udd){
        String ad = udd.getAddress();
        if(ad.contains("鹿児島県") || ad.contains("和歌山県") || ad.contains("神奈川県")){
            this.addressLevel2 = ad.substring(4);
        } else {
            this.addressLevel2 = ad.substring(3);
        }
    }
    //UsreData用のデータをUserDataDTO用のデータに変換
    public void UD2UDDMapping(UserDataDTO udd){
        udd.setName(this.name);
        udd.setPassword(this.password);
        udd.setMail(this.mail);
        udd.setAddress(this.address);
    }
    
    public ArrayList<String> chkproperties(){//?
        ArrayList<String> chkList = new ArrayList<String>();
        if(this.name.equals("")){
            chkList.add("name");
        }
        if(this.password.equals("")){
            chkList.add("password");
        }
        if(this.mail.equals("")){
            chkList.add("mail");
        }
        if(this.address.equals("")){
            chkList.add("address");
        }
        return chkList;
    }
}

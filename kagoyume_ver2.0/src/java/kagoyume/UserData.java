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
    public void setAddressLevel2byUDD(UserDataDTO udd){//東京都立川市柴崎町4-44-44の場合、立川市柴崎町の部分を切り取る
        String ad = udd.getAddress();
        int number = 20;//初期値20(住所に20文字もある地域はないと思うのでとりあえず20にセット)
        for(int count = 1; count < 10; count++){
            String co = Integer.toString(count);
            if(number > ad.indexOf(co) && ad.indexOf(co) > 0){
                number = ad.indexOf(co);//数字が初めて来る位置をセット
            }
        }
        if(ad.contains("鹿児島県") || ad.contains("和歌山県") || ad.contains("神奈川県")){
            this.addressLevel2 = ad.substring(4, number);
        } else {
            this.addressLevel2 = ad.substring(3, number);
        }
    }
    public void setAddressLine1byUDD(UserDataDTO udd){//東京都立川市柴崎町4-44-44の場合、4-44-44の部分を切り取る
        String ad = udd.getAddress();
        int number = 20;//初期値20
        for(int count = 1; count < 10; count++){
            String co = Integer.toString(count);
            if(number > ad.indexOf(co) && ad.indexOf(co) > 0){
                number = ad.indexOf(co);//数字が初めて来る位置をセット
            }
        }
        int firstHyhn = ad.indexOf("-");
        int secondHyhn = ad.indexOf("-", firstHyhn + 1);
        boolean exist = false;
        for(int num = 0; num < 10; num++){
            String nu = Integer.toString(num);
            if(ad.indexOf(nu, secondHyhn + 2) != -1){
                this.addressLine1 = ad.substring(number, secondHyhn + 3);
                exist = true;
            } 
            if(!exist){
                this.addressLine1 = ad.substring(number, secondHyhn + 2);
            }
        }
    }
    public void setAddressLine2byUDD(UserDataDTO udd){//アパートマンション名の部分があれば切り取る
        String ad = udd.getAddress();
        int firstHyhn = ad.indexOf("-");
        int secondHyhn = ad.indexOf("-", firstHyhn + 1);
        boolean exist = false;
        for(int num = 0; num < 10; num++){
            String nu = Integer.toString(num);
            if(ad.indexOf(nu, secondHyhn + 2) != -1){
                exist = true;
                if(ad.length() - 1 == ad.indexOf(nu, secondHyhn + 2)){
                    break;
                } else {
                    this.addressLine2 = ad.substring(secondHyhn + 3);
                }
            }
        }
        if(!exist){
            if(ad.length() - 1 != secondHyhn + 1){
                this.addressLine2 = ad.substring(secondHyhn + 2);
            }
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

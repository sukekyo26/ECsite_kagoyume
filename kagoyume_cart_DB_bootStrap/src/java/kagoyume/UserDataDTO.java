/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.sql.Timestamp;
/**
 * テーブルuser_tのカラム型に対応している(DTO)
 * @author sumi3
 */
public class UserDataDTO {
    private int userID;//ユーザーID(int)
    private String name;//ユーザー名(varchar(255))
    private String password;//パスワード(varchar(255))
    private String mail;//メールアドレス(varchar(255))
    private String address;//住所(text)
    private int total;//総購入金額(int)
    private Timestamp newDate;//登録日時(Datetime)
    private int deleteFlg;//int
    

    public void setUserId(int userID){
        this.userID = userID;
    }
    public int getUserID(){
        return userID;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public void setMail(String mail){
        this.mail = mail;
    }
    public String getMail(){
        return mail;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }
    public void setTotal(int total){
        this.total = total;
    }
    public int getTotal(){
        return total;
    }
    public void setNewDate(Timestamp newDate){
        this.newDate = newDate;
    }
    public Timestamp getNewDate(){
        return newDate;
    }
    public void setDeleteFlg(int deleteFlg){
        this.deleteFlg = deleteFlg;
    }
    public int getDeleteFlg(){
        return deleteFlg;
    }
}

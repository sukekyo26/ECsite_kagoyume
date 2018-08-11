/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.Serializable;

/**
 * どのページからログインページに来たのか情報を保存するクラス
 * @author sumi3
 */
public class LoginBeans implements Serializable{
    private String previousPage;
    private boolean loginFail = false;//ログインに失敗したかどうか
    private boolean nonLogin = false;//ログインしているかどうか
    
    public LoginBeans(){
        this.previousPage = "";
    }
    
    public void setPreviosPage(String page){
        this.previousPage = page;
    }
    public String getPreviousPage(){
        return previousPage;
    }
    public void setLoginFail(boolean fail){
        this.loginFail = fail;
    }
    public boolean getLoginFail(){
        return loginFail;
    }
    public void setNonLogin(boolean nonLogin){
        this.nonLogin = nonLogin;
    }
    public boolean getNonLogin(){
        return nonLogin;
    }
    
}

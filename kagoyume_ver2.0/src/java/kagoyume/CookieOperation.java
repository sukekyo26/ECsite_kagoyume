/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sumi3
 */
public class CookieOperation {
    
    public static CookieOperation getInstance(){
        return new CookieOperation();
    }
    /**
     * 商品詳細を見た商品の商品コードをクッキー名「AccessProduct」に「商品コード1/商品コード2/商品コード3/商品コード4・・・」の形で格納
     * @param request
     * @param response
     * @param si 
     */
    public void setAccessHistory(HttpServletRequest request, HttpServletResponse response, SelectItem si){
        Cookie cookie[] = request.getCookies();//ユーザーがクリックした商品コードをCookieに保存
        String cook = "";//変数cookの初期宣言
        for (Cookie cookie1 : cookie) {
            if (cookie1.getName().equals("AccessProduct")) {
                
                cook +=  cookie1.getValue();//既にAccessProductという名前のクッキーがあればcookにクッキー名AccessProductの値を代入
                if(cook.contains(si.getItemCode())){
                    //クッキー名AccessProductの中に今詳細情報を見ている商品がある場合は処理を中止
                    return;
                }
            }
        }
        if(cook.equals("")){
            //クッキー名AccessProductの値がない場合
            cook += "/" + si.getItemCode();
        } else {
            //クッキー名AccessProductの値がある場合
            int count = 0;
            for(char x : cook.toCharArray()){
                if(x == '/'){
                    count++;//"/"の数をカウント
                }
            }
            if(count >= 11){
                //"/"の数が11以上の場合(要素12個)
                int sdSlash = cook.indexOf("/", 1);
                cook = cook.substring(sdSlash) + "/" + si.getItemCode();//"/"の数が11個以上の場合一番最初の値は古いので消去しcookの値を更新
            } else {
                //"/"の数が11より少ない場合
                cook += "/" + si.getItemCode();//cookに値を追加
            }
        }
        Cookie co = new Cookie("AccessProduct", cook);
        co.setMaxAge(60 * 60 * 24 * 30);//クッキーの期限1か月
        response.addCookie(co);//クッキーに反映
    }
    
    /**
     * Cookieの中身「/要素１/要素２/要素３/要素４/・・・・」の要素を取り出してArrayListに格納。引数(リクエスト, レスポンス, 取り出したいクッキー名)
     * @param request
     * @param response
     * @param CookieName
     * @return 
     */
    public ArrayList<String> SplitElement(HttpServletRequest request, HttpServletResponse response, String CookieName){
        ArrayList<String> itemCode = new ArrayList<String>();//要素をセットするArrayList
        Cookie cookie[] = request.getCookies();
        String cook = "";//変数cookの初期宣言
        if(cookie == null){
            return itemCode;//cookieが存在しない
        }
        for (Cookie cookie1 : cookie) {
            if (cookie1.getName().equals(CookieName)) {
                cook += cookie1.getValue();//要素を取り出したいcookieの値をcookにセット
                break;
            }
        }
        if(cook.equals("")){
            return itemCode;//要素を取り出したいcookieが存在しない
        }
        ArrayList<Integer> position = new ArrayList<Integer>();//「"/"」の位置をセットするArrayList
        for(int num = 0; num < cook.length(); num++){
            int pos = cook.indexOf("/", num);//「"/"」の位置を確認
            if(pos != -1){
                position.add(pos);//「"/"」の位置をArrayListにセット
                num = pos;
            }
        }
        for(int a = 0; a < position.size(); a++){
            String code = null;
            if(a == position.size() - 1){
                code = cook.substring(position.get(a) + 1);//要素の切り取り(最後の要素)
            }else{
                code = cook.substring(position.get(a) + 1, position.get(a + 1));//要素の切り取り(最後以外の要素)
            }
            itemCode.add(code);//要素をArrayListに格納
        }
        return itemCode;
    }
}


            


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import base.DBManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * cart_t関連のDB処理クラス
 * @author sumi3
 */
public class CartDataDAO {
    
    public static CartDataDAO getInstance(){
        return new CartDataDAO();
    }
    
    /**
     * カートに入れた物のデータ(単体)を格納
     * @param ci
     * @throws SQLException 
     */
    public void CartDataInsert(CartItem ci) throws SQLException{
        Connection co = null;
        PreparedStatement ps = null;
        try{
            co = DBManager.getConnection();
            ps = co.prepareStatement("INSERT INTO cart_t(userID, itemCode, itemNumber) VALUES (?, ?, ?)");
            ps.setInt(1, ci.getUserID(0));
            ps.setString(2, ci.getItemCode(0));
            ps.setInt(3, ci.getItemNumber(0));
            ps.executeUpdate();
            
            System.out.println("insert complete");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(co != null){
                co.close();
            }
        }
    }
   
    /**
     * ユーザーIDに該当するデータを参照し、CartItemクラスに格納し戻り値としてセット
     * @param udd
     * @return
     * @throws SQLException 
     */
    public CartDataDTO CartDataSelect(UserDataDTO udd) throws SQLException{
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CartDataDTO cdd = new CartDataDTO();
        try{
           co = DBManager.getConnection();
           ps = co.prepareStatement("SELECT * FROM cart_t WHERE userID = ?");
           ps.setInt(1, udd.getUserID());
           rs = ps.executeQuery();
           while(rs.next()){
               cdd.setCartID(rs.getInt(1));
               cdd.setUserID(rs.getInt(2));
               cdd.setItemCode(rs.getString(3));
               cdd.setItemNumber(rs.getInt(4));
           }
           return cdd;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(co != null){
                co.close();
            }
        }
    }
    /**
     * ユーザーIDに該当するデータを参照し、商品コード、商品個数をCartDataDTOに格納し戻り値として返す
     * @param ci
     * @return 
     * @throws SQLException 
     */
    public CartDataDTO SearchCartItem(CartItem ci) throws SQLException{
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CartDataDTO cdd = new CartDataDTO();
        try{
            co = DBManager.getConnection();
            ps = co.prepareStatement("SELECT * FROM cart_t WHERE userID = ?");
            ps.setInt(1, ci.getUserID(0));
            rs = ps.executeQuery();
            while(!rs.next()){
                return cdd;
            }
            rs.beforeFirst();
            while(rs.next()){
                cdd.setCartID(rs.getInt(1));
                cdd.setItemCode(rs.getString(3));
                cdd.setItemNumber(rs.getInt(4));
            }
            return cdd;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(co != null){
                co.close();
            }
        }
    }
    /**
     * 同じ商品が既にcart_tにある場合個数のみ加算
     * 引数(CartItem、int)CartItemから新しく追加した商品の個数を取得し使用、intは以前までcart_tに格納されていた個数を入れる(CartDataDTOから取得)
     * @param ci
     * @param beforeNumber
     * @throws SQLException 
     */
    public void ItemNumberUpdate(CartItem ci, int beforeNumber) throws SQLException{
        Connection co = null;
        PreparedStatement ps = null;
        try{
            co = DBManager.getConnection();
            ps = co.prepareStatement("UPDATE cart_t SET itemNumber = ? WHERE userID = ? AND itemCode = ?");
            ps.setInt(1, ci.getItemNumber(0) + beforeNumber);
            ps.setInt(2, ci.getUserID(0));
            ps.setString(3, ci.getItemCode(0));
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(co != null){
                co.close();
            }
        }
    }

    /**
     * カートIDに該当するcart_tのレコードを削除
     * @param uci
     * @param number
     * @throws SQLException 
     */
    public void DeleteCartDataByCartID(CartItem uci, int number) throws SQLException{
        Connection co = null;
        PreparedStatement ps = null;
        try{
            co = DBManager.getConnection();
            ps = co.prepareStatement("DELETE FROM cart_t WHERE cartID = ?");
            ps.setInt(1, uci.getCartID(number));
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(co != null){
                co.close();
            }
        }
    }
    /**
     * ユーザーIDに該当するcart_tのレコードを削除
     * @param udd
     * @throws SQLException 
     */
    public void DeleteCartDataByUserID(UserDataDTO udd) throws SQLException{
        Connection co = null;
        PreparedStatement ps = null;
        try{
            co = DBManager.getConnection();
            ps = co.prepareStatement("DELETE FROM cart_t WHERE userID = ?");
            ps.setInt(1, udd.getUserID());
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(co != null){
                co.close();
            }
        }
    }
    
}

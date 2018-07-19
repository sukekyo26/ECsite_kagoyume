/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import base.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * ユーザーのデータに関するSQL処理を記述するクラス
 * @author sumi3
 */
public class UserDataDAO {
    
    public static UserDataDAO getInstance(){
        return new UserDataDAO();
    }
    
    /**
     * 新規ユーザーのデータ挿入処理を行う
     * @param udd
     * @throws SQLException 
     */
    public void newUserInsert(UserDataDTO udd) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement("INSERT INTO user_t(name, password, mail, address, newDate) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, udd.getName());
            ps.setString(2, udd.getPassword());
            ps.setString(3, udd.getMail());
            ps.setString(4, udd.getAddress());
            ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
            System.out.println("insert complete");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    /**
     * ユーザーの登録情報を更新するメソッド
     * フォームが空欄の場合UserDataDTOの旧情報をそのまま更新しようと思ったが、むやみに更新するとバグの原因になると思ったので入力されたもののみ更新
     * @param udd
     * @throws SQLException 
     */
    public void UserDataUpdate(UserDataDTO udd) throws SQLException{
        Connection co = null;
        PreparedStatement ps = null;
        try {
            co = DBManager.getConnection();
            String sql = "";
                    
            boolean checkInput = false;
            boolean user = false;
            boolean password = false;
            boolean mail = false;
            boolean address = false;
            if(!udd.getName().equals("")){//フォームで値が入力された場合
                sql += "UPDATE user_t SET name = ?";
                user = true;
                checkInput = true;
            }
            if(!udd.getPassword().equals("")){
                password = true;
                if(checkInput){
                    sql += ", password = ?";
                }else{
                    sql += "UPDATE user_t SET password = ?";
                    checkInput = true;
                }
            }
            if(!udd.getMail().equals("")){
                mail = true;
                if(checkInput){
                    sql += ", mail = ?";
                }else{
                    sql += "UPDATE user_t SET mail = ?";
                    checkInput = true;
                }
            }
            if(!udd.getAddress().equals("")){
                address = true; 
                if(checkInput){
                    sql += ", address = ?";
                }else{
                    sql += "UPDATE user_t SET address = ?";
                    checkInput = true;
                }
            }
            if(checkInput){
                sql += " WHERE userID = ?";
            }else{
                return;//checkInputがfalseの時更新された値は存在しないので処理を終了
            }
            
            
            ps = co.prepareStatement(sql);
            int count = 1;
            if(user){
                ps.setString(count, udd.getName());
                count++;
            }
            if(password){
                ps.setString(count, udd.getPassword());
                count++;
            }
            if(mail){
                ps.setString(count, udd.getMail());
                count++;
            }
            if(address){
                ps.setString(count, udd.getAddress());
                count++;
            }
            ps.setInt(count, udd.getUserID());
            ps.executeUpdate();
            System.out.println("update complete");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if(co != null){
                co.close();
            }
        }
            
    }
    
    /**
     * ログインで記入したユーザー名、パスワードがデータベース上に存在するかチェックし、存在すればtrue。しなければfalseで返す
     * @param udd
     * @return boolean
     * @throws SQLException 
     */
    public boolean CheckUserData(UserDataDTO udd) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement("SELECT name, password FROM user_t WHERE name = ? AND password = ? AND deleteFlg = 0");
            ps.setString(1, udd.getName());
            ps.setString(2, udd.getPassword());
            ResultSet rs = ps.executeQuery();
            return rs.next(); 
        } catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e); 
        } finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    /**
     * ログインに成功したユーザーの情報をUserDataDTOで返す
     * @param udd
     * @return UserDataDTO
     * @throws SQLException 
     */
    public UserDataDTO LoginUserData(UserDataDTO udd) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement("SELECT * FROM user_t WHERE name = ? AND password = ? AND deleteFlg = ?");
            ps.setString(1, udd.getName());
            ps.setString(2, udd.getPassword());
            ps.setInt(3, 0);
            ResultSet rs = ps.executeQuery();
            rs.next();
            UserDataDTO data = new UserDataDTO();
            data.setUserId(rs.getInt(1));
            data.setName(rs.getString(2));
            data.setPassword(rs.getString(3));
            data.setMail(rs.getString(4));
            data.setAddress(rs.getString(5));
            data.setTotal(rs.getInt(6));
            data.setNewDate(rs.getTimestamp(7));
            
            return data;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e); 
        } finally{
            if(con != null){
                con.close();
            }
        }
    }
    /**
     * 登録されているユーザーを削除するメソッド。外部キー制約があるのでDELETEではなくUPDATEでdeleteFlgを1にする
     * @param udd
     * @throws SQLException 
     */
    public void DeleteUserData(UserDataDTO udd) throws SQLException{
        Connection co = null;
        PreparedStatement ps = null;
        try{
            co = DBManager.getConnection();
            ps = co.prepareStatement("UPDATE user_t SET deleteFlg = 1 WHERE userID = ?");
            ps.setInt(1, udd.getUserID());
            ps.executeUpdate();
            System.out.println("delete complete");
        } catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if(co != null){
                co.close();
            }
        }
    }
    /**
     * ユーザーの総購入金額を更新するメソッド
     * @param udd
     * @throws SQLException 
     */
    public void UpdateUserTotalPurchase(UserDataDTO udd, CartItem ci) throws SQLException{
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            co = DBManager.getConnection();
            int total = udd.getTotal() + ci.getSumPrice(udd);//セッションに格納されているユーザーのそれまでの総購入金額 + カートに入っている商品の総額
            udd.setTotal(total);//現在のセッションの総購入金額も更新(ログアウトせずに再度商品を購入した場合、セッションの総購入金額を更新しないと前回の購入が反映されない為)
            ps = co.prepareStatement("UPDATE user_t SET total = ? WHERE userID = ?");
            ps.setInt(1, total);
            ps.setInt(2, udd.getUserID());
            ps.executeUpdate();
            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if(co != null){
                co.close();
            }
        } 
    }
        
}

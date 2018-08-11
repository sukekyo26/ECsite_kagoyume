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
 * 購入データに関するSQL処理を記述するクラス
 * @author sumi3
 */
public class BuyDataDAO {
    
    public static BuyDataDAO getInstance(){
        return new BuyDataDAO();
    }
    
    /**
     * 商品購入時、商品データを格納するメソッド
     * @param bdd
     * @throws SQLException 
     */
    public void newBuyDataInsert(BuyDataDTO bdd) throws SQLException{
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try { 
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            co = DBManager.getConnection();
            for(int count = 0; count < bdd.getItemSize(); count++){
                ps = co.prepareStatement("INSERT INTO buy_t(userID, itemCode, type, buyDate, itemNumber) VALUES (?, ?, ?, ?, ?)");
                ps.setInt(1, bdd.getUserID());//ユーザーID
                ps.setString(2, bdd.getItemCode(count));//商品コード
                ps.setInt(3, bdd.getType(count));//発送方法
                ps.setTimestamp(4, ts);//購入日時
                ps.setInt(5, bdd.getItemNumber(count));
                ps.executeUpdate();
            }
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
     * テーブルbuy_tの購入データをユーザーIDで検索しBuyDataDTO値で返す
     * @param bdd
     * @return BuyDataDTO
     * @throws SQLException 
     */
    public BuyDataDTO CheckBuyData(BuyDataDTO bdd) throws SQLException{
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = DBManager.getConnection();
            ps = co.prepareStatement("SELECT * FROM buy_t WHERE userID = ?");
            ps.setInt(1, bdd.getUserID());
            rs = ps.executeQuery();
            while(rs.next()){
                bdd.setBuyID(rs.getInt(1));
                bdd.setItemCode(rs.getString(3));
                bdd.setType(rs.getInt(4));
                bdd.setBuyDate(rs.getTimestamp(5));
                bdd.setItemNumber(rs.getInt(6));
            }
            System.out.println("CheckBuyData completed");
            return bdd;
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

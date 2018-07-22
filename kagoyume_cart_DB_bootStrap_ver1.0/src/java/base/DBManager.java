/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author sumi3
 */
public class DBManager {
    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kagoyume_db?CharacterEncording=UTF-8&serverTimezone=JST","root","");
            System.out.println("DBConnection!!");
            return con;
        } catch (ClassNotFoundException ex) {
            throw new IllegalMonitorStateException();
        } catch (SQLException ex) {
            throw new IllegalMonitorStateException();
        }
    }
}

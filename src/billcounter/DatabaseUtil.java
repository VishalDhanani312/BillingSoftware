/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billcounter;

import com.mysql.jdbc.Connection;
import static java.lang.Class.forName;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vishu
 */
public class DatabaseUtil {
    
    private Connection chainIn = null,goldOut = null,stock = null;
    
    
    public DatabaseUtil(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        chainIn = getConnection("chainin");
        goldOut = getConnection("goldout");
        stock = getConnection("stock");
        
    }
    
    private Connection getConnection(String dbName){
        Connection mConnect = null;
        try {
           mConnect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName,"root","1234567890");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
       return mConnect; 
    }
    
    public Connection getChainInDb(){
        return chainIn;
    }

    public Connection getGoldOutDb() {
        return goldOut;
    }

    public Connection getStock() {
        return stock;
    }
    
    
    
    
    
}

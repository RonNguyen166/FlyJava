/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbcontext;

/**
 *
 * @author Ly Quynh Tran
 */

import static dbcontext.DatabaseInfor.driverName;
import static dbcontext.DatabaseInfor.pass;
import static dbcontext.DatabaseInfor.url;
import static dbcontext.DatabaseInfor.user;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author thuongnnse05095
 */
public class ConnectDB implements DatabaseInfor {
 

    public ConnectDB() {
        
    }

    public Connection getConnection()throws ClassNotFoundException {
        try{
            Class.forName(driverName);
            Connection con = DriverManager.getConnection(url,user, pass);
            return con;
        }catch(SQLException ex){
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void close(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    
}

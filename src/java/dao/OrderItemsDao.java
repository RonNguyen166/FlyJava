/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbcontext.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import entity.OrderItems;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class OrderItemsDao {
    private final ConnectDB db;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public OrderItemsDao(){
        db= new ConnectDB();
    }
    public void addOrderItem(OrderItems orderItem){
          try {
            String sql = "Insert into Orders(orderId, productId, price, quantity) values(?,?,?,?)";
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderItem.getOrders().getId());
            ps.setInt(2, orderItem.getProduct().getId());
            ps.setInt(3, orderItem.getPrice());
            ps.setInt(4, orderItem.getQuatity());
            ps.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OrdersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}

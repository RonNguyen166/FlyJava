package dao;

import dbcontext.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Orders;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author DELL
 */
public class OrdersDao {

    private final ConnectDB db;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public OrdersDao() {
        db = new ConnectDB();
    }

    public Orders addOrder(Orders order) {
        try {
            String sql = "Insert into Orders(userId, paymentId, total) values(?,?,?)";
            
            conn = db.getConnection();
            ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
     
            ps.setInt(1, order.getUser().getId());
            ps.setInt(2, order.getPayment().getPaymentId());
            ps.setInt(3, order.getTotal());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                order.setId(rs.getInt(1));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OrdersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  order;
    }
}

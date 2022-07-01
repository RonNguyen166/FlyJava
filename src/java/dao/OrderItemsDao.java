/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbcontext.ConnectDB;
import entity.Cart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import entity.OrderItems;
import entity.Orders;
import entity.Product;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    public OrderItemsDao() {
        db = new ConnectDB();
    }

    public void addOrderItem(OrderItems orderItem) {
        try {
            String sql = "Insert into Order_Items(orderId, productId, quantity, price) values(?,?,?,?)";
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderItem.getOrders().getId());
            ps.setInt(2, orderItem.getProduct().getId());
            ps.setInt(3, orderItem.getQuatity());
            ps.setInt(4, orderItem.getPrice());
            ps.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OrdersDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<OrderItems> getOrderItems() {
        List<OrderItems> ls = new ArrayList<>();

        try {
            String sql = "Select * from Order_Items";
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new ProductDao().getProdut(rs.getString("productId"));
                Orders o = new OrdersDao().getOrder(rs.getString("orderId"));
                Cart c = new CartDao().getCartProduct(o.getUser(), p);
                OrderItems ot = new OrderItems(p, o, c.getQuantity(), p.getPrice());
                ls.add(ot);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OrderItemsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

}

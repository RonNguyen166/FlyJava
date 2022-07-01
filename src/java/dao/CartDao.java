package dao;

import dbcontext.ConnectDB;
import entity.Cart;
import entity.Company;
import entity.Product;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author DELL
 */
public class CartDao {

    private final ConnectDB db;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public CartDao() {
        db = new ConnectDB();
    }

    public List<Cart> getCartsUser(User user) {
        List ls = new ArrayList<Cart>();

        try {
            String sql = "Select * from Cart c, Product p, Company cp where userId= ? and c.productId = p.productId and p.companyId = cp.companyId";
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
//                Company comp = new Company(rs.getInt("companyId"), rs.getString("companyName"));
//                Product p = new Product();
//                p.setCompany(comp);
//                p.setDescription(rs.getString("description"));
//                p.setColor(rs.getString("color"));
//                p.setDiscount(rs.getInt("discount"));
//                p.setImage(rs.getString("image"));
//                p.setAmount(rs.getInt("amount"));
//                p.setName(rs.getString("productName"));
//                p.setSize(rs.getInt("size"));
//                p.setPrice(rs.getInt("product_price"));
//                p.setId(rs.getInt("productId"));

                Product p = new ProductDao().getProdut(rs.getString("productId"));
                Cart c = new Cart();
                c.setId(rs.getInt("cartId"));
                c.setProduct(p);
                c.setUser(user);
                c.setQuantity(rs.getInt("quantity"));

                ls.add(c);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    public Cart getCartProduct(User user, Product product) {
        Cart c = null;
        try {
            String sql = "Select * from Cart c, Product p, Company cp where userId= ? and c.productId = p.productId and p.companyId = cp.companyId and c.product=?";
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setInt(2, product.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
//                Company comp = new Company(rs.getInt("companyId"), rs.getString("companyName"));
//                Product p = new Product();
//                p.setCompany(comp);
//                p.setDescription(rs.getString("description"));
//                p.setColor(rs.getString("color"));
//                p.setDiscount(rs.getInt("discount"));
//                p.setImage(rs.getString("image"));
//                p.setAmount(rs.getInt("amount"));
//                p.setName(rs.getString("productName"));
//                p.setSize(rs.getInt("size"));
//                p.setPrice(rs.getInt("product_price"));
//                p.setId(rs.getInt("productId"));
                Product p = new ProductDao().getProdut(rs.getString("productId"));
                c = new Cart();
                c.setId(rs.getInt("cartId"));
                c.setProduct(p);
                c.setUser(user);
                c.setQuantity(rs.getInt("quantity"));

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public void addCart(Product product, User user, int quality) {
        try {
            String sql = "Insert into Cart values(?,?,?)";
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setInt(2, product.getId());
            ps.setInt(3, quality);
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteCart(String idd) {
        try {
            String sql = "Delete Cart where cartId =?";
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, idd);
            ps.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void incrementQuanlity(String idd) {
        try {
            String sql = "Select quality from Cart where productId = ?";
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, idd);
            rs = ps.executeQuery();
            if (rs.next()) {
                int cur = rs.getInt(1);
                String sqlUpdate = "Update Cart set quality=? where productId = ?";
                ps = conn.prepareStatement(sqlUpdate);
                ps.setInt(1, cur + 1);
                ps.execute();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void decrementQuanlity(String idd) {
        try {
            String sql = "Select quality from Cart where productId = ?";
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, idd);
            rs = ps.executeQuery();
            if (rs.next()) {
                int cur = rs.getInt(1);
                if (cur <= 1) {
                    deleteCart(idd);
                } else {
                    String sqlUpdate = "Update Cart set quality=? where productId = ?";
                    ps = conn.prepareStatement(sqlUpdate);
                    ps.setInt(1, cur - 1);
                    ps.execute();
                }

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int total(String idd) {
        try {
            String sql = "select SUM(p.product_price * c.quantity) as total from Cart c, Product p where c.productId = p.productId and c.userId = ?";
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, idd);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int totalProduct(String userId, String productId) {
        try {
            String sql = "select p.product_price * c.quantity as total from Cart c, Product p where c.productId = p.productId and c.userId = ? and  c.productId = ?";
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setString(2, productId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void updateQuantity(String productId, int quantity) {
        try {
            String sql = "Update Cart set quality=? where productId = ?";
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setString(2, productId);
            ps.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

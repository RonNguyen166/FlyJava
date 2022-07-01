/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbcontext.ConnectDB;
import entity.Company;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class ProductDao {

    private final ConnectDB db;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public ProductDao() {
        db = new ConnectDB();
    }

    public List<Product> getProducts() {
        String sql = "select * from Product p, Company c where p.companyId = c.companyId";
        List<Product> ls = new ArrayList<>();
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Company comp = new Company(rs.getInt("companyId"), rs.getString("companyName"));
                Product p = new Product();
                p.setCompany(comp);
                p.setDescription(rs.getString("description"));
                p.setDetail(rs.getString("detail"));
                p.setColor(rs.getString("color"));
                p.setDiscount(rs.getInt("discount"));
                p.setImage(rs.getString("image"));
                p.setAmount(rs.getInt("amount"));
                p.setName(rs.getString("productName"));
                p.setSize(rs.getInt("size"));
                p.setPrice(rs.getInt("productPrice"));
                p.setId(rs.getInt("productId"));
                ls.add(p);
            }
            db.close(conn, ps, rs);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    public void addProduct(Product product) {
        String sql = "Insert into Product(productName,productPrice,description,detail,amount,color,size,image,discount,companyId) values(?,?,?,?,?,?,?,?,?,?)";

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setString(4, product.getDetail());
            ps.setInt(5, product.getAmount());
            ps.setString(6, product.getColor());
            ps.setInt(7, product.getSize());
            ps.setInt(8, product.getDiscount());
            ps.setString(9, product.getImage());
            ps.setInt(10, product.getCompany().getId());

            ps.executeUpdate();

            db.close(conn, ps, rs);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Product getProdut(String idd) {
        String sql = "Select * from Product p, Company c where p.companyId = c.companyId and p.productId = ?";
        Product p = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, idd);
            rs = ps.executeQuery();
            if (rs.next()) {
                Company comp = new Company(rs.getInt("companyId"), rs.getString("companyName"));
                p = new Product();

                p.setCompany(comp);
                p.setDescription(rs.getString("description"));                
                p.setDetail(rs.getString("detail"));
                p.setColor(rs.getString("color"));
                p.setDiscount(rs.getInt("discount"));
                p.setImage(rs.getString("image"));
                p.setAmount(rs.getInt("amount"));
                p.setName(rs.getString("productName"));
                p.setSize(rs.getInt("size"));
                p.setPrice(rs.getInt("productPrice"));
                p.setId(rs.getInt("productId"));

                db.close(conn, ps, rs);

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public boolean updateProduct(Product product) {
        String sql = "Update Product SET productName =? , productPrice =? , description=?, detail=? , amount=?, color=?, size=?, image=?, discount=?, companyId=? WHERE productId =?";
        try {
            
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setString(4, product.getDetail());
            ps.setInt(5, product.getAmount());
            ps.setString(6, product.getColor());
            ps.setInt(7, product.getSize());
            ps.setString(8, product.getImage());
            ps.setInt(9, product.getDiscount());
            ps.setInt(10, product.getCompany().getId());            
            ps.setInt(11, product.getId());
            boolean result =  ps.execute();
            db.close(conn, ps, rs);
            return result;
            

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteProduct(String idd) {
        String sql = "Delete from Product where productId = " + idd;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            boolean result = ps.execute();
            db.close(conn, ps, rs);
            return result;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}

package dao;

import dbcontext.ConnectDB;
import entity.Payment;
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
public class PaymentDao {

    private final ConnectDB db;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public PaymentDao() {
        db = new ConnectDB();
    }

    public void createPayment(String paymentName){
        try {
            String sql = "Insert into Payment values(?)";
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, paymentName);
            ps.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public List<Payment> getPayments() {
        List<Payment> ls = new ArrayList<>();
        String sql = "select * from Payment";

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Payment comp = new Payment(rs.getInt(1), rs.getString(2));
                ls.add(comp);
            }
            db.close(conn, ps, rs);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }
    

    public Payment getPayment(String idd) {
        Payment comp = null;
        String sql = "Select * from Payment where paymentId = ?";

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, idd);
            rs = ps.executeQuery();
            if (rs.next()) {
                comp = new Payment();
                comp.setPaymentId(rs.getInt(1));
                comp.setPaymentName(rs.getString(2));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comp;
    }
    
    public boolean updatePayment(Payment payment){
        String sql = "Update Payment set paymentId=?, paymentName=?";
        try {
            
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, payment.getPaymentId());
            ps.setString(2, payment.getPaymentName());
            return ps.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean deletePayment(String idd){
        try {
            String sql = "Delete Payment where paymentId=?";
            conn= db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, idd);
            return ps.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

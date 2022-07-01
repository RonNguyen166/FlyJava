package dao;

import dbcontext.ConnectDB;
import entity.Company;
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
public class CompanyDao {

    private final ConnectDB db;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public CompanyDao() {
        db = new ConnectDB();
    }

    public void createCompany(String companyName){
        try {
            String sql = "Insert into Company values(?)";
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, companyName);
            ps.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public List<Company> getCompanies() {
        List<Company> ls = new ArrayList<>();
        String sql = "select * from Company";

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Company comp = new Company(rs.getInt(1), rs.getString(2));
                ls.add(comp);
            }
            db.close(conn, ps, rs);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }
    

    public Company getCompany(String idd) {
        Company comp = null;
        String sql = "Select * from Company where companyId = ?";

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, idd);
            rs = ps.executeQuery();
            if (rs.next()) {
                comp = new Company();
                comp.setId(rs.getInt(1));
                comp.setName(rs.getString(2));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comp;
    }
    
    public boolean updateCompany(Company company){
        String sql = "Update Company set companyId=?, companyName=?";
        try {
            
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, company.getId());
            ps.setString(2, company.getName());
            return ps.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean deleteCompany(String idd){
        try {
            String sql = "Delete Company where companyId=?";
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

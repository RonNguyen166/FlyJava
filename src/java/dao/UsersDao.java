package dao;

import dbcontext.ConnectDB;
import entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class UsersDao {

    private final ConnectDB db;
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public UsersDao() {
        db = new ConnectDB();
    }

    public User login(User user) {

        String sql = "select * from Users where email=? and password=?";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setDob(rs.getDate("dob"));
                user.setPhoto(rs.getString("photo"));
                user.setGender(rs.getBoolean("gender"));
                user.setRole(rs.getBoolean("roles"));
  
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public User register(User user) {
        String sql = "INSERT INTO Users(name, email, password)\n"
                + "VALUES (?, ?, ?);";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public List<User> getUsers() {
        List<User> ls = new ArrayList<>();
        String sql = "Select * from Users";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User row = new User();
                row.setId(rs.getInt("userId"));
                row.setName(rs.getString("name"));
                row.setEmail(rs.getString("email"));
                row.setAddress(rs.getString("address"));
                row.setPhone(rs.getString("phone"));
                row.setPassword(rs.getString("password"));
                row.setDob(rs.getDate("dob"));
                row.setPhoto(rs.getString("photo"));
                row.setGender(rs.getBoolean("gender"));
                row.setRole(rs.getBoolean("roles"));
                ls.add(row);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    public User getUser(String idd) {
        User user = null;
        String sql = "Select * from Users where userId=?";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, idd);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("password"));
                user.setDob(rs.getDate("dob"));
                user.setPhoto(rs.getString("photo"));
                user.setGender(rs.getBoolean("gender"));
                user.setRole(rs.getBoolean("roles"));

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public void Update(User user) {
        String sql = "Update Users SET name = ?, email = ?, password=?, dob=?, address=?, phone=?, photo=?, gender= ? WHERE userId =?";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setDate(4, (Date) user.getDob());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getPhone());
            ps.setString(7, user.getPhoto());
            ps.setBoolean(8, user.isGender());
            ps.setInt(9, user.getId());
            ps.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private Date dob;
    private String address;
    private String phone;
    private String photo;
    private Boolean role;
    private boolean gender;

    public User() {
    }

    public User(int id, String name, String email, String password, Date dob, String address, String phone, String photo, Boolean role, boolean gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.photo = photo;
        this.role = role;
        this.gender = gender;
    }



    public User(String name, String email, String password, Date dob, String address, String phone, String photo,boolean role, boolean gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.photo = photo;
        this.role = role;
        this.gender = gender;
    }

    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", dob=" + dob + ", address=" + address + ", phone=" + phone + '}';
    }

}

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
public class Orders {

    private int id;
    private User user;
    private Payment payment;
    private int total;
    private boolean status;
    private Date createdAt;

    public Orders() {
    }

    public Orders(int id, User user, Payment payment, int total) {
        this.id = id;
        this.user = user;
        this.payment = payment;
        this.total = total;
    }

    public Orders(User user, Payment payment, int total) {
        this.user = user;
        this.payment = payment;
        this.total = total;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Orders{" + "id=" + id + ", user=" + user + ", total=" + total + ", status=" + status + ", createdAt=" + createdAt + '}';
    }

}

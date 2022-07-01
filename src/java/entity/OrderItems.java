/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author DELL
 */
public class OrderItems {
    private int id;
    private Product product;
    private Orders orders;
    private int quatity;
    private int price;

    public OrderItems() {
    }

    public OrderItems(int id, Product product, Orders orders, int quatity, int price) {
        this.id = id;
        this.product = product;
        this.orders = orders;
        this.quatity = quatity;
        this.price = price;
    }

    public OrderItems(Product product, Orders orders, int quatity, int price) {
        this.product = product;
        this.orders = orders;
        this.quatity = quatity;
        this.price = price;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItems{" + "id=" + id + ", product=" + product + ", orders=" + orders + ", quatity=" + quatity + ", price=" + price + '}';
    }
    
    
    
}

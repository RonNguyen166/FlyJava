/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author DELL
 */
public class Product {

    private int id;
    private String name;
    private int Price;
    private String description;
    private String detail;
    private int amount;
    private int discount;
    private String color;
    private int size;
    private String image;
    private Company company;

    public Product() {
    }

    public Product(int id, String name, int Price, String description, String detail, int amount, int discount, String color, int size, String image, Company company) {
        this.id = id;
        this.name = name;
        this.Price = Price;
        this.description = description;
        this.detail = detail;
        this.amount = amount;
        this.discount = discount;
        this.color = color;
        this.size = size;
        this.image = image;
        this.company = company;
    }

    public Product(String name, int Price, String description, String detail, int amount, int discount, String color, int size, String image, Company company) {
        this.name = name;
        this.Price = Price;
        this.description = description;
        this.detail = detail;
        this.amount = amount;
        this.discount = discount;
        this.color = color;
        this.size = size;
        this.image = image;
        this.company = company;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

   
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", Price=" + Price + ", description=" + description + ", detail=" + detail + ", amount=" + amount + ", discount=" + discount + ", color=" + color + ", size=" + size + ", image=" + image + ", company=" + company + '}';
    }


}

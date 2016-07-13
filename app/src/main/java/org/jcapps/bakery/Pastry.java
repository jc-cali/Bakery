package org.jcapps.bakery;

import java.io.Serializable;

/**
 * Created by JC on 7/11/16.
 */
public class Pastry implements Serializable{

    private String id;
    private String name;
    private String description;
    private int picture_resource_id;
    private int calories;
    private double price;
    private String category;
    private int in_stock;
    private int quantity;


    public Pastry(String id, String name, String description, int picture_resource_id, int calories, double price, String category, int in_stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture_resource_id = picture_resource_id;
        this.calories = calories;
        this.price = price;
        this.category = category;
        this.in_stock = in_stock;
    }

    public Pastry(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPicture_resource_id() {
        return picture_resource_id;
    }

    public int getCalories() {
        return calories;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getIn_stock() {
        return in_stock;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return " Quantity: " + quantity + "\n" +
                " Name: " + name + "\n" +
                " Price: $ " + String.format("%.2f", price);
    }

}

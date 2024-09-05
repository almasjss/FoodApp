package com.example.foodapp.model;

public class Ingredient {
    private String name;
    private String unit;
    private int quantity;
    private int imageResourceId;

    public Ingredient(int quantity, String name, String unit) {
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
//        this.imageResourceId = imageResourceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

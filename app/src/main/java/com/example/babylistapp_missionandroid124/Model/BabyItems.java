package com.example.babylistapp_missionandroid124.Model;

public class BabyItems {
    private int id;
    private String itemName;
    private String quantity;
    private String color;
    private String size;
    private String dateAddedOn;

    public BabyItems() {
    }

    public BabyItems(String itemName, String quantity, String color, String size, String dateAddedOn) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.color = color;
        this.size = size;
        this.dateAddedOn = dateAddedOn;
    }

    public BabyItems(int id, String itemName, String quantity, String color, String size, String dateAddedOn) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.color = color;
        this.size = size;
        this.dateAddedOn = dateAddedOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDateAddedOn() {
        return dateAddedOn;
    }

    public void setDateAddedOn(String dateAddedOn) {
        this.dateAddedOn = dateAddedOn;
    }
}

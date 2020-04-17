package com.example.babylistapp_missionandroid124.Model;

public class BabyItems {
    private int id;
    private String itemName;
    private int quantity;
    private String color;
    private int size;
    private String dateAddedOn;

    public BabyItems() {
    }

    public BabyItems(String itemName, int quantity, String color, int size, String dateAddedOn) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.color = color;
        this.size = size;
        this.dateAddedOn = dateAddedOn;
    }

    public BabyItems(int id, String itemName, int quantity, String color, int size, String dateAddedOn) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getDateAddedOn() {
        return dateAddedOn;
    }

    public void setDateAddedOn(String dateAddedOn) {
        this.dateAddedOn = dateAddedOn;
    }
}

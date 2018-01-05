package com.example.my_store.model.localstore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "localstore")
public class LocalStoreItemEntity { //maps localstore to database
    @Id
    @Column(name = "itemname")
    private String itemName;
    private String description;
    private double price;

    public LocalStoreItemEntity() {}

    public LocalStoreItemEntity(String itemName, String description, double price) {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}


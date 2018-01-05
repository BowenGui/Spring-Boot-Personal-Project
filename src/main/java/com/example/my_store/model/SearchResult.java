package com.example.my_store.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

//kinda like a shopping-cart for a specific store
public class SearchResult {
    private String storeName;
    private ArrayList<Item> items = new ArrayList<Item>();
    private int pageNumber = 1;
    private int pageSize = 1;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Item> currentPage() {
        //int pageSize = Integer.parseInt(this.pageSize);
        int lower = Math.max((pageNumber - 1) * pageSize, 0);
        int upper = Math.min(pageNumber * pageSize, items.size());
        return items.subList(lower, upper);
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void nextPage() {
        if((pageNumber + 1) * pageSize <= items.size()) {
            pageNumber++;
        } //no safety net here.
    }

    public void previousPage() {
        if(pageNumber > 1) {
            pageNumber--;
        } else {
            pageNumber = 1; //safety net
        }
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addItemArray(ArrayList<Item> arrayListItem) {
        for(Item item : arrayListItem) {
            items.add(item);
        }
    }

    //removes by item name
    public void removeItem(String name) {
        Item deleteItem = items.stream().filter(i -> i.getItemName().equals(name)).findFirst().get();
        items.remove(deleteItem);
    }
}

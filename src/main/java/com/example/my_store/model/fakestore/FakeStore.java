package com.example.my_store.model.fakestore;

import com.example.my_store.model.IStore;
import com.example.my_store.model.Item;
import com.example.my_store.model.SearchResult;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository //spring-boot creates an instance of this (fakestore f = new fakestore())
//@bowenrepository
public class FakeStore implements IStore { //fake, so doesn't map to database

    ArrayList<Item> fakeStoreItems = new ArrayList<>();
    /* todo: who calls this constructor??? */
    public FakeStore() {
        fakeStoreItems.add(new Item("apple", "tasty", 1.00));
        fakeStoreItems.add(new Item("orange", "sour", 2.50));
        fakeStoreItems.add(new Item("banana","yellow", 0.50));
    }

    @Override
    public SearchResult searchByDescription(String description) {
        SearchResult searchResult = new SearchResult();
        searchResult.setStoreName("My Fake Store");
        //TODO: check efficiency
        for(Item item : fakeStoreItems) {
            if(item.getDescription().equals(description)) {
                searchResult.addItem(item);
            }
        }
        return searchResult;
    }
}

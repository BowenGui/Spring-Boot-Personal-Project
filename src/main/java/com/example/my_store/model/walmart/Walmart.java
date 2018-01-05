package com.example.my_store.model.walmart;

import com.example.my_store.model.IStore;
import com.example.my_store.model.Item;
import com.example.my_store.model.SearchResult;
import org.springframework.stereotype.Repository;

@Repository
public class Walmart implements IStore {

    @Override
    public SearchResult searchByDescription(String query) {
        SearchResult searchResult = new SearchResult();
        searchResult.setStoreName("Fake Walmart");
        //TODO: check efficiency
        /*for(Item item : fakeStoreItems) {
            if(item.getDescription().equals(description)) {
                searchResult.addItem(item);
            }
        } */
        searchResult.addItem(new Item("walmart1", "tasty", 1.00));
        searchResult.addItem(new Item("walmart2", "sour", 2.50));
        searchResult.addItem(new Item("walmart3","yellow", 0.50));
        return searchResult;
    }
}

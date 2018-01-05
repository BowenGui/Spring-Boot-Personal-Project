package com.example.my_store.model.localstore;

import com.example.my_store.model.IStore;
import com.example.my_store.model.Item;
import com.example.my_store.model.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LocalStore implements IStore {
    @Autowired
    private ILocalStoreJPARepository iLocalStoreJPARepository;

    @Override
    public SearchResult searchByDescription(String query) {
        SearchResult searchResult = new SearchResult();
        searchResult.setStoreName("Local Store");
        /*List<LocalStoreItemEntity> result = findByDescription(query);
        for(LocalStoreItemEntity i : result) {
            searchResult.addItem(new Item(i.getItemName(), i.getDescription(), i.getPrice()));
        } */ //lambda expression below is same as code above
        findByDescription(query).forEach(i ->
                searchResult.addItem(new Item(i.getItemName(), i.getDescription(), i.getPrice()))); //where i = localstoreitementity

        return searchResult;
    }

    private List<LocalStoreItemEntity> findByDescription(String description) {
        return iLocalStoreJPARepository.findByDescriptionContaining(description);
    }
}

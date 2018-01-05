package com.example.my_store.model;

import com.example.my_store.model.fakestore.FakeStore;
import com.example.my_store.model.localstore.LocalStore;
import com.example.my_store.model.walmart.Walmart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service //makes class a singlet bean  (create one instance bean)
//@bowenservice --> customizable service class [in the future, higher level]
public class BusinessService {
    private ArrayList<IStore> storeList = new ArrayList<IStore>();

    @Value("${numItemsPerPage}")
    private String pageSize;

    public BusinessService(){}

    @Autowired  //search-by class type, needs to be unique
    public BusinessService(FakeStore fakeStore, Walmart walmart, LocalStore localStore) {
        storeList.add(fakeStore); storeList.add(walmart); storeList.add(localStore);
    }

    public ArrayList<IStore> getStoreList() {
        return storeList;
    }

    public void setStoreList(ArrayList<IStore> storeList) {
        this.storeList = storeList;
    }

    //returns search by description, results from all-stores
    public ArrayList<SearchResult> searchByDescription(String query) {
        ArrayList<SearchResult> results = new ArrayList<>();
        for(IStore store : storeList) {
            SearchResult searchResult = store.searchByDescription(query);
            searchResult.setPageSize(Integer.parseInt(pageSize));
            results.add(searchResult);
        }
        return results;
    }
}

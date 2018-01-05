package com.example.my_store.model.localstore;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;

public class LocalStoreService { //todo: delete this, this is not needed
    @Autowired
    private ILocalStoreJPARepository iLocalStoreJPARepository; //injects a CLASS (created by spring) that implements the ILocalStoreJPARepository interface

    public ArrayList<LocalStoreItemEntity> getAllItems() {
        ArrayList<LocalStoreItemEntity> localStoreItemsArray= new ArrayList<>();
        iLocalStoreJPARepository.findAll().forEach(localStoreItemsArray::add);
        return localStoreItemsArray;
    }

    public LocalStoreItemEntity getItemByName(String name) {
        return iLocalStoreJPARepository.findOne(name);
    }
    public void deleteItemByName(String name) {
        iLocalStoreJPARepository.delete(name);
    }
    public void updateItemByName(LocalStoreItemEntity localStoreItemEntity) {
        iLocalStoreJPARepository.save(localStoreItemEntity);
    }
    public void createItem(LocalStoreItemEntity localStoreItemEntity) {
        iLocalStoreJPARepository.save(localStoreItemEntity);
    }
}

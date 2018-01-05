package com.example.my_store.model.localstore;
import org.springframework.data.repository.CrudRepository; //includes many default operations
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILocalStoreJPARepository extends CrudRepository<LocalStoreItemEntity, String> {

    /*@Query(
            value = "SELECT * FROM localstore WHERE description LIKE %:searchTerm%",
            nativeQuery = true
    )
    List<LocalStoreItemEntity> findByDescriptionContaining(@Param("description") String description); */

    //@Query("select i from LocalStoreItemEntity i where i.description like %?1")
    //public List<LocalStoreItemEntity> search(String description); //can be whatever name for the method; be-careful to select from right place (not necessarily table name)


    List<LocalStoreItemEntity> findByDescriptionContaining(String description); //JPA will implement this for me since reserved findBy...Containing
}



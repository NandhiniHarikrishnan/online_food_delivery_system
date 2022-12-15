package com.ideas2it.fooddeliverymanagement.repository;

import com.ideas2it.fooddeliverymanagement.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

   /* @Query("update restaurant r set r.deleteFlag=true where r.id=?1")
    @Modifying
    public void softDelete(String id);

    @Query("select e from #{#Restaurant} e where e.isActive = true")
    public List<Restaurant> getAll;*/
}

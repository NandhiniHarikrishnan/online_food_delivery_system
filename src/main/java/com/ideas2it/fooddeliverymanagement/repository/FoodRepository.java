package com.ideas2it.fooddeliverymanagement.repository;

import com.ideas2it.fooddeliverymanagement.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    @Query("select f from Food f where f.name like %:keyword% or f.description like %:keyword%")
    List<Food> searchFood(@Param("keyword") String value);

//    @Modifying
//    @Query("update Food set isDeleted = true where id = :id")
//    void deleteById(@Param("id")int id);
}


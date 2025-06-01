package com.mano.codecademy.dinerApi.repositories;

import com.mano.codecademy.dinerApi.restaurant.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    boolean existsByNameAndZipCode(String name, String zipCode);
    List<Restaurant> findByZipCodeAndPeanutScoreNotNullOrderByPeanutScoreDesc(String zipCode);
    List<Restaurant> findByZipCodeAndEggScoreNotNullOrderByEggScoreDesc(String zipCode);
    List<Restaurant> findByZipCodeAndDairyScoreNotNullOrderByDairyScoreDesc(String zipCode);

}

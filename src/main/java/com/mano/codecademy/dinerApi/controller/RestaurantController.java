package com.mano.codecademy.dinerApi.controller;

import com.mano.codecademy.dinerApi.User.DinerUser;
import com.mano.codecademy.dinerApi.repositories.RestaurantRepository;
import com.mano.codecademy.dinerApi.repositories.UserRepository;
import com.mano.codecademy.dinerApi.restaurant.Restaurant;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    public RestaurantController(RestaurantRepository restaurantRepository, UserRepository userRepository){
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }


    @PostMapping("/createRestaurant")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant){ // Creating restaurant endpoint
        if(restaurant.getName() == null || restaurant.getZipCode() == null){ // Making sure name and zip code are provided
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Name and ZipCode are required");
        }
        return this.restaurantRepository.save(restaurant);
    }

    @PostMapping("/createUser")
    public DinerUser createUser(@RequestBody DinerUser user){
        if(user.getName() == null || user.getCity() == null ||  user.getZipCode() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name, city and zip code are required");
        }
        Optional<DinerUser> userOptional = this.userRepository.findByName(user.getName());
        if(userOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.IM_USED, "Username is already in use");
        }
        user.setIsInterestedInDairy(user.getIsInterestedInDairy() != null && user.getIsInterestedInDairy());// Check if allergies were given
        user.setIsInterestedInEggs(user.getIsInterestedInEggs() != null && user.getIsInterestedInEggs());
        user.setIsInterestedInPeanuts(user.getIsInterestedInPeanuts() != null && user.getIsInterestedInPeanuts());
        return this.userRepository.save(user);
    }


}

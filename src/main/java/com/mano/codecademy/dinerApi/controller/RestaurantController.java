package com.mano.codecademy.dinerApi.controller;

import com.mano.codecademy.dinerApi.DiningReview.DiningReview;
import com.mano.codecademy.dinerApi.DiningReview.ReviewStatus;
import com.mano.codecademy.dinerApi.User.DinerUser;
import com.mano.codecademy.dinerApi.repositories.DiningReviewRepository;
import com.mano.codecademy.dinerApi.repositories.RestaurantRepository;
import com.mano.codecademy.dinerApi.repositories.UserRepository;
import com.mano.codecademy.dinerApi.restaurant.Restaurant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final DiningReviewRepository diningReviewRepository;
    public RestaurantController(RestaurantRepository restaurantRepository,
                                UserRepository userRepository,
                                DiningReviewRepository diningReviewRepository){

        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.diningReviewRepository = diningReviewRepository;
    }

    @PostMapping("/createRestaurant")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant){ // Creating restaurant endpoint
        if(restaurant.getName() == null || restaurant.getZipCode() == null){ // Making sure name and
                                                                             // zip code are provided
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

    @PostMapping("/updateUser")
    public DinerUser updateUser(@RequestBody DinerUser user){
        if(user.getName() == null){ //check if name is provided
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is required");
        }
        Optional<DinerUser> optionalDinerUser = this.userRepository.findByName(user.getName()); // check if userExists
        if(optionalDinerUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User was not found");
        }
        DinerUser existingUser = optionalDinerUser.get();// get the user
        if(user.getCity() != null) existingUser.setCity(user.getCity());
        if(user.getZipCode() != null) existingUser.setZipCode(user.getZipCode());
        if(user.getIsInterestedInEggs() != null) existingUser.setIsInterestedInEggs(user.getIsInterestedInEggs());

        return this.userRepository.save(existingUser);
    }

    @GetMapping("/user")
    public DinerUser getUser(@RequestParam String name){
        Optional<DinerUser> optionalDinerUser = this.userRepository.findByName(name); // check if userExists
        if(optionalDinerUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User was not found");
        }
        return optionalDinerUser.get();
    }

    @PostMapping("/submitReview")
    public DiningReview submitDiningReview(@RequestBody DiningReview diningReview){
        if (diningReview.getName()         == null ||
            diningReview.getRestaurantId() == null ||
            diningReview.getEggScore()     == null ||
            diningReview.getDairyScore()   == null ||
            diningReview.getPeanutScore()  == null ){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required data");
        }

        Optional<DinerUser> optionalDinerUser = this.userRepository.findByName(diningReview.getName());
        if (optionalDinerUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User was not found");
        }

        return this.diningReviewRepository.save(diningReview);
    }

    @GetMapping("/pendingReviews")
    public List<DiningReview> getPendingDiningReviews(){
        return this.diningReviewRepository.findByStatus(ReviewStatus.PENDING);
    }





}

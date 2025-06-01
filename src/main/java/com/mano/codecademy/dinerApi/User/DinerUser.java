package com.mano.codecademy.dinerApi.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class DinerUser {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String city;
    private String zipCode;
    private Boolean isInterestedInPeanuts;
    private Boolean isInterestedInEggs;
    private Boolean isInterestedInDairy;
}

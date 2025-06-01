package com.mano.codecademy.dinerApi.DiningReview;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class DiningReview {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Long restaurantId;
    private Float peanutScore;
    private Float eggScore;
    private Float dairyScore;
    private String commentary;

    @Enumerated(EnumType.STRING)
    private ReviewStatus status = ReviewStatus.PENDING;
}

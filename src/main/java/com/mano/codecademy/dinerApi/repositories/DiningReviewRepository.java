package com.mano.codecademy.dinerApi.repositories;

import com.mano.codecademy.dinerApi.DiningReview.DiningReview;
import com.mano.codecademy.dinerApi.DiningReview.ReviewStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {
    List<DiningReview> findByStatus(ReviewStatus status);
    List<DiningReview> findByRestaurantIdAndStatusTrue(Long id);
}

package com.interfaces;

import com.model.Review;
import java.util.List;

public interface ReviewDAO {
    boolean addReview(Review review);
    List<Review> getReviewsByTrek(int trekId);
}
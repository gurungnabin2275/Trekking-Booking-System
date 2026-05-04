// com/interfaces/ReviewDAO.java
package com.interfaces;

import com.model.Review;
import java.util.List;

public interface ReviewDAO {
    boolean addReview(Review review);
    List<Review> getReviewsByTrek(int trekId);
    Review getReviewById(int reviewId);
    List<Review> getAllReviews();
    boolean updateReview(int reviewId, int rating, String comment);
    boolean deleteReview(int reviewId);
}
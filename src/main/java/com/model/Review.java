package com.model;


import java.sql.Date;



public class Review {
    private int reviewId;
    private int userId;
    private int trekId;
    private int bookingId;
    private int rating;
    private String comment;
    private Date reviewDate;

    public Review() {}

    public int getReviewId() {
    	return reviewId; 
    	}
    public void setReviewId(int reviewId) { 
    	this.reviewId = reviewId; 
    	}
    public int getUserId() { 
    	return userId; 
    	}
    public void setUserId(int userId) { 
    	this.userId = userId;
    	}
    public int getTrekId() { 
    	return trekId; 
    	}
    public void setTrekId(int trekId) { 
    	this.trekId = trekId; 
    	}
    public int getBookingId() { 
    	return bookingId; 
    	}
    public void setBookingId(int bookingId) {
    	this.bookingId = bookingId;
    	}
    public int getRating() { 
    	return rating;
    	}
    public void setRating(int rating) {
    	this.rating = rating; 
    	}
    public String getComment() {
    	return comment;
    	}
    public void setComment(String comment) { 
    	this.comment = comment; 
    	}
    public Date getReviewDate() {
    	return reviewDate; 
    	}
    public void setReviewDate(Date reviewDate) {
    	this.reviewDate = reviewDate; 
    	}
}
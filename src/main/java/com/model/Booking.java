package com.model;


import java.sql.Date;



public class Booking {
    private int bookingId;
    private int userId;
    private int trekId;
    private Date bookingDate;
    private Date trekStartDate;
    private int numPersons;
    private String specialRequests;
    private String status;

    public Booking() {}

    public int getBookingId() {
    	return bookingId; 
    	}
    public void setBookingId(int bookingId) {
    	this.bookingId = bookingId;
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
    public Date getBookingDate() {
    	return bookingDate; 
    	}
    public void setBookingDate(Date bookingDate) { 
    	this.bookingDate = bookingDate;
    	}
    public Date getTrekStartDate() { 
    	return trekStartDate; 
    	}
    public void setTrekStartDate(Date trekStartDate) {
    	this.trekStartDate = trekStartDate; 
    	}
    public int getNumPersons() { 
    	return numPersons; 
    	}
    public void setNumPersons(int numPersons) {
    	this.numPersons = numPersons; 
    	}
    public String getSpecialRequests() { 
    	return specialRequests; 
    	}
    public void setSpecialRequests(String specialRequests) { 
    	this.specialRequests = specialRequests; 
    	}
    public String getStatus() { 
    	return status;
    	}
    public void setStatus(String status) { 
    	this.status = status;
    	}
}
package com.model;



public class Route {
    private int routeId;
    private int trekId;
    private int waypointOrder;
    private String waypointName;
    private int altitudeM;
    private String notes;

    public Route() {}

    public int getRouteId() {
    	return routeId; 
    	}
    public void setRouteId(int routeId) { 
    	this.routeId = routeId; 
    	}
    public int getTrekId() {
    	return trekId; 
    	}
    public void setTrekId(int trekId) {
    	this.trekId = trekId;
    	}
    public int getWaypointOrder() {
    	return waypointOrder;
    	}
    public void setWaypointOrder(int waypointOrder) { 
    	this.waypointOrder = waypointOrder;
    	}
    public String getWaypointName() {
    	return waypointName;
    	}
    public void setWaypointName(String waypointName) {
    	this.waypointName = waypointName;
    	}
    public int getAltitudeM() {
    	return altitudeM;
    	}
    public void setAltitudeM(int altitudeM) { 
    	this.altitudeM = altitudeM; 
    	}
    public String getNotes() {
    	return notes; 
    	}
    public void setNotes(String notes) { 
    	this.notes = notes; 
    	}
}
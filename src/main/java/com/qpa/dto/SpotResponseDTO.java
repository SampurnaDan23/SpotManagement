package com.qpa.dto;

import java.util.List;
import java.util.Set;

import com.qpa.entity.*;

public class SpotResponseDTO {
	private Long spotId;
	private String spotNumber;
	private SpotType spotType;
	private SpotStatus status;
	private boolean isActive;
	private LocationDTO location;
	private User owner;
	private boolean hasEVCharging;
	private double price;
	private PriceType priceType;
	private Double rating;
	private String spotImage;
	private Set<VehicleType> supportedVehicleTypes;
	
	public SpotResponseDTO() {

	}

	public SpotResponseDTO(Long spotId, String spotNumber, SpotType spotType, SpotStatus status, boolean isActive, LocationDTO location, User owner, boolean hasEVCharging, double price, PriceType priceType, Double rating, String spotImage, Set<VehicleType> supportedVehicleTypes) {
		this.spotId = spotId;
		this.spotNumber = spotNumber;
		this.spotType = spotType;
		this.status = status;
		this.isActive = isActive;
		this.location = location;
		this.owner = owner;
		this.hasEVCharging = hasEVCharging;
		this.price = price;
		this.priceType = priceType;
		this.rating = rating;
		this.spotImage = spotImage;
		this.supportedVehicleTypes = supportedVehicleTypes;
	}

	public Long getSpotId() {
		return spotId;
	}

	public void setSpotId(Long spotId) {
		this.spotId = spotId;
	}

	public String getSpotNumber() {
		return spotNumber;
	}

	public void setSpotNumber(String spotNumber) {
		this.spotNumber = spotNumber;
	}

	public SpotType getSpotType() {
		return spotType;
	}

	public void setSpotType(SpotType spotType) {
		this.spotType = spotType;
	}

	public SpotStatus getStatus() {
		return status;
	}

	public void setStatus(SpotStatus status) {
		this.status = status;
	}

	public LocationDTO getLocation() {
		return location;
	}

	public void setLocation(LocationDTO location) {
		this.location = location;
	}

	public boolean getHasEVCharging() {
		return hasEVCharging;
	}

	public void setHasEVCharging(boolean hasEVCharging) {
		this.hasEVCharging = hasEVCharging;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public PriceType getPriceType() {
		return priceType;
	}

	public void setPriceType(PriceType priceType) {
		this.priceType = priceType;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getSpotImage() {
		return spotImage;
	}

	public void setSpotImage(String spotImage) {
		this.spotImage = spotImage;
	}

	public Set<VehicleType> getSupportedVehicleTypes() {
		return supportedVehicleTypes;
	}

	public void setSupportedVehicleTypes(Set<VehicleType> supportedVehicleTypes) {
		this.supportedVehicleTypes = supportedVehicleTypes;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean active) {
		isActive = active;
	}
}

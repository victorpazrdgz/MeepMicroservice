package com.movility.model;

public class Vehicle {
    private String id;
    private String name;
    private String x;
    private String y;
    private String licensePlate;
    private int range;
    private int batteryLevel;
    private int seats;
    private int helmets;
    private String model;
    private String resourceImageId;
    private int pricePerMinuteParking;
    private int pricePerMinuteDriving;
    private boolean realTimeData;
    private String engineType;
    private String resourceType;
    private int companyZoneId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getHelmets() {
        return helmets;
    }

    public void setHelmets(int helmets) {
        this.helmets = helmets;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getResourceImageId() {
        return resourceImageId;
    }

    public void setResourceImageId(String resourceImageId) {
        this.resourceImageId = resourceImageId;
    }

    public int getPricePerMinuteParking() {
        return pricePerMinuteParking;
    }

    public void setPricePerMinuteParking(int pricePerMinuteParking) {
        this.pricePerMinuteParking = pricePerMinuteParking;
    }

    public int getPricePerMinuteDriving() {
        return pricePerMinuteDriving;
    }

    public void setPricePerMinuteDriving(int pricePerMinuteDriving) {
        this.pricePerMinuteDriving = pricePerMinuteDriving;
    }

    public boolean isRealTimeData() {
        return realTimeData;
    }

    public void setRealTimeData(boolean realTimeData) {
        this.realTimeData = realTimeData;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getCompanyZoneId() {
        return companyZoneId;
    }

    public void setCompanyZoneId(int companyZoneId) {
        this.companyZoneId = companyZoneId;
    }

}

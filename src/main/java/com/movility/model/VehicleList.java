package com.movility.model;

import java.util.List;

public class VehicleList {

    private List<Vehicle> vehicleList;

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public VehicleList(){}

    public  VehicleList(List<Vehicle> vehicleList){
        this.vehicleList=vehicleList;
    }
}

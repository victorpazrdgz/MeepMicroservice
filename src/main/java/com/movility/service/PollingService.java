package com.movility.service;

import com.movility.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PollingService {
    public List<Vehicle> getVehiclesAvailable() throws Exception;
    public List<Vehicle> getNewVehiclesAvailable() throws Exception;
    public List<Vehicle> getVehiclesNotAvailable() throws Exception;
}

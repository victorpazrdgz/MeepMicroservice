package com.movility.controller;

import com.movility.model.Vehicle;
import com.movility.service.PollingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class PollingController {

    @Autowired
    PollingService pollingService;

    @RequestMapping(value = "/availables", method = RequestMethod.GET, produces = { "application/json" })
    public @ResponseBody List<Vehicle> listVehicles() throws Exception {
        List<Vehicle> listVehicles = pollingService.getVehiclesAvailable();
        return listVehicles;
    }
    @RequestMapping(value = "/newAvailables", method = RequestMethod.GET, produces = { "application/json" })
    public @ResponseBody List<Vehicle> listNewVehicles() throws Exception {
        List<Vehicle> listVehicles = pollingService.getNewVehiclesAvailable();
        return listVehicles;
    }
    @RequestMapping(value = "/notAvailables", method = RequestMethod.GET, produces = { "application/json" })
    public @ResponseBody List<Vehicle> listNotAvailables() throws Exception {
        List<Vehicle> listVehicles = pollingService.getVehiclesNotAvailable();
        return listVehicles;
    }

}

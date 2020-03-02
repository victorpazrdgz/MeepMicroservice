package com.movility.service;


import com.movility.helpers.Resources;
import com.movility.model.ClientUri;
import com.movility.model.Location;
import com.movility.model.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * This class connect to endpoint, parse the results and print the result in console
 */
@Service
public class PollingServiceImpl  implements PollingService {

    private  List<Vehicle> vehiclesNew = new ArrayList<>();
    private  List<Vehicle> vehiclesOld = new ArrayList<>();
    private  List<Vehicle> vehiclesNewAUX;
    private  List<Vehicle> vehiclesOldAUX;
    private Resources resources = Resources.getInstance();
    private ClientUri clientUri;
    private Location lowerLeftLatLon;
    private Location upperRightLatLon;
    private List<Integer> companyZone;
    private static final Logger logger = LogManager.getLogger(PollingServiceImpl.class);
    @Scheduled(fixedDelay = 30000)
    public void run() {
        try {
            lowerLeftLatLon = new Location(38.711046,-9.160096);
            upperRightLatLon = new Location(38.739429,-9.137115);
            companyZone = new ArrayList<>();
            companyZone.add(545);
            companyZone.add(467);
            companyZone.add(473);

            String companyZoneId  = resources.createStringList(companyZone);
            clientUri = new ClientUri("lisboa",lowerLeftLatLon,upperRightLatLon,companyZone);
            String uri = clientUri.createUri(companyZoneId);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Vehicle[]> response = restTemplate.getForEntity(uri,Vehicle[].class);
            if (response.getStatusCode().value() == 200) {
                vehiclesNew = Arrays.asList(response.getBody());
                if (!vehiclesNew.isEmpty()) {
                    resources.printAvailable(vehiclesNew);
                    compareChangesAvailable();
                    compareChangesNotAvailable();
                    vehiclesOld = new ArrayList<>(vehiclesNew);
                } else {
                    logger.info(vehiclesNew.size());
                    logger.info(vehiclesNew);
                }
            }else {
                logger.debug(response.getStatusCode().value());
            }

        } catch (Exception e) {
           logger.info(e);
        }

    }


    /**
     * Method for Know the new available  Vehicles
     */
    public void compareChangesAvailable() {
        try {

            vehiclesNewAUX = new ArrayList<>(vehiclesNew);
            vehiclesNewAUX = resources.compareLists(vehiclesOld, vehiclesNewAUX);
            resources.printNewAvailable(vehiclesNewAUX);
        } catch (Exception e) {
            logger.info( e);
        }

    }

    /**
     * Method for Know the not available  Vehicles
     */
    public void compareChangesNotAvailable() {
        try {
            vehiclesOldAUX = new ArrayList<>(vehiclesOld);
            vehiclesOldAUX = resources.compareLists(vehiclesNew, vehiclesOldAUX);
            resources.printNotAvailable(vehiclesOldAUX);
        } catch (Exception e) {
            logger.info( e);

        }
    }

    public List<Vehicle> getVehiclesAvailable() {
        return vehiclesNew;
    }

    public List<Vehicle> getNewVehiclesAvailable() {
        return vehiclesNewAUX;
    }

    public List<Vehicle> getVehiclesNotAvailable() {
        return vehiclesOldAUX;
    }
}

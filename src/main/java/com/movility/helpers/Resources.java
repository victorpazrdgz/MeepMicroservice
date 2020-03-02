package com.movility.helpers;

import com.movility.model.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

/**
 * This class contain resources that can use for others class.
 */
public class Resources {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    private static final Logger logger = LogManager.getLogger(Resources.class);


    /**
     * Method for compare two list of vehicles
     *
     * @param origin
     * @param destination
     * @return
     */
    public List<Vehicle> compareLists(List<Vehicle> origin, List<Vehicle> destination) {

        for (int i = 0; i < destination.size(); i++) {
            for (int j = 0; j < origin.size(); j++) {
                if (origin.get(j).getId().equals(destination.get(i).getId()))
                    destination.remove(i);
            }
        }

        return destination;
    }

    public void printAvailable(List<Vehicle> availableVehicles) {
        System.out.println(ANSI_PURPLE + " **********************  VEHICLES AVAILABLE  **********************   " + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "  Number of vehicles available    : " + availableVehicles.size() + ANSI_RESET);

        for (int i = 0; i < availableVehicles.size(); i++)
            System.out.println(ANSI_GREEN + " Name Vehicle   : " + availableVehicles.get(i).getName() + ANSI_RESET);

    }

    public void printNewAvailable(List<Vehicle> newAvailableVehicles) {
        System.out.println(ANSI_PURPLE + " ********************** NEW VEHICLES AVAILABLE  **********************   " + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "  Number of NEW vehicles available    : " + newAvailableVehicles.size() + ANSI_RESET);

        for (int i = 0; i < newAvailableVehicles.size(); i++)
            System.out.println(ANSI_BLUE + " NAME of New Vehicle Available " + newAvailableVehicles.get(i).getName() + ANSI_RESET);

    }

    public void printNotAvailable(List<Vehicle> notAvailableVehicles) {
        System.out.println(ANSI_PURPLE + " ********************** NEW VEHICLES NOT AVAILABLE  **********************   " + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "  Number of new NOT vehicles available    : " + notAvailableVehicles.size() + ANSI_RESET);

        for (int i = 0; i < notAvailableVehicles.size(); i++)
            System.out.println(ANSI_RED + " NAME of New Vehicles NOT Available " + notAvailableVehicles.get(i).getName() + ANSI_RESET);

    }

    /**
     * Method for pass list to string for use in createUri
     * @param companyZone
     * @return
     */
    public String createStringList(List<Integer> companyZone) {
        StringBuilder companyZoneId = new StringBuilder();
        Iterator<Integer> iter = companyZone.iterator();
        while (iter.hasNext()) {
            companyZoneId.append(iter.next());
            if (iter.hasNext()) {
                companyZoneId.append(",");
            }
        }
        return companyZoneId.toString();
    }

    private static Resources instance;

    private Resources() {
    }

    public static Resources getInstance() {
        if (instance == null) {
            instance = new Resources();
        }
        return instance;
    }
}

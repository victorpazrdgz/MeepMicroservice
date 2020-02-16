package com.movility.service;

import com.google.gson.Gson;
import com.movility.helpers.Resources;
import com.movility.model.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.lang3.StringUtils;

public class PollingService extends TimerTask {

    private List<Vehicle> vehiclesNew = new ArrayList<Vehicle>();
    private List<Vehicle> vehiclesOld = new ArrayList<Vehicle>();
    private Resources resources = Resources.getInstance();
    private static final Logger logger = LogManager.getLogger(PollingService.class);
    public void run() {
        try {
            String uri = resources.createUri();
            HttpURLConnection connection = resources.openconnectToUrl(uri);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() == 200) {
                StringBuilder vehicleList = new StringBuilder();
                BufferedReader listVehicles = new BufferedReader(new InputStreamReader((connection.getInputStream())));
                if ((StringUtils.isNotBlank(listVehicles.toString()) && (listVehicles != null) && (listVehicles.toString() != "undefined"))) {
                    vehicleList.append(listVehicles.readLine());
                    parseStringBuilder(vehicleList);
                }
                else {
                    logger.info( "List Vehicles Call Response : " + listVehicles.toString());
                }
            }
            else{
                logger.info( "Uri : " + uri);
                logger.info( "Response Code : " + connection.getResponseCode());
                logger.info( "Response Message : " + connection.getResponseMessage());
            }
            connection.disconnect();
            if ((vehiclesNew.size() > 0) && (vehiclesNew != null)) {
                resources.printAvailable(vehiclesNew);
                compareChangesAvailable();
                compareChangesNotAvailable();
                vehiclesOld = new ArrayList<Vehicle>(vehiclesNew);
            }
            else{
                logger.info( "Number Available Vehicles : " + vehiclesNew.size());
                logger.info( "Content Vehicle List : " + vehiclesNew);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Vehicle> parseStringBuilder(StringBuilder vehicleList) throws ParseException {
       try {
           JSONParser parser = new JSONParser();
           Object obj = parser.parse(vehicleList.toString());
           JSONArray jsonArray = (JSONArray) obj;
           vehiclesNew.clear();
           for (int i = 0; i < jsonArray.size(); i++) {
               JSONObject jsonVehicle = (JSONObject) jsonArray.get(i);
               Vehicle vehicle = new Gson().fromJson(jsonVehicle.toString(), Vehicle.class);
               vehiclesNew.add(vehicle);
           }
       } catch (Exception e) {
           logger.info("Exception" + e);
           e.printStackTrace();
       }
        return vehiclesNew;
    }

    private void compareChangesAvailable() {
       try {
           List<Vehicle> vehiclesNewAUX;

           vehiclesNewAUX = new ArrayList<Vehicle>(vehiclesNew);
           vehiclesNewAUX = resources.compareLists(vehiclesOld, vehiclesNewAUX);
           resources.printNewAvailable(vehiclesNewAUX);
       }
       catch (Exception e) {
           logger.info("Exception" + e);
           e.printStackTrace();
       }

    }

    private void compareChangesNotAvailable() {
        try {
            List<Vehicle> vehiclesOldAUX;

            vehiclesOldAUX = new ArrayList<Vehicle>(vehiclesOld);
            vehiclesOldAUX = resources.compareLists(vehiclesNew, vehiclesOldAUX);
            resources.printNotAvailable(vehiclesOldAUX);
        } catch (Exception e) {
            logger.info("Exception" + e);
            e.printStackTrace();
        }
    }

}

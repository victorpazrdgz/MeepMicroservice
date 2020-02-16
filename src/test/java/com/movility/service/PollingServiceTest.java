package com.movility.service;

import com.movility.helpers.Resources;
import com.movility.model.Vehicle;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 *  Class for do same simple test
 */
class PollingServiceTest {
    private Resources resources=Resources.getInstance();
    private Vehicle test;
    private List<Vehicle> listVehicles = new ArrayList<Vehicle>();
    private StringBuilder vehicleList;
    private String expectedURL = "https://apidev.meep.me/tripplan/api/v1/routers/lisboa/resources?lowerLeftLatLon=38.711046,-9.160096&upperRightLatLon=38.739429,9.137115&companyZoneIds=545,467,473";
    @Autowired
    private PollingServiceImpl pollingService = new PollingServiceImpl();;
    @BeforeEach
    public void setUp() throws IOException {
        test = new Vehicle();
        test.setId("testId");
        test.setName("testName");
        listVehicles.add(test);
        vehicleList = new StringBuilder();
        vehicleList.append("[");
        vehicleList.append("{");
        vehicleList.append("\"id\":\"testId\",");
        vehicleList.append("\"name\":\"testName\"");
        vehicleList.append("}");
        vehicleList.append("]");
    }

    @Test
    void run() throws IOException, ParseException {
        assertEquals(expectedURL,resources.createUri());

        URL url=new URL(expectedURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        assertEquals(connection.getResponseCode(),200);

        List<Vehicle> expectedList = new ArrayList<Vehicle>( pollingService.parseStringBuilder(vehicleList));
        assertEquals(listVehicles.get(0).getId(),expectedList.get(0).getId());

    }
}
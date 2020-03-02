package com.movility.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class ClientUri {

    private List<Integer> companyZone;
    private String zone;
    private Location lowerLeftLatLon = new Location(38.711046,-9.160096);
    private Location upperRightLatLon = new Location(38.739429,-9.137115);
    private static final Logger logger = LogManager.getLogger(ClientUri.class);

    public ClientUri( String zone, Location lowerLeftLatLon, Location upperRightLatLon,List<Integer> companyZone){
        this.zone = zone;
        this.lowerLeftLatLon = lowerLeftLatLon;
        this.upperRightLatLon = upperRightLatLon;
        this.companyZone = companyZone;
    }

    public List<Integer> getCompanyZone() {
        return companyZone;
    }

    public void setCompanyZone(List<Integer> companyZone) {
        this.companyZone = companyZone;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Location getLowerLeftLatLon() {
        return lowerLeftLatLon;
    }

    public void setLowerLeftLatLon(Location lowerLeftLatLon) {
        this.lowerLeftLatLon = lowerLeftLatLon;
    }

    public Location getUpperRightLatLon() {
        return upperRightLatLon;
    }

    public void setUpperRightLatLon(Location upperRightLatLon) {
        this.upperRightLatLon = upperRightLatLon;
    }

    /**
     * Method for create Uri( necesary modificate for do the application more escalable)
     * @return
     */
    public String createUri(String companyZoneID) {
        try {
            String newUri = UriComponentsBuilder.newInstance()
                    .scheme("https").host("apidev.meep.me")
                    .path("/tripplan/api/v1/routers/").path(zone).path("/resources")
                    .queryParam("lowerLeftLatLon",lowerLeftLatLon.getLatitude()+","+lowerLeftLatLon.getLongitude())
                    .queryParam("upperRightLatLon", upperRightLatLon.getLatitude()+","+upperRightLatLon.getLongitude())
                    .queryParam("companyZoneIds", companyZoneID).build().toUriString();

            return newUri;
        }
        catch (Exception e) {
            logger.info( e);
            return null;
        }
    }
}

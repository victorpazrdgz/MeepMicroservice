package com.movility.meep;

import com.movility.model.Vehicle;
import com.movility.service.PollingService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Timer;

/**
 * Principal class. Contain a timer for execute the method run of PollingService class
 */
@SpringBootApplication
public class MeepApplication {
    private static final Logger logger = LogManager.getLogger(MeepApplication.class);

    public static void main(String[] args) throws IOException, ParseException {
        SpringApplication.run(MeepApplication.class, args);
        try {
            PollingService pollingService = new PollingService();
            Timer timer = new Timer();
            Integer seconds = 30;
            timer.scheduleAtFixedRate(pollingService, 0, 1000 * seconds);
        } catch (Exception e) {
            logger.info("Exception" + e);
            e.printStackTrace();
        }
    }

}

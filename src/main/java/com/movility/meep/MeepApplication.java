package com.movility.meep;


import com.movility.service.PollingServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.Timer;

/**
 * Principal class. Contain a timer for execute the method run of PollingService class
 */
@SpringBootApplication
@ComponentScan("com.movility")
@EnableScheduling
public class MeepApplication {
    private static final Logger logger = LogManager.getLogger(MeepApplication.class);

    public static void main(String[] args) throws IOException, ParseException {
        SpringApplication.run(MeepApplication.class, args);

    }

}

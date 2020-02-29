package com.movility.meep;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * Principal class. Contain a timer for execute the method run of PollingService class
 */
@SpringBootApplication
//@Configuration
//@EnableWebFlux
@ComponentScan("com.movility")
@EnableScheduling
public class MeepApplication {

    public static void main(String[] args) throws Exception {

            SpringApplication.run(MeepApplication.class, args);

    }

}

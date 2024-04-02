package com.example.teachcodediscoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class TeachcodeDiscoveryServiceApplication {



    public static void main(String[] args) {
        SpringApplication.run(TeachcodeDiscoveryServiceApplication.class, args);
    }

}

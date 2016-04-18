package com.olegalas.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dexter on 18.04.16.
 */
@ComponentScan("com.olegalas.controller")
@SpringBootApplication
@EnableAutoConfiguration
@Configuration
public class Run {
    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }
}

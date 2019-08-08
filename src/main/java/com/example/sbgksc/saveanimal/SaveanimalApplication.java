package com.example.sbgksc.saveanimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SaveanimalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaveanimalApplication.class, args);
    }
        //hi
}

package com.example.hobbybungae2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Hobbybungae2Application {

    public static void main(String[] args) {
        SpringApplication.run(Hobbybungae2Application.class, args);
    }

}

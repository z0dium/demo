package com.example.demo.reactive.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ReactiveServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReactiveServiceApplication.class, args);
    }
}

package com.example.demo.reactive.client;

import com.example.demo.controller.ProcessController;
import com.example.demo.domain.MyOutput;
import com.example.demo.domain.MyRequestBody;
import com.example.demo.reactive.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


//@SpringBootApplication
public class ReactiveClientApplication {

    private static Logger log = LoggerFactory.getLogger(ReactiveClientApplication.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    WebClient client() {
        return WebClient.create("http://localhost:8080");
    }

    @Bean
    CommandLineRunner demo(WebClient client) {

        MyRequestBody requestBody = new MyRequestBody("100 + parametr;", "parametr;", 5, false);
        //MyRequestBody requestBody = new MyRequestBody("var number=parametr; 'function ran ' + number + 'times';",
        //        "parametr;",5,false);

     //  Map<String, String> params = new HashMap<>();
     //  params.put("script1", requestBody.getScript1()
     //          .replace("\\", "\\\\")
     //          .replace("\"", "\\\""));
     //  params.put("script2", requestBody.getScript2()
     //          .replace("\\", "\\\\")
     //          .replace("\"", "\\\""));
     //  params.put("iterations", requestBody.getIterations().toString());
     //  params.put("ordered", requestBody.getOrdered().toString());

        try {
            String json = objectMapper.writeValueAsString(requestBody);

            return args -> {

                client.post().uri("/results")
                        .contentType(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToFlux(String.class)
                        .subscribe(myOutput -> log.info(myOutput));
            };
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }



    public static void main(String[] args) {
        new SpringApplicationBuilder(ReactiveClientApplication.class)
                .properties(Collections.singletonMap("server.port", "8081"))
                .run(args);
    }
}

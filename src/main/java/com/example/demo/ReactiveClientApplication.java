package com.example.demo;

import com.example.demo.domain.MyOutput;
import com.example.demo.domain.MyRequestBody;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Collections;



@SpringBootApplication
public class ReactiveClientApplication {

    @Bean
    WebClient client() {
        return WebClient.create("http://localhost:8080");
    }

    @Bean
    CommandLineRunner demo(WebClient client) {

    //Параметры запроса указываются здесь:
        MyRequestBody requestBody = new MyRequestBody("100 + parametr;", "parametr*parametr;",
                5, true);


            return args -> {

                client.post().uri("/results")
                        .body(BodyInserters.fromValue(requestBody))
                        .accept(MediaType.TEXT_EVENT_STREAM)
                        .retrieve()
                        .bodyToFlux(MyOutput.class)
                        .map(MyOutput::getOutput)
                        .subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
            };
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ReactiveClientApplication.class)
                .properties(Collections.singletonMap("server.port", "8081"))
                .run(args);
    }
}

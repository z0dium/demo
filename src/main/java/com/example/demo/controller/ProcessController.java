package com.example.demo.controller;

import com.example.demo.domain.MyOutput;
import com.example.demo.domain.MyRequestBody;
import com.example.demo.reactive.service.MyRequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class ProcessController {

    private final MyRequestHandler myRequestHandler;


    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/results")
    @ResponseBody
    public Flux<MyOutput> results() {
        Mono<MyRequestBody> requestBody = Mono.just(new MyRequestBody("100 + parametr;", "parametr;", 5, false));
        return myRequestHandler.handle(requestBody);
    }

    @PostMapping(path="/results", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<MyOutput> process(@RequestBody MyRequestBody myRequestBody){
        return myRequestHandler.handle(Mono.just(myRequestBody));
    }
}

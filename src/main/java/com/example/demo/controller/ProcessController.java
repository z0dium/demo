package com.example.demo.controller;

import com.example.demo.domain.MyOutput;
import com.example.demo.domain.MyRequestBody;
import com.example.demo.reactive.Event;
import com.example.demo.reactive.service.MyRequestHandler;
import com.example.demo.reactive.service.ScriptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import org.slf4j.Logger;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
public class ProcessController {

    private static Logger log = LoggerFactory.getLogger(ProcessController.class);

    private final ObjectMapper objectMapper;
    private final MyRequestHandler myRequestHandler;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/events")
    Flux<Event> events(){
        Flux<Event> eventFlux = Flux.fromStream(Stream.generate(()->new Event(System.currentTimeMillis(),new Date())));
        Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(1));
        return Flux.zip(eventFlux, durationFlux)
                .map(Tuple2::getT1);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/results")
    Flux<MyOutput> results(){
        MyRequestBody requestBody = new MyRequestBody("100 + parametr;", "parametr;", 5, false);
        return myRequestHandler.handle(requestBody).zipWith(Flux.interval(Duration.ofMillis(100L)))
                                                    .map(Tuple2::getT1);
//
   // //    return serverRequest.bodyToMono(MyRequestBody.class)
   // //                        .map(myRequestHandler::handle)
   // //                        .flatMapMany(Flux::merge);
   // }
//
   // @PostMapping(path = "/results", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
   // Flux<MyOutput> process(@ModelAttribute MyRequestBody myRequestBody){
   //     log.info(myRequestBody.toString());
   //     System.out.println("SOUT-" + myRequestBody.toString());
   //     return myRequestHandler.handle(myRequestBody);
   // }
}}

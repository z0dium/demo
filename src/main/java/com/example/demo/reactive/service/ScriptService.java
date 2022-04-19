package com.example.demo.reactive.service;


import com.example.demo.domain.Function;
import com.example.demo.domain.FunctionResult;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public interface ScriptService {
    Flux<FunctionResult> evaluate(Function function, Integer iterations);
}

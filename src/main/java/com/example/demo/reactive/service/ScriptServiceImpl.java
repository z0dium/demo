package com.example.demo.reactive.service;


import com.example.demo.controller.ProcessController;
import com.example.demo.domain.Function;
import com.example.demo.domain.FunctionResult;
import com.example.demo.domain.MyRequestBody;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.script.*;


@Service
@NoArgsConstructor
public class ScriptServiceImpl implements ScriptService{

    private static Logger log = LoggerFactory.getLogger(ScriptServiceImpl.class);
    private final ScriptEngineManager manager = new ScriptEngineManager();
    private final ScriptEngine engine = manager.getEngineByName("JavaScript");

    @Override
    public Flux<FunctionResult> evaluate(Function function, Integer iterations) {


        return Flux.range(1,iterations)
                                .concatMap(integer -> Mono.fromCallable(()->{
                                        engine.put("parametr",integer);
                                        long start = System.currentTimeMillis();
                                        String functionResult = engine.eval(function.getSource()).toString();
                                        Double randomValue = Math.random()*2000;
                                        int intValue = randomValue.intValue();
                                        Thread.sleep(Long.parseLong("" + intValue));
                                        long timer = System.currentTimeMillis() - start;
                                        FunctionResult fr = new FunctionResult(timer, functionResult,function.getNumber(),integer);
                                        log.debug("Function № " + fr.getFunctionNumber() +
                                                " returned:{" + fr.getResult() +
                                                "} at " + fr.getFunctionIteration() +
                                                " iteration, " + fr.getTimer());
                                        return fr;})).subscribeOn(Schedulers.boundedElastic());


          //  for (int i = 0;iterations > i; i++) {
          //      engine.put("parametr",i+1);
          //
          //      long start = System.currentTimeMillis();
          //      String functionResult = engine.eval(function.getSource()).toString();
          //      long timer = System.currentTimeMillis() - start;
          //
          //      FunctionResult fr = new FunctionResult(timer, functionResult,function.getNumber(),i+1);
          //      log.debug("Function № " + fr.getFunctionNumber() + " returned:{" + fr.getResult() + "} at " + fr.getFunctionIteration() + " iteration, " + fr.getTimer());
          //      result.merge(Mono.just(fr));
          //  }

    }
}

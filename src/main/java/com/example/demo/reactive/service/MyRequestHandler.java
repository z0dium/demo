package com.example.demo.reactive.service;

import com.example.demo.controller.ProcessController;
import com.example.demo.domain.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


@Component
@AllArgsConstructor
public class MyRequestHandler {

    private final ScriptService scriptService;

    public Flux<MyOutput> handle(Mono<MyRequestBody> myRequestBody) {

        return myRequestBody.flatMapMany(mrb->mrb.getOrdered()?
            scriptService.evaluate(new Function(mrb.getScript1(), 1), mrb.getIterations())
                    .zipWith(scriptService.evaluate(new Function(mrb.getScript2(), 2), mrb.getIterations()),MyOrderedOutput::new):
            scriptService.evaluate(new Function(mrb.getScript1(), 1), mrb.getIterations())
                    .mergeWith(scriptService.evaluate(new Function(mrb.getScript2(), 2), mrb.getIterations())).map(MyUnorderedOutput::new));
        }
    }


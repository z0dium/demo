package com.example.demo.reactive.service;

import com.example.demo.controller.ProcessController;
import com.example.demo.domain.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;


@Component
@AllArgsConstructor
public class MyRequestHandler {

    private final ScriptService scriptService;

    public Flux<MyOutput> handle(MyRequestBody myRequestBody) {

        if (!myRequestBody.getOrdered()) {
            return             scriptService.evaluate(new Function(myRequestBody.getScript1(), 1), myRequestBody.getIterations())
                    .mergeWith(scriptService.evaluate(new Function(myRequestBody.getScript2(), 2), myRequestBody.getIterations()))
                    .map(MyUnorderedOutput::new);
        }else{
            return           scriptService.evaluate(new Function(myRequestBody.getScript1(), 1), myRequestBody.getIterations())
                    .zipWith(scriptService.evaluate(new Function(myRequestBody.getScript2(), 2), myRequestBody.getIterations()),
                            MyOrderedOutput::new);
        }
    }
}

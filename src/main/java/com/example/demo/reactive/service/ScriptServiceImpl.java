package com.example.demo.reactive.service;


import com.example.demo.domain.Function;
import com.example.demo.domain.FunctionResult;
import com.example.demo.domain.MyRequestBody;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.script.*;


@Service
@NoArgsConstructor
public class ScriptServiceImpl implements ScriptService{

    private final ScriptEngineManager manager = new ScriptEngineManager();
    private final ScriptEngine engine = manager.getEngineByName("JavaScript");

    @Async
    @Override
    public Flux<FunctionResult> evaluate(Function function, Integer iterations) {

        Flux<FunctionResult> result = Flux.empty();
        try {
            for (int i = 0;iterations > i; i++) {
                engine.put("parametr",i+1);

                long start = System.currentTimeMillis();
                String functionResult = engine.eval(function.getSource()).toString();
                long timer = System.currentTimeMillis() - start;

                FunctionResult fr = new FunctionResult(timer, functionResult,function.getNumber(),i+1);
                result.merge(Mono.just(fr));
            }
            return result;

        }catch (ScriptException ex){
            return Flux.error(ex);
        }
    }
}

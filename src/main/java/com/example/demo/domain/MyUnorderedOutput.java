package com.example.demo.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyUnorderedOutput extends MyOutput{

    public MyUnorderedOutput(FunctionResult functionResult){
        super(new StringBuilder()
                .append("<").append(functionResult.getFunctionIteration()).append(">")
                .append("<").append(functionResult.getFunctionNumber()).append(">")
                .append("<").append(functionResult.getResult()).append(">")
                .append("<").append(functionResult.getTimer()).append(">").toString());
    }
}

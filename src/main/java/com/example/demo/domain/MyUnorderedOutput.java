package com.example.demo.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyUnorderedOutput extends MyOutput {

    public MyUnorderedOutput(FunctionResult functionResult) {
        super(new StringBuilder()
                .append(functionResult.getFunctionIteration()).append(",")
                .append(functionResult.getFunctionNumber()).append(",")
                .append(functionResult.getResult()).append(",")
                .append(functionResult.getTimer()).toString());
    }
}
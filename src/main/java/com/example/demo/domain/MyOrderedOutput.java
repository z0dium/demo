package com.example.demo.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyOrderedOutput extends MyOutput{

        public MyOrderedOutput(FunctionResult functionResult1,FunctionResult functionResult2){
            super(new StringBuilder()
                    .append("<").append(functionResult1.getFunctionIteration()).append(">")
                    .append("<").append(functionResult1.getResult()).append(">")
                    .append("<").append(functionResult1.getTimer()).append(">")
                    .append("<").append(functionResult2.getTimer()/functionResult1.getTimer())
                    .append("<").append(functionResult2.getResult()).append(">")
                    .append("<").append(functionResult2.getTimer()).append(">")
                    .append("<").append(functionResult1.getTimer()/functionResult2.getTimer()).append(">")
                    .toString());
        }
    }

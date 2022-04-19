package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FunctionResult {
    private final long timer;
    private final String result;
    private final Integer functionNumber;
    private final Integer functionIteration;

}

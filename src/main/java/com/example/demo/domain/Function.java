package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Function {
    private String source;
    private Integer number;

    public Integer getNumber() {
        return number;
    }
    public String getSource() {
        return source;
    }

}

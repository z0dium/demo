package com.example.demo.domain;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonAutoDetect
public class MyOutput {

    private String output;

    @Override
    public String toString() {
        return "MyOutput{" +
                "output='" + output + '\'' +
                '}';
    }
}

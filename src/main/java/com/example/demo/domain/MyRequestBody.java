package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonAutoDetect
public class MyRequestBody {
    private String script1;
    private String script2;
    private Integer iterations;
    private Boolean ordered;
}

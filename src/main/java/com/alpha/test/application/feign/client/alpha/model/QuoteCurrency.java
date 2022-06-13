package com.alpha.test.application.feign.client.alpha.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QuoteCurrency {
    private String disclaimer;
    private String license;
    private LocalDate timestamp;
    private String base;
    private Map<String, Double> rates;
}

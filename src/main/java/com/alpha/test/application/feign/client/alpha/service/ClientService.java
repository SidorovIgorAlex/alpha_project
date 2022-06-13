package com.alpha.test.application.feign.client.alpha.service;

import com.alpha.test.application.feign.client.alpha.controller.Client;
import com.alpha.test.application.feign.client.alpha.model.QuoteCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ClientService {
    @Autowired
    Client client;

    public Double getCurrentQuoteCurrency(String appId, String currency) {
        QuoteCurrency currentCurrency = client.getCurrentCurrency(appId, currency);
        return currentCurrency.getRates().get(currency);
    }

    public Double getYesterdayQuoteCurrency(String appId, String currency) {
        QuoteCurrency yesterdayCurrency = client.getYesterdayCurrency(LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), appId, currency);
        return yesterdayCurrency.getRates().get(currency);
    }
}

package com.alpha.test.application.feign.client.alpha.controller;

import com.alpha.test.application.feign.client.alpha.model.QuoteCurrency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "vtb", url = "http://openexchangerates.org/api")
public interface Client {
    @RequestMapping("/latest.json")
    @ResponseBody
    QuoteCurrency getCurrentCurrency(@RequestParam(value = "app_id") String appId, @RequestParam(value = "currency") String currency);

    @RequestMapping("/historical/{data}.json")
    @ResponseBody
    QuoteCurrency getYesterdayCurrency(@PathVariable(value = "data") String data, @RequestParam(value = "app_id") String appId, @RequestParam(value = "currency") String currency);
}

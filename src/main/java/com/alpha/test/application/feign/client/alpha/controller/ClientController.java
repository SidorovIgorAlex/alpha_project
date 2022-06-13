package com.alpha.test.application.feign.client.alpha.controller;

import com.alpha.test.application.feign.client.alpha.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class ClientController {

    @Value("${url.rich.gif}")
    private String urlRichGif;

    @Value("${url.broke.gif}")
    private String urlBrokeGif;
    @Value("${app.id}")
    private String appId;

    @Autowired
    private ClientService clientService;


    @GetMapping("/getInfoCurrency-client")
    @ResponseBody
    public String getClientExample(@RequestParam(value = "currency") String currency) throws IOException, InterruptedException, URISyntaxException {

        Double yesterdayAmount = clientService.getYesterdayQuoteCurrency(appId, currency);

        Double currencyAmount = clientService.getCurrentQuoteCurrency(appId, currency);

        String url = (currencyAmount < yesterdayAmount) ? urlBrokeGif : urlRichGif;

        if (Desktop.isDesktopSupported())
        {
            Desktop.getDesktop().browse(new URI(url));
        }
        else
        {
            Runtime runtime = Runtime.getRuntime();
            if (System.getenv("OS") != null && System.getenv("OS").contains("Windows"))
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            else
                runtime.exec("xdg-open " + url);
        }

        return url;
    }
}

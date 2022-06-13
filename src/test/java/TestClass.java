import com.alpha.test.application.feign.client.alpha.AlphaApplication;
import com.alpha.test.application.feign.client.alpha.controller.Client;
import com.alpha.test.application.feign.client.alpha.controller.ClientController;
import com.alpha.test.application.feign.client.alpha.model.QuoteCurrency;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations="classpath:applicationTest.properties")
@SpringBootTest(classes = AlphaApplication.class)
public class TestClass {
    @Value("${url.rich.gif.test}")
    private String urlRichGif;

    @Value("${url.broke.gif.test}")
    private String urlBrokeGif;

    @Value("${app.id}")
    private String appId;

    @Value("${currently.currency}")
    private String currency;

    @Autowired
    private ClientController clientController;
    @MockBean
    private Client client;

    @Test
    public void testWhenQuoteYesterdayCurrencyIsSmallerThanCurrently() throws InterruptedException, IOException, URISyntaxException {
        Map<String, Double> map1 = new HashMap<>();
        map1.put(currency, 60.00);
        QuoteCurrency quoteCurrency = new QuoteCurrency();
        quoteCurrency.setBase("USD");
        quoteCurrency.setDisclaimer("00");
        quoteCurrency.setTimestamp(LocalDate.now());
        quoteCurrency.setRates(map1);
        Mockito.when(client.getCurrentCurrency(appId, currency)).thenReturn(quoteCurrency);
        Map<String, Double> map2 = new HashMap<>();
        map2.put(currency, 50.00);
        QuoteCurrency quoteYesterdayCurrency = new QuoteCurrency();
        quoteYesterdayCurrency.setBase("USD");
        quoteYesterdayCurrency.setDisclaimer("00");
        quoteYesterdayCurrency.setTimestamp(LocalDate.now());
        quoteYesterdayCurrency.setRates(map2);
        Mockito.when(client.getYesterdayCurrency(LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), appId, currency)).thenReturn(quoteYesterdayCurrency);
        String gifUrl = clientController.getClientExample(currency);
        Assert.assertEquals(urlRichGif, gifUrl);
    }

    @Test
    public void testWhenQuoteYesterdayCurrencyIsHigherThanCurrently() throws InterruptedException, IOException, URISyntaxException {
        Map<String, Double> map1 = new HashMap<>();
        map1.put(currency, 50.00);
        QuoteCurrency quoteCurrency = new QuoteCurrency();
        quoteCurrency.setBase("USD");
        quoteCurrency.setDisclaimer("00");
        quoteCurrency.setTimestamp(LocalDate.now());
        quoteCurrency.setRates(map1);
        Mockito.when(client.getCurrentCurrency(appId, currency)).thenReturn(quoteCurrency);
        Map<String, Double> map2 = new HashMap<>();
        map2.put(currency, 60.00);
        QuoteCurrency quoteYesterdayCurrency = new QuoteCurrency();
        quoteYesterdayCurrency.setBase("USD");
        quoteYesterdayCurrency.setDisclaimer("00");
        quoteYesterdayCurrency.setTimestamp(LocalDate.now());
        quoteYesterdayCurrency.setRates(map2);
        Mockito.when(client.getYesterdayCurrency(LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), appId, currency)).thenReturn(quoteYesterdayCurrency);
        String gifUrl = clientController.getClientExample(currency);
        Assert.assertEquals(urlBrokeGif, gifUrl);
    }
}

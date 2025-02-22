package com.RatesAndTarifs.ratesandtarifs;

import com.RatesAndTarifs.ratesandtarifs.config.WireMockConfig;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@ContextConfiguration(classes = {WireMockConfig.class})
public class CurrencyServiceTest {

    @Autowired
    private WireMockServer wireMockServer;

    @BeforeEach
    void setUp() {
        wireMockServer.stubFor(
                WireMock.get("/currencies/EUR")
                        .willReturn(aResponse()
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody("{\"code\":\"EUR\",\"name\":\"Euro\",\"decimals\":2}")
                        )
        );

        wireMockServer.stubFor(
                WireMock.get("/currencies/USD")
                        .willReturn(aResponse()
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody("{\"code\":\"USD\",\"name\":\"US Dollar\",\"decimals\":2}")
                        )
        );
    }

    @Test
    void testCurrencyService() {
        // Add your tests here
    }
}

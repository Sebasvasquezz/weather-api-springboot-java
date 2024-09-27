package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class WeatherReportServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getWeatherReport_ReturnsWeatherReport() {
        double latitude = 37.8267;
        double longitude = -122.4233;

        WeatherApiResponse mockResponse = new WeatherApiResponse();
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(15);
        main.setHumidity(80);
        mockResponse.setMain(main);

        String expectedUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=09c8fffb24a03e07b3cb8078648052ef";

        when(restTemplate.getForObject(expectedUrl, WeatherApiResponse.class)).thenReturn(mockResponse);
        WeatherReport result = weatherReportService.getWeatherReport(latitude, longitude);
        result.getTemperature();

        assertEquals(0, result.getTemperature(), 3);
        assertEquals(80, result.getHumidity(), 10);
    }
}

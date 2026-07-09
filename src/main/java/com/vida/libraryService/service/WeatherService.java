package com.vida.libraryService.service;

import com.vida.libraryService.response.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String API_KEY = "f4478f5f389807048aa71ce07f4b45de";

    private static final String API = "http://api.weatherstack.com/current?access_key=YOUR_ACCESS_KEY&query=CITY";

    public WeatherResponse getWeather(String city){
        String finalAPI = API.replace("CITY", city).replace("YOUR_ACCESS_KEY",API_KEY);
        ResponseEntity<WeatherResponse> res =restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);
        log.info(res.getBody().toString());
        return res.getBody();

    }

}

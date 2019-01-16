package com.anselm.weather.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.anselm.weather.Config;

@Component
public class ScheduledTasks {

	private static final Config config = new Config();
	
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    private final AtomicLong counter = new AtomicLong();
    
    @Scheduled(fixedRateString = "${weather.api.fetch_frequency.seconds}000")
    public void fetchWeatherApi() throws IOException {
    	String[] citiesId = config.getProperty("weather.cities_id").split(",");
    	int cityIdx = (int) (counter.incrementAndGet() % citiesId.length);
    	String cityId = citiesId[cityIdx];
    	
    	String url = config.getWeatherApiUrlForCity(cityId);
		RestTemplate restTemplate = new RestTemplate();
        CityTemperature cityTemp = restTemplate.getForObject(url, CityTemperature.class);
        
        log.info(
        		"Initiating Weather API fetch for city {}. The time is now {}.", 
        		cityId, 
        		dateFormat.format(new Date())
        );
        log.info(cityTemp.toString());
        log.info(cityTemp.getTempReadings().toString());
    }
}

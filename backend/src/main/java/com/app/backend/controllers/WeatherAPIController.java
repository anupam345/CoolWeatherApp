package com.app.backend.controllers;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;

import com.app.backend.client.OpenWeatherApiClient;
import com.app.backend.entity.Itinerary;
import com.app.backend.service.WeatherApiService;
import com.google.gson.Gson;
import com.app.backend.dto.Root;
import com.app.backend.dto.Sys;

@RestController
@EnableFeignClients
@RequestMapping("/weatherData")
public class WeatherAPIController {
    @Value("${openWeather.api.key}")
    private String openWeatherApiKey;
    @Autowired
	private OpenWeatherApiClient client;
    @Autowired
    private WeatherApiService weatherApiService;
        
	@GetMapping("/{city}")
	@CrossOrigin(origins="http://localhost:3000")
	public Root getWeatherData(@PathVariable("city") String city) {
		Root result =
				weatherApiService.
				getFiveDaysForecast(city);
		return result;
	}
	
	@PostMapping("/generateSummary")
	@CrossOrigin(origins="http://localhost:3000")
	public String generateSummary(@RequestBody Itinerary itinerary) {
		System.out.println(itinerary);
		HashMap<String, String>  summary = weatherApiService.summaryGenerator(itinerary);
		
		Iterator itr = summary.entrySet().iterator();

		StringBuffer sb = new StringBuffer();
		
	    while (itr.hasNext()) {
	        Map.Entry pair = (Map.Entry)itr.next();
	        sb.append(pair.getValue());
	        //System.out.println(pair.getKey() + " = " + pair.getValue());
	        itr.remove(); // to avoid a concurrentModificationException
	    }
		//Gson gson = new Gson();
		System.out.println(sb.toString());
		//String json = gson.toJson(sb.toString()); 
		
		return sb.toString();
	}

	
}

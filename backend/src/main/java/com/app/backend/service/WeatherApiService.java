package com.app.backend.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;

import com.app.backend.dto.Root;
import com.app.backend.entity.Itinerary;


public interface WeatherApiService {
	
	public Root getFiveDaysForecast(String city);
	
	public HashMap<String, String> summaryGenerator(Itinerary iti);

}


/*Two services are mapped: 1.Fetches five days weather forecast and,
 * 2. Generate summary*/
package com.app.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.backend.client.OpenWeatherApiClient;
import com.app.backend.dto.Root;
import com.app.backend.entity.City;
import com.app.backend.entity.Itinerary;

@Service
public class WeatherApiServiceImp implements WeatherApiService {

	@Value("${openWeather.api.uri}")
	private String openWeatherApi;

	@Value("${openWeather.api.key}")
	private String openWeatherApiKey;
	
	@Value("${openWeather.api.units}")
	private String unit;
	

	private static final String Msg_Rain="So, it is advised to carry an umbrella";
	
	private static final String Msg_Cold="Please don't forget warm cloths";
	
	private static final String Msg_Hot="Sun Block is recommended";
	
	@Autowired
	private RedisCacheService redisCacheService;

	
	@Autowired
	private OpenWeatherApiClient client;

	/*service provides five days data by calling external open weather API*/
	public Root getFiveDaysForecast(String city) {
		
		System.out.println("executed");
		 Root data 
		 	= client.getCityForecast(city,"metric", openWeatherApiKey);		 
		return data;
	}


	/* This Service generates Summary*/
	@Override
	public HashMap<String, String> summaryGenerator(Itinerary itinerary) {
		List<City> list = itinerary.getData();
		HashMap<String, String> citiWeather =
				new HashMap<String, String>();
		
		ListIterator<City> listItr = list.listIterator();
		StringBuffer sb=new StringBuffer();
		while(listItr.hasNext()) {
			
			City city = listItr.next();
			
			String message1 = city.getCity() + " will have temperature around "+ city.getTemperature();
			String message2 = " on "+ city.getTime();
			String message3 =". There will have "+ city.getDescription()+".";
			sb.append(message1+message2+message3);
			if (city.getWeather().toLowerCase().contains("rain")) {
				sb.append(" "+Msg_Rain+ ".\n");
				
			}
			
			String temp = city.getTemperature().replace("\u2103", "");
			String [] tokens = temp.split(" ");
			Double tempVal = Double.parseDouble(tokens[0]);
			if(tempVal <= 5) {
				sb.append(" "+Msg_Cold+ ". \n");
				
			}
			else if(tempVal >= 35){
				sb.append(" "+Msg_Hot+ ".\n");
				
			}
			else {
				sb.append("\n");
				
			}
			citiWeather.put(city.getCity(),sb.toString());
		}
		
		System.out.println(citiWeather);
		return citiWeather;
	}

}

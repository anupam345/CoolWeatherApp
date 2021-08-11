package com.app.backend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.app.backend.dto.Root;

// To connect third party api openweather api
@FeignClient(name = "openWeather-client",url="${openWeather.api.uri}")
public interface OpenWeatherApiClient {
	@RequestMapping(method = RequestMethod.GET, value = "/forecast", produces = "application/json")
	Root getCityForecast(@RequestParam("q")String city, @RequestParam("units") String units, @RequestParam("appid")String appid);

}

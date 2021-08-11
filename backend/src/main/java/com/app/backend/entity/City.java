package com.app.backend.entity;

import java.io.Serializable;


/*
 * City model in relation to cities entered by user in table
 * */
public class City implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2963760911171190870L;

	private String city;
	
	private String code;
	
	private String time;
	
	private String temperature;

	private String clouds;
	
	private String weather;
	
	private String description;
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getClouds() {
		return clouds;
	}

	public void setClouds(String clouds) {
		this.clouds = clouds;
	}

	@Override
	public String toString() {
		return "City [city=" + city + ", code=" + code + ", time=" + time + ", temperature=" + temperature + ", clouds="
				+ clouds + ", weather=" + weather + ", description=" + description + "]";
	}


}

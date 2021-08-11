
/*
 * Itinerary composed of unique itinerary name and city entity
 * */
package com.app.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Itinerary")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Itinerary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    private String id;
	
	private List<City> data;
	
	public String getId() {
		return id;
	}
	
	/*getters and setters methods*/
	public void setId(String id) {
		this.id = id;
	}


	public List<City> getData() {
		return data;
	}

	public void setData(List<City> data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "Itinerary [id=" + id + ", data=" + data + "]";
	}
	
}

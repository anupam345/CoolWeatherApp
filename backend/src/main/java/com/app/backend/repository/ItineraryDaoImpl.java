/*
 * Database operations to perform CRUD operations
 * 
 * */

package com.app.backend.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.app.backend.entity.Itinerary;

@Service
public class ItineraryDaoImpl implements ItineraryDao {

	@Autowired
	private RedisTemplate redisTemplate;
	private static final String KEY = "Itinerary";
	
	/*
	 * Create new itinerary
	 * */
	@Override
    public boolean saveItinerary(Itinerary itinerary) {
        try {
            redisTemplate.opsForHash().put(KEY, itinerary.getId().toString(), itinerary);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

	/*
	 * fetch all itineraries*/
    @Override
    public List<Itinerary> fetchAllItinerary() {
        List<Itinerary> itineraries;
        itineraries = redisTemplate.opsForHash().values(KEY);
        return  itineraries;
    }
    
    /*
     * fetch itinerary by its name
     * */
    @Override
    public Itinerary fetchItineraryById(String id) {
        Itinerary itinerary;
        itinerary = (Itinerary) redisTemplate.opsForHash().get(KEY,id.toString());
        return itinerary;
    }
    
    /*
     * 
     * delete itinerary by its name
     * 
     * */
    @Override
    public boolean deleteItinerary(String id) {
        try {
            redisTemplate.opsForHash().delete(KEY,id.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * update itinerary
     * 
     * */
    @Override
    public boolean updateItinerary(String id, Itinerary itinerary) {
        try {
            redisTemplate.opsForHash().put(KEY, id, itinerary);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}

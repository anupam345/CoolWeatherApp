package com.app.backend.repository;

import java.util.List;

import com.app.backend.entity.Itinerary;

public interface ItineraryDao {
	
    boolean saveItinerary(Itinerary Itinerary);

    List<Itinerary> fetchAllItinerary();

    Itinerary fetchItineraryById(String id);

    boolean deleteItinerary(String id);

    boolean updateItinerary(String id, Itinerary Itinerary);

}


/*
 * Allows Controller to interact with database for CRUD operations
 * 
 * */
package com.app.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.backend.entity.Itinerary;
import com.app.backend.repository.ItineraryDao;

@Service
public class ItineraryServiceImp implements ItineraryService {

    @Autowired
    private ItineraryDao itineraryDao;

    @Override
    public boolean saveItinerary(Itinerary Itinerary) {
        return itineraryDao.saveItinerary(Itinerary);
    }

    @Override
    public List<Itinerary> fetchAllItinerary() {
        return itineraryDao.fetchAllItinerary();
    }

    @Override
    public Itinerary fetchItineraryById(String id) {
        return itineraryDao.fetchItineraryById(id);
    }

    @Override
    public boolean deleteItinerary(String id) {
        return itineraryDao.deleteItinerary(id);
    }

    @Override
    public boolean updateItinerary(String id, Itinerary Itinerary) {
        return itineraryDao.updateItinerary(id,Itinerary);
    }
}

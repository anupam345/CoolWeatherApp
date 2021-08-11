package com.app.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.app.backend.entity.Itinerary;
import com.app.backend.service.ItineraryService;

@RestController
@RequestMapping("/itinerary")
public class ItineraryController {
	
    @Autowired
    private ItineraryService itineraryService;

    @PostMapping
    @CrossOrigin(origins="http://localhost:3000")
    public ResponseEntity<String> saveItinerary(@RequestBody Itinerary itinerary) {
        boolean result = itineraryService.saveItinerary(itinerary);
        if(result)
            return ResponseEntity.ok("Itinerary Created Successfully!!");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    

    @GetMapping
    @CrossOrigin(origins="http://localhost:3000")
    public ResponseEntity<List<Itinerary>> fetchAllItinerary() {
        List<Itinerary> itineraries;
        itineraries = itineraryService.fetchAllItinerary();
        return ResponseEntity.ok(itineraries);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins="http://localhost:3000")
    public ResponseEntity<Itinerary> fetchItineraryById(@PathVariable("id") String id) {
        Itinerary Itinerary;
        Itinerary = itineraryService.fetchItineraryById(id);
        return ResponseEntity.ok(Itinerary);
    }
    

    @DeleteMapping("/{id}")
    @CrossOrigin(origins="http://localhost:3000")
    public ResponseEntity<String> deleteItinerary(@PathVariable("id") String id) {
        boolean result = itineraryService.deleteItinerary(id);
        if(result)
            return ResponseEntity.ok("Itinerary deleted Successfully!!");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins="http://localhost:3000")
    public ResponseEntity<String> updateItinerary(@PathVariable("id") String id, @RequestBody Itinerary Itinerary) {
        boolean result = itineraryService.updateItinerary(id,Itinerary);
        if(result)
            return ResponseEntity.ok("Itinerary Updated Successfully!!");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}

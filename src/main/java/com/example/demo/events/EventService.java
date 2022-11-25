package com.example.demo.events;

import com.example.demo.POJO.EventRequest;
import com.example.demo.category.Category;
import com.example.demo.category.CategoryRepository;
import com.example.demo.location.Location;
import com.example.demo.location.LocationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    public EventService(EventRepository eventRepository, CategoryRepository categoryRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
    }

    public ResponseEntity<List<Event>> getEvents() {

        return new ResponseEntity<>(eventRepository.findAll(), HttpStatus.OK);
    }


    public ResponseEntity<?> saveEvent(EventRequest event) {
        Optional<Category> category_optional = categoryRepository.findById(event.getCategory_id());
        Category category = category_optional.get();
        Optional<Location> location_optional = locationRepository.findById(event.getLocation_id());
        Location location = location_optional.get();

        Event new_event = new Event(event.getName(),event.getDate(), event.getImage_url(), category, location);
        eventRepository.save(new_event);
        return new ResponseEntity<>(event,HttpStatus.ACCEPTED);

        //eventRepository.save(event);
       // return new ResponseEntity<>("Event saved successfully!", HttpStatus.ACCEPTED);
    }


}

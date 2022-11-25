package com.example.demo.events;

import com.example.demo.POJO.EventRequest;
import com.example.demo.category.Category;
import com.example.demo.category.CategoryRepository;
import com.example.demo.location.Location;
import com.example.demo.location.LocationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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


    public ResponseEntity<String> deleteEvent(Long id) {
        Optional<Event> temp_event = eventRepository.findById(id);
        if(temp_event.isPresent()){
            eventRepository.deleteById(id);
            return new ResponseEntity<>("Event is deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Event with that id doesnt exist.", HttpStatus.FORBIDDEN);
    }

    @Transactional
    public ResponseEntity<String> updateEvent(Long id, EventRequest event) {
        Category category = categoryRepository.findById(event.getCategory_id()).orElseThrow(()-> new IllegalStateException());
        Location location = locationRepository.findById(event.getLocation_id()).orElseThrow(()-> new IllegalStateException());
        Event event_temp = eventRepository.findById(id).orElseThrow(()-> new IllegalStateException());

        //can be optimized. Overriding values in db even if the value is the same as before
        event_temp.setCategory(category);
        event_temp.setLocation(location);
        event_temp.setName(event.getName());
        event_temp.setDate(event.getDate());
        event_temp.setImage_url(event.getImage_url());
        event_temp.setDate(event.getDate());

        return new ResponseEntity<>("Event is udpated", HttpStatus.OK);
    }
}

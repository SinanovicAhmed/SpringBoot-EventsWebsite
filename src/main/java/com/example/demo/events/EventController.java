package com.example.demo.events;
import com.example.demo.POJO.EventRequest;
import com.example.demo.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path= "api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    @CrossOrigin("*")
    @GetMapping
    public ResponseEntity<List<Event>> getEvents(){
        return eventService.getEvents();
    }
    @CrossOrigin("*")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getEvent(@PathVariable("id") Long id){
        return eventService.getEventId(id);
    }
    @CrossOrigin("*")
    @PostMapping(value = "/save")
    public ResponseEntity<?> saveEvents(@RequestBody EventRequest event){
        return eventService.saveEvent(event);
    }

    @CrossOrigin("*")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteEvent (@PathVariable("id") Long id){
        return eventService.deleteEvent(id);
    }

    @CrossOrigin("*")
    @PutMapping(value="/update/{id}")
    public ResponseEntity<String> updateEvent (@PathVariable("id") Long id, @RequestBody EventRequest event){
        return eventService.updateEvent(id, event);
    }
}

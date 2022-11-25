package com.example.demo.events;
import com.example.demo.POJO.EventRequest;
import com.example.demo.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    @GetMapping
    public ResponseEntity<List<Event>> getEvents(){
        return eventService.getEvents();
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveEvents(@RequestBody EventRequest event){
        return eventService.saveEvent(event);
    }

}

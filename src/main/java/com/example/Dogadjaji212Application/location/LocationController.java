package com.example.Dogadjaji212Application.location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path= "api/locations")
public class LocationController {
    private final LocationService locationService;


    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
    @Autowired
    @GetMapping
    public List<Location> getLocations(){
        return locationService.getLocations();
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<?> getLocationById(@PathVariable("id") Long id){
        return locationService.getLocationById(id);
    }
    @ResponseBody
    @PostMapping(value = "/save")
    public ResponseEntity<String> postLocation(@RequestBody Location location){
        return locationService.addNewLocation(location);
    }

    @PutMapping(value = "/updateLocation/{id}")
    public ResponseEntity<String> updateLocation(@PathVariable("id") Long id,
                                                 @RequestBody Location location){
        return locationService.updateLocation(id, location);
    }

}

package com.example.Dogadjaji212Application.location;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getLocations(){
        return locationRepository.findAll();
    }


    public ResponseEntity<String> addNewLocation(Location location) {
        Optional<Location> temp_loc = locationRepository.findLocationByName(location.getName());

        if(temp_loc.isPresent()){
            return new ResponseEntity<>("Location already exists!", HttpStatus.FORBIDDEN);
        }else{
            locationRepository.save(location);
            return new ResponseEntity<>("Location saved", HttpStatus.OK);
        }
    }
    @Transactional
    public ResponseEntity<String> updateLocation(Long id, Location location) {

        if(!locationRepository.existsById(id)){
            return new ResponseEntity<>("Location doesnt exists", HttpStatus.FORBIDDEN);
        }else{
            Location temp_loc = locationRepository.findById(id).orElseThrow(()-> new IllegalStateException());
            temp_loc.setName(location.getName());
            temp_loc.setDetails(location.getDetails());
            temp_loc.setImage_url(location.getImage_url());
            return new ResponseEntity<>("Location has been updated", HttpStatus.OK);
        }
    }

    public ResponseEntity<?> getLocationById(Long id) {
        Optional<Location> temp_loc= locationRepository.findById(id);
        if(temp_loc.isPresent()){
            return new ResponseEntity<>( locationRepository.findById(id), HttpStatus.OK);
        }{
            return new ResponseEntity<>("Location with that id doesnt exist", HttpStatus.FORBIDDEN);
        }

    }
}

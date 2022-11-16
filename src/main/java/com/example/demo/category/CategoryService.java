package com.example.demo.category;

import com.example.demo.location.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<List<Category>> getLocations() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> getCategoryById(Long id) {
        Optional<Category> temp_cat = categoryRepository.findById(id);
        if(temp_cat.isPresent()){
            return new ResponseEntity<>(temp_cat,HttpStatus.OK);
        }
        return new ResponseEntity<>("Category with that id doesnt exist", HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<String> addNewLocation(Category category) {
        Optional<Category> temp_cat = categoryRepository.findCategoryByName(category.getName());
        if(temp_cat.isPresent()){
            return new ResponseEntity<>("Ta kategorija vec postoji", HttpStatus.FORBIDDEN);
        }
        categoryRepository.save(category);
        return new ResponseEntity<>("Kategorija uspjesno spremljena", HttpStatus.ACCEPTED);
    }
    @Transactional
    public ResponseEntity<String> updateCategory(Long id, Category category) {

        if(!categoryRepository.existsById(id)){
            return new ResponseEntity<>("Location doesnt exists", HttpStatus.FORBIDDEN);
        }else{
            Category temp_cat = categoryRepository.findById(id).orElseThrow(()-> new IllegalStateException());
            temp_cat.setName(category.getName());
            temp_cat.setIcon(category.getIcon());

            return new ResponseEntity<>("Category has been updated", HttpStatus.OK);
        }
    }

}

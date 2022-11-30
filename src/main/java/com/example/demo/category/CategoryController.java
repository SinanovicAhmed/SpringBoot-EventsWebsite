package com.example.demo.category;
import com.example.demo.location.Location;
import com.example.demo.location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    @CrossOrigin("*")
    @GetMapping
    public ResponseEntity<List<Category>> getCategories(){
        return categoryService.getLocations();
    }
    @CrossOrigin("*")
    @GetMapping(value = "{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id){
        return categoryService.getCategoryById(id);
    }

    @ResponseBody
    @CrossOrigin("*")
    @PostMapping(value = "/save")
    public ResponseEntity<String> postCategory(@RequestBody Category category){
        return categoryService.addNewLocation(category);
    }

    @CrossOrigin("*")
    @PutMapping(value = "/updateCategory/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable("id") Long id,
                                                 @RequestBody Category category){
        return categoryService.updateCategory(id, category);
    }
}

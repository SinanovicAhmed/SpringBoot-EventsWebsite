package com.example.Dogadjaji212Application;

import com.example.Dogadjaji212Application.category.Category;
import com.example.Dogadjaji212Application.category.CategoryService;
import com.example.Dogadjaji212Application.events.Event;
import com.example.Dogadjaji212Application.location.Location;
import com.example.Dogadjaji212Application.location.LocationService;
import com.example.Dogadjaji212Application.user.User;
import com.example.Dogadjaji212Application.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Dogadjaji212Application {

	public static void main(String[] args) {
		SpringApplication.run(Dogadjaji212Application.class, args);
	}
	@Bean
	CommandLineRunner run(UserService userService, LocationService locationService, CategoryService categoryService){
		return args -> {
			User admin = new User("administrator","administrator","admin","admin",false);
			userService.addNewUser(admin);
			User user = new User("Ahmed","Sinanovic","ahmed@gmail.com","12345",false);
			userService.addNewUser(user);
			Location location = new Location("Sarajevo", "Glavni grad Bosne i Hercegovine", "https://deih43ym53wif.cloudfront.net/bascarsija-square-sebilj-fountain-sarajevo-bosnia-and-herzegovina-shutterstock_574540984_371f9b4a39.jpeg");
			locationService.addNewLocation(location);
			Category category = new Category("Party", "https://cdn-icons-png.flaticon.com/512/2729/2729035.png");
			categoryService.addNewLocation(category);
		};
	}
}

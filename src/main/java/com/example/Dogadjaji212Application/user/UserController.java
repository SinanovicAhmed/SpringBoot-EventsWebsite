package com.example.Dogadjaji212Application.user;

import com.example.Dogadjaji212Application.DAO.LoginDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path= "api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
       return userService.getUsers();
    }
    @ResponseBody
    @PostMapping(value = "/save")
    public ResponseEntity<String> addUser(@RequestBody User user){
        return userService.addNewUser(user);
    }

    @DeleteMapping(path="{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long id){
        return userService.deleteUser(id);
    }

    @PutMapping(path = "/updatepassword/{userId}")
    public ResponseEntity<String> updateUserPassword(@PathVariable("userId") Long id,
                                                     @RequestBody String password)
    {
        return userService.updateUserPassword(id, password);
    }
    @PutMapping(path = "/updatebanned/{userId}")
    public ResponseEntity<String> updateUserBanned(@PathVariable("userId") Long id)
    {
        return userService.updateUserBanned(id);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser (@RequestBody LoginDAO login){
        return userService.loginUser(login);
    }

}

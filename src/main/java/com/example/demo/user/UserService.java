package com.example.demo.user;
import com.example.demo.DAO.LoginDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    @ResponseBody
    public ResponseEntity<String> addNewUser(User user) {
        Optional<User> userEmail = userRepository.findUserByEmail(user.getEmail());

        if(userEmail.isPresent()){
            return new ResponseEntity<>("Email already exists", HttpStatus.FORBIDDEN);
        }else{
            userRepository.save(user);
            return new ResponseEntity<>("User saved to DB", HttpStatus.OK);
        }
    }

    public ResponseEntity<String> deleteUser(Long id) {
        if(!userRepository.existsById(id)){
            return new ResponseEntity<>("User not found", HttpStatus.FORBIDDEN);
        }else{
            userRepository.deleteById(id);
            return new ResponseEntity<>("User "+ id+ " succsesfully deleted!", HttpStatus.OK);
        }
    }
    @Transactional
    public ResponseEntity<String> updateUserPassword(Long id, String password) {

        if(!userRepository.existsById(id)){
            return new ResponseEntity<>("User doesnt exist!", HttpStatus.FORBIDDEN);
        }else{
            User user = userRepository.findById(id).orElseThrow(()-> new IllegalStateException());
            user.setPassword(password.trim());
            return new ResponseEntity<>("Users password has been updated!", HttpStatus.OK);
        }
    }

    @Transactional
    public ResponseEntity<String> updateUserBanned(Long id, boolean banned) {

        if(!userRepository.existsById(id)){
            return new ResponseEntity<>("User doesnt exist!", HttpStatus.FORBIDDEN);
        }else{
            User user = userRepository.findById(id).orElseThrow(()-> new IllegalStateException());
            user.setBanned(banned);
            return new ResponseEntity<>("Users banned status has been updated!", HttpStatus.OK);
        }
    }

    public ResponseEntity<?> loginUser (LoginDAO login){
        User user = userRepository.findUserByEmail(login.getEmail())
                .orElse(null);

        if(user!=null){
            if(user.getPassword().equals(login.getPassword())){
                return new ResponseEntity<User>(user,HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("Invalid password", HttpStatus.OK);
            }
        }else{
            return new ResponseEntity<String>("Email doesnt exists", HttpStatus.OK);
        }
    }
}

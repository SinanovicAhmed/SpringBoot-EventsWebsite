package com.example.Dogadjaji212Application.comments;
import com.example.Dogadjaji212Application.POJO.CommentRequest;
import com.example.Dogadjaji212Application.events.Event;
import com.example.Dogadjaji212Application.events.EventRepository;
import com.example.Dogadjaji212Application.user.User;
import com.example.Dogadjaji212Application.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }


    public ResponseEntity<List<?>> getComments() {
        return new ResponseEntity<>(commentRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> saveComment(CommentRequest comment) {
        User user = userRepository.findById(comment.getUser_id()).orElseThrow(()-> new IllegalStateException());
        Event event = eventRepository.findById(comment.getEvent_id()).orElseThrow(()-> new IllegalStateException());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.now();
        String date = dtf.format(localDate).toString();
        Comment comment_temp = new Comment(comment.getComment(), date, event, user);
        try{
            commentRepository.save(comment_temp);
            return new ResponseEntity<>("Comment is saved", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }

}

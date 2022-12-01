package com.example.demo.comments;

import com.example.demo.POJO.CommentRequest;
import com.example.demo.events.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path= "api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    @GetMapping
    public ResponseEntity<List<?>> getComments(){
        return commentService.getComments();
    }

    @PostMapping(path = "/save")
    public ResponseEntity<String> saveComment(@RequestBody CommentRequest comment){
        return commentService.saveComment(comment);
    }
}

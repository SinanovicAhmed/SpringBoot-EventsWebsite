package com.example.Dogadjaji212Application.comments;
import com.example.Dogadjaji212Application.events.Event;
import com.example.Dogadjaji212Application.user.User;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;


@Entity
@Table(name="COMMENTS")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String comment;
    private String date;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Comment(){};
    public Comment(String comment, String date, Event event, User user) {
        this.comment = comment;
        this.date = date;
        this.event = event;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

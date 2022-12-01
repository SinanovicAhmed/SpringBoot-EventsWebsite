package com.example.demo.events;

import com.example.demo.category.Category;
import com.example.demo.comments.Comment;
import com.example.demo.location.Location;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="EVENTS")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String date;
    private String image_url;

    @ManyToOne
    @JoinColumn(name="category_id")

    private Category category;

    @ManyToOne
    @JoinColumn(name="location_id")

    private Location location;

    @OneToMany(
            mappedBy = "event",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<Comment> comments;

    public Event(){};

    public Event(String name, String description, String date, String image_url, Category category, Location location) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.image_url = image_url;
        this.category = category;
        this.location = location;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setEvent(List<Comment> comments) {
        this.comments = comments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setNaziv(String name) {
        this.name= name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

}

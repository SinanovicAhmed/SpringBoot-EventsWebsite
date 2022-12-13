package com.example.demo.category;
import com.example.demo.events.Event;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="CATEGORIES")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String icon;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Event> events;
    public Category(){}
    public Category(String name, String icon) {
        this.name = name;
        this.icon = icon;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

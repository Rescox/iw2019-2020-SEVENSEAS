package es.uca.iw.sss.spring.backend.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Event implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private String description;
    @Column(nullable=false)
    private Long aforum;
    @Column(nullable=false)
    private String photo;
    @Column(nullable=false)
    private String init_time;   //Hora inicio
    @Column(nullable=false)     //Hora fin
    private String end_time;
    @Column(nullable=false)
    private Long price;
    @ManyToOne
    private Ship ship;

    public Event() {}
    public Event(String name, String description, Long aforum, String photo, String init_time, String end_time, Long price, Ship ship) {
        this.name = name;
        this.description = description;
        this.aforum = aforum;
        this.photo = photo;
        this.init_time = init_time;
        this.end_time = end_time;
        this.price = price;
        this.ship = ship;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAforum() {
        return aforum;
    }

    public void setAforum(Long aforum) {
        this.aforum = aforum;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getInit_time() {
        return init_time;
    }

    public void setInit_time(String init_time) {
        this.init_time = init_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }

        if (obj instanceof Restaurant && obj.getClass().equals(getClass())) {
            return this.id.equals(((Event) obj).id);
        }

        return false;
    }

    @Override
    public String toString() {
        return "Event{" +
                "event='" +  + '\'' +
                '}';
    }
}

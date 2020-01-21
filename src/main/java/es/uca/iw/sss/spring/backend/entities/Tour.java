package es.uca.iw.sss.spring.backend.entities;

import javax.persistence.*;

@Entity
public class Tour {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name = "";
    @Column(nullable = false)
    private String description = "";
    @Column(nullable = false)
    private Float price;
    @Column(nullable = false)
    private String schedule = "";
    @ManyToOne
    private Ship ship;

    public Tour()
    {

    }

    public Tour(String name, String description, Float price, String schedule) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.schedule = schedule;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
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

        if (obj instanceof Tour && obj.getClass().equals(getClass())) {
            return this.id.equals(((Tour) obj).id);
        }

        return false;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", schedule='" + schedule + '\'' +
                '}';
    }
}

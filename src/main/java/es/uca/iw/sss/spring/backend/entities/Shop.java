package es.uca.iw.sss.spring;

import es.uca.iw.sss.spring.Dish;
import es.uca.iw.sss.spring.Ship;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Shop implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private String description;
    @Column(nullable=false)
    private String open_time;
    @Column(nullable=false)
    private String close_time;
    @ManyToOne
    private Ship ship;

    public Shop() {}
    public Shop(String name, String description, Ship ship, String close_time, String open_time){
        this.name = name;
        this.description = description;
        this.ship = ship;
        this.close_time = close_time;
        this.open_time = open_time;
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

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public String getOpen_time() { return open_time; }

    public void setOpen_time(String open_time) { this.open_time = open_time; }

    public String getClose_time() { return close_time; }

    public void setClose_time(String close_time) { this.close_time = close_time; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Objects.equals(id, shop.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shop='" +  + '\'' +
                '}';
    }
}

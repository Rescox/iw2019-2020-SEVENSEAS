package es.uca.iw.sss.spring.backend.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Spa implements Serializable, Cloneable {
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
    private String phone;
    @ManyToOne
    private Ship ship;

    public Spa(){}
    public Spa(String name, String description, Long aforum, String photo, String phone, Ship ship)
    {
        this.name = name;
        this.description = description;
        this.aforum = aforum;
        this.phone = phone;
        this.photo = photo;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spa spa = (Spa) o;
        return Objects.equals(id, spa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Spa{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", aforum=" + aforum +
                ", photo='" + photo + '\'' +
                ", phone='" + phone + '\'' +
                ", ship=" + ship +
                '}';
    }
}

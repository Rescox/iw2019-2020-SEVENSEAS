package es.uca.iw.sss.spring;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private float price;
    @Column(nullable = false)
    private String services;
    @Column(nullable = false)
    private String date;
    @Column(nullable = false)
    private String hour;
    @Column(nullable = false)
    private Long persons;
    @ManyToOne
    private User user;
    @ManyToOne
    private Restaurant restaurant;

    public Reservation(float price, String services, String date, String hour, User user, Restaurant restaurant, Long persons) {
        this.price = price;
        this.services = services;
        this.date = date;
        this.hour = hour;
        this.user = user;
        this.restaurant = restaurant;
        this.persons = persons;
    }

    public Reservation()
    {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() { return hour; }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Long getPersons() {
        return persons;
    }

    public void setPersons(Long persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }

        if (obj instanceof Reservation && obj.getClass().equals(getClass())) {
            return this.id.equals(((Reservation) obj).id);
        }

        return false;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "price=" + price +
                ", services='" + services + '\'' +
                ", date='" + date + '\'' +
                '}';
    }


}

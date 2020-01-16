package es.uca.iw.sss.spring.backend.entities;

import javax.persistence.*;

@Entity
public class EventReservation {
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
    private Long persons;
    @Column
    private String services;
    @Column(nullable = false)
    private String date;
    @Column(nullable = false)
    private String hour;
    @ManyToOne
    private User user;
    @ManyToOne
    private Event event;

    public EventReservation(float price, Long persons, String services, String date, String hour, User user, Event event) {
        this.price = price;
        this.persons = persons;
        this.services = services;
        this.date = date;
        this.hour = hour;
        this.user = user;
        this.event = event;
    }

    public EventReservation()
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
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
            return this.id.equals(((EventReservation) obj).id);
        }

        return false;
    }

    @Override
    public String toString() {
        return "EventReservation{" +
                "price=" + price +
                ", services='" + services + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public Long getPersons() {
        return persons;
    }

    public void setPersons(Long persons) {
        this.persons = persons;
    }
}

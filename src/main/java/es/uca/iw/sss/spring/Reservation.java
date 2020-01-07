package es.uca.iw.sss.spring;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private float price;
    @Column
    private String services;
    @Column
    private String date;
    @Column
    private String hour;
    @Column
    private Long id_client;

    public Reservation(float price, String services, String date, String hour, Long id_client) {
        this.price = price;
        this.services = services;
        this.date = date;
        this.hour = hour;
        this.id_client = id_client;
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
        this.date = hour;
    }

    public Long getId_client() { return id_client; }

    public void setId_client(Long id_client) { this.id_client = id_client; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

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

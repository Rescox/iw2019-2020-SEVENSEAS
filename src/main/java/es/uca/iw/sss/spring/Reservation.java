package es.uca.iw.sss.spring;

import javax.persistence.*;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private float price;
    private String services;
    private String date;

    public Reservation(float price, String services, String date) {
        this.price = price;
        this.services = services;
        this.date = date;
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

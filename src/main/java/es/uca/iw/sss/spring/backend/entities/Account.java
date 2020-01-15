package es.uca.iw.sss.spring.backend.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String serviceName;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Double price;

    @OneToOne
    private User user;

    public Account(String serviceName, double price, User owner) {
        this.serviceName = serviceName;
        this.price = price;
        this.user = owner;
    }

    public Account() {

    }

    //Getters
    public Long getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    //Setters
    public void setPrice(Double price) {
        this.price = price;
    }

    public void setOwner(User user) {
        this.user = user;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "Reserva de " + user.getUsername();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}

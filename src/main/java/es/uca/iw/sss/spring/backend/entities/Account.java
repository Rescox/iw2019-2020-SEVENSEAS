package es.uca.iw.sss.spring.backend.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String serviceName;

    @Column(nullable=false)
    private Date date;

    @Column(nullable=false)
    private Double price;

    @OneToOne
    private User owner;

    public Account(String serviceName, double price, User owner) {
        this.serviceName = serviceName;
        this.price = price;
        this.owner = owner;
    }
    //Getters
    public Long getId() { return id; }
    public Double getPrice() { return price; }
    public Date getDate() { return date; }
    public String getServiceName() {
        return serviceName;
    }

    //Setters
    public void setPrice(Double price) { this.price = price; }
    public void setOwner(User owner) { this.owner = owner; }
    public void setDate(Date date) { this.date = date; }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String toString() {
        return "Reserva de " + owner.getUsername();
    }


}

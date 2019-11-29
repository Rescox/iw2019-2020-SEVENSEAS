package es.uca.iw.sss.spring;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private Date date;

    @Column(nullable=false)
    private Double price;

    @OneToOne
    private User owner;

    public Account(double price, User owner) {
        this.price = price;
        this.owner = owner;
    }
    //Getters
    public Long getId() { return id; }
    public Double getPrice() { return price; }
    public Date getDate() { return date; }

    //Setters
    public void setPrice(Double price) { this.price = price; }
    public void setOwner(User owner) { this.owner = owner; }

    @Override
    public String toString() {
        return "Reserva de " + owner.getUsername();
    }


}

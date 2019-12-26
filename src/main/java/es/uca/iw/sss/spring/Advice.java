package es.uca.iw.sss.spring;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Advice {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String advice = "";
    @ManyToOne
    private Ship ship;

    public Advice()
    {

    }

    public Advice(String advice)
    {
        this.advice = advice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
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

        if (obj instanceof Advice && obj.getClass().equals(getClass())) {
            return this.id.equals(((Advice) obj).id);
        }

        return false;
    }


    @Override
    public String toString() {
        return "Advice{" +
                "advice='" + advice + '\'' +
                '}';
    }
}

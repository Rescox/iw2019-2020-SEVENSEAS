package es.uca.iw.sss.spring;

import javax.persistence.*;

@Entity
public class Scale {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String entrance = "";
    private String departure = "";
    private String date = "";

    public Scale(String entrance, String departure, String date) {
        this.entrance = entrance;
        this.departure = departure;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
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

        if (obj instanceof Scale && obj.getClass().equals(getClass())) {
            return this.id.equals(((Scale) obj).id);
        }

        return false;
    }

    @Override
    public String toString() {
        return "Scales{" +
                "entrance='" + entrance + '\'' +
                ", departure='" + departure + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

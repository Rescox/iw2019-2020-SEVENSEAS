package es.uca.iw.sss.spring;

import javax.persistence.*;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String name = "";
    private String date = "";

    public Restaurant(String name, String date) {
        this.name = name;
        this.date = date;
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

        if (obj instanceof Restaurant && obj.getClass().equals(getClass())) {
            return this.id.equals(((Restaurant) obj).id);
        }

        return false;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                " name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

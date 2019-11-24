package es.uca.iw.sss.spring;

import javax.persistence.*;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String name = "";
    private String pic = "";
    private String advice = "";

    public City(String name, String pic, String advice) {
        this.name = name;
        this.pic = pic;
        this.advice = advice;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }

        if (obj instanceof City && obj.getClass().equals(getClass())) {
            return this.id.equals(((City) obj).id);
        }

        return false;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", advice='" + advice + '\'' +
                '}';
    }
}

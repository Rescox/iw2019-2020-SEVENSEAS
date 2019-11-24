package es.uca.iw.sss.spring;

import javax.persistence.*;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String name = "";

    public Service(String name)
    {
        this.name = name;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }

        if (obj instanceof Service && obj.getClass().equals(getClass())) {
            return this.id.equals(((Service) obj).id);
        }

        return false;
    }


    @Override
    public String toString() {
        return "Service{" +
                "Service='" + name + '\'' +
                '}';
    }
}

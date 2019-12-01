package es.uca.iw.sss.spring;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(unique = true, nullable = false)
    private String licensePlate = "";
    @Column(nullable = false)
    private String name = "";
    @Column(nullable = true)
    private String plane = "";
    @OneToMany(mappedBy = "ship")
    private Set<User> userSet = new HashSet<>();


    public Ship()
    {

    }

    public Ship(String licensePlate, String name, String plane) {
        this.licensePlate = licensePlate;
        this.name = name;
        this.plane = plane;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }

        if (obj instanceof Ship && obj.getClass().equals(getClass())) {
            return this.id.equals(((Ship) obj).id);
        }

        return false;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "licensePlate='" + licensePlate + '\'' +
                ", name='" + name + '\'' +
                ", plane='" + plane + '\'' +
                '}';
    }
}

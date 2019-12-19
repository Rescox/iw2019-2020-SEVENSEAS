package es.uca.iw.sss.spring;

import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

public class Restaurant {

    private String name;
    private String description;

    @ManyToMany(mappedBy = "restaurant")
    private Set<Dish> menu = new HashSet<>();

    private String photo;


}

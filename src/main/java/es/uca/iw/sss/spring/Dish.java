package es.uca.iw.sss.spring;

import javax.persistence.*;

@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String nameDish = "";
    private float price;

    public Dish(String nameDish, float price) {
        this.nameDish = nameDish;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameDish() {
        return nameDish;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "nameDish='" + nameDish + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }

        if (obj instanceof Dish && obj.getClass().equals(getClass())) {
            return this.id.equals(((Dish) obj).id);
        }

        return false;
    }
}

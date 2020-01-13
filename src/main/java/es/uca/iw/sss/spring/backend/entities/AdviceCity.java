package es.uca.iw.sss.spring.backend.entities;

import javax.persistence.*;

@Entity
public class AdviceCity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(nullable = false)
  private String advice = "";

  @ManyToOne private City city;

  public AdviceCity() {}

  public AdviceCity(String advice) {
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

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (this.id == null) {
      return false;
    }

    if (obj instanceof AdviceCity && obj.getClass().equals(getClass())) {
      return this.id.equals(((AdviceCity) obj).id);
    }

    return false;
  }

  @Override
  public String toString() {
    return "AdviceCity{" + "advice='" + advice + '\'' + '}';
  }
}

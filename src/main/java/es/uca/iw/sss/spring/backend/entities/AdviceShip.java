package es.uca.iw.sss.spring.backend.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class AdviceShip {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @NotEmpty(message = "This field is required")
  @Column(nullable = false)
  private String advice = "";

  @ManyToOne private Ship ship;

  public AdviceShip() {}

  public AdviceShip(String advice) {
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

    if (obj instanceof AdviceShip && obj.getClass().equals(getClass())) {
      return this.id.equals(((AdviceShip) obj).id);
    }

    return false;
  }

  @Override
  public String toString() {
    return "AdviceShip{" + "advice='" + advice + '\'' + '}';
  }
}

package de.gedoplan.showcase.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Planet {

  @Id
  private String id;

  private String name;
  private double mass;

  public Planet(String id, String name, double mass) {
    this.id = id;
    this.name = name;
    this.mass = mass;
  }

  protected Planet() {
  }
}

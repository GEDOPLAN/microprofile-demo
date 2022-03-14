package de.gedoplan.showcase.service;

import de.gedoplan.showcase.entity.Planet;
import de.gedoplan.showcase.persistence.PlanetRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PlanetService {

  @Inject
  PlanetRepository planetRepository;

  public double getGravity(String id1, String id2) {
    Planet planet1 = this.planetRepository.findById(id1);
    Planet planet2 = this.planetRepository.findById(id2);

    // For demostration purposes: assume some distance
    double distance = 42_000_000;

    // Calculate gravity
    double G = 6.674e-11;
    return G * planet1.getMass() * planet2.getMass() / distance / distance;
  }
}

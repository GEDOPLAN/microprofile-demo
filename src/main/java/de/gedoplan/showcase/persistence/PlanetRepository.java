package de.gedoplan.showcase.persistence;

import de.gedoplan.showcase.entity.Planet;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.Map;

@ApplicationScoped
public class PlanetRepository {

  // Use static map instead of real database
  private static final Map<String, Planet> PLANETS = Map.of(
    "sol3", new Planet("sol3", "Earth", 5.9722e24),
    "sol5", new Planet("sol5", "Jupiter", 1.899e27)
  );

  public Collection<Planet> findAll() {
    return PLANETS.values();
  }

  public Planet findById(String id) {
    return PLANETS.get(id);
  }
}

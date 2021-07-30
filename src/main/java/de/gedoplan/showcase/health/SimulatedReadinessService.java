package de.gedoplan.showcase.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import lombok.Getter;

@ApplicationScoped
public class SimulatedReadinessService {

  @Inject
  @ConfigProperty(name = "simulated.readiness")
  @Getter
  boolean ready;
}

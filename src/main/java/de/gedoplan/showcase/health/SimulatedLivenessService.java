package de.gedoplan.showcase.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import lombok.Getter;

@ApplicationScoped
public class SimulatedLivenessService {

  @Inject
  @ConfigProperty(name = "simulated.liveness")
  @Getter
  boolean live;
}

package de.gedoplan.showcase.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class LivenessCheck implements HealthCheck {

  @Inject
  SimulatedLivenessService simulatedLivenessService;

  @Override
  public HealthCheckResponse call() {
    return HealthCheckResponse
        .named("SimulatedLiveness")
        .status(this.simulatedLivenessService.isLive())
        .build();
  }

}

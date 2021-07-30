package de.gedoplan.showcase.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {

  @Inject
  SimulatedReadinessService simulatedReadinessService;

  @Override
  public HealthCheckResponse call() {
    return HealthCheckResponse
        .named("SimulatedReadiness")
        .status(this.simulatedReadinessService.isReady())
        .withData("freeMemory", Runtime.getRuntime().freeMemory())
        .build();
  }

}

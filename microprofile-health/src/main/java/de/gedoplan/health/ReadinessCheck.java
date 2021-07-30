package de.gedoplan.health;

import de.gedoplan.service.Service2;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

@ApplicationScoped
@Readiness
public class ReadinessCheck implements HealthCheck {

  @Inject
  Service2 service2;

  @Override
  public HealthCheckResponse call() {
    // Breaking change in MP 4: state is renamed to status
    // Workaround here: Use up and down instead of state/status
    // return HealthCheckResponse
    //     .named("Service2")
    //     .state(this.service2.isOk())
    //     .withData("memory", Runtime.getRuntime().freeMemory())
    //     .build();
    HealthCheckResponseBuilder builder = HealthCheckResponse
      .named("Service2")
      .withData("memory", Runtime.getRuntime().freeMemory());
    if (this.service2.isOk())
      return builder.up().build();
    else
      return builder.down().build();
  }

}

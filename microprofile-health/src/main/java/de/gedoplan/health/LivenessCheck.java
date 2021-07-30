package de.gedoplan.health;

import de.gedoplan.service.Service1;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@ApplicationScoped
@Liveness
public class LivenessCheck implements HealthCheck {

  @Inject
  Service1 service1;

  @Override
  public HealthCheckResponse call() {
    // Breaking change in MP 4: state is renamed to status
    // Workaround here: Use up and down instead of state/status
    // return HealthCheckResponse
    //     .named("Service1")
    //     .state(this.service1.isOk())
    //     .build();
    if (this.service1.isOk())
      return HealthCheckResponse.named("Service1").up().build();
    else
      return HealthCheckResponse.named("Service1").down().build();
  }

}

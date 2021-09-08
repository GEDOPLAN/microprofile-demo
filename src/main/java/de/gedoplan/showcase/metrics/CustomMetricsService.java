package de.gedoplan.showcase.metrics;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Gauge;

@ApplicationScoped
public class CustomMetricsService {

  @Gauge(name = "answerToLifeUniverseAndEverything", unit = MetricUnits.NONE)
  public long getAnswerToLifeUniverseAndEverything() {
    return 42;
  }

  /*
   * Metrics annotations are scanned at start/deployment time and the corresponding metrics are registered in the
   * application metrics registry. This happens for all annotations with the exception of @Gauge, which can be
   * registered lazily when the declaring bean is being instantiated.
   * Therefore we cheat a little bit by observing the application scope initialization event, forcing bean instantiation then.
   */
  void startUp(@Observes @Initialized(ApplicationScoped.class) Object object) {
  }

}

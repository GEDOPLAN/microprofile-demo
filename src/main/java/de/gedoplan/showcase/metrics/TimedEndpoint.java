package de.gedoplan.showcase.metrics;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.annotation.Timed;

@ApplicationScoped
@Path("timed")
public class TimedEndpoint {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Timed(name = "getHello")
  // @SimplyTimed(name = "getHello")
  public String getHello() {

    randomDelay();

    return "hello";
  }

  private static void randomDelay() {
    try {
      TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(250));
    } catch (InterruptedException e) {
      // ignore
    }

  }
}

package de.gedoplan.showcase.config;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("config")
@ApplicationScoped
public class ConfigEndpoint {

  // Demo of simple config api usage reading a value (normally) set by a system property
  @Path("java-version")
  @GET
  public String getJavaVersion() {
    return ConfigProvider
        .getConfig()
        .getValue("java.version", String.class);
  }

  // Demo of using injected config values (normally) set by environment variables
  @Inject
  @ConfigProperty(name = "HOME")
  Optional<String> home;

  @Inject
  @ConfigProperty(name = "USERPROFILE")
  Optional<String> userProfile;

  @Path("home")
  @GET
  public String getHome() {
    return this.home.orElse(this.userProfile.orElse("NO HOME!"));
  }

  // Demo of reading a property from microprofile-config.properties
  @Inject
  @ConfigProperty(name = "answer")
  int answer;

  @Path("answer")
  @GET
  public String getHello() {
    return "The answer to the ultimate question of life, the universe and everything is " + this.answer;
  }

}

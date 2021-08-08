package de.gedoplan.showcase.config;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("config")
@ApplicationScoped
@Produces(MediaType.TEXT_PLAIN)
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

  // Demo of using config profiles
  @Inject
  @ConfigProperty(name = "mp.config.profile", defaultValue = "none")
  String profile;

  @Inject
  @ConfigProperty(name = "quarkus.profile")
  Optional<String> quarkusProfile;

  @Inject
  @ConfigProperty(name = "database")
  String database;

  @Path("profile")
  @GET
  public CharSequence getProfileInfo() {
    StringBuilder sb = new StringBuilder();
    sb.append("In profile ").append(this.profile);
    if (this.quarkusProfile.isPresent()) {
      sb.append(" (with Quarkus profile ").append(this.quarkusProfile.get()).append(")");
    }
    sb.append(" database is ").append(this.database);
    return sb;
  }

  // Demo of using config profiles
  @Inject
  @ConfigProperty(name = "server.url")
  String serverUrl;

  @Path("expression")
  @GET
  public String getExpressionValue() {
    return this.serverUrl;
  }

}

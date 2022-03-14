package de.gedoplan.showcase.restclient;

import de.gedoplan.showcase.restclient.restcountries.eu.Country;
import de.gedoplan.showcase.restclient.restcountries.eu.CountryApi;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.util.List;

@Path("restclient")
@ApplicationScoped
public class RestClientResource {
  @Inject
  @RestClient
  CountryApi countryClient;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public int getCountryCount() {
    return this.countryClient.getAll().size();
  }

  @Inject
  @ConfigProperty(name = "CountryApi/mp-rest/url")
  URI countryApiUri;

  @GET
  @Path("api")
  @Produces(MediaType.APPLICATION_JSON)
  public int getCountryCountWithMpRestClientApi() throws Exception {
    try (CountryApi countryApi = RestClientBuilder.newBuilder()
      .baseUri(this.countryApiUri)
      .build(CountryApi.class)) {

      return countryApi.getAll().size();
    }
  }

  @GET
  @Path("classic")
  @Produces(MediaType.APPLICATION_JSON)
  public int getCountryCountWithJakartaRestClient() {
    Client client = null;
    try {
      client = ClientBuilder.newClient();
      return client
        .target(countryApiUri)
        .path("v2")
        .path("all")
        .request(MediaType.APPLICATION_JSON)
        .get(new GenericType<List<Country>>() {})
        .size();
    } finally {
      try {
        client.close();
      } catch (Exception e) {
        // ignore
      }
    }
  }
}
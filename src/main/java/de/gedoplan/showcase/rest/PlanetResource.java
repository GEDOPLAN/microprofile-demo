package de.gedoplan.showcase.rest;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.gedoplan.showcase.entity.Planet;
import de.gedoplan.showcase.persistence.PlanetRepository;
import de.gedoplan.showcase.service.PlanetService;

@ApplicationScoped
@Path("planet")
@Produces(MediaType.APPLICATION_JSON)
public class PlanetResource {

  @Inject
  PlanetRepository planetRepository;

  @GET
  public Collection<Planet> get() {
    return this.planetRepository.findAll();
  }

  @Inject
  PlanetService planetService;

  @GET
  @Path("{id1}-{id2}")
  public double getGravity(@PathParam("id1") String id1, @PathParam("id2") String id2) {
    return this.planetService.getGravity(id1, id2);
  }

}

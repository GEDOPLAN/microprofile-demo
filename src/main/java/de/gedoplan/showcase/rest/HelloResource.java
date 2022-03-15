package de.gedoplan.showcase.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("hello")
@ApplicationScoped
public class HelloResource {

    @GET
    @Produces("text/plain")
    public String get() {
        return "Hello, JakartaEE and MicroProfile";
    }
}

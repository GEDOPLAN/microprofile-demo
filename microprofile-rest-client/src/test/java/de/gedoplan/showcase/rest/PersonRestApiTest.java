package de.gedoplan.showcase.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import de.gedoplan.showcase.entity.Person;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.Before;
import org.junit.Test;

public class PersonRestApiTest {

  PersonRestApi client;

  @Before
  public void before() {
    try {
      this.client = RestClientBuilder.newBuilder()
          .baseUri(new URI("http://localhost:8080"))
          .build(PersonRestApi.class);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void test_01_DagobertAndDonalDuckExist() {
    List<Person> persons = this.client.getAll();

    boolean foundDagobert = false;
    boolean foundDonald = false;

    for (Person person : persons) {
      if ("Duck".equals(person.getName())) {
        if ("Dagobert".equals(person.getFirstname())) {
          foundDagobert = true;
        }
        if ("Donald".equals(person.getFirstname())) {
          foundDonald = true;
        }
      }
    }

    assertTrue("Dagobert not found", foundDagobert);
    assertTrue("Donald not found", foundDonald);
  }

  @Test
  public void test_02_PostGetDelete() throws Exception {
    Person person = new Person("Duck", "Tick-" + UUID.randomUUID().toString());

    // Post new Person
    Response response = this.client.createPerson(person);
    assertEquals("Wrong status code", Status.CREATED.getStatusCode(), response.getStatus());

    String newPersonUrl = response.getHeaderString(HttpHeaders.LOCATION);
    assertNotNull(newPersonUrl, "New person URL must not be null");

    int newPersonId = Integer.parseInt(newPersonUrl.substring(newPersonUrl.lastIndexOf('/') + 1));

    // Get new person and compare
    Person newPerson = this.client.getById(newPersonId);
    assertEquals("Wrong last name", person.getName(), newPerson.getName());
    assertEquals("Wrong first name", person.getFirstname(), newPerson.getFirstname());

    // Delete new person
    this.client.deletePerson(newPersonId);

    // Get new person again; expect NOT_FOUND
    try {
      this.client.getById(newPersonId);

      fail("Entry should have been deleted before");
    } catch (WebApplicationException e) {
      assertEquals("Wrong status code", Status.NOT_FOUND.getStatusCode(), e.getResponse().getStatus());
    } catch (Exception e) {
      fail("Wrong exception");
    }
  }

}

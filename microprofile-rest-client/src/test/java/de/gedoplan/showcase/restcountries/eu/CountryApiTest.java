package de.gedoplan.showcase.restcountries.eu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.net.URI;
import java.util.Collection;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.Before;
import org.junit.Test;

public class CountryApiTest {

  CountryApi client;

  @Before
  public void before() {
    try {
      this.client = RestClientBuilder.newBuilder()
          .baseUri(new URI("https://restcountries.eu/rest"))
          .build(CountryApi.class);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void test_01_getByCode() {
    Country country = this.client.getByCode("de");
    assertEquals(country.getName(), "Germany");
  }

  @Test
  public void test_02_getAll() {
    Collection<Country> countries = this.client.getAll();
    assertNotEquals(0, countries.size());
  }

}

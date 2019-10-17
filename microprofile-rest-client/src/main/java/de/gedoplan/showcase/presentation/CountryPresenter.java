package de.gedoplan.showcase.presentation;

import de.gedoplan.showcase.restcountries.eu.CountryApi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
@Named
public class CountryPresenter {

  @Inject
  @RestClient
  CountryApi countryClient;

  public int getCountryCount() {
    return this.countryClient.getAll().size();
  }
}

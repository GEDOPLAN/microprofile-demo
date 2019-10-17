package de.gedoplan.showcase.restcountries.eu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
  private String alpha2Code;
  private String name;
  private String capital;
}

package de.gedoplan.showcase.rest.impl;

import de.gedoplan.showcase.entity.Person;
import de.gedoplan.showcase.persistence.PersonRepository;
import de.gedoplan.showcase.rest.PersonRestApi;

import java.net.URI;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
public class PersonRestService implements PersonRestApi {

  @Inject
  PersonRepository personRepository;

  @Context
  UriInfo uriInfo;

  @Override
  public List<Person> getAll() {
    return this.personRepository.findAll();
  }

  @Override
  public Person getById(Integer id) {
    Person person = this.personRepository.findById(id);
    if (person != null) {
      return person;
    }

    throw new NotFoundException();
  }

  @Override
  public void updatePerson(Integer id, Person person) {
    if (!id.equals(person.getId())) {
      throw new BadRequestException("id of updated object must be unchanged");
    }

    this.personRepository.merge(person);
  }

  @Override
  public Response createPerson(Person person) {
    if (person.getId() != null) {
      throw new BadRequestException("id of new entry must not be set");
    }

    this.personRepository.persist(person);

    URI createdUri = this.uriInfo
        .getAbsolutePathBuilder()
        .path(ID_TEMPLATE)
        .resolveTemplate(ID_NAME, person.getId()).build();
    return Response.created(createdUri).build();
  }

  @Override
  public void deletePerson(Integer id) {
    this.personRepository.removeById(id);
  }

}

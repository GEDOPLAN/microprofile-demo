package de.gedoplan.showcase.persistence;

import de.gedoplan.showcase.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonRepository {

  ConcurrentMap<Integer, Person> storage = new ConcurrentHashMap<>();

  AtomicInteger idGenerator = new AtomicInteger();

  public void persist(Person person) {
    if (person.getId() != null) {
      throw new IllegalArgumentException("Person.id must not be set");
    }

    merge(person);
  }

  public Person merge(Person person) {
    if (person.getId() == null) {
      person.setId(this.idGenerator.incrementAndGet());
    }

    this.storage.put(person.getId(), person);

    return person;
  }

  public List<Person> findAll() {
    return new ArrayList<>(this.storage.values());
  }

  public Person findById(Integer id) {
    return this.storage.get(id);
  }

  public void removeById(Integer id) {
    this.storage.remove(id);
  }

  @PostConstruct
  void postConstruct() {
    persist(new Person("Duck", "Dagobert"));
    persist(new Person("Duck", "Donald"));
  }

}

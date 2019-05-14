package org.medium.examples.memcached.repository;

import org.medium.examples.memcached.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {
    Person getPersonById(String id);
}

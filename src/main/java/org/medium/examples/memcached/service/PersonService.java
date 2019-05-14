package org.medium.examples.memcached.service;

import java.util.Objects;
import org.medium.examples.memcached.entity.Person;
import org.medium.examples.memcached.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person getPerson(String id) {
        Person person = personRepository.getPersonById(id);
        if (Objects.nonNull(person)) {
            double salary = 5000.00;
            person.setSalary(salary);
        }
        return person;
    }
}

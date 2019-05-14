package org.medium.examples.memcached.controller;

import java.util.Calendar;
import java.util.Date;
import org.medium.examples.memcached.entity.Person;
import org.medium.examples.memcached.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("person")
public class WebController {

    @Autowired
    private PersonService personService;

    @GetMapping()
    public Person getPerson(@RequestParam String id) {

        Date startTime = Calendar.getInstance().getTime();
        Person person = personService.getPerson(id);
        Date endTime = Calendar.getInstance().getTime();
        System.out.println("Time taken for the request: " + (endTime.getTime() - startTime.getTime()) + "ms");

        return person;
    }

}

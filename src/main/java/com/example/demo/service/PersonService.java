package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDao personDao;

    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople() {
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID uuid) {
        return personDao.selectPersonById(uuid);
    }

    public int deletePerson(UUID uuid) {
        return personDao.deletePersonById(uuid);
    }

    public int updatePerson(UUID uuid, Person person) {
        return personDao.updatePersonById(uuid, person);
    }
}

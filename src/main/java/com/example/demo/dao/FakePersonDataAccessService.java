package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID uuid, Person person) {
        DB.add(new Person(uuid, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID uuid) {
        return DB.stream()
                .filter(person -> person.getId().equals(uuid))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID uuid) {
        Optional<Person> person = selectPersonById(uuid);
        if (person.isEmpty()) {
            return 0;
        }
        DB.remove(person.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID uuid, Person person) {
        return selectPersonById(uuid)
                .map(p -> {
                    int indexOfPersonToDelete = DB.indexOf(p);
                    if (indexOfPersonToDelete >= 0) {
                        DB.set(indexOfPersonToDelete, new Person(uuid, person.getName()));
                        return 1;
                    }
                    return 0;
                }).orElse(0);
    }
}

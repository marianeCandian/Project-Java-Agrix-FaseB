package com.betrybe.agrix.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class PersonServiceTest {

  @Autowired
  PersonService personService;

  @MockBean
  PersonRepository personRepository;

  @Test
  @DisplayName("1. Testa a criaão de uma nova pessoa")
  public void testPersonCriation() {
    Person person = new Person();
    person.setUsername("Maria");
    person.setPassword("3456");
    person.setRole(Role.USER);

    // return Person
    Person personToReturn = new Person();
    personToReturn.setId(321L);
    personToReturn.setUsername(person.getUsername());
    personToReturn.setPassword(person.getPassword());
    personToReturn.setRole(person.getRole());

    Mockito.when(personRepository.save(any(Person.class)))
        .thenReturn(personToReturn);

    Person savePerson = personService.create(person);

    Mockito.verify(personRepository).save(any(Person.class));

    assertEquals(321L, savePerson.getId());
    assertEquals(person.getUsername(), savePerson.getUsername());
    assertEquals(person.getPassword(), savePerson.getPassword());
    assertEquals(person.getRole(), savePerson.getRole());

  }

  @Test
  @DisplayName("2. Testa encontrar uma pessoa pelo Id")
  public void testPersonRetrievel() {
    Person person = new Person();
    person.setId(321L);
    person.setUsername("Maria");
    person.setPassword("4569");
    person.setRole(Role.USER);


    Mockito.when(personRepository.findById(eq(321L)))
        .thenReturn(Optional.of(person));

    Person returnPerson = personService.getPersonById(321L);

    Mockito.verify(personRepository).findById(eq(321L));

    assertEquals(returnPerson.getId(), person.getId());
    assertEquals(returnPerson.getUsername(), person.getUsername());
    assertEquals(returnPerson.getPassword(), person.getPassword());
    assertEquals(returnPerson.getRole(), person.getRole());
  }

  @Test
  @DisplayName("3. Testa se a exception é lancada")
  public void testPersonRetrievalNotFound() {
    Mockito.when(personRepository.findById(anyLong()))
        .thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(456L));

    Mockito.verify(personRepository).findById(eq(456L));

    Mockito.when(personRepository.findByUsername(anyString()))
        .thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.getPersonByUsername("João"));

    Mockito.verify(personRepository).findByUsername(eq("João"));
  }

  @Test
  @DisplayName("4. Testa se encontra a pessoa pelo UserName")
  public void testPersonByUsarName() {
    Person person = new Person();
    person.setId(321L);
    person.setUsername("Maria");
    person.setPassword("4569");
    person.setRole(Role.USER);


    Mockito.when(personRepository.findByUsername(eq("Maria")))
        .thenReturn(Optional.of(person));

    Person returnPerson = personService.getPersonByUsername("Maria");

    Mockito.verify(personRepository).findByUsername(eq("Maria"));

    assertEquals(returnPerson.getId(), person.getId());
    assertEquals(returnPerson.getUsername(), person.getUsername());
    assertEquals(returnPerson.getPassword(), person.getPassword());
    assertEquals(returnPerson.getRole(), person.getRole());
  }

}

package com.betrybe.agrix.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PersonServiceTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private PersonService personService;

  private Person person;

  @MockBean
  private PersonRepository personRepository;

  @Test
  public void getPersonByIdReturnsException() {
    Mockito.when(personRepository.findById(any())).thenReturn(Optional.empty());
    Long id = 999L;
    assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(id));
  }

  @Test
  public void getPersonByIdReturnsPerson() {
    Long id = 1L;

    Person newPerson = mockPerson(id);

    Mockito.when(personRepository.findById(any())).thenReturn(Optional.of(newPerson));
    Person result = personService.getPersonById(id);
    assertEquals(newPerson, result);
  }

  @Test
  public void getPersonByUsernameReturnsPerson() {
    Long id = 2L;

    Person newPerson = mockPerson(id);

    Mockito.when(personRepository.findByUsername(any())).thenReturn(Optional.of(newPerson));

    Person result = personService.getPersonByUsername("germancano");

    assertEquals(newPerson, result);


  }

  private Person mockPerson(Long id) {
    Person person = new Person();
    person.setId(id);
    person.setPassword("fluminense");
    person.setUsername("germancano");
    return person;
  }
}

package in.kodecamp.testpoc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TestPersonRepository {

	@Mock
	private PersonRepository personRepository;


	@Test
	void testAddNew() {

		PersonEntity p1 = new PersonEntity();
		p1.setId(UUID.randomUUID());
		p1.setName("Name 4");
		p1.setAddress("Address 4");

		PersonEntity p2 = new PersonEntity();
		p2.setId(UUID.randomUUID());
		p2.setName("Name 5");
		p2.setAddress("Address 5");

		PersonEntity p3 = new PersonEntity();
		p3.setId(UUID.randomUUID());
		p3.setName("Name 6");
		p3.setAddress("Address 6");

		Iterable<PersonEntity> people = List.of(p1, p2, p3);

		when(personRepository.findAll()).thenReturn(people);

		Iterable<PersonEntity> allPerson = personRepository.findAll();

		allPerson.forEach((person) -> {
				System.out.println("-----------> " + person);
		});

		verify(personRepository, times(1)).findAll();


	}

}

package in.kodecamp.testpoc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestPersonService {

	@InjectMocks
	private PersonService service;

	@Mock
	private PersonRepository repository;

	@Test
	@DisplayName(" --- TestPersonService : testCreateNew")
	void testCreateNew() {

		PersonEntity pe = new PersonEntity();

		UUID uid = UUID.randomUUID();
		pe.setId(uid);
		pe.setName("Sunil");
		pe.setAddress("Naini");

		// intercepting repository method
		when(repository.save(any())).thenReturn(pe);

		PersonEntity savedPerson = service.createNew(pe);
		Assertions.assertThat(savedPerson.getId()).isEqualTo(uid);
		Assertions.assertThat(savedPerson).isNotNull();
		verify(repository, times(1)).save(any());
	}

	@Test
	@DisplayName(" --- TestPersonService : testFindAll")
	void testFindAll() {

		List<PersonEntity> people = List.of(
				PersonEntity.from("Sunil", "Naini"),
				PersonEntity.from("Rakesh", "Delhi"),
				PersonEntity.from("Suresh", "Sultanpur"));

		when(repository.findAll()).thenReturn(people);

		Iterable<PersonEntity> fetchedPerson = service.findAll();

		long count = StreamSupport.stream(fetchedPerson.spliterator(),false).count();

		Assertions.assertThat(count).isEqualTo(3);
	}

}

package in.kodecamp.testpoc;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/people")
final class PersonController {

	private PersonService personService;

	public PersonController(PersonService personService) {
		System.out.println("Hello World");
		this.personService = personService;
	}


	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(
				new CollectionResponseObj(personService.findAll())
				);
	}

	@PostMapping("/new")
	public ResponseEntity<?> createNew(@RequestBody PersonEntity entity) {
		System.out.println("=================== " + entity);
		return ResponseEntity.ok(new SingleResponseObj(personService.createNew(entity)));
	}


	static class SingleResponseObj implements Serializable {
		PersonEntity value;

		SingleResponseObj(PersonEntity value) {
			this.value = value;
		}

		public PersonEntity getValue() {
			return this.value;
		}


	}

	static class CollectionResponseObj implements Serializable{
		Iterable<PersonEntity> values;

		CollectionResponseObj(Iterable<PersonEntity> values) {
			this.values = values;
		}

		public Iterable<PersonEntity> getValues() {
			return this.values;
		}
	}

}

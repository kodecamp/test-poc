package in.kodecamp.testpoc;

import org.springframework.stereotype.Service;

@Service
public class PersonService {

	private final PersonRepository repository;

	public PersonService(PersonRepository repo) {
		this.repository = repo;
	}

	public PersonEntity createNew(PersonEntity entity){
		return repository.save(entity);
	}

	public Iterable<PersonEntity> findAll(){
		return repository.findAll();
	}

	public Long noOfPerson(){
		return repository.count();
	}

}

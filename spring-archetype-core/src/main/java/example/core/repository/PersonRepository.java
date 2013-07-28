package example.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import example.core.domain.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}

package mercadolibre.mutant.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import mercadolibre.mutant.entity.Person;

public interface PersonRepository extends MongoRepository<Person,String>{
	public Person findByDna(String dna);
    public List<Person> findByIsMuntat(boolean isMuntat);
}

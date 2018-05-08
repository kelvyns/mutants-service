package mercadolibre.mutant.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mercadolibre.mutant.entity.Person;
import mercadolibre.mutant.entity.PersonStats;
import mercadolibre.mutant.exception.ManagerExeption;
import mercadolibre.mutant.exception.RepositoryExeption;
import mercadolibre.mutant.manager.StatsManager;
import mercadolibre.mutant.repository.PersonRepository;

/**
 * @author kelvyns
 *
 * This interface content the principal signatures for get stasts 
 */
@Component
public class StatsManagerImpl implements StatsManager{

	@Autowired
	private PersonRepository personRepository;
	/**
	 * This method get stasts 
	 * 
	 * @return PersonStats
	 * @throws ManagerExeption
	 * @throws RepositoryExeption 
	 */
	@Override
	public PersonStats getStats() throws RepositoryExeption {
		List <Person> humans = new ArrayList<Person>();
		List <Person> mutants = new ArrayList<Person>();
		try {
			humans = personRepository.findByIsMuntat(false);
			mutants = personRepository.findByIsMuntat(true);
		} catch (Exception e) {
			throw new RepositoryExeption("Error to get table person in database");
		}
		PersonStats personStats = new PersonStats(humans.size(), mutants.size());
		return personStats;
	}

}
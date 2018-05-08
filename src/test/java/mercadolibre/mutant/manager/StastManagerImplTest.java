package mercadolibre.mutant.manager;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import mercadolibre.mutant.entity.Person;
import mercadolibre.mutant.entity.PersonStats;
import mercadolibre.mutant.exception.ManagerExeption;
import mercadolibre.mutant.exception.RepositoryExeption;
import mercadolibre.mutant.manager.impl.StatsManagerImpl;
import mercadolibre.mutant.repository.PersonRepository;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class StastManagerImplTest {
	
	@Mock
	PersonRepository personRepository;
	
	@InjectMocks
    StatsManagerImpl statsManagerImpl;
	
	@Test
	public void testGetStats() throws ManagerExeption, RepositoryExeption {
		List<Person> personsH = new ArrayList<Person>();
		List<Person> personsM = new ArrayList<Person>();
		String dnaH = "CAGT-CAGT-CAGT-CAGT";
		String dnaM = "CAGT-ATCG-CAGT-ATCG";
		Person personM = new Person(dnaM, true);
		System.out.println(personM.toString());
		Person personH = new Person(dnaH, false);
		System.out.println(personH.toString());
		personsH.add(personH);
		personsM.add(personM);
		PersonStats personStats = new PersonStats(personsH.size(), personsM.size());
		when(personRepository.findByIsMuntat(true)).thenReturn(personsH);
		when(personRepository.findByIsMuntat(false)).thenReturn(personsM);
		
		assertTrue(personM.getDna().equals(dnaM));
		assertTrue(personH.getDna().equals(dnaH));
		
		assertTrue(personM.isMuntat());
		assertTrue(!personH.isMuntat());
		
		 assertTrue(personStats.getCount_human_dna()==statsManagerImpl.getStats().getCount_human_dna());
		 assertTrue(personStats.getCount_mutant_dna()==statsManagerImpl.getStats().getCount_mutant_dna());
		 assertTrue(personStats.getRatio()==statsManagerImpl.getStats().getRatio());
	}
	
}

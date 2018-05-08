package mercadolibre.mutant.manager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import mercadolibre.mutant.entity.Person;
import mercadolibre.mutant.exception.InvalidDnaExeption;
import mercadolibre.mutant.exception.RepositoryExeption;
import mercadolibre.mutant.manager.impl.MutantsManagerImpl;
import mercadolibre.mutant.repository.PersonRepository;

/**
 * @author kelvyns
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MutantsManagerImplTest {
	
	@Mock
	PersonRepository personRepository;
	
	@InjectMocks
    MutantsManagerImpl mutantsManagerImpl;
	
	/**
	 * This test has a invalid length in DNA
	 * @throws RepositoryExeption 
	 *
	 */
	@Test
	public void testIsMutantInvalidLenght() throws RepositoryExeption {
		// DNA N = 3
		// throw new InvalidDnaExeption("Ilegal Structure in DNA. N:"+nRow);
		String[] dna = {"AAA","AAA","AAA"};
		try {
			mutantsManagerImpl.isMutant(dna);
		} catch (InvalidDnaExeption e) {
			assertTrue("Ilegal Structure in DNA. N:3".equals(e.getMessage()));
		} 
	
	}
	
	/**
	 * This test has a invalidCharacter in DNA
	 * @throws RepositoryExeption 
	 *
	 */
	@Test
	public void testIsMutantInvalidCharacter() throws RepositoryExeption {
		// DNA {"ATGC","ATGC","ATGC","ATGX"} X is character invalid
		//throw new InvalidDnaExeption("Invalid characters in DNA");
		String[] dna = {"ATGC","ATGC","ATGC","ATGX"};
		try {
			mutantsManagerImpl.isMutant(dna);
		} catch (InvalidDnaExeption e) {
			assertTrue("Invalid characters in DNA".equals(e.getMessage()));
		} 
	
	}
	/**
	 * This test has a invalid structure in DNA
	 * @throws RepositoryExeption 
	 *
	 */
	@Test
	public void testIsMutantInvalidStructureDna() throws RepositoryExeption {
		// DNA {"ATGC","ATGC","ATGC","ATGC","ATGC"} NxN -> 4x5 is invalid 	
		//throw new InvalidDnaExeption("Ilegal Structure in DNA");
		String[] dna = {"ATGC","ATGC","ATGC","ATGC","ATGC"};
		try {
			mutantsManagerImpl.isMutant(dna);
		} catch (InvalidDnaExeption e) {
			assertTrue("Ilegal Structure in DNA".equals(e.getMessage()));
		} 
	}
	
	/**
	 * This is a muntat and will save in BD
	 * @throws InvalidDnaExeption 
	 *
	 */
	@Test
	public void testIsMutantIsTrueAndWillSaveInBD() throws RepositoryExeption, InvalidDnaExeption {
		// DNA {"AAAA","AAAA","AAAA","AAAA"} 
		String[] dna = {"AAAA","AAAA","AAAA","AAAA"};
		when(personRepository.findByDna(Mockito.anyString())).thenReturn(null);
		mutantsManagerImpl.isMutant(dna);
		
	}
	
	/**
	 * This is a muntat and wont save in BD because exist in BD
	 * @throws InvalidDnaExeption 
	 *
	 */
	@Test
	public void testIsMutantIsTrueAndWontSaveInBD() throws RepositoryExeption, InvalidDnaExeption {
		// DNA {"AAAA","AAAA","AAAA","AAAA"} 
		String[] dna = {"AAAA","AAAA","AAAA","AAAA"};
		Person personM = new Person(String.join("-", dna), true);
		when(personRepository.findByDna(Mockito.anyString())).thenReturn(personM);
		mutantsManagerImpl.isMutant(dna);
	}
	
	
	/**
	 * This is a mutant because was in the horizontal pattern
	 * @throws InvalidDnaExeption 
	 *
	 */
	@Test
	public void testIsMutantIsTrueInHorizontalAnalize() throws RepositoryExeption, InvalidDnaExeption {
		// DNA {"AAAA","AAAA","AAAA","AAAA"} 
		String[] dna = {"AAAAGT",
				        "TTGCGG",
				        "ATATAT",
				        "TAGCGA",
				        "ATACGT",
				        "ACCCCT"};
		when(personRepository.findByDna(Mockito.anyString())).thenReturn(null);
		assertTrue(mutantsManagerImpl.isMutant(dna));
		
	}
	
	/**
	 * This is a mutant because was in the veritical pattern
	 * @throws InvalidDnaExeption 
	 *
	 */
	@Test
	public void testIsMutantIsTrueInVerticalAnalize() throws RepositoryExeption, InvalidDnaExeption {
		// DNA {"AAAA","AAAA","AAAA","AAAA"} 
		String[] dna = {"AAACGT",
				        "TTGCGG",
				        "ATATGT",
				        "AAGCGA",
				        "ATGCAT",
				        "ATGCGT"};
		when(personRepository.findByDna(Mockito.anyString())).thenReturn(null);
		mutantsManagerImpl.isMutant(dna);
		assertTrue(mutantsManagerImpl.isMutant(dna));
	}
	
	/**
	 * This is a mutant because was in the oblique Up pattern
	 * @throws InvalidDnaExeption 
	 *
	 */
	@Test
	public void testIsMutantIsTrueInObliqueUpAnalize() throws RepositoryExeption, InvalidDnaExeption {
		// DNA {"AAAA","AAAA","AAAA","AAAA"} 
		String[] dna = {"AAACCT",
				        "TTGAGG",
				        "ATATAT",
				        "AAGCGA",
				        "CTACAT",
				        "ATGAGT"};
		when(personRepository.findByDna(Mockito.anyString())).thenReturn(null);
		mutantsManagerImpl.isMutant(dna);
		assertTrue(mutantsManagerImpl.isMutant(dna));
	}
	
	/**
	 * This is a mutant because was in the oblique Down pattern
	 * @throws InvalidDnaExeption 
	 *
	 */
	@Test
	public void testIsMutantIsTrueInObliqueDownAnalize() throws RepositoryExeption, InvalidDnaExeption {
		// DNA {"AAAA","AAAA","AAAA","AAAA"} 
		String[] dna = {"AAACCT",
				        "TTCTGG",
				        "ACATAC",
				        "CGGCCA",
				        "CTACAT",
				        "ATCAGT"};
		when(personRepository.findByDna(Mockito.anyString())).thenReturn(null);
		mutantsManagerImpl.isMutant(dna);
		assertTrue(mutantsManagerImpl.isMutant(dna));
	}
	
	/**
	 * This is not a mutant 
	 * @throws InvalidDnaExeption 
	 *
	 */
	@Test
	public void testIsMutantIsFalse() throws RepositoryExeption, InvalidDnaExeption {
		// DNA {"AAAA","AAAA","AAAA","AAAA"} 
		String[] dna = {"AAACCT",
				        "TTCTGG",
				        "ACATAC",
				        "CGGCTA",
				        "CTACAT",
				        "ATCAGT"};
		when(personRepository.findByDna(Mockito.anyString())).thenReturn(null);
		mutantsManagerImpl.isMutant(dna);
		assertFalse(mutantsManagerImpl.isMutant(dna));
	}
	
}

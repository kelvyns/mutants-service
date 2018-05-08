package mercadolibre.mutant.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import mercadolibre.mutant.entity.DnaContent;
import mercadolibre.mutant.exception.InvalidDnaExeption;
import mercadolibre.mutant.exception.RepositoryExeption;
import mercadolibre.mutant.exception.ServiceExeption;
import mercadolibre.mutant.manager.MutantsManager;
import mercadolibre.mutant.manager.StatsManager;
import mercadolibre.mutant.service.impl.ApplicationServiceImpl;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ApplicationServiceImplTest {
	
	@Mock
	MutantsManager mutantsManager;
	
	@Mock
	StatsManager statsManager;
	
	@InjectMocks
	ApplicationServiceImpl applicationServiceImpl;
	
	/**
	 * This test is when dna is mutant
	 * @throws RepositoryExeption 
	 * @throws InvalidDnaExeption 
	 * @throws ServiceExeption 
	 * 
	 */
	@Test
	public void testIsMutant() throws InvalidDnaExeption, RepositoryExeption, ServiceExeption  {
		DnaContent dnaContent = new DnaContent();
		dnaContent.setDna(new String[] {});
		when(mutantsManager.isMutant(Mockito.any())).thenReturn(true);
		ResponseEntity<String> response = applicationServiceImpl.isMutant(dnaContent);
		assertTrue(response.getStatusCodeValue()==200);
	}
	/**
	 * This test is when dna is not mutant
	 * @throws RepositoryExeption 
	 * @throws InvalidDnaExeption 
	 * @throws ServiceExeption 
	 * 
	 */
	@Test
	public void testIsNotMutant() throws InvalidDnaExeption, RepositoryExeption, ServiceExeption  {
		DnaContent dnaContent = new DnaContent();
		dnaContent.setDna(new String[] {});
		when(mutantsManager.isMutant(Mockito.any())).thenReturn(false);
		ResponseEntity<String> response = applicationServiceImpl.isMutant(dnaContent);
		assertTrue(response.getStatusCodeValue()==403);
	}
	/**
	 * This test get Stats
	 * @throws RepositoryExeption 
	 * 
	 */
	@Test
	public void testGetStats() throws RepositoryExeption {
		when(statsManager.getStats()).thenReturn(null);
		assertTrue(applicationServiceImpl.getStats()==null);
	}
	
}

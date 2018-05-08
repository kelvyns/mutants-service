package mercadolibre.mutant.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import mercadolibre.mutant.entity.DnaContent;
import mercadolibre.mutant.exception.InvalidDnaExeption;
import mercadolibre.mutant.exception.RepositoryExeption;
import mercadolibre.mutant.exception.ServiceExeption;
import mercadolibre.mutant.service.ApplicationService;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ApplicationControllerTest {

	@Mock
	ApplicationService applicationService;
	
	@InjectMocks
    ApplicationController applicationController;
	
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
		ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.OK);
		when(applicationService.isMutant(Mockito.any())).thenReturn(response);
		ResponseEntity<String> responseApp = applicationController.isMutant(dnaContent);
		assertTrue(responseApp.getStatusCodeValue()==200);
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
		ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		when(applicationService.isMutant(Mockito.any())).thenReturn(response);
		ResponseEntity<String> responseApp = applicationController.isMutant(dnaContent);
		assertTrue(responseApp.getStatusCodeValue()==403);
	}
	/**
	 * This test get Stats
	 * @throws RepositoryExeption 
	 * @throws ServiceExeption 
	 * 
	 */
	@Test
	public void testGetStats() throws RepositoryExeption, ServiceExeption {
		when(applicationService.getStats()).thenReturn(null);
		assertTrue(applicationController.stats()==null);
	}
	
	@Test
	public void testIsMutantServiceExeption() throws RepositoryExeption, ServiceExeption, InvalidDnaExeption {
		when(applicationService.isMutant(Mockito.any())).thenThrow(new ServiceExeption("test"));
		DnaContent dnaContent = new DnaContent();
		ResponseEntity<String> responseApp = applicationController.isMutant(dnaContent);
		assertTrue(responseApp.getStatusCodeValue()==500);
	}
	
	@Test
	public void testIsMutantInvalidDnaExeption() throws RepositoryExeption, ServiceExeption, InvalidDnaExeption {
		when(applicationService.isMutant(Mockito.any())).thenThrow(new InvalidDnaExeption("test"));
		DnaContent dnaContent = new DnaContent();
		ResponseEntity<String> responseApp = applicationController.isMutant(dnaContent);
		assertTrue(responseApp.getStatusCodeValue()==403);
	}
	@Test
	public void testIsMutantRepositoryExeption() throws RepositoryExeption, ServiceExeption, InvalidDnaExeption {
		when(applicationService.isMutant(Mockito.any())).thenThrow(new RepositoryExeption("test"));
		DnaContent dnaContent = new DnaContent();
		ResponseEntity<String> responseApp = applicationController.isMutant(dnaContent);
		assertTrue(responseApp.getStatusCodeValue()==409);
	}
	
	@Test
	public void testGetStatsServiceExeption() throws ServiceExeption, RepositoryExeption  {
		when(applicationService.getStats()).thenThrow(new ServiceExeption("test"));
		applicationController.stats();
	}
	@Test
	public void testGetStatsRepositoryExeption() throws ServiceExeption, RepositoryExeption  {
		when(applicationService.getStats()).thenThrow(new RepositoryExeption("test"));
		applicationController.stats();
	}
}

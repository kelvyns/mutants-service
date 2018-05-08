package mercadolibre.mutant.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import mercadolibre.mutant.exception.InvalidDnaExeption;
import mercadolibre.mutant.exception.RepositoryExeption;
import mercadolibre.mutant.exception.ServiceExeption;
import mercadolibre.mutant.repository.PersonRepository;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RepositoryControllerTest {

	@Mock
	PersonRepository personRepository;
	
	@InjectMocks
	RepositoryController repositoryController;
	
	@Test
	public void testClear() throws InvalidDnaExeption, RepositoryExeption, ServiceExeption  {
		repositoryController.clear();
	}
	
	@Test
	public void testList() throws InvalidDnaExeption, RepositoryExeption, ServiceExeption  {
		repositoryController.list();
	}
	
	
}

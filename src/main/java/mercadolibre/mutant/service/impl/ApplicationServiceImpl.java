package mercadolibre.mutant.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mercadolibre.mutant.entity.DnaContent;
import mercadolibre.mutant.entity.PersonStats;
import mercadolibre.mutant.exception.InvalidDnaExeption;
import mercadolibre.mutant.exception.ManagerExeption;
import mercadolibre.mutant.exception.RepositoryExeption;
import mercadolibre.mutant.exception.ServiceExeption;
import mercadolibre.mutant.manager.MutantsManager;
import mercadolibre.mutant.manager.StatsManager;
import mercadolibre.mutant.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private MutantsManager  mutantsManager;
	
	@Autowired
	private StatsManager  statsManager;
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationServiceImpl.class);
	
	/**
	 * This method return true if the dna represent a mutant or false is not a mutant
	 * 
	 * @param dna represent the vector with characters 
	 * @return true if the dna represent a mutant or false is not a mutant
	 * @throws InvalidDnaExeption ServiceExeption
	 * @throws RepositoryExeption 
	 */
	@Override
	public ResponseEntity<String> isMutant(DnaContent dna) throws ServiceExeption, InvalidDnaExeption, RepositoryExeption {
		ResponseEntity<String> response = null;
		if( mutantsManager.isMutant(dna.getDna()) ) {
			logger.info("HttpStatus: 200");
			response = new ResponseEntity<>(HttpStatus.OK);
		} else {
			logger.info("HttpStatus: 403");
			response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return response;
	}

	/**
	 * @throws ManagerExeption 
	 * This method return PersonStats with mutants, humans totals and ratio
	 * @param  
	 * @return PersonStats (mutants,humans totals and ratio)
	 * @throws ManagerExeption
	 * @throws RepositoryExeption 
	 */
	@Override
	public PersonStats getStats() throws RepositoryExeption {
		PersonStats personStats = statsManager.getStats();
		return personStats;
	}

}

package mercadolibre.mutant.service;

import org.springframework.http.ResponseEntity;

import mercadolibre.mutant.entity.DnaContent;
import mercadolibre.mutant.entity.PersonStats;
import mercadolibre.mutant.exception.InvalidDnaExeption;
import mercadolibre.mutant.exception.ManagerExeption;
import mercadolibre.mutant.exception.RepositoryExeption;
import mercadolibre.mutant.exception.ServiceExeption;


/**
 * @author kelvyns
 *
 * This interface content the principal service signatures for study mutants 
 */
public interface ApplicationService {
	
	/**
	 * This method return true if the dna represent a mutant or false is not a mutant
	 * 
	 * @param DnaContent represent the dna sequences 
	 * @return ResponseEntity<String> if is mutant return OK else FORBIDDEN
	 * @throws InvalidDnaExeption ServiceExeption
	 * @throws InvalidDnaExeption
	 * @throws RepositoryExeption 
	 */
	public ResponseEntity<String> isMutant(DnaContent dna) throws ServiceExeption, InvalidDnaExeption, RepositoryExeption;
	
	/**
	 * This method return PersonStats with mutants, humans totals and ratio
	 * 
	 * @param  
	 * @return PersonStats (mutants,humans totals and ratio)
	 * @throws ManagerExeption ServiceExeption
	 * @throws RepositoryExeption 
	 * @throws InvalidDnaExeption
	 */
	public PersonStats getStats() throws ServiceExeption, ManagerExeption, RepositoryExeption;
}

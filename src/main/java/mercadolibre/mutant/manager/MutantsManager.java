package mercadolibre.mutant.manager;

import mercadolibre.mutant.exception.InvalidDnaExeption;
import mercadolibre.mutant.exception.RepositoryExeption;


/**
 * @author kelvyns
 *
 * This interface content the principal signature for study mutants 
 */
public interface MutantsManager {
	
	/**
	 * This method return true if the dna represent a mutant or false is not a mutant
	 * 
	 * @param dna represent the vector with characters 
	 * @return true if the dna represent a mutant or false is not a mutant
	 * @throws InvalidDnaExeption
	 * @throws RepositoryExeption 
	 */
	public boolean isMutant(String[] dna) throws InvalidDnaExeption, RepositoryExeption;
	
}

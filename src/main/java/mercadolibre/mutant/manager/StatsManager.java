package mercadolibre.mutant.manager;

import mercadolibre.mutant.entity.PersonStats;
import mercadolibre.mutant.exception.ManagerExeption;
import mercadolibre.mutant.exception.RepositoryExeption;

/**
 * @author kelvyns
 *
 * This interface content the principal signature for get stasts 
 */
public interface StatsManager {
	
	/**
	 * This method get stasts 
	 * 
	 * @return PersonStats
	 * @throws ManagerExeption
	 * @throws RepositoryExeption 
	 */
	public PersonStats getStats() throws ManagerExeption, RepositoryExeption;
}

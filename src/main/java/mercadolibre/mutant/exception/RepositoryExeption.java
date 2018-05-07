package mercadolibre.mutant.exception;

	
/**
 * @author kelvyns
 * This exeption is caused by problems with database
 *
 */
public class RepositoryExeption extends GeneralException {

	private static final long serialVersionUID = 1L;
	
	public RepositoryExeption(String message) {
		super(message);
	}
}

package mercadolibre.mutant.exception;

	
/**
 * @author kelvyns
 * This exeption is caused by problems in Services
 *
 */
public class ServiceExeption extends GeneralException {

	private static final long serialVersionUID = 1L;
	
	public ServiceExeption(String message) {
		super(message);
	}
}

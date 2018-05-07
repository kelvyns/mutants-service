package mercadolibre.mutant.exception;

	
/**
 * @author kelvyns
 * This exeption is caused by a DNA invalid
 *
 */
public class InvalidDnaExeption extends GeneralException {

	private static final long serialVersionUID = 1L;
	
	public InvalidDnaExeption(String message) {
		super(message);
	}
}

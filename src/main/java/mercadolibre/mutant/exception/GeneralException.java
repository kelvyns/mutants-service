package mercadolibre.mutant.exception;

	
/**
 * @author kelvyns
 * This is the exeption base
 *
 */
public abstract class GeneralException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;
	
	public GeneralException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}

package mercadolibre.mutant.manager;

import java.util.logging.Level;

/**
 * @author kelvyns
 *
 * This interface content the principal signatures for to do logging 
 */
public interface LoggerManager {
	
	/**
	 * This method to do logging in the app - INFO
	 * 
	 * @param String message
	 * @throws
	 */
	public void doLog(String message);
	
	/**
	 * This method to do logging in the app 
	 * 
	 * @param Level level, String message
	 * @throws
	 */
	public void doLog(Level level, String message);
	
	/**
	 * This method to do logging in the app with expection
	 * 
	 * @param Level level, String message, Throwable throwable
	 * @throws
	 */
	public void doLog(Level level, String message, Throwable throwable);
}

package mercadolibre.mutant.manager.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import mercadolibre.mutant.manager.LoggerManager;

/**
 * @author kelvyns
 *
 * This class is for logs management 
 */
@Component
public class LoggerManagerImpl implements LoggerManager{
	
	private static Logger logger =  Logger.getGlobal();

	/**
	 * This method to do logging in the app - INFO
	 * 
	 * @param String message
	 * @throws
	 */
	@Override
	public void doLog(String message) {
		logger.log(Level.INFO, message);
	}
	
	/**
	 * This method to do logging in the app 
	 * 
	 * @param Level level, String message
	 * @throws
	 */
	@Override
	public void doLog(Level level, String message) {
		logger.log(level, message);
	}
	
	/**
	 * This method to do logging in the app with expection
	 * 
	 * @param Level level, String message, Throwable throwable
	 * @throws
	 */
	@Override
	public void doLog(Level level, String message, Throwable throwable) {
		logger.log(level, message, throwable);
	}
	
}

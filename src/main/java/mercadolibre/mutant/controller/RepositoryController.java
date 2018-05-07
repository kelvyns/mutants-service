package mercadolibre.mutant.controller;

import java.util.List;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mercadolibre.mutant.entity.Person;
import mercadolibre.mutant.manager.LoggerManager;
import mercadolibre.mutant.repository.PersonRepository;


/**
 * @author kelvyns
 *
 *	This controller is only for test when the app go to production,
 *  this services should be deleted or blocked
 */
@RestController
public class RepositoryController {
	
	@Autowired
	private LoggerManager  loggerManager;
	
	@Autowired
	private PersonRepository personRepository;
	
	/**
	 *	This controller is only for test when the app go to production,
	 *  this services should be deleted
	 */
	@RequestMapping(value="/clearBD", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
	ResponseEntity<String> clear() {
		ResponseEntity<String> response = null;
		try {
			personRepository.deleteAll();
			response = new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			loggerManager.doLog(Level.SEVERE, e.getMessage());
			response = new ResponseEntity<>(HttpStatus.CONFLICT);
		}
        return response;
    }
	
	@RequestMapping(value="/list", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
	List<Person> list() {
		List<Person> list = null;
		try {
			list = personRepository.findAll();
		} catch (Exception e) {
			loggerManager.doLog(Level.SEVERE, e.getMessage());
		}
        return list;
    }

}

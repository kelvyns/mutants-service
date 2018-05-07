package mercadolibre.mutant.controller;

import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mercadolibre.mutant.entity.DnaContent;
import mercadolibre.mutant.entity.PersonStats;
import mercadolibre.mutant.exception.InvalidDnaExeption;
import mercadolibre.mutant.exception.ManagerExeption;
import mercadolibre.mutant.exception.RepositoryExeption;
import mercadolibre.mutant.exception.ServiceExeption;
import mercadolibre.mutant.manager.LoggerManager;
import mercadolibre.mutant.service.ApplicationService;

@RestController
public class ApplicationController {
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private LoggerManager  loggerManager;
	
	@RequestMapping(value="/mutant", method=RequestMethod.POST,produces="application/json; charset=UTF-8")
    public ResponseEntity<String> isMutant(@RequestBody DnaContent dna){
    	
    	ResponseEntity<String> response = null;
		try {
			loggerManager.doLog(Level.INFO, "run isMutant service");
			response = applicationService.isMutant(dna);
		} catch (ServiceExeption e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;
			loggerManager.doLog(Level.SEVERE, e.getMessage());
		} catch (InvalidDnaExeption e) {
			response = new ResponseEntity<>(HttpStatus.FORBIDDEN) ;
			loggerManager.doLog(Level.WARNING, e.getMessage());
		} catch (RepositoryExeption e) {
			response = new ResponseEntity<>(HttpStatus.CONFLICT) ;
			loggerManager.doLog(Level.SEVERE, e.getMessage());
		}
    	return response;
    	
    }
	
	@RequestMapping(value="/stats", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
    PersonStats stats() {
		//"{“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}"
		PersonStats personStats = null;
		try {
			loggerManager.doLog(Level.INFO, "run getStats service");
			personStats = applicationService.getStats();
		} catch (ServiceExeption e) {
			loggerManager.doLog(Level.SEVERE, e.getMessage());
		} catch (ManagerExeption e) {
			loggerManager.doLog(Level.SEVERE, e.getMessage());
		} catch (RepositoryExeption e) {
			loggerManager.doLog(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			loggerManager.doLog(Level.SEVERE, e.getMessage());
		}
        return personStats;
    }

}

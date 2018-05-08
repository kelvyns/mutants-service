package mercadolibre.mutant.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import mercadolibre.mutant.exception.RepositoryExeption;
import mercadolibre.mutant.exception.ServiceExeption;
import mercadolibre.mutant.service.ApplicationService;

@RestController
public class ApplicationController {
	
	@Autowired
	private ApplicationService applicationService;
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);
	
	@RequestMapping(value="/mutant", method=RequestMethod.POST,produces="application/json; charset=UTF-8")
    public ResponseEntity<String> isMutant(@RequestBody DnaContent dna){
    	
    	ResponseEntity<String> response = null;
		try {
			logger.info("run isMutant service");
			response = applicationService.isMutant(dna);
		} catch (ServiceExeption e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;
			logger.error(e.getMessage());
		} catch (InvalidDnaExeption e) {
			response = new ResponseEntity<>(HttpStatus.FORBIDDEN) ;
			logger.warn(e.getMessage());
		} catch (RepositoryExeption e) {
			response = new ResponseEntity<>(HttpStatus.CONFLICT) ;
			logger.error(e.getMessage());
		}
    	return response;
    	
    }
	
	@RequestMapping(value="/stats", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
    PersonStats stats() {
		//"{“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}"
		PersonStats personStats = null;
		try {
			logger.info("run getStats service");
			personStats = applicationService.getStats();
		} catch (ServiceExeption e) {
			logger.error(e.getMessage());
		} catch (RepositoryExeption e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
        return personStats;
    }

}

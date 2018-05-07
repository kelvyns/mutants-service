package mercadolibre.mutant.manager.impl;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mercadolibre.mutant.entity.Person;
import mercadolibre.mutant.exception.InvalidDnaExeption;
import mercadolibre.mutant.exception.RepositoryExeption;
import mercadolibre.mutant.manager.MutantsManager;
import mercadolibre.mutant.repository.PersonRepository;

/**
 * @author kelvyns
 *
 * This is the principal class with the principal logic for studying mutants 
 */
@Component
public class MutantsManagerImpl implements MutantsManager {
	
	@Autowired
	private PersonRepository personRepository;
	
	/**
	 * Minimal Matches for to be a mutant
	 */
	private static final int MATCHES = 2;
	/**
	 * Sequence minimal for dna
	 */
	private static final int MIN_VALUE_DNA = 4;
	/**
	 * Minimal Pattern for consider that a person is or not mutant
	 */
	private static final int PATTERN_MUTANT_DNA = 4;

	
	/**  
	 * Return true if person is mutant or false if is not mutant
	 * @param String[] dna
	 * @return boolean true or false
	 * @throws RepositoryExeption 
	 */
	@Override
	public boolean isMutant(String[] dna) throws InvalidDnaExeption, RepositoryExeption {
		int matches = 0;
		boolean isMutant = false;
		// validate dna
		validateDna(dna);
		
		// horizontal --\
		matches = horizontalAnalyze( matches, dna );
		if (matches < MATCHES) {
			// vertical |
			matches = verticalAnalyze( matches, dna );
			if ( matches < MATCHES ) {
				// oblique to left \
				matches = obliqueAnalyze((dna.length - MIN_VALUE_DNA), 0, +1, matches, dna) ;
				if (matches < MATCHES) {
					// oblique to right /
					matches = obliqueAnalyze((dna.length-1), (dna.length-1), -1, matches, dna);
				}
			}
		}
		isMutant = matches >= MATCHES;
		savePersonInBD(String.join("-", dna), isMutant);
		return isMutant;
	}
	
	private void savePersonInBD(String dnaString, boolean isMutant) throws RepositoryExeption {
		try {
			Person p = personRepository.findByDna(dnaString);
			if( p==null || !dnaString.equalsIgnoreCase(p.getDna()) ) {
				// If the person is not in databa, save person
				p = new Person(dnaString, isMutant);
				personRepository.save(p);
			}
		} catch (Exception e) {
			throw new RepositoryExeption("Fail in Database to save dna. "+ e.getMessage());
		}
	}


	/**  
	 * Transform to valid DNA matrix
	 * @param String[] dna
	 */
	private void validateDna(String[] dna) throws InvalidDnaExeption {
		int nRow = dna.length;
		// VALIDATE DNA
		if (nRow >= MIN_VALUE_DNA) {

			for (int i = 0; i < nRow; i++) {

				// This method validate characters
				validateCharacterDna(dna[i]);
				
				dna[i] = dna[i].toUpperCase();
				// This method validate structure
				validateStructureDna(dna[i], nRow);

			}
		} else {
			throw new InvalidDnaExeption("Ilegal Structure in DNA. N:"+nRow);
		}
	}
	
	/**  
	 * This method validate structure
	 * @param String dnaX, int l length in row
	 */
	private void validateStructureDna(String dnaX, int l) throws InvalidDnaExeption {
		if (dnaX.length() != l) {
			// ilegal Structure
			throw new InvalidDnaExeption("Ilegal Structure in DNA");
		}
	}

	/**  
	 * This method validate characters, The valid characters are A-T-G-C
	 * @param String dnaX
	 */
	private void validateCharacterDna(String dnaX) throws InvalidDnaExeption {
		// Character valid for dna
		Pattern p = Pattern.compile("[ATGC]+", Pattern.CASE_INSENSITIVE);
		// The valid characters are A-T-G-C
		if (!p.matcher(dnaX).matches()) {
			throw new InvalidDnaExeption("Invalid characters in DNA");
		}
	}

	/**  
	 * Search a vertical pattern in all matrix
	 * Example [ATTT,AGGG,ACCC,ATGC] has vertical pattern
	 * @param int matches, char[][] dna
	 * @return int
	 */
	private int verticalAnalyze(int matches, String[] dna) {
		for (int j = 0; j < dna.length; j++) {
			for (int i = 0; i <= (dna.length - MIN_VALUE_DNA); i++) {
				if (verticalPatternAnalyze(i, j, dna)) {
					matches++;
					if( matches >= MATCHES) {
						return matches;
					}
				}
			}
		}
		return matches;
	}

	/**  
	 * Study four element in vertical
	 * @param int i, int j, char[][] dna
	 * @return boolean
	 */
	private boolean verticalPatternAnalyze(int i, int j, String[] dna) {
		// Study four element in vertical
		for (int k = (i + (PATTERN_MUTANT_DNA - 1)); k >= i; k--) {
			if (dna[i].charAt(j) != dna[k].charAt(j)) {
				return false;
			}
		}
		return true;
	}

	/**  
	 * Search a horizontal pattern in all matrix
	 * Example [TTTT,AGGG,ACCC,ATGC] has horizontal pattern
	 * @ param int matches, char[][] dna
	 * @return int
	 */
	private int horizontalAnalyze(int matches, String[] dna) {
		for (int i = 0; i < dna.length; i++) {
			for (int j = 0; j <= (dna.length - MIN_VALUE_DNA); j++) {
				if (horizontalPatternAnalyze(i, j, dna)) {
					matches++;
					if( matches >= MATCHES) {
						return matches;
					}
					
				}
			}
		}
		return matches;
	}

	/**  
	 * Study four element in horizontal
	 * @param dna 
	 * @param int i, int j
	 * @return boolean
	 */
	private boolean horizontalPatternAnalyze(int i, int j, String[] dna) {
		for (int k = (j + PATTERN_MUTANT_DNA - 1); k >= j; k--) {
			if (dna[i].charAt(j) != dna[i].charAt(k)) {
				return false;
			}
		}
		return true;
	}

	/**  
	 * Search a Oblique pattern in all matrix
	 * Example [TAAA,CTCC,GGTG,AAAT] has Oblique pattern
	 * @param int iEnd for i, int jPosition, int direction,  int matches, char[][] dna
	 * @return int
	 */
	private int obliqueAnalyze(int iEnd, int jPosition, int direction, int matches, String[] dna) {
		int iterateIni = 0;
		for (int i = 0; i <= iEnd; i++) {
			if (i == 0) {
				if(direction == 1) {
					for (int j = (dna.length - MIN_VALUE_DNA); j >= 0; j--) {
						iterateIni++;
						if (patternObliqueAnalyze(i, j, iterateIni, direction, dna)) {
							matches++;
							if( matches >= MATCHES) {
								return matches;
							}
						}
					}
				}
				if(direction == -1) {
					for (int j = (MIN_VALUE_DNA - 1); j <= dna.length-1; j++) {
						iterateIni++;
						if (patternObliqueAnalyze(i, j, iterateIni, direction, dna)) {
							matches++;
							if( matches >= MATCHES) {
								return matches;
							}
						}
					}
				}
			} else {
				iterateIni--;
				if (iterateIni>0 && patternObliqueAnalyze(i, jPosition, iterateIni, direction, dna) ) {
					matches++;
					if( matches >= MATCHES) {
						return matches;
					}
				}
			}
		}
		return matches;
	}
	
	/**  
	 * Study four element in Oblique
	 * @param dna 
	 * @param int i, int j
	 * @return boolean
	 */
	private boolean patternObliqueAnalyze(int i, int j, int interate, int direction, String[] dna) 	{
		for (int q = 0; q < interate; q++) {
			for (int k = (MIN_VALUE_DNA-1); k > 0; k--) {
				if (dna[j + direction * q].charAt(i + q) != dna[j + direction * q + direction * k].charAt(i + q + k)) {
					break;
				}
				if(k==1) {
					return true;
				}
			}
		}
		return false;
	}
}
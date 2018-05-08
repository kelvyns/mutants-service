package mercadolibre.mutant.entity;


/**
 * @author kelvyns
 * This class represent the entity Stats for statistics about mutants
 *
 */
public class PersonStats {
	
	public double count_mutant_dna;
	public double count_human_dna;
	public double ratio;
	
	/**
	 * @param count_mutant_dna
	 * @param count_human_dna
	 */
	public PersonStats(double count_human_dna, double count_mutant_dna) {
		super();
		this.count_mutant_dna = count_mutant_dna;
		this.count_human_dna = count_human_dna;
		this.ratio = Math.round( ((count_human_dna != 0) ? count_mutant_dna / count_human_dna : 0) );
	}
	
	/**
	 * @return the count_mutant_dna
	 */
	public double getCount_mutant_dna() {
		return count_mutant_dna;
	}
	
	/**
	 * @param count_mutant_dna the count_mutant_dna to set
	 */
	public void setCount_mutant_dna(double count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}

	/**
	 * @return the count_human_dna
	 */
	public double getCount_human_dna() {
		return count_human_dna;
	}

	/**
	 * @param count_human_dna the count_human_dna to set
	 */
	public void setCount_human_dna(double count_human_dna) {
		this.count_human_dna = count_human_dna;
	}

	/**
	 * @return the ratio
	 */
	public double getRatio() {
		return ratio;
	}

	/**
	 * @param ratio the ratio to set
	 */
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	
}

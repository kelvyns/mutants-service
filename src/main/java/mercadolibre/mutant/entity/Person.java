package mercadolibre.mutant.entity;

import org.springframework.data.annotation.Id;

public class Person {
	
	@Id
	public String id;
	
	public String dna;
	public boolean isMuntat;
	
	/**
	 * @param dna
	 */
	public Person(String dna) {
		super();
		this.dna = dna;
		this.isMuntat = false;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the dna
	 */
	public String getDna() {
		return dna;
	}

	/**
	 * @param dna the dna to set
	 */
	public void setDna(String dna) {
		this.dna = dna;
	}
	
	/**
	 * @return the isMuntat
	 */
	public boolean isMuntat() {
		return isMuntat;
	}

	/**
	 * @param isMuntat the isMuntat to set
	 */
	public void setMuntat(boolean isMuntat) {
		this.isMuntat = isMuntat;
	}
	
	@Override
    public String toString() {
        return String.format(
                "Person[id=%s, dna='%s', isMuntat='%s']",
                id, dna, isMuntat);
    }

}

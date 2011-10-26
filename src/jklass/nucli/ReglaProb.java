package jklass.nucli;

/**
 * Aquesta clase representa una regla probabilitzada. Hereta de la classe <code>Regla</code>
 * @author Patrícia Garcia, Alfons Bosch
 *
 */
public class ReglaProb extends Regla {
	protected double prob;
	
	public ReglaProb(ExpressioBooleana eb, String c, int id, GestorMatriu g, String snom) {
		super(eb, c, id, g, snom);
	}
	
	public ReglaProb(int id){
		super(id);
	}

	public double getProb() {
		return prob;
	}

	public void setProb(double prob) {
		this.prob = prob;
	}

}

package jklass.nucli;

/**
 * Classe que representa els fitxers de regles d'extensió .reg
 * És un subtipus de Fitxer 
 *@author Laia Riera Guerra
 * @version v.4 05/05/2007
 */
public class FitxerReg extends Fitxer {	
	
	
	 /**
	   * Crea un FitxerReg de nom <code>nom</code>
	   * S'afegeix l'extensió .reg al nom del fitxer
	   * @param <code>nom</code>, nom del fitxer sense extensió
	   */
	  public FitxerReg(String nom) {//el public s'ha de canviar...
	    super(nom);
	    String snom=obtenirNom();
		 
		 int r = snom.lastIndexOf('.');
		 r = (r==-1)? snom.length(): r;
		 snom=snom.substring(0,r);
	    this.modificarNom(snom+".reg");	 
		 
	  }//endofmethod
	  
	  /**
	   * Escriu en una línia la regla representada per la Regla <code>reg</code>
	   * @param <code>reg</code>,regla que volem escriure al fitxer
	   */
	  public void escriureRegla(Regla reg)throws Exception{		  
		  String sAux=reg.toStringRegla();	  
		  this.escriureLin(sAux);
	  }//endofmethod	 
	  
	  
	
}

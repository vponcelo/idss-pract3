package jklass.nucli;

import java.util.ArrayList;

/**
 * Classe que representa a una expressió booleana no mínima, és a dir, que almenys conté un operador lògic
 * És un subtipus d'ExpressioBooleana
 * @author Laia Riera Guerra
 * @version v.4 05/05/2007
 */


abstract class EBLogica extends ExpressioBooleana{
	protected ArrayList alFills;//llistat d'operacions filles de l'operació que estem tractant. Pot ser buida
	/**
	 * Constructor per defecte
	 *
	 */ 
	public EBLogica(){
		super();
		alFills=new ArrayList();		
	}
	/**
	 * Constructor que associa la nova EBLogica al GestorMatriu <code>m</code>
	 * @param m, GestorMatriu
	 */
	public EBLogica(GestorMatriu m){
		super(m);
		alFills=new ArrayList();		
	}
		
		/**
		 * Afegeix una nova expressió booleana <code>op</code> al final de la llista de fills d'aquesta operació
		 * @param <code>op</code>,nova expressió booleana filla d'aquesta operació
		 * @return <code>true</code>, si s'ha pogut afegir correctament <code>op</code> a la llista de fills
		 * 			<code>false</code>, si <code>op</code> ja es trobava a la llista
		 */
	public boolean afegeixFill(ExpressioBooleana op){
		return alFills.add(op);
	}//endofmethod
	

	/**
	 * Elimina l'expressió booleana <code>op</code> de la llista de fills de l'operació que estem tractant
	 * @param <code>op</code>, expressió booleana a eliminar dels fills de l'operació que estem tractant
	 * @return <code>true</code>, si s'ha pogut eliminar <code>op</code> de la llista correctament
	 * 			<code>false</code>, si <code>op</code> no es trobava a la llista de fills
	 */
	public boolean eliminaFill(ExpressioBooleana op){
		return alFills.remove(op);
	}//endofmethod
	
	/**
	 * Obté l'ExpressioBooleana filla que es troba a la posició <code>i</code> del llistat dels fills de l'EBLogica actual
	 * @param i, posició del fill que volem obtenir dins del llistat de fills
	 * @return ExpressioBooleana filla que ocupa la posició <code>i</code>
	 */
	public ExpressioBooleana obtenirFill(int i){		
		return (ExpressioBooleana)alFills.get(i);
	}
	
	/**
	 * Obté l'ArrayList de les expressions booleanes filles de l'operació que estem tractant
	 * @return <code>ArrayList</code>, que conté les expressions booleanes filles de l'operació en curs
	 */
	public ArrayList getAlFills() {
		return alFills;
	}
	/**
	 * Posa com a nou ArrayList d'expressions booleanes filles al <code>alFills</code>
	 * @param <code>alFills</code>, nou array de fills de l'operació en curs
	 */
	public void setAlFills(ArrayList alFills) {
		this.alFills = alFills;
		for(int i=0;i<alFills.size();i++){
			ExpressioBooleana b=(ExpressioBooleana)alFills.get(i);
			b.setOpPare(this);
		}
	}
	
	/**
	 * Obté el nombre de fills de l'operador lògic
	 * @return nombre d'elements de l'arraylist alFills
	 */
	public int getNumFills(){
		return alFills.size();
	}
	
	 /**
	   * Escriu l'String equivalent als fills de l'operació <code>op</code> i el concatena amb l'string que ens passen com a paràmetre <code>s</code>
	   * @param <code>s</code>, String que ens passen com a paràmetre i que serà el resultat a retornar
	   * @param <code>op</code>, expressió booleana que es vol escriure
	   * @return String equivalent a l'operació <code>op</code>
	   * 
	   */
	  protected String escriureFills(String s, EBLogica op){
		  ArrayList alfills=op.getAlFills();			  
		  for(int i=0;i<alfills.size();i++){
			  s=s+"(";
			  ExpressioBooleana op2=(ExpressioBooleana)alfills.get(i);
			  String sAux=escriureOperacioRec(op2);
			  s=s.concat(sAux);
			  s=s+")";
		  }//endfor
		  return s;
	  }//endofmethod
	  
	  /**
	   * Comprova si les propietats que intervenen en l'expressió lògica pertanyen a la matriu actual
	   * @param propSelec, llistat de les propietats de la matriu actual
	   * @return true, si l'expressió lògica conté propietats que no existeixen a la matriu actual, false altrament
	   */
	  public boolean comprovarPropietats(String[] propSelec)throws Exception{
		  boolean resultat=false;
		  for(int i=0;i<alFills.size() && !resultat;i++){
			  ExpressioBooleana opFill=(ExpressioBooleana)alFills.get(i);
			  resultat=opFill.comprovarPropietats(propSelec);			  
		  }
			return resultat;
		}
}
package jklass.nucli;

import java.util.ArrayList;

/**
 * Classe que representa a una expressi� booleana no m�nima, �s a dir, que almenys cont� un operador l�gic
 * �s un subtipus d'ExpressioBooleana
 * @author Laia Riera Guerra
 * @version v.4 05/05/2007
 */


abstract class EBLogica extends ExpressioBooleana{
	protected ArrayList alFills;//llistat d'operacions filles de l'operaci� que estem tractant. Pot ser buida
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
		 * Afegeix una nova expressi� booleana <code>op</code> al final de la llista de fills d'aquesta operaci�
		 * @param <code>op</code>,nova expressi� booleana filla d'aquesta operaci�
		 * @return <code>true</code>, si s'ha pogut afegir correctament <code>op</code> a la llista de fills
		 * 			<code>false</code>, si <code>op</code> ja es trobava a la llista
		 */
	public boolean afegeixFill(ExpressioBooleana op){
		return alFills.add(op);
	}//endofmethod
	

	/**
	 * Elimina l'expressi� booleana <code>op</code> de la llista de fills de l'operaci� que estem tractant
	 * @param <code>op</code>, expressi� booleana a eliminar dels fills de l'operaci� que estem tractant
	 * @return <code>true</code>, si s'ha pogut eliminar <code>op</code> de la llista correctament
	 * 			<code>false</code>, si <code>op</code> no es trobava a la llista de fills
	 */
	public boolean eliminaFill(ExpressioBooleana op){
		return alFills.remove(op);
	}//endofmethod
	
	/**
	 * Obt� l'ExpressioBooleana filla que es troba a la posici� <code>i</code> del llistat dels fills de l'EBLogica actual
	 * @param i, posici� del fill que volem obtenir dins del llistat de fills
	 * @return ExpressioBooleana filla que ocupa la posici� <code>i</code>
	 */
	public ExpressioBooleana obtenirFill(int i){		
		return (ExpressioBooleana)alFills.get(i);
	}
	
	/**
	 * Obt� l'ArrayList de les expressions booleanes filles de l'operaci� que estem tractant
	 * @return <code>ArrayList</code>, que cont� les expressions booleanes filles de l'operaci� en curs
	 */
	public ArrayList getAlFills() {
		return alFills;
	}
	/**
	 * Posa com a nou ArrayList d'expressions booleanes filles al <code>alFills</code>
	 * @param <code>alFills</code>, nou array de fills de l'operaci� en curs
	 */
	public void setAlFills(ArrayList alFills) {
		this.alFills = alFills;
		for(int i=0;i<alFills.size();i++){
			ExpressioBooleana b=(ExpressioBooleana)alFills.get(i);
			b.setOpPare(this);
		}
	}
	
	/**
	 * Obt� el nombre de fills de l'operador l�gic
	 * @return nombre d'elements de l'arraylist alFills
	 */
	public int getNumFills(){
		return alFills.size();
	}
	
	 /**
	   * Escriu l'String equivalent als fills de l'operaci� <code>op</code> i el concatena amb l'string que ens passen com a par�metre <code>s</code>
	   * @param <code>s</code>, String que ens passen com a par�metre i que ser� el resultat a retornar
	   * @param <code>op</code>, expressi� booleana que es vol escriure
	   * @return String equivalent a l'operaci� <code>op</code>
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
	   * Comprova si les propietats que intervenen en l'expressi� l�gica pertanyen a la matriu actual
	   * @param propSelec, llistat de les propietats de la matriu actual
	   * @return true, si l'expressi� l�gica cont� propietats que no existeixen a la matriu actual, false altrament
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
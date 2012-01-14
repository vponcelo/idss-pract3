package jklass.nucli;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe que representa una regla lògica
 * @author Laia Riera Guerra
 * @version v.4 24/06/2007
 * 
 */
public class Regla {
	protected String nomRegla;
	protected int identificador;//identificador de la regla
	protected ExpressioBooleana expressio;//expressió booleana que representa la regla
	protected String conseguent; //consegüent de la expressió booleana
	protected GestorMatriu gestor; //GestorMatriu al que està associada la regla
	
	/**
	 * Crea una regla amb identificador = <code>id</code>, expressio = <code>eb</code>, gestor = <code>g</code>,
	 * i conseguent = <code>c</code>
	 * @param <code>eb</code>, ExpressioBooleana
	 * @param <code>c</code>, Consegüent de l'expressió booleana
	 * @param <code>id</code>, identificador de la regla
	 * @param <code>g</code>, GestorMatriu
	 */
	public Regla(ExpressioBooleana eb, String c,int id,GestorMatriu g,String snom){
		identificador=id;
		expressio=eb;
		expressio.setGestor(g);
		conseguent=c;
		gestor=g;
		if(snom==null)nomRegla="r"+id;
		else nomRegla=snom;
	}
	
	/**
	 * Crea una regla d'identificador = <code>id</code>
	 * @param <code>id</code>, enter identificador de la regla
	 */
	public Regla(int id){
		identificador=id;
		expressio=null;
		conseguent=null;
		nomRegla="r"+id;
	}
	/**
	 * Obté l'expressió booleana que representa la regla
	 * @return ExpressioBooleana
	 */
	public ExpressioBooleana getExpressio(){
		return expressio;
	}
	/**
	 * Obté el consegüent de la regla
	 * @return String, que representa el consegüent
	 */
	public String getConseguent(){
		return conseguent;
	}
	/**
	 * Obté l'identificador de la regla
	 * @return int, identificador de la regla
	 */
	public int getIdentificador(){
		return identificador;
	}
	/**
	 * Posa com a ExpressioBooleana de la regla al paràmetre que ens passen
	 * @param <code>eb</code>, nova ExpressioBooleana de la regla
	 */
	public void setExpressio(ExpressioBooleana eb){
		expressio=eb;
		expressio.setGestor(gestor);
	}
	/**
	 * Posa com a consegüent l'string que ens passen com a paràmetre
	 * @param <code>c</code>, nou consegüent de la regla
	 */
	public void setConseguent(String c){
		conseguent=c;
	}
	/**
	 * Converteix la regla a string
	 * @return String, que representa la regla
	 */
	public String toStringRegla(){
		String sExp=expressio.toStringExpressio();
		String s="->"+conseguent+")";
		sExp=sExp.concat(s);
		s="(";
		return s.concat(sExp);
	}
	/**
	 * Escriu l'ExpressioBooleana de la regla en format d'inordre i formatejada a latex  
	 * @return String que representa la l'ExpressioBooleana de la regla escrita en inordre i formatejada a latex 
	 * @throws Exception
	 */
	public String toStringReglaNormalLatex()throws Exception{		
		return expressio.toStringExpressioNormalLatex();
	}
	/**
	 * Escriu l'ExpressioBooleana de la regla en format d'inordre  
	 * @return String que representa la l'ExpressioBooleana de la regla escrita en inordre 
	 * @throws Exception
	 */
	public String toStringReglaNormal()throws Exception{		
		return expressio.toStringExpressioNormal();
	}
	/**
	 * Avalua l'expressió booleana de la regla
	 * <p>
	 * @return boolean[], un vector de booleans que representa el resultat que s'ha obtingut d'avaluar cada una de les files 
	 * de la matriu en actiu segons l'expressió booleana
	 */
	public ArrayList avalua()throws Exception{
		return expressio.avalua();
	}
	/**
	 * Obté el nom de la regla
	 * @return <code>nomRegla</code>
	 */
	public String getNomRegla() {
		return nomRegla;
	}
	/**
	 * Posa el nou nom de la regla com a <code>nomRegla</code>
	 * @param nomRegla, nou nom de la regla
	 */
	public void setNomRegla(String nomRegla) {
		this.nomRegla = nomRegla;
	}
	/**
	 * Obté el GestorMatriu al que està associat al regla
	 * @return <code>gestor</code>
	 */
	public GestorMatriu getGestor() {
		return gestor;
	}
	/**
	 * Associa la regla al nou GestorMatriu <code>gestor</code>
	 * @param gestor, nou GestorMatriu
	 */
	public void setGestor(GestorMatriu gestor) {
		this.gestor = gestor;
		expressio.setGestor(gestor);
	}
	 /**
	   * Comprova si les propietats que intervenen en l'expressió lògica de la regla pertanyen a la matriu actual
	   * @param propSelec, llistat de les propietats de la matriu actual
	   * @return true, si l'expressió lògica de la regla conté propietats que no existeixen a la matriu actual, false altrament
	   */
	public boolean comprovarPropietats(String[] propSelec)throws Exception{
		return expressio.comprovarPropietats(propSelec);
	}

	/**
	 * Support function calculates the number of objects stisfiying the antecedent of the  rule
	 * from the whole data set I divided by number of objects of the whole I
	 *
	 *@return float for the support value
	 *
	 *@author Esther Lozano
	 */
	public float support() throws Exception
	{
		ArrayList c = expressio.avalua();
		
		//n is the number of objects of the whole data set
		float card =0; 
		float res = -1;
		
		LlistaObjectes obj = this.gestor.obtenirLlistaObjs();
		float n = obj.obtenirLong();
		
		//here we count the number of objects satisfying the antecedent of the rule, that's
		//the number of "1" values returned by avalua function
		for(int i = 0; i < c.size(); i++)
		{
			if(((String)c.get(i)).compareTo("1") == 0)
			{
				card++;
			}
		}
		
		//we have to avoid division by 0
		if(n != 0)
		{
			res = card/n;
		}
		
		return res;
	}
	
	
	/**
	 * Relative Covering is the proportion of class C (the consequent) that satisfy the rule
	 * This is the number of objects  satisfying the  antecedent and the 
	 * consequent of the rule from the whole data set divided by the number of objects satifying
	 * the consequent from the whole data set
	 * 
	 *@return float for the relative covering value
	 *
	 *@author Esther Lozano
	 */
	public float coveringR() throws Exception
	{	
		ArrayList av = expressio.avalua();
		int card = 0;		//number of objects satisfying the antecedent and the consequent
		int n = 0;			//number of objects satisfying the antecedent
		float res = -1;
		
		//we cut the name of the class variable from the consequent (we need the value of the class)
		int pos = conseguent.indexOf(")");
		String nomClass = conseguent.substring(1, pos);
		String subCons = conseguent.substring(pos+1);
		
		LlistaObjectes obj = this.gestor.obtenirLlistaObjs();
		int prop = this.gestor.obtenirLlistaProps().obtenirIndex(nomClass);
		
		for(int i = 0; i < obj.obtenirLong(); i++)
		{			
			Dada dada = this.gestor.obtenirDades().obtenirDada(i, prop);
			
			//we check if the object satisfy the consequent of te rule
			if(subCons.compareTo((String)dada.obtenirValor()) == 0 )			
			{
				n++;
				
				//we check if the object satisfy the antecedent of te rule
				if(((String)av.get(i)).compareTo("1") == 0)	
				{
					card++;
				}
			}
		}
		
		//we have to avoid division by 0
		if(n != 0)
		{
			res = (float)card/n;
		}
		
		return res;
	}
	
	
	
	/**
	 * Confidence is the proportion of objects that satisfy the antecedent of the rule and belong
	 * to C from the set of objects that satisfy the antecedent
	 * Thi is the number of objects  satisfying the  antecedent and the 
	 * consequent of the rule from the whole data set divided by the number of objects satifying
	 * the antecedent from whole data set
	 *
	 *@return float for the confidence value
	 *
	 *@author Esther Lozano
	 */
	public float confidence() throws Exception
	{	
		ArrayList av = expressio.avalua();
		int card = 0;		//number of objects satisfying the antecedent and the consequent
		int n = 0;			//number of objects satisfying the consequent
		float res = -1;
			
		int pos = conseguent.indexOf(")");
		String nomClass = conseguent.substring(1, pos);
		String subCons = conseguent.substring(pos+1);
		
		LlistaObjectes obj = this.gestor.obtenirLlistaObjs();
		int prop = this.gestor.obtenirLlistaProps().obtenirIndex(nomClass);
		
		//we cut the name of the class variable from the consequent (we need the value of the class)
		
		for(int i = 0; i<obj.obtenirLong();i++)
		{			
			Dada dada = this.gestor.obtenirDades().obtenirDada(i, prop);
			
			//we check if the object satisfy the antecedent of te rule
			if(((String)av.get(i)).compareTo("1") == 0)		
			{
				n++;
				
				//we check if the object satisfy the consequent of te rule
				if(subCons.compareTo((String)dada.obtenirValor()) == 0 )	
				{
					card++;
				}
			}
		}
		
		//we have to avoid division by 0
		if(n != 0)
		{
			res = (float)card/n;
		}
		
		return res;
	}
	


	//NUEVO ALBERTO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public boolean equalAntecedents(Regla r){
		return explore(this.expressio, r.getExpressio());
	}
	//First we explore all the elements in the expression of this instance
	private boolean explore(ExpressioBooleana mine, ExpressioBooleana other){
		boolean result = false;
		if(mine instanceof EBLogica){
			ArrayList<ExpressioBooleana> aux = ((EBLogica) mine).getAlFills();
			Iterator<ExpressioBooleana> it = aux.iterator();
			while(it.hasNext() && result == false){
				result = explore(it.next(), other);
			}
		}else if(mine instanceof EBRelacional){
			result = containsSome((EBRelacional)mine, other);//And when we find some relational one we look if it is contained in any of the other's variables.
			//if(result == false && mine.getOpPare() != null){
			//	result = explore(mine.getOpPare(),other);//And if we were not lucky, we keep on looking for a match.
			//}
		}
		return result;
	}
	
	//It explores in toCompare if the variable asociated to original is present. Original should remain fixed, while we explore in toCompare
	private boolean containsSome(EBRelacional original, ExpressioBooleana toCompare){
		boolean result = false;
		if(toCompare instanceof EBLogica){
			ArrayList<ExpressioBooleana> aux = ((EBLogica) toCompare).getAlFills();
			Iterator<ExpressioBooleana> it = aux.iterator();
			while(it.hasNext() && result == false){
				result = containsSome(original,it.next());
			}
		} else if(toCompare instanceof EBRelacional){
			result = isSameVariable(original, (EBRelacional)toCompare);
			//result = exploreRelational(original, (EBRelacional)toCompare);
		}
			return result;
	}
	
	//Once we have found a relational element toCompare, we compare it with our original. 
	private boolean exploreRelational(EBRelacional original, EBRelacional toCompare){
		boolean result = false;
		if(isSameVariable(original,toCompare)){
			result = true;
		} //else if(toCompare.getPare() != null) {
		//	result = containsSome(original, toCompare.getPare()); //In case we are not lucky, we keep on looking for a match.
		//}
		return result;
	}
	
	//This tells you if the variables are the same in both boolean Expressions.
	private boolean isSameVariable(EBRelacional original, EBRelacional e2){
		String var1 = original.getVar().obtenirId();
		String var2 = e2.getVar().obtenirId();
		boolean result = false;
		if(var1.compareTo(var2) == 0){
			result = true;
		}
		return result;
	}
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

}


package jklass.nucli;

import java.util.ArrayList;

/**
* Classe que representa l'operand lògic NOT. 
* És una tipologia de la classe EBLogica
* @author Laia Riera
* @version v.4 05/05/2007
* */


public class NOT extends EBLogica{	
	/**
	 * Constructor per defecte
	 *
	 */
	public NOT(){
		super();
	}
	/**
	 * Construeix un nou objecte NOT i l'associa al GestorMatriu <code>m</code>
	 * @param m
	 */
	public NOT(GestorMatriu m){
		super(m);		
	}
	/**
	 * Avalua una expressió booleana, el nucli de la qual és l'operador not
	 * <p>
	 * @return boolean[], un vector de booleans que representa el resultat que s'ha obtingut d'avaluar cada una de les files 
	 * de la matriu en actiu segons l'expressió booleana
	 */
	public ArrayList avaluaFulla() throws Exception {		
		ArrayList resultat=new ArrayList();
		if(alFills.size()==0)throw new Exception("L'operador lògic AND ha de tenir 2 fills");
			else{
			ExpressioBooleana fill=(ExpressioBooleana)alFills.get(0);
			ArrayList b=fill.avalua();
			for(int i=0;i<b.size();i++){
				String cFill=(String)b.get(i);
				if(cFill.compareTo("?")==0)resultat.add(i,"?");		
				else if(cFill.compareTo("1")==0)resultat.add(i,"0");
				else resultat.add(i,"1");					
				
			}
			return resultat;			
		}		
	}//endofmethod
	 /**
	  *  Construeix l'ExpressioBooleana que té <code>NOT</code> com a Operand i <code>sfillE,sfillD</code> com a fill Esquerra i null respectivament
	 * Si <code>sOperand</code> no és NOT retorna null
	 * @param sOperand, string que reprenta l'operand de l'expressió booleana
	 * @param sfillE, fill (expressió booleana de l'esquerra) de l'NOT
	 * @param sfillD, null
	 * @return ExpressioBooleana resultant
	 */
	public ExpressioBooleana llegirBCNormalLogica(String sOperand,String sfillE,String sfillD)throws Exception{
		if (sOperand.compareTo(ExpressioBooleana.NOT) == 0){
			EBLogica opL=new NOT();			
			ExpressioBooleana opFill=llegirBCNormal(sfillE);
			opL.afegeixFill(opFill);
			opFill.setOpPare(opL);
			return opL;
		}else return null;
	}
	/**
	 * Obté l'string que representa l'expressió booleana d'operand NOT en format d'inordre i 
	 * formatejat per escriure a Latex
	 * @return string que representa l'expressió booleana escrita en inordre i formatejat a Latex
	 */
	public String escriureNormalLatex()throws Exception{
		EBLogica aux=this;
		 ExpressioBooleana fill=aux.obtenirFill(0);				
		  String sfill=fill.toStringExpressioNormalLatex();				  
		  String saux="\\neg ("+sfill+")";		 
		  return saux;
	}
	/**
	 * Obté l'string que representa l'expressió booleana d'operand NOT en format d'inordre
	 * @return string que representa l'expressió booleana escrita en inordre
	 */
	public String escriureNormal()throws Exception{
		EBLogica aux=this;
		 ExpressioBooleana fill=aux.obtenirFill(0);				
		  String sfill=fill.toStringExpressioNormal();				  
		  String saux=this.NOT+"("+sfill+")";		  
		  return saux;
	}
	 

}

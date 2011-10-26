package jklass.nucli;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe que representa l'operand lògic AND. 
 * És una tipologia de la classe EBLogica
 * @author Laia Riera
 * @version v.4 05/05/2007
 * */

public class AND extends EBLogica{
	
	/**
	 * Constructor per defecte
	 *
	 */
	public AND(){
		super();
	}
	/**
	 * Construeix un nou objecte AND i l'associa al GestorMatriu <code>m</code>
	 * @param m
	 */
	public AND(GestorMatriu m){
		super(m);		
	}
	
	/**
	 * Avalua una expressió booleana, el nucli de la qual és l'operador and
	 * <p>
	 * @return ArrayList de Boolean, un vector de booleans que representa el resultat que s'ha obtingut d'avaluar cada una de les files 
	 * de la matriu en actiu segons l'expressió booleana
	 */
	public ArrayList avaluaFulla() throws Exception {		
		ArrayList resultat=new ArrayList();
		if(alFills.size()==0)throw new Exception("L'operador lògic AND ha de tenir 2 fills");
			else{
			ExpressioBooleana fillE=(ExpressioBooleana)alFills.get(0);
			ArrayList bE=fillE.avalua();
			ExpressioBooleana fillD=(ExpressioBooleana)alFills.get(1);
			ArrayList bD=fillD.avalua();
			for(int i=0;i<bE.size();i++){
				String cEsquerra=(String)bE.get(i);
				String cDreta=(String)bD.get(i);
				if(cEsquerra.compareTo("?")==0||cDreta.compareTo("?")==0)resultat.add(i,"?");
				else if(cEsquerra.compareTo("1")==0 && cDreta.compareTo("1")==0)resultat.add(i,"1");
				else resultat.add(i,"0");				
			}
			return resultat;			
		}		
	}//endofmethod
	/**
	 * Construeix l'ExpressioBooleana que té <code>AND</code> com a Operand i <code>sfillE,sfillD</code> com a fill Esquerra i Dret respectivament
	 * Si <code>sOperand</code> no és AND retorna null
	 * @param sOperand, string que reprenta l'operand de l'expressió booleana
	 * @param sfillE, fill esquerra(expressió booleana de l'esquerra) de l'AND
	 * @param sfillD, fill dret(expressió booleana de la dreta) de l'AND
	 * @return ExpressioBooleana resultant
	 */
	public ExpressioBooleana llegirBCNormalLogica(String sOperand,String sfillE,String sfillD)throws Exception{
		if (sOperand.compareTo(ExpressioBooleana.AND) == 0){
			EBLogica opL = new AND();						
			ExpressioBooleana opFillE = llegirBCNormal(sfillE);// construim l'operació del substring i l'afegim
			// com a fill de l'operació lògica que estem tractant
			opL.afegeixFill(opFillE);					
			opFillE.setOpPare(opL);
			ExpressioBooleana opFillD = llegirBCNormal(sfillD);// construim l'operació del substring i l'afegim
			// com a fill de l'operació lògica que estem tractant
			opL.afegeixFill(opFillD);
			opFillD.setOpPare(opL);
			return opL;
		}else return null;
	}
	/**
	 * Obté l'string que representa l'expressió booleana d'operand AND en format d'inordre
	 * @return string que representa l'expressió booleana escrita en inordre
	 */
	public String escriureNormal()throws Exception{		
		EBLogica aux=this;
		ExpressioBooleana fillE=aux.obtenirFill(0);		//Fill Esquerra
		  ExpressioBooleana fillD=aux.obtenirFill(1);	//Fill Dret
		  String sfillE=fillE.toStringExpressioNormal();
		  String sfillD=fillD.toStringExpressioNormal();
		  String saux="("+sfillE+")"+this.AND+"("+sfillD+")";
		  return saux;
	}
	
	/**
	 * Obté l'string que representa l'expressió booleana d'operand AND en format d'inordre
	 * @return string que representa l'expressió booleana escrita en inordre
	 * @author Grup SISPD QT 2009-2010
	 */
	public String escriureNormalNaria()throws Exception
	{		
		boolean tmpIsFirst = true;
		Iterator tmpChildrenIterator = this.getAlFills().iterator();
		ExpressioBooleana tmpCurrentChild;
		String tmpResult = "";
		
		while(tmpChildrenIterator.hasNext())
		{
			tmpCurrentChild = (ExpressioBooleana)tmpChildrenIterator.next();
			if(tmpIsFirst)
			{
				tmpResult = "(" + tmpCurrentChild.toStringExpressioNormal() +")";
				tmpIsFirst = false;
			}
			else
			{
				tmpResult += this.AND+ "(" + tmpCurrentChild.toStringExpressioNormal()+ ")";
			}
		}
		return tmpResult;
	}
	
	/**
	 * Obté l'string que representa l'expressió booleana d'operand AND en format d'inordre i 
	 * formatejat per escriure a Latex
	 * @return string que representa l'expressió booleana escrita en inordre i formatejat a Latex
	 * 
	 * Modified by
	 * @author Grup SISPD QT 2009-2010
	 */
	public String escriureNormalLatex()throws Exception{		
		EBLogica aux=this;
		ExpressioBooleana fillE=aux.obtenirFill(0);		
		  ExpressioBooleana fillD=aux.obtenirFill(1);
		  String sfillE=fillE.toStringExpressioNormalLatex();		  
		  String sfillD=fillD.toStringExpressioNormalLatex();		  
		  String saux="("+sfillE+") \\wedge ("+sfillD+")";		 
		  return saux;
	}
	
}

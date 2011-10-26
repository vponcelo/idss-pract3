package jklass.nucli;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe que representa l'operand l�gic OR. 
 * �s una tipologia de la classe EBLogica
 * @author Laia Riera
 * @version v.4 05/05/2007
 * */

public class OR extends EBLogica{
	/**
	 * Constructor per defecte
	 *
	 */
	public OR(){
		super();
	}
	/**
	 * Construeix un nou objecte OR i l'associa al GestorMatriu <code>m</code>
	 * @param m
	 */
	public OR(GestorMatriu m){
		super(m);		
	}
	/**
	 * Avalua una expressi� booleana, el nucli de la qual �s l'operador or
	 * <p>
	 * @return ArrayList de Booleans, un vector de booleans que representa el resultat que s'ha obtingut d'avaluar cada una de les files 
	 * de la matriu en actiu segons l'expressi� booleana
	 */
	public ArrayList avaluaFulla() throws Exception {		
		ArrayList resultat=new ArrayList();
		if(alFills.size()==0)throw new Exception("L'operador l�gic AND ha de tenir 2 fills");
			else{
			ExpressioBooleana fillE=(ExpressioBooleana)alFills.get(0);
			ArrayList bE=fillE.avalua();
			ExpressioBooleana fillD=(ExpressioBooleana)alFills.get(1);
			ArrayList bD=fillD.avalua();
			for(int i=0;i<bE.size();i++){
				String cEsquerra=(String)bE.get(i);
				String cDreta=(String)bD.get(i);
				if(cEsquerra.compareTo("?")==0 && cDreta.compareTo("?")==0)resultat.add(i,"?");
				else{
					if(cEsquerra.compareTo("?")==0){					
						if(cDreta.compareTo("1")==0)resultat.add(i,"1");
						else resultat.set(i,"0");
					}
					else if(cDreta.compareTo("?")==0){
						if(cEsquerra.compareTo("0")==0)resultat.add(i,"0");
						else resultat.set(i,"1");
					}
					else if(cEsquerra.compareTo(cDreta)!=0)resultat.add(i,"1");
					else if(cEsquerra.compareTo("0")==0)resultat.add(i,"0");
					else resultat.add(i,"1");
				}				
			}
			return resultat;			
		}		
	}//endofmethod
	/**
	 * Construeix l'ExpressioBooleana que t� <code>OR</code> com a Operand i <code>sfillE,sfillD</code> com a fill Esquerra i Dret respectivament
	 * Si <code>sOperand</code> no �s OR retorna null
	 * @param sOperand, string que reprenta l'operand de l'expressi� booleana
	 * @param sfillE, fill esquerra(expressi� booleana de l'esquerra) de l'OR
	 * @param sfillD, fill dret(expressi� booleana de la dreta) de l'OR
	 * @return ExpressioBooleana resultant
	 */
	public ExpressioBooleana llegirBCNormalLogica(String sOperand,String sfillE,String sfillD)throws Exception{
		if (sOperand.compareTo(ExpressioBooleana.OR) == 0){
			EBLogica opL = new OR();
			ExpressioBooleana opFillE = llegirBCNormal(sfillE);// construim l'operaci� del substring i l'afegim
			// com a fill de l'operaci� l�gica que estem tractant
			opL.afegeixFill(opFillE);					
			opFillE.setOpPare(opL);
			ExpressioBooleana opFillD = llegirBCNormal(sfillD);// construim l'operaci� del substring i l'afegim
			// com a fill de l'operaci� l�gica que estem tractant
			opL.afegeixFill(opFillD);
			opFillD.setOpPare(opL);
			return opL;
		}else return null;				
	}
	
	/**
	 * Obt� l'string que representa l'expressi� booleana d'operand OR en format d'inordre
	 * @return string que representa l'expressi� booleana escrita en inordre
	 */
	public String escriureNormal()throws Exception{
		EBLogica aux=this;
		ExpressioBooleana fillE=aux.obtenirFill(0);
		  ExpressioBooleana fillD=aux.obtenirFill(1);
		  String sfillE=fillE.toStringExpressioNormal();
		  String sfillD=fillD.toStringExpressioNormal();
		  String saux="("+sfillE+")"+this.OR+"("+sfillD+")";		
		  return saux;
	}
	
	/*	*//**
	 * Obt� l'string que representa l'expressi� booleana d'operand AND en format d'inordre
	 * @return string que representa l'expressi� booleana escrita en inordre
	 * @author Grup SISPD QT 2009-2010
	 *//*
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
				tmpResult += this.OR+ "(" + tmpCurrentChild.toStringExpressioNormal()+ ")";
			}
		}
		return tmpResult;
	}*/
	
	/**
	 * Obt� l'string que representa l'expressi� booleana d'operand OR en format d'inordre i 
	 * formatejat per escriure a Latex
	 * @return string que representa l'expressi� booleana escrita en inordre i formatejat a Latex
	 */
	public String escriureNormalLatex()throws Exception{
		EBLogica aux=this;
		ExpressioBooleana fillE=aux.obtenirFill(0);
		  ExpressioBooleana fillD=aux.obtenirFill(1);
		  String sfillE=fillE.toStringExpressioNormalLatex();
		  String sfillD=fillD.toStringExpressioNormalLatex();
		  String saux="("+sfillE+") \\vee ("+sfillD+")";		
		  return saux;
	}
	
}
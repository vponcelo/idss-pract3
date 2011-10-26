package jklass.nucli;

import java.util.ArrayList;

/**
 * Classe que representa l'operador arrel quadrada sobre la matriu de dades.
 * És una extensió de la classe OperacioUnaria
 * @author Laia Riera Guerra
 * @version v.4 05/05/2007
 *
 */
public class ARREL extends OperacioUnaria{
	/**
	 * Construiex un objecte ARREL que conté com a fill l'objecte <code>fills</code> i l'associa al GestorMatriu <code>m</code>
	 * @param m, GestorMatriu al qual estarà associat l'Objecte ARREL
	 * @param fills, fill de l'operació
	 * @param f, enter que indica si <code>fills</code> és una operació(1),si és una propietat (2) o si és un valor numèric(3)
	 */
	public ARREL(GestorMatriu m,Object fills,int f){
		super(m,fills,f);		
	}
	/**
	 * Avalua l'operació ARREL sobre la llista de valors <code>alF</code>, que és un columna de la matriu de dades
	 * @param alF, llista de valors, que es correspon amb els valors d'una columna de la matriu de dades
	 * @return ArrayList que conté els valors resultant d'avaluar cada un dels valors de <code>alF</code> per l'operand arrel quadrada
	 * @throws Exception
	 */
	public ArrayList calculaUnari(ArrayList alF)throws Exception{
		ArrayList al=new ArrayList();		
		for(int j=0;j<alF.size();j++){		
				String s=alF.get(j).toString();				
				if(!this.esNumeric(s)){
					if(s.compareTo("?")==0)al.add(j,"?");
					else throw new Exception("Valors incorrectes");
				}else{		
					Float f=new Float(s);								
					al.add(j,new Float(Math.sqrt(f.doubleValue())));
			}			
		}			
		return al;	
	}
	
}

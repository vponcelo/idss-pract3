package jklass.nucli;

import java.util.ArrayList;

/**
 * Classe que representa l'operador arrel quadrada sobre la matriu de dades.
 * �s una extensi� de la classe OperacioUnaria
 * @author Laia Riera Guerra
 * @version v.4 05/05/2007
 *
 */
public class ARREL extends OperacioUnaria{
	/**
	 * Construiex un objecte ARREL que cont� com a fill l'objecte <code>fills</code> i l'associa al GestorMatriu <code>m</code>
	 * @param m, GestorMatriu al qual estar� associat l'Objecte ARREL
	 * @param fills, fill de l'operaci�
	 * @param f, enter que indica si <code>fills</code> �s una operaci�(1),si �s una propietat (2) o si �s un valor num�ric(3)
	 */
	public ARREL(GestorMatriu m,Object fills,int f){
		super(m,fills,f);		
	}
	/**
	 * Avalua l'operaci� ARREL sobre la llista de valors <code>alF</code>, que �s un columna de la matriu de dades
	 * @param alF, llista de valors, que es correspon amb els valors d'una columna de la matriu de dades
	 * @return ArrayList que cont� els valors resultant d'avaluar cada un dels valors de <code>alF</code> per l'operand arrel quadrada
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

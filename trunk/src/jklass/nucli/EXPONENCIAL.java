package jklass.nucli;

import java.util.ArrayList;
/**
* Classe que representa una l'operació aritmètica exponencial
* És una subclasse de la classe OperacioUnaria
* @author Laia Riera Guerra
* @version v.4 05/05/2007
*/
public class EXPONENCIAL extends OperacioUnaria{
	/**
	 * Constructora que associa la nova operació al GestorMatriu <code>m</code> i li posa com a fill l'objecte <code>fills</code>
	 * @param m,GestorMatriu 
	 * @param fills, fill de l'operació 
	 * @param f, indica si el fill és una propietat ( <code>f</code>==1 ) o una operació ( <code>f</code>==2 )
	 */
	public EXPONENCIAL(GestorMatriu m,Object fills,int f){
		super(m,fills,f);		
	}
	/**
	 * Obté el càlcul de l'exponencial sobre la columna de dades de la matriu <code>alF</code>
	 * @param alF, columna de dades de la matriu corresponent al fill de l'operació
	 * @return llistat de valors corresponents a la columna resultant d'efectuar el càlcul exponencial.
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
					al.add(j,new Float(Math.exp(f.doubleValue())));
			}			
		}			
		return al;	
	}
	
}

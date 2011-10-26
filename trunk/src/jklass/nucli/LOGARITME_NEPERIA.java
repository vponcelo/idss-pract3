package jklass.nucli;

import java.util.ArrayList;
/**
* Classe que representa una l'operació aritmètica logaritme neperià
* És una subclasse de la classe OperacioUnaria
* @author Laia Riera Guerra
* @version v.4 05/05/2007
*/
public class LOGARITME_NEPERIA extends OperacioUnaria{
	/**
	 * Constructora que associa la nova operació al GestorMatriu <code>m</code> i li posa com a fill l'objecte <code>fills</code>
	 * @param m,GestorMatriu 
	 * @param fills, fill de l'operació 
	 * @param f, indica si el fill és una propietat ( <code>f</code>==1 ) o una operació ( <code>f</code>==2 )
	 */
	public LOGARITME_NEPERIA(GestorMatriu m,Object fills,int f){
		super(m,fills,f);		
	}
	/**
	 * Obté el càlcul del logaritme neperià sobre la columna de dades de la matriu <code>alF</code>
	 * @param alF, columna de dades de la matriu corresponent al fill de l'operació
	 * @return llistat de valors corresponents a la columna resultant d'efectuar el càlcul logaritme neperià.
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
					al.add(j,new Float(Math.log(f.doubleValue())));
			}			
		}			
		return al;	
	}	
	
}

package jklass.nucli;

import java.util.ArrayList;
/**
 * Classe que representa l'operaci�  de pot�ncia
 * �s una subclasse de la classe OperacioBinaria
 * @author Laia Riera Guerra
 * @version v.4 05/05/2007
 */
public class POTENCIA extends OperacioBinaria {
	/**
	 * Constructor
	 * @param m, GestorMatriu on queda associada l'operaci�
	 * @param fillD, fill dret de l'operaci�
	 * @param fillE, fill esquerra de l'operaci�
	 * @param D, indica el tipus d'objecte que �s el fill dret
	 * @param E, indica el tipus d'objecte que �s el fill esquerra segons la seg�ent codificaci�:
	 * E=1, operaci�
	 * E=2, propietat o variable 
	 * E=3, n�mero
	 */
	public POTENCIA(GestorMatriu m,Object fillD,Object fillE,int D,int E){
		super(m,fillD,fillE,D,E);
	}
	/**
	 * Efectua el c�lcul de elevar el fill dret al fill esquerra
	 * @param alD, cont� els valors de la columna de la matriu de dades del fill dret
	 * @param alE, cont� els valors de la columna de la matriu de dades del fill esquerra
	 * @return un llistat amb els valors de la columna de calcular alD^alE.
	 * @throws Exception
	 */
	public ArrayList calculaBinari(ArrayList alD,ArrayList alE)throws Exception{
		ArrayList al=new ArrayList();		
		for(int j=0;j<alD.size();j++){			
				String sD=alD.get(j).toString();
				String sE=alE.get(j).toString();
				if(!this.esNumeric(sD)||!this.esNumeric(sE)){
					if(sD.compareTo("?")==0||sE.compareTo("?")==0)al.add(j,"?");
					else throw new Exception("Valors incorrectes");
				}else{		
					Float fD=new Float(sD);
					Float fE=new Float(sE);			
					al.add(j,new Float(Math.pow(fE.floatValue(),fD.floatValue())));
				}
			}	
		return al;	
	}
	
}

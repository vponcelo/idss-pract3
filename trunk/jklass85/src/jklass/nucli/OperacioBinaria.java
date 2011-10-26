package jklass.nucli;

import java.util.ArrayList;
/**
 * Classe que representa l'operaci� bin�ria aritm�tica general
 * 
 * @author Laia Riera Guerra
 * @version v.4 05/05/2007
 */
public class OperacioBinaria extends Operacio{
	protected Object fillDret;
	protected Object fillEsquerra;
	int iDret;//t� 3 possibilitats: 1=Operacio,2=Propietat,3=N�mero. sobre el fill Dret
	int iEsquerra;//t� 3 possibilitats: 1=Operacio,2=Propietat,3=N�mero. sobre el fill Esquerra
	
	/**
	 * Constructora per defecte
	 *
	 */
	public OperacioBinaria(){
		super();
		fillDret=null;
		fillEsquerra=null;
		
	}
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
	public OperacioBinaria(GestorMatriu m,Object fillD,Object fillE, int iD,int iE){
		super(m);
		fillDret=fillD;
		fillEsquerra=fillE;
		iDret=iD;
		iEsquerra=iE;
	}
	/**
	 * Obt� el fill dret de l'operaci�
	 * @return Object, fill dret
	 */
	public Object getFillDret() {
		return fillDret;
	}
	/**
	 * Posa el fill dret de l'operaci� com a <code>fillDret</code>
	 * @param fillDret, nou fill dret de l'operaci�
	 */
	public void setFillDret(Object fillDret) {
		this.fillDret = fillDret;
	}
	/**
	 * Obt� el fill esquerra de l'operaci�
	 * @return Object, fill esquerra
	 */
	public Object getFillEsquerra() {
		return fillEsquerra;
	}
	/**
	 * Posa el fill esquerra de l'operaci� com a <code>fillEsquerra</code>
	 * @param fillEsquerra, nou fill esquerra de l'operaci�
	 */
	public void setFillEsquerra(Object fillEsquerra) {
		this.fillEsquerra = fillEsquerra;
	}
	/**
	 * Calula l'operaci� sobre la matriu de dades
	 * @return llistat de valors que representa la columna de la matriu resultat de fer el c�lcul
	 */
	public ArrayList calcula()throws Exception{	
		ArrayList al=new ArrayList();
		ArrayList alDret=new ArrayList();
		ArrayList alEsquerra=new ArrayList();
		//agafem files fill dret
		if(this.iDret==1){//operaci�
			Operacio f=(Operacio)fillDret;
			alDret=f.calcula();
		}else if(iDret==2){//propietat
			LlistaPropietats llprop=gestor.obtenirLlistaProps();
			Propietat pD=(Propietat)fillDret;
			Dada[] d = gestor.obtenirColumna(pD.obtenirId());//obtenim la columna de la propietat
			
			for(int i=0;i<d.length;i++){
				alDret.add(i,d[i].obtenirValor());
			}
		}else{//n�mero, el copiem a totes les files
			Object[][] matriu=gestor.obtenirMatriuDades();
			System.out.println("Num files matriu: "+matriu.length);
			for(int i=0;i<matriu.length;i++){
				alDret.add(i,fillDret);
			}
		}
		
		//afafem files fill Esquerra
		if(this.iEsquerra==1){//operaci�
			Operacio f=(Operacio)fillEsquerra;
			alEsquerra=f.calcula();
		}else if(iEsquerra==2){//propietat
			LlistaPropietats llprop=gestor.obtenirLlistaProps();
			Propietat pD=(Propietat)fillEsquerra;
			Dada[] d = gestor.obtenirColumna(pD.obtenirId());//obtenim la columna de la propietat
			for(int i=0;i<d.length;i++){				
				alEsquerra.add(i,d[i].obtenirValor());
			}
		}else{//n�mero, el copiem a totes les files
			Object[][] matriu=gestor.obtenirMatriuDades();
			for(int i=0;i<matriu.length;i++){
				alEsquerra.add(i,fillEsquerra);
			}
		}
		
		if(this instanceof SUMA){
			SUMA aux=(SUMA)this;
			al=aux.calculaBinari(alDret,alEsquerra);
		}else if(this instanceof RESTA){
			RESTA aux=(RESTA)this;
			al=aux.calculaBinari(alDret,alEsquerra);
		}else if(this instanceof MULTIPLICACIO){
			MULTIPLICACIO aux=(MULTIPLICACIO)this;
			al=aux.calculaBinari(alDret,alEsquerra);
		}else if(this instanceof DIVISIO){
			DIVISIO aux=(DIVISIO)this;
			al=aux.calculaBinari(alDret,alEsquerra);
		}else if(this instanceof POTENCIA){
			POTENCIA aux=(POTENCIA)this;
			al=aux.calculaBinari(alDret,alEsquerra);
		}else throw new Exception("Operaci� incorrecta");
		return al;	
	}
	/**
	 * Obt� el tipus del fill dret
	 * @return <code>iDret</code>
	 */
	public int getIDret() {
		return iDret;
	}
	/**
	 * Posa el nou tipus del fill dret
	 * @param dret, nou tipus de <code>iDret</code>
	 */
	public void setIDret(int dret) {
		iDret = dret;
	}
	/**
	 * Obt� el tipus del fill esquerra
	 * @return <code>iEsquerra</code>
	 */
	public int getIEsquerra() {
		return iEsquerra;
	}
	/**
	 * Posa el nou tipus del fill esquerra
	 * @param esquerra, nou tipus de <code>iEsquerra</code>
	 */
	public void setIEsquerra(int esquerra) {
		iEsquerra = esquerra;
	}	

}

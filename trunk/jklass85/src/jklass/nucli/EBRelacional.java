package jklass.nucli;

import java.util.ArrayList;

/**
 * Classe que representa una expressió booleana mínima, és a dir, que només consta d'un operador relacional i els valors que compara
 * És un subtipus d'ExpressioBooleana
 *@author Laia Riera Guerra
 * @version v.4 05/05/2007
 */
abstract class EBRelacional extends ExpressioBooleana{
	protected Object ovalor;//valor a comparar amb la variable
	protected Propietat ovar;//variable que es vol comparar	
	protected boolean valorEsPropietat;//indica si el valor comparatiu és una propietat o no
	
	/**
	 * Creadora buida d'EBRelacional
	 *
	 */
	public EBRelacional(){
		super();		
	}//endofmethod	
	/**
	 * Crea una nova EBRelacional i l'associa al GestorMatriu <code>m</code>
	 * @param m, GestorMatriu
	 */
	public EBRelacional(GestorMatriu m){
		super(m);		
	}
	
	/**
	 * Crea una EBRelacional de propietat <code>vari</code> i valor <code>oValor</code>
	 * @param <code>vari</code>, propietat que es vol comparar
	 * @param <code>oValor</code>, valor amb el qual es vol comparar la propietat
	 */
	public EBRelacional(Propietat vari, Object oValor,boolean esPropietat){
		super();
		ovalor=oValor;
		ovar=vari;	
		valorEsPropietat=esPropietat;
	}//endofmethod		
	
	/**
	 * Obté la variable o propietat de l'operació relacional
	 * @return Object, que representa la variable de l'operació relacional
	 */
	public Propietat getVar(){
		return ovar;
	}//endofmethod
	
	/**
	 * Obté el valor de l'operació relacional
	 * @return Object, que representa el valor de l'operació relacional
	 */
	public Object getValor(){
		return ovalor;
	}//endofmethod		
	/**
	 * Indica si el valor comparatiu de l'expressió relacional és una propietat o no
	 * @return true, si el valor comparatiu és una propietat, false altrament
	 */
	public boolean isValorEsPropietat() {
		return valorEsPropietat;
	}
	/**
	 * Actualitza el valor del booleà <code>valorEsPropietat</code> amb el valor que ens passen com a paràmetre
	 * @param valorEsPropietat, booleà que conté la nova indicació de si el valor comparatiu és una propietat o no.
	 */
	public void setValorEsPropietat(boolean valorEsPropietat) {
		this.valorEsPropietat = valorEsPropietat;
	}
	/**
	   * Comprova si les propietats que intervenen en l'expressió relacional pertanyen a la matriu actual
	   * @param propSelec, llistat de les propietats de la matriu actual
	   * @return true, si l'expressió relacional conté propietats que no existeixen a la matriu actual, false altrament
	   */
	public boolean comprovarPropietats(String[] propSelec)throws Exception{		
		String snom=ovar.obtenirId();
		boolean b=false;
		for(int i=0;i<propSelec.length && !b;i++){
			if(propSelec[i].compareTo(snom)==0){//l'hem trobat
				b=true;
			}
		}
		if(!b)return true;//la propietat no hi era
		else if(valorEsPropietat){
			b=false;
			LlistaPropietats prop=gestor.obtenirLlistaProps();
			Propietat valor=prop.obtenirPropietat((String)ovalor);
			snom=valor.obtenirId();
			for(int i=0;i<propSelec.length && !b;i++){
				if(propSelec[i].compareTo(snom)==0){//l'hem trobat
					b=true;
				}
			}
			if(!b)return true;
		}
		return false;
	}
	
}

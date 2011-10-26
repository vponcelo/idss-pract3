package jklass.nucli;

import java.util.ArrayList;
/**
 * Classe que representa l'operació unària aritmètica general
 * 
 * @author Laia Riera Guerra
 * @version v.4 05/05/2007
 */
public class OperacioUnaria extends Operacio{
	protected Object fill;
	int iFill;//indica si el fill és una propietat
	/**
	 * Constructor per defecte
	 *
	 */
	public OperacioUnaria(){
		super();
		fill=null;		
	}
	/**
	 * Construeix una nova operació unària que té a <code>fills</code> com a fill i l'associa al 
	 * GestorMatriu <code>m</code>
	 * @param m, GestorMatriu
	 * @param fills, fill de l'operació
	 * @param f, tipus del fill seguint la següent codificació:
	 * f==1, operació
	 * f==2, propietat
	 * f==3, número
	 */
	public OperacioUnaria(GestorMatriu m,Object fills,int f){
		super(m);
		fill=fills;
		iFill=f;
	}
	/**
	 * Obté el fill de l'operació
	 * @return fill de l'operació
	 */
	public Object getFill() {
		return fill;
	}
	/**
	 * Posa com a nou fill de l'operació l'Object <code>fill</code>
	 * @param fill, nou fill de l'operació
	 */
	public void setFill(Object fill) {
		this.fill = fill;
	}
	/**
	 * Calula l'operació sobre la matriu de dades
	 * @return llistat de valors que representa la columna de la matriu resultat de fer el càlcul
	 */
	public ArrayList calcula()throws Exception{	
		ArrayList al=new ArrayList();
		ArrayList alDret=new ArrayList();
		
		//agafem files fill 
		if(this.iFill==1){//operació
			Operacio f=(Operacio)fill;
			alDret=f.calcula();
		}else if(iFill==2){//propietat
			LlistaPropietats llprop=gestor.obtenirLlistaProps();
			Propietat pD=(Propietat)fill;
			Dada[] d = gestor.obtenirColumna(pD.obtenirId());//obtenim la columna de la propietat
			for(int i=0;i<d.length;i++){
				alDret.add(i,d[i].obtenirValor());
			}
		}else{//número, el copiem a totes les files
			Object[][] matriu=gestor.obtenirMatriuDades();
			for(int i=0;i<matriu.length;i++){
				alDret.add(i,fill);
			}
		}
		if(this instanceof ARREL){
			ARREL aux=(ARREL)this;
			al=aux.calculaUnari(alDret);
		}else if(this instanceof LOGARITME){
			LOGARITME aux=(LOGARITME)this;
			al=aux.calculaUnari(alDret);
		}else if(this instanceof LOGARITME_NEPERIA){
			LOGARITME_NEPERIA aux=(LOGARITME_NEPERIA)this;
			al=aux.calculaUnari(alDret);
		}else if(this instanceof EXPONENCIAL){
			EXPONENCIAL aux=(EXPONENCIAL)this;
			al=aux.calculaUnari(alDret);
		}else throw new Exception("Operació incorrecta");		
		return al;
	}
	/**
	 * Obté el tipus del fill
	 * @return tipus del fill de l'operació
	 */
	public int getIFill() {
		return iFill;
	}
	/**
	 * Posa el nou tipus del fill de l'operació
	 * @param fill, nou tipus del fill de l'operació
	 */
	public void setIFill(int fill) {
		iFill = fill;
	}



}

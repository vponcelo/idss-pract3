package jklass.nucli;

import java.util.ArrayList;

/**
 * Classe que representa una expressió booleana mínim, on l'operador relacional és l'igual
 * És un subtipus de EBRelacional
 *@author Laia Riera Guerra
 * @version v.4 05/05/2007
 */
public class EsIgual extends EBRelacional{
	/**
	 * Creadora buida d'EsIgual
	 *
	 */
	public EsIgual(){
		super();
	}//endofmethod
	
	
	/**
	 * Creadora d'EsIgual, on posa com a variable el paràmetre d'entrada <code>vari</code>,
	 * com a valor a comparar el paràmetre d'entrada <code>oValor</code>, el nom de l'operació a null,crea la llista de fills buida i l'operació pare nul.la 
	 * @param vari, variable a comparar
	 * @param oValor, valor amb el que es vol comprar la variable
	 * @param esPropietat, indica si <code>oValor</code> és una propietat o no
	 */
	public EsIgual(Propietat vari, Object oValor,boolean esPropietat){
		super(vari, oValor,esPropietat);
	}//endofmethod
	
	
	/**
	 * Avalua una expressió booleana, el nucli de la qual és l'operador igual
	 * <p>
	 * @return ArrayList de Boolean, un vector de booleans que representa el resultat que s'ha obtingut d'avaluar cada una de les files 
	 * de la matriu en actiu segons l'expressió booleana
	 */
	public ArrayList avaluaFulla() throws Exception {		
		
	
			this.setGestor(this.obteGestorMatriu());
			LlistaPropietats prop=gestor.obtenirLlistaProps();
			Dada[] d = gestor.obtenirColumna(ovar.obtenirId());
			ArrayList resultat=new ArrayList(d.length);
			if(this.valorEsPropietat){
				Propietat pValor=prop.obtenirPropietat((String)ovalor);
				Dada[] dValor=gestor.obtenirColumna(pValor.obtenirId());
				for(int i=0;i<d.length;i++){
					if(ovar instanceof PropCategorica){
						String sDada=(String)d[i].obtenirValor();
						String svalor=(String)dValor[i].obtenirValor();
						if(svalor.compareTo("?")==0||sDada.compareTo("?")==0)resultat.add(i,"?");
						else if(svalor.compareTo(sDada)==0)resultat.add(i,"1");
						else resultat.add(i,"0");			
					}else{//es numerica
						String sDada=(String)d[i].obtenirValor();
						if(sDada.compareTo("?")==0)resultat.add(i,"?");
						else{
							Float fdada=new Float((String)d[i].obtenirValor());
							Float fvalor=new Float((String)dValor[i].obtenirValor());
							if(fdada.floatValue()==fvalor.floatValue())resultat.add(i,"1");
							else resultat.add(i,"0");
						}					
					}
				}
				
			}else{
				for(int i=0;i<d.length;i++){
					if(ovar instanceof PropCategorica){
						String sDada=(String)d[i].obtenirValor();
						String svalor=(String)ovalor;
						if(svalor.compareTo("?")==0||sDada.compareTo("?")==0)resultat.add(i,"?");
						else if(svalor.compareTo(sDada)==0)resultat.add(i,"1");
						else resultat.add(i,"0");			
					}else{//es numerica
						String sDada=(String)d[i].obtenirValor();
						if(sDada.compareTo("?")==0)resultat.add(i,"?");
						else{
							Float fdada=new Float((String)d[i].obtenirValor());
							Float fvalor=(Float)ovalor;
							if(fdada.floatValue()==fvalor.floatValue())resultat.add(i,"1");
							else resultat.add(i,"0");
						}					
					}
				}
			}		
			return resultat;
		
	}
	/**
	 * Escriu l'expressió relacional en inordre, seguint el format següent:
	 * (ovar) = ovalor .
	 * (ovar) = (ovalor) si ovalor és una propietat.
	 * @return String que representa l'operació relacional escrita en inordre
	 */
	 public String escriureNormal(){
		  Propietat p=this.getVar();
		  String s;
		  if(this.isValorEsPropietat()){
			  s="("+p.obtenirId()+") "+this.getIGUAL()+" ("+this.getValor()+")";
		  }else s="("+p.obtenirId()+") "+this.getIGUAL()+" "+this.getValor();		 
		  return s;
	  }
	 /**
	   * Obté l'expressió relacional escrita en inordre i formatejada a Latex
	   * @return String, expressió relacional escrita en inordre i formatejada a Latex
	   */
	 public String escriureNormalLatex(){
		  FitxerTex fTex=new FitxerTex("nou");
		  Propietat p=this.getVar();
		  String s;
		  if(this.isValorEsPropietat()){
			  s=fTex.adaptarATex(p.obtenirId())+" "+this.getIGUAL()+" "+fTex.adaptarATex((String)this.getValor());			  
		  }else{
			  Float f=(Float)this.getValor();
			  s=fTex.adaptarATex(p.obtenirId())+" "+this.getIGUAL()+" "+fTex.formatejarReal(f.floatValue());		  
		  }
		  return s;
	  }
	 /***
	  * Crea l'expressió relacional amb operand = <code>sOperand</code>, ovar= propietat de nom <code>sProp</code>, ovalor = <code>sValor</code> i valorEsPropietat= <code>esPropietat</code> 
	  * @param sOperand, operand de l'expressió relacional 
	  * @param sProp, propietat de l'expressió relacional
	  * @param sValor, valor comparatiu de l'expressió relacional
	  * @param llistaProp, llista de propietats de la matriu actual
	  * @param esPropietat, indica si <code>sValor</code> és una propietat o no
	  * @return ExpressioBooleana resultant 
	  */
	 public ExpressioBooleana llegirBCNormalRelacional(String sOperand,String sProp,String sValor, LlistaPropietats llistaProp,boolean esPropietat)throws Exception{
		  Propietat p = llistaProp.obtenirPropietat(sProp);
		  Object oValor = sValor;
		  if (sOperand.compareTo(ExpressioBooleana.IGUAL) == 0){
			  if(esPropietat==true){
				  Propietat pValor=llistaProp.obtenirPropietat(sValor);
				  if(pValor==null)throw new Exception("No existeix la propietat de nom " + sValor);
				  else if(llistaProp.esPropietatNumerica(sProp)&& !llistaProp.esPropietatNumerica(sValor))throw new Exception("No es pot comparar una variable numèrica amb una que no ho és");
				  else if(llistaProp.esPropietatCategorica(sProp) && !llistaProp.esPropietatCategorica(sValor))throw new Exception("No es pot comparar una variable categòrica amb una que no ho és");
				  else return new EsIgual(p,oValor,esPropietat);			  
			  }else{
				  if (llistaProp.esPropietatNumerica(sProp)){
					  oValor = Float.valueOf(sValor);
						PropNumerica pn = (PropNumerica) p;
						float fvalor = Float.parseFloat(sValor);
						if (fvalor > pn.obtenirRangMax()
								|| fvalor < pn.obtenirRangMin())
							throw new Exception("El valor " + sValor+ " no es troba dins els rangs permesos");
				  }
				  return new EsIgual(p, oValor,esPropietat);
			  }
		  }		  
		  else return null;
	  }
	 
	 
}

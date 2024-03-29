package jklass.nucli;

import java.util.ArrayList;

/**
 * Classe que representa una expressi� booleana m�nima, on l'operador relacional �s el diferent
 * �s una subclasse de la classe EBRelacional
 * @author Laia Riera Guerra
 * @version v.4 05/05/2007
 */
public class EsDiferent extends EBRelacional {
	
	/**
	 * Avalua una expressi� booleana, el nucli de la qual �s l'operador diferent
	 * <p>
	 * @return ArrayList de Boolean, un vector de booleans que representa el resultat que s'ha obtingut d'avaluar cada una de les files 
	 * de la matriu en actiu segons l'expressi� booleana
	 */
	public ArrayList avaluaFulla()throws Exception {		
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
					else if(svalor.compareTo(sDada)==0)resultat.add(i,"0");
					else resultat.add(i,"1");			
				}else{//es numerica
					String sDada=(String)d[i].obtenirValor();
					if(sDada.compareTo("?")==0)resultat.add(i,"?");
					else{
						Float fdada=new Float((String)d[i].obtenirValor());
						Float fvalor=new Float((String)dValor[i].obtenirValor());
						if(fdada.floatValue()==fvalor.floatValue())resultat.add(i,"0");
						else resultat.add(i,"1");
					}					
				}
			}
			
		}else{
			for(int i=0;i<d.length;i++){
				if(ovar instanceof PropCategorica){
					String sDada=(String)d[i].obtenirValor();
					String svalor=(String)ovalor;
					if(svalor.compareTo("?")==0||sDada.compareTo("?")==0)resultat.add(i,"?");
					else if(svalor.compareTo(sDada)==0)resultat.add(i,"0");
					else resultat.add(i,"1");			
				}else{//es numerica
					String sDada=(String)d[i].obtenirValor();
					if(sDada.compareTo("?")==0)resultat.add(i,"?");
					else{
						Float fdada=new Float((String)d[i].obtenirValor());
						Float fvalor=(Float)ovalor;
						if(fdada.floatValue()==fvalor.floatValue())resultat.add(i,"0");
						else resultat.add(i,"1");
					}					
				}
			}
		}		
		return resultat;
	}

	/**
	 * Creadora buida d'EsDiferent
	 * 
	 */
	public EsDiferent() {
		super();
	}// endofmethod

	/**
	 * Creadora d'EsDiferent, on posa com a variable el par�metre
	 * d'entrada <code>vari</code>, com a valor a comparar el par�metre
	 * d'entrada <code>oValor</code>, el nom de l'operaci� a null,crea la
	 * llista de fills buida i l'operaci� pare nul.la
	 * 
	 * @param vari,variable o propietat a comparar
	 * @param oValor,valor amb el que es vol comprar la variable
	 * @param esPropietat, indica si <code>oValor</code> �s una propietat o no
	 */
	public EsDiferent(Propietat vari, Object oValor,boolean esPropietat) {
		super(vari, oValor,esPropietat);
	}// endofmethod
	
	/**
	 * Escriu l'expressi� relacional en inordre, seguint el format seg�ent:
	 * (ovar) != ovalor .
	 * (ovar) != (ovalor) si ovalor �s una propietat.
	 * @return String que representa l'operaci� relacional escrita en inordre
	 */
	  public String escriureNormal(){
		  Propietat p=this.getVar();
		  String s;
		  if(this.isValorEsPropietat()){
			  s="("+p.obtenirId()+") "+this.getDIFERENT()+" ("+this.getValor()+")";
		  }else s="("+p.obtenirId()+") "+this.getDIFERENT()+" "+this.getValor();
		  
		  return s;
	  }
	  /**
	   * Obt� l'expressi� relacional escrita en inordre i formatejada a Latex
	   * @return String, expressi� relacional escrita en inordre i formatejada a Latex
	   * 
	   * Modified by
	   * @author Grup SISPD QT 2009-2010
	   */
	  public String escriureNormalLatex(){
		  FitxerTex fTex=new FitxerTex("nou");
		  Propietat p=this.getVar();
		  String s;
		  if(this.isValorEsPropietat()){
			  s=fTex.adaptarATex(p.obtenirId())+" \\not = "+fTex.adaptarATex((String)this.getValor());			  
		  }else{
			  Float f=(Float)this.getValor();
			  s=fTex.adaptarATex(p.obtenirId())+" \\not = "+fTex.formatejarReal(f.floatValue());		  
		  }
		  return s;
	  }
	 /***
	  * Crea l'expressi� relacional amb operand = <code>sOperand</code>, ovar= propietat de nom <code>sProp</code>, ovalor = <code>sValor</code> i valorEsPropietat= <code>esPropietat</code> 
	  * @param sOperand, operand de l'expressi� relacional 
	  * @param sProp, propietat de l'expressi� relacional
	  * @param sValor, valor comparatiu de l'expressi� relacional
	  * @param llistaProp, llista de propietats de la matriu actual
	  * @param esPropietat, indica si <code>sValor</code> �s una propietat o no
	  * @return ExpressioBooleana resultant 
	  */
	  public ExpressioBooleana llegirBCNormalRelacional(String sOperand,String sProp,String sValor, LlistaPropietats llistaProp,boolean esPropietat)throws Exception{
		  Propietat p = llistaProp.obtenirPropietat(sProp);
		  Object oValor = sValor;
		  if (sOperand.compareTo(ExpressioBooleana.DIFERENT) == 0){
			  if(esPropietat==true){
				  Propietat pValor=llistaProp.obtenirPropietat(sValor);
				  if(pValor==null)throw new Exception("No existeix la propietat de nom " + sValor);
				  else if(llistaProp.esPropietatNumerica(sProp)&& !llistaProp.esPropietatNumerica(sValor))throw new Exception("No es pot comparar una variable num�rica amb una que no ho �s");
				  else if(llistaProp.esPropietatCategorica(sProp) && !llistaProp.esPropietatCategorica(sValor))throw new Exception("No es pot comparar una variable categ�rica amb una que no ho �s");
				  else return new EsDiferent(p,oValor,esPropietat);			  
			  }else{
				  
					  if (llistaProp.esPropietatNumerica(sProp)){
						  oValor = Float.valueOf(sValor);
							PropNumerica pn = (PropNumerica) p;
							float fvalor = Float.parseFloat(sValor);
							if (fvalor > pn.obtenirRangMax()
									|| fvalor < pn.obtenirRangMin())
								throw new Exception("El valor " + sValor+ " no es troba dins els rangs permesos");
					  }
					  return new EsDiferent(p,oValor,esPropietat);
			  }
		  }
		  else return null;
		  
		  
	  }
	  
	 
}

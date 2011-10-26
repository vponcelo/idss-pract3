package jklass.nucli;

import java.util.ArrayList;

/**
 * Classe que representa una expressió booleana mínima, on l'operador relacional és el pertany
 * És un subtipus de EBRelacional
 *@author Laia Riera Guerra
 * @version v.4 05/05/2007
 */
public class EsPertany extends EBRelacional{

	/**
	 * Creadora buida d'EsPertany
	 *
	 */
	public EsPertany(){
		super();
	}//endofmethod
	
	
	/**
	 * Creadora d'EsPertany, on posa com a variable el paràmetre d'entrada <code>vari</code>,
	 * com a valor a comparar el paràmetre d'entrada <code>oValor</code>, el nom de l'operació a null,crea la llista de fills buida i l'operació pare nul.la 
	 * @param vari, variable a comparar
	 * @param oValor, valor amb el que comprar la variable
	 * @param esPropietat, indica si <code>oValor</code> és una propietat o no
	 */
	public EsPertany(Propietat vari, Object oValor,boolean esPropietat){
		super(vari, oValor,esPropietat);
	}//endofmethod
	
	/**
	 * Avalua una expressió booleana, el nucli de la qual és l'operador pertany
	 * <p>
	 * @return ArrayList de Boolean, un vector de booleans que representa el resultat que s'ha obtingut d'avaluar cada una de les files 
	 * de la matriu en actiu segons l'expressió booleana
	 */
	public ArrayList avaluaFulla() throws Exception {		
		ArrayList al=(ArrayList)ovalor;
		this.setGestor(this.obteGestorMatriu());
		LlistaPropietats prop=gestor.obtenirLlistaProps();		
		Dada[] d = gestor.obtenirColumna(ovar.obtenirId());
		ArrayList resultat=new ArrayList(d.length);		
		for(int i=0;i<d.length;i++){
			if(ovar instanceof PropCategorica){
				String sDada=(String)d[i].obtenirValor();
				boolean aux=false;
				if(sDada.compareTo("?")==0)resultat.add(i,"?");
				else{
					int j;
					for(j=0;j<al.size() && !aux;j++){//recorrem el conjunt de valors
						System.out.println("Valors = "+al.get(j));
						if(sDada.compareTo((String)al.get(j))==0){
							aux=true;
							resultat.add(i,"1");
						}
					}
					System.out.println("J = "+j+" i aux = "+aux);
					if(j==al.size() && !aux)resultat.add(i,"0");
				}				
			}else{//es numerica
				String sDada=(String)d[i].obtenirValor();
				if(sDada.compareTo("?")==0)resultat.add(i,"?");
				else{
					Float fdada=new Float((String)d[i].obtenirValor());
					int j;
					boolean aux=false;
					for(j=0;j<al.size() && !aux;j++){//recorrem el conjunt de valors
						Float fvalor=(Float)al.get(j);
						if(fdada.floatValue()==fvalor.floatValue()){
							aux=true;
							resultat.add(i,"1");
						}
					}
					if(j==al.size() && !aux)resultat.add(i,"0");
				}				
			}
		}
		return resultat;
	}
	/**
	 * Escriu els valors del llistat de valors segons el següent format:
	 * {v1,v2,v3,...}
	 * @return String amb els valors del llistat de valors concatenats
	 */
	public String escriure_Valor(){
		
		 ArrayList al=(ArrayList)this.getValor();	 
		 String saux="{";
		  for(int i=0;i<al.size();i++){
			  saux=saux+al.get(i)+",";
		  }
		  saux=saux.substring(0, saux.length()-1)+"}";		  
		  return saux;
	}
	/**
	 * Escriu els valors del llistat de valors segons el el següent format:
	 * {v1,v2,v3,...} i formatejat a latex 
	 * @return String amb els valors del llistat de valors concatenats i formatejat a latex
	 */
	public String escriure_ValorLatex(){
		 FitxerTex fTex=new FitxerTex("nou");
		Propietat p=this.getVar();
		 ArrayList al=(ArrayList)this.getValor();	 
		 String saux=" \\{ ";
		 if(p instanceof PropCategorica){
			 for(int i=0;i<al.size();i++){
				  saux=saux+fTex.adaptarATex((String)al.get(i))+",";
			  } 
		 }else if(p instanceof PropNumerica){
			 for(int i=0;i<al.size();i++){
				 Float f=(Float)al.get(i);
				  saux=saux+fTex.formatejarReal(f.floatValue())+",";
			  }
		 }
		  
		  saux=saux.substring(0, saux.length()-1)+" \\} ";		  
		  return saux;
	}
	/**
	 * Escriu l'expressió relacional en inordre, seguint el format següent:
	 * (ovar) member {ovalor} .	
	 * @return String que representa l'operació relacional escrita en inordre
	 */
	 public String escriureNormal(){		 
		  Propietat p=this.getVar();	
		  String s="("+p.obtenirId()+") "+this.getPERTANY()+" "+this.escriure_Valor();		 
		  return s;
	 }
	 /**
	   * Obté l'expressió relacional escrita en inordre i formatejada a Latex
	   * @return String, expressió relacional escrita en inordre i formatejada a Latex
	   */
	 public String escriureNormalLatex(){
		 FitxerTex fTex=new FitxerTex("nou");
		  Propietat p=this.getVar();	
		  String s=fTex.adaptarATex(p.obtenirId())+" \\in "+this.escriure_ValorLatex();		  
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
		 if(sOperand.compareTo(ExpressioBooleana.PERTANY) == 0){
			 ArrayList al = new ArrayList();
				char[] cAux = sValor.toCharArray();
				for (int iPar = 1; iPar < sValor.length() - 1; iPar++) {// ens saltem el { i }

					int iFin = iPar;
					while (cAux[iFin] != ',' && cAux[iFin] != '}') {// llegeix els valors
						iFin++;
					}// endwhile
					String sVal = sValor.substring(iPar, iFin);
					oValor = sVal.trim();					
					if (llistaProp.esPropietatNumerica(sProp))
						oValor = Float.valueOf((String) oValor);
					al.add(oValor);				
					iPar = iFin;
				}// endfor
				return new EsPertany(p, al,esPropietat);			 
							
		 }else return null;
		 
	 }
	 
	
}

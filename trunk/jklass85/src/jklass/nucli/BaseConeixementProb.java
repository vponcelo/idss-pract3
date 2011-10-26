package jklass.nucli;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Classe que representa un conjunt de regles probabilitzades que s'avaluen en grup.
 * És una subclasse de la classe BaseConeixement
 * @author Alfons Bosch, Patrícia Garcia
 */
public class BaseConeixementProb extends BaseConeixement {

	/**
	 * Constructora 
	 */
	public BaseConeixementProb(int id, GestorMatriu m, String nom) {
		super(id, m, nom);
	}
	
	/**
	 * Mètode que permet generar regles probabilitzades a partir de variables numèriques
	 * @param varNum - Nom de la variable numèrica
	 * @param numModalitat - Modalitat de l'interval actual
	 * @param intervals - Llista d'intervals
	 * @param varClasse - Variable de classe que s'ha utilitzat per obtenir els intervals de <code>varNum<code>
	 * @param modClasse - Modalitat de classe per la regla actual
	 * @param prob - Probabilitat de la regla
	 * @throws Exception
	 * @author Alberto Martinez
	 */
	public void generarReglaProbNumerica(String varNum, int numModalitat, List intervals, String varClasse, String modClasse, float prob) throws Exception{
		float iniciInterval = ((float[])intervals.get(0))[numModalitat];
		float fiInterval = ((float[])intervals.get(0))[numModalitat+1];
		boolean revised = (Boolean) intervals.get(1);
		int revisedBoxplotType = (Integer) intervals.get(2);
		
		String tipusInici = " >= ";
		String tipusFinal = " <= ";
		
		if ( prob > 0.0 ){
			if(!revised){
				if ( numModalitat == 1 || numModalitat == 2)	tipusInici = " > ";
			} else {
				if(revisedBoxplotType == 0){
					if ( numModalitat == 0){
						tipusFinal = " < ";
					} else if (numModalitat == 2) {
						tipusInici = " > ";
					}
				} else {
					if ( numModalitat == 1){
						tipusInici = " > ";
						tipusFinal = " < ";
					}
				}
			}
			
			llegirReglaNormalProb("((" + varNum + ")" +  tipusInici + iniciInterval +") and ((" + varNum + ")" + tipusFinal +fiInterval + ")",
					"(" + varClasse + ")" + modClasse, seguentNomRegla(), prob);
		}	
	}
	
	/*
	public void generarReglaProbNumerica(String varNum, int numModalitat, List intervals, String varClasse, String modClasse, float prob) throws Exception{
		float iniciInterval = ((float[])intervals.get(0))[numModalitat];
		float fiInterval = ((float[])intervals.get(0))[numModalitat+1];
		
		
		String tipusInici = " > ";
		String tipusFinal = " <= ";
		if ( prob > 0.0 ){
			if ( numModalitat==0 ){
				tipusInici = " >= ";
			}
				
			llegirReglaNormalProb("((" + varNum + ")" +  tipusInici + iniciInterval +") and ((" + varNum + ")" + tipusFinal +fiInterval + ")",
					"(" + varClasse + ")" + modClasse, seguentNomRegla(), prob);
		}
		
	}*/

	/**
	 * Mètode que permet generar regles probabilitzades a partir de variables categòriques
	 * @param varCat - Nom de la variable categòrica
	 * @param modVarCategorica - Modalitat que s'avaluarà a l'antecedent de la regla
	 * @param varClasse - Nom de la variable de classe
	 * @param modClasse - Modalitat de la classe (pel conseqüent)
	 * @param probabilitat - Probabilitat de la regla
	 * @throws Exception
	 */
	public void generarReglaProb(String varCat, String modVarCategorica, String varClasse, String modClasse, float probabilitat) throws Exception {
    	if ( probabilitat > 0.0 ){
    		llegirReglaNormalProb("(" + varCat + ") member " + "{" + modVarCategorica + "}", "(" + varClasse + ")" + modClasse, seguentNomRegla(),probabilitat);
    	}	
	}
	
	/**
	 * Mètode que permet podar una base de coneixement a un cert llindar mínim de probabilitat
	 * @param llindar - Llindar de probabilitat <code>float</code> de 0 a 1
	 * @return
	 */
	public int podar(float llindar){
		int numeroEliminades = 0;
		
		for ( ReglaProb regla : new ArrayList<ReglaProb>(obtenirTaulaPosicions().values())){
			if ( regla.getProb()<llindar){
				this.treureRegla(regla.getNomRegla());
				numeroEliminades++;
			}
		}
		return numeroEliminades;
	}
	
	/**
	 * Llegeix una expressió lògica en format inordre i crea la regla de nom <code>snom</code>, de
	 * conseqüent <code>sEtiqueta</code> i l'expressió booleana corresponent a <code>sregla</code>
	 * @param sregla, string que representa l'expressió booleana a construir
	 * @param sEtiqueta, conseqüent de la regla
	 * @param snom, nom de la regla
	 * @throws Exception
	 */
	public void llegirReglaNormalProb(String sregla,String sEtiqueta,String snom,float prob)throws Exception{
		ExpressioBooleana aux=new ExpressioBooleana(m);		
		ExpressioBooleana b=aux.llegirBCNormal(sregla);
		
		Regla reg = this.crearReglaProb(b, sEtiqueta,snom,prob);
		afegirRegla(reg);
	}
	
	/**
	 * Crea una nova regla que representa l'expressió booleana <code>eb</code>, de conseqüent <code>con</code> i que s'anomenarà <code>snom</code>
	 * @param <code>eb</code>, expressió booleana
	 * @param <code>con</code>, consegüent de la regla
	 * @param <code>snom</code>,nom amb que s'indentificarà la regla
	 * @return <code>Regla</code>, la regla d'expressió booleana <code>eb</code>, conseqüent = <code>con</code> i nom = <code>som</code>
	 */
	public ReglaProb crearReglaProb(ExpressioBooleana eb, String con,String snom,float prob) throws Exception{
		
		if(snom==null){
			snom=this.seguentNomRegla();
			ReglaProb r = new ReglaProb(eb, con, contador, m,snom);
			r.setProb(prob);
			contador++;	
			return r;
		}
		else if(this.existeixNomRegla(snom))throw new Exception("Ja existeix una regla amb aquest nom");
		else{
			ReglaProb r = new ReglaProb(eb, con, contador, m,snom);
			r.setProb(prob);
			contador++;	
			return r;
		}					
	}
	
	/**
	 * Realitza l'avaluació regla a regla de la base de coneixement probabilitzada actual
	 * @param notaciobinaria, indica si el resultat final ha de ser en notació binària(1,0) o no
	 * @return llista de variables creades com a resultats de l'avaluació ( una per regla )
	 * @throws Exception
	 */
	public ArrayList avaluaReglaARegla(boolean notaciobinaria)throws Exception{
		ArrayList llistaProp=new ArrayList();
		ArrayList llistaPropRet = new ArrayList();
		ArrayList mDades=new ArrayList();
		LlistaPropietats LlProp=m.obtenirLlistaProps();
		//ArrayList al=new ArrayList();
		if(alNomsRegles.size()==0)throw new Exception("Base de coneixement buida");
		else{		
		for(int i=0;i<alNomsRegles.size();i++){
			String nom = (String) alNomsRegles.get(i);//nom d'una regla
			Regla r = obtenirRegla(nom);
			ArrayList resultat = r.avalua();//avalua una regla. Retorna els resultats per files. Longitud=num de files
			ArrayList llistaModalitats = new ArrayList();
			Dada[] convertit=new Dada[resultat.size()];
			String nomRegla=obtenirNomPropietatContador(r.getNomRegla(),LlProp);
			if(notaciobinaria){
				llistaModalitats.add("0");
				llistaModalitats.add("1");
						
							
				PropCategorica p = new PropCategorica(nomRegla, llistaModalitats);				
				for(int j=0;j<resultat.size();j++){
					String s=(String)resultat.get(j);
					if(s.compareTo("?")==0){
						convertit[j]=new Dada("?");
						p.actualitzarLliure("?");
					}
					else if(s.compareTo("0")==0){
						convertit[j]=new Dada("0");
						p.actualitzarLliure("0");
					}
					else{
						convertit[j]=new Dada("1");
						p.actualitzarLliure("1");
					}
					System.out.println("El convertit "+j+" = "+convertit[j].obtenirValor());
				}
				llistaProp.add(p);
				llistaPropRet.add(p);
			}else{
				llistaModalitats.add("Fals");
				llistaModalitats.add(r.getConseguent());
				
							
				PropCategorica p = new PropCategorica(nomRegla, llistaModalitats);					
				String reg=r.getConseguent();
				for(int j=0;j<resultat.size();j++){
					String s=(String)resultat.get(j);
					if(s.compareTo("?")==0){
						convertit[j]=new Dada("?");
						p.actualitzarLliure("?");
					}
					else if(s.compareTo("0")==0){
						convertit[j]=new Dada("Fals");
						p.actualitzarLliure("Fals");
					}
					else{
						convertit[j]=new Dada(reg);
						p.actualitzarLliure(reg);
					}
					System.out.println("El convertit "+j+" = "+convertit[j].obtenirValor());
				}
				llistaProp.add(p);
				llistaPropRet.add(p);
			}
			PropNumerica probabilitatPerRegla = new PropNumerica("prob_"+nomRegla,0,1);
			Dada[] probColumna = new Dada[resultat.size()];
			for (int j=0;j<resultat.size();j++){
				if ( convertit[j].compara(new Dada("Fals")) || convertit[j].compara(new Dada("0")) ){
					probColumna[j] = new Dada("0");
				}
				else if ( convertit[j].esMissing() ){
					probColumna[j] = new Dada("?");
				}
				else{
					probColumna[j] = new Dada(((ReglaProb)r).getProb());
				}
			}
			llistaProp.add(probabilitatPerRegla);
			mDades.add(convertit);
			mDades.add(probColumna);
			//al.add(resultat);
		}
		}
		m.afegirColumnes(llistaProp, mDades);		
		return llistaPropRet;
	}
}

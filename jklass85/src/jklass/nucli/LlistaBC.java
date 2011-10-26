package jklass.nucli;

import java.util.ArrayList;

/**
 * Classe que conté totes les bases de coneixement associades a una matriu 
 * És una extensió de LlistaIndexos
 * @author Laia Riera Guerra
 * @version v.4 05/05/2007
 */
public class LlistaBC extends LlistaIndexos{
	protected ArrayList alNoms;//llista de noms de BC
	//llista conté llista d'indentificadors de BC
	//taulaPos conté correspondència identificadors--> BC
	/**
	 * Crea una LListaBC i l'associa amb el GestorMatriu <code>m</code>
	 * @param <code>m</code>, GestorMatriu al que quedarà associat la LListaBC
	 *  @author Laia Riera Guerra
	 */
	LlistaBC(GestorMatriu m){
	    super(m);
	    alNoms=new ArrayList();
	  }
	/**
	 * Afegeix una nova base de coneixement a la llistaBC
	 * @param <code>bc</code>, Base de Coneixement que es vol afegir a la llista
	 *  @author Laia Riera Guerra
	 */
	public void afegirBC(BaseConeixement bc){		
		llista.add(new Integer(bc.getIdentificador()));
		taulaPos.put(new Integer(bc.getIdentificador()),bc);
		alNoms.add(bc.getNomBC());
	}
	/**
	 * Treu de la llistaBC la base de coneixement amb identificador= <code>i</code>
	 * @param <code>i</code>, index que ocupa el nom de la BC dins alNoms
	 *  @author Laia Riera Guerra
	 */
	public void eliminarBC(int i){
		BaseConeixement bc=this.obteBC(i);
		alNoms.remove(i);
		taulaPos.remove(bc.getIdentificador());
		llista.remove(i);
		bc=null;
	}
	
	/**
	 * Obté la Base de Coneixement d'identificador o índex <code>i</code>
	 * @param <code>i</code>, index que ocupa el nom de la BC dins la llista de noms
	 * @return BaseConeixement
	 *  @author Laia Riera Guerra
	 */
	public BaseConeixement obteBC(int i){
		Integer n=(Integer)llista.get(i);
		return (BaseConeixement)taulaPos.get(n);
	}
	/**
	 * Retorna un enter que serà el següent identificador de la Base de Coneixement que es crearà
	 * @return Enter que indica el tamany actual de la llistaBC
	 *  @author Laia Riera Guerra
	 */
	public int numeroSeguentBC(){
		return llista.size();
	}
	/**
	 * Obté el llistat de noms de les bases de coneixement
	 * @return llistat de noms de les bases de coneixement
	 *  @author Laia Riera Guerra
	 */
	public ArrayList getAlNoms() {
		return alNoms;
	}
	/**
	 * Posa el llistat de noms de les bases de coneixement = <code>alNoms</code> 
	 * @param alNoms, nou llistat de noms de bases de coneixement
	 *  @author Laia Riera Guerra
	 */
	public void setAlNoms(ArrayList alNoms) {
		this.alNoms = alNoms;
	}	
	/**
	 * Copia els valors de la base de coneixement <code>copiaBC</code> a la base de coneixement del mateix nom que la base de coneixement que ens passen com a paràmetre
	 * @param copiaBC, còpia que volem guardar
	 * @author Laia Riera Guerra
	 */
	public void guardarCanvis(BaseConeixement copiaBC){
		/*int id=-copiaBC.getIdentificador();*/
		int i=this.obtenirIndex(copiaBC.getNomBC());
		BaseConeixement Original=this.obteBC(i);
		Original.setContador(copiaBC.getContador());
		Original.copiarLlista(copiaBC.getLlista());
		Original.copiarTaulaPos(copiaBC.getTaulaPos());
		Original.copiarAlNomsRegles(copiaBC.getAlNomsRegles());
		Original.copiarClassificacio(copiaBC.getClassificacio());
		Original.copiarPropClassi(copiaBC.getPropClassi());
	}
	/**
	 * Obté l'índex de la base de coneixement de nom <code>snom</code>
	 * @return índex de la base de coneixement
	 * @author Laia Riera Guerra
	 */
	public int obtenirIndex(String snom){
		int resultat=-1;
		boolean b=false;
		for(int i=0;i<alNoms.size()&&!b;i++){
			if(snom.compareTo((String)alNoms.get(i))==0){
				b=true;
				resultat=i;
			}
		}
		return resultat;
	}
	/**
	 * Obté la base de coneixement que s'identifica pel nom <code>nomBC</code>
	 * @param nomBC,nom de la base de coneixement que es vol obtenir 
	 * @return BaseConeixement
	 * @author Laia Riera Guerra
	 */
	public BaseConeixement obtenirBC(String nomBC){
		  int i=this.obtenirIndex(nomBC);
		  return this.obteBC(i);
	  }
	/**
	 * Indica si existeix la base de coneixement de nom <code>nomBC</code>
	 * @param nomBC, nom de la base de coneixement
	 * @return true, si la base de coneixement existeix , false altrament
	 * @author Laia Riera Guerra
	 */
	public boolean existeixBC(String nomBC){
		boolean b=false;
		int i=this.obtenirIndex(nomBC);
		if(i!=-1)b=true;
		return b;
	}
	/**
	 * Associa la LListaBC al nou GestorMatriu <code>m</code>
	 * @param m, nou GestorMatriu
	 * @author Laia Riera Guerra
	 */
	protected void setM(GestorMatriu m)throws Exception {
		this.m = m;
		for(int i=0;i<alNoms.size();i++){
			BaseConeixement bc=obtenirBC((String)alNoms.get(i));
			bc.setM(m);
		}
	  }
	  /**
	   * Obté un llistat que conté dos llistats. Un d'aquests conté els noms de les bases de coneixement
	   * que contenen propietats a les regles que no existeixen a la matriu de dades 
	   * L'altre llistat conté per cada posició un llistat amb els noms de les regles que contenten propietats que no existeixen a la matriu
	   * @param idMatriuPare, matriu sobre la que es vol comprovar
	   * @param propSelec, llistat de propietats de la matriu nova
	   * @return llistat amb els noms de les bases de coneixement i regles que contenen propietats que no estan a <code>propSelec</code>
	   * @throws Exception
	   * @author Laia Riera Guerra
	   */
	public ArrayList comprovarLlistaBC(String[] propSelec)throws Exception{
		ArrayList resultat=new ArrayList();
		for(int i=0;i<alNoms.size();i++){
			BaseConeixement bc=obtenirBC((String)alNoms.get(i));
			ArrayList al=bc.comprovarPropietatsRegles(propSelec);
			if(!al.isEmpty()){
				ArrayList resBC=new ArrayList(2);
				resBC.add(0,bc.getNomBC());
				resBC.add(1,al);
				resultat.add(resBC);
			}			
		}
		return resultat;
	}
}

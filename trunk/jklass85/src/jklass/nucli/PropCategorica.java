package jklass.nucli;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Classe que defineix tot el necessari per una propietat categòrica
 * De moment no es tenen en compte els pesos (fitxer.pro) pes=? freq
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas,Laia Riera Guerra
 * @version v.4
 */
public class PropCategorica extends Propietat {
  /**
   * Llista amb totes les modalitats possibles per la propietat categòrica.
   * A les modalitats es diferencia entre majúscules i minúscules.
   */
  protected ArrayList llModalitats;
  /**
   * Llista amb totes les modalitats ordenades
   */
    protected ArrayList llModsOrdre;
  /**
   * Informació estadística associada a la propietat categòrica per poder
   * per realitzar càlculs estadístics
   */
  protected EstadisticsCateg estadistics;

  /**
   * Constructor que inicialitza els atributs de la classe amb els valors indicats
   * als paràmetres
   * @param id identificador de la propietat
   * @param llistaModalitats llista de les modalitats possibles per la propietat
   */
  public PropCategorica(String id, ArrayList llistaModalitats) {
    super(id);
    llistaModalitats.trimToSize();
    llModalitats = llistaModalitats;
    llModsOrdre = llistaModalitats;
    estadistics = new EstadisticsCateg(llistaModalitats);
  }

  PropCategorica(String id){
    super(id);

    llModalitats = new ArrayList();
    llModsOrdre = new ArrayList();
    estadistics = new EstadisticsCateg();
  }
//DISTANCIES********************************************************************* pes
  /**
   * Actualitza la informació estadística associada a la propietat amb el valor
   * observat indicat al paràmetre
   * @param valor valor observat per la propietat
   * @param p conté el pes de l'objecte
   * @throws CreacioPropietatsException si el valor indicat no es correspon amb
   * cap de les modalitats vàlides per la propietat i no es un valor mancant
   */
  void actualitzar(String valor,float p) throws CreacioPropietatsException {
    if (valor.compareTo("?") == 0) {
      estadistics.incrementarMissings();
    } else {
      estadistics.incrementarFreq(valor,p);
    }
  }

  /**
   * Actualitza la informació estadística associada a la propietat amb el valor
   * observat indicat al paràmetre i la llista de modalitats si el valor no hi és
   * @param valor valor observat per la propietat
   * @throws CreacioPropietatsException
   */
  void actualitzarLliure(String valor) throws CreacioPropietatsException{
    if (valor.compareTo("?") == 0) {
      estadistics.incrementarMissings();
    } else {
      if (!llModalitats.contains(valor)) {
        // cal afegir la nova modalitat a la llista de modalitats i
        // a la llista que estableix l'ordre per defecte de les modalidtats
        llModalitats.add(valor);
        llModsOrdre.add(valor);
      }
      estadistics.incrementarFreqLliure(valor);
    }
  }

  void actualitzarOrdreMods(String[] llista) throws ParamIncorrecteException{
    int lon;

    lon = llista.length;
    if (llModalitats.size() == lon){
      llModsOrdre = new ArrayList();
      for (int i=0; i<lon; i++){
        llModsOrdre.add(llista[i]);
      }
    } else {
      throw new ParamIncorrecteException("Llista incompleta de modalitats.");
    }
  }
  /**
   * Obté la instància de <code> EstadisticsCateg</code> que permet obtenir tota
   * la informàcio estadística associada a la propietat
   * @return <code> EstadisticsCateg</code>
   */
  EstadisticsCateg obtenirEstadistics() {
    return estadistics;
  }
  //DISTANCIES*********************************************************************
  /** Fa un reset del valors de la propieta categorica
  *
  * @author Jose I Mateos
  * @version v.0 06/08/06
  */
  void reset(){
	  estadistics = new EstadisticsCateg(llModalitats);
  }
  
/////////////////////////////LAIA////////////////////////////
  /**
   * Obté la posició de la modalitat <code>modalitat</code> a la llista <code>llModsOrdre</code>
   * @return posició de la modalitat dins el llista d'ordre de modalitats (llModsOrdre)
   * @author Laia Riera Guerra
   */
  public int obtenirIndexModalitatOrdenada(String modalitat){
	  int resultat=-1;
	  boolean b=false; //indica si ja hem trobat la modalitat
	  for(int i=0;i<this.llModsOrdre.size()&& !b;i++){
		  String aux=(String)llModsOrdre.get(i);
		  if(aux.compareTo(modalitat)==0){
			  resultat=i;
			  b=true;
		  }
	  }
	  return resultat;
  }
/**
 * Obté el llistat de modalitats de la propietat
 * @return Llistat de modalitats
 * @author Laia Riera Guerra
 */
public ArrayList getLlModalitats() {
	return llModalitats;
}
/**
 * Posa el nou llistat de modalitat de la propietat actual com a <code>llModalitats</code>
 * @param llModalitats, nou llistat de modalitats de la propietat actual
 * @author Laia Riera Guerra
 */
public void setLlModalitats(ArrayList llModalitats) {
	this.llModalitats = llModalitats;
}
/**
 * Obté el llistat de modalitats amb ordre
 * @return <code>llModsOrdre</code>
 * @author Laia Riera Guerra
 */
public ArrayList getLlModsOrdre() {
	return llModsOrdre;
}
/**
 * Posa el nou llistat de modalitats amb ordre com a <code>llModsOrdre</code>
 * @param llModsOrdre, nou llistat de modalitats amb ordre
 * @author Laia Riera Guerra
 */
public void setLlModsOrdre(ArrayList llModsOrdre) {
	this.llModsOrdre = llModsOrdre;
}
/**
 * Obté els estadístics de la propietat
 * @return <code>estadistics</code>
 * @author Laia Riera Guerra
 */
public EstadisticsCateg getEstadistics() {
	return estadistics;
}
/**
 * Posa els nous estadístics de la propietat com a <code>estadistics</code>
 * @param estadistics, nous estadístics de la propietat
 * @author Laia Riera Guerra
 */
public void setEstadistics(EstadisticsCateg estadistics) {
	this.estadistics = estadistics;
}
}

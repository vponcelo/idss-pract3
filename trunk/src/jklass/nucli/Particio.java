package jklass.nucli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.1
 */
public class Particio {
  /**
   * nom de la var. de classe que genera aquesta particio
   */
  private String nom;
  /**
   * Llista amb totes les modalitats ordenades
   */
  private ArrayList llModsOrdre;
  /**
   * Llista d'indexos:
   * key = String de la modalitat;
   * value = index per la posició de la modalitat (array prop num auxiliar)
   */
  private Hashtable indexMods;
  /**
   * objectes corresponent a cada modalitat de la var. de classe que genera la particio
   */
  private Vector[] objs;

  /**
   *
   * @param nom
   * @param llistaMods
   */
  Particio(String nom, ArrayList llistaMods) {
    int i = 0, lon;
    Iterator it;
    String modal;

    this.nom = nom;
    llModsOrdre = llistaMods;
    lon = llistaMods.size();
    indexMods = new Hashtable();
    objs = new Vector[lon];
    it = llistaMods.iterator();
    while (it.hasNext()) {
      modal = it.next().toString();
      indexMods.put(modal, new Integer(i));
      objs[i] = new Vector();
      i++;
    }
  }

  /**
   *
   * @param valor
   * @param objecte
   */
  void afegirObjecte(String valor, String objecte){
    int i;

    if (valor.compareTo("?") != 0) {
      i = ( (Integer) indexMods.get(valor)).intValue();
      objs[i].add(objecte);
    }
  }

  /**
   *
   * @return
   */
  String[] obtenirModalitats(){
    String[] llista = new String[llModsOrdre.size()];
    llModsOrdre.toArray(llista);
    return llista;
  }

  /**
   *
   * @return
   */
  int obtenirNumModalitats(){
    return indexMods.size();
  }

  /**
   *
   * @param modal
   * @return
   */
  Vector obtenirObjs(String modal) {
    int i;

    i = ( (Integer) indexMods.get(modal)).intValue();
    return objs[i];
  }

  /**
   *
   * @return identificador de la variable de classe associada a la partició
   */
  String obtenirVar() {
    return nom;
  }

  void generaFitxerPar(String dir) throws IOException{
    FitxerPar fitxer;
    String str, mod;
    Enumeration llista;

    fitxer = new FitxerPar(dir + nom);
    fitxer.obrirPerEscriure(false);
    str = "(";
    llista = indexMods.keys();
    while (llista.hasMoreElements()) {
      mod = (String)llista.nextElement();
      str = str  + "("+ mod + " " +
          objs[ ( (Integer) indexMods.get(mod)).intValue()].toString().replaceAll(", ", " ").replace('[','(').replace(']',')') + ")";
    }
    str = str + ")";
    fitxer.escriureLin(str);
    fitxer.tancarEsc();
  }
}
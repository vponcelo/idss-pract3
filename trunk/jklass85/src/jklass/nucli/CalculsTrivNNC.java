package jklass.nucli;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.logging.*;


/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

public class CalculsTrivNNC extends CalculsTriv {
  private static Logger logger = Logger.getLogger(CalculsTrivNNC.class.getName());

  private PropNumerica[] propsN = null;
  private PropCategorica propC = null;
  private Hashtable indexModsC; // key = String de la modalitat
                              // value = index(Integer) per la posició de la modalitat (per arrays auxiliars)
  // Dades per les vars. X, Y (númeriques)
  private Dada[] dadesX, dadesY;
  // Dades de la var. Z traduint les categories als seus corresponent indexos
  // segons indexModsC
  private int[] dadesZ;

  public CalculsTrivNNC() {
    super();
    propsN = new PropNumerica[2];
    indexModsC = new Hashtable();
  }

  public void afegirProps(PropNumerica[] propsN, PropCategorica propC) {
    int i = 0, lon;
    Iterator it;
    String modal;

    this.propsN = (PropNumerica[]) propsN.clone();
    this.propC = propC;
    lon = propC.llModsOrdre.size();
    it = propC.llModsOrdre.iterator();
    while (it.hasNext()) {
      modal = it.next().toString();
      indexModsC.put(modal, new Integer(i));
      i++;
    }
  }

  public PropNumerica[] obtenirPropsNum() {
    return (PropNumerica[]) propsN.clone();
  }

  public PropCategorica obtenirPropsCat() {
    return propC;
  }

  public void afegirDadesX(Dada[] dades) {
    dadesX = (Dada[]) dades.clone();
  }

  public Dada[] obtenirDadesX() {
    return (Dada[]) dadesX.clone();
  }

  public void afegirDadesY(Dada[] dades) {
    dadesY = (Dada[]) dades.clone();
  }

  public Dada[] obtenirDadesY() {
    return (Dada[]) dadesY.clone();
  }

  public void afegirDadesZ(Dada[] dades) {
    int lon, i;
    Object ind;

    lon = dades.length;
    dadesZ = new int[lon];
    for (i = 0; i < lon; i++){
      String val = (String) dades[i].obtenirValor();
      ind = indexModsC.get(val);
      if (ind != null) {
        dadesZ[i] = ( (Integer) ind).intValue();
      }
      else {
        /**@todo decidir como decidimos el caso de missings '?' = ind nulo
         *
         */
      }
    }
  }

  public int[] obtenirDadesZIndexades() {
    return (int[])dadesZ.clone();
  }

  public Hashtable obtenirTaulaMods() {
    return  (Hashtable) indexModsC.clone();
  }

}
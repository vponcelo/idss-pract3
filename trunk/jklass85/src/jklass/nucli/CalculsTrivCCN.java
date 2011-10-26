package jklass.nucli;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

public class CalculsTrivCCN extends CalculsTriv {
  private ArrayList llModX; //llista ordenada de les modalitats d'X
  private Hashtable indexModsX; // key = String de la modalitat d'X
             // value = index per la posició de la modalitat X (array auxiliar)
  private ArrayList llModY; //llista ordenada de les modalitats d'Y
  private Hashtable indexModsY; // key = String de la modalitat d'Y
             // value = index per la posició de la modalitat Y (array auxiliar)

  private PropNumerica[][] auxProp = null;
  private Vector[][] auxDades;

  private int missingsX[]; //nombre de missings d'Y per cada modalitat d'X
  private int missingsY[]; // nombre de missings d'X per cada modalitat d'Y
  private int totalMissX; //  nombre total de missings per X
  private int totalMissY; // nombre total de missings per Y
  private int missingsXY; // per guardar el nombre d'objectes amb valor mancant d'X i també d'Y
  private int numObjs;

  public CalculsTrivCCN(PropCategorica propCX, PropCategorica propCY, PropNumerica propN) {
    int lonX, lonY, i, j;
    float max, min;
    Iterator it;
    String str;

    llModX = (ArrayList)propCX.llModsOrdre.clone();
    lonX = llModX.size();
    indexModsX = new Hashtable();
    missingsX = new int[lonX];
    i = 0;
    it = llModX.iterator();
    while (it.hasNext()) {
      str = it.next().toString();
      indexModsX.put(str, new Integer(i));
      missingsX[i] = 0;
      i++;
    }
    llModY = (ArrayList)propCY.llModsOrdre.clone();
    lonY = llModY.size();
    indexModsY = new Hashtable();
    missingsY = new int[lonY];
    i = 0;
    it = llModY.iterator();
    while (it.hasNext()) {
      str = it.next().toString();
      indexModsY.put(str, new Integer(i));
      missingsY[i] = 0;
      i++;
    }
    auxProp = new PropNumerica[lonX][lonY];
    auxDades = new Vector[lonX][lonY];
    str = propN.obtenirId();
    max = propN.obtenirRangMax();
    min = propN.obtenirRangMin();
    for (i = 0; i<lonX; i++){
      for (j = 0; j<lonY; j++){
        auxProp[i][j] = new PropNumerica(str+i+j, min, max);
        auxDades[i][j] = new Vector();
      }
    }

    missingsXY = 0;
    totalMissX = 0;
    totalMissY = 0;
    numObjs = 0;

  }

  public void afegirValor(String valorCategX, String valorCategY, String valorZ) throws CreacioPropietatsException{
    int i, j;

    numObjs++;
    if (valorCategY.compareTo("?") == 0) {
      if (valorCategX.compareTo("?") == 0) {
        missingsXY++;
        totalMissX++;
        totalMissY++;
      } else {
        i = ( (Integer) indexModsX.get(valorCategX)).intValue();
        missingsX[i]++;
        totalMissY++;
      }
    } else {
      j = ( (Integer) indexModsY.get(valorCategY)).intValue();
      if (valorCategX.compareTo("?") == 0) {
        missingsY[j]++;
        totalMissX++;
      } else {
        i = ( (Integer) indexModsX.get(valorCategX)).intValue();
        auxProp[i][j].actualitzar(valorZ,1);//Distancies pametre añadit per poder fer servir pesos
        if (valorZ.compareTo("?") != 0){
          auxDades[i][j].add(new Float(valorZ));
        }
      }
    }
  }

  public String[] obtenirModalitatsX(){
    String[] llista = new String[indexModsX.size()];
    llista =(String[])llModX.toArray(llista);
    return llista;
  }

  public int obtenirNumModalitatsX(){
    return indexModsX.size();
  }

  public String[] obtenirModalitatsY(){
    String[] llista = new String[indexModsY.size()];
    llista = (String[])llModY.toArray(llista);
    return llista;
  }

  public int obtenirNumModalitatsY(){
    return indexModsY.size();
  }

  public int obtenirNumObjs(){
    return numObjs;
  }

  public float obtenirMax(String modX, String modY){
    int i, j;

    i = ( (Integer) indexModsX.get(modX)).intValue();
    j = ( (Integer) indexModsY.get(modY)).intValue();

    return auxProp[i][j].obtenirEstadistics().obtenirMax();
  }

  public float obtenirMin(String modX, String modY){
    int i, j;

    i = ( (Integer) indexModsX.get(modX)).intValue();
    j = ( (Integer) indexModsY.get(modY)).intValue();

    return auxProp[i][j].obtenirEstadistics().obtenirMin();
  }

  public float obtenirAmp(String modX, String modY){
    int i, j;
    EstadisticsNum est;

    i = ( (Integer) indexModsX.get(modX)).intValue();
    j = ( (Integer) indexModsY.get(modY)).intValue();
    est = auxProp[i][j].obtenirEstadistics();
    return est.obtenirMax() - est.obtenirMin();
  }

  public int obtenirNumMissings(String modX, String modY){
    int i, j;

    i = ( (Integer) indexModsX.get(modX)).intValue();
    j = ( (Integer) indexModsY.get(modY)).intValue();

    return auxProp[i][j].obtenirEstadistics().obtenirNumMissings();
  }

  public int obtenirNumObjs(String modX, String modY){
    int i, j;

    i = ( (Integer) indexModsX.get(modX)).intValue();
    j = ( (Integer) indexModsY.get(modY)).intValue();

    return auxProp[i][j].obtenirEstadistics().obtenirNumObjs();
  }

  /**
   * sum/numObjs
   * @param modX
   * @param modY
   * @return
   */
  public float obtenirMitjana(String modX, String modY){
    int i, j;

    i = ( (Integer) indexModsX.get(modX)).intValue();
    j = ( (Integer) indexModsY.get(modY)).intValue();

    return auxProp[i][j].obtenirEstadistics().obtenirMitjana();

  }

  public float obtenirVariancia(String modX, String modY){
    int i, j;

    i = ( (Integer) indexModsX.get(modX)).intValue();
    j = ( (Integer) indexModsY.get(modY)).intValue();

    return auxProp[i][j].obtenirEstadistics().obtenirVariancia();
  }

  public float obtenirQuasiVariancia(String modX, String modY){
    int i, j;

    i = ( (Integer) indexModsX.get(modX)).intValue();
    j = ( (Integer) indexModsY.get(modY)).intValue();

    return auxProp[i][j].obtenirEstadistics().obtenirQuasiVariancia();

  }

  public float obtenirDesvTipica(String modX, String modY){
    int i, j;

    i = ( (Integer) indexModsX.get(modX)).intValue();
    j = ( (Integer) indexModsY.get(modY)).intValue();

    return auxProp[i][j].obtenirEstadistics().obtenirDesvTipica();
  }

  public float obtenirQuasiDesvTip(String modX, String modY){
    int i, j;

    i = ( (Integer) indexModsX.get(modX)).intValue();
    j = ( (Integer) indexModsY.get(modY)).intValue();

    return auxProp[i][j].obtenirEstadistics().obtenirQuasiDesvTip();

  }

  public float obtenirCoefVariacio(String modX, String modY){
    int i, j;

    i = ( (Integer) indexModsX.get(modX)).intValue();
    j = ( (Integer) indexModsY.get(modY)).intValue();

    return auxProp[i][j].obtenirEstadistics().obtenirCoefVariacio();
  }

  public float obtenirMe(String modX, String modY) {
    int i, j, n;
    Float[] dades;
    EstadisticsNum est;
    float[] res;

    i = ( (Integer) indexModsX.get(modX)).intValue();
    j = ( (Integer) indexModsY.get(modY)).intValue();
    est = auxProp[i][j].obtenirEstadistics();

    n = est.obtenirNumObjs() - est.obtenirNumMissings();
    if (n > 0) {
      dades = new Float[n];
      auxDades[i][j].toArray(dades);
      res = est.calcularQuartils(dades);
      return res[0];
    } else {
      return Float.NaN;
    }
  }

  public float obtenirQ1(String modX, String modY) {
    int i, j, n;
    Float[] dades;
    EstadisticsNum est;
    float[] res;

    i = ( (Integer) indexModsX.get(modX)).intValue();
    j = ( (Integer) indexModsY.get(modY)).intValue();
    est = auxProp[i][j].obtenirEstadistics();

    n = est.obtenirNumObjs() - est.obtenirNumMissings();
    if (n > 0) {
      dades = new Float[n];
      auxDades[i][j].toArray(dades);
      res = est.calcularQuartils(dades);
      return res[1];
    } else {
      return Float.NaN;
    }
  }

  public float  obtenirQ3(String modX, String modY) {
    int i, j, n;
    Float[] dades;
    EstadisticsNum est;
    float[] res;

    i = ( (Integer) indexModsX.get(modX)).intValue();
    j = ( (Integer) indexModsY.get(modY)).intValue();
    est = auxProp[i][j].obtenirEstadistics();

    n = est.obtenirNumObjs() - est.obtenirNumMissings();
    if (n > 0) {
      dades = new Float[n];
      auxDades[i][j].toArray(dades);
      res = est.calcularQuartils(dades);
      return res[2];
    }
    else {
      return Float.NaN;
    }
  }

  public float obtenirDistInterQ(String modX, String modY) {
    int i, j, n;
    Float[] dades;
    EstadisticsNum est;
    float[] res;

    i = ( (Integer) indexModsX.get(modX)).intValue();
    j = ( (Integer) indexModsY.get(modY)).intValue();
    est = auxProp[i][j].obtenirEstadistics();

    n = est.obtenirNumObjs() - est.obtenirNumMissings();
    if (n > 0) {
      dades = new Float[n];
      auxDades[i][j].toArray(dades);
      res = est.calcularQuartils(dades);
      return res[2] - res[1];
    }
    else {
      return Float.NaN;
    }
  }

}
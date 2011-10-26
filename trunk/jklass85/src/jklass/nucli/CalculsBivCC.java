package jklass.nucli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
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

public class CalculsBivCC extends CalculsBiv {

  private ArrayList llModY; //llista ordenada de les modalitats d'Y
  private Hashtable indexModsY; // key = String de la modalitat d'Y
                               // value = index per la posició de la modalitat Y (array props auxiliar)
  private PropCategorica[] propsAux; // propietats categoritzades (cada fila es una modalitat d'Y)

  private Vector[] dadesAux; // dades corresponent a cadascuna de les propietats auxiliars (1 per cada modalitat d'Y)

  private CalculsUnivCateg[] calculsUC; // calculs estadistics per cada propietat categoritzada (cada fila es una modalitat d'Y)
  private ArrayList llModX; //llista ordenada de les modalitats d'X
  private Hashtable indexModsX; // key = String de la modalitat d'X
                               // value = index per la posició de la modalitat X (array missingsX)
  // missings d'X per cada modalitat d'Y els tinc a les propietats auxiliars
  private int missingsX[]; //nombre de missings d'Y per cada modalitat d'X
  private int totalsX[]; // total per cada modalitat d'X
  private int totalMissX; //  nombre total de missings per X
  private int totalMissY; // nombre total de missings per Y
  private int missingsXY; // per guardar el nombre d'objectes amb valor mancant d'X i també d'Y
  private float maxFreq, maxRel;
  private int numObjs;

  public CalculsBivCC(ArrayList llistaModX, ArrayList llistaModY) {
    super();

    int i, lon;
    Iterator it;
    String modal;
    maxFreq = 0;
    maxRel = 0;
    numObjs = 0;
    llModY = (ArrayList)llistaModY.clone();
    lon = llistaModY.size();
    indexModsY = new Hashtable();
    propsAux = new PropCategorica[lon];
    dadesAux = new Vector[lon];
    calculsUC = new CalculsUnivCateg[lon];
    i = 0;
    it = llistaModY.iterator();
    while (it.hasNext()) {
      modal = (String)it.next();
      indexModsY.put(modal, new Integer(i));
      propsAux[i] = new PropCategorica(modal, llistaModX);
      dadesAux[i] = new Vector();
      i++;
    }
    llModX = (ArrayList)llistaModX.clone();
    lon = llistaModX.size();
    indexModsX = new Hashtable();
    missingsX = new int[lon];
    totalsX = new int[lon];
    i = 0;
    it = llistaModX.iterator();
    while (it.hasNext()) {
      modal = (String)it.next();
      indexModsX.put(modal, new Integer(i));
      missingsX[i] = 0;
      totalsX[i] = 0;
      i++;
    }
    missingsXY = 0;
    totalMissX = 0;
    totalMissY = 0;
  }

  public void afegirValor(String valorCategX, String valorCategY) throws CreacioPropietatsException{
    int i;
    numObjs++;
    if (valorCategY.compareTo("?") == 0) {
      if (valorCategX.compareTo("?") == 0) {
        missingsXY++;
        totalMissX++;
        totalMissY++;
      } else {
        i = ( (Integer) indexModsX.get(valorCategX)).intValue();
        missingsX[i]++;
        totalsX[i]++;
        totalMissY++;
      }
    } else {
      i = ( (Integer) indexModsY.get(valorCategY)).intValue();
      propsAux[i].actualitzar(valorCategX,1);//Distancies pametre añadit per poder fer servir pesos
      dadesAux[i].add(valorCategX);
      if (valorCategX.compareTo("?") == 0) {
        totalMissX++;
      }
      else {
        i = ( (Integer) indexModsX.get(valorCategX)).intValue();
        totalsX[i]++;
      }
    }
  }

  public void ferCalculsUniv(){
    int i, j, k, l, est, lon;
    boolean ok;
    CalculsUnivCateg calcCateg;
    int pos, iNum1, iNum2, n;
    float fNum1, fNum2, dada, pas;
    double dNum;
    String[] llistaMods;
    PropCategorica proCatX, proCatY;
    EstadisticsCateg estadsCX, estadsCY;
    float dades[], resultats[];
    int[] percentiles = new int[3];
    Iterator llistaModal;
    String modal;
    float freq, acum, rel, relAcum;
    Hashtable taulaOpc;

    llistaMods = obtenirModalitatsY();
    i = 0;
    while (i < llistaMods.length) {
      modal = llistaMods[i];
      pos = ( (Integer) indexModsY.get(modal)).intValue();
      proCatX = (PropCategorica) propsAux[pos];
      j = 0;
      calculsUC[pos] = new CalculsUnivCateg();
      calcCateg = (CalculsUnivCateg) calculsUC[pos];
      estadsCX = proCatX.obtenirEstadistics();
      dades = null;
      calcCateg.afegirMaxFreq(estadsCX.obtenirMaxFreq());
      iNum1 = proCatX.llModsOrdre.size();
      calcCateg.inicialitzarLlistaModals(iNum1);
      calcCateg.inicialitzarLlistaFreq(iNum1);
      calcCateg.inicialitzarLlistaRel(iNum1);
      llistaModal = proCatX.llModsOrdre.iterator();
      iNum1 = estadsCX.obtenirNumObjs();
      calcCateg.afegirNumObjs(iNum1);
      iNum2 = iNum1 - estadsCX.obtenirNumMissings();
      calcCateg.afegirNumUtils(iNum2);
      iNum1 = estadsCX.obtenirNumMissings();
      calcCateg.afegirNumMancants(iNum1);
      totalMissX = totalMissX + iNum1;
      k = 0;
      while (llistaModal.hasNext()) {
        modal = llistaModal.next().toString();
        calcCateg.afegirALlistaModals(k, modal);
        freq = estadsCX.obtenirFreq(modal);
        calcCateg.afegirALlistaFreq(k, freq);
        calcCateg.afegirALlistaRel(k, (freq / iNum2));
        k++;
      }
      freq = calcCateg.obtenirMaxFreq();
      if (freq > maxFreq) {
        maxFreq = freq;
      }
      freq = freq / iNum2;
      if (freq > maxRel) {
        maxRel = freq;
      }
      i++;
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

  public CalculsUnivCateg obtenirCalculsUniv(String modal){
    int i;

    i = ((Integer)indexModsY.get(modal)).intValue();
    return calculsUC[i];
  }

  public float obtenirMaxFreq(){
    return maxFreq;
  }

  public float obtenirMaxRel(){
    return maxRel;
  }

  public int obtenirMancantsX(String modal) {
    return  missingsX[((Integer) indexModsX.get(modal)).intValue()];
  }

  public int obtenirTotalX(String modal) {
    return  totalsX[((Integer) indexModsX.get(modal)).intValue()];
  }

  public int obtenirTotalMancantsX(){
  return totalMissX;
  }

  public int obtenirMancantsY(String modal) {
    return propsAux[ ( (Integer) indexModsY.get(modal)).intValue()].
        obtenirEstadistics().obtenirNumMissings();
  }

  public int obtenirTotalMancantsY(){
    return totalMissY;
  }

  public int obtenirMancantsXY(){
    return missingsXY;
  }

  public int obtenirNumObjs(){
    return numObjs;
  }

  public int obtenirNumObjsY(String mod){
    int i;

    i = ((Integer)indexModsY.get(mod)).intValue();
    return propsAux[i].obtenirEstadistics().obtenirNumObjs();
  }

}
package jklass.nucli;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import jklass.util.Opcions;

public class CalculsBivNC extends CalculsBiv {
  // Per Boxplot Multiple
  private ArrayList llMods;
  private Hashtable indexMods; // key = String de la modalitat
                              // value = index per la posició de la modalitat (array prop num auxiliar)
  private PropNumerica[] propsAux; // propietats categoritzades
  private Vector[] dadesAux; // dades corresponent a cadacuna de les propietats auxiliars
  private CalculsUnivNum[] calculsUN;
  private float min, max, minFreq, maxFreq, maxRel;
  private int numObjs;

  public CalculsBivNC(ArrayList llistaMods, float minTeo, float maxTeo, float minReal, float maxReal) {
    super();

    int i = 0, lon;
    Iterator it;
    String modal;

    min = minReal;
    max = maxReal;
    minFreq = 0;
    maxFreq = 0;
    maxRel = 0;
    numObjs = 0;
    llMods = (ArrayList)llistaMods.clone();
    lon = llistaMods.size();
    indexMods = new Hashtable();
    propsAux = new PropNumerica[lon];
    dadesAux = new Vector[lon];
    calculsUN = new CalculsUnivNum[lon];
    it = llistaMods.iterator();
    while (it.hasNext()) {
      modal = it.next().toString();
      indexMods.put(modal, new Integer(i));
      propsAux[i] = new PropNumerica(modal, minTeo, maxTeo);
      dadesAux[i] = new Vector();
      i++;
    }
  }

  public void afegirValor(String valorNum, String valorCateg) throws CreacioPropietatsException{
    int i;

    numObjs++;
    if (valorCateg.compareTo("?") != 0) {
      i = ( (Integer) indexMods.get(valorCateg)).intValue();
      propsAux[i].actualitzar(valorNum,1);//Distancies pametre añadit per poder fer servir pesos
      dadesAux[i].add(valorNum);
    }
  }

  public void ferCalculsUniv(Vector llistaEstads, Opcions opc){
    int i, j, k, l, est, lon;
    boolean ok;
    CalculsUnivNum calcNum;
    int pos, iNum1, iNum2, n;
    float fNum1, fNum2, dada = Float.NaN, pas, maxim;
    double dNum;
    String[] llistaMods;
    PropNumerica proNum;
    EstadisticsNum estadsN;
    float dades[], resultats[];
    int[] percentiles = new int[3];
    PropCategorica proCat;
    EstadisticsCateg estadsC;
    Iterator llistaModal;
    String modal;
    float freq, acum, rel, relAcum;
    Hashtable taulaOpc;

    llistaMods = obtenirModalitats();
    i = 0;
    while (i < llistaMods.length){
      modal = llistaMods[i];
      pos = ((Integer) indexMods.get(modal)).intValue();
      proNum = (PropNumerica) propsAux[pos];
      j = 0;
      calculsUN[pos] = new CalculsUnivNum();
      calcNum = (CalculsUnivNum) calculsUN[pos];
      estadsN = proNum.obtenirEstadistics();
      dades = null;
      while (j < llistaEstads.size()) {
        est = ( (Integer) llistaEstads.get(j)).intValue();
        switch (est) {
          case Opcions.DESCR_GR: case Opcions.DESCR_CLASS:
            calcNum.afegirCalcRealitzat(est);
            iNum1 = estadsN.obtenirNumObjs();
            calcNum.afegirNumObjs(iNum1);
            iNum2 = estadsN.obtenirNumMissings();
            calcNum.afegirNumMancants(iNum2);
            calcNum.afegirNumUtils(iNum1 - iNum2);
            fNum1 = estadsN.obtenirMin();
            calcNum.afegirMin(fNum1);
            fNum2 = estadsN.obtenirMax();
            calcNum.afegirMax(fNum2);
            calcNum.afegirAmplitud(fNum2 - fNum1);
            calcNum.afegirMitjana(estadsN.obtenirMitjana());
            calcNum.afegirVar(estadsN.obtenirVariancia());
            calcNum.afegirQuasiVar(estadsN.obtenirQuasiVariancia());
            calcNum.afegirDesv(estadsN.obtenirDesvTipica());
            calcNum.afegirQuasiDesv(estadsN.obtenirQuasiDesvTip());
            calcNum.afegirCoefCorr(estadsN.obtenirCoefVariacio());
            // Estadistics Robustos
            if (dades == null) {
              // recuperem les dades necessàries si no ho em fet abans per algun altre estadistic
              dades = recuperarDades(pos);
            }
            calcNum.afegirDades(dades);
            resultats = estadsN.calcularQuartils(dades);
            calcNum.afegirMediana(resultats[0]);
            calcNum.afegirQ1(resultats[1]);
            calcNum.afegirQ3(resultats[2]);
            calcNum.afegirDistInterQ(resultats[2] - resultats[1]);
            break;
          case Opcions.HISTO_MULT:
           calcNum.afegirCalcRealitzat(est);
            if (! (calcNum.calculRealitzat(Opcions.DESCR_GR)) && ! (calcNum.calculRealitzat(Opcions.DESCR_CLASS))) {
              iNum1 = estadsN.obtenirNumObjs();
              calcNum.afegirNumObjs(iNum1);
              iNum2 = estadsN.obtenirNumMissings();
              calcNum.afegirNumMancants(iNum2);
              calcNum.afegirNumUtils(iNum1 - iNum2);
              fNum1 = estadsN.obtenirMin();
              calcNum.afegirMin(fNum1);
              fNum2 = estadsN.obtenirMax();
              calcNum.afegirMax(fNum2);
            }
            fNum1 = min;
            fNum2 = max;
            // Cal fer servir els min. i max teorics per la variable
            // perque # classes es calculen sobre tot el rang de valors possibles
            taulaOpc = opc.obtenirOpcionsEstad(Opcions.HISTO_MULT);
            if ( ( (String) taulaOpc.get("limitsX")).equals("teoric")) {
              fNum1 = proNum.obtenirRangMin();
              fNum2 = proNum.obtenirRangMax();
              taulaOpc.put("minXT",
                           (new Float(fNum1)).toString());
              taulaOpc.put("maxXT",
                           (new Float(fNum2)).toString());
            }  else if (((String) taulaOpc.get("limitsX")).equals("def")){
              fNum1 = Float.parseFloat((String)taulaOpc.get("minX"));
              fNum2 = Float.parseFloat((String)taulaOpc.get("maxX"));
            }

            if (dades == null) {
              // es recuperen les dades necessàries si no ho em fet abans per algun altre estadistic
              dades = recuperarDades(pos);
            }
            // s'ordenen les dades de forma ascendent
            Arrays.sort(dades);
            //n = dades.length;
            n = numObjs; //agafem el nombre gral de objectes per tenir les mateixes classes
            // es determina el nombre de classes de freqüència
            if ( ( (String) taulaOpc.get("classes")).equals("auto")) {
              if (n < 100) {
                dNum = Math.floor(6 * (Math.log(n) / Math.log(10)));
              }
              else {
                dNum = Math.floor( (1.2 * Math.sqrt(n)));
              }
              iNum1 = new Double(dNum).intValue(); //nombre de classes
            }
            else { // el nombre de classes vé definit per l'usuari
              iNum1 = Integer.parseInt( (String) taulaOpc.get("numclass"));
            }
            calcNum.inicialitzarLlistaRang(iNum1);
            calcNum.inicialitzarLlistaFreq(iNum1);
            pas = (fNum2 - fNum1) / iNum1;
            n = dades.length;
            l = 0;
            k = 0;
            maxim = fNum2;
            if (n > 0) dada = dades[k];
            fNum2 = fNum1 + pas;
            ok = true;
            while (l < iNum1) {
              iNum2 = 0;
              while (ok && (dada >= fNum1) && (dada < fNum2)) {
                iNum2++;
                if (k < n - 1) {
                  k++;
                  dada = dades[k];
                }
                else {
                  ok = false;
                }
              }
              // tractament especial de l'ultima classe
              // per incloure els valors iguals al maxim
              if (l == (iNum1 - 1)) {
                if (fNum2 < maxim) fNum2 = maxim;
                while (ok && (dada == fNum2)) {
                  iNum2++;
                  if (k < n - 1) {
                    k++;
                    dada = dades[k];
                  }
                  else {
                    ok = false;
                  }
                }
              }
              calcNum.afegirALlistaRang(l, fNum1, fNum2);
              calcNum.afegirALlistaFreq(l, (float) iNum2);
              l++;
              fNum1 = fNum2;
              fNum2 = fNum2 + pas;
            }
            fNum1 = calcNum.obtenirMaxFreq();
            if ( fNum1 > maxFreq ) {
              maxFreq = fNum1;
            }
            fNum1 = fNum1 / calcNum.obtenirNumUtils();
            if (fNum1 > maxRel) {
              maxRel = fNum1;
            }
            fNum1 = calcNum.obtenirMinFreq();
            if ( (minFreq == 0) || (fNum1 < minFreq) ) minFreq = fNum1;
            break;
          case Opcions.BOXP_MULT:
            calcNum.afegirCalcRealitzat(est);
            taulaOpc = opc.obtenirOpcionsEstad(Opcions.BOXP_MULT);
            if ( ( (String) taulaOpc.get("limits")).equals("teoric")) {
              taulaOpc.put("minT",
                           (new Float(proNum.obtenirRangMin())).toString());
              taulaOpc.put("maxT",
                           (new Float(proNum.obtenirRangMax())).toString());
            }
            if (! (calcNum.calculRealitzat(Opcions.DESCR_GR)) &&
                ! (calcNum.calculRealitzat(Opcions.DESCR_CLASS))) {
              iNum1 = estadsN.obtenirNumObjs();
              calcNum.afegirNumObjs(iNum1);
              iNum2 = estadsN.obtenirNumMissings();
              calcNum.afegirNumMancants(iNum2);
              calcNum.afegirNumUtils(iNum1 - iNum2);
              fNum1 = estadsN.obtenirMin();
              calcNum.afegirMin(fNum1);
              fNum2 = estadsN.obtenirMax();
              calcNum.afegirMax(fNum2);
              // Estadistics Robustos
              if (dades == null) {
                // recuperem les dades necessàries si no ho em fet abans per algun altre estadistic
                dades = recuperarDades(pos);
              }
              calcNum.afegirDades(dades);
              resultats = estadsN.calcularQuartils(dades);
              calcNum.afegirMediana(resultats[0]);
              calcNum.afegirQ1(resultats[1]);
              calcNum.afegirQ3(resultats[2]);
              calcNum.afegirDistInterQ(resultats[2] - resultats[1]);
            }
            break;
          default:
            //logger.warning("Operació no vàlida");
            break;
        }
        j++;
      }
      i++;
    }
  }

  public String[] obtenirModalitats(){
    String[] llista = new String[indexMods.size()];

    llista = (String[])llMods.toArray(llista);
    return llista;
  }

  public int obtenirNumModalitats(){
    return indexMods.size();
  }

  public CalculsUnivNum obtenirCalculsUniv(String modal){
    int i;

    i = ((Integer)indexMods.get(modal)).intValue();
    return calculsUN[i];
  }

  public float obtenirMin() {
    return min;
  }

  public float obtenirMax(){
    return max;
  }

  public float obtenirMinFreq() {
    return minFreq;
  }

  public float obtenirMaxFreq(){
    return maxFreq;
  }

  public float obtenirMaxRel(){
    return maxRel;
  }

  public int obtenirNumObjs(){
    return numObjs;
  }

  public int obtenirNumObjs(String mod){
    int i;

    i = ((Integer)indexMods.get(mod)).intValue();
    return propsAux[i].obtenirEstadistics().obtenirNumObjs();
  }

  public int obtenirNumUtil(String mod) {
    int i;

    i = ( (Integer) indexMods.get(mod)).intValue();
    return (propsAux[i].obtenirEstadistics().obtenirNumObjs() - propsAux[i].obtenirEstadistics().obtenirNumMissings());
  }


  private float[] recuperarDades(int i) {

    int j,k;
    float dades[];
    String val;

    // mida = num.objs útils
    dades = new float[propsAux[i].obtenirEstadistics().obtenirNumObjs() - propsAux[i].obtenirEstadistics().obtenirNumMissings()];
    j=0;
    for (k = 0; k < dadesAux[i].size(); k++) {
      val = (String) dadesAux[i].get(k);
      if (val.compareTo("?") != 0) {
        dades[j] = Float.parseFloat(val);
        j++;
      }
    }
    return dades;
  }

}
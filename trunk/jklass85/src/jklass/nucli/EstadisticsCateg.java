package jklass.nucli;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.*;

/**
 * Classe que permet gestionar la informació necessaria associada a una
 * propietat categòrica per fer calculs estadístics.
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */
public class EstadisticsCateg {
  protected static Logger logger = Logger.getLogger(EstadisticsCateg.class.getName());
  /**
   * Nombre de dades mancants associat a la classe
   */
  private int numMissings;
  /**
   * Nombre total d'objectes associat a la classe
   */
  private int numObjs;
  /**
   * Taula de freqüències absolutes per les modalitats de la propietat
   * categòrica, on:
   * key = String de la modalitat;
   * value = Float amb la freq
   */
  private Hashtable taulaFreqs;

  //DISTANCIES*********************************************************************
  /**
   * Taula de freqüències absolutes per les modalitats de la propietat
   * categòrica, on:
   * key = String de la modalitat;
   * value = Float amb la freq
   */
  private Hashtable taulaFreqsPes;

  /**
   * Freqüència absoluta màxima observada de totes les que conté <code> taulaFreqs </code>
   */
  private int maxFreq;
  /**
   * Numero de modalitats de la propietat
   */
  private int numMod;

  /**
   * Constructor
   * @param llistaMod llista de Strings de les modalitats possibles
   */
  public EstadisticsCateg(ArrayList llistaMod) {
    numMissings = 0;
    numObjs = 0;
    numMod = llistaMod.size();
    taulaFreqs = new Hashtable(numMod);
    //DISTANCIES*********************************************************************
    taulaFreqsPes = new Hashtable(numMod);
    for (int i=0; i < numMod; i++){
      taulaFreqs.put( (String) llistaMod.get(i), new Integer(0));
      //DISTANCIES*********************************************************************
      taulaFreqsPes.put( (String) llistaMod.get(i), new Integer(0));

    }
    ;
  }

  /**
     * Constructor
     */

  EstadisticsCateg() {
    numMissings = 0;
    numObjs = 0;
    taulaFreqs = new Hashtable();
    //DISTANCIES*********************************************************************
    taulaFreqsPes = new Hashtable();
  }
  /**
   * Incrementa en 1 unitat el nombre de dades mancants i també les nombre
   * total d'objectes.
   */
  void incrementarMissings() {
    numMissings++;
    numObjs ++;
  }

    /**
   * Incrementa en 1 unitat la freqüencia absoluta de la modalitat indicicada, i
   * també el nombre total d'objectes. Increment de la freqüencia pot fer que
   * canviï el valor de la frequència màxima observada
   * @param modal String de la modalitat a incrementar.
   * @param p conté el pes de l'objecte
   * @throws CreacioPropietatsException si la modalitat indicada no és una de
   * les indicades com possibles.
   */

  public void incrementarFreq(String modal,float p) throws CreacioPropietatsException{
    int freq;
    //DISTANCIES*********************************************************************
    int freqPes;
    Object obj;
    //DISTANCIES*********************************************************************
    Object objPes;

    numObjs ++;
    obj = taulaFreqs.get(modal);
    if ( obj == null){
      throw new CreacioPropietatsException("No existeix la modalitat d'un objecte a la definició de la propietat: " + modal);
    } else{
      freq = ((Integer)obj).intValue() + 1;
      if (freq > maxFreq) maxFreq = freq;
      taulaFreqs.put(modal, new Integer(freq));
    }
    //DISTANCIES*********************************************************************
    objPes = taulaFreqsPes.get(modal);
    if ( objPes == null){
      throw new CreacioPropietatsException("No existeix la modalitat d'un objecte a la definició de la propietat: " + modal);
    } else{
      freqPes = ((Integer)objPes).intValue() + (int)p;
      taulaFreqsPes.put(modal, new Integer(freqPes));
      //*********************************************************************
    }
  }

  /**
   * Incrementa en 1 unitat la freqüencia absoluta de la modalitat indicicada, si la modalitat no existeix la afegeix, i
   * també el nombre total d'objectes. Increment de la freqüencia pot fer que
   * canviï el valor de la frequència màxima observada
   * @param modal String de la modalitat a incrementar.
   */
  public void incrementarFreqLliure(String modal){
    int freq;
    Object obj;

    numObjs ++;
    obj = taulaFreqs.get(modal);
    if (obj == null) {
      freq = 1;
    }
    else {
      freq = ( (Integer) obj).intValue() + 1;
    }
    if (freq > maxFreq) maxFreq = freq;
    taulaFreqs.put(modal, new Integer(freq));
  }


  /**
   * Obté el nombre de dades mancants associat
   * @return nombre de dades mancants
   */
  public int obtenirNumMissings(){
    return numMissings;
  }

  /**
   * Obté el nombre total d'objectes associat
   * @return nombre total d'objectes
   */
  public int obtenirNumObjs(){
    return numObjs;
  }

  /**
   * Obté la freqüència màxima observada entre totes les modalitats
   * @return freqüència màxima observada
   */
  public int obtenirMaxFreq(){
    return maxFreq;
  }

  /**
   * Obté la freqüència absoluta associada a la modalitat indicada
   * @param modal String de la modalitat
   * @return  freqüència absoluta
   */
  public int obtenirFreq(String modal){
     int freq;
//System.out.println("entro a obtenirFreq");
//System.out.println("imprimo modal"+modal);
     freq = ((Integer)taulaFreqs.get(modal)).intValue();
//	System.out.println("no salio de obtenirFreq");  
     return freq;
  }
  //DISTANCIES*********************************************************************
  /**
     * Obté la freqüència absoluta amb pes associada a la modalitat indicada
     * @param modal String de la modalitat
     * @return  freqüència absoluta
      * @author Jose I Mateos
  * @version v.0 06/08/06
     */

  public int obtenirFreqPes(String modal){
     int freq;

     freq = ((Integer)taulaFreqsPes.get(modal)).intValue();
     return freq;
  }
  //*********************************************************************





  /** Obté el numero de modalitats de la propietat
  * @return  numero de modalitats
  *
  * @author Jose I Mateos
  * @version v.0 06/08/06
  */
  public int obtenirMods(){
     return numMod;
  }

}

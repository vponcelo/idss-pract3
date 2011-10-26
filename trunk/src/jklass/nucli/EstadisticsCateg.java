package jklass.nucli;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.*;

/**
 * Classe que permet gestionar la informaci� necessaria associada a una
 * propietat categ�rica per fer calculs estad�stics.
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estad�stic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author M� del Mar Colillas
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
   * Taula de freq��ncies absolutes per les modalitats de la propietat
   * categ�rica, on:
   * key = String de la modalitat;
   * value = Float amb la freq
   */
  private Hashtable taulaFreqs;

  //DISTANCIES*********************************************************************
  /**
   * Taula de freq��ncies absolutes per les modalitats de la propietat
   * categ�rica, on:
   * key = String de la modalitat;
   * value = Float amb la freq
   */
  private Hashtable taulaFreqsPes;

  /**
   * Freq��ncia absoluta m�xima observada de totes les que cont� <code> taulaFreqs </code>
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
   * Incrementa en 1 unitat el nombre de dades mancants i tamb� les nombre
   * total d'objectes.
   */
  void incrementarMissings() {
    numMissings++;
    numObjs ++;
  }

    /**
   * Incrementa en 1 unitat la freq�encia absoluta de la modalitat indicicada, i
   * tamb� el nombre total d'objectes. Increment de la freq�encia pot fer que
   * canvi� el valor de la frequ�ncia m�xima observada
   * @param modal String de la modalitat a incrementar.
   * @param p cont� el pes de l'objecte
   * @throws CreacioPropietatsException si la modalitat indicada no �s una de
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
      throw new CreacioPropietatsException("No existeix la modalitat d'un objecte a la definici� de la propietat: " + modal);
    } else{
      freq = ((Integer)obj).intValue() + 1;
      if (freq > maxFreq) maxFreq = freq;
      taulaFreqs.put(modal, new Integer(freq));
    }
    //DISTANCIES*********************************************************************
    objPes = taulaFreqsPes.get(modal);
    if ( objPes == null){
      throw new CreacioPropietatsException("No existeix la modalitat d'un objecte a la definici� de la propietat: " + modal);
    } else{
      freqPes = ((Integer)objPes).intValue() + (int)p;
      taulaFreqsPes.put(modal, new Integer(freqPes));
      //*********************************************************************
    }
  }

  /**
   * Incrementa en 1 unitat la freq�encia absoluta de la modalitat indicicada, si la modalitat no existeix la afegeix, i
   * tamb� el nombre total d'objectes. Increment de la freq�encia pot fer que
   * canvi� el valor de la frequ�ncia m�xima observada
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
   * Obt� el nombre de dades mancants associat
   * @return nombre de dades mancants
   */
  public int obtenirNumMissings(){
    return numMissings;
  }

  /**
   * Obt� el nombre total d'objectes associat
   * @return nombre total d'objectes
   */
  public int obtenirNumObjs(){
    return numObjs;
  }

  /**
   * Obt� la freq��ncia m�xima observada entre totes les modalitats
   * @return freq��ncia m�xima observada
   */
  public int obtenirMaxFreq(){
    return maxFreq;
  }

  /**
   * Obt� la freq��ncia absoluta associada a la modalitat indicada
   * @param modal String de la modalitat
   * @return  freq��ncia absoluta
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
     * Obt� la freq��ncia absoluta amb pes associada a la modalitat indicada
     * @param modal String de la modalitat
     * @return  freq��ncia absoluta
      * @author Jose I Mateos
  * @version v.0 06/08/06
     */

  public int obtenirFreqPes(String modal){
     int freq;

     freq = ((Integer)taulaFreqsPes.get(modal)).intValue();
     return freq;
  }
  //*********************************************************************





  /** Obt� el numero de modalitats de la propietat
  * @return  numero de modalitats
  *
  * @author Jose I Mateos
  * @version v.0 06/08/06
  */
  public int obtenirMods(){
     return numMod;
  }

}

package jklass.nucli;

import java.lang.Float;
import java.util.logging.*;

// import necessari pel calcul de la mediana
import java.util.*;

/**
 * Classe que permet gestionar la informaci� necessaria associada a una
 * propietat num�rica per fer calculs estad�stics.
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estad�stic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author M� del Mar Colillas
 * @version v.0
 */
public class EstadisticsNum {
  protected static Logger logger = Logger.getLogger(EstadisticsNum.class.getName());
   /**
    * Nombre de dades mancants associat a la classe
    */
   private int numMissings;
   /**
    * Nombre total d'objectes associat a la classe
    */
   private int numObjs;
   /**
    * Valor m�xim observat
    */
  private float max;
  /**
   * Valor m�nim observat
   */
  private float min;
  /**
   * Sumatori dels valors
   */
  private float sum;
  /**
   * Sumatori dels quadrats de cada valors
   */
  private float sumQuadrats;


  //Distancies**********************************pes
  /**
    * Nombre total del pes dels objectes associats a la classe
    */


  private int pes;
  /**
  * Suma total del  pes * valor dels objectes associat a la classe
  */

  private float sumPes;
  /**
  * Sumatori dels quadrats del pes * valor
  */


  private float sumQuadratsPes;


  /**
   * Constructor que inicialitza tots els atributs de la classe
   */
  public EstadisticsNum() {
    max = Float.NaN;
    min = Float.NaN;
    numMissings = 0;
    sum = 0;
    sumQuadrats = 0;
    numObjs = 0;
    pes=0;
    sumPes=0;
    sumQuadratsPes=0;
  }

  /**
   * Incrementa en 1 unitat el nombre de dades mancants i tamb� les nombre
   * total d'objectes.
   */
  public void incrementarMissings() {
    numMissings++;
    numObjs++;
  }

  //DISTANCIES*********************************************************************el pes
  /**
   * Actualitza tots els atributs necessaris afegint el valor indicat: si �s un
   * valor mancant s'actualitzar� <code> numMissings</code> i <code> numObjs
   * </code>; en cas contrari, en lloc d'actualitzar <code> numMissings</code>,
   * s'incrementa <code> sum</code> i <code> sumQuadrats</code> i si cal
   * s'actualitza el m�xim i m�nim observat.
   * @param valor valor observat que cal afegir pels calculs estad�stics
   * @param p cont� el pes de l'objecte
   */
  public void actualitzar(float valor,float p) {
    if (Float.compare(max, Float.NaN) == 0)
      max = valor;
    //logger.finer("Condici�n =" + (max ==Float.NaN) + " Max = " + max);
    if ( Float.compare(min,Float.NaN)==0 ) min = valor;
    //logger.finer("Condici�n =" + (min ==Float.NaN) + " min = " + min);
    if ( valor > max ) max = valor;
    if ( valor < min ) min = valor;
    sum += valor;
    sumPes+=valor*p;
    sumQuadrats += Math.pow(valor,2);
    sumQuadratsPes += p*Math.pow(valor,2);
    numObjs ++;
    pes+=p;

  }

  /**
   * Obt� el m�xim valor observat
   * @return m�xim valor observat
   */
  public float obtenirMax(){
    return max;
  }

  /**
   * Obt� el m�nim valor observat
   * @return m�nim valor observat
   */
  public float obtenirMin(){
    return min;
  }

  /**
   * Obt� el nombre de dades mancants
   * @return nombre de dades mancants
   */
  public int obtenirNumMissings() {
    return numMissings;
  }

  /**
   * Obt� el nombre total d'objectes
   * @return nombre total d'objectes
   */
  public int obtenirNumObjs() {
    return numObjs;
  }

  /**
   * Obt� el sumatori de tots els valors observats
   * @return sumatori dels valors observats
   */
  public float obtenirSum(){
    return sum;
  }

  /**
   * Obt� el sumatori dels quadrats de tots els valors observats
   * @return sumatori dels quadrats dels valors observats
   */
  public float obtenirSumQuadrats(){
    return sumQuadrats;
  }

  /**
   * Obt� la mitjana de tots els valors observats (<code>sum/numObjs</code>)
   * @return mitjana
   */
  public float obtenirMitjana() {
    return sum/(numObjs-numMissings);
  }

  /**
   * Obt� la vari�ncia de tots els valors observats
   * @return var�ncia
   */
  public float obtenirVariancia() {
    double v;

    v = (sumQuadrats/(numObjs-numMissings)) - Math.pow(sum/(numObjs-numMissings),2);
    return (float) v;
  }

  /**
   * Obt� la quasi-vari�ncia de tots els valors observats
   * @return quasi-var�ncia
   */
  public float obtenirQuasiVariancia() {
    double qv;

    qv = (sumQuadrats -(Math.pow(sum,2)/(numObjs-numMissings)))/(numObjs-numMissings-1);
    return (float)qv;
  }

  /**
   * Obt� la desviaci� t�pica de tots els valors observats
   * @return desviaci� t�pica
   */
  public float obtenirDesvTipica(){
    float dt;

    dt = (float) Math.sqrt(obtenirVariancia());
    return dt;
  }

  /**
   * Obt� la quasi-desviaci� t�pica de tots els valors observats
   * @return quasi-desviaci� t�pica
   */
  public float obtenirQuasiDesvTip(){

    return  (float) Math.sqrt((float) (sumQuadrats -(Math.pow(sum,2)/(numObjs-numMissings)))/(numObjs-numMissings-1));
  }

  /**
   * Obt� el coeficient de variaci� de tots els valors observats
   * @return coeficient de variaci�
   */
  public float obtenirCoefVariacio() {
    float cv;

    cv = (obtenirDesvTipica()/obtenirMitjana());
    return cv;
  }

  /**
   * Calcula la mitjana, Q1 i Q3 per les dades indicades al par�metre
   * @param dades vector amb tots els valors observats
   * @return vector amb la mediana a la posici� 0, Q1 a la posici� 1 i Q3 a la posici� 2
   */
  public float[] calcularQuartils(float[] dades) {
    float[] result = new float[3];
    int num, mig;
    boolean senar;

    num = dades.length;
    if (num == 0) {
      result[0] = Float.NaN;
      result[1] = Float.NaN;
      result[2] = Float.NaN;
    } else if(num == 1) { // cas 1 sol element
      result[0] = dades[0];
      result[1] = dades[0];
      result[2] = dades[0];
    } else {
      Arrays.sort(dades);
      // calculs mediana
      mig = num / 2;
      senar = (num % 2 != 0);
      if (senar) { // cas senar
        result[0] = dades[mig];
      }
      else { // cas parell
        result[0] = (dades[mig - 1] + dades[mig]) / 2;
      }

      // calculs quartils
      num = mig;
      mig = num / 2;
      if ( (mig == 0) || (num % 2 != 0)) { // 1 element o cas senar
        result[1] = dades[mig];
        if (senar) {
          result[2] = dades[num + mig + 1];
        }
        else {
          result[2] = dades[num + mig];
        }
      }
      else { // cas parell
        result[1] = (dades[mig - 1] + dades[mig]) / 2;
        if (senar) {
          result[2] = (dades[num + mig] + dades[num + mig + 1]) / 2;
        }
        else {
          result[2] = (dades[num + mig - 1] + dades[num + mig]) / 2;
        }
      }
    }
    return result;
  }

  public float[] calcularQuartils(Float[] dades) {
    float[] result = new float[3];
    int num, mig;
    boolean senar;

    num = dades.length;
    if (num == 1) { // cas 1 sol element
      result[0] = dades[0].floatValue();
      result[1] = dades[0].floatValue();
      result[2] = dades[0].floatValue();
    } else {
      Arrays.sort(dades);
      // calculs mediana
      mig = num / 2;
      senar = (num % 2 != 0);
      if (senar) { // cas senar
        result[0] = dades[mig].floatValue();
      }
      else { // cas parell
        result[0] = (dades[mig - 1].floatValue() + dades[mig].floatValue()) / 2;
      }

      // calculs quartils
      num = mig;
      mig = num / 2;
      if ( (mig == 0) || (num % 2 != 0)) { // 1 element o cas senar
        result[1] = dades[mig].floatValue();
        if (senar) {
          result[2] = dades[num + mig + 1].floatValue();
        }
        else {
          result[2] = dades[num + mig].floatValue();
        }
      }
      else { // cas parell
        result[1] = (dades[mig - 1].floatValue() + dades[mig].floatValue()) / 2;
        if (senar) {
          result[2] = (dades[num + mig].floatValue() + dades[num + mig + 1].floatValue()) / 2;
        }
        else {
          result[2] = (dades[num + mig - 1].floatValue() + dades[num + mig].floatValue()) / 2;
        }
      }
    }
    return result;
  }
  //DISTANCIES*********************************************************************
  /** Obt� el rang observat
  * @return rang observat
  *
  * @author Jose I Mateos
  * @version v.0 20/6/06
  */
  public float obtenirRang(){
    return max-min;
  }
  /**
  * Decrementa en 1 en numero de missings i actualitza la matriu amb la dad substituida
  *
  * @param d es la dada que substitueix el missing
  *
  * @author Jose I Mateos
  * @version v.0 6/8/06
  */
  public void eliminarMissing(Dada d) {
    numMissings--;
	numObjs--;
	actualitzar(d.numero(),1);
  }

  /**
    * Obt� la quasidesviaci� tpus amb pes de tots valors observats
    *
    *
    * @author Jose I Mateos
    * @version v.0 6/8/06
    */



 public float obtenirQuasiSkPes(){

	 return  (float) Math.sqrt((float) (sumQuadratsPes -(Math.pow(sumPes,2)/(pes-numMissings)))/(pes-numMissings-1));

	 }
      /**
    * Obt� la suma de tots els pesos del objectes
    *
    *
    * @author Jose I Mateos
    * @version v.0 6/8/06
    */


  public int obtenirPesTotal(){
	  return (pes);
  }

  //



  // ROBER

  /**
   * Obt� el sumatori de tots els valors observats amb pes
   * @return sumatori dels valors observats
   */
  public float obtenirSumPes(){
    return sumPes;
  }

  /**
   * Obt� el sumatori dels quadrats de tots els valors observats amb pes
   * @return sumatori dels quadrats dels valors observats
   */
  public float obtenirSumQuadratsPes(){
    return sumQuadratsPes;
  }


}

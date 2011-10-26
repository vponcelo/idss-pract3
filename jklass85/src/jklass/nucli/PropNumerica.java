package jklass.nucli;

/**
 * Classe per treballar amb propietats numèriques
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */
public class PropNumerica extends Propietat {
  private float rangMin;
  private float rangMax;
  private EstadisticsNum estadistics;

  /**
   * Contructor de la classe
   * @param id identificador de la propietat
   * @param minim valor mínim de la propietat
   * @param maxim  valor màxim de la propietat
   */
  public PropNumerica(String id, float minim, float maxim ) {
    super(id);
    rangMin = minim;
    rangMax = maxim;
    estadistics = new EstadisticsNum();
  }

  /**
   * Retorna el valor mínim del rang de valors que es poden observar per la propietat
   * @return el valor mínim que teòricament pot prendre la propietat
   */
  public float obtenirRangMin(){
    return rangMin;
  }

  /**
   * Obté el valor màxim del rang de valors que es poden observar per la propietat
   * @return el valor màxim que teòricament pot prendre la propietat
   */
  public float obtenirRangMax(){
    return rangMax;
  }

  /**
   * Retorna un objecte de la classe EstadisticsNum que conté els valors dels estadístics associats a la propietat
   * @return estadístics de la propietat (mirar la documentació d'aquesta classe per veure la seva estructura)
   */
  public EstadisticsNum obtenirEstadistics(){
    return estadistics;
  }
//DISTANCIES********************************************************************* pes
  /**
   * Actualitza la informació estadística de la propietat.
   * Es comproba si és un valor mancant i si aquest valor es troba dins del rang de valors possibles per la propietat
   * @param valor cadena de caràcters que es correspon amb un valor observat per aquesta propietat
   * @param p conté el pes de l'objecte
   * @throws CreacioPropietatsException si el valor indicat està fora del rang de valors possibles especificat per la propietat
   */
  public void actualitzar(String valor,float p)throws CreacioPropietatsException{
    float f;

    if (valor.compareTo("?") == 0) {
      estadistics.incrementarMissings();
    }
    else {
      f = Float.parseFloat(valor);
      logger.finer("float: " + Float.toString(f));
      if ( (f < rangMin) || (f > rangMax)){
        throw new CreacioPropietatsException("Valor fóra del rang de la propietat");
      } else{
        estadistics.actualitzar(f,p);
      }
    }
  }

  void actualitzarLliure(String valor)throws CreacioPropietatsException{
    actualitzar(valor,1);
  }
  //DISTANCIES*********************************************************************
  /** Fa un reset del valors de la propietat numèrica
  *
  * @author Jose I Mateos
  * @version v.0 06/08/06
  */
  public void reset(){
	  estadistics = new EstadisticsNum();
  }
////////////////////////////LAIA///////////////////////////////////
  public EstadisticsNum getEstadistics() {
  	return estadistics;
  }

  public void setEstadistics(EstadisticsNum estadistics) {
  	this.estadistics = estadistics;
  }

  public float getRangMax() {
  	return rangMax;
  }

  public void setRangMax(float rangMax) {
  	this.rangMax = rangMax;
  }

  public float getRangMin() {
  	return rangMin;
  }

  public void setRangMin(float rangMin) {
  	this.rangMin = rangMin;
  }
}

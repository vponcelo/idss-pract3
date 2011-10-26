package jklass.nucli;

/**
 * Classe per treballar amb propietats num�riques
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estad�stic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author M� del Mar Colillas
 * @version v.0
 */
public class PropNumerica extends Propietat {
  private float rangMin;
  private float rangMax;
  private EstadisticsNum estadistics;

  /**
   * Contructor de la classe
   * @param id identificador de la propietat
   * @param minim valor m�nim de la propietat
   * @param maxim  valor m�xim de la propietat
   */
  public PropNumerica(String id, float minim, float maxim ) {
    super(id);
    rangMin = minim;
    rangMax = maxim;
    estadistics = new EstadisticsNum();
  }

  /**
   * Retorna el valor m�nim del rang de valors que es poden observar per la propietat
   * @return el valor m�nim que te�ricament pot prendre la propietat
   */
  public float obtenirRangMin(){
    return rangMin;
  }

  /**
   * Obt� el valor m�xim del rang de valors que es poden observar per la propietat
   * @return el valor m�xim que te�ricament pot prendre la propietat
   */
  public float obtenirRangMax(){
    return rangMax;
  }

  /**
   * Retorna un objecte de la classe EstadisticsNum que cont� els valors dels estad�stics associats a la propietat
   * @return estad�stics de la propietat (mirar la documentaci� d'aquesta classe per veure la seva estructura)
   */
  public EstadisticsNum obtenirEstadistics(){
    return estadistics;
  }
//DISTANCIES********************************************************************* pes
  /**
   * Actualitza la informaci� estad�stica de la propietat.
   * Es comproba si �s un valor mancant i si aquest valor es troba dins del rang de valors possibles per la propietat
   * @param valor cadena de car�cters que es correspon amb un valor observat per aquesta propietat
   * @param p cont� el pes de l'objecte
   * @throws CreacioPropietatsException si el valor indicat est� fora del rang de valors possibles especificat per la propietat
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
        throw new CreacioPropietatsException("Valor f�ra del rang de la propietat");
      } else{
        estadistics.actualitzar(f,p);
      }
    }
  }

  void actualitzarLliure(String valor)throws CreacioPropietatsException{
    actualitzar(valor,1);
  }
  //DISTANCIES*********************************************************************
  /** Fa un reset del valors de la propietat num�rica
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

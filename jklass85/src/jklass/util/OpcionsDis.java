package jklass.util;

 //DISTANCIES*********************************************************************
 /** Classe que conté constants, els metodes per accedir a:
 * el tipus de distancia de les opcions
 * la subcategoria de distancia
 * i parametres de les distàncies
 *
 * @author Jose I Mateos
 * @version v.0 21/4/06
 */

public class OpcionsDis {

  /**  indica distància euclídia
  */
  public final static char EUCLI = 'e';
  /**  indica distància en valor absolut
  */
  public final static char ABS = 'a';
  /**  indica distància de Minkovski
  */
  public final static char MINKO = 'k';
  /**  indica distància Chi2
  */
  public final static char CHI2 = 'c';
  /**  indica distància de Hamming Generalitzat
  */
  public final static char HAMM = 'h';
  /**  indica distància de Gowda-Diday
  */
  public final static char GOWDA = 'g';
  /**  indica distància de Ichino-Yaguchi
  */
  public final static char ICHINO = 'i';
  /**  indica distància de Gower
  */
  public final static char GOWER = 'w';
  /**  indica distància ralambondrainy
  */
  public final static char RALAM = 'r';
  /**  indica distància Mixta de Gibert
  */
  public final static char MIXTA = 'm';
  //Subcategories de les distancies

  /**  indica categoria no normalitzada
  */
  public final static char NONOR = 'n';
  /**  indica categoria normalitzada per la Sk
  */
  public final static char SK = 's';
  /**  indica categoria normalitzada pel Rang
  */
  public final static char RANG = 'r';

  /**  indica categoria inèrcia de Ralambondrainy
  */
  public final static char INER = 'i';
  /**  indica categoria norma de Ralambondrainy
  */
  public final static char NORMA = 'o';
  /**  indica parametres alfa i beta automatics de la Mixta de Gibert
  */
  public final static char AUTO = 'a';
  /**  indica parametres alfa i beta no-automatics de la Mixta de Gibert
  */
  public final static char NOAUTO = 'x';
  //Parametres de la clase
  /**  conté el tipus de distància
  */
  private char tipus;
  /**  conté la categoria o subdistància
  */
  private char categ;
  /**  indica si el càlcul es quadratic
  */
  private boolean quadrat=false;
  /**  indica si el càlcul es ponderat
  */
  private boolean ponderat=false;
  /**  conté el valor de alfa per la Mixta de Gibert i Ralambodrainy
  */
  private float alfa=0;
  /**  conté el valor de beta per la Mixta de Gibert i Ralambodrainy
  */
  private float beta=0;
  /**  conté el valor de p per Minkovski i Ichino-Yaguchi
  */
  private int p=2;
  /**  conté el valor gamma per Ichino-Yaguchi
  */
  private float gamma=(float)0.5;

  /**
  * Constructor
  */
  public OpcionsDis(){
  }
  /**
  * Establir el tipùs de distancia
  *
  * @param tip es el tipus de distancia
  */
  public void setTipus (char tip){
	tipus=tip;
  }
  /**
  * Establir el tipùs de distancia dins una distancia concreta
  *
  * @param cat es el tipus de subdistancia
  */
  public void setCateg (char cat){
	categ=cat;
  }
  /**
  * Establir si el calcul es quadratic o no
  *
  * @param qua es el boolea que indica si el calcul es quadratic
  */
  public void setQuad (boolean qua){
	quadrat=qua;
  }
  /**
  * Establir si el calcul es ponderat o no
  *
  * @param pon es el boolea que indica si el calcul es ponderat
  */
  public void setPond (boolean pon){
	ponderat=pon;
  }
  /**
  * Establir el valor de alfa
  *
  * @param a es el nou valor de alfa
  */
  public void setAlfa (float a){
	alfa=a;
  }
  /**
  * Establir el valor de beta
  *
  * @param b es el nou valor de beta
  */
  public void setBeta (float b){
	beta=b;
  }
  /**
  * Establir el valor de p
  *
  * @param vp es el nou valor de p
  */
  public void setP (int vp){
	p=vp;
  }
  /**
  * Establir el valor de gamma
  *
  * @param g es el nou valor de gamma
  */
  public void setGamma (float g){
	gamma=g;
  }
  /**
  * Obtenir el tipùs de distancia
  *
  * @return tipus de distancia
  */
  public char getTipus(){
 	return tipus;
  }
  /**
  * Obtenir el tipùs de distancia dins una distancia concreta
  *
  * @return subtipus de distancia
  */
  public char getCateg(){
	return categ;
  }
  /**
  * Saber si el calcul es ponderat o no
  *
  * @return si es ponderat el calcul
  */
  public boolean getPond(){
  	return ponderat;
  }
  /**
  * Saber si el calcul es quadratic o no
  *
  * @return si es quadratic el calcul
  */
  public boolean getQuad(){
  	return quadrat;
  }
  /**
  * Obtenir l'alfa de la distancia Mixta Gibert
  *
  * @return el numero alfa
  */
  public float getAlfa (){
	return alfa;
  }
  /**
  * Obtenir la beta de la distancia Mixta Gibert
  *
  * @return el numero beta
  */
  public float getBeta (){
	return beta;
  }
  /**
  * Obtenir el valor de p
  *
  * @return el numero p
  */
  public int getP (){
	return p;
  }
  /**
  * Obtenir el valor de gamma
  *
  * @return el numero gamma
  */
  public float getGamma (){
	return gamma;
  }


}
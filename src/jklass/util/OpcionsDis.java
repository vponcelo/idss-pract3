package jklass.util;

 //DISTANCIES*********************************************************************
 /** Classe que cont� constants, els metodes per accedir a:
 * el tipus de distancia de les opcions
 * la subcategoria de distancia
 * i parametres de les dist�ncies
 *
 * @author Jose I Mateos
 * @version v.0 21/4/06
 */

public class OpcionsDis {

  /**  indica dist�ncia eucl�dia
  */
  public final static char EUCLI = 'e';
  /**  indica dist�ncia en valor absolut
  */
  public final static char ABS = 'a';
  /**  indica dist�ncia de Minkovski
  */
  public final static char MINKO = 'k';
  /**  indica dist�ncia Chi2
  */
  public final static char CHI2 = 'c';
  /**  indica dist�ncia de Hamming Generalitzat
  */
  public final static char HAMM = 'h';
  /**  indica dist�ncia de Gowda-Diday
  */
  public final static char GOWDA = 'g';
  /**  indica dist�ncia de Ichino-Yaguchi
  */
  public final static char ICHINO = 'i';
  /**  indica dist�ncia de Gower
  */
  public final static char GOWER = 'w';
  /**  indica dist�ncia ralambondrainy
  */
  public final static char RALAM = 'r';
  /**  indica dist�ncia Mixta de Gibert
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

  /**  indica categoria in�rcia de Ralambondrainy
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
  /**  cont� el tipus de dist�ncia
  */
  private char tipus;
  /**  cont� la categoria o subdist�ncia
  */
  private char categ;
  /**  indica si el c�lcul es quadratic
  */
  private boolean quadrat=false;
  /**  indica si el c�lcul es ponderat
  */
  private boolean ponderat=false;
  /**  cont� el valor de alfa per la Mixta de Gibert i Ralambodrainy
  */
  private float alfa=0;
  /**  cont� el valor de beta per la Mixta de Gibert i Ralambodrainy
  */
  private float beta=0;
  /**  cont� el valor de p per Minkovski i Ichino-Yaguchi
  */
  private int p=2;
  /**  cont� el valor gamma per Ichino-Yaguchi
  */
  private float gamma=(float)0.5;

  /**
  * Constructor
  */
  public OpcionsDis(){
  }
  /**
  * Establir el tip�s de distancia
  *
  * @param tip es el tipus de distancia
  */
  public void setTipus (char tip){
	tipus=tip;
  }
  /**
  * Establir el tip�s de distancia dins una distancia concreta
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
  * Obtenir el tip�s de distancia
  *
  * @return tipus de distancia
  */
  public char getTipus(){
 	return tipus;
  }
  /**
  * Obtenir el tip�s de distancia dins una distancia concreta
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
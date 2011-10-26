package jklass.nucli;


/**
 * Classe que es correspom amb la informació que té una cel·la de la matriu
 *
 * @author Maria del Mar Colillas
 * @version Java-KLASS v0
 */
public class Dada {
  private Object valor;

  /**
  * Contructor que posar com a valor de la dada l'objecte que es passa
  * @param o valor de l'objecte
  */
  public Dada(Object o) {
    valor=o;
  }

  Dada clon() {
	return new Dada(valor);
  }

  /**
  * Obté el valor de la cel·la de la matriu
  * @return object que representa el valor
  */
  public Object obtenirValor(){
    return valor;
  }
  //DISTANCIES*********************************************************************
  /**
  * Obté si el valor es un missing
  *
  * @return cert o fals
  *
  * @author Jose I Mateos
  * @version v.0 28/9/06
  */
  public boolean esMissing(){
    String aux;

    aux=(String)valor;
    return (aux.compareTo("?")==0);
  }
  /**
  * Obté el valor es un float
  *
  *
  * @author Jose I Mateos
  * @version v.0 28/9/06
  */
  public float numero(){

	return Float.parseFloat((String)this.obtenirValor());

  }
  /**
  * Obté el valor es un String
  *
  * @return cert o fals
  *
  * @author Jose I Mateos
  * @version v.0 28/9/06
  */
  public String categ(){

	return (String)this.obtenirValor();

  }
  /**
  * Compara dues dades del tipus Dada
  *
  * @param d dada a comparar amb l'acutal
  * @return cert o fals si son o no iguals
  *
  * @author Jose I Mateos
  * @version v.0 28/9/06
  */
  public boolean compara(Dada d){

	return (this.categ().compareTo(d.categ())==0);

  }

   /**
  * Retorna si la dada es una dada extesa
  *
  * @return cert o fals
  *
  * @author Jose I Mateos
  * @version v.0 16/04/07
  */
  public boolean esExtesa(){
    
    return (this.categ().indexOf("((")>=0);
  }
  
  
  
  
  
}

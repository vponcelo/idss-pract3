
package jklass.nucli;

 // DISTANCIES*********************************************************************
 /** Classe que permet gestionar fitxers .cor en format KLASS, fitxers que contenen la
 * matriu de coorrelacions. Cada fila està delimitada per un parèntesi al principi i al final
 * i les dades estan separades per un espai en blanc
 *
 * @author Jose I Mateos
 * @version v.0 7/5/06
 */

class FitxerCo extends Fitxer {

  /**
  * Contructor
  *
  * @param nom nom del fitxer sense extensió
  */
  public FitxerCo(String nom) {
    super(nom);
    modificarNom(obtenirNom() + ".co");
  }

}
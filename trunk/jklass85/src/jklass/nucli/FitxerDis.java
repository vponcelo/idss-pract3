
package jklass.nucli;

 // DISTANCIES*********************************************************************
 /** Classe que permet gestionar fitxers .dis en format KLASS, fitxers que contenen la
 * matriu de distancies. Cada fila està delimitada per un parèntesi al principi i al final
 * i les dades estan separades per un espai en blanc
 *
 * @author Jose I Mateos
 * @version v.0 7/5/06
 */

class FitxerDis extends Fitxer {

  /**
  * Contructor
  *
  * @param nom nom del fitxer sense extensió
  */
  FitxerDis(String nom) {
    super(nom);
    modificarNom(obtenirNom() + ".dis");
  }

}
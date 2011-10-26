package jklass.nucli;

 // DISTANCIES*********************************************************************
 /** Classe que permet gestionar fitxers .pro en format KLASS, fitxers que contenen la
 * matriu de distancies directes.
 *
 * @author Jose I Mateos
 * @version v.0 11/6/06
 */

class FitxerUsuPro extends Fitxer {

  /**
  * Contructor
  *
  * @param nom nom del fitxer sense extensió
  */
  public FitxerUsuPro(String nom) {
    super(nom);
    modificarNom(nom +".pro");
  }

}
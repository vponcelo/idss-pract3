package jklass.nucli;

 // DISTANCIES*********************************************************************
 /** Classe que permet gestionar fitxers .obj en format KLASS, fitxers que contenen la
 * matriu de distancies directes.
 *
 * @author Jose I Mateos
 * @version v.0 20/6/06
 */

class FitxerUsuObj extends Fitxer {

  /**
  * Contructor
  *
  * @param nom nom del fitxer sense extensi�
  */
  public FitxerUsuObj(String nom) {
    super(nom);
    modificarNom(nom +".obj");
  }

}
package jklass.nucli;

import java.util.logging.*;

/**
 * Classe que permet gestionar fitxers .mk en format KLASS, fitxers que contenen
 * m�nims i m�xims de variables n�meriques categoritzades.
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estad�stic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author M� del Mar Colillas
 * @version v.0
 */

public class FitxerMk extends Fitxer {
  public FitxerMk(String nom) {
    super(nom);
    modificarNom(obtenirNom() + ".mk");
  }
}

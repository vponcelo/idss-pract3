package jklass.nucli;

import java.util.logging.*;

/**
 * Classe que permet gestionar fitxers .mk en format KLASS, fitxers que contenen
 * mínims i màxims de variables númeriques categoritzades.
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

public class FitxerMk extends Fitxer {
  public FitxerMk(String nom) {
    super(nom);
    modificarNom(obtenirNom() + ".mk");
  }
}

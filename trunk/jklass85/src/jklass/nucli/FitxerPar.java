package jklass.nucli;

import java.util.logging.*;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estad�stic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author M� del Mar Colillas
 * @version v.0
 */

public class FitxerPar extends Fitxer {
  public FitxerPar(String nom) {
    super(nom);
    modificarNom(obtenirNom() + ".par");
  }

}
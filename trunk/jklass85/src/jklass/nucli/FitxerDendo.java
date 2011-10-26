//ROBER



package jklass.nucli;

import java.util.logging.*;
import java.util.StringTokenizer;

/**
 *
ROBER
 */
public class FitxerDendo extends Fitxer {
  public FitxerDendo(String nom) {
    super(nom);
    modificarNom(obtenirNom()+".drm");
  }

  StringTokenizer llegirNode(){
    StringTokenizer st = null;
    String lin="";

    do{
      lin = llegirLinia();
      logger.finer("Linia llegida: " + lin);
      if (lin != null) {
        st = new StringTokenizer(lin, '\t' + " ()");
      }
    } while ((lin != null) && (st.countTokens()== 0));
    // la llista de nodes s'acaba quan es troba l'string 'NIL'
    if ((lin == null) || (lin.equalsIgnoreCase("NIL")))
      st=null;

    return st;
  }


}

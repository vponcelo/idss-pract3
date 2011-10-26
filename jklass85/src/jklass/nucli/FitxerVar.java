package jklass.nucli;

import java.util.logging.*;
import java.util.StringTokenizer;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

public class FitxerVar extends Fitxer {
  public FitxerVar(String nom) {
    super(nom);
    modificarNom(obtenirNom()+".var");
  }

  StringTokenizer llegirObj(){
    StringTokenizer st = null;
    String lin="";

    do{
      lin = llegirLinia();
      logger.finer("Linia llegida: " + lin);
      if (lin != null) {
        st = new StringTokenizer(lin, '\t' + " ");
      }
    } while ((lin != null) && (st.countTokens()== 0));
    if (lin == null)
      st=null;

    return st;
  }

}
package jklass.nucli;

import java.util.logging.*;
import java.util.StringTokenizer;

/**
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */
public class FitxerHis extends Fitxer {
  public FitxerHis(String nom) {
    super(nom);
    modificarNom(obtenirNom()+".his");
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
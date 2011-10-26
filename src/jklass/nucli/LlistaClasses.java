package jklass.nucli;

import java.io.IOException;
import java.util.ArrayList;
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

public class LlistaClasses {
  private static Logger logger=Logger.getLogger(LlistaClasses.class.getName());
  /** llista d'objectes a la que esta associada aquesta Llista de Classes */
  LlistaObjectes objectes;
  /** Llista d'identificadors de classe */
  String[] llista;

  public LlistaClasses(LlistaObjectes obj) {
    objectes = obj;
    llista= new String[objectes.obtenirLong()];
  }

  public void afegirClasses(FitxerPar fitxer) throws FormatIncorrecteException,
      ParamIncorrecteException {
    String lin, classe = null, obj = null;
    int i = 0, numPars = 0, pos, lon;
    char c;
    boolean fiClasse = false;

    lin = fitxer.llegirLinia();
    logger.finer("Linia llegida: " + lin);
    while (lin != null) {
      i = 0;
      lon = lin.length();
      while (i < lon) {
        c = lin.charAt(i);
        switch (c) {
          case '(':
            numPars++;
            if (numPars == 3) {
              fiClasse = (classe != null);
            }
            else if (numPars > 3) {
              throw new FormatIncorrecteException("Format del fitxer de classificació (.par) incorrecte - Parentèsi mal balancejats. ");
            }
            break;
          case ')':
            numPars--;
            if (numPars == 2) {
              if (obj != null) {
              /* tenin un element de la llista d'objectes que tenim de la classe*/
                pos = objectes.obtenirIndex(obj);
                llista[pos] = classe;
                obj = null;
              }
            }
            else if (numPars == 1) {
              classe = null;
              fiClasse = false;
            }
            break;
          case ' ':
          case '\t':
            if (obj != null) {
            /* tenin un element de la llista d'objectes que tenim de la classe*/
              pos = objectes.obtenirIndex(obj);
              llista[pos] = classe;
              obj = null;
            }
            break;
          default:
            if (numPars == 1) {
              throw new FormatIncorrecteException(
                  "Format del fitxer de classificació (.par) incorrecte - Falta ( d'inici de la classe");
            }
            else if (numPars == 2) {
              if (classe == null) {
                classe = String.valueOf(c);
              }
              else {
                classe = classe.concat(String.valueOf(c));
              }
            }
            else if (numPars == 3) {
              if (fiClasse) {
                if (obj == null) {
                  obj = String.valueOf(c);
                }
                else {
                  obj = obj.concat(String.valueOf(c));
                }
              }
              else {
                throw new FormatIncorrecteException(
                    "Format del fitxer de classificació (.par) incorrecte - Falta el nom d'una classe");
              }
            }
            else {
              throw new FormatIncorrecteException(
                  "Format del fitxer de classificació (.par) incorrecte");
            }
        }
        i++;
      }
      lin = fitxer.llegirLinia();
      logger.finer("Linia llegida: " + lin);
    }
    if (numPars != 0) {
      throw new FormatIncorrecteException("Format del fitxer de classificació (.par) incorrecte - Parentèsi mal balancejats. ");
    }
  }

  void escriureAFitxer(FitxerCls fitxer) throws IOException {
    ArrayList llObjs;
    String str = null,obj;
    int i,lon;

    llObjs = objectes.obtenirLlista();
    lon = llObjs.size();
    for (i = 0; i < lon; i++) {
      obj = ((Objecte) llObjs.get(i)).obtenirId();
      str = obj + "\t" + llista[objectes.obtenirIndex(obj)] ;
      logger.finer("Objecte classificat: " + str);
      fitxer.escriureLin(str);
    }
  }

}
package jklass.nucli;

import java.io.IOException;
import java.util.Hashtable;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

class TaulaCCN extends RepresentacioNumerica {
   TaulaCCN(FitxerTex fitxer, CalculsTrivCCN calculs, String varCategX,
                                   String varCategY, String varNumZ, Hashtable opcs, int maxX,
                                   int maxY, int iniciX, int iniciY) throws
      CreacioFitxerException {
    super(fitxer);
    String strTab = null, strTit = null, strLin = null, modX = null, modY = null;
    int est = 0;
    int i, j, x, y;
    String[] modalsX, modalsY;
    boolean fiX,fiY;
    CalculsUnivCateg calcCat;
    float f;

    try {
      strLin = new String("{Contingut de la casella: ");
      if ( ( (Boolean) opcs.get("miss")).booleanValue()) {
        est = 1;
        strLin = strLin + "percentatge de mancants";
      }
      else if ( ( (Boolean) opcs.get("min")).booleanValue()) {
        est = 2;
        strLin = strLin + "mínim";
      }
      else if ( ( (Boolean) opcs.get("max")).booleanValue()) {
        est = 3;
        strLin = strLin + "m\\`axim";
      }
      else if ( ( (Boolean) opcs.get("mitj")).booleanValue()) {
        est = 4;
        strLin = strLin + "mitjana";
      }
      else if ( ( (Boolean) opcs.get("me")).booleanValue()) {
        est = 5;
        strLin = strLin + "mediana";
      }
      else if ( ( (Boolean) opcs.get("q1")).booleanValue()) {
        est = 6;
        strLin = strLin + "Q1";
      }
      else if ( ( (Boolean) opcs.get("q3")).booleanValue()) {
        est = 7;
        strLin = strLin + "Q3";
      }
      else if ( ( (Boolean) opcs.get("dist_iq")).booleanValue()) {
        est = 8;
        strLin = strLin + "dist\\`ancia interquart\\'i\\lge lica";
      }
      else if ( ( (Boolean) opcs.get("amp")).booleanValue()) {
        est = 9;
        strLin = strLin + "amplitud";
      }
      else if ( ( (Boolean) opcs.get("var")).booleanValue()) {
        est = 10;
        strLin = strLin + "vari\\`ancia";
      }
      else if ( ( (Boolean) opcs.get("desv")).booleanValue()) {
        est = 11;
        strLin = strLin + "desviaci\\'o t\\'ipica";
      }
      else if ( ( (Boolean) opcs.get("qvar")).booleanValue()) {
        est = 12;
        strLin = strLin + "quasi-vari\\`ancia";
      }
      else if ( ( (Boolean) opcs.get("qdesv")).booleanValue()) {
        est = 13;
        strLin = strLin + "quasi-desviaci\\'o t\\'ipica";
      }
      else if ( ( (Boolean) opcs.get("coef")).booleanValue()) {
        est = 14;
        strLin = strLin + "coeficient de variaci\\'o";
      }

      if ( (iniciX == 0) && (iniciY == 0)) {
        // primera página de Taula de Contingència
        fTex.escriureLin("\\begin{center}");
        if ( (varCategX == null) || (varCategY == null)) {
          fTex.escriureLin(
              "{\\bf Taula cat$ \\times $cat$ \\times $num }");
        }
        else {
          fTex.escriureLin(
              "{\\bf Taula cat$ \\times $cat$ \\times $num  per les variables " +
              fTex.adaptarATex(varCategX) + " i " + fTex.adaptarATex(varCategY) + " vs. " +
              fTex.adaptarATex(varNumZ) + " }");
        }
        fTex.escriureLin(" ");
        fTex.escriureLin(strLin + "}\\vspace{0.5em}");
        fTex.escriureLin(" ");

      }

      if ( (varCategX == null) || (varCategY == null)) {
        strTit = new String("{\\bf X $ \\backslash $ Y} ");
      }
      else {
        strTit = new String("{\\bf " + fTex.adaptarATex(varCategX) +
                            " $ \\backslash $ " + fTex.adaptarATex(varCategY) +
                            " } ");
      }
      strTab = new String("\\begin{tabular}{|c||");
      y = iniciY;
      modalsY = calculs.obtenirModalitatsY();
      for (i = 0; i < maxY; i++) {
        strTab = strTab + "c|";
        strTit = strTit + " & {\\bf " + fTex.adaptarATex(modalsY[y + i]) + "}";
      }
      fiY = (iniciY + maxY) == calculs.obtenirNumModalitatsY();

      strTab = strTab + "}";
      strTit = strTit + "\\\\";

      fTex.escriureLin(strTab);
      fTex.escriureLin("\\hline");
      fTex.escriureLin(strTit);
      fTex.escriureLin("\\hline\\hline");

      x = iniciX;
      modalsX = calculs.obtenirModalitatsX();
      for (i = 0; i < maxX; i++) {
        modX = modalsX[x + i];
        strLin = new String(fTex.adaptarATex(modX));
        for (j = 0; j < maxY; j++) {
          modY = modalsY[y + j];
          switch (est) {
            case 1:
              f = calculs.obtenirNumObjs(modX, modY);
              if (f > 0) {
                f = calculs.obtenirNumMissings(modX, modY) / f;
              } else {
                f = Float.NaN;
              }
              break;
            case 2:
              f = calculs.obtenirMin(modX, modY);
              break;
            case 3:
              f = calculs.obtenirMax(modX, modY);
              break;
            case 4:
              f = calculs.obtenirMitjana(modX,modY);
              break;
            case 5:
              f = calculs.obtenirMe(modX,modY);
              break;
            case 6:
              f = calculs.obtenirQ1(modX,modY);
              break;
            case 7:
              f = calculs.obtenirQ3(modX,modY);
              break;
            case 8:
              f = calculs.obtenirDistInterQ(modX,modY);
              break;
            case 9:
              f = calculs.obtenirAmp(modX,modY);
              break;
            case 10:
              f = calculs.obtenirVariancia(modX,modY);
              break;
            case 11:
              f = calculs.obtenirDesvTipica(modX,modY);
              break;
            case 12:
              f = calculs.obtenirQuasiVariancia(modX,modY);
              break;
            case 13:
              f = calculs.obtenirQuasiDesvTip(modX,modY);
              break;
            case 14:
              f = calculs.obtenirCoefVariacio(modX,modY);
              break;
            default:
              f = Float.NaN;
          }
          strLin = strLin + " & " + fTex.formatejarReal(f);
        }
        strLin = strLin + " \\\\";
        fTex.escriureLin(strLin);
        fTex.escriureLin("\\hline");
      }
      fiX = (( iniciX + maxX) == calculs.obtenirNumModalitatsX());
     fTex.escriureLin("\\end{tabular}");

     if ( fiX && fiY) {
       fTex.escriureLin("");
       fTex.escriureLin("\\end{center}");
       fTex.escriureLin("\\vfill");
       fTex.escriureLin("");
     }
    }
    catch (IOException e) {
      throw new CreacioFitxerException("Error d'escriptura: " + e.getMessage());
    }
  }

}
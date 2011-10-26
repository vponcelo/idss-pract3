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

class DescrClassesCateg extends RepresentacioNumerica {
  DescrClassesCateg(FitxerTex fitxer, CalculsBivCC[] calculs,
                                       String[] llistaVar, int nTab,
                                       int ultClass, int maxClass, int iniVar,
                                       int nVars) throws
      OpcioIncorrectaException, CreacioFitxerException {
    super(fitxer);

    int lon, ini, i, j, k, l, nClass;
    String[] modalsY,  modalsX;
    String strTab, strTit, strN, strLin, mod;
    CalculsUnivCateg calc;

    try {
      fTex.escriureLin("\\begin{center}");
      modalsY = calculs[0].obtenirModalitatsY();

      ini = 0; //index de la modalitat per la qual es comença
      // es generen totes les pàgines pel conjunt de vars
      for (i = 0; ( (i <= nTab) && (ini < modalsY.length)); i++) {
        if ( (i == nTab) && (ultClass != 0)) {
          nClass = ultClass;
        }
        else {
          nClass = maxClass;
        }
        if (i > 0) {
          fTex.escriureLin("{\\large $\\sim$}");
          fTex.escriureLin("");
          fTex.escriureLin("\\vspace{3ex}");
          fTex.escriureLin("");
          fTex.escriureLin("{\\large $\\sim$}");
        }
        strTab = new String("\\begin{tabular}{|c|c||@{\\hspace{1ex}}||");
        strTit = new String(" & {\\bf CLASSE}");
        strN = new String(" &  N = " + calculs[0].obtenirNumObjs());
        strLin = new String("{\\bf VARIABLE} & ");
        for (j = ini; j < (ini + nClass); j++) {
          strTab = strTab + "c|c|c||@{\\hspace{1ex}}||";
          strTit = strTit + " & \\multicolumn{3}{c||@{\\hspace{1ex}}||}{\\bf " + fTex.adaptarATex(modalsY[j]) + "}";
          strN = strN + " & \\multicolumn{3}{c||@{\\hspace{1ex}}||}{ $n_c$ = " + calculs[0].obtenirNumObjsY(modalsY[j]) + "}";
          strLin = strLin + "& $\\bf n_i$ & $\\bf f_i$ & {\\bf N*} ";
        }
        strTab = strTab + "}";
        strTit = strTit + "\\\\";
        strN = strN + "\\\\";
        strLin = strLin + "\\\\";
        //S'escriu la capsalera del tabular
        fTex.escriureLin(strTab);
        fTex.escriureLin("\\hline");
        fTex.escriureLin(strTit);
        fTex.escriureLin("\\cline{2-" + (2 + nClass*3) + "}");
        fTex.escriureLin(strN);
        fTex.escriureLin("\\hline");
        fTex.escriureLin(strLin);
        fTex.escriureLin("\\hline");
        fTex.escriureLin("\\hline");
        fTex.escriureLin("\\multicolumn{2}{|c||@{\\hspace{1ex}}||}{}&\\multicolumn{" + (nClass * 3) + "}{c||@{\\hspace{1ex}}||}{}\\\\");
        fTex.escriureLin("\\hline");

        for (j = iniVar; j < (iniVar + nVars); j++) {
          // Per cada variable es preparen les files amb resum estadístic
          logger.finer("Preparant estadístics per la var. " + llistaVar[j]);
          modalsX = calculs[j].obtenirModalitatsX();
          lon = modalsX.length;
          for (k = 0; k < lon; k++) {
            if (k == 0) {
              strLin = new String("{\\bf " + fTex.adaptarATex(llistaVar[j]) +
                                   "}");
            } else {
              strLin = new String(" ");
            }
            mod = calculs[j].obtenirCalculsUniv(modalsY[ini]).obtenirModalitat(
                k);
            strLin = strLin + " & {\\bf " + fTex.adaptarATex(mod) + "}";
            for (l = ini; l < (ini + nClass); l++) {
              calc = calculs[j].obtenirCalculsUniv(modalsY[l]);
              strLin = strLin + " & " +
                  fTex.formatejarReal(calc.obtenirFreq(k)) + " & " +
                  fTex.formatejarReal(calc.obtenirFreqRel(k)) + " & " +
                  fTex.formatejarReal(calculs[j].obtenirMancantsX(mod));
            }
            fTex.escriureLin(strLin + " \\\\");
            fTex.escriureLin("\\cline{2-" + (2 + nClass*3) + "}");
          }
          fTex.escriureLin("\\hline");
          fTex.escriureLin("\\multicolumn{2}{|c||@{\\hspace{1ex}}||}{}&\\multicolumn{" + (nClass * 3) + "}{c||@{\\hspace{1ex}}||}{}\\\\");
          fTex.escriureLin("\\hline");
        }
        fTex.escriureLin("\\end{tabular}");
        logger.finer("Finalitzat tabular.");
        ini = ini + nClass;
      }

      fTex.escriureLin("\\end{center}");
      fTex.escriureLin("");
    }
    catch (IOException e) {
      throw new CreacioFitxerException("Error d'escriptura: " + e.getMessage());
    }
  }

}
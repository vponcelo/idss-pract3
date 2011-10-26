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

class DescrClassesNum extends RepresentacioNumerica {
  DescrClassesNum(FitxerTex fitxer, CalculsBivNC[] calculs,
                                          String[] llistaVar, Hashtable opc, int nTab,
                                          int ultClass, int maxClass, int iniVar,
                                          int nVars) throws
         OpcioIncorrectaException, CreacioFitxerException {
       super(fitxer);
       int lon, ini, i, j, k, nClass;
       String[] modals, strLin2 = new String[6];//ale cambie 6 por el 5 que habia
       String strTab, strTit, mod, strAux, strLin;
       CalculsBivNC calc;

       try {
         fTex.escriureLin("\\begin{center}");
         modals = calculs[0].obtenirModalitats();
         lon = modals.length;

         ini = 0; //index de la modalitat per la qual es comença
         // es generen totes les pàgines pel conjunt de vars
         for (i = 0; ( (i <= nTab) && (ini < lon)); i++) {
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
           strTab = new String("\\begin{tabular}{|c|c||");
           strTit = new String(" & {\\bf CLASSE }");
           strLin = new String("{\\bf VARIABLE} & N = " + calculs[0].obtenirNumObjs());
           // linia necesaria per fer ajustos per visulitzar la barra superior de la mitjana
           strAux = new String(" & ");

           for (j = ini; j < (ini + nClass); j++) {
             strTab = strTab + "c|";
             strTit = strTit + " & {\\bf " + fTex.adaptarATex(modals[j]) + "}";
             strLin = strLin + " & $n_c$ = " + calculs[0].obtenirNumObjs(modals[j]);
             strAux = strAux + " & ";
           }
           strTab = strTab + "}";
           strTit = strTit + "\\\\";
           strLin = strLin + "\\\\";
           strAux = strAux + "\\\\  [-2.4ex]";

           //S'escriu la capsalera del tabular
           fTex.escriureLin(strTab);
           fTex.escriureLin("\\hline");
           fTex.escriureLin(strTit);
           fTex.escriureLin("\\hline");
           fTex.escriureLin(strLin);
           fTex.escriureLin("\\hline");
           fTex.escriureLin("\\hline");

           for (j = iniVar; j < (iniVar + nVars); j++) {
             // Per cada variable es preparen les files amb resum estadístic
             logger.finer("Preparant estadístics per la var. " + llistaVar[j]);
             calc = calculs[j];
             strLin2[0] = new String("{\\bf " + fTex.adaptarATex(llistaVar[j]) +
                                    "} &  $\\bf \\bar{X}$");
             strLin2[1] = new String(" & {\\bf  S}");
             strLin2[2] = new String(" & {\\bf  min}");
             strLin2[3] = new String(" & {\\bf  max}");
             strLin2[4] = new String(" & {\\bf  N*}");
				 strLin2[5] = new String(" & {\\bf  Me}");//ale agregué esto
             for (k = ini; k < (ini + nClass); k++) {
               strLin2[0] = strLin2[0] + " & " +
                   fTex.formatejarReal(calc.obtenirCalculsUniv(modals[k]).
                                  obtenirMitjana());
               strLin2[1] = strLin2[1] + " & " +
                   fTex.formatejarReal(calc.obtenirCalculsUniv(modals[k]).
                                  obtenirQuasiDesv());
               strLin2[2] = strLin2[2] + " & " +
                   fTex.formatejarReal(calc.obtenirCalculsUniv(modals[k]).obtenirMin());

               strLin2[3] = strLin2[3] + " & " +
                   fTex.formatejarReal(calc.obtenirCalculsUniv(modals[k]).obtenirMax());
               strLin2[4] = strLin2[4] + " & " +
                   fTex.formatejarReal(calc.obtenirCalculsUniv(modals[k]).
                                  obtenirNumMancants());
					strLin2[5] = strLin2[5] + " & " +
                   fTex.formatejarReal(calc.obtenirCalculsUniv(modals[k]).
                                  obtenirMediana());
						 
											 
             }
             // linia necesaria per fer ajustos per visulitzar la barra superior de la mitjana
             fTex.escriureLin(strAux);
             for (k = 0; k < 6; k++) {//ale cambie aca
               fTex.escriureLin(strLin2[k] + " \\\\");
               fTex.escriureLin("\\cline{2-" + (2 + nClass) + "}");
             }
             fTex.escriureLin("\\hline");
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
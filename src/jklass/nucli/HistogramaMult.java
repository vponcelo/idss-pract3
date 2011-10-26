package jklass.nucli;
/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import jklass.util.Rang;

public class HistogramaMult extends RepresentacioGrafica {

  public HistogramaMult(FitxerTex fitxer, CalculsBivNC calculs, String varNum, String varCateg,
                 Hashtable opc, int maxFigs, int inici, boolean categEtiq) throws OpcioIncorrectaException {

   super(fitxer);
   int i, m, k, n, lon, nUtil, max;
   boolean tancat;
   float minX, maxX, minY, maxY, maxRel;
   Rang[] llistaModal;
   float[] llistaVal;
   Histograma histo;
   String[] modals;
   String mod, graduaX;
   CalculsUnivNum calcNum;
   boolean freq;
   String op, limitsX, limitsY;

   // s'adapten les opcions pel cas multiple amb els valors de la variable actual
   limitsX = (String)opc.get("limitsX");
   if (limitsX.equals("observat")){
     opc.put("limitsX","def");
     opc.put("minX", Float.toString(calculs.obtenirMin()));
     opc.put("maxX", Float.toString(calculs.obtenirMax()));
    }
   limitsY = (String)opc.get("limitsY");
   if (limitsY.equals("complet")) {
     opc.put("limitsY", "def");
     opc.put("minY", "0");
     op = (String) opc.get("tipus");
     if (op.equals("efectius")) {
       opc.put("maxY", Float.toString(calculs.obtenirNumObjs()));
     }
     else {
       opc.put("maxY", "1");
     }
   } else if (limitsY.equals("auto")) {
     /**@todo Rellenar esta opcion cuando se decida añadirla
      *
      */
   }

   try {
     graduaX = (String)opc.get("graduaX");
     if ((categEtiq) && (inici == 0)) {
       // primera página del Histograma múltiple
       escriureInici(varNum, varCateg);
     }
     iniciMultiple(Constants.OFFSET_Y + maxFigs*(Constants.ALZADA-Constants.OFFSET_Y));
     i = 0; // index del nombre de figures
     // En el multiples es treballa amb el maxims i mins de la var global
     opc.put("min", new Float(calculs.obtenirMin()).toString());
     opc.put("max", new Float(calculs.obtenirMax()).toString());
     opc.put("minFreq", new Float(calculs.obtenirMinFreq()).toString());
     opc.put("maxFreq", new Float(calculs.obtenirMaxFreq()).toString());
     opc.put("maxRel", new Float(calculs.obtenirMaxRel()).toString());

     m = inici;
     max = inici + maxFigs;
     modals = calculs.obtenirModalitats();
     while (m < max) {
       mod = modals[m];
       calcNum = calculs.obtenirCalculsUniv(mod);
        if (categEtiq) {
         etiquetarCategoria(i, mod);
       }
       iniciSituacioHisto(i);
       histo = new Histograma(fitxer, null, calcNum, opc, true);
       fiSituacioHisto();
       i++;
       if (i == 1) {
         opc.put("graduaX", "no");
       }
       m++;
     }

     fiMultiple();
     if ((categEtiq) && ( max == calculs.obtenirNumModalitats())){
       escriureFi();
     }
     opc.put("graduaX", graduaX);
    }
    catch (IOException e) {
      logger.warning("ERROR: Error en la creació del histograma múltiple. " + e.getMessage());
    }

    // Es deixen les opcions com estaven originalment
    opc.put("limitsX",limitsX);
    opc.put("limitsY",limitsY);

  }

  private void escriureInici(String str1, String str2) throws IOException {
    fTex.escriureLin("\\begin{center}");
    if ((str1 == null) || (str2 == null)) {
      fTex.escriureLin(
          "{\\hspace{60pt}\\bf Histograma m\\'ultiple }\\vspace{0.5em}");
    }
    else {
      fTex.escriureLin("{\\hspace{60pt}\\bf Histograma m\\'ultiple per les variables " +
                       str1 + " i " + str2 + " }\\vspace{0.5em}");
    }
    fTex.escriureLin("\\noindent");
    fTex.escriureLin("\\setlength{\\unitlength}{0.95 pt}");
    fTex.escriureLin("\\scriptsize");
  }

  private void escriureFi() throws IOException {
    fTex.escriureLin("\\end{center} \\vfill");
    fTex.escriureLin("");
  }

  private void iniciMultiple(int alzada) throws IOException {
    fTex.escriureLin("");
    fTex.escriureLin("\\begin{picture}(" + (Constants.AMPLADA + Constants.OFFSET_X_MULT)+ ","
                     + alzada + ")(-" + Constants.OFFSET_X_MULT
                     + ",-" + Constants.OFFSET_Y_MULT + ")");

  }

  private void fiMultiple() throws IOException {
    fTex.escriureLin("\\end{picture}");
  }

  private void iniciSituacioHisto(int pos) throws IOException {
    fTex.escriureLin("");
    fTex.escriureLin("\\put(0, "+ pos * Constants.SEPARACI0_V + "){" );
  }

  private void fiSituacioHisto() throws IOException {
    fTex.escriureLin("}");
  }

  private void etiquetarCategoria(int pos, String categ) throws IOException {
    fTex.escriureLin("");
    fTex.escriureLin("\\put(-40, "+ (pos * Constants.SEPARACI0_V + Constants.ALZADA - 20) + "){" + fTex.adaptarATex(categ) + "}" );
  }

}

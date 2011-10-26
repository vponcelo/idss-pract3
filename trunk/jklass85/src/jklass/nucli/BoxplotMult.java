package jklass.nucli;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

public class BoxplotMult extends RepresentacioGrafica {
  public BoxplotMult(FitxerTex fitxer, CalculsBivNC calculs, String varNum, String varCateg,
                 Hashtable opc, int maxFigs, int inici, boolean categEtiq) throws OpcioIncorrectaException {

   super(fitxer);
   int i, m, numPags, numFigs, n, limit;
   boolean tancat;
   float min, max, me, q1, q3, diq;
   float[] dades;
   Boxplot boxp;
   String[] modals;
   String mod, gradua;
   CalculsUnivNum calcNum;

   BoxplotMult bm;
   String limits;

    // s'adapten les opcions pel cas multiple amb els valors de la variable actual
   limits = (String)opc.get("limits");
   if (limits.equals("observat")){
     opc.put("limits","def");
     opc.put("min", Float.toString(calculs.obtenirMin()));
     opc.put("max", Float.toString(calculs.obtenirMax()));
    }

   try {
     gradua = (String)opc.get("gradua");
     if ((categEtiq) && (inici == 0)) {
       // primera página del Boxplot múltiple
       escriureInici(varNum, varCateg);
     }
     iniciMultiple(Constants.OFFSET_Y + maxFigs*(Constants.ALZADA-Constants.OFFSET_Y));
     i = 0; // index del nombre de figures
     m = inici;
     limit = inici + maxFigs;
     modals = calculs.obtenirModalitats();
     while ( m < limit){
       // es dibuixen totes les categories encara q no tinguin dades
       mod = modals[m];
       calcNum = calculs.obtenirCalculsUniv(mod);
       // Dibuixem un Boxplot normal
       if (categEtiq){
         etiquetarCategoria(i, mod);
       }
       iniciSituacioBxp(i);
       boxp = new Boxplot(fitxer, null, calcNum, opc, true);
       fiSituacioBxp();
       i++;
       // només graduar el primer boxplot (i==0)
       if (i == 1) {
         opc.put("gradua", "no");
       }
       m++;
     }
     fiMultiple();
     if ((categEtiq) && (limit == calculs.obtenirNumModalitats())){
       escriureFi();
     }
     opc.put("gradua", gradua);
    }
    catch (IOException e) {
      logger.warning("ERROR: Error en la creació del boxplot. " + e.getMessage());
    }

    // Es deixen les opcions com estaven originalment
   opc.put("limits",limits);
  }

  private void escriureInici(String str1, String str2) throws IOException {
    //fTex.escriureLin("\\mbox{ } \\vfill");
    fTex.escriureLin("\\begin{center}");
    if ((str1 == null) || (str2 == null)) {
      fTex.escriureLin(
          "{\\hspace{60pt}\\bf Boxplot m\\'ultiple }\\vspace{0.5em}");
    }
    else {
      fTex.escriureLin("{\\hspace{60pt}\\bf Boxplot m\\'ultiple per les variables " +
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
    fTex.escriureLin("\\begin{picture}(" + (Constants.AMPLADA + Constants.OFFSET_X_MULT) + ","
                     + alzada + ")(-" + Constants.OFFSET_X_MULT
                     + ",-" + Constants.OFFSET_Y_MULT + ")");

  }
  private void fiMultiple() throws IOException {
    fTex.escriureLin("\\end{picture}");
  }

  private void iniciSituacioBxp(int pos) throws IOException {
    fTex.escriureLin("");
    fTex.escriureLin("\\put(0, "+ pos * (Constants.SEPARACI0_V) + "){" );
  }

  private void fiSituacioBxp() throws IOException {
    fTex.escriureLin("}");
  }

  private void etiquetarCategoria(int pos, String categ) throws IOException {
    fTex.escriureLin("");
    fTex.escriureLin("\\put(-40, " +
                     (pos * Constants.SEPARACI0_V + Constants.ALZADA - 20) +
                     "){" +
                     fTex.adaptarATex(categ) + "}");
  }

}
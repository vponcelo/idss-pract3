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

public class DiagramaBarresMult extends RepresentacioGrafica {

  public DiagramaBarresMult(FitxerTex fitxer, CalculsBivCC calculs,
                            String varCategX, String varCategY,
                            Hashtable opc, int maxFigs, int inici,
                            boolean categEtiq) throws OpcioIncorrectaException {

    super(fitxer);
    int i, m, numPags, numFigs, n, limit, j, lon;
    boolean tancat;
    String[] modals;
    String mod, graduaX;
    CalculsUnivCateg calcCat;
    String[] llistaModal;
    float max, maxFreq, maxRel;
    float[] llistaVal;
    boolean efect;
    DiagramaBarres dg;

    try {
     graduaX = (String)opc.get("graduaX");
     if ((categEtiq) && (inici == 0)) {
       // primera página del Diagrama de barres múltiple
       escriureInici(varCategX, varCategY);
     }
     iniciMultiple(Constants.OFFSET_Y +
                   maxFigs * (Constants.ALZADA - Constants.OFFSET_Y));
     i = 0; // index del nombre de figures
     m = inici;
     limit = inici + maxFigs;
     modals = calculs.obtenirModalitatsY();
     opc.put("maxFreq", new Float(calculs.obtenirMaxFreq()).toString());
     opc.put("maxRel", new Float(calculs.obtenirMaxRel()).toString());
     while (m < limit) {
       mod = modals[m];
       calcCat = calculs.obtenirCalculsUniv(mod);
      // Dibuixem un Diagrama de barres normal
       if (categEtiq) {
         etiquetarCategoria(i, mod);
       }
       iniciSituacioDB(i);
       dg = new DiagramaBarres(fitxer, null, calcCat, opc, true);
       fiSituacioDB();
       i++;
       if (i == 1) {
         opc.put("graduaX", "no");
       }
       m++;
     }
     fiMultiple();
     if ((categEtiq) && (limit == calculs.obtenirNumModalitatsY())){
       escriureFi();
     }
     opc.put("graduaX", graduaX);

     opc.remove("maxFreq");
     opc.remove("maxRel");
    }
    catch (IOException e) {
      logger.warning("ERROR: Error en la creació del diagrama de barres. " + e.getMessage());
    }
  }

  private void escriureInici(String str1, String str2) throws IOException {
    fTex.escriureLin("\\begin{center}");
    if ((str1 == null) || (str2 == null)) {
      fTex.escriureLin(
          "{\\hspace{60pt}\\bf Diagrama de barres m\\'ultiple }\\vspace{0.5em}");
    }
    else {
      fTex.escriureLin("{\\hspace{60pt}\\bf Diagrama de barres m\\'ultiple per les variables " +
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

  private void iniciSituacioDB(int pos) throws IOException {
    fTex.escriureLin("");
    fTex.escriureLin("\\put(0, "+ pos * (Constants.SEPARACI0_V) + "){" );
  }

  private void fiSituacioDB() throws IOException {
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

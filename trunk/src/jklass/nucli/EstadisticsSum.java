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

class EstadisticsSum extends RepresentacioNumerica {
  EstadisticsSum(FitxerTex fitxer, String var,CalculsUnivNum calculs, Hashtable opcs) throws IOException {
    super(fitxer);
    try {
      escriureIniciEstSum(null);
      escriureFilaEstSum("Nombre d'objectes",
                                calculs.obtenirNumObjs());

      escriureFilaEstSumInd("Nombre de dades mancants",
                                   calculs.obtenirNumMancants());
     escriureFilaEstSumInd("Nombre d'observacions \\'utils",
                                   calculs.obtenirNumUtils());
      if (opcs == null) {
        escriureFilaEstSum("Mitjana",
                           calculs.obtenirMitjana());
        // Estadistics Robustos
        escriureFilaEstSum("Mediana",
                                  calculs.obtenirMediana());
        escriureFilaEstSum("Primer quartil (Q1)",
                                  calculs.obtenirQ1());
        escriureFilaEstSum("Tercer quartil (Q3)",
                                  calculs.obtenirQ3());
        escriureFilaEstSum("Dist\\`ancia interquart\\'i\\lge lica",
                                  calculs.obtenirDistInterQ());
        escriureFilaEstSum("M\\'inim", calculs.obtenirMin());
        escriureFilaEstSum("M\\`axim", calculs.obtenirMax());
        escriureFilaEstSum("Amplitud",
                           calculs.obtenirAmplitud());
        escriureFilaEstSum("Vari\\`ancia",
                           calculs.obtenirVar());
        escriureFilaEstSum("Quasi-vari\\`ancia",
                                  calculs.obtenirQuasiVar());
        escriureFilaEstSum("Desviaci\\'o t\\'ipica",
                                  calculs.obtenirDesv());
        escriureFilaEstSum("Quasi-desviaci\\'o t\\'ipica",
                                  calculs.obtenirQuasiDesv());
        escriureFilaEstSum("Coeficient de variaci\\'o",
                                  calculs.obtenirCoefCorr());
      } else {
        if ( ( (Boolean) opcs.get("mitj")).booleanValue())
          escriureFilaEstSum("Mitjana",
                             calculs.obtenirMitjana());
          // Estadistics Robustos
        if ( ( (Boolean) opcs.get("me")).booleanValue())
          escriureFilaEstSum("Mediana",
                                    calculs.obtenirMediana());
        if ( ( (Boolean) opcs.get("q")).booleanValue()) {
          escriureFilaEstSum("Primer quartil (Q1)",
                                    calculs.obtenirQ1());
          escriureFilaEstSum("Tercer quartil (Q3)",
                                    calculs.obtenirQ3());
        }
        if ( ( (Boolean) opcs.get("dist_iq")).booleanValue())
          escriureFilaEstSum("Dist\\`ancia interquart\\'i\\lge lica",
                                    calculs.obtenirDistInterQ());
        if ( ( (Boolean) opcs.get("min")).booleanValue())
          escriureFilaEstSum("M\\'inim",
                             calculs.obtenirMin());
        if ( ( (Boolean) opcs.get("max")).booleanValue())
          escriureFilaEstSum("M\\`axim",
                             calculs.obtenirMax());
        if ( ( (Boolean) opcs.get("amp")).booleanValue())
          escriureFilaEstSum("Amplitud",
                             calculs.obtenirAmplitud());
        if ( ( (Boolean) opcs.get("var")).booleanValue())
          escriureFilaEstSum("Vari\\`ancia",
                                    calculs.obtenirVar());
        if ( ( (Boolean) opcs.get("qvar")).booleanValue())
          escriureFilaEstSum("Quasi-vari\\`ancia",
                                    calculs.obtenirQuasiVar());
        if ( ( (Boolean) opcs.get("desv")).booleanValue())
          escriureFilaEstSum("Desviaci\\'o t\\'ipica",
                                    calculs.obtenirDesv());
        if ( ( (Boolean) opcs.get("qdesv")).booleanValue())
          escriureFilaEstSum("Quasi-desviaci\\'o t\\'ipica",
                                    calculs.obtenirQuasiDesv());
        if ( ( (Boolean) opcs.get("coef")).booleanValue())
          escriureFilaEstSum("Coeficient de variaci\\'o",
                                    calculs.obtenirCoefCorr());
      }
      escriureFiEstSum();

    }
    catch (IOException e) {
      logger.warning(
          "ERROR: Error en la creació del fitxer Latex dels estadístics sumaris. " +
          e.getMessage());
      throw e;
    }
  }

  void escriureIniciEstSum(String str) throws IOException{
    fTex.escriureLin("\\begin{center}");
    fTex.escriureLin("\\begin{tabular}{|l|l|}");
    fTex.escriureLin("\\hline");
    if (str == null){
      fTex.escriureLin("\\multicolumn{2}{|l|}{\\bf Estad\\'istics sumaris } \\\\ ");
    } else {
      fTex.escriureLin("\\bf Estad\\'istics sumaris Variable: &{\\bf " + str +
                  "} \\\\");
    }
    fTex.escriureLin("\\hline");
    fTex.escriureLin("\\hline");
  }

  void escriureFilaEstSum(String val1, float val2) throws IOException{
    fTex.escriureLin("{\\bf "+ val1 + "} & " + fTex.formatejarReal(val2) + " \\\\ ");
  }

  void escriureFilaEstSumInd(String val1, float val2) throws IOException{
    fTex.escriureLin("{\\small\\hspace{3ex} " + val1 + "} & " + fTex.formatejarReal(val2) + " \\\\ ");
  }

  void escriureFiEstSum() throws IOException{
    fTex.escriureLin("\\hline");
    fTex.escriureLin("\\end{tabular}");
    fTex.escriureLin("\\end{center} \\vfill");
    fTex.escriureLin("");
  }

}
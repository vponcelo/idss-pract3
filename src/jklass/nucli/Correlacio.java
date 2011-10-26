package jklass.nucli;

import java.io.IOException;
/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

class Correlacio extends RepresentacioNumerica {
  Correlacio(FitxerTex fitxer, CalculsBivNN calculs, String varX, String varY) throws ParamIncorrecteException,CreacioFitxerException, IOException{
    super(fitxer);
    int[][] mancs;

     try {
       fTex.escriureLin("\\begin{center}");
       fTex.escriureLin("\\begin{tabular}{|c|c|c|}");
       fTex.escriureLin("\\hline");
       fTex.escriureLin("\\multicolumn{3}{|c|}{\\bf Correlaci\\'o per les variables " +
                   varX + " i " + varY + "} \\\\");
       fTex.escriureLin("\\hline");
       fTex.escriureLin("\\multicolumn{3}{|c|}{{\\bf r = " +
                   fTex.formatejarDouble(calculs.obtenirCorrel()) + "} ( coVar = " + fTex.formatejarDouble(calculs.obtenirCoVar()) + " )} \\\\");
       fTex.escriureLin("\\hline");
       fTex.escriureLin("\\hline");
       fTex.escriureLin(
           "\\multicolumn{3}{|c|}{\\bf Informaci\\'o sobre dades mancants}  \\\\");
       fTex.escriureLin("\\hline");
       fTex.escriureLin(" { "+ varX +" $ \\backslash $ "+ varY +"} & \\'jklass.util & mancant \\\\");
       fTex.escriureLin("\\hline");
       mancs = calculs.obtenirInfoMancants();
       fTex.escriureLin("\\'jklass.util & " + mancs[0][0] + " & " + mancs[0][1] + " \\\\");
       fTex.escriureLin("\\hline");
       fTex.escriureLin("mancant & " + mancs[1][0] + " & " + mancs[1][1] + " \\\\");
       fTex.escriureLin("\\hline");
       fTex.escriureLin("\\end{tabular}");
       fTex.escriureLin("\\end{center} \\vfill");

     }  catch (IOException e) {
       throw new CreacioFitxerException("Error d'escriptura: " + e.getMessage());
     }

   }

}
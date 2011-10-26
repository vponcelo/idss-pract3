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

class Resum5 extends RepresentacioNumerica {

  Resum5(FitxerTex fitxer, String var, CalculsUnivNum calculs) throws
      IOException {
    super(fitxer);

    float min, max, me, q1, q3;

    min = calculs.obtenirMin();
    max = calculs.obtenirMax();
    me = calculs.obtenirMediana();
    q1 = calculs.obtenirQ1();
    q3 = calculs.obtenirQ3();

    if (var == null) {
      fTex.escriureLin("\\begin{center} \\bf Resum en 5 n\\'umeros \\end{center} ");
    }
    else {
      fTex.escriureLin("\\begin{center} \\bf Resum en 5 n\\'umeros de la variable " +
                  var + " \\end{center} ");
    }
    fTex.escriureLin("\\begin{center}");
    fTex.escriureLin("\\begin{tabular}{c c c c c}");
    fTex.escriureLin(
        "{\\bf min} & {\\bf Q1} & {\\bf Me } & {\\bf Q3} & {\\bf max} \\\\");
    fTex.escriureLin(fTex.formatejarReal(min) + " & " + fTex.formatejarReal(q1) + " & " +
                fTex.formatejarReal(me) + " & " + fTex.formatejarReal(q3) + " & " +
                fTex.formatejarReal(max) + " \\\\");
    fTex.escriureLin("\\end{tabular}");
    fTex.escriureLin("\\end{center} \\vfill");
    fTex.escriureLin("");

  }

}
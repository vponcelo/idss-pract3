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

class TaulaContingencia extends RepresentacioNumerica {
  public TaulaContingencia(FitxerTex fitxer, CalculsBivCC calculs, String varCategX,
                                   String varCategY, Hashtable opcs, int maxX,
                                   int maxY, int iniciX, int iniciY) throws
      CreacioFitxerException {
    super(fitxer);
    String strTab = null, strTit = null, strLin = null, mod = null,opcio;
    char tipus = ' ';
    int i, j, x, y, utilsX, utilsTot;
    float freq = Float.NaN;
    String[] modalsX, modalsY;
    boolean fiX,fiY;
    CalculsUnivCateg calcCat;

    try {
      // S'obté el tipus de contingut de les caselles de les opcions
      // i s'escriu al fitxer
      strLin = new String("{Contingut de la casella: ");
      opcio = (String) opcs.get("tipus");
      if (opcio.compareTo("efectius") == 0) {
        strLin = strLin + "efectius absoluts";
        tipus = 'e';
      }
      else if (opcio.compareTo("freqsFil") == 0) {
        strLin = strLin + "freqs. condicionades a files";
        tipus = 'f';
      }
      else if (opcio.compareTo("freqsCol") == 0) {
        strLin = strLin + "freqs. condicionades a columnes";
        tipus = 'c';
      }
      //Es recupera de les opcions si cal generar el fitxer .tc
      if ( (iniciX == 0) && (iniciY == 0)) {
        // primera página de Taula de Contingència
        fTex.escriureLin("\\begin{center}");
        if ( (varCategX == null) || (varCategY == null)) {
          fTex.escriureLin(
              "{\\bf Taula de conting\\`encia }");
        }
        else {
          fTex.escriureLin(
              "{\\bf Taula de  conting\\`encia  per les variables " +
              fTex.adaptarATex(varCategX) + " i " + fTex.adaptarATex(varCategY) + " }");
        }

        fTex.escriureLin(" ");
        fTex.escriureLin(strLin + "}\\vspace{0.5em}");
        fTex.escriureLin(" ");
      }

      if ( (varCategX == null) || (varCategY == null)) {
        strTit = new String ("{\\bf X $ \\backslash $ Y} ");
      }
      else {
        strTit = new String ("{\\bf " + fTex.adaptarATex(varCategX) +" $ \\backslash $ "+ fTex.adaptarATex(varCategY) + " } ");
      }
      strTab = new String ("\\begin{tabular}{|c||");
      y = iniciY;
      modalsY = calculs.obtenirModalitatsY();
      utilsTot = calculs.obtenirNumObjs() - calculs.obtenirTotalMancantsY();
      for (i = 0; i < maxY; i++) {
        strTab = strTab + "c|";
        strTit = strTit + " & {\\bf " + fTex.adaptarATex(modalsY[y + i]) + "}";
      }
      fiY = (iniciY + maxY) == calculs.obtenirNumModalitatsY();
      if ( fiY ) {
        switch (tipus){
          case 'f':
            strTab = strTab + "|c|";
            strTit = strTit + " & {\\peq \\bf total} ";
            break;
          case 'c':
            strTab = strTab + "|c|";
            strTit = strTit + " & {\\peq \\bf marginal} ";
            break;
      }
        strTab = strTab + "|c|c|";
        strTit = strTit + " & {\\peq \\bf \\'utils} & {\\peq \\bf mancants} ";
      }
      strTab = strTab + "}";
      strTit = strTit + "\\\\";

      fTex.escriureLin(strTab);
      fTex.escriureLin("\\hline");
      fTex.escriureLin(strTit);
      fTex.escriureLin("\\hline\\hline");

      x = iniciX;
      for (i = 0; i < maxX; i++) {
        // totes les propietats auxiliars derivades de les categories d'Y
        // tenen la mateixa llista de modalitats d'X
        mod = calculs.obtenirCalculsUniv(modalsY[y]).obtenirModalitat(x + i);
        strLin = new String(fTex.adaptarATex(mod));
        utilsX =  calculs.obtenirTotalX(mod)- calculs.obtenirMancantsX(mod);
        for (j = 0; j < maxY; j++) {
          // calculem el contingut de la casella en funció del tipus seleccionat per l'usuari
          switch (tipus){
            case 'e':
              freq = calculs.obtenirCalculsUniv(modalsY[y + j]).obtenirFreq(x + i);
              break;
            case 'f':
              freq = calculs.obtenirCalculsUniv(modalsY[y + j]).obtenirFreq(x + i) / utilsX;
              break;
            case 'c':
              freq = calculs.obtenirCalculsUniv(modalsY[y + j]).obtenirFreq(x + i) / calculs.obtenirCalculsUniv(modalsY[y + j]).obtenirNumUtils();
             break;
          }
          strLin = strLin + " & " + fTex.formatejarReal(freq);
        }
        if (fiY) {
          switch (tipus){
            case 'f':
              strLin = strLin + " & 1" ;
              break;
            case 'c':
              strLin = strLin + " & " + fTex.formatejarReal((float)utilsX/utilsTot);
             break;
          }
          strLin = strLin + " & " + fTex.formatejarReal(utilsX)
              + " & " + fTex.formatejarReal(calculs.obtenirMancantsX(mod));
        }
        strLin = strLin + " \\\\";
        fTex.escriureLin(strLin);
        fTex.escriureLin("\\hline");
      }
      fiX = (( iniciX + maxX) == calculs.obtenirNumModalitatsX());
      if (fiX) {
        fTex.escriureLin("\\hline");
        // si s'ha seleccionat freqs. condicionades cal afegir una linea amb...
        switch (tipus){
          case 'f': //... marginals
            strLin = "{\\peq \\bf marginal}";
            for (j = 0; j < maxY; j++) {
              freq = (float) calculs.obtenirCalculsUniv(modalsY[y + j]).obtenirNumUtils() / utilsTot;
              strLin = strLin + " & " +
                  fTex.formatejarReal(freq);
            }
            if (fiY) {
              strLin = strLin + "  & 1 & & ";
            }
            strLin = strLin + " \\\\";
            fTex.escriureLin(strLin);
            fTex.escriureLin("\\hline\\hline");
            break;
          case 'c': //... totals
            strLin = "{\\peq \\bf total}";
            for (j = 0; j < maxY; j++) {
              strLin = strLin + " & 1 ";
            }
            if (fiY) {
              strLin = strLin + "  & 1 & & ";
            }
            strLin = strLin + " \\\\";
            fTex.escriureLin(strLin);
            fTex.escriureLin("\\hline\\hline");
            break;
        }

        // afegim la linia de utils
        strLin = new String("{\\peq \\bf \\'utils}");
        for (j = 0; j < maxY; j++) {
          strLin = strLin + " & " +
              fTex.formatejarReal(calculs.obtenirCalculsUniv(modalsY[y + j]).obtenirNumUtils());
        }
        if (fiY) {
          // si s'ha seleccionat freqs. condicionades cal afegir una columna
          if ((tipus == 'f')  || (tipus == 'c')){
            strLin = strLin + " & ";
          }
          strLin = strLin + " & " + fTex.formatejarReal(utilsTot) + " & ";
        }
        strLin = strLin + " \\\\";
        fTex.escriureLin(strLin);
        fTex.escriureLin("\\hline");

        // afegim la linia de mancants
        strLin = new String("{\\peq \\bf mancants}");
        for (j = 0; j < maxY; j++) {
          strLin = strLin + " & " +
                 fTex.formatejarReal(calculs.obtenirCalculsUniv(modalsY[y + j]).obtenirNumMancants());
        }
        if (fiY) {
          // si s'ha seleccionat freqs. condicionades cal afegir una columna
          if ( (tipus == 'f') || (tipus == 'c')) {
            strLin = strLin + " & ";
          }
          strLin = strLin + " & & " + fTex.formatejarReal(calculs.obtenirMancantsXY());
        }
        strLin = strLin + " \\\\";
        fTex.escriureLin(strLin);
        fTex.escriureLin("\\hline");
     }
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
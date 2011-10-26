package jklass.nucli;

import java.io.IOException;

/**
 * Classe que permet gestionar fitxers .tc en format KLASS, fitxers que contenen
 * la taula de contingència de dues variables categoriques simplificada
 * i en texte pla.
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

class FitxerTc extends Fitxer {
  FitxerTc(String nom) {
    super(nom);
    modificarNom(obtenirNom() + ".tc");
  }

  /**
   * Genera el contingut d'una taula de contingècia en format texte, separant
   * les files per salts de linia i les columnes per espais en blanc.
   * @param tipus tipus de taula de contingencia a generar ('e'=efectius absoluts;'f'= freqs. condicionades a files; 'c'= freqs. condicionades a columnes
   * @param calculs estructura que conté els calculs necessaris pel una Anàlisi Bivariant
   */
  void generaTaula(char tipus, CalculsBivCC calculs, String varCategX,
                                   String varCategY) throws IOException {
    String str = null, mod = null;

    int i, j, utilsX, utilsTot, nModsY;
    float freq = Float.NaN;
    String[] modalsX, modalsY;

    // Es genera la 1ª
    str = new String(varCategX + "\\" + varCategY);
    modalsY = calculs.obtenirModalitatsY();
    nModsY = calculs.obtenirNumModalitatsY();
    utilsTot = calculs.obtenirNumObjs() - calculs.obtenirTotalMancantsY();
    for (i = 0; i < nModsY; i++) {
      str = str + " " + modalsY[i];
    }
    switch (tipus){
      case 'f':
        str = str + " total";
        break;
      case 'c':
        str = str + " marginal";
        break;
      case 'e':
        str =  str + " utils";
        break;
    }
    escriureLin(str);

    //es generen les files de la taula per cada modalitat de var X
    for (i = 0; i < calculs.obtenirNumModalitatsX(); i++) {
      // totes les propietats auxiliars derivades de les categories d'Y
      // tenen la mateixa llista de modalitats d'X
      mod = calculs.obtenirCalculsUniv(modalsY[0]).obtenirModalitat(i);
      str = new String(mod);
      utilsX = calculs.obtenirTotalX(mod) - calculs.obtenirMancantsX(mod);
      for (j = 0; j < nModsY; j++) {
        // calculem el contingut de la casella en funció del tipus de taula de contingencia
        switch (tipus) {
          case 'e':
            freq = calculs.obtenirCalculsUniv(modalsY[j]).obtenirFreq(i);
            break;
          case 'f':
            freq = calculs.obtenirCalculsUniv(modalsY[j]).obtenirFreq(i) /
                utilsX;
            break;
          case 'c':
            freq = calculs.obtenirCalculsUniv(modalsY[j]).obtenirFreq(i) /
                calculs.obtenirCalculsUniv(modalsY[j]).obtenirNumUtils();
            break;
        }
        str = str + " " + FitxerTex.formatejarReal(freq);
      }
      // la columna final de cada fila depen del tipus de taula de contingencia
      switch (tipus) {
        case 'e':
          str = str + " " + FitxerTex.formatejarReal(utilsX);
          break;
        case 'f':
          str = str + " 1";
          break;
        case 'c':
          str = str + " " + FitxerTex.formatejarReal( (float) utilsX / utilsTot);
          break;
      }
      // s'escriu fa fila construida
      escriureLin(str);
    }

    // Es genera la darrera fila en funcio del tipus de taula de contingencia,
    // posant...
    switch (tipus){
      case 'e':
        str = new String("utils");
        for (j = 0; j < nModsY; j++) {
          str = str + " " +
              FitxerTex.formatejarReal(calculs.obtenirCalculsUniv(modalsY[j]).
                                       obtenirNumUtils());
        }
        str = str + " " + FitxerTex.formatejarReal(utilsTot);
        break;
      case 'f': //... marginals
         str = new String("marginal");
        for (j = 0; j < nModsY; j++) {
          freq = (float) calculs.obtenirCalculsUniv(modalsY[j]).obtenirNumUtils() / utilsTot;
          str = str + " " + FitxerTex.formatejarReal(freq);
        }
        str = str + " 1";
        break;
      case 'c': //... totals
        str = new String("total");
        for (j = 0; j < nModsY; j++) {
          str = str + " 1 ";
        }
        str = str + " 1";
        break;
    }
    escriureLin(str);
  }
}
package jklass.nucli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.util.logging.*;
import java.util.LinkedList;

import jklass.util.Rang;
import jklass.util.Opcions;
import java.util.*;

/**
 * Classe que s'encarrega de generar el document Latex corresponent a partir dels
 * calculs que s'han realitzat. Tota la sortida generada es posa en un únic fitxer.
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas,Laia Riera Guerra
 * @version v.4
 */

public class GeneradorTex {
  private static Logger logger=Logger.getLogger(GeneradorTex.class.getName());
  private FitxerTex fTex;

  public GeneradorTex(FitxerTex fitxer) {
    fTex = fitxer;
  }
  public FitxerTex getFitxerTex(){
		return this.fTex;
	}
  void escriureTitol(String titol) throws IOException{
    String str;

    fTex.escriureLin("\\mbox{ } \\vfill");
    str = "\\begin{center} \\Large \\bf " + titol + " \\end{center} \\vfill";
    fTex.escriureLin(str);
    fTex.escriureLin("");
    logger.finer("Titol escrit: " + str);
  }

  void escriureSubtitol(String titol) throws IOException{
    String str;

    fTex.escriureLin("\\vspace{3ex}");
    fTex.escriureLin("\\mbox{ } \\vfill");
    str = "\\begin{center} \\Large \\bf " + titol + " \\end{center}";
    fTex.escriureLin(str);
    fTex.escriureLin("");
    logger.finer("Titol escrit: " + str);
  }

  private String adaptarRuta(String ruta){
    String str;
    String[] llistaStr;
    int i=1;

    llistaStr = ruta.split("[\\\\]");
    str = new String(llistaStr[0]);
    logger.finer("Long split: " + llistaStr.length);
    while (i < llistaStr.length){
      str = str + "$\\backslash$"+ new String(llistaStr[i]);
      i++;
    }
    logger.finer("Després split: " + str);
    return str;
  }

  void generarLtxUnivarPerVars(ArrayList ordre, CalculsUniv[][] calculs, String[][] llistaVar, Vector[] llistaEstads, Opcions opc, boolean esPart) throws CreacioFitxerException, IOException, OpcioIncorrectaException {
    int i, j, est, lon;
    int[] mostra = new int[ordre.size()];
    String[] tipus = new String[ordre.size()];

    Arrays.fill(mostra, -1);
    lon = llistaVar[0].length;
    // Tractament de la llista de vars Numeriques
    for (i = 0; i < lon; i++) {
      j = ordre.indexOf(llistaVar[0][i]);
      mostra[j] = i;
      tipus[j] = Constants.PROP_NUMERICA;
    }
    // Tractament de la llista de vars Categoriques
    lon = llistaVar[1].length;
    for (i = 0; i < lon; i++) {
      j = ordre.indexOf(llistaVar[1][i]);
      mostra[j] = i;
      tipus[j] = Constants.PROP_CATEGORICA;
    }
    // Es genera el document Latex amb els resultats obtinguts
    if (!esPart) {
  // Si no forma part d'un altre doc cal afegir la capçalera i preparar-lo per escriure
    fTex.copiarCapsaleraTex(Constants.FITXER_CAP_GRAL);
    fTex.obrirPerEscriure(true);
    }
    escriureTitol("An\\`alisi descriptiva univariant");

    lon = ordre.size();
    for (i = 0; i < lon; i++){
      if (mostra[i] != -1){
        if (tipus[i].equals(Constants.PROP_NUMERICA)) {
          escriureSubtitol("Variable " + ordre.get(i));
          logger.finer("Generat codi Latex per: ");
          for (j = 0; j < llistaEstads[0].size(); j++) {
            est = ( (Integer) llistaEstads[0].get(j)).intValue();
            switch (est) {
              case Opcions.ESTAD_SUM:
                new EstadisticsSum(fTex, null, (CalculsUnivNum) calculs[0][mostra[i]],
                                   opc.obtenirOpcionsEstad(est));
                logger.finer("-- Estadistics sumaris.");
                break;
              case Opcions.RESUM_5:
                new Resum5(fTex, null, (CalculsUnivNum) calculs[0][mostra[i]]);
                logger.finer("-- Resum  en 5 nums.");
                break;
              case Opcions.HISTO:
                new Histograma(fTex, null, (CalculsUnivNum) calculs[0][mostra[i]],
                               opc.obtenirOpcionsEstad(est), false);
                logger.finer("-- Histograma");
                break;
              case Opcions.BOXPLOT:
                new Boxplot(fTex, null, (CalculsUnivNum) calculs[0][mostra[i]],
                            opc.obtenirOpcionsEstad(est), false);
                logger.finer("-- Boxplot");
                break;
            }
          }
        } else { //CATEGORICA
          escriureSubtitol("Variable " + ordre.get(i));
          for (j = 0; j < llistaEstads[1].size(); j++) {
            est = ( (Integer) llistaEstads[1].get(j)).intValue();
            switch (est) {
              case Opcions.TAULA_FREQS:
                new TaulaFreqs(fTex, null, (CalculsUnivCateg)calculs[1][mostra[i]], opc.obtenirOpcionsEstad(est));
                break;
              case Opcions.DIAGR_BARRES:
                new DiagramaBarres(fTex, null, (CalculsUnivCateg)calculs[1][mostra[i]], opc.obtenirOpcionsEstad(est), false);
                break;
            }
          }
        }
      }
    }
    if (!esPart) {
      fTex.finalitzarTex();
      fTex.tancarEsc();
    }
  }

  void generarLtxBivarPerVars(ArrayList ordre, CalculsBiv[][] calculs,
                              String[][] llistaVar,
                              Vector[] llistaEstads, Opcions opc,
                              boolean esPart) throws
      CreacioFitxerException, OpcioIncorrectaException,
      ParamIncorrecteException {
    int i, j, k, l, m, est, lon, nX, nY, maxModsY, numTauX, numTauY, nModX, nModY,
        ultModsX, ultModsY, numPags, ultFigs, numFigs, iniciV, iniciH;
    CalculsBivNC calcNC;
    CalculsBivCC calcCC;
    CalculsBivNN calcNN;
    int[][] mostra;
    String[][] tipus; //Tipus de Bivariant
    Hashtable opcEst;

    try {
      lon = ordre.size();
      mostra = new int[lon][lon];
      tipus = new String[lon][lon];
      // inicialització de la matriu de les combinacions de vars a mostrar
      for (i = 0; i < lon; i++) {
        for (j = 0; j < lon; j++) {
          mostra[i][j] = -1;
        }
      }
      // Tractament de la llista de vars Num / Num
      if ( (llistaVar[0] == null) || (llistaVar[1] == null)) {
        lon = 0;
      }
      else {
        lon = llistaVar[0].length;
        if (llistaVar[1].length != lon)
          throw new ParamIncorrecteException("Nombre de params. incorrecte");
      }
      for (i = 0; i < lon; i++) {
        if (llistaVar[0][i] != null) {
          j = ordre.indexOf(llistaVar[0][i]);
          k = ordre.indexOf(llistaVar[1][i]);
          mostra[j][k] = i;
          tipus[j][k] = Constants.NUMxNUM;
        }
      }
      // Tractament de la llista de vars Num / Categ
      if ( (llistaVar[2] == null) || (llistaVar[3] == null)) {
        lon = 0;
      }
      else {
        lon = llistaVar[2].length;
        if (llistaVar[3].length != lon)
          throw new ParamIncorrecteException("Nombre de params. incorrecte");
      }
      for (i = 0; i < lon; i++) {
        if (llistaVar[2][i] != null) {
          j = ordre.indexOf(llistaVar[2][i]);
          k = ordre.indexOf(llistaVar[3][i]);
          mostra[j][k] = i;
          tipus[j][k] = Constants.NUMxCAT;
        }
      }
      // Tractament de la llista de vars Categ / Categ
      if ( (llistaVar[4] == null) || (llistaVar[5] == null)) {
        lon = 0;
      }
      else {
        lon = llistaVar[4].length;
        if (llistaVar[5].length != lon)
          throw new ParamIncorrecteException("Nombre de params. incorrecte");
      }
      for (i = 0; i < lon; i++) {
        if (llistaVar[4][i] != null) {
          j = ordre.indexOf(llistaVar[4][i]);
          k = ordre.indexOf(llistaVar[5][i]);
          mostra[j][k] = i;
          tipus[j][k] = Constants.CATxCAT;
        }
      }
      // Es genera el document Latex amb els resultats obtinguts
      if (!esPart) {
        // Si no forma part d'un altre doc cal afegir la capçalera i preparar-lo per escriure
        fTex.copiarCapsaleraTex(Constants.FITXER_CAP_GRAL);
        fTex.obrirPerEscriure(true);
      }
      escriureTitol("An\\`alisi descriptiva bivariant");
      lon = ordre.size();
      for (i = 0; i < lon; i++) {
        for (j = 0; j < lon; j++) {
          if (mostra[i][j] != -1) {
            if (tipus[i][j].equals(Constants.NUMxNUM)) {
              for (k = 0; k < llistaEstads[0].size(); k++) {
                est = ( (Integer) llistaEstads[0].get(k)).intValue();
                switch (est) {
                  case Opcions.CORREL:
                    new Correlacio(fTex, (CalculsBivNN) calculs[0][mostra[i][j]],
                                   llistaVar[0][mostra[i][j]],
                                   llistaVar[1][mostra[i][j]]);
                    break;
                  case Opcions.PLOT:
                    int nMarques = 3;
                    String graduacio = "R";
                    calcNN = (CalculsBivNN) calculs[0][mostra[i][j]];
                    new Plot(fTex, calcNN.obtenirProps(), calcNN.obtenirDadesX(),
                             calcNN.obtenirDadesY(),
                             opc.obtenirOpcionsEstad(est), nMarques, graduacio);
                    break;
                }
              }
            }
            else if (tipus[i][j].equals(Constants.NUMxCAT)) {
              calcNC = (CalculsBivNC) calculs[1][mostra[i][j]];
              for (k = 0; k < llistaEstads[1].size(); k++) {
                est = ( (Integer) llistaEstads[1].get(k)).intValue();
                nY = calcNC.obtenirNumModalitats();
                // nombre de págines senceres
                numPags = nY / Constants.MAX_FIG_VERT;
                // nombre de figures a la última pàgina
                ultFigs = nY % Constants.MAX_FIG_VERT;
                iniciV = 0; //index de la modalitat per la qual es comença
                // es generen totes les pàgines
                for (l = 0; ( (l <= numPags) && (iniciV < nY)); l++) {
                  if ( (l == numPags) && (ultFigs != 0)) {
                    numFigs = ultFigs;
                  }
                  else {
                    numFigs = Constants.MAX_FIG_VERT;
                  }
                  if (l > 0) {
                    fTex.escriureLin("");
                    fTex.escriureLin("{\\hspace{-8.5cm} \\large $\\wr$}");
                    fTex.escriureLin("\\newpage");
                    fTex.escriureLin("{\\hspace{-8.5cm} \\large $\\wr$}");
                    fTex.escriureLin("");
                    fTex.escriureLin("\\mbox{ }");
                  }

                  switch (est) {
                    case Opcions.DESCR_GR:
                      new DescrGrups(fTex, calcNC, llistaVar[2][mostra[i][j]],
                                     llistaVar[3][mostra[i][j]],
                                     opc.obtenirOpcionsEstad(est),
                                     numFigs, iniciV, true);
                      break;
                    case Opcions.HISTO_MULT:
                      new HistogramaMult(fTex, calcNC,
                                         llistaVar[2][mostra[i][j]],
                                         llistaVar[3][mostra[i][j]],
                                         opc.obtenirOpcionsEstad(est),
                                         numFigs, iniciV, true);
                      break;
                    case Opcions.BOXP_MULT:
                      new BoxplotMult(fTex, calcNC,
                                      llistaVar[2][mostra[i][j]],
                                      llistaVar[3][mostra[i][j]],
                                      opc.obtenirOpcionsEstad(est), numFigs,
                                      iniciV, true);
                      break;
                  }
                  iniciV = iniciV + numFigs;
                }

              }
            } // fi NUMxCAT
            else if (tipus[i][j].equals(Constants.CATxCAT)) {
              calcCC = (CalculsBivCC) calculs[2][mostra[i][j]];
              for (k = 0; k < llistaEstads[2].size(); k++) {
                est = ( (Integer) llistaEstads[2].get(k)).intValue();
                nY = calcCC.obtenirNumModalitatsY();
                switch (est) {
                  case Opcions.T_CONTINGENCIA:
                    opcEst = opc.obtenirOpcionsEstad(est);
                    nX = calcCC.obtenirNumModalitatsX();
                    maxModsY = new Integer( (String) opc.obtenirOpcionsEstad(
                        est).get("nmods")).intValue();
                    numTauY = nY / maxModsY;
                    ultModsY = nY % maxModsY;
                    // cal restar 3 al nombre max. de  files que podem posar en un tabular per reservarles per:
                    // 1 - linia per X\Y ...
                    // 2 - linia per utils
                    // 3 - linia per mancants
                    numTauX = nX / (Constants.MAX_FILES_TABV - 3);
                    ultModsX = nX % (Constants.MAX_FILES_TABV - 3);
                    iniciV = 0; //index de la modalitat d'X per la qual es comença
                    // es generen totes les taules necessaries per la llista de
                    // modalitats d'X
                    for (l = 0; ( (l <= numTauX) && (iniciV < nX)); l++) {
                      if ( (l == numTauX) && (ultModsX != 0)) {
                        nModX = ultModsX;
                      }
                      else {
                        nModX = (Constants.MAX_FILES_TABV - 3);
                      }
                      if (l > 0) {
                        fTex.escriureLin("");
                        fTex.escriureLin("{\\large $\\wr$}");
                        fTex.escriureLin("");
                        fTex.escriureLin("\\mbox{ }");
                      }

                      iniciH = 0; //index de la modalitat d'Y per la qual es comença
                      // es generen totes les taules necessaries per la llista de
                      // modalitats d'Y per el grup actual de modalitats d'X
                      for (m = 0; ( (m <= numTauY) && (iniciH < nY)); m++) {
                        if ( (m == numTauY) && (ultModsY != 0)) {
                          nModY = ultModsY;
                        }
                        else {
                          nModY = maxModsY;
                        }
                        if (m > 0) {
                          fTex.escriureLin("");
                          fTex.escriureLin("{\\large $\\wr$}");
                          fTex.escriureLin("");
                          fTex.escriureLin("\\mbox{ }");
                        }
                        new TaulaContingencia(fTex, calcCC,
                                              llistaVar[4][mostra[i][j]],
                                              llistaVar[5][mostra[i][j]],
                                              opcEst,
                                              nModX, nModY,
                                              iniciV, iniciH);

                        iniciH = iniciH + nModY;
                      }
                      iniciV = iniciV + nModX;
                    }
                    // Si s'ha demanat es genera un fitxer extra .tc amb la
                    // taula en format texte (en el mateix directori que el
                    // fitxer Tex)
                    if (( (Boolean) opcEst.get("fitxer")).booleanValue()) {
                      FitxerTc fTc = new FitxerTc(fTex.obtenirDirAbsolut()+ System.getProperty("file.separator")+ llistaVar[4][mostra[i][j]] + "_" + llistaVar[5][mostra[i][j]]);
                      fTc.obrirPerEscriure(false);
                      char tipusTc = ' ';
                      String opcio = (String) opcEst.get("tipus");
                      if (opcio.compareTo("efectius") == 0) {
                         tipusTc = 'e';
                      }
                      else if (opcio.compareTo("freqsFil") == 0) {

                        tipusTc = 'f';
                      }
                      else if (opcio.compareTo("freqsCol") == 0) {
                        tipusTc = 'c';
                      }
                      fTc.generaTaula(tipusTc, calcCC, llistaVar[4][mostra[i][j]],
                                              llistaVar[5][mostra[i][j]]);
                      fTc.tancarEsc();
                    }
                    break;

                  case Opcions.D_BAR_MULT:
                    // nombre de págines senceres
                    numPags = nY / Constants.MAX_FIG_VERT;
                    // nombre de figures a la última pàgina
                    ultFigs = nY % Constants.MAX_FIG_VERT;
                    iniciV = 0; //index de la modalitat per la qual es comença
                    // es generen totes les pàgines
                    for (l = 0; ( (l <= numPags) && (iniciV < nY)); l++) {
                      if ( (l == numPags) && (ultFigs != 0)) {
                        numFigs = ultFigs;
                      }
                      else {
                        numFigs = Constants.MAX_FIG_VERT;
                      }
                      if (l > 0) {
                        fTex.escriureLin("");
                        fTex.escriureLin("{\\hspace{-8.5cm} \\large $\\wr$}");
                        fTex.escriureLin("\\newpage");
                        fTex.escriureLin("{\\hspace{-8.5cm} \\large $\\wr$}");
                        fTex.escriureLin("");
                        fTex.escriureLin("\\mbox{ }");
                      }
                      new DiagramaBarresMult(fTex, calcCC,
                                             llistaVar[4][mostra[i][j]],
                                             llistaVar[5][mostra[i][j]],
                                             opc.obtenirOpcionsEstad(est),
                                             numFigs,
                                             iniciV, true);

                      iniciV = iniciV + numFigs;
                    }
                    break;
                }
              }
            }
          }
        }
      }

      if (!esPart) {
        fTex.finalitzarTex();
        fTex.tancarEsc();
      }
    }
    catch (CreacioFitxerException e) {
      throw e;
    }
    catch (ParamIncorrecteException e) {
      throw e;
    }
    catch (IOException e) {
      throw new CreacioFitxerException("Error d'escriptura: " + e.getMessage());
    }
   }

   void generarLtxTrivarPerVars(ArrayList ordre, CalculsTriv[][] calculs,
                                      String[][] llistaVar,
                                      Vector[] llistaEstads, Opcions opc, boolean esPart) throws
      CreacioFitxerException, OpcioIncorrectaException,
      ParamIncorrecteException {
    int i, j, k, l, m, n, est, lon, nX, nY, maxModsY, numTauX, numTauY, nModX, nModY,
         ultModsX, ultModsY, numPags, ultFigs, numFigs, iniciV, iniciH;
    CalculsTrivCCN calcCCN;
    int[][][] mostra;
    String[][][] tipus;

    try {
      lon = ordre.size();
      mostra = new int[lon][lon][lon];
      tipus = new String[lon][lon][lon];
      // inicialització de la matriu de les combinacions de vars a mostrar
      for (i = 0; i < lon; i++) {
        for (j = 0; j < lon; j++) {
          for (k = 0; k < lon; k++) {
            mostra[i][j][k] = -1;
          }
        }
      }
      // Tractament de la llista de vars Num / Num / Categ
       if ( (llistaVar[0] == null) || (llistaVar[1] == null) || (llistaVar[2] == null)) {
         lon = 0;
       }
       else {
         lon = llistaVar[0].length;
         if ((llistaVar[1].length != lon) || (llistaVar[2].length != lon) )
           throw new ParamIncorrecteException("Nombre de params. incorrecte");
       }
       for (i = 0; i < lon; i++) {
         if (llistaVar[0][i] != null) {
           j = ordre.indexOf(llistaVar[0][i]);
           k = ordre.indexOf(llistaVar[1][i]);
           l = ordre.indexOf(llistaVar[2][i]);
           mostra[j][k][l] = i;
           tipus[j][k][l] = Constants.NUMxNUMxCAT;
         }
       }
       // Tractament de la llista de vars Categ / Categ / Num
       if ((llistaVar[3] == null) || (llistaVar[4] == null) || (llistaVar[5] == null)) {
         lon = 0;
       } else {
         lon = llistaVar[3].length;
         if ( (llistaVar[4].length != lon) || (llistaVar[5].length != lon))
           throw new ParamIncorrecteException("Nombre de params. incorrecte");
       }
       for (i = 0; i < lon; i++) {
         if (llistaVar[3][i] != null) {
           j = ordre.indexOf(llistaVar[3][i]);
           k = ordre.indexOf(llistaVar[4][i]);
           l = ordre.indexOf(llistaVar[5][i]);
           mostra[j][k][l] = i;
           tipus[j][k][l] = Constants.CATxCATxNUM;
         }
       }
      if (!esPart) {
        // Es genera el document Latex amb els resultats obtinguts
        fTex.copiarCapsaleraTex(Constants.FITXER_CAP_COLOR);
        fTex.obrirPerEscriure(true);
      }
      escriureTitol("An\\`alisi descriptiva trivariant");
      lon = ordre.size();
      for (i = 0; i < lon; i++) {
        for (j = 0; j < lon; j++) {
          for (k = 0; k < lon; k++) {
            if (mostra[i][j][k] != -1) {
              if (tipus[i][j][k].equals(Constants.NUMxNUMxCAT)) {
                for (l = 0; l < llistaEstads[0].size(); l++) {
                  est = ( (Integer) llistaEstads[0].get(l)).intValue();
                  switch (est) {
                    case Opcions.LETTERPLOT:
                      int nMarques = 3;
                      String graduacio = "R";
                      new LetterPlot(fTex, (CalculsTrivNNC)calculs[0][mostra[i][j][k]], opc.obtenirOpcionsEstad(est), nMarques, graduacio);
                      break;
                  }
                }
              }
              else if (tipus[i][j][k].equals(Constants.CATxCATxNUM)) {
                calcCCN = (CalculsTrivCCN) calculs[1][mostra[i][j][k]];
                for (l = 0; l < llistaEstads[1].size(); l++) {
                  est = ( (Integer) llistaEstads[1].get(l)).intValue();
                  nY = calcCCN.obtenirNumModalitatsY();
                  switch (est) {
                    case Opcions.TAULA_CCN:
                      nX = calcCCN.obtenirNumModalitatsX();
                      maxModsY = new Integer( (String) opc.obtenirOpcionsEstad(
                          est).
                                             get("nmods")).intValue();
                      numTauY = nY / maxModsY;
                      ultModsY = nY % maxModsY;
                      // cal restar 1 al nombre max. de  files que podem posar en un tabular per reservarles per:
                      // 1 - linia per X\Y ...
                      numTauX = nX / (Constants.MAX_FILES_TABV - 3);
                      ultModsX = nX % (Constants.MAX_FILES_TABV - 3);
                      iniciV = 0; //index de la modalitat d'X per la qual es comença
                      // es generen totes les taules necessaries per la llista de
                      // modalitats d'X
                      for (m = 0; ( (m <= numTauX) && (iniciV < nX)); m++) {
                        if ( (m == numTauX) && (ultModsX != 0)) {
                          nModX = ultModsX;
                        }
                        else {
                          nModX = (Constants.MAX_FILES_TABV - 3);
                        }
                        if (m > 0) {
                          fTex.escriureLin("");
                          fTex.escriureLin("{\\large $\\wr$}");
                          fTex.escriureLin("");
                          fTex.escriureLin("\\mbox{ }");
                        }

                        iniciH = 0; //index de la modalitat d'Y per la qual es comença
                        // es generen totes les taules necessaries per la llista de
                        // modalitats d'Y per el grup actual de modalitats d'X
                        for (n = 0; ( (n <= numTauY) && (iniciH < nY)); n++) {
                          if ( (n == numTauY) && (ultModsY != 0)) {
                            nModY = ultModsY;
                          }
                          else {
                            nModY = maxModsY;
                          }
                          if (n > 0) {
                            fTex.escriureLin("");
                            fTex.escriureLin("{\\large $\\wr$}");
                            fTex.escriureLin("");
                            fTex.escriureLin("\\mbox{ }");
                          }
                          new TaulaCCN(fTex, calcCCN,
                                       llistaVar[3][mostra[i][j][k]],
                                       llistaVar[4][mostra[i][j][k]],
                                       llistaVar[5][mostra[i][j][k]],
                                       opc.obtenirOpcionsEstad(est), nModX,
                                       nModY, iniciV, iniciH);

                          iniciH = iniciH + nModY;
                        }
                        iniciV = iniciV + nModX;
                      }
                      break;
                  }
                }
              }
            } // if mostra
          }
        }
      }
      if (!esPart) {
        fTex.finalitzarTex();
        fTex.tancarEsc();
      }
     }
     catch (CreacioFitxerException e) {
       throw e;
     }
     catch (ParamIncorrecteException e) {
       throw e;
     }
     catch (IOException e) {
       throw new CreacioFitxerException("Error d'escriptura: " + e.getMessage());
     }
   }

   void generarLtxClassesPerVars(ArrayList ordre, CalculsClass[] calculs, String[] llVarsClas,
                                        Vector[] llistaEstads, Opcions opc, boolean esPart) throws
       CreacioFitxerException, OpcioIncorrectaException,
       ParamIncorrecteException {
     int i, j, lon;
     int[] mostra = new int[ordre.size()];

     Arrays.fill(mostra, -1);
     lon = llVarsClas.length;
     // Tractament de la llista de vars de classe
     for (i = 0; i < lon; i++) {
       j = ordre.indexOf(llVarsClas[i]);
       mostra[j] = i;
     }

     // Es genera el document Latex per cadascuna de les variables de classe
     try {
       if (!esPart) {
         fTex.copiarCapsaleraTex(Constants.FITXER_CAP_CLASS);
         fTex.obrirPerEscriure(true);
       }
       else {
         // cal posar les pàgs en horitz
         fTex.escriureLin("\\begin{landscape}");
       }
       escriureTitol("An\\`alisi descriptiva per classes");

       lon = ordre.size();
       for (i = 0; i < lon; i++) {
         if (mostra[i] != -1){
           generarLtxVarClasse(ordre, calculs[mostra[i]], llistaEstads, opc);
         }
       }
       if (!esPart) {
         fTex.finalitzarTex();
         fTex.tancarEsc();
       }
       else {
         fTex.escriureLin("\\end{landscape}");
       }
     }
     catch (IOException e) {
       throw new CreacioFitxerException("Error d'escriptura: " + e.getMessage());
     }

   }

   private void generarLtxVarClasse(ArrayList ordre, CalculsClass calculs,
                                      Vector[] llistaEstads, Opcions opc) throws
       CreacioFitxerException, OpcioIncorrectaException,
       ParamIncorrecteException {
     CalculsBiv[][] calcsBiv = calculs.obtenirCalculBiv();
     String[][] llistaVar = calculs.obtenirLlistaVars();
     Particio par = calculs.obtenirParticio();
     boolean vista, hihaVarsN;
     int i, j, k, l, iniM, m, n, est, nVarsN, nVarsC, lon, nPags1, ultVars, iniVar,
         nPags2, ultFigs, nFigs, nClass =0, inici, maxH, maxClass;
     CalculsBivNC calcNC = null;
     CalculsBivCC calcCC = null;
     String nomClass = null, str0 ="", str1 = "";
     String[] llMods;
     Vector objs;
     int[][] mostra;
     String[][] tipus;

     try {
       if ((llistaVar[3] != null) && (llistaVar[3].length > 0)){
         nomClass = llistaVar[3][0];
       }
       else if ((llistaVar[5] != null) && (llistaVar[5].length > 0)) {
         nomClass = llistaVar[5][0];
       }
       else if (par != null) {
         nomClass = par.obtenirVar();
       } else {
         throw new ParamIncorrecteException("No hi ha variable de classe.");
       }

       fTex.escriureLin("\\newpage");
       escriureSubtitol("Variable de classe: " + fTex.adaptarATex(nomClass));

       if (par != null){
         // Se recorre la lista de calculos estadísticos sobre la var. de clase
         for (j = 0; j < llistaEstads[0].size(); j++) {
           est = ( (Integer) llistaEstads[0].get(j)).intValue();
           switch (est) {
            case Opcions.DESCR_EXTENSIONAL:
              llMods = par.obtenirModalitats();
              fTex.escriureLin("{\\noindent \\bf Descripci\\'o extensional de les classes: }");
              if (((Boolean) opc.obtenirOpcionsEstad(Opcions.DESCR_EXTENSIONAL).
                        get("tabular")).booleanValue())
              {
                fTex.escriureLin("\\begin{center}");
                fTex.escriureLin(
                    "\\begin{tabular}{|p{0.15\\textwidth}|p{0.85\\textwidth}|}");
                fTex.escriureLin("\\hline");
                fTex.escriureLin("\\bf Classe & \\bf Objectes \\\\");
                fTex.escriureLin("\\hline");
                for (k = 0; k < llMods.length; k++) {
                  str0 = fTex.adaptarATex(llMods[k]) + " & " ;
                  objs = par.obtenirObjs(llMods[k]);
                  for (l = 0; l < objs.size(); l++){
                    if (l > 0) {
                      str0 = str0 + ", " +  fTex.adaptarATex((String)objs.get(l));
                    } else {
                      str0 = str0 +  fTex.adaptarATex((String)objs.get(l));
                    }
                  }
                  str0 = str0 + " \\\\";
                  fTex.escriureLin(str0);
                  fTex.escriureLin("\\hline");
                }
                fTex.escriureLin("\\end{tabular}");
                fTex.escriureLin("\\end{center}");
              }
              else {
                fTex.escriureLin("\\begin{description}");
                for (k = 0; k < llMods.length; k++) {
                  str0 = "\\item[" + fTex.adaptarATex(llMods[k]) + "=] $\\{$" ;
                  objs = par.obtenirObjs(llMods[k]);
                  for (l = 0; l < objs.size(); l++){
                    if (l > 0) {
                      str0 = str0 + ", " +  fTex.adaptarATex((String)objs.get(l));
                    } else {
                      str0 = str0 +  fTex.adaptarATex((String)objs.get(l));
                    }
                  }
                  str0 = str0 + "$\\}$";
                  fTex.escriureLin(str0);
                }
                fTex.escriureLin("\\end{description}");
              }
              break;
           }
         }
         fTex.escriureLin("");
         fTex.escriureLin("\\newpage");
         fTex.escriureLin("");
       }
       if (calcsBiv != null) {
         lon = ordre.size();
         mostra = new int[lon][lon];
         tipus = new String[lon][lon];
         // Inicialització de la matriu de les combinacions de vars a mostrar
         for (i = 0; i < lon; i++) {
           for (j = 0; j < lon; j++) {
             mostra[i][j] = -1;
           }
         }
         // Inicialització de la llista de vars Num / Categ
         if ( (llistaVar[2] == null) || (llistaVar[3] == null)) {
           nVarsN = 0;
         }
         else {
           nVarsN = llistaVar[2].length;
           if (llistaVar[3].length != nVarsN)
             throw new ParamIncorrecteException(
                 "Nombre de params. incorrecte");
         }
         hihaVarsN = (nVarsN > 0);
         k = -1;
         for (i = 0; i < nVarsN; i++) {
           if (llistaVar[2][i] != null) {
             j = ordre.indexOf(llistaVar[2][i]);
             k = ordre.indexOf(llistaVar[3][i]);
             mostra[j][k] = i;
             tipus[j][k] = Constants.NUMxCAT;
           }
         }
         // Inicialització de la llista de vars Categ / Categ
         if ( (llistaVar[4] == null) || (llistaVar[5] == null)) {
           nVarsC = 0;
         }
         else {
           nVarsC = llistaVar[4].length;
           if (llistaVar[5].length != nVarsC)
             throw new ParamIncorrecteException(
                 "Nombre de params. incorrecte");
         }
         //k = -1;
         for (i = 0; i < nVarsC; i++) {
           if (llistaVar[4][i] != null) {
             j = ordre.indexOf(llistaVar[4][i]);
             k = ordre.indexOf(llistaVar[5][i]);
             mostra[j][k] = i;
             tipus[j][k] = Constants.CATxCAT;
           }
         }
         iniM = 0;
         n = k;
         // Da lo mismo que lista de estads. consultemos porque ambas tendran el mismo número de elems.
         for (j = 0; j < llistaEstads[1].size(); j++) { //++++++
           est = ( (Integer) llistaEstads[1].get(j)).intValue();
           switch (est) {
             case Opcions.HISTO_MULT: case Opcions.BOXP_MULT:
             fTex.escriureLin("\\begin{center}");
               // Es miren les opcions de diagrama de barres (estad. que sempre hi es)
               // per veure si cal fer vista d'ocell
               vista = ( (String) (opc.obtenirOpcionsEstad(Opcions.D_BAR_MULT)).
                        get(
                   "graduaX")).equals("vista");
               if (vista) {
                 fTex.escriureLin("{\\setlength{\\unitlength}{0.125pt}\\tiny");
                 maxH = Constants.MAX_FIG_HOR * 2;
                 maxClass = Constants.MAX_FIG_CLASS * 2;
               }
               else {
                 fTex.escriureLin("{\\setlength{\\unitlength}{0.25pt}\\tiny");
                 maxH = Constants.MAX_FIG_HOR;
                 maxClass = Constants.MAX_FIG_CLASS;
               }
               // Tractament de la llista de vars Num / Categ  i Categ / Categ
               if ((nVarsN + nVarsC) > 0) {
                 if (hihaVarsN) {
                   calcNC = (CalculsBivNC) calcsBiv[1][0];
                   nClass = calcNC.obtenirNumModalitats();
                 }
                 if (nVarsC > 0) {
                   calcCC = (CalculsBivCC) calcsBiv[2][0];
                   nClass = calcCC.obtenirNumModalitatsY();
                 }
                 // es controla el nombre màxim de variables en horizontal q es poden posar
                 // nombre de págines senceres
                 nPags1 = (nVarsN + nVarsC) / maxH;
                 // nombre de vars a la última pàgina
                 ultVars = (nVarsN + nVarsC) % maxH;
                 iniVar = 0;
                 str0 = "\\begin{tabular}{|@{}c@{}c@{}c" ;
                 str1 = "\\bf Classe & & $\\bf n_c$";
                 for (l = 0; ( (l <= nPags1) && (iniVar < (nVarsN + nVarsC))); l++) {
                   if ( (l == nPags1) && (ultVars != 0)) {
                     lon = ultVars;
                   }
                   else {
                     lon = maxH;
                   }
                   if (l > 0) {
                     fTex.escriureLin("{\\large $\\sim$}"); //
                     fTex.escriureLin("");
                     fTex.escriureLin("\\newpage");
                     fTex.escriureLin("");
                     fTex.escriureLin("{\\large $\\sim$}"); //
                   }
                   i = 0;
                   m = iniM;
                   while ((i < lon) && (m < ordre.size())){
                     if (mostra[m][n] != -1) {
                       str0 = str0 + "@{}c@{}c";
                       if (tipus[m][n].equals(Constants.NUMxCAT)) {
                         str1 = str1 + " & &\\bf " + fTex.adaptarATex(llistaVar[2][mostra[m][n]]);
                       } else {
                         str1 = str1 + " & &\\bf " + fTex.adaptarATex(llistaVar[4][mostra[m][n]]);
                       }
                       i++;
                     }
                     m++;
                   }
                   str0 = str0 + "||}";
                   str1 = str1 + " \\\\";
                   // nombre de págines senceres
                   nPags2 = nClass / maxClass;
                   // nombre de figures a la última pàgina
                   ultFigs = nClass % maxClass;
                   inici = 0; //index de la modalitat per la qual es comença
                   // es generen totes les pàgines pel conjunt de vars
                   for (k = 0; ( (k <= nPags2) && (inici < nClass)); k++) {
                     if ( (k == nPags2) && (ultFigs != 0)) {
                       nFigs = ultFigs;
                     }
                     else {
                       nFigs = maxClass;
                     }
                     if (k > 0) {
                       fTex.escriureLin("");
                       fTex.escriureLin("{\\large $\\wr$}");
                       fTex.escriureLin("\\newpage");
                       fTex.escriureLin("{\\large $\\wr$}");
                       fTex.escriureLin("");
                       fTex.escriureLin("\\mbox{ }");
                     }
                     //S'escriu la capsalera del tabular
                     fTex.escriureLin(str0);
                     fTex.escriureLin(str1);
                     fTex.escriureLin("\\hline");

                     if (hihaVarsN){
                       generarLtxEtiqClasses(nFigs, inici,
                                             calcNC.obtenirModalitats(),
                                             vista);
                       generarLtxSeparaVars(nFigs, false, vista);
                       fTex.escriureLin("&");
                       generarLtxEtiqNumObjs(nFigs, inici, calcNC, null, vista);
                     }
                     else {
                       generarLtxEtiqClasses(nFigs, inici,
                                             calcCC.obtenirModalitatsY(),
                                             vista);
                       generarLtxSeparaVars(nFigs, false, vista);
                       fTex.escriureLin("&");
                       generarLtxEtiqNumObjs(nFigs, inici, null, calcCC, vista);
                     }
                     // es fa el gràfic per totes les variables
                     i = 0;
                     m = iniM;
                     while ( (i < lon) && (m < ordre.size())) {
                       if (mostra[m][n] != -1) {
                         if (tipus[m][n].equals(Constants.NUMxCAT)) {
                           calcNC = (CalculsBivNC) calcsBiv[1][mostra[m][n]];
                           if (calcNC != null) {
                             generarLtxSeparaVars(nFigs, i == 0, vista);
                             fTex.escriureLin("&");
                             if (vista) {
                               fTex.escriureLin("\\hspace{-" +
                                                (Constants.OFFSET_X_MULT / 4) +
                                                " pt}");
                               fTex.escriureLin("\\begin{minipage}{" +
                                                (Constants.AMP_MINIPAGE_GRAF / 2) +
                                                "pt}");
                             }
                             else {
                               fTex.escriureLin("\\hspace{-" +
                                                (Constants.OFFSET_X_MULT / 2) +
                                                " pt}");
                               fTex.escriureLin("\\begin{minipage}{" +
                                                Constants.AMP_MINIPAGE_GRAF +
                                                "pt}");
                             }
                             est = ( (Integer) llistaEstads[1].get(j)).intValue();
                             switch (est) {
                               case Opcions.HISTO_MULT:
                                 logger.finer(
                                     "Generant minipage amb histo mult per " +
                                     llistaVar[2][mostra[m][n]]);
                                 new HistogramaMult(fTex, calcNC,
                                     llistaVar[2][mostra[m][n]],
                                     llistaVar[3][mostra[m][n]],
                                     opc.obtenirOpcionsEstad(est),
                                     nFigs,
                                     inici, false);
                                 break;
                               case Opcions.BOXP_MULT:
                                 logger.finer(
                                     "Generant minipage amb boxplot mult per " +
                                     llistaVar[2][mostra[m][n]]);
                                 new BoxplotMult(fTex, calcNC,
                                                 llistaVar[2][mostra[m][n]],
                                                 llistaVar[3][mostra[m][n]],
                                                 opc.obtenirOpcionsEstad(est),
                                                 nFigs, inici, false);
                                 break;
                             }
                             fTex.escriureLin("\\end{minipage}");
                           }
                         }
                         else { //tipus CATxCAT
                           calcCC = (CalculsBivCC) calcsBiv[2][mostra[m][n]];
                           if (calcCC != null) {
                             generarLtxSeparaVars(nFigs, i == 0, vista);
                             fTex.escriureLin("&");
                             if (vista) {
                               fTex.escriureLin("\\hspace{-" +
                                                (Constants.OFFSET_X_MULT / 4) +
                                                " pt}");
                               fTex.escriureLin("\\begin{minipage}{" +
                                                (Constants.AMP_MINIPAGE_GRAF / 2) +
                                                "pt}");
                             }
                             else {
                               fTex.escriureLin("\\hspace{-" +
                                                (Constants.OFFSET_X_MULT / 2) +
                                                " pt}");
                               fTex.escriureLin("\\begin{minipage}{" +
                                                Constants.AMP_MINIPAGE_GRAF +
                                                "pt}");
                             }
                             est = ( (Integer) llistaEstads[2].get(j)).intValue();
                             switch (est) {
                               case Opcions.D_BAR_MULT:
                                 logger.finer(
                                     "Generant minipage amb diagrama de barres mult per " +
                                     llistaVar[4][mostra[m][n]]);
                                 new DiagramaBarresMult(fTex, calcCC,
                                     llistaVar[4][mostra[m][n]],
                                     llistaVar[5][mostra[m][n]],
                                     opc.obtenirOpcionsEstad(est),
                                     nFigs, inici, false);
                                 break;
                             }
                             fTex.escriureLin("\\end{minipage}");
                           }
                         }
                         i++;
                       } // mostra
                       m++;
                     }// while i
                     inici = inici + nFigs;
                     fTex.escriureLin("\\end{tabular}");
                   } // for k
                   str0 = "\\begin{tabular}{|@{}c@{}c@{}c";
                   str1 = "\\bf Classe & & $\\bf n_c$";
                   iniVar = iniVar + lon;
                   iniM = m;
                 } //for l
               }
               fTex.escriureLin("}"); //final del ambit del setlength
               fTex.escriureLin("\\end{center}");
               if ( (llistaEstads[1].size() > 0) || (llistaEstads[2].size() > 0)) {
                 fTex.escriureLin("");
                 fTex.escriureLin("\\newpage");
                 fTex.escriureLin("");
               }
               break;
             case Opcions.DESCR_CLASS:
              // Tractament de la llista de vars Num / Categ
               if ( hihaVarsN ){
                 calcNC = (CalculsBivNC) calcsBiv[1][0];
                 nClass = calcNC.obtenirNumModalitats();
                 maxClass = new Integer((String)opc.obtenirOpcionsEstad(est).get("maxnum")).intValue();
                 /* càlcul del nombre de tabulars necessaris, per amplada,
                 per la llista de classes(modalitats) */
                 // nombre de págines senceres (seran tabulars)
                 nPags1 = nClass / maxClass;
                 // nombre de classes a la última pàgina
                 ultFigs = nClass % maxClass;
                 // es controla el nombre màxim de variables en vertical q es poden posar
                 // es resten 2 files per la capcalera de la taula
                 maxH = (Constants.MAX_FILES_TABH -2)/5;
                 // nombre de págines senceres
                 nPags2 = nVarsN / maxH;
                 // nombre de vars a la última pàgina
                 ultVars = nVarsN % maxH;
                 iniVar = 0;
                 for (l = 0; ( (l <= nPags2) && (iniVar < nVarsN)); l++) {
                   if ( (l == nPags2) && (ultVars != 0)) {
                     lon = ultVars;
                   }
                   else {
                     lon = maxH;
                   }
                   if (iniVar > 0) {
                     fTex.escriureLin("");
                     fTex.escriureLin("\\begin{center}");
                     fTex.escriureLin("{\\large $\\wr$}");
                     fTex.escriureLin("\\newpage");
                     fTex.escriureLin("{\\large $\\wr$}");
                     fTex.escriureLin("\\mbox{ }");
                     fTex.escriureLin("\\end{center}");
                     fTex.escriureLin("");
                   }
                   new DescrClassesNum( fTex,(CalculsBivNC[]) calcsBiv[1],
                                           llistaVar[2], opc.obtenirOpcionsEstad(est), nPags1,
                                           ultFigs, maxClass, iniVar, lon);
                   iniVar = iniVar + lon;
                 }

               }

               // Tractament de la llista de vars Categ / Categ
               if ( (llistaVar[4] == null) || (llistaVar[5] == null)) {
                 nVarsC = 0;
               }
               else {
                 nVarsC = llistaVar[4].length;
                 if (llistaVar[5].length != nVarsC)
                   throw new ParamIncorrecteException(
                       "Nombre de params. incorrecte");
               }
               if ( nVarsC > 0){
                 calcCC = (CalculsBivCC) calcsBiv[2][0];
                 nClass = calcCC.obtenirNumModalitatsY();
                 maxClass = new Integer((String)opc.obtenirOpcionsEstad(est).get("maxcateg")).intValue();
                 /* càcul del nombre de tabulars necessaris, per amplada,
                 per la llista de classes(modalitats) */
                 // nombre de págines senceres (seran tabulars)
                 nPags1 = nClass / maxClass;
                 // nombre de classes a la última pàgina
                 ultFigs = nClass % maxClass;
                 // max nombre de files que poden ocupar les vars. per página
                 // es resten 3 files per la capcalera de la taula
                 maxH = (Constants.MAX_FILES_TABH -3);

                 i = 0;
                 k = 0;
                 iniVar = 0;
                 while (i < nVarsC) {
                   calcCC = (CalculsBivCC) calcsBiv[2][i];
                   // es controla el nombre màxim de variables en vertical q es poden posar
                   // aixo depen del nombre de modalitats de cada variable
                   // pero també cal sumar 1 fila més de separació entre variables (fila buida)
                   lon = calcCC.obtenirNumModalitatsX() + 1;
                   if ( (k + lon) > maxH) {
                     // nombre de variables per les que generarem la taula
                     l = i - iniVar;
                     if (iniVar > 0) {
                       fTex.escriureLin("");
                       fTex.escriureLin("\\begin{center}");
                       fTex.escriureLin("{\\large $\\wr$}");
                       fTex.escriureLin("\\newpage");
                       fTex.escriureLin("{\\large $\\wr$}");
                       fTex.escriureLin("\\mbox{ }");
                       fTex.escriureLin("\\end{center}");
                       fTex.escriureLin("");
                     }
                     new DescrClassesCateg(fTex, (CalculsBivCC[]) calcsBiv[2],
                                               llistaVar[4], nPags1,
                                               ultFigs, maxClass, iniVar, l);
                     iniVar = iniVar + l;

                     k = lon;
                   }
                   else if (i == nVarsC - 1) {
                     // nombre de variables per les que generarem la taula
                     l = i - iniVar + 1;
                     if (iniVar > 0) {
                       fTex.escriureLin("");
                       fTex.escriureLin("\\begin{center}");
                       fTex.escriureLin("{\\large $\\wr$}");
                       fTex.escriureLin("\\newpage");
                       fTex.escriureLin("{\\large $\\wr$}");
                       fTex.escriureLin("\\mbox{ }");
                       fTex.escriureLin("\\end{center}");
                       fTex.escriureLin("");
                     }
                     new DescrClassesCateg(fTex, (CalculsBivCC[]) calcsBiv[2],
                                               llistaVar[4], nPags1,
                                               ultFigs, maxClass, iniVar, l);
                   }
                   else {
                     k = k + lon;
                   }
                   i++;
                 }
               }

               break;
           }
         } //++++++
       }
       else {
         logger.warning("No s'han realitzat els calculs de la descriptiva per classes");
       }
     }
     catch (CreacioFitxerException e) {
       throw e;
     }
     catch (ParamIncorrecteException e) {
       throw e;
     }
     catch (IOException e) {
       throw new CreacioFitxerException("Error d'escriptura: " + e.getMessage());
     }
   }

   private void generarLtxEtiqClasses(int numModalitats, int inici, String[] modalitats, boolean vista)throws
       CreacioFitxerException {
     int i, k, lon, max;
     boolean tancat;
     String mod;

     try {
       if (vista){
         lon = Constants.AMPLADA;
       } else {
         lon = (Constants.AMPLADA / 2);
       }
       fTex.escriureLin("");
       fTex.escriureLin("\\begin{minipage}{" + Constants.AMP_MINIPAGE_ETIQ + "pt}");
       fTex.escriureLin("\\begin{picture}(" + lon + ","
                   + (Constants.OFFSET_Y + numModalitats * (Constants.SEPARACI0_V)) +
                   ")(-" + Constants.OFFSET_X_MULT
                   + ",-" + Constants.OFFSET_Y_MULT + ")");

       i = 0;
       k = inici;
       max = inici + numModalitats;
       while (k < max) {
         mod = modalitats[k];
         // es dibuixen totes les categories encara q no tinguin dades
         fTex.escriureLin("");
         // es dibuixa la linea de separació entre classes
         fTex.escriureLin("\\put(-" + Constants.OFFSET_X_MULT + ", " +
                     (i * Constants.SEPARACI0_V + Constants.OFFSET_Y) +
                     "){\\line(1,0){" + lon + "}}");
         // es posa la etiqueta de la classe
         fTex.escriureLin("\\put(-" + (Constants.OFFSET_X_MULT - 15) + ", " +
                     (i * Constants.SEPARACI0_V + Constants.ALZADA - 50) + "){" +
                     fTex.adaptarATex(mod) + "}");
         i++;
         k++;
       }
       fTex.escriureLin("\\end{picture}");
       fTex.escriureLin("\\end{minipage}");
     }
     catch (IOException e) {
       throw new CreacioFitxerException("Error d'escriptura: " + e.getMessage());
     }
   }

   private void generarLtxEtiqNumObjs(int numModalitats, int inici,
                                      CalculsBivNC calcNC, CalculsBivCC calcCC,
                                      boolean vista) throws
       CreacioFitxerException {
     int i, k, lon, max, n;
     boolean tancat;
     String[] modalitats;

     try {
       if (calcNC != null) {
         modalitats = calcNC.obtenirModalitats();
       }
       else {
         modalitats = calcCC.obtenirModalitatsY();
       }
       if (vista) {
         lon = Constants.AMPLADA / 3;
       }
       else {
         lon = (Constants.AMPLADA / 3) / 2;
       }
       fTex.escriureLin("");
       fTex.escriureLin("\\begin{minipage}{" + Constants.AMP_MINIPAGE_ETIQ / 3 +
                        "pt}");
       fTex.escriureLin("\\begin{picture}(" + lon + ","
                        +
                        (Constants.OFFSET_Y + numModalitats * (Constants.SEPARACI0_V)) +
                        ")(-" + Constants.OFFSET_X_MULT
                        + ",-" + Constants.OFFSET_Y_MULT + ")");

       i = 0;
       k = inici;
       max = inici + numModalitats;
       while (k < max) {
         if (calcNC != null) {
           n = calcNC.obtenirCalculsUniv(modalitats[k]).obtenirNumObjs();
         }
         else {
           n = calcCC.obtenirCalculsUniv(modalitats[k]).obtenirNumObjs();
         }
         // es dibuixen totes les categories encara q no tinguin dades
         fTex.escriureLin("");
         // es dibuixa la linea de separació entre classes
         fTex.escriureLin("\\put(-" + Constants.OFFSET_X_MULT + ", " +
                          (i * Constants.SEPARACI0_V + Constants.OFFSET_Y) +
                          "){\\line(1,0){" + lon + "}}");
         // es posa el nombre d'objs. de la classe
         fTex.escriureLin("\\put(-" + (Constants.OFFSET_X_MULT - 15) + ", " +
                          (i * Constants.SEPARACI0_V + Constants.ALZADA - 50) +
                          "){" +
                          n + "}");
         i++;
         k++;
       }
       fTex.escriureLin("\\end{picture}");
       fTex.escriureLin("\\end{minipage}");
     }
     catch (IOException e) {
       throw new CreacioFitxerException("Error d'escriptura: " + e.getMessage());
     }
   }

   private void generarLtxSeparaVars(int numClasses, boolean linExtra, boolean vista) throws
       CreacioFitxerException {
     int i, lon;
     float pt;

     try {
       fTex.escriureLin("&");
       if (vista) {
         fTex.escriureLin("\\begin{minipage}{" + Constants.AMP_MINIPAGE_SEPAR / 2 + "pt}");
       } else {
         fTex.escriureLin("\\begin{minipage}{" + Constants.AMP_MINIPAGE_SEPAR + "pt}");
       }
       fTex.escriureLin("");
       lon = (Constants.OFFSET_Y + numClasses * (Constants.SEPARACI0_V));
       fTex.escriureLin("\\begin{picture}(" + Constants.AMP_FIG_SEPAR + ","
                   + lon + ")(-" + Constants.OFFS_AMP_SEPAR
                   + ",-" + Constants.OFFS_ALZ_SEPAR + ")");
       pt = Constants.AMP_FIG_SEPAR / 2;
       if (linExtra) {
         fTex.escriureLin("\\put(" + (pt - 10) + ",0){\\line(0,1){" + lon + "}}");
       }
       fTex.escriureLin("\\put(" + pt + ",0){\\line(0,1){" + lon + "}}");
       for (i = 0; i < numClasses; i++) {
         fTex.escriureLin("");
         // es dibuixa la linea de separació entre classes
         fTex.escriureLin("\\put(-" + Constants.OFFS_AMP_SEPAR + ", " +
                     (i * Constants.SEPARACI0_V + Constants.OFFSET_Y) +
                     "){\\line(1,0){" + Constants.AMP_FIG_SEPAR + "}}");
       }
       fTex.escriureLin("\\end{picture}");
       fTex.escriureLin("\\end{minipage}");
     }
     catch (IOException e) {
       throw new CreacioFitxerException("Error d'escriptura: " + e.getMessage());
     }
   }

   void generaLtxIniciInformeAuto(String matriu, String numObjs, String numProps) throws
       CreacioFitxerException {
     String str = null, matriuAdapt;

     try {
       fTex.copiarCapsaleraTex(Constants.FITXER_CAP_INF);
       fTex.obrirPerEscriure(true);
       escriureTitol("Informe autom\\`atic");
       logger.finer("Path original: " + matriu);
       matriuAdapt = adaptarRuta(matriu);
       str = "{\\bf Dades:} " + matriuAdapt;
       fTex.escriureLin(str);
       fTex.escriureLin("");
       logger.finer("Path modificat: " + str);
       str = "{\\bf Nombre d'objectes:} " + numObjs;
       fTex.escriureLin(str);
       fTex.escriureLin("");
       str = "{\\bf Nombre de propietats:} " + numProps;
       fTex.escriureLin(str);
       fTex.escriureLin("");
       str = "{\\bf Fitxers:} \\vspace{-\\abovedisplayskip}";
       fTex.escriureLin(str);
       str = "\\begin{enumerate}\\setlength{\\itemsep}{0pt}\\setlength{\\itemindent}{8ex}";
       fTex.escriureLin(str);
       str = "\\item " + matriuAdapt + ".dat, ";
       fTex.escriureLin(str);
       str = "\\item "  + matriuAdapt + ".pro, ";
       fTex.escriureLin(str);
       str = "\\item " + matriuAdapt + ".obj";
       fTex.escriureLin(str);
       str = "\\end{enumerate}";
       fTex.escriureLin(str);
       fTex.escriureLin("");
     }
     catch (IOException ex) {
       throw new CreacioFitxerException("Error d'escriptura: " + ex.getMessage());
     }
   }

   void generaLtxFiInformeAuto() throws
       IOException {
     fTex.finalitzarTex();
     fTex.tancarEsc();
   }

   void generaLtxArbreClassif(ArbreClassif arbre, String titol, double tall, boolean etiqRestrin,boolean reglas, String[] llista, boolean esPart) throws CreacioFitxerException,
       IOException {
      if (!esPart) {
       // Si no forma part d'un altre doc cal afegir la capçalera i preparar-lo per escriure
       fTex.copiarCapsaleraTex(Constants.FITXER_CAP_GRAL);
       fTex.obrirPerEscriure(true);
     }
     new Dendograma(arbre, titol, tall, etiqRestrin,reglas, llista, fTex);
     if (!esPart) {
       fTex.finalitzarTex();
       fTex.tancarEsc();
     }
   }
   
////////////////////////////////////LAIA//////////////////////////////////////
   
  /**
    * Acció que genera un fitxer latex de la descriptiva de les bases de coneixement
    * @param nomsBC, llistat amb els noms de les bases de coneixement
    * @param llistaVariables, llistat amb els noms de les variables de la matriu
    *  @param llistaEstads, llista Estadístics pel cas Univariant
     * llistaEstads[0] - Conté la llista d'estad. numerics <br>
     * llistaEstads[1] - Conté la llista d'estad. categorics<br>
     * @param llistaEstadsC, llista Estadístics pel cas Per classes
     * llistaEstadsC[0] - Conté la llista d'estad. NN<br>
     * llistaEstadsC[1] - Conté la llista d'estad. NC<br>
     * llistaEstadsC[2] - Conté la llista d'estad. CC<br>
     * @param opcUniv, opcions dels tipus dels estadístics de tipus d'anàlisi descriptiva a realitzar per Univariant
     * @param opcClass, opcions dels tipus dels estadístics de tipus d'anàlisi descriptiva a realitzar per Classes
     * @param avaluacio, enter que indica quin tipus d'avaluació de les bases de coneixement s'ha efectuat
     * 1-->Regla a regla<br>
     * 2-->Per conseqüent<br>
     * 3-->Integrada     
     * @param tipusDescriptiva, enter que indica quin tipus d'ànalisi es fa:
     * 1-->Univariant<br>
     * 2-->Per classes<br>
     * 3-->Univariant i per classes
     * @param alCalculsU, llistat que conté els càlculs per descriptives univariants
     * @param alCalculsC, llistat que conté els càlculs per descriptiva per classes
     * @param alReglesNormals, llistat de les regles escrites en inordre
     * @param alNomsRegles, llistat dels noms de les regles sense sufixos
     * @param ordre, llistat que conté totes les propietats de la matriu de dades amb un ordre establert
     * @param esPart, enter que indica si s'ha d'escriure o no la capçalera i final del fitxer
     * 0-->Escriure capçalera i final (el fitxer només té una part)
     * 1-->Escriure només la capçalera (aquesta és la primera part del fitxer)
     * 2-->No escriure res (aquesta és una part del mig del fitxer)
     * 3-->Escriure només el final (aquesta és la última part del fitxer)
     * @param printRegles, indica si cal escriure les regles a l'informe final
    */
   void generarLtxPerVarsBC(ArrayList nomsBC,ArrayList llistaVariables,Vector[] llistaEstads,Vector[] llistaEstadsC,Opcions opcUniv,Opcions opcClass,int avaluacio,int tipusDescriptiva,ArrayList alCalculsU,ArrayList alCalculsC,ArrayList alReglesNormals,ArrayList alNomsRegles,ArrayList ordre,int esPart,boolean printRegles) throws CreacioFitxerException, IOException, OpcioIncorrectaException,ParamIncorrecteException {
	    int i, j, est, lon;
	    
	    
	    
	    if(esPart < 2) 
	    {
	  	  // Si no forma part d'un altre doc cal afegir la capçalera i preparar-lo per escriure
	  	    fTex.copiarCapsaleraTex(Constants.FITXER_CAP_GRAL);
	  	    fTex.obrirPerEscriure(true);
	    }
	    else 
	    {
	         // cal posar les pàgs en horitz
	    	fTex.obrirPerEscriure(true);
	        //fTex.escriureLin("\\begin{landscape}");
	    }
	    escriureTitol("Descriptiva de les bases de coneixement");
	    for(int k=0;k<nomsBC.size();k++){
	    	int[] mostra = new int[ordre.size()];
	 	    String[] tipus = new String[ordre.size()];
	 	    Arrays.fill(mostra, -1);
	 	    fTex.escriureLin("\\newpage");
	    	escriureNomBC((String)nomsBC.get(k)+" :");
	    	ArrayList alRegles=(ArrayList)alReglesNormals.get(k);
	    	ArrayList alNoms=(ArrayList)alNomsRegles.get(k);
	    	
	    	if(printRegles)
	    	{
	    		for(int reg=0;reg<alRegles.size();reg++)
	    		{//Escrivim les regles de la BC	    		
	    			fTex.escriureLin((String)alRegles.get(reg));
	    			fTex.escriureLin("");
	    		}
	    	}
	    	
	    	if(avaluacio==1)escriureSubtitolBC("Resultats de l'avaluaci\\'o regla a regla");
	    	else if(avaluacio==2)escriureSubtitolBC("Resultats de l'avaluaci\\'o per conseq\\\"uent");
	    	else escriureSubtitolBC("Resultats de l'avaluaci\\'o integrada");
	    	ArrayList al=(ArrayList)llistaVariables.get(k);
	    	String[][] llistaVar=(String[][])al.get(0);
	    	String[][] llistaVarC=(String[][])al.get(1);    			    
		    // Tractament de la llista de vars Categoriques(llistaVar[1] i llistaVarC[0] contenen el mateix)
		    lon = llistaVar[1].length;
		    for (i = 0; i < lon; i++) {		 
		    	j = ordre.indexOf(llistaVar[1][i]);		      
		      mostra[j] = i;
		      tipus[j] = Constants.PROP_CATEGORICA;
		    }
		    //Es genera el document Latex amb els resultats obtinguts
		    lon = ordre.size();
		    int inom=0;
		    for (i = 0; i < lon; i++){
		      if (mostra[i] != -1){
		       //CATEGORICA
		    	  if(avaluacio==1){		    		
		    		  escriureSubtitolSecundari("Regla " + fTex.adaptarATex((String)alNoms.get(inom)));
		    	  }else if(avaluacio==2){		    	
		    		  escriureSubtitolSecundari("Conseq\\\"uent " + fTex.adaptarATex((String)alNoms.get(inom)));
		    	  }else{		    		  
		    		  escriureSubtitolSecundari("Base de coneixement " + fTex.adaptarATex((String)alNoms.get(inom)));		   
		    	  }
		    	  inom++;
		    	  try {
					switch(tipusDescriptiva){
					  	case 1:
					  		CalculsUniv[][] calculsU=(CalculsUniv[][])alCalculsU.get(k);
					  		for (j = 0; j < llistaEstads[1].size(); j++) {
					            est = ( (Integer) llistaEstads[1].get(j)).intValue();
					            switch (est) {
					              case Opcions.TAULA_FREQS:
					                new TaulaFreqs(fTex, null, (CalculsUnivCateg)calculsU[1][mostra[i]], opcUniv.obtenirOpcionsEstad(est),true);
					                break;
					              case Opcions.DIAGR_BARRES:
					                new DiagramaBarres(fTex, null, (CalculsUnivCateg)calculsU[1][mostra[i]], opcUniv.obtenirOpcionsEstad(est), false);
					                break;
					                
					            }
					          }	
					  		break;
					  	case 2:
					  		CalculsClass[] calculsC=(CalculsClass[])alCalculsC.get(k);
					  		generarLtxVarClasseBC(ordre, calculsC[mostra[i]], llistaEstadsC, opcClass,avaluacio);
					  		break;
					  	case 3:
					  		CalculsClass[] calculsC2=(CalculsClass[])alCalculsC.get(k);
					  		generarLtxVarClasseBC(ordre, calculsC2[mostra[i]], llistaEstadsC, opcClass,avaluacio);
					  		CalculsUniv[][] calculsU2=(CalculsUniv[][])alCalculsU.get(k);
					  		for (j = 0; j < llistaEstads[1].size(); j++) {
					            est = ( (Integer) llistaEstads[1].get(j)).intValue();
					            switch (est) {
					              case Opcions.TAULA_FREQS:
					                new TaulaFreqs(fTex, null, (CalculsUnivCateg)calculsU2[1][mostra[i]], opcUniv.obtenirOpcionsEstad(est),true);
					                break;
					              case Opcions.DIAGR_BARRES:
					                new DiagramaBarres(fTex, null, (CalculsUnivCateg)calculsU2[1][mostra[i]], opcUniv.obtenirOpcionsEstad(est), false);
					                break;
					                
					            }
					  		}					  		
					  		break;
					  }
				} catch (ParamIncorrecteException e) {
					throw e;
				}
		          	        
		      }
		    }
	    }	    
	    if(esPart == 0 || esPart == 3) 
	    {
	      fTex.finalitzarTex();
	      fTex.tancarEsc();
	    } 
	    else 
	    {
	         //fTex.escriureLin("\\end{landscape}");
	         fTex.tancarEsc();
	    }
   }
	
	
/**
    * Method that generates a LaTeX file with the quality values of the knowledge base
    * 
    * @param nomBC - name of the knowledge base
    * @param alRegles - rules written in normal form
    * @param alNoms - names of the rules
    * @param cols - boolean values for the columns that must be included
    * @param values - vector column values for all the rules
    * @param suppT - total support value (it is 0 if we don't want to show it)
    * @param mConf - mean confidence value (it is 0 if we don't want to show it)
    * @param esPart - indicates the options to create the tex file:
	 * 		0 -> print opening and ending of the file (the file has just one part)
	 * 		1 -> print opening of the file (this is the first part of the file)
	 * 		2 -> not print anything (this is a middle part of the file)
	 * 		3 -> print the ending of the file (this is the last part of the file)
    * 
    * @author Esther Lozano
    * @author Grup SISPD QT 2009-2010
    */
   void generarLtxQualitatBC(String nomBC, ArrayList alReglesNorm, ArrayList alRegles, boolean[] cols, Vector<float[]> values, float suppT, float mConf, float covG, int esPart) throws CreacioFitxerException, IOException, OpcioIncorrectaException,ParamIncorrecteException 
   {
	   	//we print the opening of the file just when necessary
	    if(esPart < 2)
	    {
	    	fTex.copiarCapsaleraTex(Constants.FITXER_CAP_GRAL);
	    	fTex.obrirPerEscriure(true);
	    }
	    fTex.obrirPerEscriure(true);
	    escriureTitol("Qualitat de les Bases de coneixement");
	    
 	    fTex.escriureLin("\\newpage");
    	escriureNomBC(nomBC+" :");
    	
    	for(int reg = 0; reg < alReglesNorm.size(); reg++)
    	{	//Escrivim les regles de la BC	    		
    		fTex.escriureLin((String)alReglesNorm.get(reg));
    		fTex.escriureLin("");
    	}
    	fTex.escriureLin("\\newpage");
    	escriureSubtitolBC("Resultats de la qualitat regla a regla");
	    
    	new TaulaQualitat(fTex, alRegles, cols, values);
    	
    	if(suppT != 0)
    	{
    		fTex.escriureLin("");
    		fTex.escriureLin("{\\bf Suport total de la base de coneixement:} "+fTex.formatejarReal(suppT));
    	}
    	
    	if(mConf != 0)
    	{
    		fTex.escriureLin("");
    		fTex.escriureLin("{\\bf Confian\\c ca mitjana de la base de coneixement:} "+fTex.formatejarReal(mConf));
    	}
    	
    	if(covG != 0)
    	{
    		fTex.escriureLin("");
    		fTex.escriureLin("{\\bf Cobertura global de la base de coneixement:} "+fTex.formatejarReal(covG));
    	}
		
    	//we print the ending of the file just when necessary
    	if(esPart == 0 || esPart == 3)
    	{
    		fTex.finalitzarTex();
    		fTex.tancarEsc();
    	}	
    	else
    	{
    		fTex.escriureLin("\\newpage");
    		fTex.tancarEsc();
    	}   	
  }
   
	
	
	
	
	
	
   /**
    * Acció que genera un fitxer latex amb la descriptiva per classes de les bases de coneixement
    * @param ordre, llistat que conté totes les propietats de la matriu de dades amb un ordre establert
    * @param calculs, llistat que conté els càlculs per descriptiva per classes
    * @param llistaEstads, llista Estadístics pel cas Per classes
     * llistaEstads[0] - Conté la llista d'estad. NN<br>
     * llistaEstads[1] - Conté la llista d'estad. NC<br>
     * llistaEstads[2] - Conté la llista d'estad. CC<br>
    * @param opc, opcions dels tipus dels estadístics de tipus d'anàlisi descriptiva a realitzar per Classes
    * @param avaluacio, enter que indica quin tipus d'avaluació de les bases de coneixement s'ha efectuat
     * 1-->Regla a regla<br>
     * 2-->Per conseqüent<br>
     * 3-->Integrada 
    * @throws CreacioFitxerException
    * @throws OpcioIncorrectaException
    * @throws ParamIncorrecteException
    */
   private void generarLtxVarClasseBC(ArrayList ordre, CalculsClass calculs,
		   Vector[] llistaEstads, Opcions opc,int avaluacio) throws
		   CreacioFitxerException, OpcioIncorrectaException,
		   ParamIncorrecteException {
	   CalculsBiv[][] calcsBiv = calculs.obtenirCalculBiv();
	   String[][] llistaVar = calculs.obtenirLlistaVars();
	   Particio par = calculs.obtenirParticio();
	   boolean vista, hihaVarsN;
	   int i, j, k, l, iniM, m, n, est, nVarsN, nVarsC, lon, nPags1, ultVars, iniVar,
	   nPags2, ultFigs, nFigs, nClass =0, inici, maxH, maxClass;
	   CalculsBivNC calcNC = null;
	   CalculsBivCC calcCC = null;
	   String nomClass = null, str0 ="", str1 = "";
	   String[] llMods;
	   Vector objs;
	   int[][] mostra;
	   String[][] tipus;

	   try {
		   if ((llistaVar[3] != null) && (llistaVar[3].length > 0)){
			   nomClass = llistaVar[3][0];
		   }
		   else if ((llistaVar[5] != null) && (llistaVar[5].length > 0)) {
			   nomClass = llistaVar[5][0];
		   }
		   else if (par != null) {
			   nomClass = par.obtenirVar();
		   } else {
			   throw new ParamIncorrecteException("No hi ha variable de classe.");
		   }
		   if (par != null){
//			   Se recorre la lista de calculos estadísticos sobre la var. de clase
			   for (j = 0; j < llistaEstads[0].size(); j++) {
				   est = ( (Integer) llistaEstads[0].get(j)).intValue();
				   switch (est) {
				   case Opcions.DESCR_EXTENSIONAL:
					   llMods = par.obtenirModalitats();
					   if(avaluacio==1){
						   fTex.escriureLin("{\\noindent \\bf Descripci\\'o extensional de la regla: }");
					   }else if(avaluacio==2){
						   fTex.escriureLin("{\\noindent \\bf Descripci\\'o extensional del conseq\\\"uent: }");
					   }else fTex.escriureLin("{\\noindent \\bf Partici\\'o indu\\\"ida per les regles: }");
					   
					   if (((Boolean) opc.obtenirOpcionsEstad(Opcions.DESCR_EXTENSIONAL).
							   get("tabular")).booleanValue())
					   {
						   fTex.escriureLin("\\begin{center}");
						   fTex.escriureLin(
						   "\\begin{tabular}{|p{0.15\\textwidth}|p{0.85\\textwidth}|}");
						   fTex.escriureLin("\\hline");
						   if(avaluacio==1){
							   fTex.escriureLin("\\bf Regla & \\bf Objectes \\\\");
						   }else if(avaluacio==2){
							   fTex.escriureLin("\\bf Conseq\\\"uent & \\bf Objectes \\\\");
						   }else fTex.escriureLin("\\bf PIR & \\bf Objectes \\\\");
						   
						   fTex.escriureLin("\\hline");
						   for (k = 0; k < llMods.length; k++) {
							   str0 = fTex.adaptarATex(llMods[k]) + " & " ;
							   objs = par.obtenirObjs(llMods[k]);
							   for (l = 0; l < objs.size(); l++){
								   if (l > 0) {
									   str0 = str0 + ", " +  fTex.adaptarATex((String)objs.get(l));
								   } else {
									   str0 = str0 +  fTex.adaptarATex((String)objs.get(l));
								   }
							   }
							   str0 = str0 + " \\\\";
							   fTex.escriureLin(str0);
							   fTex.escriureLin("\\hline");
						   }
						   fTex.escriureLin("\\end{tabular}");
						   fTex.escriureLin("\\end{center}");
					   }
					   else {
						   fTex.escriureLin("\\begin{description}");
						   for (k = 0; k < llMods.length; k++) {
							   str0 = "\\item[" + fTex.adaptarATex(llMods[k]) + "=] $\\{$" ;
							   objs = par.obtenirObjs(llMods[k]);
							   for (l = 0; l < objs.size(); l++){
								   if (l > 0) {
									   str0 = str0 + ", " +  fTex.adaptarATex((String)objs.get(l));
								   } else {
									   str0 = str0 +  fTex.adaptarATex((String)objs.get(l));
								   }
							   }
							   str0 = str0 + "$\\}$";
							   fTex.escriureLin(str0);
						   }
						   fTex.escriureLin("\\end{description}");
					   }
					   break;
				   }
			   }
			   fTex.escriureLin("");			  
			   fTex.escriureLin("");
		   }
		   
	   else {
			   logger.warning("No s'han realitzat els calculs de la descriptiva per classes");
		   }
	   }
	  
	   catch (ParamIncorrecteException e) {
		   throw e;
	   }
	   catch (IOException e) {
		   throw new CreacioFitxerException("Error d'escriptura: " + e.getMessage());
	   }
   }
   /**
    * Escriu el títol del tipus noms de les bases de coneixement
    * @param titol, títol a escriure
    * @throws IOException
    */
   void escriureNomBC(String titol) throws IOException{
	    String str;
	    fTex.escriureLin("\\mbox{ }");
	    str = "{\\Large \\bf " + titol + "}\\\\";
	    fTex.escriureLin(str);
	    fTex.escriureLin("");
	    logger.finer("Titol escrit: " + str);
	  }
   /**
    * 
    * @param llistaProp, conté el les propietats creades a partir de la discretització
    * @param alValors, conté per cada posició de l'array un llistat amb els valors (mínim i màxim ) de cada interval, relacionats amb les modalitats de la variable. A més, conte un boolea indicant si el metode de discretitzacio ha estat revisedBoxPlot
    * @param esPart, indica si el document forma part d'un altre document o no
    * @param tipus, indica el tipus de discretització segons la següent codificació:
    * tipus==1, discretització BoxPlot <br>
    * tipus==2, discretització Uniforme<br>
    * tipus==3, discretització Manual
    * @throws CreacioFitxerException
    * @throws IOException
    * @throws OpcioIncorrectaException
    * @throws ParamIncorrecteException
    */
   void generarLtxDiscretitzacio(ArrayList llistaProp,List alValors,boolean esPart,int tipus) throws CreacioFitxerException, IOException, OpcioIncorrectaException,ParamIncorrecteException {
	    int i, j, est, lon;	   
	    if (!esPart) {
	  	  // Si no forma part d'un altre doc cal afegir la capçalera i preparar-lo per escriure
	  	    fTex.copiarCapsaleraTex(Constants.FITXER_CAP_GRAL);
	  	    fTex.obrirPerEscriure(true);
	    }
	    if(tipus==1)escriureTitol("Discretitzaci\\'o basada en BoxPlot");
	    else if(tipus==2)escriureTitol("Discretitzaci\\'o uniforme");
	    else escriureTitol("Discretitzaci\\'o manual");
	    fTex.escriureLin("\\newpage");
	    for(int k=0;k<llistaProp.size();k++){
	    	PropCategorica p=(PropCategorica)llistaProp.get(k);	    	
	    	escriureNomBC("Propietat "+fTex.adaptarATex((String)p.obtenirId())+" :");
	    	ArrayList llistaMods=p.llModalitats;
	    	//float dadesAux[]=(float[])alValors.get(k);    	
	    	new TaulaFreqs(fTex,alValors.get(k),llistaMods,tipus);
	    }	    
	    if (!esPart) {
	      fTex.finalitzarTex();
	      fTex.tancarEsc();
	    } 
  }
   
   /**
    * Escriu un subtítol sense posar espais després d'ell
    * @param titol, títol a escriure
    * @throws IOException, si es produeix algun error d'escriptura
    * @author Laia Riera Guerra
    * @version v4
    * Data: 24/06/2007
    */
   void escriureSubtitolBC(String titol) throws IOException{
 	    String str;

 	    fTex.escriureLin("\\vspace{3ex}");
 	    fTex.escriureLin("\\mbox{ }");
 	    str = "\\begin{center} \\Large \\bf " + titol + " \\end{center}";
 	    fTex.escriureLin(str);
 	    fTex.escriureLin("");
 	    logger.finer("Titol escrit: " + str);
 	  }
   
   /**
    * Escriu un subtítol de un substítol
    * @param titol, títol que es vol escriure
    * @throws IOException
    * @author Laia Riera Guerra
    * @version v.4
    * Data: 24/06/2007
    */
   void escriureSubtitolSecundari(String titol) throws IOException{
 	    String str;
 	    fTex.escriureLin("\\newpage");
 	    fTex.escriureLin("\\vspace{3ex}");
 	    fTex.escriureLin("\\mbox{ }");
 	    str = "\\begin{center} \\Large \\bf " + titol + " \\end{center}";
 	    fTex.escriureLin(str);
 	    fTex.escriureLin("");
 	    logger.finer("Titol escrit: " + str);
 	  }

   void tancarFitxer() throws IOException	{
	   fTex.finalitzarTex();
   }
}
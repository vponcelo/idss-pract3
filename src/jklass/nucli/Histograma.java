package jklass.nucli;

import java.io.IOException;
import java.util.Hashtable;

import jklass.util.Rang;

/**
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.1
 */
public class Histograma extends RepresentacioGrafica {

  public Histograma(FitxerTex fitxer, String var, CalculsUnivNum calculs, Hashtable opc, boolean mult) throws OpcioIncorrectaException{
    super(fitxer);

    float minEixX, maxEixX, minEixY, maxEixY, amp, minC, maxC;
    int i, nClasses;
    int foraX = 0; // foraX = -1, indica que hi han observacions fora dels limits del grafic per l'esquerra
    // foraX = 0, indica que no hi han observacions fora dels limits
    // foraX = 1, indica que hi han observacions fora dels limits per la dreta
    // foraX = 2, indica que hi han observacions fora dels limits pels dos extrems

    int foraY = 0; // foraY = -1, indica que hi han observacions fora dels limits del grafic per baix
    // foraY = 0, indica que no hi han observacions fora dels limits
    // foraY = 1, indica que hi han observacions fora dels limits per dalt
    // foraY = 2, indica que hi han observacions fora dels limits pels dos extrems

    float ct, pt1, pt2, ptIni, f1, f2;
    float factX, factY;
    String str,op;
    boolean cont, classes, etiq;
    float minX, maxX, minY, maxY, maxRel;
    Rang[] llistaModal;
    float[] llistaVal;
    Histograma histo;
    int j, n, lon, nUtil;
    boolean freq;

    if (mult) {
      minX = Float.parseFloat((String) opc.get("min"));
      maxX = Float.parseFloat((String) opc.get("max"));
      minY = Float.parseFloat((String) opc.get("minFreq"));
      maxY = Float.parseFloat((String) opc.get("maxFreq"));
      maxRel = Float.parseFloat((String) opc.get("maxFreq"));
    }
    else {
      minX = calculs.obtenirMin();
      maxX = calculs.obtenirMax();
      minY = calculs.obtenirMinFreq();
      maxY = calculs.obtenirMaxFreq();
    }
    n = calculs.obtenirNumObjs();
    llistaModal = calculs.obtenirLlRangs();
    llistaVal = calculs.obtenirLlFreq();
    freq = ((String)opc.get("tipus")).equals("freqs");
    if (freq) {
      j = 0;
      lon = llistaVal.length;
      nUtil = calculs.obtenirNumUtils();
      while (j < lon){
        llistaVal[j] = llistaVal[j]/nUtil;
        j++;
      }
      maxY = maxY/nUtil;
    }
    if ( ( (String) opc.get("limitsY")).equals("complet")) {
      if (freq){
        maxY = 1;
      } else {
        maxY = n;
      }
    }


    try {
      // Opcions de l'eix X
      op = (String)opc.get("limitsX");
      if (op.equals("observat")){
        minEixX = minX;
        maxEixX = maxX;
      } else if (op.equals("teoric")){
        minEixX = Float.parseFloat((String)opc.get("minXT"));
        maxEixX = Float.parseFloat((String)opc.get("maxXT"));
      } else { // limits definits per l'usuari
        minEixX = Float.parseFloat((String)opc.get("minX"));
        maxEixX = Float.parseFloat((String)opc.get("maxX"));
        if (minEixX > maxEixX) throw new OpcioIncorrectaException("Histograma - Valor mínim dels límits indicat per l'usuari superior al màxim");
        if (minEixX > minX) {
          foraX = -1;
          if (maxEixX < maxX) {
            foraX = 2;
          }
        } else { // minEixX <= minX
          if (maxEixX < maxX) {
            foraX = 1;
          }
        }
      }
      classes = ((String)opc.get("graduaX")).equals( "classes"); // indica si cal graduar l'eix X amb la modalitat de cada classe
      // Opcions de l'eix Y
      op = (String) opc.get("limitsY");
      if (op.equals("complet")) {
        minEixY = 0;
        maxEixY = maxY;
      }
      else if (op.equals("auto")) {
        minEixY = 0;
        maxEixY = maxY;
      }
      else { // limits definits per l'usuari
        minEixY = Float.parseFloat( (String) opc.get("minY"));
        maxEixY = Float.parseFloat( (String) opc.get("maxY"));
        if ( (maxY > 0) && (minEixY > 0))
          foraY = -1;
      }
      if (minEixY > maxEixY)
        throw new OpcioIncorrectaException("Histograma - Valor mínim dels límits de l'eix Y indicat per l'usuari superior al màxim");

      escriureIniciHisto(var, mult);
      dibuixarYEtiquetarEixos(null, null);

      // Es calculen els factors de conversió que caldrá aplicar a les dades per dibuixar els punts
      factX = (Constants.AMPLADA - Constants.OFFSET_X - Constants.OFFSET_EIX) /
          (maxEixX - minEixX);
      factY = (Constants.ALZADA - Constants.OFFSET_Y - Constants.OFFSET_EIX) /
          (maxEixY - minEixY);
      nClasses = llistaVal.length;
      minC = llistaModal[0].obtenirMin();
      maxC = llistaModal[nClasses -1].obtenirMax();
      logger.finer("Num. de classes: " + nClasses + " (min:" + minC + ", max:" + maxC + ")");
      logger.finest("minEixX: "  + minEixX + " ,maxEixX: " + maxEixX);

      ct = ((maxC - minC) / nClasses )* factX; // ct. d'amplada entre classe i classe
      logger.finest("Amplada entre classe i classe: " + ct);
      i = 0;
      pt1 = Float.NaN; // cantonada inf. esq. del rectangle
      // s'obtenen els limits de la classe inicial
      f1 = llistaModal[0].obtenirMin(); // valor inicial de la classe
      f2 = llistaModal[0].obtenirMax(); // valor final de la classe
      if (! (foraX == -1)) {
        ptIni = (f1 - minEixX) * factX;
      }
      else {
        ptIni = Float.NaN;
      }
      cont = true; // indica si rectangle amb linia continua
      etiq = false;
      amp = ct;
      op = (String)opc.get("graduaX");
      while (i < nClasses) {
        // s'obtenen els limits de cada classe
        f1 = llistaModal[i].obtenirMin();
        f2 = llistaModal[i].obtenirMax();
        pt1 = ptIni + ct * i;
        logger.finest("Freq. class " + i + " = " + llistaVal[i]);
        if (llistaVal[i] <= maxEixY) {
          pt2 = llistaVal[i];   // pt. superior del rectangle
        }
        else {
          pt2 = maxEixY;
          if (foraY == 0)
            foraY = 1;
          else if (foraY == -1)
            foraY = 2;
        }
        if (pt2 >= minEixY) {
          // si els limits de la clase estan fora dels limits de dibuix
          //establerts per l'usuari comprobem si cal dibuixar rectangle especial
            if (Float.isNaN(ptIni)) {
              ptIni = (f1 - minEixX) * factX;
            }
            if (minEixX > f1) {
              cont = false;
              pt1 = 0;  // (minEix - minEixX) * factX
              if (maxEixX < f2) {
                amp = (maxEixX - minEixX) * factX;
              }
              else { // maxEixX >= f2
                if (minEixX > f2) {
                  if (foraX == 0)
                    foraX = -1;
                  else if (foraX == 1)
                    foraX = 2;
                  amp = -1;
                }
                else {
                  amp = (f2 - minEixX) * factX;
                }
              }
            }
            else { // minEixX <= f1
              if (maxEixX < f1) {
                if (foraX == 0)
                  foraX = 1;
                else if (foraX == -1)
                  foraX = 2;
                cont = false;
                amp = -1;
              } else { // maxEix >= f1
                if (maxEixX < f2) {
                  cont = false;
                  amp = (maxEixX - f1) * factX;
                }
              }
            }

          if (cont) { // rectangle amb linia continua
            amp = ct;
            // dibuixem el rectangle
            fTex.escriureLin("\\put(" + fTex.formatejarReal(pt1) +
                             ",0){\\framebox( " + fTex.formatejarReal(amp) + ", " +
                             fTex.formatejarReal( (pt2 - minEixY) * factY) +
                             "){}}");
            logger.finest("Alzada dibuixada: " + (pt2 - minEixY) * factY);
            if (classes) {
              // cal etiquetar amb els rangs de cada classe (modalitats)
              etiq = true;
            }
          }
          else {
            if (amp > 0) {
              // rectangle amb linia discontinua
              fTex.escriureLin("\\put(" + fTex.formatejarReal(pt1) +
                               ",0){\\dashbox( " + fTex.formatejarReal(amp) + ", " +
                               fTex.formatejarReal( (pt2 - minEixY) * factY) +
                               "){}}");
              if (classes) {
                // cal etiquetar amb els rangs de cada classe (modalitats)
                etiq = true;
                if (f1 < minEixX) {
                  f1 = minEixX;
                }
              }
            }
          }

        }
        else {
          if (foraY == 0)
            foraY = -1;
          else if (foraY == 1)
            foraY = 2;

          if (classes) {
            if (f1 >= minEixX){
              if (f1 <= maxEixX){
                  etiq = true;
              }
            } else {
              etiq = true;
              f1 = minEixX;
            }
          }
        }
        if ((etiq) && !(op.equals( "no"))){
          // posem una etiqueta indican l'inici de la classe q acabem de dibuixar
          fTex.escriureLin("\\put(" + fTex.formatejarReal(pt1) +
                           ",-4){\\line(0,1){4}}");
          fTex.escriureLin("\\put(" + fTex.formatejarReal(pt1) + ",-15){" +
                               fTex.formatejarReal(f1) + "}");
          etiq = false;
        }
        i++;
        cont = true;
      }

      if (classes) {
        if (nClasses > 0) {
          if (!(op.equals( "no"))){
            //cal etiquetar l'ultim limit de la ultima classe
            fTex.escriureLin("\\put(" + fTex.formatejarReal(pt1 + amp) +
                             ",-4){\\line(0,1){4}}");
            if (f2 < maxEixX) {
              str = fTex.formatejarReal(f2);
            }
            else {
              str = fTex.formatejarReal(maxEixX);
            }
            fTex.escriureLin("\\put(" + fTex.formatejarReal(pt1 + amp) +
                             ",-15){" +
                             str + "}");
          }
        }
      }
      else  if (op.equals("vista")){
        // etiqueta minimo
        if (minX >= minEixX){
          pt1 = (minX - minEixX) * factX;
          str = fTex.formatejarReal(minX);
        }
        else {
          pt1 = 0;
          str = fTex.formatejarReal(minEixX);
        }
        fTex.escriureLin("\\put(" + fTex.formatejarReal(pt1) +
                         ",-4){\\line(0,1){4}}");
        fTex.escriureLin("\\put(" + fTex.formatejarReal(pt1) +
                         ",-" + Constants.OFFSET_Y + "){" +
                         str + "}");
        // etiqueta máximo
        if (maxX <= maxEixX) {
          pt1 = (maxX - minEixX) * factX;
          str = fTex.formatejarReal(maxX);
        }
        else {
          pt1 = (maxEixX - minEixX) * factX;;
          str = fTex.formatejarReal(maxEixX);
        }
        fTex.escriureLin("\\put(" + fTex.formatejarReal(pt1) +
                         ",-4){\\line(0,1){4}}");
        fTex.escriureLin("\\put(" + fTex.formatejarReal(pt1) +
                         ",-" + Constants.OFFSET_Y + "){" +
                         str + "}");


      } else if (!(op.equals( "no"))){
        // cal posar un nombre de marques definit per l'usuari al eix X
        str = (String) opc.get("nummarqX");
        if (str != null) {
          graduarYEtiquetarEixX(minEixX, maxEixX, new Integer(str).intValue());
        }
      }
      if (!(((String)opc.get("graduaY")).equals( "no"))){
        // graduació amb etiquetatge del eix Y
        str = (String) opc.get("nummarqY");
        if (str != null) {
          graduarYEtiquetarEixY(minEixY, maxEixY, new Integer(str).intValue());
        }
      }

      if (!mult) {
        // Es posa una nota si queda informació fora dels limits indicats per l'usuari per l'eix X
        if (foraX == 2) {
          fTex.escriureLin("\\put(0," +
                           (Constants.ALZADA - Constants.OFFSET_Y + 25) +
                           "){+ Les classes en discont\\'inua estan tallades.}");
          fTex.escriureLin("\\put(0," +
                           (Constants.ALZADA - Constants.OFFSET_Y + 15) +
              "){+ Hi ha observacions que queden fora del gr\\`afic (a dreta i esquerra).}");
        }
        else if (foraX == -1) {
          fTex.escriureLin("\\put(0," +
                           (Constants.ALZADA - Constants.OFFSET_Y + 25) +
                           "){+ Les classes en discont\\'inua estan tallades.}");
          fTex.escriureLin("\\put(0," +
                           (Constants.ALZADA - Constants.OFFSET_Y + 15) +
              "){+ Hi ha observacions que queden fora del gr\\`afic (a l'esquerra).}");
        }
        else if (foraX == 1) {
          fTex.escriureLin("\\put(0," +
                           (Constants.ALZADA - Constants.OFFSET_Y + 25) +
                           "){+ Les classes en discont\\'inua estan tallades.}");
          fTex.escriureLin("\\put(0," +
                           (Constants.ALZADA - Constants.OFFSET_Y + 15) +
              "){+ Hi ha observacions que queden fora del gr\\`afic (a la dreta).}");
        }

        // Es posa una nota si queden observacions fora dels limits indicats per l'usuari per l'eix Y
        if (foraY == 2) {
          fTex.escriureLin("\\put(0," +
                           (Constants.ALZADA - Constants.OFFSET_Y + 5) +
              "){+ Hi ha informaci\\'o que queda fora del gr\\`afic (per dalt i per baix).}");
        }
        else if (foraY == -1) {
          fTex.escriureLin("\\put(0," +
                           (Constants.ALZADA - Constants.OFFSET_Y + 5) +
              "){+ Hi ha informaci\\'o que queda fora del gr\\`afic (per baix).}");
        }
        else if (foraY == 1) {
          fTex.escriureLin("\\put(0," +
                           (Constants.ALZADA - Constants.OFFSET_Y + 5) +
              "){+ Hi ha informaci\\'o que queda fora del gr\\`afic (per dalt).}");
        }
      }
      escriureFiHisto(mult);
      generarTaulaClasses(calculs, opc) ;
    }
    catch (IOException e) {
      logger.warning("ERROR: Error en la creació del histograma. " +
                     e.getMessage());
    }

  }

  private void escriureIniciHisto(String str, boolean multi) throws IOException {
    logger.finer("Inici histograma simple.");
    if (!multi){
      fTex.escriureLin("\\mbox{ } \\vfill");
      fTex.escriureLin("\\begin{center}");
      if (str == null) {
        fTex.escriureLin(
            "{\\hspace{60pt}\\bf Histograma }\\vspace{0.5em}");
      }
      else {
        fTex.escriureLin("{\\hspace{60pt}\\bf Histograma de la variable " +
                         str + " }\\vspace{0.5em}");
      }
      fTex.escriureLin("");
      fTex.escriureLin("\\vspace{6ex}");
      fTex.escriureLin("\\noindent");
      fTex.escriureLin("\\setlength{\\unitlength}{0.95 pt}");
      fTex.escriureLin("\\scriptsize");
    }
    fTex.escriureLin("\\begin{picture}(" + Constants.AMPLADA + ","
                     + Constants.ALZADA + ")(-" + Constants.OFFSET_X
                     + ",-" + Constants.OFFSET_Y + ")");
  }

  private void graduarYEtiquetarEixX(float minX, float maxX, int nMarques) throws
      IOException {
    float ct, lon, pt;
    int i;

    logger.finer("Es gradua i etiqueta l'eix X.");
    // cal restar l'offset per obtenir la longitud real del eix dibuixat
    // i l'offset que deixem al final de l'eix
    lon = Constants.AMPLADA - Constants.OFFSET_X - Constants.OFFSET_EIX;
    ct = (maxX - minX) / (nMarques - 1);
    for (i = 0; i < nMarques; i++) {
      pt = (lon / (nMarques - 1)) * i;
      fTex.escriureLin("\\put(" + pt + ",-4){\\line(0,1){4}}");
      fTex.escriureLin("\\put(" + pt + ",-6){\\makebox(0,0)[t]{" + fTex.formatejarReal(minX + ct * i) + "}}");
    }
  }

  private void graduarYEtiquetarEixY(float minY, float maxY, int nMarques) throws IOException {
   float ct1, ct2, lon, pt;
   int i;

    logger.finer("Es gradua i etiqueta l'eix Y.");
    // graduació amb etiquetatge del eix Y
    // cal restar l'offset per obtenir la longitud real del eix dibuixat
    // i l'offset que deixem al final de l'eix
    lon = Constants.ALZADA - Constants.OFFSET_Y - Constants.OFFSET_EIX;
    ct1 = lon / (nMarques - 1);
    ct2 = (maxY - minY) / (nMarques - 1);
    for (i = 0; i < nMarques; i++) {
      pt = ct1 * i;
      fTex.escriureLin("\\put(-4," + pt + "){\\line(1,0){4}}");
      fTex.escriureLin("\\put(-6," + pt + "){\\makebox(0,0)[r]{" + fTex.formatejarReal(minY + ct2*i) + "}}");
    }

  }

  private void escriureFiHisto(boolean multi) throws IOException {
    logger.finer("Inici histograma simple.");
    fTex.escriureLin("\\end{picture}");
    if (!multi) {
      fTex.escriureLin("\\end{center} \\vfill");
      fTex.escriureLin("");
    }
  }

  private void generarTaulaClasses(CalculsUnivNum calculs, Hashtable opcs) throws OpcioIncorrectaException, IOException {
    float minX, maxX, minY, maxY;
    Rang[] llistaModal;
    float[] llistaVal;
    Histograma histo;
    int j, n, lon, nUtil;
    boolean freq;
    //vars per la taula
    boolean freqs[] = {
        false, false, false, false};
    int nCol = 0, i;
    float valors[] = {
        Float.NaN, Float.NaN, Float.NaN, Float.NaN};
    float missings;
    String modal;

    llistaModal = calculs.obtenirLlRangs();
    llistaVal = calculs.obtenirLlFreq();
    freq = ( (String) opcs.get("tipus")).equals("freqs");

    if ( ( (String) opcs.get("taula")).equals("si")) {
      // es genera la taula de classes de freqs
      freqs[0] = !freq;
      freqs[2] = freq;

      if (freqs[0])
        nCol++;
      if (freqs[2])
        nCol++;

      escriureIniciTFreqs(nCol, null, freqs, false);
      lon = llistaModal.length;
      i = 0;
      while (i < lon) {
        j = 0;
        if (freqs[0]) {
          valors[j] = llistaVal[i];
          j++;
        }
        if (freqs[2]) {
          valors[j] = llistaVal[i];
          j++;
        }
        modal = fTex.formatejarReal(llistaModal[i].obtenirMin()) + " - " +
            fTex.formatejarReal(llistaModal[i].obtenirMax()); ;
        escriureFilaTFreqs(nCol, modal, valors);
        i++;
      }
      escriureLiniesHor(1);
      missings = calculs.obtenirNumMancants();
      j = 0;
      if (freqs[0]) {
        valors[j] = missings;
        j++;
      }
      if (freqs[2]) {
        valors[j] = missings / calculs.obtenirNumObjs();
        j++;
      }
      escriureMancantsTFreqs(nCol, valors);
      escriureFiTFreqs();
    }
  }

     private void escriureIniciTFreqs(int cols, String var, boolean[] freqs, boolean especial) throws IOException{
       String str0 = "\\begin{tabular}{|c|" , str1 = "{\\bf Modalitats}", str2 = "";
       int i;

       fTex.escriureLin("\\begin{center}");
       // es preparen els strings per les linies inicials de la taula
       if (freqs[0]){
         str0 = str0 + "c|";
         str1 = str1 + " & {\\bf Freq.}";
         str2 = str2 + " & {\\bf absol.}";
       }
       if (freqs[1]){
         str0 = str0 + "c|";
         str1 = str1 + " & {\\bf Freq.}";
         str2 = str2 + " & {\\bf acum.}";
       }
       if (freqs[2]) {
         if (especial)
           str0 = str0 + "@{}c@{}|";
         else  str0 = str0 + "c|";
         str1 = str1 + " & {\\bf Freq.}";
         str2 = str2 + " & {\\bf relat.}";
       }
       if (freqs[3]) {
         str0 = str0 + "@{}c@{}|";
         str1 = str1 + " & {\\bf Freq.}";
         str2 = str2 + " & {\\bf rel. acum.}";
       }
       str0 = str0 + "}";
       str1 = str1 + " \\\\ ";
       str2 = str2 + " \\\\ ";

       fTex.escriureLin(str0);
       fTex.escriureLin("\\hline");
       if (var == null) {
         fTex.escriureLin(
             "\\multicolumn{" + (cols + 1) + "}{|c|}{\\bf Taula de freq\\\" u\\`encies} \\\\ ");
       }
       else {
         fTex.escriureLin(
             "\\multicolumn{" + (cols + 1) + "}{|c|}{\\bf Taula de freq\\\" u\\`encies Variable " +
             var + "} \\\\ ");
       }

       fTex.escriureLin("\\hline");
       fTex.escriureLin(str1);
       fTex.escriureLin(str2);
       fTex.escriureLin("\\hline");
       fTex.escriureLin("\\hline");
     }

     private void escriureFilaTFreqs(int cols, String modal, float[] valors) throws
         IOException {
       String str;
       int i;

       // Modalitats
       str = fTex.adaptarATex(modal);
       // Valors de les freqs
       for (i = 0; i < cols; i++) {
         str = str + " & " + fTex.formatejarReal(valors[i]);
       }
       str = str + " \\\\ ";
       fTex.escriureLin(str);
     }

     private void escriureLiniesHor(int num) throws IOException {
       int i=1;
       if (num > 0) {
         while (i <= num) {
           fTex.escriureLin("\\hline");
           i++;
         }
       }
     }

     private void escriureMancantsTFreqs(int cols, float[] valors) throws IOException {
       String str = "\\it dades mancants";
       int i, ancho;

       fTex.escriureLin("\\hline");
       for (i=0; i < cols;  i++) {
         if (Float.isNaN(valors[i])) {
           ancho = fTex.obtenirMaxDecimals()+5;
           if (ancho < Constants.MIN_COL_TAB) ancho = Constants.MIN_COL_TAB;
           str = str + " & \\colorbox{gris}{\\color{gris}$|$ \\hspace{" + ancho + "ex}}";
         } else {
           str = str + " & " + fTex.formatejarReal(valors[i]);
         }
       }
       str = str + " \\\\ ";
       fTex.escriureLin(str);
     }

     private void escriureNumUtilsTFreqs(int cols, float[] valors) throws IOException {
       String str = "\\it dades \\'utils";
       int i;

       fTex.escriureLin("\\hline");
       for (i=0; i < cols;  i++) {
         if (Float.isNaN(valors[i])) {
           str = str + " & \\colorbox{gris}{\\color{gris}$|$ \\hspace{" + (fTex.obtenirMaxDecimals() + 5) + "ex}}";
         } else {
           str = str + " & " + fTex.formatejarReal(valors[i]);
         }
       }
       str = str + " \\\\ ";
       fTex.escriureLin(str);
     }

     private void escriureNumObsTFreqs(int cols, float[] valors) throws IOException {
       String str = "\\it total observacions";
       int i;

       fTex.escriureLin("\\hline");
       for (i = 0; i < cols; i++) {
         if (Float.isNaN(valors[i])) {
           str = str + " & \\colorbox{gris}{\\color{gris}$|$ \\hspace{" +
               (fTex.obtenirMaxDecimals() + 5) + "ex}}";
         }
         else {
           str = str + " & " + fTex.formatejarReal(valors[i]);
         }
       }
       str = str + " \\\\ ";
       fTex.escriureLin(str);
     }

     private void escriureFiTFreqs() throws IOException {
       fTex.escriureLin("\\hline");
       fTex.escriureLin("\\end{tabular}");
       fTex.escriureLin("\\end{center} \\vfill");
       fTex.escriureLin("");
     }

}

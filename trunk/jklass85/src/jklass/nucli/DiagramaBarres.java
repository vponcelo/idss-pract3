package jklass.nucli;

import java.io.IOException;
import java.util.Hashtable;

/**
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.1
 */
public class DiagramaBarres extends RepresentacioGrafica {

  public DiagramaBarres(FitxerTex fitxer, String var, CalculsUnivCateg calculs, Hashtable opc, boolean mult) throws OpcioIncorrectaException{
    super(fitxer);

    float minEix, maxEix, et;
    float factX, factY;
    float lon, ct1, ct2, pt1, pt2;
    int i, maxL, nMarques, fora = 0; // fora = -1, indica que hi han observacions fora dels limits del grafic per baix
                               // fora = 0, indica que no hi han observacions fora dels limits
                               // fora = 1, indica que hi han observacions fora dels limits per dalt
                               // fora = 2, indica que hi han observacions fora dels limits pels dos extrems

    String str, op;
    boolean abs;
    int j, iLon, n;
    String[] llistaMod;
    float max;
    float[] llistaVal;
    boolean efect;

    n = calculs.obtenirNumUtils();
    max = calculs.obtenirMaxFreq();
    if ( ( (String) opc.get("limits")).equals("complet")) {
      max = n;
    }
    if (mult) {
      max = Float.parseFloat((String) opc.get("maxFreq"));
    }
    iLon = calculs.obtenirLonLlistaMods();
    llistaMod = new String[iLon];
    llistaVal = new float[iLon];
    j = 0;
    efect = ( (String) opc.get("tipus")).equals("efectius");
    while (j < iLon) {
      llistaMod[j] = calculs.obtenirModalitat(j);
      if (efect) {
        llistaVal[j] = calculs.obtenirFreq(j);
      }
      else {
        llistaVal[j] = calculs.obtenirFreqRel(j);
      }
      j++;
    }
    if (!efect){
      if (mult){
        max = Float.parseFloat((String) opc.get("maxRel"));
      } else {
        max = max / n;
      }
    }

    try {
      escriureIniciDB(var, mult);
      dibuixarYEtiquetarEixos(null, null);
      abs = ((String)opc.get("tipus")).equals("efectius"); // es tracta amb valors absoluts?
      op = (String)opc.get("limits");
      if (op.equals("complet")){
        minEix = 0;
        if (abs){
          maxEix = max;
        } else {
          maxEix = Float.parseFloat((String) opc.get("maxFreq"));
        }
      } else if (op.equals("auto")){
        minEix = 0;

        maxEix = max;
      }
      else { // limits definits per l'usuari
        minEix = Float.parseFloat( (String) opc.get("min"));
        maxEix = Float.parseFloat( (String) opc.get("max"));
        if ((max > 0) && (minEix > 0)) fora = -1;
      }
      if (minEix > maxEix) throw new OpcioIncorrectaException("Diagrama de barres - Valor mínim dels límits indicat per l'usuari superior al màxim");
      // Es calculen els factors de conversió que caldrá aplicar a les dades per dibuixar els punts
      factY = (Constants.ALZADA - Constants.OFFSET_Y - Constants.OFFSET_EIX)/(maxEix - minEix);
      // graduació amb etiquetatge del eix X, si cal, i traçat de les barres
      nMarques = llistaMod.length;
      // cal restar l'offset per obtenir la longitud real del eix dibuixat i
      // també el marge que deixem entre la primera marca i el inici del eix i la última marca i el final de l'eix
      lon = (Constants.AMPLADA - Constants.OFFSET_X) - (Constants.OFFSET_EIX * 2) ;
      ct1 = lon/(nMarques -1);
      op = (String) opc.get("graduaX");
      if (op.equals("vista")) {
        maxL = Constants.MAX_ETIQ; //per la vista d'ocell hi ha una long. màxima per la modalitat
      } else {
        maxL = -1; // valor invàlid
      }
      for (i = 0; i < nMarques; i++){
        pt1 = ct1*i;
        if (! ( op.equals("no"))) {
          fTex.escriureLin("\\put(" +
                           fTex.formatejarReal(pt1 + Constants.OFFSET_EIX) +
                           ",-4){\\line(0,1){4}}");
          str = adaptarModalitat(llistaMod[i], maxL);
          fTex.escriureLin("\\put(" +
                           fTex.formatejarReal(pt1 + Constants.OFFSET_EIX) +
                           ",-6){\\makebox(0,0) [t] {\\shortstack{" + str +
                           "}}}");
        }
        // dibuixem la barra
        if  (llistaVal[i]<= maxEix) {
          pt2 = llistaVal[i];
        } else {
          pt2 = maxEix;
          if (fora == 0) fora = 1;
          else if (fora == -1) fora = 2;
        }
        if (pt2 >= minEix) {
          fTex.escriureLin("\\put(" +
                           fTex.formatejarReal(pt1 + Constants.OFFSET_EIX) +
                           ", 0){\\line(0,1){" +
                           fTex.formatejarReal( (pt2 - minEix) * factY) + "}}");
        } else {
          if (fora == 0) fora = -1;
          else if (fora == 1) fora = 2;
        }
      }

      if (! ( ( (String) opc.get("graduaY")).equals("no"))) {
        // graduació amb etiquetatge del eix Y
        str = (String) opc.get("nummarq");
        if (str != null) {
          nMarques = new Integer(str).intValue();
          lon = Constants.ALZADA - Constants.OFFSET_Y - Constants.OFFSET_EIX;
          ct1 = lon / (nMarques - 1);
          ct2 = (maxEix - minEix) / (nMarques - 1);
          for (i = 0; i < nMarques; i++) {
            pt1 = ct1 * i;
            et = minEix + ct2 * i;
            fTex.escriureLin("\\put(-4," + fTex.formatejarReal(pt1) +
                             "){\\line(1,0){4}}");
            fTex.escriureLin("\\put(-6," + fTex.formatejarReal(pt1) +
                             "){\\makebox(0,0)[r]{" + fTex.formatejarReal(et) +
                             "}}");
          }
        }
      }
      if (!mult) {
        // Es posa una nota si queden observacions fora dels limits indicats per l'usuari
        if (fora == 2) {
          fTex.escriureLin("\\put(0," +
                           (Constants.ALZADA - Constants.OFFSET_Y + 10) +
              "){+ Hi ha informaci\\'o que queda fora del gr\\`afic (per dalt i per baix).}");
        }
        else if (fora == -1) {
          fTex.escriureLin("\\put(0," +
                           (Constants.ALZADA - Constants.OFFSET_Y + 10) +
              "){+ Hi ha informaci\\'o que queda fora del gr\\`afic (per baix).}");
        }
        else if (fora == 1) {
          fTex.escriureLin("\\put(0," +
                           (Constants.ALZADA - Constants.OFFSET_Y + 10) +
              "){+ Hi ha informaci\\'o que queda fora del gr\\`afic (per dalt).}");
        }
      }
      escriureFiDB(mult);
    }
    catch (IOException e) {
      logger.warning("ERROR: Error en la creació del diagrama de barres. " + e.getMessage());
    }

  }

  private void escriureIniciDB(String str, boolean multi) throws IOException {
    if (!multi) {
      fTex.escriureLin("\\mbox{ } \\vfill");
      fTex.escriureLin("\\begin{center}");
      if (str == null) {
        fTex.escriureLin(
            "{\\hspace{60pt}\\bf Diagrama de barres }\\vspace{0.5em}");
      }
      else {
        fTex.escriureLin(
            "{\\hspace{60pt}\\bf Diagrama de barres de la variable " +
            str + " }\\vspace{0.5em}");
      }
      fTex.escriureLin("");
      fTex.escriureLin("\\vspace{4ex}");
      fTex.escriureLin("\\noindent");
      fTex.escriureLin("\\setlength{\\unitlength}{0.95 pt}");
      fTex.escriureLin("\\scriptsize");
    }
    fTex.escriureLin("\\begin{picture}(" + Constants.AMPLADA + ","
                     + Constants.ALZADA + ")(-" + Constants.OFFSET_X
                     + ",-" + Constants.OFFSET_Y + ")");
  }

  private String adaptarModalitat(String mod, int lon) {
    String strTex, str = new String();
    int i, max; // longitud maxima que pot ocupar la modalitat
    char c;

    max = mod.length();
    if ((lon > 0) && (max>lon)){
      max = lon;
    }
    for (i = 0; i < max; i++) {
      c = mod.charAt(i);
      strTex = fTex.adaptarATex(String.valueOf(c));
      str = str + "\\\\" + strTex;
    }

    return str;
  }

  private void escriureFiDB(boolean multi) throws IOException {
    fTex.escriureLin("\\end{picture}");
    if (!multi) {
      fTex.escriureLin("\\end{center} \\vfill");
      fTex.escriureLin("");
    }
  }

}
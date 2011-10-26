package jklass.nucli;

import java.io.IOException;
import java.util.Hashtable;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */
public class Boxplot extends RepresentacioGrafica {
  public Boxplot(FitxerTex fitxer, String var, CalculsUnivNum calculs,
                 Hashtable opc, boolean mult) throws OpcioIncorrectaException {
    super(fitxer);

    float minEix, maxEix; // limits que es fan servir per dibuixar
    float ptMig,  pt, factX, factY;
    float ptQ1 = Float.NaN, ptQ3 = Float.NaN, ptMe = Float.NaN, p1 = Float.NaN, p2 = Float.NaN, p3 = Float.NaN, p4= Float.NaN, pt1= Float.NaN, pt2 = Float.NaN;
    int i, lon, nMarques, fora = 0; // fora = -1, indica que hi han observacions fora dels limits del grafic per l'esquerra
                                    // fora = 0, indica que no hi han observacions fora dels limits
                                    // fora = 1, indica que hi han observacions fora dels limits per la dreta
                                    // fora = 2, indica que hi han observacions fora dels limits pels dos extrems
    String nota = null, op;
    float min, max, me, q1, q3, diq;
    float[] dades;


    dades = calculs.obtenirDades();
    min = calculs.obtenirMin();
    max = calculs.obtenirMax();
    me = calculs.obtenirMediana();
    q1 = calculs.obtenirQ1();
    q3 = calculs.obtenirQ3();
    diq = calculs.obtenirDistInterQ();

    try {
      escriureIniciBxp(var, mult);
      dibuixarYEtiquetarEixos(null, null);
      op = (String)opc.get("limits");
      if (op.equals("observat")){
        minEix = min;
        maxEix = max;
      } else if (op.equals("teoric")){
        minEix = Float.parseFloat((String)opc.get("minT"));
        maxEix = Float.parseFloat((String)opc.get("maxT"));
      } else { // limits definits per l'usuari
        minEix = Float.parseFloat((String)opc.get("min"));
        maxEix = Float.parseFloat((String)opc.get("max"));
        if (minEix > maxEix) throw new OpcioIncorrectaException("Boxplot - Valor mínim dels límits indicat per l'usuari superior al màxim");
        if (minEix > q1) {
          fora = -1;
          if (maxEix < q3) {
            fora = 2;
          }
        } else if (maxEix < q3){
          fora = 1;
        }
      }
      if (minEix == maxEix) throw new OpcioIncorrectaException("Boxplot - Límits iguals");
      // Es calculen els factors de conversió que caldrá aplicar a les dades per dibuixar els punts
      factX = (Constants.AMPLADA - Constants.OFFSET_X - 2*Constants.OFFSET_EIX)/(maxEix - minEix);

      ptMig = (Constants.ALZADA - Constants.OFFSET_Y)/2;

      if ((minEix <= q3) && (maxEix >= q1)){
        // dibuixem el rectangle tenint en compte un petit offset del marge que es deixa del origen dels eixos
        ptQ1 = (q1 - minEix) * factX;
        ptQ3 = (q3 - minEix) * factX;
        if (fora == 0) {
          pt1 = ptQ1;
          pt2 = ptQ3;
        } else if (fora == -1) {
          pt1 = 0;
          pt2 = ptQ3;
        } else if (fora == 2) {
          pt1 = 0;
          pt2 = (maxEix - minEix) * factX;
        } else if (fora == 1) {
          pt1 = ptQ1;
          pt2 = (maxEix - minEix) * factX;
        }
        fTex.escriureLin("\\put(" +
                         fTex.formatejarReal(pt1 + Constants.OFFSET_EIX) +
                         ", " +
                         fTex.formatejarReal(ptMig - (Constants.AMP_CAIXA / 2)) +
                         "){\\framebox( " + fTex.formatejarReal(pt2 - pt1) +
                         ", " +
                         Constants.AMP_CAIXA + "){}}");
      } else { // el grafic no engloba el rectangle dels quartils, esta a un dels extrems
        pt1 = (maxEix - minEix) * factX;
        pt2 = 0;
      }

      if (minEix > me){
        if (fora == 1)
          fora = 2;
      } else if (maxEix < me){
        if (fora == -1)
          fora = 2;
      } else {
        // dibuixem la linea de la mediana
        ptMe = (me - minEix) * factX;
        fTex.escriureLin("\\put(" +
                         fTex.formatejarReal(ptMe + Constants.OFFSET_EIX) +
                         ", " +
                         fTex.formatejarReal(ptMig - (Constants.AMP_CAIXA / 2)) +
                         "){\\line(0,1){" +
                         Constants.AMP_CAIXA + "}}");
      }

      if (!(fora == 2)) {
        if (!(fora == -1)) {
          p1 = Math.max(min, (q1 - 1.5F * diq));
          if (maxEix >= p1) {
            // dibuixem el  bigoti esquerre
            if (minEix > p1) {
              pt = 0;
              if (fora == 1)
                fora = 2;
              else
                fora = -1;
            } else {
              pt = (p1 - minEix) * factX;
            }
            fTex.escriureLin("\\put(" +
                             fTex.formatejarReal(pt + Constants.OFFSET_EIX) +
                             ", " + fTex.formatejarReal(ptMig) +
                             "){\\line(1,0){" +
                             fTex.formatejarReal(pt1 - pt) + "}}");
          }
        }
        if (!(fora == 1) && !(fora == 2)) {
          p2 = Math.min(max, (q3 + 1.5F * diq));
          if (minEix <= p2){
            // dibuixem el bigoti dret
            if (maxEix < p2) {
              pt = (maxEix - minEix) * factX;
              if (fora == -1)
                fora = 2;
              else
                fora = 1;
            } else {
              pt = (p2 - minEix) * factX;
            }
            fTex.escriureLin("\\put(" +
                             fTex.formatejarReal(pt2 + Constants.OFFSET_EIX) +
                             ", " +
                             fTex.formatejarReal(ptMig) + "){\\line(1,0){" +
                             fTex.formatejarReal(pt - pt2) + "}}");
          }
        }

        if (!(fora == 2)){
          // dibuixem els outliers
          p3 = Math.max(min, (q1 - 3 * diq));
          p4 = Math.min(max, (q3 + 3 * diq));
          lon = dades.length;
          for (i = 0; i < lon; i++) {
            // es comproba que no sigui un missing
           if (Float.compare(dades[i],Float.NaN) != 0) {
             //if ((dades[i] >= minEix) && (dades[i] <= maxEix)){
             if ( (dades[i] < p3) || (dades[i] > p4)) { // outlier extrem
               if (dades[i] >= minEix) {
                 if (dades[i] <= maxEix) {
                   pt = (dades[i] - minEix) * factX;
                   fTex.escriureLin("\\put(" +
                                    fTex.formatejarReal(pt +
                       Constants.OFFSET_EIX) +
                                    ", " + fTex.formatejarReal(ptMig) +
                                    "){\\makebox(0,0){$\\ast$}}");
                 }
                 else {
                   if (fora == 0)
                     fora = 1;
                   if (fora == -1)
                     fora = 2;
                 }
               }
               else {
                 if (fora == 0)
                   fora = -1;
                 if (fora == 1)
                   fora = 2;
               }
             }
             else if ( ( (dades[i] >= p3) && (dades[i] < p1)) ||
                      ( (dades[i] > p2) && (dades[i] <= p4))) { // outlier suau
               if (dades[i] >= minEix) {
                 if (dades[i] <= maxEix) {
                   pt = (dades[i] - minEix) * factX;
                   fTex.escriureLin("\\put(" +
                                    fTex.formatejarReal(pt +
                       Constants.OFFSET_EIX) +
                                    ", " + fTex.formatejarReal(ptMig) +
                                    "){\\makebox(0,0){$\\circ$}}");
                 }
                 else {
                   if (fora == 0)
                     fora = 1;
                   if (fora == -1)
                     fora = 2;
                 }
               }
               else {
                 if (fora == 0)
                   fora = -1;
                 if (fora == 1)
                   fora = 2;
               }
             }
           }
          } // final recurregut de les dades
        }
      }

      // Graduació de l'eix X
      op = (String)opc.get("gradua");
      if (op.equals("resum")){
        if ((min >= minEix) && (min <= maxEix)) {
          pt = ( (min - minEix) * factX) + Constants.OFFSET_EIX;
          fTex.escriureLin("\\put(" + fTex.formatejarReal(pt) +
                           ",-4){\\line(0,1){4}}");
          fTex.escriureLin("\\put(" + fTex.formatejarReal(pt) + ",-6){\\makebox(0,0)[t]{" +
                           fTex.formatejarReal(min) + "}}");
        }
        if ( (q1 >= minEix) && (q1 <= maxEix)) {
          pt = ptQ1 + Constants.OFFSET_EIX;
          fTex.escriureLin("\\put(" + fTex.formatejarReal(pt) +
                           ",-4){\\line(0,1){4}}");
          fTex.escriureLin("\\put(" + fTex.formatejarReal(pt) + ",-6){\\makebox(0,0)[t]{" +
                           fTex.formatejarReal(q1) + "}}");
        }
        if ((me >= minEix) && (me <= maxEix)) {
          pt = ptMe + Constants.OFFSET_EIX;
          fTex.escriureLin("\\put(" + fTex.formatejarReal(pt) +
                           ",-4){\\line(0,1){4}}");
          fTex.escriureLin("\\put(" + fTex.formatejarReal(pt) + ",-6){\\makebox(0,0)[t]{" +
                           fTex.formatejarReal(me) + "}}");
        }
        if ((q3 >= minEix) && (q3 <= maxEix)) {
          pt = ptQ3 + Constants.OFFSET_EIX;
          fTex.escriureLin("\\put(" + fTex.formatejarReal(pt) +
                           ",-4){\\line(0,1){4}}");
          fTex.escriureLin("\\put(" + fTex.formatejarReal(pt) + ",-6){\\makebox(0,0)[t]{" +
                           fTex.formatejarReal(q3) + "}}");
        }
        if ((max >= minEix) && (max <= maxEix)) {
          pt = ( (max - minEix) * factX) + Constants.OFFSET_EIX;
          fTex.escriureLin("\\put(" + fTex.formatejarReal(pt) +
                           ",-4){\\line(0,1){4}}");
          fTex.escriureLin("\\put(" + fTex.formatejarReal(pt) + ",-6){\\makebox(0,0)[t]{" +
                           fTex.formatejarReal(max) + "}}");
        }
      } else if (op.equals("vista")){
        if ( (min >= minEix) && (min <= maxEix)) {
          pt = ( (min - minEix) * factX) + Constants.OFFSET_EIX;
          fTex.escriureLin("\\put(" + fTex.formatejarReal(pt) +
                           ",-4){\\line(0,1){4}}");
          fTex.escriureLin("\\put(" + fTex.formatejarReal(pt) + ",-" + Constants.OFFSET_Y + "){" +
                           fTex.formatejarReal(min) + "}");
        }
        if ((max >= minEix) && (max <= maxEix)) {
          pt = ( (max - minEix) * factX) + Constants.OFFSET_EIX;
          fTex.escriureLin("\\put(" + fTex.formatejarReal(pt) +
                           ",-4){\\line(0,1){4}}");
          fTex.escriureLin("\\put(" + fTex.formatejarReal(pt) + ",-" + Constants.OFFSET_Y + "){" +
                           fTex.formatejarReal(max) + "}");
        }
      } else if (!(op.equals( "no"))){
        // num de marques indicat per l'usuari
        op = (String)opc.get("num");
        nMarques = Integer.parseInt(op);
        graduarYEtiquetarEixX(minEix, maxEix, nMarques);
      }
      if (!mult) {
        // Es posa una nota si queden observacions fora dels limits indicats per l'usuari
        if (fora == 2) {
          fTex.escriureLin("\\put(0," + (10 - Constants.OFFSET_Y) +
              "){+ Hi ha observacions que queden fora del gr\\`afic (a dreta i esquerra).}");
        }
        else if (fora == -1) {
          fTex.escriureLin("\\put(0," + (10 - Constants.OFFSET_Y) +
              "){+ Hi ha observacions que queden fora del gr\\`afic (a l'esquerra).}");
        }
        else if (fora == 1) {
          fTex.escriureLin("\\put(0," + (10 - Constants.OFFSET_Y) +
              "){+ Hi ha observacions que queden fora del gr\\`afic (a la dreta).}");
        }
      }
      escriureFiBxp(mult);
    }
    catch (IOException e) {
      logger.warning("ERROR: Error en la creació del boxplot. " + e.getMessage());
    }
  }

  private void escriureIniciBxp(String str, boolean multi) throws IOException {
    if (!multi){
      fTex.escriureLin("\\mbox{ } \\vfill");
      fTex.escriureLin("\\begin{center}");
      if (str == null) {
        fTex.escriureLin(
            "{\\hspace{60pt}\\bf Boxplot }\\vspace{0.5em}");
      }
      else {
        fTex.escriureLin("{\\hspace{60pt}\\bf Boxplot de la variable " +
                         str + " }\\vspace{0.5em}");
      }
      fTex.escriureLin("\\noindent");
      fTex.escriureLin("\\setlength{\\unitlength}{0.95 pt}");
      fTex.escriureLin("\\scriptsize");
    }
    fTex.escriureLin("\\begin{picture}(" + Constants.AMPLADA + ","
                     + Constants.ALZADA + ")(-" + Constants.OFFSET_X
                     + ",-" + Constants.OFFSET_Y + ")");
  }

  private void graduarYEtiquetarEixX(float minX, float maxX, int nMarques) throws IOException {
    float ct, lon, pt;
    int i;

    // graduació amb etiquetatge del eix X
    ct = (maxX - minX)/(nMarques - 1);
    // cal restar l'offset per obtenir la longitud real del eix dibuixat
    // i el offset del marge que deixem al principi i final del eix
    lon = Constants.AMPLADA - Constants.OFFSET_X - 2*Constants.OFFSET_EIX;
    for (i = 0; i < nMarques; i++){
      pt = (lon/(nMarques -1))*i;
      fTex.escriureLin("\\put(" + fTex.formatejarReal(pt + Constants.OFFSET_EIX) + ",-4){\\line(0,1){4}}");
      fTex.escriureLin("\\put(" + fTex.formatejarReal(pt + Constants.OFFSET_EIX) + ",-6){\\makebox(0,0)[t]{" + fTex.formatejarReal(minX + ct*i) + "}}");
    }
  }

  private void escriureFiBxp(boolean multi) throws IOException {
    fTex.escriureLin("\\end{picture}");
    if (!multi){
      fTex.escriureLin("\\end{center} \\vfill");
      fTex.escriureLin("");
    }
  }

}
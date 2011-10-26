package jklass.nucli;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.*;

/**
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.1
 */
 class LetterPlot extends RepresentacioGrafica {
   /**
   *
   * @param fitxer fitxer Latex que contindrá el plot
   * @param calculs
   * @param opc
   * @param nMarques
   * @param graduacio
   * @throws IOException
   * @throws ParamIncorrecteException
   */
   LetterPlot(FitxerTex fitxer, CalculsTrivNNC calculs, Hashtable opc, int nMarques, String graduacio) throws IOException,ParamIncorrecteException {
    super(fitxer);
    String varX, varY, varZ, op;
    int nMarquesX, nMarquesY;
    PropNumerica[] propsNum;
    PropCategorica propCat;
    Dada[] dadesVarX, dadesVarY;
    int[] dadesVarZ;
    float maxX, minX, maxY, minY;
    EstadisticsNum estadis;
    Hashtable taula;
    float factX, factY;
    boolean color;

    propsNum = calculs.obtenirPropsNum();
    propCat = calculs.obtenirPropsCat();
    varX = propsNum[0].obtenirId(); // primera var correspon al eix de les X
    varY = propsNum[1].obtenirId(); //segona var a la del eix de les Y
    varZ = propCat.obtenirId();
    try {
      if (propCat.llModsOrdre.size() <= Constants.MAX_MODALS){
        escriureIniciLetterPlot(varX, varY, varZ);
        dibuixarYEtiquetarEixos(varX, varY);
        color = ((String)opc.get("tipus")).equals("color");
        nMarquesX = Integer.parseInt((String)opc.get("nummarqX"));
        nMarquesY = Integer.parseInt( (String) opc.get("nummarqY"));
        // Opcions de l'eix X
        op = (String) opc.get("limitsX");
        estadis = propsNum[0].obtenirEstadistics();
        if (op.equals("observat")) {
          maxX = estadis.obtenirMax();
          minX = estadis.obtenirMin();
        }
        else if (op.equals("teoric")) {
          maxX = propsNum[0].obtenirRangMax();
          minX = propsNum[0].obtenirRangMin();
        }
        else { // limits definits per l'usuari
          maxX = Float.parseFloat( (String) opc.get("maxX"));
          minX = Float.parseFloat( (String) opc.get("minX"));
        }
        // Opcions de l'eix Y
        op = (String) opc.get("limitsY");
        estadis = propsNum[1].obtenirEstadistics();
        if (op.equals("observat")) {
          maxY = estadis.obtenirMax();
          minY = estadis.obtenirMin();
        }
        else if (op.equals("teoric")) {
          maxY = propsNum[1].obtenirRangMax();
          minY = propsNum[1].obtenirRangMin();
        }
        else { // limits definits per l'usuari
          maxY = Float.parseFloat( (String) opc.get("maxY"));
          minY = Float.parseFloat( (String) opc.get("minY"));
        }
        graduarYEtiquetarEixos(maxX, minX, maxY, minY, nMarquesX, nMarquesY, nMarques);
        // Es calculen els factors de conversió que caldrá aplicar a les dades per dibuixar els punts
        factX = (Constants.PLOT_AMP - Constants.PLOT_OFF_AMP -
                 2 * Constants.OFFSET_EIX) / (maxX - minX);
        factY = (Constants.PLOT_ALZ - Constants.PLOT_OFF_ALZ -
                 2 * Constants.OFFSET_EIX) / (maxY - minY);
        dadesVarX = calculs.obtenirDadesX();
        dadesVarY = calculs.obtenirDadesY();
        dadesVarZ = calculs.obtenirDadesZIndexades();
        dibuixarPunts(dadesVarX, dadesVarY, dadesVarZ, factX, factY, minX, minY, color);
        escriureFiLetterPlot(propCat.llModsOrdre, calculs.obtenirTaulaMods(), varZ, color);

      }
      else {
        fTex.escriureLin("\\mbox{ } \\vfill");
        fTex.escriureLin("\\begin{center}");
        fTex.escriureLin("{\\bf Letterplot per les variables " + varX
                         + " i " + varY + " vs. " + varZ + "}");
        fTex.escriureLin("\\mbox{ } \\vfill");
        fTex.escriureLin("- No es pot mostrar aquest gr\\`afic perque la variable categ\\`orica t\\'e m\\'es de "
                         + Constants.MAX_MODALS + " modalitats.");
        fTex.escriureLin("\\end{center}");
      }
    }
    catch (IOException e) {
      logger.warning("ERROR: Error en la creació del letterplot. " +
                     e.getMessage());
    }
  }

  private void escriureIniciLetterPlot(String varX, String varY, String varZ) throws IOException {
    fTex.escriureLin("\\mbox{ } \\vfill");
    fTex.escriureLin("\\begin{center}");
    fTex.escriureLin("{\\bf Letterplot per les variables " + varX
                     + " i " + varY + " vs. " + varZ + "}");
    fTex.escriureLin("\\end{center} \\vfill");
    fTex.escriureLin("\\vspace{3.5em}");
    fTex.escriureLin("\\begin{center}");
    fTex.escriureLin("\\noindent");
    fTex.escriureLin("\\setlength{\\unitlength}{0.95 pt}");
    fTex.escriureLin("\\scriptsize");
    fTex.escriureLin("\\begin{picture}(" + Constants.PLOT_AMP*2 + ","
                     + Constants.PLOT_ALZ + ")(-" + Constants.PLOT_OFF_AMP
                     + ",-" + Constants.PLOT_OFF_ALZ + ")");
    fTex.escriureLin("\\put(0,0){ ");
    fTex.escriureLin("\\begin{picture}(" + Constants.PLOT_AMP + ","
                     + Constants.PLOT_ALZ + ")(-" + Constants.PLOT_OFF_AMP
                     + ",-" + Constants.PLOT_OFF_ALZ + ")");
  }

  protected void dibuixarYEtiquetarEixos(String varX, String varY) throws IOException {
    int lonX, lonY;

    lonX = Constants.PLOT_AMP - Constants.PLOT_OFF_AMP;
    lonY = Constants.PLOT_ALZ - Constants.PLOT_OFF_ALZ;
    fTex.escriureLin("\\thicklines");
    fTex.escriureLin("\\put(0,0){\\line(1,0){" + lonX + "}}");
    fTex.escriureLin("\\put(0,0){\\line(0,1){" + lonY + "}}");
    fTex.escriureLin("\\put(" + (lonX + 8) + ",-5){\\large " + varX + "}");
    fTex.escriureLin("\\put(-20," + (lonY + 10) +"){\\large " + varY + "}");
  }

  private void graduarYEtiquetarEixos(float maxX, float minX, float maxY, float minY, int nMarquesX, int nMarquesY, int nMarques) throws IOException {
    float ct, lon, pt;
    int i;

    // graduació amb etiquetatge del eix X
    ct = (maxX - minX)/(nMarquesX - 1);
    // cal restar l'offset per obtenir la longitud real del eix dibuixat
    // i el offset del marge que deixem al principi i final del eix
    lon = Constants.PLOT_AMP - Constants.PLOT_OFF_AMP - 2*Constants.OFFSET_EIX;
    for (i = 0; i < nMarquesX; i++){
      pt = (lon/(nMarquesX -1))*i;
      fTex.escriureLin("\\put(" + (pt+ Constants.OFFSET_EIX) + ",-4){\\line(0,1){4}}");
      fTex.escriureLin("\\put(" + (pt+ Constants.OFFSET_EIX) + ",-6){\\makebox(0,0)[t]{" + (minX + ct*i) + "}}");
    }
    // graduació amb etiquetatge del eix Y
    ct = (maxY - minY) / (nMarquesY - 1);
    lon = Constants.PLOT_ALZ - Constants.PLOT_OFF_ALZ - 2*Constants.OFFSET_EIX;
    for (i = 0; i < nMarquesY; i++) {
      pt = (lon/ (nMarquesY - 1)) * i;
      fTex.escriureLin("\\put(-4," + (pt+ Constants.OFFSET_EIX) + "){\\line(1,0){4}}");
      fTex.escriureLin("\\put(-6," + (pt+ Constants.OFFSET_EIX) + "){\\makebox(0,0)[r]{" + (minY + ct*i) + "}}");
    }
  }

  private void dibuixarPunts(Dada[] dadesVarX, Dada[] dadesVarY, int[] dadesVarZ, float factX, float factY, float minX, float minY, boolean color) throws IOException{
    LinkedList punts = new LinkedList(); //llista de punts dibuixats (de menor a major)
    ListIterator iterador;
    String valX, valY;
    int i, pes;
    float x, y, px, py;
    PuntColor p = null, pActual = null;
    boolean afegit;

    valX = (String) dadesVarX[0].obtenirValor();
    valY = (String) dadesVarY[0].obtenirValor();
    if ((valX.compareTo("?") != 0) && (valY.compareTo("?") != 0)){
      // Es recuperen les dades i es transformen per poder dibuixar els punts
      x = Float.parseFloat(valX);
      y = Float.parseFloat(valY);
      p = new PuntColor(x, y, 1, dadesVarZ[0]);
      punts.add(p);
      if (color) {
        fTex.escriureLin("\\put(" +
                         ( ( (x - minX) * factX) + Constants.OFFSET_EIX) + "," +
                         ( ( (y - minY) * factY) + Constants.OFFSET_EIX) +
                         "){\\textcolor{" +
                         Constants.colors[p.obtenirColor()] + "}{\\circle*{" +
                         (Constants.DIAM_PT) + "}}}");
      } else {
        fTex.escriureLin("\\put(" +
                         ( ( (x - minX) * factX) + Constants.OFFSET_EIX) + "," +
                         ( ( (y - minY) * factY) + Constants.OFFSET_EIX) +
                         "){\\makebox(0,0){$" +
                         Constants.simbols[p.obtenirColor()] + "$}}");
      }
      logger.finest("Punt afegit: " + p.toString() +
                    " Long. llista dibuixats: " + punts.size());
    }
    iterador = punts.listIterator();
    for(i = 1; i < dadesVarX.length; i++){
      valX = (String) dadesVarX[i].obtenirValor();
      valY = (String) dadesVarY[i].obtenirValor();
      if ( (valX.compareTo("?") != 0) && (valY.compareTo("?") != 0)) {
        x = Float.parseFloat(valX);
        y = Float.parseFloat(valY);
        logger.finest("Dades llegides: " + x + "," + y + "," + dadesVarZ[i]);
        // S'afegeix el punt a la llista de dibuixats i es dibuixa
        iterador = punts.listIterator();
        afegit = false;
        while (iterador.hasNext() && !afegit) {
          pActual = (PuntColor) iterador.next();
          px = pActual.obtenirX();
          py = pActual.obtenirY();
          if (x < px) {
            iterador.previous();
            p = new PuntColor(x, y, 1, dadesVarZ[i]);
            iterador.add(p);
            if (color) {
              fTex.escriureLin("\\put(" +
                               ( ( (x - minX) * factX) + Constants.OFFSET_EIX) +
                               "," +
                               ( ( (y - minY) * factY) + Constants.OFFSET_EIX) +
                               "){\\textcolor{" +
                               Constants.colors[p.obtenirColor()] +
                               "}{\\circle*{" + (Constants.DIAM_PT) + "}}}");
            }
            else {
              fTex.escriureLin("\\put(" +
                               ( ( (x - minX) * factX) + Constants.OFFSET_EIX) +
                               "," +
                               ( ( (y - minY) * factY) + Constants.OFFSET_EIX) +
                               "){\\makebox(0,0){$" +
                               Constants.simbols[p.obtenirColor()] + "$}}");
            }

            logger.finest("Punt afegit: " + p.toString() +
                          " Long. llista dibuixats: " + punts.size());
            afegit = true;
          }
          else if (x == px) {
            if (y < py) {
              iterador.previous();
              p = new PuntColor(x, y, 1, dadesVarZ[i]);
              iterador.add(p);
              if (color) {
                fTex.escriureLin("\\put(" +
                                 ( ( (x - minX) * factX) + Constants.OFFSET_EIX) +
                                 "," +
                                 ( ( (y - minY) * factY) + Constants.OFFSET_EIX) +
                                 "){\\textcolor{" +
                                 Constants.colors[p.obtenirColor()] +
                                 "}{\\circle*{" + (Constants.DIAM_PT) + "}}}");
              }
              else {
                fTex.escriureLin("\\put(" +
                                 ( ( (x - minX) * factX) + Constants.OFFSET_EIX) +
                                 "," +
                                 ( ( (y - minY) * factY) + Constants.OFFSET_EIX) +
                                 "){\\makebox(0,0){$" +
                                 Constants.simbols[p.obtenirColor()] + "$}}");
              }

              logger.finest("Punt afegit: " + p.toString() +
                            " Long. llista dibuixats: " + punts.size());
              afegit = true;
            }
            else if (y == py) {
              if (p.obtenirColor() == dadesVarZ[i]) {
                pes = pActual.obtenirPes() + 1;
              }
              else {
                pes = 1;
              }
              p = new PuntColor(x, y, pes, dadesVarZ[i]);
              iterador.set(p);
              if (color) {
                fTex.escriureLin("\\put(" +
                                 ( ( (x - minX) * factX) + Constants.OFFSET_EIX) +
                                 "," +
                                 ( ( (y - minY) * factY) + Constants.OFFSET_EIX) +
                                 "){\\textcolor{" +
                                 Constants.colors[p.obtenirColor()] +
                                 "}{\\circle*{" +
                                 (Constants.DIAM_PT + pes * Constants.PAS) +
                                 "}}}");
              }
              else {
                fTex.escriureLin("\\put(" +
                                 ( ( (x - minX) * factX) + Constants.OFFSET_EIX) +
                                 "," +
                                 ( ( (y - minY) * factY) + Constants.OFFSET_EIX) +
                                 "){\\makebox(0,0){$" +
                                 Constants.simbols[p.obtenirColor()] + "$}}");
              }

              logger.finest("Punt afegit: " + p.toString() +
                            " Long. llista dibuixats: " + punts.size());
              afegit = true;
            }
          }
        }
        if (!afegit) {
          p = new PuntColor(x, y, 1, dadesVarZ[i]);
          iterador.add(p);
          if (color) {
            fTex.escriureLin("\\put(" +
                             ( ( (x - minX) * factX) + Constants.OFFSET_EIX) +
                             "," +
                             ( ( (y - minY) * factY) + Constants.OFFSET_EIX) +
                             "){\\textcolor{" + Constants.colors[p.obtenirColor()] +
                             "}{\\circle*{" + (Constants.DIAM_PT) + "}}}");
          }
          else {
            fTex.escriureLin("\\put(" +
                             ( ( (x - minX) * factX) + Constants.OFFSET_EIX) +
                             "," +
                             ( ( (y - minY) * factY) + Constants.OFFSET_EIX) +
                             "){\\makebox(0,0){$" +
                             Constants.simbols[p.obtenirColor()] + "$}}");
          }

          logger.finest("Punt afegit: " + p.toString() +
                        " Long. llista dibuixats: " + punts.size());
        }
      }
    }
  }

  private void escriureFiLetterPlot(ArrayList llOrdre, Hashtable taulaMods, String varCateg, boolean color) throws IOException {
    fTex.escriureLin("\\end{picture}");
    if ((taulaMods != null) && (varCateg != null)){
      afegirLlegenda(llOrdre, taulaMods, varCateg, color);
    }
    fTex.escriureLin("}");
    fTex.escriureLin("\\end{picture}");
    fTex.escriureLin("");
    fTex.escriureLin("\\end{center} \\vfill");
    fTex.escriureLin("");
  }

  private void afegirLlegenda(ArrayList llOrdre, Hashtable taulaMods, String varCateg, boolean color) throws IOException {
    String[] modals = new String[llOrdre.size()];
    String mod;
    int i;

    modals = (String[])llOrdre.toArray(modals);
    fTex.escriureLin("}");
    fTex.escriureLin("");
    fTex.escriureLin("\\put(" + (Constants.PLOT_AMP + Constants.PLOT_OFF_AMP) +
                     "," + (Constants.PLOT_ALZ * 2) / 3 + "){");
    fTex.escriureLin("\\begin{tabular}{|c l|}");
    fTex.escriureLin("\\hline");
    fTex.escriureLin("\\multicolumn{2}{|c|}{\\bf Llegenda per " + varCateg + "} \\\\");
    fTex.escriureLin("\\hline");
    i=0;
    while ( i < modals.length){
      mod = modals[i];
      if (color){
        fTex.escriureLin("\\raisebox{0.8ex}{\\colorbox{" +
                         fTex.adaptarATex(Constants.colors[ ( (Integer)
            taulaMods.get(mod)).intValue()])
                         + "}{}} & " + fTex.adaptarATex(mod) + " \\\\");
      } else {
        fTex.escriureLin("$" +
            Constants.simbols[( (Integer)taulaMods.get(mod)).intValue()]
            + "$ & " + fTex.adaptarATex(mod) + " \\\\");

      }
      i++;
    }
    fTex.escriureLin("\\hline");
    fTex.escriureLin("\\end{tabular}");
  }

  private class PuntColor{
    private float x, y;
    private int pes;
    private int color;

    public PuntColor(float px, float py, int n, int color){
      x = px;
      y = py;
      pes = n;
      this.color = color;
    }

    public float obtenirX(){
      return x;
    }

    public float obtenirY(){
      return y;
    }

    public int obtenirPes(){
      return pes;
    }

    public int obtenirColor(){
      return color;
    }

    public void modificarX(float px){
      x = px;
    }

    public void modificarY(float py) {
      y = py;
    }

    public void modificarPes(int p) {
      pes = p;
    }

    public void modificarColor(int c) {
      color = c;
    }

    public String toString() {
      return "(" + x + "," + y + "," + pes + "," + color + ")";
    }

  }

}

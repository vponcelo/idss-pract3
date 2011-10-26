package jklass.nucli;

import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.1
 */
class Plot extends RepresentacioGrafica {

  /**
   * @param fitxer fitxer Latex que contindrá el plot
   * @param props llista que conté les dues propietats de les que es realitza el plot (la primera correspon al eix de les X i la segona a la del eix de les Y
   * @param dadesVarX llista que conté totes les dades de corresponents a la primera propietat (la que es posarà al eix de les X)
   * @param dadesVarY llista que conté totes les dades de corresponents a la segona propietat (la que es posarà al eix de les Y)
   */
  Plot(FitxerTex fitxer, PropNumerica[] props, Dada[] dadesVarX, Dada[] dadesVarY, Hashtable opc, int nMarques, String graduacio) throws IOException,ParamIncorrecteException {
    super(fitxer);

    String varX, varY, op;
    int nMarquesX, nMarquesY;
    float maxX, minX, maxY, minY;
    EstadisticsNum estadis;
    float factX, factY;

    varX = props[0].obtenirId();
    varY = props[1].obtenirId();
    try {
      escriureIniciPlot(varX, varY);
      dibuixarYEtiquetarEixos(varX,varY);
      nMarquesX = Integer.parseInt((String)opc.get("nummarqX"));
      nMarquesY = Integer.parseInt( (String) opc.get("nummarqY"));
      // Opcions de l'eix X
      op = (String) opc.get("limitsX");
      estadis = props[0].obtenirEstadistics();
      if (op.equals("observat")) {
        maxX = estadis.obtenirMax();
        minX = estadis.obtenirMin();
      }
      else if (op.equals("teoric")) {
        maxX = props[0].obtenirRangMax();
        minX = props[0].obtenirRangMin();
      }
      else { // limits definits per l'usuari
        maxX = Float.parseFloat( (String) opc.get("maxX"));
        minX = Float.parseFloat( (String) opc.get("minX"));
      }
      // Opcions de l'eix Y
      op = (String) opc.get("limitsY");
      estadis = props[1].obtenirEstadistics();
      if (op.equals("observat")) {
        maxY = estadis.obtenirMax();
        minY = estadis.obtenirMin();
      }
      else if (op.equals("teoric")) {
        maxY = props[1].obtenirRangMax();
        minY = props[1].obtenirRangMin();
      }
      else { // limits definits per l'usuari
        maxY = Float.parseFloat( (String) opc.get("maxY"));
        minY = Float.parseFloat( (String) opc.get("minY"));
      }
      graduarYEtiquetarEixos(maxX, minX, maxY, minY, nMarquesX, nMarquesY, nMarques);
      // Es calculen els factors de conversió que caldrá aplicar a les dades per dibuixar els punts
      factX = (Constants.PLOT_AMP - Constants.PLOT_OFF_AMP - 2*Constants.OFFSET_EIX)/(maxX - minX);
      factY = (Constants.PLOT_ALZ - Constants.PLOT_OFF_ALZ- 2*Constants.OFFSET_EIX)/(maxY - minY);
      dibuixarPunts(dadesVarX, dadesVarY, factX, factY, minX, minY);
      escriureFiPlot();
    } catch (IOException e){
        logger.warning("ERROR: Error en la creació del plot. " + e.getMessage() );
    }
  }

  private void escriureIniciPlot(String varX, String varY) throws IOException {
    fTex.escriureLin("\\mbox{ } \\vfill");
    fTex.escriureLin("\\begin{center}");
    fTex.escriureLin("{\\bf Diagrama bivariant per les variables " + varX
                     + " i " + varY + "}");
    fTex.escriureLin("\\end{center} \\vfill");
    fTex.escriureLin("\\begin{center} \\noindent");
    fTex.escriureLin("\\setlength{\\unitlength}{0.95 pt}");
    fTex.escriureLin("\\scriptsize");
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

  private void dibuixarPunts(Dada[] dadesVarX, Dada[] dadesVarY, float factX, float factY, float minX, float minY) throws IOException{
    LinkedList punts = new LinkedList(); //llista de punts dibuixats (de menor a major)
    ListIterator iterador;
    int i = 0, pes;
    float x, y, px, py;
    Punt p, pActual;
    boolean afegit;
    String valX, valY;

    valX = (String) dadesVarX[0].obtenirValor();
    valY = (String) dadesVarY[0].obtenirValor();
    if ((valX.compareTo("?") != 0) && (valY.compareTo("?") != 0)){
      // Es recuperen les dades i es transformen per poder dibuixar els punts
      x = Float.parseFloat(valX);
      y = Float.parseFloat(valY);
      p = new Punt(x, y, 1);
      punts.add(p);
      fTex.escriureLin("\\put(" +
                       ( ( (x - minX) * factX) + Constants.OFFSET_EIX) + "," +
                       ( ( (y - minY) * factY) + Constants.OFFSET_EIX) +
                       "){\\circle*{" + (Constants.DIAM_PT) + "}}");
      logger.finest("Punt afegit: " + p.toString() +
                    " Long. llista dibuixats: " + punts.size());
      iterador = punts.listIterator();
    }
    for(i = 1; i < dadesVarX.length; i++){
      valX = (String) dadesVarX[i].obtenirValor();
      valY = (String) dadesVarY[i].obtenirValor();
      if ((valX.compareTo("?") != 0) && (valY.compareTo("?") != 0)){
        x = Float.parseFloat(valX);
        y = Float.parseFloat(valY);
        logger.finest("Dades llegides: " + x + "," + y);
        // S'afegeix el punt a la llista de dibuixats i es dibuixa
        iterador = punts.listIterator();
        afegit = false;
        while (iterador.hasNext() && !afegit) {
          pActual = (Punt) iterador.next();
          px = pActual.obtenirX();
          py = pActual.obtenirY();
          if (x < px) {
            iterador.previous();
            p = new Punt(x, y, 1);
            iterador.add(p);
            fTex.escriureLin("\\put(" +
                             ( ( (x - minX) * factX) + Constants.OFFSET_EIX) +
                             "," +
                             ( ( (y - minY) * factY) + Constants.OFFSET_EIX) +
                             "){\\circle*{" + (Constants.DIAM_PT) + "}}");
            logger.finest("Punt afegit: " + p.toString() +
                          " Long. llista dibuixats: " + punts.size());
            afegit = true;
          }
          else if (x == px) {
            if (y < py) {
              iterador.previous();
              p = new Punt(x, y, 1);
              iterador.add(p);
              fTex.escriureLin("\\put(" +
                               ( ( (x - minX) * factX) + Constants.OFFSET_EIX) +
                               "," +
                               ( ( (y - minY) * factY) + Constants.OFFSET_EIX) +
                               "){\\circle*{" + (Constants.DIAM_PT) + "}}");
              logger.finest("Punt afegit: " + p.toString() +
                            " Long. llista dibuixats: " + punts.size());
              afegit = true;
            }
            else if (y == py) {
              pes = pActual.obtenirPes() + 1;
              p = new Punt(x, y, pes);
              iterador.set(p);
              fTex.escriureLin("\\put(" +
                               ( ( (x - minX) * factX) + Constants.OFFSET_EIX) +
                               "," +
                               ( ( (y - minY) * factY) + Constants.OFFSET_EIX) +
                               "){\\circle*{" +
                               (Constants.DIAM_PT + pes * Constants.PAS) + "}}");
              logger.finest("Punt afegit: " + p.toString() +
                            " Long. llista dibuixats: " + punts.size());
              afegit = true;
            }
          }
        }
        if (!afegit) {
          p = new Punt(x, y, 1);
          iterador.add(p);
          fTex.escriureLin("\\put(" +
                           ( ( (x - minX) * factX) + Constants.OFFSET_EIX) +
                           "," +
                           ( ( (y - minY) * factY) + Constants.OFFSET_EIX) +
                           "){\\circle*{" +
                           (Constants.DIAM_PT) + "}}");
          logger.finest("Punt afegit: " + p.toString() +
                        " Long. llista dibuixats: " + punts.size());
        }
      }
    } // fi for
  }

  private void escriureFiPlot() throws IOException {
    fTex.escriureLin("\\end{picture}");
    fTex.escriureLin("\\end{center} \\vfill");
    fTex.escriureLin("");
  }

  private class Punt{
    float x, y;
    int pes;

    public Punt(float px, float py, int n){
      x = px;
      y = py;
      pes = n;
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

    public void modificarX(float px){
      x = px;
    }

    public void modificarY(float py) {
      y = py;
    }

    public void modificarPes(int p) {
      pes = p;
    }

    public String toString(){
      return "("+ x +"," + y + "," + pes + ")";
    }

  }
}

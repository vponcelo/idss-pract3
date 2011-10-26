package jklass.nucli;

import java.util.logging.*;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

public class CalculsBivNN extends CalculsBiv {
  static Logger logger = Logger.getLogger(CalculsBivNN.class.getName());

  private PropNumerica[] props = null;
  private Dada[] dadesVarX, dadesVarY;
  private double correl;
  private double coVar;
  // matriu que conté la info sobre dades mancants:
  // files -> X (jklass.util, mancants), columnes -> Y(jklass.util, mancants)
  private int[][] mancants = {{0,0},{0,0}};

  public CalculsBivNN() {
    super();
    props = new PropNumerica[2];
  }

  public void afegirProps(PropNumerica[] propsN){
    props[0] = propsN[0];
    props[1] = propsN[1];
  }

  public PropNumerica[] obtenirProps(){
    return (PropNumerica[])props.clone();
  }

  public void afegirDadesX(Dada[] dades) {
    dadesVarX = (Dada[]) dades.clone();
  }

  public Dada[] obtenirDadesX() {
    return (Dada[]) dadesVarX.clone();
  }

  public void afegirDadesY(Dada[] dades) {
    dadesVarY = (Dada[]) dades.clone();
  }

  public Dada[] obtenirDadesY() {
    return (Dada[]) dadesVarY.clone();
  }

  public void calcularCorrelacio() {
    EstadisticsNum estadX, estadY;
    int i, n;
    String valorX, valorY;
    boolean missX, missY;
    float mitjX, mitjY;

    estadX = props[0].obtenirEstadistics();
    estadY = props[1].obtenirEstadistics();
    n = estadX.obtenirNumObjs();
    mitjX = estadX.obtenirMitjana();
    mitjY = estadY.obtenirMitjana();
    logger.finest("Mitjana X: " + mitjX + "-  Mitjana Y: " + mitjY);
    coVar = 0;
    for (i =0; i<n; i++){
      valorX = (String) dadesVarX[i].obtenirValor();
      valorY = (String) dadesVarY[i].obtenirValor();
      missX = (valorX.compareTo("?") == 0);
      missY = (valorY.compareTo("?") == 0);
      logger.finest(i+" MissX: " + missX + "; MissY: " + missY);
      if (!missX && !missY){
        mancants[0][0] = mancants[0][0] + 1;
        coVar = coVar + (Float.parseFloat(valorX) - mitjX) * (Float.parseFloat(valorY) -mitjY);
      } else if (!missX && missY){
        mancants[0][1] = mancants[0][1] + 1;
      } else if (missX && !missY){
        mancants[1][0] = mancants[1][0] + 1;
      } else if (missX && missY){
        mancants[1][1] = mancants[1][1] + 1;
      }
    }
    logger.finest("Mancants: " + mancants[0][0]);
    logger.finest("Numerador coVar: " + coVar);
    /* Es calcula abans la coVar perque:
     * coVar = correl*Sx*Sy                           */
    coVar = coVar /(mancants[0][0]-1);
    logger.finest("coVar: " + coVar);
    logger.finest("Quasi-desv. tip.  X: " + estadX.obtenirQuasiDesvTip() +  " -- Quasi-desv. tip.  Y: " + estadY.obtenirQuasiDesvTip());
    correl = coVar/ (estadX.obtenirQuasiDesvTip() * estadY.obtenirQuasiDesvTip());
    logger.finest("Correlació: " + correl);
  }

  public double obtenirCorrel() {
    return correl;
  }

  public double obtenirCoVar() {
    return coVar;
  }

  public int[][] obtenirInfoMancants() {
    return mancants;
  }

}
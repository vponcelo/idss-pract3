package jklass.nucli;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

import java.util.Vector;

public abstract class Calculs {
  protected int numCalcs;
  protected Vector calcReal;

  public Calculs() {
    numCalcs = 0;
    calcReal = new Vector();
  }

  public void afegirCalcRealitzat(int calc){
    calcReal.add(numCalcs, new Integer(calc));
    numCalcs++;
  }

  public boolean calculRealitzat(int c) {
    boolean b = false;
    b = calcReal.contains(new Integer(c));
    return b;
  }

}
package jklass.util;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author M� del Mar Colillas
 * @version v.0
 */
public class Rang {
  float inf, sup;
  public Rang(float fMin, float fMax) {
    inf = fMin;
    sup = fMax;
  }

  public float obtenirMin() {
    return inf;
  }

  public float obtenirMax() {
    return sup;
  }

}
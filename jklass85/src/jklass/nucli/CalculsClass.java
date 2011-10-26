package jklass.nucli;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

public class CalculsClass extends Calculs {
  private CalculsBiv[][] calculs;
  // llista[0][] - (Conté la llista de vars numeriques X de NN) Sempre null
  // llista[1][] - (Conté la llista de vars numeriques Y de NN) Sempre null
  // llista[2][] - Conté la llista de vars numeriques X de NC
  // llista[3][] - Conté la llista de vars categoriques Y de NC
  // llista[4][] - Conté la llista de vars categoriques X de CC
  // llista[5][] - Conté la llista de vars categoriques Y de CC
  private String[][] vars;
  private Particio par;

  public CalculsClass() {
    super();
    calculs = new CalculsBiv[3][];
    par = null;
  }

  public void afegirCalculBiv(CalculsBiv[][] calc){
    calculs = calc;
  }

  public void afegirLlistaVars(String[][] llista){
    vars = llista;
  }

  public void afegirParticio(Particio particio){
    par = particio;
  }

  public CalculsBiv[][] obtenirCalculBiv(){
    return calculs;
  }

  public String[][] obtenirLlistaVars() {
    return vars;
  }

  public Particio obtenirParticio(){
    return par;
  }

}
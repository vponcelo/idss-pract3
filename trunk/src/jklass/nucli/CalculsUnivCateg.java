package jklass.nucli;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

public class CalculsUnivCateg extends CalculsUniv {
  private String tipusCateg;
  private String[] llModal;
  // Per Taula de Freqüencies (llFreq i llRel també per Diagrama de Barres)
  private float[] llFreq, llAcum, llRel, llRelAcum;
  private int maxFreq;

  public CalculsUnivCateg() {
    super();
    llFreq = null;
    llAcum = null;
    llRel = null;
    llRelAcum = null;
  }

  public void inicialitzarLlistaModals(int lon){
    llModal = new String[lon];
  }

  public void inicialitzarLlistaFreq(int lon){
    llFreq = new float[lon];
  }

  public void inicialitzarLlistaAcum(int lon){
    llAcum = new float[lon];
  }

  public void inicialitzarLlistaRel(int lon){
    llRel = new float[lon];
  }

  public void inicialitzarLlistaRelAcum(int lon){
    llRelAcum = new float[lon];
  }

  public void afegirTipus(String str){
    tipusCateg = str;
  }

  public void afegirMaxFreq(int n){
    maxFreq = n;
  }

  public void afegirALlistaModals(int pos, String modal){
    llModal[pos] = modal;
  }

  public void afegirALlistaFreq(int pos, float n){
    llFreq[pos] = n;
  }

  public void afegirALlistaAcum(int pos, float n){
    llAcum[pos] = n;
  }

  public void afegirALlistaRel(int pos, float n){
    llRel[pos] = n;
  }

  public void afegirALlistaRelAcum(int pos, float n){
    llRelAcum[pos] = n;
  }

  public String obtenirTipusCateg(){
    return tipusCateg;
  }

  public String obtenirModalitat(int pos){
    return llModal[pos];
  }

  public int obtenirLonLlistaMods(){
    return llModal.length;
  }

  public int obtenirMaxFreq(){
    return maxFreq;
  }

  public float obtenirFreq(int pos){
    return llFreq[pos];
  }

  public float obtenirFreqAcum(int pos){
    return llAcum[pos];
  }

  public float obtenirFreqRel(int pos){
    return llRel[pos];
  }

  public float obtenirFreqRelAcum(int pos){
    return llRelAcum[pos];
  }

}
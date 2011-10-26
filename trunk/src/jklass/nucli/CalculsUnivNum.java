package jklass.nucli;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */
import java.text.NumberFormat;
import java.util.Locale;

import jklass.util.Rang;

public class CalculsUnivNum extends CalculsUniv {
  /**
   * limits mínim i màxim teorics
   */
  float minT, maxT;
  // Estadistics sumaris
  float min, max, amplitud, m, var, qVar, desv, qDesv, coef, me, q1,
      q3, dist;

  // Histograma
  Rang[] llRang; //llista dels rangs és equivalent a la llista de Modalitats
  float[] llFreq;
  float minFreq = 0, maxFreq = 0; // freqüències mín. i màx de la llista de freq

  //Boxplot
  float[] dades;

  public CalculsUnivNum() {
    super();
  }

  public void inicialitzarLlistaRang(int lon){
    llRang = new Rang[lon];
  }

  public void inicialitzarLlistaFreq(int lon){
    llFreq = new float[lon];
  }

  public void afegirMinTeoric(float n){
    minT = n;
  }

  public void afegirMaxTeoric(float n){
    maxT = n ;
  }


  public void afegirMin(float n){
    min = n;
  }

  public void afegirMax(float n){
    max = n ;
  }

  public void afegirAmplitud(float n){
    amplitud = n;
  }

  public void afegirMitjana(float n){
    m = n;
  }

  public void afegirVar(float n){
    var = n;
  }

  public void afegirQuasiVar(float n){
    qVar= n;
  }

  public void afegirDesv(float n){
    desv = n;
  }

  public void afegirQuasiDesv(float n){
    qDesv = n;
  }

  public void afegirCoefCorr(float n){
    coef = n;
  }

  public void afegirMediana(float n){
    me = n;
  }

  public void afegirQ1(float n){
    q1 = n;
  }

  public void afegirQ3(float n){
    q3 = n;
  }

  public void afegirDistInterQ(float n) {
    dist = n;
  }

  public void afegirALlistaRang(int pos, float inf, float sup) {
    llRang[pos] = new Rang(inf,sup);
  }

  public void afegirALlistaFreq(int pos, float n) {
    llFreq[pos] = n;
    if ( n > maxFreq ) maxFreq = n;
    if ( n < minFreq ) minFreq = n;
  }

  public void afegirDades(float[] llista){
   dades = (float[])llista.clone();
  }

  public float obtenirMinTeoric(){
    return minT;
  }

  public float obtenirMaxTeoric(){
    return maxT;
  }

  public float obtenirMin(){
    return min;
  }

  public float obtenirMax(){
    return max;
  }

  public float obtenirAmplitud(){
    return amplitud;
  }

  public float obtenirMitjana(){
    return m;
  }

  public float obtenirVar(){
    return var;
  }

  public float obtenirQuasiVar(){
    return qVar;
  }

  public float obtenirDesv(){
    return desv;
  }

  public float obtenirQuasiDesv(){
    return qDesv;
  }

  public float obtenirCoefCorr(){
    return coef;
  }

  public float obtenirMediana(){
    return me;
  }

  public float obtenirQ1(){
    return q1;
  }

  public float obtenirQ3(){
    return q3;
  }

  public float obtenirDistInterQ(){
    return dist;
  }

  public float obtenirMinDelRang(int pos){
    return llRang[pos].obtenirMin();
  }

  public float obtenirMaxDelRang(int pos) {
    return llRang[pos].obtenirMax();
  }

  public Rang[] obtenirLlRangs() {
    return llRang;
  }

  public float obtenirMinFreq(){
    return minFreq;
  }

  public float obtenirMaxFreq(){
    return maxFreq;
  }

  public float obtenirFreq(int pos){
    return llFreq[pos];
  }

  public float[] obtenirLlFreq() {
    return llFreq;
  }

  public float[] obtenirDades(){
    return dades;
  }
}
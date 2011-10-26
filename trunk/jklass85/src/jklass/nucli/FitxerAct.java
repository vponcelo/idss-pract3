package jklass.nucli;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Classe que permet gestionar fitxers .dat en format KLASS, fitxers que contenen la
 * matrius de dades per files. Cada fila està delimitada per un parell de
 * parèntesi al principi i al final i les dades estan separades per tabuladors
 * o espais en blanc, per exemple així:
 * <br> <code>
 * (( COLOR-TV  48 10 6502  52))  <br>
 * (( COLOR-TV 48  10  6502 57-63)) <br>
 * (( COLOR-TV 32 11 6502A  64-73)) <br>
 *            ... <br></code>
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */
class FitxerAct extends Fitxer {

  /**
   * Contructor
   * @param nom nom del fitxer sense extensió
   */
  public FitxerAct(String nom) {//el public s'ha de canviar...
	    super(nom);
	    String snom=obtenirNom();
		 
		 int r = snom.lastIndexOf('.');
		 r = (r==-1)? snom.length(): r;
		 snom=snom.substring(0,r);
	    this.modificarNom(snom+".act");	 
		// System.out.println("Estoy en el constructoractttttttt con"+snom); 
	  }//endofmethod

  /**
   * Llegeix una fila del fitxer de dades i retorna un 'StringTokenizer' amb les
   *  dades per cadascuna de les propietats de la matriu. StringTokenizer no és res
   * més que una linia de dades partida en 'Strings' (tokens) de forma que s'obtindrà:<br>
   * <code> valor_prop1 valor_prop2 ... valor_propN </code>
   * @return StringTokenizer que conté les dades d'una fila (cada fila es correspon a un objecte)
   */
  //  ROBER  modifico la funcion para que luego no elimine parentesis al leer StringTokenizer
  StringTokenizer llegirFilaDades(){
    StringTokenizer st = null;
    String lin = "";

    do {
      lin = llegirLinia();
      logger.finer("Linia llegida: " + lin);
      if (lin != null) {
        //st = new StringTokenizer(lin, '\t' + " ()");
        st = new StringTokenizer(lin, '\t' + " ");
      }
    }
    while ( (lin != null) && (st.countTokens() == 0));
    if (lin == null)
      st = null;

    return st;
  }


  /**
   * Escriu les dades al fitxer seguint el format de KLASS
   * @param dades
   * @throws IOException
   */
  void escriureDades(String[] dades) throws IOException {
    String lin="(";
    lin=lin+'\n';
    for (int i =0; i < dades.length; i++){
      lin = lin  +dades[i]+'\n' ;
    }
    lin = lin +")";
	 String subStr=lin.substring(2, (lin.length())-2);

    escriureLin(subStr);
  }


}
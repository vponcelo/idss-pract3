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
class FitxerDat extends Fitxer {

  /**
   * Contructor
   * @param nom nom del fitxer sense extensió
   */
  FitxerDat(String nom) {
    super(nom);
    modificarNom(obtenirNom() + ".dat");
  }

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
    String lin = "((";

    for (int i =0; i < dades.length; i++){
      lin = lin + '\t' +dades[i];
    }
    lin = lin + "))";
    escriureLin(lin);
  }

}

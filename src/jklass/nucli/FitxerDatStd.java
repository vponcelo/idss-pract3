package jklass.nucli;

import java.util.StringTokenizer;

/**
 * Classe que permet gestionar fitxers .dat en format est�ndard, fitxers que contenen la
 * matrius de dades per files. Cada fila cont� les dades d'un objecte
 * separades per tabuladors o espais en blanc, d'aquesta manera:
 * <br> <code>
 *  COLOR-TV  48 10 6502  52  <br>
 * COLOR-TV 48  10  6502 57-63 <br>
 *  COLOR-TV 32 11 6502A  64-73 <br>
 *            ... <br></code>
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estad�stic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author M� del Mar Colillas,Laia Riera
 * @version v.4
 */
class FitxerDatStd extends Fitxer {
  /**
   * Contructor
   * @param nom nom del fitxer de dades sense extensi�
   */
  FitxerDatStd(String nom) {
    super(nom);
    modificarNom(obtenirNom()+".dat");
  }

  /**
   * (PER IMPLEMENTAR) Escriu al fitxer una linia que con t� tots les dades d'una fila de la matriu,
   * d'un objectes. La linia que s'escriu estar� formada per els valors indicats
   *  al par�metre d'entrada posant entre ells un espai en blanc.
   * @param valors a cada posici� del vector hi ha la dada corresponent a la propietat que ocupa aquesta posici�
   */
  void escriureFilaDades(String[] valors) {

  }
  
  /**
   * Llegeix una fila del fitxer de dades i retorna un 'StringTokenizer' amb les
   *  dades per cadascuna de les propietats de la matriu. StringTokenizer no �s res
   * m�s que una linia de dades partida en 'Strings' (tokens) de forma que s'obtindr�:<br>
   * <code> valor_prop1 valor_prop2 ... valor_propN </code>
   * @return StringTokenizer que cont� les dades d'una fila (cada fila es correspon a un objecte)
   * @author Laia Riera Guerra
   */
  StringTokenizer llegirFilaDades()throws Exception{
    StringTokenizer st = null;
    String lin = "";

    do {
      lin = llegirLinia();
      logger.finer("Linia llegida: " + lin);
      if (lin != null) {
    	  char[] c=lin.trim().toCharArray();
    	  if(c[0]=='(')throw new Exception("Aquest fitxer �s de format klass.Si desitja obrir-lo, faci-ho a trav�s del men� Obre");
    	  else st = new StringTokenizer(lin);
      }
    }
    while ( (lin != null) && (st.countTokens() == 0));
    if (lin == null)
      st = null;

    return st;
  }


}

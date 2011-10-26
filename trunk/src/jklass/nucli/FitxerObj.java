package jklass.nucli;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Classe que permet gestionar fitxers .obj en format KLASS, fitxers que contenen la
 * la caracterització dels objectes de la matriu de dades, la descripció de les files de la matriu. Cada fila conté
 * la informació d'un objecte, per exemple:
 *  <br> <code>
 * ((12 TRS-80-I)  nil) <br>
 * ((13  TRS-80-III)  nil) <br>
 *            ... <br> </code>
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */
class FitxerObj extends Fitxer {
  /**
   * Constructor
   * @param nom nom del fitxer sense extensió
   */
  FitxerObj(String nom) {
    super(nom);
    modificarNom(obtenirNom()+".obj");
  }

  /**
   * Llegeix una fila del fitxer d'objectes (un objecte) i retorna un 'StringTokenizer' que
   * conté la informació del objecte, StringTokenizer no és res
   * més que una linia que descriu l'objecte partida en 'Strings' (tokens), sense caracters intermitjos que no formen part dels
   * valors en sí, de forma que s'obtindrà:<br>
   * <code> index id_obj  nil</code> <br>
   * @return StringTokenizer que conté les dades que caracteritzen un objecte
   */
  StringTokenizer llegirObj(){
    StringTokenizer st = null;
    String lin="";

    do{
      lin = llegirLinia();
      logger.finer("Linia llegida: " + lin);
      if (lin != null) {
        st = new StringTokenizer(lin, '\t' + " ()");
      }
    } while ((lin != null) && (st.countTokens()== 0));
    if (lin == null)
      st=null;

    return st;
  }

  /**
   * Escriu els detalls indicats de l'objecte al fitxer seguint el format de KLASS
   * @param index
   * @param nomObj
   * @throws IOException
   */
  void escriureObjecte(int index, String nomObj) throws IOException {
    escriureLin("((" + index + " " + nomObj + ") NIL)");
  }

  //ROBER  Ahora se guardaran objetos con peso

  /**
   * Funcio igual que la anterior afegintli el pes del objecte
   *
   * @ param index.  Conte l'index de l'objecte.
   * @ param nomObj.  Conte l'identificador de l'objecte.
   * @ param pes.  Conte el pes de l'objecte.
   * @ return
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */

  void escriureObjecte(int index, String nomObj, float pes) throws IOException {
    escriureLin("((" + index + " " + nomObj  + " " + pes + ") NIL)");
  }

}

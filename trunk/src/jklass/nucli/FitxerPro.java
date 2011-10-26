package jklass.nucli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.*;
import java.util.StringTokenizer;

/**
 * Classe que permet gestionar fitxers .pro en format KLASS, fitxers que contenen la
 * la caracteritzaci� de les propietats de la matriu de dades, la descripci� de
 * les columnes de la matriu. Cada l�nia cont�
 * la informaci� d'una columna, per exemple:
 *  <br> <code>
 * ((0 Display)  (nil    1    13    Q    (COLOR-TV B-&-W-TV BUILT-IN TERMINAL))) <br>
 * ((0 RAM)   ((0 100)  1     13           C       NIL))<br>
 *            ... <br> </code> <br>
 * La primera fila cont� la caracteritzaci� d'una variable num�rica, i la segona
 * cont� la de una variable num�rica. Depenent del tipus de variable es trobaran
 * certes posicions de la fila que en lloc de contenir un valor contindran l'String 'nil'.
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estad�stic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author M� del Mar Colillas
 * @version v.0
 */
class FitxerPro extends Fitxer {
  /**
   * Constructor
   * @param nom nom del fitxer sense extensi�
   */
  FitxerPro(String nom) {
    super(nom);
    modificarNom(obtenirNom()+".pro");
  }

 /**
   * Llegeix una fila del fitxer de propietats (una propietat) i retorna un 'StringTokenizer' que
   * cont� la informaci� de la propietat, StringTokenizer no �s res
   * m�s que una linia que descriu la propietat partida en 'Strings' (tokens), sense car�cters intermitjos que no formen part dels
   * valors en s�, de forma que s'obtindr� per una propietat:<br>
   * <code> index id_pro  rang  pes  num_objs  tipus_variable  llista_modalitats </code> <br>
   * @return StringTokenizer que cont� les dades que caracteritzen una propietat
   */
  StringTokenizer llegirProp(){
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

  void escriurePropNumerica(int ordre,String nomPro, float min, float max, int pes, int nobjs) throws IOException {
    escriureLin("( (" + ordre + " " + nomPro+ ")"+ "( (" + min + " " + max + ")" + " " + pes +  " " + nobjs +  " C NIL ))");
  }

  void escriurePropCategoricaGral(int ordre, String nomPro, int pes, int nObjs, ArrayList llMods, char tipus) throws IOException {
    String str;
    Iterator llistaModal;

    str = "((" + ordre + " " + nomPro+ ")"+ "( NIL " + pes +  " " + nObjs + " " + tipus +" (";
    llistaModal = llMods.iterator();
    while (llistaModal.hasNext()) {
      str = str + " " + llistaModal.next().toString();
    }
    escriureLin(str + ")))");
  }

  void escriurePropCategorica(int ordre, String nomPro, int pes, int nObjs, ArrayList llMods) throws IOException {
    escriurePropCategoricaGral(ordre, nomPro, pes, nObjs, llMods, 'Q');
  }

  void escriurePropNominal(int ordre, String nomPro, int pes, int nObjs, ArrayList llMods) throws IOException {
    escriurePropCategoricaGral(ordre, nomPro, pes, nObjs, llMods, 'N');
  }

  void escriurePropOrdinal(int ordre, String nomPro, int pes, int nObjs, ArrayList llMods) throws IOException {
    escriurePropCategoricaGral(ordre, nomPro, pes, nObjs, llMods, 'O');
  }

}

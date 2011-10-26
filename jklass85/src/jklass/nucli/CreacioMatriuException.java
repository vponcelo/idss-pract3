package jklass.nucli;

/**
 * Excepci� que indica que s'ha produ�t algun problema en el proc�s de creaci�
 * d'una matriu.
 *
 * Aquesta excepci� es fa servir a les classes: GestorKlass, GestorMatriu.
 *
 * <p>Title: Java-Klass</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author M� del Mar Colillas
 * @version 0
 */

public class CreacioMatriuException extends Exception {
  /**
   * Constructor de l'excepci� sense cap tipus d'informaci� associada
   */
  public CreacioMatriuException() {
    super();
  }

  /**
   * Constructor de l'excepci� amb un missatge associat que proporcioni
   * informaci� detallada.
   * @param s String que cont� informaci� detallada sobre la ra� per la qual es crea l'excepci�
   */
  public CreacioMatriuException(String s) {
    super(s);
  }

}
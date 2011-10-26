package jklass.nucli;

/**
 * Excepció que indica que s'ha produït algun problema en el procés de creació
 * d'una matriu.
 *
 * Aquesta excepció es fa servir a les classes: GestorKlass, GestorMatriu.
 *
 * <p>Title: Java-Klass</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version 0
 */

public class CreacioMatriuException extends Exception {
  /**
   * Constructor de l'excepció sense cap tipus d'informació associada
   */
  public CreacioMatriuException() {
    super();
  }

  /**
   * Constructor de l'excepció amb un missatge associat que proporcioni
   * informació detallada.
   * @param s String que conté informació detallada sobre la raó per la qual es crea l'excepció
   */
  public CreacioMatriuException(String s) {
    super(s);
  }

}
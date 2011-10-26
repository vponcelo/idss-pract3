package jklass.nucli;

import java.util.logging.*;

/**
 * Classe abstracta que proporciona la funcionalitat bàsica per tota propietat
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */
abstract public class Propietat {
  protected static Logger logger = Logger.getLogger(Propietat.class.getName());
  /**
   * Identificador de la propietat
   */
  private String identificador;

  /**
   * Contructor
   * @param id Identificador de la propietat
   */
  Propietat(String id) {
    identificador=id;
  }

  /**
   * Obté l'identificador de la propietat
   * @return identificador de la propietat
   */
  public String obtenirId() {
    return identificador;
  }
//DISTANCIES*********************************************************************  pes
  /**
   * Métode abstracte que ha de actualitzar l'estructura interna de la propietat
   * @param valor cadena de caràcters que es correspon amb un valor observat per aquesta propietat
   * @param p conté el pes de l'objecte
   * @throws CreacioPropietatsException si es produeix algun error intern al fer les actualitzacions corresponents
   */
  abstract void actualitzar(String valor,float p) throws CreacioPropietatsException;

  /**
   * Métode abstracte que ha de actualitzar l'estructura interna de la propietat
   * @param valor cadena de caràcters que es correspon amb un valor observat per aquesta propietat
   * @throws CreacioPropietatsException si es produeix algun error intern al fer les actualitzacions corresponents
   */
  abstract void actualitzarLliure(String valor) throws CreacioPropietatsException;



}

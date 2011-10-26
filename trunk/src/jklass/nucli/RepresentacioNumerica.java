package jklass.nucli;

import java.io.IOException;
import java.util.logging.*;

/**
 * Classe que engloba tots els atributs i mètodes comuns per totes les
 * representacions numèriques. Les classes que heredin d'aquesta poden sobreescriure
 * els métodes que siguin necessaris o extendre'ls si cal.
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */
abstract class RepresentacioNumerica {
  protected static Logger logger=Logger.getLogger(RepresentacioNumerica.class.getName());
  /**
   * Fitxer on s'ha de escriure el codi LaTeX corresponent a la representació numèrica
   */
  protected FitxerTex fTex;

  /**
   * Constructor
   * @param fitxer fitxer on s'escriu tota la sortida en LaTeX
   */
  RepresentacioNumerica(FitxerTex fitxer) {
    fTex = fitxer;
  }

}
package jklass.nucli;

import java.io.IOException;
import java.util.logging.*;

/**
 * Classe que engloba tots els atributs i m�todes comuns per totes les
 * representacions num�riques. Les classes que heredin d'aquesta poden sobreescriure
 * els m�todes que siguin necessaris o extendre'ls si cal.
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estad�stic</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p> </p>
 * @author M� del Mar Colillas
 * @version v.0
 */
abstract class RepresentacioNumerica {
  protected static Logger logger=Logger.getLogger(RepresentacioNumerica.class.getName());
  /**
   * Fitxer on s'ha de escriure el codi LaTeX corresponent a la representaci� num�rica
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
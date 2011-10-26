package jklass.nucli;

import java.io.IOException;
import java.util.logging.*;

/**
 * Classe que engloba tots els atributs i mètodes comuns per totes les
 * representacions gràfiques de l'Anàlisi Descriptiva. Les classes que heredin d'aquesta poden sobreescriure
 * els métodes que siguin necessaris o extendre'ls si cal.
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */
abstract class RepresentacioGrafica {
  protected static Logger logger=Logger.getLogger(RepresentacioGrafica.class.getName());
  /**
   * Fitxer on s'ha de escriure el codi LaTeX corresponent a la representació gráfica
   */
  protected FitxerTex fTex;

  protected final static int lonX = (Constants.AMPLADA - Constants.OFFSET_X);
  protected final static int lonY = (Constants.ALZADA - Constants.OFFSET_Y);
  /**
   * Constructor
   * @param fitxer fitxer on s'escriu tota la sortida en LaTeX
   */
  RepresentacioGrafica(FitxerTex fitxer) {
    fTex = fitxer;
  }

  /**
   * Dibuixa i etiqueta els eixos X i Y del gràfic amb els noms indicats als
   * paràmetres seguint les mides estàndard pels gràfics,
   * especificades a les constants AMPLADA, OFFSET_AMP, ALZADA i
   * OFFSET_ALZ de la 'interface' Constants.
   *
   * @param varX nom de la variable del eix X
   * @param varY nom de la variable del eix Y
   * @throws IOException si es produeix algun error al excriure el codi LaTeX
   * en el fitxer associat
   * @see jklass.nucli.Constants
   */
  protected void dibuixarYEtiquetarEixos(String varX, String varY) throws
      IOException {

    fTex.escriureLin("\\thicklines");
    fTex.escriureLin("\\put(0,0){\\line(1,0){" + lonX + "}}");
    fTex.escriureLin("\\put(0,0){\\line(0,1){" + lonY + "}}");
    if ( (varX != null) && (varY != null)) {
      fTex.escriureLin("\\put(" + (lonX + 8) + ",-5){\\large " + varX + "}");
      fTex.escriureLin("\\put(-20," + (lonY + 10) + "){\\large " + varY + "}");
    }
  }

}

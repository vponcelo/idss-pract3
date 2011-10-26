package jklass.nucli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.*;
import java.util.StringTokenizer;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

public class Extrems {
  private static Logger logger=Logger.getLogger(Extrems.class.getName());
  /**
   * nom de la var. numerica associada
   */
  private String nomNum;
  /**
   * nom de la var. categorica associada
   */
  private String nomCateg;
  /**
   * dades (mínim i màxims ordenats després de la creació del objecte)
   */
  private float dades[]; //dades (mínims i màxims)

  public Extrems(CalculsBivNC calculs, String nomNum, String nomCateg, String dir) throws IOException {
    FitxerZk fZk;
    FitxerMk fMk;
    String str = null;
    String[] llMods;
    int i, n=0;
    CalculsUnivNum calculsUN;

    //es crea el fitxer mk amb els min i max de cada modalitat
    fMk = new FitxerMk(dir + nomNum + "_" + nomCateg);
    fMk.obrirPerEscriure(false);
    llMods = calculs.obtenirModalitats();
    dades = new float[llMods.length * 2]; //per cada modalitat es recuperen 2 valors (min i max)
    for (i = 0; i < llMods.length; i++) {
      calculsUN = calculs.obtenirCalculsUniv(llMods[i]);
      dades[n] = calculsUN.obtenirMin();
      str = new Float(dades[n]).toString();
      n++;
      fMk.escriureLin(str);
      dades[n] = calculsUN.obtenirMax();
      str = new Float(dades[n]).toString();
      n++;
      fMk.escriureLin(str);
    }
    fMk.tancarEsc();
    logger.info("Creat el fitxer " + nomNum + "_" + nomCateg + ".mk");
    // Es crea el fitxer zk ordenant els valors de mk
    Arrays.sort(dades);
    fZk = new FitxerZk(dir + nomNum + "_" + nomCateg);
    fZk.obrirPerEscriure(false);

    for (i = 0; i < dades.length; i++) {
      str = new Float(dades[i]).toString();
      fZk.escriureLin(str);
    }
    fZk.tancarEsc();
    logger.info("Creat el fitxer " + nomNum + "_" + nomCateg + ".zk");
  }
}
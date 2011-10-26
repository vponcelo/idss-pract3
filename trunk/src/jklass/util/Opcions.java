package jklass.util;

import java.util.Hashtable;

/**
 * Conté constants que indiquen: el tipus d'estadistics que engloben les opcions;
 *  Estadistics Analisi descriptiva univariant; Estadistics Analisi descriptiva bivariant;
 *  Estadistics Analisi descriptiva trivariant;
 * Estadistics Analisi descriptiva per classes
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas,Laia Riera Guerra
 * @version v.4
 */

public class Opcions {

  // Constants que indiquen el tipus d'estadistics que engloben les opcions
  /**
   *
   */
  public final static char UNIVARIANT = 'u';
  /**
   *
   */
  public final static char BIVARIANT = 'b';
  /**
   *
   */
  public final static char TRIVARIANT = 't';
  /**
   *
   */
  public final static char PER_CLASSES = 'c';

  // Estadistics Analisi descriptiva univariant
  /**
   *
   */
  public final static int ESTAD_SUM = 0;
  /**
   *
   */
  public final static int RESUM_5 = 1;
  /**
   *
   */
  public final static int HISTO = 2;
  /**
   *
   */
  public final static int BOXPLOT = 3;
  /**
   *
   */
  public final static int TAULA_FREQS = 4;
  /**
   *
   */
  public final static int DIAGR_BARRES = 5;

  // Estadistics Analisi descriptiva bivariant
  /**
   *
   */
  public final static int CORREL= 6;
  /**
   *
   */
  public final static int PLOT = 7;
  /**
   *
   */
  public final static int DESCR_GR = 8;
  /**
   *
   */
  public final static int HISTO_MULT = 9;
  /**
   *
   */
  public final static int BOXP_MULT = 10;
  /**
   *
   */
  public final static int T_CONTINGENCIA = 11;
  /**
   *
   */
  public final static int D_BAR_MULT = 12;

  // Estadistics Analisi descriptiva trivariant
  /**
   *
   */
  public final static int LETTERPLOT = 13;
  /**
   *
   */
  public final static int TAULA_CCN = 14;

  // Estadistics Analisi descriptiva per classes
  /**
   *
   */
  public final static int DESCR_CLASS = 15;
  public final static int DESCR_EXTENSIONAL = 16;
  /**
   * Tipus d'estadistics que engloben les opcions
   */
  char tipus;
  /**
   *  Taula que conté la taula amb les opcions per cada estadistic del tipus
   * d'anàlisi que s'estigui realitzant, depenent del cas: <br>
   * key = constant que indica l'estadistic <br>
   * value = Hashtable amb totes les opcions per l'estadistic de la 'key'
   */
  Hashtable taula;

  /**
   * Constructor d'Opcions del tipus indicat
   * @param tipus un dels quatre tipus d'opcions especificats a la classe
   */
  public Opcions(char tipus) {
    taula = new Hashtable();
     this.tipus = tipus;
  }

  /**
   * Afegeix les opcions específicades a la taula per l'estadistic indicat
   * @param estad constant especificada a la classe que indica un estadístic
   * @param opc taula amb les opcions
   */
  public void afegirOpcions(int estad, Hashtable opc) {
    taula.put(new Integer(estad), (Object)opc);
  }

  /**
   * Elimina la taula d'opcions de l'estadistic indicat
   * @param estad constant especificada a la classe que indica un estadístic
   */
  public void eliminarOpcions(int estad){
    taula.remove(new Integer(estad));
  }

  /**
   * Obte el tipus d'opcions que conté l'objecte Opcions
   * @return constant que indica el tipus
   */
  public char obtenirTipusOpcions(){
    return tipus;
  }

  /**
   * Obté la taula que conté totes les opcions per l'estadistic indicat
   * @param estad estadístic del que es volen les opcions
   * @return Hashtable taula que conté totes les opcions de l'estadístic
   */
  public Hashtable obtenirOpcionsEstad(int estad){
    return (Hashtable)taula.get(new Integer(estad));
  }

  /**
   * Posar totes les opcions de l'estadístic indicat amb els seus valors per defecte
   * @param estad estedistic que es vol actualitzar amb els opcions per defecte
   */
  public void posarPerDefecte(int estad){
    Hashtable opc = new Hashtable();

    switch (estad) {
      case ESTAD_SUM :
        opc.put("min", Boolean.TRUE);
        opc.put("max", Boolean.TRUE);
        opc.put("mitj", Boolean.TRUE);
        opc.put("me", Boolean.TRUE);
        opc.put("q", Boolean.TRUE);
        opc.put("dist_iq", Boolean.FALSE);
        opc.put("amp", Boolean.FALSE);
        opc.put("var", Boolean.FALSE);
        opc.put("desv", Boolean.FALSE);
        opc.put("qvar", Boolean.FALSE);
        opc.put("qdesv", Boolean.TRUE);
        opc.put("coef", Boolean.TRUE);

        afegirOpcions(estad, opc);
        break;
      case RESUM_5:
        break;
      case HISTO:
        opc.put("tipus", "efectius");
        opc.put("classes", "auto");
        opc.put("taula", "no");
        opc.put("limitsX", "observat");
        opc.put("graduaX", "marques");
        opc.put("nummarqX", "5");
        opc.put("limitsY", "complet");
        opc.put("graduaY", "si");
        opc.put("nummarqY", "5");

        afegirOpcions(estad, opc);
        break;
      case BOXPLOT:
        opc.put("limits", "observat");
        opc.put("gradua", "marques");
        opc.put("num", "5");

        afegirOpcions(estad, opc);
        break;
      case TAULA_FREQS:
        opc.put("abs", Boolean.TRUE);
        opc.put("rel", Boolean.TRUE);
        opc.put("abs_acum", Boolean.TRUE);
        opc.put("rel_acum", Boolean.TRUE);

        afegirOpcions(estad, opc);
        break;
      case DIAGR_BARRES:
        opc.put("tipus", "efectius");
        opc.put("limits", "complet");
        opc.put("graduaX", "si");
        opc.put("graduaY", "marques");
        opc.put("nummarq", "5");

        afegirOpcions(estad, opc);
        break;
      case CORREL:
        break;
      case PLOT:
        opc.put("limitsX", "observat");
        opc.put("graduaX", "si");
        opc.put("nummarqX", "3");
        opc.put("limitsY", "observat");
        opc.put("graduaY", "si");
        opc.put("nummarqY", "3");

        afegirOpcions(estad, opc);
        break;
      case DESCR_GR:
        opc.put("min", Boolean.TRUE);
        opc.put("max", Boolean.TRUE);
        opc.put("mitj", Boolean.TRUE);
        opc.put("me", Boolean.TRUE);
        opc.put("q", Boolean.TRUE);
        opc.put("dist_iq", Boolean.FALSE);
        opc.put("amp", Boolean.FALSE);
        opc.put("var", Boolean.FALSE);
        opc.put("desv", Boolean.FALSE);
        opc.put("qvar", Boolean.FALSE);
        opc.put("qdesv", Boolean.TRUE);
        opc.put("coef", Boolean.TRUE);

        afegirOpcions(estad, opc);
        break;
      case HISTO_MULT:
        opc.put("classes", "auto");
        opc.put("taula", "no");
        opc.put("limitsX", "observat");
        opc.put("limitsY", "auto");
        opc.put("graduaY", "si");
        if (tipus == PER_CLASSES) {
          opc.put("tipus", "freqs");
          opc.put("graduaX", "vista");
          opc.put("graduaY", "no");
        }
        else {
          opc.put("tipus", "efectius");
          opc.put("graduaX", "marques");
          opc.put("nummarqX", "5");
          opc.put("graduaY", "si");
          opc.put("nummarqY", "5");
        }
        afegirOpcions(estad, opc);
        break;
      case BOXP_MULT:
        opc.put("limits", "observat");
        if (tipus == PER_CLASSES) {
          opc.put("gradua", "vista");
        }
        else {
          opc.put("gradua", "marques");
          opc.put("num", "5");
        }
        afegirOpcions(estad, opc);
        break;
      case T_CONTINGENCIA:
        opc.put("tipus","efectius");
        opc.put("nmods","5");
        opc.put("fitxer", Boolean.FALSE);
        afegirOpcions(estad, opc);
        break;
      case D_BAR_MULT:
        opc.put("limits", "auto");
        if (tipus == PER_CLASSES) {
          opc.put("tipus", "freqs");
          opc.put("graduaX", "vista");
          opc.put("graduaY", "no");
        }
        else {
          opc.put("tipus", "efectius");
          opc.put("graduaX", "si");
          opc.put("graduaY", "marques");
          opc.put("nummarq", "5");
        }
        afegirOpcions(estad, opc);
        break;
      case DESCR_CLASS:
        opc.put("maxnum", "5");
        opc.put("maxcateg", "5");

        opc.put("min", Boolean.TRUE);
        opc.put("max", Boolean.TRUE);
        opc.put("mitj", Boolean.TRUE);
        opc.put("me", Boolean.FALSE);
        opc.put("q", Boolean.FALSE);
        opc.put("dist_iq", Boolean.FALSE);
        opc.put("amp", Boolean.FALSE);
        opc.put("var", Boolean.FALSE);
        opc.put("desv", Boolean.FALSE);
        opc.put("qvar", Boolean.FALSE);
        opc.put("qdesv", Boolean.TRUE);
        opc.put("coef", Boolean.FALSE);

        afegirOpcions(estad, opc);
        break;
      case LETTERPLOT:
        opc.put("tipus", "color"); // canviat, abans: opc.put("tipus", "bn");
        opc.put("limitsX", "observat");
        opc.put("graduaX", "si");
        opc.put("nummarqX", "3");
        opc.put("limitsY", "observat");
        opc.put("graduaY", "si");
        opc.put("nummarqY", "3");

        afegirOpcions(estad, opc);
        break;
      case TAULA_CCN:
        opc.put("nmods","5");

        opc.put("miss", Boolean.FALSE);
        opc.put("min", Boolean.FALSE);
        opc.put("max", Boolean.FALSE);
        opc.put("mitj", Boolean.TRUE);
        opc.put("me", Boolean.FALSE);
        opc.put("q1", Boolean.FALSE);
        opc.put("q3", Boolean.FALSE);
        opc.put("dist_iq", Boolean.FALSE);
        opc.put("amp", Boolean.FALSE);
        opc.put("var", Boolean.FALSE);
        opc.put("desv", Boolean.FALSE);
        opc.put("qvar", Boolean.FALSE);
        opc.put("qdesv", Boolean.FALSE);
        opc.put("coef", Boolean.FALSE);

        afegirOpcions(estad, opc);
        break;
      case DESCR_EXTENSIONAL:
        opc.put("tabular",Boolean.TRUE);

        afegirOpcions(estad, opc);
        break;
      default: break;
    }
  }

  /**
   * Indica si s'han afegir les opcions per l'estadístic indicat
   * @param estad estadistic a comprobar
   * @return cert, si conté les opcions de l'estadístic;
   * fals, en cas contrari.
   */
  public boolean estadisticAfegit(int estad) {
    return taula.containsKey(new Integer(estad));
  }
  
  //////////////////////////////LAIA////////////////////////////////////
  /**
   * Posar totes les opcions de l'estadístic indicat amb els seus valors per defecte segons la descriptiva de Bases de coneixement
   * @param estad estedistic que es vol actualitzar amb els opcions per defecte
   * @author Laia Riera Guerra
   */
  public void posarPerDefecteBC(int estad){
    Hashtable opc = new Hashtable();

    switch (estad) {     
      case TAULA_FREQS:
        opc.put("abs", Boolean.TRUE);
        opc.put("rel", Boolean.TRUE);
        opc.put("abs_acum", Boolean.FALSE);
        opc.put("rel_acum", Boolean.FALSE);

        afegirOpcions(estad, opc);
        break;
      case DIAGR_BARRES:
        opc.put("tipus", "efectius");
        opc.put("limits", "complet");
        opc.put("graduaX", "si");
        opc.put("graduaY", "marques");
        opc.put("nummarq", "5");

        afegirOpcions(estad, opc);
        break; 
      
      case DESCR_EXTENSIONAL:
        opc.put("tabular",Boolean.FALSE);

        afegirOpcions(estad, opc);
        break;
      default: break;
    }
  }
  
}
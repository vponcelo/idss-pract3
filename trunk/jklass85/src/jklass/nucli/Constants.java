package jklass.nucli;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

public interface Constants {
  /**
   * nom del fitxer que conté la capçalera general a utilitzar
   */
  public final static String FITXER_CAP_GRAL = "capsalera_vertical.tex";
  /** nom del fitxer que conté la capçalera a utilitzar per fer servir colors
   *
   */
  public final static String FITXER_CAP_COLOR = "capsalera_color.tex";
  /**nom del fitxer que conté la capçalera a utilitzar per fer servir colors
   *
   */
  public final static String FITXER_CAP_INF = "capsalera_informe.tex";

  /** Numero mín. de decimals que es mostren al escriure un real en un fitxer Latex
   *
   */
  public final static int MIN_N_DECIMALS = 0;
  /** Numero màx. de decimals que es mostren al escriure un real en un fitxer Latex
 *
 */
  public final static int MAX_N_DECIMALS = 4;

  // Constants relatives als tabulars
  /**
   * nombre màx. de files que pot tenir un tabular per pàg. vertical amb mida de lletra normal
   */
  public final static int MAX_FILES_TABV = 42;
  /**
   * nombre màx. de files que pot tenir un tabular per pàg. horitzontal amb mida
   *  de lletra normal, es fa servir a la descriptiva per classes
   */
  public final static int MAX_FILES_TABH = 32;

  /** Amplada minima d'una columna en un tabular d'un fitxer Latex.
  * Només es fa servir per la fila de dades mancants de la taula de freqs.
  */
  public final static int MIN_COL_TAB = 11;

  // Constants pel tractament de les propietats
  public final static String PROP_NUMERICA = "C";
  public final static String PROP_CATEGORICA = "Q";
  public final static String PROP_ORDINAL = "O";
  public final static String PROP_NOMINAL = "N";
  public final static String NULO = "NIL";

  //Constants pel tractament de combinacions de propietats
  public final static String NUMxNUM ="NN";
  public final static String NUMxCAT ="NC";
  public final static String CATxCAT ="CC";
  public final static String NUMxNUMxCAT ="NNC";
  public final static String CATxCATxNUM ="CCN";

  // Constants generals relatives a les representacions gràfiques
  public final static int ALZADA = 180; // originalment era 140
  public final static int AMPLADA = 420;
  public final static int OFFSET_Y = 40;
  public final static int OFFSET_X = 40;
  public final static int OFFSET_EIX = 20;

  //Constants específiques per l'histograma
  public final static int N_MARQUES = 5;

  //Constants específiques pel Boxplot
  public final static float AMP_CAIXA = 20;

  // Constants relatives a les representacions gràfiques multiples
  public final static int OFFSET_Y_MULT = 0;
  public final static int OFFSET_X_MULT = 40;
  public final static int SEPARACI0_V = ALZADA - OFFSET_Y;
  /**
   * nombre màx. de figures per página
   */
  public final static int MAX_FIG_VERT = 5; //

  // Constants específiques pel Plot
  public final static int PLOT_ALZ = 240;
  public final static int PLOT_AMP = 240;
  public final static int PLOT_OFF_ALZ = 40;
  public final static int PLOT_OFF_AMP = 40;
  public final static String RANG_TEORIC = "T";
  public final static String RANG_REAL = "R";
  public final static float DIAM_PT = 3F;
  public final static float PAS = 0.3F;

  // Constants específiques pel LetterPlot
  public final static int MAX_MODALS = 10;
  /** Quantitat de colors definits a la llista ha de coincidir amb MAX_MODALS is
   * els noms dels colors han de coincidir amb els que es defineixin a
   * FITXER_CAP_COLOR, excepte els colors que ja estan definits al paquet color
   * ("red","blue","green","cyan","magenta","yellow") sempre que es faci servir
   * aquest paquet                                                            */
  public final static String[] colors = {"red","blue","green","cyan","magenta",
      "yellow","rawSienna","slateGreyDark", "cadmiumYellowLight", "purple"};
  public final static String[] simbols = {"\\circ", "\\times", "\\ast",
      "\\bullet", "\\star", "\\diamond", "+", "\\odot", "\\ominus", "\\vee"};

  // Constants relatives a les representacions per classes
  public final static String FITXER_CAP_CLASS = "capsalera_horitzontal.tex";
  public final static String FITXER_HORIT_CLASS = "capsalera_horitzontal_jose.tex";
  public final static int MAX_FIG_HOR = 5;
  /**
   * nombre de figures de classe (en alçada) es poden posar per página
   */
  public final static int MAX_FIG_CLASS = 12;
  /**
   * amplada de la picture del gràfic de separació
   */
  public final static float AMP_FIG_SEPAR = 100;
  public final static int OFFS_ALZ_SEPAR = 0;
  public final static int OFFS_AMP_SEPAR = 0;
  /**
   * amplada en pt per la minipage de les etiquetes
   */
  public final static float AMP_MINIPAGE_ETIQ = ((float)AMPLADA / 2) / 4;
  /**
   * amplada en pt per la minipage del gràfic de separació entre vars.
   */
  public final static float AMP_MINIPAGE_SEPAR = AMP_FIG_SEPAR/4;
  /**
   * amplada en pt per la minipage dels gràfics d'una var.
   */
  public final static float AMP_MINIPAGE_GRAF = (AMPLADA + OFFSET_X_MULT) / 4;
  /**
   * longitud màxima de la etiqueta vertical que es posen a les categories d'una variable
   */
  public final static int MAX_ETIQ = 5;

  // Constants generals relatives a la representació d'arbres
  public final static float AMPLADA_AR = 9;
  public final static float ALZADA_AR = 11;
  public final static float OFFSET_X_AR = -0.3F;
  public final static float OFFSET_Y_AR= -0.1F;
  public final static float LON_Y_AR = ALZADA_AR-1;

}
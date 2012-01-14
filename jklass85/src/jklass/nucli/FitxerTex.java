package jklass.nucli;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.text.ParseException;

/**
 * Classe que permet gestionar fitxers .tex amb codi LaTeX.
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */
public class FitxerTex extends Fitxer {
  /**
   * Objecte que permet donar format a un número.
   */
  private static NumberFormat formatNum = NumberFormat.getNumberInstance(Locale.
      ENGLISH);

  static {
    formatNum.setMinimumFractionDigits(Constants.MIN_N_DECIMALS);
    formatNum.setMaximumFractionDigits(Constants.MAX_N_DECIMALS);
    formatNum.setGroupingUsed(false);
  }

  /**
   * Constructor
   * @param nom Nom del fitxer Latex a crear
   */
  FitxerTex(String nom) {
    super(nom + ".tex");
  }

  /**
   * Copia el contingut del fitxer indicat per el paràmetre que serà la capsalera
   * del fitxer .tex
   * @param fitxerCap nom del fitxer de capçalera a copiar
   * @throws CreacioFitxerException si es produeix un error al llegir el fitxer
   * <code> fitxerCap <code> o al escriure en el .tex actual
   */
  void copiarCapsaleraTex(String fitxerCap) throws CreacioFitxerException{
    copiarFitxer(".//conf//" + fitxerCap);
  }

  /**
   * Formateja el número real de tipus float passat per paràmetre amb un mínim i
   * màxim nombre de decimals i genera el seu String equivalent.
   * @param real nombre a formatejar
   * @return String equivalent al nombre indicat
   */
  static String formatejarReal(float real){
    String str=null;

    if (Float.isNaN(real)) {
      str = " ";
    } else {
      str = formatNum.format(real);
    }
    return str;

  }

  static float parsejarReal(String str) throws ParseException {
    return formatNum.parse(str).floatValue();
  }

  /**
   * Formateja el número real de tipus float passat per paràmetre amb un mínim i
   * màxim nombre de decimals i genera el seu String equivalent.
   * @param real nombre a formatejar
   * @return String equivalent al nombre indicat
   */
  static String formatejarDouble(double real){
    String str=null;
    if (Double.isNaN(real)) {
      str = " ";
    } else {
      str = formatNum.format(real);
    }
    return str;
  }

  static double parsejarDouble(String str) throws ParseException {
    return formatNum.parse(str).doubleValue();
  }

  /**
   * Obté el mínim nombre de decimals que es faran servir per escriure reals
   * en el fitxer
   * @return nombre mínim de decimals
   */
  int obtenirMinDecimals(){
    return (formatNum.getMinimumFractionDigits());
  }

  /**
   * Obté el màxim nombre de decimals que es faran servir per escriure reals
   * en el fitxer
   * @return nombre mínim de decimals
   */
  int obtenirMaxDecimals(){
    return (formatNum.getMaximumFractionDigits());
  }

  /**
   * Adapta l'String per a que pugui ser compil·lat correctament en LaTeX, modificant
   * els caràcters reservats de LaTeX ( &, %, $, >, , _ )
   * @param str String a adaptar per LaTeX
   * @return String modificat i adaptat
   */
  public String adaptarATex(String str) {
    String strTex = new String();
    int i;
    char c;

    for (i = 0; i < str.length(); i++) {
      c = str.charAt(i);
      switch (c) {
        case '&': case '%': case '$':
          strTex = strTex + "\\" + c;
          break;
        case '>': case '<': case '|':
          strTex = strTex + '$' + c + '$';
          break;
        case '_':
          strTex = strTex + '-';
          break;
        default:
          strTex = strTex + c;
          break;
      }
    }
    return strTex;
  }

  public String adaptarPerShortstack(String etiq, int lon) {
    String strTex, str = new String();
    int i, max; // longitud maxima que pot ocupar la modalitat
    char c;

    max = etiq.length();
    if ((lon > 0) && (max>lon)){
      max = lon;
    }
    for (i = 0; i < max; i++) {
      c = etiq.charAt(i);
      strTex = adaptarATex(String.valueOf(c));
      str = str + "\\\\" + strTex;
    }

    return str;
  }

  public String eliminarChar(String str, char c) {
    String strSin = new String();
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) != c)
        strSin += str.charAt(i);
    }
    return strSin;
  }
  
 public boolean encontrarChar(String str, char c) {
    boolean strSin = false;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == c)
        strSin = true;
    }
    return strSin;
  }
  
  

  /**
   * Tanca el document i el fitxer .tex.
   * @throws IOException si es produeix un error al escriure al fitxer
   */
  public void finalitzarTex() throws IOException {
    String str;

    str = "\\end{document}";
    escriureLin(str);
    tancarEsc();
  }

}

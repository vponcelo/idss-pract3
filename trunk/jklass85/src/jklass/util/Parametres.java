package jklass.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.logging.*;
import java.util.Properties;

/** Classe que permet gestionar el paràmetres de configuració de l'aplicació.
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

public class Parametres {
  private static Logger logger=Logger.getLogger(Parametres.class.getName());

  private String nomFitxer;
  private String descripcio;

  protected Properties properties = null;

  private String maxMatrius;
  private String cmdCompilaLTX;
  private String argsCompilaLTX;
  private String cmdVisorLTX;
  private String argsVisorLTX;
  private String cmdCompilaPDF;
  private String argsCompilaPDF;
  private String cmdVisorPDF;
  private String argsVisorPDF;

  private String sMaxMatrius = "matrius.max";
  private String sCmdCompilaLTX = "compilaLTX.cmd";
  private String sArgsCompilaLTX = "compilaLTX.args";
  private String sCmdVisorLTX = "visorLTX.cmd";
  private String sArgsVisorLTX = "visorLTX.args";
  private String sCmdCompilaPDF = "compilaPDF.cmd";
  private String sArgsCompilaPDF = "compilaPDF.args";
  private String sCmdVisorPDF = "visorPDF.cmd";
  private String sArgsVisorPDF = "visorPDF.args";

  public Parametres(String nomFitxer){
    new Parametres(nomFitxer,"");
  }

  public Parametres(String nomFitxer, String descripcio) {
      this.nomFitxer = nomFitxer;
      this.descripcio = descripcio;
      obtenirParametres();
  }

  protected void actualitzarProperties() {
       properties.put(sMaxMatrius, maxMatrius);
       properties.put(sCmdCompilaLTX,cmdCompilaLTX);
       properties.put(sArgsCompilaLTX, argsCompilaLTX);
       properties.put(sCmdVisorLTX, cmdVisorLTX);
       properties.put(sArgsVisorLTX, argsVisorLTX);
       properties.put(sCmdCompilaPDF, cmdCompilaPDF);
       properties.put(sArgsCompilaPDF, argsCompilaPDF);
       properties.put(sCmdVisorPDF, cmdVisorPDF);
       properties.put(sArgsVisorPDF,argsVisorPDF);
   }

  protected void actualitzarParams() {
      try {
        maxMatrius = properties.getProperty(sMaxMatrius);
        cmdCompilaLTX = properties.getProperty(sCmdCompilaLTX);
        argsCompilaLTX = properties.getProperty(sArgsCompilaLTX);
        cmdVisorLTX = properties.getProperty(sCmdVisorLTX);
        argsVisorLTX = properties.getProperty(sArgsVisorLTX);
        cmdCompilaPDF = properties.getProperty(sCmdCompilaPDF);
        argsCompilaPDF = properties.getProperty(sArgsCompilaPDF);
        cmdVisorPDF = properties.getProperty(sCmdVisorPDF);
        argsVisorPDF = properties.getProperty(sArgsVisorPDF);
      } catch (NumberFormatException e) {
         logger.warning("Algún parametro del fichero de properties tiene un formato incorrecto ( " + e.getMessage() + ").");
      }
  }

  protected void obtenirParametres() {
      Properties defecte = new Properties();
      FileInputStream in = null;

      /* Si fuera necesario se podria añadir un paso previo:
       * posarPerDefecte(defecte);
       * de forma que este metodo fuese el encargado de que si
       * no encuentra el fichero de propiedades coja el de por defecto
       */

      properties = new Properties(defecte);

      try {
        String separa = System.getProperty("file.separator");
        String dir = new String("." + separa  + "conf");
        
        //OLD: in = new FileInputStream(dir
        //                        + "/"//+ separa
        //                         + nomFitxer);
        in = new FileInputStream(dir + separa + nomFitxer);
        properties.load(in);
      }
      catch (java.io.FileNotFoundException e) {
        in = null;
        logger.warning("No se puede encontrar el fichero de properties. ");
      } catch (java.io.IOException e) {
          logger.warning("No se puede leer el fichero de properties " );
      } finally {
          if (in != null) {
              try { in.close(); } catch (java.io.IOException e) { }
              in = null;
          }
      }
      actualitzarParams();
  }

  public void salvarParams() {
    FileOutputStream out = null;

    actualitzarProperties();

    try {
      String separa = System.getProperty("file.separator");
      String dir = new String("." + separa + "conf");

      String filesep = System.getProperty("file.separator");
      out = new FileOutputStream(dir
                                 + separa
                                 + nomFitxer);
      properties.store(out, descripcio);
    }
    catch (java.io.IOException e) {
      logger.warning("No es pot guardar el fitxer de propietats." +
                     e.getMessage());
    }
    catch (Exception e) {
      logger.warning("Otro error.");
    }
    finally {
      if (out != null) {
        try {
          out.close();
        }
        catch (java.io.IOException e) {}
        out = null;
      }
    }
  }

  public String obtenirMaximMatrius(){
    return maxMatrius;
  }

  public void modificarMaxMAtrius(String str){
    maxMatrius = new String(str);
  }

  public String obtenirCmdCompilaLaTeX(){
    return cmdCompilaLTX;
  }

  public void modificarCmdCompilaLaTeX(String str){
    cmdCompilaLTX = new String(str);
  }

  public String obtenirArgsCompilaLaTeX(){
    return argsCompilaLTX;
  }

  public void modificarArgsCompilaLaTeX(String str){
    argsCompilaLTX = new String(str);
  }

  public String obtenirCmdVisorLaTeX(){
    return cmdVisorLTX;
  }

  public void modificarCmdVisorLaTeX(String str){
    cmdVisorLTX = new String(str);
  }

  public String obtenirArgsVisorLaTeX(){
    return argsVisorLTX;
  }

  public void modificarArgsVisorLaTeX(String str){
    argsVisorLTX = new String(str);
  }

  public String obtenirCmdCompilaPDF(){
    return cmdCompilaPDF;
  }

  public void modificarCmdCompilaPDF(String str){
    cmdCompilaPDF = new String(str);
  }

  public String obtenirArgsCompilaPDF(){
    return argsCompilaPDF;
  }

  public void modificarArgsCompilaPDF(String str){
    argsCompilaPDF = new String(str);
  }

  public String obtenirCmdVisorPDF(){
    return cmdVisorPDF;
  }

  public void modificarCmdVisorPDF(String str){
    cmdVisorPDF = new String(str);
  }

  public String obtenirArgsVisorPDF(){
    return argsVisorPDF;
  }

  public void modificarArgsVisorPDF(String str){
    argsVisorPDF = new String(str);
  }

}
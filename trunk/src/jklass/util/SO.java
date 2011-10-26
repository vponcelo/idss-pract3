package jklass.util;

import java.io.File;
import java.util.logging.*;
/**
 * Classe que engloba la contrucció de totes les comandes que poden dependre del
 *  Sistema Operatiu.
 * <p>Title: Java-KLASS</p>
 * <p>Description: Classe per obtenir informació relacionada amb el Sistema Operatiu </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

public class SO {
	
	/**
	 * Constants relatives als Sistemes Operatius
	 * @author Grup SISPD QT 2009-2010
	 */
	private static final String OSNAME_WIN7 = "Windows 7";
	private static final String OSNAME_WINNT = "Windows NT";
	private static final String OSNAME_WIN2000 = "Windows 2000";
	private static final String OSNAME_WINVISTA = "Windows Vista";
	private static final String OSNAME_WINXP = "Windows XP";
	private static final String OSNAME_WIN95 = "Windows 95";
	private static final String OSNAME_WIN98 = "Windows 98";
	private static final String OSNAME_WINME = "Windows Me";
	private static final String OSNAME_LINUX = "Linux";
	private static final String OSNAME_SOLARIS = "Solaris";
	private static final String OSNAME_SUNOS = "SunOS";
	private static final String OSNAME_AIX = "AIX";
	private static final String OSNAME_MACX = "Mac OS X";
	
  private static Logger logger=Logger.getLogger(SO.class.getName());
  private static Parametres param = new Parametres("jklass.properties","Propiedades de configuracion de Java-Klass");
  private static final String OS_NAME = System.getProperty("os.name");

  public static String obtenirCmdCompilaLtx(String entrada) {
    String cmd = new String();
    String separa, entAbs;

    String pCmd = param.obtenirCmdCompilaLaTeX();
    String pArgs = param.obtenirArgsCompilaLaTeX();
    String dir = new File(entrada).getParent();
    logger.finest("Entrada: " + entrada + "- dir: " + dir);
    if (dir != null) {
      entAbs = entrada;
    } else {
      separa = System.getProperty("file.separator");
      entAbs = System.getProperty("user.dir") + separa + "resultats" + separa +
          entrada;
    }

    logger.finer("OS Name: " + OS_NAME.toString());
    if (OS_NAME.equalsIgnoreCase(OSNAME_WIN7) ||
        OS_NAME.equalsIgnoreCase(OSNAME_WINNT) ||
        OS_NAME.equalsIgnoreCase(OSNAME_WIN2000) ||
        OS_NAME.equalsIgnoreCase(OSNAME_WINVISTA) ||
        OS_NAME.equalsIgnoreCase(OSNAME_WINXP)) {

      cmd = pCmd + " " + pArgs + " " + entAbs;
    }
    else if (OS_NAME.equalsIgnoreCase(OSNAME_WIN95) ||
             OS_NAME.equalsIgnoreCase(OSNAME_WIN98) ||
             OS_NAME.equalsIgnoreCase(OSNAME_WINME)) {
      cmd = "command.com /C " + pCmd  + " " + pArgs + " " + entAbs;
    }
    else if (OS_NAME.equalsIgnoreCase(OSNAME_LINUX) ||
             OS_NAME.equalsIgnoreCase(OSNAME_SOLARIS) ||
             OS_NAME.equalsIgnoreCase(OSNAME_SUNOS) ||
             OS_NAME.equalsIgnoreCase(OSNAME_AIX) ||
             OS_NAME.equalsIgnoreCase(OSNAME_MACX)) {

      cmd = pCmd  + " " + pArgs + " " + entAbs;
    }
    else {
      cmd = null;
    }
    logger.finer("Comanda SO: " + cmd);

    return cmd;
  }

  public static String obtenirCmdCompilaLtxPdf(String entrada) {
    String cmd = new String();
    String separa, entAbs;

    logger.finer("OS Name: " + OS_NAME.toString());
    String dir = new File(entrada).getParent();
    if (dir != null) {
      entAbs = entrada;
    }
    else {
      separa = System.getProperty("file.separator");
      entAbs = System.getProperty("user.dir") + separa + "resultats" + separa +
          entrada;
    }

    if (OS_NAME.equalsIgnoreCase(OSNAME_WIN7) ||
    	OS_NAME.equalsIgnoreCase(OSNAME_WINNT) ||
        OS_NAME.equalsIgnoreCase(OSNAME_WIN2000) ||
        OS_NAME.equalsIgnoreCase(OSNAME_WINVISTA) ||
        OS_NAME.equalsIgnoreCase(OSNAME_WINXP)) {
      cmd = "cmd.exe /C pdflatex " +  entAbs;
    }
    else if (OS_NAME.equalsIgnoreCase(OSNAME_WIN95) ||
             OS_NAME.equalsIgnoreCase(OSNAME_WIN98) ||
             OS_NAME.equalsIgnoreCase(OSNAME_WINME)) {
      cmd = "command.com /C pdflatex " + entAbs;
    }
    else if (OS_NAME.equalsIgnoreCase(OSNAME_LINUX) ||
             OS_NAME.equalsIgnoreCase(OSNAME_SOLARIS) ||
             OS_NAME.equalsIgnoreCase(OSNAME_SUNOS) ||
             OS_NAME.equalsIgnoreCase(OSNAME_AIX) ||
             OS_NAME.equalsIgnoreCase(OSNAME_MACX)) {
      cmd = "pdflatex " + entAbs;
    }
    else {
     cmd = null;
    }

    return cmd;
  }

  public static String obtenirCmdVisorLtx(String entrada) {
    String cmd;

    cmd = param.obtenirCmdVisorLaTeX() + " " + param.obtenirArgsVisorLaTeX()  + " " + entrada;
    return cmd;
  }

  public static String obtenirCmdVisorPdf(String entrada) {
    String cmd = new String();
    String separa, entAbs;

    logger.finer("OS Name: " + OS_NAME.toString());
    String dir = new File(entrada).getParent();
    if (dir != null) {
      entAbs = entrada;
    }
    else {
      separa = System.getProperty("file.separator");
      entAbs = System.getProperty("user.dir") + separa + "resultats" + separa +
          entrada;
    }
    cmd = new String(param.obtenirCmdVisorPDF() + " " +
                     param.obtenirArgsVisorPDF()
                     + " " + entAbs);
    return cmd;
  }

  public static String obtenirCmdCIADEC() {
    String cmd = new String();

    logger.finer("OS Name: " + OS_NAME.toString());

    if (OS_NAME.equalsIgnoreCase(OSNAME_WINNT) ||
    	OS_NAME.equalsIgnoreCase(OSNAME_WIN7) ||
        OS_NAME.equalsIgnoreCase(OSNAME_WIN2000) ||
        OS_NAME.equalsIgnoreCase(OSNAME_WINVISTA) ||
        OS_NAME.equalsIgnoreCase(OSNAME_WINXP)) {
      cmd = "cmd.exe /C start java -cp satelits\\CIADEC\\ciadec.jar Ciadec";
     }
    else if (OS_NAME.equalsIgnoreCase(OSNAME_WIN95) ||
             OS_NAME.equalsIgnoreCase(OSNAME_WIN98) ||
             OS_NAME.equalsIgnoreCase(OSNAME_WINME)) {
      cmd = "command.com /C start java -cp " + System.getProperty("user.dir") +
          "\\satelits\\CIADEC\\ciadec.jar Ciadec";
    }
    else if (OS_NAME.equalsIgnoreCase(OSNAME_LINUX) ||
             OS_NAME.equalsIgnoreCase(OSNAME_SOLARIS) ||
             OS_NAME.equalsIgnoreCase(OSNAME_SUNOS) ||
             OS_NAME.equalsIgnoreCase(OSNAME_AIX) ||
             OS_NAME.equalsIgnoreCase(OSNAME_MACX)) {
      cmd = "java -cp satelits\\CIADEC\\ciadec.jar Ciadec";
    }
    else {
      cmd = null;
    }

    return cmd;
  }

}
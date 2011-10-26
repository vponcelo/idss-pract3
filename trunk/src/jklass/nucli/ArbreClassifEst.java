package jklass.nucli;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.*;
import java.util.Vector;

//Rober
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.1
 */
public class ArbreClassifEst extends ArbreClassif {
 // private static Logger logger=Logger.getLogger(ArbreClassifEst.class.getName());
  /**
   * nom, nom de l'arbre de classificació
   * prefix, prefix que utilitzen les etiquetes dels nodes de classes
   */
  private String Proceso;
  /**
   * llista de nodes ordenats per index de nivell
   */
  
  
  private String Estado;
  /**
   * Hashtable que conté les particions generades per l'arbre;
   * té com clau el nom de la partició (String) i com valor
   * la partició
   */
 
   /**
   * Constructor
   * @param arrel node arrel de l'arbre a construir
   * @param nomArbre nom a donar a l'arbre
   * @param pref prefix indicat al fitxer
   */
  ArbreClassifEst(NodeBinari arrel, String nomArbre, String pref, String proc, String est ) {
    super(arrel,nomArbre,pref);
    Proceso = proc;
    Estado = est;
    }
	 
   void modificarEst ( String est ) {
    Estado = est;
  }
 
	 

 

}

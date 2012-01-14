package jklass.nucli;

import java.io.*;
import java.util.logging.*;

/**
 * Classe genèrica que proporciona una sèrie d'atributs i mètodes
 * per treballar amb qualsevol tipus de fitxers.
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */
abstract class Fitxer {
  protected static Logger logger=Logger.getLogger(Fitxer.class.getName());
  /**
   * Nom físic del fitxer
   */
  private String nom;
  /**
   * Buffer per llegir una línia del fitxer
   */
  private BufferedReader bufferEnt = null;
  /**
   *  Buffer per escriure una línia al fitxer
   */
  private BufferedWriter bufferSort = null;

  /**
   * Constructor
   * @param nomFitxer nom amb el que es crea el fitxer
   */
  Fitxer(String nomFitxer) {
    nom = nomFitxer;
    logger.finest("Fitxer " + nom);
  }

  /**
   * Obté el nom del fitxer al sistema de fitxers
   * @return nom del fitxer
   */
  String obtenirNom(){
    return nom;
  }

  /**
   * Modifica el nom del fitxer
   * @param nomFitxer nou nom que s'assigna al fitxer
   */
  void modificarNom(String nomFitxer){
    nom = nomFitxer;
  }

  public String obtenirDirAbsolut(){
    return new File(nom).getParent();
  }

  /**
   * Intenta obrir el fitxer per lectura. Obre un 'buffer' de lectura per poder
   * anar llegint el fitxer.
   * @throws FileNotFoundException si el fitxer amb el nom que es té assignat no existeix
   */
   void obrirPerLlegir() throws FileNotFoundException{
	//	System.out.println("estoy en obrirPerLlegir"+nom);
    FileReader fr = new FileReader(nom);
    bufferEnt = new BufferedReader(fr);
  }

  /**
   * Llegeix una linea del fitxer.
   * @return cadena de caracters que conté tota una líniea del fitxer, pot ser
   * nul·la si no s'ha pogut llegir res.
   */
  String llegirLinia(){
    String lin=null;

    try {
      lin = bufferEnt.readLine();
    } catch (IOException e){
      logger.warning("ERROR de Lectura: " + e.getMessage());
    }
    return lin;
  }
  /**
   * Obre el fitxer per escriptura. S'obre un 'buffer' d'escriptura per poder
   * anara escribint al fitxer.
   * @param afegint booleà que indica si s'ha d'afegir el que s'escrigui al final del fitxer o no
   * @throws IOException si el fitxer no existeix o no es pot obrir
   */
  void obrirPerEscriure(boolean afegint) throws IOException{
    /* Sempre que s'escrigui un fitxer s'afegirà al final */
    FileWriter fw = new FileWriter(nom,afegint);
    bufferSort = new BufferedWriter(fw);
  }
  
  
  // void obrirPerEscriureSinAgregar() throws IOException{
    /* Sempre que s'escrigui un fitxer s'afegirà al final */
   // FileWriter fw = new FileWriter(nom);
   // bufferSort = new BufferedWriter(fw);
 // }

  

  /**
   * Copia en el fitxer el contigut del fitxer amb el nom indicat al paràmetre.
   * El fitxer només contindrà el contingut copiat.
   * @param nomOrig nom del fitxer a copiar
   * @throws CreacioFitxerException si no es troba el fitxer a copiar o no es
   * pot obrir el fitxer on s'ha de fer la copia.
   */
  void copiarFitxer(String nomOrig) throws CreacioFitxerException{
    FileWriter fw;
    FileReader fr;
    BufferedReader br;
    BufferedWriter bw;
    String lin=null;

    try {
      fr = new FileReader(nomOrig);
      br = new BufferedReader(fr);
      /* Es crea un fitxer nou */
      fw = new FileWriter(nom, false);
      bw = new BufferedWriter(fw);
    } catch (FileNotFoundException e){
      throw new CreacioFitxerException("No s'ha trobat el fitxer a copiar. " + e.getMessage());
    } catch (IOException e){
      throw new CreacioFitxerException("No s'ha pogut crear el nou fitxer. " + e.getMessage());
    }
    try {
      while ((lin = br.readLine()) != null) {
        bw.write(lin);
        bw.newLine();
      }
      br.close();
      fr.close();
      bw.close();
      fw.close();
    }
    catch (IOException e) {
      logger.warning("ERROR de Lectura/Excriptura: " + e.getMessage());
    }
  }

  /**
   * S'escriu la linia indicada al fitxer
   * @param lin cadena de caràcters que es volen escriure al fitxer
   * @throws IOException si no es pot escriure la linia
   */
  void escriureLin(String lin) throws IOException {
    bufferSort.write(lin);
    bufferSort.newLine();
  }

  /**
   * Es tanca el 'buffer' de lectura del fitxer
   * @throws IOException si es produeix un error d'E/S al intentar tancar el buffer
   */
  void tancarLec() throws IOException{
    bufferEnt.close();
  }

  /**
   * Es tanca el 'buffer' de escriptura del fitxer
   * @throws IOException si es produeix un error d'E/S al intentar tancar el buffer
   */
  void tancarEsc() throws IOException{
    bufferSort.close();
  }
//DISTANCIES*********************************************************************
  /** Escriu un String dada dintre l'arxiu pero no canvia de linea
  *
  * @param dada es la cadena a escriure
  *
  * @throws IOException
  *
  * @author Jose I Mateos
  * @version v.0 25/6/06
  */
  public void escriureDada(String dada) throws IOException {
	bufferSort.write(dada);
  }
  //

}
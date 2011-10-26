package jklass.nucli;
import jklass.nucli.ArbreClassif;

/**
 * Classe que té els atributs i mètodes bàsics que ha de tenir tot objecte.
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */
public class Bosque {

  /**
   * Index númeric assignat a l'objecte
   */
  //public int index; ///////////PASAR ESTOS PUBLIC A PRIVATE
  /**
   * Identificador assignat a l'objecte, que es correspon amb el nom de l'objecte.
   */
  public String proc;
  /**
   * pes que té l'objecte
   */
  public ArbreClassif[] bosquearbo = new ArbreClassif[10]; 

    
  
  Bosque(String nomproc, ArbreClassif[] arboles) {
    this.proc = nomproc;
    this.bosquearbo=arboles;
   // pes=1;
  }

 
 

 // abstract Estado clon();

  /**
   * Retorna l'index númeric associat a l'objecte
   * @return index númeric de l'objecte
   */
 // int obtenirIndex(){
  //  return index;
 // }

  /**
   * Retorna l'identificador associat a l'objecte
   * @return identificador de l'objecte
   */
  String obtenirProc(){
    return proc;
  }
  
 
}

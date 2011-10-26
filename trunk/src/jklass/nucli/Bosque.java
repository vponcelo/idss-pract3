package jklass.nucli;
import jklass.nucli.ArbreClassif;

/**
 * Classe que t� els atributs i m�todes b�sics que ha de tenir tot objecte.
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estad�stic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author M� del Mar Colillas
 * @version v.0
 */
public class Bosque {

  /**
   * Index n�meric assignat a l'objecte
   */
  //public int index; ///////////PASAR ESTOS PUBLIC A PRIVATE
  /**
   * Identificador assignat a l'objecte, que es correspon amb el nom de l'objecte.
   */
  public String proc;
  /**
   * pes que t� l'objecte
   */
  public ArbreClassif[] bosquearbo = new ArbreClassif[10]; 

    
  
  Bosque(String nomproc, ArbreClassif[] arboles) {
    this.proc = nomproc;
    this.bosquearbo=arboles;
   // pes=1;
  }

 
 

 // abstract Estado clon();

  /**
   * Retorna l'index n�meric associat a l'objecte
   * @return index n�meric de l'objecte
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

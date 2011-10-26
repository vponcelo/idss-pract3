package jklass.nucli;

/**
 * Classe que t� els atributs i m�todes b�sics que ha de tenir tot objecte.
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estad�stic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author M� del Mar Colillas
 * @version v.0
 */
public class Estado {

  /**
   * Index n�meric assignat a l'objecte
   */
  //public int index; ///////////PASAR ESTOS PUBLIC A PRIVATE
  /**
   * Identificador assignat a l'objecte, que es correspon amb el nom de l'objecte.
   */
  public String nom;
  /**
   * pes que t� l'objecte
   */
  public String [] variables;
  
  
/*  Estado(){
  index=0;
  nom = "ale";
  variables = null;
  }*/

  /**
   * Construeix un Objecte de pes unitari associant-lo amb l'index i l'identificador indicats als par�metres.
   * @param index index de l'objecte
   * @param id identificador de l'objecte
   */
 
 
//  Estado(int index, String id) {
 //   this.index = index;
  //  nom=id;
   // pes=1;
 // }

  /**
   * Construeix un Objecte amb el pes indicat als par�metre i que es podr� identificar amb l'index i l'identificador.
   * @param index index de l'objecte
   * @param id identificador de l'objecte
   * @param pes pes que t� l'objecte
   */
 // Estado(int index, String id) {
   // this.index = index;
   // nom=id;
   // this.pes=pes;
 // }
 
  Estado(String nombest, String[] lvars) {
    this.nom = nombest;
    this.variables=lvars;
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
  String obtenirNom(){
    return nom;
  }
  
  String [] obtenirVars(){
    return variables;
  }


  /**
   * Retorna el pes associat a l'objecte
   * @return pes de l'objecte
   */
 // float obtenirPes() {
   // return pes;
 // }

}

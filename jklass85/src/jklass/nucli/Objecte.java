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
abstract class Objecte {

  /**
   * Index n�meric assignat a l'objecte
   */
  private int index;
  /**
   * Identificador assignat a l'objecte, que es correspon amb el nom de l'objecte.
   */
  private String identificador;
  /**
   * pes que t� l'objecte
   */
  private float pes;

  /**
   * Construeix un Objecte de pes unitari associant-lo amb l'index i l'identificador indicats als par�metres.
   * @param index index de l'objecte
   * @param id identificador de l'objecte
   */
  Objecte(int index, String id) {
    this.index = index;
    identificador=id;
    pes=1;
  }

  /**
   * Construeix un Objecte amb el pes indicat als par�metre i que es podr� identificar amb l'index i l'identificador.
   * @param index index de l'objecte
   * @param id identificador de l'objecte
   * @param pes pes que t� l'objecte
   */
  Objecte(int index, String id, float pes) {
    this.index = index;
    identificador=id;
    this.pes=pes;
  }

  abstract Objecte clon();

  /**
   * Retorna l'index n�meric associat a l'objecte
   * @return index n�meric de l'objecte
   */
  int obtenirIndex(){
    return index;
  }

  /**
   * Retorna l'identificador associat a l'objecte
   * @return identificador de l'objecte
   */
  String obtenirId(){
    return identificador;
  }

  /**
   * Retorna el pes associat a l'objecte
   * @return pes de l'objecte
   */
  float obtenirPes() {
    return pes;
  }

}

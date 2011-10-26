package jklass.nucli;

/**
 * Classe pels objectes simples
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */
class ObjSimple extends Objecte {

  /**
   * Contructor
   * @param index index de l'objecte simple
   * @param id identificador de l'objecte simple
   */
  ObjSimple(int index, String id) {
    super(index, id);
  }

  Objecte clon() {
	return new ObjSimple(obtenirIndex(), obtenirId());
  }

}

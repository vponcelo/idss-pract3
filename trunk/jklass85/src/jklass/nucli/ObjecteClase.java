//Rober

package jklass.nucli;

import java.util.logging.*;


public class ObjecteClase extends Objecte {

  private static Logger logger = Logger.getLogger(ObjecteClase.class.getName());


  /**
   * Constructor d'un objete amb pes
   *
   * @ param indice.  Conte l'index de l'objecte
   * @ param identif.  Conte l'identificador de l'objecte
   * @ param peso.  Conte el pes de l'objecte
   * @ return
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */

  public ObjecteClase(int indice, String identif, float peso ){
    super(indice, identif, peso);
  }

  Objecte clon() {
	return new ObjecteClase(obtenirIndex(), obtenirId(), obtenirPes());
  }

}

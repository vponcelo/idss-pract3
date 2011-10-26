//ROBER

package jklass.nucli;



public class ObjecteGibert {

  /**
   * Identificador assignat a l'objecte, que es correspon amb el nom de l'objecte.
   */
  private String identificador;
  /**
   * pes que té l'objecte
   */
  private float pes;


  /**
 * Constructor d'un Objecte de pes unitari associant-lo amb l'identificador indicat al paràmetre.
 *
 * @ param id.  Conte l'identificador de l'objecte
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

  ObjecteGibert(String id) {
    identificador=id;
    pes=1;
  }


  /**
 * Constructor d'un Objecte amb pes associant-lo amb l'identificador indicat al paràmetre.
 *
 * @ param id.  Conte l'identificador de l'objecte
 * @ param pes.  Conte el pes de l'objecte
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

  ObjecteGibert(String id, float pes) {
    identificador=id;
    this.pes=pes;
  }



  /**
   * Función que modifica el pes d'un objecte.
   *
   * @ param peso.  Conte el pes que li volem assignar a l'objecte
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

  void modificarPes ( float peso) {
    pes = peso;
  }


  /**
 * Funció que retorna l'identificador associat a l'objecte
 *
 * @ param
 * @ return l'identificador de l'objecte
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p> </p>
 * @author Rober
 * CLASSIFICACIÖ
 * @version v.0
 */

  String obtenirId(){
    return identificador;
  }


  /**
 * Funció que retorna el pes associat a l'objecte
 *
 * @ param
 * @ return el pes de l'objecte
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p> </p>
 * @author Rober
 * CLASSIFICACIÖ
 * @version v.0
 */


  float obtenirPes() {
    return pes;
  }

}

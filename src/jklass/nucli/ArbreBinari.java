package jklass.nucli;

import java.util.Hashtable;
import java.util.logging.*;

/**
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estad�stic</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p> </p>
 * @author M� del Mar Colillas
 * @version v.1
 */
public class ArbreBinari {
  private static Logger logger=Logger.getLogger(ArbreBinari.class.getName());
  /**
   * arrel de l'arbre binari
   */
  protected NodeBinari arrel;
  /**
   * taula que cont� els nodes que formen l'arbre; l'etiqueta del node ser� la clau i el node el valor
   */
  protected Hashtable nodes;

  ArbreBinari(NodeBinari arrel) {
    this.arrel = arrel;
    nodes = new Hashtable();
    afegirNode(arrel);
  }
  /**
   * Afegeix el node indicat a l'arbre binari
   * @param node dades a afegir
   */
  public void afegirNode(NodeBinari node) {
    nodes.put(node.obtenirEtiqueta(), node);
  }
  /**
   * Recupera el NodeBinari corresponent a l'arrel de l'arbre binari
   * @return arrel de l'arbre binari
   */
 public NodeBinari obtenirArrel(){
    return arrel;
  }

  /**
   * Recupera el node corresponent a la clau etiq indicada
   * @param etiq clau que identifica el node a obtenir
   * @return node corresponent a la clau etiq ; null si la clau no te mapejat cap valor (cap node) a la taula.
   */
 public NodeBinari obtenirNode(String etiq){
    return (NodeBinari)nodes.get(etiq);
  }

  int obtenirNumNodes() {
    return nodes.size();
  }

  int obtenirNumFulles() {
    // tenint en compte que un arbre binari amb n fulles t� n-1 nodes interns
    // numNodes de ArbreBinari = n + n-1
    return ((nodes.size() +1)/2 );
  }
  //  ROBER
  /**
 * Funcio que modifica la arrel d'un ArbreBinari
 *
 * @ param raiz.  Te el NodeBinari que ser� la nova arrel de l'arbre
 * @ return
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estad�stic</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p> </p>
 * @author Rober
 * CLASSIFICACI�
 * @version v.0
 */

  void modificarArrel ( NodeBinari raiz ) {

    this.arrel = raiz;
    afegirNode(arrel);
  }



}

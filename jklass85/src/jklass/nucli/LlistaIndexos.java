package jklass.nucli;

import java.util.ArrayList;
import java.util.Hashtable;

/** Classes que cont� els atributs i m�todes b�sics necessaris per una llista d'indexos d'una matriu
 * <p>Title: Java-Klass</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author M� del Mar Colillas,Laia Riera Guerra
 * @version v4
 */

class LlistaIndexos {
  /**
   * Hashtable que t� com clau l'identificador de l'index (String) i com valor
   * l'index de la seva posici� a la llista (columna o fila de la matriu)
   */
  protected Hashtable taulaPos;
  /**
   * llista d'identificadors (Strings) d'indexos
   */
  protected ArrayList llista;
  /**
   * GestorMatriu de dades a la que fa refer�ncia la llista d'indexos
   */
  protected GestorMatriu m;

  /**
   * Construeix la llista d'indexos indicant la matriu a la qual indexen
   * @param mat
   */
  LlistaIndexos(GestorMatriu mat) {
    taulaPos=new Hashtable();
    llista=new ArrayList();
    m=mat;
  }

  /**
   * Obt� la taula que permet obtenir la posici� a la que apunta cada index
   * @return hashtable de les posicions on apunta cada index
   */
  Hashtable obtenirTaulaPosicions(){
    return taulaPos;
  }

  /**
   * Obtenir una llista amb tots els indexos
   * @return ArrayList amb tots els indexos
   */
  ArrayList obtenirLlista(){
    return llista;
  }

  /**
   * Obtenir longitud de la llista de indexos
   * @return
   */
 public int obtenirLong() {
    return llista.size();
  }

  /**
   * Obt� l'index de la posici� que ocupa l'identificador <code> nom </code> dins de la llista d'indexos
   * @param nom identificador de la propietat
   * @return index de la columna de la matriu
   */
  public int obtenirIndex(String nom) {
    Integer pos;
	 //  System.out.println( "voy a entrar a obtenirTaulaPosicions con nom  "+ nom );
    Hashtable taula = obtenirTaulaPosicions();
	  // System.out.println( " sali de obtenirTaula posicions " );
		  //System.out.println( " vy a entrar apos con nom "+nom );
    pos = ((Integer) taula.get(nom));
	  // System.out.println( " sali de pos con pos=  "+pos );
    if (pos == null) pos = new Integer(-1);

    return pos.intValue() ;
  }
  
///////////////////////////LAIA//////////////////////////////////////////////////
  /**
   * Obt� el GestorMatriu al que est� associat la LListaIndexos
   * @return GestorMatriu
   * @author Laia Riera Guerra
   */
  protected GestorMatriu obtenirGestorMatriu(){
	  return m;
  }
 
  /**
   * Obt� la llista d'identificadors
   * @return llista, llista d'identificadors associada al hashtable
   * @author Laia Riera Guerra
   */
  protected ArrayList getLlista() {
	return llista;
  }
  /**
   * Posa com a nova llista d'identificadors la llistat <code>llista</code>
   * @param llista, nova llista d'identificadors
   * @author Laia Riera Guerra
   */
  protected void setLlista(ArrayList llista) {
	this.llista = llista;
  }
  /**
   * Posa un nou gestor matriu al qual estar� associada la LlistaIndexos
   * @param m, nou GestorMatriu
   * @author Laia Riera Guerra
   */
  protected void setM(GestorMatriu m) throws Exception{
	this.m = m;
  }
  /**
   * Obt� la hashtable que cont� els objectes de la LListaIndexos
   * @return Hastable
   * @author Laia Riera Guerra
   */
  protected Hashtable getTaulaPos() {
	return taulaPos;
  }
  /**
   * Posa com a nova hastable la hashtable <code>taulaPos</code>
   * @param taulaPos, nova hashtable d'objectes de la LListaIndexos
   * @author Laia Riera Guerra
   */
  protected void setTaulaPos(Hashtable taulaPos) {
	this.taulaPos = taulaPos;
  }
 

  
 

}
package jklass.nucli;

import java.util.logging.*;
import java.util.StringTokenizer;

//Rober
import java.util.ArrayList;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas,Laia Riera Guerra
 * @version v.4
 */

public class MatriuDades {
  private static Logger logger=Logger.getLogger(MatriuDades.class.getName());

  /**
   * nombre de files de la matriu, es correspon amb el nombre d'objectes
   */
  private int numFiles;
  /**
   * nombre de colummnes, es correspon amb el nombre de propietats de la matriu
   */
  private int numColumnes;
  /**
   * vectors que contenen els valors de les dades associades a la matriu per
   * cada objecte i propietat
   */
  private Dada dades[][];

  private GestorMatriu gestor;

  /**
   * Constructor de la classe que crea una matriu de dades buida
   * @param nFils nombre de files
   * @param nCols nombre de columnes
   * @param gm GestorMatriu associat
   */
  MatriuDades(int nFils, int nCols, GestorMatriu gm) {
    numFiles = nFils;
    //DISTANCIES*********************************************************************
    numColumnes = nCols;
    dades = new Dada[numFiles][numColumnes];
    logger.finer("Num. Files: " + numFiles + "; Num. Colums: " + numColumnes);
    gestor = gm;
  }

  /**
   *
   * @param gm GestorMatriu associat
   * @return retorna una copia de la matriu de dades
   */
  MatriuDades obtenirCopia (GestorMatriu gm) {
    MatriuDades nova;
    int i, j;

    nova = new MatriuDades(numFiles, numColumnes, gm);
    // es copien les dades
    for (i = 0; i < numFiles; i++) {
      for (j = 0; j < numColumnes; j++) {
        nova.dades[i][j] = this.dades[i][j];
      }
    }
    return nova;
  }
//DISTANCIES*********************************************************************
 /**
   * Omple de dades la fila indicada a partir d'un StringTokenizer
   * @param fila indica la fila on colocar les dades
   * @param st conté les dades de la fila
   * @throws CreacioMatriuException
   * @throws CreacioPropietatsException
   */
  
  void omplirDades(int fila, Dada[] registre) throws CreacioPropietatsException{
//	   LlistaPropietats propietats = gestor.obtenirLlistaProps();
	   for (int i = 0; i< registre.length; i++) {
		   dades[fila][i]= registre[i].clon();
			 logger.finer("Valor: " + dades[fila][i]);
			 //propietats.nouValor(i, (String)dades[fila][i].obtenirValor());
		 }
  }

  void omplirDades(int fila, Dada[] registre, int skipProp) throws CreacioPropietatsException{
//	   LlistaPropietats propietats = gestor.obtenirLlistaProps();
	  for (int i = 0; i< registre.length; i++) {
		if (i < skipProp) {
		   dades[fila][i]= registre[i].clon();
		   logger.finer("Valor: " + dades[fila][i]);
		} else if (i > skipProp) {
		   dades[fila][i-1]= registre[i].clon();
		   logger.finer("Valor: " + dades[fila][i-1]);
		}
		//propietats.nouValor(i, (String)dades[fila][i].obtenirValor());
	  }
  }

  
  /**
   * Funcio que omple les dades de una fila en la Matriu Actual
   *
   * @ param fila.  Indica el número de fila de la matriu on s'inseriran les dades
   * @ param st.  Objecte que conte les dades de la fila indicada
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


  void omplirDades(int fila, StringTokenizer st) throws CreacioMatriuException, CreacioPropietatsException{
    int j;
    String token;
    float peso;

    if (st.countTokens() < numColumnes  ) {
      throw new CreacioMatriuException("Falten dades per alguna propietat de la matriu.");
    }
    j = 0;
    peso = gestor.obtenirLlistaObjs().obtenirObjecte(fila).obtenirPes();
    token = st.nextToken();

    if ( token.length() > 2 ) {
      char c1 = token.charAt(0);
      char c2 = token.charAt(1);
      String aux = new String();
      if ((c1 == '(')&&(c2 == '(')) {
        for ( int i = 2; i < token.length(); i ++ ) {
          c1 = token.charAt(i);
          aux = aux + c1;
        }
        token = aux;
        omplirDada ( fila, 0, token, peso );
        j++;
      }
    }

    while (j < numColumnes) {
      token = st.nextToken();
      omplirDada ( fila, j, token, peso );
      j++;
    }
  }


  /**
   * Funcio que omple les dades de una casella en la Matriu Actual
   *
   * @ param fila.  Indica el número de fila de la matriu on s'inseriran les dades
   * @ param columna.  Indica el número de columna de la matriu on s'inseriran les dades
   * @ param st.  Objecte que conte les dades de la fila i columna indicada
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



  void omplirDada ( int fila, int columna, String dato, float peso )  throws CreacioMatriuException, CreacioPropietatsException{

    String aux = new String();
    ArrayList lista;
    ObjecteGibert obj ;
    float p;
    char c1, c2;
    String s1;
    int pes, longitud;
    LlistaPropietats propietats;

    if ( dato.length() < 2 ) {
      c1 = dato.charAt(0);
      c2 = c1;
    }
    else {
      c1 = dato.charAt(0);
      c2 = dato.charAt(1);
    }
    propietats = gestor.obtenirLlistaProps();
    if ((c1 == '(')&&(c2 == '(')) {
      //dades[fila][columna]= new Dada(new String(dato));
      modificarDada(fila,columna, new Dada(new String(dato)));

      lista = gestor.obtenirListaGibert(dato, peso);
      for ( int i = 0; i < lista.size(); i ++ ) {
        obj = (ObjecteGibert)lista.get(i);
        s1 = obj.obtenirId();
        p = obj.obtenirPes();
        pes = (int) (peso * p) ;
        propietats.nouValor(columna,s1,pes);

      }
    }

    else {
      if ( columna == numColumnes - 1 ) {
         longitud = dato.length();
         if ( longitud >= 2 ) {
           for ( int i = 0; i < longitud - 2; i ++ ) {
             c1 = dato.charAt(i);
             aux = aux + c1;
           }
           c1 = dato.charAt(longitud - 2);
           c2 = dato.charAt(longitud - 1);

           if ((c1 == ')')&&(c2 == ')')) {
             dato = aux;
           }
         }
       }

       propietats.nouValor(columna,dato,peso);
       //dades[fila][columna]= new Dada(new String(dato));
       modificarDada(fila,columna, new Dada(new String(dato)));

     }
   }


  /**
   * Amplia la matriu de dades afegint el nombre de files i columnes indicats buides
   * @param nFiles nombre de files a afegir
   * @param nCols nombre de columnes a afegir
   */
  void ampliar(int nFiles, int nCols){
    Dada nouDades[][];
    int i , j;

    nouDades = new Dada[numFiles + nFiles][numColumnes + nCols];
    // es copien les dades existents
    for (i = 0; i < numFiles; i++) {
      for (j = 0; j < numColumnes; j++) {
        nouDades[i][j] = this.dades[i][j];
      }
    }
    dades = nouDades;
    numFiles += nFiles;
    numColumnes += nCols;
  }
  
  /**
   * Redueix la matriu de dades elinant la darrera columna
   */
  void eliminarDarreraColumna(){
    Dada[][] nouDades = new Dada[numFiles][--numColumnes];
    for (int i = 0; i < numFiles; i++) {
        for (int j = 0; j < numColumnes; j++) {
          nouDades[i][j] = this.dades[i][j];
        }
    }
    dades = null; // Ens avancem al Garbage Collector?
    dades = nouDades;
  }

  int obtenirNumFiles() {
    return numFiles;
  }

  int obtenirNumColumnes() {
	  //DISTANCIES*********************************************************************
    return numColumnes;
  }

  /**
   * Obté les dades de la columna de la propietat indicada per index
   * @param index index de la columna de la matriu
   * @return vector amb les dades de la columna de l'index especificat
   */
  Dada[] obtenirColumna(int index) {
    int i;
    Dada[] columna = new Dada[numFiles];

    for (i = 0; i < numFiles; i++) {
      columna[i] = dades[i][index];
    }
    return columna;
  }

  /**
   * Obté les dades de la fila del objecte indicat per index
   * @param index index de la fila de la matriu
   * @return vector amb les dades de la fila de l'index especificat
   */
  Dada[] obtenirFila(int index) {
    int j;
    Dada[] fila = new Dada[numColumnes];

    for (j = 0; j < numColumnes; j++) {
      fila[j] = dades[index][j];
    }
    return fila;
  }


  /**
   * Obté el contingut de la posicio (fila,columna) de la matriu de dades
   * @param fila index de la fila
   * @param columna index de la columna
   * @return Dada de la posició indicada
   */
  Dada obtenirDada(int fila, int columna) {
    return dades[fila][columna];
  }

  /**
   * Actualitza la dada de la posició (fila,columna) de la matriu de dades
   * @param fila index de la fila
   * @param columna index de la columna
   * @param dada nou contingut a posar a la posició indicada
   */
  void modificarDada(int fila, int columna, Dada dada) {
    dades[fila][columna] = dada;
  }

  /**
   * Obté la colecció de valors com strings de la fila indicada
   * @param i index de la fila
   * @return array amb els valors de les dades de la fila
   */
  String[] obtenirArrayFila(int i){
    String[] fila = new String[numColumnes];

    for (int j=0; j < numColumnes; j++){
      fila[j] = dades[i][j].obtenirValor().toString();
    }

    return fila;
  }

  /**
   *  Obté la colecció de valors com strings de la fila indicada per index, però
   * reordenats segons l'ordre indicat a la llista.
   * @param index index de la fila de la matriu
   * @param nouOrdre llista ordenada dels indexos de les columnes actuals
   * @return vector amb els valors de la fila de l'index especificat seguint
   * l'ordre indicat a la llista ordenada nouOrdre
   */
  String[] obtenirArrayFilaReordenada(int index, int[] nouOrdre) {
    String[] fila = new String[numColumnes];

    for (int j = 0; j < numColumnes; j++) {
      fila[j] = dades[index][nouOrdre[j]].obtenirValor().toString();
    }
    return fila;
  }
  
///////////////////////////////////LAIA//////////////////////////////////
/**
 * Obté una tupla amb els valors de la matriu de dades actual
 * @return Object[][] amb els valors de la matriu de dades
 * @author Laia Riera Guerra
 */
public Object[][] obtenirMatriu(){
	 Object[][] resultat=new Object[this.numFiles][this.numColumnes];
	  for(int i=0;i<this.numFiles;i++){
		  for(int j=0;j<this.numColumnes;j++){
			  resultat[i][j]=(Object)dades[i][j].obtenirValor();
		  }
	  }
	  return resultat;
}
/**
 * Elimina les últimes <code>nCols</code> de la matriu de dades actual
 * @param nCols, número de columnes a eliminar.
 * @author Laia Riera Guerra
 */
public void reduir(int nCols){
	  Dada nouDades[][];
	    int i , j;

	    nouDades = new Dada[numFiles][numColumnes-nCols];
	    // es copien les dades existents
	    for (i = 0; i < numFiles; i++) {
	      for (j = 0; j < numColumnes-nCols; j++) {
	        nouDades[i][j] = this.dades[i][j];
	      }
	    }
	    dades = nouDades;
	    numColumnes =numColumnes-nCols;
}

/**
 * Elimina la columna que ocupa la posició dades[][<code>iCol</code>]
 * @param iCol, número de la columna a eliminar
 * @author Laia Riera
 */
 void eliminar(int iCol){
   Dada nouDades[][];
   int i , j;
   nouDades = new Dada[numFiles][numColumnes - 1];
   // es copien les dades existents
   for (i = 0; i < numFiles; i++) {
     for (j = 0; j < numColumnes; j++) {
   	  if(j>=iCol){
   		  if(j>iCol){
   			  nouDades[i][j-1]=this.dades[i][j];
   		  }   		  
   	  }else{
   		  nouDades[i][j] = this.dades[i][j];
   	  }        
     }
   }
   dades = nouDades;    
   numColumnes = numColumnes-1;
 }


}

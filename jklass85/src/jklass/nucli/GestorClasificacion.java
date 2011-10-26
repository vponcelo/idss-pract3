package jklass.nucli;

import java.util.logging.*;
import java.lang.Double;
import java.util.ArrayList;
import java.util.Stack;
import java.io.FileNotFoundException;
import java.io.IOException;
import jklass.util.OpcionsDis;
import java.util.Iterator;
import java.util.Vector;
import java.util.HashMap; 
/**
 * Classe que permet accedir a tota la funcionalitat per fer la classificacio dels objectes de Klass
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p> </p>
 * @author Rober
 * CLASSIFICACIÖ
 * @version v.0
 */


public class GestorClasificacion {

  private static Logger logger = Logger.getLogger(GestorClasificacion.class.getName());

  /**
   * GestorMatriu que s'utilitza per fer la classificacio.
   */
  private GestorMatriu gesMatri;

  private LlistaObjectes objectesAux;

  /**
   * Matriu de Dades on es guardaran les dades de les classes que es vagin Agregant.
   */
  private MatriuDades dadesClas;

  /**
   * Matriu on es guardaran les distancies dels objectes entre si.
   */
  private Double [][] clasifica;

  /**
   * Indica el número de objectes que te el GestorMatriu.
   * Quan cerquem un objete si te un index superior o igual a aquest paràmetre voldrá dir
   * que es una classe formada per la agregació de dos objectes simples ( tenen un index
   * inferior a aquet parametre.
   */
  private int numObjetos;

  /**
   * Arbre utilitzat per guardar l'ordre de la classificació.  Cada clase estará formada per un
   * NodeBinari on es guardara quin son els objectes que s'han utilitzat per crear la classe.
   */
  private ArbreClassif arbol;
  
  private Bosque bos;

  /**
   * Vector que ens indica si un determinat objecte o clase ha calculat la distancia entre ell
   * i la resta de objectes o classes que estiguin a la llista de Objectes.
   */
  //private boolean [] visitados;

  /**
   * Vector que ens indica si un determinat objecte o clase ha sigut agregat com a fill d'una
   * altre classe.
   */
  private boolean [] agregados;

  /**
   * Prefixe que utilitzarem per anomenar a una nova classe creada.
   */
  private String idClasse;

  /**
   * Prefixe que utilitzarem per anomenar al fitxer ( *.his ) on guardarem la classificació.
   */
  private String ficheroResultadosHis;
  /**
   * Prefixe que utilitzarem per anomenar al fitxer ( *.drm ) on guardarem la classificació.
   */
  private String ficheroResultadosDrm;

private HashMap info = new HashMap();//ale
private int nro=0;//ale
private int susa=0;// ale

  /**
   * Constructor
   *
   * @ param gm.  GestorMatriu que s'utilitza per fer la classificacio.
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
  GestorClasificacion (GestorMatriu gm) {
    int filas, columnas;
    NodeBinari arrel;
    NodeBinari nodeAux;
    String [] lista;
    String identificador;
    String raiz;

    //gesMatri = gm.obtenirCopia(gm.obtenirNomMatriu(), gm.obtenirId());
    gesMatri = gm;
    idClasse = "Classe";

    objectesAux = gesMatri.obtenirLlistaObjs().obtenirCopia(gm);

    filas = gesMatri.obtenirLlistaObjs().obtenirLong();
    columnas = gesMatri.obtenirLlistaProps().obtenirLong();
    dadesClas = new MatriuDades (filas,columnas,gesMatri);
    numObjetos = filas;

    // quiza solo inicialize las filas para tener matriz triangular
    clasifica = new Double [2*numObjetos-1][2*numObjetos-1];
    //visitados = new boolean [2*numObjetos-1];
    agregados = new boolean [2*numObjetos-1];

    for (int i = 0; i< 2*numObjetos-1; i++) {
      //visitados [i] = false;
      agregados [i] = false;
    }

    raiz = idClasse + Integer.toString(numObjetos-1);

    arrel = new NodeBinari ( raiz );
    arbol = new ArbreClassif ( arrel, gesMatri.obtenirNomMatriuRelatiu() ,idClasse );

    lista = gesMatri.obtenirLlistaObjs().llistarIDsObjs();

    for ( int i=0; i<numObjetos; i++ ) {
      identificador = lista[i];
      nodeAux = new NodeBinari (identificador);
      arbol.afegirNode(nodeAux);
    }
    ficheroResultadosHis = gesMatri.obtenirNomMatriu();
	 // System.out.println("HOLAAA ESTOY EN GestorClasificacion "+ gesMatri.obtenirNomMatriu());

    ficheroResultadosDrm = gesMatri.obtenirNomMatriu();
  }


 public void modifIdClasse ( String identif ) {
   idClasse = identif;
 }

 public void modifFicheroResultadosHis ( String archivo ) {
     ficheroResultadosHis = archivo;
 }

 public void modifFicheroResultadosDrm ( String archivo ) {
   ficheroResultadosDrm = archivo;
 }

  public GestorMatriu obtenirGesMatri(){
    return gesMatri;
  }

  public LlistaObjectes obtenirObjectesAux() {
    return objectesAux;
  }


  public boolean [] obtenirAgregados(){
    return agregados;
  }

  public int obtenirNumObjetos(){
    return numObjetos;
  }

  public ArbreClassif obtenirArbol(){
    return arbol;
  }




  /**
   * Funció que retorna l'index d'un objecte.
   *
   * @ param identificador.  Nom del objecte.
   * @ return int amb l'index del objecte.
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */
  public int obtenirIndex ( String identificador) {
    int indice;

    indice = obtenirObjectesAux().obtenirIndex(identificador);
    return indice;
  }

  /**
   * Funció que retorna un objecte.
   *
   * @ param index.  Index del objecte.
   * @ return objecte.
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */
  public Objecte obtenirObjecte ( int index ) {
    Objecte obj;

    obj = obtenirObjectesAux().obtenirObjecte(index);
    return obj;
  }

  /**
   * Funció que retorna un objecte.
   *
   * @ param identificador.  Nom del objecte.
   * @ return objecte.
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */
  public Objecte obtenirObjecte ( String identificador ) {
    Objecte obj;
    int index;

    index = obtenirObjectesAux().obtenirIndex(identificador);
    obj = obtenirObjecte(index);
    return obj;
  }

  /**
   * Funció que retorna el nom d'un objecte.
   *
   * @ param obj.  Objecte del cual volem obtenir el nom.
   * @ return String que conte el nom del objecte.
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */
  public String obtenirIdentificador ( Objecte obj) {
    String identificador;

    identificador = obj.obtenirId();
    return identificador;
  }

  /**
   * Funció que retorna el nom d'un objecte.
   *
   * @ param index.  Index del objecte del cual volem obtenir el nom.
   * @ return String que conte el nom del objecte.
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */
  public String obtenirIdentificador ( int index) {
    String identificador;
    Objecte obj;

    obj = obtenirObjecte ( index);
    identificador = obj.obtenirId();
    return identificador;
  }

  /**
   * Funció que retorna el pes d'un objecte.
   *
   * @ param identificador.  Nom del objecte del cual volem obtenir el pes.
   * @ return float que conte el pes del objecte.
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */
  public float obtenirPes ( String identificador ) {
    float pes;
    Objecte obj;

    obj = obtenirObjecte(identificador);
    pes = obj.obtenirPes();
    return pes;
  }

  /**
   * Funció que retorna la llista de Objectes com un vector de Strings
   *
   * @ param
   * @ return String [] on es trobaran els noms dels objectes
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */
  public String [] obtenirListaObj ( ) {
    String [] lista;

    lista = obtenirObjectesAux().llistarIDsObjs();
    return lista;
  }



  /**
   * Funció que retorna la fila de la matriu de dades on es troben les dades del objecte.
   * Si l'index es igual o superior al parametre "numObjectes" caldrá cercarlo a la matriu
   * de dades "dadesClas" que es un paràmetre de aquesta classe.  Si fos inferior ho
   * trobarem a la matriu de dades del GestorMatriu
   *
   * @ param identificador.  Nom del objecte del cual volem obtenir fila de dades.
   * @ return Dada [] que conte els valors "Dada" del objecte pasat per paràmetre.
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */
  public Dada [] obtenirFilaDada ( String identificador ){
    Dada [] dad;
    int indice;

    indice = obtenirIndex ( identificador );
    dad = new Dada [obtenirGesMatri().obtenirLlistaProps().obtenirLong()];
    if ( indice < numObjetos ) {
       dad = obtenirGesMatri().obtenirDades().obtenirFila(indice);
    }
    else {
      dad = dadesClas.obtenirFila(indice-numObjetos);
    }

    return dad;
  }

  /**
   * Funció igual que l'anterior pero retornant els objectes pasant-los a Strings
   *
   * @ param identificador.  Nom del objecte del cual volem obtenir fila de dades.
   * @ return String [] que conte els valors de les dades del objecte pasat per paràmetre.
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */
  public String [] obtenirFila ( String identificador ){
    String[] lista;
    Dada [] dad;
    Object obj;

    dad = obtenirFilaDada (identificador);
    lista = new String [dad.length];
    for ( int i=0; i<lista.length; i++ ) {
      obj = dad[i].obtenirValor();
      lista[i] = (String)obj.toString();

    }

    return lista;
  }

  /**
   * Funcio que retorna els identificadors de les propietats
   *
   * @ param
   * @ return String [] que conte els identificadors de les propietats
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */
  public String [] obtenirListaPropiedades ( ) {
    String [] lista;
    int longitud;

    longitud = obtenirGesMatri().obtenirLlistaProps().obtenirLong();
    lista = new String [longitud];
    for ( int i=0; i<longitud; i++ ) {
      lista[i] = obtenirGesMatri().obtenirLlistaProps().obtenirPropietat(i).obtenirId();
    }

    return lista;
  }

  /**
   * Funció que retorna el fill esquerre d'un objecte.
   * Si l'objecte ha estat agregat com a classe ens retornará el valor del seu fill esquerre.
   * Si l'objecte es simple ens retornará "NULL".
   *
   * @ param identificador.  Nom del objecte.
   * @ return String que conte l'identificador del fill esquerre.
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */
  public String obtenirFillEsquerre ( String identificador){
    String fillEsq = null;
    NodeBinari nodeAux, nodeClase;

    nodeClase = arbol.obtenirNode(identificador);
    if ( nodeClase != null) {
      nodeAux = nodeClase.obtenirFillEsq();
      if (nodeAux == null ){
        fillEsq = "NULL";
      }
      else {
        fillEsq = nodeAux.obtenirEtiqueta();
      }
    }

    return fillEsq;
  }

  /**
   * Funció que retorna el fill dret d'un objecte.
   * Si el objecte ha estat agregat com a classe ens retornará el valor del seu fill dret.
   * Si el objecte es simple ens retornará "NULL".
   *
   * @ param identificador.  Nom del objecte.
   * @ return String que conte l'identificador del fill dret.
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */
  public String obtenirFillDret ( String identificador){
    String fillDret = null;
    NodeBinari nodeAux, nodeClase;

    nodeClase = arbol.obtenirNode(identificador);
    if ( nodeClase != null) {
      nodeAux = nodeClase.obtenirFillDret();
      if (nodeAux == null ){
        fillDret = "NULL";
      }
      else {
        fillDret = nodeAux.obtenirEtiqueta();
      }
    }

    return fillDret;
  }

  /**
   * Funció que comproba que no es puguin repetir els identificadors de les classes, si cal
   * modificarà l'identificador.  No poden haber dos objectes o classes amb el mateix identificador.
   *
   * @ param id.  Nom del parámetre "idClasse".
   * @ param identif.  Index de la nova classe traduit a String.
   * @ return String que conte el nou identificador de la classe sense que hagi repetits.
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */
  /*
  public String comprobarClase (String id, String identif) {
    String identificador;
    int s = 0;
    int contX = 0;

    while ( 1 == 1) {
      identificador = id;
      if ( contX > 0 ) {
        for ( int i = 0; i < contX; i++ ) {
          identificador = identificador + "X";
        }
      }

      identificador = identificador + identif;
      s = obtenirIndex ( identificador );
      if ( s < 0 ) {
        return identificador;
      }
      else {
        contX ++;
      }
    }
  }

*/

  /**
   * Funció que comproba que no es puguin repetir els identificadors de les classes.
   * No poden haber dos objectes o classes amb el mateix identificador.
   *
   * @ param identificador.  Index de la nova classe traduit a String.
   * @ return boolean que indica si el nou identificador de la classe es valid per que no hi hagin repetits.
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */

  public boolean comprobarIdClase ( String identificador ) {
      String objeto;
      boolean idCorrecto, objCorrecto;
      int lonIdent, lonObj;
      char c1, c2;
      LlistaObjectes llObj;

      idCorrecto = true;
      objCorrecto= false;
      lonIdent = identificador.length();
      llObj = obtenirObjectesAux();

      for (int i = 0; i < numObjetos; i++) {
         if ( idCorrecto ) {
           objeto = llObj.obtenirObjecte(i).obtenirId();
           lonObj = objeto.length();
           if ( lonIdent > lonObj) {
             objCorrecto = true;
           }
           else {
             for ( int j = 0; j < lonIdent; j++ ){
               if ( ! objCorrecto ) {
                 c1 = identificador.charAt(j);
                 c2 = objeto.charAt(j);
                 if ( c1 != c2 ) {
                   objCorrecto = true;
                 }
               }
             }
           }
           if ( objCorrecto ) {
             objCorrecto = false;
           }
           else {
             idCorrecto = false;
           }
         }

      }
    if ( idCorrecto ) {
        modifIdClasse ( identificador );
      }
      return idCorrecto;
    }

  /**
   * Funció que fa la agregacio de dos objectes agrupantlos en una classe.
   *
   * @ param id.  Nom del parámetre "idClasse".
   * @ param tipus.  "Gibert" y "Moda".
   * @ param identif1.  Indentirficador del primer objecte a agregar.
   * @ param identif2.  Indentirficador del segon objecte a agregar.
   * @ param pon.  Distancia entre els dos objectes a agregar.
   * @ param comprobar.  Indica si cal comprobar que no es repeteixi l'identificador per la nova classe.
   * @ return String que conte el nou identificador de la classe sense que hagi repetits.
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */
  public String agregarClase( String tipus, String identif1, String identif2, double pon ){
    int indice,ind1,ind2;
    float peso1, peso2;
    String identif;
    String[] fila1, fila2;
    NodeBinari nodeAux, nodeFillEsq, nodeFillDret;

    ind1 = obtenirIndex(identif1);
    peso1 = obtenirPes (identif1);

    ind2 = obtenirIndex(identif2);
    peso2 = obtenirPes (identif2);

    indice = obtenirObjectesAux().obtenirLong();

    identif = new String ((Integer.toString(indice-numObjetos)));
    identif = idClasse + identif;
    objectesAux.afegirObjecteClase( indice, identif, (peso1+peso2));

    fila1 = new String [ obtenirGesMatri().obtenirLlistaProps().obtenirLong()];
    fila2 = new String [ fila1.length];

    fila1 = obtenirFila(identif1);
    fila2 = obtenirFila(identif2);

    rellenarFilaDades(fila1, fila2, tipus, peso1, peso2, indice);

    agregados[ind1] = true;
    agregados[ind2] = true;

    nodeFillEsq = arbol.obtenirNode(identif1);
    nodeFillDret = arbol.obtenirNode(identif2);
    nodeAux = new NodeBinari ( identif, pon, nodeFillEsq, nodeFillDret);

    if ( indice == 2*numObjetos-2){
      arbol.modificarArrel(nodeAux);

      String identifArrel = nodeAux.obtenirEtiqueta();
      float pesArrel = obtenirPes(identifArrel);
      Dada [] dadesArrel = obtenirFilaDada (identifArrel);

      arbol.modificarPesArrel (pesArrel);
      arbol.modificarFilaDades (dadesArrel);
		//arbol.modificarEst(est);
      obtenirGesMatri().agregarArbol(arbol);
    }
    else {
      arbol.afegirNode(nodeAux);

    }

    return identif;
  }
  
  
  
  
  public String agregarClaseEst(String est, String tipus, String identif1, String identif2, double pon ){
    int indice,ind1,ind2;
    float peso1, peso2;
    String identif;
    String[] fila1, fila2;
    NodeBinari nodeAux, nodeFillEsq, nodeFillDret;
	 String [] estad; 
      
    ind1 = obtenirIndex(identif1);
    peso1 = obtenirPes (identif1);

    ind2 = obtenirIndex(identif2);
    peso2 = obtenirPes (identif2);

    indice = obtenirObjectesAux().obtenirLong();

    identif = new String ((Integer.toString(indice-numObjetos)));
    identif = idClasse + identif;
    objectesAux.afegirObjecteClase( indice, identif, (peso1+peso2));

    fila1 = new String [ obtenirGesMatri().obtenirLlistaProps().obtenirLong()];
    fila2 = new String [ fila1.length];

    fila1 = obtenirFila(identif1);
    fila2 = obtenirFila(identif2);

    rellenarFilaDades(fila1, fila2, tipus, peso1, peso2, indice);

    agregados[ind1] = true;
    agregados[ind2] = true;

    nodeFillEsq = arbol.obtenirNode(identif1);
    nodeFillDret = arbol.obtenirNode(identif2);
    nodeAux = new NodeBinari ( identif, pon, nodeFillEsq, nodeFillDret);

    if ( indice == 2*numObjetos-2){
      arbol.modificarArrel(nodeAux);

      String identifArrel = nodeAux.obtenirEtiqueta();
      float pesArrel = obtenirPes(identifArrel);
      Dada [] dadesArrel = obtenirFilaDada (identifArrel);

      arbol.modificarPesArrel (pesArrel);
      arbol.modificarFilaDades (dadesArrel);
	//	arbol.modificarEst(est);
		
      obtenirGesMatri().agregarArbol(arbol);
		nro++;
		System.out.println( "A ver arboles por favor?"+nro);
   	info.put(est,(ArbreClassif)arbol);
		
    }
    else {
      arbol.afegirNode(nodeAux);
		

    }
	 ////////Aca generar el bosque con el map para cada estado y arbol 
	susa= obtenirGesMatri().obtenirLlArbres().size();
     System.out.println( "A ver el tamaño del map"+info.size());
	  System.out.println( "A ver el tamaño de la lista de arboles"+susa  );
	  System.out.println( "A ver el estado"+est  );
    return identif;
  }

  
  
  
  
  

  /**
   * Funció que càlcula y fica les dades obtingudes de la fusió de dos objectes al formar una
   * nova classe.
   *
   * @ param fila1.  Dades del primer objecte a agregar.
   * @ param fila2.  Dades del segon objecte a agregar.
   * @ param tipus.  "Gibert" y "Moda".
   * @ param peso1.  Pes del primer objecte a agregar.
   * @ param peso2.  Pes del segon objecte a agregar.
   * @ param indice.  Indica l'index per la nova classe.
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
  void rellenarFilaDades (String [] fila1, String [] fila2, String tipus, float peso1, float peso2, int indice) {
    double d1, d2, d3;
    String dad1, dad2;
    Dada dada3;
    Propietat pro;
    ArrayList llistaProps = obtenirGesMatri().obtenirLlistaProps().obtenirLlista();

    for (int j = 0; j < obtenirGesMatri().obtenirLlistaProps().obtenirLong(); j++) {
      dad1 = fila1[j];
      dad2 = fila2[j];

      pro = (Propietat) llistaProps.get(j);

      if ( dad1.compareTo("?") == 0 && dad2.compareTo("?") == 0  )  {
        dada3 = new Dada ( "?");
      }
      else if ( dad2.compareTo("?") == 0)  {
        dada3 = new Dada ( dad1);
      }
      else if ( dad1.compareTo("?") == 0)  {
        dada3 = new Dada ( dad2);
      }
       else if (pro instanceof PropNumerica) {
         d1 = Double.parseDouble(dad1);
         d2 = Double.parseDouble(dad2);
         d3 = (d1 * peso1 + d2 * peso2) / (peso1 + peso2);
         dada3 = new Dada(new String( (Double.toString(d3))));

      }
       else {
         if ( tipus == "Moda" ) {
           dada3 = obtenirDadaModa(dad1,dad2,peso1,peso2);

         }
         else {
           dada3 = obtenirDadaGibert(dad1,dad2,peso1,peso2);
         }
       }

       dadesClas.modificarDada(indice - numObjetos, j, dada3);
     }
   }

   /**
    * Funció que agafa dues Dades de dos objectes diferents y selecciona la Moda.
    *
    * @ param dad1.  String que conté el valor de la dada del primer objecte.
    * @ param dad2.  String que conté el valor de la dada del segon objecte.
    * @ param pes1.  Float que conté el pes del primer objecte.
    * @ param pes1.  Float que conté el pes del segon objecte.
    * @ return Dada que conte la nova Dada.
    *
    * <p>Title: Java-KLASS</p>
    * <p>Description: Paquet estadístic</p>
    * <p>Copyright: Copyright (c) 2006</p>
    * <p> </p>
    * @author Rober
    * CLASSIFICACIÖ
    * @version v.0
    */

   public Dada obtenirDadaModa (String dad1, String dad2, float pes1, float pes2){
     Dada dada;
     char c1;
     ArrayList datos1 = new ArrayList();
     ObjecteGibert obj1;
     float p, pes = 0;
     String dato="",datoObjeto;

     c1 = dad1.charAt(0);
     if (c1 == '(') {
       datos1 = obtenirListaGibert(dad1);
       for ( int i = 0; i < datos1.size(); i ++ ) {
         obj1 = (ObjecteGibert)datos1.get(i);
         datoObjeto = obj1.obtenirId();
         p = obj1.obtenirPes()*pes1;

         if ( p > pes ) {
           dato = datoObjeto;
           pes = p;
         }
       }
     }
     else {
       if ( pes1 > pes ) {
         dato = dad1;
         pes = pes1;
       }
     }

     c1 = dad2.charAt(0);
     if (c1 == '(') {
       datos1 = obtenirListaGibert(dad2);
       for ( int i = 0; i < datos1.size(); i ++ ) {
         obj1 = (ObjecteGibert)datos1.get(i);
         datoObjeto = obj1.obtenirId();
         p = obj1.obtenirPes()*pes2;

         if ( p > pes ) {
           dato = datoObjeto;
           pes = p;
         }
       }
     }
     else {
       if ( pes2 > pes ) {
         dato = dad2;
         pes = pes2;
       }
     }

     dada = new Dada ( dato );
     return dada;
   }


   /**
    * Funció que agafa dues Dades de dos objectes diferents y els posa en format Dada estesa.
    *
    * @ param dad1.  String que conté el valor de la dada del primer objecte.
    * @ param dad2.  String que conté el valor de la dada del segon objecte.
    * @ param pes1.  Float que conté el pes del primer objecte.
    * @ param pes1.  Float que conté el pes del segon objecte.
    * @ return Dada que conte la Dada extesa.
    *
    * <p>Title: Java-KLASS</p>
    * <p>Description: Paquet estadístic</p>
    * <p>Copyright: Copyright (c) 2006</p>
    * <p> </p>
    * @author Rober
    * CLASSIFICACIÖ
    * @version v.0
    */
   public Dada obtenirDadaGibert (String dad1, String dad2, float pes1, float pes2){
     Dada dada;
     char c1,c2;
     ArrayList datos1 = new ArrayList();
     ArrayList datos2 = new ArrayList();
     ObjecteGibert obj1, obj2;

     if (dad1.compareTo(dad2) == 0) {
       dada = new Dada(dad1);
     }
     else {
       c1 = dad1.charAt(0);
       if (c1 == '(') {
         // caracter compuesto obtenerlos todos
         datos1 = obtenirListaGibert(dad1);
       }
       else {
         obj1 = new ObjecteGibert(dad1);
         datos1.add(obj1);
       }

       c2 = dad2.charAt(0);
       if (c2 == '(') {
         // caracter compuesto obtenerlos todos
         datos2 = obtenirListaGibert(dad2);
       }
       else {
         obj2 = new ObjecteGibert(dad2);
         datos2.add(obj2);
       }

       dada = obtenirDadaG(datos1, datos2, pes1, pes2);

     }

     return dada;
   }

   /**
    * Funció que agafa una "Dada" extesa y la descomposa en un ArrayList.
    * El Array estará composat de Objectes Gibert, objectes amb identificador y pes???.
    *
    * @ param nom.  Dada en format extes.
    * @ param pes.  Pes del Objecte que conte la Dada.
    * @ return ArrayList que conte la Dada extesa descomposada en objectes amb identificador y pes.
    *
    * <p>Title: Java-KLASS</p>
    * <p>Description: Paquet estadístic</p>
    * <p>Copyright: Copyright (c) 2006</p>
    * <p> </p>
    * @author Rober
    * CLASSIFICACIÖ
    * @version v.0
    */
   public ArrayList obtenirListaGibert ( String nom ) {
     float p;
     char c;
     int parentesis = 0;
     String s1 = new String();
     String s2 = new String();
     ArrayList lista = new ArrayList();
     boolean finString = false;
     ObjecteGibert obj;

     for (int i = 0; i < nom.length(); i++) {
       c = nom.charAt(i);

       switch (c) {
         case '(':
           s1 = new String();
           s2 = new String();
           parentesis ++;
           break;

         case ',':
           finString = true;
           break;

         case ')':
           if ( parentesis == 2 ) {
             p = Float.parseFloat(s2);
             obj = new ObjecteGibert(s1, p);
             lista.add(obj);
             parentesis--;
             finString = false;
           }
           break;

         default:
           if ( parentesis == 2 ) {
             if ( ! finString ) {
               s1 = s1 + c;
             }
             else {
               s2 = s2 + c;
             }
           }
           break;
       }
     }

     return lista;
   }


   /**
    * Funció que agafa dos ArrayList amb objectes Gibert de dos objectes diferents
    * els ordena alfabeticament y els converteix en format "Dada" extesa.
    *
    * @ param a1.  ArrayList que conté els diferents objectes que composan la dada extesa del primer objecte.
    * @ param a2.  ArrayList que conté els diferents objectes que composan la dada extesa del segon objecte.
    * @ param pes1.  Float que conté el pes del primer objecte.
    * @ param pes2.  Float que conté el pes del segon objecte.
    * @ return Dada que conte la Dada extesa.
    *
    * <p>Title: Java-KLASS</p>
    * <p>Description: Paquet estadístic</p>
    * <p>Copyright: Copyright (c) 2006</p>
    * <p> </p>
    * @author Rober
    * CLASSIFICACIÖ
    * @version v.0
    */
   public Dada obtenirDadaG ( ArrayList a1, ArrayList a2, float pes1, float pes2){
     String s1, s2, s3;
     int tam1, tam2, i, j;
     float p1, p2;
     ObjecteGibert obj1, obj2;
     ArrayList lista = new ArrayList();
     Dada d;

     tam1 = a1.size();
     tam2 = a2.size();
     i = 0;
     j = 0;

     while (i < tam1 && j < tam2 ) {
       obj1 = ( ObjecteGibert ) a1.get(i);
       s1 = obj1.obtenirId();
       p1 = obj1.obtenirPes();

       obj2 = ( ObjecteGibert ) a2.get(j);
       s2 = obj2.obtenirId();
       p2 = obj2.obtenirPes();

       if ( s1.compareTo(s2) > 0  ) {
         obj2.modificarPes(p2*pes2/(pes2+pes1));
         lista.add(obj2);
         j++;
       }
       else if ( s1.compareTo(s2) < 0  ){
         obj1.modificarPes(p1*pes1/(pes1+pes2));
         lista.add(obj1);
         i++;
       }
       else if ( s1.compareTo(s2) == 0  ){
       obj1.modificarPes((p1*pes1+p2*pes2)/(pes1+pes2));
       lista.add(obj1);
       i++;
       j++;
     }
   }

   if (i >= tam1 && j < tam2) {
     // El vector izquierda ya se ha copiado completamente.
     //resto = derecha;
     //indiceResto = indiceDerecha;
     for ( int z = 0 ; z < tam2-j; z++) {
       obj1 = ( ObjecteGibert ) a2.get(j+z);
       s1 = obj1.obtenirId();
       p1 = obj1.obtenirPes();
       obj2 = new ObjecteGibert ( s1, p1*pes2/(pes1+pes2));
       lista.add(obj2);
       //j++;
     }
   }
   else if (j >= tam2 && i < tam1){
     // El vector derecha ya se ha copiado completamente.
     for ( int z = 0 ; z < tam1-i; z++) {
       obj1 = ( ObjecteGibert ) a1.get(i+z);
       s1 = obj1.obtenirId();
       p1 = obj1.obtenirPes();
       obj2 = new ObjecteGibert ( s1, p1*pes1/(pes1+pes2));
       lista.add(obj2);
       //i++;
     }
   }
   s3 = escribirListaGibert (lista);
   d = new Dada (s3);
   return d;
 }

 /**
  * Funció que agafa un ArrayList amb objectes Gibert y els converteix en format "Dada" extesa.
  *
  * @ param lista.  ArrayList que conté els diferents objectes que composan la dada extesa.
  * @ return String que conte la Dada extesa.
  *
  * <p>Title: Java-KLASS</p>
  * <p>Description: Paquet estadístic</p>
  * <p>Copyright: Copyright (c) 2006</p>
  * <p> </p>
  * @author Rober
  * CLASSIFICACIÖ
  * @version v.0
  */
 public String escribirListaGibert ( ArrayList lista ) {
   String s1, s2, dada;
   float p;
   ObjecteGibert obj;

   dada = "(";
   for ( int i = 0; i < lista.size(); i ++ ) {
     obj = (ObjecteGibert)lista.get(i);
     s1 = obj.obtenirId();
     p = obj.obtenirPes();
     s2 = Float.toString (p);
     dada = dada + "(" + s1 + "," + s2 + ")";
   }

   dada = dada + ")";
   return dada;
 }

 /**
  * Funció que fa la classificació comprobant que el métode sigui correcte.
  *
  * @ param metode.  "Veïns recíprocs encadenats".
  * @ param criteri.  "Centroide" y "Ward".
  * @ param opc.  OpcionsDis que conte les opcions de classificació.
  * @ param tipus.  "Gibert" y "Moda".
  * @ param ficheroHis.  Boolea que indica si s'ha de crear el Fitxer *.his.
  * @ param ficheroDrm.  Boolea que indica si s'ha de crear el Fitxer *.drm.
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
 void classificarMatriu( /*String metode, */String criteri, OpcionsDis opc, String tipus, boolean ficheroHis, boolean ficheroDrm){
   boolean deshacerCambio = false;

   //if ( metode.compareTo("Veïns recíprocs encadenats") == 0 ){
   // System.out.println("ESTOY EN GESTORCLASIFICACION CON"+  obtenirGesMatri().obtenirNomMatriuRelatiu());
     if ( opc.getTipus() == 'm'){
       if (opc.getCateg() == 'a'){
         opc.setCateg('x');
         deshacerCambio = true;
       }
     }

   //   System.out.println("ESTOY EN GESTORCLASIFICACION CON "+"opc="+opc+"criteri="+criteri+"tipus="+tipus+" ale");
   // System.out.println("antes de matriuveins "+"opc.tipus="+opc.getTipus()+"opc.alfa"+opc.getAlfa()+"opc.beta="+opc.getBeta()+" ale");

 System.out.println("Entra en clasificarMatriuVeins 04/08/09");

	  clasificarMatriuVeins ( opc, criteri, tipus );
	System.out.println("sale en clasificarMatriuVeins 04/08/09");  
	  //	System.out.println( "gestor clasificacion opc: "+opc + "criterio"+criteri+"tipus"+tipus);
     arbol.rellenarllNodesIdn ( this );
     if (deshacerCambio) {
       opc.setCateg('a');
     }

     try {
       if (ficheroHis) {
         crearFitxerHis( /*metode, */criteri, opc);
       }
       if (ficheroDrm) {
		  //System.out.println("ESTOY EN GESTORCLASIFICACION CON222"+  obtenirGesMatri().obtenirNomMatriuRelatiu());
         arbol.crearFitxerDendo(obtenirGesMatri().obtenirDirResultats()+ficheroResultadosDrm);
       }
     }
     catch (Exception ex) {
     }
   //}
 }


///////////
 void classificarMatriuEst( /*String metode, */String est, String[] varsest, String criteri, OpcionsDis opc, String tipus, boolean ficheroHis, boolean ficheroDrm){
   boolean deshacerCambio = false;

   //if ( metode.compareTo("Veïns recíprocs encadenats") == 0 ){
   // System.out.println("ESTOY EN GESTORCLASIFICACION CON"+  obtenirGesMatri().obtenirNomMatriuRelatiu());
     if ( opc.getTipus() == 'm'){
       if (opc.getCateg() == 'a'){
         opc.setCateg('x');
         deshacerCambio = true;
       }
     }

     clasificarMatriuVeinsEst (est,varsest, opc, criteri, tipus );
	  //	System.out.println( "gestor clasificacion opc: "+opc + "criterio"+criteri+"tipus"+tipus);
     arbol.rellenarllNodesIdn ( this );
     if (deshacerCambio) {
       opc.setCateg('a');
     }

     try {
       if (ficheroHis) {
         crearFitxerHis( /*metode, */criteri, opc);
       }
       if (ficheroDrm) {
		  //System.out.println("ESTOY EN GESTORCLASIFICACION CON222"+  obtenirGesMatri().obtenirNomMatriuRelatiu());
         arbol.crearFitxerDendo(obtenirGesMatri().obtenirDirResultats()+ficheroResultadosDrm);
       }
     }
     catch (Exception ex) {
     }
   //}
 }


















 /**
  * Funció que fa la classificacio.  Creant la pila on es guardará la llista de veins reciprocs.
  *
  * @ param opc.  OpcionsDis que conte les opcions de classificacio.
  * @ param criteri.  "Centroide" y "Ward"
  * @ param tipus.  "Gibert" y "Moda".
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

 void clasificarMatriuVeins ( OpcionsDis opc, String criteri, String tipus ) {
   int indice, ind, distanciaMenor1, distanciaMenor2;
   int clasesAgregadas = obtenirNumObjetos() ;
   double pon;
   String identificador1, identificador2;
   Stack pila = new Stack();
   int estaApilado ;


   indice = 0;
   pila.push(obtenirIdentificador(indice));

   distanciaMenor1 = calcularDistanciaMenor(indice, criteri, opc);


   while (clasesAgregadas > 1) {
       distanciaMenor2 = calcularDistanciaMenor(distanciaMenor1, criteri, opc);
       estaApilado = pila.search(obtenirIdentificador(distanciaMenor2)) ;

     if (estaApilado == -1) {
         pila.push(obtenirIdentificador(distanciaMenor1));
         distanciaMenor1 = distanciaMenor2;
     }
     else if ( estaApilado == 1 ) {
       identificador1 = (String) pila.pop();
       ind = obtenirIndex ( identificador1);
       identificador2 = obtenirIdentificador(distanciaMenor1);

       pon = clasifica[ind][distanciaMenor1].doubleValue();
       agregarClase(tipus, identificador1, identificador2, pon);

       clasesAgregadas--;

       if ( pila.isEmpty() ) {
         distanciaMenor1 = obtenirObjectesAux().obtenirLong()-1;
     }
       else {
         distanciaMenor1 = obtenirIndex ((String) pila.pop());

       }
     }
     else {
       for ( int i = 0; i < estaApilado; i ++ ) {

         identificador1 = (String) pila.pop();
         ind = obtenirIndex ( identificador1);
         identificador2 = obtenirIdentificador(distanciaMenor1);


         pon = calcularDistan ( ind,distanciaMenor1,criteri,opc);
         clasifica [ind][distanciaMenor1]= new Double (pon);
         clasifica [distanciaMenor1][ind]= new Double (pon);

         pon = clasifica[ind][distanciaMenor1].doubleValue();
         agregarClase(tipus, identificador1, identificador2, pon);

         clasesAgregadas--;

         distanciaMenor1 = obtenirObjectesAux().obtenirLong()-1;
       }

         if ( ! pila.isEmpty() ) {
           distanciaMenor1 = obtenirIndex ((String) pila.pop());
         }
       }
     }
   }
	
	
	////////
	 void clasificarMatriuVeinsEst (String est, String[] varsest, OpcionsDis opc, String criteri, String tipus ) {
   int indice, ind, distanciaMenor1, distanciaMenor2;
   int clasesAgregadas = obtenirNumObjetos() ;
   double pon;
   String identificador1, identificador2;
   Stack pila = new Stack();
   int estaApilado ;
	
 // System.out.println("esta en clasificrMatriuVeinsEst"+opc.getTipus());

   
   indice = 0;
   pila.push(obtenirIdentificador(indice));

//System.out.println("entra en calcularDistanciaMenorEst");
   distanciaMenor1 = calcularDistanciaMenorEst(varsest,indice, criteri, opc);
//System.out.println("sale de calcularDistanciaMenorEst");


   while (clasesAgregadas > 1) {
       distanciaMenor2 = calcularDistanciaMenorEst(varsest,distanciaMenor1, criteri, opc);
       estaApilado = pila.search(obtenirIdentificador(distanciaMenor2)) ;

     if (estaApilado == -1) {
         pila.push(obtenirIdentificador(distanciaMenor1));
         distanciaMenor1 = distanciaMenor2;
     }
     else if ( estaApilado == 1 ) {
       identificador1 = (String) pila.pop();
       ind = obtenirIndex ( identificador1);
       identificador2 = obtenirIdentificador(distanciaMenor1);

       pon = clasifica[ind][distanciaMenor1].doubleValue();
       agregarClaseEst(est,tipus, identificador1, identificador2, pon);

       clasesAgregadas--;

       if ( pila.isEmpty() ) {
         distanciaMenor1 = obtenirObjectesAux().obtenirLong()-1;
     }
       else {
         distanciaMenor1 = obtenirIndex ((String) pila.pop());

       }
     }
     else {
       for ( int i = 0; i < estaApilado; i ++ ) {

         identificador1 = (String) pila.pop();
         ind = obtenirIndex ( identificador1);
         identificador2 = obtenirIdentificador(distanciaMenor1);


         pon = calcularDistanEst (varsest, ind,distanciaMenor1,criteri,opc);
         clasifica [ind][distanciaMenor1]= new Double (pon);
         clasifica [distanciaMenor1][ind]= new Double (pon);

         pon = clasifica[ind][distanciaMenor1].doubleValue();
         agregarClaseEst(est,tipus, identificador1, identificador2, pon);

         clasesAgregadas--;

         distanciaMenor1 = obtenirObjectesAux().obtenirLong()-1;
       }

         if ( ! pila.isEmpty() ) {
           distanciaMenor1 = obtenirIndex ((String) pila.pop());
         }
       }
     }
   }

	
///////////////
   /**
    * Funció calcula totes les distancias del objecte passat com a paràmetre, les guarda
    * a la matriu "classifica" y retorna el index del objecte que es troba a menor distancia
    * del objecte inicial.
    *
    * @ param indice.  Index del objecte del qual li volem trobar el que es troba a distancia menor.
    * @ param criteri.  "Centroide" y "Ward".
    * @ param opc.  OpcionsDis que conte les opcions de classificacio.
    * @ return int que conte el index del objecte que es troba a menor distancia del objecte
    * passat com a paràmetre.
    *
    * <p>Title: Java-KLASS</p>
    * <p>Description: Paquet estadístic</p>
    * <p>Copyright: Copyright (c) 2006</p>
    * <p> </p>
    * @author Rober
    * CLASSIFICACIÖ
    * @version v.0
    */


   public int calcularDistanciaMenor(int indice, String criteri, OpcionsDis opc){
     int ind1 = -1;
     double distancia = -1, menor = -1;
     int posicion = obtenirObjectesAux().obtenirLong();

     for ( int i = 0; i < posicion; i++ ) {
       if ( !agregados[i] && indice != i ){

         if (clasifica [i][indice] != null  ) {
           distancia = clasifica [i][indice].doubleValue();
         }
         else {
           distancia = calcularDistan ( indice,i,criteri,opc);
           clasifica [indice][i]= new Double (distancia);
           clasifica [i][indice]= new Double (distancia);
         }

         if ( ind1 == -1 ) {
           ind1 = i ;
           menor = distancia;
         }
         else {
           if ( distancia < menor ){
             ind1 = i;
             menor = distancia;
           }
         }
       }
     }

     clasifica [indice][indice] = new Double (ind1);
     return ind1;
   }





 public int calcularDistanciaMenorEst(String[] varsest,int indice, String criteri, OpcionsDis opc){
     int ind1 = -1;
     double distancia = -1, menor = -1;
     int posicion = obtenirObjectesAux().obtenirLong();

     for ( int i = 0; i < posicion; i++ ) {
       if ( !agregados[i] && indice != i ){

         if (clasifica [i][indice] != null  ) {
           distancia = clasifica [i][indice].doubleValue();
         }
         else {
		//	System.out.println( " entra en calcularDistanEst");
           distancia = calcularDistanEst (varsest, indice,i,criteri,opc);
			 // 	System.out.println( " sale de calcularDistanEst");
           clasifica [indice][i]= new Double (distancia);
           clasifica [i][indice]= new Double (distancia);
         }

         if ( ind1 == -1 ) {
           ind1 = i ;
           menor = distancia;
         }
         else {
           if ( distancia < menor ){
             ind1 = i;
             menor = distancia;
           }
         }
       }
     }

     clasifica [indice][indice] = new Double (ind1);
     return ind1;
   }








  /**
   * Funció calcula les distancias del objecte passat com a paràmetre.
   *
   * @ param indice1.  Index del primer objecte.
   * @ param indice2.  Index del segon objecte.
   * @ param criteri.  "Gibert" y "Moda".
   * @ param opc.  String que conte les opcions de classificacio.
   * @ return double que conte la distancia entre els objectes passats com a paràmetre.
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */
  public double calcularDistan (int indice1, int indice2, String criteri, OpcionsDis opc){
    int indiceEsq, indiceDret;
    float pesEsq, pesDret, pes2;
    String valor, identificador, identificador2, fillEsq, fillDret;
    double distanciaEsq, distanciaDret, distancia2;
    double distancia = -1;

    if ( criteri.compareTo("Centroide")== 0 ) {

      valor = obtenirDis2Obj (indice1,indice2,opc);

      if ( valor != null  ){

        distancia = Double.parseDouble(valor);

      }
    }
    else {


      if (( indice1 < numObjetos) && ( indice2 < numObjetos)){
        valor = obtenirDis2Obj (indice1,indice2,opc);
        if ( valor != null  ){
          distancia = Double.parseDouble(valor);
          distancia = distancia / 2;
        }
      }
      else {
        //   Por lo menos uno de ellos es clase el otro no importa
        if ( indice1 < numObjetos ) {
          int i1 = indice1;
          indice1 = indice2;
          indice2 = i1;
        }

        identificador = obtenirIdentificador(indice1);

        fillEsq = obtenirFillEsquerre(identificador);
        fillDret = obtenirFillDret(identificador);

        indiceEsq = obtenirIndex(fillEsq);
        indiceDret = obtenirIndex(fillDret);

        pesEsq = obtenirPes (fillEsq);
        pesDret = obtenirPes (fillDret);

        identificador2 = obtenirIdentificador(indice2);
        pes2 = obtenirPes (identificador2);
        distancia2 = clasifica [indiceEsq][indiceDret].doubleValue();

        if ( clasifica[indiceEsq][indice2] == null  ){
          distanciaEsq = calcularDistan (indiceEsq,indice2,criteri,opc);
        }
        else {
          distanciaEsq = clasifica[indiceEsq][indice2].doubleValue();
        }

        if ( clasifica[indiceDret][indice2] == null  ){
          distanciaDret = calcularDistan (indiceDret,indice2,criteri,opc);
        }
        else {
          distanciaDret = clasifica[indiceDret][indice2].doubleValue();
        }

        distancia = ((pesEsq + pes2) * distanciaEsq + (pesDret + pes2) * distanciaDret - pes2 * distancia2 )/ (pesEsq + pesDret + pes2);
      }

    }

    if ( distancia < 0 ) {
      distancia = Math.abs(distancia);
    }

    return distancia;
  }



 public double calcularDistanEst (String[] varsest,int indice1, int indice2, String criteri, OpcionsDis opc){
    int indiceEsq, indiceDret;
    float pesEsq, pesDret, pes2;
    String valor, identificador, identificador2, fillEsq, fillDret;
    double distanciaEsq, distanciaDret, distancia2;
    double distancia = -1;

    if ( criteri.compareTo("Centroide")== 0 ) {

      valor = obtenirDis2ObjEst (varsest,indice1,indice2,opc);

      if ( valor != null  ){

        distancia = Double.parseDouble(valor);

      }
    }
    else {


      if (( indice1 < numObjetos) && ( indice2 < numObjetos)){
			//System.out.println( " entra en obtenirDis2ObjEst");
        valor = obtenirDis2ObjEst (varsest,indice1,indice2,opc);
		  //	System.out.println( " sale de obtenirDis2ObjEst");
        if ( valor != null  ){
          distancia = Double.parseDouble(valor);
          distancia = distancia / 2;
        }
      }
      else {
        //   Por lo menos uno de ellos es clase el otro no importa
        if ( indice1 < numObjetos ) {
          int i1 = indice1;
          indice1 = indice2;
          indice2 = i1;
        }

        identificador = obtenirIdentificador(indice1);

        fillEsq = obtenirFillEsquerre(identificador);
        fillDret = obtenirFillDret(identificador);

        indiceEsq = obtenirIndex(fillEsq);
        indiceDret = obtenirIndex(fillDret);

        pesEsq = obtenirPes (fillEsq);
        pesDret = obtenirPes (fillDret);

        identificador2 = obtenirIdentificador(indice2);
        pes2 = obtenirPes (identificador2);
        distancia2 = clasifica [indiceEsq][indiceDret].doubleValue();

        if ( clasifica[indiceEsq][indice2] == null  ){
		  //	System.out.println( " entra en obtenirDis2ObjEst izq");
          distanciaEsq = calcularDistanEst (varsest,indiceEsq,indice2,criteri,opc);
			 	//System.out.println( " entra en obtenirDis2ObjEst izq");
        }
        else {
          distanciaEsq = clasifica[indiceEsq][indice2].doubleValue();
        }

        if ( clasifica[indiceDret][indice2] == null  ){
          distanciaDret = calcularDistanEst (varsest,indiceDret,indice2,criteri,opc);
        }
        else {
          distanciaDret = clasifica[indiceDret][indice2].doubleValue();
        }

        distancia = ((pesEsq + pes2) * distanciaEsq + (pesDret + pes2) * distanciaDret - pes2 * distancia2 )/ (pesEsq + pesDret + pes2);
      }

    }

    if ( distancia < 0 ) {
      distancia = Math.abs(distancia);
    }

    return distancia;
  }



























  /**
   * Funció que escriurá en el FitxerHis passat com a paràmetre la terna formada per
   * ( Classe? indexDeNivell (FillEsquerre FillDret)) ordenades per index de nivell.
   *
   * @ param fichero.  FitxerHis on s'escriuran les dades.
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

  void obtenerOrdenIdnOrdenado ( FitxerHis fichero )   throws FileNotFoundException, FormatIncorrecteException, IOException {
    String lin = new String();
    ArrayList lista = obtenirArbol().obtenirLlNodesIdn();

    for (int i = 0; i < lista.size(); i++) {
      NodeBinari node = (NodeBinari) lista.get(i);
      String raiz = node.obtenirEtiqueta();
      double idn = node.obtenirIndexNivell();
      String fillEsq = node.obtenirFillEsq().obtenirEtiqueta();
      String fillDret = node.obtenirFillDret().obtenirEtiqueta();

      lin = "("+raiz+" "+Double.toString(idn) +" ("+fillEsq+" "+fillDret+"))";
      fichero.escriureLin(lin);
    }

    lin = "NIL";
    fichero.escriureLin(lin);

  }


  /**
   * Funció que escriurá en el FitxerHis passat com a paràmetre els identificadors de la
   * llista d'objectes amb les dades que li corresponen.
   *
   * @ param fichero.  FitxerHis on s'escriuran les dades.
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
  void obtenerOrdenClases ( FitxerHis fichero )   throws FileNotFoundException, FormatIncorrecteException, IOException {
    String linea;
    String clase;
    String[] fila;

    int posicion = obtenirObjectesAux().obtenirLong()-1;
    while ( posicion >= 0 ) {
      clase = obtenirIdentificador(posicion);
      linea = "("+clase+"" ;

      fila = new String [ obtenirGesMatri().obtenirLlistaProps().obtenirLong()];
      fila = obtenirFila(clase);
      for ( int j = 0; j < fila.length; j++) {
        linea = linea + " " + fila[j];
      }

      linea = linea + " )";
      fichero.escriureLin(linea);
      posicion --;
    }
  }

  /**
   * Funció que ens dirá a quina tipus de métrica pertany la OpcionsDis utilitzada.
   *
   * @ param c.  Char que conte el tipus de métrica.
   * @ return String amb el nom de la métrica utilitzada.
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */
  public String obtenerMetrica (char c) {
    String metrica;

    switch (c) {
      case 'e':
        metrica = "Euclídia";
        break;

      case 'a':
        metrica = "Valor Absolut";
        break;

      case 'k':
        metrica = "Minkovski";
        break;

      case 'c':
        metrica = "Chi2";
        break;

      case 'h':
        metrica = "Hamming Generalitzat";
        break;

      case 'g':
        metrica = "Gowda-Diday";
        break;

      case 'i':
        metrica = "Ichino-Yaguchi";
        break;

      case 'w':
        metrica = "Gower";
        break;

      case 'r':
        metrica = "Ralambondrainy";
        break;

      case 'm':
        metrica = "Mixta Gibert";
        break;

      default:
        metrica = "Metrica desconeguda.";
        break;
    }

    return metrica;
  }

  public String obtenerCategoria (char c) {
    String categoria;


    switch (c) {
      case 'n':
        categoria = "No normalitzar";
        break;

      case 's':
        categoria = "Normalitzar per la desviació típica";
        break;

      case 'r':
        categoria = "Normalitzar pel rang";
        break;

      case 'i':
        categoria = "Inercia";
        break;

      case 'o':
        categoria = "Norma";
        break;

      case 'a':
        categoria = "Automàtic";
        break;

      case 'x':
        categoria = "No Automàtic";
        break;


      default:
        categoria = "Categoria desconeguda.";
        break;
    }

    return categoria;
  }

      /**
       * Funció que crearà el FitxerHis per poder crear els Dendogramas.
       *
       * @ param
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
  void crearFitxerHis(/*String metode, */String criteri, OpcionsDis opc)  throws FileNotFoundException, FormatIncorrecteException, IOException {
    FitxerHis fichero; // = new FitxerHis("a");
    String lin = new String();
    String identificadorRaiz;
    String [] dada;
    String datosRaiz;
    String numPropietats;
    String [] listaObjetos;
    String llObjs;

    String nomp;
    String dirResultats;
    String p ="";

    dirResultats = obtenirGesMatri().obtenirDirResultats();
    nomp = ficheroResultadosHis;

    fichero = new FitxerHis(dirResultats+nomp);
    try {
      fichero.obrirPerEscriure(false);

      lin = obtenirGesMatri().obtenirNomMatriuRelatiu();
      fichero.escriureLin(lin); // nom dades

      if ( opc.getPond() ) {
        lin = "1";
      }
      else {
        lin = "0";
      }
      fichero.escriureLin(lin); // ponderar

      if ( obtenirGesMatri().obtenirMiss() ) {
        fichero.escriureLin("1"); // hi ha miss
      }
      else {
        fichero.escriureLin("0"); // no hi ha miss
      }

      if ( opc.getCateg() == 's' ) {
        lin = "1";
      }
      else if ( opc.getCateg() == 'r' ) {
        lin = "1";
      }
      else {
        lin = "0";
      }
      fichero.escriureLin(lin); // modus de normalització

      fichero.escriureLin("TOTS");  // objs a usar
      fichero.escriureLin("TOTES"); // propietats a usar

      String metrica = obtenerMetrica (opc.getTipus());
      fichero.escriureLin( metrica ); // metrica
      //fichero.escriureLin( metode ); // metrica

      fichero.escriureLin( criteri ); // criteri

      lin = obtenirOpcionDis (opc);
      fichero.escriureLin(lin); // limit  --> transformado en OpcionsDis

      fichero.escriureLin( Float.toString(opc.getAlfa()) ); // alfa

      fichero.escriureLin( Float.toString( opc.getBeta() ) ); // beta

      fichero.escriureLin( Float.toString( opc.getGamma() ) ); // gamma

      fichero.escriureLin(idClasse); // id_lclass (prefixe de les classes)

      identificadorRaiz = obtenirArbol().obtenirArrel().obtenirEtiqueta();
      fichero.escriureLin( identificadorRaiz ); // arrel arbre

      dada = obtenirFila ( identificadorRaiz );
      datosRaiz = "(";
      for ( int i = 0; i < dada.length; i ++ ) {
        datosRaiz = datosRaiz + dada[i] + " ";
      }
      datosRaiz = datosRaiz + ")";
      fichero.escriureLin(datosRaiz); // vector corresponent a les components de l'arrel de l'arbre

      numPropietats = Integer.toString (obtenirGesMatri().obtenirLlistaProps().obtenirLong());
      fichero.escriureLin( numPropietats ); // nombre total de propietats

      listaObjetos = obtenirObjectesAux().llistarIDsObjs();
      llObjs = "(";
      for ( int i = 0; i < numObjetos; i ++ ) {
        int indice = obtenirIndex ( listaObjetos[i] );

        llObjs = llObjs + "(" + listaObjetos[i] + " " + obtenirArbol().obtenirNode(listaObjetos[i]).obtenirIndexNivell() + " " + indice + " " + obtenirPes(listaObjetos[i]) + ")" + " " ;
      }
      llObjs = llObjs + ")";
      fichero.escriureLin(llObjs); // llista d'objectes

      LlistaPropietats listaPropiedades = obtenirGesMatri().obtenirLlistaProps();
      String llProps = "(";


      for ( int i = 0; i < listaPropiedades.obtenirLong(); i ++ ) {
        Propietat prop = listaPropiedades.obtenirPropietat(i);

        if (prop instanceof PropNumerica) {
          String nomProp = prop.obtenirId();
          EstadisticsNum estad = new EstadisticsNum();
          float rangMinim, rangMaxim, suma, sumaCuad;
          int numMis;

          estad = ((PropNumerica) prop).obtenirEstadistics();
          numMis = estad.obtenirNumMissings();
          suma = estad.obtenirSumPes();
          sumaCuad = estad.obtenirSumQuadratsPes();
          rangMinim = estad.obtenirMin();
          rangMaxim = estad.obtenirMax();

          llProps = llProps + "(" + nomProp + " C (";
          llProps = llProps + Float.toString(suma) + "/" + Integer.toString(numObjetos) + " ";
          llProps = llProps + Float.toString(sumaCuad) + "/" + Integer.toString(numObjetos) + " ";
          llProps = llProps + Integer.toString(numMis) + " ";
          llProps = llProps + Float.toString(rangMinim) + " ";
          llProps = llProps + Float.toString(rangMaxim) + ") ";

/*
            (RAM 608/13 1920/13 0 32 64)
*/

        }
        else if (prop instanceof PropOrdinal) {
          String nomProp = prop.obtenirId();
          ArrayList llMods =  ((PropOrdinal)prop).llModalitats;
          EstadisticsCateg estad = new EstadisticsCateg();

          estad = ((PropOrdinal) prop).obtenirEstadistics();
          p = p + "(";
          llProps = llProps + "(" + nomProp + " O (";
          for ( int j = 0; j < llMods.size(); j ++ ) {
            llProps = llProps + llMods.get(j) + " ";
            p = p + "(" + llMods.get(j).toString() + "." + estad.obtenirFreqPes(llMods.get(j).toString()) + ")";
          }
          llProps = llProps + ") 1)";
          p = p + ")";
        }
        else if (prop instanceof PropNominal) {
          String nomProp = prop.obtenirId();
          ArrayList llMods =  ((PropNominal)prop).llModalitats;
          EstadisticsCateg estad = new EstadisticsCateg();

          estad = ((PropNominal) prop).obtenirEstadistics();
          p = p + "(";
          llProps = llProps + "(" + nomProp + " N (";
          for ( int j = 0; j < llMods.size(); j ++ ) {
            llProps = llProps + llMods.get(j) + " ";
            p = p + "(" + llMods.get(j).toString() + "." + estad.obtenirFreqPes(llMods.get(j).toString()) + ")";
          }
          llProps = llProps + ") 1)";
          p = p + ")";
        }
        else if (prop instanceof PropCategorica) {
          String nomProp = prop.obtenirId();
          ArrayList llMods =  ((PropCategorica)prop).llModalitats;
          EstadisticsCateg estad = new EstadisticsCateg();

          estad = ((PropCategorica) prop).obtenirEstadistics();
          p = p + "(";
          llProps = llProps + "(" + nomProp + " Q (";
          for ( int j = 0; j < llMods.size(); j ++ ) {
            llProps = llProps + llMods.get(j) + " ";
            p = p + "(" + llMods.get(j).toString() + "." + estad.obtenirFreqPes(llMods.get(j).toString()) + ")";

          }
          llProps = llProps + ") 1)";
          p = p + ")";
        }
      }
      llProps = llProps + ")";
      fichero.escriureLin(llProps); // llista de propietats

      fichero.escriureLin(p); // llista d'efectius


      obtenerOrdenIdnOrdenado ( fichero );

      //lin = "NIL";
      //fichero.escriureLin(lin);

      obtenerOrdenClases ( fichero);

      lin = "NIL";
      fichero.escriureLin(lin);

      fichero.tancarEsc();
    }
    catch (IOException e) {
    }

  }

// Rober Funcion nueva para obtener las OpcionsDis en un String

  public String obtenirOpcionDis ( OpcionsDis opc ) {
    String opcions = "";
    char tipus;
    char categ;
    boolean quadrat;
    boolean ponderat;
    float alfa;
    float beta;
    int p = 0;
    float gamma = 0;

    tipus = opc.getTipus();
    categ = opc.getCateg();
    quadrat = opc.getQuad();
    ponderat = opc.getPond();
    alfa = opc.getAlfa();
    beta = opc.getBeta();
    p = opc.getP();
    gamma = opc.getGamma();

    opcions = "Opcions Dis : ( Metrica = " + obtenerMetrica(tipus) + "; Categoría = " +
        obtenerCategoria (categ ) +"; Quadrat = "+ quadrat + "; Ponderat = " + ponderat +
        "; Alfa = " + alfa + "; Beta = " + beta + "; P = " + p + "; Gamma = " + gamma +";)";

    return opcions;
  }

  // Funcion del jose rectificada y adaptada a mis matrices
  public String obtenirDis2Obj(int i1 ,int i2,OpcionsDis opc) {
   ArrayList activasPr = new ArrayList();

	    String[][] llProps;
	    String[] llObjs;
	    int lonprop0=0;
	    int lonprop1=0;
	    int lonprop2=0;
	    int lonprop3=0;
	    Dada[]xin ;
	    Dada[]xiin;
	    Dada[]xic ;
	    Dada[]xiic;
	    Dada[]x1;
	    Dada[]x2;
	    String result=null;
	    int fila;
	    int col;
	    float p1;
	    float p2;


	//	 activasPr = obtenirGesMatri().ferActiv();
		 //	 for (int i = 0; i <  activasPr.size(); i++) {
    //    ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
		//  System.out.println( "activas en gestor clasifica"+   activasPr.get(i));
    //  }

///////////////////
//llProps=obtenirGesMatri().obtenirLlistaPropsa().llistarIDsPropietats();
// System.out.println( "ESTOY EN OBTENIRDIS2OBJ");

 //System.out.println( "llProps[0].length"+llProps[0].length);
//System.out.println( "llProps[1].length"+llProps[1].length);
 //System.out.println( "llProps[0][0]"+llProps[0][0]);
// System.out.println( "llProps[1][0]"+llProps[1][0]);
// System.out.println( "llProps[1][1]"+llProps[1][1]);
////////////////////	
//System.out.println( "entre a carregarpropietatsa");

//obtenirGesMatri().carregarPropietatsa();
//System.out.println( "sali de carregarpropietasa");
 // System.out.println("ESTOY EN OBTENIR2OBJ"+  obtenirGesMatri().obtenirNomMatriuRelatiu());
		 
		 
		 
		  ArrayList activias=new ArrayList();
	   activias= obtenirGesMatri().obtenirActivas();
	  
	  for (int i = 0; i<activias.size(); i++){
         
            System.out.println( "LAS Activiiiiisa"+  activias.get(i));
         }

	  
	  
 if (obtenirGesMatri().activasel){
// System.out.println( "estan las activas seleccionadas");

 llProps = obtenirGesMatri().obtenirLlistaProps().llistarIDsPropietatsEnBasicsA(activias);}
 else{
    // {llProps = gestor.obtenirLlistaIDsProps();}
	 llProps=obtenirGesMatri().obtenirLlistaProps().llistarIDsPropietats();}
// System.out.println( "no estan las activas seleccionadas");}

/*
Estado esta;
	  
if  (obtenirGesMatri().estadosel){
String [] varsest=null;
String actual = obtenirGesMatri().estadoactual;
	for(int j=0;j<obtenirGesMatri().llEstados.size();j++){
					esta = (Estado) (obtenirGesMatri().llEstados.get(j)); 
					if (esta.nom== obtenirGesMatri().estadoactual){
					varsest=esta.variables;}
          }
llProps = obtenirGesMatri().obtenirLlistaProps().llistarIDsPropietasEstados(varsest);
//System.out.println( "esta marc clasificacion");
}
else{
llProps=obtenirGesMatri().obtenirLlistaProps().llistarIDsPropietats();
//System.out.println( "no esta marcado clasificacion");
}

*/
		 
		 
		 
	   // llProps=obtenirGesMatri().obtenirLlistaProps().llistarIDsPropietats();
		 
	    lonprop0=llProps[0].length;
	    lonprop1=llProps[1].length;
	    lonprop2=llProps[2].length;
	    lonprop3=llProps[3].length;
		 
	    llObjs=obtenirObjectesAux().llistarIDsObjs();


	    xin =new Dada[lonprop0];
	    xiin =new Dada[lonprop0];
	    xic =new Dada[lonprop1+lonprop2+lonprop3];
	    xiic =new Dada[lonprop1+lonprop2+lonprop3];

	  x1 = new Dada [obtenirGesMatri().obtenirLlistaProps().obtenirLong()];
	  x2 = new Dada [obtenirGesMatri().obtenirLlistaProps().obtenirLong()];



	    MatriuDades dad1 = obtenirGesMatri().obtenirDades();
	    fila=obtenirObjectesAux().obtenirIndex(llObjs[i1]);


	    p1=obtenirObjecte(fila).obtenirPes();

	    if ( i1>= numObjetos ){
	      dad1 = dadesClas;
	      fila = fila - numObjetos;
	    }

	    for (int k = 0; k < lonprop0; k++) {
	      col=obtenirGesMatri().obtenirLlistaProps().obtenirIndex(llProps[0][k]);
		//	System.out.println( "imprimo col"+col);
	      xin[k]=dad1.obtenirDada(fila,col);
	    }
	    for (int k = 0; k < lonprop1; k++) {
	      col=obtenirGesMatri().obtenirLlistaProps().obtenirIndex(llProps[1][k]);
				//System.out.println( "imprimo col2"+col);
	      xic[k]=dad1.obtenirDada(fila,col);
	    }
	    for (int k = 0; k < lonprop2; k++) {
	      col=obtenirGesMatri().obtenirLlistaProps().obtenirIndex(llProps[2][k]);
				//System.out.println( "imprimo col3"+col);
	      xic[k+lonprop1]=dad1.obtenirDada(fila,col);
	    }
	    for (int k = 0; k < lonprop3; k++) {
	      col=obtenirGesMatri().obtenirLlistaProps().obtenirIndex(llProps[3][k]);
				//System.out.println( "imprimo col4"+col);
	      xic[k+lonprop2]=dad1.obtenirDada(fila,col);
	    }

	    //Agafa X2
	    MatriuDades dad2 = obtenirGesMatri().obtenirDades();
	    fila=obtenirObjectesAux().obtenirIndex(llObjs[i2]);


	    p2=obtenirObjecte(fila).obtenirPes();

	    if ( i2>= numObjetos ){
	      dad2 = dadesClas;
	      fila = fila - numObjetos;
	    }
	    else {
	      dad2 = obtenirGesMatri().obtenirDades();
	    }

	    for (int k = 0; k < lonprop0; k++) {
	      col=obtenirGesMatri().obtenirLlistaProps().obtenirIndex(llProps[0][k]);
			//	System.out.println( "imprimo col5"+col);
	      xiin[k]=dad2.obtenirDada(fila,col);
	    }
	    for (int k = 0; k < lonprop1; k++) {
	      col=obtenirGesMatri().obtenirLlistaProps().obtenirIndex(llProps[1][k]);
				//System.out.println( "imprimo col6"+col);
	      xiic[k]=dad2.obtenirDada(fila,col);
	    }
	    for (int k = 0; k < lonprop2; k++) {
	      col=obtenirGesMatri().obtenirLlistaProps().obtenirIndex(llProps[2][k]);
				//System.out.println( "imprimo col7"+col);
	      xiic[k+lonprop1]=dad2.obtenirDada(fila,col);
	    }
	    for (int k = 0; k < lonprop3; k++) {
	      col=obtenirGesMatri().obtenirLlistaProps().obtenirIndex(llProps[3][k]);
				//System.out.println( "imprimo col8"+col);
	      xiic[k+lonprop2]=dad2.obtenirDada(fila,col);
	    }


//	 Rober  Añadido 20070714

	    if ( !opc.getPond()){
	      p1=1;
	      p2=1;
	    }
	    result=obtenirGesMatri().obtenirDisInter(xin,xic,xiin,xiic,opc,p1,p2);


	    return result;
	  }
	  
	  
/////////////	  
	  
 // Funcion del jose rectificada y adaptada a mis matrices
  public String obtenirDis2ObjEst(String[] varsest,int i1 ,int i2,OpcionsDis opc) {
   ArrayList activasPr = new ArrayList();

	    String[][] llProps;
	    String[] llObjs;
	    int lonprop0=0;
	    int lonprop1=0;
	    int lonprop2=0;
	    int lonprop3=0;
	    Dada[]xin ;
	    Dada[]xiin;
	    Dada[]xic ;
	    Dada[]xiic;
	    Dada[]x1;
	    Dada[]x2;
	    String result=null;
	    int fila;
	    int col;
	    float p1;
	    float p2;


	//	 activasPr = obtenirGesMatri().ferActiv();
		 //	 for (int i = 0; i <  activasPr.size(); i++) {
    //    ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
		//  System.out.println( "activas en gestor clasifica"+   activasPr.get(i));
    //  }

///////////////////
//llProps=obtenirGesMatri().obtenirLlistaPropsa().llistarIDsPropietats();
// System.out.println( "ESTOY EN OBTENIRDIS2OBJ");

 //System.out.println( "llProps[0].length"+llProps[0].length);
//System.out.println( "llProps[1].length"+llProps[1].length);
 //System.out.println( "llProps[0][0]"+llProps[0][0]);
// System.out.println( "llProps[1][0]"+llProps[1][0]);
// System.out.println( "llProps[1][1]"+llProps[1][1]);
////////////////////	
//System.out.println( "entre a carregarpropietatsa");

//obtenirGesMatri().carregarPropietatsa();
//System.out.println( "sali de carregarpropietasa");
 // System.out.println("ESTOY EN OBTENIR2OBJ"+  obtenirGesMatri().obtenirNomMatriuRelatiu());
		 
 
		 /* ArrayList activias=new ArrayList();
	   activias= obtenirGesMatri().obtenirActivas();
	  
	  	  
 if (obtenirGesMatri().activasel){

 llProps = obtenirGesMatri().obtenirLlistaProps().llistarIDsPropietatsEnBasicsA(activias);}
 else{
    
	 llProps=obtenirGesMatri().obtenirLlistaProps().llistarIDsPropietats();}*/


Estado esta;
	  
/*if  (obtenirGesMatri().estadosel){
//String [] varsest=null;
String actual = obtenirGesMatri().estadoactual;
	for(int j=0;j<obtenirGesMatri().llEstados.size();j++){
					esta = (Estado) (obtenirGesMatri().llEstados.get(j)); 
					if (esta.nom== obtenirGesMatri().estadoactual){
					varsest=esta.variables;}
          }*/
llProps = obtenirGesMatri().obtenirLlistaProps().llistarIDsPropietasEstados(varsest);

/*System.out.println( "esta marc clasificacion");}
else{
llProps=obtenirGesMatri().obtenirLlistaProps().llistarIDsPropietats();
System.out.println( "no esta marcado clasificacion");
}*/


		 
		 
		 
	   // llProps=obtenirGesMatri().obtenirLlistaProps().llistarIDsPropietats();
		 
	    lonprop0=llProps[0].length;
	    lonprop1=llProps[1].length;
	    lonprop2=llProps[2].length;
	    lonprop3=llProps[3].length;
		 
	    llObjs=obtenirObjectesAux().llistarIDsObjs();


	    xin =new Dada[lonprop0];
	    xiin =new Dada[lonprop0];
	    xic =new Dada[lonprop1+lonprop2+lonprop3];
	    xiic =new Dada[lonprop1+lonprop2+lonprop3];

	  x1 = new Dada [obtenirGesMatri().obtenirLlistaProps().obtenirLong()];
	  x2 = new Dada [obtenirGesMatri().obtenirLlistaProps().obtenirLong()];



	    MatriuDades dad1 = obtenirGesMatri().obtenirDades();
	    fila=obtenirObjectesAux().obtenirIndex(llObjs[i1]);


	    p1=obtenirObjecte(fila).obtenirPes();

	    if ( i1>= numObjetos ){
	      dad1 = dadesClas;
	      fila = fila - numObjetos;
	    }

	    for (int k = 0; k < lonprop0; k++) {
	      col=obtenirGesMatri().obtenirLlistaProps().obtenirIndex(llProps[0][k]);
		//	System.out.println( "imprimo col"+col);
	      xin[k]=dad1.obtenirDada(fila,col);
	    }
	    for (int k = 0; k < lonprop1; k++) {
	      col=obtenirGesMatri().obtenirLlistaProps().obtenirIndex(llProps[1][k]);
				//System.out.println( "imprimo col2"+col);
	      xic[k]=dad1.obtenirDada(fila,col);
	    }
	    for (int k = 0; k < lonprop2; k++) {
	      col=obtenirGesMatri().obtenirLlistaProps().obtenirIndex(llProps[2][k]);
				//System.out.println( "imprimo col3"+col);
	      xic[k+lonprop1]=dad1.obtenirDada(fila,col);
	    }
	    for (int k = 0; k < lonprop3; k++) {
	      col=obtenirGesMatri().obtenirLlistaProps().obtenirIndex(llProps[3][k]);
				//System.out.println( "imprimo col4"+col);
	      xic[k+lonprop2]=dad1.obtenirDada(fila,col);
	    }

	    //Agafa X2
	    MatriuDades dad2 = obtenirGesMatri().obtenirDades();
	    fila=obtenirObjectesAux().obtenirIndex(llObjs[i2]);


	    p2=obtenirObjecte(fila).obtenirPes();

	    if ( i2>= numObjetos ){
	      dad2 = dadesClas;
	      fila = fila - numObjetos;
	    }
	    else {
	      dad2 = obtenirGesMatri().obtenirDades();
	    }

	    for (int k = 0; k < lonprop0; k++) {
	      col=obtenirGesMatri().obtenirLlistaProps().obtenirIndex(llProps[0][k]);
			//	System.out.println( "imprimo col5"+col);
	      xiin[k]=dad2.obtenirDada(fila,col);
	    }
	    for (int k = 0; k < lonprop1; k++) {
	      col=obtenirGesMatri().obtenirLlistaProps().obtenirIndex(llProps[1][k]);
				//System.out.println( "imprimo col6"+col);
	      xiic[k]=dad2.obtenirDada(fila,col);
	    }
	    for (int k = 0; k < lonprop2; k++) {
	      col=obtenirGesMatri().obtenirLlistaProps().obtenirIndex(llProps[2][k]);
				//System.out.println( "imprimo col7"+col);
	      xiic[k+lonprop1]=dad2.obtenirDada(fila,col);
	    }
	    for (int k = 0; k < lonprop3; k++) {
	      col=obtenirGesMatri().obtenirLlistaProps().obtenirIndex(llProps[3][k]);
				//System.out.println( "imprimo col8"+col);
	      xiic[k+lonprop2]=dad2.obtenirDada(fila,col);
	    }


//	 Rober  Añadido 20070714

	    if ( !opc.getPond()){
	      p1=1;
	      p2=1;
	    }
		 //	System.out.println( " entra en obtenirDisInterEst");
	    result=obtenirGesMatri().obtenirDisInterEst(varsest,xin,xic,xiin,xiic,opc,p1,p2);
   //	System.out.println( " sale de obtenirDisInterEst");


	    return result;
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
////////////////	  

}




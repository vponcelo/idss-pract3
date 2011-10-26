package jklass.nucli;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.*;
import java.util.Vector;

//Rober
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.1
 */
public class ArbreClassif extends ArbreBinari {
  private static Logger logger=Logger.getLogger(ArbreClassif.class.getName());
  /**
   * nom, nom de l'arbre de classificació
   * prefix, prefix que utilitzen les etiquetes dels nodes de classes
   */
  public String nom, prefix;
  /**
   * llista de nodes ordenats per index de nivell
   */
  public ArrayList llNodesIdn;
  /**
   * Hashtable que conté les particions generades per l'arbre;
   * té com clau el nom de la partició (String) i com valor
   * la partició
   */
  public Hashtable llParticions;
  /**
   * Hashtable que conté els indexos de nivell de cadascuna de les particions
   * generades per l'arbre; al igual que llParticions té com a clau
   * el nom de la partició (String) i com valor la partició
   */
  private Hashtable llIdnParts;

  /**
   * Indicador per a saber si s'ha de fer control d'inversions.
   */
  private boolean controlInversions;

  //ROBER
  /**
   * float que conte el pes de l'objecte que es la etiqueta del NodeBinari
   * que forma la arrel de l'arbre
   */

  private float pesArrel;

  /**
   * Array que conte les dades de l'objecte que es la etiqueta del NodeBinari
   * que forma la arrel de l'arbre
   */
  public Dada [] dadesArrel;


public  ArbreClassif(NodeBinari arrel, String nomArbre) {
    this(arrel, nomArbre, "");
  }

  /**
   * Constructor
   * @param arrel node arrel de l'arbre a construir
   * @param nomArbre nom a donar a l'arbre
   * @param pref prefix indicat al fitxer
   */
public  ArbreClassif(NodeBinari arrel, String nomArbre, String pref) {
    super(arrel);
    nom = nomArbre;
    prefix = pref;
    llNodesIdn = new ArrayList();
    llParticions = new Hashtable();
    llIdnParts = new Hashtable();
  }

 public  void afegirALlistaIdn(NodeBinari node){
    llNodesIdn.add(node);
  }

  String obtenirPrefix() {
    return prefix;
  }

 public String obtenirNom() {
    return nom;
  }

  /**
   * Es realitza un tall de l'arbre segons el index de nivell indicat i es crea partició corresponent a aquest tall
   * @param idn index de nivell del tall a realitzar al arbre
   * @param nom nom de la partició que es crearà a partir del tall
   * @return Particio corresponent al tall de l'arbre per l'index de nivell indicat per idn
   */
  Particio tallIdn(double idn, String nom){
    Particio par;
    Vector aux;
    int i = 0;
    NodeBinari node, fe, fd;
    boolean ok = true;
    String etiq;

    aux = new Vector();
    while ((i < llNodesIdn.size()) & ok){
      node = (NodeBinari)llNodesIdn.get(i);
      if (node.obtenirIndexNivell() >= idn){
        fe = node.obtenirFillEsq();
        if (fe.obtenirIndexNivell() < idn){
          aux.add(fe.obtenirEtiqueta());
        }
        fd = node.obtenirFillDret();
        if (fd.obtenirIndexNivell() < idn) {
          aux.add(fd.obtenirEtiqueta());
        }
        i++;
      } else {
        etiq = node.obtenirEtiqueta();
        if ( !aux.contains(etiq)){
          aux.add(etiq);
        }
        ok = false;
      }
    }
    aux.trimToSize();
    // Es crea la partició a partir del conjunt de nodes
    // obtingut del tall per l'idn indicat
    par = new Particio(nom, new ArrayList(aux));
    for (i=0; i < aux.size(); i++ ){
      etiq = (String)aux.get(i);
      node = (NodeBinari)nodes.get(etiq);
      desplegaClasse(par, etiq, node);
    }
    llParticions.put(nom, par);
    llIdnParts.put(nom,new Double(idn));
    return par;
  }

  private void desplegaClasse(Particio par, String classe, NodeBinari node){
    if (node.esFulla()) {
      par.afegirObjecte(classe, node.obtenirEtiqueta());
    }
    else {
      desplegaClasse(par, classe,node.obtenirFillEsq());
      desplegaClasse(par, classe, node.obtenirFillDret());
    }
  }

  /**
   * Es realitza un tall de l'arbre en el nombre de classe indicat i es crea
   * la partició corresponent a aquest tall
   * @param numClasses nombre de classes del tall a realitzar
   * @param nom nom de la partició que es crearà a partir del tall
   * @return Particio corresponent al tall de l'arbre realitzat
   */
  Particio tallClasses(int numClasses, String nom) {
    double idn;
    Particio par;
    Vector aux;
    NodeBinari node, fe, fd;
    int i, j;
    ArrayList llista;
    String etiq;

    /* Per obtenir numClasses cal desplegar numClasses-1 nodes */
    aux = new Vector();
    node = arrel;
    idn = node.obtenirIndexNivell();
    aux.add(node);
    for (i = 1; i < numClasses; i++) {
      node = (NodeBinari) aux.remove(0);
      fe = node.obtenirFillEsq();
      j = 0;
      while ( (j < aux.size()) &&
             (fe.obtenirIndexNivell() <
              ( (NodeBinari) aux.get(j)).obtenirIndexNivell())) {
        j++;
      }
      aux.add(j, fe);
      fd = node.obtenirFillDret();
      j = 0;
      while ( (j < aux.size()) &&
             (fd.obtenirIndexNivell() <
              ( (NodeBinari) aux.get(j)).obtenirIndexNivell())) {
        j++;
      }
      aux.add(j, fd);
      idn = (node.obtenirIndexNivell() + ((NodeBinari) aux.get(0)).obtenirIndexNivell())/2;
    }
    aux.trimToSize();
    llista = new ArrayList();
    for (i = 0; i < aux.size(); i++) {
      llista.add( ( (NodeBinari) aux.get(i)).obtenirEtiqueta());
    }
    // Es crea la partició a partir del conjunt de nodes
    // obtingut del tall en el nombre de classes indicat
    par = new Particio(nom, llista);
    for (i = 0; i < llista.size(); i++) {
      etiq = (String) llista.get(i);
      node = (NodeBinari) nodes.get(etiq);
      desplegaClasse(par, etiq, node);
    }
    llParticions.put(nom, par);
    llIdnParts.put(nom,new Double(idn));
    return par;
  }

  double obtenirIdnParticio(String nomPart){
    return ((Double)llIdnParts.get(nomPart)).doubleValue();
  }

//ROBER

  /**
   * Funció que modifica l'atribut que guarda el pes de l'objecte que es la etiqueta
   * del NodeBinari que forma la arrel de l'arbre
   *
   * @ param pes.  pes de l'objecte que es la etiqueta del NodeBinari que forma la arrel de l'arbre
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

  public void modificarPesArrel ( float pes ) {
    pesArrel = pes;

  }

  /**
   * Funció que modifica l'atribut que guarda la fila de dades de l'objecte que es la etiqueta
   * del NodeBinari que forma la arrel de l'arbre
   *
   * @ param dades.  fila de dades de l'objecte que es la etiqueta del NodeBinari que forma la arrel de l'arbre
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

  void modificarFilaDades ( Dada [] dades ) {
    dadesArrel = dades;
  }

  /**
   * Funció que retorna l'atribut que guarda el pes de l'objecte que es la etiqueta
   * del NodeBinari que forma la arrel de l'arbre
   *
   * @ param
   * @ return float amb el valor del pes del objecte que es la etiqueta del NodeBinari
   * que forma la arrel de l'arbre
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */

  float obtenirPesArrel (){
    return pesArrel;
  }


  /**
   * Funció que retorna l'atribut que guarda la fila de dades de l'objecte que es la etiqueta
   * del NodeBinari que forma la arrel de l'arbre
   *
   * @ param
   * @ return Dada [] que conte la fila de dades de l'objecte que es la etiqueta del NodeBinari
   * que forma la arrel de l'arbre
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */


public  Dada[] obtenirDadesArrel (){
    return dadesArrel;
  }


  /**
   * Funció que retorna l'atribut que guarda la llista de Nodes que forman l'arbre
   * ordenades per index de nivell
   *
   * @ param
   * @ return Arraylist amb la llista de Nodes que forman l'arbre ordenades per index de nivell
   *
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Rober
   * CLASSIFICACIÖ
   * @version v.0
   */

public  ArrayList obtenirLlNodesIdn (){
    return llNodesIdn;
  }


  /**
   * Funció que crea un fitxer *.drm del arbre.
   * Aquest fitxer estarà format per una representació dels NodeBinari que forman l'arbre
   * ordenats per index de nivell y que tenen la següent sintaxis:
   * ( etiqueta indexNivell ( fillEsquerre fillDret))
   *
   * @ param nomMatriu.  El nom del gestorMatriu del cual hem fet la classificació.
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

  void crearFitxerDendo (String nomFitxer)  throws FileNotFoundException, FormatIncorrecteException, IOException {
    FitxerDendo fichero;
    String lin = new String();
    fichero = new FitxerDendo(nomFitxer);

    try {
      fichero.obrirPerEscriure(false);
      ArrayList lista = obtenirLlNodesIdn();
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
      fichero.tancarEsc();
    }
    catch (IOException e) {
    }
  }



  /**
   * Funció que crea una llista de nodes del arbre ordenada per index de nivell.
   *
   * @ param gv.  El GestorClasificacion amb el cual hem fet la classificació.
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



  void rellenarllNodesIdn ( GestorClasificacion gc )  {// throws FileNotFoundException, FormatIncorrecteException, IOException {

    String clase;
    int numObjetos = gc.obtenirNumObjetos();
	  System.out.println("cuantos objetos hay eh!"+numObjetos);
    double [] aux  = new double [numObjetos-1];
    int [] aux2 = new int [numObjetos-1];
    int posicion = gc.obtenirObjectesAux().obtenirLong()-1;


    for ( int i = numObjetos; i <= posicion; i++ ){
      clase = gc.obtenirIdentificador(i);
      aux2[i-numObjetos] = i;
      aux[i-numObjetos] = obtenirNode(clase).obtenirIndexNivell();
    }

    // Algoritmo ineficiente --  mejorar con quicksort o algún otro recursivo
    bubleSort (aux,aux2);

    while (  posicion >= numObjetos ) {
      clase = gc.obtenirIdentificador(aux2[posicion - numObjetos]);
      afegirALlistaIdn(obtenirNode(clase));
      posicion --;
    }
  }

  /**
   * Funció que ordenara per index de nivell els dos vectors passats com a paràmetre.
   *
   * @ param v.  Vector amb les distancies a ordenar.
   * @ param a.  Vector amb els indexos a ordenar.
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
  void bubleSort(double [] v, int[] a){
    int i, j;
    int tmp;
    double d;
    boolean sorted = false;

    for(i=0; i<v.length && !sorted; i++){
      sorted=true;
      for(j = v.length-1; j>i; j--){
        if(v[j]<v[j-1]){
          d = v[j];
          tmp = a[j];

          v[j]=v[j-1];
          a[j] = a[j-1];

          v[j-1] = d;
          a[j-1] = tmp;

          sorted = false;
        }//fin del if
      }//fin del for
    }//fin del for
  }

public void afegirNodesArbre (NodeBinari arbre) {
		NodeBinari node = arbre;
		if (null != node) { 
				String etiqueta = node.obtenirEtiqueta();
				if (!nodes.containsKey(etiqueta)) { nodes.put(etiqueta, node); }
			  afegirNodesArbre(node.obtenirFillDret());
			  afegirNodesArbre(node.obtenirFillEsq());
		}
  	}

	public void modificarNom(String string) {
		nom = string;
	}

	public void controlInversions(boolean b) {
		controlInversions = b;
	}
		
	public boolean obtenirControlInversions() {
		return controlInversions;
	}


}

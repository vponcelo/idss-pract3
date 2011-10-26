package jklass.nucli;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.*;
import java.util.StringTokenizer;

/**
 * Classe que conté totes el atributs i mètodes per gestionar l'estructura d'objectes
 * que formen la matriu de dades. Cada objecte tindrà una posició concreta dins
 * la llista d'objectes, aquesta posició indica quina fila ocupa a la matriu.
 * <p>Title: Java-Klass</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas,Laia Riera
 * @version v.4
 */
public class LlistaObjectes extends LlistaIndexos {
  private static Logger logger = Logger.getLogger(LlistaObjectes.class.getName());

  /**
   * Constructor que crea la llista d'objectes associada a la matriu m.
   * @param m referència a la matriu
   */
  public LlistaObjectes(GestorMatriu m){
    super(m);
  }

/**
 * Afegeix un objecte a la llista d'objetes, posant la infornació recopilada al StringTokenizer
 * @param st
 * @throws CreacioObjectesException
 */
//ROBER  añado el else en el if para tratar objetos con peso

  void afegirObjecte(StringTokenizer st) throws CreacioObjectesException{
    String token;
    Objecte obj = null;
    String id;
    int index;

    ObjecteClase objCl = null;
    float peso;

    try{
      token = st.nextToken(); // index numeric
      index = new Integer(token).intValue();
      token = st.nextToken(); // id
      id = token;
      token = st.nextToken();
      if (token.compareToIgnoreCase(Constants.NULO) == 0){
        obj = new ObjSimple(index, id);
        llista.add(obj);
        taulaPos.put(new String(obj.obtenirId()), new Integer(obtenirLong()-1));
      } else {
        //No es un objecte simple i per ara no es contemplen altres tipus d'objectes
        //throw new CreacioObjectesException("S'ha trobat un format d'objecte no contemplat.");

        peso = Float.parseFloat(token);
        objCl = new ObjecteClase(index, id, peso);
        llista.add(objCl);
        taulaPos.put(new String(objCl.obtenirId()), new Integer(obtenirLong()-1));

      }
    } catch (NoSuchElementException e){
      logger.warning("ERROR: Falta informació sobre un objecte.");
      throw new CreacioObjectesException("Falta informació sobre un objecte.");
    }
  }

	void afegirObjecte(Objecte obj) {
	  llista.add(obj);
	  taulaPos.put(new String(obj.obtenirId()), new Integer(obtenirLong()-1));
	}


  /**
   * Retorna l'objecte que ocupa la posició <code>index</code> de la llista d'objectes
   * @param index posició que ocupa l'objecte al que es vol accedir
   * @return Objecte de la posició indicada
   */
  Objecte obtenirObjecte(int index){
    Objecte obj;
    ArrayList llistaObjs = obtenirLlista();

    obj = (Objecte)llistaObjs.get(index);

    return obj;
  }

  LlistaObjectes obtenirCopia(GestorMatriu gm){
    LlistaObjectes copia = new LlistaObjectes(gm);

    copia.taulaPos = (Hashtable)this.taulaPos.clone();
    copia.llista = (ArrayList)this.llista.clone();

    return copia;
  }
//DISTANCIES*********************************************************************
 /** Retorna una llista amb els identificadors dels objectes
 * que formen la matriu de dades.
 *
 * @return llista d'objectes
 *
 * @author Jose I Mateos
 * @version v.0 1/5/06
 */
   public String[] llistarIDsObjs(){

    String[]llIds;
	int i;
    Objecte obj;

    llIds=new String[obtenirLong()];

    for (i=0; i<obtenirLong(); i++){
      obj = obtenirObjecte(i);
      llIds[i]=obj.obtenirId();
    }

    return llIds;
  }
 /**
 * Afegeix l'objecte obj dins de la matriu carregada
 *
 * @param obj es l'objecte a insertar
 * @throws CreacioObjectesException
 *
 * @author Jose I Mateos
 * @version v.0 25/6/06
 */
 public void afegirObj(Objecte obj) throws CreacioObjectesException{
    String id;

    id = obj.obtenirId();
    if (taulaPos.containsKey(id)) {
      throw new CreacioObjectesException(
          "Ja existeix un objecte anomenat " + id + ".");
    }
    else {
      llista.add(obj);
      taulaPos.put(id, new Integer(obtenirLong() - 1));
      logger.fine("Afegida la propietat " + obj.obtenirId());
    }
  }


  //  Funcion nuevas que necesito  --  Rober
  /**
   * Funcio que inserta un noy objecte amb parametres
   *
   * @ param indice.  Index númeric assignat a l'objecte
   * @ param identif.  Identificador assignat a l'objecte, que es correspon amb el nom de l'objecte.
   * @ param peso.  Pes que té l'objecte
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

  void afegirObjecteClase( int indice, String identif, float peso){
    Objecte obj;
    Integer i = new Integer ( indice );

    obj = new ObjecteClase(indice,identif,peso);
    llista.add(obj);
    taulaPos.put(new String(obj.obtenirId()), new Integer(obtenirLong() - 1));

  }
  
/////////////////////////LAIA///////////////////////////////////////////////
  /**
   * Obté un vector amb les identificadors dels objectes de la matriu de dades actual
   * @return String[] que conté els identificadors dels objectes de la matriu
   * @author Laia Riera Guerra
   */
  String[] llistarIDsObjectes(){
 	  Vector aux=new Vector();
 	  for(int i=0;i<this.llista.size();i++){
 		  Objecte ob=this.obtenirObjecte(i);
 		  aux.add((String)ob.obtenirId());
 	  }
 	 String[] result=new String[aux.size()];
 	 for(int j=0;j<aux.size();j++)result[j]=(String)aux.get(j);
 	 return result;
  }



}

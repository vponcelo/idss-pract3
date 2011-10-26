   package jklass.nucli;

   import java.io.File;
   import java.io.FileNotFoundException;
   import java.io.IOException;
   import java.lang.Integer;
   import java.text.ParseException;
   import java.util.ArrayList;
   import java.util.Arrays;
   import java.util.Hashtable;
   import java.util.Iterator;
   import java.util.List;
   import java.util.logging.*;
   import java.util.StringTokenizer;
   import java.util.Vector;
	import java.text.NumberFormat;
	import java.lang.Math;
   import java.lang.Number; 

   import jklass.nucli.Constants;
   import jklass.util.Opcions;
   import jklass.util.OpcionsDis;
import jklass.nucli.CalcDis;
import jklass.iu.PanelClassificaEst;

/**
 * Classe que engloba totes les estructures i metodes necessaris per treballar
 * amb una matriu de dades.
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas,Laia Riera Guerra
 * @version v.4
 */
    public class GestorMatriu {
      private static Logger logger=Logger.getLogger(GestorMatriu.class.getName());
   /**
   * Identificador de la matriu. Aquest identificador coincideix amb la posició
   * del vector de matriusCarregades, de GestorKlass, on es troba la matriu). Per
   * tant, l'identificador sempre serà un valor dins del rang del (0...maxMatrius-1)
   */
      private int id;
   /**
   * Nom de la matriu, és també els nom sense sufixos ni extensions que s'assignarà
   * per defecte al conjunt de fitxers que existeixin o es generin associats a la matriu,
   * ja siguin en format KLASS  o  .dat estàndar
   */
      public String nom;
   
   /**
   * Llista d'objectes associats a la matriu
   */
      private LlistaObjectes objectes;
   
   /**
   * Llista de propietats associades a la matriu
   */
      public LlistaPropietats propietats;
   
   /**
   * Llista de propietats associades a la matriu
   */
      private LlistaPropietats propietatsa;
   
   
   /**
   * Matriu de dades propiament dita (només les dades)
   */
      private MatriuDades dades;
   
   /**
   * Directori on es guarden els resultats generats per aquesta matriu de dades
   */
      private String dirResultats;
   
   /**
   * Llista dels identificadors de les propietats ordenats segons les preferències de visualització del usuari
   */
      private ArrayList ordreProps;
   
   /**
   * Llista dels identificadors de les propietats activas segons les preferències  del usuari
   */
      private ArrayList activeProps;
	public String [] estados = new String [10];

		
   public boolean activas; 
	
	public boolean marcado;
	public boolean activasel;
	public boolean estadosel;
	public String estadoactual;
   
   // private String[] activas;
	public String proceso;
   
   /**
   * Llista d'arbres carregats (associats a la matriu de dades)
   * ¡¡¡ De moment només es té un arbre associat a la matriu de dades!!!
   */
    //  private ArrayList llArbres;
		
		public ArrayList<ArbreClassif> llArbres = new ArrayList<ArbreClassif>();

		
		
		public ArrayList llEstados;
   
   /**
   * Llista de bases de coneixement associada a la matriu
   * @author Laia Riera Guerra
   */
      private LlistaBC llistaBC;
   
   /**
   * tipus=0 la columna és numèrica
   * tipus=1 la columna es categòrica               
   */
      private ArrayList tipusColumna; 
   
   /**
   * Identificador de l'arbre actual
   */
      private int arbreActual;
		static float MAX_VALUE;
   
       GestorMatriu(){
         id = -1;
         nom = "";
         dirResultats = "./resultats/";
         propietats = new LlistaPropietats(this);
         propietatsa = new LlistaPropietats(this);
      
         objectes = new LlistaObjectes(this);
         dades = null;
         ordreProps = null;
         activeProps = null;
      
         llArbres = new ArrayList();
         arbreActual = -1;
      /////////////////LAIA/////////////////////////////////////////////////////////
         llistaBC=null;
      ////////////////////////////////////////////////////////////////////////////
        llEstados=null;    
		}
   
       GestorMatriu(int posId, String fileName, LlistaPropietats llistaPropietats) {
         id = posId;
         nom = fileName;
         dirResultats = "./resultats/";
         propietats = llistaPropietats;
         propietatsa= llistaPropietats;
      
      //propietats.setM(this);
         objectes = new LlistaObjectes(this);
      //dades = new MatriuDades(objectes.obtenirLong(),  propietats.obtenirLong(), this);
         dades = null;
      //ordreProps = null;
         ordreProps = new ArrayList(propietats.obtenirLong());
         for (int i = 0; i < propietats.obtenirLong(); i++) {
            ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
         }
        // System.out.println( "y campeon entras aca????");
         activeProps = new ArrayList();
       //  for (int i = 0; i < propietats.obtenirLong(); i++) {
        //    activeProps.add(i, propietats.obtenirPropietat(i).obtenirId());
        // }
      
      
      
         llArbres = new ArrayList();
         arbreActual = -1;
			llEstados = new ArrayList();
      }
   
   /**
   * Constructor
   * @param nomDades nom de la matriu
   * @param idMatriu identificador numèric assignat a la matriu
   * @throws CreacioMatriuException si es produeix algun error en la creació de la
   * llista de propietats, la llista d'objectes o el conjunt de dades
   */
       GestorMatriu(String nomDades, int idMatriu) throws
       CreacioPropietatsException, CreacioObjectesException,
       CreacioMatriuException {
         File fResult;
         boolean ok;
         id = idMatriu;
         nom = nomDades;
      
         try {
            propietats = new LlistaPropietats(this);
            propietatsa = new LlistaPropietats(this);
         
            carregarPropietats();
         //	carregarPropietatsa();
            logger.fine("Creada la llista de propietats.");
            objectes = new LlistaObjectes(this);
            carregarObjectes();
            logger.fine("Creada la llista d'objectes.");
            dades = new MatriuDades(objectes.obtenirLong(),  propietats.obtenirLong(), this);
            carregarDades();
            logger.info("Carregades les dades");
            propietats.llista.trimToSize();
            ordreProps = new ArrayList(propietats.obtenirLong());
            for (int i = 0; i < propietats.obtenirLong(); i++) {
               ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
               logger.info("DEBUG - ordreProps["+ i +"] = " + ordreProps.get(i));
            
            }
         //////////////////alejandro
           // System.out.println( "a ver el largo de propietats"+ propietats.obtenirLong() );
            activeProps = new ArrayList();
           // for (int i = 0; i < propietats.obtenirLong(); i++) {
             //  activeProps.add(i, propietats.obtenirPropietat(i).obtenirId());
              // System.out.println( "a ver si las activas estan aca?"+  activeProps.get(i));                        
          // }
         
         activeProps.clear();
            activeProps.trimToSize();
            ordreProps.trimToSize();
            actualitzarDirResultats(new File(nom).getParent() + File.separator +
                              "resultats" + File.separator);
            llArbres = new ArrayList();
            arbreActual = -1;
         /////////////////LAIA/////////////////////////////////////////////////////////
            llistaBC=new LlistaBC(this);
				llEstados = new ArrayList();
         //////////////////////////////////////////////////////////////////////////  //
         }
             catch (CreacioPropietatsException e) {
               throw e;
            }
             catch (CreacioObjectesException e) {
               throw e;
            }
             catch (Exception e) {
               throw new CreacioMatriuException(e.getMessage());
            }
      }
   
	
	
	
	
   /**
   * Carrega la informació de les propietats associades a la matriu
   * (a partir del fitxer .pro associat a la matriu)
   * @throws CreacioPropietatsException si no es pot obrir el fitxer associat o
   * es produeix un error al crear les propietats
   */
       private void carregarPropietats() throws CreacioPropietatsException{
         FitxerPro fPro;
         StringTokenizer st;
      
         fPro = new FitxerPro(nom);
         try {
            fPro.obrirPerLlegir();
         }
             catch (FileNotFoundException e) {
               logger.warning("ERROR: No s'ha trobat el fitxer de propietats");
               throw new CreacioPropietatsException(
                  "No s'ha trobat el fitxer de propietats.");
            }
         st = fPro.llegirProp();
        // System.out.println( "st del cargar propietats"+st);
      
         while (st != null) {
            propietats.afegirPropietat(st);
         //	propietatsa.afegirPropietat(st);////////ale
            st = fPro.llegirProp();
         }
      }
   
   
 /**
   * Alejandro eliminar inactivas   */	
	
 // public void eliminarinac( String[] llMods ) throws CreacioPropietatsException{
   //      FitxerPro fPro;
     //    StringTokenizer st;
     //  String activas=null;
	//	 Propietat prop;
		 
	
	//	 System.out.println( "voy a entrar a buscar el activeProps " ); 
   //      for (int i = 0; i < llMods.length; i++) {
     //       activas = activas + " " + llMods[i];
		//	 System.out.println( "HOLA estoy en carrearPropietatsa con activas="+activas);	
    //     }
						        
			
	//	 for (int s = 0; s < llMods.length; s++) {
		 
		//  try {
           // propietats.afegirProp(obtenirPropietat(llMods[s]));
			 //  System.out.println( "Voy a entrar al  obtenir");
			 //  prop =  obtenirPropietat(llMods[s]);
		//		System.out.println( " sali del obtenir");	
    //        propietats.eliminarPropietat(prop);			
			//	}
		
		 // catch (Exception ex) {
     //             ex.printStackTrace();
               //Any other exception handling...
       //        }
		
		     // }
 //  }






/*
   
   /**
   * Alejandro cargar propiedades activas   */
 //      private void carregarPropietatsa(String[] llMods) throws CreacioPropietatsException{
   //      FitxerPro fPro;
	//		Propietat prop;
         //StringTokenizer st;
   //    System.out.println( " ESTOY EN PROPIETATSAAAAA  " );
		 
     //    String activas=new String();
		//	StringTokenizer st = new StringTokenizer(activas); 

     // System.out.println( "voy a entrar a buscar el activeProps " ); 
      //   for (int i = 0; i < llMods.length; i++) {
      //      activas = activas + " " + llMods[i];
		//	 System.out.println( "HOLA estoy en carrearPropietatsa con activas="+activas);	
      //   }
						        
			
	//	 for (int s = 0; s < llMods.length; s++) {
		 
	//	  try {
           // propietats.afegirProp(obtenirPropietat(llMods[s]));
	//		   System.out.println( "Voy a entrar al  obtenir");
			//   prop =  obtenirPropietat(llMods[s]);
		//		System.out.println( " sali del obtenir");	
    //        propietats.afegirProp(prop);
			  
			
			//	}
		
		 // catch (Exception ex) {
     //             ex.printStackTrace();
               //Any other exception handling...
  //             }
		
			 	
   //      }
	
			
			
			
     // StringTokenizer st = new StringTokenizer(activas);
      
        // String [] arractivas = new String [activeProps.size()];
      
      //alejandro 
        // for (int p = 0; p < activeProps.size(); p++) {
         //    ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
         // System.out.println( "tengo las activas aca????"+  activas.get(p));
        //    arractivas[p]=(String)activeProps.get(p);
         // System.out.println( "tengoactivas en array????"+  arractivas[p]);
         
       //  }
            
        // fPro = new FitxerPro(nom);
        // try {
        //    fPro.obrirPerLlegir();
        // }
          //   catch (FileNotFoundException e) {
            //   logger.warning("ERROR: No s'ha trobat el fitxer de propietats");
              // throw new CreacioPropietatsException(
                //  "No s'ha trobat el fitxer de propietats.");
           // }
      // st = fPro.llegirProp();
      // while (st != null) 
 

//while(st.hasMoreTokens()){ 
//System.out.println(tokens.nextToken()); 
 //System.out.println( "Voy a entrar al afegirPropietat");

   // propietats.afegirPropietat(st);
 //System.out.println( "sali del afegirpropietat");
	 
         
        // }
   //   }
   
   
   
   
   
   
   
   
   
   
   /**
   * Carrega la informació dels objectes associats a la matriu
   * (a partir del fitxer .obj associat a la matriu)
   * @throws CreacioObjectesException si es produeix algun error al llegir el
   * fitxer d'objectes o al crear els objectes
   */
       private void carregarObjectes() throws CreacioObjectesException{
         FitxerObj fObj;
         StringTokenizer st;
      
         fObj = new FitxerObj(nom);
         try {
            fObj.obrirPerLlegir();
         }
             catch (FileNotFoundException e) {
               logger.warning("ERROR: No s'ha trobat el fitxer d'objectes.");
               throw new CreacioObjectesException("No s'ha trobat el fitxer d'objectes.");
            }
         st = fObj.llegirObj();
         while (st != null) {
            objectes.afegirObjecte(st);
            st = fObj.llegirObj();
         }
      }
   
   /**
   * Carrega les dades associades a la matriu que estan al fitxer .dat
   * @throws CreacioMatriuException si es produeix algun error al llegir les
   * dades o el nombre de files i columnes no és correspon amb el nombre de
   * objectes i propietats de la matriu, respectivament.
   * @throws CreacioPropietatsException si es produeix algun error al actualizar
   *  la informació de les propietats amb les dades llegides
   */
       private void carregarDades() throws CreacioMatriuException, CreacioPropietatsException{
         FitxerDat fDat;
         StringTokenizer st;
         int i=0, numFiles;
      
         fDat = new FitxerDat(nom);
         try
         {
            fDat.obrirPerLlegir();
         } 
             catch (FileNotFoundException e) {
               logger.warning("ERROR: No s'ha trobat el fitxer de dades");
               throw new CreacioMatriuException("No s'ha trobat el fitxer de dades.");
            }
      
         st = fDat.llegirFilaDades();
         while (st != null) {
            dades.omplirDades(i, st);
            st = fDat.llegirFilaDades();
            i++;
         }
         numFiles = dades.obtenirNumFiles();
         if (i < numFiles){
            throw new CreacioMatriuException("Falten dades per algun objecte de la matriu.");
         } 
         else if (i > numFiles){
            throw new CreacioMatriuException("Sobren dades pels objectes definits a la matriu.");
         }
      }
   
   /**
   * Permet obtenir el nom de la matriu de dades que gestiona
   * @return nom de la matriu de dades
   */
       String obtenirNomMatriu(){
         return nom;
      }
   
   /**
   * Genera una còpia de la instància actual de GestorMatriu, incloses les ordenacions de propietats definides per l'usuari
   * @return nou GestorMatriu
   */
       GestorMatriu obtenirCopia(String nomMatriu, int idMatriu) {
         GestorMatriu nouGM = null;
         int i, j, f, c;
      
         nouGM = new GestorMatriu();
         nouGM.id = idMatriu;
         nouGM.nom = nomMatriu;
         nouGM.objectes = objectes.obtenirCopia(nouGM);
         nouGM.propietats = propietats.obtenirCopia(nouGM);
			nouGM.llistaBC= obtenirLlistaBC();//////////////////alejandro
         nouGM.ordreProps = (ArrayList) ordreProps.clone();
         nouGM.activeProps = (ArrayList) activeProps.clone();
         nouGM.dades = dades.obtenirCopia(nouGM);
         nouGM.dirResultats = dirResultats;
         return nouGM;
      }
   
   /**
   * Genera els fitxers nom.dat, nom.obj i nom.pro corresponents a la matriu en
   * el format estàndar de KLASS. Al fitxer de propietats, les propietats es
   * guarden seguint l’ordre definit per l’usuari,i per tant es perd l'ordre del
   * fitxer original. Al fitxer de dades les columnes seguiran l'ordre guardat
   * al fitxer de propietats, per tant també es pot perdre l'ordre original.
   * En el cas de les modalitats d'una propietat es guardant seguint l'ordre
   * establert per l'usuari, i per tant es perd l'ordre del fitxer original.
   * @param nom nom sense extensió dels fitxers que es generen
   */
   
   //Rober lo cambio para que se guarden los objetos con peso
       void desarMatriu(String nomFitxer) throws IOException{
         FitxerPro fPro;
         FitxerObj fObj;
         FitxerDat fDat;
         int i, lon, n, index;
         float min, max;
         PropNumerica proN = null;
         PropCategorica proC = null;
         PropNominal proNom = null;
         PropOrdinal proOrd = null;
         String nomClas, nomPro; //Nom de la classe de Propietat i nom de la propietat (id)
         int llColsOrd[]; //llista d'indexos de columnes ordenats segons l'ordre de propietats establert per l'usuari
         Objecte obj;
      
      // Es crea el fitxer .pro
         fPro = new FitxerPro(nomFitxer);
         logger.info("DEBUG - desarMatriu - nomFitxer = " + nomFitxer);
         fPro.obrirPerEscriure(false);
         lon = ordreProps.size();
         llColsOrd = new int[lon];
         logger.info("DEBUG - desarMatriu - ordreProps.size = " + ordreProps.size());
         for (i=0; i< lon; i++ ){
            nomPro = (String)ordreProps.get(i);
            index = propietats.obtenirIndex(nomPro);
            llColsOrd[i] = index;
            nomClas = propietats.obtenirPropietat(index).getClass().getName();
            n = dades.obtenirNumFiles();
         //no funciona fent proN.getClass().getName()
            if (nomClas.compareTo("jklass.nucli.PropNumerica") == 0){
               proN = (PropNumerica)propietats.obtenirPropietat(index);
               min = proN.obtenirRangMin();
               max = proN.obtenirRangMax();
               fPro.escriurePropNumerica(i, nomPro, min, max, 1, n );
            } 
            else if (nomClas.compareTo("jklass.nucli.PropCategorica") == 0){
               proC = (PropCategorica)propietats.obtenirPropietat(index);
               fPro.escriurePropCategorica(i, nomPro, 1, n, proC.llModsOrdre);
            } 
            else if (nomClas.compareTo("jklass.nucli.PropNominal") == 0){
               proNom = (PropNominal)propietats.obtenirPropietat(index);
               fPro.escriurePropNominal(i, nomPro, 1, n, proNom.llModsOrdre);
            } 
            else if (nomClas.compareTo("jklass.nucli.PropOrdinal") == 0){
               proOrd = (PropOrdinal)propietats.obtenirPropietat(index);
               fPro.escriurePropOrdinal(i, nomPro, 1, n, proOrd.llModsOrdre);
            }
         }
         fPro.tancarEsc();
         logger.info("Creat el fitxer .pro per la matriu de dades actual.");
      
      // Es crea el fitxer .obj
         fObj = new FitxerObj(nomFitxer);
         fObj.obrirPerEscriure(false);
         lon = objectes.obtenirLong();
         for (i=0; i< lon; i++ ){
            obj = objectes.obtenirObjecte(i);
         
         //Rober lo cambio para que se guarden los objetos con peso
         //fObj.escriureObjecte(obj.obtenirIndex(), obj.obtenirId());
            fObj.escriureObjecte(obj.obtenirIndex(), obj.obtenirId(), obj.obtenirPes());
         
         }
         fObj.tancarEsc();
         logger.info("Creat el fitxer .obj per la matriu de dades actual.");
      
      // Es crea el fitxer .dat, seguint l'ordre de columnes establert al
      // fitxer .pro i que s'ha guardat  a llColsOrd
         fDat = new FitxerDat(nomFitxer);
         fDat.obrirPerEscriure(false);
         lon = dades.obtenirNumFiles();
         for (i=0; i< lon; i++ ){
            fDat.escriureDades(dades.obtenirArrayFilaReordenada(i,llColsOrd));
         }
         fDat.tancarEsc();
         logger.info("Creat el fitxer .dat per la matriu de dades actual.");
      
      // S'assigna el nom del nou grup de fitxers associats
         nom = nomFitxer;
      }
   
   /** (PENDENT D'IMPLEMENTAR) Crea una nova matriu que només conté les files i columnes indicades per la
   *  columnes indicades per la llista d'identificadors de les propietats i dels objectes
   * @param llistaIDProps llista amb els noms de les propietats a incloure a la nova matriu
   * @param llistaIDObjs llista amb els noms dels objectes a incloure a la nova matriu
   * @return GestorMatriu nova matriu creada
   * @throws CreacioMatriuException si no es pot crear la nova matriu
   */
       GestorMatriu crearSubmatriu(String[] llistaIDProps, String[] llistaIDObjs) throws CreacioMatriuException{
      /**@todo Cal acabar la implementació d'aquesta funcionalitat:
       * matriusCarregades[i] = matriusCarregades[matriuActual].crearSubmatriu(llVars, null); */
         return null;
      }
   
   /**
   * Obté les dades de la columna de la propietat amb nom <code> pro </code>
   * @param pro nom de la propietat
   * @return vector amb totes les dades de la columna indicada
   */
       Dada[] obtenirColumna(String pro) {
         int j;
         Dada[] columna;
      
         j = propietats.obtenirIndex(pro);
         columna = dades.obtenirColumna(j);
         return columna;
      }
   
   /**
   * Obté les dades de la fila del objecte amb nom <code> obj </code>
   * @param obj nom de la propietat
   * @return vector amb totes les dades de la fila indicada
   */
       Dada[] obtenirFila(String obj) {
         int i;
         Dada[] fila;
      
      //i = propietats.obtenirIndex(obj);
         i = objectes.obtenirIndex(obj);
         fila = dades.obtenirFila(i);
         return fila;
      }
   
   /**
   * Funció que obre el fitxer *.his el nom del cual pasem com a parametre.
   *
   * @ param nomMatriu.  Nom de la matriu que coincideix amb el nom del fitxer a obrir.
   * @throws FileNotFoundException
   * @throws FormatIncorrecteException
   * @throws IOException
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
   
   //  ROBER  --  Hago que el arbol se añada a la ultima posicion del atributo de clase llArbres
       void carregarHis(String nomMatriu) throws FileNotFoundException, FormatIncorrecteException, IOException {
         FitxerHis fHis;
         GestorMatriu nouGM = null;
         StringTokenizer st;
         String lin, etiq, idn, esq, dret;
         NodeBinari pare, nEsq, nDret;
         ArbreClassif arbre;
         String prefix;
         
      
         fHis = new FitxerHis(nomMatriu);
      
         fHis.obrirPerLlegir();
      /* Es llegeix tota la info prèvia al arbre de classificació sense tractar-la. */
         lin = fHis.llegirLinia(); // nom dades
      
         lin = fHis.llegirLinia(); // ponderar
         lin = fHis.llegirLinia(); // hi ha miss
         lin = fHis.llegirLinia(); // modus de normalització
         lin = fHis.llegirLinia(); // objs a usar
         lin = fHis.llegirLinia(); // propietats a usar
         lin = fHis.llegirLinia(); // metrica
         lin = fHis.llegirLinia(); // criteri
         lin = fHis.llegirLinia(); // limit
         lin = fHis.llegirLinia(); // alfa
         lin = fHis.llegirLinia(); // beta
         lin = fHis.llegirLinia(); // gamma
         lin = fHis.llegirLinia(); // id_lclass (prefixe de les classes)
      //prefix = lin.substring(0, lin.length()-2); //interessa agafar com a prefixe a eliminar tot excepte l'ultim caracter
      // Es passa del prefix indicat al fitxer
         prefix = "classe";
         lin = fHis.llegirLinia(); // arrel arbre
         lin = fHis.llegirLinia(); // vector corresponent a les components de l'arrel de l'arbre
         lin = fHis.llegirLinia(); // nombre total de propietats
         lin = fHis.llegirLinia(); // llista d'objectes
         lin = fHis.llegirLinia(); // llista de propietats
         lin = fHis.llegirLinia(); // llista d'efectius
      // es llegeix i es crea l'arbre binari de classificació
         st = fHis.llegirNode();
         if ((st != null) && (st.countTokens() != 4)) {
            throw new FormatIncorrecteException("Format del fitxer HIS incorrecte.");
         }
         etiq = st.nextToken();
         idn = st.nextToken();
         esq = st.nextToken();
         dret = st.nextToken();
         nEsq = new NodeBinari(esq);
         nDret = new NodeBinari(dret);
         pare = new NodeBinari(etiq, new Double(idn).doubleValue(), nEsq, nDret);
         arbre = new ArbreClassif(pare, new File(nomMatriu).getName(), prefix);
         arbre.afegirALlistaIdn(pare);
      /**@todo Més endavant es podria afegir al final de la llista d'arbres carregats: llArbres.add(arbre);*/
      
         arbreActual = llArbres.size();
      //RoberControl
      //llArbres.add(0,arbre);
         llArbres.add(arbre);
      
         arbre.afegirNode(nEsq);
         arbre.afegirNode(nDret);
         st = fHis.llegirNode();
         while (st != null) {
            if (st.countTokens() == 4) {
               etiq = st.nextToken();
               idn = st.nextToken();
               esq = st.nextToken();
               dret = st.nextToken();
               nEsq = new NodeBinari(esq);
               nDret = new NodeBinari(dret);
               pare = arbre.obtenirNode(etiq);
               if (pare == null) {
               // no s'ha afegit encara el node
                  pare = new NodeBinari(etiq, new Double(idn).doubleValue(), nEsq,
                                nDret);
                  arbre.afegirNode(pare);
                  arbre.afegirALlistaIdn(pare);
               } 
               else {
                  pare.modifIndexNivell(new Double(idn).doubleValue());
                  arbre.afegirALlistaIdn(pare);
                  pare.modifFillEsq(nEsq);
                  pare.modifFillDret(nDret);
               }
               arbre.afegirNode(nEsq);
               arbre.afegirNode(nDret);
               st = fHis.llegirNode();
            }
            else {
               throw new FormatIncorrecteException("Format del fitxer HIS incorrecte.");
            }
         }
         fHis.tancarLec();
         logger.info("Llegit el fitxer d'història " + nomMatriu + ".his");
         logger.finer("Arbre de classificació amb "+ arbre.obtenirNumNodes() + " nodes, d'ells " + arbre.obtenirNumFulles() + " són fulles" );
      }
   
       public void resizeNumDades(int numNewObj) throws CreacioPropietatsException {
      /*MatriuDades oldDades = dades;
      int oLen = objectes.obtenirLong();
      dades = new MatriuDades(oLen + numNewObj,  propietats.obtenirLong(), this);
      for (int i = 0; i < oLen; i++) {
      	dades.omplirDades(i, oldDades.obtenirFila(i));	
      }*/
         dades.ampliar(numNewObj, 0);
      }
   
       public void afegirObjecte(Objecte obj, Dada[] registre) throws CreacioPropietatsException {
         int currentIndex = objectes.obtenirLong();
         objectes.afegirObjecte(obj);
         dades.omplirDades(currentIndex, registre);	
      }
     
       public void afegirClass(String ident, float pes, Dada[] registre) throws CreacioPropietatsException {
         int currentIndex = objectes.obtenirLong();
         objectes.afegirObjecteClase(currentIndex, ident, pes);
         dades.omplirDades(currentIndex, registre);	
      }
     
   /**
   * Funció que obre el fitxer *.drm el nom del cual pasem com a parametre.
   *
   * @ param nomMatriu.  Nom de la matriu que coincideix amb el nom del fitxer a obrir.
   * @throws FileNotFoundException
   * @throws FormatIncorrecteException
   * @throws IOException
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
   
   
   
   // Funcion nueva  ROBER
       void carregarLni(String nomMatriu) throws FileNotFoundException, FormatIncorrecteException, IOException {
         FitxerDendo fDen;
         GestorMatriu nouGM = null;
         StringTokenizer st;
         String lin, etiq, idn, esq, dret;
         NodeBinari pare, nEsq, nDret;
         ArbreClassif arbre;
         String prefix;
      
         prefix = "classe";
         fDen = new FitxerDendo(nomMatriu);
      
         fDen.obrirPerLlegir();
      /* Es llegeix tota la info prèvia al arbre de classificació sense tractar-la. */
      
      // es llegeix i es crea l'arbre binari de classificació
         st = fDen.llegirNode();
         if ((st != null) && (st.countTokens() != 4)) {
            throw new FormatIncorrecteException("Format del fitxer Dendo incorrecte.");
         }
         etiq = st.nextToken();
         idn = st.nextToken();
         esq = st.nextToken();
         dret = st.nextToken();
         nEsq = new NodeBinari(esq);
         nDret = new NodeBinari(dret);
         pare = new NodeBinari(etiq, new Double(idn).doubleValue(), nEsq, nDret);
         arbre = new ArbreClassif(pare, new File(nomMatriu).getName(), prefix);
         arbre.afegirALlistaIdn(pare);
      /**@todo Més endavant es podria afegir al final de la llista d'arbres carregats: llArbres.add(arbre);*/
      
      //RoberControl
      //llArbres.add(0,arbre);
         llArbres.add(arbre);
      
         arbre.afegirNode(nEsq);
         arbre.afegirNode(nDret);
         st = fDen.llegirNode();
         while (st != null) {
            if (st.countTokens() == 4) {
               etiq = st.nextToken();
               idn = st.nextToken();
               esq = st.nextToken();
               dret = st.nextToken();
               nEsq = new NodeBinari(esq);
               nDret = new NodeBinari(dret);
               pare = arbre.obtenirNode(etiq);
               if (pare == null) {
               // no s'ha afegit encara el node
                  pare = new NodeBinari(etiq, new Double(idn).doubleValue(), nEsq,
                                nDret);
                  arbre.afegirNode(pare);
                  arbre.afegirALlistaIdn(pare);
               } 
               else {
                  pare.modifIndexNivell(new Double(idn).doubleValue());
                  arbre.afegirALlistaIdn(pare);
                  pare.modifFillEsq(nEsq);
                  pare.modifFillDret(nDret);
               }
               arbre.afegirNode(nEsq);
               arbre.afegirNode(nDret);
               st = fDen.llegirNode();
            }
            else {
               throw new FormatIncorrecteException("Format del fitxer Dendo incorrecte.");
            }
         }
         fDen.tancarLec();
         logger.info("Llegit el fitxer Dendo " + nomMatriu + ".his");
         logger.finer("Arbre de classificació amb "+ arbre.obtenirNumNodes() + " nodes, d'ells " + arbre.obtenirNumFulles() + " són fulles" );
      }
   
   
   
   /**
   *
   * @param nomClas
   * @param nomCls
   * @throws FileNotFoundException
   * @throws CreacioMatriuException
   * @throws FormatIncorrecteException
   * @throws IOException
   * @throws CreacioPropietatsException
   */
       void afegirClassificacio(String nomClas, String nomCls) throws FileNotFoundException, CreacioMatriuException, FormatIncorrecteException, IOException, CreacioPropietatsException{
         FitxerCls fCls;
         StringTokenizer st;
         String classe;
         Hashtable taulaFreqs = new Hashtable();
         Object obj;
         int nLin, i, j, c;
         boolean  vacio=true;
      // vacio = activeProps.isEmpty();
        // System.out.println("entre al integraclassificacio"+vacio);
      
         fCls = new FitxerCls(nomCls);
      //Preparem la matriu de dades afegint una columna per la var. de classe
         dades.ampliar(0,1);
			
         propietats.afegirPropCateg(nomClas);
			
         ordreProps.add(ordreProps.size(),nomClas);
         activeProps.add(activeProps.size(),nomClas);
      
         c = dades.obtenirNumColumnes() - 1;
         fCls.obrirPerLlegir();
         nLin = 0;
         st = fCls.llegirObj();
         j = propietats.obtenirIndex(nomClas);
         while (st != null) {
            nLin++;
            if (st.countTokens() == 2) {
               i = objectes.obtenirIndex(st.nextToken());
               classe = st.nextToken();
               propietats.nouValorLliure(j, classe);
               dades.modificarDada(i, c, new Dada(classe));
               st = fCls.llegirObj();
            }
            else {
               throw new FormatIncorrecteException("Format del fitxer CLS incorrecte.");
            }
         }
         fCls.tancarLec();
      }
		
		
	  void afegirVar(String nomClas, String nomCls) throws FileNotFoundException, CreacioMatriuException, FormatIncorrecteException, IOException, CreacioPropietatsException{
         FitxerVar fCls;
         StringTokenizer st;
         String classe;
         Hashtable taulaFreqs = new Hashtable();
         Object obj;
         int nLin, i, j, c;
         boolean  vacio=true;
      // vacio = activeProps.isEmpty();
        // System.out.println("entre al integraclassificacio"+vacio);
      
         fCls = new FitxerVar(nomCls);
      //Preparem la matriu de dades afegint una columna per la var. de classe
         dades.ampliar(0,1);
         propietats.afegirPropCateg(nomClas);
         ordreProps.add(ordreProps.size(),nomClas);
         activeProps.add(activeProps.size(),nomClas);
      
         c = dades.obtenirNumColumnes() - 1;
         fCls.obrirPerLlegir();
         nLin = 0;
         st = fCls.llegirObj();
         j = propietats.obtenirIndex(nomClas);
         while (st != null) {
            nLin++;
				System.out.println("Que hay en st"+st);
            if (st.countTokens() == 2) {
               i = objectes.obtenirIndex(st.nextToken());
               classe = st.nextToken();
					System.out.println("Que hay en classe"+classe);
               propietats.nouValorLliure(j, classe);
               dades.modificarDada(i, c, new Dada(classe));
               st = fCls.llegirObj();
            }
            else {
               throw new FormatIncorrecteException("Format del fitxer CLS incorrecte.");
            }
         }
         fCls.tancarLec();
      }
	
	
 void afegirVarNum(String nomClas, String nomCls) throws FileNotFoundException, CreacioMatriuException, FormatIncorrecteException, IOException, CreacioPropietatsException{
         FitxerVar fCls;
         StringTokenizer st,st2;
         String classe;
         Hashtable taulaFreqs = new Hashtable();
         Object obj;
         int nLin, i, j, c;
         boolean  vacio=true;
      // vacio = activeProps.isEmpty();
         System.out.println("entre al AfegirVarNum"+vacio);
         float min;
			float max;
         fCls = new FitxerVar(nomCls);
      //Preparem la matriu de dades afegint una columna per la var. de classe
         dades.ampliar(0,1);
			// Dada[] col;
			  //System.out.println("que hay en NomClas"+nomClas);
			// col= obtenirColumna(nomClas);
			 
			// for (int f = 0;f< 10;f++){
			// System.out.println("a ver que hay en col afegir num"+ col[f].obtenirValor());
			// }
			
			//Encontrar minimio y maximo
			 fCls.obrirPerLlegir();
         int nLin2=0,j2 = 0;
         st2 = fCls.llegirObj();
         j2 = propietats.obtenirIndex(nomClas);
			String classe2;
			
		//	int i2,minint=0,maxint=Integer.MAX_VALUE ,minintant=Integer.MAX_VALUE,maxintant=0;
			 float i2;
			 float minint;
			 float maxint=Float.MAX_VALUE;
			 float minintant=Float.MAX_VALUE;
			 float maxintant= Float.MIN_VALUE;
			 while (st2 != null) {
            nLin2++;
            if (st2.countTokens() == 2) {
               i2 = objectes.obtenirIndex(st2.nextToken());
               classe2 = st2.nextToken();
              // propietats.nouValorLliure(j, classe);
              // dades.modificarDada(i, c, new Dada(classe));
				 // System.out.println("a ver classe2"+ classe2);
               st2 = fCls.llegirObj();
				   //minint =Integer.parseInt(classe2);
					//maxint =Integer.parseInt(classe2);
					 minint =Float.parseFloat(classe2);
					maxint =Float.parseFloat(classe2);


	         // System.out.println("a ver minint"+ classe2);
			    
				 if (minintant > minint)
				    minintant=minint;
					 
				 if (maxintant < maxint)
				    maxintant=maxint;
	 
					 
				 					
            }
            else {
               throw new FormatIncorrecteException("Format del fitxer CLS incorrecte.");
            }
				}
				fCls.tancarLec();		
			/////finencontrar minimo y maximo
			
			    min=minintant;
				 max=maxintant;
		 
         propietats.afegirPropNum(nomClas,min,max);
         ordreProps.add(ordreProps.size(),nomClas);
         activeProps.add(activeProps.size(),nomClas);
      
         c = dades.obtenirNumColumnes() - 1;
         fCls.obrirPerLlegir();
         nLin = 0;
         st = fCls.llegirObj();
         j = propietats.obtenirIndex(nomClas);
         while (st != null) {
            nLin++;
            if (st.countTokens() == 2) {
               i = objectes.obtenirIndex(st.nextToken());
               classe = st.nextToken();
               propietats.nouValorLliure(j, classe);
               dades.modificarDada(i, c, new Dada(classe));
               st = fCls.llegirObj();
				
					
            }
            else {
               throw new FormatIncorrecteException("Format del fitxer CLS incorrecte.");
            }
         }
			
         fCls.tancarLec();
						
			
      }
	
	
	
	
	///////////////
	
	
		

 void afegirMat(String nomCls) throws FileNotFoundException, CreacioMatriuException, FormatIncorrecteException, IOException, CreacioPropietatsException{
         FitxerDat fCls;
         StringTokenizer st;
         String classe;
         Hashtable taulaFreqs = new Hashtable();
         Object obj;
         int nLin, i, j, c;
         boolean  vacio=true;
      // vacio = activeProps.isEmpty();
        // System.out.println("entre al integraclassificacio"+vacio);
       
         fCls = new FitxerDat(nomCls);
      //Preparem la matriu de dades afegint una columna per la var. de classe
         dades.ampliar(0,1);
         propietats.afegirPropCateg(nomCls);
         ordreProps.add(ordreProps.size(),nomCls);
         activeProps.add(activeProps.size(),nomCls);
      
         c = dades.obtenirNumColumnes() - 1;
			 System.out.println("a ver cuanto vale c"+c);
         fCls.obrirPerLlegir();
         nLin = 0;
			 System.out.println("llegue aca en afegirmat2");
        // st = fCls.llegirObj();
			st=fCls.llegirFilaDades();
		
		System.out.println("cuantos tokens"+st.countTokens() );
			         j = propietats.obtenirIndex(nomCls);
         while (st != null) {
            nLin++;
            if (st.countTokens() == 2) {
               i = objectes.obtenirIndex(st.nextToken());
               classe = st.nextToken();
						System.out.println("hay algo en clase"+classe );
               propietats.nouValorLliure(j, classe);
						System.out.println("a ver nLin0"+nLin );

               dades.modificarDada(i, c, new Dada(classe));
					System.out.println("a ver nLin1"+nLin );
               //st = fCls.llegirObj();
					st=fCls.llegirFilaDades();
					System.out.println("a ver nLin2"+nLin );
            }
            else {
               throw new FormatIncorrecteException("Format del fitxer CLS incorrecte.");
            }
         }
         fCls.tancarLec();
      }
	


		
		
		
		
		
   
   /**@todo En desús, LAIA LA REIMPLEMENTA
   * Afegeix les propietats i les dades corresponents a aquestes propietats a GestorMatriu
   * @param llistaProps llista de propietats (on l'index de cada propietat indica la columna a dades que ocupen les seves dades
   * @param dades matriu de dades per les propietats indicades a llistaProps
   *        pels mateixos objectes (i en el mateix ordre) que els de la matriu on s'afegeix
   * @return nou GestorMatriu per la nova matriu de dades que inclou les columnes afegides
   */
       GestorMatriu afegirColumnes(String nomMatriu, int idMatriu, LlistaPropietats llistaProps, MatriuDades mDades) throws CreacioMatriuException, CreacioPropietatsException{
         GestorMatriu nouGM = null;
         int i, j, k,  nFiles, nCols, c, n, m, index;
         Propietat pro;
         Dada dada;
      
         nouGM = new GestorMatriu();
         nouGM.id = idMatriu;
         nouGM.nom = nomMatriu;
         nouGM.objectes = objectes.obtenirCopia(nouGM);
         nouGM.propietats = propietats.obtenirCopia(nouGM);
         nFiles = this.dades.obtenirNumFiles();
         nCols = this.dades.obtenirNumColumnes();
         n = llistaProps.obtenirLong();
         m = dades.obtenirNumFiles();
         if (m != nFiles) {
            throw new CreacioMatriuException("Falten dades per algun objecte de la matriu.");
         }
         nouGM.dades = new MatriuDades(nFiles, nCols + n, nouGM);
         k = 0;
         for (i=0; i<n; i++){
            pro = llistaProps.obtenirPropietat(i);
            nouGM.propietats.afegirProp(pro);
            nouGM.ordreProps.add(ordreProps.size(), pro.obtenirId());
            nouGM.activeProps.add(activeProps.size(), pro.obtenirId());
            for (j=0; j<m ; j++) {
               dada = dades.obtenirDada(i,j);
               nouGM.propietats.nouValorLliure(j, dada.obtenirValor().toString());
               nouGM.dades.modificarDada(i, nCols + k, dada);
               k++;
            }
         }
      // es copien la resta de dades
         for (i = 0; i < nFiles; i++) {
            for (j = 0; j < nCols; j++) {
               nouGM.dades.modificarDada(i, j, this.dades.obtenirDada(i, j));
            }
         }
         nouGM.dirResultats = dirResultats;
         return nouGM;
      }
   
   
   
  public  LlistaObjectes obtenirLlistaObjs() {
         return objectes;
      }
   
       String[] obtenirLlistaMods(String prop) {
         String[] llista = new String[1];
         int i = propietats.obtenirIndex(prop);
      
         llista = (String[])((PropCategorica)propietats.obtenirPropietat(i)).llModalitats.toArray(llista);
         return llista;
      }
   
   /** Es retorna una llista de registres per a cada element del domini de la propietat 
   * Nota: no utilitzarem el métode MatriuDades.ampliar(fil, col) per ser massa costosa.
   **/
       GestorMatriuDividida[] dividirBDD(String nomVar) throws CreacioPropietatsException /*, ParamIncorrecteException*/{
         return dividirBDD(nomVar, false);
      }
   
       GestorMatriuDividida[] dividirBDD(String nomVar, boolean removeVar) throws CreacioPropietatsException /*, ParamIncorrecteException*/{
      //residualMatrix = NO_RESIDUAL;
         int i = propietats.obtenirIndex(nomVar);
         PropCategorica propietat = ((PropCategorica)propietats.obtenirPropietat(i));
         ArrayList domini = propietat.llModalitats;
         int size = domini.size();
         GestorMatriuDividida[] res = new GestorMatriuDividida[size];
      /* Es genera un array list per a saber la posició a l'array que li correspon a cada element del domini */
         Hashtable domProperty = new Hashtable();
         for (int j = 0; j < size; j++) {
            domProperty.put(domini.get(j), new Integer(j)); 
         }
      /* Recorrem la matriu de dades, ubicant cada registre a la nova matriu que li correspon segons el valor de la propietat. */
       //  System.out.println("Num Files: "+dades.obtenirNumFiles());
      //	for (int k = 0; k < dades.obtenirNumFiles(); k++) {
         for (int k = 0; k < objectes.obtenirLong(); k++) {
            Objecte o = objectes.obtenirObjecte(k);
            Dada[] registre = dades.obtenirFila(objectes.obtenirIndex(o.obtenirId()));
            Object propValue = registre[i].obtenirValor();
            Integer iMatrix = (Integer)domProperty.get(propValue);
            int numMatrix = iMatrix.intValue();
            if (null == res[numMatrix]) {
               LlistaPropietats newLProp = propietats.obtenirCopia(this);
               if (removeVar) { newLProp.eliminarProp(i); }
               res[numMatrix] =  new GestorMatriuDividida(id, nom, newLProp, propValue.toString());
            	/*if (RESIDUAL.equalsIgnoreCase(propValue)) {
            		residualMatrix = k; 
            	}*/
            
            }
            ((GestorMatriu)res[numMatrix]).objectes.afegirObjecte(o.clon());
         }
      // Omplenem la matriu de dades
         for (int l = 0; l < size; l++) {
            GestorMatriu currentManagement = res[l];
            if (null != currentManagement) {
               currentManagement.inicialitzarDades();
               LlistaObjectes currentObjectes = currentManagement.objectes; 
               for (int m = 0; m < currentObjectes.obtenirLong(); m++) {
                  String objId = currentObjectes.obtenirObjecte(m).obtenirId(); 
                  int generalIndex = objectes.obtenirIndex(objId);
                  Dada[] registre = dades.obtenirFila(generalIndex);
                  int currentIndex = currentObjectes.obtenirIndex(objId);
                  if (removeVar) {
                     currentManagement.dades.omplirDades(currentIndex, registre, i);
                  } 
                  else {
                     currentManagement.dades.omplirDades(currentIndex, registre);
                  }
               }
            }
         }
         return res;
      }
     
       void establirOrdreMods(String nomVar, String[] llMods) throws ParamIncorrecteException{
         int i = propietats.obtenirIndex(nomVar);
         ((PropCategorica)propietats.obtenirPropietat(i)).actualitzarOrdreMods(llMods);
      }
   
       void establirOrdreVars(String[] llVars) throws ParamIncorrecteException{
         int i;
      
         ordreProps.clear();
         for (i = 0; i<llVars.length; i++){
            ordreProps.add(i,llVars[i]);
         }
         logger.finest("Nou ordre de vars: " + ordreProps.toString());
      }
		
		
 void establirActiveVars(String[] llVars) throws ParamIncorrecteException{
         int i;
      
         activeProps.clear();
         for (i = 0; i<llVars.length; i++){
            activeProps.add(i,llVars[i]);
         }
         logger.finest("Nou ordre de vars: " + activeProps.toString());
      }
		
		
 public int LenActiveProps(){
   
   return  activeProps.size();
       
  }
		
		
		
   
   ///////////////////////// alejandro   
     //  void establirActiveVars(String[] llVars) throws ParamIncorrecteException{
		 
       //  int i;
        // String[][] llPropsa;
        // Propietat prop =null;
      
        // activeProps.clear();
        // for (i = 0; i<llVars.length; i++){
          //  activeProps.add(i,llVars[i]);
        // }
      
        // String [] arractivas = new String [activeProps.size()];
      
        // for (int r = 0; r < activeProps.size(); r++) {
         //   arractivas[r]=(String)activeProps.get(r);
        // }
      		
       //  for (int q = 0; q < activeProps.size(); q++) {
         //   try{  
          //     prop =  obtenirPropietat(arractivas[q]);
           // }
             //   catch (Exception ex) {
              //    ex.printStackTrace();
               
               //}
         
           // try{
           //    propietatsa.afegirProp(prop);
           // }
             //   catch (Exception ex) {
              //    ex.printStackTrace();
               
              // }
         		 
         	 
         	  
       //  }
      
        
        // llPropsa = propietatsa.llistarIDsPropietats();
		// System.out.println( "HOLA CREE PROPIETATSA!!!");
	
    //     System.out.println( "llPropsa[0].length"+llPropsa[0].length);
      //   System.out.println( "llPropsa[1].length"+llPropsa[1].length);
			// System.out.println( "llPropsa[2].length"+llPropsa[2].length);
			 // System.out.println( "llPropsa[3].length"+llPropsa[3].length);


      
        // System.out.println( "llPropsa[0][0]"+llPropsa[0][0]);
        // System.out.println( "llPropsa[1][0]"+llPropsa[1][0]);
        // System.out.println( "llPropsa[1][1]"+llPropsa[1][1]);
      
        
       
        // logger.finest("Nou ordre de vars: " + activeProps.toString());
     // }
   
   
	
	
	
   
   
   /**
   * Realitza l'Anàlisi Descriptiva de tipus pertinent (Univariant, Bivariant,
   * Trivariant o Per Classes) indicat a les opcions amb els estadístics indicats
   * a les llistes d'estadístics i per les variables indicades a les llistes.
   * @param llistaVar Llistat de les variables a estudiar. Pel cas Univariant: <br>
   *  llistaVars[0][] - Conté la llista de vars numeriques <br>
   *  llistaVars[1][] - Conté la llista de vars categoriques<br>
   * Pel cas Bivariant: <br>
   * llistaVars[0][] - Conté la llista de vars numeriques X de NN <br>
   * llistaVars[1][] - Conté la llista de vars numeriques Y de NN <br>
   * llistaVars[2][] - Conté la llista de vars numeriques X de NC<br>
   * llistaVars[3][] - Conté la llista de vars categoriques Y de NC<br>
   * llistaVars[4][] - Conté la llista de vars numeriques X de CC<br>
   * llistaVars[5][] - Conté la llista de vars categoriques Y de CC<br>
   * Pel cas Trivariant: <br>
   * llistaVars[0][] - Conté la llista de vars numeriques X de NNC  <br>
   * llistaVars[1][] - Conté la llista de vars numeriques Y de NNC <br>
   *  llistaVars[2][] - Conté la llista de vars categoriques Z de NNC <br>
   * llistaVars[3][] - Conté la llista de vars categoriques X de CCN <br>
   *  llistaVars[4][] - Conté la llista de vars categoriques Y de CCN <br>
   * llistaVars[5][] - Conté la llista de vars numeriques Z de CCN <br>
   * Pel cas Per Classe: <br>
   * llistaVars[0][] - Conté la llista de vars categ de classe <br>
   * llistaVars[1][] - Conté la llista de vars numeriques<br>
   * llistaVars[2][] - Conté la llista de vars categoriques<br>
   *
   * @param llistaEstads Llistat dels estadistics que s'han de calcular.
   * Pel cas Univariant: <br>
   * llistaEstads[0] - Conté la llista d'estad. numerics <br>
   * llistaEstads[1] - Conté la llista d'estad. categorics<br>
   * Pel cas Bivariant: <br>
   * llistaEstads[0] - Conté la llista d'estad. NN <br>
   * llistaEstads[1] - Conté la llista d'estad. NC <br>
   * llistaEstads[2] - Conté la llista d'estad. CC <br>
   * Pel cas Trivariant: <br>
   * llistaEstads[0] - Conté la llista d'estad. NNC <br>
   * llistaEstads[1] - Conté la llista d'estad. CCN <br>
   * Pel cas Per Classe: <br>
   * llistaEstads[0] - Conté la llista d'estad. NN<br>
   * llistaEstads[1] - Conté la llista d'estad. NC<br>
   * llistaEstads[2] - Conté la llista d'estad. CC<br>
   * @param opc opcions dels tipus dels estadístics de tipus d'anàlisi descriptiva a realitzar
   * @throws CreacioFitxerException si es produexi un error al crear el fitxer .tex
   * @throws OpcioIncorrectaException si una de les opcions és incorrecta
   * @throws ParamIncorrecteException si les llistes de variables o estadístics tenen alguna incorrecció
   * @throws CalculException si es produeix un error al realitzar els càlculs necessaris
   */
       void ferDescrip(String[][] llistaVar, Vector[] llistaEstads, Opcions opc) throws CreacioFitxerException, OpcioIncorrectaException, ParamIncorrecteException, CalculException {
         FitxerTex fitxer;
         String nomBase, nomFitxer;
         GeneradorTex gen;
         char tipus;
      
         tipus = opc.obtenirTipusOpcions();
         nomBase = dirResultats + new File(nom).getName();
      	 	 
      
         try {
            switch (tipus) {
               case Opcions.UNIVARIANT:
                  nomFitxer = nomBase + "Univ";
               // Es fan el calculs necesaris per tots els estadistics demanats
                  CalculsUniv[][] calculsU = ferCalculsUniv(llistaVar,
                     llistaEstads, opc);
                  logger.fine("Calculs per descr. univariant realitzats.");
                  fitxer = new FitxerTex(nomFitxer);
                  gen = new GeneradorTex(fitxer);
                  gen.generarLtxUnivarPerVars(ordreProps, calculsU, llistaVar, llistaEstads, opc, false);
                  break;
               case Opcions.BIVARIANT:
                  nomFitxer = nomBase + "Biv";
                  CalculsBiv[][] calculsB = ferCalculsBiv(llistaVar, llistaEstads, opc);
                  logger.fine("Calculs per descr. bivariant realitzats.");
                  fitxer = new FitxerTex(nomFitxer);
                  gen = new GeneradorTex(fitxer);
                  gen.generarLtxBivarPerVars(ordreProps, calculsB, llistaVar, llistaEstads, opc, false);
                  break;
               case Opcions.TRIVARIANT:
                  nomFitxer = nomBase + "Triv";
                  CalculsTriv[][] calculsT = ferCalculsTriv(llistaVar, llistaEstads, opc);
                  logger.fine("Calculs per descr. trivariant realitzats.");
                  fitxer = new FitxerTex(nomFitxer);
                  gen = new GeneradorTex(fitxer);
                  gen.generarLtxTrivarPerVars(ordreProps, calculsT, llistaVar, llistaEstads, opc, false);
                  break;
               case Opcions.PER_CLASSES:
                  nomFitxer = nomBase + "Class";
                  CalculsClass[] calculsC = ferCalculsClass(llistaVar,
                     llistaEstads, opc);
                  logger.fine("Calculs per descr. per classes realitzats.");
                  fitxer = new FitxerTex(nomFitxer);
                  gen = new GeneradorTex(fitxer);
               
               //alejandro 
                 // for (int i = 0; i < ordreProps.size(); i++) {
                  //    ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
                    // System.out.println( "antes de generarlatex en ferdescr"+  ordreProps.get(i));
                 // }
               // for (int i = 0; i < activeProps.size(); i++) {
               //    ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
               // System.out.println( "antes de generarlatex activeprops"+  activeProps.get(i));
               // }
               /*   if (soloactivas){
                     gen.generarLtxClassesPerVars(activeProps, calculsC, llistaVar[0], llistaEstads, opc, false);
                  }
                  else{
                     gen.generarLtxClassesPerVars(ordreProps, calculsC, llistaVar[0], llistaEstads, opc, false);
                  }*/
               
                gen.generarLtxClassesPerVars(ordreProps, calculsC, llistaVar[0], llistaEstads, opc, false);
                  break;
            }
         
         }
             catch (CreacioFitxerException e) {
               throw e;
            }
             catch (IOException e) {
               throw new CreacioFitxerException("Error d'escriptura: " + e.getMessage());
            }
      
      }
   
   
   
   /* ESTE ESTA OK*//*
   ArrayList ferActiv() 
   {   					
   		 for (int i = 0; i < ordreProps.size(); i++) {
   	  System.out.println( "antes de generarlatex en ferdescr"+  ordreProps.get(i));
     
      }
   return ordreProps;		
   
   }*/
   
   
   //alejandro
       ArrayList ferActiv() 
      {   					
        // for (int i = 0; i < activeProps.size(); i++) {
          //  System.out.println( "ferActiv en gestormatriz"+  activeProps.get(i));
            
         //}
         return activeProps;		
      
      }
   
   
   
   
   
   
       private float[] recuperarDadesPropietat(int pos, int nutils){
         Dada[] colDades;
         float datos[];
         String val;
         int i, j;
      
         colDades = dades.obtenirColumna(pos);
         datos = new float[nutils];
         i=0;
         for (j = 0; j < colDades.length; j++) {
            val = (String) colDades[j].obtenirValor();
            if (val.compareTo("?") != 0) {
               datos[i] = Float.parseFloat(val);
               i++;
            }
         }
         return datos;
      }
   
   /**
   * Realitza l'Anàlisi Descriptiva Univariant indicat a les opcions amb els estadístics indicats
   * a les llistes d'estadístics i per les variables indicades a les llistes.
   * @param llistaVar Llistat de les variables a estudiar: <br>
   *  llistaVars[0][] - Conté la llista de vars numeriques <br>
   *  llistaVars[1][] - Conté la llista de vars categoriques<br>
   * @param llistaEstads Llistat dels estadistics que s'han de calcular: <br>
   * llistaEstads[0] - Conté la llista d'estad. numerics <br>
   * llistaEstads[1] - Conté la llista d'estad. categorics<br>
   * @param opc opcions dels tipus dels estadístics de l'anàlisi descriptiva a realitzar
   * @return estructura que conté tots el càlculs de l'Anàlisi Descriptiva
   * Univariant a partir dels quals es pot generar un fitxer de sortida
   */
       private CalculsUniv[][] ferCalculsUniv(String[][] llistaVar, Vector llistaEstads[], Opcions opc){
         int i, j, k, l, est, lon;
         boolean ok;
         CalculsUniv[][] calculs = new CalculsUniv[2][];
         CalculsUnivNum calcNum;
         CalculsUnivCateg calcCateg;
         int pos, iNum1, iNum2, n;
         float fNum1, fNum2, min, max, dada, pas;
         double dNum;
         ArrayList llistaProps = propietats.obtenirLlista();
         PropNumerica proNum;
         EstadisticsNum estadsN;
         float dades[], resultats[];
         int[] percentiles = new int[3];
         PropCategorica proCat;
         EstadisticsCateg estadsC;
         Iterator llistaModal;
         String modal;
         float freq, acum, rel, relAcum;
         Hashtable taulaOpc;
      
      // Tractament de la llista de vars Numeriques
         lon = llistaVar[0].length;
         calculs[0] = new CalculsUnivNum[lon];
         for (i = 0; i < lon; i++) {
            pos = propietats.obtenirIndex(llistaVar[0][i]);
            proNum = (PropNumerica)llistaProps.get(pos);
            j = 0;
            calculs[0][i] = new CalculsUnivNum();
            calcNum = (CalculsUnivNum)calculs[0][i];
            estadsN = proNum.obtenirEstadistics();
            dades = null;
            while (j < llistaEstads[0].size()){
               est = ( (Integer) llistaEstads[0].get(j)).intValue();
               switch (est) {
                  case Opcions.ESTAD_SUM:
                     calcNum.afegirCalcRealitzat(est);
                     iNum1 = estadsN.obtenirNumObjs();
                     calcNum.afegirNumObjs(iNum1);
                     iNum2 = estadsN.obtenirNumMissings();
                     calcNum.afegirNumMancants(iNum2);
                     calcNum.afegirNumUtils(iNum1 - iNum2);
                     fNum1 = estadsN.obtenirMin();
                     calcNum.afegirMin(fNum1);
                     fNum2 = estadsN.obtenirMax();
                     calcNum.afegirMax(fNum2);
                     calcNum.afegirAmplitud(fNum2 - fNum1);
                     calcNum.afegirMitjana(estadsN.obtenirMitjana());
                     calcNum.afegirVar(estadsN.obtenirVariancia());
                     calcNum.afegirQuasiVar(estadsN.obtenirQuasiVariancia());
                     calcNum.afegirDesv(estadsN.obtenirDesvTipica());
                     calcNum.afegirQuasiDesv(estadsN.obtenirQuasiDesvTip());
                     calcNum.afegirCoefCorr(estadsN.obtenirCoefVariacio());
                  // Estadistics Robustos
                     if (dades == null) {
                     // recuperem les dades necessàries si no ho em fet abans per algun altre estadistic
                        dades = recuperarDadesPropietat(pos, calcNum.obtenirNumUtils());
                     }
                     calcNum.afegirDades(dades);
                     resultats = estadsN.calcularQuartils(dades);
                     calcNum.afegirMediana(resultats[0]);
                     calcNum.afegirQ1(resultats[1]);
                     calcNum.afegirQ3(resultats[2]);
                     calcNum.afegirDistInterQ(resultats[2] - resultats[1]);
                     logger.info("Realitzats calculs previs per estadistics sumaris.");
                     break;
                  case Opcions.RESUM_5:
                     calcNum.afegirCalcRealitzat(est);
                     if (!(calcNum.calculRealitzat(Opcions.ESTAD_SUM))) {
                        iNum1 = estadsN.obtenirNumObjs();
                        calcNum.afegirNumObjs(iNum1);
                        iNum2 = estadsN.obtenirNumMissings();
                        calcNum.afegirNumMancants(iNum2);
                        calcNum.afegirNumUtils(iNum1 - iNum2);
                        fNum1 = estadsN.obtenirMin();
                        calcNum.afegirMin(fNum1);
                        fNum2 = estadsN.obtenirMax();
                        calcNum.afegirMax(fNum2);
                     // Estadistics Robustos
                        if (dades == null) {
                        // recuperem les dades necessàries si no ho em fet abans per algun altre estadistic
                           dades = recuperarDadesPropietat(pos, calcNum.obtenirNumUtils());
                        }
                        calcNum.afegirDades(dades);
                        resultats = estadsN.calcularQuartils(dades);
                        percentiles[0] = 25;
                        percentiles[1] = 50;
                        percentiles[2] = 75;
                     //resultats = estadsN.calculaMediana(dades, percentiles);
                        calcNum.afegirMediana(resultats[0]);
                        calcNum.afegirQ1(resultats[1]);
                        calcNum.afegirQ3(resultats[2]);
                        calcNum.afegirDistInterQ(resultats[2] - resultats[1]);
                     //calcNum.afegirQ3(resultats[3]);
                     //calcNum.afegirDistInterQ(resultats[3] - resultats[1]);
                     }
                     logger.info("Realitzats calculs previs per resum en 5 números.");
                     break;
                  case Opcions.HISTO:
                     calcNum.afegirCalcRealitzat(est);
                     if (!(calcNum.calculRealitzat(Opcions.ESTAD_SUM))) {
                        iNum1 = estadsN.obtenirNumObjs();
                        calcNum.afegirNumObjs(iNum1);
                        iNum2 = estadsN.obtenirNumMissings();
                        calcNum.afegirNumMancants(iNum2);
                        calcNum.afegirNumUtils(iNum1 - iNum2);
                        min = estadsN.obtenirMin();
                        calcNum.afegirMin(min);
                        max = estadsN.obtenirMax();
                        calcNum.afegirMax(max);
                     } 
                     else {
                        min = calcNum.obtenirMin();
                        max = calcNum.obtenirMax();
                     }
                  
                     taulaOpc = opc.obtenirOpcionsEstad(Opcions.HISTO);
                     if ( ((String) taulaOpc.get("limitsX")).equals("teoric")) {
                        min = proNum.obtenirRangMin();
                        max = proNum.obtenirRangMax();
                        taulaOpc.put("minXT",
                           (new Float(min)).toString());
                        taulaOpc.put("maxXT",
                           (new Float(max)).toString());
                     } 
                     else if (((String) taulaOpc.get("limitsX")).equals("def")){
                        min = Float.parseFloat((String)taulaOpc.get("minX"));
                        max = Float.parseFloat((String)taulaOpc.get("maxX"));
                     }
                     if (dades == null) {
                     // es recuperen les dades necessàries si no ho em fet abans per algun altre estadistic
                        dades = recuperarDadesPropietat(pos, calcNum.obtenirNumUtils());
                     }
                  // s'ordenen les dades de forma ascendent
                     Arrays.sort(dades);
                     n = dades.length;
                  // es determina el nombre de classes de freqüència
                     if ( ((String)taulaOpc.get("classes")).equals("auto")){
                        if (n < 100) {
                           dNum = Math.floor(6 * (Math.log(n) / Math.log(10)));
                        }
                        else {
                           dNum = Math.floor( (1.2 * Math.sqrt(n)));
                        }
                        iNum1 = new Double(dNum).intValue(); //nombre de classes
                     } 
                     else { // el nombre de classes vé definit per l'usuari
                        iNum1 = Integer.parseInt((String)taulaOpc.get("numclass"));
                     }
                     calcNum.inicialitzarLlistaRang(iNum1);
                     calcNum.inicialitzarLlistaFreq(iNum1);
                     pas = (max - min) / iNum1;
                     l = 0;
                     k = 0;
                     dada = dades[k];
                     fNum1 = min;
                     fNum2 = min + pas;
                     ok = true;
                     while (l < iNum1) {
                        iNum2 = 0;
                        while (ok && (dada >= fNum1) && (dada < fNum2)) {
                           iNum2++;
                           if (k < n - 1) {
                              k++;
                              dada = dades[k];
                           }
                           else {
                              ok = false;
                           }
                        }
                     // tractament especial de l'ultima classe
                     // per incloure els valors iguals al maxim
                        if (l == (iNum1 - 1)) {
                           if (fNum2 < max) fNum2 = max;
                           while (ok && (dada == fNum2)) {
                              iNum2++;
                              if (k < n - 1) {
                                 k++;
                                 dada = dades[k];
                              }
                              else {
                                 ok = false;
                              }
                           }
                        }
                        calcNum.afegirALlistaRang(l, fNum1, fNum2);
                        calcNum.afegirALlistaFreq(l, (float) iNum2);
                        l++;
                        fNum1 = fNum2;
                        fNum2 = fNum2 + pas;
                     }
                     logger.info("Realitzats calculs previs per histograma.");
                     break;
                  case Opcions.BOXPLOT:
                     calcNum.afegirCalcRealitzat(est);
                     taulaOpc = opc.obtenirOpcionsEstad(Opcions.BOXPLOT);
                     if ( ((String)taulaOpc.get("limits")).equals("teoric")){
                        taulaOpc.put("minT", (new Float(proNum.obtenirRangMin())).toString());
                        taulaOpc.put("maxT", (new Float(proNum.obtenirRangMax())).toString());
                     }
                     if (!( calcNum.calculRealitzat(Opcions.ESTAD_SUM) ||
                     calcNum.calculRealitzat(Opcions.RESUM_5) ) ) {
                        iNum1 = estadsN.obtenirNumObjs();
                        calcNum.afegirNumObjs(iNum1);
                        iNum2 = estadsN.obtenirNumMissings();
                        calcNum.afegirNumMancants(iNum2);
                        calcNum.afegirNumUtils(iNum1 - iNum2);
                        fNum1 = estadsN.obtenirMin();
                        calcNum.afegirMin(fNum1);
                        fNum2 = estadsN.obtenirMax();
                        calcNum.afegirMax(fNum2);
                     // Estadistics Robustos
                        if (dades == null) {
                        // recuperem les dades necessàries si no ho em fet abans per algun altre estadistic
                           dades = recuperarDadesPropietat(pos, calcNum.obtenirNumUtils());
                        }
                        calcNum.afegirDades(dades);
                        resultats = estadsN.calcularQuartils(dades);
                        calcNum.afegirMediana(resultats[0]);
                        calcNum.afegirQ1(resultats[1]);
                        calcNum.afegirQ3(resultats[2]);
                        calcNum.afegirDistInterQ(resultats[2] - resultats[1]);
                     //calcNum.afegirQ3(resultats[3]);
                     //calcNum.afegirDistInterQ(resultats[3] - resultats[1]);
                     }
                     logger.info("Realitzats calculs previs per boxplot.");
                     break;
                  default:
                     logger.warning("Operació no vàlida");
                     break;
               }
               j++;
            }
         }
      
      // Tractament de la llista de vars Categoriques
         lon = llistaVar[1].length;
         calculs[1] = new CalculsUnivCateg[lon];
         for (i = 0; i < lon; i++) {
            pos = propietats.obtenirIndex(llistaVar[1][i]);
            proCat = (PropCategorica) llistaProps.get(pos);
            estadsC = proCat.obtenirEstadistics();
            j = 0;
            calculs[1][i] = new CalculsUnivCateg();
            calcCateg = (CalculsUnivCateg) calculs[1][i];
            while (j < llistaEstads[1].size()) {
               est = ( (Integer) llistaEstads[1].get(j)).intValue();
               switch (est) {
                  case Opcions.TAULA_FREQS:
                     calcCateg.afegirCalcRealitzat(est);
                     acum = 0;
                     relAcum = 0;
                     n = proCat.llModsOrdre.size();
                     calcCateg.inicialitzarLlistaModals(n);
                     calcCateg.inicialitzarLlistaFreq(n);
                     calcCateg.inicialitzarLlistaRel(n);
                     llistaModal = proCat.llModsOrdre.iterator();
                     iNum1 = estadsC.obtenirNumObjs();
                     calcCateg.afegirNumObjs(iNum1);
                     iNum2 = estadsC.obtenirNumMissings();
                     calcCateg.afegirNumMancants(iNum2);
                     iNum2 = iNum1 - iNum2;
                     calcCateg.afegirNumUtils(iNum2);
                     if (proCat instanceof PropNominal) {
                        calcCateg.afegirTipus(Constants.PROP_NOMINAL);
                        k = 0;
                        while (llistaModal.hasNext()) {
                           modal = llistaModal.next().toString();
                           calcCateg.afegirALlistaModals(k, modal);
                           freq = estadsC.obtenirFreq(modal);
                           calcCateg.afegirALlistaFreq(k, freq);
                           rel = freq / iNum2;
                           calcCateg.afegirALlistaRel(k, rel);
                           k++;
                        }
                     }
                     else if ( (proCat instanceof PropOrdinal) ||
                     (proCat instanceof PropCategorica)) {
                        if (proCat instanceof PropOrdinal)
                           calcCateg.afegirTipus(Constants.PROP_ORDINAL);
                        else
                           calcCateg.afegirTipus(Constants.PROP_CATEGORICA);
                        calcCateg.inicialitzarLlistaAcum(n);
                        calcCateg.inicialitzarLlistaRelAcum(n);
                        k = 0;
                        while (llistaModal.hasNext()) {
                           modal = llistaModal.next().toString();
                           calcCateg.afegirALlistaModals(k, modal);
                           freq = estadsC.obtenirFreq(modal);
                           calcCateg.afegirALlistaFreq(k, freq);
                           acum += freq;
                           calcCateg.afegirALlistaAcum(k, acum);
                           rel = freq / iNum2;
                           calcCateg.afegirALlistaRel(k, rel);
                           relAcum += rel;
                           calcCateg.afegirALlistaRelAcum(k, relAcum);
                           k++;
                        }
                     }
                     logger.info("Realitzats calculs previs per taula de frequencies.");
                     break;
                  case Opcions.DIAGR_BARRES:
                     calcCateg.afegirCalcRealitzat(est);
                     taulaOpc = opc.obtenirOpcionsEstad(Opcions.DIAGR_BARRES);
                     calcCateg.afegirMaxFreq(estadsC.obtenirMaxFreq());
                     if (! (calcCateg.calculRealitzat(Opcions.TAULA_FREQS))) {
                        iNum1 = proCat.llModsOrdre.size();
                        calcCateg.inicialitzarLlistaModals(iNum1);
                        calcCateg.inicialitzarLlistaFreq(iNum1);
                        calcCateg.inicialitzarLlistaRel(iNum1);
                        llistaModal = proCat.llModsOrdre.iterator();
                        iNum1 = estadsC.obtenirNumObjs();
                        calcCateg.afegirNumObjs(iNum1);
                        iNum2 = iNum1 - estadsC.obtenirNumMissings();
                        calcCateg.afegirNumUtils(iNum2);
                        k = 0;
                        while (llistaModal.hasNext()) {
                           modal = llistaModal.next().toString();
                           calcCateg.afegirALlistaModals(k, modal);
                           freq = estadsC.obtenirFreq(modal);
                           calcCateg.afegirALlistaFreq(k, freq);
                           calcCateg.afegirALlistaRel(k, (freq / iNum2));
                           k++;
                        }
                     }
                     logger.info("Realitzats calculs previs per diagrames de barres.");
                     break;
               
                  default:
                     logger.warning("Operació no vàlida");
                     break;
               }
               j++;
            }
         }
         return calculs;
      }
   
   /**
   * Realitza l'Anàlisi Descriptiva Bivariant indicat a les opcions amb els estadístics indicats
   * a les llistes d'estadístics i per les variables indicades a les llistes.
   * @param llistaVar Llistat de les variables a estudiar: <br>
   * llistaVars[0][] - Conté la llista de vars numeriques X de NN <br>
   *  llistaVars[1][] - Conté la llista de vars numeriques Y de NN <br>
   * llistaVars[2][] - Conté la llista de vars numeriques X de NC<br>
   * llistaVars[3][] - Conté la llista de vars categoriques Y de NC<br>
   * llistaVars[4][] - Conté la llista de vars numeriques X de CC<br>
   * llistaVars[5][] - Conté la llista de vars categoriques Y de CC<br>
   * @param llistaEstads Llistat dels estadistics que s'han de calcular: <br>
   * llistaEstads[0] - Conté la llista d'estad. NN <br>
   * llistaEstads[1] - Conté la llista d'estad. NC <br>
   * llistaEstads[2] - Conté la llista d'estad. CC <br>
   * @param opc opcions dels tipus dels estadístics de l'anàlisi descriptiva a realitzar
   * @return estructura que conté tots el càlculs de l'Anàlisi Descriptiva
   * Bivariant a partir dels quals es pot generar un fitxer de sortida
   * @throws ParamIncorrecteException si les llistes de variables o estadístics tenen alguna incorrecció
   * @throws CalculException si es produeix un error al realitzar els càlculs necessaris
   */
       private CalculsBiv[][] ferCalculsBiv(String[][] llistaVar, Vector llistaEstads[], Opcions opc) throws ParamIncorrecteException, CalculException{
      // llistaVars[0][] - Conté la llista de vars numeriques X de NN
      // llistaVars[1][] - Conté la llista de vars numeriques Y de NN
      // llistaVars[2][] - Conté la llista de vars numeriques X de NC
      // llistaVars[3][] - Conté la llista de vars categoriques Y de NC
      // llistaVars[4][] - Conté la llista de vars numeriques X de CC
      // llistaVars[5][] - Conté la llista de vars categoriques Y de CC
      
      // llistat dels estadistics que s'han de calcular
      // llistaEstads[0] - Conté la llista d'estad. NN
      // llistaEstads[1] - Conté la llista d'estad. NC
      // llistaEstads[2] - Conté la llista d'estad. CC
      
         CalculsBiv[][] calculs = new CalculsBiv[3][];
         CalculsBivNN calcNN;
         CalculsBivNC calcNC;
         CalculsBivCC calcCC;
         int i, j, k, est, lon, lonD;
         int[] llistaKs = new int[2];
         PropNumerica[] propsN = new PropNumerica[2];
         PropCategorica[] propsC = new PropCategorica[2];
         Dada[] dadesVarX, dadesVarY;
         int iVarN, iVarC;
         PropNumerica proN;
         PropCategorica proC;
         float min, max;
         String op;
         Hashtable opcEst;
      
      // Tractament de la llista de vars Num / Num
         if ((llistaVar[0] == null) || (llistaVar[1] == null)) {
            lon = 0;
         } 
         else {
            lon = llistaVar[0].length;
            if (llistaVar[1].length != lon) throw new ParamIncorrecteException("Nombre de params. incorrecte");
         }
         calculs[0] = new CalculsBivNN[lon];
         for (i = 0; i < lon; i++) {
            if ((llistaVar[0][i] != null) && (llistaVar[1][i] != null)){
               calculs[0][i] = new CalculsBivNN();
               calcNN = (CalculsBivNN) calculs[0][i];
               for (j = 0; j < llistaEstads[0].size(); j++) {
                  est = ( (Integer) llistaEstads[0].get(j)).intValue();
                  llistaKs[0] = propietats.obtenirIndex(llistaVar[0][i]);
                  llistaKs[1] = propietats.obtenirIndex(llistaVar[1][i]);
                  propsN[0] = (PropNumerica) propietats.obtenirPropietat(llistaKs[0]);
                  propsN[1] = (PropNumerica) propietats.obtenirPropietat(llistaKs[1]);
                  calcNN.afegirProps(propsN);
                  calcNN.afegirDadesX(dades.obtenirColumna(llistaKs[0]));
                  calcNN.afegirDadesY(dades.obtenirColumna(llistaKs[1]));
               
                  switch (est) {
                     case Opcions.CORREL:
                        calcNN.calcularCorrelacio();
                        calcNN.afegirCalcRealitzat(est);
                        break;
                     case Opcions.PLOT:
                        calcNN.afegirCalcRealitzat(est);
                        break;
                  }
               }
            }
         }
      // Tractament de la llista de vars Num / Categ
         if ((llistaVar[2] == null) || (llistaVar[3] == null)) {
            lon = 0;
         } 
         else {
            lon = llistaVar[2].length;
            if (llistaVar[3].length != lon) throw new ParamIncorrecteException("Nombre de params. incorrecte");
         }
         calculs[1] = new CalculsBivNC[lon];
         for (i = 0; i < lon; i++) {
            if ((llistaVar[2][i] != null) && (llistaVar[3][i] != null)){
               llistaKs[0] = propietats.obtenirIndex(llistaVar[2][i]);
               llistaKs[1] = propietats.obtenirIndex(llistaVar[3][i]);
               dadesVarX = dades.obtenirColumna(llistaKs[0]);
               dadesVarY = dades.obtenirColumna(llistaKs[1]);
               proN = (PropNumerica) propietats.obtenirPropietat(llistaKs[0]);
               proC = (PropCategorica) propietats.obtenirPropietat(llistaKs[1]);
               min = proN.obtenirEstadistics().obtenirMin();
               max = proN.obtenirEstadistics().obtenirMax();
               calculs[1][i] = new CalculsBivNC(proC.llModsOrdre, proN.obtenirRangMin(),
                                         proN.obtenirRangMax(), min, max);
               calcNC = (CalculsBivNC) calculs[1][i];
               lonD = dadesVarX.length;
               for (k = 0; k < lonD; k++) {
                  try {
                     calcNC.afegirValor( (String) dadesVarX[k].obtenirValor().toString(),(String) dadesVarY[k].obtenirValor().toString());
                  }
                      catch (CreacioPropietatsException e) {
                        throw new CalculException(e.getLocalizedMessage());
                     }
               }
               calcNC.ferCalculsUniv(llistaEstads[1], opc);
            }
         }
         logger.fine("Realitzats calculs previs per biv. de num. vs. cat.");
      
      // Tractament de la llista de vars Categ / Categ
         if ((llistaVar[4] == null) || (llistaVar[5] == null)) {
            lon = 0;
         } 
         else {
            lon = llistaVar[4].length;
            if (llistaVar[5].length != lon) throw new ParamIncorrecteException("Nombre de params. incorrecte");
         }
         calculs[2] = new CalculsBivCC[lon];
         for (i = 0; i < lon; i++) {
            if ((llistaVar[4][i] != null) && (llistaVar[5][i] != null)){
               llistaKs[0] = propietats.obtenirIndex(llistaVar[4][i]);
               llistaKs[1] = propietats.obtenirIndex(llistaVar[5][i]);
               dadesVarX = dades.obtenirColumna(llistaKs[0]);
               dadesVarY = dades.obtenirColumna(llistaKs[1]);
               propsC[0] = (PropCategorica) propietats.obtenirPropietat(llistaKs[0]);
               propsC[1]  = (PropCategorica) propietats.obtenirPropietat(llistaKs[1]);
               calculs[2][i] = new CalculsBivCC(propsC[0].llModsOrdre, propsC[1].llModsOrdre);
               calcCC = (CalculsBivCC) calculs[2][i];
               lonD = dadesVarX.length;
               for (k = 0; k < lonD; k++) {
                  try {
                     calcCC.afegirValor( (String) dadesVarX[k].obtenirValor(),
                               (String) dadesVarY[k].obtenirValor());
                  }
                      catch (CreacioPropietatsException e) {
                        throw new CalculException(e.getLocalizedMessage());
                     }
               }
               calcCC.ferCalculsUniv();
            }
         }
         logger.fine("Realitzats calculs previs per biv. de cat. vs. cat.");
         return calculs;
      }
   
   /**
   * Realitza l'Anàlisi Descriptiva Trivariant indicat a les opcions amb els estadístics indicats
   * a les llistes d'estadístics i per les variables indicades a les llistes.
   * @param llistaVar Llistat de les variables a estudiar: <br>
   * llistaVars[0][] - Conté la llista de vars numeriques X de NNC  <br>
   * llistaVars[1][] - Conté la llista de vars numeriques Y de NNC <br>
   *  llistaVars[2][] - Conté la llista de vars categoriques Z de NNC <br>
   * llistaVars[3][] - Conté la llista de vars categoriques X de CCN <br>
   *  llistaVars[4][] - Conté la llista de vars categoriques Y de CCN <br>
   * llistaVars[5][] - Conté la llista de vars numeriques Z de CCN <br>
   * @param llistaEstads Llistat dels estadistics que s'han de calcular: <br>
   * llistaEstads[0] - Conté la llista d'estad. NNC <br>
   * llistaEstads[1] - Conté la llista d'estad. CCN <br>
   * @param opc opcions dels tipus dels estadístics de l'anàlisi descriptiva a realitzar
   * @return estructura que conté tots el càlculs de l'Anàlisi Descriptiva
   * Trivariant a partir dels quals es pot generar un fitxer de sortida
   * @throws ParamIncorrecteException si les llistes de variables o estadístics tenen alguna incorrecció
   * @throws CalculException si es produeix un error al realitzar els càlculs necessaris
   */
       private CalculsTriv[][] ferCalculsTriv(String[][] llistaVar, Vector llistaEstads[], Opcions opc) throws ParamIncorrecteException, CalculException{
      // llistaVars[0][] - Conté la llista de vars numeriques X de NNC
      // llistaVars[1][] - Conté la llista de vars numeriques Y de NNC
      // llistaVars[2][] - Conté la llista de vars categoriques Z de NNC
      // llistaVars[3][] - Conté la llista de vars categoriques X de CCN
      // llistaVars[4][] - Conté la llista de vars categoriques Y de CCN
      // llistaVars[5][] - Conté la llista de vars numeriques Z de CCN
      
      // llistat dels estadistics que s'han de calcular
      // llistaEstads[0] - Conté la llista d'estad. NNC
      // llistaEstads[1] - Conté la llista d'estad. CCN
         CalculsTriv[][] calculs = new CalculsTriv[3][];
         CalculsTrivNNC calcNNC;
         CalculsTrivCCN calcCCN;
         int i, j, k, est, lon, lonD;
         int[] indexsN = new int[2];
         int[] indexsC = new int[2];;
         PropNumerica[] propsN = new PropNumerica[2];
         PropCategorica[] propsC = new PropCategorica[2];
         Dada[] dadesVarX, dadesVarY, dadesVarZ;
      
      // Tractament de la llista de vars Num / Num /Categ
         if ((llistaVar[0] == null) || (llistaVar[1] == null) || (llistaVar[2] == null)) {
            lon = 0;
         } 
         else {
            lon = llistaVar[0].length;
            if ( (llistaVar[1].length != lon) || (llistaVar[2].length != lon))
               throw new ParamIncorrecteException("Nombre de params. incorrecte");
         
         }
         calculs[0] = new CalculsTrivNNC[lon];
         for (i = 0; i < lon; i++) {
            if ((llistaVar[0][i] != null) && (llistaVar[1][i] != null)){
               calculs[0][i] = new CalculsTrivNNC();
               calcNNC = (CalculsTrivNNC) calculs[0][i];
               for (j = 0; j < llistaEstads[0].size(); j++) {
                  est = ( (Integer) llistaEstads[0].get(j)).intValue();
                  indexsN[0] = propietats.obtenirIndex(llistaVar[0][i]);
                  indexsN[1] = propietats.obtenirIndex(llistaVar[1][i]);
                  indexsC[0] = propietats.obtenirIndex(llistaVar[2][i]);
                  propsN[0] = (PropNumerica) propietats.obtenirPropietat(indexsN[0]);
                  propsN[1] = (PropNumerica) propietats.obtenirPropietat(indexsN[1]);
                  propsC[0] = (PropCategorica) propietats.obtenirPropietat(indexsC[0]);
                  calcNNC.afegirProps(propsN, propsC[0]);
                  calcNNC.afegirDadesX(dades.obtenirColumna(indexsN[0]));
                  calcNNC.afegirDadesY(dades.obtenirColumna(indexsN[1]));
                  calcNNC.afegirDadesZ(dades.obtenirColumna(indexsC[0]));
                  switch (est) {
                     case Opcions.LETTERPLOT:
                        calcNNC.afegirCalcRealitzat(est);
                        break;
                  }
               }
            }
         }
         logger.fine("Realitzats calculs previs per triv. de num. i num. vs. cat.");
      
      // Tractament de la llista de vars Categ / Categ / Num
         if ((llistaVar[3] == null) || (llistaVar[4] == null) || (llistaVar[5] == null)) {
            lon = 0;
         } 
         else {
            lon = llistaVar[3].length;
            if ( (llistaVar[4].length != lon) || (llistaVar[5].length != lon))
               throw new ParamIncorrecteException("Nombre de params. incorrecte");
         }
         calculs[1] = new CalculsTrivCCN[lon];
         for (i = 0; i < lon; i++) {
            if ((llistaVar[3][i] != null) && (llistaVar[4][i] != null)){
               indexsC[0] = propietats.obtenirIndex(llistaVar[3][i]);
               indexsC[1] = propietats.obtenirIndex(llistaVar[4][i]);
               indexsN[0] = propietats.obtenirIndex(llistaVar[5][i]);
               propsC[0] = (PropCategorica) propietats.obtenirPropietat(indexsC[0]);
               propsC[1] = (PropCategorica) propietats.obtenirPropietat(indexsC[1]);
               propsN[0] = (PropNumerica) propietats.obtenirPropietat(indexsN[0]);
               calculs[1][i] = new CalculsTrivCCN(propsC[0],propsC[1],propsN[0]);
               calcCCN = (CalculsTrivCCN)calculs[1][i];
               dadesVarX = dades.obtenirColumna(indexsC[0]);
               dadesVarY = dades.obtenirColumna(indexsC[1]);
               dadesVarZ = dades.obtenirColumna(indexsN[0]);
               lonD = dadesVarX.length;
               for (k = 0; k < lonD; k++) {
                  try {
                     calcCCN.afegirValor( (String) dadesVarX[k].obtenirValor(),
                               (String) dadesVarY[k].obtenirValor(),(String) dadesVarZ[k].obtenirValor());
                  }
                      catch (CreacioPropietatsException e) {
                        throw new CalculException(e.getLocalizedMessage());
                     }
               }
            
               for (j = 0; j < llistaEstads[1].size(); j++) {
                  est = ( (Integer) llistaEstads[1].get(j)).intValue();
               
                  switch (est) {
                     case Opcions.TAULA_CCN:
                        calcCCN.afegirCalcRealitzat(est);
                        break;
                  }
               }
            }
         }
         logger.fine("Realitzats calculs previs per triv. de cat. i cat. vs. num.");
         return calculs;
      }
   
   /**
   * Realitza l'Anàlisi Descriptiva Per Classes indicat a les opcions amb els estadístics indicats
   * a les llistes d'estadístics i per les variables indicades a les llistes.
   * @param llistaVar Llistat de les variables a estudiar: <br>
   * @param llistaEstads Llistat dels estadistics que s'han de calcular: <br>
   * @param opc opcions dels tipus dels estadístics de l'anàlisi descriptiva a realitzar
   * @return estructura que conté tots el càlculs de l'Anàlisi Descriptiva
   * Per Classes a partir dels quals es pot generar un fitxer de sortida
   * @throws ParamIncorrecteException si les llistes de variables o estadístics tenen alguna incorrecció
   * @throws CalculException si es produeix un error al realitzar els càlculs necessaris
   */
       private CalculsClass[] ferCalculsClass(String[][] llistaVar, Vector llistaEstads[], Opcions opc) throws ParamIncorrecteException, CalculException{
      // llistaVars[0][] - Conté la llista de vars categ de classe
      // llistaVars[1][] - Conté la llista de vars numeriques
      // llistaVars[2][] - Conté la llista de vars categoriques
      
      // llistat dels estadistics que s'han de calcular
      // llistaEstads[0] - Conté la llista d'estad. de var. de classe
      // llistaEstads[1] - Conté la llista d'estad. NC
      // llistaEstads[2] - Conté la llista d'estad. CC
         int i, j, k, nvars;
         String[][] llVar;
      // llVar[0][] - Conté la llista de vars numeriques X de NN
      // llVar[1][] - Conté la llista de vars numeriques Y de NN
      // llVar[2][] - Conté la llista de vars numeriques X de NC
      // llVar[3][] - Conté la llista de vars categoriques Y de NC
      // llVar[4][] - Conté la llista de vars categoriques X de CC
      // llVar[5][] - Conté la llista de vars categoriques Y de CC
         CalculsClass[] calculs;
         boolean particio;
         Particio par;
      
         calculs = new CalculsClass[llistaVar[0].length];
         particio = (llistaEstads[0] != null) && (llistaEstads[0].contains(new Integer(Opcions.DESCR_EXTENSIONAL)));
         for (i= 0; i < llistaVar[0].length; i++){
         //Es fan els calculs per cada variable de classe
            calculs[i] = new CalculsClass();
            if (particio) { //Cal fer la descripcio extensional
               par = generarParticio(llistaVar[0][i]);
               calculs[i].afegirParticio(par);
            }
         //Es preparen les llistes de variables per fer el calculs Biv necessaris
            nvars = llistaVar[1].length;
            llVar = new String[6][];
            llVar[0] = null;
            llVar[1] = null;
         // preparació Num. vs. Categ.(Classe)
            llVar[2] = new String[nvars];
            llVar[3] = new String[nvars];
            k = 0;
            for (j = 0; j < nvars; j++) {
               llVar[2][k] = llistaVar[1][j];
               llVar[3][k] = llistaVar[0][i];
               k++;
            }
         // preparació Categ. vs. Categ.(Classe)
            nvars = llistaVar[2].length;
            llVar[4] = new String[nvars];
            llVar[5] = new String[nvars];
            k=0;
            for (j = 0; j < nvars; j++) {
               llVar[4][k] = llistaVar[2][j];
               llVar[5][k] = llistaVar[0][i];
               k++;
            }
            CalculsBiv[][] calcBiv = ferCalculsBiv(llVar, llistaEstads, opc);
            calculs[i].afegirLlistaVars(llVar);
            calculs[i].afegirCalculBiv(calcBiv);
         }
         return calculs;
      }
   /**
   * Permet generar una particio utilitzant com a variable de classe la indicada com a parametre
   * @param var variable de classe que s'utilitza per crear la particio
   * @return estructura que conté la particio de la matriu de dades segons la variable de classe var
   */
       private Particio generarParticio(String var){
         Particio par;
         int i, j, nObjs;
         PropCategorica pro;
         String valor;
         String nom;
      
         j = propietats.obtenirIndex(var);
         pro =(PropCategorica)propietats.obtenirPropietat(j);
         par = new Particio(var,pro.llModsOrdre);
         nObjs = objectes.obtenirLong();
         for (i=0; i < nObjs; i++){
            valor = (String)dades.obtenirDada(i,j).obtenirValor();
            nom = objectes.obtenirObjecte(i).obtenirId();
            par.afegirObjecte(valor,nom);
         }
      
         return par;
      }
   /**
   * Permet transformar un fitxer .par que indica una partició llistant
   * els objectes que conté cada classe en un fitxer .cls que indica per cada
   * objecte a quina classe pertany
   * @param nomFitx nom del fitxer .par associat a la matriu de dades que gestiona
   * GestorMatriu
   * @throws FileNotFoundException
   * @throws FormatIncorrecteException
   * @throws IOException
   * @throws ParamIncorrecteException
   */
       void transformarParticio(String nomFitx) throws FileNotFoundException, FormatIncorrecteException, IOException, ParamIncorrecteException{
         FitxerPar fPar;
         FitxerCls fCls;
         LlistaClasses llCls;
      
         fPar = new FitxerPar(nomFitx);
         fPar.obrirPerLlegir();
         llCls = new LlistaClasses(objectes);
         llCls.afegirClasses(fPar);
         fPar.tancarLec();
         fCls = new FitxerCls(nomFitx);
         fCls.obrirPerEscriure(false);
         llCls.escriureAFitxer(fCls);
         fCls.tancarEsc();
         logger.info("Creat el fitxer CLS.");
      }
		
		
		
		
		
		
   
   /**
   * Permet discretitzar variables categoriques, normalment de classe.
   * @param llistaVar:
   * llistaVar[0][] - Conté la llista de vars numeriques X de NC
   * llistaVar[1][] - Conté la llista de vars categoriques Y de NC
   * @param fitxers
   * @throws CreacioFitxerException
   * @throws OpcioIncorrectaException
   * @throws ParamIncorrecteException
   * @throws CalculException
   */
       void discretitzar(String[][] llVar, boolean fitxers) throws CreacioFitxerException, OpcioIncorrectaException, ParamIncorrecteException, CalculException {
      /* Pel cas Bivariant: <br>
      * llistaVar[0][] - Conté la llista de vars numeriques X de NN <br>
      * llistaVar[1][] - Conté la llista de vars numeriques Y de NN <br>
      * llistaVar[2][] - Conté la llista de vars numeriques X de NC<br>
      * llistaVar[3][] - Conté la llista de vars categoriques Y de NC<br>
      * llistaVar[4][] - Conté la llista de vars numeriques X de CC<br>
      * llistaVar[5][] - Conté la llista de vars categoriques Y de CC<br>
      */
         String[][] llistaVar = new String[6][];
      /* Pel cas Bivariant: <br>
      * llistaEstads[0] - Conté la llista d'estad. NN <br>
      * llistaEstads[1] - Conté la llista d'estad. NC <br>
      * llistaEstads[2] - Conté la llista d'estad. CC <br>*/
         Vector[] llistaEstads = new Vector[3];
         Opcions opc = new Opcions(Opcions.BIVARIANT);
         CalculsBivNC calcNC;
         int[][] mostra;
         int lon, i, j = 0 , k = 0, lon2;
      
         lon = llVar[1].length;
         lon2 = llVar[0].length;
         llistaVar[2] = new String[lon * lon2];
         llistaVar[3] = new String[lon * lon2];
         for (i = 0; i < lon; i++) {
            for (j = 0; j < lon2; j++) {
               llistaVar[2][k] = llVar[0][j];
               llistaVar[3][k] = llVar[1][i];
               k++;
            }
         }
         llistaEstads[0] = null;
         llistaEstads[1] = new Vector(1);
         llistaEstads[2] = null;
         llistaEstads[1].add(new Integer(Opcions.DESCR_GR));
         opc.posarPerDefecte(Opcions.DESCR_GR);
         CalculsBiv[][] calculs = ferCalculsBiv(llistaVar, llistaEstads, opc);
      //es prepara per generar els fitxers
         lon = ordreProps.size();
         mostra = new int[lon][lon];
      // inicialització de la matriu de les combinacions de vars a mostrar
         for (i = 0; i < lon; i++) {
            for (j = 0; j < lon; j++) {
               mostra[i][j] = -1;
            }
         }
      // Tractament de la llista de vars Num / Categ
         if ( (llistaVar[2] == null) || (llistaVar[3] == null)) {
            lon = 0;
         }
         else {
            lon = llistaVar[2].length;
            if (llistaVar[3].length != lon)
               throw new ParamIncorrecteException("Nombre de params. incorrecte");
         }
         for (i = 0; i < lon; i++) {
            if (llistaVar[2][i] != null) {
               j = ordreProps.indexOf(llistaVar[2][i]);
               k = ordreProps.indexOf(llistaVar[3][i]);
               mostra[j][k] = i;
            }
         }
         lon = ordreProps.size();
         try {
            for (i = 0; i < lon; i++) {
               for (j = 0; j < lon; j++) {
                  if (mostra[i][j] != -1) {
                     calcNC = (CalculsBivNC) calculs[1][mostra[i][j]];
                  /*** Funcio que genera fitxer d'extrems ***/
                     new Extrems(calcNC, llistaVar[2][mostra[i][j]],
                        llistaVar[3][mostra[i][j]], dirResultats);
                  }
               }
            }
         }
             catch (IOException e) {
               throw new CreacioFitxerException("Error d'escriptura: " + e.getMessage());
            }
      
      }
   
   /**
   * Permet fer un informe Automàtic sobre la matriu de dades que engloba l'anàlisi univariant i la de per classes.
   * @param opc opcions dels tipus dels estadístics de tipus d'anàlisi descriptiva a realitzar
   * @throws CreacioFitxerException si es produexi un error al crear el fitxer .tex
   * @throws OpcioIncorrectaException si una de les opcions és incorrecta
   * @throws ParamIncorrecteException les llistes de variables o estadístics tenen alguna incorreccio
   * @throws CalculException si es produeix un error al realitzar els càlculs necessaris
   
   */
       void ferInformeAuto() throws CreacioFitxerException, OpcioIncorrectaException, ParamIncorrecteException, CalculException{
         String nomFitxer;
         FitxerTex fitxer;
         GeneradorTex gen;
         String[][] llProps;
         Vector[] llEstads;
         Opcions opc;
      // llistaVars[0][] - Conté la llista de vars categ de classe
      // llistaVars[1][] - Conté la llista de vars numeriques
      // llistaVars[2][] - Conté la llista de vars categoriques
         String[][] llVar = new String[3][];
         int nvars, i, j, k;
      
      
         nomFitxer = dirResultats + new File(nom).getName() + "InfAuto";
         fitxer = new FitxerTex(nomFitxer);
         gen = new GeneradorTex(fitxer);
         try{
            gen.generaLtxIniciInformeAuto(nom, (new Integer(dades.obtenirNumFiles()).toString()),
                             (new Integer(dades.obtenirNumColumnes()).toString()));
         // llProps[0]: props.Num
         // llProps[1]: props.Categ
            llProps = propietats.llistarIDsPropietatsEnBasics();
            logger.info("Preparant generació estudi univariant...");
         // S'afegeix l'estudi Univariant
            llEstads = new Vector[2];
            llEstads[0] = new Vector(3);
            llEstads[1] = new Vector(2);
            opc = new Opcions(Opcions.UNIVARIANT);
            llEstads[0].add(new Integer(Opcions.HISTO));
            opc.posarPerDefecte(Opcions.HISTO);
            llEstads[0].add(new Integer(Opcions.BOXPLOT));
            opc.posarPerDefecte(Opcions.BOXPLOT);
            llEstads[0].add(new Integer(Opcions.ESTAD_SUM));
            opc.posarPerDefecte(Opcions.ESTAD_SUM);
            llEstads[1].add(new Integer(Opcions.DIAGR_BARRES));
            opc.posarPerDefecte(Opcions.DIAGR_BARRES);
            llEstads[1].add(new Integer(Opcions.TAULA_FREQS));
            opc.posarPerDefecte(Opcions.TAULA_FREQS);
         // Es fan el calculs necesaris per tots els estadistics preparats
            CalculsUniv[][] calculsU = ferCalculsUniv(llProps,
               llEstads, opc);
            logger.fine("Calculs per descr. univariant realitzats.");
            gen.generarLtxUnivarPerVars(ordreProps, calculsU, llProps, llEstads, opc,true);
            logger.info("Estudi univariant generat.");
         
         // S'afegeix l'estudi  per Classes
            logger.info("Preparant generació estudi per classes...");
         // Es fan el calculs necesaris per tots els estadistics preparats
            llEstads = new Vector[3];
            llEstads[0] = null;
            llEstads[1] = new Vector(2);
            llEstads[2] = new Vector(2);
            opc = new Opcions(Opcions.PER_CLASSES);
         // Al final l'informe no inclou la descr. extensional
         /*llEstads[0].add(new Integer(Opcions.DESCR_EXTENSIONAL));
         Hashtable taula = new Hashtable();
         taula.put("tabular", new Boolean(false));
         opc.afegirOpcions(Opcions.DESCR_EXTENSIONAL, taula);*/
            llEstads[1].add(new Integer(Opcions.HISTO_MULT));
            opc.posarPerDefecte(Opcions.HISTO_MULT);
            llEstads[1].add(new Integer(Opcions.DESCR_CLASS));
            opc.posarPerDefecte(Opcions.DESCR_CLASS);
            llEstads[2].add(new Integer(Opcions.D_BAR_MULT));
            opc.posarPerDefecte(Opcions.D_BAR_MULT);
            llEstads[2].add(new Integer(Opcions.DESCR_CLASS));
            opc.posarPerDefecte(Opcions.DESCR_CLASS);
         
            nvars = llProps[1].length;
            llVar[1] = (String [])llProps[0].clone();
            for (i = 0; i < nvars; i++) {
               llVar[0] = new String[1];
               llVar[2] = new String[nvars-1];
               llVar[0][0] = llProps[1][i];
               k=0;
               for(j=0; j<nvars; j++){
                  if (j!=i) {
                     llVar[2][k] = llProps[1][j];
                     k++;
                  }
               }
               CalculsClass[] calculsC = ferCalculsClass(llVar, llEstads, opc);
               logger.fine("Calculs per descr. per classes realitzats.");
               gen.generarLtxClassesPerVars(ordreProps, calculsC, llVar[0], llEstads, opc,true);
            }
            logger.info("Estudi per classes generat.");
            gen.generaLtxFiInformeAuto();
         
         } 
             catch (CreacioFitxerException e) {
               throw e;
            } 
             catch (IOException e){
               throw new CreacioFitxerException("Error d'escriptura: " + e.getMessage());
            }
      }
   
       boolean actualitzarDirResultats(String dir){
         File fResult;
         boolean ok = false;
      
      // Cal assegurar-se que tenim la barra de directori al final del nom
         dirResultats = dir.endsWith(File.separator)? dir : dir + File.separator;
         fResult = new File(dirResultats);
         if (fResult.exists()) {
            if (fResult.isFile()) {
               logger.warning(
                  "No s'ha pogut crear el directori de resultats associat (" +
                  dirResultats + ") perqué existeix un fitxer amb el mateix nom");
            } 
            else {
               ok = true;
            }
         }
         else {
            ok = fResult.mkdir();
            if (ok) {
               logger.info("Creat el directori de resultats " + dirResultats);
            }
            else {
               logger.warning(
                  "No s'ha pogut crear el directori de resultats associat (" +
                  dirResultats + ")");
            }
         }
         return ok;
      }
   
   /**
   * Retorna un string que indica el camí fins al directori que conté els resultats de l'aplicació
   * @return directori on es deixen els resultats de les diferents operacions
   */
       String obtenirDirResultats(){
         return dirResultats;
      }
   
   //  ROBER  --  Hago que se visualice el arbol de la ultima posicion de llArbres
   //  Lo mismo a alguien le va bien crear la misma funcion pasandole la posicion como parametro
   
       boolean visualitzaArbre() throws CreacioFitxerException {
         boolean ok = false;
         FitxerTex fitxer;
         GeneradorTex gen;
			// System.out.println("A ver si entra a visualitzarArbre con nom"+nom);
      
         try {
            fitxer = new FitxerTex(dirResultats + new File(nom).getName() + "Dendo");
            gen = new GeneradorTex(fitxer);
         
         //RoberPuntoControl
         //gen.generaLtxArbreClassif((ArbreClassif)llArbres.get(0), "Arbre general de classificaci\\'o", 0.0, false, null, false);
         /*int posicion= llArbres.size();
         gen.generaLtxArbreClassif((ArbreClassif)llArbres.get(posicion-1), "Arbre general de classificaci\\'o", 0.0, false, null, false); : */
            gen.generaLtxArbreClassif((ArbreClassif)llArbres.get(arbreActual), "Arbre general de classificaci\\'o", 0.0, false,false, null, false);
         
         } 
             catch (CreacioFitxerException e) {
               throw e;
            } 
             catch (IOException e) {
               throw new CreacioFitxerException("Error d'escriptura: " + e.getMessage());
            }
      
         ok = true;
         return ok;
      }
   
   
   //  ROBER  --  Hago que se visualice el arbol de la ultima posicion de llArbres
   //  Lo mismo a alguien le va bien crear la misma funcion pasandole la posicion como parametro
   
   
       void tallaArbre(char tipus, String numTall, boolean visual,
                  boolean etiqRest,boolean reglas, boolean fitxers, String nomTall) throws
       ParamIncorrecteException, CreacioFitxerException {
         double idn;
         int numClas;
         ArbreClassif ar;
         String titol;
         FitxerTex fTex;
         GeneradorTex gen;
         Particio par = null;
      
      //RoberPuntoControl
      //ar = (ArbreClassif)llArbres.get(0);
      
      /*int posicion= llArbres.size();
      ar = (ArbreClassif)llArbres.get(posicion-1); : */
         ar = (ArbreClassif)llArbres.get(arbreActual);
      
         if (tipus == 'I') {
            try {
               idn = FitxerTex.parsejarDouble(numTall);
            } 
                catch (ParseException ex) {
                  throw new ParamIncorrecteException(
                     "Parametre d'index de nivell del tall incorrecte.");
               }
            par = ar.tallIdn(idn, nomTall);
            titol = " Arbre general de classificaci\\'o tallat a nivell " + numTall;
            logger.info("Calculat el tall a nivell " + idn + " de l'arbre.");
         
         }
         else if (tipus == 'C') {
            numClas = Integer.parseInt(numTall);
            par = ar.tallClasses(numClas, nomTall);
            titol = " Arbre general de classificaci\\'o tallat en  " + numTall +" classes";
            idn = ar.obtenirIdnParticio(nomTall);
            logger.info("Calculat el tall en " + numClas + " classes de l'arbre.");
         }
         else {
            throw new ParamIncorrecteException(
               "Parametre de tipus de tall amb valor desconegut.");
         }
         try {
            if (visual) {
               fTex = new FitxerTex(dirResultats + nomTall + "Dendo");
               gen = new GeneradorTex(fTex);
               gen.generaLtxArbreClassif(ar, titol, idn, etiqRest,reglas, par.obtenirModalitats(), false);
            }
            if (fitxers && (par != null)) {
               par.generaFitxerPar(dirResultats);
            }
         }
             catch (CreacioFitxerException e) {
               throw e;
            }
             catch (IOException e) {
               throw new CreacioFitxerException("Error d'escriptura: " + e.getMessage());
            }
      
      }
   
   
       String obtenirIdnArrelArbre(int pos) {
         ArbreClassif ar = (ArbreClassif) llArbres.get(pos);
         return FitxerTex.formatejarDouble(ar.obtenirArrel().obtenirIndexNivell());
      }
   
       String obtenirIdnArrelArbre() {
         return obtenirIdnArrelArbre(arbreActual);
      }
   
       int obtenirNumNodesInternsArbre(int pos) {
         ArbreClassif ar = (ArbreClassif) llArbres.get(pos);
         return ar.obtenirNumFulles() - 1;
      }
   
       int obtenirNumNodesInternsArbre() {
         return obtenirNumNodesInternsArbre(arbreActual);
      }
   
       public String obtenirNomArbre(int pos) {
		//	System.out.println("ESTOY en obtenirNomArbre(0) ");

         ArbreClassif ar = (ArbreClassif) llArbres.get(pos);
         return ar.obtenirNom();
      }
   
       public String obtenirNomArbre() {
		 //	System.out.println("ESTOY en obtenirNomArbre de gestormatriz ");

         return obtenirNomArbre(arbreActual);
      }
   
   // DISTANCIES*********************************************************************
   /** Metode que crea un gestorMatriu a partir de unes propietats i uns objectes determinats
   *
   * @param nomDades conte el nom de l'arxiu
   * @param idMatriu es l'identificador de la subMatriu
   * @param pare es el Gestor matriu d'on es creara la submatriu
   * @param propSelec es un subconjunt de les propietats del gestor pare
   * @param objSelec es un subconjunt dels objectes del gestor pare
   *
   * @throws CreacioPropietatsException
   * @throws CreacioObjectesException
   * @throws CreacioMatriuException
   *
   * @author Jose I Mateos
   * @version v.0 25/6/06
   */
       public GestorMatriu(String nomDades, int idMatriu,GestorMatriu pare,String [] propSelec,String [] objSelec) throws
       CreacioPropietatsException, CreacioObjectesException,
       CreacioMatriuException {
      
         File fResult;
         boolean ok;
         id = idMatriu;
         nom = nomDades;
         try {
            propietats = new LlistaPropietats(this);
            carregarSubProp(propSelec,pare.obtenirLlistaProps());
            logger.fine("Creada la llista de propietats.");
            logger.info("DEBUG - Propietats["+ propietats.llista.size()+"] = " + propietats.toString());
            objectes = new LlistaObjectes(this);
            carregarSubObj(objSelec,pare.obtenirLlistaObjs());
            logger.fine("Creada la llista d'objectes.");
            dades = new MatriuDades(objectes.obtenirLong(),propietats.obtenirLong(), this);
            carregarSubDades(propSelec,objSelec,pare);
         
            logger.info("Carregades les dades.");
            propietats.llista.trimToSize();
            ordreProps = new ArrayList(propietats.obtenirLong());
            logger.info("DEBUG - ordreProps["+ ordreProps.size()+"] = " + ordreProps.toString());
				 activeProps = new ArrayList(propietats.obtenirLong());
            for (int i = 0; i < propietats.obtenirLong(); i++) {
               ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
            // System.out.println( "a ver que"+  ordreProps.get(i));
            }
          //  System.out.println( "ENTRA A ESTEEEEEEEEEEEE?????");	
            ordreProps.trimToSize();
            actualitzarDirResultats(new File(nom).getParent() + File.separator +
                              "resultats" + File.separator);
            llArbres = new ArrayList();
            arbreActual = -1;
         ////////////////LAIA/////////////////////////////////
            LlistaBC list=pare.obtenirLlistaBC();
            list.setM(this);
            llistaBC=list;
         //////////////////////////////////////////////////////
         }
             catch (Exception e) {
               throw new CreacioMatriuException(e.getMessage());
            }
      }
   /**
   * Carrega las propietats prop dins la matriu, prop es una seleccio de les propietats propPare
   *
   * @param prop es un subconjunt de les propietats de la llista de propietats de la matriu pare
   * @param propPare es la llista de propietats de la matriu pare
   *
   * @throws CreacioPropietatsException
   *
   * @author Jose I Mateos
   * @version v.0 25/6/06
   */
       private void carregarSubProp(String[] prop,LlistaPropietats propPare)throws CreacioPropietatsException {
      
         String[][] propAntic;
         int lon1;
         int lon2;
         int lon3;
         int lon4;
         int lon;
         PropNumerica propN;
         PropCategorica propC;
         PropNominal propNo;
         PropOrdinal propO;
      
         try{
            propAntic=propPare.llistarIDsPropietats();
            lon1=propAntic[0].length;
            lon=prop.length;
            for (int i=0;i<lon1;i++){
               for (int j=0;j<lon;j++)
               {
                  if(prop[j].compareTo(propAntic[0][i])==0){
                     propN=(PropNumerica)propPare.obtenirPropietat(propPare.obtenirIndex(prop[j]));
                     propN.reset();
                     propietats.afegirProp(propN);
                  }
               }
            }
            lon2=propAntic[1].length;
            for (int i=0;i<lon2;i++){
               for (int j=0;j<lon;j++){
                  if(prop[j].compareTo(propAntic[1][i])==0){
                     propC=(PropCategorica)propPare.obtenirPropietat(propPare.obtenirIndex(prop[j]));
                     propC.reset();
                     propietats.afegirProp(propC);
                  }
               }
            }
            lon3=propAntic[2].length;
            for (int i=0;i<lon3;i++){
               for (int j=0;j<lon;j++){
                  if(prop[j].compareTo(propAntic[2][i])==0){
                     propNo=(PropNominal)propPare.obtenirPropietat(propPare.obtenirIndex(prop[j]));
                     propNo.reset();
                     propietats.afegirProp(propNo);
                  }
               }
            }
            lon4=propAntic[3].length;
            for (int i=0;i<lon4;i++){
               for (int j=0;j<lon;j++){
                  if(prop[j].compareTo(propAntic[3][i])==0){
                     propO=(PropOrdinal)propPare.obtenirPropietat(propPare.obtenirIndex(prop[j]));
                     propO.reset();
                     propietats.afegirProp(propO);
                  }
               }
            }
         }
             catch (Exception e) {
            }
      }
   /**
   * Carrega els objectes obj dins la matriu, obj es una seleccio dels objectes objPare
   *
   * @param obj es un subconjunt dels objectes de la llista d'objectes de la matriu pare
   * @param objPare es la llista d'objectes de la matriu pare
   *
   * @throws CreacioObjectesException
   *
   * @author Jose I Mateos
   * @version v.0 25/6/06
   */
       private void carregarSubObj(String[] obj,LlistaObjectes objPare)throws CreacioObjectesException {
      
         String[] objAntic;
         int lon1;
         int lon2;
      
         try{
            objAntic=objPare.llistarIDsObjs();
            lon1=objAntic.length;
            lon2=obj.length;
         
         
            for (int i=0;i<lon1;i++){
               for (int j=0;j<lon2;j++){
                  if(obj[j].compareTo(objAntic[i])==0){
                     objectes.afegirObj(objPare.obtenirObjecte(i));
                  }
               }
            }
         }
             catch (Exception e) {
            }
      }
   /**
   * Carrega les dades de les propietats prop que estan dins el gestor matriu pare
   *
   * @param prop es un subconjunt de les propietats de la llista de propietats de la matriu pare
   * @param obj es un subconjunt dels objectes de la llista d'objectes de la matriu pare
   * @param pare es el gestor matriu  que conte la matriu pare
   *
   * @author Jose I Mateos
   * @version v.0
   * Data: 25/6/06
   */
       private void carregarSubDades(String[] prop,String[] obj, GestorMatriu pare){
      
         MatriuDades dadesPare;
         LlistaPropietats propPare;
         LlistaObjectes objPare;
         String[] objAntic;
         int lonobj1;
         int lonobj2;
         String[][] propAntic;
         int lonprop1;
         int lonprop2;
         int lonprop3;
         int lonprop4;
         int lonprop;
         int filaPare;
         int colPare;
         int fila;
         int col;
         PropNumerica propN;
         PropCategorica propC;
         PropNominal propNo;
         PropOrdinal propO;
         Dada dada;
      
         try{
            propPare=pare.obtenirLlistaProps();
            objPare=pare.obtenirLlistaObjs();
            dadesPare=pare.obtenirDades();
            objAntic=objPare.llistarIDsObjs();
            lonobj1=objAntic.length;
            lonobj2=obj.length;
            propAntic=propPare.llistarIDsPropietats();
            lonprop=prop.length;
            lonprop1=propAntic[0].length;
            lonprop2=propAntic[1].length;
            lonprop3=propAntic[2].length;
            lonprop4=propAntic[3].length;
            for (int i=0;i<lonobj1;i++){
               for (int z=0;z<lonobj2;z++){
                  if(obj[z].compareTo(objAntic[i])==0){
                     filaPare=objPare.obtenirIndex(obj[z]);
                     fila=objectes.obtenirIndex(obj[z]);
                     for (int k=0;k<lonprop1;k++){
                        for (int j=0;j<lonprop;j++){
                           if(prop[j].compareTo(propAntic[0][k])==0){
                              colPare=propPare.obtenirIndex(prop[j]);
                              dada=dadesPare.obtenirDada(filaPare,colPare);
                              col=propietats.obtenirIndex(prop[j]);
                              dades.modificarDada(fila,col,dada);
                              propN=(PropNumerica)propietats.obtenirPropietat(col);
                              propN.actualitzar((String)dada.obtenirValor(),objectes.obtenirObjecte(fila).obtenirPes());
                           }
                        }
                     }
                     for (int k=0;k<lonprop2;k++){
                        for (int j=0;j<lonprop;j++){
                           if(prop[j].compareTo(propAntic[1][k])==0){
                              colPare=propPare.obtenirIndex(prop[j]);
                              dada=dadesPare.obtenirDada(filaPare,colPare);
                              col=propietats.obtenirIndex(prop[j]);
                              dades.modificarDada(fila,col,dada);
                              propC=(PropCategorica)propietats.obtenirPropietat(col);
                              propC.actualitzar((String)dada.obtenirValor(),objectes.obtenirObjecte(fila).obtenirPes());
                           }
                        }
                     }
                     for (int k=0;k<lonprop3;k++){
                        for (int j=0;j<lonprop;j++){
                           if(prop[j].compareTo(propAntic[2][k])==0){
                              colPare=propPare.obtenirIndex(prop[j]);
                              dada=dadesPare.obtenirDada(filaPare,colPare);
                              col=propietats.obtenirIndex(prop[j]);
                              dades.modificarDada(fila,col,dada);
                              propNo=(PropNominal)propietats.obtenirPropietat(col);
                              propNo.actualitzar((String)dada.obtenirValor(),objectes.obtenirObjecte(fila).obtenirPes());
                           }
                        }
                     }
                     for (int k=0;k<lonprop4;k++){
                        for (int j=0;j<lonprop;j++){
                           if(prop[j].compareTo(propAntic[3][k])==0){
                              colPare=propPare.obtenirIndex(prop[j]);
                              dada=dadesPare.obtenirDada(filaPare,colPare);
                              col=propietats.obtenirIndex(prop[j]);
                              dades.modificarDada(fila,col,dada);
                              propO=(PropOrdinal)propietats.obtenirPropietat(col);
                              propO.actualitzar((String)dada.obtenirValor(),objectes.obtenirObjecte(fila).obtenirPes());
                           }
                        }
                     }
                  }
               }
            }
         }
             catch (Exception e) {
            
            }
      }
   /**
   * Calcula les diferents estadistics minims i pasa les dades a la clase que calcula les distancies
   * per cada parella de vectors d'intre la matriu carregada
   *
   * @param opc conte les opcions de la distancia seleccionada
   *
   * @return la matriu de distancies
   *
   * @author Jose I Mateos
   * @version v.0 02/7/06
   */
       public String[][] obtenirDistancies(OpcionsDis opc) {
      
         String[][] llProps;
         String[] llObjs;
         CalcDis calculs = new CalcDis();
         int lonobj=0;
         int lonprop0=0;
         int lonprop1=0;
         int lonprop2=0;
         int lonprop3=0;
         float p1;
         float p2;
         Dada[]xin ;
         Dada[]xiin;
         Dada[]xic ;
         Dada[]xiic;
         String[]freqxi ;
         String[]freqxii;
         float[] sk;
         float[] rang;
         String[][] result=null;
         PropNumerica proNum;
         EstadisticsNum estadsN;
         int modal=0;
         int fila;
         int col;
         float correla=0;
         float alfa;
         float beta;
         String[][] corre=null;
         llProps=propietats.llistarIDsPropietats();
         lonprop0=llProps[0].length;
         lonprop1=llProps[1].length;
         lonprop2=llProps[2].length;
         lonprop3=llProps[3].length;
         llObjs=objectes.llistarIDsObjs();
         lonobj = llObjs.length;
         rang =new float[lonprop0];
         sk =new float[lonprop0];
         xin =new Dada[lonprop0];
         xiin =new Dada[lonprop0];
         xic =new Dada[lonprop1+lonprop2+lonprop3];
         xiic =new Dada[lonprop1+lonprop2+lonprop3];
         freqxi =new String[lonprop1+lonprop2+lonprop3];
         freqxii =new String[lonprop1+lonprop2+lonprop3];
         result=new String[lonobj][lonobj];
      
      
      
         switch(opc.getTipus()){
         
            case OpcionsDis.MIXTA:
            
               switch(opc.getCateg()){
               
                  case OpcionsDis.AUTO:
                     obtenirAlfaBeta(opc);
               }
               break;
         
            case OpcionsDis.RALAM:
            
               switch(opc.getCateg()){
               
                  case OpcionsDis.NORMA:
                     correla=valorCorrelacio();
               }
               break;
         
         
         }
         alfa=opc.getAlfa();
         beta=opc.getBeta();
      
      //Calcular estadistics minims
         for (int k = 0; k < lonprop0; k++) {
            proNum = (PropNumerica) propietats.obtenirPropietat(propietats.obtenirIndex(llProps[0][k]));
            estadsN = proNum.obtenirEstadistics();
            if (!opc.getPond()){
               sk[k]=estadsN.obtenirQuasiDesvTip();
            }
            else{
               sk[k]=estadsN.obtenirQuasiSkPes();
            }
            rang[k]=estadsN.obtenirRang();
         };
         modal=valorModal();
      
      //Agafa X1
         for (int i = 0; i < lonobj-1; i++) {
            fila=objectes.obtenirIndex(llObjs[i]);
            p1=objectes.obtenirObjecte(fila).obtenirPes();
         
            for (int k = 0; k < lonprop0; k++) {
               col=propietats.obtenirIndex(llProps[0][k]);
               xin[k]=dades.obtenirDada(fila,col);
            }
            for (int k = 0; k < lonprop1; k++) {
               col=propietats.obtenirIndex(llProps[1][k]);
               xic[k]=dades.obtenirDada(fila,col);
            }
            for (int k = 0; k < lonprop2; k++) {
               col=propietats.obtenirIndex(llProps[2][k]);
               xic[k+lonprop1]=dades.obtenirDada(fila,col);
            }
            for (int k = 0; k < lonprop3; k++) {
               col=propietats.obtenirIndex(llProps[3][k]);
               xic[k+lonprop2]=dades.obtenirDada(fila,col);
            }
         //Agafa X2
            for (int j = i+1; j < lonobj; j++) {
               fila=objectes.obtenirIndex(llObjs[j]);
               p2=objectes.obtenirObjecte(fila).obtenirPes();
               for (int k = 0; k < lonprop0; k++) {
                  col=propietats.obtenirIndex(llProps[0][k]);
                  xiin[k]=dades.obtenirDada(fila,col);
               }
               for (int k = 0; k < lonprop1; k++) {
                  col=propietats.obtenirIndex(llProps[1][k]);
                  xiic[k]=dades.obtenirDada(fila,col);
               }
               for (int k = 0; k < lonprop2; k++) {
                  col=propietats.obtenirIndex(llProps[2][k]);
                  xiic[k+lonprop1]=dades.obtenirDada(fila,col);
               }
               for (int k = 0; k < lonprop3; k++) {
                  col=propietats.obtenirIndex(llProps[3][k]);
                  xiic[k+lonprop2]=dades.obtenirDada(fila,col);
               }
               freqxi=calculFreq(xic,opc.getPond());
               freqxii=calculFreq(xiic,opc.getPond());
               if (!opc.getPond()){
                  p1=1;
                  p2=1;
               }
            
               switch(opc.getTipus()){
               
                  case OpcionsDis.EUCLI:
                  
                     switch(opc.getCateg()){
                     
                        case OpcionsDis.NONOR:
                           result[i][j]=(calculs.eucliNoNor(xin,xiin,opc.getQuad(),p1,p2));
                           break;
                        case OpcionsDis.SK:
                           result[i][j]=(calculs.eucliNor(xin,xiin,sk,opc.getQuad(),p1,p2));
                           break;
                        case OpcionsDis.RANG:
                           result[i][j]=(calculs.eucliNor(xin,xiin,rang,opc.getQuad(),p1,p2));
                           break;
                     }
                     break;
               
                  case OpcionsDis.GOWER:
                  
                     result[i][j]=(calculs.gower(xin,xiin,xic,xiic,rang,p1,p2));
                     break;
               
                  case OpcionsDis.ABS:
                  
                     switch(opc.getCateg()){
                     
                        case OpcionsDis.RANG:
                           result[i][j]=(calculs.absRang(xin,xiin,rang,p1,p2));
                           break;
                        default:
                           result[i][j]=(calculs.abs(xin,xiin,p1,p2));
                     }
                     break;
                  case OpcionsDis.MINKO:
                  
                     switch(opc.getCateg()){
                     
                        case OpcionsDis.RANG:
                           result[i][j]=(calculs.minkoRang(xin,xiin,rang,opc.getP()));
                           break;
                        default:
                           result[i][j]=(calculs.minko(xin,xiin,opc.getP()));
                     }
                     break;
               
                  case OpcionsDis.HAMM:
                  
                     result[i][j]=(calculs.hamm(xic,xiic));
                     break;
               
                  case OpcionsDis.CHI2:
                  
                     result[i][j]=(calculs.chi2(xic,xiic,freqxi,freqxii,p1,p2));
                     break;
               
                  case OpcionsDis.MIXTA:
                  
                     result[i][j]=(calculs.mixta(xin,xiin,xic,xiic,sk,freqxi,freqxii,alfa,beta,p1,p2));
                     break;
               
                  case OpcionsDis.RALAM:
                  
                     switch(opc.getCateg()){
                     
                        case OpcionsDis.INER:
                        
                           result[i][j]=(calculs.rIner(xin,xiin,xic,xiic,sk,freqxi,freqxii,lonprop0,modal,1,1));
                           break;
                        case OpcionsDis.NORMA:
                           result[i][j]=(calculs.rNorma(xin,xiin,xic,xiic,sk,freqxi,freqxii,correla,modal,1,1));
                           break;
                     
                     }
                     break;
               
                  case OpcionsDis.GOWDA:
                  
                     result[i][j]=(calculs.gowda(xin,xiin,xic,xiic,rang,p1,p2));
                     break;
               
                  case OpcionsDis.ICHINO:
                     result[i][j]=(calculs.ichino(xin,xiin,xic,xiic,rang,modal,opc.getP(),opc.getGamma()));
                     break;
               }
            }
         }
         return result;
      }
   /**
   * Agafa els 2 obejctes pasats per valor i pasa les dades a la clase que calcula les distancies
   * per una parella de ojectes donada d'intre la matriu carregada
   *
   * @param i1 index de l'objecte1 dintre la matriu
   * @param i2 index de l'objecte2 dintre la matriu
   * @param opc conte les opcions de la distancia seleccionada
   *
   * @return calcul de la distancia
   *
   * @author Jose I Mateos
   * @version v.0 02/7/06
   */
       public String obtenirDis2Obj(int i1 ,int i2,OpcionsDis opc) {
      
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
         String result=null;
         int fila;
         int col;
         float p1;
         float p2;
      
      
         llProps=propietats.llistarIDsPropietats();
         lonprop0=llProps[0].length;
         lonprop1=llProps[1].length;
         lonprop2=llProps[2].length;
         lonprop3=llProps[3].length;
         llObjs=objectes.llistarIDsObjs();
      
         xin =new Dada[lonprop0];
         xiin =new Dada[lonprop0];
         xic =new Dada[lonprop1+lonprop2+lonprop3];
         xiic =new Dada[lonprop1+lonprop2+lonprop3];
      
      
      //Agafa X1
         fila=objectes.obtenirIndex(llObjs[i1]);
         p1=objectes.obtenirObjecte(fila).obtenirPes();
      
         for (int k = 0; k < lonprop0; k++) {
            col=propietats.obtenirIndex(llProps[0][k]);
            xin[k]=dades.obtenirDada(fila,col);
         }
         for (int k = 0; k < lonprop1; k++) {
            col=propietats.obtenirIndex(llProps[1][k]);
            xic[k]=dades.obtenirDada(fila,col);
         }
         for (int k = 0; k < lonprop2; k++) {
            col=propietats.obtenirIndex(llProps[2][k]);
            xic[k+lonprop1]=dades.obtenirDada(fila,col);
         }
         for (int k = 0; k < lonprop3; k++) {
            col=propietats.obtenirIndex(llProps[3][k]);
            xic[k+lonprop2]=dades.obtenirDada(fila,col);
         }
      
      
      //Agafa X2
         fila=objectes.obtenirIndex(llObjs[i2]);
         p2=objectes.obtenirObjecte(fila).obtenirPes();
      
         for (int k = 0; k < lonprop0; k++) {
            col=propietats.obtenirIndex(llProps[0][k]);
            xiin[k]=dades.obtenirDada(fila,col);
         }
         for (int k = 0; k < lonprop1; k++) {
            col=propietats.obtenirIndex(llProps[1][k]);
            xiic[k]=dades.obtenirDada(fila,col);
         }
         for (int k = 0; k < lonprop2; k++) {
            col=propietats.obtenirIndex(llProps[2][k]);
            xiic[k+lonprop1]=dades.obtenirDada(fila,col);
         }
         for (int k = 0; k < lonprop3; k++) {
            col=propietats.obtenirIndex(llProps[3][k]);
            xiic[k+lonprop2]=dades.obtenirDada(fila,col);
         }
      
         result=obtenirDisInter(xin,xic,xiin,xiic,opc,p1,p2);
      
         return result;
      }
   /**
   * Calcula les diferents estadistics minims i pasa les dades a la clase que calcula les distancies
   * per una parella de ojectes donada d'intre la matriu carregada
   *
   * @param xin son les propietats numeriques del vectorl
   * @param xic son les propietats categoriques del vectorl
   * @param xiin son les propietats numeriques del vector2
   * @param xiic son les propietats categoriques del vector2
   * @param opc conte les opcions de la distancia seleccionada
   * @param p1 conte el valor del pes de l'objecte1
   * @param p2 conte el valor del pes de l'objecte2
   *
   * @return calcul de la distancia
   *
   * @author Jose I Mateos
   * @version v.0 02/7/06
   */
       public String obtenirDisInter(Dada[] xin,Dada[] xic,Dada[] xiin,Dada[] xiic,OpcionsDis opc,float p1,float p2) {
      
         String[][] llProps;
         String[] llObjs;
      
         CalcDis calculs = new CalcDis();
         int lonprop0=0;
         int lonprop1=0;
         int lonprop2=0;
         int lonprop3=0;
         String[]freqxi ;
         String[]freqxii;
         float[] sk;
         float[] rang;
         String result=null;
         PropNumerica proNum;
         EstadisticsNum estadsN;
         PropCategorica proCat;
         PropNominal proNom;
         PropOrdinal proOrd;
         EstadisticsCateg estadsC;
         int modal=0;
         int fila;
         int col;
         float correla=0;
         float alfa;
         float beta;
         String[][] corre=null;
      
         llProps= propietats.llistarIDsPropietats();
      
      // llProps=propietats.llistarIDsPropietats();
      //	llProps=propietats.llistarIDsPropietats();
        // System.out.println( "HOLA ESTOY en  obtenirDisInter HOLAAAAAAA ");
        // System.out.println( "llProps[0].lengthseg"+llProps[0].length);
        // System.out.println( "llProps[1].lengthseg"+llProps[1].length);
        // System.out.println( "llProps[0][0]seg"+llProps[0][0]);
        // System.out.println( "llProps[1][0]seg"+llProps[1][0]);
       //  System.out.println( "llProps[1][1]seg"+llProps[1][1]);
      
      
      
         lonprop0=llProps[0].length;
         lonprop1=llProps[1].length;
         lonprop2=llProps[2].length;
         lonprop3=llProps[3].length;
         llObjs=objectes.llistarIDsObjs();
      
         rang =new float[lonprop0];
         sk =new float[lonprop0];
         freqxi =new String[lonprop1+lonprop2+lonprop3];
         freqxii =new String[lonprop1+lonprop2+lonprop3];
         corre=new String[lonprop0][lonprop0];
      
     //  System.out.println( " opc.getTipus()"+opc.getTipus());
      // System.out.println( " opc.getCateg()"+opc.getCateg());
      
         switch(opc.getTipus()){
         
            case OpcionsDis.MIXTA:
            
               switch(opc.getCateg()){
               
                  case OpcionsDis.AUTO:
                     {
                       
                        obtenirAlfaBeta(opc);
                       
							  }	
               }
               break;
         
            case OpcionsDis.RALAM:
            
               switch(opc.getCateg()){
               
                  case OpcionsDis.NORMA:
                     correla=valorCorrelacio();
               }
         }
         alfa=opc.getAlfa();
         beta=opc.getBeta();
      
      //Calcular estadistics minims
         for (int k = 0; k < lonprop0; k++) {
            proNum = (PropNumerica) propietats.obtenirPropietat(propietats.obtenirIndex(llProps[0][k]));
            estadsN = proNum.obtenirEstadistics();
            if (!opc.getPond()){
               sk[k]=estadsN.obtenirQuasiDesvTip();
            }
            else{
               sk[k]=estadsN.obtenirQuasiSkPes();
            }
            rang[k]=estadsN.obtenirRang();
         };
         modal=valorModal();
         freqxi=calculFreq(xic,opc.getPond());
         freqxii=calculFreq(xiic,opc.getPond());
      
      
         switch(opc.getTipus()){
         
            case OpcionsDis.EUCLI:
            
               switch(opc.getCateg()){
               
                  case OpcionsDis.NONOR:
                     result=(calculs.eucliNoNor(xin,xiin,opc.getQuad(),p1,p2));
                     break;
                  case OpcionsDis.SK:
                     result=(calculs.eucliNor(xin,xiin,sk,opc.getQuad(),p1,p2));
                     break;
                  case OpcionsDis.RANG:
                     result=(calculs.eucliNor(xin,xiin,rang,opc.getQuad(),p1,p2));
                     break;
               }
               break;
         
            case OpcionsDis.GOWER:
               result=(calculs.gower(xin,xiin,xic,xiic,rang,p1,p2));
               break;
         
            case OpcionsDis.ABS:
               switch(opc.getCateg()){
               
                  case OpcionsDis.RANG:
                     result=(calculs.absRang(xin,xiin,rang,p1,p2));
                     break;
                  default:
                     result=(calculs.abs(xin,xiin,p1,p2));
               }
               break;
            case OpcionsDis.MINKO:
               switch(opc.getCateg()){
               
                  case OpcionsDis.RANG:
                     result=(calculs.minkoRang(xin,xiin,rang,opc.getP()));
                     break;
                  default:
                     result=(calculs.minko(xin,xiin,opc.getP()));
               }
               break;
         
            case OpcionsDis.HAMM:
               result=(calculs.hamm(xic,xiic));
               break;
         
            case OpcionsDis.CHI2:
               result=(calculs.chi2(xic,xiic,freqxi,freqxii,p1,p2));
               break;
         
            case OpcionsDis.MIXTA:
               result=(calculs.mixta(xin,xiin,xic,xiic,sk,freqxi,freqxii,alfa,beta,p1,p2));
               break;
         
            case OpcionsDis.RALAM:
               switch(opc.getCateg()){
               
                  case OpcionsDis.INER:
                     result=(calculs.rIner(xin,xiin,xic,xiic,sk,freqxi,freqxii,lonprop0,modal,1,1));
                     break;
               
                  case OpcionsDis.NORMA:
                     result=(calculs.rNorma(xin,xiin,xic,xiic,sk,freqxi,freqxii,correla,modal,1,1));
                     break;
               }
               break;
         
            case OpcionsDis.GOWDA:
               result=(calculs.gowda(xin,xiin,xic,xiic,rang,p1,p2));
               break;
         
            case OpcionsDis.ICHINO:
               result=(calculs.ichino(xin,xiin,xic,xiic,rang,modal,opc.getP(),opc.getGamma()));
               break;
         }
         return result;
      }
		
		
 public String obtenirDisInterEst(String[] varsest,Dada[] xin,Dada[] xic,Dada[] xiin,Dada[] xiic,OpcionsDis opc,float p1,float p2) {
      
         String[][] llProps;
         String[] llObjs;
      
         CalcDis calculs = new CalcDis();
         int lonprop0=0;
         int lonprop1=0;
         int lonprop2=0;
         int lonprop3=0;
         String[]freqxi ;
         String[]freqxii;
         float[] sk;
         float[] rang;
         String result=null;
         PropNumerica proNum;
         EstadisticsNum estadsN;
         PropCategorica proCat;
         PropNominal proNom;
         PropOrdinal proOrd;
         EstadisticsCateg estadsC;
         int modal=0;
         int fila;
         int col;
         float correla=0;
         float alfa;
         float beta;
         String[][] corre=null;
     // System.out.println( "Estoy en obtenirDisInterEst!!!!!  ");
       //  llProps= propietats.llistarIDsPropietats();
		 
		 	for(int p=0;p<varsest.length;p++){
				   	//System.out.println( "que hay acaaa varsest"+varsest[p]);
					}

		          
		 llProps = propietats.llistarIDsPropietasEstados(varsest);
		 
		 
      
      // llProps=propietats.llistarIDsPropietats();
      //	llProps=propietats.llistarIDsPropietats();
        // System.out.println( "HOLA ESTOY en  obtenirDisInter HOLAAAAAAA ");
        // System.out.println( "llProps[0].lengthseg"+llProps[0].length);
        // System.out.println( "llProps[1].lengthseg"+llProps[1].length);
        // System.out.println( "llProps[0][0]seg"+llProps[0][0]);
        // System.out.println( "llProps[1][0]seg"+llProps[1][0]);
       //  System.out.println( "llProps[1][1]seg"+llProps[1][1]);
      
      
      
         lonprop0=llProps[0].length;
         lonprop1=llProps[1].length;
         lonprop2=llProps[2].length;
         lonprop3=llProps[3].length;
         llObjs=objectes.llistarIDsObjs();
      
         rang =new float[lonprop0];
         sk =new float[lonprop0];
         freqxi =new String[lonprop1+lonprop2+lonprop3];
         freqxii =new String[lonprop1+lonprop2+lonprop3];
         corre=new String[lonprop0][lonprop0];
      
    //  System.out.println( " opc.getTipus()"+opc.getTipus());
      // System.out.println( " opc.getCateg()"+opc.getCateg());
         switch(opc.getTipus()){
         
            case OpcionsDis.MIXTA:
        //    System.out.println( "esta en MIXTA");
				
               switch(opc.getCateg()){
                                   					  
							case OpcionsDis.AUTO:
                     {
                      //	System.out.println( " entra en obtenirAlfaBetaEst");

                        obtenirAlfaBetaEst(varsest,opc);
                       	//System.out.println( " sale de obtenirAlfaBetaEst");

							  }	
               }
               break;
         
            case OpcionsDis.RALAM:
            
               switch(opc.getCateg()){
               
                  case OpcionsDis.NORMA:
                     correla=valorCorrelacio();
               }
         }
				
		//	System.out.println( "salio del case");
			
         alfa=opc.getAlfa();
         beta=opc.getBeta();
			//System.out.println( "alfa="+alfa);
			//System.out.println( "beta="+beta);
      
      //Calcular estadistics minims
         for (int k = 0; k < lonprop0; k++) {
            proNum = (PropNumerica) propietats.obtenirPropietat(propietats.obtenirIndex(llProps[0][k]));
           // System.out.println( "entra al obtenirEstadistics");
				estadsN = proNum.obtenirEstadistics();
				//System.out.println( "salio obtenirEstadistics");
            if (!opc.getPond()){
               sk[k]=estadsN.obtenirQuasiDesvTip();
            }
            else{
               sk[k]=estadsN.obtenirQuasiSkPes();
            }
            rang[k]=estadsN.obtenirRang();
         };
         modal=valorModal();
		//	System.out.println( "entra al calculFreq");
         freqxi=calculFreqEst(varsest,xic,opc.getPond());
		//	System.out.println( "sale del calculFreq");
         freqxii=calculFreqEst(varsest,xiic,opc.getPond());
      
    //  	System.out.println( "salio del calculFreq");
			//	System.out.println( "que han en este opc.getTipus()"+opc.getTipus());
			
         switch(opc.getTipus()){
         
            case OpcionsDis.EUCLI:
            
               switch(opc.getCateg()){
               
                  case OpcionsDis.NONOR:
                     result=(calculs.eucliNoNor(xin,xiin,opc.getQuad(),p1,p2));
                     break;
                  case OpcionsDis.SK:
                     result=(calculs.eucliNor(xin,xiin,sk,opc.getQuad(),p1,p2));
                     break;
                  case OpcionsDis.RANG:
                     result=(calculs.eucliNor(xin,xiin,rang,opc.getQuad(),p1,p2));
                     break;
               }
               break;
         
            case OpcionsDis.GOWER:
               result=(calculs.gower(xin,xiin,xic,xiic,rang,p1,p2));
               break;
         
            case OpcionsDis.ABS:
               switch(opc.getCateg()){
               
                  case OpcionsDis.RANG:
                     result=(calculs.absRang(xin,xiin,rang,p1,p2));
                     break;
                  default:
                     result=(calculs.abs(xin,xiin,p1,p2));
               }
               break;
            case OpcionsDis.MINKO:
               switch(opc.getCateg()){
               
                  case OpcionsDis.RANG:
                     result=(calculs.minkoRang(xin,xiin,rang,opc.getP()));
                     break;
                  default:
                     result=(calculs.minko(xin,xiin,opc.getP()));
               }
               break;
         
            case OpcionsDis.HAMM:
               result=(calculs.hamm(xic,xiic));
               break;
         
            case OpcionsDis.CHI2:
               result=(calculs.chi2(xic,xiic,freqxi,freqxii,p1,p2));
               break;
         
            case OpcionsDis.MIXTA:
			//	System.out.println( "entra en MIXTA aca");
               result=(calculs.mixta(xin,xiin,xic,xiic,sk,freqxi,freqxii,alfa,beta,p1,p2));
					//System.out.println( "sale de mixta");
               break;
         
            case OpcionsDis.RALAM:
               switch(opc.getCateg()){
               
                  case OpcionsDis.INER:
                     result=(calculs.rIner(xin,xiin,xic,xiic,sk,freqxi,freqxii,lonprop0,modal,1,1));
                     break;
               
                  case OpcionsDis.NORMA:
                     result=(calculs.rNorma(xin,xiin,xic,xiic,sk,freqxi,freqxii,correla,modal,1,1));
                     break;
               }
               break;
         
            case OpcionsDis.GOWDA:
               result=(calculs.gowda(xin,xiin,xic,xiic,rang,p1,p2));
               break;
         
            case OpcionsDis.ICHINO:
               result=(calculs.ichino(xin,xiin,xic,xiic,rang,modal,opc.getP(),opc.getGamma()));
               break;
         }
         return result;
      }
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
   /**
   *Calcula els alfa i beta de la matriu per la distancia Mixta Gibert
   *
   * @param opc conte les opcions de la distancia seleccionada
   *
   * @author Jose I Mateos
   * @version v.0 02/7/06
   */
       public void obtenirAlfaBeta(OpcionsDis opc) {
		 System.out.println( "ENTRA A ESTE OBTENIR Alfa Beta? NO ENTRAAAA??4/08/2009");
		 String[][] llProps; 
		 
		  ArrayList activi=new ArrayList();
	     activi= obtenirActivas();
	   //System.out.println( "COMO ESTA ESTADOSEL???"+estadosel);
	  
	//   for (int i = 0; i<activi.size(); i++){
            //ActiveProps.add(i,llistaActive[i]);
   //         System.out.println( "LAS Activiiiii"+  activi.get(i));
     //    }

	// System.out.println( "hola imprimo activasel"+activasel);  
	  
 if (activasel){
 // System.out.println( "hola estoy en activasel");
 llProps = propietats.llistarIDsPropietatsEnBasicsA(activi);
// System.out.println( "hola sali de acaaaaaaa?????");
 System.out.println( "ENTRA EN ACTIVASEL????????ALEEEEJANDROOOOO");
 }
 else
     {llProps=propietats.llistarIDsPropietats();
	   System.out.println( "sale por el else");
	  }
	  

/*
Estado esta;
	  
if  (estadosel){
System.out.println( "ENTRA EN ESTADOSEL????????ALEEEE");
String [] varsest=null;
String actual = estadoactual;
	for(int j=0;j<llEstados.size();j++){
					esta = (Estado) (llEstados.get(j)); 
					if (esta.nom==estadoactual){
					varsest=esta.variables;}
          }
llProps = propietats.llistarIDsPropietasEstados(varsest);
System.out.println( "esta marcado");}
else{
llProps=propietats.llistarIDsPropietats();
System.out.println( "no esta marcado");
}

*/		 
		 
      
        // String[][] llProps;
         String[] llObjs;
         CalcDis calculs = new CalcDis();
         int lonobj=0;
         int lonprop0=0;
         int lonprop1=0;
         int lonprop2=0;
         int lonprop3=0;
         float p1;
         float p2;
         Dada[]xin ;
         Dada[]xiin;
         Dada[]xic ;
         Dada[]xiic;
         String[]freqxi ;
         String[]freqxii;
         float[] sk;
         float[] rang;
         String[][] resultN=null;
         String[][] resultC=null;
         PropNumerica proNum;
         EstadisticsNum estadsN;
         int fila;
         int col;
         float alfa;
         float beta;
         String[][] corre=null;
      // llProps=propietats.llistarIDsPropietats(); comente 16/4
            
         lonprop0=llProps[0].length;
         lonprop1=llProps[1].length;
         lonprop2=llProps[2].length;
         lonprop3=llProps[3].length;
      
      //   System.out.println( "longprop0"+llProps[0].length);
       //  System.out.println( "longpro1"+llProps[1].length);
      
         //System.out.println( "longprop2"+llProps[2].length);
      
         //System.out.println( "longprop3"+llProps[3].length);
      
      
      
      
      
         llObjs=objectes.llistarIDsObjs();
         lonobj = llObjs.length;
         rang =new float[lonprop0];
         sk =new float[lonprop0];
         xin =new Dada[lonprop0];
         xiin =new Dada[lonprop0];
         xic =new Dada[lonprop1+lonprop2+lonprop3];
         xiic =new Dada[lonprop1+lonprop2+lonprop3];
         freqxi =new String[lonprop1+lonprop2+lonprop3];
         freqxii =new String[lonprop1+lonprop2+lonprop3];
         resultN=new String[lonobj][lonobj];
         resultC=new String[lonobj][lonobj];
      
      
         alfa=opc.getAlfa();
         beta=opc.getBeta();
       //  System.out.println("ALE salio del getAlfa getBeta()");
      //Calcular estadistics minims
         for (int k = 0; k < lonprop0; k++) {
            proNum = (PropNumerica) propietats.obtenirPropietat(propietats.obtenirIndex(llProps[0][k]));
            estadsN = proNum.obtenirEstadistics();
            if (!opc.getPond()){
               sk[k]=estadsN.obtenirQuasiDesvTip();
            }
            else{
               sk[k]=estadsN.obtenirQuasiSkPes();
            }
            rang[k]=estadsN.obtenirRang();
         };
         if (lonprop0==0){
            alfa=0;
            beta=1;
         }
         else if ((lonprop1+lonprop2+lonprop3)==0){
            alfa=1;
            beta=0;
         }
         else{
         //Agafa X1
            for (int i = 0; i < lonobj-1; i++) {
               fila=objectes.obtenirIndex(llObjs[i]);
               p1=objectes.obtenirObjecte(fila).obtenirPes();
               for (int k = 0; k < lonprop0; k++) {
                  col=propietats.obtenirIndex(llProps[0][k]);//ale
                 // System.out.println("que carajo le da el primer col"+ col);	
                  xin[k]=dades.obtenirDada(fila,col);
					// System.out.println("que tiene xin[k]"+ xin[k].obtenirValor());		
               }
               for (int k = 0; k < lonprop1; k++) {
                  col=propietats.obtenirIndex(llProps[1][k]); //ale
                //  System.out.println("que carajo le da el segundo col"+ col);	
                  xic[k]=dades.obtenirDada(fila,col);
						//System.out.println("que tiene xic[k]"+ xic[k].obtenirValor());	
               }
               for (int k = 0; k < lonprop2; k++) {
                  col=propietats.obtenirIndex(llProps[2][k]);
                 // System.out.println("que carajo le da el tercer col"+ col);	
                  xic[k+lonprop1]=dades.obtenirDada(fila,col);
					   //System.out.println("que tiene xic[k+lonprop1]"+ xic[k].obtenirValor());
               }
               for (int k = 0; k < lonprop3; k++) {
                  col=propietats.obtenirIndex(llProps[3][k]);
                 // System.out.println("que carajo le da el cuarto col"+ col);	
                  xic[k+lonprop2]=dades.obtenirDada(fila,col);
               }
            //Agafa X2
               for (int j = i+1; j < lonobj; j++) {
                  fila=objectes.obtenirIndex(llObjs[j]);
                  p2=objectes.obtenirObjecte(fila).obtenirPes();
                  for (int k = 0; k < lonprop0; k++) {
                     col=propietats.obtenirIndex(llProps[0][k]); //ale
                    // System.out.println("que carajo le da el primer col de X2"+ col);
                     xiin[k]=dades.obtenirDada(fila,col);
                  }
                  for (int k = 0; k < lonprop1; k++) {
                     col=propietats.obtenirIndex(llProps[1][k]); //ale
                    // System.out.println(" segundo col de X2"+ col);
                     xiic[k]=dades.obtenirDada(fila,col);
                  }
                  for (int k = 0; k < lonprop2; k++) {
                     col=propietats.obtenirIndex(llProps[2][k]);
                     xiic[k+lonprop1]=dades.obtenirDada(fila,col);
                  }
                  for (int k = 0; k < lonprop3; k++) {
                     col=propietats.obtenirIndex(llProps[3][k]);
                     xiic[k+lonprop2]=dades.obtenirDada(fila,col);
                  }
                 // System.out.println(" va a entrar a calculFreqaaaaaaaaaaaaaaaaaaaaaaaaa");	
                  freqxi=calculFreq(xic,opc.getPond());
						 //System.out.println("salio del primer calculFreqaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");	
                  freqxii=calculFreq(xiic,opc.getPond());
                 // System.out.println(" salio de calculFreq");
                  if (!opc.getPond()){
                     p1=1;
                     p2=1;
                  }
                  resultN[i][j]=(calculs.eucliNor(xin,xiin,sk,true,p1,p2));
                  resultC[i][j]=(calculs.chi2(xic,xiic,freqxi,freqxii,p1,p2));
                  alfa=Float.parseFloat(resultN[i][j]);
                  if(alfa>opc.getAlfa()){
                     opc.setAlfa(alfa);
                  }
                  beta=Float.parseFloat(resultC[i][j]);
                  if(beta>opc.getBeta()){
                     opc.setBeta(beta);
                  }
               }
            }
            alfa=opc.getAlfa();
            beta=opc.getBeta();
            alfa=lonprop0/alfa;
            beta=(lonprop1+lonprop2+lonprop3)/beta;
            alfa=alfa/(alfa+beta);
            beta=1 - alfa;
         }
         opc.setAlfa(alfa);
         opc.setBeta(beta);
      }
		
		
 public void obtenirAlfaBetaEst(String[] varsest,OpcionsDis opc) {
		 String[][] llProps; 
		//	System.out.println( " Estoy en obtenir alfa beta est"); 
		  ArrayList activi=new ArrayList();
	     activi= obtenirActivas();
	   //System.out.println( "COMO ESTA ESTADOSEL???"+estadosel);
	  
	//   for (int i = 0; i<activi.size(); i++){
            //ActiveProps.add(i,llistaActive[i]);
   //         System.out.println( "LAS Activiiiii"+  activi.get(i));
     //    }

	// System.out.println( "hola imprimo activasel"+activasel);  
	  
 if (activasel){
 // System.out.println( "hola estoy en activasel");
 llProps = propietats.llistarIDsPropietatsEnBasicsA(activi);
// System.out.println( "hola sali de acaaaaaaa?????");
 
 }
 else
     {llProps=propietats.llistarIDsPropietats();}
	  

Estado esta;
	  
llProps = propietats.llistarIDsPropietasEstados(varsest);
	for(int p=0;p<varsest.length;p++){
				   	//System.out.println( " varsest"+varsest[p]);
					}

		 
		 
      
        // String[][] llProps;
         String[] llObjs;
         CalcDis calculs = new CalcDis();
         int lonobj=0;
         int lonprop0=0;
         int lonprop1=0;
         int lonprop2=0;
         int lonprop3=0;
         float p1;
         float p2;
         Dada[]xin ;
         Dada[]xiin;
         Dada[]xic ;
         Dada[]xiic;
         String[]freqxi ;
         String[]freqxii;
         float[] sk;
         float[] rang;
         String[][] resultN=null;
         String[][] resultC=null;
         PropNumerica proNum;
         EstadisticsNum estadsN;
         int fila;
         int col;
         float alfa;
         float beta;
         String[][] corre=null;
      // llProps=propietats.llistarIDsPropietats(); comente 16/4
            
         lonprop0=llProps[0].length;
         lonprop1=llProps[1].length;
         lonprop2=llProps[2].length;
         lonprop3=llProps[3].length;
      
      //   System.out.println( "longprop0"+llProps[0].length);
       //  System.out.println( "longpro1"+llProps[1].length);
      
         //System.out.println( "longprop2"+llProps[2].length);
      
         //System.out.println( "longprop3"+llProps[3].length);
      
      
      
      
      
         llObjs=objectes.llistarIDsObjs();
         lonobj = llObjs.length;
         rang =new float[lonprop0];
         sk =new float[lonprop0];
         xin =new Dada[lonprop0];
         xiin =new Dada[lonprop0];
         xic =new Dada[lonprop1+lonprop2+lonprop3];
         xiic =new Dada[lonprop1+lonprop2+lonprop3];
         freqxi =new String[lonprop1+lonprop2+lonprop3];
         freqxii =new String[lonprop1+lonprop2+lonprop3];
         resultN=new String[lonobj][lonobj];
         resultC=new String[lonobj][lonobj];
      
      
         alfa=opc.getAlfa();
         beta=opc.getBeta();
       //  System.out.println("ALE salio del getAlfa getBeta()");
      //Calcular estadistics minims
         for (int k = 0; k < lonprop0; k++) {
            proNum = (PropNumerica) propietats.obtenirPropietat(propietats.obtenirIndex(llProps[0][k]));
            estadsN = proNum.obtenirEstadistics();
            if (!opc.getPond()){
               sk[k]=estadsN.obtenirQuasiDesvTip();
            }
            else{
               sk[k]=estadsN.obtenirQuasiSkPes();
            }
            rang[k]=estadsN.obtenirRang();
         };
         if (lonprop0==0){
            alfa=0;
            beta=1;
         }
         else if ((lonprop1+lonprop2+lonprop3)==0){
            alfa=1;
            beta=0;
         }
         else{
         //Agafa X1
            for (int i = 0; i < lonobj-1; i++) {
               fila=objectes.obtenirIndex(llObjs[i]);
               p1=objectes.obtenirObjecte(fila).obtenirPes();
               for (int k = 0; k < lonprop0; k++) {
                  col=propietats.obtenirIndex(llProps[0][k]);//ale
                 // System.out.println("que carajo le da el primer col"+ col);	
                  xin[k]=dades.obtenirDada(fila,col);
					// System.out.println("que tiene xin[k]"+ xin[k].obtenirValor());		
               }
               for (int k = 0; k < lonprop1; k++) {
                  col=propietats.obtenirIndex(llProps[1][k]); //ale
                //  System.out.println("que carajo le da el segundo col"+ col);	
                  xic[k]=dades.obtenirDada(fila,col);
						//System.out.println("que tiene xic[k]"+ xic[k].obtenirValor());	
               }
               for (int k = 0; k < lonprop2; k++) {
                  col=propietats.obtenirIndex(llProps[2][k]);
                 // System.out.println("que carajo le da el tercer col"+ col);	
                  xic[k+lonprop1]=dades.obtenirDada(fila,col);
					   //System.out.println("que tiene xic[k+lonprop1]"+ xic[k].obtenirValor());
               }
               for (int k = 0; k < lonprop3; k++) {
                  col=propietats.obtenirIndex(llProps[3][k]);
                 // System.out.println("que carajo le da el cuarto col"+ col);	
                  xic[k+lonprop2]=dades.obtenirDada(fila,col);
               }
            //Agafa X2
               for (int j = i+1; j < lonobj; j++) {
                  fila=objectes.obtenirIndex(llObjs[j]);
                  p2=objectes.obtenirObjecte(fila).obtenirPes();
                  for (int k = 0; k < lonprop0; k++) {
                     col=propietats.obtenirIndex(llProps[0][k]); //ale
                    // System.out.println("que carajo le da el primer col de X2"+ col);
                     xiin[k]=dades.obtenirDada(fila,col);
                  }
                  for (int k = 0; k < lonprop1; k++) {
                     col=propietats.obtenirIndex(llProps[1][k]); //ale
                    // System.out.println(" segundo col de X2"+ col);
                     xiic[k]=dades.obtenirDada(fila,col);
                  }
                  for (int k = 0; k < lonprop2; k++) {
                     col=propietats.obtenirIndex(llProps[2][k]);
                     xiic[k+lonprop1]=dades.obtenirDada(fila,col);
                  }
                  for (int k = 0; k < lonprop3; k++) {
                     col=propietats.obtenirIndex(llProps[3][k]);
                     xiic[k+lonprop2]=dades.obtenirDada(fila,col);
                  }
                 // System.out.println(" va a entrar a calculFreqaaaaaaaaaaaaaaaaaaaaaaaaa");	
                  freqxi=calculFreqEst(varsest,xic,opc.getPond());
						 //System.out.println("salio del primer calculFreqaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");	
                  freqxii=calculFreqEst(varsest,xiic,opc.getPond());
                 // System.out.println(" salio de calculFreq");
                  if (!opc.getPond()){
                     p1=1;
                     p2=1;
                  }
                  resultN[i][j]=(calculs.eucliNor(xin,xiin,sk,true,p1,p2));
                  resultC[i][j]=(calculs.chi2(xic,xiic,freqxi,freqxii,p1,p2));
                  alfa=Float.parseFloat(resultN[i][j]);
                  if(alfa>opc.getAlfa()){
                     opc.setAlfa(alfa);
                  }
                  beta=Float.parseFloat(resultC[i][j]);
                  if(beta>opc.getBeta()){
                     opc.setBeta(beta);
                  }
               }
            }
            alfa=opc.getAlfa();
            beta=opc.getBeta();
            alfa=lonprop0/alfa;
            beta=(lonprop1+lonprop2+lonprop3)/beta;
            alfa=alfa/(alfa+beta);
            beta=1 - alfa;
         }
         opc.setAlfa(alfa);
         opc.setBeta(beta);
      }
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
   /**
   * Metode que escriu la matriu de distancies en un fitxer .dis
   *
   * @param matriu conte els valors de les distancies calculades
   *
   * @author Jose I Mateos
   * @version v.0 7/5/06
   */
       public void escriuMatriuDis(Object[][] matriu, OpcionsDis opc){
      
         int arxiu;
         String nomp;
         String[]llObjs;
         String dada;
         int lon;
         FitxerDis f;
         String metrica="";
         String linea="";
         String str ="";
      
      
         llObjs= objectes.llistarIDsObjs();
         arxiu=nom.lastIndexOf("\\");
         nomp=nom.substring(arxiu+1);
         f = new FitxerDis(dirResultats+nomp);
      
         switch(opc.getTipus()){
         
            case OpcionsDis.EUCLI:
               metrica = "Euclídia";
               break;
         
            case OpcionsDis.GOWER:
               metrica = "Gower";
               break;
         
            case OpcionsDis.ABS:
               metrica = "Valor absolut";
               break;
         
            case OpcionsDis.MINKO:
               metrica = "Minkovski";
               break;
         
            case OpcionsDis.HAMM:
               metrica = "Hamming Generealitzat";
               break;
         
            case OpcionsDis.CHI2:
               metrica = "Chi2";
               break;
         
            case OpcionsDis.MIXTA:
               metrica = "Mixta de Gibert";
               break;
         
            case OpcionsDis.RALAM:
               metrica = "Ralambondrainy";
               break;
         
            case OpcionsDis.GOWDA:
               metrica = "Gowda-Diday";
               break;
         
            case OpcionsDis.ICHINO:
               metrica = "Ichino-Yaguchi";
               break;
         }
         switch(opc.getCateg()){
         
            case OpcionsDis.NONOR:
               metrica = metrica + " sense normalitzar ";
               break;
         
            case OpcionsDis.SK:
               metrica = metrica + " normalitzada per la desviació tipus ";
               break;
         
            case OpcionsDis.RANG:
               metrica = metrica + " normalitzada pel rang ";
               break;
         
            case OpcionsDis.INER:
               metrica = metrica + " normalitzada per la Inèrcia";
               break;
         
            case OpcionsDis.NORMA:
               metrica = metrica + " normalitzada per la Norma";
               break;
         }
      
         if (opc.getQuad()){
            metrica = metrica + " al quadrat";
         }
         if (opc.getPond()){
            metrica = metrica + " ponderada";
         }
      
      
         try {
         
            f.obrirPerEscriure(true);
            lon=matriu[0].length;
         
            str = "Matriu de distàncies de l'arxiu " +nomp + " calculada sobre la mètrica " + metrica;
            f.escriureLin(str);
            f.escriureLin("");
            logger.finer("Titol escrit: " + str);
         
            for (int i = 0; i < lon; i++) {
               dada=" "+ llObjs[i] ;
               f.escriureDada(dada);
            }
            f.escriureLin(linea);
            for (int i = 0; i < lon; i++) {
               dada=llObjs[i]  + " ";
               f.escriureDada(dada);
               for (int j = 0; j < lon; j++) {
                  dada=matriu[i][j]+" ";
                  f.escriureDada(dada);
               }
               f.escriureLin(linea);
            }
            f.tancarEsc();
         }
             catch (IOException e) {
            }
      }
   /**
   * Metode que escriu la matriu de distancies en un fitxer .tex
   *
   * @param matriu conte els valors de les distancies calculades
   *
   * @author Jose I Mateos
   * @version v.0 16/2/07
   */
       public void escriuMatriuDisTex(Object[][] matriu,int colum, OpcionsDis opc)  {
      
         int arxiu;
         String nomp;
         String[]llObjs;
         String dada;
         int lon;
         FitxerTex f;
         int i=0;
         String metrica="";
         String linea="";
         String str ="";
      
      
         llObjs= objectes.llistarIDsObjs();
         arxiu=nom.lastIndexOf("\\");
         nomp=nom.substring(arxiu+1);
         f = new FitxerTex(dirResultats+nomp+"Dis");
      
         switch(opc.getTipus()){
         
            case OpcionsDis.EUCLI:
               metrica = "Euclídia";
               break;
         
            case OpcionsDis.GOWER:
               metrica = "Gower";
               break;
         
            case OpcionsDis.ABS:
               metrica = "Valor absolut";
               break;
         
            case OpcionsDis.MINKO:
               metrica = "Minkovski amb p="+ opc.getP();
               break;
         
            case OpcionsDis.HAMM:
               metrica = "Hamming Generealitzat";
               break;
         
            case OpcionsDis.CHI2:
               metrica = "Chi2";
               break;
         
            case OpcionsDis.MIXTA:
               metrica = "Mixta de Gibert amb $\\alpha$ ="+ opc.getAlfa()+ " i $\\beta$="+ opc.getBeta();
               break;
         
            case OpcionsDis.RALAM:
               metrica = "Ralambondrainy ";
               break;
         
            case OpcionsDis.GOWDA:
               metrica = "Gowda-Diday";
               break;
         
            case OpcionsDis.ICHINO:
               metrica = "Ichino-Yaguchi amb p="+ opc.getP()+ " i $\\gamma$="+ opc.getGamma();
               break;
         }
         switch(opc.getCateg()){
         
            case OpcionsDis.NONOR:
               metrica = metrica + " sense normalitzar ";
               break;
         
            case OpcionsDis.SK:
               metrica = metrica + " normalitzada per la desviació tipus ";
               break;
         
            case OpcionsDis.RANG:
               metrica = metrica + " normalitzada pel rang ";
               break;
         
            case OpcionsDis.INER:
               metrica = metrica + " normalitzada per la Inèrcia";
               break;
         
            case OpcionsDis.NORMA:
               metrica = metrica + " normalitzada per la Norma";
               break;
         }
      
         if (opc.getQuad()){
            metrica = metrica + " al quadrat";
         }
         if (opc.getPond()){
            metrica = metrica + " ponderada";
         }
      
      
         try {
         
         //f.copiarCapsaleraTex(Constants.FITXER_CAP_CLASS);
            f.copiarCapsaleraTex(Constants.FITXER_HORIT_CLASS);
            f.obrirPerEscriure(true);
            lon=matriu[0].length;
         
         
            str = "\\footnotesize{\\bf Matriu de distàncies de l'arxiu " +nomp + " calculada sobre la mètrica " + metrica + "} \\vfill";
            f.escriureLin(str);
            f.escriureLin("");
            logger.finer("Titol escrit: " + str);
         
         
            while ((i+colum) <= lon){
               taula(llObjs,f,i,i+colum,matriu,lon);
               i=i+colum;
            }
         
            if (i<lon){
               taula(llObjs,f,i,lon,matriu,lon);
            }
            f.escriureDada("\\end{landscape}");
            f. finalitzarTex();
         }
             catch (IOException e) {
            }
             catch (CreacioFitxerException e) {
            }
      
      }
   //DISTANCIES*********************************************************************
   /**
   * Metode que escriu la taula de resultats per el fitxer latex
   *
   * @param llObj es la llsta d'objectes
   * @param f es el fitxer on escriure la taula
   * @param i es l'apuntador de la fila de la taula
   * @param colum es l'apntador de la columna de la taula
   * @param matriu conte els valors de les resultats a escriure
   * @param lon conte la llargada en columnes del fitxer latex
   *
   * @author Jose I Mateos
   * @version v.0 7/5/06
   */
   
       private void taula(String[]llObjs,FitxerTex f, int i, int colum,Object[][] matriu,int lon){
      
         String linea="";
         String dada;
      
         try {
            f.escriureDada("\\footnotesize\\begin{tabular}{");
            for (int j = i; j < colum; j++) {
               f.escriureDada("c");
            }
            f.escriureDada("cc}");
            f.escriureLin(linea);
            f.escriureDada("&");
            for (int j = i; j < colum; j++) {
               f.escriureDada("&"+llObjs[j]);
            }
            f.escriureDada("\\\\");
            f.escriureLin(linea);
            f.escriureDada("\\hline");
            f.escriureLin(linea);
         
            for (int k = 0; k < lon; k++) {
               if ((k%32)==0 && k>0){
                  f.escriureDada("\\end{tabular} \\ \\newpage");
                  f.escriureLin(linea);
                  f.escriureDada("\\begin{tabular}{");
                  for (int j = i; j < colum; j++) {
                     f.escriureDada("c");
                  }
                  f.escriureDada("cc}");
                  f.escriureLin(linea);
                  f.escriureDada("&");
                  for (int j = i; j < colum; j++) {
                     f.escriureDada("&"+llObjs[j]);
                  }
                  f.escriureDada("\\\\");
                  f.escriureLin(linea);
                  f.escriureDada("\\hline");
                  f.escriureLin(linea);
               }
               f.escriureDada(llObjs[k]+"& \\vline" );
               for (int j = i; j < colum; j++) {
                  dada="&"+(String)matriu[k][j];
                  f.escriureDada(dada);
               }
               f.escriureDada("\\\\");
               f.escriureLin(linea);
            }
            f.escriureDada("\\end{tabular} \\ \\newpage");
            f.escriureLin(linea);
         }
             catch (IOException e) {
            }
      }
   
   
   /**
   * Metode que escriu la matriu de correlaciones en un fitxer .co
   *
   * @param cor conte els valors de les correclacions o covariances calculades
   *
   * @author Jose I Mateos
   * @version v.0 7/5/06
   */
       public void escriuMatriuCo(Object[][] co, String opc){
      
         int arxiu;
         String nomp;
         String[][]llProps;
         String dada="";
         int lon;
         String linea="";
         String str ="";
         FitxerCo f;
      
         llProps= propietats.llistarIDsPropietats();
         arxiu=nom.lastIndexOf("\\");
         nomp=nom.substring(arxiu+1);
         f = new FitxerCo(dirResultats+nomp);
      
         try {
         
            f.obrirPerEscriure(true);
            lon=co[0].length;
         
            str = "Matriu " + opc +" de l'arxiu " +nomp ;
            f.escriureLin(str);
            f.escriureLin("");
            logger.finer("Titol escrit: " + str);
         
            for (int i = 0; i < lon; i++) {
               dada=" "+ llProps[0][i] ;
               f.escriureDada(dada);
            }
            f.escriureLin(linea);
            for (int i = 0; i < lon; i++) {
               dada=llProps[0][i]  + " ";
               f.escriureDada(dada);
               for (int j = 0; j < lon; j++) {
                  dada=(String)co[i][j]+" ";
                  f.escriureDada(dada);
               }
               f.escriureLin(linea);
            }
            f.tancarEsc();
         }
             catch (IOException e) {
            }
      }
   /**
   * Calcula si hi ha missings en les propietats de la matriu
   *
   * @return cert o fals depenent si hi ha missings o no
   *
   * @author Jose I Mateos
   * @version v.0 25/6/06
   */
       public boolean obtenirMiss(){
      
         int miss=0;
         String[][] llProps;
         int lonprop0=0;
         PropNumerica proNum;
         EstadisticsNum estadsN;
      
         llProps=propietats.llistarIDsPropietats();
         lonprop0=llProps[0].length;
         for (int i = 0; i < lonprop0; i++) {
            proNum = (PropNumerica) propietats.obtenirPropietat(propietats.obtenirIndex(llProps[0][i]));
            estadsN = proNum.obtenirEstadistics();
            miss=miss + estadsN.obtenirNumMissings();
         }
         return (miss>0);
      }
   /**
   * Substitueix de la matriu actual els missings per valor subs
   *
   * @param subs es un valor que si val 0 es substitueixen els valors per 0 o sino per la mitjana
   *
   * @author Jose I Mateos
   * @version v.0 25/6/06
   */
       public void subs(int subs)
      {
         String[][] llProps;
         String[] llObjs;
         int lonobj=0;
         int lonprop0=0;
         int fila;
         int col;
         float[] mitjana;
         PropNumerica proNum;
         EstadisticsNum estadsN;
         llProps=propietats.llistarIDsPropietats();
         lonprop0=llProps[0].length;
         llObjs=objectes.llistarIDsObjs();
         lonobj = llObjs.length;
         Dada dada;
         mitjana=new float[lonprop0];
      
         for (int k = 0; k < lonprop0; k++) {
            col=propietats.obtenirIndex(llProps[0][k]);
            proNum = (PropNumerica) propietats.obtenirPropietat(col);
            estadsN = proNum.obtenirEstadistics();
            mitjana[k]=estadsN.obtenirMitjana();
         }
      
      
         for (int i = 0; i < lonobj; i++) {
            fila=objectes.obtenirIndex(llObjs[i]);
            for (int k = 0; k < lonprop0; k++) {
               col=propietats.obtenirIndex(llProps[0][k]);
               if (dades.obtenirDada(fila,col).esMissing()) {
                  switch(subs){
                     case 0:
                        dada=new Dada(new String("0"));
                        break;
                     default:
                        dada=new Dada(new String(String.valueOf(mitjana[k])));
                        break;
                  }
                  dades.modificarDada(fila,col,dada);
                  proNum = (PropNumerica) propietats.obtenirPropietat(col);
                  estadsN = proNum.obtenirEstadistics();
                  estadsN.eliminarMissing(dada);
               }
            }
         }
      }
   /**
   * Obte la matriu de dades de la matriu actual
   *
   * @return una matriu amb totes les dades de la matriu actual
   *
   * @author Jose I Mateos
   * @version v.0 25/6/06
   */
       public MatriuDades obtenirDades(){
         return dades;
      }
   /**
   * Obte la matriu de correlacions de la matriu actual
   *
   * @param e factor que eleva la correlació
   *
   * @return una matriu amb totes les correlacions de les propietats
   *
   * @author Jose I Mateos
   * @version v.0 25/6/06
   */
       public String[][] correlacio(int e){
      
         String[][] llProps;
         CalcDis calculs = new CalcDis();
         int lonprop0=0;
         PropNumerica propsN[]=new PropNumerica[2];
         int fila;
         int col;
         CalculsBivNN calcNN=new CalculsBivNN();
         String[][] corre;
      
         llProps=propietats.llistarIDsPropietats();
         lonprop0=llProps[0].length;
         corre=new String[lonprop0][lonprop0];
         for (int i = 0; i < lonprop0; i++) {
            fila=propietats.obtenirIndex(llProps[0][i]);
            for (int j = 0; j < lonprop0; j++) {
               col=propietats.obtenirIndex(llProps[0][j]);
               if(i==j){
                  corre[i][j]="1";
               }
               else if (i>j){
                  corre[i][j]=corre[j][i];
               }
               else{
                  propsN[0] = (PropNumerica) propietats.obtenirPropietat(fila);
                  propsN[1] = (PropNumerica) propietats.obtenirPropietat(col);
                  calcNN.afegirProps(propsN);
                  calcNN.afegirDadesX(dades.obtenirColumna(fila));
                  calcNN.afegirDadesY(dades.obtenirColumna(col));
                  calcNN.calcularCorrelacio();
                  corre[i][j]=String.valueOf((float)(Math.pow(calcNN.obtenirCorrel(),e)));
               }
            }
         }
         return corre;
      }/**
   * Obte la matriu de covariances de la matriu actual
   *
   *
   * @return una matriu amb totes les covariances de les propietats
   *
   * @author Jose I Mateos
   * @version v.0 25/6/06
   */
       public String[][] covarianza(){
      
         String[][] llProps;
         CalcDis calculs = new CalcDis();
         int lonprop0=0;
         PropNumerica propsN[]=new PropNumerica[2];
         int fila;
         int col;
         CalculsBivNN calcNN=new CalculsBivNN();
         String[][] cov;
      
         llProps=propietats.llistarIDsPropietats();
         lonprop0=llProps[0].length;
         cov=new String[lonprop0][lonprop0];
         for (int i = 0; i < lonprop0; i++) {
            fila=propietats.obtenirIndex(llProps[0][i]);
            for (int j = 0; j < lonprop0; j++) {
               col=propietats.obtenirIndex(llProps[0][j]);
               if(i==j){
                  cov[i][j]="1";
               }
               else if (i>j){
                  cov[i][j]=cov[j][i];
               }
               else{
                  propsN[0] = (PropNumerica) propietats.obtenirPropietat(fila);
                  propsN[1] = (PropNumerica) propietats.obtenirPropietat(col);
                  calcNN.afegirProps(propsN);
                  calcNN.afegirDadesX(dades.obtenirColumna(fila));
                  calcNN.afegirDadesY(dades.obtenirColumna(col));
                  calcNN.calcularCorrelacio();
                  cov[i][j]=String.valueOf((float)(calcNN.obtenirCoVar()));
               }
            }
         }
         return cov;
      }
   
   /**
   * Obte la llista de frequencies pels valors de l'obejcte xic,tant si es un objecte extes com sino
   *
   * @param xic es un vector amb dades categoriques
   *
   * @return una llista amb els valors de les frequencies
   *
   * @author Jose I Mateos
   * @version v.0 25/6/06
   */
       private String[] calculFreq(Dada[] xic, boolean pond){
       //  System.out.println("ENTRE EN CALCUL FREQ");
         int lonprop1=0;
         int lonprop2=0;
         int lonprop3=0;
         String[]freq;
         PropCategorica proCat;
         PropNominal proNom;
         PropOrdinal proOrd;
         EstadisticsCateg estadsC;
         int col;
         String x=null;
         String num=null;
         String[][] llProps;
         DadaExtesa ve;
      
		
		  ArrayList activi=new ArrayList();
	  activi= obtenirActivas();
	  
	  // for (int i = 0; i<activi.size(); i++){
            //ActiveProps.add(i,llistaActive[i]);
     //       System.out.println( "LAS Activiiiii"+  activi.get(i));
       //  }

	// System.out.println( "hola imprimo activasel"+activasel);  
	  
 if (activasel){
  System.out.println( "hola estoy en activasel");
 llProps = propietats.llistarIDsPropietatsEnBasicsA(activi);
 System.out.println( "hola sali de acaaaaaaa?????");
 
 }
 else
     {llProps=propietats.llistarIDsPropietats();}

		
/////////
/*
Estado esta;
	  
if  (estadosel){
String [] varsest=null;
String actual = estadoactual;
	for(int j=0;j<llEstados.size();j++){
					esta = (Estado) (llEstados.get(j)); 
					if (esta.nom==estadoactual){
					varsest=esta.variables;}
          }
llProps = propietats.llistarIDsPropietasEstados(varsest);
//System.out.println( "esta marcadoxsvcx");
}
else{
llProps=propietats.llistarIDsPropietats();
//System.out.println( "no esta marcadzcxvzvo");
}

*/

///////////		
		
      
      
        // llProps=propietats.llistarIDsPropietats();//ale comento 16/4
         lonprop1=llProps[1].length;  
         lonprop2=llProps[2].length;
         lonprop3=llProps[3].length;
			
		
			
         freq=new String[lonprop1+lonprop2+lonprop3];
      
        // System.out.println("lonprop1="+llProps[1].length);
			// System.out.println("lonprop1="+llProps[1].length);

     //    System.out.println("lonprop2="+llProps[2].length);
       //  System.out.println("lonprop3="+llProps[3].length);
      
      
         for (int k = 0; k < lonprop1; k++) {
            col=propietats.obtenirIndex(llProps[1][k]);
           // System.out.println("a ver si encuentra el col este?"+col);
            proCat = (PropCategorica) propietats.obtenirPropietat(col);
         
            estadsC = proCat.obtenirEstadistics();
          //  System.out.println("sale del obtenirEstadistics?");
				// System.out.println("xic[k]"+xic[k]);
            if (!xic[k].esMissing()){
              // System.out.println("sale del esMissing?");
               if(xic[k].esExtesa()){
                //  System.out.println("entro en este if");
                  ve= new DadaExtesa(xic[k].obtenirValor());
                  ve.inicialitzar();
                  freq[k]="(";
                  while (ve.noFin()){
					//	System.out.println("habra entrado en este while");
                     x=ve.modalitat();//Conte quina es la modalitat del valor extes
                     num=ve.fraccio();//Conte el valor de la modalitat del valor extes
                   //  System.out.println("x"+x);
                     if (!pond){
                        freq[k]=freq[k]+String.valueOf(estadsC.obtenirFreq(x))+",";
                     }
                     else{
                        freq[k]=freq[k]+String.valueOf(estadsC.obtenirFreqPes(x))+",";
                     }
                  }
                  freq[k]=freq[k].substring(0,(freq[k].length())-1);
                  freq[k]=freq[k]+")";
               }
               else{
                  if (!pond){
						 // System.out.println("esto es lo ultimo que hace antes de morir");	
                     freq[k]=String.valueOf(estadsC.obtenirFreq(xic[k].categ()));
                    // System.out.println("para mi que no sale de aca");	
                  }
                  else{
                     freq[k]=String.valueOf(estadsC.obtenirFreqPes(xic[k].categ()));
                    // System.out.println("o entra para mi que entra aca");	
                  }
               }
            }
            else
            {
               freq[k]=String.valueOf(estadsC.obtenirNumMissings());
            }
         }
         for (int k = 0; k < lonprop2; k++) {
		//	System.out.println("al segundo for no entra no?");
            col=propietats.obtenirIndex(llProps[2][k]);
            proNom = (PropNominal) propietats.obtenirPropietat(col);
            estadsC = proNom.obtenirEstadistics();
            if (!xic[k+lonprop1].esMissing()){
               if(xic[k+lonprop1].esExtesa()){
                  ve=new DadaExtesa(xic[k+lonprop1].obtenirValor());
                  ve.inicialitzar();
                  freq[k]="(";
                  while (ve.noFin()){
                     x=ve.modalitat();//Conte quina es la modalitat del valor extes
                     num=ve.fraccio();//Conte el valor de la modalitat del valor extes
                     if (!pond){
                        freq[k+lonprop1]=freq[k+lonprop1]+String.valueOf(estadsC.obtenirFreq(x))+",";
                     }
                     else{
                        freq[k+lonprop1]=freq[k+lonprop1]+String.valueOf(estadsC.obtenirFreqPes(x))+",";
                     }
                  }
                  freq[k+lonprop1]=freq[k+lonprop1].substring(0,(freq[k+lonprop1].length())-1);
                  freq[k+lonprop1]=freq[k+lonprop1]+")";
               }
               else{
                  if (!pond){
                     freq[k+lonprop1]=String.valueOf(estadsC.obtenirFreq(xic[k+lonprop1].categ()));
                  }
                  else{
                     freq[k+lonprop1]=String.valueOf(estadsC.obtenirFreqPes(xic[k+lonprop1].categ()));
                  }
               }
            }
            else
            {
               freq[k+lonprop1]=String.valueOf(estadsC.obtenirNumMissings());
            }
         }
         for (int k = 0; k < lonprop3; k++) {
			//	System.out.println("al tercer for no entra no?");
            col=propietats.obtenirIndex(llProps[3][k]);
            proOrd = (PropOrdinal) propietats.obtenirPropietat(col);
            estadsC = proOrd.obtenirEstadistics();
            if (!xic[k+lonprop2].esMissing()){
               if(xic[k+lonprop2].esExtesa()){
                  ve=new DadaExtesa(xic[k+lonprop2].obtenirValor());
                  ve.inicialitzar();
                  freq[k]="(";
                  while (ve.noFin()){
                     x=ve.modalitat();//Conte quina es la modalitat del valor extes
                     num=ve.fraccio();//Conte el valor de la modalitat del valor extes
                     if (!pond){
                        freq[k+lonprop2]=freq[k+lonprop2]+String.valueOf(estadsC.obtenirFreq(x))+",";
                     }
                     else{
                        freq[k+lonprop2]=freq[k+lonprop2]+String.valueOf(estadsC.obtenirFreqPes(x))+",";
                     }
                  }
                  freq[k+lonprop2]=freq[k+lonprop2].substring(0,(freq[k+lonprop2].length())-1);
                  freq[k+lonprop2]=freq[k+lonprop2]+")";
               }
               else{
                  if (!pond){
                     freq[k+lonprop2]=String.valueOf(estadsC.obtenirFreq(xic[k+lonprop2].categ()));
                  }
                  else{
                     freq[k+lonprop2]=String.valueOf(estadsC.obtenirFreqPes(xic[k+lonprop2].categ()));
                  }
               }
            }
            else
            {
               freq[k+lonprop2]=String.valueOf(estadsC.obtenirNumMissings());
            }
         }
         return freq;
      }
		
		
		
 private String[] calculFreqEst(String[] varsest,Dada[] xic, boolean pond){
       //  System.out.println("ENTRE EN CALCUL FREQ");
         int lonprop1=0;
         int lonprop2=0;
         int lonprop3=0;
         String[]freq;
         PropCategorica proCat;
         PropNominal proNom;
         PropOrdinal proOrd;
         EstadisticsCateg estadsC;
         int col;
         String x=null;
         String num=null;
         String[][] llProps;
         DadaExtesa ve;
      
		
		  ArrayList activi=new ArrayList();
	  activi= obtenirActivas();
	  
	  // for (int i = 0; i<activi.size(); i++){
            //ActiveProps.add(i,llistaActive[i]);
     //       System.out.println( "LAS Activiiiii"+  activi.get(i));
       //  }

	// System.out.println( "hola imprimo activasel"+activasel);  
	  
 if (activasel){
 // System.out.println( "hola estoy en activasel");
 llProps = propietats.llistarIDsPropietatsEnBasicsA(activi);
 //System.out.println( "hola sali de acaaaaaaa?????");
 
 }
 else
     {llProps=propietats.llistarIDsPropietats();}

		
/////////
Estado esta;
	  
llProps = propietats.llistarIDsPropietasEstados(varsest);



///////////		
		
      
      
        // llProps=propietats.llistarIDsPropietats();//ale comento 16/4
         lonprop1=llProps[1].length;  
         lonprop2=llProps[2].length;
         lonprop3=llProps[3].length;
			
		
			
         freq=new String[lonprop1+lonprop2+lonprop3];
      
        // System.out.println("lonprop1="+llProps[1].length);
			// System.out.println("lonprop1="+llProps[1].length);

     //    System.out.println("lonprop2="+llProps[2].length);
       //  System.out.println("lonprop3="+llProps[3].length);
      
      
         for (int k = 0; k < lonprop1; k++) {
            col=propietats.obtenirIndex(llProps[1][k]);
           // System.out.println("a ver si encuentra el col este?"+col);
            proCat = (PropCategorica) propietats.obtenirPropietat(col);
         
            estadsC = proCat.obtenirEstadistics();
          //  System.out.println("sale del obtenirEstadistics?");
				// System.out.println("xic[k]"+xic[k]);
            if (!xic[k].esMissing()){
              // System.out.println("sale del esMissing?");
               if(xic[k].esExtesa()){
                //  System.out.println("entro en este if");
                  ve= new DadaExtesa(xic[k].obtenirValor());
                  ve.inicialitzar();
                  freq[k]="(";
                  while (ve.noFin()){
					//	System.out.println("habra entrado en este while");
                     x=ve.modalitat();//Conte quina es la modalitat del valor extes
                     num=ve.fraccio();//Conte el valor de la modalitat del valor extes
                   //  System.out.println("x"+x);
                     if (!pond){
                        freq[k]=freq[k]+String.valueOf(estadsC.obtenirFreq(x))+",";
                     }
                     else{
                        freq[k]=freq[k]+String.valueOf(estadsC.obtenirFreqPes(x))+",";
                     }
                  }
                  freq[k]=freq[k].substring(0,(freq[k].length())-1);
                  freq[k]=freq[k]+")";
               }
               else{
                  if (!pond){
						 // System.out.println("esto es lo ultimo que hace antes de morir");	
                     freq[k]=String.valueOf(estadsC.obtenirFreq(xic[k].categ()));
                    // System.out.println("para mi que no sale de aca");	
                  }
                  else{
                     freq[k]=String.valueOf(estadsC.obtenirFreqPes(xic[k].categ()));
                    // System.out.println("o entra para mi que entra aca");	
                  }
               }
            }
            else
            {
               freq[k]=String.valueOf(estadsC.obtenirNumMissings());
            }
         }
         for (int k = 0; k < lonprop2; k++) {
		//	System.out.println("al segundo for no entra no?");
            col=propietats.obtenirIndex(llProps[2][k]);
            proNom = (PropNominal) propietats.obtenirPropietat(col);
            estadsC = proNom.obtenirEstadistics();
            if (!xic[k+lonprop1].esMissing()){
               if(xic[k+lonprop1].esExtesa()){
                  ve=new DadaExtesa(xic[k+lonprop1].obtenirValor());
                  ve.inicialitzar();
                  freq[k]="(";
                  while (ve.noFin()){
                     x=ve.modalitat();//Conte quina es la modalitat del valor extes
                     num=ve.fraccio();//Conte el valor de la modalitat del valor extes
                     if (!pond){
                        freq[k+lonprop1]=freq[k+lonprop1]+String.valueOf(estadsC.obtenirFreq(x))+",";
                     }
                     else{
                        freq[k+lonprop1]=freq[k+lonprop1]+String.valueOf(estadsC.obtenirFreqPes(x))+",";
                     }
                  }
                  freq[k+lonprop1]=freq[k+lonprop1].substring(0,(freq[k+lonprop1].length())-1);
                  freq[k+lonprop1]=freq[k+lonprop1]+")";
               }
               else{
                  if (!pond){
                     freq[k+lonprop1]=String.valueOf(estadsC.obtenirFreq(xic[k+lonprop1].categ()));
                  }
                  else{
                     freq[k+lonprop1]=String.valueOf(estadsC.obtenirFreqPes(xic[k+lonprop1].categ()));
                  }
               }
            }
            else
            {
               freq[k+lonprop1]=String.valueOf(estadsC.obtenirNumMissings());
            }
         }
         for (int k = 0; k < lonprop3; k++) {
			//	System.out.println("al tercer for no entra no?");
            col=propietats.obtenirIndex(llProps[3][k]);
            proOrd = (PropOrdinal) propietats.obtenirPropietat(col);
            estadsC = proOrd.obtenirEstadistics();
            if (!xic[k+lonprop2].esMissing()){
               if(xic[k+lonprop2].esExtesa()){
                  ve=new DadaExtesa(xic[k+lonprop2].obtenirValor());
                  ve.inicialitzar();
                  freq[k]="(";
                  while (ve.noFin()){
                     x=ve.modalitat();//Conte quina es la modalitat del valor extes
                     num=ve.fraccio();//Conte el valor de la modalitat del valor extes
                     if (!pond){
                        freq[k+lonprop2]=freq[k+lonprop2]+String.valueOf(estadsC.obtenirFreq(x))+",";
                     }
                     else{
                        freq[k+lonprop2]=freq[k+lonprop2]+String.valueOf(estadsC.obtenirFreqPes(x))+",";
                     }
                  }
                  freq[k+lonprop2]=freq[k+lonprop2].substring(0,(freq[k+lonprop2].length())-1);
                  freq[k+lonprop2]=freq[k+lonprop2]+")";
               }
               else{
                  if (!pond){
                     freq[k+lonprop2]=String.valueOf(estadsC.obtenirFreq(xic[k+lonprop2].categ()));
                  }
                  else{
                     freq[k+lonprop2]=String.valueOf(estadsC.obtenirFreqPes(xic[k+lonprop2].categ()));
                  }
               }
            }
            else
            {
               freq[k+lonprop2]=String.valueOf(estadsC.obtenirNumMissings());
            }
         }
         return freq;
      }
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
   
   /**
   * Obte la suma de les correlacions de la matriu actual
   *
   * @return la suma de la matriu de correlacions
   *
   * @author Jose I Mateos
   * @version v.0 19/9/06
   */
       public float valorCorrelacio(){
      
         String[][] corre;
         float correla;
         String[][] llProps;
         int lonprop0;
      
         llProps=propietats.llistarIDsPropietats();
         lonprop0=llProps[0].length;
         corre=correlacio(1);
         correla=0;
         for (int i = 0; i < lonprop0; i++) {
            for (int j = 0; j < lonprop0; j++) {
               correla=correla+ Float.parseFloat(corre[i][j]);
            }
         }
         return correla;
      }
   /**
   * Obte la suma de les modalitats de la matriu actual
   *
   * @return la suma de les diferents modalitats
   *
   * @author Jose I Mateos
   * @version v.0 19/9/06
   */
       public int valorModal(){
      
         String[][] llProps;
         int lonprop1=0;
         int lonprop2=0;
         int lonprop3=0;
         PropNumerica proNum;
         EstadisticsNum estadsN;
         PropCategorica proCat;
         PropNominal proNom;
         PropOrdinal proOrd;
         EstadisticsCateg estadsC;
         int modal=0;
      
         llProps=propietats.llistarIDsPropietats();
         lonprop1=llProps[1].length;
         lonprop2=llProps[2].length;
         lonprop3=llProps[3].length;
      
         for (int k = 0; k < lonprop1; k++) {
            proCat = (PropCategorica) propietats.obtenirPropietat(propietats.obtenirIndex(llProps[1][k]));
            estadsC = proCat.obtenirEstadistics();
            modal=modal+ estadsC.obtenirMods();
         };
         for (int k = 0; k < lonprop2; k++) {
            proNom = (PropNominal) propietats.obtenirPropietat(propietats.obtenirIndex(llProps[2][k]));
            estadsC = proNom.obtenirEstadistics();
            modal=modal+ estadsC.obtenirMods();
         };
         for (int k = 0; k < lonprop3; k++) {
            proOrd = (PropOrdinal) propietats.obtenirPropietat(propietats.obtenirIndex(llProps[3][k]));
            estadsC = proOrd.obtenirEstadistics();
            modal=modal+ estadsC.obtenirMods();
         };
         return modal;
      }
   
   
       public void prova(){
      
      
      }
   
   
        //  Funciones nuevas que necesito  --  Rober
   
        /**
         * Funcio que realitza la classificacio de la Matriu Actual
         *
         * @ param metode.  "Veïns recíprocs encadenats".
         * @ param criteri.  "Centroide" y "Ward".
         * @ param opc.  String que conte les opcions de classificació.
         * @ param tipus.  "Gibert" y "Moda".
         * @ param archivoHis.  String que indica el nom del Fitxer *.his.
         * @ param archivoDrm.  String que indica el nom del Fitxer *.drm.
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
   
   
        /*public boolean classificarMatriu( String metode, String criteri, OpcionsDis opc, String identificador, String tipus, String archivoHis, String archivoDrm, boolean ficheroHis, boolean ficheroDrm)throws
            CreacioPropietatsException, CreacioObjectesException,
            CreacioMatriuException {
   
          boolean clasificada = false;
   
          try {
            logger.info("Classificarem la matriu de dades.");
            GestorClasificacion gc = crearGestorClasif ();
            if ( gc.comprobarIdClase(identificador) ) {
              gc.modifFicheroResultadosHis(archivoHis);
              gc.modifFicheroResultadosDrm(archivoDrm);
              gc.classificarMatriu( metode, criteri, opc, tipus, ficheroHis, ficheroDrm) ;
              clasificada = true;
            }
          }
          catch (Exception e) {
            throw new CreacioMatriuException(e.getMessage());
          }
   
          return clasificada;
        } : */
       public boolean classificarMatriu(/* String metode, */String criteri, OpcionsDis opc, String identificador, String tipus, String archivoHis, String archivoDrm, boolean ficheroHis, boolean ficheroDrm)throws
       CreacioPropietatsException, CreacioObjectesException,
       CreacioMatriuException {
      
         boolean clasificada = false;
      
         try {
            logger.info("Classificarem la matriu de dades.");
            GestorClasificacion gc = crearGestorClasif();
         //			if (null == identificador || gc.comprobarIdClase(identificador) ) {
            if (true) {         gc.modifIdClasse ( identificador );
          //  System.out.println("esta en gestormatriz con identificador"+identificador);

               if (null != archivoHis) { gc.modifFicheroResultadosHis(archivoHis); }
               if (null != archivoDrm) { gc.modifFicheroResultadosDrm(archivoDrm); }
					 System.out.println("antes de gc.classificarMatriu");
               gc.classificarMatriu( /*metode, */criteri, opc, tipus, ficheroHis, ficheroDrm) ;
					 System.out.println("despues de gc.classificarMatriu");
               clasificada = true;
            }
         }
             catch (Exception e) {
               throw new CreacioMatriuException(e.getMessage());
            }
         return clasificada;
      }
   
	
	
	
 public boolean classificarMatriuEst(/* String metode, */String est, String[] varsest,  String criteri, OpcionsDis opc, String identificador, String tipus, String archivoHis, String archivoDrm, boolean ficheroHis, boolean ficheroDrm)throws
       
		 CreacioPropietatsException, CreacioObjectesException,
       CreacioMatriuException {
      System.out.println("antes  ver que hay en identificador"+ identificador);
         boolean clasificada = false;
      
         try {
            logger.info("Classificarem la matriu de dades.");
            GestorClasificacion gc = crearGestorClasif();
         //			if (null == identificador || gc.comprobarIdClase(identificador) ) {
            if (true) {         gc.modifIdClasse ( est );
           // System.out.println("esta en gestormatriz con identificador"+identificador);

               if (null != archivoHis) { gc.modifFicheroResultadosHis(archivoHis); }
               if (null != archivoDrm) { gc.modifFicheroResultadosDrm(archivoDrm); }
					System.out.println("a ver que hay en est"+est);
					System.out.println("a ver que hay en criteri"+criteri);
					System.out.println("a ver que hay en opcion"+opc);
					System.out.println("a ver que hay en tipus"+ tipus);
               gc.classificarMatriuEst( /*metode, */est,varsest,criteri, opc, tipus, ficheroHis, ficheroDrm) ;
               clasificada = true;
            }
         }
             catch (Exception e) {
               throw new CreacioMatriuException(e.getMessage());
            }
         return clasificada;
      }
   
	
	
	
	
	
	
	
	
	
	
	
   
        /**
         * Funcio que genera un objecte GestorClasificacion
         *
         * @ param
         * @ return gc.  Retorna un objecte GestorClasificacio amb les dades del GestorMatriu actual
         *
         * <p>Title: Java-KLASS</p>
         * <p>Description: Paquet estadístic</p>
         * <p>Copyright: Copyright (c) 2006</p>
         * <p> </p>
         * @author Rober
         * CLASSIFICACIÖ
         * @version v.0
         */
   
       GestorClasificacion crearGestorClasif ( ) {
         GestorClasificacion gc = new GestorClasificacion ( this);
         return gc;
      }
   
   
        /**
         * Constructor
         *
         * @ param nomDades.  Nom de la matriu.
         * @ param idMatriu.  Identificador numèric assignat a la matriu
         * @ param gestorClasifica.  Objecte on es trobaran els valors del nou GestorMatriu
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
       GestorMatriu(String nomDades, int idMatriu, GestorClasificacion gestorClasifica) throws CreacioPropietatsException, CreacioObjectesException, CreacioMatriuException {
      
         id = idMatriu;
         nom = nomDades;
      
         try {
            propietats = new LlistaPropietats(this);
            carregarPropietats1(gestorClasifica);
            logger.fine("Creada la llista de propietats.");
         
            objectes = new LlistaObjectes(this);
            carregarObjectes1(gestorClasifica);
            logger.fine("Creada la llista d'objectes.");
         
            dades = new MatriuDades(objectes.obtenirLong(),  propietats.obtenirLong(), this);
            carregarDades1(gestorClasifica);
            logger.info("Carregades les dades.");
         
            propietats.llista.trimToSize();
            ordreProps = new ArrayList(propietats.obtenirLong());
            for (int i = 0; i < propietats.obtenirLong(); i++) {
               ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
            }
            ordreProps.trimToSize();
            actualitzarDirResultats(new File(nom).getParent() + File.separator + "resultats" + File.separator);
            llArbres = gestorClasifica.obtenirGesMatri().llArbres;
         }
             catch (CreacioPropietatsException e) {
               throw e;
            }
             catch (CreacioObjectesException e) {
               throw e;
            }
             catch (Exception e) {
               System.out.println("dades exception");
               throw new CreacioMatriuException(e.getMessage());
            }
      }
   
        /**
         * S'inicialitza la matriu de dades dimensionant-la segons leslogituds de les
         * llistes de propietas i objectes.
         */
       void inicialitzarDades() {
         dades = new MatriuDades(objectes.obtenirLong(),  propietats.obtenirLong(), this);
      }
   
          /**
         * Funció que carrega la informació de les propietats associades a la matriu
         *
         * @ param gestorClasifica.  Objecte on es trobaran els valors del nou GestorMatriu
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
       private void carregarPropietats1(GestorClasificacion gestorClasifica) throws CreacioPropietatsException{
         Propietat pro;
         int longitudLlista;
      
         longitudLlista = gestorClasifica.obtenirGesMatri().obtenirLlistaProps().obtenirLong();
      
         for ( int i = 0; i < longitudLlista; i ++ ) {
         
            try {
               pro = gestorClasifica.obtenirGesMatri().obtenirLlistaProps().obtenirPropietat(i);
               if (pro instanceof PropCategorica) {
                  PropCategorica propAuxCat = (PropCategorica) pro;
                  String id = propAuxCat.obtenirId();
                  ArrayList llMod = propAuxCat.llModalitats;
               
                  PropCategorica propAuxCat1 = new PropCategorica ( id, llMod );
                  propietats.afegirProp(propAuxCat1);
               }
               else if (pro instanceof PropNominal) {
                  PropNominal propAuxNom = (PropNominal) pro;
                  String id = propAuxNom.obtenirId();
                  ArrayList llMod = propAuxNom.llModalitats;
               
                  PropNominal propAuxNom1 = new PropNominal ( id, llMod );
                  propietats.afegirProp(propAuxNom1);
               }
               else if (pro instanceof PropOrdinal) {
                  PropOrdinal propAuxOrd = (PropOrdinal) pro;
                  String id = propAuxOrd.obtenirId();
                  ArrayList llMod = propAuxOrd.llModalitats;
               
                  PropOrdinal propAuxOrd1 = new PropOrdinal ( id, llMod );
                  propietats.afegirProp(propAuxOrd1);
               }
               else {
               
                  PropNumerica propAuxNum = (PropNumerica) pro;
                  String id = propAuxNum.obtenirId();
                  float minim = propAuxNum.obtenirRangMin() ;
                  float maxim = propAuxNum.obtenirRangMax() ;
               
                  PropNumerica propAuxNum1 = new PropNumerica ( id, minim, maxim );
                  propietats.afegirProp(propAuxNum1);
               }
            }
                catch (Exception e) {
                  throw new CreacioPropietatsException(e.getMessage());
               }
         }
      }
   
   
          /**
        * Funció que carrega la informació dels objectes associats a la matriu
        *
        * @ param gestorClasifica.  Objecte on es trobaran els valors del nou GestorMatriu
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
   
       private void carregarObjectes1(GestorClasificacion gestorClasifica) throws CreacioObjectesException{
         Objecte obj;
         int posicion, indice;
         float pes;
         String identificador;
      
         try {
            posicion = gestorClasifica.obtenirObjectesAux().obtenirLong();
            for ( int i = 0; i < posicion; i++ ) {
               if ( ! gestorClasifica.obtenirAgregados()[i] ) {
                  obj = (Objecte)gestorClasifica.obtenirObjectesAux().obtenirObjecte(i);
                  identificador = obj.obtenirId();
                  pes = obj.obtenirPes();
                  indice = objectes.obtenirLong();
                  objectes.afegirObjecteClase(indice, identificador, pes);
               }
            }
         }
             catch (Exception e) {
               throw new CreacioObjectesException(e.getMessage());
            }
      }
   
   
          /**
        * Funció que carrega les dades associades a la matriu
        *
        * @ param gestorClasifica.  Objecte on es trobaran els valors del nou GestorMatriu
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
   
       private void carregarDades1(GestorClasificacion gestorClasifica ) throws CreacioMatriuException, CreacioPropietatsException{
         String identDato,identObjeto;
         Dada [] dato;
         float  peso;
      
         for ( int i = 0; i < objectes.obtenirLong(); i++ ) {
            identObjeto = objectes.obtenirObjecte(i).obtenirId();
            peso = objectes.obtenirObjecte(i).obtenirPes();
            dato = gestorClasifica.obtenirFilaDada(identObjeto);
         
            for ( int j = 0; j < dato.length; j++ ) {
               identDato = (String) dato[j].obtenirValor().toString();
               dades.omplirDada(i,j,identDato, peso);
            }
         }
      
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
   
       public ArrayList obtenirListaGibert ( String nom, float peso ) {
      
         ArrayList lista = new ArrayList();
         String s1 = new String();
         String s2 = new String();
         float p=0;
         int parentesis = 0;
         char c;
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
   
   
       public ArbreClassif obtenirArbre () {
         return obtenirArbre(arbreActual);
      }
        
       public ArbreClassif obtenirArbre (int pos) {
         return (ArbreClassif)llArbres.get(pos);
      }
   
       public void canviarNodePerArbre (ArbreClassif arbre, boolean controlInversions) throws FormatIncorrecteException {
         NodeBinari arrelSubarbre = arbre.obtenirArrel();
         String idSubarbre = arrelSubarbre.obtenirEtiqueta();
         ArbreClassif arbreGM = (ArbreClassif)llArbres.get(arbreActual);
         arbreGM.controlInversions(controlInversions);
         NodeBinari fullaResidual = arbreGM.obtenirNode(idSubarbre);
         if (null == fullaResidual) {
            throw new FormatIncorrecteException("No s'ha trobat l'arrel del subarbre '"+ idSubarbre + "' a l'arbre de la classe residual");
         } 
         else if (!fullaResidual.esFulla()) {
            throw new FormatIncorrecteException("L'arrel del subarbre '"+ idSubarbre + "' no és una fulla a l'arbre de la classe residual");
         }
         NodeBinari fillEsq = arrelSubarbre.obtenirFillEsq();
         fullaResidual.modifFillEsq(fillEsq);
         arbreGM.afegirNodesArbre(fillEsq);
         NodeBinari fillDret = arrelSubarbre.obtenirFillDret();
         fullaResidual.modifFillDret(fillDret);
         arbreGM.afegirNodesArbre(fillDret);
         fullaResidual.modifIndexNivell(arrelSubarbre.obtenirIndexNivell());
      }
   
         /**
          * Funció que afegeix un ArbreClassif a la llista d'arbres del gestorMatriu.
          *
          * @ param arbol.  Objecte que es vol afegir a la llista d'arbres del gestorMatriu.
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
   
       public void agregarArbol (ArbreClassif arbol) {
         arbreActual = llArbres.size();
         llArbres.add(arbol);
      }
   
   
   
         /**
          * Funció que retrona el nom de la matriu sense els direcctoris on es guardat el fitxer.
          *
          * @ param
          * @ return String amb el nom de la matriu sense els direcctoris on es guardat el fitxer.
          *
          * <p>Title: Java-KLASS</p>
          * <p>Description: Paquet estadístic</p>
          * <p>Copyright: Copyright (c) 2006</p>
          * <p> </p>
          * @author Rober
          * CLASSIFICACIÖ
          * @version v.0
          */
   
       public String obtenirNomMatriuRelatiu(){
         String nom;
         String nomArxiu;
         int arxiu;
      
         nom = obtenirNomMatriu();
         arxiu=nom.lastIndexOf("\\");
         nomArxiu=nom.substring(arxiu+1);
         return nomArxiu;
      
      }
   
       String obtenirValorVariable(String nomVar) {
         int index = obtenirLlistaProps().obtenirIndex(nomVar);
         Objecte o = obtenirLlistaObjs().obtenirObjecte(0);
         Dada[] registre = obtenirFila(o.obtenirId());
         Dada propReg = registre[index];
         return propReg.obtenirValor().toString();
      }
   
       public void modificaArbreActual(String nomArbre) {
         boolean trobat = false;
         int i, s = llArbres.size();
         for (i = 0; !trobat && i < s; i ++) {
            trobat = nomArbre.equals(((ArbreClassif)llArbres.get(i)).obtenirNom());
         }
         if (trobat) { arbreActual = i - 1; }
         else { arbreActual = s - 1; }
      }
   
      public ArrayList obtenirLlArbres(){
         return llArbres;
      }
    
         /*
         public int obtenirId (){
           return id;
         }
      */
   ///////////////////////////////LAIA ///////////////////////////////////////////////    
         /**  
          * Obté la base de coneixement que s'identifica pel nom <code>nomBC</code>
          * @return BaseConeixement de nom <code>nomBC</code>
          * @author Laia Riera Guerra
          */
       public BaseConeixement obtenirBC(String nomBC){
         return llistaBC.obtenirBC(nomBC);
      }
         
    
	      /**
          * Crea una base de coneixement que s'identificarà pel nom <code>snom</code> i que quedarà associada a la matriu de dades actual
          * @param snom, nom amb que s'identificarà la base de coneixement creada
          * @return BaseConeixement creada
          * @throws Exception
          * @author Laia Riera Guerra
          */
       public BaseConeixement crearBC(String snom)throws Exception{	 
         if(llistaBC.existeixBC(snom))throw new Exception("Ja existeix una base de coneixement amb aquest nom");
         else{
            BaseConeixement bc=new BaseConeixement(llistaBC.numeroSeguentBC(),this,snom);
            llistaBC.afegirBC(bc);
            return bc;
         }  
      }
       
       /**
        * Crea una base de coneixement probabilizada que s'identificarà pel nom <code>snom</code> i que quedarà associada a la matriu de dades actual
        * @param snom, nom amb que s'identificarà la base de coneixement creada
        * @return BaseConeixement creada
        * @throws Exception
        * @author Alfons Bosch, Patricia Garcia
        */
       public BaseConeixementProb crearBCProb(String snom)throws Exception{	 
     	  if(llistaBC.existeixBC(snom))throw new Exception("Ja existeix una base de coneixement amb aquest nom");
     	  else{
     		  BaseConeixementProb bc=new BaseConeixementProb(llistaBC.numeroSeguentBC(),this,snom);
     		  llistaBC.afegirBC(bc);
     		  return bc;
     	  }  
       }
		 
		 /**
        * Crear una base de coneixement probabilitzada a partir d'una base de coneixement
        * probabilitzada existent i un llindar de probabilitat minim i maxim
        * @param llindarMinim - Llindar de probabilitat minim
        * @param llindarMaxim - Llindar de probabilitat maxim
        * @param bc - BC original
        * @author Alfons
        */
       public BaseConeixement crearBCProb(float llindarMinim, float llindarMaxim, String nomBC) throws Exception{
           BaseConeixementProb bc = (BaseConeixementProb) obtenirBC(nomBC);
           BaseConeixementProb novaBC = crearBCProb(bc.getNomBC() + "_" + llindarMinim + "-" + llindarMaxim);
           for ( String nomRegla : (List<String>)bc.getAlNomsRegles() ){
               ReglaProb r = (ReglaProb)bc.obtenirRegla(nomRegla);
               if ( r.prob >= llindarMinim && r.prob <= llindarMaxim ){
                   ReglaProb r2 = novaBC.crearReglaProb(r.getExpressio(), r.getConseguent(), novaBC.seguentNomRegla(), (float)r.getProb());
                   novaBC.afegirRegla(r2);
               }
           }
           return novaBC;
       }



		 
		 
       
         /**
          * Obté l'enter identificador del gestorMatriu actual
          * @return id
          * @author Laia Riera Guerra
          */
       public int obtenirId(){
         return id;
      }
         
         /**
          * Afegeix la propietat <code>p</code> al llistat de propietats de la matriu de dades
          * @param p, propietat a afegir a la llista de propietats de la matriu de dades
          * @throws CreacioPropietatsException
          * @author Laia Riera Guerra
          */
       public void afegirPropietat(Propietat p) throws CreacioPropietatsException{
         propietats.afegirProp(p);
      	 // propietatsa.afegirProp(p);
      }
   
   /**
          * Afegeix la propietat <code>p</code> al llistat de propietats de la matriu de dades
          * @param p, propietat a afegir a la llista de propietats de la matriu de dades
          * @throws CreacioPropietatsException
          * @author Alejadnro Garcia
          */
        // public void afegirPropietata(Propietat p) throws CreacioPropietatsException{
       	//  propietatsa.afegirProp(p);
         //}
   
         
         /**
          * Obté la llista de Bases de coneixement associada a la matriu
          * @return LlistaBC associada a la matriu de dades
          * @author Laia Riera Guerra
          */
       public LlistaBC obtenirLlistaBC(){
         return llistaBC;
      }
         /**
          * Obté la llista d'identificadors( que no són els noms sinó els camps claus de la hastable que conté les bases de coneixement ) de les bases de coneixement associades a la matriu
          * @return llista dels identificadors de les bases de coneixement associades a la matriu de dades
          * @author Laia Riera Guerra
          */
       public ArrayList obtenirLlistaIdsBC(){
         return llistaBC.llista;
      }
         /**
          * Obté l'enter que es correspon al contador de regles de la base de coneixement que s'identifica pel nom <code>nomBC</code>
          * @param nomBC, nom amb que s'identifica la base de coneixement
          * @return el valor del contador de regles de la base de coneixement de nom <code>nomBC</code>
          * @author Laia Riera Guerra
          */
       public int idReglaPerDefecte(String nomBC){
         int i=llistaBC.obtenirIndex(nomBC);
         BaseConeixement bc=llistaBC.obteBC(i);
         return bc.getContador();
      }
         /**
          * Obté un llistat de noms de les regles que pertanyen a la base de coneixement <code>bc</code>
          * @param bc, base de coneixement de la qual es volen obtenir els noms de les regles
          * @return ArrayList amb els noms de les regles de la base de coneixement <code>bc</code>
          * @author Laia Riera Guerra
          */
       public ArrayList obtenirReglesBC(BaseConeixement bc){  	 
         return bc.getAlNomsRegles();
      }
         
         /**
          * Obté un llistat de noms de les regles que pertanyen a la base de coneixement de nom <code>nomBC</code>
          * @param nomBC,nom de la base de coneixement de la qual es volen obtenir els noms de les regles
          * @return ArrayList amb els noms de les regles de la base de coneixement <code>bc</code>
          * @author Laia Riera Guerra
          */
       public ArrayList obtenirNomsReglesBC(String nomBC){
         BaseConeixement bc=llistaBC.obtenirBC(nomBC);
         return bc.getAlNomsRegles();
      }
         
         /**
          * Obté un llistat de noms de les bases de coneixement associades a la matriu de dades
          * @return ArrayList amb els noms de les bases de coneixement
          * @author Laia Riera Guerra
          */
       public ArrayList obtenirNomsBC(){
         return llistaBC.alNoms;
      }
         
         /**
          * Afegeix la propietat prop com una nova propietat de la matriu de dades.
          * @param prop, Propietat que es vol afegir
          * @param Mdades, tupla de dades que es correspon amb els valors en columna de la propietat <code>prop</code>
          * @throws CreacioPropietatsException
          * @throws CreacioMatriuException
          * @author Laia Riera Guerra
          */
       public void afegirColumna(Propietat prop,Dada[] Mdades) throws CreacioPropietatsException, CreacioMatriuException{
         this.dades.ampliar(0,1);//creem la nova columna buida
         propietats.afegirProp(prop);//afegim la nova propietat amb el seu ordre
         ordreProps.add(ordreProps.size(), prop.obtenirId());
      	 //alejandro agregue estoooo
        // activeProps.add(activeProps.size(), prop.obtenirId());
      
         int m = dades.obtenirNumFiles();
         if (m != Mdades.length) {
            throw new CreacioMatriuException("Falten dades per algun objecte de la matriu.");
         }
         int col=propietats.obtenirLong();
         for(int i=0;i<m;i++){//Posem les dades de la nova columna  		 
            dades.modificarDada(i, col-1, Mdades[i]);
         }	 
      }
         /**
          * Elimina l'última propietat de la matriu de dades
          * @author Laia Riera
          *
          */
       public void eliminarColumna()throws CreacioPropietatsException, CreacioMatriuException{
         this.dades.reduir(1);
         propietats.eliminarProp();
         ordreProps.remove(ordreProps.size()-1);    	
      }
         
         /**
          * Afegeix les propietat contingudes a la llista <code>llistaProps</code> a la matriu de dades
          * @param llistaProps, llista amb les propietats a afegir a la matriu de dades
          * @param mDades, llista que conté a cada posició una tupla de Dades. Aquestes tuples són els valors per columnes de cada propietat. 
          * Cada posició de la llista <code>mDades</code> va associada a la mateixa posició de la llista <code>llistaProps</code>
          * @throws CreacioMatriuException
          * @throws CreacioPropietatsException
          * @author Laia Riera Guerra
          */
       public void afegirColumnes(ArrayList llistaProps, ArrayList mDades) throws CreacioMatriuException, CreacioPropietatsException{
         for(int i=0;i<llistaProps.size();i++){
            Propietat prop=(Propietat)llistaProps.get(i);
            Dada[] dat=(Dada[])mDades.get(i);		 
            this.afegirColumna(prop, dat);
         }
      }
         
         /**
          * Elimina les últimes <code>nCols</code> columnes i propietats de la matriu de dades actual
          * @param nCols, número de columnes a eliminar
          * @throws CreacioMatriuException
          * @throws CreacioPropietatsException
          * @author Laia Riera Guerra
          */
       public void eliminarColumnes(int nCols)throws CreacioMatriuException, CreacioPropietatsException{
         for(int i=0;i<nCols;i++){
            eliminarColumna();
         }
      }
         
         /**
          * Crea una nova base de coneixement que és una còpia de la base de coneixement que s'identifica pel nom <code>BaseC</code>
          * @param BaseC, nom amb que s'identifica la base de coneixement a copiar
          * @return BaseConeixement còpia.
          * @author Laia Riera Guerra
          */
       public BaseConeixement copiarBC(String BaseC){
         int i=this.llistaBC.obtenirIndex(BaseC);  	
         BaseConeixement bc=llistaBC.obteBC(i);  	
         return bc.copiarBaseConeixement();	  
      }
         
         /**
          * Guarda els canvis efectuats sobre la base de coneixement <code>bc</code>
          * @param bc, base de coneixement de la qual es volen guardar els canvis
          * @author Laia Riera Guerra
          */
       public void guardarCanvis(BaseConeixement bc){
         this.llistaBC.guardarCanvis(bc);
      }
         /**
          * Obté l'string que representa la regla que s'identifica pel nom <code>ob</code> en forma de inordre.
          * @param ob, nom amb que s'indentifica la regla de la que es vol obtenir l'escrit en forma d'inordre
          * @param BaseC, base de coneixement que conté la regla
          * @return String que representa la regla en forma d'inordre
          * @throws Exception
          * @author Laia Riera Guerra
          */
       public String escriureRegla(String ob,BaseConeixement BaseC)throws Exception{  
         String s=BaseC.escriureReglaNormal(ob);  	  
         return s;
      }
         
       /**
        * Obté la llista de les regles escrites en inordre que pertanyen a la base de coneixement que s'identifica pel nom <code>nomBaseC</code>
        * @param nomBaseC,nom amb què s'identifica la base de coneixement 
        * @return ArrayList amb les regles escritas en inordre
        * @throws Exception
        * @author Laia Riera Guerra, Alfons Bosch, Patrícia Garcia
        */
      public ArrayList escriureReglaNormal(String nomBaseC)throws Exception{
        FitxerTex fTex=new FitxerTex("nou");
        ArrayList resultat=new ArrayList();
        BaseConeixement BaseC=this.obtenirBC(nomBaseC);
        ArrayList llistaNomsRegles=BaseC.getAlNomsRegles();
        for(int i=0;i<llistaNomsRegles.size();i++){
            String ob=(String)llistaNomsRegles.get(i);
            String s=BaseC.escriureReglaNormalLatex(ob);
            String consequent=BaseC.obtenirConsequent(ob);
            String res = "";
            Regla regla = BaseC.obtenirRegla(ob);
            if (  regla instanceof ReglaProb ){
                double prob = ((ReglaProb)regla).getProb();
                NumberFormat format = NumberFormat.getInstance();
                format.setMaximumFractionDigits(2);
                
                res="$"+fTex.adaptarATex(ob)+": "+s+"\\stackrel{" + format.format(prob) + "}" + "{\\longrightarrow} "+fTex.adaptarATex(consequent)+"$\\\\";
            }
            else res="$"+fTex.adaptarATex(ob)+": "+s+"\\longrightarrow "+fTex.adaptarATex(consequent)+"$\\\\";
            resultat.add(res);
        }        
        return resultat;
    }
       
         /**
          * Obté el conseqüent de la regla que s'identifica amb el nom <code>ob</code> que forma part de la base de coneixement <code>BaseC</code>
          * @param ob, nom amb que s'identifica la regla de la qual es vol obtenir el conseqüent
          * @param BaseC, base de coneixement a la que pertany la regla <code>ob</code>
          * @return conseqüent de la regla <code>ob</code>
          * @throws Exception
          * @author Laia Riera Guerra
          */
       public String obtenirConsequent(String ob,BaseConeixement BaseC)throws Exception{  	 
         String s=BaseC.obtenirConsequent(ob);
         return s;
      }
         
         /**
          * Eliminar la regla que s'identifica pel nom <code>ob</code> del llistat de regles de la base de coneixement bc
          * @param bc, base de coneixement d'on es vol eliminar la regla
          * @param ob, nom amb que s'identifica la regla a eliminar
          * @throws Exception
          * @author Laia Riera Guerra
          */
       public void eliminarRegla(BaseConeixement bc,String ob)throws Exception{
         bc.treureRegla(ob);
      }
         
       public String[] obtenirLlistaNomArbres() {
         int size = llArbres.size();
         String[] arbres = new String[size];
         for(int i = 0; i < size; i++){
            arbres[i]=((ArbreClassif)llArbres.get(i)).obtenirNom();
            System.out.println(i + "-èssim arbre:" + arbres[i]);
         }
         return arbres;
      }
   
         /**
          * Obté la propietat que s'identifica pel nom que es passa com a paràmetre
          * @param sid, nom amb el que s'identifica la propietat a obtenir
          * @return Propietat
          * @author Laia Riera Guerra
          */
       public Propietat obtenirPropietat(String sid)throws Exception{
         return this.propietats.obtenirPropietat(sid);
      }
         
         /**
          * Elimina de la llista de bases de coneixement associada la matriu, la base de coneixement que s'identifica pel nom <code>nomBC</code>
          * @param nomBC nom amb que s'identifica la base de coneixement a eliminar
          * @author Laia Riera Guerra
          */
       public void eliminarBC(String nomBC){
         int i=llistaBC.obtenirIndex(nomBC);
         llistaBC.eliminarBC(i);
      }
         
         /**
          * Obté la matriu de dades, és a dir la tupla que conté objectes Dada, associada al gestor matriu
          * @return tupla [][] amb les dades de la matriu.
          * @author Laia Riera Guerra
          */
       public Object[][] obtenirMatriuDades(){
         return dades.obtenirMatriu();
      }
         
         /**
          * Avalua regla a regla les regles de la base de coneixement que s'identifica pel nom <code>nomBC</code>
          * Es crearan a la matriu de dades tantes noves propietats com regles contingui la base de coneixement avaluada.
          * Les noves propietats s'identificaran pels noms amb que s'identifiquen les regles.Si ja existís a la matriu alguna propietat del mateix nom, la nova propietat es passarà a anomenar nomRegla-id, on id és un enter
          * @param nomBC, nom amb que s'indentifica la base de coneixement a avaluar
          * @param notacioBinaria, booleà que ens indica si els resultats a obtenir han de ser binaris o no
          * @return Llista de les propietats categòriques creades.
          * @throws Exception
          * @author Laia Riera Guerra
          */
       public ArrayList avaluaReglaARegla(String nomBC, boolean notacioBinaria) throws Exception{
         BaseConeixement bc=llistaBC.obtenirBC(nomBC);
         return bc.avaluaReglaARegla(notacioBinaria);
      }
		

 public ArrayList ComprobarProps(String nomBC, String[] propSelec) throws Exception{
         BaseConeixement bc=llistaBC.obtenirBC(nomBC);
         return bc. comprovarPropietatsRegles(propSelec);
      }
		
		
         
         /**
          * Avalua per conseqüents la base de coneixement que s'identifica pel nom <code>nomBC</code>.
          * Es crearan a la matriu tantes noves propietats com conseqüents diferents continguin les regles de la base de coneixement avaluada.
          * Les noves propietats s'identificaran pels noms dels conseqüents corresponents. Si ja existien propietats amb el mateix nom, llavors el nom passa a ser Conseqüent-id, on id és un enter.
          * @param nomBC, nom amb que s'identifica la base de coneixement a avaluar
          * @param notacioBinaria, booleà que ens indica si el resultat a obtenir ha de ser binari o no.
          * @return Llista de les propietats categòriques creades.
          * @throws Exception
          * @author Laia Riera Guerra
          */
       public ArrayList avaluaConsequent(String nomBC, boolean notacioBinaria) throws Exception{
         BaseConeixement bc=llistaBC.obtenirBC(nomBC);
         return bc.avaluaConsequent(notacioBinaria);
      }
         /**
          * Avalua de forma integrada la base de coneixement que s'identifica pel nom <code>nomBC</code>.
          * Es crea una nova propietat de la matriu de nom = <code>nomFinal</code> si aquest no és null, i si no es crea amb el nom de la base de coneixement avaluada
          * L'avaluació es fa enriquida, si el booleà <code>enriquit</code> és cert, si no el resultat obtingut no serà enriquit
          * Si ja existia una propietat amb el mateix nom a la matriu, la nova propietat s'anomenarà com nom-id, on id és un enter
          * @param nomBC, nom amb que s'identifica la base de coneixement a avaluar
          * @param enriquit, ens indica si el resultat ha obtenir serà enriquit o no
          * @param nomFinal, nom amb que s'indentificarà la nova propietat de la matriu ( resultat d'avaluar la base de coneixement). Si <code>nomFinal</code> és null, la nova propietat s'identificarà pel nom de la base de coneixement
          * @return Llista que conté les propietats categòriques que contenen el resultat de l'avaluació.( en aquest cas 1 propietat )
          * @throws Exception
          * @author Laia Riera Guerra
          */
       public ArrayList avaluaIntegrat(String nomBC, boolean enriquit, String nomFinal) throws Exception{
         BaseConeixement bc=llistaBC.obtenirBC(nomBC);
         return bc.avaluaIntegrat(enriquit,nomFinal);	
      }
         /**
          * Avalua l'operació que es passa com a paràmetre <code>soperacio</code> sobre la matriu de dades
          * i crea una nova propietat de nom <code>snom</code> i contingut el resultat de l'avaluació de l'operació
          * @param soperacio String que representa l'operació a calcular
          * @param snom nom de la nova propietat que es crearà
          * @throws Exception
          * @author Laia Riera Guerra
          */
       public void calcular(String soperacio,String snom)throws Exception{
         Operacio op=new Operacio(this);
         op.calcular(soperacio, snom);
      }
         
        /**
          * Realitza l'Anàlisi Descriptiva de tipus pertinent (Univariant o Per Classes) indicat a les opcions amb els estadístics indicats
          * a les llistes d'estadístics i per les variables indicades a les llistes.
          * @param nomsBC, llista que conté els noms de les bases de coneixement avaluades
          * @param llistaVariables, llista que conté llistes, que la seva vegada contenen dos vectors de propietats ( un vector en el format de variables univariant i l'altre per classes)
          * Pel cas Univariant: <br>
          *  llistaVars[0][] - Conté la llista de vars numeriques <br>
          *  llistaVars[1][] - Conté la llista de vars categoriques<br>    
          * Pel cas Per Classe: <br>
          * llistaVarsC[0][] - Conté la llista de vars categ de classe <br>
          * llistaVarsC[1][] - Conté la llista de vars numeriques<br>
          * llistaVarsC[2][] - Conté la llista de vars categoriques<br> 
          * @param llistaEstads, llista Estadístics pel cas Univariant
          * llistaEstads[0] - Conté la llista d'estad. numerics <br>
          * llistaEstads[1] - Conté la llista d'estad. categorics<br>
          * @param llistaEstadsC, llista Estadístics pel cas Per classes
          * llistaEstadsC[0] - Conté la llista d'estad. NN<br>
          * llistaEstadsC[1] - Conté la llista d'estad. NC<br>
          * llistaEstadsC[2] - Conté la llista d'estad. CC<br>
          * @param opcUniv, opcions dels tipus dels estadístics de tipus d'anàlisi descriptiva a realitzar per Univariant
          * @param opcClass, opcions dels tipus dels estadístics de tipus d'anàlisi descriptiva a realitzar per Classes
          * @param avaluacio, enter que indica quin tipus d'avaluació de les bases de coneixement s'ha efectuat
          * 1-->Regla a regla<br>
          * 2-->Per conseqüent<br>
          * 3-->Integrada     
          * @param tipusDescriptiva, enter que indica quin tipus d'ànalisi es fa:
          * 1-->Univariant
          * 2-->Per classes
          * 3-->Univariant i per classes
          * @param esPart, enter que indica si s'ha d'escriure o no el començament i final del fitxer
          * 0-->Escriure començament i final (el fitxer només té una part)
          * 1-->Escriure només el començament (aquesta és la primera part del fitxer)
          * 2-->No escriure res (aquesta és una part del mig del fitxer)
          * 3-->Escriure només el final (aquesta és la última part del fitxer)
          * @param printRegles, indica si cal escriure les regles a l'informe final
          * @author Laia Riera Guerra
          */ 
       public void ferDescripBC(ArrayList nomsBC,ArrayList llistaVariables,Vector[] llistaEstads,Vector[] llistaEstadsC,Opcions opcUniv,Opcions opcClass,int avaluacio,int tipusDescriptiva,int esPart,boolean printRegles, String subfix) throws Exception,CreacioFitxerException, OpcioIncorrectaException, ParamIncorrecteException, CalculException {
         FitxerTex fitxer;
         String nomBase, nomFitxer;
         GeneradorTex gen;	   
         String nomBC;
         ArrayList al;
         String[][] llistaVar;
         String[][] llistaVarC;
         CalculsUniv[][] calculsU;
         CalculsClass[] calculsC;
         ArrayList alCalculsU=new ArrayList();
         ArrayList alCalculsC=new ArrayList();
         ArrayList alReglesNormals=new ArrayList();
         ArrayList alNomsRegles=new ArrayList();
         nomBase = dirResultats + new File(nom).getName();
         //nomFitxer = nomBase + "dreg";
         nomFitxer = nomBase + subfix;
         
         fitxer = new FitxerTex(nomFitxer);
         gen = new GeneradorTex(fitxer); 
         try {
            for(int i=0;i<nomsBC.size();i++){	
               nomBC=(String)nomsBC.get(i);
               ArrayList alRegles=this.escriureReglaNormal(nomBC);
               alReglesNormals.add(alRegles);
               if(avaluacio==1){
                  ArrayList nomsRegles=this.obtenirNomsReglesBC(nomBC);
                  alNomsRegles.add(nomsRegles);
               }
               else if(avaluacio==2){
                  ArrayList nomsRegles=this.obtenirNomsReglesBC(nomBC);
                  ArrayList consequents=new ArrayList();
                  for(int s=0;s<nomsRegles.size();s++){
                     BaseConeixement BaseC=this.obtenirBC(nomBC);
                     String snom=(String)nomsRegles.get(s);
                     String c=this.obtenirConsequent(snom, BaseC);
                     consequents.add(c);
                  }
                  alNomsRegles.add(consequents);	    			
               }
               else{
                  ArrayList bc=new ArrayList();
                  bc.add(nomBC);
                  alNomsRegles.add(bc);
               }
            	
               switch (tipusDescriptiva) {
                  case 1:	    	    			
                     al=(ArrayList)llistaVariables.get(i);
                     llistaVar=(String[][])al.get(0);
                     calculsU = ferCalculsUniv(llistaVar,llistaEstads, opcUniv);
                     alCalculsU.add(calculsU);
                     logger.fine("Calculs per descr. univariant realitzats.");	    			
                     break;	       
                  case 2:	    			
                     al=(ArrayList)llistaVariables.get(i);
                     llistaVarC=(String[][])al.get(1);
                     calculsC = ferCalculsClass(llistaVarC,llistaEstadsC, opcClass);
                     alCalculsC.add(calculsC);
                     logger.fine("Calculs per descr. per classes realitzats.");
                     break;
                  case 3:	    			
                     al=(ArrayList)llistaVariables.get(i);
                     llistaVar=(String[][])al.get(0);         
                     calculsU = ferCalculsUniv(llistaVar,llistaEstads, opcUniv);
                     alCalculsU.add(calculsU);
                     llistaVarC=(String[][])al.get(1);
                     calculsC = ferCalculsClass(llistaVarC,llistaEstadsC, opcClass);
                     alCalculsC.add(calculsC);
                     logger.fine("Calculs per descr.univariant i per classes realitzats.");	    			
                     break;
               }
            
            }
            gen.generarLtxPerVarsBC(nomsBC,llistaVariables,llistaEstads,llistaEstadsC,opcUniv,opcClass,avaluacio,tipusDescriptiva,alCalculsU,alCalculsC,alReglesNormals,alNomsRegles,ordreProps,esPart,printRegles);
         }
             catch (CreacioFitxerException e) {
               throw e;
            }
             catch (IOException e) {
               throw new CreacioFitxerException("Error d'escriptura: " + e.getMessage());
            }
             catch (Exception e) {
               throw e;
            }
          
      }
       
         /**
     	 * Crea una nova propietat de nom = <code>nomPropNova</code> i llista de modalitats= <code> alnomModalitats com a conseqüència de la recodificació de la variable <code>nomPropAntiga</code>
     	 * @param nomPropNova, nom de la nova propietat
     	 * @param nomPropAntiga, nom de la propietat sobre la que s'ha efectuat la recodificació
     	 * @param alnomModalitats,noms de les modalitats de la nova variable
     	 * @param alagrupacionsModals, per cada posició de la llista conté un llistat amb les modalitat de la variable recodificada que es corresponen amb l'agrupació d'aquestes sota la modalitat de la nova variable
     	 * que ocupa la mateixa posició relativa dins el vector <code>alnomModalitats</code>
     	 * @throws Exception
     	 * @author Laia Riera Guerra
     	 */
       public void crearNovaPropietat(String nomPropNova,String nomPropAntiga,ArrayList alnomModalitats,ArrayList alagrupacionsModals)throws Exception{
         if(propietats.existeixPropietat(nomPropNova))throw new Exception("Ja existeix una propietat amb aquest nom");
         else{
            Propietat pValor=obtenirPropietat(nomPropAntiga);
            Dada[] dValor=obtenirColumna(pValor.obtenirId());
            PropCategorica Prop=new PropCategorica(nomPropNova,alnomModalitats);
         		
            Dada[] dNovaCol=new Dada[dValor.length];
            for(int i=0;i<dValor.length;i++){
               String s=(String)dValor[i].obtenirValor();
               String snou=obtenirModalitatCorresponent(alnomModalitats, alagrupacionsModals, s);
               if(snou==null)dNovaCol[i]=new Dada("?");
               else dNovaCol[i]=new Dada(snou);
            }
         		
            afegirColumna(Prop,dNovaCol);
            this.calcularEstadistics(Prop);
         }
         	
      }
         /**
          * Obté la modalitat del llistat <code>alnomModalitats</code> que correspon, segons on es trobi la modalitat <code>mod</code> dins els llistats del llistat <code>alagrupacionsModals</code>
          * @param alnomModalitats, nom de les modalitats ja agruapades
          * @param alagrupacionsModals, llistat que conté les agrupacions de les modalitats sota els noms de la llista <code>alnomModalitats</code>
          * @param mod, modalitat que es vol transformar
          * @return Modalitat del llistat <code>alnomModalitats</code> que és on es troba subagruapada la modalitat <code>mod</code>
          */
       public String obtenirModalitatCorresponent(ArrayList alnomModalitats,ArrayList alagrupacionsModals,String mod){
         String resultat=null;
         boolean b=false;
         for(int i=0;i<alagrupacionsModals.size() && !b;i++){
            String[] grup=(String[])alagrupacionsModals.get(i);
            boolean b2=false;
            for(int j=0;j<grup.length && !b2;j++){
               if(grup[j].compareTo(mod)==0)b2=true;
            }
            if(b2==true){
               b=true;
               resultat=(String)alnomModalitats.get(i);
            }
         }
         return resultat;
      }
   
       GestorMatriu obtenirResidual(int numRegistres, String nomProp) {
         LlistaPropietats newLProp = propietats.obtenirCopia(this);
         newLProp.eliminarProp(nomProp);
         GestorMatriu matriuResidual = new GestorMatriu(id, nom,	newLProp);
         matriuResidual.dades = /*dades = */new MatriuDades(numRegistres,  newLProp.obtenirLong(), this); 
         return matriuResidual;
      }
   
         /**
     	 * Obté el nom de base de coneixement per defecte que no existeixi encara al llistat de bases de coneixement associat a la matriu actual
     	 * Seguint el format següent:BCi on i és un número enter
     	 * 
     	 * @return nom de la base de coneixement per defecte
     	 * @author Laia Riera Guerra	 
     	 */
       public String nomBCPerDefecte(){
         int i=0;
         String s="BC"+i;
         while(llistaBC.existeixBC(s)){
            i++;
            s="BC"+i;
         }
         return s;    	
      }
         /**
     	 * Obté el següent nom de la propietat encara no existent a la matriu actual
     	 * seguint el format següent: <code>snomO</code>i on i és un número enter
     	 * @param snomO, nom base de la propietat
     	 * @return <code>snomO</code>i
     	 * @author Laia Riera Guerra
     	 */
       public String nomPropietatPerDefecte(String snomO){
         int i=0;
         String s=snomO+i;
         while(propietats.existeixPropietat(s)){
            i++;
            s=snomO+i;
         }
         return s;  
      }
         /**
     	 * Posa com a nova llista de bases de coneixment la LlistaBC <code>llistaBC</code>
     	 * @param llistaBC, nova LlistaBC de la matriu actual
     	 * @throws Exception
     	 * @author Laia Riera Guerra
     	 */
       public void setLlistaBC(LlistaBC llistaBC)throws Exception {
         this.llistaBC = llistaBC;
         llistaBC.setM(this);
      }
     	
     	  
       public void eliminarDarreraPropietat() throws CreacioPropietatsException, CreacioMatriuException{
         ordreProps.remove(ordreProps.size()-1);
         propietats.eliminarDarreraProp();
         dades.eliminarDarreraColumna();
      }
   
         /**
          * Elimina la propietat <code>prop</code> de la matriu de dades
          * @param prop, propietat a eliminar
          * @author Laia Riera
          *
          */
       public void eliminarPropietat(Propietat prop)throws CreacioPropietatsException, CreacioMatriuException{
         ArrayList alCols=new ArrayList();
         int col=propietats.obtenirIndex(prop.obtenirId());    	
         this.dades.eliminar(col);
         propietats.eliminarPropietat(prop);
         int index=0;
         boolean b=false;
         for(int i=0;i<ordreProps.size() && !b;i++){
            String s=(String)ordreProps.get(i);
            if(s.compareTo(prop.obtenirId())==0){
               index=i;
               b=true;
            }
         }
         ordreProps.remove(index);    	
      }
         /**
          * Elimina les propietats identificades pels noms continguts a la llista <code>alProps</code>
          * @param alProps, llista que conté els noms de les variables a eliminar
          * @throws Exception, si alguna de les propietats no existeix ja
          * @author Laia Riera Guerra
          */
       public void eliminarPropietats(ArrayList alProps)throws Exception{
         for(int i=0;i<alProps.size();i++){
            String nom=(String)alProps.get(i);
            Propietat p=this.obtenirPropietat(nom);
            this.eliminarPropietat(p);
         }
      }
       
       /**
        * Mètode que permet induir un conjunt de regles a partir d'una (o més) variables de classe i un conjunt de variables numèriques/categòriques
        * @param llistaVars - llistaVars[0] conté les variables de classe, llistaVars[1] conté les variables numèriques, llistaVars[2] conté les variables categòriques
        * @param mkmz - indica si es generaran els fitxers mk i zk al realitzar la discretització de les variables numèriques
        * @param revisat - indica si s'aplicarà el mètode revisat al realitzar la discretització de les variables numèriques
        * @author Alfons Bosch, Patrícia Garcia
        * @return
        */
       public BaseConeixement generacioReglesBoxPlot(String[][] llistaVars, boolean descriptiva, boolean mkmz, boolean revisat) throws Exception{
   		String nomBC = this.nomBCPerDefecte();
   		BaseConeixementProb bc = crearBCProb(nomBC);
       
   		String[] llistaVarsCategoriques = llistaVars[2];
       	String[] llistaVarsNumeriques = llistaVars[1];
       	String[] llistaVarsClase = llistaVars[0];
       	
       	for ( String classe : llistaVarsClase){
       		generarReglesBoxPlotClasse(llistaVarsCategoriques, llistaVarsNumeriques, classe, mkmz, revisat, bc);
       	}  	
       	if ( descriptiva ){
    		
       	}
   		return bc;
   	}
       
       /**
        * Mètode encarregat de induir un conjunt de regles per a una sóla variable de classe
        * @param varsCat - Conjunt de variables categòriques
        * @param varsNum - Conjunt de variables numèriques
        * @param varClasse - Variable de classe 
        * @param mkmz - indica si s'ha de generar els fitxers .mk i .zk
        * @param revisat - indica si s'ha d'aplicar el mètode revisat al realitzar la discretització de les variables numèriques
        * @param bc - base de coneixement on aniran les regles creades
        * @return 
        * @throws Exception
        * @author Alfons Bosch, Patrícia Garcia
        */
       private boolean generarReglesBoxPlotClasse(String[] varsCat, String[] varsNum, String varClasse, boolean mkmz, boolean revisat, BaseConeixementProb bc) throws Exception{
       	//Realitzem la discretització boxplot based de les variables numeriques
       	String[][] numeriquesBBD= new String[2][];
       	String[] varClasseArray = new String[1];
       	varClasseArray[0] = varClasse;
       	numeriquesBBD[0] = varsNum;
       	numeriquesBBD[1] = varClasseArray;
       	List[] intervals = discretitzarBoxPlot(numeriquesBBD, false, revisat,mkmz);
       	List<PropCategorica> varsNumeriquesCat = intervals[0];
      
       	//Creem i omplim els inputs de la funcio que realitzarà els calculs
       	String[][] llistaVarCalculs = new String[6][];
       	llistaVarCalculs[4] = new String[(varsCat.length+varsNumeriquesCat.size())];
       	llistaVarCalculs[5] = new String[(varsCat.length+varsNumeriquesCat.size())];
       	int k=0;
       	for( String varCat : varsCat ){
       		llistaVarCalculs[4][k] = varClasse;
       		llistaVarCalculs[5][k] = varCat;
       		k++;
       	}
       	int iniciVariablesDiscretitzades = k;
       	for ( PropCategorica varNumDiscr : varsNumeriquesCat){
       		llistaVarCalculs[4][k] = varClasse;
       		llistaVarCalculs[5][k] = varNumDiscr.obtenirId();
       		k++;
       	}
       	
       	//Creem els vectors que contindran el resultat 
       	Vector[] llistaEstads = new Vector[3];
       	llistaEstads[0] = null;
           llistaEstads[1] = null;
           llistaEstads[2] = new Vector(1);
           
           Hashtable taula = new Hashtable();
           taula.put("tipus", "freqsFil");
           llistaEstads[2].add(Opcions.T_CONTINGENCIA);
           Opcions opc = new Opcions(Opcions.BIVARIANT);
           opc.afegirOpcions(Opcions.T_CONTINGENCIA,taula);
           CalculsBiv[][] calculs = ferCalculsBiv(llistaVarCalculs, llistaEstads, opc);
                 
           CalculsBivCC[] calculsBiv = (CalculsBivCC[]) calculs[2];
           
           int indexCat = 0;
           for ( CalculsBivCC calculCC : calculsBiv){
           	int numeroModalitat =0;
         		String varCat=llistaVarCalculs[5][indexCat];
           	        
           	//Per cada modalitat de la variable cateogrica
           	for ( String modVarCategorica : calculCC.obtenirModalitatsY() ){
           		//Obtenim les frecuencies
           		CalculsUnivCateg freqsPerModalitat = calculCC.obtenirCalculsUniv(modVarCategorica);
           		for (int i=0;i<freqsPerModalitat.obtenirLonLlistaMods();i++){
           			//Generem una regla per cada una de les modalitats de classe
           			String modalitatClasse = freqsPerModalitat.obtenirModalitat(i);
           			float freqRelativa = freqsPerModalitat.obtenirFreqRel(i);
           			
           			//La variable original era categòrica (a partir de IndexCat totes seran numeriques)
           			if ( indexCat < iniciVariablesDiscretitzades ){
           				bc.generarReglaProb(varCat,modVarCategorica,varClasse,modalitatClasse,freqRelativa);
           			}
           			//La variable original era numèrica
           			else{
           				int indexNumActual = indexCat-iniciVariablesDiscretitzades;
           				bc.generarReglaProbNumerica(varsNum[indexNumActual],numeroModalitat,(List)intervals[1].get(indexNumActual),varClasse,modalitatClasse,freqRelativa);
           			}
           		}
           		
           		numeroModalitat++;
           	}
           	indexCat++;
           }
           
           for ( PropCategorica propAux : (List<PropCategorica>)intervals[0] ){
           	this.eliminarPropietat(propAux);
           }
       	return true;
       }
       
       
       /**
        * Discretitza mitjançant el mètode BoxPlot variables numèriques en combinació amb categòriques.
        * @param llistaVar:
        * llistaVar[0][] - Conté la llista de vars numeriques X de NC
        * llistaVar[1][] - Conté la llista de vars categoriques Y de NC
        * @param fitxers, indica si s'ha de generar el fitxer latex o no
        * @param generarMkZk - indica si s'ha de generar el fitxer mk i el zk
        * @param revisedBoxPlot - indica si s'ha d'aplicar la versió revisada del "boxplot based discretization" en quant a les variables categòriques formades per dues classes.
        * @return List[] - La primera possicio es la llista de propietats creades. La segona conté els intervals amb els que s'ha creat cadaescuna d'aquestes variables.
        * @throws CreacioFitxerException
        * @throws OpcioIncorrectaException
        * @throws ParamIncorrecteException
        * @throws CalculException
        * @throws CreacioPropietatsException 
        * @throws CreacioMatriuException 
        * @throws IOException 
        * @author Laia Riera Guerra, Alfons Bosch, Patrícia Garcia
        */
       public List[] discretitzarBoxPlot(String[][] llVar, boolean fitxers,boolean revisedBoxPlot,boolean generarMkZk) throws CreacioFitxerException, OpcioIncorrectaException, ParamIncorrecteException, CalculException, CreacioMatriuException, CreacioPropietatsException, IOException {
       	ArrayList llistaProp=new ArrayList();
       	List intervalsCalculats = new ArrayList();
   		ArrayList mDades=new ArrayList();
   		GeneradorTex gen;	   
   		FitxerTex fitxer;
   		String nomBase, nomFitxer;
   	    nomBase = dirResultats + new File(nom).getName();
   	    nomFitxer = nomBase + "disc";
   	    fitxer = new FitxerTex(nomFitxer);
   		gen = new GeneradorTex(fitxer); 
   		
         /* Pel cas Bivariant: <br>
          * llistaVar[0][] - Conté la llista de vars numeriques X de NN <br>
          * llistaVar[1][] - Conté la llista de vars numeriques Y de NN <br>
          * llistaVar[2][] - Conté la llista de vars numeriques X de NC<br>
          * llistaVar[3][] - Conté la llista de vars categoriques Y de NC<br>
          * llistaVar[4][] - Conté la llista de vars numeriques X de CC<br>
          * llistaVar[5][] - Conté la llista de vars categoriques Y de CC<br>
          */
         String[][] llistaVar = new String[6][];
        
         /* Pel cas Bivariant: <br>
          * llistaEstads[0] - Conté la llista d'estad. NN <br>
          * llistaEstads[1] - Conté la llista d'estad. NC <br>
          * llistaEstads[2] - Conté la llista d'estad. CC <br>*/
         Vector[] llistaEstads = new Vector[3];
         
         Opcions opc = new Opcions(Opcions.BIVARIANT);
         CalculsBivNC calcNC;
         int[][] mostra;
         float dadesAux[];
         int lon, i, j = 0 , k = 0, lon2;


         
         //num de variables numeriques
         lon2 = llVar[0].length;
         //num de variables categoriques
         lon = llVar[1].length;
         
         
         //Incialitzem el vector de variables de cara a realitzar el càlcul bivariant
         llistaVar[2] = new String[lon * lon2];
         llistaVar[3] = new String[lon * lon2];
         
         //L'omplim amb les combinacions possibles de vars numèriques amb categòriques
         for (i = 0; i < lon; i++) {
           for (j = 0; j < lon2; j++) {
             llistaVar[2][k] = llVar[0][j];
             llistaVar[3][k] = llVar[1][i];
             k++;
           }
         }
         
         //Inicialitzem el vector d'estadistics
         llistaEstads[0] = null;
         llistaEstads[1] = new Vector(1);
         llistaEstads[2] = null;
         
         //Afegim l'opcio de l'estadistic que volem
         llistaEstads[1].add(new Integer(Opcions.DESCR_GR));
         opc.posarPerDefecte(Opcions.DESCR_GR);
         
         //Realitzem els calculs
         CalculsBiv[][] calculs = ferCalculsBiv(llistaVar, llistaEstads, opc);

         //es prepara per generar els fitxers?
         lon = ordreProps.size();
         mostra = new int[lon][lon];
         // inicialització de la matriu de les combinacions de vars a mostrar
         for (i = 0; i < lon; i++) {
           for (j = 0; j < lon; j++) {
             mostra[i][j] = -1;
           }
         }
         
         // Tractament de la llista de vars Num / Categ
         if ( (llistaVar[2] == null) || (llistaVar[3] == null)) {
           lon = 0;
         }
         else {
           lon = llistaVar[2].length;
           if (llistaVar[3].length != lon)
             throw new ParamIncorrecteException("Nombre de params. incorrecte");
         }
         
         //Per cada variable numerica
         for (i = 0; i < lon; i++) {
           if (llistaVar[2][i] != null) {
             j = ordreProps.indexOf(llistaVar[2][i]);
             k = ordreProps.indexOf(llistaVar[3][i]);
             //Posem a la matriu les variables afectades
             mostra[j][k] = i;
           }
         }     
         lon = ordreProps.size();
         
         List modadilitatInterval = new ArrayList(); 
           for (i = 0; i < lon; i++) {
             for (j = 0; j < lon; j++) {        	  
               if (mostra[i][j] != -1) {            	
                 int pos=mostra[i][j];
                 calcNC = (CalculsBivNC) calculs[1][mostra[i][j]];
                 /*** Funcio que genera fitxer d'extrems ***/
                 String[] llMods;
                
                 int r, n=0;
                 CalculsUnivNum calculsUN;
                 llMods = calcNC.obtenirModalitats();
                 dadesAux = new float[llMods.length * 2]; //per cada modalitat es recuperen 2 valors (min i max)
                 for (int s = 0; s < llMods.length; s++) {
               	  
                   calculsUN = calcNC.obtenirCalculsUniv(llMods[s]);
                   dadesAux[n] = calculsUN.obtenirMin();                
                   n++;                
                   dadesAux[n] = calculsUN.obtenirMax();               
                   n++;                
                 }
                 
                 int revisedBoxPlotType = 0;
                 if ( llMods.length == 2 && revisedBoxPlot ){
               	  //si el maxim de la classe 1 es més petit que el mínim de la clase dos o viceversa
               	  if ( dadesAux[1]<dadesAux[2] || dadesAux[3]<dadesAux[0] ){
               		  revisedBoxPlotType = 1;
               	  }
                 }
                 
                 
                 //Ordenem el vector que conté els minims i maxims de les modalitats
                 Arrays.sort(dadesAux);
                 
                 
                 //intervalsCalculats conté els intervals ordenats i per cada interval un boolea que indica si es revised o no
                 //Primer construim el contingut
                 List tuplaIntervalRevised = new ArrayList();
                 tuplaIntervalRevised.add(dadesAux);
                 tuplaIntervalRevised.add(revisedBoxPlot);
                 if ( revisedBoxPlot ){
               	  tuplaIntervalRevised.add(revisedBoxPlotType);
                 }
                 //L'afegim a la llista a (s'utilitza per generar el tex)
                 intervalsCalculats.add(tuplaIntervalRevised);
                 
                 ///Creem la variable, amb les seves modalitats 
                 ArrayList llistaModalitats = new ArrayList();              
                 for(int s=0;s<dadesAux.length-1;s++){
               	  String mod=new String();
               	  mod="I"+s;
               	  llistaModalitats.add(mod);
                 }         
                 
                 String nomN=llistaVar[2][pos];
                 String nomC= llistaVar[3][pos];      
                 String snom=nomN+"_"+nomC;
                 if(propietats.existeixPropietat(snom))snom=nomPropietatPerDefecte(snom);
                 PropCategorica p = new PropCategorica(snom, llistaModalitats);
                 llistaProp.add(p);
                 
                 //creem la columna             
             	  Dada[] d = obtenirColumna(nomN);
                 Dada[] convertit=new Dada[d.length];
                 for(int fila=0;fila<d.length;fila++){
               	  String svalor=d[fila].obtenirValor().toString();
               	  //si valor nul es manté
               	  if(svalor.compareTo("?")==0)convertit[fila]=new Dada("?");
               	  else{
               		  Float f=new Float(svalor);
               		  boolean b=false;
                   	  for(int imod=0;imod<dadesAux.length-1 && !b;imod++){
                    		  	  if ( llistaModalitats.size()==3 && revisedBoxPlot ){
                   		  		  try{
   	                		  		  switch (imod){
   	                		  		  	case 0: 
   	                		  		  		if ( revisedBoxPlotType == 1 ){
   	                		  		  			if(f.floatValue()>=dadesAux[imod]&& f.floatValue()<=dadesAux[imod+1]){
   	                		  		  				b=true;
   	                		  		  				convertit[fila]=new Dada("I"+imod);
   	                		  		  			}
   	                		  		  		}
   	                		  		  		else{
   	                		  		  			if(f.floatValue()>=dadesAux[imod]&& f.floatValue()<dadesAux[imod+1]){
   	                		  		  				b=true;
   	                		  		  				convertit[fila]=new Dada("I"+imod);
   	                		  		  			}
   	                		  		  		}
   	                		  		  		break;
   	                		  		  	case 1: 
   	                		  		  		if ( revisedBoxPlotType == 1 ){
   		                		  		  		if(f.floatValue()>dadesAux[imod]&& f.floatValue()<dadesAux[imod+1]){
   		            		  		  				b=true;
   		            		  		  				convertit[fila]=new Dada("I"+imod);
   		            		  		  			}
   	                		  		  		}
   	                		  		  		else{
   	                		  		  			if(f.floatValue()>=dadesAux[imod]&& f.floatValue()<=dadesAux[imod+1]){
   	                		  		  				b=true;
   	                		  		  				convertit[fila]=new Dada("I"+imod);
   	                		  		  			}
   	                		  		  		}
   	                		  		  		break;
   	                		  		  	case 2:
   	                		  		  		if(f.floatValue()>=dadesAux[imod]&& f.floatValue()<=dadesAux[imod+1]){
   	                		  		  			b=true;
   	                		  		  			convertit[fila]=new Dada("I"+imod);
   	                		  		  		}
   	                		  		  		break;
   	                		  		  	default: throw new Exception();
   	                		  		  }
                   		  		  }
                   		  		  catch(Exception e){
                   		  			  e.printStackTrace();
                   		  		  }
                   		  	  }
                    		  	  else{
   	                		  	  if(f.floatValue()>=dadesAux[imod]&& f.floatValue()<=dadesAux[imod+1]){
   	                    			  b=true;
   	                    			  convertit[fila]=new Dada("I"+imod);
   	                    		  }
                    		  	  }
                   			  
                   		  /*}else{
                   			  if(f.floatValue()>=dadesAux[imod]&& f.floatValue()<dadesAux[imod+1]){
                       			  b=true;
                       			  convertit[fila]=new Dada("I"+imod);
                       		  }
                   		  } */               		  
                   	  }
                   	  if(!b)convertit[fila]=new Dada("?");
               	  }            	 
                 }
                 mDades.add(convertit);              
                 
               }
             }
           }
           afegirColumnes(llistaProp, mDades);	
           for(int s=0;s<llistaProp.size();s++){
           	Propietat p=(Propietat)llistaProp.get(s);
           	this.calcularEstadistics(p);
           }
           if ( generarMkZk ){
   	        try{
   	        	k=0;
   	        	for (i = 0; i < llVar[0].length; i++) {
   	        		 for (j = 0; j < llVar[1].length; j++) {
   	        			 new Extrems((CalculsBivNC)calculs[1][k],llVar[0][i],llVar[1][j],dirResultats);
   	        			 llistaVar[2][k] = llVar[0][i];
   	        	         llistaVar[3][k] = llVar[1][j];
   	        	         k++;
   	        		 }
   	        	}
   	        }
   	        catch (IOException e) {
   	            throw new CreacioFitxerException("Error d'escriptura: " + e.getMessage());
   	        }
           }
           if(fitxers){
           	gen.generarLtxDiscretitzacio(llistaProp,intervalsCalculats,false,1);
           }
           
           List[] ret = new List[2];
           ret[0] = llistaProp;
           ret[1] = intervalsCalculats;

           
           
           return ret;
       }
         /**
     	   * Discretitza mijançant la discretització uniforme variables numèriques
     	   * @param llistaVar:
     	   * llistaVar[0][] - Conté la llista de vars numeriques X de NC
     	   * llistaVar[1][] - Conté la llista de vars categoriques Y de NC	  
     	   * @param numIntervals, número d'intervals en que s'ha de dividir el rang de les variables numèriques, equival al número de modalitats que tindran les noves 
     	   * variables discretitzades
     	   * @param fitxers, indica si s'ha de generar el fitxer latex o no
     	   
     	   * @throws Exception
     	   * @author Laia Riera Guerra
     	   */
       public void discretitzarUniforme(String[][] llistaVars,int numIntervals,boolean fitxers) throws Exception{
         ArrayList llistaProp=new ArrayList();
         ArrayList mDades=new ArrayList();
         GeneradorTex gen;	   
         FitxerTex fitxer;
         String nomBase, nomFitxer;
         nomBase = dirResultats + new File(nom).getName();
         nomFitxer = nomBase + "disc";
         fitxer = new FitxerTex(nomFitxer);
         gen = new GeneradorTex(fitxer); 
      	//creem la llista de modalitats que serà la mateixa per totes les variables ( numIntervals modalitats )
         ArrayList alValors=new ArrayList();
         ArrayList llistaModalitats = new ArrayList(); 	  
         for(int s=0;s<numIntervals;s++){
            String mod=new String();
            mod="I"+s;
            llistaModalitats.add(mod);
         }         
         for(int i=0;i<llistaVars[0].length;i++){
         	//creem la propietat
            String spropNum=llistaVars[0][i];
            Propietat propN=this.obtenirPropietat(spropNum);
            PropNumerica propNum=(PropNumerica)propN;
            String snom=propNum.obtenirId()+"'";
            if(propietats.existeixPropietat(snom)){				
               snom=nomPropietatPerDefecte(snom);
            }
            PropCategorica p=new PropCategorica(snom,llistaModalitats);
            llistaProp.add(p);
         	
         	//creem la columna
            float fmin=propNum.obtenirRangMin();
            float fmax=propNum.obtenirRangMax();
            float longitud=Math.abs(fmax-fmin)/numIntervals;
            float dadesAux[]=new float[numIntervals+1]; 
            for(int j=0;j<numIntervals;j++){
               dadesAux[j]=fmin+longitud*j;
            }
            dadesAux[numIntervals]=fmax;
            Dada[] d = obtenirColumna(propNum.obtenirId());
            Dada[] convertit=new Dada[d.length];
            for(int fila=0;fila<d.length;fila++){
               String svalor=d[fila].obtenirValor().toString();
               if(svalor.compareTo("?")==0)convertit[fila]=new Dada("?");
               else{
                  Float f=new Float(svalor);
                  boolean b=false;
                  for(int imod=0;imod<dadesAux.length-1 && !b;imod++){
                     if(imod==dadesAux.length-2){
                        if(f.floatValue()>=dadesAux[imod]&& f.floatValue()<=dadesAux[imod+1]){
                           b=true;
                           convertit[fila]=new Dada("I"+imod);
                        }
                     }
                     else{
                        if(f.floatValue()>=dadesAux[imod]&& f.floatValue()<dadesAux[imod+1]){
                           b=true;
                           convertit[fila]=new Dada("I"+imod);
                        }
                     }                		  
                  }
                  if(!b)convertit[fila]=new Dada("?");
               }            	 
            }
            alValors.add(dadesAux);
            mDades.add(convertit);
         }
      	
         afegirColumnes(llistaProp, mDades);	
         for(int s=0;s<llistaProp.size();s++){
            Propietat p=(Propietat)llistaProp.get(s);
            this.calcularEstadistics(p);
         }
         if(fitxers){
            gen.generarLtxDiscretitzacio(llistaProp,alValors,false,2);
         }
      }
         
     	  /**
     	   * Discretitza una variable numèrica segons els valors definits per l'usuari
     	   * @param dadesAux, conté el llistat de valors ordenats de menor a major que es corresponen amb els punts de tall dels intervals
     	   * @param llistaModalitats, llistat amb els noms de les modalitats
     	   * @param snomPropietat, nom de la nova propietat discretitzada
     	   * @param fitxers, indica si s'ha de generar el fitxer latex o no
     	   * @throws Exception
     	   * @author Laia Riera Guerra
     	   */
       public void discretitzarManual(float[] dadesAux,ArrayList llistaModalitats,String snomPropietat,boolean fitxers) throws Exception{
         	
         ArrayList llistaProp=new ArrayList();
         ArrayList mDades=new ArrayList();
         GeneradorTex gen;	   
         FitxerTex fitxer;
         String nomBase, nomFitxer;
         nomBase = dirResultats + new File(nom).getName();
         nomFitxer = nomBase + "disc";
         fitxer = new FitxerTex(nomFitxer);
         gen = new GeneradorTex(fitxer); 
      	//creem la llista de modalitats que serà la mateixa per totes les variables ( numIntervals modalitats )
         ArrayList alValors=new ArrayList();
         Propietat propN=this.obtenirPropietat(snomPropietat);
         PropNumerica propNum=(PropNumerica)propN;
         String snom=snomPropietat+"'";
         if(propietats.existeixPropietat(snom)){				
            snom=nomPropietatPerDefecte(snom);
         }
         PropCategorica p=new PropCategorica(snom,llistaModalitats);
         llistaProp.add(p);
      		
      	//creem la columna			
         Dada[] d = obtenirColumna(snomPropietat);
         Dada[] convertit=new Dada[d.length];
         for(int fila=0;fila<d.length;fila++){
            String svalor=d[fila].obtenirValor().toString();
            if(svalor.compareTo("?")==0)convertit[fila]=new Dada("?");
            else{
               Float f=new Float(svalor);
               boolean b=false;
               for(int imod=0;imod<dadesAux.length-1 && !b;imod++){
                  if(imod==dadesAux.length-2){
                     if(f.floatValue()>=dadesAux[imod]&& f.floatValue()<=dadesAux[imod+1]){
                        b=true;
                        convertit[fila]=new Dada(llistaModalitats.get(imod));
                     }
                  }
                  else{
                     if(f.floatValue()>=dadesAux[imod]&& f.floatValue()<dadesAux[imod+1]){
                        b=true;
                        convertit[fila]=new Dada(llistaModalitats.get(imod));
                     }
                  }         
               }
               if(!b)convertit[fila]=new Dada("?");
            }            	 
         }
         alValors.add(dadesAux);
         mDades.add(convertit);    
      	
         afegirColumnes(llistaProp, mDades);	
         for(int s=0;s<llistaProp.size();s++){
            Propietat p2=(Propietat)llistaProp.get(s);
            this.calcularEstadistics(p2);
         }
         if(fitxers){
            gen.generarLtxDiscretitzacio(llistaProp,alValors,false,3);
         }
      }
   
         /**
     	   * Obté un llistat que conté dos llistats. Un d'aquests conté els noms de les bases de coneixement
     	   * que contenen propietats a les regles que no existeixen a la matriu de dades 
     	   * L'altre llistat conté per cada posició un llistat amb els noms de les regles que contenten propietats que no existeixen a la matriu
     	   * @param idMatriuPare, matriu sobre la que es vol comprovar
     	   * @param propSelec, llistat de propietats de la matriu nova
     	   * @return llistat amb els noms de les bases de coneixement i regles que contenen propietats que no estan a <code>propSelec</code>
     	   * @throws Exception
     	   * @author Laia Riera Guerra
     	   */
       public ArrayList comprovarLlistaBC(String[] propSelec)throws Exception{
         return llistaBC.comprovarLlistaBC(propSelec);
         	
      }
         /**
          *  Carrega una matriu de dades a partir d'un fitxer .dat estàndard
          * @param nomDades, nom del fitxer sense extensió
          * @param idMatriu, identificador de la nova matriu
          * @param fila, indica si la primera fila de dades conté els identificadors de les variables
          * @param col, indica si la primera columna de dades conté els identificadors dels objectes
          * @throws CreacioPropietatsException
          * @throws CreacioObjectesException
          * @throws CreacioMatriuException
          * @author Laia Riera Guerra
          */
       GestorMatriu(String nomDades, int idMatriu,boolean fila,boolean col) throws
         CreacioPropietatsException, CreacioObjectesException,
         CreacioMatriuException {
         int numCols=0,numFiles=0,ifila=0,icolumna=0;
         File fResult;
         boolean ok;
         id = idMatriu;
         nom = nomDades;
         FitxerDatStd fDat = new FitxerDatStd(nom);
         try {
            propietats = new LlistaPropietats(this);
            objectes = new LlistaObjectes(this);
            llArbres = new ArrayList();
            llistaBC=new LlistaBC(this);
            tipusColumna=new ArrayList();      		
            ArrayList alMatriu=carregarMatriu(fDat,fila,col);
            System.out.println("tipusColumna.size = "+tipusColumna.size());
            if(col)numCols=tipusColumna.size()-1;//la primera columna son objectes
            else numCols=tipusColumna.size();
            if(fila)numFiles=alMatriu.size()-1;
            else numFiles=alMatriu.size();
            ArrayList alrangs=new ArrayList();
            dades=new MatriuDades(numFiles,numCols,this);			
         	//Carreguem les dades
         	
            if(alMatriu.size()==0)throw new Exception("No hi ha dades");
            else{
               ArrayList alfila=(ArrayList)alMatriu.get(0);
               if(col){    				
                  if((alfila.size()-1)!=numCols)throw new Exception("El nombre d'elements de la fila 0 de dades no es correspon amb el nombre de columnes de la matriu");
               }
               else if(alfila.size()!=numCols)throw new Exception("El nombre d'elements de la fila 0 de dades no es correspon amb el nombre de columnes de la matriu");    			
            		
               if(!fila){//la primera fila també són dades    				
                  icolumna=0;
                  for(int j=0;j<alfila.size();j++){
                     String svalor=(String)alfila.get(j);
                     if(col && j==0){}
                     else{
                        dades.modificarDada(ifila,icolumna,new Dada(svalor));
                        icolumna++;
                     }
                     if(svalor.compareTo("?")==0){
                        alrangs.add("?");
                     }
                     else{
                        Integer itipus=(Integer)tipusColumna.get(j);
                        ArrayList al=new ArrayList();
                        if(itipus.intValue()==0){    						
                           al.add(0,new Float(svalor));//mínim
                           al.add(1,new Float(svalor));//màxim        						
                        }
                        else al.add(svalor);//modalitat
                        alrangs.add(al);
                     }    					
                  }
                  ifila++;
               }
            		
               for(int i=1;i<alMatriu.size();i++){//ja s'ha tractat la primera fila, o no s'ha de tractar
                  ArrayList alfilaAux=(ArrayList)alMatriu.get(i);
                  if(col){
                     if(alfila.size()-1!=numCols)throw new Exception("El nombre d'elements de la fila 0 de dades no es correspon amb el nombre de columnes de la matriu");
                  }
                  else if(alfila.size()!=numCols)throw new Exception("El nombre d'elements de la fila 0 de dades no es correspon amb el nombre de columnes de la matriu");    			
               		
                  icolumna=0;
                  for(int j=0;j<alfilaAux.size();j++){//columnes
                     String svalor=(String)alfilaAux.get(j);
                     if(col && j==0){}
                     else{    						
                        dades.modificarDada(ifila,icolumna,new Dada(svalor));
                        icolumna++;
                     }
                     ArrayList al=new ArrayList();
                     if(svalor.compareTo("?")!=0)
                     {    						
                        Integer itipus=(Integer)tipusColumna.get(j);        					
                        if(itipus.intValue()==0){//numèric    	
                           if(alrangs.size()<=j){//encara no s'ha introduit cap valor
                              al.add(0,new Float(svalor));//mínim
                              al.add(1,new Float(svalor));//màxim
                              alrangs.add(j,al);
                           }
                           else{
                              try {
                                 ArrayList alJ=(ArrayList)alrangs.get(j);
                                 Float f=new Float(svalor);										
                                 Float fminim=(Float)alJ.get(0);
                                 Float fmaxim=(Float)alJ.get(1);
                                 if(f.floatValue()<fminim.floatValue())alJ.set(0,f);
                                 else if(f.floatValue()>fmaxim.floatValue())alJ.set(1,f);
                                 alrangs.set(j,alJ);
                              } 
                                  catch (ClassCastException e) {
                                    al.add(0,new Float(svalor));//mínim
                                    al.add(1,new Float(svalor));//màxim
                                    alrangs.set(j,al);
                                 }
                           }    						
                        }
                        else{//categòrica
                           if(alrangs.size()<=j){
                              al.add(svalor);//modalitat
                              alrangs.add(j,al);
                           }
                           else{
                              try {
                                 ArrayList alJ=(ArrayList)alrangs.get(j);
                                 if(!conteModalitat(alJ,svalor)){
                                    alJ.add(svalor);
                                    alrangs.set(j,alJ);
                                 }
                              } 
                                  catch (ClassCastException e) {
                                    al.add(svalor);//modalitat
                                    alrangs.set(j,al);
                                 }
                           }    						
                        }
                     }  
                     else{
                        if(alrangs.size()<=j)alrangs.add(j,"?");
                     }
                  }
                  ifila++;
               }   			
            }		
         		//creem les propietats
            ArrayList alfila=(ArrayList)alMatriu.get(0);
            for(int j=0;j<tipusColumna.size();j++){
               if(col && j==0){}
               else{
                  Integer itipus=(Integer)tipusColumna.get(j);
                  ArrayList al=(ArrayList)alrangs.get(j);
                  String id;
                  if(fila)id=(String)alfila.get(j);
                  else if(col)id="v"+(j-1);
                  else id="v"+j;    				
                  if(itipus.intValue()==0){
                     Float fmin=(Float)al.get(0);
                     Float fmax=(Float)al.get(1);
                     Propietat p=new PropNumerica(id,fmin.floatValue(),fmax.floatValue());
                     propietats.afegirProp(p);
                  }
                  else{
                     Propietat p=new PropCategorica(id,al);
                     propietats.afegirProp(p);
                  }    				
               }
            }
         		//calculem estadístics de les propietats
         				
            for(int iprop=0;iprop<propietats.obtenirLong();iprop++){
               Propietat p=propietats.obtenirPropietat(iprop);
               this.calcularEstadistics(p);
            }
         		
         		//creem els objectes
            ArrayList alObjectes=(ArrayList)alrangs.get(0);
            for(int i=0;i<numFiles;i++){
               String id;
               if(col)id=(String)alObjectes.get(i);
               else id="o"+i;
               Objecte obj=new ObjSimple(i,id);
               objectes.afegirObj(obj);
            }
         				
            propietats.llista.trimToSize();
            ordreProps = new ArrayList(propietats.obtenirLong());
            for (int i = 0; i < propietats.obtenirLong(); i++) {
               ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
            }
            ordreProps.trimToSize();
            actualitzarDirResultats(new File(nom).getParent() + File.separator +
                                 "resultats" + File.separator);
         }
             catch (CreacioPropietatsException e) {
               throw e;
            }
             catch (CreacioObjectesException e) {
               throw e;
            }
             catch (Exception e) {
               e.printStackTrace();
               throw new CreacioMatriuException(e.getMessage());
            }	
      }
         /**
          * Obté un llistat on cada posició conté un llistat amb els valors de la matriu per files
          * @param fDat, nom del fitxer sense extensió
          * @param fila,indica si la primera fila de dades conté els identificadors de les variables
          * @param col, indica si la primera columna de dades conté els identificadors dels objectes
          * @return llistat que representa els valors de la matriu de dades
          * @throws CreacioMatriuException
          * @throws CreacioPropietatsException
          * @throws Exception
          * @author Laia Riera Guerra
          */
       public ArrayList carregarMatriu(FitxerDatStd fDat,boolean fila,boolean col)throws CreacioMatriuException, CreacioPropietatsException,Exception{
         ArrayList al=new ArrayList();
         StringTokenizer st;
         int i=0;
         try
         {
            fDat.obrirPerLlegir();
         } 
             catch (FileNotFoundException e) {
               logger.warning("ERROR: No s'ha trobat el fitxer de dades");
               throw new CreacioMatriuException("No s'ha trobat el fitxer de dades.");
            }
             
         st = fDat.llegirFilaDades();
         while (st != null) {        	
            ArrayList alFila=omplirDades(i,st,fila,col);
            al.add(alFila);
            st = fDat.llegirFilaDades();
            i++;
         }
         return al;
      
      }
         /**
          * Obté la fila de dades i de la matriu
          * @param i, fila de dades 
          * @param st, StringTokenizer a tractar
          * @param fila,indica si la primera fila de dades conté els identificadors de les variables
          * @param col, indica si la primera columna de dades conté els identificadors dels objectes
          * @return llistat amb els valors de la fila i de la matriu
          * @throws CreacioMatriuException
          * @throws CreacioPropietatsException
          * @author Laia Riera Guerra
          */
       public ArrayList omplirDades(int i,StringTokenizer st,boolean fila,boolean col) throws CreacioMatriuException, CreacioPropietatsException{
         int j,tipus=0;//tipus=0, la columna és numèrica, tipus=1 la columna es categòrica
         String token;       
         ArrayList al=new ArrayList();
         j = 0;       
         while (st.hasMoreTokens()) {
            token = st.nextToken();
            al.add(new String(token));
            if(i==0 && fila){}
            else if(token.compareTo("?")!=0){         
               try{
                  Float f=new Float(token);
                  float fvalor=f.floatValue();
                  if(tipusColumna.size()<=j)tipusColumna.add(new Integer(0));        				 
               }
                   catch(Exception ex){
                     if(tipusColumna.size()<=j)tipusColumna.add(new Integer(1));          			 
                     else{
                        Integer tip=(Integer)tipusColumna.get(j);
                        if(tip.intValue()==0)tipusColumna.set(j,new Integer(1));        			 
                     }
                  }
            }
            else if(tipusColumna.size()<=j)tipusColumna.add(new Integer(0));      
            logger.finer("Valor: " + token);          
            j++;
         }
         if(col){
            if(fila && i==0){}
            else if(j==0)tipusColumna.add(0,new Integer(1));
         }       
         return al;
      }
         
         /**
          * Retorna un booleà que indica si l'string <code>svalor</code> està contingut dins la llista <code>alJ</code>
          * @param alJ, llistat
          * @param svalor, string que volem trobar
          * @return true,si <code>svalor</code> es troba en el llistat <code>alJ</code>, false altrament
          * @author Laia Riera Guerra
          */
       public boolean conteModalitat(ArrayList alJ,String svalor){
         boolean b=false;
         for(int i=0;i<alJ.size() && !b;i++){
            String s=(String)alJ.get(i);
            if(s.compareTo(svalor)==0)b=true;
         }
         return b;
      }
         /**
     	   * Calula els estadístics de la propietat <code>propC</code>
     	   * @param propC, propietat de la qual volem calcular els estadístics
     	   * @throws CreacioPropietatsException
     	   * @author Laia Riera Guerra
     	   */
       public void calcularEstadistics(Propietat propC) throws CreacioPropietatsException{
         Dada[] d = obtenirColumna(propC.obtenirId());
         for(int i=0;i<d.length;i++){    		
            propC.actualitzarLliure(d[i].obtenirValor().toString());    		
         }
      }
         /**
     	   * Obté una còpia de la LListaPropietats de la matriu actual
     	   * @return LListaPropietats
     	   * @author Laia Riera Guerra
     	   */
       public LlistaPropietats obtenirCopiaLlistaPropietats(){
         return propietats.obtenirCopia(this);
      }
         /**
     	   * Posa com a nova LListaPropietats de la matriu actual la LListaPropietats <code>llprop</code>
     	   * @param llprop, nova LlistaPropietats de la matriu actual
     	   * @author Laia Riera Guerra
     	   */
       public void setPropietats(LlistaPropietats propietats) {
         this.propietats = propietats;
      }
     	 /**
     	   * Comprova que els nous valors mínim i màxim de la propietat <code>propC</code> incloguin tots els valors observats per la variable
     	   * @param minim,nou valor mínim de la propietat
     	   * @param maxim, nou valor màxim de la propietat
     	   * @param propC, propietat
     	   * @return 0-->correcte, 1-->mínim incorrecte, 2-->màxim incorrecte
     	   * @author Laia Riera Guerra
     	   */
       public int minMaxCorrectes(float minim,float maxim,Propietat propC){
         int result=0;//0-->correcte, 1-->minim incorrecte, 2-->maxim incorrecte
         Dada[] d = obtenirColumna(propC.obtenirId());
         for(int i=0;i<d.length && result==0;i++){ 
            String obj=d[i].obtenirValor().toString();
            Float f=new Float(obj);
            if(f>maxim)result=2;
            else if(f<minim)result=1;
         }
         return result;
      }
     	 /**
     	   * Obté la llista de propietats de la matriu actual
     	   * @return LlistaPropietats
     	   * @author Laia Riera Guerra
     	   */
       LlistaPropietats obtenirLlistaProps() {
         return propietats;
      }
   
   
   /**
     	   * Obté la llista de propietats de la matriu actual
     	   * @return LlistaPropietats
     	   * @author Alejandro Garcia
     	   */
       LlistaPropietats obtenirLlistaPropsa() {
         return propietatsa;
      }
   
public  ArrayList obtenirActivas() {
         return activeProps;
      }
      
  
public  ArrayList obtenirTots() {
         return ordreProps;
      }

 
public  ArrayList dife(){
    	ArrayList d =new ArrayList();	
	 boolean ok;
	 
	 // System.out.println( "HOLA entre a dife con largo de ordreProps="+ ordreProps.size());
	 // System.out.println( "HOLA entre a dife con largo de activeProps="+ activeProps.size());
	  // for (int x = 0; x< ordreProps.size(); x++){
// System.out.println( "Quien hay en ordreProps="+ ordreProps.get(x));
//}
// for ( int x = 0; x< activeProps.size(); x++){
// System.out.println( "Quien hay en activeProps="+ activeProps.get(x));
//}
	  
if (activeProps.size()>0){
	  
 for (int p = 0; p< ordreProps.size(); p++){
// System.out.println("que devuelve"+activeProps.contains(ordreProps.get(p)));
  
	if ((!activeProps.contains(ordreProps.get(p)))){
	      d.add(ordreProps.get(p));
			}
    }
	 
// for ( int x = 0; x< d.size(); x++){
// System.out.println( "Quien hay en d="+ d.get(x));
//}
}	 
	 
	 
return d;
	 
 } 
 
 
 
 public ArrayList obtenirActive(){
return activeProps;
 }
 
 
	/**
	 * Crea un fitxer de nom <code>sNom</code>.reg on hi haurà escrites en notació polaca
	 * totes les regles de la llista <code>alRegles</code>.
	 * 
	 * @param <code>sNom</code>,nom amb que es guardarà el fitxer d'extensió .reg
	 * @param <code>alRegles</code>,noms identificadors de les regles que es volen escriure al
	 *            fitxer
	 * @throws Exception, si no es pot crear el fitxer correctament
	 */
	public void exportarActivas(String sNom, ArrayList activas)
			throws Exception {
			
	//	boolean success = (new File("sNom")).delete(); 
	
			
	
     	FitxerAct fReg = new FitxerAct(sNom);
		
		 String [] arractivas = new String [activas.size()];
      
      //alejandro 
         for (int p = 0; p < activas.size(); p++) {
                    arractivas[p]=(String)activas.get(p);
                 
         }
		
		try {				
			
			fReg.modificarNom(sNom+ ".act");
			fReg.obrirPerEscriure(false);
			//for (int i = 0; i < activas.size(); i++) {
		    	
			//	Regla reg = obtenirRegla((String) alNomsRegles.get(i));
				fReg. escriureDades(arractivas);
			fReg.tancarEsc();
			//}
			
		} catch (Exception e) {
			
			throw e;
		}// endtry
	}// endofmethod
 
 
    public void ConfirmaEsts(ArrayList estados,ArrayList variables){
         Estado est;
		
		//	for (int k=0;k<estados.size()-1;k++){
	/*	String e;
		e=(String)estados.get(0);
		System.out.println( "a ver si agarra el primer estado??"+e);
		String[] listavars;
		listavars = (String[])variables.get(0);
		 for (int j=0;j<listavars.length;j++){
		   System.out.println( "a ver si agarra las varaibles??"+listavars[j]);
         };
		 System.out.println( "va a intentar hacer el new");
			est= new Estado(e,listavars);
		 System.out.println( "hizo el new");
		System.out.println( "a ver si tiene el nombre"+ est.nom);	

			//est.nom = (String) estados.get(k);
		//	est.variables = (String[])variables.get(k);*/
		System.out.println( "va a agregar");
		
		for (int i=0;i<estados.size()-1;i++){
			est= new Estado((String)estados.get(i),(String[])variables.get(i));
      	llEstados.add(est);
			} 
			System.out.println( "agregó");
     // }
     }
 
   
   
   }

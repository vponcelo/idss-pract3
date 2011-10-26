   package jklass.nucli;

   import java.io.FileNotFoundException;
   import java.io.IOException;
   import java.lang.Math;
   import java.util.ArrayList;
   import java.util.Hashtable;
   import java.util.logging.*;
   import java.util.NoSuchElementException;
   import java.util.StringTokenizer;
   import java.util.Vector;

   import jklass.util.Opcions;

/**
 * Classe que gestiona tot un conjunt de propietats que poden ser de diferents
 * tipus però que estan associades a una mateixa matriu.
 * <p>Title: Java-Klass</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas,Laia Riera Guerra
 * @version v.4
 */
    public class LlistaPropietats extends LlistaIndexos {
      private static Logger logger = Logger.getLogger(LlistaPropietats.class.getName());
   
   /**
   * Constructor
   * @param m matriu amb la que està associada la llista de Propietats
   * @throws CreacioPropietatsException
   */
       LlistaPropietats(GestorMatriu m){
         super(m);
      }
   
       LlistaPropietats obtenirCopia(GestorMatriu m){
         LlistaPropietats copia = new LlistaPropietats(m);
      
         copia.taulaPos = (Hashtable)this.taulaPos.clone();
         copia.llista = (ArrayList)this.llista.clone();
      
         return copia;
      }
   
   /**
   * Afegeix una propietat a la llista de propietat després de crear-la a partir de
   * la informació proporcionada pel paràmetre:<br>
   *  <code> index id_pro  rang  pes  num_objs  tipus_variable  llista_modalitats </code> <br>
   * @param st conjunt d'Strings que contenen la informació per contruir una propietat
   * @throws CreacioPropietatsException si es produeix una error al construir la propietat perque
   * hi ha informació no coherent o falta informació
   */
       void afegirPropietat(StringTokenizer st) throws CreacioPropietatsException{
         String token;
         Propietat pro = null;
         String id;
         String tipusPro;
         float min;
         float max;
         ArrayList llistaModal = new ArrayList();
    // System.out.println( "entre a afegirPropietatenlistaprops");
  
      // Es crea la propietat adecuada
         try{
            token = st.nextToken(); // index numeric
			//System.out.println( "primer token"+token);
	
            token = st.nextToken(); // id
				//	System.out.println( "segundo token id"+token);

            id = token;
            token = st.nextToken(); // rang
				//	System.out.println( "tercer token rang"+token);

            if (token.compareToIgnoreCase(Constants.NULO)==0) {
            // no té rang
				//	System.out.println( "entro al primer if");

               token = st.nextToken(); // peso
               token = st.nextToken(); // num. objetos
               token = st.nextToken(); // tipo propiedad
               if (token.compareToIgnoreCase(Constants.PROP_CATEGORICA)==0) {
                  logger.finer(id + " prop. categorica");
               // modalitats de la propietat
                  while (st.hasMoreTokens()) {
                     token = st.nextToken();
                     logger.finest(token);
                     llistaModal.add(new String(token));
                  }
                  llistaModal.trimToSize();
                  pro = new PropCategorica(id,llistaModal);
               } 
               else if (token.compareToIgnoreCase(Constants.PROP_NOMINAL)==0) {
                  logger.finer(id + " prop. nominal");
               // modalitats de la propietat
                  while (st.hasMoreTokens()) {
                     token = st.nextToken();
                     llistaModal.add(new String(token));
                     logger.finer(token);
                  }
                  llistaModal.trimToSize();
                  pro = new PropNominal(id,llistaModal);
               } 
               else if (token.compareToIgnoreCase(Constants.PROP_ORDINAL)==0) {
                  logger.finer(id + " prop. ordinal");
               // modalitats de la propietat
                  while (st.hasMoreTokens()) {
                     token = st.nextToken();
                     llistaModal.add(new String(token));
                     logger.finer(token);
                  }
                  llistaModal.trimToSize();
                  pro = new PropOrdinal(id,llistaModal);
               } 
               else {
                  logger.finer("Creada EXCEPCIÓ: Informació de una propietat incorrecta");
                  throw new CreacioPropietatsException("Informació de una propietat incorrecta");
               }
            } 
            else{
            // té rang
               min = Float.parseFloat(token);
               token = st.nextToken();
               max = Float.parseFloat(token);
               token = st.nextToken(); // peso
               token = st.nextToken(); // num. objetos
               token = st.nextToken(); // tipo propiedad
               if (token.compareToIgnoreCase(Constants.PROP_NUMERICA)==0) {
                  logger.finer(id + " prop. numerica");
               // falta comprobar q no hay modalidades
                  pro=new PropNumerica(id,min,max);
               } 
               else {
                  logger.finer("Creada EXCEPCIÓ: Informació de una propietat incorrecta");
                  throw new CreacioPropietatsException("Informació de una propietat incorrecta");
               }
            }
         } 
             catch (NoSuchElementException e){
               logger.finer("Creada EXCEPCIÓ: Falta informació sobre una propietat.");
               throw new CreacioPropietatsException("Falta informació sobre una propietat.");
            }
      // s'afegeix la propietat creada
	// System.out.println( "creo la propiedad y va a entrar a afegirProp");
	
         afegirProp(pro);
      }
   
       void afegirProp(Propietat pro) throws CreacioPropietatsException{
         String id;
       //System.out.println( "entre en afegirProp");

         id = pro.obtenirId();
         if (taulaPos.containsKey(id)) {
            throw new CreacioPropietatsException(
               "Ja existeix una propietat anomenada " + id + ".");
         }
         else {
            llista.add(pro);
            taulaPos.put(id, new Integer(obtenirLong() - 1));
            logger.fine("Afegida la propietat " + pro.obtenirId());
         }
      }
   
       void eliminarDarreraProp() {
         int last = llista.size()-1;
         Propietat prop = (Propietat)llista.get(last);
         String id = prop.obtenirId();
         taulaPos.remove(id);
         llista.remove(last);
      }
   
       void eliminarProp(String nomProp) {
         Integer pos = ((Integer) taulaPos.get(nomProp));
         if (null != pos) {eliminarProp(pos); }
      }
     
       void eliminarProp(int posProp) {
      //Propietat prop = (Propietat)llista.get(posProp);
      //String id = prop.obtenirId();
      //taulaPos.remove(id);
         llista.remove(posProp);
         taulaPos.clear();
         Object[] propAry = llista.toArray();
         for (int i = 0; i < propAry.length; i++) {
            taulaPos.put(((Propietat)propAry[i]).obtenirId(), i);
         }
      
      }
   
       void afegirPropCateg(String id) throws CreacioPropietatsException{
         PropCategorica pro = null;
      
         pro = new PropCategorica(id);
      // s'afegeix la propietat creada
         afegirProp(pro);
      }
		
	
	
	 void afegirPropNum(String id,float min,float max) throws CreacioPropietatsException{
         PropNumerica pro = null;
      
         pro = new PropNumerica(id,min,max);
      // s'afegeix la propietat creada
         afegirProp(pro);
      }
	
		
   
   //DISTANCIES********************************************************************* p pes
   /**
   * Actualitza la propietat <code>i</code>-èssima
   * amb el valor observat indicat
   * @param i posició que ocupa la propietat a la matriu
   * @param valor valor observat per la propietat
   * @param p conté el pes de l'objecte
   * @throws CreacioPropietatsException si es produeix un error al actualitzar la
   * informació estadística associada a la propietat amb el valor indicat
   */
       void nouValor(int i,String valor,float p) throws CreacioPropietatsException{
         Propietat pro;
      
         pro=(Propietat)llista.get(i);
         pro.actualitzar(valor,p);
      }
   
   /**
   * Actualitza la propietat <code>i</code>-èssima
   * amb el valor observat indicat sense realitzar certes actualitzacions
   * @param i posició que ocupa la propietat a la matriu
   * @param valor valor observat per la propietat
   * @throws CreacioPropietatsException  si es produeix un error al actualitzar la
   * informació estadística associada a la propietat amb el valor indicat
   */
       void nouValorLliure(int i,String valor) throws CreacioPropietatsException{
         Propietat pro;
     
         pro=(Propietat)llista.get(i);
         pro.actualitzarLliure(valor);
      }
   
   /**
    * Obté una propietat a partir del seu nom
    * @param <code>sid</code>, nom de la propietat que es vol obtenir
    * @return Propietat que s'identifica amb el nom que ens passen com a paràmetre
    * @author Laia Riera Guerra
    */
       public Propietat obtenirPropietat(String sid)throws Exception{
		// System.out.println( "voy a entrar al obtenirIndex" );	 
         int iIndex=obtenirIndex(sid);
		//System.out.println( "Sali del obtenir index"+sid );	
			
         if(iIndex==-1)throw new Exception("No existe!! la propietat "+sid+" a la matriu de dades");
         else{
            Propietat prop=obtenirPropietat(iIndex);
            return prop;
         }
      
      }
   
   
   
   
   
   /**
   * Retorna la propietat que ocupa la posició <code>index</code> de la llista de propietats
   * @param index posició que ocupa la propietat a la que es vol accedir
   * @return Propietat de la posició indicada
   */
       public Propietat obtenirPropietat(int index){
         Propietat pro;
         ArrayList llistaProps = obtenirLlista();
      
         pro = (Propietat)llistaProps.get(index);
      
         return pro;
      }
   //DISTANCIES*********************************************************************
   /**
   * Obté les llistes de identificadors, noms, dels diferents tipus de propietats que conté
   * @return vector amb les llistes de identificadors de cada tipus de propietats: <br>
   *  llista[0] = propietats numèriques;<br>  llista[1] = propietats categoriques;<br>
   * llista[1] = propietats nominals;<br> llista[3] = propietats ordinals.<br>
   *
   */
       String[][] llistarIDsPropietats(){
         Vector llPropsNum = new Vector(), llPropsCateg = new Vector(), llPropsNom = new Vector(), llPropsOrd = new Vector();
         int i;
         Propietat pro;
         ArrayList llistaProps = obtenirLlista();
      
      //alejandro 
         for (int p = 0; p < llista.size(); p++) {
         //    ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
           // System.out.println( "estoy en llistarIDsPropietats muestro llista"+  llista.get(p));
         }
      
      
         String[][] llIDs = new String[4][];
      
         for (i=0; i<llista.size(); i++){
            pro = (Propietat)llistaProps.get(i);
            if (pro instanceof PropNumerica) {
               llPropsNum.add(pro.obtenirId());
            } 
            else if (pro instanceof PropOrdinal) {
               llPropsOrd.add(pro.obtenirId());
            } 
            else if (pro instanceof PropNominal) {
               llPropsNom.add(pro.obtenirId());
            } 
            else if (pro instanceof PropCategorica) {
               llPropsCateg.add(pro.obtenirId());
            }
         }
         llIDs[0] = new String[llPropsNum.size()];
         llIDs[0] = (String[])llPropsNum.toArray(llIDs[0]);
         llIDs[1] = new String[llPropsCateg.size()];
         llIDs[1] = (String[])llPropsCateg.toArray(llIDs[1]);
         llIDs[2] = new String[llPropsNom.size()];
         llIDs[2] = (String[])llPropsNom.toArray(llIDs[2]);
         llIDs[3] = new String[llPropsOrd.size()];
         llIDs[3] = (String[])llPropsOrd.toArray(llIDs[3]);
      
         return llIDs;
      }
   
   /**
   * Obté les llistes de identificadors, noms, dels tipus bàsics de propietats (numèriques i categòriques) que conté
   * @return vector amb les llistes de identificadors pels tipus bàsics de propietats:<br>
   *  llista[0] = propietats numèriques; <br>llista[1] = propietats categoriques.<br>
   *
   */
       String[][] llistarIDsPropietatsEnBasics(){
         Vector llPropsNum = new Vector(), llPropsCateg = new Vector();
         int i;
         Propietat pro;
         ArrayList llistaProps = obtenirLlista();
         String[][] llIDs = new String[4][];
      
         for (i=0; i<llista.size(); i++){
            pro = (Propietat)llistaProps.get(i);
            if (pro instanceof PropNumerica) {
               llPropsNum.add(pro.obtenirId());
            } 
            else if ((pro instanceof PropOrdinal) || (pro instanceof PropNominal) || (pro instanceof PropCategorica)){
               llPropsCateg.add(pro.obtenirId());
            }
         }
         llIDs[0] = new String[llPropsNum.size()];
         llIDs[0] = (String[])llPropsNum.toArray(llIDs[0]);
         llIDs[1] = new String[llPropsCateg.size()];
         llIDs[1] = (String[])llPropsCateg.toArray(llIDs[1]);
      
         return llIDs;
      }
   
   /////////////
   /**alejandro
   * Obté les llistes de identificadors, noms, dels tipus bàsics de propietats (numèriques i categòriques) que conté
   * @return vector amb les llistes de identificadors pels tipus bàsics de propietats:<br>
   *  llista[0] = propietats numèriques; <br>llista[1] = propietats categoriques.<br>
   *
   */
       String[][] llistarIDsPropietatsEnBasicsA(ArrayList activas){
         Vector llPropsNum = new Vector(), llPropsCateg = new Vector(), llPropsNom = new Vector(), llPropsOrd = new Vector();
         int i;
         Propietat pro=null;
         ArrayList llistaProps = obtenirLlista();
      //pro = (Propietat)llistaProps.get(1);
         String[][] llIDs = new String[4][];
        // System.out.println("hola ALEEEEEEEE estoy en llistarIDsPropietatsBasicsA");
      //alejandro 
      //	 for (int r = 0; r < activas.size(); r++) {
      //    ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
       // System.out.println( "tengo las activas en el arrayList????"+  activas.get(r));
      //	  arractivas[p]=(String)activas.get(p);
       // System.out.println( "tengoactivas en array????"+  arractivas[p]);
      
      //  }
      
      
         String [] arractivas = new String [activas.size()];
      
      //alejandro 
         for (int p = 0; p < activas.size(); p++) {
         //    ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
        //  System.out.println( "ALEEE tengo las activas aca????"+  activas.get(p));
            arractivas[p]=(String)activas.get(p);
         // System.out.println( "tengoactivas en array????"+  arractivas[p]);
         
         }
      
         int j;
         String var;
         for (i=0; i<activas.size(); i++){
         // alejandro comente esto 
         // pro = (Propietat)llistaProps.get(i);	  
            try{
               pro =  obtenirPropietat(arractivas[i]);
            }
                catch (Exception ex) {
                  ex.printStackTrace();
               //Any other exception handling...
               }
         
            String id;
            id = pro.obtenirId();
          // System.out.println( "ALEEEE tengo la propiedaddddddpor favor"+  id);
         
         
            if (pro instanceof PropNumerica) {
               llPropsNum.add(pro.obtenirId());
            
              
            //System.out.println( "imprimo llPropsNum"+ llPropsNum.elementAt(i));
            } 
            else if ((pro instanceof PropOrdinal) || (pro instanceof PropNominal) || (pro instanceof PropCategorica)){
               llPropsCateg.add(pro.obtenirId());
            }
         }
         llIDs[0] = new String[llPropsNum.size()];
         llIDs[0] = (String[])llPropsNum.toArray(llIDs[0]);
         llIDs[1] = new String[llPropsCateg.size()];
         llIDs[1] = (String[])llPropsCateg.toArray(llIDs[1]);
			llIDs[2] = new String[llPropsNom.size()];
   		llIDs[2] = (String[])llPropsNom.toArray(llIDs[2]);
         llIDs[3] = new String[llPropsOrd.size()];
         llIDs[3] = (String[])llPropsOrd.toArray(llIDs[3]);
			
			

      
         return llIDs;
      }
   
   
 //ALE Estados
  /**alejandro
   * Obté les llistes de identificadors, noms, dels tipus bàsics de propietats (numèriques i categòriques) que conté
   * @return vector amb les llistes de identificadors pels tipus bàsics de propietats:<br>
   *  llista[0] = propietats numèriques; <br>llista[1] = propietats categoriques.<br>
   *
   */
       String[][] llistarIDsPropietasEstados(String[] variablest){
         Vector llPropsNum = new Vector(), llPropsCateg = new Vector(), llPropsNom = new Vector(), llPropsOrd = new Vector();
         int i;
         Propietat pro=null;
         ArrayList llistaProps = obtenirLlista();
      //pro = (Propietat)llistaProps.get(1);
         String[][] llIDs = new String[4][];
             
      
        // String [] arractivas = new String [activas.size()];
      
      //alejandro 
      /*   for (int p = 0; p < activas.size(); p++) {
         //    ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
        //  System.out.println( "ALEEE tengo las activas aca????"+  activas.get(p));
            arractivas[p]=(String)activas.get(p);
         // System.out.println( "tengoactivas en array????"+  arractivas[p]);
         
         }*/
      
         int j;
         String var;
         for (i=0; i<variablest.length; i++){
         // alejandro comente esto 
         // pro = (Propietat)llistaProps.get(i);	  
            try{
               pro =  obtenirPropietat(variablest[i]);
            }
                catch (Exception ex) {
                  ex.printStackTrace();
               //Any other exception handling...
               }
         
            String id;
            id = pro.obtenirId();
          // System.out.println( "ALEEEE tengo la propiedaddddddpor favor"+  id);
         
         
            if (pro instanceof PropNumerica) {
               llPropsNum.add(pro.obtenirId());
            
              
            //System.out.println( "imprimo llPropsNum"+ llPropsNum.elementAt(i));
            } 
            else if ((pro instanceof PropOrdinal) || (pro instanceof PropNominal) || (pro instanceof PropCategorica)){
               llPropsCateg.add(pro.obtenirId());
            }
         }
         llIDs[0] = new String[llPropsNum.size()];
         llIDs[0] = (String[])llPropsNum.toArray(llIDs[0]);
         llIDs[1] = new String[llPropsCateg.size()];
         llIDs[1] = (String[])llPropsCateg.toArray(llIDs[1]);
			llIDs[2] = new String[llPropsNom.size()];
   		llIDs[2] = (String[])llPropsNom.toArray(llIDs[2]);
         llIDs[3] = new String[llPropsOrd.size()];
         llIDs[3] = (String[])llPropsOrd.toArray(llIDs[3]);
			
			

      
         return llIDs;
      }
   

 
 
 
 
 //// FIN ALE ESTADOS  
   
   
   
   
   
   
   
   
   
   
   
   
   
   //////////////////  
   
   
   
   
   
   /////////////LAIA//////////////////////////////////////////////////////////////
   /**
    *Retorna un booleà que indica si la propietat identificada per l'string <code>sid</code> és categòrica o no
    *@return <code>true</code>, si la propietat és categòrica
    *		<code>false</code>, si la propietat no és categòrica
    *@author Laia Riera Guerra
    */
       public boolean esPropietatCategorica(String sid)throws Exception{
         Propietat pro=this.obtenirPropietat(sid);
         boolean res=false;
         if ((pro instanceof PropOrdinal) || (pro instanceof PropNominal) || (pro instanceof PropCategorica)){
            res=true;
         }
         return res;
      }
   
   /**
    *Retorna un booleà que indica si la propietat identificada per l'string <code>sid</code> és ordinal o no
    *@return <code>true</code>, si la propietat és ordinal
    *		<code>false</code>, si la propietat no és ordinal
    *@author Laia Riera Guerra
    */
       public boolean esPropietatOrdinal(String sid)throws Exception{
         Propietat pro=this.obtenirPropietat(sid);
         boolean res=false;
         if (pro instanceof PropOrdinal){
            res=true;
         }
         return res;
      }
   
   /**
    *Retorna un booleà que indica si la propietat identificada per l'string <code>sid</code> és numèrica o no
    *@return <code>true</code>, si la propietat és numèrica
    *		<code>false</code>, si la propietat no és numèrica
    *@author Laia Riera Guerra
    */
       public boolean esPropietatNumerica(String sid)throws Exception{
         Propietat pro=this.obtenirPropietat(sid);
         boolean res=false;
         if(pro instanceof PropNumerica)res=true;
         return res;
      }
   /**
    * Obté un vector amb la llista d'identificadors de les propietats de la matriu de dades actual
    * @return String[] amb els identificadors de les propietats
    * @author Laia Riera Guerra
    */
       public String[] llistarIDsPropietatsSenseDistincio(){
         String[] resultat=new String[llista.size()];
         ArrayList llistaProps = obtenirLlista();
         for(int i=0;i<llista.size();i++){
            Propietat prop=(Propietat)llistaProps.get(i);
            resultat[i]=prop.obtenirId();
         }
         return resultat;
      }
		
		
		
   
   
   
   
   
   /////////////
   /**alejandro 07/10
   * Obté les llistes de identificadors, noms, dels tipus bàsics de propietats (numèriques i categòriques) que conté
   * @return vector amb les llistes de identificadors pels tipus bàsics de propietats:<br>
   *  llista[0] = propietats numèriques; <br>llista[1] = propietats categoriques.<br>
   *
   */
       String[]llistarIDsPropietatsSenseDistincioA(ArrayList activas){
         String[] resultat=new String[activas.size()];
      
         int i;
         Propietat pro=null;
         ArrayList llistaProps = obtenirLlista();
      //pro = (Propietat)llistaProps.get(1);
         String[][] llIDs = new String[4][];
        // System.out.println("hola estoy en llistarIDsPropietatsSenseDistincioA");

         String [] arractivas = new String [activas.size()];
      
      //alejandro 
         for (int p = 0; p < activas.size(); p++) {
         //    ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
         // System.out.println( "tengo las activas aca????"+  activas.get(p));
            arractivas[p]=(String)activas.get(p);
         // System.out.println( "tengoactivas en array????"+  arractivas[p]);
         }
      
         int j;
         String var;
         for (i=0; i<activas.size(); i++){
         // alejandro comente esto 
         // pro = (Propietat)llistaProps.get(i);	  
            try{
               pro =  obtenirPropietat(arractivas[i]);
            }
                catch (Exception ex) {
                  ex.printStackTrace();
               //Any other exception handling...
               }
         
            String id;
            id = pro.obtenirId();
         //  System.out.println( "tengo la propiedaddddddpor favor en sense disnticon"+  id);
         
            resultat[i]= pro.obtenirId();
         }    
			
			
		//	 for (int r = 0; r < resultat.length; r++) {
         //    ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
         // System.out.println( "tengo las activas aca????"+  activas.get(p));
         
      //    System.out.println( "tengoactivas en resultat????"+  resultat[r]);
       //  }

			
			
         return resultat;
      }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   /**
    * Obté una propietat a partir del seu nom
    * @param <code>sid</code>, nom de la propietat que es vol obtenir
    * @return Propietat que s'identifica amb el nom que ens passen com a paràmetre
    * @author Laia Riera Guerra
    */
   /*  public Propietat obtenirPropietat(String sid)throws Exception{	 
      int iIndex=obtenirIndex(sid);
      if(iIndex==-1)throw new Exception("No existeix la propietat "+sid+" a la matriu de dades");
      else{
   	   Propietat prop=obtenirPropietat(iIndex);
   	   return prop;
      }
      
   }*/
   /**
    * Retornà un booleà que indica si existeix una propietat a la matriu de dades actual de nom <code>sid</code>
    * @param sid, nom de la propietat que es vol saber si existeix
    * @return true, si la propietat existeix, false altrament
    * @author Laia Riera Guerra
    */
       public boolean existeixPropietat(String sid){
         boolean b=false;
         int iIndex=obtenirIndex(sid);
         if(iIndex!=-1)b=true;
         return b;
      }
		
  public int indiceProp(String sid){
         boolean b=false;
         int iIndex=obtenirIndex(sid);
         //if(iIndex!=-1)b=true;
         return iIndex;
      }
		
		
		
		
   
   /**
    * Elimina l'última propietat de la llista d'identificadors de propietats.
    * @throws CreacioPropietatsException
    * @author Laia Riera Guerra
    */
       void eliminarProp() throws CreacioPropietatsException{
         String id;
         Propietat pro=this.obtenirPropietat(llista.size()-1);
         id = pro.obtenirId();
         if (taulaPos.containsKey(id)) {
            llista.remove(llista.size()-1);
            taulaPos.remove(id);	    
            logger.fine("Eliminada la propietat " + pro.obtenirId());	      
         }
         else {
            throw new CreacioPropietatsException(
                "No existeix una propietat anomenada " + id + ".");
         }
      }
   
   /**
    * Elimina la propietat <code>pro</code> de la llista de propietats
    * @param pro, propietat a eliminar
    * @throws CreacioPropietatsException, si la propietat no existeix
    * @author Laia Riera
    */
       void eliminarPropietat(Propietat pro)throws CreacioPropietatsException{
         String id=pro.obtenirId();
         if (taulaPos.containsKey(id)) {
            boolean b=false;
            Integer pos=(Integer)taulaPos.get(id);
            int index=pos.intValue();
         //movem tots els indexos un enrere
            for(int i=index+1;i<llista.size();i++){
               Propietat prop=(Propietat)llista.get(i);
               taulaPos.put(prop.obtenirId(),new Integer(i-1));
            }
            taulaPos.remove(id);	
            llista.remove(index);
            logger.fine("Eliminada la propietat " + pro.obtenirId());
         }
         else throw new CreacioPropietatsException(
                "No existeix una propietat anomenada " + id + ".");
      }
   /**
    * Substitueix la propietat d'índex = <code>index</code> per la propietat <code>pro</code>
    * @param pro, nova propietat de l'índex <code>index</code>
    * @param index, posició que ocuparà la propietat dins la llista d'índexos i la hastable
    * @author Laia Riera Guerra
    */
       public void substituirProp(Propietat pro,int index){	     
         llista.set(index,pro);
         taulaPos.put(pro.obtenirId(), new Integer(index));
         logger.fine("Afegida la propietat " + pro.obtenirId());	    
      }
   
   
   
   }
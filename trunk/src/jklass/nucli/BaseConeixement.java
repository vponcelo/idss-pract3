package jklass.nucli;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

/**
 * Classe que representa un conjunt de regles que s'avaluen en grup.
 * És una subclasse de la classe LlistaIndexos
 * @author Laia Riera Guerra
 * @version v.4 05/05/2007
 */
public class BaseConeixement extends LlistaIndexos {
	String nomBC;//nom de la BC
	int identificador;//identificador de la classe
	ArrayList alNomsRegles;//conté llistat de noms de regles
	int contador;//contador que permet identificar les regles o expressions booleanes que es van creant
	int[] classificacio;//conté el número de regles k compleix cada fila
	ArrayList propClassi;//cada posició representa una fila
	//llista conte noms dels consegüents
	//conté els noms de les regles que compleix la fila			
	/**
	 * Crea una BaseConeixement amb identificador <code>id</code>, GestorMatriu <code>m</code> i de nom <code>nom</code>
	 * @param id, enter que identifica la nova BaseConeixement
	 * @param m, GestorMatriu al qual queda associada la nova BaseConeixement
	 * @param nom, nom de la Base de coneixement
	 */
	public BaseConeixement(int id, GestorMatriu m,String nom) {
		super(m);
		identificador = id;
		contador = 0;
		propClassi=new ArrayList();	
		nomBC=nom;
		alNomsRegles=new ArrayList();
		classificacio=null;
	}
	/**
	 * Obté el número de regles que conté la BaseConeixement
	 * @return int, enter que representa el número de regles que conté la base de coneixement
	 */
	public int numRegles(){
		return obtenirLong();
	}
	
	/**
	 * Obté l'identificador de la Base de Coneixement
	 * @return int, enter que representa l'identificador de la Base de Coneixement
	 */
	public int getIdentificador() {
		return identificador;
	}
	

	/**
	 * Crea una nova regla que representa l'expressió booleana <code>eb</code>, de conseqüent <code>con</code> i que s'anomenarà <code>snom</code>
	 * @param <code>eb</code>, expressió booleana
	 * @param <code>con</code>, consegüent de la regla
	 * @param <code>snom</code>,nom amb que s'indentificarà la regla
	 * @return <code>Regla</code>, la regla d'expressió booleana <code>eb</code>, conseqüent = <code>con</code> i nom = <code>som</code>
	 */
	public Regla crearRegla(ExpressioBooleana eb, String con,String snom) throws Exception{
		
		if(snom==null){
			snom=this.seguentNomRegla();
			Regla r = new Regla(eb, con, contador, m,snom);
			contador++;	
			return r;
		}
		else if(this.existeixNomRegla(snom))throw new Exception("Ja existeix una regla amb aquest nom");
		else{
			Regla r = new Regla(eb, con, contador, m,snom);
			contador++;	
			return r;
		}					
	}

	/**
	 * Obté la regla que s'identifica pel nom <code>sNom</code>
	 * 
	 * @param sNom,
	 *            nom que identifica la regla que volem obtenir
	 * @return Regla identificada per sNom
	 * @throws Exception,
	 *             si no existeix una regla de nom= <code>sNom</code> o
	 *             l'operació apuntada per aquest nom està buida
	 */
	public Regla obtenirRegla(String sNom) throws Exception {	
		int i=this.obtenirIndexRegla(sNom);			
		if (i==-1)throw new Exception("No existeix la regla especificada");
		else{ 
			Regla reg = (Regla) taulaPos.get(i);			
			return reg;
		}		
	}// endofmethod

	/**
	 * Afegeix una nova regla a la llista de regles, si no existia ja
	 * la regla a la llista
	 * 
	 * @param <code>r</code>,
	 *            regla a afegir
	 * @throws Exception,si
	 *             la regla ja es troba a la llista de regles o existeix una
	 *             regla amb el mateix nom
	 */
	public void afegirRegla(Regla r) throws Exception {
		if(existeixNomRegla(r.getNomRegla()))throw new Exception("Ja existeix una regla amb aquest nom");
		else{
			int i=alNomsRegles.size();
			alNomsRegles.add(r.getNomRegla());
			llista.add(r.getConseguent());
			taulaPos.put(i, r);
		}		
	}// endofmethod

	/**
	 * Elimina de la llista d'identificadors de regles, llista de noms de regles i hashtable la regla de nom <code>sNom</code>
	 * 
	 * @param <code>sNom</code>,nom de la regla que es vol eliminar de la llista de regles
	 */
public void treureRegla(String sNom) {

        int i=this.obtenirIndexRegla(sNom);
        
        alNomsRegles.remove(i);

        llista.remove(i);

        taulaPos.remove(new Integer(i));

//    s'han de moure totes les regles un lloc enrere

        int numRegles=taulaPos.size();

        if(numRegles!=0){//si encara hi ha regles, continuem

            for(int n=i;n<numRegles;n++){

                Regla reg = (Regla)
                taulaPos.get(new Integer(n+1));

                taulaPos.put(new Integer(n), reg);

                taulaPos.remove(new Integer(n+1));
                
            }
        }
    }
	
	

	/**
	 * Verifica si el text que es passa com a paràmetre representa una regla.
	 * En cas afirmatiu, crea la regla corresponent i l'insereix al llistat de regles
	 * @param slinia, String que representa la regla a crear
	 * @return boolean, que ens indica si l'operació s'ha efectuat correctament
	 * @throws Exception, si es detecta alguna incorreció en l'escriptura de la regla o bé ja existeix una regla amb aquell nom
	 */
	public boolean verificarRegla(String slinia) throws Exception {
		if (slinia == null)
			throw new Exception("La regla esta buida");
		else {
			slinia = slinia.trim();
			if (slinia.charAt(0) != '('
					|| slinia.charAt(slinia.length() - 1) != ')')
				throw new Exception("Falten els parèntesis al inici i al final de la regla");
			else {
				slinia = slinia.substring(1, slinia.length() - 1);// eliminem el primer i últim parèntesis
				slinia = slinia.trim();
				int i = slinia.length() - 1;
				while (!(slinia.charAt(i) == '>' && slinia.charAt(i - 1) == '-')
						&& i >= 1)
					i--;// busquem ->
				if (i < 1)//no hem trobat ->
					throw new Exception("La regla no té identificador");
				else {
					String sEtiqueta = slinia.substring(i + 1, slinia.length());
					sEtiqueta = sEtiqueta.trim();
					System.out.println("Etiqueta =" + sEtiqueta);
					slinia = slinia.substring(0, i - 1);
					slinia = slinia.trim();
					ExpressioBooleana op = llegirSubsequencia(slinia);					
					Regla reg = this.crearRegla(op, sEtiqueta,null);
					afegirRegla(reg);// afegim la regla a la llista de regles,si no hi era ja
				}// endif
			}// endif
		}// endif
		return true;
	}

	/**
	 * Llegeix el fitxer de nom <code>sNom</code> i crea una base de coneixement
	 * Cada línia representa una regla de la base de coneixement.
	 * @param <code>sNom</code>,nom del fitxer que es vol llegir 
	 * @throws Exception, 	si no es pot llegir el fitxer correctament
	 * 						si el fitxer comença per una línia en blanc
	 * 						si es detecta algun error d'escriptura de les regles
	 * 						si es repeteixen noms de regles
	 */
	public void importarBaseConeixement(String sNom) throws Exception {
		try {
			System.out.println("ALEEEE Estoy en importar base de coneixement con"+sNom);
			FitxerReg fReg = new FitxerReg(sNom);
			fReg.obrirPerLlegir();
			String slinia = fReg.llegirLinia();
		System.out.println("ALEEEE entro al primer if");	
			if (slinia.length() == 0)
				throw new Exception("La base de coneixement està buida o la primera línia està en blanc");
			else {
				while (slinia != null) {// suposem format correcte,no deixa espais
					// al principi del fitxer ni entre línies.
					// Un cop es llegeix una línia en blanc es para de llegir
					slinia = slinia.trim();
					if (slinia.charAt(0) != '('
							|| slinia.charAt(slinia.length() - 1) != ')')
						throw new Exception("Falten els parèntesis al inici i al final de la regla");
					else {
						slinia = slinia.substring(1, slinia.length() - 1);// eliminem el primer i últim parèntesis
						slinia = slinia.trim();
						int i = slinia.length() - 1;
						while (!(slinia.charAt(i) == '>' && slinia
								.charAt(i - 1) == '-')
								&& i >= 1)
							i--;// busquem ->
						if (i < 1)//no s'ha trobat ->
							throw new Exception("No s'ha trobat el separador d'identificador de la regla ->");
						else {
							String sEtiqueta = slinia.substring(i + 1, slinia
									.length());
							sEtiqueta = sEtiqueta.trim();							
							if(sEtiqueta.length()==0)throw new Exception("La regla no té conseqüent");
							else{
								slinia = slinia.substring(0, i - 1);
								slinia = slinia.trim();
								if(slinia.length()==0)throw new Exception("No existeix una regla");
								else{	System.out.println("ALEEEE entro a llegirSubsequencia");
									ExpressioBooleana op = llegirSubsequencia(slinia);	
										System.out.println("ALEEEE salgo de llegirSubsequencia");
										System.out.println("ALEEEE entro al crear Regla");							
									Regla reg = this.crearRegla(op, sEtiqueta,null);
										System.out.println("ALEEEE sale del crear Regla");
									afegirRegla(reg);// afegim la regla a la llista de regles,si no hi era ja
								}								
							}
							
						}// endif
					}// endif
						System.out.println("ALEEEE entro al llegirLinea");	
					slinia = fReg.llegirLinia();
						System.out.println("ALEEEE salgo del llegirLinea");	
				}// endwhile
			}// endif
			fReg.tancarLec();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}// endofmethod

	/**
	 * Mètode recursiu que construeix una subseqüència d'expressions booleanes
	 * 
	 * @param <code>slinia</code>,String que representa la subseqüència d'expressions booleanes a construir
	 * @return ExpressioBooleana,  que representa l'String que ens han passat com a
	 *         paràmetre
	 * @exception, si es detecta algun error d'escriptura de l'expressió booleana
	 */
	private ExpressioBooleana llegirSubsequencia(String slinia)
			throws Exception {
		ExpressioBooleana op;
		char[] c = slinia.toCharArray();
		if(slinia.length()==0)throw new Exception("La regla està mal formulada");
		if (c[0] == ')')
			throw new Exception("Els parèntesis no es corresponen");// hi ha algun parèntesi tancat més que no pas oberts
		else {
			if (c[0] == '(') {
				int icomp = 1;// conta (=1 i )=-1
				int icont = 1;
				while (icomp != 0 && icont<c.length) {// llegim caràcters fins a trobar el ) que tanca el ( que estem tractant
					if (c[icont] == '(') {
						icomp++;						
					}
					if (c[icont] == ')') {
						icomp--;						
					}
					icont++;
				}// endwhile
				if(icont==c.length && icomp!=0)throw new Exception("Els parèntesis no es corresponen");
				String sSub = slinia.substring(1, icont - 1);
				sSub = sSub.trim();
			
				op = llegirSubsequencia(sSub);
			} else {
				int i = 0;
				while (slinia.charAt(i) != '(' && i < slinia.length()-1)
					i++;
				if (i == slinia.length()-1)
					throw new Exception("La regla no està ben formulada");
				else {
					String sOperand = slinia.substring(0, i);
					sOperand = sOperand.trim();
					slinia = slinia.substring(i, slinia.length());
					slinia = slinia.trim();
					if (esOperadorLogic(sOperand)) {						
						op = construirOperadorLogic(sOperand, slinia);
					} else {
						System.out.println("ALEEEE entro construir operador relacional");					
						op = construirOperadorRelacional(sOperand, slinia);
						System.out.println("ALEEEE salgo del construir operador relacional");
					}
				}// endif
			}// endif
		}// endif
		return op;
	}// endofmethod

	/**
	 * Mètode que ens indica si un String representa un operador lògic o no
	 * 
	 * @param <code>c</code>,String a comprovar si és operador lògic
	 * @return <code>true</code>, si <code>c</code> és un operador lògic, false en cas contrari
	 */
	public boolean esOperadorLogic(String c) {
		if (c.compareToIgnoreCase(ExpressioBooleana.OR) == 0
				|| c.compareToIgnoreCase(ExpressioBooleana.AND) == 0
				|| c.compareToIgnoreCase(ExpressioBooleana.NOT) == 0)
			return true;
		else
			return false;
	}// endofmethod

	/**
	 * Mètode que construeix un operador lògic a partir de l'string que ens
	 * passen com a paràmetre
	 * 
	 * @param slinia,
	 *            String que volem construir
	 * @return ExpressioBooleana, que representa l'string <code>slinia</code>
	 */
	private ExpressioBooleana construirOperadorLogic(String sOperand,
			String slinia) throws Exception {
		char[] c = slinia.toCharArray();
		EBLogica op;
		if (sOperand.compareToIgnoreCase(ExpressioBooleana.OR) == 0){
			op = new OR();			
		}			
		else if (sOperand.compareToIgnoreCase(ExpressioBooleana.AND) == 0)
			op = new AND();
		else if(sOperand.compareToIgnoreCase(ExpressioBooleana.NOT) == 0)
			op = new NOT();
		else throw new Exception("Operand incorrecte");
		String saux=slinia;
		for (int j = 0; j < saux.length(); j++) {
			String sAux = String.valueOf(c[j]);
			//System.out.println(" la c["+j+"] = "+c[j]);
			if (c[j] != '(')
				throw new Exception("Els parèntesis no es corresponen");
			else {
				int icomp = 1;// conta (=1 i )=-1
				int icont = j + 1;
				while (icomp != 0) {
					if (c[icont] == '(') {
						icomp++;						
					}
					if (c[icont] == ')') {
						icomp--;						
					}
					icont++;
				}// endwhile
				String sSub = saux.substring(j + 1, icont - 1);// agafem el substring sense parèntesis
				sSub = sSub.trim();
				ExpressioBooleana opFill = llegirSubsequencia(sSub);// construim l'operació del substring i l'afegim
				// com a fill de l'operació lògica que estem tractant
				if(sOperand.compareTo(ExpressioBooleana.AND) == 0||sOperand.compareTo(ExpressioBooleana.OR) == 0){
					if(op.getNumFills()==2){
						EBLogica aux;
						if(op instanceof AND)aux=new AND();
						else aux=new OR();
						aux.setAlFills(op.getAlFills());
						
						if(sOperand.compareTo(ExpressioBooleana.AND)==0)op=new AND();
						else op=new OR();
						aux.setOpPare(op);
						op.afegeixFill(aux);
						op.afegeixFill(opFill);
						opFill.setOpPare(op);						
					}else{
						op.afegeixFill(opFill);
						opFill.setOpPare(op);
					}
				}else{
					if(op.getNumFills()==1)throw new Exception("L'operador lògic NOT ha de tenir 1 i només 1 fill");
					else{
						op.afegeixFill(opFill);
						opFill.setOpPare(op);
					}
				}				
				saux=saux.substring(icont).trim();
				System.out.println(saux);
				c=saux.toCharArray();
				j=-1;				
			}// endif
		}// endfor
					
		return op;
	}// endofmethod

	/**
	 * Mètode que construeix un operador relacional a partir de l'string que ens
	 * passen com a paràmetre
	 * @param <code>sOperand</code>, String que ens indica de quin operand relacional es tracta
	 * @param <code>slinia</code>,
	 *            String que es vol passar a operador relacional
	 * @return ExpressioBooleana que representa l'string <code>slinia</code>
	 */
	private ExpressioBooleana construirOperadorRelacional(String sOperand,
			String slinia) throws Exception {
		char[] c = slinia.toCharArray();
		boolean esPropietatValor=false;
		EBRelacional op;
		if (c[0] != '(')
			throw new Exception("Els parèntesis no es corresponen");
		else {
			int i = 1;
			while (c[i] != ')' && i < slinia.length())i++;
			if (i == slinia.length())
				throw new Exception("Els parèntesis no es corresponen");
			String sProp = slinia.substring(1, i);
			sProp = sProp.trim();			
			LlistaPropietats llistaProp = m.obtenirLlistaProps();
			Propietat p = llistaProp.obtenirPropietat(sProp);
			String sValor = slinia.substring(i + 1, slinia.length());
			sValor = sValor.trim();			
			char[] auxValor=sValor.toCharArray();
			if(auxValor[0]=='('){
				if(auxValor[auxValor.length-1]!=')')throw new Exception("Els parèntesis no es corresponen");
				else{//el valor és una propietat
					String valorPropietat=sValor.substring(1,sValor.length()-1);
					sValor=valorPropietat.trim();
					Propietat pValor = llistaProp.obtenirPropietat(sValor);
					if(pValor==null)throw new Exception("No existeix la propietat de nom " + sValor);
					esPropietatValor=true;
				}
			}			
			
			if (llistaProp.esPropietatCategorica(sProp) && !esPropietatValor)
				sValor = sValor.substring(1).trim();//li traiem el 'inicial
			if(llistaProp.esPropietatCategorica(sProp) && esPropietatValor){
				if(llistaProp.esPropietatCategorica(sValor)){}
				else throw new Exception("No es pot comprar una variable categòrica("+sProp+") amb una que no ho és ("+sValor+")");
			}
			Object oValor = sValor;
			if (p == null)
				throw new Exception("No existeix la propietat de nom " + sProp);
			else {
				if (llistaProp.esPropietatNumerica(sProp)
						&& (sOperand.compareTo(ExpressioBooleana.PERTANY) != 0)&& !esPropietatValor) {
					oValor = Float.valueOf(sValor);
								
				}
				if(llistaProp.esPropietatNumerica(sProp) && esPropietatValor){
					if(llistaProp.esPropietatNumerica(sValor)){}
					else throw new Exception("No es pot comprar una variable numèrica("+sProp+") amb una que no ho és ("+sValor+")");
				}
				if (llistaProp.esPropietatCategorica(sProp)
						&& !(llistaProp.esPropietatOrdinal(sProp))
						&& ((sOperand.compareTo(ExpressioBooleana.MAJOR) == 0)
								|| (sOperand.compareTo(ExpressioBooleana.MENOR) == 0)
								|| (sOperand
										.compareTo(ExpressioBooleana.MAJORIGUAL) == 0) || (sOperand
								.compareTo(ExpressioBooleana.MENORIGUAL) == 0))) {
					throw new Exception(
							"No es poden aplicar operacions comparatives de (<,>,<=,>=) a variables categòriques no ordinals");
				}
			}

			if (sOperand.compareTo(ExpressioBooleana.MENOR) == 0)
				op = new EsMenor(p, oValor,esPropietatValor);
			else if (sOperand.compareTo(ExpressioBooleana.MAJOR) == 0) {				
				op = new EsMajor(p, oValor,esPropietatValor);				
			} else if (sOperand.compareTo(ExpressioBooleana.IGUAL) == 0)
				op = new EsIgual(p, oValor,esPropietatValor);
			else if (sOperand.compareTo(ExpressioBooleana.MENORIGUAL) == 0)
				op = new EsMenorIgual(p, oValor,esPropietatValor);
			else if (sOperand.compareTo(ExpressioBooleana.MAJORIGUAL) == 0)
				op = new EsMajorIgual(p, oValor,esPropietatValor);
			else if (sOperand.compareTo(ExpressioBooleana.DIFERENT) == 0)
				op = new EsDiferent(p, oValor,esPropietatValor);
			else if (sOperand.compareTo(ExpressioBooleana.PERTANY) == 0) {
				ArrayList al = new ArrayList();				
				char[] cAux = sValor.toCharArray();
				int iaux=0;
				if(cAux[0]=='{')iaux++;
				for (int iPar = iaux; iPar < sValor.length() - 1; iPar++) {// ens saltem el { i }				
					int iFin = iPar;
					while (cAux[iFin] != ',' && cAux[iFin]!=' ' && cAux[iFin] != '}' && iFin<cAux.length-1) {// llegeix els valors
						iFin++;
					}// endwhile
					if(iFin==cAux.length-1 && cAux[iFin]!=',' && cAux[iFin]!=' ' && cAux[iFin]!='}')throw new Exception("Format incorrecte: falten comes o claus");
					else{
						int s=iFin;
						if(cAux[iFin]==' '){
							while(cAux[s]==' ' && s<cAux.length-1)s++;
							if(s<cAux.length-1 && cAux[s]==','){
								iFin=s;
							}
						}
						String sVal = sValor.substring(iPar, iFin).trim();
						if(sVal.compareTo("")==0||sVal==null){}
						else{
							oValor = sVal;						
							if (llistaProp.esPropietatNumerica(sProp))oValor = Float.valueOf((String) oValor);						
							al.add(oValor);		
						}									
						iPar = iFin;
					}
					
				}// endfor
				op = new EsPertany(p, al,esPropietatValor);
			} else {
				throw new Exception("Operand incorrecte " + sOperand);
			}// endif
		}// endif		
		return op;
	}// endofmethod

	/**
	 * Crea un fitxer de nom <code>sNom</code>.reg on hi haurà escrites en notació polaca
	 * totes les regles de la llista <code>alRegles</code>.
	 * 
	 * @param <code>sNom</code>,nom amb que es guardarà el fitxer d'extensió .reg
	 * @param <code>alRegles</code>,noms identificadors de les regles que es volen escriure al
	 *            fitxer
	 * @throws Exception, si no es pot crear el fitxer correctament
	 */
	public void exportarBaseConeixement(String sNom, ArrayList alRegles)
			throws Exception {
		FitxerReg fReg = new FitxerReg(sNom);
		try {				
			
			fReg.modificarNom(sNom+ ".reg");
			fReg.obrirPerEscriure(true);
			for (int i = 0; i < alRegles.size(); i++) {
				Regla reg = obtenirRegla((String) alNomsRegles.get(i));
				fReg.escriureRegla(reg);
			}// endfor
			fReg.tancarEsc();
		} catch (Exception e) {
			
			throw e;
		}// endtry
	}// endofmethod
	
	/**
	 * Obté la posició a la llista <code>llista</code> del conseqüent <code>cons</code>. Si <code> cons</code> no es troba a llista, es retorna -1
	 * @param llista, ArrayList on busquem l'string
	 * @param cons, String del qual volem obtenir la posició
	 * @return posició de l'string <code>cons</code> dins l'ArrayList <code>llista</code>. ( -1 si no hi és )
	 */
	public int consequentJaClassificat(ArrayList llista,String cons){
		int resultat=-1;		
		for(int i=0;i<llista.size() && resultat==-1;i++){
			ArrayList aux=(ArrayList)llista.get(i);//anem recorrent els diferents llistats comparant el primer element
					//que és el consequent
			String sC=(String)aux.get(0);
			if(sC.compareTo(cons)==0)resultat=i;		
		}
		return resultat;
	}
	/**
	 * Fa la unió dels resultats obtinguts d'avaluar un parell de regles sobre la matriu de dades
	 * @param al, ArrayList que conté la columna de resultats obtingut al avaluar una regla <code>a</code>
	 * @param bl, ArrayList que conté la columna de resultats(dades) obtingut al avaluar una regla <code>b</code>
	 * @return, l'ArrayList que conté el resultat de fer la unió de les dos columnes de resultats que ens passen.
	 * On:
	 * 1 U 1 = 1
	 * 1 U 0 = 1
	 * 0 U 0 = 0
	 * 1 U ? = 1
	 * ? U ? = ?
	 * (la unió és commutativa)
	 * 
	 */
	public ArrayList unioRegles(ArrayList al,ArrayList bl){
		ArrayList alUnio=new ArrayList();
		for(int i=0;i<al.size();i++){
			String a=(String)al.get(i);
			String b=(String)bl.get(i);
			if(a.compareTo("?")==0 && b.compareTo("?")==0)alUnio.add(i,"?");
			else if(a.compareTo("?")==0)alUnio.add(i,b);
			else if(b.compareTo("?")==0)alUnio.add(i,a);
			else if(a.compareTo("1")==0 || b.compareTo("1")==0)alUnio.add(i,"1");
			else alUnio.add(i,"0");			
		}
		return alUnio;
	}
	/**
	 * Indica si l'string <code>s</code> es troba al llistat d'strings <code>filai</code>
	 * @param s, string que volem trobar
	 * @param filai, llistat on busquem l'string
	 * @return true si <code>s</code> es troba al llistat, false altrament
	 */
	public boolean trobatEnArray(String s,ArrayList filai){
		boolean b=false;
		if(filai.size()==0)b=false;
		else{
			for(int i=0;i<filai.size() && !b;i++){
				String aux=(String)filai.get(i);
				if(aux.compareTo(s)==0)b=true;
			}
		}
		return b;
	}
	/**
	 * Obté l'string resultant de fer la concatenació dels conseqüents continguts al llistat <code>auxFila</code>
	 * obtenint C1-C2-C3-...on Ci són conseqüents
	 * @param auxFila,llistat de conseqüents que es vol concatenar
	 * @return String amb la concatenació de conseqüents
	 */
	public String concatenarConsequents(ArrayList auxFila){
		String resultat="";	
		for(int i=0;i<auxFila.size();i++){
			String s=(String)auxFila.get(i);
			if(s.compareTo("?")==0){}
			else{
				resultat=resultat+s+"-";
			}
		}
		return resultat.substring(0,resultat.length()-1);
	}
	
	
	/**
	 * Realitza l'avaluació integrada de la base de coneixement actual, creant com a resultat la variable de nom <code>nomFinal</code>
	 * 
	 * @param enriquit, ens indica si el resultat de l'avaluació a de ser enriquit o no
	 * @param nomFinal, nom de la variable resultant
	 * @return la llista de variables creades com a resultat de l'avaluació ( només una )
	 * @throws Exception
	 */
	public ArrayList avaluaIntegrat(boolean enriquit,String nomFinal)throws Exception{
	
		LlistaPropietats LlProp=m.obtenirLlistaProps();
	   		
		
		ArrayList llistaProp=new ArrayList();
		
			
			
		ArrayList llistaModalitats = new ArrayList();
		ArrayList mDades=new ArrayList();		
		ArrayList consequentsAvaluats=new ArrayList();//conté Arraylists, on cada un representa la fila dels resultats d'avaluar 1 consequent
		ArrayList reglesIguals=new ArrayList();//conté ArrayLists de consequents i regles
		ArrayList resultatFiles=new ArrayList();//conté tantes posicions com files.Cada posició conté un array amb els consequents que compleix la fila.
		if(alNomsRegles.size()==0)throw new Exception("Base de coneixement buida");
		else{
			ArrayList consequents=new ArrayList();
			Regla rinicial=obtenirRegla((String)alNomsRegles.get(0));
			consequents.add(0,rinicial.getConseguent());//la primera posicio conte consequent, la resta regles
			consequents.add(1,rinicial);
			reglesIguals.add(consequents);
			for(int i=1;i<alNomsRegles.size();i++){//classifiquem les regles segons el seu consequent
				String nom = (String) alNomsRegles.get(i);//nom d'una regla
				Regla r = obtenirRegla(nom);
				int res=this.consequentJaClassificat(reglesIguals,r.getConseguent());
				if(res!=-1){//ja hi ha regles amb aquest consequent
					ArrayList alaux=(ArrayList)reglesIguals.get(res);
					alaux.add(r);
					reglesIguals.set(res,alaux);
				}else{//no hi ha cap regla amb aquest consequent
					ArrayList alaux=new ArrayList();
					alaux.add(0,r.getConseguent());
					alaux.add(1,r);
					reglesIguals.add(alaux);
				}
			}
			//posem els diferents consequents com a modalitats
			for(int i=0;i<reglesIguals.size();i++){
				ArrayList a=(ArrayList)reglesIguals.get(i);
				llistaModalitats.add(a.get(0));
			}
			for(int j=0;j<reglesIguals.size();j++){//avaluem les regles
				ArrayList alaux=(ArrayList)reglesIguals.get(j);
				ArrayList resultatCons=new ArrayList();
				Regla raux=(Regla)alaux.get(1);
				resultatCons=raux.avalua();
				for(int k=1;k<alaux.size();k++){//regles amb un mateix consequent
					Regla r=(Regla)alaux.get(k);
					ArrayList resultat = r.avalua();
					resultatCons=this.unioRegles(resultatCons,resultat);
				}
				consequentsAvaluats.add(j,resultatCons);//manté correspondència amb reglesIguals				
			}
			Object[][] aux=this.m.obtenirMatriuDades();
			int files=aux.length;
			for(int i=0;i<files;i++){//recorrem files
				ArrayList filai=new ArrayList();//conté consequents que satisfà la fila i
				for(int j=0;j<consequentsAvaluats.size();j++){//recorrem columnes
					ArrayList consequent=(ArrayList)consequentsAvaluats.get(j);
					String s=(String)consequent.get(i);//obtenim el resultat d'avaluar el consequent j per la fila i
					if(s.compareTo("1")==0){
						ArrayList al=(ArrayList)reglesIguals.get(j);
						filai.add(al.get(0));
					}else if(s.compareTo("?")==0){
						if(!trobatEnArray(s,filai))filai.add("?");
					}
				}
				resultatFiles.add(i,filai);
			}
			//construim el convertit
			Dada[] convertit=new Dada[resultatFiles.size()];
			if(enriquit){
				llistaModalitats.add("Residual");
				
				String nomRegla;
				if(nomFinal==null || nomFinal.compareTo("")==0)nomRegla=obtenirNomPropietatContador(this.nomBC,LlProp);		
				else nomRegla=obtenirNomPropietatContador(nomFinal,LlProp);			
				PropCategorica p = new PropCategorica(nomRegla, llistaModalitats);
				for(int i=0;i<resultatFiles.size();i++){
					ArrayList auxFila=(ArrayList)resultatFiles.get(i);
					if(auxFila.size()==0){
						convertit[i]=new Dada("Residual");
						p.actualitzarLliure("Residual");
					}
					else if(auxFila.size()==1){//pot tenir un consequent o ?
						String scon=(String)auxFila.get(0);
						if(scon.compareTo("?")==0){
							convertit[i]=new Dada("?");
							p.actualitzarLliure("?");
						}
						else{
							convertit[i]=new Dada(scon);
							p.actualitzarLliure(scon);
						}
					}
					else{
						String mod=concatenarConsequents(auxFila);						
						p.actualitzarLliure(mod);					
						convertit[i]=new Dada(mod);						
					}
				}
				
							
				
				llistaProp.add(p);
			}else{
				llistaModalitats.add("Residual");
				String nomRegla;
				if(nomFinal==null || nomFinal.compareTo("")==0)nomRegla=obtenirNomPropietatContador(this.nomBC,LlProp);		
				else nomRegla=obtenirNomPropietatContador(nomFinal,LlProp);			
				PropCategorica p = new PropCategorica(nomRegla, llistaModalitats);						
				for(int i=0;i<resultatFiles.size();i++){
					ArrayList auxFila=(ArrayList)resultatFiles.get(i);
					if(auxFila.size()==0){
						convertit[i]=new Dada("Residual");
						p.actualitzarLliure("Residual");
					}
					else if(auxFila.size()==1){//pot tenir un consequent o ?
						String scon=(String)auxFila.get(0);
						if(scon.compareTo("?")==0){
							convertit[i]=new Dada("Residual");
							p.actualitzarLliure("Residual");
						}
						else{
							convertit[i]=new Dada(scon);
							p.actualitzarLliure(scon);
						}
					}
					else{
						convertit[i]=new Dada("Residual");
						p.actualitzarLliure("Residual");
					}
				}
				llistaProp.add(p);
			}					
			mDades.add(convertit);			
			m.afegirColumnes(llistaProp, mDades);	
		}
		//System.out.println(" VOY A ENTRAR AL FOR CHE");	
	//	for(int i=0;i<llistaProp.size();i++){
           // String s=(String)ordreProps.get(i);
			//	System.out.println(" "+llistaProp.llistarIDsPropietats() );
   //    }
			//System.out.println("SALI DEL FOR CHE");	 
		return llistaProp;
	}
	
	/**
	 * Realitza l'avaluació per conseqüents de la base de coneixement actual
	 * @param notaciobinaria, indica si el resultat final ha de ser en notació binària o no
	 * @return llista de variables creades com a resultat de l'avaluació ( contindrà tantes variables com conseqüents diferents hi hagi )
	 * @throws Exception
	 */
	public ArrayList avaluaConsequent(boolean notaciobinaria)throws Exception{
		LlistaPropietats LlProp=m.obtenirLlistaProps();
		ArrayList llistaProp=new ArrayList();
		ArrayList mDades=new ArrayList();		
		ArrayList reglesIguals=new ArrayList();//conté ArrayLists de consequents i regles
		if(alNomsRegles.size()==0)throw new Exception("Base de coneixement buida");
		else{
			ArrayList consequents=new ArrayList();
			Regla rinicial=obtenirRegla((String)alNomsRegles.get(0));
			consequents.add(0,rinicial.getConseguent());//la primera posicio conte consequent, la resta regles
			consequents.add(1,rinicial);
			reglesIguals.add(consequents);
			for(int i=1;i<alNomsRegles.size();i++){//classifiquem les regles segons el seu consequent
				String nom = (String) alNomsRegles.get(i);//nom d'una regla
				Regla r = obtenirRegla(nom);
				int res=this.consequentJaClassificat(reglesIguals,r.getConseguent());
				if(res!=-1){//ja hi ha regles amb aquest consequent
					ArrayList alaux=(ArrayList)reglesIguals.get(res);
					alaux.add(r);
					reglesIguals.set(res,alaux);
				}else{//no hi ha cap regla amb aquest consequent
					ArrayList alaux=new ArrayList();
					alaux.add(0,r.getConseguent());
					alaux.add(1,r);
					reglesIguals.add(alaux);
				}
			}
			for(int j=0;j<reglesIguals.size();j++){//avaluem les regles
				ArrayList alaux=(ArrayList)reglesIguals.get(j);
				ArrayList resultatCons=new ArrayList();
				Regla raux=(Regla)alaux.get(1);
				resultatCons=raux.avalua();
				for(int k=1;k<alaux.size();k++){//regles amb un mateix consequent
					Regla r=(Regla)alaux.get(k);
					ArrayList resultat = r.avalua();
					resultatCons=this.unioRegles(resultatCons,resultat);
				}
				
				ArrayList llistaModalitats = new ArrayList();
				Dada[] convertit=new Dada[resultatCons.size()];
				if(notaciobinaria){
					llistaModalitats.add("0");
					llistaModalitats.add("1");
				
					String nomRegla=obtenirNomPropietatContador((String)alaux.get(0),LlProp);			
					PropCategorica p = new PropCategorica(nomRegla, llistaModalitats);									
					for(int s=0;s<resultatCons.size();s++){
						String saux=(String)resultatCons.get(s);
						if(saux.compareTo("?")==0){
							convertit[s]=new Dada("?");
							p.actualitzarLliure("?");
						}
						else if(saux.compareTo("0")==0){
							convertit[s]=new Dada("0");
							p.actualitzarLliure("0");
						}
						else{
							convertit[s]=new Dada("1");
							p.actualitzarLliure("1");
						}
						System.out.println("El convertit "+s+" = "+convertit[s].obtenirValor());
					}
					llistaProp.add(p);
				}else{
					String reg=(String)alaux.get(0);
					llistaModalitats.add("Fals");
					llistaModalitats.add(reg);
				
					String nomRegla=obtenirNomPropietatContador((String)alaux.get(0),LlProp);			
					PropCategorica p = new PropCategorica(nomRegla, llistaModalitats);
					for(int s=0;s<resultatCons.size();s++){
						String saux=(String)resultatCons.get(s);
						if(saux.compareTo("?")==0){
							convertit[s]=new Dada("?");
							p.actualitzarLliure("?");
						}
						else if(saux.compareTo("0")==0){
							convertit[s]=new Dada("Fals");
							p.actualitzarLliure("Fals");
						}
						else{
							convertit[s]=new Dada(reg);
							p.actualitzarLliure(reg);
						}
						System.out.println("El convertit "+s+" = "+convertit[s].obtenirValor());
					}
					llistaProp.add(p);
				}		
				mDades.add(convertit);				
			}
			m.afegirColumnes(llistaProp, mDades);	
		}
		return llistaProp;		
	}
	
	
	/**
	 * Realitza l'avaluació regla a regla de la base de coneixement actual
	 * @param notaciobinaria, indica si el resultat final ha de ser en notació binària(1,0) o no
	 * @return llista de variables creades com a resultats de l'avaluació ( una per regla )
	 * @throws Exception
	 */
	public ArrayList avaluaReglaARegla(boolean notaciobinaria)throws Exception{
		ArrayList llistaProp=new ArrayList();
		ArrayList mDades=new ArrayList();
		LlistaPropietats LlProp=m.obtenirLlistaProps();
		//ArrayList al=new ArrayList();
		if(alNomsRegles.size()==0)throw new Exception("Base de coneixement buida");
		else{		
		for(int i=0;i<alNomsRegles.size();i++){
			String nom = (String) alNomsRegles.get(i);//nom d'una regla
			Regla r = obtenirRegla(nom);
			ArrayList resultat = r.avalua();//avalua una regla. Retorna els resultats per files. Longitud=num de files
			ArrayList llistaModalitats = new ArrayList();
			Dada[] convertit=new Dada[resultat.size()];
			if(notaciobinaria){
				llistaModalitats.add("0");
				llistaModalitats.add("1");
						
				String nomRegla=obtenirNomPropietatContador(r.getNomRegla(),LlProp);			
				PropCategorica p = new PropCategorica(nomRegla, llistaModalitats);				
				for(int j=0;j<resultat.size();j++){
					String s=(String)resultat.get(j);
					if(s.compareTo("?")==0){
						convertit[j]=new Dada("?");
						p.actualitzarLliure("?");
					}
					else if(s.compareTo("0")==0){
						convertit[j]=new Dada("0");
						p.actualitzarLliure("0");
					}
					else{
						convertit[j]=new Dada("1");
						p.actualitzarLliure("1");
					}
					System.out.println("El convertit "+j+" = "+convertit[j].obtenirValor());
				}
				llistaProp.add(p);
			}else{
				llistaModalitats.add("Fals");
				llistaModalitats.add(r.getConseguent());
				
				String nomRegla=obtenirNomPropietatContador(r.getNomRegla(),LlProp);			
				PropCategorica p = new PropCategorica(nomRegla, llistaModalitats);					
				String reg=r.getConseguent();
				for(int j=0;j<resultat.size();j++){
					String s=(String)resultat.get(j);
					if(s.compareTo("?")==0){
						convertit[j]=new Dada("?");
						p.actualitzarLliure("?");
					}
					else if(s.compareTo("0")==0){
						convertit[j]=new Dada("Fals");
						p.actualitzarLliure("Fals");
					}
					else{
						convertit[j]=new Dada(reg);
						p.actualitzarLliure(reg);
					}
					System.out.println("El convertit "+j+" = "+convertit[j].obtenirValor());
				}
				llistaProp.add(p);
			}			
			mDades.add(convertit);
			//al.add(resultat);
		}
		}
		m.afegirColumnes(llistaProp, mDades);		
		return llistaProp;
	}
	/**
	 * Obté el següent nom de variable, no existent encara al llistat de propietats
	 * format per r-i on <code>r</code> és un string i <code>i</code> és un número
	 * @param r, nom base de la variable.
	 * @param LlProp, llista de variables on es comprova que no existeixi el nom de propietat
	 * @return <code>r</code> si aquest no existeix encara, <code>r-nº</code> altrament
	 */
	public String obtenirNomPropietatContador(String r,LlistaPropietats LlProp){
		int cont=0;
		String nom=r;
		if(LlProp.existeixPropietat(r)){	
			nom=r+"-"+cont;
			while(LlProp.existeixPropietat(nom)){
				cont++;
				nom=r+"-"+cont;
			}			
		}
		return nom;
	}
	/**
	 * Llegeix una expressió lògica en format inordre i crea la regla de nom <code>snom</code>, de
	 * conseqüent <code>sEtiqueta</code> i l'expressió booleana corresponent a <code>sregla</code>
	 * @param sregla, string que representa l'expressió booleana a construir
	 * @param sEtiqueta, conseqüent de la regla
	 * @param snom, nom de la regla
	 * @throws Exception
	 */
	public void llegirReglaNormal(String sregla,String sEtiqueta,String snom)throws Exception{
		ExpressioBooleana aux=new ExpressioBooleana(m);		
		ExpressioBooleana b=aux.llegirBCNormal(sregla);
		
		Regla reg = this.crearRegla(b, sEtiqueta,snom);
		afegirRegla(reg);
	}
	/**
	 * Modifica la regla d'identificador <code>idRegla</code>, posant-li com a nom <code>nom</code>, expressió booleana
	 * l'expressió equivalent a l'string <code>regla</code> i conseqüent <code>consequent</code>
	 * @param idRegla, identificador de la regla
	 * @param nom, nou nom de la regla
	 * @param regla, nova expressió lògica de la regla
	 * @param consequent, nou conseqüent de la regla
	 * @throws Exception, si ja existeix una regla de nom <code>nom</code> i no és correspon amb l'antic nom de la regla <code>idRegla</code>
	 */
	public void modificarRegla(String idRegla,String nom,String regla,String consequent)throws Exception{
		if(existeixNomRegla(nom)&& idRegla.compareTo(nom)!=0)throw new Exception("Ja existeix una regla amb aquest nom");
		else{
			ExpressioBooleana aux=new ExpressioBooleana(m);		
			ExpressioBooleana b=aux.llegirBCNormal(regla);
			Regla reg=this.obtenirRegla(idRegla);
			reg.setConseguent(consequent);
			reg.setNomRegla(nom);
			int i=this.obtenirIndexRegla(idRegla);
			alNomsRegles.set(i,nom);
			reg.setExpressio(b);
		}	
	}
	/**
	 * Obté un string que representa la regla d'identificador <code>id</code> que conté el format següent formatejat a Latex:
	 * nomRegla: (expressió booleana en inordre) -> conseqüent
	 * @param id, identificador de la regla
	 * @return String que representa la regla, formatejat a latex
	 * @throws Exception
	 */
	public String escriureReglaNormalLatex(String id)throws Exception{		
		Regla reg = obtenirRegla(id);		
		return reg.toStringReglaNormalLatex();
	}
	/**
	 * Obté l'string corresponen a escriure l'expressió booleana de la regla d'identificador <code>id</code> en inordre
	 * @param id, identificador de la regla
	 * @return string que representa l'expressió booleana de la regla escrita en inordre
	 * @throws Exception
	 */
	public String escriureReglaNormal(String id)throws Exception{		
		Regla reg = obtenirRegla(id);		
		return reg.toStringReglaNormal();
	}
	
	/*public String llegirOperacio(String soperacio)throws Exception{
		char[] caux = soperacio.toCharArray();
		int s=0;
		while(caux[s]!='(' && s<caux.length-1){s++;}
		if(s==caux.length-1)throw new Exception("La operació no està ben formada");
		else{
			String sOperand=soperacio.substring(0,s).trim();
			soperacio=soperacio.substring(s+1,soperacio.length()).trim();
			caux=soperacio.toCharArray();
			s=0;
			int contador=1;
			while(caux[s]!=')' && contador<0 && s<caux.length-1){
				if(caux[s]=='(')contador++;
				if(caux[s]==')')contador--;
				s++;
			}
		}
		
		return null;
	}*/
	
	/**
	 * Obté el nom de la base de coneixement
	 * @return nom de la base de coneixement
	 */
	public String getNomBC() {
		return nomBC;
	}
	/***
	 * Posa com a nou nom de la base de coneixement l'string <code>nomBC</code>
	 * @param nomBC, nou nom de la base de coneixement
	 */
	public void setNomBC(String nomBC) {
		this.nomBC = nomBC;
	}
	/**
	 * Obté una còpia de la base de coneixement actual
	 * @return BaseConeixement còpia
	 */
	public BaseConeixement copiarBaseConeixement(){
		int id=-1;
		if(this.identificador!=0)id=-this.identificador;
		BaseConeixement bc=new BaseConeixement(id,this.m,this.nomBC);
		bc.setContador(this.contador);
		bc.copiarClassificacio(this.classificacio);
		bc.copiarLlista(this.llista);
		bc.copiarTaulaPos(this.taulaPos);
		bc.copiarPropClassi(this.propClassi);
		bc.copiarAlNomsRegles(this.alNomsRegles);
		ArrayList al=bc.getAlNomsRegles();
		System.out.println("Nombre de regles de la copia = "+al.size());
		return bc;
	}
	/**
	 * Obté el següent nom de regle que no existeixi a la llista de regles de la base de coneixement actual
	 * seguint el format <code>ri</code> on i és un número
	 * @return següent nom per defecte de regla
	 */
	public String seguentNomRegla(){
		int i=alNomsRegles.size();
		String nom="r"+i;
		while(this.existeixNomRegla(nom)){
			i++;
			nom="r"+i;
		}
		return nom;
	}
	/**
	 * Obté el vector <code>classificacio</code>
	 * @return vector <code>classificacio</code>
	 */
	public int[] getClassificacio() {
		return classificacio;
	}
	/**
	 * Posa com a nou vector <code>classificacio</code> el vector classificacio
	 * @param classificacio, nou vector <code>classificacio</code> de la base de coneixement
	 */
	public void setClassificacio(int[] classificacio) {
		this.classificacio = classificacio;
	}
	/**
	 * Obté un contador del nombre de regles de la base de coneixement
	 * @return enter, que indica el nombre de regles que conté la base de coneixement
	 */
	public int getContador() {
		return contador;
	}
	/**
	 * Posa el nou valor del <code>contador</code> el valor contador
	 * @param contador, nou valor del <code>contador</code> de la base de coneixement
	 */
	public void setContador(int contador) {
		this.contador = contador;
	}
	/**
	 * Obté el llistat propClassi
	 * @return llistat propClassi
	 */
	public ArrayList getPropClassi() {
		return propClassi;
	}
	/**
	 * Posa el com a nou llistat <code>propClassi</code> el llistat que ens passen com a paràmetre
	 * @param propClassi, nou llistat <code>propClassi</code> de la base de coneixement
	 */
	public void setPropClassi(ArrayList propClassi) {
		this.propClassi = propClassi;
	}
	/**
	 * Posa el com a nou identificador de la base de coneixement el valor que ens passen com a paràmetre
	 * @param identificador, nou identificador de la base de coneixement
	 */
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	/**
	 * Obté el llista de noms de les regles que componen la base de coneixement
	 * @return llistat dels noms de les regles de la base de coneixement
	 */
	public ArrayList getAlNomsRegles() {
		return alNomsRegles;
	}
	/**
	 * Posa com a nou llistat de noms de regles el paràmetre que ens passen
	 * @param alNomsRegles, nou llistat de noms de regles de la base de coneixement
	 */
	public void setAlNomsRegles(ArrayList alNomsRegles) {
		this.alNomsRegles = alNomsRegles;
	}
	/**
	 * Obtené l'index de la regla de nom= <code>snom</code>
	 */
	public int obtenirIndexRegla(String snom){
		int resultat=-1;
		boolean b=false;
		for(int i=0;i<alNomsRegles.size()&&!b;i++){
			if(snom.compareTo((String)alNomsRegles.get(i))==0){
				b=true;
				resultat=i;
			}
		}
		return resultat;
	}
	/**
	 * Obté el conseqüent de la regla de nom = <code>snom</code>
	 * @param snom, nom de la regla
	 * @return conseqüent de la regla
	 * @throws Exception
	 */
	public String obtenirConsequent(String snom)throws Exception{
		Regla reg = obtenirRegla(snom);
		return reg.getConseguent();
	}
	/**
	 * Copia els valors del vector <code>classifica</code> en el vector <code>classificacio</code>, substituint els que anteriorment contenia
	 * @param classifica, vector que conté els nous valors del vectors <code>classificacio</code>
	 */
	public void copiarClassificacio(int[] classifica){
		this.classificacio=null;
		if(classifica==null)this.classificacio=null;
		else{
			this.classificacio=new int[classifica.length];
			for(int i=0;i<classifica.length;i++){
				this.classificacio[i]=classifica[i];
			}	
		}		
	}
	/**
	 * Substitueix els valors del llistat <code>alNomsRegles</code> pels valors del llistat que ens passen com a paràmetre
	 * @param reg, llistat que conté els nous valors del llistat <code>alNomsRegles</code>
	 */
	public void copiarAlNomsRegles(ArrayList reg){
		this.alNomsRegles=new ArrayList();
		if(reg==null)this.alNomsRegles=null;
		else{
			for(int i=0;i<reg.size();i++){
				this.alNomsRegles.add(i,reg.get(i));
			}
		}		
	}
	/**
	 * Substituex els valors de la <code>llista</code> pels valors del llistat que ens passen com a paràmetre
	 * @param list, llistat que conté els nous valors de <code>llista</code>
	 */
	public void copiarLlista(ArrayList list){
		this.llista=new ArrayList();
		if(list==null)this.llista=null;
		else{
			for(int i=0;i<list.size();i++){
				this.llista.add(i,list.get(i));
			}
		}
	}
	/**
	 * Posa com a nova <code>TaulaPos</code> la hastable que ens passen per paràmetre
	 * @param taula, nova Hastable <code>TaulaPos</code>
	 */
	public void copiarTaulaPos(Hashtable taula){
		this.setTaulaPos(taula);		
	}
	/**
	 * Posa com a nous valors del llistat <code>propClassi</code> els valors del llistat que ens passen com a paràmetre
	 * @param propC, llistat que conté els nous valors de <code>propClassi</code>
	 */
	public void copiarPropClassi(ArrayList propC){
		this.propClassi= new ArrayList();
		if(propC==null)this.propClassi=null;
		else{
			for(int i=0;i<propC.size();i++){
				this.propClassi.add(i,propC.get(i));
			}
		}
	}
	/**
	 * Indica si existeix el nom de regla <code>snom</code> en el llistat de regles de la base de coneixement actual
	 * @param snom
	 * @return
	 */
	public boolean existeixNomRegla(String snom){
		boolean b=false;
		int i=this.obtenirIndexRegla(snom);
		if(i==-1)b=false;
		else b=true;
		return b;
	}
	/**
	 * Associa la base de coneixement actual al GestorMatriu <code>m</code>
	 * @param m, nou GestorMatriu de la base de coneixement actual
	 */
	protected void setM(GestorMatriu m)throws Exception {
		this.m = m;
		for(int i=0;i<alNomsRegles.size();i++){
			Regla reg=obtenirRegla((String)alNomsRegles.get(i));
			reg.setGestor(m);
		}
	  }
	/**
	 * Obté un llistat amb els noms de les regles que contenen propietats que han deixat d'existir a la matriu de dades on estan associades
	 * @param propSelec, 
	 * @return
	 * @throws Exception
	 */
	public ArrayList comprovarPropietatsRegles(String[] propSelec)throws Exception{
		ArrayList resultat=new ArrayList();
		for(int i=0;i<alNomsRegles.size();i++){
			Regla reg=obtenirRegla((String)alNomsRegles.get(i));
			boolean b=reg.comprovarPropietats(propSelec);
			if(b){
				resultat.add(reg.getIdentificador());
			}
		}
		return resultat;
	}
	/**
	 * Method that calculates the confidence of all the rules 
	 * 
	 * @return float array with confidence values for all the rules
	 * @throws Exception
	 * 
	 * @author Esther Lozano
	 */
	public float[] getConfidenceValues()throws Exception
	{
		int n = alNomsRegles.size();
		float[] conf = new float[n];

		if(n == 0)
		{
			throw new Exception("Base de coneixement buida");
		}
		else
		{		
			for(int i = 0; i < n; i++)
			{
				String nom = (String) alNomsRegles.get(i);//nom d'una regla
				Regla r = obtenirRegla(nom);
				conf[i] = r.confidence();
			}
		}

		return conf;
	}
	
	
	/**
	 * Method that calculates the support of all the rules 
	 * 
	 * @return float array with support values for all the rules
	 * @throws Exception
	 * 
	 * @author Esther Lozano
	 */
	public float[] getSupportValues()throws Exception
	{
		int n = alNomsRegles.size();
		float[] supp = new float[n];

		if(n == 0)
		{
			throw new Exception("Base de coneixement buida");
		}
		else
		{		
			for(int i = 0; i < n; i++)
			{
				String nom = (String) alNomsRegles.get(i);//nom d'una regla
				Regla r = obtenirRegla(nom);
				supp[i] = r.support();
			}
		}

		return supp;
	}
	
	
	/**
	 * Method that calculates the relative covering of all the rules 
	 * 
	 * @return float array with relative covering values for all the rules
	 * @throws Exception
	 * 
	 * @author Esther Lozano
	 */
	public float[] getCoveringRValues()throws Exception
	{
		int n = alNomsRegles.size();
		float[] covR = new float[n];

		if(n == 0)
		{
			throw new Exception("Base de coneixement buida");
		}
		else
		{		
			for(int i = 0; i < n; i++)
			{
				String nom = (String) alNomsRegles.get(i);//nom d'una regla
				Regla r = obtenirRegla(nom);
				covR[i] = r.coveringR();
			}
		}

		return covR;
	}
	
	
	/**
	 * Method that calculates the proportion of objects that satisfy any of the rules
	 * of the knowledge base (global covering of the knowledge base)
	 * 
	 * @return float array with global covering value
	 * 
	 * @author Esther Lozano
	 */
	public float coveringG()throws Exception
	{
		int n = 0;
		int sum = 0;
		int numRules = alNomsRegles.size();
		float covG = -1;
		ArrayList llistaAv = new ArrayList();
		ArrayList nomsClass = new ArrayList();
		ArrayList mods = new ArrayList();

		n = this.obtenirGestorMatriu().obtenirLlistaObjs().obtenirLong();
		
		for(int k = 0; k < numRules; k++)
		{
			Regla r = obtenirRegla((String)alNomsRegles.get(k));
			
			llistaAv.add(r.avalua());
			
			String cons = r.getConseguent();
			
			//we cut the name of the class variable from the modality
			int pos = cons.indexOf(")");
			nomsClass.add(cons.substring(1, pos));
			mods.add(cons.substring(pos+1));
		}
		
		//we analyze all the objects
		for(int i = 0; i < n; i++)
		{		
			int j = 0;
			
			boolean found = false;
			
			//we check if the current object satisfies any of the rules
			while(j < numRules & !found)
			{
				String nomClass = (String)nomsClass.get(j);
				String subCons = (String)mods.get(j);
				int prop = this.obtenirGestorMatriu().obtenirLlistaProps().obtenirIndex(nomClass);
				
				Dada dada = this.obtenirGestorMatriu().obtenirDades().obtenirDada(i, prop);
				
				//checking
				if((subCons.compareTo((String)dada.obtenirValor()) == 0) & (((String)((ArrayList)llistaAv.get(j)).get(i)).compareTo("1") == 0))			
				{
					sum++;
					found = true;
				}
				
				j++;
			}
		}
		
		//we have to avoid division by 0
		if(n != 0)
		{
			covG = (float)sum/n;
		}//karina: here we should decide what to do if n=0, before check if it can occur//
		
		return covG;
	}
	
	
	/**
	 * Method to calculate the total support of the knowledge base. It is calculated by adding
	 * the support values of the rules
	 * 
	 * @return the float value for the total support
	 * @throws Exception
	 * 
	 * @author Esther Lozano
	 */
	public float totalSupport()throws Exception
	{
		float suppT = 0;

		if(alNomsRegles.size()==0)
		{
			throw new Exception("Base de coneixement buida");
		}
		else
		{		
			for(int i = 0; i < alNomsRegles.size(); i++)
			{
				String nom = (String) alNomsRegles.get(i);//nom d'una regla
				Regla r = obtenirRegla(nom);
				suppT = suppT + r.support();
			}
		}

		return suppT;
	}
	
	
	/**
	 * Method to calculate the mean confidence of the knowledge base. 
	 * 
	 * @return the float value for the mean confidence
	 * @throws Exception
	 * 
	 * @author Esther Lozano
	 */
	public float meanConfidence()throws Exception
	{
		float mConf = 0;
		int n = alNomsRegles.size();

		if(n == 0)
		{
			throw new Exception("Base de coneixement buida");
		}
		else
		{		
			for(int i = 0; i < n; i++)
			{
				String nom = (String) alNomsRegles.get(i);//nom d'una regla
				Regla r = obtenirRegla(nom);
				mConf = mConf + r.confidence();
			}
		}

		return (mConf/n);
	}
}


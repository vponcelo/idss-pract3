package jklass.nucli;
import java.lang.Math;
import java.util.ArrayList;

/**
 * Classe que representa l'operació aritmètica general
 * 
 * @author Laia Riera Guerra
 * @version v.4 05/05/2007
 */
public class Operacio {
	protected static String ARREL="arrel";
	protected static String LOG="log";
	protected static String LN="ln";
	protected static String EXP="exp";
	protected static String MULT="x";
	protected static String SUMA="+";
	protected static String RESTA="-";
	protected static String DIV="/";
	protected static String POT="^";
	protected static String PI="pi";
	protected static String E="e";
	protected Operacio opPare;//Operació pare de l'operació que estem tractant. Només n'hi ha un i pot ser null en cas de ser l'operació arrel
	protected GestorMatriu gestor;//gestor matriu al que està associada
	
	/**
	 * Constructora per defecte
	 *
	 */
	public Operacio(){
		opPare=null;
	}
	/**
	 * Construeix una nova operació i l'associa al GestorMatriu <code>m</code>
	 * @param m, GestorMatriu
	 */
	public Operacio(GestorMatriu m){
		opPare=null;
		gestor=m;
	}
	
	/**
	   * Retorna el GestorMatriu que està associat a l'operació
	   * @return <code>GestorMatriu</code>
	   */
	  protected GestorMatriu obteGestorMatriu(){
		  if(opPare==null)return gestor;
		  else return opPare.obteGestorMatriu();
	  }
	/**
	 * Obté el símbol de l'operació aritmètica arrel
	 * @return símbol d'arrel
	 */  
	public static String getARREL() {
		return ARREL;
	}
	/**
	 * Posa el nou símbol de l'operació aritmètica arrel
	 * @param arrel, nou símbol d'arrel
	 */
	public static void setARREL(String arrel) {
		ARREL = arrel;
	}
	/**
	 * Obté el símbol de l'operació aritmètica de divisió
	 * @return símbol de divisió
	 */
	public static String getDIV() {
		return DIV;
	}
	/**
	 * Posa el nou símbol de l'operació aritmètica divisió
	 * @param div, nou símbol divisió
	 */
	public static void setDIV(String div) {
		DIV = div;
	}
	/**
	 * Obté el símbol de l'operació aritmètica exponencial
	 * @return símbol d'exponencial
	 */
	public static String getEXP() {
		return EXP;
	}
	/**
	 * Posa el nou símbol de l'operació aritmètica exponencial
	 * @param exp, nou símbol d'exponencial
	 */
	public static void setEXP(String exp) {
		EXP = exp;
	}
	/**
	 * Obté el símbol de l'operació aritmètica logaritme neperià
	 * @return símbol de logaritme neperià
	 */
	public static String getLN() {
		return LN;
	}
	/**
	 * Posa el nou símbol de l'operació aritmètica logaritme neperià
	 * @param ln, nou símbol de logaritme neperià
	 */
	public static void setLN(String ln) {
		LN = ln;
	}
	/**
	 * Obté el símbol de l'operació aritmètica logaritme
	 * @return símbol de logaritme
	 */
	public static String getLOG() {
		return LOG;
	}
	/**
	 * Posa el nou símbol de l'operació aritmètica logaritme
	 * @param log, nou símbol de logaritme
	 */
	public static void setLOG(String log) {
		LOG = log;
	}
	/**
	 * Obté el símbol de l'operació aritmètica multiplicació
	 * @return símbol de multiplicació
	 */
	public static String getMULT() {
		return MULT;
	}
	/**
	 * Posa el nou símbol de l'operació aritmètica multiplicació
	 * @param mult, nou símbol de multiplicació
	 */
	public static void setMULT(String mult) {
		MULT = mult;
	}
	/**
	 * Obté el símbol de l'operació aritmètica potència
	 * @return símbol de potència
	 */
	public static String getPOT() {
		return POT;
	}
	/**
	 * Posa el nou símbol de l'operació aritmètica potència
	 * @param pot, nou símbol de potència
	 */
	public static void setPOT(String pot) {
		POT = pot;
	}
	/**
	 * Obté el símbol de l'operació aritmètica resta
	 * @return símbol de resta
	 */
	public static String getRESTA() {
		return RESTA;
	}
	/**
	 * Posa el nou símbol de l'operació aritmètica resta
	 * @param resta, nou símbol de resta
	 */
	public static void setRESTA(String resta) {
		RESTA = resta;
	}
	/**
	 * Obté el símbol de l'operació aritmètica suma
	 * @return símbol de suma
	 */
	public static String getSUMA() {
		return SUMA;
	}
	/**
	 * Posa el nou símbol de l'operació aritmètica suma
	 * @param suma, nou símbol de suma
	 */
	public static void setSUMA(String suma) {
		SUMA = suma;
	}
	/**
	 * Obté el GestorMatriu a on està associada l'operació
	 * @return GestorMatriu
	 */
	public GestorMatriu getGestor() {
		return gestor;
	}
	/**
	 * Associa l'operació al nou GestorMatriu <code>gestor</code>
	 * @param gestor, nou GestorMatriu
	 */
	public void setGestor(GestorMatriu gestor) {
		this.gestor = gestor;
	}
	/**
	 * Obté l'operació pare de l'operació actual
	 * @return Operacio pare
	 */
	public Operacio getOpPare() {
		return opPare;
	}
	/**
	 * Posa com a nou pare de l'operació actual a l'Operacio <code>opPare</code>
	 * @param opPare, nova operació pare de l'actual
	 */
	public void setOpPare(Operacio opPare) {
		this.opPare = opPare;
	}
	/**
	 * Obté el símbol del nombre pi
	 * @return, símbol del nombre pi
	 */
	public static String getPI() {
		return PI;
	}
	/**
	 * Posa el nou símbol del nombre pi
	 * @param pi, nou símbol del nombre pi
	 */
	public static void setPI(String pi) {
		PI = pi;
	}
	/**
	 * Obté el símbol del nombre e
	 * @return, símbol del nombre e
	 */
	public static String getE() {
		return E;
	}
	/**
	 * Posa el nou símbol del nombre e
	 * @param e, nou símbol del nombre e
	 */
	public static void setE(String e) {
		E = e;
	}
	/**
	 * 
	 * @param sFill, text que representa l'operació que volem construir
	 * @return 
	 * @throws Exception
	 */
	public ArrayList tractarFill(String sFill)throws Exception{
		ArrayList resultat=new ArrayList();
		Object ofill;
		int iFill=1;
		char[] cFill=sFill.toCharArray();
		LlistaPropietats llistaProp = gestor.obtenirLlistaProps();
		if(cFill[0]=='('){//hem de mirar si és una propietat
			String sprop=sFill.substring(1,sFill.length()-1);
			if(llistaProp.obtenirIndex(sprop)==-1){//no és una propietat
				Operacio fill=this.llegirOperacio(sFill);
				ofill=fill;
			}else{//es una propietat
				Propietat pr=llistaProp.obtenirPropietat(sprop);				
				iFill=2;				
				ofill=pr;
			}
		}else{//hem de mirar si és un número	
			if(this.esNumeric(sFill)){
				iFill=3;
				ofill=new Float(sFill);				
			}else{
				if(cFill[0]=='['){//es pi o e
					int i=1;
					while(cFill[i]!=']'&& i<cFill.length-1)i++;
					if(i==cFill.length-1 && cFill[i]!=']')throw new Exception("Claudators incorrectes");
					else{
						String sPIE=sFill.substring(1,i);
						if(i==cFill.length-1){//només hi ha el E o el PI
							iFill=3;
							if(sPIE.compareTo(Operacio.getE())==0)ofill=new Float(Math.E);
							else if(sPIE.compareTo(Operacio.getPI())==0)ofill=new Float(Math.PI);
							else throw new Exception("Nombre incorrecte: "+sPIE);
						}else{//operació binària amb el Pi o E com a fill Esquerra
							String s=sFill.substring(i+1,sFill.length()).trim();//per no agafar claudators
							String soperand=s.substring(0,1);
							String sFillD=s.substring(1,s.length()).trim();
							ArrayList alDret=this.tractarFill(sFillD);
							Integer iDret=(Integer)alDret.get(1);
							Object oEsquerra;
							if(sPIE.compareTo(Operacio.getE())==0)oEsquerra=new Float(Math.E);
							else if(sPIE.compareTo(Operacio.getPI())==0)oEsquerra=new Float(Math.PI);
							else throw new Exception("Nombre incorrecte: "+sPIE);
							if(soperand.compareTo(Operacio.SUMA)==0)ofill=new SUMA(gestor,alDret.get(0),oEsquerra,iDret.intValue(),3);
							else if(soperand.compareTo(Operacio.RESTA)==0)ofill=new RESTA(gestor,alDret.get(0),oEsquerra,iDret.intValue(),3);
							else if(soperand.compareTo(Operacio.MULT)==0)ofill=new MULTIPLICACIO(gestor,alDret.get(0),oEsquerra,iDret.intValue(),3);
							else if(soperand.compareTo(Operacio.DIV)==0)ofill=new DIVISIO(gestor,alDret.get(0),oEsquerra,iDret.intValue(),3);
							else if(soperand.compareTo(Operacio.POT)==0)ofill=new POTENCIA(gestor,alDret.get(0),oEsquerra,iDret.intValue(),3);
							else throw new Exception("Símbol d'operand incorrecte :"+soperand);				
						}
					}
					
				}else{//és una operació
					Operacio fillD=this.llegirOperacio(sFill);
					ofill=fillD;
				}
				
			}			
		}
		resultat.add(ofill);
		resultat.add(new Integer(iFill));
		return resultat;
	}
	/**
	 * Indica si l'string <code>s</code> és un valor numèric o no
	 * @param s, String que es vol tractar
	 * @return true, si l'string <code>s</code> es pot convertir a numèric ( float )
	 */
	public boolean esNumeric(String s){
		boolean bresultat=false;
		try{
			Float f=Float.parseFloat(s);
			bresultat=true;
		}catch(NumberFormatException ex){	
			bresultat=false;
		}
		return bresultat;
	}
	/**
	 * Construeix l'Operacio que representa el text <code>soperacio</code>
	 * @param soperacio, text que conté la representació de l'operació a construir
	 * @return Operacio
	 * @throws Exception
	 */
	public Operacio llegirOperacio(String soperacio)throws Exception{
		Operacio op;
		Object ofillEsquerra;
		Object ofillDret;
		Object ofill;
		int ifill=1;
		int iE=1;//booleans que indiquen si els fills són propietats,Operacions o números
		int iD=1;
		char[] c = soperacio.toCharArray();
		LlistaPropietats llistaProp = gestor.obtenirLlistaProps();
		if(soperacio.length()==0)throw new Exception("Operació buida");
		if(c[0]=='('){
			int icomp = 1;// conta (=1 i )=-1
			int icont = 1;
			while (icomp!= 0 && icont<c.length) {// llegim caràcters fins a trobar el ) que tanca el ( que estem tractant
				if (c[icont] == '(') {
					icomp++;
					System.out.println("icomp=" + icomp + " icont=" + icont);
				}
				if (c[icont] == ')') {
					icomp--;
					System.out.println("icomp=" + icomp + " icont=" + icont);
				}
				icont++;
			}// endwhile
			if(icont==c.length && icomp!=0)throw new Exception("Els parèntesis no es corresponen");
			else if(icont==c.length && icomp==0){//parèntesis al inici i al final
				soperacio=soperacio.substring(1,icont-1);
				op=this.llegirOperacio(soperacio);
			}else{//propietat binaria				
				String sFillE=soperacio.substring(1,icont-1);//no agafem els parèntesis
				String sresta=soperacio.substring(icont,soperacio.length()).trim();
				String soperand=sresta.substring(0,1);
				System.out.println("Operand :"+soperand);
				String sFillD=sresta.substring(1,sresta.length()).trim();
				System.out.println("Fill Dret: "+sFillD);
				if(llistaProp.obtenirIndex(sFillE)==-1){//és una operació el fill esquerre
					System.out.println("fill E operació :"+sFillE);
					Operacio fillE=this.llegirOperacio(sFillE);
					ofillEsquerra=fillE;
				}else{//és una propietat el fill esquerre
					System.out.println("fill E propietat :"+sFillE);
					if(!llistaProp.esPropietatNumerica(sFillE))throw new Exception("La propietat no és numèrica");
					iE=2;
					Propietat p=llistaProp.obtenirPropietat(sFillE);
					ofillEsquerra=p;
				}
				//tractem el fill dret
				ArrayList alFillD=this.tractarFill(sFillD);
				Integer iDret=(Integer)alFillD.get(1);
				if(soperand.compareTo(Operacio.SUMA)==0)op=new SUMA(gestor,alFillD.get(0),ofillEsquerra,iDret.intValue(),iE);
				else if(soperand.compareTo(Operacio.RESTA)==0)op=new RESTA(gestor,alFillD.get(0),ofillEsquerra,iDret.intValue(),iE);
				else if(soperand.compareTo(Operacio.MULT)==0)op=new MULTIPLICACIO(gestor,alFillD.get(0),ofillEsquerra,iDret.intValue(),iE);
				else if(soperand.compareTo(Operacio.DIV)==0)op=new DIVISIO(gestor,alFillD.get(0),ofillEsquerra,iDret.intValue(),iE);
				else if(soperand.compareTo(Operacio.POT)==0)op=new POTENCIA(gestor,alFillD.get(0),ofillEsquerra,iDret.intValue(),iE);
				else throw new Exception("Símbol d'operand incorrecte :"+soperand);				
			}			
		}else{//no comença per parèntesis, comença per número o caràcter
			System.out.println("Operacio unaria");
			String saux=soperacio.substring(0,1);
			if(this.esNumeric(saux)){//és un número
				int i=1;
				while((this.esNumeric(saux)|| c[i-1]=='.'||c[i-1]==','||c[i-1]==' ')&& i<soperacio.length()){
					i++;
					saux=soperacio.substring(i-1,i);					
				}
				if(i==soperacio.length())throw new Exception("No es permeten números sols");					
				else{//operació binària
					String sFillE=soperacio.substring(0,i-1);
					ofillEsquerra=new Float(sFillE);
					iE=3;
					String sResta=soperacio.substring(i-1,soperacio.length()).trim();
					String soperand=sResta.substring(0,1);
					String sFillD=sResta.substring(1,sResta.length()).trim();
					//tractem fill dret
					ArrayList alFillDret=this.tractarFill(sFillD);
					Integer iDret=(Integer)alFillDret.get(1);
					if(soperand.compareTo(Operacio.SUMA)==0)op=new SUMA(gestor,alFillDret.get(0),ofillEsquerra,iDret.intValue(),iE);
					else if(soperand.compareTo(Operacio.RESTA)==0)op=new RESTA(gestor,alFillDret.get(0),ofillEsquerra,iDret.intValue(),iE);
					else if(soperand.compareTo(Operacio.MULT)==0)op=new MULTIPLICACIO(gestor,alFillDret.get(0),ofillEsquerra,iDret.intValue(),iE);
					else if(soperand.compareTo(Operacio.DIV)==0)op=new DIVISIO(gestor,alFillDret.get(0),ofillEsquerra,iDret.intValue(),iE);
					else if(soperand.compareTo(Operacio.POT)==0)op=new POTENCIA(gestor,alFillDret.get(0),ofillEsquerra,iDret.intValue(),iE);
					else throw new Exception("Símbol d'operand incorrecte :"+soperand);				
					
				}
			}else{//és un caràcter-->operació unària		
				System.out.println("Es un caracter");
				if(c[0]=='['){//es pi o e
					int i=1;
					while(c[i]!=']'&& i<c.length-1)i++;
					if(i==c.length-1 && c[i]!=']')throw new Exception("Claudators incorrectes");
					else{
						String sPIE=soperacio.substring(1,i);
						if(i==c.length-1){//només hi ha el E o el PI
							/*iFill=3;
							if(sPIE.compareTo(Operacio.getE())==0)op=new Float(Math.E);
							else if(sPIE.compareTo(Operacio.getPI())==0)op=new Float(Math.PI);*/
							throw new Exception("No es permeten números sols");
						}else{//operació binària amb el Pi o E com a fill Esquerra
							String s=soperacio.substring(i+1,soperacio.length()).trim();//per no agafar claudators
							String soperand=s.substring(0,1);
							String sFillD=s.substring(1,s.length()).trim();
							ArrayList alDret=this.tractarFill(sFillD);
							Integer iDret=(Integer)alDret.get(1);
							Object oEsquerra;
							if(sPIE.compareTo(Operacio.getE())==0)oEsquerra=new Float(Math.E);
							else if(sPIE.compareTo(Operacio.getPI())==0)oEsquerra=new Float(Math.PI);
							else throw new Exception("Nombre incorrecte: "+sPIE);
							if(soperand.compareTo(Operacio.SUMA)==0)op=new SUMA(gestor,alDret.get(0),oEsquerra,iDret.intValue(),3);
							else if(soperand.compareTo(Operacio.RESTA)==0)op=new RESTA(gestor,alDret.get(0),oEsquerra,iDret.intValue(),3);
							else if(soperand.compareTo(Operacio.MULT)==0)op=new MULTIPLICACIO(gestor,alDret.get(0),oEsquerra,iDret.intValue(),3);
							else if(soperand.compareTo(Operacio.DIV)==0)op=new DIVISIO(gestor,alDret.get(0),oEsquerra,iDret.intValue(),3);
							else if(soperand.compareTo(Operacio.POT)==0)op=new POTENCIA(gestor,alDret.get(0),oEsquerra,iDret.intValue(),3);
							else throw new Exception("Símbol d'operand incorrecte :"+soperand);				
						}
					}
					
				}else{
					int i=0;
					while(c[i]!='(' && i<c.length-1)i++;				
					if(i==c.length-1)throw new Exception("Operació incorrecta");
					else{
						String soperand=soperacio.substring(0,i).trim();
						System.out.println("Operand :"+soperand);
						int icomp = 1;// conta (=1 i )=-1
						int icont = i+1;
						while (icomp!= 0 && icont<c.length) {// llegim caràcters fins a trobar el ) que tanca el ( que estem tractant
							if (c[icont] == '(') {
								icomp++;
								System.out.println("icomp=" + icomp + " icont=" + icont);
							}
							if (c[icont] == ')') {
								icomp--;
								System.out.println("icomp=" + icomp + " icont=" + icont);
							}
							icont++;
						}// endwhile
						if(icont==c.length && icomp!=0)throw new Exception("Els parèntesis no es corresponen");
						else{
							String sFill=soperacio.substring(i+1,icont-1).trim();//fill de la operació unària
							System.out.println("Fill de l'operacio unaria: "+sFill);
							//tractem el fill
							ArrayList alFill=this.tractarFill(sFill);
							Integer iFill=(Integer)alFill.get(1);
							Operacio opUnaria;
							if(soperand.compareTo(Operacio.ARREL)==0)opUnaria=new ARREL(gestor,alFill.get(0),iFill.intValue());
							else if(soperand.compareTo(Operacio.EXP)==0)opUnaria=new EXPONENCIAL(gestor,alFill.get(0),iFill.intValue());
							else if(soperand.compareTo(Operacio.LN)==0)opUnaria=new LOGARITME_NEPERIA(gestor,alFill.get(0),iFill.intValue());
							else if(soperand.compareTo(Operacio.LOG)==0)opUnaria=new LOGARITME(gestor,alFill.get(0),iFill.intValue());
							else throw new Exception("Operand incorrecte: "+soperand);
							if(icont==c.length)op=opUnaria;
							else{//té una operació binària que el segueix
								String sResta=soperacio.substring(icont,soperacio.length()).trim();
								String soperand2=sResta.substring(0,1);
								String sFillD=sResta.substring(1,sResta.length());
								//tractem el fill dret de la operació binària
								ArrayList alFillDret=this.tractarFill(sFillD);
								Integer iDret=(Integer)alFillDret.get(1);
								if(soperand2.compareTo(Operacio.SUMA)==0)op=new SUMA(gestor,alFillDret.get(0),opUnaria,iDret.intValue(),1);
								else if(soperand2.compareTo(Operacio.RESTA)==0)op=new RESTA(gestor,alFillDret.get(0),opUnaria,iDret.intValue(),1);
								else if(soperand2.compareTo(Operacio.MULT)==0)op=new MULTIPLICACIO(gestor,alFillDret.get(0),opUnaria,iDret.intValue(),1);
								else if(soperand2.compareTo(Operacio.DIV)==0)op=new DIVISIO(gestor,alFillDret.get(0),opUnaria,iDret.intValue(),1);
								else if(soperand2.compareTo(Operacio.POT)==0)op=new POTENCIA(gestor,alFillDret.get(0),opUnaria,iDret.intValue(),1);
								else throw new Exception("Símbol d'operand incorrecte :"+soperand);				
							}
						}
					}
				}	
			}
		}
		return op;
	}
	/**
	 * Calcula l'operació actual avaluant-la sobre la matriu de dades actual i creant la variable resultant
	 * @param soperacio, text que reprenta l'operació aritmètica a calcular
	 * @param snom, nom de la variable resultant
	 * @throws Exception
	 */
	public void calcular(String soperacio,String snom)throws Exception{
		ArrayList al=new ArrayList();
		Operacio op=this.llegirOperacio(soperacio);
		if(op instanceof OperacioBinaria){
			OperacioBinaria aux=(OperacioBinaria)op;
			al=aux.calcula();
		}else if(op instanceof OperacioUnaria){
			OperacioUnaria aux=(OperacioUnaria)op;
			al=aux.calcula();		
		}else throw new Exception("Operació incorrecta");		
		Dada[] convertit=new Dada[al.size()];
		Float faux=(Float)al.get(0);
		float minim=faux;
		float maxim=faux;
		for(int i=0;i<al.size();i++){
			faux=(Float)al.get(i);
			if(minim>faux.floatValue())minim=faux;
			if(maxim<faux.floatValue())maxim=faux;
			convertit[i]=new Dada(faux);
		}
		Propietat p=new PropNumerica(snom,minim,maxim);
		ArrayList llistaProp=new ArrayList();
		ArrayList mDades=new ArrayList();
		llistaProp.add(p);
		mDades.add(convertit);
		gestor.afegirColumnes(llistaProp, mDades);
		 for(int s=0;s<llistaProp.size();s++){
	        	Propietat paux=(Propietat)llistaProp.get(s);
	        	gestor.calcularEstadistics(paux);
		 }
	}
	/**
	 * Càlcul recursiu de l'operació
	 * @return llistat que conté els valors corresponents a la columna de la variable resultant
	 * @throws Exception
	 */
	public ArrayList calcula()throws Exception{	
		return null;
	}
	

}

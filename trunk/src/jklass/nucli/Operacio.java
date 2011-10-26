package jklass.nucli;
import java.lang.Math;
import java.util.ArrayList;

/**
 * Classe que representa l'operaci� aritm�tica general
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
	protected Operacio opPare;//Operaci� pare de l'operaci� que estem tractant. Nom�s n'hi ha un i pot ser null en cas de ser l'operaci� arrel
	protected GestorMatriu gestor;//gestor matriu al que est� associada
	
	/**
	 * Constructora per defecte
	 *
	 */
	public Operacio(){
		opPare=null;
	}
	/**
	 * Construeix una nova operaci� i l'associa al GestorMatriu <code>m</code>
	 * @param m, GestorMatriu
	 */
	public Operacio(GestorMatriu m){
		opPare=null;
		gestor=m;
	}
	
	/**
	   * Retorna el GestorMatriu que est� associat a l'operaci�
	   * @return <code>GestorMatriu</code>
	   */
	  protected GestorMatriu obteGestorMatriu(){
		  if(opPare==null)return gestor;
		  else return opPare.obteGestorMatriu();
	  }
	/**
	 * Obt� el s�mbol de l'operaci� aritm�tica arrel
	 * @return s�mbol d'arrel
	 */  
	public static String getARREL() {
		return ARREL;
	}
	/**
	 * Posa el nou s�mbol de l'operaci� aritm�tica arrel
	 * @param arrel, nou s�mbol d'arrel
	 */
	public static void setARREL(String arrel) {
		ARREL = arrel;
	}
	/**
	 * Obt� el s�mbol de l'operaci� aritm�tica de divisi�
	 * @return s�mbol de divisi�
	 */
	public static String getDIV() {
		return DIV;
	}
	/**
	 * Posa el nou s�mbol de l'operaci� aritm�tica divisi�
	 * @param div, nou s�mbol divisi�
	 */
	public static void setDIV(String div) {
		DIV = div;
	}
	/**
	 * Obt� el s�mbol de l'operaci� aritm�tica exponencial
	 * @return s�mbol d'exponencial
	 */
	public static String getEXP() {
		return EXP;
	}
	/**
	 * Posa el nou s�mbol de l'operaci� aritm�tica exponencial
	 * @param exp, nou s�mbol d'exponencial
	 */
	public static void setEXP(String exp) {
		EXP = exp;
	}
	/**
	 * Obt� el s�mbol de l'operaci� aritm�tica logaritme neperi�
	 * @return s�mbol de logaritme neperi�
	 */
	public static String getLN() {
		return LN;
	}
	/**
	 * Posa el nou s�mbol de l'operaci� aritm�tica logaritme neperi�
	 * @param ln, nou s�mbol de logaritme neperi�
	 */
	public static void setLN(String ln) {
		LN = ln;
	}
	/**
	 * Obt� el s�mbol de l'operaci� aritm�tica logaritme
	 * @return s�mbol de logaritme
	 */
	public static String getLOG() {
		return LOG;
	}
	/**
	 * Posa el nou s�mbol de l'operaci� aritm�tica logaritme
	 * @param log, nou s�mbol de logaritme
	 */
	public static void setLOG(String log) {
		LOG = log;
	}
	/**
	 * Obt� el s�mbol de l'operaci� aritm�tica multiplicaci�
	 * @return s�mbol de multiplicaci�
	 */
	public static String getMULT() {
		return MULT;
	}
	/**
	 * Posa el nou s�mbol de l'operaci� aritm�tica multiplicaci�
	 * @param mult, nou s�mbol de multiplicaci�
	 */
	public static void setMULT(String mult) {
		MULT = mult;
	}
	/**
	 * Obt� el s�mbol de l'operaci� aritm�tica pot�ncia
	 * @return s�mbol de pot�ncia
	 */
	public static String getPOT() {
		return POT;
	}
	/**
	 * Posa el nou s�mbol de l'operaci� aritm�tica pot�ncia
	 * @param pot, nou s�mbol de pot�ncia
	 */
	public static void setPOT(String pot) {
		POT = pot;
	}
	/**
	 * Obt� el s�mbol de l'operaci� aritm�tica resta
	 * @return s�mbol de resta
	 */
	public static String getRESTA() {
		return RESTA;
	}
	/**
	 * Posa el nou s�mbol de l'operaci� aritm�tica resta
	 * @param resta, nou s�mbol de resta
	 */
	public static void setRESTA(String resta) {
		RESTA = resta;
	}
	/**
	 * Obt� el s�mbol de l'operaci� aritm�tica suma
	 * @return s�mbol de suma
	 */
	public static String getSUMA() {
		return SUMA;
	}
	/**
	 * Posa el nou s�mbol de l'operaci� aritm�tica suma
	 * @param suma, nou s�mbol de suma
	 */
	public static void setSUMA(String suma) {
		SUMA = suma;
	}
	/**
	 * Obt� el GestorMatriu a on est� associada l'operaci�
	 * @return GestorMatriu
	 */
	public GestorMatriu getGestor() {
		return gestor;
	}
	/**
	 * Associa l'operaci� al nou GestorMatriu <code>gestor</code>
	 * @param gestor, nou GestorMatriu
	 */
	public void setGestor(GestorMatriu gestor) {
		this.gestor = gestor;
	}
	/**
	 * Obt� l'operaci� pare de l'operaci� actual
	 * @return Operacio pare
	 */
	public Operacio getOpPare() {
		return opPare;
	}
	/**
	 * Posa com a nou pare de l'operaci� actual a l'Operacio <code>opPare</code>
	 * @param opPare, nova operaci� pare de l'actual
	 */
	public void setOpPare(Operacio opPare) {
		this.opPare = opPare;
	}
	/**
	 * Obt� el s�mbol del nombre pi
	 * @return, s�mbol del nombre pi
	 */
	public static String getPI() {
		return PI;
	}
	/**
	 * Posa el nou s�mbol del nombre pi
	 * @param pi, nou s�mbol del nombre pi
	 */
	public static void setPI(String pi) {
		PI = pi;
	}
	/**
	 * Obt� el s�mbol del nombre e
	 * @return, s�mbol del nombre e
	 */
	public static String getE() {
		return E;
	}
	/**
	 * Posa el nou s�mbol del nombre e
	 * @param e, nou s�mbol del nombre e
	 */
	public static void setE(String e) {
		E = e;
	}
	/**
	 * 
	 * @param sFill, text que representa l'operaci� que volem construir
	 * @return 
	 * @throws Exception
	 */
	public ArrayList tractarFill(String sFill)throws Exception{
		ArrayList resultat=new ArrayList();
		Object ofill;
		int iFill=1;
		char[] cFill=sFill.toCharArray();
		LlistaPropietats llistaProp = gestor.obtenirLlistaProps();
		if(cFill[0]=='('){//hem de mirar si �s una propietat
			String sprop=sFill.substring(1,sFill.length()-1);
			if(llistaProp.obtenirIndex(sprop)==-1){//no �s una propietat
				Operacio fill=this.llegirOperacio(sFill);
				ofill=fill;
			}else{//es una propietat
				Propietat pr=llistaProp.obtenirPropietat(sprop);				
				iFill=2;				
				ofill=pr;
			}
		}else{//hem de mirar si �s un n�mero	
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
						if(i==cFill.length-1){//nom�s hi ha el E o el PI
							iFill=3;
							if(sPIE.compareTo(Operacio.getE())==0)ofill=new Float(Math.E);
							else if(sPIE.compareTo(Operacio.getPI())==0)ofill=new Float(Math.PI);
							else throw new Exception("Nombre incorrecte: "+sPIE);
						}else{//operaci� bin�ria amb el Pi o E com a fill Esquerra
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
							else throw new Exception("S�mbol d'operand incorrecte :"+soperand);				
						}
					}
					
				}else{//�s una operaci�
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
	 * Indica si l'string <code>s</code> �s un valor num�ric o no
	 * @param s, String que es vol tractar
	 * @return true, si l'string <code>s</code> es pot convertir a num�ric ( float )
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
	 * @param soperacio, text que cont� la representaci� de l'operaci� a construir
	 * @return Operacio
	 * @throws Exception
	 */
	public Operacio llegirOperacio(String soperacio)throws Exception{
		Operacio op;
		Object ofillEsquerra;
		Object ofillDret;
		Object ofill;
		int ifill=1;
		int iE=1;//booleans que indiquen si els fills s�n propietats,Operacions o n�meros
		int iD=1;
		char[] c = soperacio.toCharArray();
		LlistaPropietats llistaProp = gestor.obtenirLlistaProps();
		if(soperacio.length()==0)throw new Exception("Operaci� buida");
		if(c[0]=='('){
			int icomp = 1;// conta (=1 i )=-1
			int icont = 1;
			while (icomp!= 0 && icont<c.length) {// llegim car�cters fins a trobar el ) que tanca el ( que estem tractant
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
			if(icont==c.length && icomp!=0)throw new Exception("Els par�ntesis no es corresponen");
			else if(icont==c.length && icomp==0){//par�ntesis al inici i al final
				soperacio=soperacio.substring(1,icont-1);
				op=this.llegirOperacio(soperacio);
			}else{//propietat binaria				
				String sFillE=soperacio.substring(1,icont-1);//no agafem els par�ntesis
				String sresta=soperacio.substring(icont,soperacio.length()).trim();
				String soperand=sresta.substring(0,1);
				System.out.println("Operand :"+soperand);
				String sFillD=sresta.substring(1,sresta.length()).trim();
				System.out.println("Fill Dret: "+sFillD);
				if(llistaProp.obtenirIndex(sFillE)==-1){//�s una operaci� el fill esquerre
					System.out.println("fill E operaci� :"+sFillE);
					Operacio fillE=this.llegirOperacio(sFillE);
					ofillEsquerra=fillE;
				}else{//�s una propietat el fill esquerre
					System.out.println("fill E propietat :"+sFillE);
					if(!llistaProp.esPropietatNumerica(sFillE))throw new Exception("La propietat no �s num�rica");
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
				else throw new Exception("S�mbol d'operand incorrecte :"+soperand);				
			}			
		}else{//no comen�a per par�ntesis, comen�a per n�mero o car�cter
			System.out.println("Operacio unaria");
			String saux=soperacio.substring(0,1);
			if(this.esNumeric(saux)){//�s un n�mero
				int i=1;
				while((this.esNumeric(saux)|| c[i-1]=='.'||c[i-1]==','||c[i-1]==' ')&& i<soperacio.length()){
					i++;
					saux=soperacio.substring(i-1,i);					
				}
				if(i==soperacio.length())throw new Exception("No es permeten n�meros sols");					
				else{//operaci� bin�ria
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
					else throw new Exception("S�mbol d'operand incorrecte :"+soperand);				
					
				}
			}else{//�s un car�cter-->operaci� un�ria		
				System.out.println("Es un caracter");
				if(c[0]=='['){//es pi o e
					int i=1;
					while(c[i]!=']'&& i<c.length-1)i++;
					if(i==c.length-1 && c[i]!=']')throw new Exception("Claudators incorrectes");
					else{
						String sPIE=soperacio.substring(1,i);
						if(i==c.length-1){//nom�s hi ha el E o el PI
							/*iFill=3;
							if(sPIE.compareTo(Operacio.getE())==0)op=new Float(Math.E);
							else if(sPIE.compareTo(Operacio.getPI())==0)op=new Float(Math.PI);*/
							throw new Exception("No es permeten n�meros sols");
						}else{//operaci� bin�ria amb el Pi o E com a fill Esquerra
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
							else throw new Exception("S�mbol d'operand incorrecte :"+soperand);				
						}
					}
					
				}else{
					int i=0;
					while(c[i]!='(' && i<c.length-1)i++;				
					if(i==c.length-1)throw new Exception("Operaci� incorrecta");
					else{
						String soperand=soperacio.substring(0,i).trim();
						System.out.println("Operand :"+soperand);
						int icomp = 1;// conta (=1 i )=-1
						int icont = i+1;
						while (icomp!= 0 && icont<c.length) {// llegim car�cters fins a trobar el ) que tanca el ( que estem tractant
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
						if(icont==c.length && icomp!=0)throw new Exception("Els par�ntesis no es corresponen");
						else{
							String sFill=soperacio.substring(i+1,icont-1).trim();//fill de la operaci� un�ria
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
							else{//t� una operaci� bin�ria que el segueix
								String sResta=soperacio.substring(icont,soperacio.length()).trim();
								String soperand2=sResta.substring(0,1);
								String sFillD=sResta.substring(1,sResta.length());
								//tractem el fill dret de la operaci� bin�ria
								ArrayList alFillDret=this.tractarFill(sFillD);
								Integer iDret=(Integer)alFillDret.get(1);
								if(soperand2.compareTo(Operacio.SUMA)==0)op=new SUMA(gestor,alFillDret.get(0),opUnaria,iDret.intValue(),1);
								else if(soperand2.compareTo(Operacio.RESTA)==0)op=new RESTA(gestor,alFillDret.get(0),opUnaria,iDret.intValue(),1);
								else if(soperand2.compareTo(Operacio.MULT)==0)op=new MULTIPLICACIO(gestor,alFillDret.get(0),opUnaria,iDret.intValue(),1);
								else if(soperand2.compareTo(Operacio.DIV)==0)op=new DIVISIO(gestor,alFillDret.get(0),opUnaria,iDret.intValue(),1);
								else if(soperand2.compareTo(Operacio.POT)==0)op=new POTENCIA(gestor,alFillDret.get(0),opUnaria,iDret.intValue(),1);
								else throw new Exception("S�mbol d'operand incorrecte :"+soperand);				
							}
						}
					}
				}	
			}
		}
		return op;
	}
	/**
	 * Calcula l'operaci� actual avaluant-la sobre la matriu de dades actual i creant la variable resultant
	 * @param soperacio, text que reprenta l'operaci� aritm�tica a calcular
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
		}else throw new Exception("Operaci� incorrecta");		
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
	 * C�lcul recursiu de l'operaci�
	 * @return llistat que cont� els valors corresponents a la columna de la variable resultant
	 * @throws Exception
	 */
	public ArrayList calcula()throws Exception{	
		return null;
	}
	

}

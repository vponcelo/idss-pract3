package jklass.nucli;
import java.util.ArrayList;

/**
 * Classe que representa a una expressi� booleana 
 *@author Laia Riera Guerra
 * @version v.4 05/05/2007
 */
public class ExpressioBooleana {
	protected static String NOT="not";
	protected static String AND="and";
	protected static String OR="or";
	protected static String MENOR="<";
	protected static String MAJOR=">";
	protected static String IGUAL="=";
	protected static String MENORIGUAL="<=";
	protected static String MAJORIGUAL=">=";
	protected static String PERTANY="member";
	protected static String DIFERENT="!=";
	
	
	
	protected ExpressioBooleana opPare;//Operaci� pare de l'operaci� que estem tractant. Nom�s n'hi ha un i pot ser null en cas de ser l'operaci� arrel
	protected GestorMatriu gestor;//gestor matriu al que est� associada
	
	
	/**
	 * Creadora buida d'ExpressioBooleana. 
	 * Inicialitza l'expressi� pare de l'expressi� a null
	 *
	 */
	public ExpressioBooleana(){		
		opPare=null;	
	}//endofmethod	
	/**
	 * Crea una nova expressi� booleana associant-la al GestorMatriu <code>m</code> i inicialitza
	 * l'expressi� booleana pare a null
	 * @param m
	 */
	public ExpressioBooleana(GestorMatriu m){
		gestor=m;
		opPare=null;
	}
	
	/**
	 * Obt� el GestorMatriu al qual est� associada l'expressi� booleana
	 * @return <code>GestorMatriu</code> associat a l'expressi� booleana
	 */
	public GestorMatriu getGestor(){
		return gestor;
	}
	
	/**
	 * Associa l'expressi� booleana al GestorMatriu <code>g</code>
	 * @param <code>g</code>, nou GestorMatriu de l'expressi� booleana
	 */
	public void setGestor(GestorMatriu g){
		gestor=g;
	}
	
		
	/**
	 * Obt� l'operaci� pare d'aquesta operaci�, i null en cas de tractar-se de l'arrel de l'arbre
	 * @return ExpressioBooleana pare de l'operaci� que estem tractant
	 */
	public ExpressioBooleana getPare(){
		return opPare;
	}//endofmethod
	
	
	
	/*public void posaComaPare(ExpressioBooleana op1, ExpressioBooleana op2){
		ExpressioBooleana opPare1=op1.getPare();
		if(opPare1!=null){
			if(!op2.afegeixFill(op1)){//op2 no �s encara pare de op1
				opPare1.eliminaFill(op1);
				if(opPare1.afegeixFill(op2)){//si opPare1 no �s pare de op2
					ExpressioBooleana opPare2=op2.getPare();
					if(opPare2!=null){
						opPare2.eliminaFill(op2);
					}//endif
					op2.setPare(opPare1);
				}//endif			
			}//endif		
		}
		else{
			op2.afegeixFill(op1);
			op2.setPare(null);
			op1.setPare(op2);
		}//endif		
	}//endofmethod*/
	
	/**
	 * Avalua les files de la matriu de dades segons l'expressi� booleana que estem tractant
	 * @return boolean[], vector de booleans, on cada boole� representa el resultat d'avaluar una fila de la matriu per l'expressi� booleana
	 */
	public ArrayList avalua()throws Exception{
		ArrayList al=new ArrayList();
		if(this instanceof AND){
			AND a=(AND)this;
			al=a.avaluaFulla();
		}else if(this instanceof OR){
			OR a=(OR)this;
			al=a.avaluaFulla();
		}else if(this instanceof NOT){
			NOT a=(NOT)this;
			al=a.avaluaFulla();
		}else if(this instanceof EsDiferent){
			EsDiferent a=(EsDiferent)this;
			al=a.avaluaFulla();
		}else if(this instanceof EsIgual){
			EsIgual a=(EsIgual)this;
			al=a.avaluaFulla();
		}else if(this instanceof EsMajor){
			EsMajor a=(EsMajor)this;
			al=a.avaluaFulla();
		}else if(this instanceof EsMajorIgual){
			EsMajorIgual a=(EsMajorIgual)this;
			al=a.avaluaFulla();
		}else if(this instanceof EsMenor){
			EsMenor a=(EsMenor)this;
			al=a.avaluaFulla();
		}else if(this instanceof EsMenorIgual){
			EsMenorIgual a=(EsMenorIgual)this;
			al=a.avaluaFulla();
		}else if(this instanceof EsPertany){
			EsPertany a=(EsPertany)this;
			al=a.avaluaFulla();
		}else throw new Exception("Expressio booleana incorrecta");
		return al;	
	}
	
	 /**
	   * Escriu en una l�nia la regla representada per l'expressi� booleana <code>op</code>
	   * @param <code>op</code>, operaci� arrel de l'arbre l�gic
	   */
	  public String toStringExpressio(){
		  String s="(";
		  String sAux=escriureOperacioRec(this);
		  s=s.concat(sAux);
		  sAux=")";
		  s=s.concat(sAux);
		  return s;
	  }//endofmethod
	  /**
	   * Obt� l'expressi� booleana escrita en inordre
	   * @return String que representa l'expressio booleana escrita en inordre
	   * @throws Exception
	   */
	  public String toStringExpressioNormal()throws Exception{		 
		  String saux=new String();
		  ExpressioBooleana b;		
		  if(this instanceof EsDiferent){
			  b=new EsDiferent();
			  b=this;			 
		  }
		  else if(this instanceof EsIgual){
			  b=new EsIgual();
			  b=this;			 
		  }
		  else if(this instanceof EsMajor){
			  b=new EsMajor();
			  b=this;			 
		  }
		  else if(this instanceof EsMajorIgual){
			  b=new EsMajorIgual();
			  b=this;			  
		  }
		  else if(this instanceof EsMenor){
			  b=new EsMenor();
			  b=this;			  
		  }
		  else if(this instanceof EsMenorIgual){
			  b=new EsMenorIgual();
			  b=this;			  
		  }
		  else if(this instanceof EsPertany){
			  b=new EsPertany();
			  b=this;			  
		  }
		  else if( this instanceof AND){
			  b=new AND();
			  b=this;			  
		  }
		  else if( this instanceof OR){
			  b=new OR();
			  b=this;			  
		  }
		  else if( this instanceof NOT){
			  b=new NOT();
			  b=this;			  
		  }
		  else throw new Exception("Operand no correcte");
		  saux=b.escriureNormal();		 
		  return saux;
	  }
	  /**
	   * Obt� l'expressi� booleana escrita en inordre i formatejada a latex
	   * @return String que representa l'expressio booleana escrita en inordre i formatejada a latex
	   * @throws Exception
	   */
	  public String toStringExpressioNormalLatex()throws Exception{		 
		  String saux=new String();
		  ExpressioBooleana b;		
		  if(this instanceof EsDiferent){
			  b=new EsDiferent();
			  b=this;			 
		  }
		  else if(this instanceof EsIgual){
			  b=new EsIgual();
			  b=this;			 
		  }
		  else if(this instanceof EsMajor){
			  b=new EsMajor();
			  b=this;			 
		  }
		  else if(this instanceof EsMajorIgual){
			  b=new EsMajorIgual();
			  b=this;			  
		  }
		  else if(this instanceof EsMenor){
			  b=new EsMenor();
			  b=this;			  
		  }
		  else if(this instanceof EsMenorIgual){
			  b=new EsMenorIgual();
			  b=this;			  
		  }
		  else if(this instanceof EsPertany){
			  b=new EsPertany();
			  b=this;			  
			  System.out.println("Estem a Pertany");
		  }
		  else if( this instanceof AND){
			  b=new AND();
			  b=this;			  
		  }
		  else if( this instanceof OR){
			  b=new OR();
			  b=this;			  
		  }
		  else if( this instanceof NOT){
			  b=new NOT();
			  b=this;			 
		  }
		  else throw new Exception("Operand no correcte");
		  saux=b.escriureNormalLatex();		 
		  return saux;
	  }
	  /**
	   * Acci� recursiva que obt� l'expressi� booleana escrita en inordre
	   * @return String, expressi� booleana escrita en inordre
	   * @throws Exception
	   */
	  public String escriureNormal()throws Exception{		  
		  return null;
	  }
	  /**
	   * Acci� recursiva que obt� l'expressi� booleana escrita en inordre i formatejada a latex
	   * @return String, expressi� booleana escrita en inordre i formatejada a latex
	   * @throws Exception
	   */
	  public String escriureNormalLatex()throws Exception{		 
		  return null;
	  }
	  
	  /**
	   * Escriu recursivament les operacions
	   * @param <code>op</code>, Expressi� booleana a transformar en String
	   * @return <code>String</code>, representant de l'expressi� booleana <code>op</code>
	   *
	   */
	  protected String escriureOperacioRec(ExpressioBooleana op){
		  String s=null;
		  if(op instanceof EBLogica){
			  EBLogica aux=(EBLogica)op;
			  if(op instanceof AND)s=AND;
			  else if(op instanceof OR)s=OR;
			  else s=NOT;
			  s=aux.escriureFills(s,aux);
		  }
		  else{
			  if(op instanceof EsDiferent)s=DIFERENT;
			  if(op instanceof EsIgual)s=IGUAL;
			  if(op instanceof EsMenor)s=MENOR;
			  if(op instanceof EsMajor)s=MAJOR;
			  if(op instanceof EsMenorIgual)s=MENORIGUAL;
			  if(op instanceof EsMajorIgual)s=MAJORIGUAL;
			  if(op instanceof EsPertany)s=PERTANY;
			  s=escriureOperand(s,(EBRelacional)op);
		  }//endif
		  return s;
	  }//endofmethod
	  
	 	  
	  /**
	   * Converteix l'operacio relacional <code>op</code> a String i la concatena a l'string que ens passen com a par�metre
	   * @param s, String que volem concatenar amb l'string representant de l'operacio relacional
	   * @param op, Operaci� relacional que volem transformar a String
	   * @return String, resultant de la concatenaci� de <code>s</code> i l'string que representa l'operacio relacional <code>op</code>
	   */
	  protected String escriureOperand(String s,EBRelacional op){
		  Propietat prop=op.getVar();
		  Object ovalor=op.getValor();
		  String saux=ovalor.toString();
		  if(op instanceof EsPertany){
			  EsPertany opaux=(EsPertany)op;
			  saux=opaux.escriure_Valor();
		  }
		  if(op.isValorEsPropietat()){
			  String sPar="(";
			  saux=sPar.concat(saux);
			  sPar=")";
			  saux=saux.concat(sPar);
		  }else{
			  if( prop instanceof PropCategorica && !(op instanceof EsPertany) ){				  
				  String saux2="'";
				  saux=saux2.concat(saux);
			  }
		  }		  
		  s=s+"("+prop.obtenirId()+")"+saux;
		  return s;
	  }//endofmethod

	  /**
	   * Retorna el GestorMatriu que est� associat a l'expressi� booleana
	   * @return <code>GestorMatriu</code>
	   */
	  protected GestorMatriu obteGestorMatriu(){
		  if(opPare==null)return gestor;
		  else return opPare.obteGestorMatriu();
	  }

	/**
	 * Retorna l'string que representa l'operador l�gic AND
	 * @return String, representant de l'AND
	 */  
	public static String getAND() {
		return AND;
	}

	/**
	 * Posa com a nou representant de l'AND l'string <code>and</code>
	 * @param <code>and</code>, String representaci� de l'AND
	 */
	public static void setAND(String and) {
		AND = and;
	}
	
	/**
	 * Retorna l'string que representa l'operador relacional diferent
	 * @return String, representant del diferent
	 */
	public static String getDIFERENT() {
		return DIFERENT;
	}
	/**
	 * Posa com a nou representant del diferent l'string <code>diferent</code>
	 * @param <code>diferent</code>, String representaci� del diferent
	 */
	public static void setDIFERENT(String diferent) {
		DIFERENT = diferent;
	}
	
	/**
	 * Retorna l'string que representa l'operador relacional igual
	 * @return String, representant de l'igual
	 */
	public static String getIGUAL() {
		return IGUAL;
	}
	/**
	 * Posa com a nou representant de l'igual l'string <code>igual</code>
	 * @param <code>igual</code>, String representaci� de l'igual
	 */
	public static void setIGUAL(String igual) {
		IGUAL = igual;
	}
	/**
	 * Retorna l'string que representa l'operador relacional major
	 * @return String, representant del major
	 */
	public static String getMAJOR() {
		return MAJOR;
	}
	/**
	 * Posa com a nou representant de major l'string <code>major</code>
	 * @param <code>major</code>, String representaci� del major
	 */
	public static void setMAJOR(String major) {
		MAJOR = major;
	}
	/**
	 * Retorna l'string que representa l'operador relacional major igual
	 * @return String, representant del major igual
	 */
	public static String getMAJORIGUAL() {
		return MAJORIGUAL;
	}
	/**
	 * Posa com a nou representant del major igual l'string <code>majorigual</code>
	 * @param <code>majorigual</code>, String representaci� del major igual
	 */
	public static void setMAJORIGUAL(String majorigual) {
		MAJORIGUAL = majorigual;
	}
	/**
	 * Retorna l'string que representa l'operador relacional menor
	 * @return String, representant del menor
	 */
	public static String getMENOR() {
		return MENOR;
	}
	/**
	 * Posa com a nou representant del menor l'string <code>menor</code>
	 * @param <code>menor</code>, String representaci� del menor
	 */
	public static void setMENOR(String menor) {
		MENOR = menor;
	}
	/**
	 * Retorna l'string que representa l'operador relacional menor igual
	 * @return String, representant del menor igual
	 */
	public static String getMENORIGUAL() {
		return MENORIGUAL;
	}
	/**
	 * Posa com a nou representant del menor igual l'string <code>menorigual</code>
	 * @param <code>menorigual</code>, String representaci� del menor igual
	 */
	public static void setMENORIGUAL(String menorigual) {
		MENORIGUAL = menorigual;
	}
	/**
	 * Retorna l'string que representa l'operador l�gic NOT
	 * @return String, representant del NOT
	 */
	public static String getNOT() {
		return NOT;
	}
	/**
	 * Posa com a nou representant del NOT l'string <code>not</code>
	 * @param <code>not</code>, String representaci� del NOT
	 */
	public static void setNOT(String not) {
		NOT = not;
	}
	/**
	 * Retorna l'string que representa l'operador l�gic OR
	 * @return String, representant de l'OR
	 */
	public static String getOR() {
		return OR;
	}
	/**
	 * Posa com a nou representant de l'OR l'string <code>or</code>
	 * @param <code>or</code>, String representaci� de l'OR
	 */
	public static void setOR(String or) {
		OR = or;
	}
	/**
	 * Retorna l'string que representa l'operador relacional pertany
	 * @return String, representant del pertany
	 */
	public static String getPERTANY() {
		return PERTANY;
	}
	/**
	 * Posa com a nou representant del pertany l'string <code>pertany</code>
	 * @param <code>pertany</code>, String representaci� del pertany
	 */
	public static void setPERTANY(String pertany) {
		PERTANY = pertany;
	}
	
	/**
	 * Obt� el l'operaci� pare de l'expressi� booleana en curs
	 * @return ExpressioBooleana pare de l'operaci� actual
	 */
	public ExpressioBooleana getOpPare() {
		return opPare;
	}
	/**
	 * Posa l'expressi� booleana <code>opPare</code> com a nou pare de l'operaci� en curs
	 * @param <code>opPare</code>, Operaci� pare de l'expressi� booleana en curs
	 */
	public void setOpPare(ExpressioBooleana opPare) {
		this.opPare = opPare;
	}
	/**
	 * Llegeix l'string <code>sregla</code> que representa una expressi� booleana escrita en inordre i construeix l'ExpressioBooleana corresponent
	 * @param sregla, text que representa una expressi� booleana escrita en inordre
	 * @return ExpressioBooleana resultant
	 * @throws Exception
	 */
	public ExpressioBooleana llegirBCNormal(String sregla)throws Exception{
		System.out.println("La regla = "+sregla);
		ExpressioBooleana op;
		char[] c = sregla.toCharArray();
		if(sregla.length()==0)throw new Exception("Regla buida");		
		boolean esPropietat=false;
		 LlistaPropietats llistaProp = gestor.obtenirLlistaProps();
		if(c[0]=='('){//Nom�s el NOT pot NO comen�ar per (			
				int icomp = 1;// conta (=1 i )=-1
				int icont = 1;
				while (icomp != 0 && icont<c.length) {// llegim car�cters fins a trobar el ) que tanca el ( que estem tractant
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
					sregla=sregla.substring(1,icont-1);
					op=this.llegirBCNormal(sregla);
				}
				else{
					String sFillE = sregla.substring(1, icont - 1);//part esquerra de l'operand
					sFillE = sFillE.trim();			
					if(icont==c.length-1)op=llegirBCNormal(sFillE);//par�ntesis envoltant tota la regla
					else{
						int j=icont;
						while(c[j]!='(' && j<c.length-1)j++;
						if(j==sregla.length()-1){//no hem trobat (,llavors o regla mal formulada o regla at�mica amb segon element que no �s propietat
							if(c[j]=='(')throw new Exception("Els par�ntesis no es corresponen");
							else{
								String sFillD=sregla.substring(icont,sregla.length()).trim();//cont� operand i valor
								char[] cAuxiliar=sFillD.toCharArray();
								int x=0;
								while(cAuxiliar[x]!=' ' && x<cAuxiliar.length-1)x++;
								if(x==cAuxiliar.length-1)throw new Exception("Regla mal formulada o no hi ha espai entre operand relacional i el valor de comparaci�");
								else{
									String sOperand=sFillD.substring(0,x);
									String sValor=sFillD.substring(x).trim();
									
									 if(sOperand.compareTo(ExpressioBooleana.DIFERENT)==0)op=new EsDiferent();
								     else if(sOperand.compareTo(ExpressioBooleana.MENOR)==0)op=new EsMenor();
								     else if(sOperand.compareTo(ExpressioBooleana.MENORIGUAL)==0)op=new EsMenorIgual();	 
								     else if(sOperand.compareTo(ExpressioBooleana.MAJOR)==0)op=new EsMajor();
								     else if(sOperand.compareTo(ExpressioBooleana.MAJORIGUAL)==0)op=new EsMajorIgual();
								     else if(sOperand.compareTo(ExpressioBooleana.IGUAL)==0)op=new EsIgual();
								     else if(sOperand.compareTo(ExpressioBooleana.PERTANY)==0)op=new EsPertany();
								     else throw new Exception("Operand incorrecte "+sOperand);
									    op=op.llegirBCNormalRelacional(sOperand,sFillE,sValor,llistaProp,esPropietat);
									     if(op==null)throw new Exception("Operand incorrecte " + sOperand);
								}
							}							
						}
						else{//Un AND,OR o regla at�mica amb valor que �s una propietat
							String sOperand=sregla.substring(icont,j).trim();	
							String sFillD=sregla.substring(j,sregla.length());//incloem el dos parentesis
							sFillD=sFillD.trim();
							if (sOperand.compareTo(ExpressioBooleana.OR) == 0){
								op=new OR(gestor);
								op=op.llegirBCNormalLogica(sOperand,sFillE,sFillD);
							}else if (sOperand.compareTo(ExpressioBooleana.AND) == 0){
								op=new AND(gestor);
								op=op.llegirBCNormalLogica(sOperand,sFillE,sFillD);
							}
							else{//es operador relacional de valor una propietat
								//tractar sFillD								
								char[] cauxFillD=sFillD.toCharArray();
								if(cauxFillD[cauxFillD.length-1]!=')')throw new Exception("Par�ntesis no s�n correctes");
								else{
										esPropietat=true;
										sFillD=sFillD.substring(1,sFillD.length()-1);//propietat de valor sense parentesis
										System.out.println("El valor de propietat = "+sFillD);
										 if(sOperand.compareTo(ExpressioBooleana.DIFERENT)==0)op=new EsDiferent();
									     else if(sOperand.compareTo(ExpressioBooleana.MENOR)==0)op=new EsMenor();
									     else if(sOperand.compareTo(ExpressioBooleana.MENORIGUAL)==0)op=new EsMenorIgual();	 
									     else if(sOperand.compareTo(ExpressioBooleana.MAJOR)==0)op=new EsMajor();
									     else if(sOperand.compareTo(ExpressioBooleana.MAJORIGUAL)==0)op=new EsMajorIgual();
									     else if(sOperand.compareTo(ExpressioBooleana.IGUAL)==0)op=new EsIgual();
									     else if(sOperand.compareTo(ExpressioBooleana.PERTANY)==0)op=new EsPertany();
									     else throw new Exception("Operand incorrecte "+sOperand);
									    op=op.llegirBCNormalRelacional(sOperand,sFillE,sFillD,llistaProp,esPropietat);
									     if(op==null)throw new Exception("Operand incorrecte " + sOperand);										
								}								
							}
						}
					}
				}
		}		
		else{//Un NOT 
			int j=0;
			while(c[j]!='(' && j<sregla.length()-1)j++;
			if(j==sregla.length()-1)throw new Exception("Par�ntesis incorrectes");
			else if(c[c.length-1]!=')')throw new Exception("Par�ntesis incorrectes");
			else{
				String sOperand=sregla.substring(0,j).trim();
				if(sOperand.compareTo(ExpressioBooleana.NOT) != 0)throw new Exception("L'operand l�gic �s incorrecte");
				else{
					String sFill=sregla.substring(j+1,sregla.length()-1).trim();//traiem els par�ntesis que envolten
					op=new NOT(gestor);
					op=op.llegirBCNormalLogica(sOperand,sFill,null);
					if(op==null)throw new Exception("Operand incorrecte "+sOperand);
				}
					
			}
		}		
		return op;
	}
	/**
	 * Construeix l'ExpressioLogica que t� <code>sOperand</code> com a Operand i <code>sfillE,sfillD</code> com a fill Esquerra i Dret respectivament	
	 * @param sOperand, string que reprenta l'operand de l'expressi� l�gica
	 * @param sfillE, fill esquerra(expressi� booleana de l'esquerra) de l'AND
	 * @param sfillD, fill dret(expressi� booleana de la dreta) de l'AND
	 * @return ExpressioBooleana resultant
	 */
	public ExpressioBooleana llegirBCNormalLogica(String sOperand,String sfillE,String sfillD)throws Exception{
		return null;
	}
	 /***
	  * Crea l'expressi� relacional amb operand = <code>sOperand</code>, ovar= propietat de nom <code>sProp</code>, ovalor = <code>sValor</code> i valorEsPropietat= <code>esPropietat</code> 
	  * @param sOperand, operand de l'expressi� relacional 
	  * @param sProp, propietat de l'expressi� relacional
	  * @param sValor, valor comparatiu de l'expressi� relacional
	  * @param llistaProp, llista de propietats de la matriu actual
	  * @param esPropietat, indica si <code>sValor</code> �s una propietat o no
	  * @return ExpressioBooleana resultant 
	  */
	public ExpressioBooleana llegirBCNormalRelacional(String sOperand,String sProp,String sValor, LlistaPropietats llistaProp,boolean esPropietat)throws Exception{
		return null;
	}
	/**
	 * Comprova si les propietats que intervenen a l'expressi� booleana pertanyen a la matriu de dades actual o no
	 * @param propSelec, llistat de propietats de la matriu actual
	 * @return true, si l'expressi� booleana cont� propietats que han deixat d'existir a la matriu actual, false altrament
	 * @throws Exception
	 */
	public boolean comprovarPropietats(String[] propSelec)throws Exception{
		return false;
	}
	
}

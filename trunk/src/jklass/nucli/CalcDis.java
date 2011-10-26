package jklass.nucli;

import jklass.util.OpcionsDis;
import jklass.nucli.Dada;
//DISTANCIES*********************************************************************
 /** Classe que conté els calculs per les diferents distancies
 *
 * @author Jose I Mateos
 * @version v.0 20/12/06
 */

public class CalcDis {


/**
* Constructor
*/
	public CalcDis(){
	}

/**
* Calcul de la distancia euclidiana no normalitzada
*
* @param v1 es el vector amb les dades corresponents a l'objecte 1
* @param v2 es el vector amb les dades corresponents a l'objecte 2
* @param quad indica si el calcula al quadrat o no
* @param p1 conte el valor del pes de l'objecte1
* @param p2 conte el valor del pes de l'objecte2
* @return la distància euclidiana entre v1 i v2,al quadrat si quad=cert
*/
	public String eucliNoNor(Dada[] v1,Dada[] v2,boolean quad, float p1,float p2){

		float sum=0;

		for (int i = 0; i < v1.length; i++) {
			sum=sum + (float)(Math.pow(((p1*v1[i].numero())-(p2*v2[i].numero())),2));
		}
		if(!quad){
			sum=(float)Math.sqrt(sum);
		}
		return String.valueOf(sum);
	}
/**
* Calcul de la distancia euclidiana normalitzada
*
* @param v1 es el vector amb les dades corresponents a l'objecte 1
* @param v2 es el vector amb les dades corresponents a l'objecte 2
* @param nor es un vector amb les diferents desviacion tipiques o rangs de les propietats depenent
* de si es vol normalitzar pel rang o per la desviacio tipica
* @param quad indica si el calcula al quadrat o no
* @param p1 conte el valor del pes de l'objecte1
* @param p2 conte el valor del pes de l'objecte2
* @return la distància euclidiana entre v1 i v2 normalitzada segons el parametre nor i al quadrat si quad=cert
*/
	public String eucliNor(Dada[] v1,Dada[] v2,float[] nor, boolean quad, float p1,float p2){

		float sum=0;

		for (int i = 0; i < v1.length; i++) {
			if(nor[i]!=0){
				sum=sum + (float)(Math.pow((((p1*v1[i].numero())-(p2*v2[i].numero()))/nor[i]),2));
			}
		}
		if(!quad){
			sum=(float)Math.sqrt(sum);
		}
		return String.valueOf(sum);
	}
/**
* Calcul de la distancia gower
*
* @param v1n te les components numèriques del vector corresponent a l'objecte 1
* @param v2n te les components numèriques del vector corresponent a l'objecte 2
* @param v1c te les components categòriques del vector corresponent a l'objecte 1
* @param v2c te les components categòriques del vector corresponent a l'objecte 2
* @param rang es un vector amb els diferents rangs de les propietats numèriques
* @param p1 conte el valor del pes de l'objecte1
* @param p2 conte el valor del pes de l'objecte2
* @return la distància de Gower entre l'objecte 1 i l'objecte 2 considerant les components numèriques i categòriques
*/
	public String gower(Dada[] v1n,Dada[] v2n,Dada[] v1c,Dada[] v2c,float[] rang,float p1,float p2){

		float num=0;
		float cat=0;
		float min=0;
		float max=0;
		float sum=0;
		String num1=null;
		String x1=null;
		String num2=null;
		String x2=null;
		DadaExtesa ve1;
		DadaExtesa ve2;
		boolean trobat;
		int w=0;

		for (int i = 0; i < v1n.length; i++) {
			if(!v1n[i].esMissing() && !v2n[i].esMissing()){
				w++;
				if(rang[i]!=0){
					num=num + (1 - ((float)(Math.abs(v1n[i].numero()-v2n[i].numero()))/rang[i]));
				}
			}
		}
		for (int i = 0; i < v1c.length; i++) {
			w++;
			if (!v1c[i].compara(v2c[i])){
				if(v1c[i].esExtesa() || v2c[i].esExtesa()){//Comprobem v1 o v2 son valors extesos ex: ((blau,0.5)(vermell,0.7))
					if(v1c[i].esExtesa()){//Si com a minim v1 ho es, hem d'extreure la modalitat i la frequencia
						ve1= new DadaExtesa(v1c[i].obtenirValor());
						ve1.inicialitzar();
						while (ve1.noFin()){
							x1=ve1.modalitat();//Conte quina es la modalitat del valor extes ex:blau
							num1=ve1.fraccio();//Conte el valor de la modalitat del valor extes ej:0.5

							if(v2c[i].esExtesa()){//Comprobem si v2,tambe es un valor extes
													   //Si ho es fem el mateix proces que per V1
								ve2= new DadaExtesa(v2c[i].obtenirValor());
								ve2.inicialitzar();
								trobat=false;
								while (ve2.noFin()&& !trobat){
									x2=ve2.modalitat();//Conte quina es la modalitat del valor extes ex:blau
									num2=ve2.fraccio();//Conte el valor de la modalitat del valor extes ej:0.5

									if(x1.compareTo(x2)==0){//Una vegada tenim els 2 valors fem el calcul
										cat=cat + (float)(Math.pow(((p1*(float)Float.parseFloat(num1))-(p2*(float)Float.parseFloat(num2))),2));
										trobat=true;
									}
								}
								if(!trobat){//Sumem encara que no hem trobat cap v2 igual
									cat=cat + (float)(Math.pow(((p1*(float)Float.parseFloat(num1))-0),2));
								}
							}
							else{//Si v2 no es un valor extes, ya podem fer els calculs
							     //depenen si les modalitas coincideixen o no
								if (x1.compareTo(v2c[i].categ())==0){
									cat=cat + (float)(Math.pow(((p1*(float)Float.parseFloat(num1))-(p2*1)),2));
								}
								else{
									cat=cat + (float)(Math.pow(((p1*(float)Float.parseFloat(num1))-(p2*0)),2));
								}
							}
						}
						//En Aquest punt ja hem calculat les posibles opcions si v1 era extes l'unic que ens falta
						//es afegir els valors de V2, si era extes,si no ja hem acabat per aquests 2 objectes
						if(v2c[i].esExtesa()){
							ve2= new DadaExtesa(v2c[i].obtenirValor());
							ve2.inicialitzar();
							while (ve2.noFin()){
								x2=ve2.modalitat();//Conte quina es la modalitat del valor extes ex:blau
								num2=ve2.fraccio();//Conte el valor de la modalitat del valor extes ej:0.5
								trobat=false;
								ve1= new DadaExtesa(v1c[i].obtenirValor());
								ve1.inicialitzar();
								while (ve1.noFin() && !trobat){
									//Utilizem el valor trobat, per saber quina modalitat hem de sumar i quina no
									x1=ve1.modalitat();//Conte quina es la modalitat del valor extes ex:blau
									num1=ve1.fraccio();//Conte el valor de la modalitat del valor extes ej:0.5
									if(x1.compareTo(x2)==0){
										trobat=true;
									}
								}
								if (!trobat){//Sumarem sempre que no hagem trobat cap modalitat de v1 que coincideixi
										     //amb la de v2
									cat=cat + (float)(Math.pow(((p1*0)-(p2*(float)Float.parseFloat(num2))),2));
								}
							}
						}
					}
					else{//Aqui fem els calculs de v2 valor extes i v1 valor no extes, es l'unica posibilitat no tractada
					     //Com hem fet amb v1 hem d'extreure la modalitat,la freq. y la freq total de cada valor
						if(v2c[i].esExtesa()){
							ve2= new DadaExtesa(v2c[i].obtenirValor());
							ve2.inicialitzar();
							while (ve2.noFin()){
								x2=ve2.modalitat();//Conte quina es la modalitat del valor extes ex:blau
								num2=ve2.fraccio();//Conte el valor de la modalitat del valor extes ej:0.5
								if (x2.compareTo(v1c[i].categ())==0){
									cat=cat + (float)(Math.pow(((p1*1)-(p2*(float)Float.parseFloat(num2))),2));
								}
								else{
									cat=cat + (float)(Math.pow(((p1*0)-(p2*(float)Float.parseFloat(num2))),2));
								}
							}
						}
					}
				cat=1-cat;
				}
			}
			else{
				cat++;
			}
		}
		sum=num+cat;
		return String.valueOf(1-(sum/w));
	}
/**
* Calcul de la distancia del valor absolut
*
* @param v1 te les components numèriques del vector corresponent a l'objecte 1
* @param v2 te les components numèriques del vector corresponent a l'objecte 2
* @param p1 conte el valor del pes de l'objecte1
* @param p2 conte el valor del pes de l'objecte2
* @return la distància del valor absolut entre l'objecte 1 i l' objecte 2a
*/
	public String abs(Dada[] v1,Dada[] v2,float p1,float p2){

		float sum=0;
		float x1;
		float x2;

		for (int i = 0; i < v1.length; i++) {
			sum=sum + ((float)(Math.abs((p1*v1[i].numero())-(p2*v2[i].numero()))));
		}
		return String.valueOf(sum);
	}
/**
* Calcul de la distancia del valor absolut normalitzat pel rang
*
* @param v1 es el vector amb les dades corresponents a l'objecte 1
* @param v2 es el vector amb les dades corresponents a l'objecte 2
* @param rang es un vector dels rangs de les propietats numèriques
* @param p1 conte el valor del pes de l'objecte1
* @param p2 conte el valor del pes de l'objecte2
* @return la distància del valor absolut normalitzada pel rang entre l'objecte 1 i l'objecte 2
*/
	public String absRang(Dada[] v1,Dada[] v2,float[] rang,float p1, float p2){

		float sum=0;

		for (int i = 0; i < v1.length; i++) {
			if(rang[i]!=0){
				sum=sum + ((float)(Math.abs(v1[i].numero()-v2[i].numero()))/rang[i]);
			}
		}
		return String.valueOf(sum);
	}
/**
* Calcul de la distancia de Hamming
*
* @param v1 es el vector amb les dades corresponents a l'objecte 1
* @param v2 es el vector amb les dades corresponents a l'objecte 2
* @return la distància de Hamming entre l'objecte 1 i l'objecte 2
*/
	public String hamm(Dada[] v1,Dada[] v2){

		float sum=0;

		for (int i = 0; i < v1.length; i++) {
			if (!v1[i].compara(v2[i])){
				sum++;
			}
		}
		return String.valueOf(sum);
	}
/**
* Calcul de la distancia de Chi2
*
* @param v1 es el vector amb les dades corresponents a l'objecte 1
* @param v2 es el vector amb les dades corresponents a l'objecte 2
* @param freq1 es el vector que conte la frequencia de les modalitats del valos de v1
* @param freq2 es el vector que conte la frequencia de les modalitats del valos de v2
* @param p1 conte el valor del pes de l'objecte1
* @param p2 conte el valor del pes de l'objecte2
* @return la distància de Chi2 entre l'objecte 1 i l'objecte 2
*/
	public String chi2(Dada[] v1,Dada[] v2,String[] freq1,String[] freq2, float p1, float p2){

		float sum=0;
		String num1=null;
		String x1=null;
		String mol1=null;
		String num2=null;
		String x2=null;
		String mol2=null;
		DadaExtesa ve1;
		DadaExtesa ve2;
		boolean trobat;


		for (int i = 0; i < v1.length; i++) {
			if (!v1[i].compara(v2[i])){
				if(v1[i].esExtesa() || v2[i].esExtesa()){//Comprobem v1 o v2 son valors extesos ex: ((blau,0.5)(vermell,0.7))
					if(v1[i].esExtesa()){//Si com a minim v1 ho es, hem d'extreure la modalitat i la frequencia
						ve1= new DadaExtesa(v1[i].obtenirValor());
						ve1.inicialitzar();
						ve1.freq(freq1[i]);
						while (ve1.noFin()){
							x1=ve1.modalitat();//Conte quina es la modalitat del valor extes ex:blau
							num1=ve1.fraccio();//Conte el valor de la modalitat del valor extes ej:0.5
							mol1=ve1.freqModal();//Conte la frequencia de la modalitat dins la matriu
																//es a dir el numero de blaus total en l'exemple

							if(v2[i].esExtesa()){//Comprobem si v2,tambe es un valor extes
													   //Si ho es fem el mateix proces que per V1
								ve2= new DadaExtesa(v2[i].obtenirValor());
								ve2.inicialitzar();
								ve2.freq(freq2[i]);
								trobat=false;
								while (ve2.noFin()&& !trobat){
									x2=ve2.modalitat();//Conte quina es la modalitat del valor extes ex:blau
									num2=ve2.fraccio();//Conte el valor de la modalitat del valor extes ej:0.5
									mol2=ve2.freqModal();//Conte la frequencia de la modalitat dins la matriu
																//es a dir el numero de blaus total en l'exemple
									if(x1.compareTo(x2)==0){//Una vegada tenim els 2 valors fem el calcul
										sum=sum + (float)Math.pow((float)(p1*Float.parseFloat(num1))-(p2*(float)Float.parseFloat(num2)),2)/(float)Float.parseFloat(mol1);
										trobat=true;
									}
								}
								if(!trobat){//Sumem encara que no hem trobat ca v2 igual
									sum=sum + (float)Math.pow(((float)(p1*Float.parseFloat(num1))-0),2)/(float)Float.parseFloat(mol1);

								}
							}
							else{//Si v2 no es un valor extes, ya podem fer els calculs
							     //depenen si les modalitas coincideixen o no
								if (x1.compareTo(v2[i].categ())==0){
									sum=sum + (float)Math.pow((float)(p1*Float.parseFloat(num1))-p2,2)/(float)Float.parseFloat(mol1);
								}
								else{
									sum=sum + (float)Math.pow((float)(p1*Float.parseFloat(num1)),2)/(float)Float.parseFloat(mol1);
								}
							}
						}
						//En Aquest punt ya hem calculat les posibles opcions si v1 era extes l'unic que ens falta
						//es afegir els valors de V2, si era extes,si no ya hem acabat per aquests 2 objectes
						if(v2[i].esExtesa()){
							ve2= new DadaExtesa(v2[i].obtenirValor());
							ve2.inicialitzar();
							ve2.freq(freq2[i]);
							while (ve2.noFin()){
								x2=ve2.modalitat();//Conte quina es la modalitat del valor extes ex:blau
								num2=ve2.fraccio();//Conte el valor de la modalitat del valor extes ej:0.5
								mol2=ve2.freqModal();//Conte la frequencia de la modalitat dins la matriu
															//es a dir el numero de blaus total en l'exemple
								trobat=false;
								ve1= new DadaExtesa(v1[i].obtenirValor());
								ve1.inicialitzar();
								ve1.freq(freq1[i]);
								while (ve1.noFin() && !trobat){
									//Utilizem el valor trobat, per saber quina modalitat hem de sumar i quina no
									x1=ve1.modalitat();//Conte quina es la modalitat del valor extes ex:blau
									num1=ve1.fraccio();//Conte el valor de la modalitat del valor extes ej:0.5
									mol1=ve1.freqModal();//Conte la frequencia de la modalitat dins la matriu
																//es a dir el numero de blaus total en l'exemple
									if(x1.compareTo(x2)==0){
										trobat=true;
									}
								}
								if (!trobat){//Sumarem sempre que no hagem trobat cap modalitat de v1 que coincideixi
										     //amb la de v2
									sum=sum + (float)Math.pow((float)(p2*Float.parseFloat(num2)),2)/(float)Float.parseFloat(mol2);
								}
							}
						}
					}
					else{//Aqui fem els calculs de v2 valor extes i v1 valor no extes, es l'unica posibilitat no tractada
					     //Com hem fet amb v1 hem d'extreure la modalitat,la freq. y la freq total de cada valor
						if(v2[i].esExtesa()){
							ve2= new DadaExtesa(v2[i].obtenirValor());
							ve2.inicialitzar();
							ve2.freq(freq2[i]);
							while (ve2.noFin()){
								x2=ve2.modalitat();//Conte quina es la modalitat del valor extes ex:blau
								num2=ve2.fraccio();//Conte el valor de la modalitat del valor extes ej:0.5
								mol2=ve2.freqModal();//Conte la frequencia de la modalitat dins la matriu
															//es a dir el numero de blaus total en l'exempleseq2=v2[i].categ().substring(1);
								if (x2.compareTo(v1[i].categ())==0){
									sum=sum + (float)Math.pow((float)(p2*Float.parseFloat(num2))-p1,2)/(float)Float.parseFloat(mol2);
								}
								else{
									sum=sum + (float)Math.pow((float)(p2*Float.parseFloat(num2)),2)/(float)Float.parseFloat(mol2);
								}
							}
						}
					}
				}
				else{
					sum=sum + ((p1/(float)Float.parseFloat(freq1[i]))+(p2/(float)Float.parseFloat(freq2[i])));
				}
			}

		}
		return String.valueOf(sum);
	}
/**
* Calcul de la distancia Mixta de Gibert
*
* @param v1n te les components numèriques del vector corresponent a l'objecte 1
* @param v2n te les components numèriques del vector corresponent a l'objecte 2
* @param v1c te les components categòriques del vector corresponent a l'objecte 1
* @param v2c te les components categòriques del vector corresponent a l'objecte 2
* @param sk2 es un vector amb les diferents desviacion tipiques de les propietats numèriques
* @param freq1 es el vector que conte la frequencia de les modalitats del valos de v1c
* @param freq2 es el vector que conte la frequencia de les modalitats del valos de v2c
* @param a es el valor de alfa, que ha d'estar entre 0 i 1
* @param b es el valors de beta,que ha d'estar entre 0 i 1
* @param p1 conte el valor del pes de l'objecte 1
* @param p2 conte el valor del pes de l'objecte 2
* @return la distància Mixta de Gibert entre l'objecte 1 i l'objecte 2 considerant les components numèriques i categòriques
*/
	public String mixta(Dada[] v1n,Dada[] v2n,Dada[] v1c,Dada[] v2c,float[] sk2,String[] freq1,String[] freq2,float a,float b,float p1,float p2){

		float sum=0;
		String num;
		String cat;
		float alfa=a;
		float beta=b;

		num=eucliNor(v1n,v2n,sk2,true,p1,p2);
		cat=chi2(v1c,v2c,freq1,freq2,p1,p2);
		sum=alfa*(Float.parseFloat(num))+ beta*(Float.parseFloat(cat));
		return String.valueOf(sum);
	}
/**
* Calcul de la distancia Ralambondrainy per la Inercia
*
* @param v1n te les components numèriques del vector corresponent a l'objecte 1
* @param v2n te les components numèriques del vector corresponent a l'objecte 2
* @param v1c te les components categòriques del vector corresponent a l'objecte 1
* @param v2c te les components categòriques del vector corresponent a l'objecte 2
* @param sk2 es un vector amb les diferents desviacion tipiques de les propietats numèriques
* @param freq1 es el vector que conte la frequencia de les modalitats del valos de v1c
* @param freq2 es el vector que conte la frequencia de les modalitats del valos de v2c
* @param numer es el nùmero de variables numèriques de v1n i v2n
* @param modal es el valor de la suma de les modalitats de les variables categòriques de v1c i v2c
* @param p1 conte el valor del pes de l'objecte1
* @param p2 conte el valor del pes de l'objecte2
* @return la distància de Ralambondrainy per la Inercia entre l'objecte 1 i l'objecte 2 considerant les components numèriques i categòriques
*/
	public String rIner(Dada[] v1n,Dada[] v2n,Dada[] v1c,Dada[] v2c,float[] sk2,String[] freq1,String[] freq2,int numer,int modal,float p1,float p2){

		float sum=0;
		String num;
		String cat;
		float alfa;
		float beta;

		if (numer==0){
			alfa=0;
		}
		else{
			alfa=(float)1/numer;
		}
		if (modal==0){
			beta=0;
		}
		else{
			beta=(float)1 /(modal-1);
		}
		num=eucliNor(v1n,v2n,sk2,true,p1,p2);
		cat=chi2(v1c,v2c,freq1,freq2,p1,p2);
		sum=alfa*(Float.parseFloat(num))+ beta*(Float.parseFloat(cat));
		return String.valueOf(sum);
	}
/**
* Calcul de la distancia Ralambondrainy per la Norma
*
* @param v1n te les components numèriques del vector corresponent a l'objecte 1
* @param v2n te les components numèriques del vector corresponent a l'objecte 2
* @param v1c te les components categòriques del vector corresponent a l'objecte 1
* @param v2c te les components categòriques del vector corresponent a l'objecte 2
* @param sk2 es un vector amb les diferents desviacion tipiques de les propietats numèriques
* @param freq1 es el vector que conte la frequencia de les modalitats del valos de v1c
* @param freq2 es el vector que conte la frequencia de les modalitats del valos de v2c
* @param correla es la suma de les correlacions de totes les propietats numèriques
* @param modal es la suma del nùmero de modalitats de les variables categòriques de v1c i v2c
* @param p1 conte el valor del pes de l'objecte1
* @param p2 conte el valor del pes de l'objecte2
* @return la distància de Ralambondrainy per la Norma entre l'objecte 1 i l'objecte 2 considerant les components numèriques i categòriques
*/
	public String rNorma(Dada[] v1n,Dada[] v2n,Dada[] v1c,Dada[] v2c,float[] sk2,String[] freq1,String[] freq2,float correla,int modal,float p1, float p2){

		float sum=0;
		String num;
		String cat;
		float alfa;
		float beta;

		if (correla==0){
			alfa=0;
		}
		else{
			alfa=(float)1/(float)Math.sqrt(correla);
		}
		if (modal==0){
			beta=0;
		}
		else{
			beta=(float)Math.sqrt(modal-1);
		}
		num=eucliNor(v1n,v2n,sk2,true,p1,p2);
		cat=chi2(v1c,v2c,freq1,freq2,p1,p2);
		sum=alfa*(Float.parseFloat(num))+ beta*(Float.parseFloat(cat));
		return String.valueOf(sum);
	}
/**
* Calcul de la distancia de Gowda-Diday
*
* @param v1n te les components numèriques del vector corresponent a l'objecte 1
* @param v2n te les components numèriques del vector corresponent a l'objecte 2
* @param v1c te les components categòriques del vector corresponent a l'objecte 1
* @param v2c te les components categòriques del vector corresponent a l'objecte 2
* @param rang es un vector amb els rangs de les propietats numèriques
* @param p1 conte el valor del pes de l'objecte1
* @param p2 conte el valor del pes de l'objecte2
* @return la distància de Gowda-Diday entre l'objecte 1 i l'objecte 2 considerant les components numèriques i categòriques
*/

	public String gowda(Dada[] v1n,Dada[] v2n,Dada[] v1c,Dada[] v2c,float[] rang,float p1,float p2){

		String num;
		float cat=0;
		float min=0;
		float max=0;
		float sum=0;
		String num1=null;
		String x1=null;
		String num2=null;
		String x2=null;
		DadaExtesa ve1;
		DadaExtesa ve2;
		boolean trobat;

		num=absRang(v1n,v2n,rang,p1,p2);
		for (int i = 0; i < v1c.length; i++) {
			if (!v1c[i].compara(v2c[i])){
				if(v1c[i].esExtesa() || v2c[i].esExtesa()){//Comprobem v1 o v2 son valors extesos ex: ((blau,0.5)(vermell,0.7))
					if(v1c[i].esExtesa()){//Si com a minim v1 ho es, hem d'extreure la modalitat i la frequencia
						ve1= new DadaExtesa(v1c[i].obtenirValor());
						ve1.inicialitzar();
						while (ve1.noFin()){
							x1=ve1.modalitat();//Conte quina es la modalitat del valor extes ex:blau
							num1=ve1.fraccio();//Conte el valor de la modalitat del valor extes ej:0.5

							if(v2c[i].esExtesa()){//Comprobem si v2,tambe es un valor extes
													   //Si ho es fem el mateix proces que per V1
								ve2= new DadaExtesa(v2c[i].obtenirValor());
								ve2.inicialitzar();
								trobat=false;
								while (ve2.noFin()&& !trobat){
									x2=ve2.modalitat();//Conte quina es la modalitat del valor extes ex:blau
									num2=ve2.fraccio();//Conte el valor de la modalitat del valor extes ej:0.5

									if(x1.compareTo(x2)==0){//Una vegada tenim els 2 valors fem el calcul
										min=min + (float)(Math.min(p1*(float)Float.parseFloat(num1),p2*(float)Float.parseFloat(num2)));
										max=max + (float)(Math.max(p1*(float)Float.parseFloat(num1),p2*(float)Float.parseFloat(num2)));
										trobat=true;
									}
								}
								if(!trobat){//Sumem encara que no hem trobat cap v2 igual
									min=min + (float)(Math.min(p1*(float)Float.parseFloat(num1),p2*0));
									max=max + (float)(Math.max(p1*(float)Float.parseFloat(num1),p2*0));
								}
							}
							else{//Si v2 no es un valor extes, ya podem fer els calculs
							     //depenen si les modalitas coincideixen o no
								if (x1.compareTo(v2c[i].categ())==0){
									min=min + (float)(Math.min(p1*(float)Float.parseFloat(num1),p2*1));
									max=max + (float)(Math.max(p1*(float)Float.parseFloat(num1),p2*1));
								}
								else{
									min=min + (float)(Math.min(p1*(float)Float.parseFloat(num1),p2*0));
									max=max + (float)(Math.max(p1*(float)Float.parseFloat(num1),p2*0));
								}
							}
						}
						//En Aquest punt ja hem calculat les posibles opcions si v1 era extes l'unic que ens falta
						//es afegir els valors de V2, si era extes,si no ja hem acabat per aquests 2 objectes
						if(v2c[i].esExtesa()){
							ve2= new DadaExtesa(v2c[i].obtenirValor());
							ve2.inicialitzar();
							while (ve2.noFin()){
								x2=ve2.modalitat();//Conte quina es la modalitat del valor extes ex:blau
								num2=ve2.fraccio();//Conte el valor de la modalitat del valor extes ej:0.5
								trobat=false;
								ve1= new DadaExtesa(v1c[i].obtenirValor());
								ve1.inicialitzar();
								while (ve1.noFin() && !trobat){
									//Utilizem el valor trobat, per saber quina modalitat hem de sumar i quina no
									x1=ve1.modalitat();//Conte quina es la modalitat del valor extes ex:blau
									num1=ve1.fraccio();//Conte el valor de la modalitat del valor extes ej:0.5
									if(x1.compareTo(x2)==0){
										trobat=true;
									}
								}
								if (!trobat){//Sumarem sempre que no hagem trobat cap modalitat de v1 que coincideixi
										     //amb la de v2
									min=min + (float)(Math.min(p1*0,p2*(float)Float.parseFloat(num2)));
									max=max + (float)(Math.max(p1*0,p2*(float)Float.parseFloat(num2)));
								}
							}
						}
					}
					else{//Aqui fem els calculs de v2 valor extes i v1 valor no extes, es l'unica posibilitat no tractada
					     //Com hem fet amb v1 hem d'extreure la modalitat,la freq. y la freq total de cada valor
						if(v2c[i].esExtesa()){
							ve2= new DadaExtesa(v2c[i].obtenirValor());
							ve2.inicialitzar();
							while (ve2.noFin()){
								x2=ve2.modalitat();//Conte quina es la modalitat del valor extes ex:blau
								num2=ve2.fraccio();//Conte el valor de la modalitat del valor extes ej:0.5
								if (x2.compareTo(v1c[i].categ())==0){
									min=min + (float)(Math.min(p1*1,p2*(float)Float.parseFloat(num2)));
									max=max + (float)(Math.max(p1*1,p2*(float)Float.parseFloat(num2)));
								}
								else{
									min=min + (float)(Math.min(p1*0,p2*(float)Float.parseFloat(num2)));
									max=max + (float)(Math.max(p1*0,p2*(float)Float.parseFloat(num2)));
								}
							}
						}
					}
					cat= (float)(2*(1-min))/max;
				}
				else{
					cat++;
				}
			}
		}
		sum=(Float.parseFloat(num))+cat;
		return String.valueOf(sum);
	}
/**
* Calcul de la distancia de Minkovski
*
* @param v1 es el vector amb les dades corresponents a l'objecte 1
* @param v2 es el vector amb les dades corresponents a l'objecte 2
* @param p es l'ordre de la distància de Minkovski, i ha de ser un numero natural
* @return la distància de Minkovski entre l'objecte 1 i l'objecte 2
*/
	public String minko(Dada[] v1,Dada[] v2,float p){

		float sum=0;

		for (int i = 0; i < v1.length; i++) {
			sum=sum + (float)(Math.pow(Math.abs(v1[i].numero()-v2[i].numero()),p));
		}
		sum=(float)Math.pow(sum,1/p);
		return String.valueOf(sum);

	}
/**
* Calcul de la distancia de Minkovski normalitzada pel Rang
*
* @param v1 es el vector amb les dades corresponents a l'objecte 1
* @param v2 es el vector amb les dades corresponents a l'objecte 2
* @param rang un vector amb els rangs de les propietats numèriques
* @param p es l'ordre de la distància de Minkovski, i ha de ser un numero natural
* @return la distància de Minkovski normalitzada pel Rang entre l'objecte 1 i l'objecte 2
*/
	public String minkoRang(Dada[] v1,Dada[] v2,float[] rang,float p){

		float sum=0;

		for (int i = 0; i < v1.length; i++) {
			if(rang[i]!=0){
				sum=sum + (float)(Math.pow(((Math.abs(v1[i].numero()-v2[i].numero()))/rang[i]),p));
			}
		}
		sum=(float)Math.pow(sum,1/p);
		return String.valueOf(sum);
	}
/**
* Calcul de la distancia Ichino-Yaguchi
*
* @param v1n te les components numèriques del vector corresponent a l'objecte 1
* @param v2n te les components numèriques del vector corresponent a l'objecte 2
* @param v1c te les components categòriques del vector corresponent a l'objecte 1
* @param v2c te les components categòriques del vector corresponent a l'objecte 2
* @param rang es un vector amb els rangs de les propietats numèriques
* @param modal es la suma del nùmero de modalitats de les variables categòriques de v1c i v2c
* @param p es l'ordre de la distància de Minkovski, i ha de ser un numero natural
* @param gamma es el parametre normalitzador de la distància de Minkovski i esta entre 0 i 0,5
* @return la distància de Ichino-Yaguchi entre l'objecte 1 i l'objecte 2
*/
	public String ichino(Dada[] v1n,Dada[] v2n,Dada[] v1c,Dada[] v2c,float[] rang,int modal,float p,float gamma){

		float sum=0;
		float join;
		float meet;
		float cardi;
		float fi;

		for (int i = 0; i < v1n.length; i++) {

			join=Math.abs(v1n[i].numero()-v2n[i].numero());
			if (v1n[i].compara(v2n[i])){
				meet=1;
			}
			else{
				meet=0;
			}
			cardi=0;
			fi=join-meet+ gamma*((2*meet)-cardi-cardi);
			if (rang[i]!=0){
				sum=sum+(float)(Math.pow((float)fi/rang[i],p));
			}
		}
		for (int i = 0; i < v1c.length; i++) {

			if (v1c[i].compara(v2c[i])){
				join=1;
				meet=1;
			}
			else{
				join=2;
				meet=0;
			}
			cardi=1;
			fi=join-meet+ gamma*((2*meet)-cardi-cardi);
			sum=sum+(float)(Math.pow((float)fi/modal,p));
		}
		sum=(float)Math.pow(sum,1/p);
		return String.valueOf(sum);
	}

}
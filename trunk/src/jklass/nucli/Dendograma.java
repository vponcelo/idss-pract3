package jklass.nucli;

import java.io.*;
import java.util.logging.*;
import java.util.*;

/**
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.1
 */
public class Dendograma {
  protected static Logger logger = Logger.getLogger(Dendograma.class.getName());
  /**
   * Fitxer on s'ha de escriure el codi LaTeX corresponent a la representació gráfica
   */
  protected FitxerTex fTex;
  protected double pas;
  protected double indexArrel;
  protected String prefix;
  protected boolean etiqRestr;
  protected String[] limit;
  protected double tall;
  protected boolean regles;

  public Dendograma(ArbreClassif arbre, String titol, double idnTall, boolean etiqRestrin,boolean reglas, String[] llista, FitxerTex fitxer) throws IOException {
    NodeBinari node;

    fTex = fitxer;
    etiqRestr = etiqRestrin;
    limit = llista;
    tall = idnTall;
	 regles = reglas;
    fTex.escriureLin("\\begin{figure}");
    fTex.escriureLin("\\noindent \\centering");
    fTex.escriureLin("\\setlength{\\unitlength}{10ex}");
    fTex.escriureLin("\\tiny");
    fTex.escriureLin("\\caption{CAJ. " + titol + "}");
    fTex.escriureLin("\\begin{picture}(" +
                     fTex.formatejarReal(Constants.AMPLADA_AR) + ","
                     + fTex.formatejarReal(Constants.ALZADA_AR) + ")(" +
                     fTex.formatejarReal(Constants.OFFSET_X_AR)
                     + "," + fTex.formatejarReal(Constants.OFFSET_Y_AR) + ")");
    fTex.escriureLin("\\thicklines");
    // es dibuixa un eix vertical
    fTex.escriureLin("\\put(" + fTex.formatejarReal(Constants.OFFSET_X_AR)+ ",0){\\line(0,1){"+ fTex.formatejarReal(Constants.LON_Y_AR) +"}}");
    // es gradua l'eix amb 3 marques
    fTex.escriureLin("\\put(" +fTex.formatejarReal(Constants.OFFSET_X_AR -0.1F)+",0){\\makebox(0,0)[rc]{0}}");
    fTex.escriureLin("\\put(" + fTex.formatejarReal(Constants.OFFSET_X_AR)+ ",0){\\line(1,0){0.1}}");
    fTex.escriureLin("\\put(" +fTex.formatejarReal(Constants.OFFSET_X_AR -0.1F)+","+ fTex.formatejarReal(Constants.LON_Y_AR /2) +"){\\makebox(0,0)[rc]{"+  fTex.formatejarDouble(arbre.obtenirArrel().obtenirIndexNivell()/2)+ "}}");
    fTex.escriureLin("\\put(" + fTex.formatejarReal(Constants.OFFSET_X_AR)+ ","+ fTex.formatejarReal(Constants.LON_Y_AR /2) +"){\\line(1,0){0.1}}");
    fTex.escriureLin("\\put(" +fTex.formatejarReal(Constants.OFFSET_X_AR -0.1F)+","+ fTex.formatejarReal(Constants.LON_Y_AR) +"){\\makebox(0,0)[rc]{"+ fTex.formatejarDouble(arbre.obtenirArrel().obtenirIndexNivell())+ "}}");
    fTex.escriureLin("\\put(" + fTex.formatejarReal(Constants.OFFSET_X_AR)+ ","+ fTex.formatejarReal(Constants.LON_Y_AR) +"){\\line(1,0){0.1}}");
    fTex.escriureLin("\\thinlines");
    node = arbre.obtenirArrel();
	 
	 
    pas = Constants.AMPLADA_AR / arbre.obtenirNumFulles(); //potser s'ha de dividir pel nombre total de nodes??
	 System.out.println("que hay en obtenirNumFulles?"+ arbre.obtenirNumFulles());  
	 
	 //indexArrel = node.obtenirIndexNivell();
	indexArrel = obtenirMaximIndex(node, arbre.obtenirControlInversions()); 
    prefix = arbre.obtenirPrefix();
    if (tall > 0) {
      fTex.escriureLin("\\put(-0.1," +
                       fTex.formatejarDouble( (tall * (Constants.LON_Y_AR)) /
                                             indexArrel) + "){\\dashbox{0.15}(" +
                       fTex.formatejarReal(Constants.AMPLADA_AR) + ",0)}");
    }
    tractaSubarbre(node,0, indexArrel,fTex);
    fTex.escriureLin("\\end{picture}");
    fTex.escriureLin("\\end{figure}");
  }

  /**
   *
   * @param node
   * @param x
   * @param indexArrel
   * @param nObjs
   * @param fTex
   * @return
   * @throws IOException
   */
  private double[] tractaSubarbre(NodeBinari node, double x, double indexPare, FitxerTex fTex) throws IOException {
    double pos[] = new double[2];
    double posE[] = new double[2];
    double posD[] = new double[2];
    double idn;

    if (node.esFulla()){
      pos = tractaFulla(node, x, indexPare, fTex);

    } else {
      idn = node.obtenirIndexNivell(); //index de nivell del node intern
	//	System.out.println("Entra al dendo?"+ idn);
      posE = tractaSubarbre(node.obtenirFillEsq(), x, idn ,fTex);
      posD = tractaSubarbre(node.obtenirFillDret(), posE[1], idn,fTex);
      pos[0] = tractaNodeIntern(node, posE[0], posD[0], idn, indexPare);
      pos[1] = posD[1];
    }
    return pos;
  }

  private double obtenirMaximIndex(NodeBinari node, boolean controlInversions) throws IOException {
		if (node.esFulla()){
		  return node.obtenirIndexNivell();
		} else {
		  double idn = node.obtenirIndexNivell(); //index de nivell del node intern
		  NodeBinari fillEsq = node.obtenirFillEsq();
		  double idnFE = obtenirMaximIndex(fillEsq, controlInversions);
		  NodeBinari fillDret = node.obtenirFillDret();
		  double idnFD = obtenirMaximIndex(fillDret, controlInversions);
		  double maxIndex = Math.max(idn, Math.max(idnFE, idnFD)); 
		  if (controlInversions && idn < maxIndex) { 
				node.modifIndexNivell(maxIndex); 
		  } 
		  return maxIndex;
		}
} 

  /**
   *
   * @param node
   * @param x
   * @param indexArrel
   * @param fTex
   * @return dos reals: el primer indica la posició on s'acaba de pintar la fulla i el segon la posició on s'ha de pintar la següent fulla
   * @throws IOException
   */
  private double[] tractaFulla(NodeBinari node, double x, double indexPare, FitxerTex fTex)throws IOException {
    String str;
    double lon;
    double pos[] = new double[2];

    //Rober Modifico para que tome cualquier valor valido
    //str = node.obtenirEtiqueta();
    str = fTex.adaptarATex(node.obtenirEtiqueta());
   // System.out.println("que hay en str"+str);
    lon = (indexPare* (Constants.LON_Y_AR))/indexArrel;
    pos[0] = x;
    pos[1] = pos[0] + pas;

    //Rober
    //fTex.escriureLin("\\put(" + fTex.formatejarDouble(x) + ",0){\\makebox(0,0)[t]{\\shortstack{"+ fTex.adaptarPerShortstack(str, str.length())+"}}}");
    fTex.escriureLin("\\put(" + fTex.formatejarDouble(x) + ",0){\\makebox(0,0)[t]{\\shortstack{"+ fTex.adaptarATex(fTex.adaptarPerShortstack(str, str.length()))+"}}}");

    fTex.escriureLin("\\put(" + fTex.formatejarDouble(x) + ",0){\\line(0,1){"+ fTex.formatejarDouble(lon)+"}}");
    return pos;
  }

  private double tractaNodeIntern(NodeBinari node, double xfe, double xfd, double indexNode, double indexPare)throws IOException {
    double x, index;
    String etiq;
	 

    x = (xfe + xfd) / 2;
    //Rober Modifico para que tome cualquier valor valido
    //etiq = node.obtenirEtiqueta();
    etiq = fTex.adaptarATex(node.obtenirEtiqueta());
//alejandro
 if ((fTex.encontrarChar(etiq, '*')))
  {node.modifReglas(true); 
 etiq = fTex.eliminarChar(etiq, '*');}
 

//ale

if ((regles)&&(!(node.obtenirReglas())) && (indexNode < tall)&& (!dinsLimit(etiq))   ){
   etiq = " ";	
	}

    index = (indexNode * (Constants.LON_Y_AR)) / indexArrel;// ale este lo puse arriba
    if (! (etiqRestr) || (indexNode > tall) || dinsLimit(etiq)) {
      // Rober comento la instruccion para que salga el prefijo no "c".
      //etiq = etiq.replaceFirst(prefix, "c"); esto dejalo comentado ale

      etiq = fTex.eliminarChar(etiq, '|');
      fTex.escriureLin("\\put(" + fTex.formatejarDouble(x) + "," +
                       fTex.formatejarDouble(index) + "){\\makebox(0.4,0.2){" +
                       etiq + "}}");
    }
	 
	 	 
	 
    fTex.escriureLin("\\put("+fTex.formatejarDouble(xfe)+ "," +
                     fTex.formatejarDouble(index)+"){\\line(1,0){" + fTex.formatejarDouble(xfd-xfe)+"}}");
    if (indexNode < indexPare){
	
      fTex.escriureLin("\\put("+ fTex.formatejarDouble(x) +","+
                       fTex.formatejarDouble(index) +"){\\line(0,1){"+
                       fTex.formatejarDouble(((indexPare * (Constants.LON_Y_AR))/indexArrel) - index) +"}}");
    } else{
	 
      fTex.escriureLin("\\put("+ fTex.formatejarDouble(x) +","+
                       fTex.formatejarDouble(index) +"){\\line(0,-1){"+
                       fTex.formatejarDouble(index - ((indexPare * (Constants.LON_Y_AR))/indexArrel)) +"}}");
    }
    return x;
  }


  private boolean dinsLimit(String etiq) {
    boolean ok = false;
    int i = 0;

    while ((i < limit.length) && (!ok)){
      ok = limit[i].equals(etiq);
      i++;
    }
    return ok;
  }
}

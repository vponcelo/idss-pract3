package jklass.nucli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas,Laia Riera Guerra
 * @version v.4
 */

class TaulaFreqs extends RepresentacioNumerica {
  TaulaFreqs(FitxerTex fitxer, String var, CalculsUnivCateg calculs, Hashtable opcs) throws IOException {
    super(fitxer);

    int nCol = 0, i, j, lon, missings, n, utils;
    float freq, acum, rel, relAcum;
    String modal;
    boolean freqs[] = {
        false, false, false, false};
    float valors[] = {
        Float.NaN, Float.NaN, Float.NaN, Float.NaN};
    String extras[] = {"", "", "", ""};


    freqs[0] = ( (Boolean) opcs.get("abs")).booleanValue();
    freqs[2] = ( (Boolean) opcs.get("rel")).booleanValue();

    if (freqs[0])
      nCol++;
    if (freqs[2])
      nCol++;

    if (calculs.obtenirTipusCateg().compareTo(Constants.PROP_NOMINAL) == 0) {
      /*  No cal perque s'ha establert que com a mín sempre tindrem l'opcio de freqs. absolutes
           if (nCol == 0){
              freqs[0] = true;
              nCol++;
            }
       */
      // La columna de freqs relatives contindrà cel·les buides si hi és
      escriureIniciTFreqs(nCol, var, freqs, true);
      lon = calculs.obtenirLonLlistaMods();
      i = 0;
      while (i < lon) {
        modal = calculs.obtenirModalitat(i);
        j = 0;
        if (freqs[0]) {
          valors[j] = calculs.obtenirFreq(i);
          j++;
        }
        if (freqs[2]) {
          valors[j] = calculs.obtenirFreqRel(i);
        }
        escriureFilaTFreqs(nCol, modal, valors);
        i++;
      }
      escriureLiniesHor(1);

      // fila d'observacions utils
      utils = calculs.obtenirNumUtils();
      n = calculs.obtenirNumObjs();
      j = 0;
      if (freqs[0]) {
        valors[j] = utils;
        j++;
      }
      if (freqs[2]) {
        valors[j] = utils / n;
      }
      escriureNumUtilsTFreqs(nCol, valors);

      // fila de dades mancants
      missings = calculs.obtenirNumMancants();
      j = 0;
      if (freqs[0]) {
        valors[j] = missings;
        j++;
      }
      if (freqs[2]) {
        valors[j] = missings / n;
      }
      escriureMancantsTFreqs(nCol, valors);

      // fila del total d'observacions
      j = 0;
      if (freqs[0]) {
        valors[j] = n;
        j++;
      }
      if (freqs[2]) {
        valors[j] = Float.NaN;
      }
      escriureNumObsTFreqs(nCol, valors);

    }
    else {
      freqs[1] = ( (Boolean) opcs.get("abs_acum")).booleanValue();
      freqs[3] = ( (Boolean) opcs.get("rel_acum")).booleanValue();
      if (freqs[1])
        nCol++;
      if (freqs[3])
        nCol++;
      escriureIniciTFreqs(nCol, null, freqs, false);
      lon = calculs.obtenirLonLlistaMods();
      i = 0;
      acum = 0;
      while (i < lon) {
        modal = calculs.obtenirModalitat(i);
        freq = calculs.obtenirFreq(i);
        acum = calculs.obtenirFreqAcum(i);
        rel = calculs.obtenirFreqRel(i);
        relAcum = calculs.obtenirFreqRelAcum(i);
        j = 0;
        if (freqs[0]) {
          valors[j] = freq;
          j++;
        }
        if (freqs[1]) {
          valors[j] = acum;
          j++;
        }
        if (freqs[2]) {
          valors[j] = rel;
          j++;
        }
        if (freqs[3]) {
          valors[j] = relAcum;
        }
        escriureFilaTFreqs(nCol, modal, valors);
        i++;
      }
      escriureLiniesHor(1);
      missings = calculs.obtenirNumMancants();
      j = 0;
      if (freqs[0]) {
        valors[j] = missings;
        j++;
      }
      if (freqs[1]) {
        extras[j] = "N = ";
        valors[j] = acum + missings;
        j++;
      }
      if (freqs[2]) {
        valors[j] = (float)missings / calculs.obtenirNumObjs();
        j++;
      }
      if (freqs[3]) {
        valors[j] = Float.NaN;
      }
      escriureMancantsTFreqs(nCol, valors, extras);
    }
    escriureFiTFreqs();

  }

  /**
   *
   * @param cols
   * @param var
   * @param freqs
   * @param especial indica si la columna de freqs. relatives s'ha de preparar per tenir una cel·la buida
   * @throws IOException
   */
  public void escriureIniciTFreqs(int cols, String var, boolean[] freqs, boolean especial) throws IOException{
    String str0 = "\\begin{tabular}{|c|" , str1 = "{\\bf Modalitats}", str2 = "";
    int i;

    fTex.escriureLin("\\begin{center}");
    // es preparen els strings per les linies inicials de la taula
    if (freqs[0]){
      str0 = str0 + "c|";
      str1 = str1 + " & {\\bf Freq.}";
      str2 = str2 + " & {\\bf absol.}";
    }
    if (freqs[1]){
      str0 = str0 + "c|";
      str1 = str1 + " & {\\bf Freq.}";
      str2 = str2 + " & {\\bf acum.}";
    }
    if (freqs[2]) {
      if (especial)
        str0 = str0 + "@{}c@{}|";
      else  str0 = str0 + "c|";
      str1 = str1 + " & {\\bf Freq.}";
      str2 = str2 + " & {\\bf relat.}";
    }
    if (freqs[3]) {
      str0 = str0 + "@{}c@{}|";
      str1 = str1 + " & {\\bf Freq.}";
      str2 = str2 + " & {\\bf rel. acum.}";
    }
    str0 = str0 + "}";
    str1 = str1 + " \\\\ ";
    str2 = str2 + " \\\\ ";

    fTex.escriureLin(str0);
    fTex.escriureLin("\\hline");
    if (var == null) {
      fTex.escriureLin(
          "\\multicolumn{" + (cols + 1) + "}{|c|}{\\bf Taula de freq\\\" u\\`encies} \\\\ ");
    }
    else {
      fTex.escriureLin(
          "\\multicolumn{" + (cols + 1) + "}{|c|}{\\bf Taula de freq\\\" u\\`encies Variable " +
          var + "} \\\\ ");
    }

    fTex.escriureLin("\\hline");
    fTex.escriureLin(str1);
    fTex.escriureLin(str2);
    fTex.escriureLin("\\hline");
    fTex.escriureLin("\\hline");
  }

  public void escriureFilaTFreqs(int cols, String modal, float[] valors) throws
      IOException {
    String str;
    int i;

    // Modalitats
    str = fTex.adaptarATex(modal);
    // Valors de les freqs
    for (i = 0; i < cols; i++) {
      str = str + " & " + fTex.formatejarReal(valors[i]);
    }
    str = str + " \\\\ ";
    fTex.escriureLin(str);
  }

  public void escriureLiniesHor(int num) throws IOException {
    int i=1;
    if (num > 0) {
      while (i <= num) {
        fTex.escriureLin("\\hline");
        i++;
      }
    }
  }

  public void escriureMancantsTFreqs(int cols, float[] valors) throws IOException {
    String str = "\\it dades mancants";
    int i, ancho;

    fTex.escriureLin("\\hline");
    for (i=0; i < cols;  i++) {
      if (Float.isNaN(valors[i])) {
        ancho = fTex.obtenirMaxDecimals()+5;
        if (ancho < Constants.MIN_COL_TAB) ancho = Constants.MIN_COL_TAB;
        str = str + " & \\colorbox{gris}{\\color{gris}$|$ \\hspace{" + ancho + "ex}}";
      } else {
        str = str + " & " + fTex.formatejarReal(valors[i]);
      }
    }
    str = str + " \\\\ ";
    fTex.escriureLin(str);
  }

  public void escriureMancantsTFreqs(int cols, float[] valors, String[] extras) throws IOException {
    String str = "\\it dades mancants";
    int i, ancho;

    fTex.escriureLin("\\hline");
    for (i=0; i < cols;  i++) {
      if (Float.isNaN(valors[i])) {
        ancho = fTex.obtenirMaxDecimals()+5;
        if (ancho < Constants.MIN_COL_TAB) ancho = Constants.MIN_COL_TAB;
        str = str + " & \\colorbox{gris}{\\color{gris}$|$ \\hspace{" + ancho + "ex}}";
      } else {
        str = str + " & " +  extras[i] + fTex.formatejarReal(valors[i]);
      }
    }
    str = str + " \\\\ ";
    fTex.escriureLin(str);
  }

  public void escriureNumUtilsTFreqs(int cols, float[] valors) throws IOException {
    String str = "\\it dades \\'utils";
    int i;

    fTex.escriureLin("\\hline");
    for (i=0; i < cols;  i++) {
      if (Float.isNaN(valors[i])) {
        str = str + " & \\colorbox{gris}{\\color{gris}$|$ \\hspace{" + (fTex.obtenirMaxDecimals() + 5) + "ex}}";
      } else {
        str = str + " & " + fTex.formatejarReal(valors[i]);
      }
    }
    str = str + " \\\\ ";
    fTex.escriureLin(str);
  }

  public void escriureNumObsTFreqs(int cols, float[] valors) throws IOException {
    String str = "\\it total observacions";
    int i;

    fTex.escriureLin("\\hline");
    for (i = 0; i < cols; i++) {
      if (Float.isNaN(valors[i])) {
        str = str + " & \\colorbox{gris}{\\color{gris}$|$ \\hspace{" +
            (fTex.obtenirMaxDecimals() + 5) + "ex}}";
      }
      else {
        str = str + " & " + fTex.formatejarReal(valors[i]);
      }
    }
    str = str + " \\\\ ";
    fTex.escriureLin(str);
  }


  public void escriureFiTFreqs() throws IOException {
    fTex.escriureLin("\\hline");
    fTex.escriureLin("\\end{tabular}");
    fTex.escriureLin("\\end{center} \\vfill");
    fTex.escriureLin("");
  }
  
  //////////////////////////////////////////////LAIA/////////////////////////////
  /**
   * Escriu en un fitxer latex les capçaleres de la taula de freqüències per les descriptives de bases de coneixement
   * @param cols, número de columnes
   * @param var, variable
   * @param freqs, freqüències a mostrar
   * @param especial, indica si la columna de freqs. relatives s'ha de preparar per tenir una cel·la buida
   * @param bc, ens indica si prové de la descriptiva de bases de coneixement
   * @throws IOException
   * @author Laia Riera Guerra
   */
  public void escriureIniciTFreqs(int cols, String var, boolean[] freqs, boolean especial,boolean bc) throws IOException{
	  String str0 = "\\begin{tabular}{|c|" , str1 = "{\\bf Valors}", str2 = "";
	    int i;

	    fTex.escriureLin("\\begin{center}");
	    // es preparen els strings per les linies inicials de la taula
	    if (freqs[0]){
	      str0 = str0 + "c|";
	      str1 = str1 + " & {\\bf Freq.}";
	      str2 = str2 + " & {\\bf absol.}";
	    }
	    if (freqs[1]){
	      str0 = str0 + "c|";
	      str1 = str1 + " & {\\bf Freq.}";
	      str2 = str2 + " & {\\bf acum.}";
	    }
	    if (freqs[2]) {
	      if (especial)
	        str0 = str0 + "@{}c@{}|";
	      else  str0 = str0 + "c|";
	      str1 = str1 + " & {\\bf Freq.}";
	      str2 = str2 + " & {\\bf relat.}";
	    }
	    if (freqs[3]) {
	      str0 = str0 + "@{}c@{}|";
	      str1 = str1 + " & {\\bf Freq.}";
	      str2 = str2 + " & {\\bf rel. acum.}";
	    }
	    str0 = str0 + "}";
	    str1 = str1 + " \\\\ ";
	    str2 = str2 + " \\\\ ";

	    fTex.escriureLin(str0);
	    fTex.escriureLin("\\hline");
	    if (var == null) {
	      fTex.escriureLin(
	          "\\multicolumn{" + (cols + 1) + "}{|c|}{\\bf Taula de freq\\\" u\\`encies} \\\\ ");
	    }
	    else {
	      fTex.escriureLin(
	          "\\multicolumn{" + (cols + 1) + "}{|c|}{\\bf Taula de freq\\\" u\\`encies Variable " +
	          var + "} \\\\ ");
	    }

	    fTex.escriureLin("\\hline");
	    fTex.escriureLin(str1);
	    fTex.escriureLin(str2);
	    fTex.escriureLin("\\hline");
	    fTex.escriureLin("\\hline");
	  }
  /**
   * Constructora
   * @param fitxer, fitxer latex on s'escriu
   * @param var, variable
   * @param calculs, CalculsUnivCateg
   * @param opcs, Hashtable
   * @param bc, indica si es tracta de la constructora de TaulaFreqs per la descritpiva de bases de coneixement
   * @throws IOException
   * @author Laia Riera Guerra
   */
  TaulaFreqs(FitxerTex fitxer, String var, CalculsUnivCateg calculs, Hashtable opcs,boolean bc) throws IOException {
	    super(fitxer);

	    int nCol = 0, i, j, lon, missings, n, utils;
	    float freq, acum, rel, relAcum;
	    String modal;
	    boolean freqs[] = {
	        false, false, false, false};
	    float valors[] = {
	        Float.NaN, Float.NaN, Float.NaN, Float.NaN};
	    String extras[] = {"", "", "", ""};


	    freqs[0] = ( (Boolean) opcs.get("abs")).booleanValue();
	    freqs[2] = ( (Boolean) opcs.get("rel")).booleanValue();

	    if (freqs[0])
	      nCol++;
	    if (freqs[2])
	      nCol++;

	    if (calculs.obtenirTipusCateg().compareTo(Constants.PROP_NOMINAL) == 0) {
	      /*  No cal perque s'ha establert que com a mín sempre tindrem l'opcio de freqs. absolutes
	           if (nCol == 0){
	              freqs[0] = true;
	              nCol++;
	            }
	       */
	      // La columna de freqs relatives contindrà cel·les buides si hi és
	      escriureIniciTFreqs(nCol, var, freqs, true,bc);
	      lon = calculs.obtenirLonLlistaMods();
	      i = 0;
	      while (i < lon) {
	        modal = calculs.obtenirModalitat(i);
	        j = 0;
	        if (freqs[0]) {
	          valors[j] = calculs.obtenirFreq(i);
	          j++;
	        }
	        if (freqs[2]) {
	          valors[j] = calculs.obtenirFreqRel(i);
	        }
	        escriureFilaTFreqs(nCol, modal, valors);
	        i++;
	      }
	      escriureLiniesHor(1);

	      // fila d'observacions utils
	      utils = calculs.obtenirNumUtils();
	      n = calculs.obtenirNumObjs();
	      j = 0;
	      if (freqs[0]) {
	        valors[j] = utils;
	        j++;
	      }
	      if (freqs[2]) {
	        valors[j] = utils / n;
	      }
	      escriureNumUtilsTFreqs(nCol, valors);

	      // fila de dades mancants
	      missings = calculs.obtenirNumMancants();
	      j = 0;
	      if (freqs[0]) {
	        valors[j] = missings;
	        j++;
	      }
	      if (freqs[2]) {
	        valors[j] = missings / n;
	      }
	      escriureMancantsTFreqs(nCol, valors);

	      // fila del total d'observacions
	      j = 0;
	      if (freqs[0]) {
	        valors[j] = n;
	        j++;
	      }
	      if (freqs[2]) {
	        valors[j] = Float.NaN;
	      }
	      escriureNumObsTFreqs(nCol, valors);

	    }
	    else {
	      freqs[1] = ( (Boolean) opcs.get("abs_acum")).booleanValue();
	      freqs[3] = ( (Boolean) opcs.get("rel_acum")).booleanValue();
	      if (freqs[1])
	        nCol++;
	      if (freqs[3])
	        nCol++;
	      escriureIniciTFreqs(nCol, null, freqs, false,bc);
	      lon = calculs.obtenirLonLlistaMods();
	      i = 0;
	      acum = 0;
	      while (i < lon) {
	        modal = calculs.obtenirModalitat(i);
	        freq = calculs.obtenirFreq(i);
	        acum = calculs.obtenirFreqAcum(i);
	        rel = calculs.obtenirFreqRel(i);
	        relAcum = calculs.obtenirFreqRelAcum(i);
	        j = 0;
	        if (freqs[0]) {
	          valors[j] = freq;
	          j++;
	        }
	        if (freqs[1]) {
	          valors[j] = acum;
	          j++;
	        }
	        if (freqs[2]) {
	          valors[j] = rel;
	          j++;
	        }
	        if (freqs[3]) {
	          valors[j] = relAcum;
	        }
	        escriureFilaTFreqs(nCol, modal, valors);
	        i++;
	      }
	      escriureLiniesHor(1);
	      missings = calculs.obtenirNumMancants();
	      j = 0;
	      if (freqs[0]) {
	        valors[j] = missings;
	        j++;
	      }
	      if (freqs[1]) {
	        extras[j] = "N = ";
	        valors[j] = acum + missings;
	        j++;
	      }
	      if (freqs[2]) {
	        valors[j] = (float)missings / calculs.obtenirNumObjs();
	        j++;
	      }
	      if (freqs[3]) {
	        valors[j] = Float.NaN;
	      }
	      escriureMancantsTFreqs(nCol, valors, extras);
	    }
	    escriureFiTFreqs();

  }
  /**
   * Escriu la capçalera de la taula de freqüències per la discretització
   * @throws IOException
   * @author Laia Riera Guerra
   */
  public void escriureIniciTDiscretitzacio() throws IOException{
	  fTex.escriureLin("\\begin{center}");	  
	    fTex.escriureLin("\\begin{tabular}{|c||c|c|}");
	    fTex.escriureLin("\\hline");
	    fTex.escriureLin("\\hline");
	    fTex.escriureLin("{\\bf Modalitat} & {\\bf M\\'inim} & {\\bf M\\`axim} \\\\");	   
	    fTex.escriureLin("\\hline");
	    fTex.escriureLin("\\hline");
  }
  /**
   * Constructora de la TaulaFreqs per la discretització de variables
   * @param fitxer, fitxer latex on s'escriurà
   * @param dadesAux, llistat de valors dels intervals
   * @param llistaMods, llistat de modalitats
   * @throws IOException
   * @author Laia Riera Guerra, Patrícia Garcia, Alfons Bosch
   */
  TaulaFreqs(FitxerTex fitxer,Object dadesAux,ArrayList llistaMods, int tipus) throws IOException {
	    super(fitxer);
	    escriureIniciTDiscretitzacio();
	    float[] intervals;
	    
	    
	    if ( tipus == 1 ){
	    	intervals = (float[])((List) dadesAux).get(0);
	    	//Si es revised discretization
	    	if ( (Boolean)((List)dadesAux).get(1) ){
	    		//Mètode revisat centre obert
	    		if ( ((Integer)((List)dadesAux).get(2)).intValue() == 1 ){
	    			escriureFilaTDiscretitzacio((String)llistaMods.get(0),intervals[0],intervals[1],"[","]");
	    			escriureFilaTDiscretitzacio((String)llistaMods.get(1),intervals[1],intervals[2],"(",")");
	    			escriureFilaTDiscretitzacio((String)llistaMods.get(2),intervals[2],intervals[3],"[","]");
	    		}
	    		//Mètode revisat centre tancat
	    		else{
	    			escriureFilaTDiscretitzacio((String)llistaMods.get(0),intervals[0],intervals[1],"[",")");
	    			escriureFilaTDiscretitzacio((String)llistaMods.get(1),intervals[1],intervals[2],"[","]");
	    			escriureFilaTDiscretitzacio((String)llistaMods.get(2),intervals[2],intervals[3],"(","]");
	    		}
	    	}
	    	else{
	    		escriureFilaTDiscretitzacio((String)llistaMods.get(0),intervals[0],intervals[1],"[","]");
	    		for (int i=1;i<llistaMods.size();i++){
	    			escriureFilaTDiscretitzacio((String)llistaMods.get(i),intervals[i],intervals[i+1],"(","]");
	    			
	    		}
	    	}
	    }
	    else{
	    	
	    	intervals = (float[]) dadesAux;
	    	for(int i=0;i<llistaMods.size();i++){
	    		escriureFilaTDiscretitzacio((String)llistaMods.get(i),intervals[i],intervals[i+1],"","");
	    	}     
	    	
	    }
	    escriureLiniesHor(1);     
    	escriureFiTFreqs();
  }
  /**
   * Escriu cada fila de la taula per la discretització
   * @param modal, modalitat
   * @param minim, valor mínim de l'interval
   * @param maxim, valor màxim de l'interval
   * @param intervalEsquerre - indica si l'interval esquerre és obert o tancat
   * @param intervalDret - indica si l'interval dret és obert o tancat
   * @throws IOException
   * @author Laia Riera Guerra, Alfons Bosch, Patrícia Garcia
   */
  public void escriureFilaTDiscretitzacio(String modal,float minim,float maxim,String intervalEsquerre,String intervalDreta) throws  IOException {
	    String str;
	    int i;
	    // Modalitats
	    str = fTex.adaptarATex(modal);
	    // Valors de les freqs
	    str = str + " & " + intervalEsquerre + fTex.formatejarReal(minim) + " & " + fTex.formatejarReal(maxim) + intervalDreta;	   
	    //str = str + " & " + fTex.formatejarReal(minim) + " & " + fTex.formatejarReal(maxim);
	    str = str + " \\\\ ";
	    fTex.escriureLin(str);
	  }

}
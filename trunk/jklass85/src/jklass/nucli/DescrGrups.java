package jklass.nucli;

import java.io.IOException;
import java.util.Hashtable;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

class DescrGrups extends RepresentacioNumerica {
  DescrGrups(FitxerTex fitxer,CalculsBivNC calculs, String varNum,
                                    String varCateg, Hashtable opcs,
                                    int maxFigs, int inici, boolean categEtiq) throws
      OpcioIncorrectaException, CreacioFitxerException {
    super(fitxer);

    int i, m, numPags, numFigs, n, limit, c, numc;
    boolean bMin, bMax, bAmp, bMitj ,bVar, bQV, bDesv, bQD, bCoef, bMe, bQs, bDiQ;
    String[] modals;
    String strTab, strTit, strLin, strAux, mod;
    CalculsUnivNum calcNum;


    try {
      if ( (categEtiq) && (inici == 0)) {
        fTex.escriureLin("\\begin{center}");
        if ( (varNum == null) || (varCateg == null)) {
          fTex.escriureLin(
              "{\\bf Descriptiva per grups }\\vspace{0.5em}");
        }
        else {
          fTex.escriureLin(
              "{\\bf Descriptiva per grups per les variables " +
              varNum + " i " + varCateg + " }\\vspace{0.5em}");
        }
        fTex.escriureLin(" ");
      }

      if (opcs == null) {
        bMin = true;
        bMax = true;
        bAmp = true;
        bMitj = true;
        bVar = true;
        bQV = true;
        bDesv = true;
        bQD = true;
        bCoef = true;
        // Estadistics Robustos
        bMe = true;
        bQs = true;
        bDiQ = true;
      } else {
        bMin = ( (Boolean) opcs.get("min")).booleanValue();
        bMax = ( (Boolean) opcs.get("max")).booleanValue();
        bAmp = ( (Boolean) opcs.get("amp")).booleanValue();
        bMitj = ( (Boolean) opcs.get("mitj")).booleanValue();
        bVar = ( (Boolean) opcs.get("var")).booleanValue();
        bQV = ( (Boolean) opcs.get("qvar")).booleanValue();
        bDesv = ( (Boolean) opcs.get("desv")).booleanValue();
        bQD = ( (Boolean) opcs.get("qdesv")).booleanValue();
        bCoef = ( (Boolean) opcs.get("coef")).booleanValue();
        // Estadistics Robustos
        bMe = ( (Boolean) opcs.get("me")).booleanValue();
        bQs = ( (Boolean) opcs.get("q")).booleanValue();
        bDiQ = ( (Boolean) opcs.get("dist_iq")).booleanValue();
      }
      strTab = new String("\\begin{tabular}{|c||c|c|");
      strTit = new String(
          "{\\bf " + varCateg +"} & {\\bf Obs. \\'utils} & {\\bf Mancants }");
      // linia necesaria per fer ajustos per visulitzar la barra superior de la mitjana
      strLin = new String(" &  & ");
      numc = 3;

      if (bMitj) {
        strTab = strTab + "c|";
        strTit = strTit + " & $\\bf \\bar{X}$";
        strLin = strLin + " & " ;
        numc++;
      }
      if (bVar) {
        strTab = strTab + "c|";
        strTit = strTit + " & {\\bf V}";
        strLin = strLin + " & " ;
        numc++;
      }
      if (bQV) {
        strTab = strTab + "c|";
        strTit = strTit + " & $\\bf S^2$";
        strLin = strLin + " & " ;
        numc++;
      }
      if (bDesv) {
        strTab = strTab + "c|";
        strTit = strTit + " & $\\bf \\sqrt{V}$";
        strLin = strLin + " & " ;
        numc++;
      }
      if (bQD) {
        strTab = strTab + "c|";
        strTit = strTit + "& {\\bf  S}";
        strLin = strLin + " & " ;
        numc++;
      }
      if (bCoef) {
        strTab = strTab + "c|";
        strTit = strTit + "& {\\bf  CV}";
        strLin = strLin + " & " ;
        numc++;
      }
      strTab = strTab + "}";
      strTit = strTit + "\\\\";
      strLin = strLin + "\\\\  [-2.4ex]";

      fTex.escriureLin(strTab);
      fTex.escriureLin("\\hline");
      strAux = new String("&\\multicolumn{" + (numc - 1) + "}{|c|}{\\bf " +
                          varNum + "}\\\\");
      fTex.escriureLin(strAux);
      strAux = new String("\\cline{2-"+ numc +"}");
      fTex.escriureLin(strAux);
      fTex.escriureLin(strLin);
      fTex.escriureLin(strTit);
      fTex.escriureLin("\\hline\\hline");

      m = inici;
      limit = inici + maxFigs;
      modals = calculs.obtenirModalitats();
	//	if (bMin || bMax || bAmp || bMe || bQs || bDiQ){//ale
      while ( m < limit){
        mod = modals[m];
        calcNum = calculs.obtenirCalculsUniv(mod);
        strLin = new String( fTex.adaptarATex(mod)+ " & " +
                    fTex.formatejarReal(calcNum.obtenirNumUtils()) + " & " +
                    fTex.formatejarReal(calcNum.obtenirNumMancants()));
        if (bMitj) {
          strLin = strLin + " & " +
                    fTex.formatejarReal(calcNum.obtenirMitjana());
        }
        if (bVar) {
          strLin = strLin + " & " + fTex.formatejarReal(calcNum.obtenirVar());
        }
        if (bQV) {
          strLin = strLin +  " & " +
                    fTex.formatejarReal(calcNum.obtenirQuasiVar());
        }
        if (bDesv) {
          strLin = strLin + " & " + fTex.formatejarReal(calcNum.obtenirDesv());
        }
        if (bQD) {
          strLin = strLin + " & " +
                    fTex.formatejarReal(calcNum.obtenirQuasiDesv());
        }
        if (bCoef) {
          strLin = strLin + " & " +
                    fTex.formatejarReal(calcNum.obtenirCoefCorr());
        }
        strLin = strLin + " \\\\";
        fTex.escriureLin(strLin);
        fTex.escriureLin("\\hline");
        m++;
      }
	      fTex.escriureLin("\\end{tabular}");

      fTex.escriureLin(" ");
      fTex.escriureLin("\\vfill");
      fTex.escriureLin(" ");

      strTab = new String ("\\begin{tabular}{|c||");
      strTit = new String("{\\bf " + varCateg +"} ");
      numc = 1;
		if (bMin || bMax || bAmp || bMe || bQs || bDiQ){//alejandro para que controle la segunda tabla

      if (bMin) {
        strTab = strTab + "c|";
        strTit = strTit + " & {\\bf min }";
        numc++;
      }
      if (bMax) {
        strTab = strTab + "c|";
        strTit = strTit + " & {\\bf max }";
        numc++;
      }
      if (bAmp) {
        strTab = strTab + "c|";
        strTit = strTit + " & {\\bf Amp}";
        numc++;
      }
     
	   if (bMe) {
        strTab = strTab + "c|";
        strTit = strTit + " & {\\bf Me}";
        numc++;
      }
		
		
      if (bQs) {
        strTab = strTab + "c|c|";
        strTit = strTit + " & $\\bf  Q_{1}$ &  $\\bf Q_{3}$";
        numc = numc + 2;
      }
      if (bDiQ) {
        strTab = strTab + "c|";
        strTit = strTit + " & $\\bf d_{i}$";
        numc++;
      }
      strTab = strTab + "}";
      strTit = strTit + "\\\\";

      fTex.escriureLin(strTab);
      fTex.escriureLin("\\hline");
      strAux = new String("&\\multicolumn{" + (numc - 1) + "}{|c|}{\\bf " +
                          varNum + "}\\\\");
      fTex.escriureLin(strAux);
      strAux = new String("\\cline{2-" + numc + "}");
      fTex.escriureLin(strAux);
      fTex.escriureLin(strTit);
      fTex.escriureLin("\\hline\\hline");
      m = inici;
      limit = inici + maxFigs;
		}//alejandro fin if
      modals = calculs.obtenirModalitats();
		if (bMin || bMax || bAmp || bMe || bQs || bDiQ){//alejandro para que controle la segunda tabla
		                                                 // si son todos falsos daria error
		
      while ( m < limit){
        mod = modals[m];
        calcNum = calculs.obtenirCalculsUniv(mod);

        strLin = new String( fTex.adaptarATex(mod));
	  
        if (bMin) {
          strLin = strLin + " & " +
              fTex.formatejarReal(calcNum.obtenirMin());
        }
        if (bMax) {
          strLin = strLin + " & " + fTex.formatejarReal(calcNum.obtenirMax());
        }
        if (bAmp) {
          strLin = strLin + " & " +
              fTex.formatejarReal(calcNum.obtenirAmplitud());
        }
        if (bMe) {
          strLin = strLin + " & " + fTex.formatejarReal(calcNum.obtenirMediana());
        }
		  
		 /* if (bMitj) {
          strLin = strLin + " & " +
                    fTex.formatejarReal(calcNum.obtenirMitjana());
        }*/

		  
        if (bQs) {
          strLin = strLin + " & " + fTex.formatejarReal(calcNum.obtenirQ1()) + " & " +
              fTex.formatejarReal(calcNum.obtenirQ3());
        }
        if (bDiQ) {
          strLin = strLin + " & " +
              fTex.formatejarReal(calcNum.obtenirDistInterQ());
        }
		  
		  
        strLin = strLin + " \\\\";

        fTex.escriureLin(strLin);
        fTex.escriureLin("\\hline");
        m++;
      }
		
      fTex.escriureLin("\\end{tabular}");
	}//ale	
      if ( (categEtiq) && (limit == calculs.obtenirNumModalitats())) {
        fTex.escriureLin("\\end{center}");
        fTex.escriureLin("\\vfill");
        fTex.escriureLin("");
      }
		
    }
	 
    catch (IOException e) {
      throw new CreacioFitxerException("Error d'escriptura: " + e.getMessage());
    }

 }

}

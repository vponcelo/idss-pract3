package jklass.nucli;

import java.io.IOException;
import java.util.*;

/**
 * This class writes a table in a latex file with values for quality of the knowledge base
 * 
 * @author Esther Lozano
 *
 */
public class TaulaQualitat extends RepresentacioNumerica
{
	TaulaQualitat(FitxerTex fitxer, ArrayList alRegles, boolean[] cols, Vector<float[]> values) throws IOException 
	{
		super(fitxer);

		escriureIniciTaula(cols);
		
		int counter = 0;
		      
		for(int i = 0; i < alRegles.size(); i++)
		{
			if(counter > 45)
			{
				escriureFiTaula();
				escriureIniciTaula(cols);
				counter = -1;
			}
			
			Regla r = (Regla)alRegles.get(i);
			//String nomR = (String)alNoms.get(i);
			String nomR = (String)r.getNomRegla();
			float[] fValues = new float[values.size()];
			
			for(int j = 0; j < values.size(); j++)
			{
				fValues[j] = ((float[])values.get(j))[i];
			}
			
			escriureFilaTaula(nomR, r.getConseguent(), fValues, values.size());
			fTex.escriureLin("\\hline");
			
			counter++;
		}
	 
		escriureFiTaula();

	}
	
	/**
	 * Modified by:
	 * @author Grup SISPD QT 2009-2010
	 */
	public void escriureIniciTaula(boolean[] cols) throws IOException
	{
		int ncols = 2;
		
		String str0 = "\\begin{tabular}{|p{3cm}|" , str1 = "{\\bf Valors}", str2 = "";
		    
	    fTex.escriureLin("\\begin{center}");
	    
	    str0 = str0 + "c|";
    	str1 = str1 + " & {\\bf Consecuent}";
    	str2 = str2 + " & {\\bf }";
	    
	    // es preparen els strings per les linies inicials de la taula
	    if (cols[0])
	    {
	    	str0 = str0 + "c|";
	    	str1 = str1 + " & {\\bf Confian\\c ca}";
	    	str2 = str2 + " & {\\bf }";
	    	ncols++;
	    }
	    if (cols[1])
	    {
	    	str0 = str0 + "c|";
	    	str1 = str1 + " & {\\bf Support}";
	    	str2 = str2 + " & {\\bf }";
	    	ncols++;
	    }
	    if (cols[2]) 
	    {
	    	str0 = str0 + "c|";
	    	str1 = str1 + " & {\\bf Cobertura}";
	    	str2 = str2 + " & {\\bf relativa}";
	    	ncols++;
	    }
	    /*if (cols[3]) 
	    {
	    	str0 = str0 + "@{}c@{}|";
	    	str1 = str1 + " & {\\bf Cobertura}";
	    	str2 = str2 + " & {\\bf global}";
	    	ncols++;
	    }*/
	    
	    str0 = str0 + "}";
	    str1 = str1 + " \\\\ ";
	    str2 = str2 + " \\\\ ";

	    fTex.escriureLin(str0);
	    fTex.escriureLin("\\hline");
	  
	    fTex.escriureLin(
	          "\\multicolumn{" + ncols + "}{|c|}{\\bf Taula de qualitat} \\\\ ");

	    fTex.escriureLin("\\hline");
	    fTex.escriureLin(str1);
	    fTex.escriureLin(str2);
	    fTex.escriureLin("\\hline");
	    fTex.escriureLin("\\hline");
    }
	
	
	public void escriureFilaTaula(String nom, String consecuent, float[] values, int nCols) throws IOException 
	{
		String str;
		int i;
		float v;
		
		str = fTex.adaptarATex(nom);
		str = str + " & " + fTex.adaptarATex(consecuent);
		
		// Valors de les freqs
		for (i = 0; i < nCols; i++) 
		{
			v = values[i];
			
			//If the value was an error or a missing, we put an '*'
			if(v == -1)
			{
				str = str + " & " + "*";
			}
			else
			{
				str = str + " & " + fTex.formatejarReal(v);
			}
		}
		
		str = str + " \\\\ ";
		fTex.escriureLin(str);
	}
	
	
	public void escriureFiTaula() throws IOException 
	{
	    fTex.escriureLin("\\hline");
	    fTex.escriureLin("\\end{tabular}");
	    fTex.escriureLin("\\end{center} \\vfill");
	    fTex.escriureLin("");
	  }
}

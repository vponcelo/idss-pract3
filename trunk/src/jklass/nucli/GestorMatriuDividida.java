package jklass.nucli;

public class GestorMatriuDividida extends GestorMatriu {

	static final int NO_RESIDUAL = -1;
	private static final String RESIDUAL = "Residual";

	private String valorDivisio;

	GestorMatriuDividida(int posId, String fileName, LlistaPropietats llistaPropietats, String vDivisio){
		super(posId, fileName, llistaPropietats);
		valorDivisio = vDivisio;
	}

	String obtenirValorDivisio() {
		return valorDivisio;
	}
	
	/*boolean isResidual(String nomVar) {
  		int index = obtenirLlistaProps().obtenirIndex(nomVar);
  		Objecte o = obtenirLlistaObjs().obtenirObjecte(0);
  		Dada[] registre = obtenirFila(o.obtenirId());
  		Dada propReg = registre[index];
  		String propValue = propReg.obtenirValor().toString();
  		return (RESIDUAL.equalsIgnoreCase(propValue)); 
    } : */
	boolean isResidual() {
  		return (RESIDUAL.equalsIgnoreCase(valorDivisio));
    }
	    
}

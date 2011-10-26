package jklass.nucli;

/**
 * Classe que es correspom amb la informació que té una cel·la de la matriu
 *
 * @author Jose I Mateos
 * @version Java-KLASS v2
 */
public class DadaExtesa extends Dada {

  private String valorExtes;
  private String freqExtesa;

  private int fin=0;
  private int finFreq=0;

   /**
  * Contructor que posar com a valor de la dada l'objecte que es passa
  * @param o valor de l'objecte
  */


  DadaExtesa(Object o) {
    super(o);
  }

  /**
  * Inicialitza la dadaextesa elimininat el primer parentesis
   */

 public void inicialitzar(){

    valorExtes=this.categ().substring(1);
  }

  /**
  * Indica si s'ha arribat al final de la dada
  * @return cert si estem al final de la dada i fals sino
   */

  public boolean noFin(){

    return (fin<valorExtes.indexOf("))"));
  }

  /**
  * Extreu la la modalitat de la dada estesa en la que es troba
  * @return el el valor de la modalitat
   */


  public String modalitat(){
   String xi;

   fin=valorExtes.indexOf(",");
   xi=valorExtes.substring(1,fin);
   valorExtes=valorExtes.substring(fin+1);

   return xi;
  }

  /**
  * Extreu el valor numeric corresponent a la modalitat de la dada extesa en la que es troba
  * @return el valor numeric
   */


  public String fraccio(){
   String fr;

   fin=valorExtes.indexOf(")");
   fr=valorExtes.substring(0,fin);
   valorExtes=valorExtes.substring(fin+1);

   return fr;
  }

  /**
  *  Inicialitza la dadaextesa elimininat el primer parentesis
  *  @param frequ conte totes les freqüencies de la dada extesa
  */

  public void freq(String frequ){

   freqExtesa=frequ.substring(1);;
  }

  /**
  * Extreu el valor numeric corresponent al numero d'objectes amb la modalitat de la dada extesa en la que es troba
  * @return el valor de la  numeric corresponent al numero d'objectes amb la modalitat de la dada extesa
  */

  public String freqModal(){
    String freqMol;

	finFreq=freqExtesa.indexOf(",");
  	if (finFreq==-1){
	  	finFreq=1;
	}
	freqMol= freqExtesa.substring(0,finFreq);
	freqExtesa= freqExtesa.substring(finFreq+1);

	return freqMol;
  }


}

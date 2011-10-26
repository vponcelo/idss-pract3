package jklass.iu;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
/**
 * Implementa el model de taula que utilitza la matriu mostrada a la discretitzaci� manual.
 * @author Laia Riera Guerra
 * @version v4
 * Data: 23/06/2007
 */
public class ModelTaula extends AbstractTableModel{
	String[] cap�alera={"M�nim","M�xim","Modalitat"};
	Object[][] files=new Object[100][3];
	float min,max;
	private LinkedList listeners = new LinkedList();
	private LinkedList datos = new LinkedList();
	private int rowMax=100;
	/**
	 * Obt� la cap�alera de la columna <code>columnIndex</code>
	 * @param int, �ndex de la columna
	 * @return String, nom de la cap�alera corresponent a la columna
	 */
	  public String getColumnName(int columnIndex) 
	    {
	        // Devuelve el nombre de cada columna. Este texto aparecer� en la
	        // cabecera de la tabla.
	        switch (columnIndex)
	        {
	            case 0:
	                return "M�nim";
	            case 1:
	                return "M�xim";
	            case 2:
	                return "Modalitat";
	            default:
	                return null;
	        }
	    }
	/***
	 * Indica si la cel.la de la matriu de  coordenades <code>row</code> <code>col</code> �s editable
	 * @param row, �ndex de la fila
	 * @param col, �ndex de la columna
	 * @return true si la cel.la �s editable, false altrament
	 */
	public boolean isCellEditable(int row, int col) {
		if(row <= rowMax){
			if (col == 0) { 
	            return false;
	        } else return true;
		}else return false;       
    }
	/***
	 * Obt� el nombre de columnes de la taula
	 * @return enter, n�mero de columnes
	 */
	public int getColumnCount() {		
		return cap�alera.length;
	}
	/**
	 * Obt� el nombre de files de la taula
	 * @return enter, n�mero de files
	 */
	public int getRowCount() {		
		return files.length;
	}
	/***
	 * Obt� el valor de la fila <code>fila</code> i columna <code>col</code>
	 * @param fila, n�mero de fila
	 * @param col, n�mero de columna
	 * @return el valor de la posici� indicada
	 */
	public Object getValueAt(int fila, int col) {		
		return files[fila][col];
	}
	/**
	 * Insereix el valor <code>value</code> a la fila <code>row</code> i columna <code>col</code>
	 *@param value, valor a inserir
	 *@param row, n�mero de fila
	 *@param col, n�mero de columna
	 *
	 */
	  public void setValueAt(Object value, int row, int col){	
		  	try {
		  		 String s=value.toString();
				  if(s.compareTo("")==0||s==null){}
				  else{
					  if(col==2||col==0){
						  if(col==0){
							  files[row][col] = value;
						      fireTableCellUpdated(row, col); 
						      files[row][2]="I"+row;
							  fireTableCellUpdated(row, 2);	
						  }else{
							  files[row][col] = value;
						      fireTableCellUpdated(row, col); 
						  }				      
					  }else {
						  Float f=(Float)value;
						  if(f.floatValue()==max){
							  files[row][col] = value;
							  fireTableCellUpdated(row, col);							 
							  files[row][2]="I"+row;
							  fireTableCellUpdated(row, 2);	
							  rowMax=row;							  
						  }else if(row==rowMax && f.floatValue()<max){
							  rowMax=100;
							  files[row][col] = value;
							  fireTableCellUpdated(row, col); 
							  files[row+1][0]=value;
							  fireTableCellUpdated(row+1, 0);
							  files[row][2]="I"+row;
							  fireTableCellUpdated(row, 2);
						  }else{
							  files[row][col] = value;
							  fireTableCellUpdated(row, col); 
							  files[row+1][0]=value;
							  fireTableCellUpdated(row+1, 0);
							  files[row][2]="I"+row;
							  fireTableCellUpdated(row, 2);			
						  }							 			  				  
					  }
				  }
				  TableModelEvent evento = new TableModelEvent (this, row, row,col);				      
				  avisaSuscriptores (evento);
				
			} catch (ClassCastException e) {
				e.printStackTrace();			
			}		        
	    }
	  /**
	   * Buida la taula
	   *
	   */
	  public void clear(){
		  files=new Object[100][3];
	  }
	  /**
	   * Obt� el valor m�xim que es pot inserir a la taula
	   * @return float, valor m�xim que est� perm�s inserir a la taula
	   */
	public float getMax() {
		return max;
	}
	/**
	 * Estableix el valor m�xim que es pot inserir a la taula
	 * @param max, valor m�xim de la taula
	 */
	public void setMax(float max) {
		this.max = max;
	}
	/**
	 * Obt� el valor m�nim que es pot inserir a la taula
	 * @return float, valor m�nim perm�s
	 */
	public float getMin() {
		return min;
	}
	/**
	 * Estableix el valor m�nim que es pot inserir a la taula
	 * @param min, valor m�nim perm�s
	 */
	public void setMin(float min) {
		this.min = min;
	}
	/**
	 * Avisa als listeners de la taula de que s'ha produ�t un canvi en el contingut de la mateixa.
	 * @param evento, modificaci� que s'ha efectuat
	 */
	 private void avisaSuscriptores (TableModelEvent evento)
	 {
	        int i;
	        
	        // Bucle para todos los suscriptores en la lista, se llama al metodo
	        // tableChanged() de los mismos, pas�ndole el evento.
	        for (i=0; i<listeners.size(); i++)
	            ((TableModelListener)listeners.get(i)).tableChanged(evento);
	 }
	 /**
	  * Elimina el listener <code>l</code> 
	  */
	 public void removeTableModelListener(TableModelListener l) {
	        // Elimina los suscriptores.
	        listeners.remove(l);
	 }
	 /**
	  * Afegeix el listener <code>l</code> 
	  */
	 public void addTableModelListener(TableModelListener l) {
	        // A�ade el suscriptor a la lista de suscriptores
	        listeners.add (l);
	    }
	 
	 /** 
	     *Retorna la superclasse m�s espec�fica dels Objectes de la taula.
	     * @param columnIndex, l'�ndex de la columna
	     * @return la classe pare comuna als objectes de la taula.
	     *
	     */
	    public Class getColumnClass(int columnIndex) {
	        // Devuelve la clase que hay en cada columna.
	        switch (columnIndex)
	        {
	            case 0:
	                // La columna cero contiene el m�nimo, que es un float
	            	return Float.class;
	            case 1:
	                // La columna uno contiene el maximo, que es un float
	                return Float.class;
	            case 2:
	                // La columna dos contiene la modalidad, que es un string
	                return String.class;
	            default:
	                // Devuelve una clase Object por defecto.
	                return Object.class;
	        }
	    }
	    /**
	     * Obt� els valors num�rics introdu�ts a la columna de m�nims i m�xims a la taula, ordenats ascendentment
	     * @return float[], vector que cont� els valors num�rics introdu�ts
	     * @throws Exception
	     */
	    public float[] obteValors()throws Exception{
	    	float[] f=new float[files.length+1];
	    	float[] resultat;
	    	int n=0;
	    	boolean maxim=false;
	    	for(int i=0;i<files.length && !maxim;i++){
	    		if(files[i][0]!=null){
	    			Float aux=new Float(files[i][0].toString());
	    			f[n]=aux.floatValue();
	    			n++;
	    			aux=new Float(files[i][1].toString());
	    			if(aux.floatValue()==max){
	    				maxim=true;
	    				f[n]=aux.floatValue();
	    				n++;
	    			}
	    		}
	    	}
	    	try {
				if(!maxim)throw new Exception("No s'ha introdu�t el valor m�xim. Encara queden intervals per definir");
				else{
					if(n==0)throw new Exception("No s'ha introdu�t cap valor");
					else{
						resultat=new float[n];
						float min=f[0];
						for(int i=0;i<n;i++){
							if(min>f[i])throw new Exception("Els valors introdu�ts no segueixen un ordre ascendent");
							else min=f[i];
							resultat[i]=f[i];
						}
						 Arrays.sort(resultat);	    		 
					}
				}	    	
				return resultat;
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());				
			}
	    }
	    /**
	     * Obt� el llistat de modalitats introdu�ts a la taula
	     * @return ArrayList que cont� les modalitats introdu�des a la columna de modalitats de la taula
	     * @throws Exception
	     */
	    public ArrayList obteModalitats()throws Exception{
	    	ArrayList f=new ArrayList();    	
	    	boolean maxim=false;
	    	for(int i=0;i<files.length && !maxim;i++){
	    		if(files[i][0]!=null){
	    			f.add(files[i][2].toString());	    		
	    			Float aux=new Float(files[i][1].toString());
	    			if(aux.floatValue()==max)maxim=true;
	    		}
	    	}
	    	if(f.size()==0)throw new Exception("No s'ha introdu�t cap valor");
	    	else if(!maxim)throw new Exception("No s'ha introdu�t el valor m�xim. Encara queden intervals per definir");
	    	else return f;	    		    	
	    }
}

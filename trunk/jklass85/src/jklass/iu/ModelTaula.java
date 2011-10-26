package jklass.iu;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
/**
 * Implementa el model de taula que utilitza la matriu mostrada a la discretització manual.
 * @author Laia Riera Guerra
 * @version v4
 * Data: 23/06/2007
 */
public class ModelTaula extends AbstractTableModel{
	String[] capçalera={"Mínim","Màxim","Modalitat"};
	Object[][] files=new Object[100][3];
	float min,max;
	private LinkedList listeners = new LinkedList();
	private LinkedList datos = new LinkedList();
	private int rowMax=100;
	/**
	 * Obté la capçalera de la columna <code>columnIndex</code>
	 * @param int, índex de la columna
	 * @return String, nom de la capçalera corresponent a la columna
	 */
	  public String getColumnName(int columnIndex) 
	    {
	        // Devuelve el nombre de cada columna. Este texto aparecerá en la
	        // cabecera de la tabla.
	        switch (columnIndex)
	        {
	            case 0:
	                return "Mínim";
	            case 1:
	                return "Màxim";
	            case 2:
	                return "Modalitat";
	            default:
	                return null;
	        }
	    }
	/***
	 * Indica si la cel.la de la matriu de  coordenades <code>row</code> <code>col</code> és editable
	 * @param row, índex de la fila
	 * @param col, índex de la columna
	 * @return true si la cel.la és editable, false altrament
	 */
	public boolean isCellEditable(int row, int col) {
		if(row <= rowMax){
			if (col == 0) { 
	            return false;
	        } else return true;
		}else return false;       
    }
	/***
	 * Obté el nombre de columnes de la taula
	 * @return enter, número de columnes
	 */
	public int getColumnCount() {		
		return capçalera.length;
	}
	/**
	 * Obté el nombre de files de la taula
	 * @return enter, número de files
	 */
	public int getRowCount() {		
		return files.length;
	}
	/***
	 * Obté el valor de la fila <code>fila</code> i columna <code>col</code>
	 * @param fila, número de fila
	 * @param col, número de columna
	 * @return el valor de la posició indicada
	 */
	public Object getValueAt(int fila, int col) {		
		return files[fila][col];
	}
	/**
	 * Insereix el valor <code>value</code> a la fila <code>row</code> i columna <code>col</code>
	 *@param value, valor a inserir
	 *@param row, número de fila
	 *@param col, número de columna
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
	   * Obté el valor màxim que es pot inserir a la taula
	   * @return float, valor màxim que està permés inserir a la taula
	   */
	public float getMax() {
		return max;
	}
	/**
	 * Estableix el valor màxim que es pot inserir a la taula
	 * @param max, valor màxim de la taula
	 */
	public void setMax(float max) {
		this.max = max;
	}
	/**
	 * Obté el valor mínim que es pot inserir a la taula
	 * @return float, valor mínim permés
	 */
	public float getMin() {
		return min;
	}
	/**
	 * Estableix el valor mínim que es pot inserir a la taula
	 * @param min, valor mínim permés
	 */
	public void setMin(float min) {
		this.min = min;
	}
	/**
	 * Avisa als listeners de la taula de que s'ha produït un canvi en el contingut de la mateixa.
	 * @param evento, modificació que s'ha efectuat
	 */
	 private void avisaSuscriptores (TableModelEvent evento)
	 {
	        int i;
	        
	        // Bucle para todos los suscriptores en la lista, se llama al metodo
	        // tableChanged() de los mismos, pasándole el evento.
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
	        // Añade el suscriptor a la lista de suscriptores
	        listeners.add (l);
	    }
	 
	 /** 
	     *Retorna la superclasse més específica dels Objectes de la taula.
	     * @param columnIndex, l'índex de la columna
	     * @return la classe pare comuna als objectes de la taula.
	     *
	     */
	    public Class getColumnClass(int columnIndex) {
	        // Devuelve la clase que hay en cada columna.
	        switch (columnIndex)
	        {
	            case 0:
	                // La columna cero contiene el mínimo, que es un float
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
	     * Obté els valors numèrics introduïts a la columna de mínims i màxims a la taula, ordenats ascendentment
	     * @return float[], vector que conté els valors numèrics introduïts
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
				if(!maxim)throw new Exception("No s'ha introduït el valor màxim. Encara queden intervals per definir");
				else{
					if(n==0)throw new Exception("No s'ha introduït cap valor");
					else{
						resultat=new float[n];
						float min=f[0];
						for(int i=0;i<n;i++){
							if(min>f[i])throw new Exception("Els valors introduïts no segueixen un ordre ascendent");
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
	     * Obté el llistat de modalitats introduïts a la taula
	     * @return ArrayList que conté les modalitats introduïdes a la columna de modalitats de la taula
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
	    	if(f.size()==0)throw new Exception("No s'ha introduït cap valor");
	    	else if(!maxim)throw new Exception("No s'ha introduït el valor màxim. Encara queden intervals per definir");
	    	else return f;	    		    	
	    }
}

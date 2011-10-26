package jklass.iu;
 import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;
import java.util.ArrayList;

   import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*; 
 



	

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import jklass.nucli.GestorKlass;
import jklass.nucli.PropNumerica;
import jklass.iu.ColorColumnRenderer;


/** Classe que dibuixa el submenú per visualitzar la matriu de dades
* @author Laia Riera Guerra
* @version v.4 22/6/07
*/

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class PanelMatriu extends javax.swing.JPanel {
	private static Logger logger = 
Logger.getLogger(PanelMatriu.class.getName());
	private JPanel jPanelBotons;
	private JTable jTableMatriuDades;
		private JTable jTableMatriuDades2;
	private JButton jButtonTanca;
	private JScrollPane jScrollPaneMatriu = new JScrollPane();
	private JCheckBox soloactivas = new JCheckBox();
	private JTextField  txtField = new JTextField("", 12);
   private JTextField  txtField2 = new JTextField("", 12);

		  
 
private final static String CONDICIONADA =  "Només variables actives";
private final static String CONDICIONA =  "Activa";
private final static String CONDI =  "Desactivada";
	
	private JRadioButton jRadioButtonLLExplicita;
ArrayList diff=new ArrayList();
ArrayList diffi=new ArrayList();
int [] indicesnoact2 = new int [1000];
	FrPrincipal frPare;
	GestorKlass gestor;
	Object[][] result;
	String[] llProps;
	String[] llObjectes;
	DefaultListModel listR = new DefaultListModel();
	DefaultListModel listCol = new DefaultListModel();
	JList capça;
	JList columncapça;
	TableModel jTableMatriuDadesModel = new DefaultTableModel();
  
	/**
	 * Constructor
	 * @param fr, finestra pare
	 * @param gk, GestorKlass
	 */
	public PanelMatriu(FrPrincipal fr,GestorKlass gk) {
		super();
		frPare=fr;
		gestor=gk;
		ArrayList activas=new ArrayList();		  


	llProps = gestor.obtenirLlistaIDsPropsSenseDistincio();
	

		llObjectes=gestor.obtenirLlistaIDsObjs();


		result=gestor.obtenirMatriuDades();
		//Construir tooltiptext
		jTableMatriuDades = new JTable(result,llProps);

		for(int i=0;i<llObjectes.length;i++)listCol.insertElementAt(llObjectes[i],i);
		capça = new JList(listCol);
		columncapça=new JList(listCol);
		jScrollPaneMatriu.setRowHeaderView(capça);
		capça.setCellRenderer(new RowHeaderRenderer (jTableMatriuDades));
		capça.setFixedCellHeight(jTableMatriuDades.getRowHeight());
		jScrollPaneMatriu.setColumnHeaderView(columncapça);
		columncapça.setCellRenderer(new RowHeaderRenderer (jTableMatriuDades));
	//	jTableMatriuDades.setBackground(Color.pink);

		jTableMatriuDades.setCellSelectionEnabled(false);
		jTableMatriuDades.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTableMatriuDades.setEnabled(false);

		 try{
		    	initGUI();
		    }catch(Exception ex){
		    	ex.printStackTrace();
		    }
	}
	/**
	 * Dibuixa el menú
	 	 
	 *
	 */
	 	 
	 
	private void initGUI() {
	ArrayList acti=new ArrayList();
	 




		try {
		////////////pintar la inicial
		 if (gestor.LenActiveProps()>0)  {
	
		   if (gestor.estamarcado()) soloactivas.setSelected(true);
		 	diffi=gestor.diferencia();
						
 String [] noactivasi = new String [diffi.size()];
			 
      	 for (int r = 0; r < diffi.size(); r++) {
            	  noactivasi[r]=(String)diffi.get(r);
            
        }
							  
 
 int [] indicesnoacti = new int [diffi.size()];
 for (int s = 0; s < noactivasi.length; s++) {
            indicesnoacti[s]= gestor.obtenirIdProp(noactivasi[s]);         
         }
			
	//////////defino aca indicesnoact2	
	 for (int s = 0; s < noactivasi.length; s++) {
            indicesnoact2[s]= gestor.obtenirIdProp(noactivasi[s]);         
         }

		
			
			//////////////////////
if (gestor.estamarcado()){

		 	jTableMatriuDades.setBackground(Color.WHITE);
				Color Pigment1;
		Pigment1 = new Color (219, 219, 219);
			TableColumn tm;
			 for (int d = 0; d < indicesnoacti.length; d++) {
            //System.out.println( " ACtivas true  " +indices[d]);
				tm = jTableMatriuDades.getColumnModel().getColumn(indicesnoacti[d]);
				tm.setCellRenderer(new ColorColumnRenderer(Color.white, Color.black));
       	 tm = jTableMatriuDades.getColumnModel().getColumn(indicesnoacti[d]);
			 tm.setCellRenderer(new ColorColumnRenderer(Color.white, Color.black));
        }

	 	 for (int d = 0; d < indicesnoacti.length; d++) {
           // System.out.println( " ACtivas true  " +indicesnoacti[d]);
				tm = jTableMatriuDades.getColumnModel().getColumn(indicesnoacti[d]);
				tm.setCellRenderer(new ColorColumnRenderer(Pigment1, Color.gray));
       	 tm = jTableMatriuDades.getColumnModel().getColumn(indicesnoacti[d]);
			 tm.setCellRenderer(new ColorColumnRenderer(Pigment1, Color.gray));
         }
 

		  frPare.repaint();
	
	}// fin del if gestor.marcado	 
		 /////////////// fin de pintar la inicial
		 
		}	 
		 
			//frPare.setSize(700,610);
			frPare.setSize(700,670);
			frPare.centrar();
			this.setPreferredSize(new java.awt.Dimension(672, 505));
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.0, 0.1};
			thisLayout.rowHeights = new int[] {418, 7};
			thisLayout.columnWeights = new double[] {0.1};
			thisLayout.columnWidths = new int[] {7};
			this.setLayout(thisLayout);
			
			// JORDI >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	         // Se obtienen las dimensiones en pixels de la pantalla.         
	         Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	         
	         // Se obtienen las dimensiones en pixels de la ventana.
	         Dimension ventana = frPare.getSize();
	         
	         // Un calculo para situar la ventana en el centro de la pantalla.
	         frPare.setLocation((pantalla.width - ventana.width) / 2,(pantalla.height - ventana.height) / 2);
	         // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			
			this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED), 
"Representació matricial de les dades", TitledBorder.LEADING, 
TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
			{
				jPanelBotons = new JPanel();
				this.add(jPanelBotons, new GridBagConstraints(0, 1, 1, 2, 0.0, 0.0, 
GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 
0, 0));
				jPanelBotons.setLayout(null);
				{
				
			// jRadioButtonLLExplicita = new JRadioButton();
				// jRadioButtonLLExplicita.setText(" actives");
        //  jRadioButtonLLExplicita.setBounds(294, 227, 77, 28);
		  //	private JCheckBox soloactivas = new JCheckBox();
		  JLabel lEspaiactivas = new JLabel();
		  
		   //lEspaiactivas.setFont(new Font("Dialog", 2, 13));
        // lEspaiactivas.setText("Només variables actives");
		  lEspaiactivas.setText(CONDICIONADA);
  lEspaiactivas.setBounds(26, 5, 167, 24);
		  
			soloactivas.setBounds(0, 2, 24, 28);
		  	jPanelBotons.add(soloactivas);
        jPanelBotons.add(lEspaiactivas);
		  
txtField.setBounds(4, 36, 44, 18); 
txtField.setEnabled(false);
txtField.setBackground(Color.WHITE);
txtField.setBorder( new LineBorder(Color.GRAY, 1) ); 

jPanelBotons.add(txtField);
 JLabel lEspaiactiv = new JLabel();
 lEspaiactiv.setText(CONDICIONA);
  lEspaiactiv.setBounds(56, 34, 167, 24);
 jPanelBotons.add(lEspaiactiv);
 
 //////el segundo rectangulo
 	Color Pig;
 	Pig = new Color (219, 219, 219);
txtField2.setBounds(4, 60, 44, 18); 
txtField2.setEnabled(false);
txtField2.setBackground(Pig);
txtField2.setBorder( new LineBorder(Color.GRAY, 1) ); 
jPanelBotons.add(txtField2);

jPanelBotons.add(txtField2);
 JLabel lEspaiacti = new JLabel();
 lEspaiacti.setText(CONDI);
  lEspaiacti.setBounds(56, 58, 167, 24);
 jPanelBotons.add(lEspaiacti);


		  
		   soloactivas.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     gestionarActivas();
                  }
               });

		  
		  
					jButtonTanca = new JButton();
					jPanelBotons.add(jButtonTanca);
					jButtonTanca.setText("Tanca");
					jButtonTanca.setBounds(294, 80, 77, 28);
					jButtonTanca.addActionListener(new java.awt.event.ActionListener() {
					      public void actionPerformed(ActionEvent e) {
					    	  jButtonTanca_actionPerformed(e);
					      }
					});
				}
			}
			
				// jRadioButtonLLExplicita = new JRadioButton();
				// jRadioButtonLLExplicita.setText("Només variables actives");
            //  jRadioButtonLLExplicita.setBounds(294, 227, 77, 28);
			
			
			{

				this.add(jScrollPaneMatriu, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 
GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 
0, 0));
				jScrollPaneMatriu.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), 
"Matriu de dades", TitledBorder.LEADING, TitledBorder.TOP, new 
java.awt.Font("Dialog",0,10)));
				{
					/*TableModel jTableMatriuDadesModel = new DefaultTableModel();*/

					/*jTableMatriuDades = new JTable();*/
					jScrollPaneMatriu.setViewportView(jTableMatriuDades);
					/*jTableMatriuDades.setModel(jTableMatriuDadesModel);*/
				}
				
				
			}
			

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Tanca la finestra
	 * @param e, event que detecta que s'ha premut el botó <code>Tanca</code>
	 */
	protected void jButtonTanca_actionPerformed(ActionEvent e) {
		frPare.remove(this);
		 frPare.validate();
		 frPare.repaint();

	}
	
	
	
	
	
 private void gestionarActivas() {
 	ArrayList activ=new ArrayList();	
	int indice=0;
//	ArrayList diff=new ArrayList();	
//	int [] indicesnoact = new int [diff.size()];
 
 if (soloactivas.isSelected()) {
 gestor.activasel();}
 else{ 
 gestor.activasdesel();}
 
 
 
 if (gestor.LenActiveProps()>0){

 			  
		activ= gestor.matriusCarregades[gestor.obteniractual()].obtenirActivas();
//			 for ( int x = 0; x< 1; x++){
// System.out.println( "Quien hay en activeProps="+ activ.get(x));
//};


 String [] arractivas = new String [activ.size()];
 int [] indices = new int [activ.size()];
 
 // int [] indicesn = new int [activ.size()];

 

 //alejandro 
         for (int p = 0; p < activ.size(); p++) {
                    arractivas[p]=(String)activ.get(p);
         
         }
			
 for (int s = 0; s < arractivas.length; s++) {
            indices[s]= gestor.obtenirIdProp(arractivas[s]);         
         }
						
 indice = gestor.obtenirIdProp(arractivas[0]);
 

 

if(soloactivas.isSelected()){
gestor.marcar();
	diff=gestor.diferencia();
					
////////////aca paso la diferencia a un array
 String [] noactivas = new String [diff.size()];
		
	 
      	 for (int r = 0; r < diff.size(); r++) {
            	  noactivas[r]=(String)diff.get(r);
       // System.out.println( "a ver noactivas????"+  noactivas[r]);      
        }
					
	/////////////fin de pasar la diferencia a un array			  
 
 int [] indicesnoact = new int [diff.size()];
 for (int s = 0; s < noactivas.length; s++) {
            indicesnoact[s]= gestor.obtenirIdProp(noactivas[s]);         
         }

 ///////////////////////////////////////ACA LES PINTO EL FONDO
 		Color Pigment;
		Pigment = new Color (219, 219, 219);
 

			jTableMatriuDades.setBackground (Pigment);
			TableColumn tm;
			 for (int d = 0; d < indices.length; d++) {
            //System.out.println( " ACtivas true  " +indices[d]);
				tm = jTableMatriuDades.getColumnModel().getColumn(indices[d]);
				tm.setCellRenderer(new ColorColumnRenderer(Color.white, Color.black));
       	 tm = jTableMatriuDades.getColumnModel().getColumn(indices[d]);
			 tm.setCellRenderer(new ColorColumnRenderer(Color.white, Color.black));
         }

	 	 for (int d = 0; d < indicesnoact.length; d++) {
           // System.out.println( " ACtivas true  " +indicesnoact[d]);
				tm = jTableMatriuDades.getColumnModel().getColumn(indicesnoact[d]);
				tm.setCellRenderer(new ColorColumnRenderer(Pigment, Color.gray));
       	 tm = jTableMatriuDades.getColumnModel().getColumn(indicesnoact[d]);
			 tm.setCellRenderer(new ColorColumnRenderer(Pigment, Color.gray));
         }

			
			
			 frPare.repaint();

 //for (int r = 0; r < indices.length; r++) {
   //         System.out.println( " ACtivas true  " +indices[r]);       
     //    }

         }
			
////////////////////////////aca empieza el else y tengo que definir de nuevo
         else{
			gestor.desmarcar();				
			// System.out.println( "No entra aca cuando desmarcoooo???");
			//int [] indicesnoact2 = new int [diff.size()];
			String [] noactivas2 = new String [diff.size()];
		
	      	 for (int r = 0; r < diff.size(); r++) {
            	  noactivas2[r]=(String)diff.get(r);
       // System.out.println( "a ver noactivas2222????"+  noactivas2[r]);      
        }
		  
//		  int [] indicesnoact2 = new int [diff.size()];
// for (int s = 0; s < noactivas2.length; s++) {
  //          indicesnoact2[s]= gestor.obtenirIdProp(noactivas2[s]);         
    //     }


			
       //  System.out.println( "activas false  "  );
//ale esto anda bien			
		//	jTableMatriuDades.setBackground(Color.WHITE);
		 //  TableColumn tm = jTableMatriuDades.getColumnModel().getColumn(indice);
		//	tm.setCellRenderer(new ColorColumnRenderer(Color.white, Color.black));
		//	tm = jTableMatriuDades.getColumnModel().getColumn(indice);
		//	tm.setCellRenderer(new ColorColumnRenderer(Color.white, Color.black));

/// ale fin del esto anda bien


/////////////////////////////ACA LAS DEJO NEGRAAAAAAAAAAAAAAASSS/////////

	jTableMatriuDades.setBackground(Color.WHITE);
			TableColumn tm;
			 for (int d = 0; d < indices.length; d++) {
          //  System.out.println( " ACtivas true  " +indices[d]);
				tm = jTableMatriuDades.getColumnModel().getColumn(indices[d]);
				tm.setCellRenderer(new ColorColumnRenderer(Color.white, Color.black));
       	 tm = jTableMatriuDades.getColumnModel().getColumn(indices[d]);
			 tm.setCellRenderer(new ColorColumnRenderer(Color.white, Color.black));
         }
		
		////////agrego para no activas	
				
		 for (int d = 0; d < indicesnoact2.length; d++) {
          //  System.out.println( " ACtivas true  " +indicesnoact2[d]);
				tm = jTableMatriuDades.getColumnModel().getColumn(indicesnoact2[d]);
				tm.setCellRenderer(new ColorColumnRenderer(Color.white, Color.black));
       	 tm = jTableMatriuDades.getColumnModel().getColumn(indicesnoact2[d]);
			 tm.setCellRenderer(new ColorColumnRenderer(Color.white, Color.black));
         }
	
  ///////////////fin para no activas

						
		 	frPare.repaint();

         
         }
      
 }
  		         		       
			
}//end del if que esta arriba de todo	

}


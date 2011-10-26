package jklass.iu;
import jklass.util.AnchorConstraint;
import jklass.util.AnchorLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import jklass.nucli.BaseConeixement;
import jklass.nucli.ExpressioBooleana;
import jklass.nucli.GestorKlass;
import jklass.nucli.LlistaBC;
import jklass.nucli.PropNumerica;
import jklass.nucli.Propietat;
import jklass.nucli.ReglaProb;

/** Classe que dibuixa el submenú per editar bases de coneixement
*
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
public class PanelEditorRegles extends javax.swing.JPanel {

	{
		//Set Look & Feel
		/*try {
			  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
		} catch(Exception e) {
			e.printStackTrace();
		}*/
	}
	private static Logger logger=Logger.getLogger(PanelEditorRegles.class.getName());
	DefaultComboBoxModel jListPropietatsModel=new DefaultComboBoxModel();
	DefaultComboBoxModel jListReglesModel; 
	DefaultComboBoxModel jListBCModel;
	private JPanel jPanel1;
	private JScrollPane jScrollPaneRegles;
	private JButton jButtonConsultar;
	private JList jListPropietats;
	private JScrollPane jScrollPanePropietats;
	private JPanel jPanelProps;
	private JTextField jTextFieldNomRegla=new JTextField();
	private JPanel jPanelNomRegla;
	private JPanel jPanelPropietats;
	private JPanel jPanelEditorRegles;
	private JButton jButtonEliminarRegla;
	private JButton jButtonAfegirRegla;
	private JButton jButtonEliminarBC;
	private JLabel jLabelProbabilitat;
	private JButton jButtonCrearBC;
	private JPanel jPanelBotonsBC;
	private JButton jButtonMajorIgual;
	private JTextArea jTextAreaExpressio;
	private JScrollPane jScrollPaneExpressio;
	private JButton jButtonPertany;
	private JButton jButtonTancarClaudator;
	private JButton jButtonObrirClaudator;
	private JButton jButtonTancarParentesis;
	private JButton jButtonOR;
	private JButton jButtonNOT;
	private JButton jButtonAND;
	private JButton jButtonClear;
	private JLabel jLabelConsequent;
	private JTextField 	jTextFieldConsequent = new JTextField();
	private JPanel jPanelConsequent;
	private JButton jButtonObrirParentesis;
	private JButton jButtonDiferent;
	private JButton jButtonMenorIgual;
	private JButton jButtonIgual;
	private JButton jButtonMajor;
	private JButton jButtonMenor;
	private JPanel jPanelBotonsCalculadora;
	private JPanel jPanelExpressio;
	private JLabel jLabel1;
	private JButton jButtonDreta;
	private JPanel jPanelBotoDreta;
	private JButton jButtonModificarRegla;
	private JPanel jPanelBotons;
	private JList jListRegles;
	private JList jListBC;
	private JScrollPane jScrollPaneBC;
	private JTextField jTextFieldNomBC= new JTextField();
	private JPanel jPanelNomBC;
	private JButton jButtonCancela;
	private JPanel jPanelCancela;
	private JButton jButtonOK;
	private JPanel jPanelOK;
	private JPanel jPanel2;
	private JPanel jPanelBC;
	FrPrincipal frPare;
	GestorKlass gestor;
	private boolean IS_OK;//ens indica si s'han guardat els canvis
	BaseConeixement bc;//Base de coneixement amb la que es treballa( copia )
	MouseListener mouseListener = new MouseAdapter() {
		/**
		 * Selecciona la base de coneixement seleccionada fent doble clic sobre el seu nom. 
		 * Si s'havien efectuat canvis a la base de coneixement anteriorment seleccionada i no s'havien guardat, apareix una finestra
		 * preguntant si es desitgen guardar els canvis
		 * @param e, event que detecta que s'ha fet doble clic sobre el nom d'una base de coneixement de la llista de Bases de Coneixement
		 */
	     public void mouseClicked(MouseEvent e) {
	         if (e.getClickCount() == 2) {
	        	 int index = jListBC.locationToIndex(e.getPoint());	            
	        	 if(!IS_OK){
	        		 int answer=JOptionPane.showConfirmDialog(frPare,"Desitja guardar els canvis efectuats a la Base de Coneixement?","Desitja guardar els canvis efectuats a la Base de Coneixement?",JOptionPane.YES_NO_CANCEL_OPTION);
	        		 if (answer == JOptionPane.YES_OPTION) {
	        				gestor.guardarCanvis(bc);
	        				 Object arg=jListBC.getSelectedValue();
	        	             jTextFieldNomBC.setText((String)arg);	             
	        	             bc=copiarBC((String) arg);	        	            
	        	             String[] idRegles=gestor.obtenirNomsRegles(bc);	        	           
	        	             jListReglesModel=new DefaultComboBoxModel(idRegles);
	        	             jListRegles.setModel(jListReglesModel);	
	        	             
	        	             jTextFieldNomRegla.setText(bc.seguentNomRegla());
	        	             jTextAreaExpressio = new JTextArea();
	        	             jScrollPaneExpressio.setViewportView(jTextAreaExpressio);
	        	             //falta comprovar que la BC anterior ha estat guardada.Si no, pop up
	        	             jTextFieldConsequent.setText("T");      
	        	             IS_OK=true;
	        		    } else if (answer == JOptionPane.NO_OPTION) {
	        		    	 Object arg=jListBC.getSelectedValue();
	        	             jTextFieldNomBC.setText((String)arg);	             
	        	             bc=copiarBC((String) arg);	        	          
	        	             String[] idRegles=gestor.obtenirNomsRegles(bc);	        	            
	        	             jListReglesModel=new DefaultComboBoxModel(idRegles);
	        	             jListRegles.setModel(jListReglesModel);	                
	        	             jTextFieldNomRegla.setText(bc.seguentNomRegla());
	        	             jTextAreaExpressio = new JTextArea();
	        	             jScrollPaneExpressio.setViewportView(jTextAreaExpressio);
	        	             //falta comprovar que la BC anterior ha estat guardada.Si no, pop up
	        	             jTextFieldConsequent.setText("T");      
	        	             IS_OK=true;
	        		    } 
	        	 }else{
	        		 Object arg=jListBC.getSelectedValue();
		             jTextFieldNomBC.setText((String)arg);	             
		             bc=copiarBC((String) arg);		           
		             String[] idRegles=gestor.obtenirNomsRegles(bc);		           
		             jListReglesModel=new DefaultComboBoxModel(idRegles);
		             jListRegles.setModel(jListReglesModel);	                
		             jTextFieldNomRegla.setText(bc.seguentNomRegla());
		             jTextAreaExpressio = new JTextArea();
		             jScrollPaneExpressio.setViewportView(jTextAreaExpressio);
		             //falta comprovar que la BC anterior ha estat guardada.Si no, pop up
		             jTextFieldConsequent.setText("T");      
		             IS_OK=true;
	        	 }
        		 
	          }
	     }
	 };
	 
	 MouseListener mouseRegla =new MouseAdapter(){
		 /**
		  * Selecciona la regla seleccionada fent doble clic sobre el seu nom en el llista de regles
		  * @param e, event que detecta que s'ha fet doble clic sobre el nom d'una regla del llistat de regles.
		  * 
		  */
		   public void mouseClicked(MouseEvent e) {
		         if (e.getClickCount() == 2) {
		          	int index = jListRegles.locationToIndex(e.getPoint());
		        	Object ob=jListRegles.getSelectedValue();
		        	jTextFieldNomRegla.setText((String)ob);
		        	try{
			        	if ( bc.obtenirRegla((String)ob) instanceof ReglaProb ){
			        		ReglaProb r = (ReglaProb)bc.obtenirRegla((String)ob);
			        		NumberFormat f = NumberFormat.getInstance();
			        		f.setMaximumFractionDigits(2);
			        		jLabelProbabilitat.setText("Probabilitat: " + f.format(r.getProb()));
			        	}
			        	else{
			        		jLabelProbabilitat.setText("");
			        	}
		        	}catch(Exception ex){
		        		ex.printStackTrace();
		        		frPare.actualitzarBarraEstat(ex.getMessage(), true);
		        	}
		     		/*String nomBC=jTextFieldNomBC.getText();*/
		     		try{
		     			String sregla=gestor.escriureRegla((String)ob,bc);		     			
		     			jTextAreaExpressio.setText(sregla);
		     			jScrollPaneExpressio.setViewportView(jTextAreaExpressio);
		     			String sconsequent=gestor.obtenirConsequent((String)ob,bc);
		     			jTextFieldConsequent.setText(sconsequent);		     			 
		     		}catch(Exception ex) {
		     			ex.printStackTrace();
		     			 frPare.actualitzarBarraEstat(ex.getMessage(),true);
		     		}
		         }
		   }
	 };

	/**
	 * Constructor
	 * @param fr, finestra pare
	 * @param gk, GestorKlass
	 */
	public PanelEditorRegles(FrPrincipal fr,GestorKlass gk) {
		super();
		frPare = fr;
		gestor = gk;		
		String[] idBC=gestor.obtenirLlistaNomsBC();		
		jTextFieldConsequent.setText("T");
		this.IS_OK=true;//encara no s'han produït canvis
		if(idBC.length==0){//no hi ha bases de coneixement creades
			bc=null;
			jListBCModel=new DefaultComboBoxModel();
			jListReglesModel=new DefaultComboBoxModel();
		}else{
			jListBCModel=new DefaultComboBoxModel(idBC);
			bc=copiarBC(idBC[0]);			
			String sBC=idBC[0];
			String novaId=bc.seguentNomRegla();            
	        jTextFieldNomRegla.setText(novaId);			
			jTextFieldNomBC.setText(idBC[0]);
			String[] idRegles=gestor.obtenirNomsRegles(bc);
			if(idRegles.length==0)jListReglesModel=new DefaultComboBoxModel();
			else jListReglesModel=new DefaultComboBoxModel(idRegles);
		}		
		String[][] llProps;
	    int lon;
		 llProps = gestor.obtenirLlistaIDsProps();
		    lon = llProps[0].length;
		    for (int i = 0; i < lon; i++) {
		    	jListPropietatsModel.insertElementAt(llProps[0][i], i);
		    }
		    lon = llProps[1].length;
		    for (int i = 0; i < lon; i++) {
		    	jListPropietatsModel.insertElementAt(llProps[1][i], i);
		    }
		    lon = llProps[2].length;
		    for (int i = 0; i < lon; i++) {
		    	jListPropietatsModel.insertElementAt(llProps[2][i], i);
		    }
		    lon = llProps[3].length;
		    for (int i = 0; i < lon; i++) {
		    	jListPropietatsModel.insertElementAt(llProps[3][i], i);
		    }
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
		try {
			frPare.setSize(1010,565);
			frPare.centrar();
			this.setPreferredSize(new java.awt.Dimension(994, 490));
			this.setLayout(null);
			this.setBorder(BorderFactory.createTitledBorder(null, "Editor de Bases de Coneixement", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10), new java.awt.Color(40,106,196)));
			{
				jPanel1 = new JPanel();
				GridLayout jPanel1Layout = new GridLayout(1, 1);
				jPanel1Layout.setHgap(5);
				jPanel1Layout.setVgap(5);
				jPanel1Layout.setColumns(1);
				this.add(jPanel1);
				jPanel1.setLayout(jPanel1Layout);
				jPanel1.setBounds(0, 0, 0, 0);
			}
			{
				jPanelBC = new JPanel();
				GridBagLayout jPanelBCLayout = new GridBagLayout();
				jPanelBCLayout.rowWeights = new double[] {0.0, 0.0, 0.1};
				jPanelBCLayout.rowHeights = new int[] {55, 73, 7};
				jPanelBCLayout.columnWeights = new double[] {0.1};
				jPanelBCLayout.columnWidths = new int[] {7};
				jPanelBC.setLayout(jPanelBCLayout);
				FlowLayout jPanel2Layout = new FlowLayout();
				this.add(jPanelBC);
				jPanelBC.setLayout(jPanelBCLayout);
				jPanelBC.setBorder(BorderFactory.createTitledBorder(null, "Base de Coneixement", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10), new java.awt.Color(40,106,196)));
				jPanelBC.setBounds(14, 21, 168, 427);
				{
					jPanelNomBC = new JPanel();
					jPanelBC.add(jPanelNomBC, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelNomBC.setPreferredSize(new java.awt.Dimension(155, 48));
					jPanelNomBC.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Nom de BC", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
					jPanelNomBC.setFont(new java.awt.Font("Dialog",0,10));
					{						
						jPanelNomBC.add(jTextFieldNomBC);
						jTextFieldNomBC.setPreferredSize(new java.awt.Dimension(144, 21));
					}
				}
				{
					jScrollPaneBC = new JScrollPane();
					jScrollPaneBC.setAutoscrolls(true);
					jPanelBC.add(jScrollPaneBC, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jScrollPaneBC.setPreferredSize(new java.awt.Dimension(158, 299));
					jScrollPaneBC.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Llistat BC's", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
					jScrollPaneBC.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					jScrollPaneBC.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
					jScrollPaneBC.setWheelScrollingEnabled(true);
					{					
						jListBC = new JList();
						jScrollPaneBC.setViewportView(jListBC);
						jListBC.setModel(jListBCModel);
						jListBC.setPreferredSize(new java.awt.Dimension(500,500));
						jListBC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						jListBC.addMouseListener(mouseListener);
					}
				}
				{
					jPanelBotonsBC = new JPanel();
					jPanelBC.add(jPanelBotonsBC, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelBotonsBC.setLayout(null);
					{
						jButtonCrearBC = new JButton();
						jPanelBotonsBC.add(jButtonCrearBC);
						jButtonCrearBC.setText("Crear BC");
						jButtonCrearBC.setBounds(28, 7, 98, 28);
						jButtonCrearBC.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
						jButtonCrearBC.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e){
						    	  jButtonCrearBC_actionPerformed(e);
						      }
						});
					}
					{
						jButtonEliminarBC = new JButton();
						jPanelBotonsBC.add(jButtonEliminarBC);
						jButtonEliminarBC.setText("Eliminar BC");
						jButtonEliminarBC.setBounds(28, 42, 98, 28);
						jButtonEliminarBC.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
						jButtonEliminarBC.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e){
						    	  jButtonEliminarBC_actionPerformed(e);
						      }
						});
					}
				}
			}
			{
				jPanel2 = new JPanel();
				GridBagLayout jPanel2Layout = new GridBagLayout();
				jPanel2Layout.rowWeights = new double[] {0.0};
				jPanel2Layout.rowHeights = new int[] {407};
				jPanel2Layout.columnWeights = new double[] {0.0, 0.0, 0.1};
				jPanel2Layout.columnWidths = new int[] {160, 87, 7};
				jPanel2.setLayout(jPanel2Layout);
				this.add(jPanel2);
				jPanel2.setLayout(jPanel2Layout);
				jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Regles", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10), new java.awt.Color(40,106,196)));
				jPanel2.setBounds(189, 21, 798, 427);
				{
					jScrollPaneRegles = new JScrollPane();
					jScrollPaneRegles.setAutoscrolls(true);
					jPanel2.add(jScrollPaneRegles, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jScrollPaneRegles.setPreferredSize(new java.awt.Dimension(146, 385));
					jScrollPaneRegles.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "LListat Regles", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
					jScrollPaneRegles.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					jScrollPaneRegles.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
					jScrollPaneRegles.setWheelScrollingEnabled(true);
					{						
						jListRegles = new JList();
						jScrollPaneRegles.setViewportView(jListRegles);
						jListRegles.setModel(jListReglesModel);
						jListRegles.setPreferredSize(new java.awt.Dimension(500,500));
						jListRegles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						jListRegles.addMouseListener(mouseRegla);
					}
				}
				{
					jPanelBotons = new JPanel();
					jPanelBotons.setLayout(null);
					jPanel2.add(jPanelBotons, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelBotons.setPreferredSize(new java.awt.Dimension(77, 196));
					{
						jButtonModificarRegla = new JButton();
						jPanelBotons.add(jButtonModificarRegla);
						jButtonModificarRegla.setText("Modificar \nRegla");
						jButtonModificarRegla.setBounds(7, 154, 77, 49);
						jButtonModificarRegla.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
						jButtonModificarRegla.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e){
						    	  jButtonModificarRegla_actionPerformed(e);
						      }
						});
					}
					{
						jButtonAfegirRegla = new JButton();
						jPanelBotons.add(jButtonAfegirRegla);
						jButtonAfegirRegla.setBounds(7, 91, 77, 49);
						jButtonAfegirRegla.setText("Afegir Regla");
						jButtonAfegirRegla.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
						jButtonAfegirRegla.setFont(new java.awt.Font("Tahoma",0,11));
						jButtonAfegirRegla.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e) {
						    	  jButtonAfegirRegla_actionPerformed(e);
						      }
						});
					}
					{
						jButtonEliminarRegla = new JButton();
						jPanelBotons.add(jButtonEliminarRegla);
						jButtonEliminarRegla.setText("Eliminar \nRegla");
						jButtonEliminarRegla.setBounds(7, 217, 77, 49);
						jButtonEliminarRegla.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
						jButtonEliminarRegla.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e) {
						    	  jButtonEliminarRegla_actionPerformed(e);
						      }
						});
					}
				}
				{
					jPanelEditorRegles = new JPanel();
					GridBagLayout jPanelEditorReglesLayout = new GridBagLayout();
					jPanelEditorReglesLayout.rowWeights = new double[] {0.1};
					jPanelEditorReglesLayout.rowHeights = new int[] {7};
					jPanelEditorReglesLayout.columnWeights = new double[] {0.0, 0.0, 0.1};
					jPanelEditorReglesLayout.columnWidths = new int[] {150, 70, 7};
					jPanelEditorRegles.setLayout(jPanelEditorReglesLayout);
					jPanel2.add(jPanelEditorRegles, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						jPanelPropietats = new JPanel();
						GridBagLayout jPanelPropietatsLayout = new GridBagLayout();
						jPanelPropietatsLayout.rowWeights = new double[] {0.0, 0.1};
						jPanelPropietatsLayout.rowHeights = new int[] {48, 7};
						jPanelPropietatsLayout.columnWeights = new double[] {0.1};
						jPanelPropietatsLayout.columnWidths = new int[] {7};
						jPanelPropietats.setLayout(jPanelPropietatsLayout);
						jPanelEditorRegles.add(jPanelPropietats, new GridBagConstraints(-1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPanelPropietats.setPreferredSize(new java.awt.Dimension(150, 414));
						{
							jPanelNomRegla = new JPanel();
							jPanelPropietats.add(
								jPanelNomRegla,
								new GridBagConstraints(
									0,
									0,
									1,
									1,
									0.0,
									0.0,
									GridBagConstraints.CENTER,
									GridBagConstraints.NONE,
									new Insets(0, 0, 0, 0),
									0,
									0));
							jPanelNomRegla.setPreferredSize(new java.awt.Dimension(138, 50));
							jPanelNomRegla.setEnabled(false);
							jPanelNomRegla.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Nom Regla", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
							{								
								jPanelNomRegla.add(jTextFieldNomRegla);
								jTextFieldNomRegla.setPreferredSize(new java.awt.Dimension(124, 23));
							}
						}
						{
							jPanelProps = new JPanel();
							GridBagLayout jPanelPropsLayout = new GridBagLayout();
							jPanelPropsLayout.rowWeights = new double[] {0.0, 0.1};
							jPanelPropsLayout.rowHeights = new int[] {283, 7};
							jPanelPropsLayout.columnWeights = new double[] {0.1};
							jPanelPropsLayout.columnWidths = new int[] {7};
							jPanelProps.setLayout(jPanelPropsLayout);
							jPanelPropietats.add(
								jPanelProps,
								new GridBagConstraints(
									0,
									1,
									1,
									1,
									0.0,
									0.0,
									GridBagConstraints.CENTER,
									GridBagConstraints.NONE,
									new Insets(0, 0, 0, 0),
									0,
									0));
							jPanelProps.setPreferredSize(new java.awt.Dimension(139, 346));
							jPanelProps.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Propietats", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
							{
								jScrollPanePropietats = new JScrollPane();
								jScrollPanePropietats.setAutoscrolls(true);
								jPanelProps.add(jScrollPanePropietats, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
								jScrollPanePropietats.setPreferredSize(new java.awt.Dimension(1000,1000));
								jScrollPanePropietats.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
								jScrollPanePropietats.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
								jScrollPanePropietats.setWheelScrollingEnabled(true);
								{									
									jListPropietats = new JList();
									jScrollPanePropietats
										.setViewportView(jListPropietats);
									jListPropietats
										.setModel(jListPropietatsModel);
									jListPropietats.setPreferredSize(new java.awt.Dimension(2000,2000));
									jListPropietats.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
								}
							}
							{
								jButtonConsultar = new JButton();
								jPanelProps.add(
									jButtonConsultar,
									new GridBagConstraints(
										0,
										1,
										1,
										1,
										0.0,
										0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.NONE,
										new Insets(0, 0, 0, 0),
										0,
										0));
								jButtonConsultar.setText("Consultar valors");
								jButtonConsultar.addActionListener(new java.awt.event.ActionListener() {
								      public void actionPerformed(ActionEvent e) {
								    	  jButtonConsultar_actionPerformed(e);
								      }
								});
							}
						}
					}
					{
						jPanelBotoDreta = new JPanel();
						jPanelBotoDreta.setLayout(null);
						jPanelEditorRegles.add(jPanelBotoDreta, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPanelBotoDreta.setPreferredSize(new java.awt.Dimension(77, 301));
						{
							jButtonDreta = new JButton();
							jPanelBotoDreta.add(jButtonDreta);
							jButtonDreta.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
							jButtonDreta.setBounds(7, 112, 63, 42);
							jButtonDreta.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonDreta_actionPerformed(e);
							      }
							});
						}
						{
							jLabel1 = new JLabel();
							jPanelBotoDreta.add(jLabel1);
							jLabel1.setText("=");
							jLabel1.setBounds(7, 14, 56, 35);
							jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
							jLabel1.setFont(new java.awt.Font("Tahoma",0,22));
						}
					}
					{
						jPanelExpressio = new JPanel();
						GridBagLayout jPanelExpressioLayout = new GridBagLayout();
						jPanelExpressioLayout.rowWeights = new double[] {0.0, 0.0, 0.1};
						jPanelExpressioLayout.rowHeights = new int[] {207, 30, 7};
						jPanelExpressioLayout.columnWeights = new double[] {0.1};
						jPanelExpressioLayout.columnWidths = new int[] {7};
						jPanelExpressio.setLayout(jPanelExpressioLayout);
						jPanelEditorRegles.add(jPanelExpressio, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPanelExpressio.setPreferredSize(new java.awt.Dimension(314, 412));
						{
							jPanelBotonsCalculadora = new JPanel();
							GridBagLayout jPanelBotonsCalculadoraLayout = new GridBagLayout();
							jPanelBotonsCalculadoraLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.1};
							jPanelBotonsCalculadoraLayout.rowHeights = new int[] {35, 41, 39, 7};
							jPanelBotonsCalculadoraLayout.columnWeights = new double[] {0.0, 0.0, 0.1, 0.1};
							jPanelBotonsCalculadoraLayout.columnWidths = new int[] {78, 83, 7, 7};
							jPanelBotonsCalculadora.setLayout(jPanelBotonsCalculadoraLayout);
							jPanelExpressio.add(jPanelBotonsCalculadora, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jPanelBotonsCalculadora.setPreferredSize(new java.awt.Dimension(314, 151));
							{
								jButtonMenor = new JButton();
								jPanelBotonsCalculadora.add(
									jButtonMenor,
									new GridBagConstraints(
										0,
										0,
										1,
										1,
										0.0,
										0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.NONE,
										new Insets(0, 0, 0, 0),
										0,
										0));
								jButtonMenor.setText("<");
								jButtonMenor.setPreferredSize(new java.awt.Dimension(54, 28));
								jButtonMenor.setSize(54, 28);
								jButtonMenor.addActionListener(new java.awt.event.ActionListener() {
								      public void actionPerformed(ActionEvent e) {
								    	  jButtonMenor_actionPerformed(e);
								      }
								});
							}
							{
								jButtonMajor = new JButton();
								jPanelBotonsCalculadora.add(
									jButtonMajor,
									new GridBagConstraints(
										1,
										0,
										1,
										1,
										0.0,
										0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.NONE,
										new Insets(0, 0, 0, 0),
										0,
										0));
								jButtonMajor.setText(">");
								jButtonMajor.setPreferredSize(new java.awt.Dimension(54, 29));
								jButtonMajor.setSize(54, 29);
								jButtonMajor.addActionListener(new java.awt.event.ActionListener() {
								      public void actionPerformed(ActionEvent e) {
								    	  jButtonMajor_actionPerformed(e);
								      }
								});
							}
							{
								jButtonIgual = new JButton();
								jPanelBotonsCalculadora.add(
									jButtonIgual,
									new GridBagConstraints(
										2,
										0,
										1,
										1,
										0.0,
										0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.NONE,
										new Insets(0, 0, 0, 0),
										0,
										0));
								jButtonIgual.setText("=");
								jButtonIgual.setPreferredSize(new java.awt.Dimension(54, 29));
								jButtonIgual.addActionListener(new java.awt.event.ActionListener() {
								      public void actionPerformed(ActionEvent e) {
								    	  jButtonIgual_actionPerformed(e);
								      }
								});
							}
							{
								jButtonMenorIgual = new JButton();
								jPanelBotonsCalculadora.add(
									jButtonMenorIgual,
									new GridBagConstraints(
										0,
										1,
										1,
										1,
										0.0,
										0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.NONE,
										new Insets(0, 0, 0, 0),
										0,
										0));
								jButtonMenorIgual.setText("<=");
								jButtonMenorIgual.setPreferredSize(new java.awt.Dimension(55, 29));
								jButtonMenorIgual.addActionListener(new java.awt.event.ActionListener() {
								      public void actionPerformed(ActionEvent e) {
								    	  jButtonMenorIgual_actionPerformed(e);
								      }
								});
							}
							{
								jButtonMajorIgual = new JButton();
								jPanelBotonsCalculadora.add(
									jButtonMajorIgual,
									new GridBagConstraints(
										1,
										1,
										1,
										1,
										0.0,
										0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.NONE,
										new Insets(0, 0, 0, 0),
										0,
										0));
								jButtonMajorIgual.setText(">=");
								jButtonMajorIgual.setPreferredSize(new java.awt.Dimension(55, 29));
								jButtonMajorIgual.setSize(54, 29);
								jButtonMajorIgual.addActionListener(new java.awt.event.ActionListener() {
								      public void actionPerformed(ActionEvent e) {
								    	  jButtonMajorIgual_actionPerformed(e);
								      }
								});
							}
							{
								jButtonDiferent = new JButton();
								jPanelBotonsCalculadora.add(
									jButtonDiferent,
									new GridBagConstraints(
										2,
										1,
										1,
										1,
										0.0,
										0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.NONE,
										new Insets(0, 0, 0, 0),
										0,
										0));
								jButtonDiferent.setText("!=");
								jButtonDiferent.setPreferredSize(new java.awt.Dimension(55, 29));
								jButtonDiferent.setSize(54, 29);
								jButtonDiferent.addActionListener(new java.awt.event.ActionListener() {
								      public void actionPerformed(ActionEvent e) {
								    	  jButtonDiferent_actionPerformed(e);
								      }
								});
							}
							{
								jButtonObrirParentesis = new JButton();
								jPanelBotonsCalculadora.add(
									jButtonObrirParentesis,
									new GridBagConstraints(
										3,
										0,
										1,
										1,
										0.0,
										0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.NONE,
										new Insets(0, 0, 0, 0),
										0,
										0));
								jButtonObrirParentesis.setText("(");
								jButtonObrirParentesis.setSize(39, 29);
								jButtonObrirParentesis.setPreferredSize(new java.awt.Dimension(54, 26));
								jButtonObrirParentesis.addActionListener(new java.awt.event.ActionListener() {
								      public void actionPerformed(ActionEvent e) {
								    	  jButtonObrirParentesis_actionPerformed(e);
								      }
								});
							}
							{
								jButtonAND = new JButton();
								jPanelBotonsCalculadora.add(
									jButtonAND,
									new GridBagConstraints(
										0,
										2,
										1,
										1,
										0.0,
										0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.NONE,
										new Insets(0, 0, 0, 0),
										0,
										0));
								jButtonAND.setText("AND");
								jButtonAND.setPreferredSize(new java.awt.Dimension(54, 29));
								jButtonAND.setFont(new java.awt.Font("Arial",0,9));
								jButtonAND.addActionListener(new java.awt.event.ActionListener() {
								      public void actionPerformed(ActionEvent e) {
								    	  jButtonAND_actionPerformed(e);
								      }
								});
							}
							{
								jButtonNOT = new JButton();
								jPanelBotonsCalculadora.add(
									jButtonNOT,
									new GridBagConstraints(
										1,
										2,
										1,
										1,
										0.0,
										0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.NONE,
										new Insets(0, 0, 0, 0),
										0,
										0));
								jButtonNOT.setText("NOT");
								jButtonNOT.setPreferredSize(new java.awt.Dimension(55, 29));
								jButtonNOT.setFont(new java.awt.Font("Arial",0,9));
								jButtonNOT.setSize(54, 29);
								jButtonNOT.addActionListener(new java.awt.event.ActionListener() {
								      public void actionPerformed(ActionEvent e) {
								    	  jButtonNOT_actionPerformed(e);
								      }
								});
							}
							{
								jButtonOR = new JButton();
								jPanelBotonsCalculadora.add(
									jButtonOR,
									new GridBagConstraints(
										2,
										2,
										1,
										1,
										0.0,
										0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.NONE,
										new Insets(0, 0, 0, 0),
										0,
										0));
								jButtonOR.setText("OR");
								jButtonOR.setPreferredSize(new java.awt.Dimension(55, 29));
								jButtonOR.setFont(new java.awt.Font("Arial",0,9));
								jButtonOR.addActionListener(new java.awt.event.ActionListener() {
								      public void actionPerformed(ActionEvent e) {
								    	  jButtonOR_actionPerformed(e);
								      }
								});
							}
							{
								jButtonTancarParentesis = new JButton();
								jPanelBotonsCalculadora.add(
									jButtonTancarParentesis,
									new GridBagConstraints(
										3,
										1,
										1,
										1,
										0.0,
										0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.NONE,
										new Insets(0, 0, 0, 0),
										0,
										0));
								jButtonTancarParentesis.setText(")");
								jButtonTancarParentesis.setSize(39, 29);
								jButtonTancarParentesis.setPreferredSize(new java.awt.Dimension(54, 29));
								jButtonTancarParentesis.addActionListener(new java.awt.event.ActionListener() {
								      public void actionPerformed(ActionEvent e) {
								    	  jButtonTancarParentesis_actionPerformed(e);
								      }
								});
							}
							{
								jButtonObrirClaudator = new JButton();
								jPanelBotonsCalculadora.add(jButtonObrirClaudator, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
								jButtonObrirClaudator.setText("{");
								jButtonObrirClaudator.setPreferredSize(new java.awt.Dimension(54, 29));
								jButtonObrirClaudator.addActionListener(new java.awt.event.ActionListener() {
								      public void actionPerformed(ActionEvent e) {
								    	  jButtonObrirClaudator_actionPerformed(e);
								      }
								});
							}
						}
						{
							jButtonTancarClaudator = new JButton();
							jPanelBotonsCalculadora.add(jButtonTancarClaudator, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jButtonTancarClaudator.setText("}");
							jButtonTancarClaudator.setPreferredSize(new java.awt.Dimension(53, 29));
							jButtonTancarClaudator.setSize(54, 29);
							jButtonTancarClaudator.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonTancarClaudator_actionPerformed(e);
							      }
							});
							{
								jButtonPertany = new JButton();
								jPanelBotonsCalculadora.add(
									jButtonPertany,
									new GridBagConstraints(
										0,
										3,
										1,
										1,
										0.0,
										0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.NONE,
										new Insets(0, 0, 0, 0),
										0,
										0));
								jButtonPertany.setText("\u0404");
								jButtonPertany.setSize(55, 29);
								jButtonPertany.setPreferredSize(new java.awt.Dimension(55, 29));
								jButtonPertany.setFont(new java.awt.Font("Tahoma",0,12));
								jButtonPertany.addActionListener(new java.awt.event.ActionListener() {
								      public void actionPerformed(ActionEvent e) {
								    	  jButtonPertany_actionPerformed(e);
								      }
								});
							}
							{
								jButtonClear = new JButton();
								jPanelBotonsCalculadora.add(
									jButtonClear,
									new GridBagConstraints(
										1,
										3,
										1,
										1,
										0.0,
										0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.NONE,
										new Insets(0, 0, 0, 0),
										0,
										0));
								jButtonClear.setText("Clear");
								jButtonClear.setPreferredSize(new java.awt.Dimension(56, 29));
								jButtonClear.setSize(54, 29);
								jButtonClear.setFont(new java.awt.Font("Arial",0,9));
								jButtonClear.addActionListener(new java.awt.event.ActionListener() {
								      public void actionPerformed(ActionEvent e) {
								    	  jButtonClear_actionPerformed(e);
								      }
								});
							}
						}
						{
							jScrollPaneExpressio = new JScrollPane();
							jScrollPaneExpressio.setAutoscrolls(true);
							jPanelExpressio.add(
								jScrollPaneExpressio,
								new GridBagConstraints(
									0,
									0,
									1,
									1,
									0.0,
									0.0,
									GridBagConstraints.CENTER,
									GridBagConstraints.NONE,
									new Insets(0, 0, 0, 0),
									0,
									0));
							jScrollPaneExpressio.setPreferredSize(new java.awt.Dimension(303, 194));
							jScrollPaneExpressio.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Expressió lògica", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
							jScrollPaneExpressio.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
							jScrollPaneExpressio.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
							jScrollPaneExpressio.setWheelScrollingEnabled(true);
							{
								jTextAreaExpressio = new JTextArea();
								jScrollPaneExpressio
									.setViewportView(jTextAreaExpressio);
								jTextAreaExpressio.setPreferredSize(new java.awt.Dimension(100000,100000));
							}
						}
						{
							jPanelConsequent = new JPanel();
						jPanelExpressio.add(
								jPanelConsequent,
								new GridBagConstraints(
									0,
									1,
									1,
									1,
									0.0,
									0.0,
									GridBagConstraints.CENTER,
									GridBagConstraints.NONE,
									new Insets(0, 0, 0, 0),
									0,
									0));
						jPanelConsequent.setPreferredSize(new java.awt.Dimension(308, 35));
						jPanelConsequent.setLayout(null);
						{
							/*jTextFieldConsequent = new JTextField();*/
							jPanelConsequent.add(jTextFieldConsequent);
							jTextFieldConsequent.setBounds(84, 7, 109, 28);
							{
								jLabelConsequent = new JLabel();
								jPanelConsequent.add(jLabelConsequent);
								jLabelConsequent.setText("Conseqüent: ");
								jLabelConsequent.setBounds(7, 7, 77, 28);
								jLabelConsequent.setFont(new java.awt.Font("Tahoma",1,11));
							}
							{
								jLabelProbabilitat = new JLabel();
								jPanelConsequent.add(jLabelProbabilitat);
								jLabelProbabilitat.setText("");
								jLabelProbabilitat.setBounds(202, 13, 95, 14);
							}
						}
						}
					}
				}
			}
			{
				jPanelOK = new JPanel();
				FlowLayout jPanel3Layout = new FlowLayout();
				this.add(jPanelOK);
				jPanelOK.setLayout(jPanel3Layout);
				jPanelOK.setBounds(280, 448, 105, 35);
				{
					jButtonOK = new JButton();
					jPanelOK.add(jButtonOK);

					jButtonOK.setLayout(null);
					jButtonOK.setText("D'acord");
					jButtonOK.setPreferredSize(new java.awt.Dimension(70, 26));
					jButtonOK.addActionListener(new java.awt.event.ActionListener() {
					      public void actionPerformed(ActionEvent e) {
					    	  jButtonOK_actionPerformed(e);
					      }
					});
				}
			}
			{
				jPanelCancela = new JPanel();
				this.add(jPanelCancela);
				
				jPanelCancela.setBounds(490, 448, 77, 35);
				{
					jButtonCancela = new JButton();
					jPanelCancela.add(jButtonCancela);
					jButtonCancela.setLayout(null);
					jButtonCancela.setText("Cancel.la");
					jButtonCancela.setPreferredSize(new java.awt.Dimension(78, 27));
					jButtonCancela.addActionListener(new java.awt.event.ActionListener() {
					      public void actionPerformed(ActionEvent e) {
					    	  jButtonCancela_actionPerformed(e);
					      }
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Neteja l'àrea d'edició de l'expressió lògica
	 * @param e, event que detecta que s'ha premut el botó <code>Clear</code>
	 */
	protected void jButtonClear_actionPerformed(ActionEvent e) {
		 jTextAreaExpressio = new JTextArea();
         jScrollPaneExpressio.setViewportView(jTextAreaExpressio);		
	}
	/**
	 * Elimina la Base de coneixement seleccionada al llistat de bases de coneixement.
	 * @param e, event que detecta que s'ha premut el botó <code>Eliminar BC</code>
	 */
	protected void jButtonEliminarBC_actionPerformed(ActionEvent e) {
		IS_OK=true;
		Object ob=jListBC.getSelectedValue();
		gestor.eliminarBC((String) ob);
		 jTextAreaExpressio = new JTextArea();
         jScrollPaneExpressio.setViewportView(jTextAreaExpressio);
		String[] idBC=gestor.obtenirLlistaNomsBC();		
		jListReglesModel=new DefaultComboBoxModel();
		jListRegles.setModel(jListReglesModel);
		jTextFieldNomRegla.setText("");
		jPanelNomRegla.add(jTextFieldNomRegla);
		jTextFieldNomBC.setText("");
		jPanelNomBC.add(jTextFieldNomBC);
		if(idBC.length==0){//no hi ha bases de coneixement creades
			bc=null;
			jListBCModel=new DefaultComboBoxModel();			
		}else{
			jListBCModel=new DefaultComboBoxModel(idBC);
			bc=copiarBC(idBC[0]);						
		}		
		jListBC.setModel(jListBCModel);
	}
	/**
	 * Crea la base de coneixement amb el nom introduït al camp <code>Nom de BC</code> i l'afegeix a la llista de Bases de Coneixement
	 * @param e, event que detecta que s'ha premut el botó <code>Crear BC</code>
	 */
	protected void jButtonCrearBC_actionPerformed(ActionEvent e) {
		try{
			String nomBC=jTextFieldNomBC.getText();
			if(nomBC==null||nomBC.compareTo("")==0){
				 frPare.actualitzarBarraEstat("No s'ha introduït un nom per la BC",true);	
			}else{
				gestor.crearBC(nomBC);
				String[] idBC=gestor.obtenirLlistaNomsBC();				
				jListReglesModel=new DefaultComboBoxModel();
				jListRegles.setModel(jListReglesModel);
				jTextFieldNomRegla.setText("");
				jPanelNomRegla.add(jTextFieldNomRegla);
				jTextFieldNomBC.setText("");
				jPanelNomBC.add(jTextFieldNomBC);
				jListBCModel=new DefaultComboBoxModel(idBC);
				bc=null;
				jListBC.setModel(jListBCModel);
				 frPare.actualitzarBarraEstat("Base de Coneixement creada",false);	
			}
			
		}catch(Exception ex){
			 frPare.actualitzarBarraEstat("No s'ha pogut crear la Base de Coneixement: "+ex.getMessage(),true);	
		}		
	}
	/**
	 * Tanca la finestra. Si existien canvis no guardats, obre una finestra preguntant si es desitgen guardar els canvis.
	 * @param e, event que detecta que s'ha premut el botó <code>Cancel.la</code>
	 */
	protected void jButtonCancela_actionPerformed(ActionEvent e) {
		if(!IS_OK){
   		 int answer=JOptionPane.showConfirmDialog(frPare,"Desitja guardar els canvis efectuats a la Base de Coneixement?","Desitja guardar els canvis efectuats a la Base de Coneixement?",JOptionPane.YES_NO_OPTION);
   		 if (answer == JOptionPane.YES_OPTION) {
   				gestor.guardarCanvis(bc);
   		    } else if (answer == JOptionPane.NO_OPTION) {
   		      
   		    } 
		}
		 frPare.remove(this);
		 frPare.validate();
		 frPare.repaint();		
	}
	/**
	 * Fa permanents les modificacions efectuades sobre la base de coneixement seleccionada
	 * @param e, event que detecta que s'ha premut el botó <code>D'acord</code>
	 */
	protected void jButtonOK_actionPerformed(ActionEvent e) {
		try{
			IS_OK=true;
			 jTextAreaExpressio = new JTextArea();
	         jScrollPaneExpressio.setViewportView(jTextAreaExpressio);
			//guardar els canvis
	         if(jTextFieldNomBC.getText().compareTo("")==0||jTextFieldNomBC.getText()==null){
	        	 frPare.actualitzarBarraEstat("No s'ha seleccionat una base de coneixement de la llista",true);	        	 
	         }else{
	        	 gestor.guardarCanvis(this.bc);
				 frPare.actualitzarBarraEstat("S'han guardat els canvis",false);	
	         }
			
		}catch(Exception ex){
			frPare.actualitzarBarraEstat("No s'han pogut guardar els canvis. "
					+ ex.getMessage(), true);	
		}		
	}
	/**
	 * Insereix el símbol de pertany a l'àrea d'edició de l'expressió lògica
	 * @param e, event que detecta que s'ha premut el botó <code>Pertany</code>
	 */
	protected void jButtonPertany_actionPerformed(ActionEvent e) {
		jTextAreaExpressio.insert(" "+ExpressioBooleana.getPERTANY()+" ", jTextAreaExpressio.getCaretPosition());				
		
	}
	/**
	 * Insereix el símbol de tancar claus } a l'àrea d'edició de l'expressió lògica
	 * @param e, event que detecta que s'ha premut el botó <code>}</code>
	 */
	protected void jButtonTancarClaudator_actionPerformed(ActionEvent e) {
		jTextAreaExpressio.insert("}", jTextAreaExpressio.getCaretPosition());		
		
	}
	/**
	 * Insereix el símbol d'obrir claus { a l'àrea d'edició de l'expressió lògica
	 * @param e, event que detecta que s'ha premut el botó <code>{</code>
	 */
	protected void jButtonObrirClaudator_actionPerformed(ActionEvent e) {
		jTextAreaExpressio.insert("{", jTextAreaExpressio.getCaretPosition());		
		
	}
	/**
	 * Insereix el símbol de tancar parèntesis ) a l'àrea d'edició de l'expressió lògica
	 * @param e, event que detecta que s'ha premut el botó <code>)</code>
	 */
	protected void jButtonTancarParentesis_actionPerformed(ActionEvent e) {
		jTextAreaExpressio.insert(")", jTextAreaExpressio.getCaretPosition());		
		
	}
	/**
	 * Insereix el símbol d'OR or a l'àrea d'edició de l'expressió lògica
	 * @param e, event que detecta que s'ha premut el botó <code>OR</code>
	 */
	protected void jButtonOR_actionPerformed(ActionEvent e) {
		jTextAreaExpressio.insert(" "+ExpressioBooleana.getOR()+" ", jTextAreaExpressio.getCaretPosition());		
		
	}
	/**
	 * Insereix el símbol de NOT not a l'àrea d'edició de l'expressió lògica
	 * @param e, event que detecta que s'ha premut el botó <code>NOT</code>
	 */
	protected void jButtonNOT_actionPerformed(ActionEvent e) {
		jTextAreaExpressio.insert(" "+ExpressioBooleana.getNOT()+" ", jTextAreaExpressio.getCaretPosition());		
		
	}
	/**
	 * Insereix el símbol d'AND and a l'àrea d'edició de l'expressió lògica
	 * @param e, event que detecta que s'ha premut el botó <code>AND</code>
	 */
	protected void jButtonAND_actionPerformed(ActionEvent e) {
		jTextAreaExpressio.insert(" "+ExpressioBooleana.getAND()+" ", jTextAreaExpressio.getCaretPosition());			
		
	}
	/**
	 * Insereix el símbol d'obrir parèntesis a l'àrea d'edició de l'expressió lògica
	 * @param e, event que detecta que s'ha premut el botó <code>(</code>
	 */
	protected void jButtonObrirParentesis_actionPerformed(ActionEvent e) {
		jTextAreaExpressio.insert("(", jTextAreaExpressio.getCaretPosition());		
		
	}
	/**
	 * Insereix el símbol de diferent != a l'àrea d'edició de l'expressió lògica
	 * @param e, event que detecta que s'ha premut el botó <code>!=</code>
	 */
	protected void jButtonDiferent_actionPerformed(ActionEvent e) {
		jTextAreaExpressio.insert(" "+ExpressioBooleana.getDIFERENT()+" ", jTextAreaExpressio.getCaretPosition());		
		
	}
	/**
	 * Insereix el símbol de major igual >= a l'àrea d'edició de l'expressió lògica
	 * @param e, event que detecta que s'ha premut el botó <code>>=</code>
	 */
	protected void jButtonMajorIgual_actionPerformed(ActionEvent e) {
		jTextAreaExpressio.insert(" "+ExpressioBooleana.getMAJORIGUAL()+" ", jTextAreaExpressio.getCaretPosition());		
		
	}
	/**
	 * Insereix el símbol de menor igual <= a l'àrea d'edició de l'expressió lògica
	 * @param e, event que detecta que s'ha premut el botó <= 
	 */
	protected void jButtonMenorIgual_actionPerformed(ActionEvent e) {
		jTextAreaExpressio.insert(" "+ExpressioBooleana.getMENORIGUAL()+" ", jTextAreaExpressio.getCaretPosition());		;	
		
	}
	/**
	 * Insereix el símbol d'igual = a l'àrea d'edició de l'expressió lògica
	 * @param e, event que detecta que s'ha premut el botó <code>=</code> 
	 */
	protected void jButtonIgual_actionPerformed(ActionEvent e) {
		
		jTextAreaExpressio.insert(" "+ExpressioBooleana.getIGUAL()+" ", jTextAreaExpressio.getCaretPosition());		
	}
	/**
	 * Insereix el símbol de major > a l'àrea d'edició de l'expressió lògica
	 * @param e, event que detecta que s'ha premut el botó <code>></code> 
	 */
	protected void jButtonMajor_actionPerformed(ActionEvent e) {

		jTextAreaExpressio.insert(" "+ExpressioBooleana.getMAJOR()+" ", jTextAreaExpressio.getCaretPosition());		
	}
	/**
	 * Insereix el símbol de menor < a l'àrea d'edició de l'expressió lògica
	 * @param e, event que detecta que s'ha premut el botó < 
	 */
	protected void jButtonMenor_actionPerformed(ActionEvent e) {
		jTextAreaExpressio.insert(" "+ExpressioBooleana.getMENOR()+" ", jTextAreaExpressio.getCaretPosition());		
	}
	/**
	 * Afegeix la variables seleccionada a la llista de variables a l'àrea d'edició de l'expressió lògica
	 * @param e, event que detecta que s'ha premut el botó <code>Afegir propietat(mà cap a la dreta)</code>
	 */
	protected void jButtonDreta_actionPerformed(ActionEvent e) {
		  Object vars = jListPropietats.getSelectedValue();		 
		  int i=jTextAreaExpressio.getCaretPosition();
		  vars="("+vars+")";
		  jTextAreaExpressio.insert((String)vars, i);
	}
	/**
	 * Obre una finestra amb els valors característics de la variable seleccionada
	 * @param e, event que detecta que s'ha premut el botó <code>Consultar valors</code>
	 */
	protected void jButtonConsultar_actionPerformed(ActionEvent e) {
		//if(variable categoria)
		try {
			Object ob=jListPropietats.getSelectedValue();
			Propietat prop=gestor.obtenirPropietat((String)ob);
			if(prop instanceof PropNumerica){
				PropNumerica aux=(PropNumerica)prop;
				float max=aux.obtenirRangMax();
				float min=aux.obtenirRangMin();
				DialogValorsNum inst = new DialogValorsNum(frPare,gestor,max,min,(String)ob);
				inst.setLocationRelativeTo(this);
				inst.setVisible(true);
			}else{
				String[] alModalitats=gestor.obtenirLlistaMods((String)ob);				
				DialogValorsCat inst = new DialogValorsCat(frPare,gestor,(String)ob,alModalitats);
				inst.setLocationRelativeTo(this);
				inst.setVisible(true);
				if(inst.OKPremut()){
					String valor=inst.getValorSeleccionat();					
					jTextAreaExpressio.insert(valor, jTextAreaExpressio.getCaretPosition());	
				}
			}
		} catch (Exception e1) {
			frPare.actualitzarBarraEstat("No s'ha pogut analitzar els valors de la variable: "+e1.getMessage(),true);
			
		}			
	}
	/**
	 * Elimina la regla seleccionada de la llista de regles
	 * @param e, event que detecta que s'ha premut el botó <code>Eliminar regla</code>
	 */
	protected void jButtonEliminarRegla_actionPerformed(ActionEvent e) {
		IS_OK=false;
		Object ob=jListRegles.getSelectedValue();		
		try{
			gestor.eliminarRegla(bc,(String)ob);
			String[] idRegles=gestor.obtenirNomsRegles(bc);
            jListReglesModel=new DefaultComboBoxModel(idRegles);
            jListRegles.setModel(jListReglesModel);
            String novaId=bc.seguentNomRegla();            
            jTextFieldNomRegla.setText(novaId);
            jTextFieldConsequent.setText("T");
            jTextAreaExpressio = new JTextArea();
            jScrollPaneExpressio.setViewportView(jTextAreaExpressio);
            frPare.actualitzarBarraEstat("S'ha eliminat la regla",false);	
		}catch(Exception ex){
			frPare.actualitzarBarraEstat("La regla no s'ha pogut eliminar. "
					+ ex.getMessage(), true);
		}
	}
	/**
	 * Crea la nova regla definida i de nom el valor introduït en el camp <code>Nom regla</code> i l'afegeix a la llista de regles
	 * @param e, event que detecta que s'ha premut el botó <code>Afegir regla</code>
	 */
	protected void jButtonAfegirRegla_actionPerformed(ActionEvent e) {
		
		String nom=jTextFieldNomRegla.getText();
		String regla=jTextAreaExpressio.getText();
		String consequent=jTextFieldConsequent.getText();
		IS_OK=false;
		try{	
			regla=regla.trim();
			if(regla==null)throw new Exception("La regla està buida");			
			bc.llegirReglaNormal(regla, consequent, nom);			
			String[] idRegles=gestor.obtenirNomsRegles(bc);
            jListReglesModel=new DefaultComboBoxModel(idRegles);
            jListRegles.setModel(jListReglesModel);
            String novaId=bc.seguentNomRegla();            
            jTextFieldNomRegla.setText(novaId);
            jTextAreaExpressio = new JTextArea();
            jScrollPaneExpressio.setViewportView(jTextAreaExpressio);
            jTextFieldConsequent.setText("T");
			frPare.actualitzarBarraEstat("S'ha inserit la regla",false);			
		}catch(Exception ex){
			ex.printStackTrace();
			frPare.actualitzarBarraEstat("La regla no s'ha pogut inserir. "
					+ ex.getMessage(), true);
		}
		
	}
	/**
	 * Guarda els canvis efectuats a la regla que es troba seleccionada al camp <code>Nom regla</code>
	 * i neteja l'àrea d'edició i actualitza els camps de <code>Nom regla</code> i <code>Conseqüent</code>
	 * @param e, event que detecta que s'ha premut el botó <code>Modificar regla</code>
	 */
	protected void jButtonModificarRegla_actionPerformed(ActionEvent e){
		String nom=jTextFieldNomRegla.getText();
		String regla=jTextAreaExpressio.getText();
		String consequent=jTextFieldConsequent.getText();
		IS_OK=false;
		try{
			if(regla.trim()==null)throw new Exception("La regla està buida");			
			
			Object nomRegla=jListRegles.getSelectedValue();		
			if(nomRegla==null)throw new Exception("No hi ha cap regla seleccionada al llistat de regles");
			bc.modificarRegla((String)nomRegla,nom,regla,consequent);
			
			String[] idRegles=gestor.obtenirNomsRegles(bc);
            jListReglesModel=new DefaultComboBoxModel(idRegles);
            jListRegles.setModel(jListReglesModel);
            String novaId=bc.seguentNomRegla();            
            jTextFieldNomRegla.setText(novaId);
            jTextAreaExpressio = new JTextArea();
            
            jScrollPaneExpressio.setViewportView(jTextAreaExpressio);
            jTextFieldConsequent.setText("T");
			frPare.actualitzarBarraEstat("S'ha modificat la regla",false);	
		}catch(Exception ex){
			ex.printStackTrace();
			frPare.actualitzarBarraEstat("La regla no s'ha pogut modificar. "
					+ ex.getMessage(), true);
		}
		
	}
	/**
	 * Obté una còpia de la base de coneixement de nom <code>BaseC</code>
	 * @param BaseC, nom de la base de coneixement a copiar
	 * @return
	 */
	protected BaseConeixement copiarBC(String BaseC){
		return gestor.copiarBaseConeixement(BaseC);		
	}

}

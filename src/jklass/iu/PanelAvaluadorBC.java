package jklass.iu;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

import jklass.nucli.GestorKlass;
import jklass.nucli.Propietat;
import jklass.util.Opcions;
import jklass.util.SO;
/** Classe que dibuixa el submenú per seleccionar les opcions d'avaluació d'una base de coneixement 
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
public class PanelAvaluadorBC extends javax.swing.JPanel {
	private static Logger logger=Logger.getLogger(PanelAvaluadorBC.class.getName());
	private JPanel jPanelOpcions;
	private JButton jButtonCancel;
	private JButton jButtonOK;
	private JButton jButtonPerDefecte;
	private JList jListBC;
	private JRadioButton jRadioButtonRegla=new JRadioButton();
	private JPanel jPanel9;
	private JPanel jPanel8;
	private JPanel jPanelSelectors;
	private JPanel jPanelBCSeleccionada;
	private JButton jButtonSeleccionarBC;
	private JPanel jPanel3;
	private JPanel jPanel2;
	private JPanel jPanel1;
	private JCheckBox jCheckBoxEnriquit;
	private JSeparator jSeparator2;
	private JCheckBox jCheckBoxNotacioBinaria;
	private JPanel jPanel10;
	private JRadioButton jRadioButtonIntegrat=new JRadioButton();
	private JLabel jLabelNomBC;
	private JSeparator jSeparator1;
	private JTextField jTextFieldNomBC=new JTextField();
	private JRadioButton jRadioButtonConsequent=new JRadioButton();
	private JScrollPane jScrollPaneBC;
	private JPanel jPanelButons;
	ListModel jListBCModel = new DefaultComboBoxModel();
	FrPrincipal frPare;
	GestorKlass gestor;	 
	Opcions opcUniv = new Opcions(Opcions.UNIVARIANT);
	String nomFitxer = null;
	Opcions opcClass = new Opcions(Opcions.PER_CLASSES);
	private int matriuCarregada;
	private int matriuActual;
	private JCheckBox jCheckBoxDescriptiva =new JCheckBox();
	private JTextField jTextFieldBCSeleccionada=new JTextField();
	private JLabel jLabelBCSeleccionada;
	private JPanel jPanel4;
	  ButtonGroup group = new ButtonGroup();
	 
	 MouseListener mouseRegla =new MouseAdapter(){
		 /**
		  * Selecciona una base de coneixement a partir de fer doble clic sobre un nom
		  * @param MouseEvent, detecta que s'ha fet doble clic sobre el nom d'una base de coneixement
		  */
		   public void mouseClicked(MouseEvent e) {
		         if (e.getClickCount() == 2) {
		        		Object ob=jListBC.getSelectedValue();
		        		jTextFieldBCSeleccionada.setText((String)ob);
		        		jPanel4.add(jTextFieldBCSeleccionada);
		        		jTextFieldNomBC.setText((String)ob);
		        		jPanel9.add(jTextFieldNomBC);
		     		try{		     			 
		     			 
		     		}catch(Exception ex) {
		     			 ex.getMessage();
		     		}
		         }
		   }
	 };
	
	/**
	 * Constructor
	 * @param fr,finestra pare
	 * @param gk, GestorKlass
	 */	
	public PanelAvaluadorBC(FrPrincipal fr,GestorKlass gk) {
		super();
		frPare = fr;
		gestor = gk;		
		opcUniv.posarPerDefecteBC(Opcions.TAULA_FREQS);
		opcUniv.posarPerDefecteBC(Opcions.DIAGR_BARRES);
		opcClass.posarPerDefecteBC(Opcions.DESCR_EXTENSIONAL);
		nomFitxer = frPare.obtenirNomDades();
		String[] idBC=gestor.obtenirLlistaNomsBC();	
		jRadioButtonIntegrat.setSelected(true);
		jCheckBoxDescriptiva.setSelected(true);
		if(idBC.length==0){//no hi ha bases de coneixement creades		
			jListBCModel=new DefaultComboBoxModel();		
		}else{
			jListBCModel=new DefaultComboBoxModel(idBC);
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
			matriuCarregada=gestor.idMatriuActual();
			frPare.setSize(580,464);
			frPare.centrar();
			this.setPreferredSize(new java.awt.Dimension(568, 383));
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.0, 0.1};
			thisLayout.rowHeights = new int[] {317, 7};
			thisLayout.columnWeights = new double[] {0.1};
			thisLayout.columnWidths = new int[] {7};
			this.setLayout(thisLayout);
			this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED), "Avaluador de Bases de Coneixement", TitledBorder.LEADING, TitledBorder.TOP,new java.awt.Font("Dialog",0,10)));
			{
				jPanelOpcions = new JPanel();
				GridBagLayout jPanelOpcionsLayout = new GridBagLayout();
				jPanelOpcionsLayout.rowWeights = new double[] {0.0, 0.1};
				jPanelOpcionsLayout.rowHeights = new int[] {275, 20};
				jPanelOpcionsLayout.columnWeights = new double[] {0.0, 0.1};
				jPanelOpcionsLayout.columnWidths = new int[] {190, 7};
				jPanelOpcions.setLayout(jPanelOpcionsLayout);
				this.add(jPanelOpcions, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jPanelOpcions.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				{
					jScrollPaneBC = new JScrollPane();
					jScrollPaneBC.setAutoscrolls(true);
					jPanelOpcions.add(jScrollPaneBC, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jScrollPaneBC.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Bases de Coneixement", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
					jScrollPaneBC.setPreferredSize(new java.awt.Dimension(190, 271));
					{
						/*ListModel jListBCModel = new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });*/
						jListBC = new JList();
						jScrollPaneBC.setViewportView(jListBC);
						jListBC.setModel(jListBCModel);
						jListBC.setPreferredSize(new java.awt.Dimension(182, 308));
						jListBC.addMouseListener(mouseRegla);
					}
				}
				{
					jPanel1 = new JPanel();
					GridBagLayout jPanel1Layout = new GridBagLayout();
					jPanel1Layout.rowWeights = new double[] {0.1};
					jPanel1Layout.rowHeights = new int[] {7};
					jPanel1Layout.columnWeights = new double[] {0.0, 0.1};
					jPanel1Layout.columnWidths = new int[] {91, 7};
					jPanel1.setLayout(jPanel1Layout);
					jPanelOpcions.add(jPanel1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						jPanel2 = new JPanel();
						GridBagLayout jPanel2Layout = new GridBagLayout();
						jPanel2Layout.rowWeights = new double[] {0.0, 0.1};
						jPanel2Layout.rowHeights = new int[] {73, 7};
						jPanel2Layout.columnWeights = new double[] {0.1};
						jPanel2Layout.columnWidths = new int[] {7};
						jPanel2.setLayout(jPanel2Layout);
						jPanel1.add(jPanel2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						{
							jPanelBCSeleccionada = new JPanel();
							GridBagLayout jPanelBCSeleccionadaLayout = new GridBagLayout();
							jPanelBCSeleccionadaLayout.rowWeights = new double[] {0.1};
							jPanelBCSeleccionadaLayout.rowHeights = new int[] {7};
							jPanelBCSeleccionadaLayout.columnWeights = new double[] {0.1};
							jPanelBCSeleccionadaLayout.columnWidths = new int[] {7};
							jPanelBCSeleccionada.setLayout(jPanelBCSeleccionadaLayout);
							jPanel2.add(jPanelBCSeleccionada, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							jPanelBCSeleccionada.setBorder(BorderFactory.createTitledBorder(null, "Base de Coneixement seleccionada", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
							jPanelBCSeleccionada.setFont(new java.awt.Font("Dialog",0,10));
							{
								jPanel4 = new JPanel();
								jPanelBCSeleccionada.add(jPanel4, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
								jPanel4.setLayout(null);
								{
									jLabelBCSeleccionada = new JLabel();
									jPanel4.add(jLabelBCSeleccionada);
									jLabelBCSeleccionada.setText("Base de coneixement seleccionada");
									jLabelBCSeleccionada.setBounds(7, 7, 217, 14);
								}
								{
									//jTextFieldBCSeleccionada = new JTextField();
									jPanel4.add(jTextFieldBCSeleccionada);
									jTextFieldBCSeleccionada.setBounds(7, 21, 168, 21);
								}
							}
						}
						{
							jPanelSelectors = new JPanel();
							jPanel2.add(jPanelSelectors, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						}
						{
							jPanel8 = new JPanel();
							GridBagLayout jPanel8Layout = new GridBagLayout();
							jPanel8Layout.rowWeights = new double[] {0.0, 0.0, 0.1};
							jPanel8Layout.rowHeights = new int[] {7, 182, 7};
							jPanel8Layout.columnWeights = new double[] {0.0, 0.1};
							jPanel8Layout.columnWidths = new int[] {151, 7};
							jPanel8.setLayout(jPanel8Layout);
							jPanel2.add(jPanel8, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							jPanel8.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED), "", TitledBorder.LEADING, TitledBorder.TOP));
							{
								jPanel9 = new JPanel();
								jPanel8.add(jPanel9, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
								jPanel9.setLayout(null);
								{
									//jRadioButtonRegla = new JRadioButton();
									jPanel9.add(jRadioButtonRegla);
									jRadioButtonRegla.setText("Regla a Regla");
									jRadioButtonRegla.setBounds(7, 21, 119, 21);
									group.add(jRadioButtonRegla);
								}
								{
									//jRadioButtonConsequent = new JRadioButton();
									jPanel9.add(jRadioButtonConsequent);
									jRadioButtonConsequent.setText("Per conseqüent");
									jRadioButtonConsequent.setBounds(7, 49, 119, 21);
									group.add(jRadioButtonConsequent);
								}
								{
									//jTextFieldNomBC = new JTextField();
									jPanel9.add(jTextFieldNomBC);
									jTextFieldNomBC.setBounds(7, 112, 133, 21);
								}
								{
									jSeparator1 = new JSeparator();
									jPanel9.add(jSeparator1);
									jSeparator1.setBounds(14, 84, 140, 7);
								}
								{
									jLabelNomBC = new JLabel();
									jPanel9.add(jLabelNomBC);
									jLabelNomBC.setText("Nom de la nova propietat");
									jLabelNomBC.setBounds(7, 91, 126, 21);
								}
								{
									//jRadioButtonIntegrat = new JRadioButton();
									jPanel9.add(jRadioButtonIntegrat);
									jRadioButtonIntegrat.setText("Integrat");
									jRadioButtonIntegrat.setBounds(7, 147, 112, 28);
									group.add(jRadioButtonIntegrat);
								}
							}
							{
								jPanel10 = new JPanel();
								jPanel8.add(jPanel10, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
								jPanel10.setLayout(null);
								{
									jCheckBoxNotacioBinaria = new JCheckBox();
									jPanel10.add(jCheckBoxNotacioBinaria);
									jCheckBoxNotacioBinaria.setText("Notació binària");
									jCheckBoxNotacioBinaria.setBounds(0, 49, 119, 21);
								}
								{
									jSeparator2 = new JSeparator();
									jPanel10.add(jSeparator2);
									jSeparator2.setBounds(-7, 84, 105, 7);
								}
								{
									jCheckBoxEnriquit = new JCheckBox();
									jPanel10.add(jCheckBoxEnriquit);
									jCheckBoxEnriquit.setText("Enriquit");
									jCheckBoxEnriquit.setBounds(0, 147, 112, 28);
								}
							}
						}
					}
					{
						jPanel3 = new JPanel();
						jPanel1.add(jPanel3, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPanel3.setLayout(null);
						{
							jButtonSeleccionarBC = new JButton();
							jPanel3.add(jButtonSeleccionarBC);
							jButtonSeleccionarBC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
							jButtonSeleccionarBC.setBounds(14, 21, 63, 42);
							jButtonSeleccionarBC.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonSeleccionarBC_actionPerformed(e);
							      }
							});
						}
					}
				}
				{
					
					jPanelOpcions.add(jCheckBoxDescriptiva, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					jCheckBoxDescriptiva
						.setText("Generar la descriptiva de la base de coneixement");
				}
			}
			{
				jPanelButons = new JPanel();
				this.add(jPanelButons, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jPanelButons.setLayout(null);
				{
					jButtonPerDefecte = new JButton();
					jPanelButons.add(jButtonPerDefecte);
					jButtonPerDefecte.setText("Per defecte");
					jButtonPerDefecte.setBounds(56, 7, 91, 28);
					jButtonPerDefecte.addActionListener(new java.awt.event.ActionListener() {
					      public void actionPerformed(ActionEvent e) {
					    	  jButtonPerDefecte_actionPerformed(e);
					      }
					});
				}
				{
					jButtonOK = new JButton();
					jPanelButons.add(jButtonOK);
					jButtonOK.setText("D'acord");
					jButtonOK.setBounds(238, 7, 84, 28);
					jButtonOK.addActionListener(new java.awt.event.ActionListener() {
					      public void actionPerformed(ActionEvent e) {
					    	  jButtonOK_actionPerformed(e);
					      }
					});
				}
				{
					jButtonCancel = new JButton();
					jPanelButons.add(jButtonCancel);
					jButtonCancel.setText("Cancel.la");
					jButtonCancel.setBounds(357, 7, 84, 28);
					jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
					      public void actionPerformed(ActionEvent e) {
					    	  jButtonCancel_actionPerformed(e);
					      }
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Selecciona una base de coneixement
	 * @param e, event que detecta que s'ha premut el botó de selecció de bases de coneixement
	 */
	protected void jButtonSeleccionarBC_actionPerformed(ActionEvent e) {
		Object ob=jListBC.getSelectedValue();
		jTextFieldBCSeleccionada.setText((String)ob);
		jPanel4.add(jTextFieldBCSeleccionada);		
		jTextFieldNomBC.setText((String)ob);
		jPanel9.add(jTextFieldNomBC);
	}
	/**
	 * Estableix les opcions per defecte del menú
	 * @param e, event que detecta que s'ha premut el botó <code>Per defecte</code>
	 */
	protected void jButtonPerDefecte_actionPerformed(ActionEvent e) {
		jRadioButtonIntegrat.setSelected(true);
		jRadioButtonConsequent.setSelected(false);
		jRadioButtonRegla.setSelected(false);
		jPanel9.add(jRadioButtonRegla);
		jPanel9.add(jRadioButtonConsequent);
		jPanel9.add(jRadioButtonIntegrat);
		jCheckBoxNotacioBinaria.setSelected(false);
		jCheckBoxEnriquit.setSelected(false);
		jPanel10.add(jCheckBoxNotacioBinaria );
		jPanel10.add(jCheckBoxEnriquit);		
		jTextFieldNomBC.setText("");
		jPanel9.add(jTextFieldNomBC);
		jTextFieldBCSeleccionada.setText("");
		jPanel4.add(jTextFieldBCSeleccionada);
		jCheckBoxDescriptiva.setSelected(true);
	}
	/**
	 * Tanca la finestra
	 * @param e, event que detecta que s'ha premut el botó <code>Cancel.la</code>
	 */
	protected void jButtonCancel_actionPerformed(ActionEvent e) {
		 frPare.remove(this);
		 frPare.validate();
		 frPare.repaint();		
	}
	/**
	 * Avalua la base de coneixement seleccionada
	 * @param e, event que detecta que s'ha premut el botó <code>D'acord</code>
	 */
	protected void jButtonOK_actionPerformed(ActionEvent e) {
		ArrayList llistaBC=new ArrayList();//conté els noms de les BC's avaluades
		ArrayList llistaVariables=new ArrayList();//conté llistes, que alhora contenen els vectors de variables
		ArrayList llistaProp=new ArrayList();
		ArrayList al=new ArrayList();	
		int avaluacio=0;//avaluacio: 1-->Regla a regla, 2-->Per conseqüent, 3-->Integrada
		int tipusDescriptiva=0;//0-->Univariant, ,1-->Per classes, 2-->Univariant i per classes
		boolean ok=true;
		
		    // llistaVars[0][] - Conté la llista de vars numeriques per univariant
		    // llistaVars[1][] - Conté la llista de vars categoriques per univariant
		    String[][] llistaVars = new String[2][];
		    //llistaVars[0][] - Conté la llista de vars categ de classe per desc. classes 
		    //llistaVars[1][] - Conté la llista de vars numeriques per desc. classes
		    //llistaVars[2][] - Conté la llista de vars categoriques per desc. classes
		    String[][] llistaVarsC = new String[3][];
		    // llistat dels estadistics que s'han de calcular per univariant
		    // llistaEstads[0] - Conté la llista d'estad. numerics per univariant
		    // llistaEstads[1] - Conté la llista d'estad. categorics per univariant
		    Vector[] llistaEstads = new Vector[2];
		    //llistaEstads[0] - Conté la llista d'estad. NN per desc. classes 
		    //llistaEstads[1] - Conté la llista d'estad. NC per desc. classes 
		    //llistaEstads[2] - Conté la llista d'estad. CC per desc. classes 
		    Vector[] llistaEstadsC= new Vector[3];
		    int /*nvarsN,*/ nvarsC, i, err,iNumProps=0;//nvarsC és el nombre de bases de coneixement seleccionades
		    String cmd;
		    Process proc;
		    llistaEstads[0]=new Vector();
    		llistaEstads[1] = new Vector();	
    		llistaEstadsC[0]=new Vector();
    		llistaEstadsC[1]=new Vector();
    		llistaEstadsC[2]=new Vector();
    		llistaEstads[1].add(new Integer(Opcions.DIAGR_BARRES));
    		llistaEstads[1].add(new Integer(Opcions.TAULA_FREQS));
    		llistaEstadsC[0].add(new Integer(Opcions.DESCR_EXTENSIONAL));
    		tipusDescriptiva=3;
    		nvarsC = 1;
		try{
			String snomBC=jTextFieldBCSeleccionada.getText();
			//Object ob=jListBC.getSelectedValue();
			snomBC=snomBC.trim();
			if(snomBC.compareTo("")==0 || snomBC==null)frPare.actualitzarBarraEstat("No s'ha seleccionat una Base de Coneixement",true);
			else{
				if(jRadioButtonRegla.isSelected()){
					avaluacio=1;
					llistaProp=gestor.avaluaReglaARegla(snomBC,jCheckBoxNotacioBinaria .isSelected());
					iNumProps=iNumProps+llistaProp.size();
	    			llistaBC.add(snomBC);
	    			llistaVars[0]=new String[0];
		    		llistaVars[1]=new String[llistaProp.size()];
		    		
		    		llistaVarsC[0]=new String[llistaProp.size()];
		    		llistaVarsC[1]=new String[0];
		    		llistaVarsC[2]=new String[0];
		    		for(int j=0;j<llistaProp.size();j++){
	    				Propietat paux=(Propietat)llistaProp.get(j);
	    				llistaVars[1][j]=paux.obtenirId();			    				
		    			llistaVarsC[0][j]=paux.obtenirId();
	    			}
	    			al=new ArrayList(2);			    			
	    			al.add(llistaVars);
	    			al.add(llistaVarsC);
	    			llistaVariables.add(al);			    	
				}else if(jRadioButtonConsequent.isSelected()){
					avaluacio=2;
					llistaProp=gestor.avaluaConsequent(snomBC,jCheckBoxNotacioBinaria .isSelected());
					iNumProps=iNumProps+llistaProp.size();
	    			llistaBC.add(snomBC);
	    			llistaVars[0]=new String[0];
		    		llistaVars[1]=new String[llistaProp.size()];
		    		
		    		llistaVarsC[0]=new String[llistaProp.size()];
		    		llistaVarsC[1]=new String[0];
		    		llistaVarsC[2]=new String[0];
		    		for(int j=0;j<llistaProp.size();j++){
	    				Propietat paux=(Propietat)llistaProp.get(j);
	    				llistaVars[1][j]=paux.obtenirId();			    				
		    			llistaVarsC[0][j]=paux.obtenirId();
	    			}
	    			al=new ArrayList(2);			    			
	    			al.add(llistaVars);
	    			al.add(llistaVarsC);
	    			llistaVariables.add(al);			    
				}else{//Integrat
					avaluacio=3;
					String snom=jTextFieldNomBC.getText().trim();
					llistaProp=gestor.avaluaIntegrat(snomBC,jCheckBoxEnriquit.isSelected(),snom);
					iNumProps=iNumProps+llistaProp.size();
	    			llistaBC.add(snomBC);
	    			llistaVars[0]=new String[0];
		    		llistaVars[1]=new String[llistaProp.size()];
		    		
		    		llistaVarsC[0]=new String[llistaProp.size()];
		    		llistaVarsC[1]=new String[0];
		    		llistaVarsC[2]=new String[0];
		    		for(int j=0;j<llistaProp.size();j++){
	    				Propietat paux=(Propietat)llistaProp.get(j);
	    				llistaVars[1][j]=paux.obtenirId();			    				
		    			llistaVarsC[0][j]=paux.obtenirId();
	    			}
	    			al=new ArrayList(2);			    			
	    			al.add(llistaVars);
	    			al.add(llistaVarsC);
	    			llistaVariables.add(al);			    
				}	
				frPare.actualitzarBarraEstat("S'ha avaluat la Base de Coneixement correctament",false);
				if(jCheckBoxDescriptiva.isSelected()){
					ok=gestor.ferDescripBC(llistaBC,llistaVariables,llistaEstads,llistaEstadsC,opcUniv,opcClass,avaluacio,tipusDescriptiva,0,false, "Avaluador");
					if (ok) {
	    				  				
	    				matriuActual=gestor.idMatriuActual();
	    				String nom = new File(nomFitxer).getName();
	    				frPare.actualitzarBarraEstat(
	    						"S'ha realitzat l'anàlisi descriptiva correctament. (Resultats al fitxer: " + nom + "Univ.tex)", false);
	    				try {
	    					String pathResult = gestor.obtenirDirResultats();
	    					File dirResult = new File(pathResult);
	    					//pathResult = pathResult + nom + "dreg";
	    					pathResult = pathResult + nom + "Avaluador";

	    					cmd = SO.obtenirCmdCompilaLtx(pathResult + ".tex");
	    					if (cmd == null) {
	    						/** @todo També es podria obrir una finestra */
	    						frPare.actualitzarBarraEstat(
	    								"No es pot compilar ni visualitzar automàticament el fitxer Latex generat per no haver-se reconegut el sistema operatiu.", true);
	    					}
	    					else {
	    						logger.finer("Comanda compilació (exec): " + cmd);
	    						proc = Runtime.getRuntime().exec(cmd, null, dirResult);
	    						try {
	    							// error?
	    							StreamGobbler errorGobbler = new
	    							StreamGobbler(proc.getErrorStream(), "ERROR (exec)");

	    							// output?
	    							StreamGobbler outputGobbler = new
	    							StreamGobbler(proc.getInputStream(), "OUTPUT (exec)");

	    							errorGobbler.start();
	    							outputGobbler.start();

	    							err = proc.waitFor();
	    							if (err == 0) {
	    								cmd = SO.obtenirCmdVisorLtx(pathResult + ".dvi");
	    								logger.finer("Comanda visor (exec): " + cmd);
	    								Runtime.getRuntime().exec(cmd, null, dirResult);
	    							}
	    							else {
	    								frPare.actualitzarBarraEstat(
	    										"No s'ha pogut compilar correctament el fitxer Latex generat. (Error " + err + ")" , true);
	    							}
	    						}catch (InterruptedException exc) {
	    							frPare.actualitzarBarraEstat("S'ha interrumput el procés de compilació del fitxer Latex ( InterruptedException " +
	                                             exc.getMessage() + " )", true);
	    						}
	    					}
	    				}
	    				catch (IOException exc) {
	    					frPare.actualitzarBarraEstat(
	    							"Error al generar i visualitzar el latex ( IOException " +
	    							exc.getMessage() + " )", true);
	    				}
	    			}
	    			else {
	    				frPare.actualitzarBarraEstat(
	    						"No ha estat possible realitzar l'anàlisi descriptiva.", true);
	    			}
				}
			}
			
		}catch(ArrayIndexOutOfBoundsException ex){
			frPare.actualitzarBarraEstat("No s'ha pogut avaluar la Base de Coneixement:alguna regla utilitza variables que no existeixen a la matriu de dades", true);
		}catch(Exception ex){
			ex.printStackTrace();
			frPare.actualitzarBarraEstat("No s'ha pogut avaluar la Base de Coneixement: "+ex.getMessage(),true);	
		}		
	}

}

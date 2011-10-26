package jklass.iu;
import jklass.util.AnchorConstraint;
import jklass.util.AnchorLayout;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
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
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListModel;

import jklass.nucli.BaseConeixementProb;
import jklass.nucli.GestorKlass;
import jklass.nucli.LlistaBC;
import jklass.nucli.Propietat;
import jklass.util.Opcions;
import jklass.util.SO;
/** Classe que dibuixa el submenú per seleccionar les descriptives a realitzar i el mètode d'avaluació que s'aplicarà
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
public class PanelDescriptivaBC extends javax.swing.JPanel {
	private static Logger logger=Logger.getLogger(PanelDescriptivaBC.class.getName());
	private JPanel jPanel1;
	private JButton jButtonPerDefecte;
	private JList jListBCSeleccionades;
	private JScrollPane jScrollPaneBCSeleccionades;
	private JButton jButtonEsquerra;
	private JButton jButtonDreta;
	private JPanel jPanelBotons;
	private JList jListBC;
	private JRadioButton jRadioButtonPerConsequent=new JRadioButton();
	private JCheckBox jCheckBoxEnriquit=new JCheckBox();
	private JCheckBox jCBDiagBar=new JCheckBox();
	private JSeparator jSeparator3;
	private JRadioButton jRadioButtonIntegrat=new JRadioButton();
	private JSeparator jSeparator2;
	private JRadioButton jRadioButtonReglaARegla=new JRadioButton();
	private JCheckBox jCheckBoxDesarResultats=new JCheckBox();;
	private JPanel jPanel6;
	private JButton jBOpcDescrExt;
	private JButton jBOpcTFreq;
	private JButton jBOpcDiagBar;
	private JCheckBox jCBDescrExt=new JCheckBox();
	private JCheckBox jCBTaulaFreq=new JCheckBox();
	private JPanel jPanel5;
	private JPanel jPanel4;
	private JSeparator jSeparator1;
	private JPanel jPanelOpcions;
	private JPanel jPanelMetodes;
	private JPanel jPanel3;
	private JScrollPane jScrollPaneLlistaBC;
	private JButton jButtonCancel;
	private JButton jButtonOK;
	private JPanel jPanel2;
	FrPrincipal frPare;
	GestorKlass gestor;
	ButtonGroup group = new ButtonGroup();
	DefaultListModel jListBCModel = new DefaultListModel();
	DefaultListModel jListBCSeleccionadesModel = new DefaultListModel();
	Opcions opcUniv = new Opcions(Opcions.UNIVARIANT);
	String nomFitxer = null;
	Opcions opcClass = new Opcions(Opcions.PER_CLASSES);
	private int matriuCarregada;
	private int matriuActual;
	/**
	 * Constructor
	 * @param fr, finestra pare
	 * @param gk,, GestorKlass
	 */
	public PanelDescriptivaBC(FrPrincipal fr,GestorKlass gk) {
		super();
		frPare = fr;
		gestor = gk;
		jCBTaulaFreq.setSelected(true);
		opcUniv.posarPerDefecteBC(Opcions.TAULA_FREQS);
		jCBDiagBar.setSelected(true);
		opcUniv.posarPerDefecteBC(Opcions.DIAGR_BARRES);
		jCBDescrExt.setSelected(true);
	    opcClass.posarPerDefecteBC(Opcions.DESCR_EXTENSIONAL);
		nomFitxer = frPare.obtenirNomDades();
		String[] idBC=gestor.obtenirLlistaNomsBC();	
		jRadioButtonIntegrat.setSelected(true);
		if(idBC.length==0){//no hi ha bases de coneixement creades		
			jListBCModel=new DefaultListModel();		
		}else{
			for(int i=0;i<idBC.length;i++){
				jListBCModel.insertElementAt(idBC[i],i);
			}
			
		}
		 try{
		    	initGUI();
		    }catch(Exception ex){
		    	ex.printStackTrace();
		    }
	}
	/***
	 * Dibuixa el menú
	 *
	 */
	private void initGUI() {
		try {
			{
				jRadioButtonIntegrat.setBounds(7, 84, 84, 28);
			}
			{
				jRadioButtonPerConsequent.setBounds(7, 35, 98, 28);
			}
			{
				jRadioButtonReglaARegla.setBounds(7, 7, 98, 21);
			}
			{
				jRadioButtonReglaARegla.setBounds(14, 14, 98, 21);
			}
			{
				jRadioButtonPerConsequent.setBounds(14, 42, 161, 28);
			}
			matriuCarregada=gestor.idMatriuActual();
			frPare.setSize(660,500);
			frPare.centrar();
			this.setPreferredSize(new java.awt.Dimension(681, 421));
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.0, 0.1};
			thisLayout.rowHeights = new int[] {357, 7};
			thisLayout.columnWeights = new double[] {0.0};
			thisLayout.columnWidths = new int[] {686};
			this.setLayout(thisLayout);
			this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED), "Descriptiva de Bases de coneixement", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
			{
				jPanel1 = new JPanel();
				GridBagLayout jPanel1Layout = new GridBagLayout();
				jPanel1Layout.rowWeights = new double[] {0.0, 0.1};
				jPanel1Layout.rowHeights = new int[] {321, 20};
				jPanel1Layout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1};
				jPanel1Layout.columnWidths = new int[] {147, 75, 132, 7};
				jPanel1.setLayout(jPanel1Layout);
				this.add(jPanel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jPanel1.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				{
					jScrollPaneLlistaBC = new JScrollPane();
					jScrollPaneLlistaBC.setAutoscrolls(true);
					jPanel1.add(jScrollPaneLlistaBC, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jScrollPaneLlistaBC.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Llista de BC's", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
					jScrollPaneLlistaBC.setFont(new java.awt.Font("Dialog",0,10));
					{
						/*ListModel jListBCModel = new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });*/
						jListBC = new JList();
						jScrollPaneLlistaBC.setViewportView(jListBC);
						jListBC.setModel(jListBCModel);
						jListBC.setPreferredSize(new java.awt.Dimension(305, 578));
					}
				}
				{
					jPanelBotons = new JPanel();
					jPanel1.add(jPanelBotons, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelBotons.setLayout(null);
					{
						jButtonDreta = new JButton();
						jPanelBotons.add(jButtonDreta);
						jButtonDreta.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
						jButtonDreta.setBounds(7, 77, 63, 42);
						jButtonDreta.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e){
						    	  jButtonDreta_actionPerformed(e);
						      }
						});
					}
					{
						jButtonEsquerra = new JButton();
						jPanelBotons.add(jButtonEsquerra);
						jButtonEsquerra.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
						jButtonEsquerra.setBounds(7, 168, 63, 42);
						jButtonEsquerra.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e){
						    	  jButtonEsquerra_actionPerformed(e);
						      }
						});
					}
				}
				{
					jScrollPaneBCSeleccionades = new JScrollPane();
					jScrollPaneBCSeleccionades.setAutoscrolls(true);
					jPanel1.add(jScrollPaneBCSeleccionades, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jScrollPaneBCSeleccionades.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "BC's seleccionades", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
					{
						/*ListModel jListBCSeleccionadesModel = new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });*/
						jListBCSeleccionades = new JList();
						jScrollPaneBCSeleccionades
							.setViewportView(jListBCSeleccionades);
						jListBCSeleccionades
							.setModel(jListBCSeleccionadesModel);
						jListBCSeleccionades.setPreferredSize(new java.awt.Dimension(207, 524));
					}
				}
				{
					jPanel3 = new JPanel();
					GridBagLayout jPanel3Layout = new GridBagLayout();
					jPanel3Layout.rowWeights = new double[] {0.0, 0.1};
					jPanel3Layout.rowHeights = new int[] {152, 7};
					jPanel3Layout.columnWeights = new double[] {0.1};
					jPanel3Layout.columnWidths = new int[] {7};
					jPanel3.setLayout(jPanel3Layout);
					jPanel1.add(jPanel3, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						jPanelMetodes = new JPanel();
						GridBagLayout jPanelMetodesLayout = new GridBagLayout();
						jPanelMetodesLayout.rowWeights = new double[] {0.1};
						jPanelMetodesLayout.rowHeights = new int[] {7};
						jPanelMetodesLayout.columnWeights = new double[] {0.0, 0.1};
						jPanelMetodesLayout.columnWidths = new int[] {191, 7};
						jPanel3.add(jPanelMetodes, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPanelMetodes.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED), "Mètode d'avaluació de les bases de coneixement", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
						jPanelMetodes.setLayout(jPanelMetodesLayout);
						
						{
							jPanel4 = new JPanel();
							jPanelMetodes.add(jPanel4, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							jPanel4.setLayout(null);
							{
								/*jRadioButtonReglaARegla = new JRadioButton();*/
								jPanel4.add(jRadioButtonReglaARegla);
								jRadioButtonReglaARegla.setText("Regla a regla");
								jRadioButtonReglaARegla.setBounds(14, 14, 98, 21);
								group.add(jRadioButtonReglaARegla);
							}
							{
								/*jRadioButtonPerConsequent = new JRadioButton();*/
								jPanel4.add(jRadioButtonPerConsequent);
								jRadioButtonPerConsequent.setText("Per conseqüent");
								jRadioButtonPerConsequent.setBounds(14, 42, 189, 28);
								group.add(jRadioButtonPerConsequent);
							}
							{
								jSeparator2 = new JSeparator();
								jPanel4.add(jSeparator2);
								jSeparator2.setBounds(7, 77, 189, 14);
							}
							{
								/*jRadioButtonIntegrat = new JRadioButton();*/
								jPanel4.add(jRadioButtonIntegrat);
								jRadioButtonIntegrat.setText("Integrat");
								jRadioButtonIntegrat.setBounds(14, 84, 84, 28);
								group.add(jRadioButtonIntegrat);
							}
						}
						{
							jPanel5 = new JPanel();
							jPanelMetodes.add(jPanel5, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							jPanel5.setLayout(null);
							{
								
								jPanel5.add(jCheckBoxEnriquit);
								jCheckBoxEnriquit.setText("Enriquit");
								jCheckBoxEnriquit.setBounds(7, 84, 63, 28);
							}
							{
								jSeparator3 = new JSeparator();
								jPanel5.add(jSeparator3);
								jSeparator3.setBounds(0, 77, 98, 14);
							}
						}
					}
					{
						jPanelOpcions = new JPanel();
						jPanel3.add(jPanelOpcions, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPanelOpcions.setLayout(null);
						jPanelOpcions.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED), "Descriptiva de l'avaluació", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
						{
							
							jPanelOpcions.add(jCBDiagBar);
							jCBDiagBar.setText("Diagrama de barres");
							jCBDiagBar.setBounds(21, 28, 126, 21);
							jCBDiagBar.addItemListener(new java.awt.event.ItemListener() {
							      public void itemStateChanged(ItemEvent e) {
							        jCBDiagBar_itemStateChanged(e);
							      }
							    });
						}
						{
							
							jPanelOpcions.add(jCBTaulaFreq);
							jCBTaulaFreq.setText("Taula de freqüències");
							jCBTaulaFreq.setBounds(21, 70, 140, 28);
							jCBTaulaFreq.addItemListener(new java.awt.event.ItemListener() {
							      public void itemStateChanged(ItemEvent e) {
							        jCBTaulaFreq_itemStateChanged(e);
							      }
							    });
						}
						{
							
							jPanelOpcions.add(jCBDescrExt);
							jCBDescrExt.setText("Descripció extensional");
							jCBDescrExt.setBounds(21, 119, 133, 28);
							jCBDescrExt.addItemListener(new java.awt.event.ItemListener() {
							      public void itemStateChanged(ItemEvent e) {
							        jCBDescrExt_itemStateChanged(e);
							      }
							    });
						}
						{
							jBOpcDiagBar = new JButton();
							jPanelOpcions.add(jBOpcDiagBar);
							jBOpcDiagBar.setText("Opcions");
							jBOpcDiagBar.setBounds(203, 21, 77, 28);
							jBOpcDiagBar.setMnemonic('0');
							jBOpcDiagBar.setRolloverEnabled(false);							
							jBOpcDiagBar.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							        jBOpcDiagBar_actionPerformed(e);
							      }
							    });
						}
						{
							jBOpcTFreq = new JButton();
							jPanelOpcions.add(jBOpcTFreq);
							jBOpcTFreq.setText("Opcions");
							jBOpcTFreq.setBounds(203, 70, 77, 28);
							jBOpcTFreq.setRolloverEnabled(false);						
						    jBOpcTFreq.setMnemonic('0');
							jBOpcTFreq.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							        jBOpcTFreq_actionPerformed(e);
							      }
							    });
						}
						{
							jBOpcDescrExt = new JButton();
							jPanelOpcions.add(jBOpcDescrExt);
							jBOpcDescrExt.setText("Opcions");						
							jBOpcDescrExt.setBounds(203, 119, 77, 28);
							jBOpcDescrExt.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							        jBOpcDescrExt_actionPerformed(e);
							      }
							    });
						}
					}
				}
				{
					jPanel6 = new JPanel();
					jPanel1.add(jPanel6, new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanel6.setLayout(null);
					{
						
						jPanel6.add(jCheckBoxDesarResultats);
						jCheckBoxDesarResultats.setText("Desar resultats a la matriu de dades");
						jCheckBoxDesarResultats.setBounds(7, 7, 238, 21);
					}
				}
			}
			{
				jPanel2 = new JPanel();
				this.add(jPanel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jPanel2.setLayout(null);
				{
					jButtonPerDefecte = new JButton();
					jPanel2.add(jButtonPerDefecte);
					jButtonPerDefecte.setText("Per defecte");
					jButtonPerDefecte.setBounds(63, 7, 105, 28);
					jButtonPerDefecte.addActionListener(new java.awt.event.ActionListener() {
					      public void actionPerformed(ActionEvent e){
					    	  jButtonPerDefecte_actionPerformed(e);
					      }
					});
				}
				{
					jButtonOK = new JButton();
					jPanel2.add(jButtonOK);
					jButtonOK.setText("D'acord");
					jButtonOK.setBounds(322, 7, 84, 28);
					jButtonOK.addActionListener(new java.awt.event.ActionListener() {
					      public void actionPerformed(ActionEvent e){
					    	  jButtonOK_actionPerformed(e);
					      }
					});
				}
				{
					jButtonCancel = new JButton();
					jPanel2.add(jButtonCancel);
					jButtonCancel.setText("Cancel.la");
					jButtonCancel.setBounds(448, 7, 91, 28);
					jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
					      public void actionPerformed(ActionEvent e){
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
	 * Tanca la finestra
	 * @param e, event que detecta que s'ha premut el botó <code>Cancel.la</code>
	 */
	protected void jButtonCancel_actionPerformed(ActionEvent e) {
		frPare.remove(this);
	    frPare.validate();
	    frPare.repaint();		
	}
	/**
	 * Realitza la descriptiva definida de les bases de coneixement seleccionades i
	 * aplicant el mètode avaluatiu indicat
	 * @param e, event que detecta que s'ha premut el botó <code>D'acord</code>
	 */
	protected void jButtonOK_actionPerformed(ActionEvent e) {
		ArrayList llistaBC=new ArrayList();//conté els noms de les BC's avaluades
		ArrayList llistaVariables=new ArrayList();//conté llistes, que alhora contenen els vectors de variables
		int avaluacio=0;//avaluacio: 1-->Regla a regla, 2-->Per conseqüent, 3-->Integrada
		int tipusDescriptiva=0;//0-->Univariant, ,1-->Per classes, 2-->Univariant i per classes
		boolean ok=true;
		
		    // llistaVars[0][] - Conté la llista de vars numeriques per univariant
		    // llistaVars[1][] - Conté la llista de vars categoriques per univariant
		    
		    //llistaVars[0][] - Conté la llista de vars categ de classe per desc. classes 
		    //llistaVars[1][] - Conté la llista de vars numeriques per desc. classes
		    //llistaVars[2][] - Conté la llista de vars categoriques per desc. classes
		   
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
    		
    		if ( jCBDiagBar.isSelected()){
    			tipusDescriptiva=1;
    			llistaEstads[1].add(new Integer(Opcions.DIAGR_BARRES));
    		}		    			
    		if ( jCBTaulaFreq.isSelected() ){
    			tipusDescriptiva=1;
    			llistaEstads[1].add(new Integer(Opcions.TAULA_FREQS));
    		}
    		if (jCBDescrExt.isSelected()) {
    			tipusDescriptiva=2;
    			llistaEstadsC[0].add(new Integer(Opcions.DESCR_EXTENSIONAL));
    		}
    		if((jCBDiagBar.isSelected()||jCBTaulaFreq.isSelected())&& jCBDescrExt.isSelected()){
    			tipusDescriptiva=3;
    		}
		    nvarsC = jListBCSeleccionadesModel.getSize();
		    /*nvarsC = listModelSC.getSize();*/
		    if ((nvarsC == 0)) {
		      frPare.actualitzarBarraEstat("S'ha de seleccionar alguna variable per cada tipus d'estadístic seleccionat", true);
		    } else if(llistaEstads[1].size()==0 && llistaEstadsC[0].size()==0){
		    	frPare.actualitzarBarraEstat("S'ha de seleccionar algun estadístic",true);
		    }else{
		    	try{
		    		if(jRadioButtonReglaARegla.isSelected()){
		    			avaluacio=1;
			    		for(i=0;i<nvarsC;i++){
			    			String nomBC=(String)jListBCSeleccionadesModel.get(i);			    			
			    			ArrayList llistaProp=gestor.avaluaReglaARegla(nomBC,false);	
			    			if ( gestor.obtenirBC(nomBC) instanceof BaseConeixementProb )
			    				iNumProps=iNumProps+llistaProp.size()*2;
			    			else iNumProps=iNumProps+llistaProp.size();
			    			llistaBC.add(nomBC);
			    			String[][] llistaVars = new String[2][];
			    			llistaVars[0]=new String[0];
				    		llistaVars[1]=new String[llistaProp.size()];
				    		String[][] llistaVarsC = new String[3][];
				    		llistaVarsC[0]=new String[llistaProp.size()];
				    		llistaVarsC[1]=new String[0];
				    		llistaVarsC[2]=new String[0];
				    		for(int j=0;j<llistaProp.size();j++){
			    				Propietat paux=(Propietat)llistaProp.get(j);
			    				llistaVars[1][j]=paux.obtenirId();			    				
				    			llistaVarsC[0][j]=paux.obtenirId();
			    			}
			    			ArrayList al=new ArrayList(2);			    			
			    			al.add(llistaVars);
			    			al.add(llistaVarsC);
			    			llistaVariables.add(al);	
			    			
			    		}			    					    		
			    		
			    	}else if(jRadioButtonPerConsequent.isSelected()){
			    		avaluacio=2;
			    		for(i=0;i<nvarsC;i++){
			    			String nomBC=(String)jListBCSeleccionadesModel.get(i);			    		
			    			ArrayList llistaProp=gestor.avaluaConsequent(nomBC,false);		
			    			iNumProps=iNumProps+llistaProp.size();
			    			llistaBC.add(nomBC);
			    			String[][] llistaVars = new String[2][];
			    			llistaVars[0]=new String[0];
				    		llistaVars[1]=new String[llistaProp.size()];
				    		String[][] llistaVarsC = new String[3][];
				    		llistaVarsC[0]=new String[llistaProp.size()];
				    		llistaVarsC[1]=new String[0];
				    		llistaVarsC[2]=new String[0];
				    		for(int j=0;j<llistaProp.size();j++){
			    				Propietat paux=(Propietat)llistaProp.get(j);
			    				llistaVars[1][j]=paux.obtenirId();
				    			llistaVarsC[0][j]=paux.obtenirId();
			    			}
				    		ArrayList al=new ArrayList();
			    			al.add(llistaVars);
			    			al.add(llistaVarsC);
			    			llistaVariables.add(al);
			    		
			    		}	
			    	}else{
			    		avaluacio=3;
			    		for(i=0;i<nvarsC;i++){
			    			String nomBC=(String)jListBCSeleccionadesModel.get(i);			    			
			    			ArrayList llistaProp=gestor.avaluaIntegrat(nomBC,jCheckBoxEnriquit.isSelected(),null);
			    			iNumProps=iNumProps+llistaProp.size();
			    			llistaBC.add(nomBC);
			    			String[][] llistaVars = new String[2][];
			    			llistaVars[0]=new String[0];
				    		llistaVars[1]=new String[llistaProp.size()];
				    		String[][] llistaVarsC = new String[3][];
				    		llistaVarsC[0]=new String[llistaProp.size()];
				    		llistaVarsC[1]=new String[0];
				    		llistaVarsC[2]=new String[0];
				    		for(int j=0;j<llistaProp.size();j++){
			    				Propietat paux=(Propietat)llistaProp.get(j);
			    				llistaVars[1][j]=paux.obtenirId();
				    			llistaVarsC[0][j]=paux.obtenirId();
			    			}
				    		ArrayList al=new ArrayList();
			    			al.add(llistaVars);
			    			al.add(llistaVarsC);
			    			llistaVariables.add(al);
			    		}				    		
			    	}	    		    		
		    			
		    			ok=gestor.ferDescripBC(llistaBC,llistaVariables,llistaEstads,llistaEstadsC,opcUniv,opcClass,avaluacio,tipusDescriptiva,0,false, "DescripBC");
		    			if (ok) {
		    				if(!jCheckBoxDesarResultats.isSelected()){
		    					gestor.eliminarColumnes(iNumProps);			    	   			
		    				}   				
		    				matriuActual=gestor.idMatriuActual();
		    				String nom = new File(nomFitxer).getName();
		    				//frPare.actualitzarBarraEstat("S'ha realitzat l'anàlisi descriptiva correctament. (Resultats al fitxer: " + nom + "dreg.tex)", false);
		    				frPare.actualitzarBarraEstat("S'ha realitzat l'anàlisi descriptiva correctament. (Resultats al fitxer: " + nom + "DescripBC.tex)", false);
		    				
		    				try {
		    					String pathResult = gestor.obtenirDirResultats();
		    					File dirResult = new File(pathResult);
		    					//pathResult = pathResult + nom + "dreg";
		    					pathResult = pathResult + nom + "DescripBC";

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
		     
		    			frPare.remove(this);
		    			frPare.validate();
		    			frPare.repaint();
		    		
		    	}catch(ArrayIndexOutOfBoundsException ex){
					frPare.actualitzarBarraEstat("No s'ha pogut avaluar la Base de Coneixement:alguna regla utilitza variables que no existeixen a la matriu de dades", true);
				}
		    	catch(Exception ex){
	    			frPare.actualitzarBarraEstat("No s'ha pogut avaluar la base de coneixement",true);
	    			ex.printStackTrace();
	    		}	
		    }
		
	}
	/**
	 * Actualitza la finestra seleccionant les opcions per defecte
	 * @param e, event que detecta que s'ha premut el botó <code>Per defecte</code>
	 */
	protected void jButtonPerDefecte_actionPerformed(ActionEvent e) {
		  int n = JOptionPane.showConfirmDialog(
                  this, "Aquesta opció selecciona tots els estadístics posant a TOTS les seves opcions per defecte. Segur que vols continuar?",
                  "Assignació d'opcions per defecte",
                  JOptionPane.YES_NO_OPTION);

		  if (n == JOptionPane.YES_OPTION) {			  
			  jCBTaulaFreq.setSelected(true);
			  opcUniv.posarPerDefecteBC(Opcions.TAULA_FREQS);
			  jCBDiagBar.setSelected(true);
			  opcUniv.posarPerDefecteBC(Opcions.DIAGR_BARRES);
			  jCBDescrExt.setSelected(true);
		      opcClass.posarPerDefecteBC(Opcions.DESCR_EXTENSIONAL);
		  }	
		
	}
	/**
	 * Afegeix la base/S de coneixement seleccionada a la llista de bases de coneixement seleccionades i l'elimina
	 * de la llista de bases de coneixement
	 * @param e, event que detecta que s'ha premut el botó <code>Seleccionar base de coneixement (mà cap a la dreta) </code>
	 */
	protected void jButtonDreta_actionPerformed(ActionEvent e) {
		 Object[] vars = jListBC.getSelectedValues();
		    boolean hay_mas_vars = true;
		    int n = 0;
		    do {
		      try {
		    	jListBCSeleccionadesModel.addElement(vars[n]);
		        jListBCModel.removeElement(vars[n]);
		        n++;
		      }
		      catch (Exception exc) { hay_mas_vars = false; }
		    } while (hay_mas_vars);
		
	}
	/**
	 * Afegeix la base/s de coneixement seleccionada de la llista de BC's seleccionades a la llista de Bases de coneixement i 
	 * l'elimina de la llista de BC's seleccionades
	 * @param e, event que detecta que s'ha premut el botó <code>Deseleccionar base de coneixement (mà cap a l'esquerra) </code>
	 */
	protected void jButtonEsquerra_actionPerformed(ActionEvent e) {
		Object[] vars = jListBCSeleccionades.getSelectedValues();
	    boolean hay_mas_vars = true;
	    int n = 0;
	    do {
	      try {
	    	jListBCModel.addElement(vars[n]);
	    	jListBCSeleccionadesModel.removeElement(vars[n]);
	        n++;
	      }
	      catch (Exception exc) { hay_mas_vars = false; }
	    } while (hay_mas_vars);
		
	}
	/**
	 * Posa les opcions particulars per defecte de la descriptiva taula de freqüències quan aquesta és seleccionada
	 * @param e, event que detecta que s'ha seleccionat l'opció <code>Taula de freqüències</code>
	 */
	void jCBTaulaFreq_itemStateChanged(ItemEvent e) {
	    boolean selec;

	    selec = jCBTaulaFreq.isSelected();
	    if (selec){
	      opcUniv.posarPerDefecte(Opcions.TAULA_FREQS);
	    } else {
	      opcUniv.eliminarOpcions(Opcions.TAULA_FREQS);
	    }
	    jBOpcTFreq.setEnabled(selec);
	  }
	/**
	 * Posa les opcions particulars per defecte de la descriptiva Diagrama de barres quan aquesta és seleccionada
	 * @param e, event que detecta que s'ha seleccionat l'opció <code>Diagrama de barres</code>
	 */
	  void jCBDiagBar_itemStateChanged(ItemEvent e) {
	    boolean selec;

	    selec = jCBDiagBar.isSelected();
	    if (selec){
	      opcUniv.posarPerDefecte(Opcions.DIAGR_BARRES);
	    } else {
	      opcUniv.eliminarOpcions(Opcions.DIAGR_BARRES);
	    }

	    jBOpcDiagBar.setEnabled(selec);
	  }
	  /**
	   * Mostra el menú de les opcions concretes de la descriptiva<code>Taula de freqüències</code>
	   * @param e, event que detecta que s'ha premut el botó <code>Opcions</code> associat a la descriptiva <code>Taula de freqüències</code> 
	   */
	  void jBOpcTFreq_actionPerformed(ActionEvent e) {
		    DlgOpcTFreq dlg = new DlgOpcTFreq(frPare, "Opcions per a la taula de freqüències",true, opcUniv,true);
		    dlg.setLocationRelativeTo(this);
		    dlg.show();
		  }
	  /**
	   * Mostra el menú de les opcions concretes de la descriptiva<code>Diagrama de barres</code>
	   * @param e, event que detecta que s'ha premut el botó <code>Opcions</code> associat a la descriptiva <code>Diagrama de barres</code> 
	   */
	  void jBOpcDiagBar_actionPerformed(ActionEvent e) {
		    DlgOpcDiagBar dlg = new DlgOpcDiagBar(frPare, "Opcions per al diagrama de barres",true, opcUniv);
		    dlg.setLocationRelativeTo(this);
		    dlg.show();
		  }
	  /**
		 * Posa les opcions particulars per defecte de la descriptiva Descripció extensional quan aquesta és seleccionada
		 * @param e, event que detecta que s'ha seleccionat l'opció <code>Descripció extensional</code>
		 */
	  void jCBDescrExt_itemStateChanged(ItemEvent e) {
		    boolean selec;

		    selec = jCBDescrExt.isSelected();
		    if (selec){
		      opcClass.posarPerDefecteBC(Opcions.DESCR_EXTENSIONAL);
		    } else {
		      opcClass.eliminarOpcions(Opcions.DESCR_EXTENSIONAL);
		    }
		    jBOpcDescrExt.setEnabled(selec);
		  }
	  /**
	   * Mostra el menú de les opcions concretes de la descriptiva<code>Descripció extensional</code>
	   * @param e, event que detecta que s'ha premut el botó <code>Opcions</code> associat a la descriptiva <code>Descripció extensional</code> 
	   */
	  void jBOpcDescrExt_actionPerformed(ActionEvent e) {
		    DlgOpcDescrExtClass dlg = new DlgOpcDescrExtClass(frPare, "Opcions per a la descripció extensional",true, opcClass,true);
		    dlg.setLocationRelativeTo(this);
		    dlg.show();
		  }

}

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
import javax.swing.JFormattedTextField;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
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
public class PanelPodarRegles extends javax.swing.JPanel {
	private static Logger logger=Logger.getLogger(PanelDescriptivaBC.class.getName());
	private JPanel jPanel1;
	private JList jListBCSeleccionades;
	private JScrollPane jScrollPaneBCSeleccionades;
	private JButton jButtonEsquerra;
	private JLabel jLabel1;
	private JTextField jTextFieldLlindarSuperior;
	private JLabel jLabelLlindarProbabilitat;
	private JTextField jLLindarProbabilitatTextField;
	private JButton jButtonDreta;
	private JPanel jPanelBotons;
	private JList jListBC;
	private JPanel jPanel6;
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
	public PanelPodarRegles(FrPrincipal fr,GestorKlass gk) {
		super();
		frPare = fr;
		gestor = gk;
		opcUniv.posarPerDefecteBC(Opcions.TAULA_FREQS);

		opcUniv.posarPerDefecteBC(Opcions.DIAGR_BARRES);

	    opcClass.posarPerDefecteBC(Opcions.DESCR_EXTENSIONAL);
		nomFitxer = frPare.obtenirNomDades();
		String[] idBC=gestor.obtenirLlistaNomsBC();	

		if(idBC.length==0){//no hi ha bases de coneixement creades		
			jListBCModel=new DefaultListModel();		
		}else{
			for(int i=0;i<idBC.length;i++){
				if ( gestor.obtenirBC(idBC[i]) instanceof BaseConeixementProb ){
					jListBCModel.insertElementAt(idBC[i],i);
				}
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
		
			matriuCarregada=gestor.idMatriuActual();
			frPare.setSize(660,500);
			frPare.centrar();
			this.setPreferredSize(new java.awt.Dimension(423, 421));
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.0, 0.1};
			thisLayout.rowHeights = new int[] {357, 7};
			thisLayout.columnWeights = new double[] {0.0};
			thisLayout.columnWidths = new int[] {686};
			this.setLayout(thisLayout);
			this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED), "Podar regles probabilitzades", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
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
				jPanel1.setPreferredSize(new java.awt.Dimension(413, 357));
				{
					jScrollPaneLlistaBC = new JScrollPane();
					jScrollPaneLlistaBC.setAutoscrolls(true);
					jPanel1.add(jScrollPaneLlistaBC, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jScrollPaneLlistaBC.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Llista de BC's", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
					jScrollPaneLlistaBC.setFont(new java.awt.Font("Dialog",0,10));
					jScrollPaneLlistaBC.setPreferredSize(new java.awt.Dimension(164, 321));
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
					jScrollPaneBCSeleccionades.setPreferredSize(new java.awt.Dimension(161, 321));
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
					jLLindarProbabilitatTextField = new JTextField();
					jPanel1.add(jLLindarProbabilitatTextField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLLindarProbabilitatTextField.setPreferredSize(new java.awt.Dimension(49, 21));
					jLLindarProbabilitatTextField.setMinimumSize(new java.awt.Dimension(35, 21));
				}
				{
					jLabelLlindarProbabilitat = new JLabel();
					jPanel1.add(jLabelLlindarProbabilitat, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelLlindarProbabilitat.setText("Llindar inferior:");
				}
				{
					jTextFieldLlindarSuperior = new JTextField();
					jPanel1.add(jTextFieldLlindarSuperior, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jTextFieldLlindarSuperior.setPreferredSize(new java.awt.Dimension(49, 21));
					jTextFieldLlindarSuperior.setMinimumSize(new java.awt.Dimension(35, 21));
				}
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabel1.setText("Llindar Superior:");
				}
			}
			{
				jPanel2 = new JPanel();
				this.add(jPanel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jPanel2.setLayout(null);
				{
				
				}
				{
					jButtonOK = new JButton();
					jPanel2.add(jButtonOK);
					jButtonOK.setText("D'acord");
					jButtonOK.setBounds(12, 7, 84, 28);
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
					jButtonCancel.setBounds(299, 7, 91, 28);
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
		
		
		String llindarMinimText = jLLindarProbabilitatTextField.getText();
		String llindarMaximText = jTextFieldLlindarSuperior.getText();
		
		float llindarMinim = 0;
		float llindarMaxim =  1;
		
		try{
			if ( llindarMinimText != null ) llindarMinim = Float.parseFloat(llindarMinimText);
			if ( llindarMaximText != null ) llindarMaxim = Float.parseFloat(llindarMaximText);
		}
		catch (Exception e1){
			frPare.actualitzarBarraEstat("Els llindars han de estar establits entre 0 i 1", true);
			return;
		}
		if ( llindarMinim < 0 || llindarMinim > 1 || llindarMaxim < 0 || llindarMaxim > 1 ){
			frPare.actualitzarBarraEstat("Els llindars han de estar establits entre 0 i 1", true);
			return;
		}
		if ( llindarMinim > llindarMaxim ){
			frPare.actualitzarBarraEstat("El llindar minim ha de tenir un valor inferior que el llindar màxim.", true);
			return;
		}
		if ( jListBCSeleccionadesModel.size()==0 ){
			frPare.actualitzarBarraEstat("S'ha de seleccionar, al menys, una base de coneixement probabilitzada", true);
			return;
		}
		
		int nvarsC = jListBCSeleccionadesModel.getSize();
		for(int i=0;i<nvarsC;i++){
			String nomBC=(String)jListBCSeleccionadesModel.get(i);
			try {
				gestor.crearBCProb(llindarMinim, llindarMaxim, nomBC);
			} catch (Exception e1) {
				e1.printStackTrace();
				frPare.actualitzarBarraEstat("No s'han creat correctament les bases de coneixement podades", true);
			}
		}
		
		frPare.actualitzarBarraEstat("S'han creat correctament les bases de coneixement podades", false);
		frPare.remove(this);
		frPare.validate();
		frPare.repaint();
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
	   * Mostra el menú de les opcions concretes de la descriptiva<code>Descripció extensional</code>
	   * @param e, event que detecta que s'ha premut el botó <code>Opcions</code> associat a la descriptiva <code>Descripció extensional</code> 
	   */
	  void jBOpcDescrExt_actionPerformed(ActionEvent e) {
		    DlgOpcDescrExtClass dlg = new DlgOpcDescrExtClass(frPare, "Opcions per a la descripció extensional",true, opcClass,true);
		    dlg.setLocationRelativeTo(this);
		    dlg.show();
		  }

}

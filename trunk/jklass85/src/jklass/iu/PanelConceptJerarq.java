package jklass.iu;


import java.awt.BorderLayout;
//import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
//import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import jklass.nucli.*;
import jklass.util.Opcions;
import jklass.util.SO;

/**
 * This class shows the panel for the Hierarchical Conceptualization process.
 * This can be found at "Interpretació -> Conceptualització Jeràrquica" menu.
 * 
 * @author Esther Lozano
 *
 */
@SuppressWarnings("serial")
public class PanelConceptJerarq extends JPanel 
{
	private static Logger logger=Logger.getLogger(PanelUnivariant.class.getName());

	Opcions opcClass = new Opcions(Opcions.PER_CLASSES);

	BorderLayout borderLayout1 = new BorderLayout();
	FrPrincipal frPare;
	GestorKlass gestor;
	TitledBorder titledBorder1;
	JPanel jPanelBotones1 = new JPanel();
	JPanel jPanelBotones2 = new JPanel();
	JPanel jPanelVarNum = new JPanel();
	JPanel jPanelVarCat = new JPanel();
	BorderLayout borderLayout2 = new BorderLayout();
	Border border2;
	TitledBorder titledBorder2;
	Border border3;
	Border border4;
	TitledBorder titledBorder3;
	Border border5;
	TitledBorder titledBorder4;
	Border border6;
	TitledBorder titledBorder5;
	DefaultListModel listModelVN = new DefaultListModel();
	JList jListVarsN = new JList(listModelVN);
	DefaultListModel listModelSN = new DefaultListModel();
	JList jListSelcN = new JList(listModelSN);
	Border border7;
	TitledBorder titledBorder6;
	DefaultListModel listModelVC = new DefaultListModel();
	JList jListVarsC = new JList(listModelVC);
	DefaultListModel listModelSC = new DefaultListModel();
	JList jListSelcC = new JList(listModelSC);
	FlowLayout flowLayout2 = new FlowLayout();
	JButton jBttnDefecte = new JButton();
	JButton jBttnCancel = new JButton();
	JPanel jPanTancar = new JPanel();
	JButton jBttnOK = new JButton();
	FlowLayout flowLayout3 = new FlowLayout();
	FlowLayout flowLayout4 = new FlowLayout();
	String nomFitxer = null;
	Border border8;
	TitledBorder titledBorder7;
	Border border9;
	TitledBorder titledBorder8;
	JButton jBttnAfegirC = new JButton();	//add cat. variable button
	JButton jBttnTreureC = new JButton();	//remove cat. variable button
	JPanel jPanelBtnsC = new JPanel();		//cat. vars. buttons panel
	JScrollPane jSPSelecC = new JScrollPane(jListSelcC);	//selected class vars
	BorderLayout borderLayout6 = new BorderLayout();
	JPanel jPanelSelecCat = new JPanel();	//selected cat. vars. panel
	JPanel jPanelVarsC = new JPanel();		//cat. variables panel
	JPanel jPanelCat = new JPanel();		//panel with JPanelSeleccat and JPanelSelecClass
	BorderLayout borderLayout5 = new BorderLayout();
	JPanel jPanLlistes = new JPanel();
	JScrollPane jSPVarsN = new JScrollPane(jListVarsN);	//available num. variables
	JScrollPane jSPVarsC = new JScrollPane(jListVarsC);	//available cat. vars.
	FlowLayout flowLayout1 = new FlowLayout();
	JPanel jPanelSelecNum = new JPanel();	//selected num. vars. panel
	JScrollPane jSPSelecN = new JScrollPane(jListSelcN);	//selected num. vars.
	BorderLayout borderLayout4 = new BorderLayout();
	JButton jBttnAfegirN = new JButton();
	private JCheckBox jCheckBoxRevisat;
	private JCheckBox jCheckBoxGenerarMKMZ;
	private JCheckBox jCheckBoxInformacioBC;
	private JCheckBox jCheckBoxDescripcioBC; // Jordi PM
	private JCheckBox jCheckBoxFinalBC;
	//private JCheckBox jCheckBoxCriteria;
	//private JRadioButton jRadioButtonSimple = new JRadioButton();
	private JRadioButton jRadioButtonBLnCWA = new JRadioButton();
	private JRadioButton jRadioButtonBLCWA = new JRadioButton();
	private JRadioButton jRadioButtonBGCWA = new JRadioButton();
	//NUEVO ALBERTO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private JRadioButton jRadioButtonBLpCWA = new JRadioButton();
	private JRadioButton jRadioButtonBLGCWA = new JRadioButton();
	private JCheckBox jCheckBoxSimple;
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	private JPanel jPanel1;
	private JPanel jPanel2;
	JButton jBttnTreureN = new JButton();	//remove num. vars. button
	JPanel jPanelBtnsN = new JPanel();		//num. vars. buttons panel
	Border border10;
	TitledBorder titledBorder9;
	BorderLayout borderLayout7 = new BorderLayout();
	Border border11;
	TitledBorder titledBorder10;
	
	DefaultListModel listModelD = new DefaultListModel();
	JList jListDendo = new JList(listModelD);
	DefaultListModel listModelSD = new DefaultListModel();
	JList jListSDendo = new JList(listModelSD);
	JPanel jPanelDendogram = new JPanel();	//dendogram panel
	JPanel jPanelBtnsD = new JPanel();		//dendogram buttons panel
	JButton jBttnAfegirD = new JButton();	//add dendogram button
	JButton jBttnTreureD = new JButton();	//remove dendogram button
	JScrollPane jSPDendo = new JScrollPane(jListDendo);	//dendograms
	JScrollPane jSPSelecD = new JScrollPane(jListSDendo);	//selected dendograms
	TitledBorder titledBorder11;
	TitledBorder titledBorder12;
	TitledBorder titledBorder13;
	JPanel jPanelDendo = new JPanel();		//panel with jSPDendo, jSPSelecD and buttons
	JPanel jPanelTall = new JPanel();	//tree cutting options buttons panel
	JPanel jPanelTallClass = new JPanel();	//number of classes panel
	JPanel jPanelClassName = new JPanel();	//name of the class variable
	JTextField jTFClass = new JTextField(2);	//number of classes to cut the tree
	JLabel jLabelNClass = new JLabel();
	TitledBorder titledBorder14;
	String nomArbre;						//name of cut tree variable
	JTextField jTFNomClasse = new JTextField();	//name of the new class variable
	JLabel jLblNomClasse = new JLabel();
	int indexClass;
	ButtonGroup group = new ButtonGroup();
	

	//Constructor
	public PanelConceptJerarq(FrPrincipal fr, GestorKlass gk) 
	{
		String[][] llProps;
		int lon;

		frPare = fr;
		gestor = gk;
		nomFitxer = frPare.obtenirNomDades();
		llProps = gestor.obtenirLlistaIDsProps();
		
		lon = llProps[0].length;
		for (int i = 0; i < lon; i++) 
		{
			listModelVN.insertElementAt(llProps[0][i], i);
		}
		
		lon = llProps[1].length;
		indexClass = lon;
		for (int i = 0; i < lon; i++) 
		{
			listModelVC.insertElementAt(llProps[1][i], i);
		}
		
		lon = llProps[2].length;
		for (int i = 0; i < lon; i++) 
		{
				listModelVC.insertElementAt(llProps[2][i], i);
		}
		
		lon = llProps[3].length;
		for (int i = 0; i < lon; i++) 
		{
			listModelVC.insertElementAt(llProps[3][i], i);
		}
		
		String [] llArbres = gestor.obtenirLlistaArbres();
		lon = llArbres.length;
		for(int i = 0; i < lon; i++)
		{
			listModelD.insertElementAt(llArbres[i], i);
		}
		
	    try {
	      jbInit();
		} catch (Exception ex) { ex.printStackTrace(); }
	}

	void jbInit() throws Exception 
	{
		//NUEVO ALBERTO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		frPare.setSize(1000,470);
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		frPare.centrar();
		titledBorder1 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, Color.white, new Color(156, 156, 158)),
		"Conceptualització jeràrquica (CCEC)");
		border8 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white, new Color(109, 109,
				110), new Color(156, 156, 158));
		titledBorder7 = new TitledBorder(border8, "Vars. Categ.");
		border9 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white, new Color(109, 109,
				110), new Color(156, 156, 158));
		titledBorder8 = new TitledBorder(border9, "Vars. categ. selecc.");
		border10 = BorderFactory.createEtchedBorder(Color.white, new Color(156, 156, 158));
		titledBorder9 = new TitledBorder(border10, "Càlculs a realitzar");	
		border2 = new EtchedBorder(EtchedBorder.RAISED, Color.white, new Color(178, 178, 178));
		titledBorder2 = new TitledBorder(border2, "Llista de variables");
		border3 = BorderFactory.createCompoundBorder(titledBorder2, BorderFactory.createEmptyBorder(2, 2, 2, 2));
		border4 = BorderFactory.createEmptyBorder();
		titledBorder3 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(156, 156, 158)),
				"Vars. numèriques");
		border5 = BorderFactory.createEmptyBorder();
		titledBorder4 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(156, 156, 158)),
				"Vars. categòriques");
		border6 = BorderFactory.createLineBorder(Color.white, 1);
		titledBorder5 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white,
				new Color(109, 109, 110), new Color(156, 156, 158)), "Vars. Num.");
		border7 = new EtchedBorder(EtchedBorder.RAISED, Color.white, new Color(156, 156, 158));
		titledBorder6 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white,
				new Color(109, 109, 110), new Color(156, 156, 158)), "Vars. num. selecc.");
		titledBorder11 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(156, 156, 158)),
		"Dendogrames");
		titledBorder12 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white,
				new Color(109, 109, 110), new Color(156, 156, 158)), "Dends.");
		titledBorder13 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white,
				new Color(109, 109, 110), new Color(156, 156, 158)), "Dends. selecc.");
		titledBorder14 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(156, 156, 158)),
		"Tall de l'arbre");
		this.setLayout(borderLayout1);
		jPanelVarNum.setLayout(new BorderLayout());
	    jPanelVarCat.setLayout(new BorderLayout());
	    jPanelDendogram.setLayout(new BorderLayout());
	    jPanelTall.setLayout(new BorderLayout());
	    jPanelVarNum.setBorder(titledBorder3);
	    jPanelVarCat.setBorder(titledBorder4);
	    jPanelDendogram.setBorder(titledBorder11);
	    jPanelTall.setBorder(titledBorder14);
		jListVarsN.setBorder(null);
		jListVarsC.setBorder(null);
		jListDendo.setBorder(null);
		
		this.setBorder(titledBorder1);
		this.setDebugGraphicsOptions(0);
		this.setPreferredSize(new java.awt.Dimension(532, 567));
		this.setEnabled(false);
		{
			jPanel1 = new JPanel();
			jPanelVarNum.add(jPanel1, null);
			jPanel1.setPreferredSize(new java.awt.Dimension(218, 49));
			jPanel1.setLayout(null);
			jPanel1.add(jPanelBotones1);
		}
		{
			jPanel2 = new JPanel();
			jPanelVarCat.add(jPanel2, null);
			jPanel2.setPreferredSize(new java.awt.Dimension(30, 19));
			jPanel2.setLayout(null);
			jPanel2.add(jPanelBotones2);
		}
		flowLayout2.setAlignment(FlowLayout.RIGHT);

		jBttnDefecte.setText("Per defecte");
		jBttnDefecte.addActionListener(new java.awt.event.ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        jBttnDefecte_actionPerformed(e);
		      }
		    });
		jBttnDefecte.setHorizontalAlignment(SwingConstants.LEFT);
		jBttnCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBttnCancel_actionPerformed(e);
			}
		});
		jBttnCancel.setText("Cancel·la");
		jPanTancar.setLayout(flowLayout2);
		jBttnOK.setText("D'acord");
		jBttnOK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBttnOK_actionPerformed(e);
			}
		});
		jPanelBotones1.setLayout(flowLayout3);
		flowLayout3.setHgap(75);
		
		jPanelBotones2.setLayout(flowLayout4);
		flowLayout4.setHgap(75);

		jBttnAfegirC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
		jBttnAfegirC.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBttnAfegirC_actionPerformed(e);
			}
		});
		jBttnTreureC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
		jBttnTreureC.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBttnTreureC_actionPerformed(e);
			}
		});
		jPanLlistes.setLayout(borderLayout5);
		jPanLlistes.setBorder(null);
		
		jSPVarsN.getViewport().setBackground(Color.white);
		jSPVarsN.setAutoscrolls(true);
		jSPVarsN.setBorder(titledBorder5);
		jSPVarsN.setPreferredSize(new Dimension(100, 153));
		
		jSPVarsC.setBorder(titledBorder7);
		jSPVarsC.setOpaque(true);
		jSPVarsC.setPreferredSize(new Dimension(100, 153));
		jSPVarsC.setRequestFocusEnabled(true);
		jSPVarsC.setAutoscrolls(true);
		
		jSPDendo.setAutoscrolls(true);
		jSPDendo.setPreferredSize(new Dimension(100, 153));
		jSPDendo.setBorder(titledBorder12);
		
		jPanelSelecNum.setLayout(flowLayout1);
		jSPSelecN.setAutoscrolls(true);
		jSPSelecN.setBorder(titledBorder6);
		jSPSelecN.setPreferredSize(new Dimension(115, 153));
		
		jPanelBtnsC.setLayout(borderLayout6);
		jSPSelecC.setBorder(titledBorder8);
		jSPSelecC.setPreferredSize(new Dimension(115, 153));
		jSPSelecC.setAutoscrolls(true);
		
		jSPSelecD.setAutoscrolls(true);
		jSPSelecD.setPreferredSize(new Dimension(100, 153));
		jSPSelecD.setBorder(titledBorder13);
		
		jPanelCat.setLayout(new BorderLayout());
		jPanelVarsC.setLayout(new BorderLayout());
		jBttnAfegirN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
		jBttnAfegirN.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBttnAfegirN_actionPerformed(e);
			}
		});
		jBttnTreureN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
		jBttnTreureN.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBttnTreureN_actionPerformed(e);
			}
		});
		jPanelBtnsN.setLayout(borderLayout4);
		
		borderLayout5.setVgap(6);
		
		//initialization of text field (number of classes)
		jLabelNClass.setText("Nº classes a interpretar:");
		
		jTFClass.setMinimumSize(new Dimension(40, 21));
		jTFClass.setPreferredSize(new Dimension(25, 21));
	    jTFClass.setCaretPosition(0);
	    jTFClass.setText("3");
	    jTFClass.setColumns(3);
	    
	  //initialization of text field (name of cut)
	    jLblNomClasse.setRequestFocusEnabled(true);
	    jLblNomClasse.setText("Prefix particions successives:");
	  jTFNomClasse.setMinimumSize(new Dimension(80, 21));
	  jTFNomClasse.setPreferredSize(new Dimension(100, 21));
	    jTFNomClasse.setRequestFocusEnabled(true);
	    jTFNomClasse.setText("NovaClasse");
	    jTFNomClasse.setColumns(15);
	    
		//Buttons for dendograms
		jPanelBtnsD.setLayout(new BorderLayout());
		jBttnAfegirD.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
		jBttnAfegirD.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBttnAfegirD_actionPerformed(e);
			}
		});
		jBttnTreureD.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
		jBttnTreureD.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBttnTreureD_actionPerformed(e);
			}
		});
		
		jPanelSelecNum.add(jSPVarsN, null);
		jPanelSelecNum.add(jPanelBtnsN, null);
		jPanelBtnsN.add(jBttnAfegirN, BorderLayout.NORTH);
		jPanelBtnsN.add(jBttnTreureN, BorderLayout.SOUTH);
		jPanelSelecNum.add(jSPSelecN, null);
		
		jPanelBotones1.setBounds(58, 58, 413, 39);
		{
			jCheckBoxRevisat = new JCheckBox();
			jPanel1.add(jCheckBoxRevisat);
			jCheckBoxRevisat.setText("Inducció basada en Boxplots revisada");
			jCheckBoxRevisat.setBounds(40, 10, 250, 15);
			jCheckBoxRevisat.setEnabled(true);
			jCheckBoxRevisat.setSelected(true);
		}
		{
			jPanel1.add(jRadioButtonBGCWA);
			jRadioButtonBGCWA.setText("BG&CWA");
			jRadioButtonBGCWA.setBounds(40, 40, 80, 15);
			group.add(jRadioButtonBGCWA);
			//NUEVO ALBERTO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			jRadioButtonBGCWA.setSelected(true);
			jRadioButtonBGCWA.addActionListener(new java.awt.event.ActionListener() 
			{
			      public void actionPerformed(ActionEvent e) {
			        jRadioButton_actionPerformed(e);
			      }
			});
		}
		{
			jCheckBoxSimple = new JCheckBox();
			jPanel1.add(jCheckBoxSimple);
			jCheckBoxSimple.setText("Simple");
			jCheckBoxSimple.setBounds(120, 60, 80, 15);
			jCheckBoxSimple.setEnabled(false);
			//jCheckBoxSimple.setBounds(60, 60, 250, 17);
		}
		//{
		//	jPanel1.add(jRadioButtonSimple);
		//	jRadioButtonSimple.setText("BL&noCWA simple");
		//	jRadioButtonSimple.setBounds(40, 40, 250, 16);
		//	group.add(jRadioButtonSimple);
		//}
		{
			jPanel1.add(jRadioButtonBLnCWA);
			jRadioButtonBLnCWA.setText("BL&noCWA");
			jRadioButtonBLnCWA.setBounds(40, 60, 80, 15);
			group.add(jRadioButtonBLnCWA);
			//jRadioButtonBLnCWA.setSelected(true);
			jRadioButtonBLnCWA.addActionListener(new java.awt.event.ActionListener() 
			{
			      public void actionPerformed(ActionEvent e) {
			        jRadioButton_actionPerformed(e);
			      }
			});
			//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		}
		{
			jPanel1.add(jRadioButtonBLCWA);
			jRadioButtonBLCWA.setText("BL&CWA");
			jRadioButtonBLCWA.setBounds(40, 80, 80, 15);
			group.add(jRadioButtonBLCWA);
			//NUEVO ALBERTO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			jRadioButtonBLCWA.addActionListener(new java.awt.event.ActionListener() 
			{
			      public void actionPerformed(ActionEvent e) {
			        jRadioButton_actionPerformed(e);
			      }
			});
		}
		{
			jPanel1.add(jRadioButtonBLpCWA);
			jRadioButtonBLpCWA.setText("BL&partial-CWA");
			jRadioButtonBLpCWA.setBounds(40, 100, 160, 15);
			group.add(jRadioButtonBLpCWA);
			jRadioButtonBLpCWA.addActionListener(new java.awt.event.ActionListener() 
			{
			      public void actionPerformed(ActionEvent e) {
			        jRadioButton_actionPerformed(e);
			      }
			});
		}
		{
			jPanel1.add(jRadioButtonBLGCWA);
			jRadioButtonBLGCWA.setText("BL+G&CWA");
			jRadioButtonBLGCWA.setBounds(40, 120, 160, 15);
			group.add(jRadioButtonBLGCWA);
			jRadioButtonBLGCWA.addActionListener(new java.awt.event.ActionListener() 
			{
			      public void actionPerformed(ActionEvent e) {
			        jRadioButton_actionPerformed(e);
			      }
			});
		}
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		jPanelSelecCat.add(jSPVarsC, null);
		jPanelSelecCat.add(jPanelBtnsC, null);
		jPanelBtnsC.add(jBttnAfegirC, BorderLayout.NORTH);
		jPanelBtnsC.add(jBttnTreureC, BorderLayout.SOUTH);
		jPanelSelecCat.add(jSPSelecC, null);
		
		{
			jCheckBoxInformacioBC = new JCheckBox();
			jPanel2.add(jCheckBoxInformacioBC);
			jCheckBoxInformacioBC.setText("Generar informació BC intermèdies");
			jCheckBoxInformacioBC.setSelected(true); // Jordi PM
			jCheckBoxInformacioBC.setBounds(10, 40, 190, 16);
		}
		// Jordi PM >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		{
			jCheckBoxDescripcioBC = new JCheckBox();
			jPanel2.add(jCheckBoxDescripcioBC);
			jCheckBoxDescripcioBC.setText("Amb descriptiva");
			jCheckBoxDescripcioBC.setBounds(200, 40, 110, 16);
		}
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		{
			jCheckBoxFinalBC = new JCheckBox();
			jPanel2.add(jCheckBoxFinalBC);
			jCheckBoxFinalBC.setText("Visualitzar informe");
			jCheckBoxFinalBC.setBounds(10, 20, 250, 15);
			jCheckBoxFinalBC.setSelected(true);
		}
		{
			jCheckBoxGenerarMKMZ = new JCheckBox();
			jPanel2.add(jCheckBoxGenerarMKMZ);
			jCheckBoxGenerarMKMZ.setText("Generar fitxers .mk i .zk");
			jCheckBoxGenerarMKMZ.setBounds(10, 60, 250, 17);
			jCheckBoxGenerarMKMZ.setEnabled(true);
		}
		
		jPanTancar.add(jBttnOK, null);
		jPanTancar.add(jBttnCancel);
		jPanelBotones1.add(jBttnDefecte, null);
		jPanelBotones1.add(jPanTancar, null);
		
		jPanelVarNum.add(jPanelSelecNum,  BorderLayout.NORTH);
		jPanelVarCat.add(jPanelSelecCat,  BorderLayout.NORTH);
		
		jPanelBtnsD.add(jBttnAfegirD, BorderLayout.NORTH);
		jPanelBtnsD.add(jBttnTreureD, BorderLayout.SOUTH);
		jPanelDendo.add(jSPDendo, null);
		jPanelDendo.add(jPanelBtnsD, null);
		jPanelDendo.add(jSPSelecD, null);
		jPanelTallClass.add(jLabelNClass, null);
		jPanelTallClass.add(jTFClass, null);
		jPanelClassName.add(jLblNomClasse, null);
		jPanelClassName.add(jTFNomClasse, null);
		jPanelTall.add(jPanelClassName, BorderLayout.NORTH);
		jPanelTall.add(jPanelTallClass, BorderLayout.CENTER);
		jPanelDendogram.add(jPanelDendo, BorderLayout.NORTH);
		jPanelDendogram.add(jPanelTall, BorderLayout.SOUTH);
		
		//Main panel
		this.add(jPanelDendogram,  BorderLayout.WEST);
		this.add(jPanelVarNum,  BorderLayout.CENTER);
	    this.add(jPanelVarCat,  BorderLayout.EAST);
	    this.add(jPanelBotones1,  BorderLayout.SOUTH);
	}
	
	
	void jBttnDefecte_actionPerformed(ActionEvent e) 
	{
	    // Es demana confirmació abans de posar opcions per defecte per tot
	    int n = JOptionPane.showConfirmDialog(
	        this,
	        "Aquesta opció selecciona les opcions per defecte. Segur que vols continuar?",
	        "Assignació d'opcions per defecte",
	        JOptionPane.YES_NO_OPTION);

	    if (n == JOptionPane.YES_OPTION) 
	    {
	    	jTFClass.setEnabled(true);
	    	jTFClass.setText("3");
	    	jTFNomClasse.setText("NovaClasse");
	    	jCheckBoxFinalBC.setSelected(true);
	    	jCheckBoxRevisat.setSelected(true);
	    	jRadioButtonBLCWA.setSelected(true);
	    }
	}

	
	/**
	 * Mètode que s'activa al realitzar l'acció
	 * @param e
	 */
	void jBttnOK_actionPerformed(ActionEvent e) 
	{
		boolean ok = false;
	    
	    // llistaVars[0][] - It contains the list of class variables
		// llistaVars[1][] - It contains the list of numerical variables
		// llistaVars[2][] - It contains the list of categorical variables
		
	    String[][] llistaVars = new String[3][];
		int nvarsN, nvarsC, nDendo;
		
		nvarsN = listModelSN.getSize();
		nvarsC = listModelSC.getSize();
		nDendo = listModelSD.getSize();

		//BEGIN [01M - Interpretacio CCEC –Indicacio de l’estat] 20100310
		frPare.actualitzarBarraEstat("Calculant interpretació.", false);
	    frPare.repaint();
		//END [01M - Interpretacio CCEC –Indicacio de l’estat] 20100310
		
		if(nDendo != 1)
		{
			frPare.actualitzarBarraEstat("S'ha de seleccionar un únic dendograma. Si no existeix cap es pot crear fent una classificació de les dades.", true);
		}
		else
		{
			nomArbre = (String) listModelSD.get(0);
			
			if ((nvarsC == 0) && (nvarsN == 0)) 
			{
				frPare.actualitzarBarraEstat("S'ha de seleccionar alguna variable numèrica o categòrica", true);
			} 
			else 
			{
				String nomClass = jTFNomClasse.getText();
				String numClass = jTFClass.getText();
				
				if(gestor.obtenirLListaPropietats().existeixPropietat(nomClass+numClass))
				{
					frPare.actualitzarBarraEstat("El nom de la classe ja existeix a la matriu.", true);
				}
				else
				{
					llistaVars[0] = new String[1];		//there is only a class variable. It will be inicialized after integration
					llistaVars[1] = new String[nvarsN];
					llistaVars[2] = new String[nvarsC];
						
					for (int i = 0; i < nvarsN; i++) 
					{
						llistaVars[1][i] = (String) listModelSN.get(i);
					}
					for (int i = 0; i < nvarsC; i++) 
					{
						llistaVars[2][i] = (String) listModelSC.get(i);
					}
		
					String nomTall = nomArbre +"K"+ jTFClass.getText().trim();
					
					Vector results = new Vector();
					Vector<Regla> rules = new Vector<Regla>();

					//Get the KiMethod selected
					int crt = this.getKnowledgeIntegrationMethod();
					
					ArrayList llistaBC = new ArrayList();
					
					/*ok = gestor.obtenirConceptJerarq(llistaVars, numClass, nomTall, frPare.obtenirDirActual(), frPare.obtenirNomDades(),
						nomClass, jCheckBoxGenerarMKMZ.isSelected(), jCheckBoxRevisat.isSelected(), results, rules, 
						jCheckBoxInformacioBC.isSelected(), crt);*/
					
					ok = gestor.obtenirConceptJerarq(llistaVars, numClass, nomTall, frPare.obtenirDirActual(), frPare.obtenirNomDades(),
							nomClass, jCheckBoxGenerarMKMZ.isSelected(), jCheckBoxRevisat.isSelected(), results, rules, 
							jCheckBoxInformacioBC.isSelected(), crt,llistaBC);
				
					if (ok) 
				    {
				    	frPare.actualitzarBarraEstat("S'ha realitzat la conceptualització jeràrquica correctament.", false);
				    	
				    	try
				    	{	
					    	BaseConeixement bc = gestor.crearBC("CCEC"+nomClass);
				    		
				    		for(int i = 0; i < rules.size(); i++)
				    		{
				    			Regla r = rules.get(i);
				    			
				    			r.setNomRegla("r"+((int)i+1)+".BC"+i+"."+r.getNomRegla());
				    			r.setGestor(gestor.matriusCarregades[gestor.obteniractual()]);
				    			bc.afegirRegla(r);
				    		}
				    		
				    		llistaBC.add(bc.getNomBC()); // TEMP!
				    		
				    		generarDescrip(llistaBC, nomClass, jCheckBoxFinalBC.isSelected());
				    		
				    	}
				    	catch(Exception e1)
				    	{
				    		e1.printStackTrace();
							frPare.actualitzarBarraEstat("Error al generar i visualitzar el latex ( Exception "
									+ e1.getMessage() + " )", true);
				    	}
				    }
				    else 
				    {
				      frPare.actualitzarBarraEstat("No ha estat possible realitzar la conceptualització jeràrquica", true);
		
				    }
				    frPare.remove(this);
				    frPare.validate();
				    frPare.repaint();
				}
			}
		}
	}
	
	
	/**
	 * Function that generates a file with description of the given knowledge bases.
	 * 
	 * @param bc - Final knowledge base
	 * @param nomClass - name of the new class generated in the process
	 * @param visual - boolean that indicates if the file must be displayed or not after creation
	 * 
	 * @return boolean indicating success of the function
	 */
	boolean generarDescrip(ArrayList nomsBc, String nomClass, boolean visual)
	{
		boolean ok = true;
		int esPart1 = 1;
		int esPart2 = 3;
		
		// JORDI >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		if(jCheckBoxInformacioBC.isSelected())
		{
			// En funcio si amb descripcio es seleccionat tancaré el fitxer o no.
			esPart1 = ( jCheckBoxDescripcioBC.isSelected() ) ? 2 : 3;
		}
		else
			esPart1 = ( jCheckBoxDescripcioBC.isSelected() ) ? 1 : 0;
		//esPart1 = ( jCheckBoxDescripcioBC.isSelected() ) ? 2 : 3;
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		try
		{
			int err;
			
			Vector<Integer>[] llistaEstads = new Vector[2];
			Vector<Integer>[] llistaEstadsC = new Vector[3];
			llistaEstads[0] = new Vector<Integer>();
			llistaEstads[1] = new Vector<Integer>();
			llistaEstadsC[0] = new Vector<Integer>();
			llistaEstadsC[1] = new Vector<Integer>();
			llistaEstadsC[2] = new Vector<Integer>();
			
			// JORDI >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			llistaEstads[1].add(new Integer(Opcions.DIAGR_BARRES));
			llistaEstads[1].add(new Integer(Opcions.TAULA_FREQS));
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			llistaEstadsC[0].add(new Integer(Opcions.DESCR_EXTENSIONAL));

			int iNumProps = 0;
			//ArrayList llistaBC = new ArrayList();
			//ArrayList llistaBC2 = new ArrayList();
			
			ArrayList llistaVariables = new ArrayList();

			ArrayList llistaProp;
			
			String nomBC = (String)nomsBc.get(nomsBc.size() - 1);
			//String nomBC = bc.getNomBC();
			//llistaBC.add(nomBC);

			llistaProp = gestor.avaluaReglaARegla(nomBC, false);

			iNumProps = iNumProps + llistaProp.size();
			String[][] llistaVarsDescr = new String[2][];
			llistaVarsDescr[0] = new String[0];
			llistaVarsDescr[1] = new String[llistaProp.size()];
			String[][] llistaVarsCDescr = new String[3][];
			llistaVarsCDescr[0] = new String[llistaProp.size()];
			llistaVarsCDescr[1] = new String[0];
			llistaVarsCDescr[2] = new String[0];
			
			for (int j = 0; j < llistaProp.size(); j++) 
			{
				Propietat paux = (Propietat) llistaProp.get(j);
				llistaVarsDescr[1][j] = paux.obtenirId();
				llistaVarsCDescr[0][j] = paux.obtenirId();
			}
			
			for (int j = 0; j < nomsBc.size(); j++) { //TEMP!!
				ArrayList al = new ArrayList(2);
				al.add(llistaVarsDescr);
				al.add(llistaVarsCDescr);
				llistaVariables.add(al);
			}
			/*ArrayList al = new ArrayList(2);
			al.add(llistaVarsDescr);
			al.add(llistaVarsCDescr);
			llistaVariables.add(al);*/
			
			Opcions opcUniv = new Opcions(Opcions.UNIVARIANT);
			opcUniv.posarPerDefecteBC(Opcions.TAULA_FREQS);
			opcUniv.posarPerDefecteBC(Opcions.DIAGR_BARRES);

			Opcions opcClass = new Opcions(Opcions.PER_CLASSES);
			opcClass.posarPerDefecteBC(Opcions.DESCR_EXTENSIONAL);
			
			// JORDI >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			gestor.ferQualitatBC(nomBC, true, true, true, true, true, true, esPart1, "CCEC");
			
			if ( jCheckBoxDescripcioBC.isSelected() )	{
				//llistaBC2.clear();
			
				//llistaBC2.add(nomBC);
				/*gestor.ferDescripBC(llistaBC, llistaVariables, llistaEstads, llistaEstadsC, opcUniv,
					opcClass, 1, 3, esPart2, false, "CCEC");*/
				gestor.ferDescripBC(nomsBc, llistaVariables, llistaEstads, llistaEstadsC, opcUniv,
						opcClass, 1, 3, esPart2, false, "CCEC");
			}
			/*else if( !jCheckBoxInformacioBC.isSelected() )	{
				gestor.tancarFitxer("CCEC");
			}*/
			
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			gestor.eliminarColumnes(iNumProps);
				
			String nomFitxer = frPare.obtenirNomDades();
			String nom = new File(nomFitxer).getName();

			String cmd;
			Process proc;
				
			String pathResult = gestor.obtenirDirResultats();
			//String outputPath = pathResult + nomBC;
			File dirResult = new File(pathResult);
			// JORDI >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			pathResult = pathResult + nom + "CCEC";
			//pathResult = pathResult + nom + "int";
			//gestor = null;
			//System.gc();

			//cmd = SO.obtenirCmdCompilaLtx(pathResult + ".tex" + " -job-name " + outputPath);
			cmd = SO.obtenirCmdCompilaLtx(pathResult + ".tex");
			
			//Thread.sleep(5000); // TEMP!!
			
			System.out.println("--> " + cmd);
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			if (cmd == null) 
			{
				frPare.actualitzarBarraEstat("No es pot compilar ni visualitzar automàticament el fitxer Latex generat per no haver-se reconegut el sistema operatiu.",
								true);
			} 
			else 
			{
				logger.finer("Comanda compilació (exec): " + cmd);
				proc = Runtime.getRuntime().exec(cmd, null, dirResult);
				
				// error?
				StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(),"ERROR (exec)");

				// output?
				StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(),"OUTPUT (exec)");

				errorGobbler.start();
				outputGobbler.start();

				err = proc.waitFor();
				
				if (err == 0) 
				{
					if(visual)
					{
						// JORDI >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
						//cmd = SO.obtenirCmdVisorLtx(outputPath + ".dvi");
						cmd = SO.obtenirCmdVisorLtx(pathResult + ".dvi");
						// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
						System.out.println("--> " + cmd);
						logger.finer("Comanda visor (exec): " + cmd);
						Runtime.getRuntime().exec(cmd, null, dirResult);
					}
				} 
				else 
				{
					frPare.actualitzarBarraEstat("No s'ha pogut compilar correctament el fitxer Latex generat. (Error "
									+ err + ")", true);
				}
			}
		}
		catch (InterruptedException exc) 
		{
			frPare.actualitzarBarraEstat(
					"S'ha interrumput el procés de compilació del fitxer Latex ( InterruptedException "
							+ exc.getMessage() + " )", true);
		}
		catch (IOException exc) 
		{
			frPare.actualitzarBarraEstat("Error al generar i visualitzar el latex ( IOException "
					+ exc.getMessage() + " )", true);
		}
		catch (Exception e1) 
		{
		e1.printStackTrace();
		frPare.actualitzarBarraEstat("Error al generar i visualitzar el latex ( IOException "
				+ e1.getMessage() + " )", true);
		}
		
		return ok;
	}
	
	
	void jBttnCancel_actionPerformed(ActionEvent e) 
	{
		frPare.remove(this);
		frPare.validate();
		frPare.repaint();
	}

	
	void jBttnAfegirN_actionPerformed(ActionEvent e) 
	{
		Object[] vars = jListVarsN.getSelectedValues();
		boolean hay_mas_vars = true;
		int n = 0;
		
		do {
			try {
				listModelSN.addElement(vars[n]);
				listModelVN.removeElement(vars[n]);
				n++;
			} 
			catch (Exception exc) {	hay_mas_vars = false; }
		} while (hay_mas_vars);
	}

	
	void jBttnTreureN_actionPerformed(ActionEvent e)
	{
		Object[] vars = jListSelcN.getSelectedValues();
		boolean hay_mas_vars = true;
		int n = 0;
		
		do {
			try {
				listModelVN.addElement(vars[n]);
				listModelSN.removeElement(vars[n]);
				n++;
			} 
			catch (Exception exc) {	hay_mas_vars = false; }
		} while (hay_mas_vars);
	}

	
	void jBttnAfegirC_actionPerformed(ActionEvent e) 
	{
		Object[] vars = jListVarsC.getSelectedValues();
		boolean hay_mas_vars = true;
		int n = 0;
		do {
			try {
				listModelSC.addElement(vars[n]);
				listModelVC.removeElement(vars[n]);
				n++;
			} 
			catch (Exception exc) {	hay_mas_vars = false; }
		} while (hay_mas_vars);
	}

	
	void jBttnTreureC_actionPerformed(ActionEvent e) 
	{
		Object[] vars = jListSelcC.getSelectedValues();
		boolean hay_mas_vars = true;
		int n = 0;
		
		do {
			try {
				listModelVC.addElement(vars[n]);
				listModelSC.removeElement(vars[n]);
				n++;
			} 
			catch (Exception exc) {	hay_mas_vars = false; }
		} while (hay_mas_vars);
	}

	
	void jBttnAfegirD_actionPerformed(ActionEvent e) 
	{
		Object[] vars = jListDendo.getSelectedValues();
		boolean hay_mas_vars = true;
		int n = 0;
		
		do {
			try {
				listModelSD.addElement(vars[n]);
				listModelD.removeElement(vars[n]);
				n++;
			} 
			catch (Exception exc) {	hay_mas_vars = false; }
		} while (hay_mas_vars);
	}

	
	void jBttnTreureD_actionPerformed(ActionEvent e) 
	{
		Object[] vars = jListSDendo.getSelectedValues();
		boolean hay_mas_vars = true;
		int n = 0;
		
		do {
			try {
				listModelD.addElement(vars[n]);
				listModelSD.removeElement(vars[n]);
				n++;
			} 
			catch (Exception exc) {	hay_mas_vars = false; }
		} while (hay_mas_vars);
	}

	
	void jBOpcDescrExt_actionPerformed(ActionEvent e) 
	{
		DlgOpcDescrExtClass dlg = new DlgOpcDescrExtClass(frPare, "Opcions per a la descripció extensional", true,
				opcClass);
		dlg.setLocationRelativeTo(this);
		dlg.show();
	}
	
	/**
	 * Get the KiMethod selected
	 * @author Grup SISPD QT 2009-2010
	 * @return
	 */
	private int getKnowledgeIntegrationMethod()
	{
		//NUEVO ALBERTO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		//int tmpKiMethod = GestorKlass.CCEC_BLC_NoCWA_Simple;
		int tmpKiMethod = GestorKlass.CCEC_BGC_CWA;
		
		if(jRadioButtonBLnCWA.isSelected())
		{
			if(jCheckBoxSimple.isSelected())
				tmpKiMethod = GestorKlass.CCEC_BLC_NoCWA_Simple;
			else
				tmpKiMethod = GestorKlass.CCEC_BLC_NoCWA;
		}
		else if(jRadioButtonBLCWA.isSelected())
		{
			tmpKiMethod = GestorKlass.CCEC_BLC_CWA;
		}
		//else if(jRadioButtonBGCWA.isSelected())
		//{
		//	tmpKiMethod = GestorKlass.CCEC_BGC_CWA;
		//}
		else if(jRadioButtonBLpCWA.isSelected())
		{
			tmpKiMethod = GestorKlass.CCEC_BLC_pCWA;
		}
		else if(jRadioButtonBLGCWA.isSelected())
		{
			tmpKiMethod = GestorKlass.CCEC_BLGC_CWA;
		}
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		return tmpKiMethod;
	}
	
	//NUEVO ALBERTO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**
	 * Action listener for the selection of any of the radio buttons for the Boxplot-based induction
	 * 
	 * @author Alberto Martinez Gonzalez
	 * @param e
	 */
	private void jRadioButton_actionPerformed(ActionEvent e) {
		if(jRadioButtonBLnCWA.isSelected()){
			jCheckBoxSimple.setEnabled(true);
		} else if(!jRadioButtonBLnCWA.isSelected()){
			jCheckBoxSimple.setEnabled(false);
			jCheckBoxSimple.setSelected(false);
		}
	}
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
}


package jklass.iu;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;

import jklass.nucli.ExpressioBooleana;
import jklass.nucli.GestorKlass;
import jklass.nucli.LlistaPropietats;
import jklass.nucli.Operacio;
/** Classe que dibuixa el submenú per crear variables numèriques derivades a partir
 * de càlculs aritmètics sobre les variables de la matriu 
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
public class PanelCalculador extends javax.swing.JPanel {
	private JPanel jPanel2;
	private JPanel jPanel1;
	private JPanel jPanelAfegirProp;
	private JLabel jLabelNomFiltre;
	private JTextArea jTextAreaFiltre;
	private JScrollPane jScrollPaneFiltre;
	private JPanel jPanel3;
	private JPanel jPanelBotons;
	private JPanel jPanelNomFiltre;
	private JButton jButtonAfegirProp;
	private JList jListPropietats;
	private JPanel jPanelFiltre;
	private JScrollPane jScrollPanePropietats;
	private JButton jButtonVuit;
	private JButton jButtonSum;
	private JButton jButtonE;
	private JButton jButtonCancel;
	private JButton jButtonOK;
	private JButton jButtonTancarParentesis;
	private JButton jButtonObrirParèntesis;
	private JButton jButtonLogaritmeNeperia;
	private JButton jButtonLogaritme;
	private JButton jButtonExponencial;
	private JButton jButtonPotencia;
	private JButton jButtonArrel;
	private JButton jButtonMultiplicacio;
	private JButton jButtonDivisio;
	private JButton jButtonResta;
	private JButton jButtonPi;
	private JButton jButtonPunt;
	private JButton jButtonZero;
	private JButton jButtonNou;
	private JButton jButtonSet;
	private JButton jButtonSis;
	private JButton jButtonCinc;
	private JButton jButtonQuatre;
	private JButton jButtonTres;
	private JButton jButtonDos;
	private JButton jButtonUn;
	private JTextField jTextFieldNomFiltre;
	FrPrincipal frPare;
	GestorKlass gestor;
	DefaultComboBoxModel  jListPropietatsModel = new DefaultComboBoxModel();
	
	/**
	 * Constructor
	 * @param fr, finestra pare
	 * @param gk, GestorKlass
	 */
	public PanelCalculador(FrPrincipal fr,GestorKlass gk) {
		super();
		frPare = fr;
		gestor = gk;
		String[][] llProps;
	    int lon;
		 llProps = gestor.obtenirLlistaIDsProps();
		    lon = llProps[0].length;//la posició 0 conté les propietats numèriques
		    for (int i = 0; i < lon; i++) {		    	
		    	jListPropietatsModel.insertElementAt(llProps[0][i], i);
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
			frPare.setSize(670,480);
			frPare.centrar();
			this.setPreferredSize(new java.awt.Dimension(652, 403));
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.0, 0.0};
			thisLayout.rowHeights = new int[] {324, 40};
			thisLayout.columnWeights = new double[] {0.0};
			thisLayout.columnWidths = new int[] {640};
			this.setLayout(thisLayout);
			this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED), "Calculadora", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
			{
				jPanel2 = new JPanel();
				this.add(jPanel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jPanel2.setLayout(null);
				{
					jButtonOK = new JButton();
					jPanel2.add(jButtonOK);
					jButtonOK.setText("D'acord");
					jButtonOK.setBounds(133, 7, 70, 28);
					jButtonOK.addActionListener(new java.awt.event.ActionListener() {
					      public void actionPerformed(ActionEvent e) {
					    	  jButtonOK_actionPerformed(e);
					      }
					});
				}
				{
					jButtonCancel = new JButton();
					jPanel2.add(jButtonCancel);
					jButtonCancel.setText("Cancel.la");
					jButtonCancel.setBounds(420, 7, 84, 28);
					jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
					      public void actionPerformed(ActionEvent e) {
					    	  jButtonCancel_actionPerformed(e);
					      }
					});
				}
			}
			{
				jPanel1 = new JPanel();
				GridBagLayout jPanel1Layout = new GridBagLayout();
				this.add(jPanel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				GridBagLayout jPanel2Layout = new GridBagLayout();
				jPanel2Layout.rowWeights = new double[] {0.1};
				jPanel2Layout.rowHeights = new int[] {7};
				jPanel2Layout.columnWeights = new double[] {0.0, 0.0, 0.1};
				jPanel2Layout.columnWidths = new int[] {130, 93, 7};
				jPanel1.setLayout(jPanel2Layout);
				{
					jScrollPanePropietats = new JScrollPane();
					jScrollPanePropietats.setAutoscrolls(true);
					jPanel1.add(jScrollPanePropietats, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jScrollPanePropietats.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Propietats numèriques", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
					jScrollPanePropietats.setPreferredSize(new java.awt.Dimension(130, 316));
					{
						/*ListModel jListPropietatsModel = new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });*/
						jListPropietats = new JList();
						jScrollPanePropietats.setViewportView(jListPropietats);
						jListPropietats.setModel(jListPropietatsModel);
						jListPropietats.setPreferredSize(new java.awt.Dimension(118, 367));
					}
				}
				{
					jPanelAfegirProp = new JPanel();
					jPanel1.add(jPanelAfegirProp, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelAfegirProp.setLayout(null);
					{
						jButtonAfegirProp = new JButton();
						jPanelAfegirProp.add(jButtonAfegirProp);
						jButtonAfegirProp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
						jButtonAfegirProp.setBounds(14, 98, 63, 42);
						jButtonAfegirProp.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e) {
						    	  jButtonAfegirProp_actionPerformed(e);
						      }
						});
					}
				}
				{
					jPanelFiltre = new JPanel();
					GridBagLayout jPanelFiltreLayout = new GridBagLayout();
					jPanelFiltreLayout.rowWeights = new double[] {0.0, 0.1};
					jPanelFiltreLayout.rowHeights = new int[] {163, 7};
					jPanelFiltreLayout.columnWeights = new double[] {0.1};
					jPanelFiltreLayout.columnWidths = new int[] {7};
					jPanelFiltre.setLayout(jPanelFiltreLayout);
					jPanel1.add(jPanelFiltre, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelFiltre.setBorder(BorderFactory.createTitledBorder(null, "Editor", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
					jPanelFiltre.setPreferredSize(new java.awt.Dimension(417, 336));
					{
						jPanelNomFiltre = new JPanel();
						GridBagLayout jPanelNomFiltreLayout = new GridBagLayout();
						jPanelNomFiltreLayout.rowWeights = new double[] {0.0, 0.1};
						jPanelNomFiltreLayout.rowHeights = new int[] {47, 7};
						jPanelNomFiltreLayout.columnWeights = new double[] {0.1};
						jPanelNomFiltreLayout.columnWidths = new int[] {7};
						jPanelNomFiltre.setLayout(jPanelNomFiltreLayout);
						jPanelFiltre.add(jPanelNomFiltre, new GridBagConstraints(-1, -1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						{
							jPanel3 = new JPanel();
							jPanelNomFiltre.add(jPanel3, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							jPanel3.setLayout(null);
							jPanel3.setPreferredSize(new java.awt.Dimension(406, 35));
							{
								jLabelNomFiltre = new JLabel();
								jPanel3.add(jLabelNomFiltre);
								jLabelNomFiltre.setText("Nova propietat:");
								jLabelNomFiltre.setBounds(7, 7, 84, 14);
							}
							{
								jTextFieldNomFiltre = new JTextField();
								jPanel3.add(jTextFieldNomFiltre);
								jTextFieldNomFiltre.setBounds(91, 7, 308, 21);
							}
						}
						{
							jScrollPaneFiltre = new JScrollPane();
							jScrollPaneFiltre.setAutoscrolls(true);
							jPanelNomFiltre.add(jScrollPaneFiltre, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							{
								jTextAreaFiltre = new JTextArea();
								jScrollPaneFiltre
									.setViewportView(jTextAreaFiltre);
								jTextAreaFiltre.setPreferredSize(new java.awt.Dimension(651, 375));
							}
						}
					}
					{
						jPanelBotons = new JPanel();
						GridBagLayout jPanelBotonsLayout = new GridBagLayout();
						jPanelBotonsLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.1};
						jPanelBotonsLayout.rowHeights = new int[] {40, 30, 34, 7};
						jPanelBotonsLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
						jPanelBotonsLayout.columnWidths = new int[] {7, 20, 20, 7, 7, 7};
						jPanelBotons.setLayout(jPanelBotonsLayout);
						jPanelFiltre.add(jPanelBotons, new GridBagConstraints(-1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPanelBotons.setPreferredSize(new java.awt.Dimension(405, 141));
						{
							jButtonUn = new JButton();
							jPanelBotons.add(jButtonUn, new GridBagConstraints(
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
							jButtonUn.setText("1");
							jButtonUn.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonUn_actionPerformed(e);
							      }
							});
						}
						{
							jButtonDos = new JButton();
							jPanelBotons.add(
								jButtonDos,
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
							jButtonDos.setText("2");
							jButtonDos.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonDos_actionPerformed(e);
							      }
							});
						}
						{
							jButtonTres = new JButton();
							jPanelBotons.add(
								jButtonTres,
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
							jButtonTres.setText("3");
							jButtonTres.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonTres_actionPerformed(e);
							      }
							});
						}
						{
							jButtonQuatre = new JButton();
							jPanelBotons.add(
								jButtonQuatre,
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
							jButtonQuatre.setText("4");
							jButtonQuatre.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonQuatre_actionPerformed(e);
							      }
							});
						}
						{
							jButtonCinc = new JButton();
							jPanelBotons.add(
								jButtonCinc,
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
							jButtonCinc.setText("5");
							jButtonCinc.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonCinc_actionPerformed(e);
							      }
							});
						}
						{
							jButtonSis = new JButton();
							jPanelBotons.add(
								jButtonSis,
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
							jButtonSis.setText("6");
							jButtonSis.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonSis_actionPerformed(e);
							      }
							});
						}
						{
							jButtonSet = new JButton();
							jPanelBotons.add(
								jButtonSet,
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
							jButtonSet.setText("7");
							jButtonSet.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonSet_actionPerformed(e);
							      }
							});
						}
						{
							jButtonVuit = new JButton();
							jPanelBotons.add(
								jButtonVuit,
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
							jButtonVuit.setText("8");
							jButtonVuit.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonVuit_actionPerformed(e);
							      }
							});
						}
						{
							jButtonNou = new JButton();
							jPanelBotons.add(
								jButtonNou,
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
							jButtonNou.setText("9");
							jButtonNou.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonNou_actionPerformed(e);
							      }
							});
						}
						{
							jButtonZero = new JButton();
							jPanelBotons.add(
								jButtonZero,
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
							jButtonZero.setText("0");
							jButtonZero.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonZero_actionPerformed(e);
							      }
							});
						}
						{
							jButtonPunt = new JButton();
							jPanelBotons.add(jButtonPunt, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jButtonPunt.setText(".");
							jButtonPunt.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonPunt_actionPerformed(e);
							      }
							});
						}
						{
							jButtonPi = new JButton();
							jPanelBotons.add(jButtonPi, new GridBagConstraints(
								2,
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
							jButtonPi.setText("pi");
							jButtonPi.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonPi_actionPerformed(e);
							      }
							});
						}
						{
							jButtonSum = new JButton();
							jPanelBotons.add(
								jButtonSum,
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
							jButtonSum.setText("+");
							jButtonSum.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonSum_actionPerformed(e);
							      }
							});
						}
						{
							jButtonResta = new JButton();
							jPanelBotons.add(
								jButtonResta,
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
							jButtonResta.setText("-");
							jButtonResta.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonResta_actionPerformed(e);
							      }
							});
						}
						{
							jButtonDivisio = new JButton();
							jPanelBotons.add(
								jButtonDivisio,
								new GridBagConstraints(
									3,
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
							jButtonDivisio.setText("/");
							jButtonDivisio.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonDivisio_actionPerformed(e);
							      }
							});
						}
						{
							jButtonMultiplicacio = new JButton();
							jPanelBotons.add(
								jButtonMultiplicacio,
								new GridBagConstraints(
									3,
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
							jButtonMultiplicacio.setText("x");
							jButtonMultiplicacio.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonMultiplicacio_actionPerformed(e);
							      }
							});
						}
						{
							jButtonArrel = new JButton();
							jPanelBotons.add(
								jButtonArrel,
								new GridBagConstraints(
									4,
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
							jButtonArrel.setText("arrel(x)");
							jButtonArrel.setSize(66, 22);
							jButtonArrel.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonArrel_actionPerformed(e);
							      }
							});
						}
						{
							jButtonPotencia = new JButton();
							jPanelBotons.add(jButtonPotencia, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jButtonPotencia.setText("x^y");
							jButtonPotencia.setPreferredSize(new java.awt.Dimension(66, 22));
							jButtonPotencia.setSize(66, 22);
							jButtonPotencia.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonPotencia_actionPerformed(e);
							      }
							});
						}
						{
							jButtonExponencial = new JButton();
							jPanelBotons.add(jButtonExponencial, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jButtonExponencial.setText("exp(x)");
							jButtonExponencial.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonExponencial_actionPerformed(e);
							      }
							});
						}
						{
							jButtonLogaritme = new JButton();
							jPanelBotons.add(
								jButtonLogaritme,
								new GridBagConstraints(
									5,
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
							jButtonLogaritme.setText("log(x)");
							jButtonLogaritme.setSize(66, 22);
							jButtonLogaritme.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonLogaritme_actionPerformed(e);
							      }
							});
						}
						{
							jButtonLogaritmeNeperia = new JButton();
							jPanelBotons.add(
								jButtonLogaritmeNeperia,
								new GridBagConstraints(
									5,
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
							jButtonLogaritmeNeperia.setText("ln(x)");
							jButtonLogaritmeNeperia.setSize(66, 22);
							jButtonLogaritmeNeperia.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonLogaritmeNeperia_actionPerformed(e);
							      }
							});
						}
						{
							jButtonObrirParèntesis = new JButton();
							jPanelBotons.add(
								jButtonObrirParèntesis,
								new GridBagConstraints(
									5,
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
							jButtonObrirParèntesis.setText("(");
							jButtonObrirParèntesis.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonObrirParèntesis_actionPerformed(e);
							      }
							});
						}
						{
							jButtonTancarParentesis = new JButton();
							jPanelBotons.add(
								jButtonTancarParentesis,
								new GridBagConstraints(
									5,
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
							jButtonTancarParentesis.setText(")");
							jButtonTancarParentesis.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonTancarParentesis_actionPerformed(e);
							      }
							});
						}
						{
							jButtonE = new JButton();
							jPanelBotons.add(jButtonE, new GridBagConstraints(
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
							jButtonE.setText("e");
							jButtonE.setSize(42, 22);
							jButtonE.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonE_actionPerformed(e);
							      }
							});
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Insereix el símbol del valor e [e] a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>e</code>
	 */
	protected void jButtonE_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert("["+Operacio.getE()+"]", jTextAreaFiltre.getCaretPosition());
		
	}
	/**
	 * Insereix el símbol de parèntesis tancat ) a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>)</code>
	 */
	protected void jButtonTancarParentesis_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert(")", jTextAreaFiltre.getCaretPosition());				
		
	}
	/**
	 * Insereix el símbol de parèntesis obert ( a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>(</code>
	 */
	protected void jButtonObrirParèntesis_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert("(", jTextAreaFiltre.getCaretPosition());	
		
	}
	/**
	 * Insereix el símbol de logaritme neperià ln a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>ln(x)</code>
	 */
	protected void jButtonLogaritmeNeperia_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert(Operacio.getLN()+"()", jTextAreaFiltre.getCaretPosition());
		
	}
	/**
	 * Insereix el símbol de logaritme en base 10 log a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>log(x)</code>
	 */
	protected void jButtonLogaritme_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert(Operacio.getLOG()+"()", jTextAreaFiltre.getCaretPosition());
		
	}
	/**
	 * Insereix el símbol d'exponencial exp a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>exp(x)</code>
	 */
	protected void jButtonExponencial_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert(Operacio.getEXP()+"()", jTextAreaFiltre.getCaretPosition());
		
	}
	/**
	 * Insereix el símbol de potència ^ a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>x^y</code>
	 */
	protected void jButtonPotencia_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert(" "+Operacio.getPOT()+" ", jTextAreaFiltre.getCaretPosition());
		
	}
	/**
	 * Insereix el símbol d'arrel arrel a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>arrel(x)</code>
	 */
	protected void jButtonArrel_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert(Operacio.getARREL()+"()", jTextAreaFiltre.getCaretPosition());
		
	}
	/**
	 * Insereix el símbol de multiplicació x a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>x</code>
	 */
	protected void jButtonMultiplicacio_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert(" "+Operacio.getMULT()+" ", jTextAreaFiltre.getCaretPosition());
		
	}
	/**
	 * Insereix el símbol de divisió / a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>/</code>
	 */
	protected void jButtonDivisio_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert(" "+Operacio.getDIV()+" ", jTextAreaFiltre.getCaretPosition());
		
	}
	/**
	 * Insereix el símbol de resta - a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>-</code>
	 */
	protected void jButtonResta_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert(" "+Operacio.getRESTA()+" ", jTextAreaFiltre.getCaretPosition());
		
	}
	/**
	 * Insereix el símbol de suma + a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>+</code>
	 */
	protected void jButtonSum_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert(" "+Operacio.getSUMA()+" ", jTextAreaFiltre.getCaretPosition());
		
	}
	/**
	 * Insereix el símbol de pi [pi] a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>pi</code>
	 */
	protected void jButtonPi_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert("["+Operacio.getPI()+"]", jTextAreaFiltre.getCaretPosition());
		
	}
	/**
	 * Insereix el símbol de punt . a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>.</code>
	 */
	protected void jButtonPunt_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert(".", jTextAreaFiltre.getCaretPosition());	
		
	}
	/**
	 * Insereix el número 0 a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>0</code>
	 */
	protected void jButtonZero_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert("0", jTextAreaFiltre.getCaretPosition());	
		
	}
	/**
	 * Insereix el número 9 a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>9</code>
	 */
	protected void jButtonNou_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert("9", jTextAreaFiltre.getCaretPosition());	
		
	}
	/**
	 * Insereix el número 8 a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>8</code>
	 */
	protected void jButtonVuit_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert("8", jTextAreaFiltre.getCaretPosition());	
		
	}
	/**
	 * Insereix el número 7 a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>7</code>
	 */
	protected void jButtonSet_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert("7", jTextAreaFiltre.getCaretPosition());	
		
	}
	/**
	 * Insereix el número 6 a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>6</code>
	 */
	protected void jButtonSis_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert("6", jTextAreaFiltre.getCaretPosition());	
		
	}
	/**
	 * Insereix el número 5 a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>5</code>
	 */
	protected void jButtonCinc_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert("5", jTextAreaFiltre.getCaretPosition());	
		
	}
	/**
	 * Insereix el número 4 a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>4</code>
	 */
	protected void jButtonQuatre_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert("4", jTextAreaFiltre.getCaretPosition());	
		
	}
	/**
	 * Insereix el número 3 a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>3</code>
	 */
	protected void jButtonTres_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert("3", jTextAreaFiltre.getCaretPosition());	
		
	}
	/**
	 * Insereix el número 2 a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>2</code>
	 */
	protected void jButtonDos_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert("2", jTextAreaFiltre.getCaretPosition());	
		
	}
	/**
	 * Insereix el número 1 a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>1</code>
	 */
	protected void jButtonUn_actionPerformed(ActionEvent e) {
		jTextAreaFiltre.insert("1", jTextAreaFiltre.getCaretPosition());	
		
	}
	/**
	 * Insereix el nom de la variable seleccionada a l'àrea d'edició del filtre
	 * @param e, event que detecta que s'ha premut el botó <code>Imatge amb mà</code>
	 */
	protected void jButtonAfegirProp_actionPerformed(ActionEvent e) {
		Object vars=jListPropietats.getSelectedValue();
		jTextAreaFiltre.insert("("+(String)vars+")", jTextAreaFiltre.getCaretPosition());
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
	 * Realitza els càlculs aritmètics especificats i crea la variable resultant
	 * @param e, event que detecta que s'ha premut el botó <code>D'acord</code>
	 */
	protected void jButtonOK_actionPerformed(ActionEvent e) {
		try{
			String soperacio=jTextAreaFiltre.getText();
			String snom=jTextFieldNomFiltre.getText();
			if(snom.compareTo("")==0 || snom==null)frPare.actualitzarBarraEstat("No s'ha seleccionat introduït un nom per la nova propietat",true);
			else{
				LlistaPropietats llProps=gestor.obtenirLListaPropietats();
				if(llProps.existeixPropietat(snom))frPare.actualitzarBarraEstat("Ja existeix una propietat amb aquest nom",true);
				else{
					gestor.calcular(soperacio,snom);
					frPare.actualitzarBarraEstat("S'ha calculat l'operació correctament",false);
				}				
			}			
		}catch(Exception ex){
			frPare.actualitzarBarraEstat("No s'ha pogut calcular l'operació:"+ex.getMessage(),true);
			ex.printStackTrace();
		}
		
	}

}

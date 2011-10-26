package jklass.iu;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;

import jklass.nucli.GestorKlass;
import jklass.nucli.PropCategorica;
import jklass.nucli.PropNumerica;
import jklass.nucli.Propietat;
import jklass.util.SO;
/** Classe que dibuixa el submenú per seleccionar i especificar la discretització de la variable/s seleccionades 
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
public class PanelDiscretitzador extends javax.swing.JPanel {
	private static Logger logger=Logger.getLogger(PanelDiscretitzador.class.getName());
	private JPanel jPanel1;
	private JButton jButtonPerDefecte=new JButton();
	private JButton jButtonDreta=new JButton();
	private JPanel jPanelBotons;
	private JButton jButtonConsultarValors=new JButton();
	private JPanel jPanelConsultar;
	private JList jListVarsNumeriques=new JList();
	private JScrollPane jScrollPaneVarsNumeriques;
	private JButton jButtonEsquerra=new JButton();
	private JButton jButtonDretaUnic=new JButton();
	private JPanel jPanelUniforme;
	private JScrollPane jScrollPaneVarsCatSelecc;
	private JCheckBox jCheckBoxMkZk;

	private JScrollPane jScrollPane1;
	private JPanel jPanel11;
	private JPanel jPanel10;
	private JPanel jPanel9;
	private JButton jButtonTreureUnic=new JButton();
	private JTextField jTextFieldVarSelecc = new JTextField();
	private JPanel jPanel8;
	private JRadioButton jRadioButtonManual= new JRadioButton();
	private JButton jButtonTreure=new JButton();
	private JSeparator jSeparator3;
	private JTextField jTextFieldNumIntervals= new JTextField();
	private JLabel jLabelNumIntervals;
	private JPanel jPanel7;
	private JRadioButton jRadioButtonUniforme= new JRadioButton();	
	private JRadioButton jRadioButtonBoxplot= new JRadioButton();
	private JSeparator jSeparator2;
	private JList jListVarsCategSelecc=new JList();
	private JButton jButtonAfegir=new JButton();
	private JPanel jPanel6;
	private JList jListVarsCate=new JList();
	private JScrollPane jScrollPaneVarsCat;
	private JPanel jPanelLlistes;
	private JPanel jPanelManual;
	private JPanel jPanelBoxPlot;
	private JCheckBox jCheckBoxMostrarD= new JCheckBox();	
	private JCheckBox jCheckBoxRevisedDiscretization;
	private JPanel jPanelMetode;
	private JPanel jPanel5;
	private JPanel jPanel4;
	private JSeparator jSeparator1;
	private JPanel jPanel3;
	private JList jListVarsSelecc=new JList();
	private JScrollPane jScrollPaneVarsSelecc;
	
	private JPanel jPanelVarsNumSelecc;
	private JPanel jPanelLListaVarsNum;
	private JButton jButtonCancel=new JButton();
	private JButton jButtonOK=new JButton();
	private JPanel jPanel2;
	private FrPrincipal frPare;
	private GestorKlass gestor;
	private ModelTaula jTable1Model = new ModelTaula();
	private JTable jTable1=new JTable(jTable1Model);
	DefaultListModel listModelLin = new DefaultListModel();
	
	 int files=100;
	DefaultListModel listR = new DefaultListModel();
	DefaultListModel listCol = new DefaultListModel();
	JList capça;
	JList columncapça;	
	private DefaultListModel jListVarsNumeriquesModel=new DefaultListModel();
	private DefaultListModel jListVarsSeleccModel=new DefaultListModel();
	private DefaultListModel jListVarsCateModel=new DefaultListModel();
	private DefaultListModel jListVarsCategSeleccModel=new DefaultListModel();
	private int matriuCarregada;
	private int matriuActual;
	String nomFitxer = null;
	/**
	 * Constructor
	 * @param fr, finestra pare
	 * @param gk, GestorKlass
	 */
	public PanelDiscretitzador(FrPrincipal fr,GestorKlass gk) {
		 super();
		 frPare = fr;
		 gestor = gk;
		 nomFitxer = frPare.obtenirNomDades();
		 try {
			 initGUI();
		 }catch(Exception ex) {
		     ex.printStackTrace();
		 }
		
	}
	/***
	 * Dibuixa el menú
	 *
	 */
	private void initGUI() {
		try {
			frPare.setSize(725,590);
			frPare.centrar();
			 llistarVariables();
			this.setPreferredSize(new java.awt.Dimension(716, 519));
			GridBagConstraints gridBagConstraints = new GridBagConstraints(-1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 90, 0);
			gridBagConstraints.gridwidth = 20;
			gridBagConstraints.gridy = 2;
			gridBagConstraints.gridx = 0;
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.0, 0.1};
			thisLayout.rowHeights = new int[] {454, 7};
			thisLayout.columnWeights = new double[] {0.0};
			thisLayout.columnWidths = new int[] {689};
			this.setLayout(thisLayout);
			this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED), "Discretitzador", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
			{
				jPanel1 = new JPanel();
				GridBagLayout jPanel1Layout = new GridBagLayout();
				jPanel1Layout.rowWeights = new double[] {0.1};
				jPanel1Layout.rowHeights = new int[] {7};
				jPanel1Layout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1};
				jPanel1Layout.columnWidths = new int[] {121, 68, 118, 7};
				jPanel1.setLayout(jPanel1Layout);
				this.add(jPanel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					jPanelLListaVarsNum = new JPanel();
					GridBagLayout jPanelLListaVarsNumLayout = new GridBagLayout();
					jPanelLListaVarsNumLayout.rowWeights = new double[] {0.0, 0.1};
					jPanelLListaVarsNumLayout.rowHeights = new int[] {407, 7};
					jPanelLListaVarsNumLayout.columnWeights = new double[] {0.1};
					jPanelLListaVarsNumLayout.columnWidths = new int[] {7};
					jPanelLListaVarsNum.setLayout(jPanelLListaVarsNumLayout);
					jPanel1.add(jPanelLListaVarsNum, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						jScrollPaneVarsNumeriques = new JScrollPane();
						jScrollPaneVarsNumeriques.setAutoscrolls(true);
						jPanelLListaVarsNum.add(jScrollPaneVarsNumeriques, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jScrollPaneVarsNumeriques.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Vars. numèriques", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
						{
							
							
							jScrollPaneVarsNumeriques
								.setViewportView(jListVarsNumeriques);
							jListVarsNumeriques
								.setModel(jListVarsNumeriquesModel);
						}
					}
					{
						jPanelConsultar = new JPanel();
						jPanelLListaVarsNum.add(jPanelConsultar, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						{
							
							jPanelConsultar.add(jButtonConsultarValors);
							jButtonConsultarValors.setText("Consultar valors");
							jButtonConsultarValors.addActionListener(new java.awt.event.ActionListener() {
							      public void actionPerformed(ActionEvent e) {
							    	  jButtonConsultarValors_actionPerformed(e);
							      }
							    });
						}
					}
				}
				{
					jPanelBotons = new JPanel();
					jPanel1.add(jPanelBotons, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelBotons.setLayout(null);
					{
						
						jPanelBotons.add(jButtonDreta);
						jButtonDreta.setIcon(new ImageIcon(getClass()
							.getClassLoader().getResource("images/dreta.gif")));
						jButtonDreta.setBounds(0, 84, 63, 42);
						jButtonDreta.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e) {
						    	  jButtonDreta_actionPerformed(e);
						      }
						    });
					}
					{
						
						jPanelBotons.add(jButtonEsquerra);
						jButtonEsquerra.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
						jButtonEsquerra.setBounds(0, 133, 63, 35);
						jButtonEsquerra.setSize(63, 42);
						jButtonEsquerra.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e) {
						    	  jButtonEsquerra_actionPerformed(e);
						      }
						    });
					}
					{
						
						jPanelBotons.add(jButtonDretaUnic);
						jButtonDretaUnic.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
						jButtonDretaUnic.setBounds(0, 280, 63, 42);
						jButtonDretaUnic.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e) {
						    	  jButtonDretaUnic_actionPerformed(e);
						      }
						    });
					}
					{
						
						jPanelBotons.add(jButtonTreureUnic);
						jButtonTreureUnic.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
						jButtonTreureUnic.setBounds(0, 329, 63, 28);
						jButtonTreureUnic.setSize(63, 42);
						jButtonTreureUnic.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e) {
						    		jButtonTreureUnic_actionPerformed(e);
						      }
						    });
					}
				}
				{
					jPanelVarsNumSelecc = new JPanel();
					GridBagLayout jPanelVarsNumSeleccLayout = new GridBagLayout();
					jPanelVarsNumSeleccLayout.rowWeights = new double[] {0.0, 0.0, 0.1};
					jPanelVarsNumSeleccLayout.rowHeights = new int[] {237, 28, 7};
					jPanelVarsNumSeleccLayout.columnWeights = new double[] {0.1};
					jPanelVarsNumSeleccLayout.columnWidths = new int[] {7};
					jPanelVarsNumSelecc.setLayout(jPanelVarsNumSeleccLayout);
					jPanel1.add(jPanelVarsNumSelecc, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						jScrollPaneVarsSelecc = new JScrollPane();
						jScrollPaneVarsSelecc.setAutoscrolls(true);
						jPanelVarsNumSelecc.add(jScrollPaneVarsSelecc, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jScrollPaneVarsSelecc.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Vars. seleccionades", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
						{
							
							
							jScrollPaneVarsSelecc
								.setViewportView(jListVarsSelecc);
							jListVarsSelecc.setModel(jListVarsSeleccModel);
						}
					}
					{
						jPanel3 = new JPanel();
						GridBagLayout jPanel3Layout = new GridBagLayout();
						jPanel3Layout.rowWeights = new double[] {0.0, 0.0, 0.1};
						jPanel3Layout.rowHeights = new int[] {14, 103, 7};
						jPanel3Layout.columnWeights = new double[] {0.1};
						jPanel3Layout.columnWidths = new int[] {7};
						jPanel3.setLayout(jPanel3Layout);
						jPanelVarsNumSelecc.add(jPanel3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						{
							jPanel8 = new JPanel();
							jPanel3.add(jPanel8, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							jPanel8.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Var. seleccionada", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
							{
								
								jPanel8.add(jTextFieldVarSelecc);
								jTextFieldVarSelecc.setPreferredSize(new java.awt.Dimension(102, 26));
								jTextFieldVarSelecc.setEditable(false);
							}
						}
						{

						}
					}
					{
						jSeparator1 = new JSeparator();
						jPanelVarsNumSelecc.add(jSeparator1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					}
				}
				{
					jPanel4 = new JPanel();
					GridBagLayout jPanel4Layout = new GridBagLayout();
					jPanel4Layout.rowWeights = new double[] {0.0, 0.1};
					jPanel4Layout.rowHeights = new int[] {423, 7};
					jPanel4Layout.columnWeights = new double[] {0.1};
					jPanel4Layout.columnWidths = new int[] {7};
					jPanel4.setLayout(jPanel4Layout);
					jPanel1.add(jPanel4, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						jPanel5 = new JPanel();
						jPanel4.add(jPanel5, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPanel5.setLayout(null);
						{
							
							jPanel5.add(jCheckBoxMostrarD);
							jCheckBoxMostrarD.setText("Mostrar discretització");
							jCheckBoxMostrarD.setBounds(7, -1, 142, 22);
							{
								jCheckBoxMkZk = new JCheckBox();
								jPanel5.add(jCheckBoxMkZk);
								jCheckBoxMkZk.setText("Generar fitxers .mk i .zk");
								jCheckBoxMkZk.setBounds(161, 1, 190, 18);
							}
						}
					}
					{
						jPanelMetode = new JPanel();
						GridBagLayout jPanelMetodeLayout = new GridBagLayout();
						jPanelMetodeLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.1};
						jPanelMetodeLayout.rowHeights = new int[] {151, 16, 55, 16, 7};
						jPanelMetodeLayout.columnWeights = new double[] {0.1};
						jPanelMetodeLayout.columnWidths = new int[] {7};
						jPanelMetode.setLayout(jPanelMetodeLayout);
						jPanel4.add(jPanelMetode, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPanelMetode.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED), "Mètode", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
						{
							jPanelBoxPlot = new JPanel();
							GridBagLayout jPanelBoxPlotLayout = new GridBagLayout();
							jPanelBoxPlotLayout.rowWeights = new double[] {0.0, 0.1};
							jPanelBoxPlotLayout.rowHeights = new int[] {22, 7};
							jPanelBoxPlotLayout.columnWeights = new double[] {0.1};
							jPanelBoxPlotLayout.columnWidths = new int[] {7};
							jPanelBoxPlot.setLayout(jPanelBoxPlotLayout);
							jPanelMetode.add(jPanelBoxPlot, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 10));
							{
								jPanelLlistes = new JPanel();
								GridBagLayout jPanelLlistesLayout = new GridBagLayout();
								jPanelLlistesLayout.rowWeights = new double[] {0.1};
								jPanelLlistesLayout.rowHeights = new int[] {7};
								jPanelLlistesLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1};
								jPanelLlistesLayout.columnWidths = new int[] {47, 119, 75, 7};
								jPanelLlistes.setLayout(jPanelLlistesLayout);
								jPanelBoxPlot.add(jPanelLlistes, new GridBagConstraints(-1, 1, 20, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
								jPanelLlistes.setPreferredSize(new java.awt.Dimension(370, 134));
								{
									jScrollPaneVarsCat = new JScrollPane();
									jScrollPaneVarsCat.setAutoscrolls(true);
									jPanelLlistes.add(jScrollPaneVarsCat, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
									jScrollPaneVarsCat.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Vars. categòriques", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
									{
										
										
										jScrollPaneVarsCat
											.setViewportView(jListVarsCate);
										jListVarsCate
											.setModel(jListVarsCateModel);
									}
								}
								{
									jPanel6 = new JPanel();
									jPanelLlistes.add(jPanel6, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
									jPanel6.setLayout(null);
									{
										
										jPanel6.add(jButtonAfegir);
										jButtonAfegir.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
										jButtonAfegir.setBounds(7, 12, 63, 37);
										jButtonAfegir.addActionListener(new java.awt.event.ActionListener() {
										      public void actionPerformed(ActionEvent e) {
										    	  jButtonAfegir_actionPerformed(e);
										      }
										    });
									}
									{
										
										jPanel6.add(jButtonTreure);
										jButtonTreure.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
										jButtonTreure.setBounds(7, 54, 63, 41);
										jButtonTreure.addActionListener(new java.awt.event.ActionListener() {
										      public void actionPerformed(ActionEvent e) {
										    	  jButtonTreure_actionPerformed(e);
										      }
										    });
									}
								}
								{
									jScrollPaneVarsCatSelecc = new JScrollPane();
									jScrollPaneVarsCatSelecc.setAutoscrolls(true);
									jPanelLlistes.add(jScrollPaneVarsCatSelecc, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
									jScrollPaneVarsCatSelecc.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Vars. seleccionades", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
									{
										
										
										jScrollPaneVarsCatSelecc
											.setViewportView(jListVarsCategSelecc);
										jListVarsCategSelecc
											.setModel(jListVarsCategSeleccModel);
									}
								}
							}
							{
								
								jPanelBoxPlot.add(jRadioButtonBoxplot, new GridBagConstraints(-1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
								jRadioButtonBoxplot
									.setText("Discretització basada en boxplot");
								jRadioButtonBoxplot.addActionListener(new java.awt.event.ActionListener() {
							   		public void actionPerformed(ActionEvent e) {
							   			jRadioButtonBoxplot_actionPerformed(e);
							   		}
							   	});
							}
							{
								jCheckBoxRevisedDiscretization = new JCheckBox();
								jCheckBoxRevisedDiscretization.setContentAreaFilled(false);
								jCheckBoxRevisedDiscretization.addActionListener(new ActionListener() {
									public void actionPerformed(final ActionEvent arg0) {
									}
								});
								jCheckBoxRevisedDiscretization.setText("Mètode revisat (centre obert-centre tancat)");
								jCheckBoxRevisedDiscretization.setEnabled(false);
								jPanelBoxPlot.add(jCheckBoxRevisedDiscretization, gridBagConstraints);
							}
						}
						{
							jPanelUniforme = new JPanel();
							GridBagLayout jPanelUniformeLayout = new GridBagLayout();
							jPanelUniformeLayout.rowWeights = new double[] {0.0, 0.1};
							jPanelUniformeLayout.rowHeights = new int[] {25, 7};
							jPanelUniformeLayout.columnWeights = new double[] {0.1};
							jPanelUniformeLayout.columnWidths = new int[] {7};
							jPanelUniforme.setLayout(jPanelUniformeLayout);
							jPanelMetode.add(jPanelUniforme, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							{
								
								jPanelUniforme.add(jRadioButtonUniforme, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
								jRadioButtonUniforme
									.setText("Discretització uniforme");
								jRadioButtonUniforme.addActionListener(new java.awt.event.ActionListener() {
							   		public void actionPerformed(ActionEvent e) {
							   			jRadioButtonUniforme_actionPerformed(e);
							   		}
							   	});
							}
							{
								jPanel7 = new JPanel();
								jPanelUniforme.add(jPanel7, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
								jPanel7.setLayout(null);
								{
									jLabelNumIntervals = new JLabel();
									jPanel7.add(jLabelNumIntervals);
									jLabelNumIntervals.setText("Número d'intervals :");
									jLabelNumIntervals.setBounds(49, 7, 112, 14);
								}
								{
									
									jPanel7.add(jTextFieldNumIntervals);
									jTextFieldNumIntervals.setBounds(161, 7, 91, 21);
								}
							}
						}
						{
							jPanelManual = new JPanel();
							GridBagLayout jPanelManualLayout = new GridBagLayout();
							jPanelManualLayout.rowWeights = new double[] {0.0, 0.1};
							jPanelManualLayout.rowHeights = new int[] {20, 7};
							jPanelManualLayout.columnWeights = new double[] {0.1};
							jPanelManualLayout.columnWidths = new int[] {7};
							jPanelManual.setLayout(jPanelManualLayout);
							jPanelMetode.add(jPanelManual, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							{
								
								jPanelManual.add(jRadioButtonManual, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
								jRadioButtonManual
									.setText("Discretització manual");
								{
									jPanel9 = new JPanel();
									GridBagLayout jPanel9Layout = new GridBagLayout();
									jPanel9Layout.rowWeights = new double[] {0.1};
									jPanel9Layout.rowHeights = new int[] {7};
									jPanel9Layout.columnWeights = new double[] {0.0, 0.0, 0.1};
									jPanel9Layout.columnWidths = new int[] {52, 262, 7};
									jPanel9.setLayout(jPanel9Layout);
									jPanelManual.add(jPanel9, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
									{
										jPanel10 = new JPanel();
										jPanel9.add(jPanel10, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
									}
									{
										jPanel11 = new JPanel();
										jPanel9.add(jPanel11, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
									}
									{
										jScrollPane1 = new JScrollPane();
										jPanel9.add(jScrollPane1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
										{
																					
											for (int i = 0; i < files; i++) {
											      listModelLin.insertElementAt(new Integer(i+1), i);
											    }
											capça = new JList(listModelLin);
											columncapça=new JList(listModelLin);
											jScrollPane1.setRowHeaderView(capça);
											capça.setCellRenderer(new RowHeaderRenderer (jTable1));
											capça.setFixedCellHeight(jTable1.getRowHeight());
											jScrollPane1.setColumnHeaderView(columncapça);
											columncapça.setCellRenderer(new RowHeaderRenderer (jTable1));											
											jScrollPane1.setViewportView(jTable1);																				
											jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
											jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
											
										}
									}
								}
								jRadioButtonManual.addActionListener(new java.awt.event.ActionListener() {
							   		public void actionPerformed(ActionEvent e) {
							   			jRadioButtonManual_actionPerformed(e);
							   		}
							   	});
							}
						}
						{
							jSeparator2 = new JSeparator();
							jPanelMetode.add(jSeparator2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						}
						{
							jSeparator3 = new JSeparator();
							jPanelMetode.add(jSeparator3, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						}
					}
				}
			}
			{
				jPanel2 = new JPanel();
				this.add(jPanel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jPanel2.setLayout(null);
				{
					
					jPanel2.add(jButtonPerDefecte);
					jButtonPerDefecte.setText("Per defecte");
					jButtonPerDefecte.setBounds(140, 7, 91, 21);
					jButtonPerDefecte.addActionListener(new java.awt.event.ActionListener() {
					      public void actionPerformed(ActionEvent e) {
					    	  jButtonPerDefecte_actionPerformed(e);
					      }
					    });
				}
				{
					
					jPanel2.add(jButtonOK);
					jButtonOK.setText("D'acord");
					jButtonOK.setBounds(364, 7, 70, 21);
					jButtonOK.addActionListener(new java.awt.event.ActionListener() {
					      public void actionPerformed(ActionEvent e) {
					    	  jButtonOK_actionPerformed(e);
					      }
					    });
				}
				{
					
					jPanel2.add(jButtonCancel);
					jButtonCancel.setText("Cancel.la");
					jButtonCancel.setBounds(455, 7, 84, 21);
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
	 * Activa les opcions del menú <code>Discretizació manual</code> i desactiva els altres menús que estiguessin activats
	 * @param e, event que detecta que s'ha seleccionat l'opció <code>Discretització Manual</code>
	 */
	 protected void jRadioButtonManual_actionPerformed(ActionEvent e) {
		jRadioButtonBoxplot.setSelected(false);
		jRadioButtonUniforme.setSelected(false);
		desactivarBoxplot();
		desactivarUniforme();
		activarManual();		
	}
	 /**
		 * Activa les opcions del menú <code>Discretizació uniforme</code> i desactiva els altres menús que estiguessin activats
		 * @param e, event que detecta que s'ha seleccionat l'opció <code>Discretització uniforme</code>
		 */
	protected void jRadioButtonUniforme_actionPerformed(ActionEvent e) {
		jRadioButtonBoxplot.setSelected(false);
		jRadioButtonManual.setSelected(false);
		desactivarBoxplot();
		desactivarManual();
		activarUniforme();		
	}
	/**
	 * Activa les opcions del menú <code>Discretizació BoxPlot</code> i desactiva els altres menús que estiguessin activats
	 * @param e, event que detecta que s'ha seleccionat l'opció <code>Discretització BoxPlot</code>
	 */
	protected void jRadioButtonBoxplot_actionPerformed(ActionEvent e) {
		jRadioButtonManual.setSelected(false);
		jRadioButtonUniforme.setSelected(false);
		desactivarManual();
		desactivarUniforme();
		activarBoxplot();		
	}
	/**
	 * Afegeix la variable del camp <code>Var. seleccionada</code> a la llista de variables numèriques i l'elimina del camp
	 * @param e, event que detecta que s'ha premut el botó <code>Deseleccionar variable(mà cap a l'esquerra)</code>
	 */
	protected void jButtonTreureUnic_actionPerformed(ActionEvent e) {
		 String s=jTextFieldVarSelecc.getText();
		 if(s.compareTo("")==0||s==null){}
		 else{
			 jListVarsNumeriquesModel.addElement(s);
			 jTextFieldVarSelecc.setText("");
		 }
		 jTable1Model.clear();
		 jTable1.setEnabled(false);
		 jRadioButtonManual.setEnabled(false);
		 jRadioButtonManual.setSelected(false);
	}
/**
 * Obre una finestra amb els valors mínim i màxim de la variable seleccionada de la llista de variables numèriques
 * @param e, event que detecta que s'ha premut el botó <code>Consultar valors</code>
 */
	protected void jButtonConsultarValors_actionPerformed(ActionEvent e) {
		 try {
				Object ob=jListVarsNumeriques.getSelectedValue();
				if(ob==null){
					frPare.actualitzarBarraEstat("S'ha de seleccionar algun element de la llista",true);
				}else{
					Propietat prop=gestor.obtenirPropietat((String)ob);
					if(prop instanceof PropNumerica){
						PropNumerica aux=(PropNumerica)prop;
						float max=aux.obtenirRangMax();
						float min=aux.obtenirRangMin();
						DialogValorsNum inst = new DialogValorsNum(frPare,gestor,max,min,(String)ob);
						inst.setLocationRelativeTo(this);
						inst.setVisible(true);
					}
				}
				
			} catch (Exception e1) {
				frPare.actualitzarBarraEstat("No s'ha pogut analitzar els valors de la variable: "+e1.getMessage(),true);
				e1.printStackTrace();
			}			
		
	}
	/**
	 * Afegeix la variable/s seleccionada a la llista de <code>Vars. seleccionades</code> i l'elimina de la llista de variables numèriques
	 * @param e, event que detecta que s'ha premut el botó <code>Seleccionar variable(mà cap a la dreta)</code>
	 */
	protected void jButtonDreta_actionPerformed(ActionEvent e) {
		Object[] vars = jListVarsNumeriques.getSelectedValues();
	    boolean hay_mas_vars = true;
	    int n = 0;
        System.out.println( "entre en este alebobo");
		  System.out.println( "vars[0]"+vars[0]);
	    do {
	      try {
	        jListVarsSeleccModel.addElement(vars[n]);
	        jListVarsNumeriquesModel.removeElement(vars[n]);
	        n++;
	      }
	      catch (Exception exc) { hay_mas_vars = false; }

	    } while (hay_mas_vars);
		 
		jRadioButtonBoxplot.setEnabled(true);
		jRadioButtonUniforme.setEnabled(true);
		desactivarManual();
		jRadioButtonManual.setSelected(false);
		jRadioButtonManual.setEnabled(false);
		String var=jTextFieldVarSelecc.getText();
		if(var.compareTo("")==0||var==null){}
		else jListVarsNumeriquesModel.addElement(var);
		jTextFieldVarSelecc.setText("");
	}
	/**
	 * Afegeix la variable/s seleccionada a la llista de <code>Vars. numèriques</code> i l'elimina de la llista de <code>Vars. seleccionades</code>
	 * @param e, event que detecta que s'ha premut el botó <code>Deseleccionar variable(mà cap a l'esquerra)</code>
	 */
	protected void jButtonEsquerra_actionPerformed(ActionEvent e) {
		Object[] vars = jListVarsSelecc.getSelectedValues();
	    boolean hay_mas_vars = true;
	    int n = 0;
      System.out.println( "este es el que saca");
	    do {
	      try {
	        jListVarsNumeriquesModel.addElement(vars[n]);
	        jListVarsSeleccModel.removeElement(vars[n]);
	        n++;
	      }
	      catch (Exception exc) { hay_mas_vars = false; }

	    } while (hay_mas_vars);
	    Object[] aux=jListVarsSeleccModel.toArray();
		if(aux.length==0){
			Object[] s=jListVarsCategSeleccModel.toArray();
			for(int i=0;i<s.length;i++){
				jListVarsCateModel.addElement(s[i]);
			}
			jListVarsCategSeleccModel.clear();
			jTextFieldNumIntervals.setText("");
			jRadioButtonBoxplot.setEnabled(false);
			jRadioButtonUniforme.setEnabled(false);
			jRadioButtonBoxplot.setSelected(false);
			jRadioButtonUniforme.setSelected(false);
			desactivarBoxplot();
			desactivarUniforme();
		}
		
	}
	/**
	 * Afegeix la variable seleccionada al camp <code>Var. seleccionada</code> i l'elimina de la llista de variables numèriques
	 * @param e, event que detecta que s'ha premut el botó <code>Seleccionar variable(mà cap a la dreta)</code>
	 */
	protected void jButtonDretaUnic_actionPerformed(ActionEvent e) {
		try {
			String s=jTextFieldVarSelecc.getText();
			if(s.compareTo("")!=0 && s!=null){
				 int answer=JOptionPane.showConfirmDialog(frPare,"S'esborrarà la matriu.Desitja continuar?","S'esborrarà la matriu.Desitja continuar?",JOptionPane.YES_NO_OPTION);
				 if (answer == JOptionPane.YES_OPTION){
					 Object var=jListVarsNumeriques.getSelectedValue();
					 jListVarsNumeriquesModel.removeElement(var);
					 jListVarsNumeriquesModel.addElement(s);
					 jTextFieldVarSelecc.setText((String)var);	
					 jTable1Model.clear();
					 Propietat prop=gestor.obtenirPropietat((String)var);
						PropNumerica aux=(PropNumerica)prop;
						float max=aux.obtenirRangMax();
						float min=aux.obtenirRangMin();
						Float flmin=new Float(min);				
						jTable1Model.setValueAt(flmin,0,0);
						jTable1.setEnabled(true);
						jRadioButtonManual.setEnabled(true);
						jRadioButtonManual.setSelected(true);
						jTable1.getColumnModel().getColumn(1).setCellEditor(new IntegerEditor(min,max));
				 }
			}else{
				Object var=jListVarsNumeriques.getSelectedValue();
				jListVarsNumeriquesModel.removeElement(var);
				jTextFieldVarSelecc.setText((String)var);				
				Propietat prop=gestor.obtenirPropietat((String)var);
				PropNumerica aux=(PropNumerica)prop;
				float max=aux.obtenirRangMax();
				float min=aux.obtenirRangMin();
				Float flmin=new Float(min);			
				jTable1Model.setMax(max);
				jTable1Model.setMin(min);
				jTable1Model.setValueAt(flmin,0,0);
				 jTable1.setEnabled(true);
				jRadioButtonManual.setEnabled(true);
				jRadioButtonManual.setSelected(true);
				jTable1.getColumnModel().getColumn(1).setCellEditor(new IntegerEditor(min,max));
			}
			desactivarBoxplot();
			desactivarUniforme();
			jRadioButtonBoxplot.setSelected(false);
			jRadioButtonBoxplot.setEnabled(false);
			jRadioButtonUniforme.setSelected(false);
			jRadioButtonUniforme.setEnabled(false);
			int pos=jListVarsSeleccModel.getSize();
			for(int i=0;i<pos;i++){
				Object ovar=jListVarsSeleccModel.getElementAt(i);
				jListVarsNumeriquesModel.addElement(ovar);				
			}
			jListVarsSeleccModel.clear();
			jCheckBoxMostrarD.setSelected(false);
			
		} catch (Exception e1) {
			e1.printStackTrace();
			frPare.actualitzarBarraEstat("No s'ha pogut seleccionar la variable: "+e1.getMessage(),true);
		}
		
		
		
	}
	/**
	 * Afegeix la variable categòrica seleccionada a la llista de <code>Vars. categòriques seleccionades</code> i l'elimina de la llista de variables categòriques
	 * @param e, event que detecta que s'ha premut el botó <code>Seleccionar variable/s categòriques( mà cap a la dreta)</code>
	 */
	protected void jButtonAfegir_actionPerformed(ActionEvent e) {
		Object[] vars = jListVarsCate.getSelectedValues();
	    boolean hay_mas_vars = true;
	    int n = 0;

	    do {
	      try {
	        jListVarsCategSeleccModel.addElement(vars[n]);
	        jListVarsCateModel.removeElement(vars[n]);
	        Propietat prop;
	        prop = gestor.obtenirPropietat((String)vars[n]);
	        if (  prop instanceof Propietat ){
	        	PropCategorica propCat = (PropCategorica)prop;
	        	if ( propCat.getLlModalitats().size()==2 ) jCheckBoxRevisedDiscretization.setEnabled(true);
	        }
	        n++;
	        
	      }
	      catch (Exception exc) { hay_mas_vars = false; }

	    } while (hay_mas_vars);
	    //if ( vars[n])
		
	}
	/**
	 * Afegeix la variable categòrica seleccionada a la llista de <code>Vars. categòriques </code> i l'elimina de la llista de variables categòriques seleccionades
	 * @param e, event que detecta que s'ha premut el botó <code>Deseleccionar variable/s categòriques( mà cap a l'esquerra)</code>
	 */
	protected void jButtonTreure_actionPerformed(ActionEvent e) {
		Object[] vars = jListVarsCategSelecc.getSelectedValues();
	    boolean hay_mas_vars = true;
	    int n = 0;

	    do {
	      try {
	        jListVarsCateModel.addElement(vars[n]);
	        jListVarsCategSeleccModel.removeElement(vars[n]);
	        
	        
	        boolean b = false;
	        for (int i = 0;i<jListVarsCategSeleccModel.size() && !b;i++){
	        	if ( ((PropCategorica)gestor.obtenirPropietat((String)jListVarsCategSeleccModel.get(i))).getLlModalitats().size() == 2 ){
	        		b = true;
	        	}
	        }
	        if ( b ) jCheckBoxRevisedDiscretization.setEnabled(true);
	        else desactivarRevisedDiscretization();
	        
	        n++;
	      }
	      catch (Exception exc) { hay_mas_vars = false; }

	    } while (hay_mas_vars);
		
	}
	/**
	 * Actualitza la finestra a les opcions per defecte
	 *@param e, event que detecta que s'ha premut el botó <code>Per defecte</code>
	 */
	protected void jButtonPerDefecte_actionPerformed(ActionEvent e) {
		this.llistarVariables();
		
	}
	/**
	 *Crea les variables resultants d'efectuar la discretització especificada
	 * @param e, event que detecta que s'ha premut el botó <code>D'acord</code>
	 */
	protected void jButtonOK_actionPerformed(ActionEvent e) {
		 boolean ok=false;
		 
		String cmd;
		Process proc;
		int err;
		try {
			if(jRadioButtonBoxplot.isSelected()){
				 boolean fitxers = true;
				
				if(jCheckBoxMostrarD.isSelected())fitxers=true;
				
				    /* llistaVars[0][] - Conté la llista de vars numeriques X de NC
				     * llistaVars[1][] - Conté la llista de vars categoriques Y de NC */
				    String[][] llistaVars = new String[2][];
				    // llistat dels estadistics que s'han de calcular
				    int nvarsN, nvarsC, nvarsCl, i;
				    nvarsN = jListVarsSeleccModel.getSize();
				    System.out.println("Numeriques seleccionades = "+nvarsN);
				    nvarsC = jListVarsCategSeleccModel.getSize();
				    System.out.println("Categ seleccionades = "+nvarsC);
				    if ( (nvarsC == 0) || (nvarsN == 0)) {
				      throw new Exception("S'ha de seleccionar alguna variable numèrica i categòrica");
				    }
				    else {
				      llistaVars[0] = new String[nvarsN];
				      llistaVars[1] = new String[nvarsC];
				      for (i = 0; i < nvarsN; i++) {
				        llistaVars[0][i] = (String) jListVarsSeleccModel.get(i);
				      }
				      for (i = 0; i < nvarsC; i++) {
				        llistaVars[1][i] = (String) jListVarsCategSeleccModel.get(i);
				      }
				      
				    
				      ok = gestor.discretitzarBoxPlot(llistaVars,fitxers,jCheckBoxRevisedDiscretization.isSelected(),jCheckBoxMkZk.isSelected());
				    }				    				    
			}else
			
			if(jRadioButtonUniforme.isSelected()){
				 boolean fitxers = true;
				
				 if(jCheckBoxMostrarD.isSelected())fitxers=true;
				    /* llistaVars[0][] - Conté la llista de vars numeriques X de NC
				     * llistaVars[1][]- Conté la llista de vars categoriques Y de NC */
				    String[][] llistaVars = new String[2][];
				    // llistat dels estadistics que s'han de calcular
				    int nvarsN, nvarsC, nvarsCl, i;
				    
				    nvarsN = jListVarsSeleccModel.getSize();
				   String sNumIntervals=jTextFieldNumIntervals.getText().trim();
				    if (nvarsN == 0){
				      throw new Exception(
				          "S'ha de seleccionar alguna variable numèrica");
				    }else if(sNumIntervals.compareTo("")==0||sNumIntervals==null){
				    	throw new Exception(
						          "El nombre d'intervals ha de ser major o igual a 1");
				    }else {
				    	Integer numIntervals=new Integer(sNumIntervals);				    	
				    	llistaVars[0] = new String[nvarsN];
				   
				      for (i = 0; i < nvarsN; i++) {
				        llistaVars[0][i] = (String) jListVarsSeleccModel.get(i);
				      }
				  
				      ok = gestor.discretitzarUniforme(llistaVars,numIntervals.intValue(), fitxers);
				    }
				    
			}else
			
			if(jRadioButtonManual.isSelected()){
				 
				 boolean fitxers = true;			
				if(jCheckBoxMostrarD.isSelected())fitxers=true;
				
				float dadesAux[];
				ArrayList llistaModalitats;
				try {
					dadesAux = jTable1Model.obteValors();
					llistaModalitats = jTable1Model.obteModalitats();
					String snomPropietat=jTextFieldVarSelecc.getText();
					ok=gestor.discretitzarManual(dadesAux, llistaModalitats, snomPropietat, fitxers);
				} catch (Exception e1) {
					throw new Exception("Els valors de la taula no són correctes");
				}
				
				
			}else throw new Exception("No s'ha marcat ninguna opció de discretització");
			if(ok && jCheckBoxMostrarD.isSelected()){
				
				matriuActual=gestor.idMatriuActual();
				String nom = new File(nomFitxer).getName();
				//BEGIN - [05M –Avaluar BC – Regla a Regla]- 20100325 
				//OLD:
				//frPare.actualitzarBarraEstat(
				//		"S'ha realitzat la discretització correctament. (Resultats al fitxer: " + nom + "dreg.tex)", false);
				//try {
				//	String pathResult = gestor.obtenirDirResultats();
				//	File dirResult = new File(pathResult);
				//	pathResult = pathResult + nom + "dreg";
				//NEW:
					frPare.actualitzarBarraEstat(
							"S'ha realitzat la discretització correctament. (Resultats al fitxer: " + nom + "disc.tex)", false);
					try {
						String pathResult = gestor.obtenirDirResultats();
						File dirResult = new File(pathResult);
						pathResult = pathResult + nom + "disc";
				//END - [05M –Avaluar BC – Regla a Regla]- 20100325
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
						"No s'ha realitzat l'anàlisi descriptiva.", false);
			}
 
			
		}catch(NumberFormatException ex){
			
			frPare.actualitzarBarraEstat(ex.getMessage(),true);
		}catch (Exception e1) {
			frPare.actualitzarBarraEstat("No s'ha dut a terme la discretització: "+e1.getMessage(),true);
			
		}
		
	}
	/**
	 * Tanca la finestra
	 * @param e, event que detecta que s'ha premut el botó <code>Cancel.la</code>
	 */
	protected void jButtonCancel_actionPerformed(ActionEvent e) {
		frPare.actualitzarBarraEstat("",false);
		frPare.remove(this);
		 frPare.validate();
		 frPare.repaint();		
	}
	/**
	 * Incialitza les llistes de la finestra
	 *
	 */
	private void llistarVariables(){

			String[][] llProps;		    
			int lon;

			jListVarsNumeriquesModel.clear();
			jListVarsSeleccModel.clear();
			jListVarsCateModel.clear();
			jListVarsCategSeleccModel.clear();
		   
			llProps = gestor.obtenirLlistaIDsProps();
		    lon = llProps[0].length;
		    for (int i = 0; i < lon; i++) {
		    	jListVarsNumeriquesModel.insertElementAt(llProps[0][i], i);
		    }
		    lon = llProps[1].length;
		    for (int i = 0; i < lon; i++) {
		    	jListVarsCateModel.insertElementAt(llProps[1][i], i);
		    }
		    lon = llProps[2].length;
		    for (int i = 0; i < lon; i++) {
		    	jListVarsCateModel.insertElementAt(llProps[2][i], i);
		    }
		    lon = llProps[3].length;
		    for (int i = 0; i < lon; i++) {
		    	jListVarsCateModel.insertElementAt(llProps[3][i], i);
		    } 		
		    jRadioButtonBoxplot.setEnabled(false);
		    jRadioButtonManual.setEnabled(false);
		    jRadioButtonUniforme.setEnabled(false);
		    jRadioButtonBoxplot.setSelected(false);
		    jRadioButtonManual.setSelected(false);
		    jRadioButtonUniforme.setSelected(false);
		    jCheckBoxMostrarD.setSelected(false);
		    desactivarBoxplot();
		    desactivarManual();
		    desactivarUniforme();
	 }
	/**
	 * Desactiva el menú de la <code>Discretització BoxPlot</code>
	 *
	 */
	protected void desactivarBoxplot(){
		if ( jCheckBoxRevisedDiscretization !=null ) desactivarRevisedDiscretization();
		jButtonAfegir.setEnabled(false);
		jButtonTreure.setEnabled(false);
		jListVarsCate.setEnabled(false);
		jListVarsCategSelecc.setEnabled(false);
		for(int pos=0;pos<jListVarsCategSeleccModel.size();pos++){
			Object var=jListVarsCategSeleccModel.get(pos);
			jListVarsCateModel.addElement(var);
		}
		jListVarsCategSeleccModel.clear();
	}
	/**
	 * Activa el menú de la <code>Discretització BoxPlot</code>
	 *
	 */
	protected void activarBoxplot(){
		jButtonAfegir.setEnabled(true);
		jButtonTreure.setEnabled(true);
		jListVarsCate.setEnabled(true);
		jListVarsCategSelecc.setEnabled(true);
		
		boolean b = false;
		for (int i = 0;i<jListVarsCateModel.size() && !b;i++){
        	try {
				if ( ((PropCategorica)gestor.obtenirPropietat((String)jListVarsCategSeleccModel.get(i))).getLlModalitats().size() == 2 ){
					b = true;
				}
			} catch (Exception e) {
			}
        }
        if ( b ) jCheckBoxRevisedDiscretization.setEnabled(true);
        else desactivarRevisedDiscretization();
		
	}
	
	/**
	 * Desactiva el checkbox que permet seleccionar el mode de discretització revisat
	 *
	 */
	protected void desactivarRevisedDiscretization(){
		jCheckBoxRevisedDiscretization.setEnabled(false);
		jCheckBoxRevisedDiscretization.setSelected(false);
		
	}
	/**
	 * Desactiva el menú de la <code>Discretització uniforme</code>
	 *
	 */
	protected void desactivarUniforme(){
		jTextFieldNumIntervals.setEnabled(false);
		jTextFieldNumIntervals.setText("");
	}
	/**
	 * Activa el menú de la <code>Discretització uniforme</code>
	 *
	 */
	protected void activarUniforme(){
		jTextFieldNumIntervals.setEnabled(true);
	}
	/**
	 * Desactiva el menú de la <code>Discretització manual</code>
	 *
	 */
	protected void desactivarManual(){
		jTable1.setEnabled(false);
		jCheckBoxMostrarD.setSelected(true);
		jTable1Model.clear();
	}
	/**
	 * Activa el menú de la <code>Discretització manual</code>
	 *
	 */
	protected void activarManual(){
		jTable1.setEnabled(true);
		jCheckBoxMostrarD.setSelected(false);
	}

}

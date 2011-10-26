package jklass.iu;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
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
import javax.swing.DefaultListModel;
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

import jklass.nucli.BaseConeixement;
import jklass.nucli.GestorKlass;
import jklass.nucli.Propietat;
import jklass.util.Opcions;
import jklass.util.SO;

/**
 * Class that generates a file with quality values of a knowledge base.
 * 
 * @author Esther Lozano
 *
 */
public class PanelQualitatBC extends javax.swing.JPanel 
{
	private static Logger logger=Logger.getLogger(PanelAvaluadorBC.class.getName());
	private JPanel jPanelOpcions;
	private JButton jButtonCancel;
	private JButton jButtonOK;
	private JButton jButtonPerDefecte;
	private JButton jButtonTot;
	private JList jListBC;
	private JCheckBox jCheckBoxConf = new JCheckBox();
	private JCheckBox jCheckBoxMeanConf = new JCheckBox();
	private JPanel jPanel9;
	private JPanel jPanel8;
	private JPanel jPanelSelectors;
	private JPanel jPanelBCSeleccionada;
	private JButton jButtonSeleccionarBC;
	private JPanel jPanel3;
	private JPanel jPanel2;
	private JPanel jPanel1;
	private JCheckBox jCheckBoxCoverG = new JCheckBox();
	private JCheckBox jCheckBoxSuppTotal = new JCheckBox();
	private JPanel jPanel10;
	private JCheckBox jCheckBoxCoverR = new JCheckBox();
	private JCheckBox jCheckBoxSupport = new JCheckBox();
	private JScrollPane jScrollPaneBC;
	private JPanel jPanelButons;
	DefaultListModel jListBCModel = new DefaultListModel();
	FrPrincipal frPare;
	GestorKlass gestor;	 
	Opcions opcUniv = new Opcions(Opcions.UNIVARIANT);
	String nomFitxer = null;
	Opcions opcClass = new Opcions(Opcions.PER_CLASSES);
	private JCheckBox jCheckBoxDescriptiva =new JCheckBox();
	private JTextField jTextFieldBCSeleccionada=new JTextField();
	private JLabel jLabelBCSeleccionada;
	private JPanel jPanel4;
	private JPanel jPanel5;

	MouseListener mouseRegla =new MouseAdapter(){
		 /**
		  * Selecciona una base de coneixement a partir de fer doble clic sobre un nom
		  * @param MouseEvent, detecta que s'ha fet doble clic sobre el nom d'una base de coneixement
		  */
		   public void mouseClicked(MouseEvent e) 
		   {
		         if (e.getClickCount() == 2) 
		         {
		        	 Object ob = jListBC.getSelectedValue();
		        	 jListBCModel.removeElement(ob);
		        	 jTextFieldBCSeleccionada.setText((String)ob);
		        	 jPanel4.add(jTextFieldBCSeleccionada);
		         }
		   }
	 };
	
	/**
	 * Constructor
	 * @param fr,finestra pare
	 * @param gk, GestorKlass
	 */	
	public PanelQualitatBC(FrPrincipal fr,GestorKlass gk) 
	{
		super();
		frPare = fr;
		gestor = gk;		
		opcUniv.posarPerDefecteBC(Opcions.TAULA_FREQS);
		opcUniv.posarPerDefecteBC(Opcions.DIAGR_BARRES);
		opcClass.posarPerDefecteBC(Opcions.DESCR_EXTENSIONAL);
		nomFitxer = frPare.obtenirNomDades();
		jCheckBoxDescriptiva.setSelected(true);
		jCheckBoxConf.setSelected(true);
		jCheckBoxSupport.setSelected(true);
		jCheckBoxCoverR.setSelected(true);
		jCheckBoxCoverG.setSelected(true);
		jCheckBoxMeanConf.setSelected(true);
		jCheckBoxSuppTotal.setSelected(true);
		
		String [] llBCs = gestor.obtenirLlistaNomsBC();
		int lon = llBCs.length;
		for(int i = 0; i < lon; i++)
		{
			jListBCModel.insertElementAt(llBCs[i], i);
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
			//matriuCarregada=gestor.idMatriuActual();
			frPare.setSize(590,460);
			frPare.centrar();
			this.setPreferredSize(new java.awt.Dimension(568, 383));
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.0, 0.1};
			thisLayout.rowHeights = new int[] {317, 7};
			thisLayout.columnWeights = new double[] {0.1};
			thisLayout.columnWidths = new int[] {7};
			this.setLayout(thisLayout);
			this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED), "Qualitat de Bases de Coneixement", TitledBorder.LEADING, TitledBorder.TOP,new java.awt.Font("Dialog",0,10)));
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
									jPanel9.add(jCheckBoxConf);
									jCheckBoxConf.setText("Confiança");
									jCheckBoxConf.setBounds(7, 21, 119, 21);
								}
								{
									jPanel9.add(jCheckBoxSupport);
									jCheckBoxSupport.setText("Suport");
									jCheckBoxSupport.setBounds(7, 49, 119, 21);
								}
								{
									jPanel9.add(jCheckBoxCoverR);
									jCheckBoxCoverR.setText("Cobertura relativa");
									jCheckBoxCoverR.setBounds(7, 77, 119, 21);
								}
							}
							{
								jPanel10 = new JPanel();
								jPanel8.add(jPanel10, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
								jPanel10.setLayout(null);
								{
									jPanel10.add(jCheckBoxMeanConf);
									jCheckBoxMeanConf.setText("Confiança mitjana");
									jCheckBoxMeanConf.setBounds(0, 21, 119, 21);
								}
								{
									jPanel10.add(jCheckBoxSuppTotal);
									jCheckBoxSuppTotal.setText("Suport total");
									jCheckBoxSuppTotal.setBounds(0, 49, 119, 21);
								}
								{
									jPanel10.add(jCheckBoxCoverG);
									jCheckBoxCoverG.setText("Cobertura global");
									jCheckBoxCoverG.setBounds(0, 77, 119, 21);
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
					jButtonPerDefecte.setBounds(50, 7, 91, 28);
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
					jButtonOK.setBounds(200, 7, 84, 28);
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
					jButtonCancel.setBounds(320, 7, 84, 28);
					jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
					      public void actionPerformed(ActionEvent e) {
					    	  jButtonCancel_actionPerformed(e);
					      }
					});
				}
				{
					jButtonTot = new JButton();
					jPanelButons.add(jButtonTot);
					jButtonTot.setText("Desmarcar");
					jButtonTot.setBounds(470, 7, 84, 28);
					jButtonTot.addActionListener(new java.awt.event.ActionListener() {
					      public void actionPerformed(ActionEvent e) {
					    	  jButtonTot_actionPerformed(e);
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
	protected void jButtonSeleccionarBC_actionPerformed(ActionEvent e) 
	{
		Object ob = jListBC.getSelectedValue();
		jListBCModel.removeElement(ob);
		jTextFieldBCSeleccionada.setText((String)ob);
		jPanel4.add(jTextFieldBCSeleccionada);		
		
	}
	
	
	/**
	 * Estableix les opcions per defecte del menú
	 * @param e, event que detecta que s'ha premut el botó <code>Per defecte</code>
	 */
	protected void jButtonPerDefecte_actionPerformed(ActionEvent e) 
	{
		jCheckBoxConf.setSelected(true);
		jCheckBoxSupport.setSelected(true);
		jCheckBoxCoverR.setSelected(true);
		jCheckBoxCoverG.setSelected(true);
		jPanel9.add(jCheckBoxConf);
		jPanel9.add(jCheckBoxSupport);
		jPanel9.add(jCheckBoxCoverR);
		jPanel9.add(jCheckBoxCoverG);
		jCheckBoxMeanConf.setSelected(true);
		jCheckBoxSuppTotal.setSelected(true);	
		jPanel10.add(jCheckBoxMeanConf);
		jPanel10.add(jCheckBoxSuppTotal );				
		//jTextFieldBCSeleccionada.setText("");
		jPanel4.add(jTextFieldBCSeleccionada);
		jCheckBoxDescriptiva.setSelected(true);
	}
	
	
	/**
	 * This button deletes selection of all the quality indicators
	 * @param e, event that detects that the button <code>Desmarcar Tot</code> has been pressed
	 */
	protected void jButtonTot_actionPerformed(ActionEvent e) 
	{
		jCheckBoxConf.setSelected(false);
		jCheckBoxSupport.setSelected(false);
		jCheckBoxCoverR.setSelected(false);
		jCheckBoxCoverG.setSelected(false);
		jCheckBoxMeanConf.setSelected(false);
		jCheckBoxSuppTotal.setSelected(false);
	}
	
	/**
	 * Tanca la finestra
	 * @param e, event que detecta que s'ha premut el botó <code>Cancel.la</code>
	 */
	protected void jButtonCancel_actionPerformed(ActionEvent e) 
	{
		 frPare.remove(this);
		 frPare.validate();
		 frPare.repaint();		
	}
	
	
	/**
	 * Evaluates the quality of the selected knowledge base
	 * @param e, event that detects that the button <code>Acceptar</code> has been pressed
	 */
	protected void jButtonOK_actionPerformed(ActionEvent e) 
	{
		boolean ok;
		String nomBC = jTextFieldBCSeleccionada.getText();
		String cmd;
	    Process proc;
	    int err;
	    int esPart = 0;
		
		if(nomBC.compareTo("") == 0 || nomBC == null)
		{
			frPare.actualitzarBarraEstat("No s'ha seleccionat una Base de Coneixement",true);
		}
		else
		{		
			//if we have to create the KB description, then the quality analysis is just the
			//first part of the file
			if(jCheckBoxDescriptiva.isSelected())
			{
				esPart = 1;
			}
			
			//Calculate the selected values for each rule and show them in a table
			//Do the same for selected values over the whole knowledge base (total support and mean conf.)
			ok = gestor.ferQualitatBC(nomBC,jCheckBoxConf.isSelected(),jCheckBoxSupport.isSelected(),jCheckBoxCoverR.isSelected(),
					jCheckBoxCoverG.isSelected(),jCheckBoxSuppTotal.isSelected(),jCheckBoxMeanConf.isSelected(),esPart,"Qualitat");
				
			if (ok) 
			{	
				frPare.actualitzarBarraEstat("S'ha realitzat l'anàlisi de qualitat correctament", false);
		    	
				try 
				{
					//If "Generar descriptiva" option is selected, we have to concatenate the two files in one
					if(jCheckBoxDescriptiva.isSelected())
					{
						ok = generarDescrp(nomBC);
					}
					
					String nomFitxer = frPare.obtenirNomDades();
					String nom = new File(nomFitxer).getName();
					
					String pathResult = gestor.obtenirDirResultats();
					//String outputPath = pathResult + nomBC + "Qualitat";
					File dirResult = new File(pathResult);
					//pathResult = pathResult + nom + "dreg";
					pathResult = pathResult + nom + "Qualitat";

					//cmd = SO.obtenirCmdCompilaLtx(pathResult + ".tex" + " -job-name " + outputPath);
					cmd = SO.obtenirCmdCompilaLtx(pathResult + ".tex");
					
					System.out.println("--> " + cmd);
					
					if (cmd == null) 
					{
						/** @todo També es podria obrir una finestra */
						frPare.actualitzarBarraEstat(
								"No es pot compilar ni visualitzar automàticament el fitxer Latex generat per no haver-se reconegut el sistema operatiu.", true);
					}
					else 
					{
						logger.finer("Comanda compilació (exec): " + cmd);
						proc = Runtime.getRuntime().exec(cmd, null, dirResult);
						try 
						{
							// error?
							StreamGobbler errorGobbler = new
							StreamGobbler(proc.getErrorStream(), "ERROR (exec)");

							// output?
							StreamGobbler outputGobbler = new
							StreamGobbler(proc.getInputStream(), "OUTPUT (exec)");

							errorGobbler.start();
							outputGobbler.start();

							err = proc.waitFor();
							
							if (err == 0) 
							{
								//cmd = SO.obtenirCmdVisorLtx(outputPath + ".dvi");
								cmd = SO.obtenirCmdVisorLtx(pathResult + ".dvi");
								logger.finer("Comanda visor (exec): " + cmd);
								Runtime.getRuntime().exec(cmd, null, dirResult);
							}
							else 
							{
								frPare.actualitzarBarraEstat(
										"No s'ha pogut compilar correctament el fitxer Latex generat. (Error " + err + ")" , true);
							}
						}catch (InterruptedException exc) {
							frPare.actualitzarBarraEstat("S'ha interrumput el procés de compilació del fitxer Latex ( InterruptedException " +
                                         exc.getMessage() + " )", true);
						}
					}
				}catch (IOException exc) 
				{
    					frPare.actualitzarBarraEstat(
    							"Error al generar i visualitzar el latex ( IOException " +
    							exc.getMessage() + " )", true);
    			}
			}
			else 
			{
				frPare.actualitzarBarraEstat(
						"No ha estat possible realitzar l'anàlisi de qualitat de la base de coneixement.", true);
			}
			
			frPare.remove(this);
		    frPare.validate();
		    frPare.repaint();
		}
	}
	
	
	/**
	 * This function generates the descriptive file for the given knowledge base. It returns the
	 * name of the generated file.
	 * 
	 * @param nomBC - name of the knowledge base
	 * 
	 * @return the name of the generated file
	 */
	public boolean generarDescrp(String nomBC)
	{
		ArrayList llistaBC=new ArrayList();//conté els noms de les BC's avaluades
		ArrayList llistaVariables=new ArrayList();//conté llistes, que alhora contenen els vectors de variables
		ArrayList llistaProp=new ArrayList();
		ArrayList al=new ArrayList();	
		int tipusDescriptiva=0;//0-->Univariant, ,1-->Per classes, 2-->Univariant i per classes
		boolean ok=true;
		String descrpFile = null;
		int esPart = 3;
		
	    // llistaVars[0][] - It contains the list of numerical variables (univariant)
	    // llistaVars[1][] - It contains the list of categorical variables (univariant)
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
	    int err,iNumProps=0;//nvarsC és el nombre de bases de coneixement seleccionades
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
				
		try
		{
			//Evaluation must be done rule by rule
			llistaProp = gestor.avaluaReglaARegla(nomBC,false);
			iNumProps = iNumProps + llistaProp.size();
			
			llistaBC.add(nomBC);
			llistaVars[0]=new String[0];
			llistaVars[1]=new String[llistaProp.size()];
			
			llistaVarsC[0]=new String[llistaProp.size()];
			llistaVarsC[1]=new String[0];
			llistaVarsC[2]=new String[0];
			
			for(int j = 0; j < llistaProp.size(); j++)
			{
				Propietat paux = (Propietat)llistaProp.get(j);
				llistaVars[1][j] = paux.obtenirId();			    				
				llistaVarsC[0][j] = paux.obtenirId();
			}
			
			al=new ArrayList(2);			    			
			al.add(llistaVars);
			al.add(llistaVarsC);
			llistaVariables.add(al);
		    		
			ok = gestor.ferDescripBC(llistaBC,llistaVariables,llistaEstads,llistaEstadsC,opcUniv,opcClass,1,tipusDescriptiva,esPart,false,"Qualitat");
		
		}catch(ArrayIndexOutOfBoundsException ex)
		{
			frPare.actualitzarBarraEstat("No s'ha pogut avaluar la Base de Coneixement:alguna regla utilitza variables que no existeixen a la matriu de dades", true);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			frPare.actualitzarBarraEstat("No s'ha pogut avaluar la Base de Coneixement: "+ex.getMessage(),true);	
		}
		
		return ok;
	}

}


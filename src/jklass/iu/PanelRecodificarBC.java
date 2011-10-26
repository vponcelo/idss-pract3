package jklass.iu;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import jklass.nucli.GestorKlass;
import jklass.nucli.LlistaPropietats;
/** Classe que dibuixa el submenú per definir la recodificació de variables categòriques
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
public class PanelRecodificarBC extends javax.swing.JPanel {
	private JPanel jPanel1;
	private JButton jButtonCancel;
	private JPanel jPanelDreta;
	private JButton jButtonTreureNovaModalitat;
	private JList jListModsNoves;
	private JScrollPane jScrollPaneModsNoves;
	private JPanel jPanel6;
	private JTextField jTextFieldNomVarNova = new JTextField();
	private JButton jButtonAfegirModalitatNova;
	private JPanel jPanelAfegirModalitat;
	private JList jListModsSelecc;
	private JScrollPane jScrollPaneModalitatsSelecc;
	private JPanel jPanelNomModalitat;
	private JPanel jPanel5;
	JTextField jTextFieldNomModalitatNova = new JTextField();
	private JLabel jLabelNomVar;
	private JPanel jPanel4;
	private JPanel jPanelNomVarNova;
	private JPanel jPanel3;
	private JButton jButtonTreureMod;
	private JButton jButtonAfegirMod;
	private JPanel jPanelButtons;
	private JList jListVarsSeleccionades;
	private JScrollPane jScrollPaneModalitats;
	private JTextField jTextFieldNomVarSelecc=new JTextField();
	private JPanel jPanelNomVarSelecc;
	private JPanel jPanelVarsSelecc;
	private JButton jButtonDreta;
	private JList jListVars;
	private JScrollPane jScrollPaneVars;
	private JButton jButtonOK;
	private JPanel jPanel2;
	FrPrincipal frPare;
	GestorKlass gestor;	
	DefaultListModel jListVarsModel=new DefaultListModel();
	DefaultListModel jListVarsSeleccionadesModel=new DefaultListModel();
	DefaultListModel jListModsSeleccModel= new DefaultListModel();
	DefaultListModel jListModsNovesModel=new DefaultListModel();
	private boolean IS_OK;//ens indica si s'han guardat els canvis
	ArrayList alnomModalitats=new ArrayList();//cada posició es correspon amb un posició de l'array agrupacionsModals i conté el nom de la nova modalitat
	ArrayList alagrupacionsModals=new ArrayList();//a cada posició conté un vector de modalitats
	
	MouseListener mouseRegla =new MouseAdapter(){
		/**
		 * Mostra les modalitats agrupades sota la nova modalitat seleccionada a la llista <code>Agrupació de modalitats</code>.
		 * La modalitat seleccionada s'elimina de la llista <code>Modalitats de la nova variable</code>
		 * @param e, event que detecta que s'ha fet doble clic sobre un element de la llista <code>Modalitats de la nova variable</code>
		 */
		   public void mouseClicked(MouseEvent e) {
		         if (e.getClickCount() == 2) {
		          	int index = jListModsNoves.locationToIndex(e.getPoint());
		        	Object ob=jListModsNoves.getSelectedValue();
		        	jTextFieldNomModalitatNova.setText((String)ob);	     		
		     		int imod=0;
		     		boolean b=false;
		     		for(int i=0;i<alnomModalitats.size() && !b;i++){
		     			String saux=(String)alnomModalitats.get(i);
		     			if(saux.compareTo((String)ob)==0){
		     				imod=i;
		     				b=true;
		     			}
		     		}
		     		String[] modsSelec=(String[])alagrupacionsModals.get(imod);
		     		jListModsSeleccModel.clear();
		     		for(int j=0;j<modsSelec.length;j++){
		     			jListModsSeleccModel.insertElementAt(modsSelec[j],j);
		     		}
		     		jListModsNovesModel.removeElementAt(imod);
		     		alagrupacionsModals.remove(imod);
		     		alnomModalitats.remove(imod);
		         }
		   }
	 };
	 
	 MouseListener mouseSeleccionarVar=new MouseAdapter(){
		 /**
		  * Selecciona la nova variable a recodificar, posant les seves modalitats a la <code>Llista de modalitats</code> i el seu nom al camp
		  * de <code>Variable seleccionada</code>
		  * Si ja hi havia un altre variable seleccionada i s'havien efectuat canvis que no s'havien guardat, es mostra una finestra indicant que els canvis es perdran.
		  * @param e, event que detecta que s'ha fet doble clic sobre un element de la llista <code>Variables categòriques</code>
		  */
		 public void mouseClicked(MouseEvent e) {
	         if (e.getClickCount() == 2) {
	        	 canviarPropietat();
	        	 
	         }
		 }
	 };
	
	 MouseListener mouseAfegir=new MouseAdapter(){
		 /**
		  * Afegeix una nova modalitat a la llista de <code>Agrupació de modalitats</code> i l'elimina de la <code>Llista de modalitats</code>
		  * @param e, event que detecta que s'ha fet doble clic sobre un element de la <code>Llista de modalitats</code>
		  */
		 public void mouseClicked(MouseEvent e) {
	         if (e.getClickCount() == 2) {
	        	Object vars = jListVarsSeleccionades.getSelectedValue();	 		    
	 		    jListModsSeleccModel.addElement(vars);
	 		    jListVarsSeleccionadesModel.removeElement(vars);
	        	IS_OK=false; 
	         }
		 }
	 };
	
	 MouseListener mouseTreure=new MouseAdapter(){
		 /**
		  * Afegeix una la modalitat seleccionada a la llista de <code>Agrupació de modalitats</code>a la <code>Llista de modalitats</code> i
		  * l'elimina de la llista <code>Agrupació de modalitats</code>
		  * @param e, event que detecta que s'ha fet doble clic sobre un element de la llista<code>Agrupació de modalitats</code>
		  */
		 public void mouseClicked(MouseEvent e) {
	         if (e.getClickCount() == 2) {
	        	Object vars = jListModsSelecc.getSelectedValue();	 		    
	        	 jListVarsSeleccionadesModel.addElement(vars);
	 		    jListModsSeleccModel.removeElement(vars);
	        	IS_OK=false; 
	         }
		 }
	 };
	 
	 /**
	  * Constructor
	  * @param fr, finestra pare
	  * @param gk, GestorKlass
	  */
	public PanelRecodificarBC(FrPrincipal fr,GestorKlass gk) {
		super();
		frPare = fr;
		gestor = gk;	
		IS_OK=true;
		jTextFieldNomModalitatNova.setText("");
		String s=gestor.nomPropietatPerDefecte("VAR");
		
		jTextFieldNomVarNova.setText(s);
		String[][] llProps;
	    int lon;
		 llProps = gestor.obtenirLlistaIDsProps();
		    lon = llProps[1].length;//la posició 0 conté les propietats numèriques
		    for (int i = 0; i < lon; i++) {		    	
		    	jListVarsModel.insertElementAt(llProps[1][i], i);
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
			frPare.setSize(870, 510);
			frPare.centrar();
			this.setPreferredSize(new java.awt.Dimension(875, 430));
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.0, 0.1};
			thisLayout.rowHeights = new int[] {363, 7};
			thisLayout.columnWeights = new double[] {0.1};
			thisLayout.columnWidths = new int[] {7};
			this.setLayout(thisLayout);
			this.setBorder(BorderFactory.createTitledBorder(null, "Recodificador", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
			{
				jPanel1 = new JPanel();
				GridBagLayout jPanel1Layout = new GridBagLayout();
				jPanel1Layout.rowWeights = new double[] {0.1};
				jPanel1Layout.rowHeights = new int[] {7};
				jPanel1Layout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.1};
				jPanel1Layout.columnWidths = new int[] {127, 71, 137, 75, 7};
				jPanel1.setLayout(jPanel1Layout);
				this.add(jPanel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					jScrollPaneVars = new JScrollPane();
					jScrollPaneVars.setAutoscrolls(true);
					jPanel1.add(jScrollPaneVars, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jScrollPaneVars.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Variables categòriques", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
					{
						/*ListModel jListVarsModel = new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });*/
						jListVars = new JList();
						jScrollPaneVars.setViewportView(jListVars);
						jListVars.setModel(jListVarsModel);
					//	jListVars.setPreferredSize(new java.awt.Dimension(239, 531));
						jListVars.addMouseListener(mouseSeleccionarVar);
					}
				}
				{
					jPanelDreta = new JPanel();
					jPanel1.add(jPanelDreta, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelDreta.setLayout(null);
					{
						jButtonDreta = new JButton();
						jPanelDreta.add(jButtonDreta);
						jButtonDreta.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
						jButtonDreta.setBounds(7, 14, 56, 35);
						jButtonDreta.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e){
						    	  jButtonDreta_actionPerformed(e);
						      }
						});
					}
				}
				{
					jPanelVarsSelecc = new JPanel();
					GridBagLayout jPanelVarsSeleccLayout = new GridBagLayout();
					jPanelVarsSeleccLayout.rowWeights = new double[] {0.0, 0.1};
					jPanelVarsSeleccLayout.rowHeights = new int[] {49, 7};
					jPanelVarsSeleccLayout.columnWeights = new double[] {0.1};
					jPanelVarsSeleccLayout.columnWidths = new int[] {7};
					jPanelVarsSelecc.setLayout(jPanelVarsSeleccLayout);
					jPanel1.add(jPanelVarsSelecc, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						jPanelNomVarSelecc = new JPanel();
						jPanelVarsSelecc.add(jPanelNomVarSelecc, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPanelNomVarSelecc.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Variable seleccionada", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
						{
							/*jTextFieldNomVarSelecc = new JTextField();*/
							jPanelNomVarSelecc.add(jTextFieldNomVarSelecc);
							jTextFieldNomVarSelecc.setPreferredSize(new java.awt.Dimension(126, 20));
							jTextFieldNomVarSelecc.setEditable(false);
						}
					}
					{
						jScrollPaneModalitats = new JScrollPane();
						jScrollPaneModalitats.setAutoscrolls(true);
						jPanelVarsSelecc.add(jScrollPaneModalitats, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jScrollPaneModalitats.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Llista de modalitats", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
						{
							/*ListModel jListVarsSeleccionadesModel = new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });*/
							jListVarsSeleccionades = new JList();
							jScrollPaneModalitats
								.setViewportView(jListVarsSeleccionades);
							jListVarsSeleccionades
								.setModel(jListVarsSeleccionadesModel);
							jListVarsSeleccionades.setPreferredSize(new java.awt.Dimension(237, 526));
							jListVarsSeleccionades.addMouseListener(mouseAfegir);
						}
					}
				}
				{
					jPanelButtons = new JPanel();
					jPanel1.add(jPanelButtons, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelButtons.setLayout(null);
					{
						jButtonAfegirMod = new JButton();
						jPanelButtons.add(jButtonAfegirMod);
						jButtonAfegirMod.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
						jButtonAfegirMod.setBounds(7, 154, 63, 42);
						jButtonAfegirMod.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e){
						    	  jButtonAfegirMod_actionPerformed(e);
						      }
						});
					}
					{
						jButtonTreureMod = new JButton();
						jPanelButtons.add(jButtonTreureMod);
						jButtonTreureMod.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
						jButtonTreureMod.setBounds(7, 245, 63, 42);
						jButtonTreureMod.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e){
						    	  jButtonTreureMod_actionPerformed(e);
						      }
						});
					}
				}
				{
					jPanel3 = new JPanel();
					GridBagLayout jPanel3Layout = new GridBagLayout();
					jPanel3Layout.rowWeights = new double[] {0.0, 0.1};
					jPanel3Layout.rowHeights = new int[] {34, 7};
					jPanel3Layout.columnWeights = new double[] {0.1};
					jPanel3Layout.columnWidths = new int[] {7};
					jPanel3.setLayout(jPanel3Layout);
					jPanel1.add(jPanel3, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED), "Variable resultat", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
					{
						jPanelNomVarNova = new JPanel();
						jPanel3.add(jPanelNomVarNova, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPanelNomVarNova.setLayout(null);
						{
							jLabelNomVar = new JLabel();
							jPanelNomVarNova.add(jLabelNomVar);
							jLabelNomVar.setText("Nom de la nova variable :");
							jLabelNomVar.setBounds(7, 7, 133, 14);
						}
						{
							/*jTextFieldNomVarNova = new JTextField();*/
							jPanelNomVarNova.add(jTextFieldNomVarNova);
							jTextFieldNomVarNova.setBounds(140, 7, 280, 21);
						}
					}
					{
						jPanel4 = new JPanel();
						GridBagLayout jPanel4Layout = new GridBagLayout();
						jPanel4Layout.rowWeights = new double[] {0.1};
						jPanel4Layout.rowHeights = new int[] {7};
						jPanel4Layout.columnWeights = new double[] {0.0, 0.0, 0.1};
						jPanel4Layout.columnWidths = new int[] {170, 88, 7};
						jPanel4.setLayout(jPanel4Layout);
						jPanel3.add(jPanel4, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						{
							jPanel5 = new JPanel();
							GridBagLayout jPanel5Layout = new GridBagLayout();
							jPanel5Layout.rowWeights = new double[] {0.0, 0.1};
							jPanel5Layout.rowHeights = new int[] {51, 7};
							jPanel5Layout.columnWeights = new double[] {0.1};
							jPanel5Layout.columnWidths = new int[] {7};
							jPanel5.setLayout(jPanel5Layout);
							jPanel4.add(jPanel5, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							{
								jPanelNomModalitat = new JPanel();
								jPanel5.add(jPanelNomModalitat, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
								jPanelNomModalitat.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Nom de la nova modalitat", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
								{
									/*jTextFieldNomModalitatNova = new JTextField();*/
									jPanelNomModalitat
										.add(jTextFieldNomModalitatNova);
									jTextFieldNomModalitatNova.setPreferredSize(new java.awt.Dimension(155, 20));
								}
								{
									/*jTextFieldNomModalitatNova = new JTextField();*/

								}
							}
							{
								jScrollPaneModalitatsSelecc = new JScrollPane();
								jScrollPaneModalitatsSelecc.setAutoscrolls(true);
								jPanel5.add(jScrollPaneModalitatsSelecc, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
								jScrollPaneModalitatsSelecc.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Agrupació de modalitats", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
								jScrollPaneModalitatsSelecc.setPreferredSize(new java.awt.Dimension(170, 247));
								{
									/*ListModel jListModsSeleccModel = new DefaultComboBoxModel(
										new String[] { "Item One", "Item Two" });*/
									jListModsSelecc = new JList();
									jScrollPaneModalitatsSelecc
										.setViewportView(jListModsSelecc);
									jListModsSelecc
										.setModel(jListModsSeleccModel);
									jListModsSelecc.setPreferredSize(new java.awt.Dimension(219, 438));
									jListModsSelecc.addMouseListener(mouseTreure);
								}
							}
						}
						{
							jPanelAfegirModalitat = new JPanel();
							jPanel4.add(jPanelAfegirModalitat, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							jPanelAfegirModalitat.setLayout(null);
							{
								jButtonAfegirModalitatNova = new JButton();
								jPanelAfegirModalitat.add(jButtonAfegirModalitatNova);
								jButtonAfegirModalitatNova.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
								jButtonAfegirModalitatNova.setBounds(14, 14, 63, 35);
								jButtonAfegirModalitatNova.addActionListener(new java.awt.event.ActionListener() {
								      public void actionPerformed(ActionEvent e){
								    	  jButtonAfegirModalitatNova_actionPerformed(e);
								      }
								});
							}
							{
								jButtonTreureNovaModalitat = new JButton();
								jPanelAfegirModalitat
									.add(jButtonTreureNovaModalitat);
								jButtonTreureNovaModalitat.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
								jButtonTreureNovaModalitat.setBounds(14, 56, 63, 35);
								jButtonTreureNovaModalitat.addActionListener(new java.awt.event.ActionListener() {
								      public void actionPerformed(ActionEvent e){
								    	  jButtonTreureNovaModalitat_actionPerformed(e);
								      }
								});
							}
						}
						{
							jPanel6 = new JPanel();
							GridBagLayout jPanel6Layout = new GridBagLayout();
							jPanel6Layout.rowWeights = new double[] {0.0};
							jPanel6Layout.rowHeights = new int[] {300};
							jPanel6Layout.columnWeights = new double[] {0.1};
							jPanel6Layout.columnWidths = new int[] {7};
							jPanel6.setLayout(jPanel6Layout);
							jPanel4.add(jPanel6, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							{
								jScrollPaneModsNoves = new JScrollPane();
								jScrollPaneModsNoves.setAutoscrolls(true);
								jPanel6.add(jScrollPaneModsNoves, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
								jScrollPaneModsNoves.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Modalitats de la nova variable", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
								jScrollPaneModsNoves.setPreferredSize(new java.awt.Dimension(185, 243));
								{
									/*ListModel jListModsNovesModel = new DefaultComboBoxModel(
										new String[] { "Item One", "Item Two" });*/
									jListModsNoves = new JList();
									jScrollPaneModsNoves
										.setViewportView(jListModsNoves);
									jListModsNoves
										.setModel(jListModsNovesModel);
									jListModsNoves.setPreferredSize(new java.awt.Dimension(331, 450));
									jListModsNoves.addMouseListener(mouseRegla);
								}
							}
						}
					}
				}
			}
			{
				jPanel2 = new JPanel();
				this.add(jPanel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jPanel2.setLayout(null);
				{
					jButtonOK = new JButton();
					jPanel2.add(jButtonOK);
					jButtonOK.setText("D'acord");
					jButtonOK.setBounds(252, 7, 77, 28);
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
					jButtonCancel.setBounds(546, 7, 98, 28);
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
	 * Mostra les modalitats agrupades sota la nova modalitat seleccionada a la llista <code>Agrupació de modalitats</code>.
	 * La modalitat seleccionada s'elimina de la llista <code>Modalitats de la nova variable</code>
	 * @param e, event que detecta que s'ha premut sobre el botó <code>modificar una modalitat ja creada( mà cap a l'esquerra)</code>
	 */
	protected void jButtonTreureNovaModalitat_actionPerformed(ActionEvent e) {
		System.out.println( "este las lleva de derecha a izquierda!! ");
		try{
			Object ob=jListModsNoves.getSelectedValue();
	    	jTextFieldNomModalitatNova.setText((String)ob);	     		
	 		int imod=0;
	 		boolean b=false;
	 		for(int i=0;i<alnomModalitats.size() && !b;i++){
	 			String saux=(String)alnomModalitats.get(i);
				System.out.println( "que hay en alnomModalitats "+saux);
	 			if(saux.compareTo((String)ob)==0){
	 				imod=i;
	 				b=true;
	 			}
	 		}
	 		String[] modsSelec=(String[])alagrupacionsModals.get(imod);
	 		jListModsSeleccModel.clear();
	 		for(int j=0;j<modsSelec.length;j++){
	 			jListModsSeleccModel.insertElementAt(modsSelec[j],j);
			System.out.println( "que hay en modsSelec "+	modsSelec[j]);
	 		}
	 		jListModsNovesModel.removeElementAt(imod);
	 		alagrupacionsModals.remove(imod);
	 		alnomModalitats.remove(imod);
	 		frPare.actualitzarBarraEstat("",false);
		}catch(IndexOutOfBoundsException ex){
			frPare.actualitzarBarraEstat("No s'ha seleccionat cap element",true);
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
	 * Crea la nova variable recodificada
	 * @throws Exception
	 */
	public void crearVariable()throws Exception{
		String nomPropNova=jTextFieldNomVarNova.getText();
		String nomPropAntiga=jTextFieldNomVarSelecc.getText();		
		if(!jListVarsSeleccionadesModel.isEmpty()){
			 int answer=JOptionPane.showConfirmDialog(frPare,"La variable resultant contindrà dades mancats.Desitja continuar?","La variable resultant contindrà dades mancats.Desitja continuar?",JOptionPane.YES_NO_OPTION);
			 if (answer == JOptionPane.YES_OPTION) {
				 if(nomPropNova.compareTo("")==0||nomPropNova==null){
						throw new Exception("No s'ha introduït un nom de variable");
					}else{
						try{
							gestor.crearNovaPropietat(nomPropNova,nomPropAntiga,alnomModalitats,alagrupacionsModals);
							IS_OK=true;
							frPare.actualitzarBarraEstat("S'ha creat la nova variable correctament",false);
						}catch(Exception ex){
							frPare.actualitzarBarraEstat("No s'ha pogut crear la nova variable: "+ex.getMessage(),true);
							ex.printStackTrace();
						}
					}
			 }
		}else{
			if(nomPropNova.compareTo("")==0||nomPropNova==null){
				frPare.actualitzarBarraEstat("No s'ha introduït un nom de variable", true);
			}else{
				try{
					gestor.crearNovaPropietat(nomPropNova,nomPropAntiga,alnomModalitats,alagrupacionsModals);
					IS_OK=true;
					frPare.actualitzarBarraEstat("S'ha creat la nova variable correctament",false);
				}catch(Exception ex){
					frPare.actualitzarBarraEstat("No s'ha pogut crear la nova variable: "+ex.getMessage(),true);
					ex.printStackTrace();
				}
			}
		}		
	}
	
	/**
	 * Crea la nova variable recodificada
	 * @param e, event que detecta que s'ha premut el botó <code>D'acord</code>
	 */
	protected void jButtonOK_actionPerformed(ActionEvent e) {
		try{
			crearVariable();	
			String nom=gestor.nomPropietatPerDefecte("VAR");
   			jTextFieldNomVarNova.setText(nom);
		}catch(Exception ex){
			frPare.actualitzarBarraEstat(ex.getMessage(),true);
		}
			
	}
	/**
	 * Afegeix la modalitat del camp <code>Nom de la nova modalitat</code> al llistat </code>Modalitats de la nova variable</code>
	 * i buida la llista <code>Agrupació de modalitats</code> i el camp <code>Nom de la nova modalitat</code>
	 * @param e, event que detecta que s'ha premut el botó <code>Crear nova modalitat(mà cap a la dreta)</code>
	 */
	protected void jButtonAfegirModalitatNova_actionPerformed(ActionEvent e) {		
		System.out.println( "hola2");
		String nomModalitat=jTextFieldNomModalitatNova.getText();
			//System.out.println( "nommodalitat"+nomModalitat );	
		if(nomModalitat.compareTo("")==0||nomModalitat==null){
			frPare.actualitzarBarraEstat("No s'ha introduït un nom de modalitat", true);
		}else if(jListModsSeleccModel.getSize()==0){
			frPare.actualitzarBarraEstat("No s'ha seleccionat cap valor de modalitat", true);
		}
		else{
			int pos=existeixNomModalitat(nomModalitat);
			if(pos!=-1){
				 int answer=JOptionPane.showConfirmDialog(frPare,"Ja existeix una modalitat amb aquest nom.Desitja substituir-la?","Ja existeix una modalitat amb aquest nom.Desitja substituir-la?",JOptionPane.YES_NO_OPTION);
		   		 if (answer == JOptionPane.YES_OPTION) {
		   			int numMods=jListModsSeleccModel.getSize();
					String[] mods=new String[numMods];
					for(int i=0;i<numMods;i++){
						mods[i]=(String)jListModsSeleccModel.getElementAt(i);
					}
					 
					 alagrupacionsModals.set(pos,mods);					
					jTextFieldNomModalitatNova.setText("");
					jPanelNomModalitat.add(jTextFieldNomModalitatNova);
					jListModsSeleccModel=new DefaultListModel();
					jScrollPaneModalitatsSelecc.setViewportView(jListModsSelecc);
					jListModsSelecc.setModel(jListModsSeleccModel);
					IS_OK=false;
					frPare.actualitzarBarraEstat("",false);
		   		 }
			}else{
				int numMods=jListModsSeleccModel.getSize();
				String[] mods=new String[numMods];
				for(int i=0;i<numMods;i++){
					mods[i]=(String)jListModsSeleccModel.getElementAt(i);
					System.out.println( "ESTAS ACA?"+mods[i] );	
				}
				jListModsNovesModel.addElement(nomModalitat); //este es el que pasa el nombre para el otro lado
				 alnomModalitats.add(nomModalitat);
				 alagrupacionsModals.add(mods);
				 //	System.out.println( "a ver que hay"+jTextFieldNomModalitatNova.getText() );
				jTextFieldNomModalitatNova.setText(""); // este limpia el campo de texto
				jPanelNomModalitat.add(jTextFieldNomModalitatNova);
				
				jListModsSeleccModel=new DefaultListModel();
				jScrollPaneModalitatsSelecc.setViewportView(jListModsSelecc);//
				jListModsSelecc.setModel(jListModsSeleccModel);
				IS_OK=false;
				frPare.actualitzarBarraEstat("",false);
			}
			
		}		
	}
	/**
	 * Indica si ja existeix al llistat <code>Modalitats de la nova variable</code> una modalitat de nom <code>smod</code>
	 * @param smod, nom de la modalitat a comprovar
	 * @return -1 si no existeix, la posició de la modalitat dins la llista altrament
	 */
	public int existeixNomModalitat(String smod){
		int res=-1;
		for(int i=0;i<alnomModalitats.size()&& res==-1;i++){
			String s=(String)alnomModalitats.get(i);
			if(s.compareTo(smod)==0)res=i;
		}
		return res;
	}
	/**
	 * Afegeix les modalitats seleccionades de la llista <code>Agrupació de modalitats</code> a la llista
	 * <code>Llista de modalitats</code> iles elimina del llistat <code>Agrupació de modalitats</code>
	 * @param e, event que detecta que s'ha premut el botó <code>Treure modalitats ( mà cap a l'esquerra) </code>
	 */
	protected void jButtonTreureMod_actionPerformed(ActionEvent e) {
		IS_OK=false;
		 Object[] vars = jListModsSelecc.getSelectedValues();
		    boolean hay_mas_vars = true;
		    int n = 0;
		    do {
		      try {
		    	  jListVarsSeleccionadesModel.addElement(vars[n]);
		    	  jListModsSeleccModel.removeElement(vars[n]);
		        n++;
		      }
		      catch (Exception exc) { hay_mas_vars = false; }
		    } while (hay_mas_vars);
		
	}
	/**
	 * Afegeix les modalitats seleccionades de la llista <code>Llista de modalitats</code> al llistat <code>Agrupació de modalitats</code> i les
	 * elimina de la <code>Llista de modalitats</code> 
	 * @param e, event que detecta que s'ha premut el botó <code>Afegir modalitats(mà cap a la dreta)</code>
	 */
	protected void jButtonAfegirMod_actionPerformed(ActionEvent e) {
	System.out.println( "hola3");
		IS_OK=false;
		 Object[] vars = jListVarsSeleccionades.getSelectedValues();
		    boolean hay_mas_vars = true;
		    int n = 0;
		    do {
		      try {
		    	jListModsSeleccModel.addElement(vars[n]);
		    	jListVarsSeleccionadesModel.removeElement(vars[n]);
		        n++;
		      }
		      catch (Exception exc) { hay_mas_vars = false; }
		    } while (hay_mas_vars);
		
	}
	/**
	 * Canvia la propietat que s'estava recodificant per la nova variable seleccionada
	 * Si els canvis no s'havien guardat, es mostra un missatge indicant que les modificacions es perdran
	 * 
	 */
	public void canviarPropietat(){
		if(!IS_OK){
	   		 int answer=JOptionPane.showConfirmDialog(frPare,"Es perdran els canvis efectuats a la variable, desitja crear la nova variable?","Es perdran els canvis efectuats a la variable, desitja crear la nova variable?",JOptionPane.YES_NO_CANCEL_OPTION);
	   		 if (answer == JOptionPane.YES_OPTION) {
	   			 try{   			 
	   			crearVariable();	
	   			alagrupacionsModals=new ArrayList();
	   			alnomModalitats=new ArrayList();
	   			Object obj=jListVars.getSelectedValue();
	   			jTextFieldNomVarSelecc.setText((String)obj);
	   			jPanelNomVarSelecc.add(jTextFieldNomVarSelecc);
	   			String[] modalitats=gestor.obtenirLlistaMods((String)obj);
	   			jListVarsSeleccionadesModel.clear();
	   			for(int i=0;i<modalitats.length;i++){
	   				jListVarsSeleccionadesModel.insertElementAt(modalitats[i],i);
	   			}
	   			jScrollPaneModalitats.setViewportView(jListVarsSeleccionades);
	   			jListVarsSeleccionades.setModel(jListVarsSeleccionadesModel);
	   			String nom=gestor.nomPropietatPerDefecte("VAR");
	   			jTextFieldNomVarNova.setText(nom);
	   			
	   			jTextFieldNomModalitatNova.setText("");
	   			
	   			jListModsSeleccModel=new DefaultListModel();
	   			jScrollPaneModalitatsSelecc.setViewportView(jListModsSelecc);
	   			jListModsSelecc.setModel(jListModsSeleccModel);
	   			jListModsNovesModel=new DefaultListModel();
	   			jScrollPaneModsNoves.setViewportView(jListModsNoves);
	   			jListModsNoves.setModel(jListModsNovesModel);
	   			
	   			 }catch(Exception ex){
	   				 frPare.actualitzarBarraEstat(ex.getMessage(),true);
	   			 }
	   			
	   		    } else if (answer == JOptionPane.NO_OPTION) {
	   		    	
	   		    	alagrupacionsModals=new ArrayList();
	   				alnomModalitats=new ArrayList();
	   				Object obj=jListVars.getSelectedValue();
	   				jTextFieldNomVarSelecc.setText((String)obj);
	   				jPanelNomVarSelecc.add(jTextFieldNomVarSelecc);
	   				String[] modalitats=gestor.obtenirLlistaMods((String)obj);
	   				jListVarsSeleccionadesModel.clear();
	   				for(int i=0;i<modalitats.length;i++){
	   					jListVarsSeleccionadesModel.insertElementAt(modalitats[i],i);
	   				}
	   				jScrollPaneModalitats.setViewportView(jListVarsSeleccionades);
	   				jListVarsSeleccionades.setModel(jListVarsSeleccionadesModel);
		   			String nom=gestor.nomPropietatPerDefecte("VAR");
		   			jTextFieldNomVarNova.setText(nom);
	   				jPanelNomVarNova.add(jTextFieldNomVarNova);
	   				jTextFieldNomModalitatNova.setText("");
	   				jPanelNomModalitat.add(jTextFieldNomModalitatNova);
	   				jListModsSeleccModel=new DefaultListModel();
	   				jScrollPaneModalitatsSelecc.setViewportView(jListModsSelecc);
	   				jListModsSelecc.setModel(jListModsSeleccModel);
	   				jListModsNovesModel=new DefaultListModel();
	   				jScrollPaneModsNoves.setViewportView(jListModsNoves);
	   				jListModsNoves.setModel(jListModsNovesModel);
	   				
	   		    } 
			}else{
				alagrupacionsModals=new ArrayList();
				alnomModalitats=new ArrayList();
				Object obj=jListVars.getSelectedValue();
				jTextFieldNomVarSelecc.setText((String)obj);
				jPanelNomVarSelecc.add(jTextFieldNomVarSelecc);
				String[] modalitats=gestor.obtenirLlistaMods((String)obj);
				jListVarsSeleccionadesModel.clear();
				for(int i=0;i<modalitats.length;i++){
					jListVarsSeleccionadesModel.insertElementAt(modalitats[i],i);
				}
				jScrollPaneModalitats.setViewportView(jListVarsSeleccionades);
				jListVarsSeleccionades.setModel(jListVarsSeleccionadesModel);
	   			String nom=gestor.nomPropietatPerDefecte("VAR");
	   			jTextFieldNomVarNova.setText(nom);
				jPanelNomVarNova.add(jTextFieldNomVarNova);
				jTextFieldNomModalitatNova.setText("");
				jPanelNomModalitat.add(jTextFieldNomModalitatNova);
				jListModsSeleccModel=new DefaultListModel();
				jScrollPaneModalitatsSelecc.setViewportView(jListModsSelecc);
				jListModsSelecc.setModel(jListModsSeleccModel);
				jListModsNovesModel=new DefaultListModel();
				jScrollPaneModsNoves.setViewportView(jListModsNoves);
				jListModsNoves.setModel(jListModsNovesModel);
				
			}
			
	}

	/**
	  * Selecciona la nova variable a recodificar, posant les seves modalitats a la <code>Llista de modalitats</code> i el seu nom al camp
	  * de <code>Variable seleccionada</code>
	  * Si ja hi havia un altre variable seleccionada i s'havien efectuat canvis que no s'havien guardat, es mostra una finestra indicant que els canvis es perdran.
	  * @param e, event que detecta que s'ha premut el botó <code>Seleccionar la variable a recodificar(mà cap a la dreta)</code>
	  */
	protected void jButtonDreta_actionPerformed(ActionEvent e) {
		this.canviarPropietat();
		
	}

}

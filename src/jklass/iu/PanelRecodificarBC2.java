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
import java.util.HashMap; 


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
public class PanelRecodificarBC2 extends javax.swing.JPanel {
	private JPanel jPanel1;
	private JButton jButtonCancel;
	private JPanel jPanelDreta;
	private JButton jButtonTreureNovaModalitat;
	private JList jListModsNoves;
	private JScrollPane jScrollPaneModsNoves;
	private JPanel jPanel6;
	private JTextField jTextFieldNomVarNova = new JTextField();
		private JTextField jTextFieldNomVarNova2 = new JTextField();

	private JButton jButtonAfegirModalitatNova;
	private JPanel jPanelAfegirModalitat;
	private JList jListModsSelecc;
	private JScrollPane jScrollPaneModalitatsSelecc;
	private JPanel jPanelNomModalitat;
	private JPanel jPanel5;
	JTextField jTextFieldNomModalitatNova = new JTextField();
	private JLabel jLabelNomVar;
	private JLabel jLabelNomVar2;
	private JPanel jPanel4;
	private JPanel jPanelNomVarNova;
	private JPanel jPanelNomVarNova2;
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
	private JButton jButtonDreta2;
	private JList jListVars;
	private JScrollPane jScrollPaneVars;
	private JButton jButtonOK;
	private JPanel jPanel2;
	FrPrincipal frPare;
	GestorKlass gestor;	
	//DefaultListModel jListVarsModel=new DefaultListModel();
	//DefaultListModel jListVarsSeleccionadesModel=new DefaultListModel();
	//DefaultListModel jListModsSelecc= new DefaultListModel();
	//DefaultListModel jListModsNovesModel=new DefaultListModel();
	
	 DefaultListModel listModelSN = new DefaultListModel();////ale
   JList jListSelcN = new JList(listModelSN);////ale
	 DefaultListModel listModelVN = new DefaultListModel();//ale
  // JList jListVarsN = new JList(listModelVN);//ale

	DefaultListModel jListVarsModel=new DefaultListModel();
	
	JList jListVarsN = new JList(listModelVN);


	DefaultListModel jListVarsSeleccionadesModel=new DefaultListModel();
	DefaultListModel jListModsSeleccModel= new DefaultListModel();
	DefaultListModel jListModsNovesModel=new DefaultListModel();
	DefaultListModel modelo = new DefaultListModel();
	


	
	
	private boolean IS_OK;//ens indica si s'han guardat els canvis
	ArrayList alnomModalitats=new ArrayList();//cada posició es correspon amb un posició de l'array agrupacionsModals i conté el nom de la nova modalitat
	//en alnomModalitats estan los estados
	ArrayList alagrupacionsModals=new ArrayList();//a cada posició conté un vector de modalitats
   //en alagrupacionsModals tiene las variables de cada estado
	
	String [] ests = new String [10];
	String[] listavars=new String[100];
	String[] listaprocesos=new String[20];
	//listavars []todas = new todas[10];
 String [] arraestados = new String [10];
 String [] arraestadosmap;
 String proceso=null;
 
 private HashMap info = new HashMap();



	
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
	      //  	Object vars = jListModsSelecc.getSelectedValue();	 		    
	        //	 jListVarsSeleccionadesModel.addElement(vars);
	 		   // jListModsSeleccModel.removeElement(vars);
	        	IS_OK=false; 
	         }
		 }
	 };
	 
	 /**
	  * Constructor
	  * @param fr, finestra pare
	  * @param gk, GestorKlass
	  */
	public PanelRecodificarBC2(FrPrincipal fr,GestorKlass gk) {
		super();
		frPare = fr;
		gestor = gk;	
		IS_OK=true;
		jTextFieldNomModalitatNova.setText("ALE");
		String s=gestor.nomPropietatPerDefecte("VAR");
		
		jTextFieldNomVarNova.setText(s);
		String[][] llProps;
	    int lon;
		 llProps = gestor.obtenirLlistaIDsProps();
		    lon = llProps[1].length;//la posició 0 conté les propietats numèriques
		    for (int i = 0; i < lon; i++) {		    	
		    	jListVarsModel.insertElementAt(llProps[1][i], i);
		    }
	
		int lon2;	
			lon2 = llProps[0].length;//la posició 0 conté les propietats numèriques
		    for (int i = 0; i < lon2; i++) {		    	
		    	jListVarsModel.insertElementAt(llProps[0][i], i);
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
			this.setBorder(BorderFactory.createTitledBorder(null, "Definicion de Procesos", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
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
					jScrollPaneVars.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Llista de variables", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
					{
						/*ListModel jListVarsModel = new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });*/
						jListVars = new JList();
						jScrollPaneVars.setViewportView(jListVarsN);
						jListVarsN.setModel(jListVarsModel); 
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
						jButtonDreta.setBounds(7, 84, 56, 35);
						jButtonDreta.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e){
						    	  jButtonDreta_actionPerformed(e); //este es el primer boton
						      }
						});
					}
					
					{
						jButtonDreta2 = new JButton();
						jPanelDreta.add(jButtonDreta2);
						jButtonDreta2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
						jButtonDreta2.setBounds(7, 184, 56, 35);
						jButtonDreta2.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e){
						    	  jButtonDreta2_actionPerformed(e);
						      }
						});
					}
	
					
				}
				{
					jPanelVarsSelecc = new JPanel();
					GridBagLayout jPanelVarsSeleccLayout = new GridBagLayout();
					jPanelVarsSeleccLayout.rowWeights = new double[] {0.0, 0.1};
					jPanelVarsSeleccLayout.rowHeights = new int[] {49, 70};
					jPanelVarsSeleccLayout.columnWeights = new double[] {0.1};
					jPanelVarsSeleccLayout.columnWidths = new int[] {7};
					jPanelVarsSelecc.setLayout(jPanelVarsSeleccLayout);
					jPanel1.add(jPanelVarsSelecc, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						jPanelNomVarSelecc = new JPanel(); //ale aca agrego la etiqueta
				
               
						jPanelNomVarNova2 = new JPanel();
					//	jPanel3.add(jPanelNomVarNova2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPanelNomVarNova2.setLayout(null);
						{
							jLabelNomVar2 = new JLabel();
							jPanelNomVarNova2.add(jLabelNomVar2);
							jLabelNomVar2.setText("Estats:");
							jLabelNomVar2.setBounds(7, 17, 80, 14);
						}

						{
							/*jTextFieldNomVarNova = new JTextField();*/
							jPanelNomVarNova2.add(jTextFieldNomVarNova2);
							jTextFieldNomVarNova2.setBounds(60, 17, 70, 21); /////textfield de Estats
							jTextFieldNomVarNova2.setText("ESTAT0");
						}

					
						
						jPanelVarsSelecc.add(jPanelNomVarNova2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPanelNomVarSelecc.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "esto sacarlo", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
						{
							/*jTextFieldNomVarSelecc = new JTextField();*/
							jPanelNomVarSelecc.add(jTextFieldNomVarSelecc);
							jTextFieldNomVarSelecc.setPreferredSize(new java.awt.Dimension(126, 20));
							jTextFieldNomVarSelecc.setEditable(true);
						}
						
						
						
					}
					{
						jScrollPaneModalitats = new JScrollPane();
						jScrollPaneModalitats.setAutoscrolls(true);
						jPanelVarsSelecc.add(jScrollPaneModalitats, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jScrollPaneModalitats.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Variables d'estat", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
						{
							/*ListModel jListVarsSeleccionadesModel = new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });*/
							jListVarsSeleccionades = new JList();
							jScrollPaneModalitats.setViewportView(jListVarsSeleccionades);
						
							jListVarsN.setModel(jListVarsModel);
							jListVarsSeleccionades.setModel(jListVarsSeleccionadesModel);//solo comenté esto
							jListVarsSeleccionades.setModel(jListModsSeleccModel); //solo cambié esto para que pasen al 2do panel
					   

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
					jPanel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED), "Procesos", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
					{
						jPanelNomVarNova = new JPanel();
						jPanel3.add(jPanelNomVarNova, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPanelNomVarNova.setLayout(null);
						{
							jLabelNomVar = new JLabel();
							jPanelNomVarNova.add(jLabelNomVar);
							jLabelNomVar.setText("Procés:");
							jLabelNomVar.setBounds(7, 7, 133, 14);
						}
						{
							/*jTextFieldNomVarNova = new JTextField();*/
							jPanelNomVarNova.add(jTextFieldNomVarNova);
							jTextFieldNomVarNova.setBounds(60, 7, 50, 21);
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
							jPanel5Layout.rowHeights = new int[] {1, 7};
							jPanel5Layout.columnWeights = new double[] {0.1};
							jPanel5Layout.columnWidths = new int[] {7};
							jPanel5.setLayout(jPanel5Layout);
							jPanel4.add(jPanel5, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
							{
								jPanelNomModalitat = new JPanel();
							//	jPanel5.add(jPanelNomModalitat, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
								jPanelNomModalitat.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Nom de la nova modalitat", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
								{
									/*jTextFieldNomModalitatNova = new JTextField();*/
									jPanelNomModalitat.add(jTextFieldNomModalitatNova);
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
								jScrollPaneModalitatsSelecc.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Llista d'estats", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
								jScrollPaneModalitatsSelecc.setPreferredSize(new java.awt.Dimension(170, 247));
								{
									/*ListModel jListModsSeleccModel = new DefaultComboBoxModel(
										new String[] { "Item One", "Item Two" });*/
									jListModsSelecc = new JList();
									// jListModsSelecc=new DefaultListModel();
									jListModsSelecc.setModel(modelo);

									jScrollPaneModalitatsSelecc.setViewportView(jListModsSelecc);
								//	jListModsSelecc.setModel(jListModsSeleccModel); //BOLUDO este va comentado
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
								jButtonAfegirModalitatNova.setBounds(14, 64, 63, 35);
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
								jButtonTreureNovaModalitat.setBounds(14, 106, 63, 35);
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
								jScrollPaneModsNoves.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Llista de processos", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
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
	    	jTextFieldNomVarNova.setText((String)ob);	     		
	 		int imod=0;
	 		boolean b=false;
	 		for(int i=0;i<alnomModalitats.size() && !b;i++){
	 			String saux=(String)alnomModalitats.get(i);
				System.out.println( "que hay en alnomModalitats7777 "+saux);
	 			if(saux.compareTo((String)ob)==0){
	 				imod=i;
	 				b=true;
	 			}
	 		}
	 		String[] modsSelec=(String[])alagrupacionsModals.get(imod);
						System.out.println( "cual es el largo "+	modsSelec.length);

	 		modelo.clear();
	 		for(int j=0;j<modsSelec.length;j++){
	 			modelo.insertElementAt(modsSelec[j],j);
				//modelo.insertElementAt(modsSelec[j],j);
			System.out.println( "que hay en modsSelecdomingo9999 "+	modsSelec[j]);
	 		}
	 		//jListModsNovesModel.removeElementAt(imod);
			jListModsNovesModel.removeElementAt(0);
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
			System.out.println( "le di al OK ");

		try{
		//	crearVariable();	
		//	String nom=gestor.nomPropietatPerDefecte("VAR");
   		//	jTextFieldNomVarNova.setText(nom);
		for (int i=0; i < alnomModalitats.size();i++){
		//gestor. matriusCarregades[0].estados[i]=(String)alnomModalitats.get(i);
	     ests[i]=(String)alnomModalitats.get(i);
		  gestor. matriusCarregades[0].estados[i]=ests[i];
		  	System.out.println( "que hay en ests "+ests[i]);

			}
			
			
			// for (int p = 0; p < alagrupacionsModals.size(); p++) {
         //    ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
        //  System.out.println( "cual es el largo de alagrupacionsmodals"+  alagrupacionsModals.size());
         for (int j=0;j<alnomModalitats.size()-1;j++){
			arraestados = (String[]) alagrupacionsModals.get(1);
			//Si pongo get(1) tengo en arraestados las variables del estado 1, ojo que empieza en CERO
			info.put(ests[j],(String[]) alagrupacionsModals.get(j));
			}
			
		
	/*	 for (int s = 0; s <arraestados.length; s++) {
         //    ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
         

			 System.out.println( "que hay en alagrupacions de cero"+  arraestados[s]);
             //tendria que crear un map de mi array ests de arraestados asi tengo para
				 //cada estado sus variables   			
					}*/
         //  String est = ests[1];
			
		/*	for(int t=0;t< 3;t++ ){
			arraestadosmap= (String[])info.get(est);
			 System.out.println( "que hay en arrayestadosmap"+  arraestadosmap[t]); 
			};*/
			 System.out.println( "antes del confirmaestados");
			gestor.ConfirmaEsts(alnomModalitats,alagrupacionsModals);
			gestor.SetearProceso(proceso);
			 System.out.println( "despues del confirmaestados");
			frPare.actualitzarBarraEstat("S'ha creat el procés correctament",false);
	
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
		System.out.println( "hola2proceso"); // este lleva los procesos a la derecha
		
		String nomModalitat=jTextFieldNomVarNova.getText();
			//System.out.println( "nommodalitat"+nomModalitat );	
		if(nomModalitat.compareTo("")==0||nomModalitat==null){
			frPare.actualitzarBarraEstat("No s'ha introduït un nom de modalitat", true);
		}else if(modelo.getSize()==0){
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
					jTextFieldNomVarNova.setText("");
				//	jPanelNomModalitat.add(jTextFieldNomVarNova);
			   	jPanelNomVarNova.add(jTextFieldNomVarNova);
					modelo=new DefaultListModel();
					jScrollPaneModalitatsSelecc.setViewportView(jListModsSelecc);
					jListModsSelecc.setModel(modelo);
					IS_OK=false;
					frPare.actualitzarBarraEstat("",false);
		   		 }
			}else{
				int numMods=modelo.getSize();
				String[] mods=new String[numMods];
				for(int i=0;i<numMods;i++){
					mods[i]=(String)modelo.getElementAt(i);
					System.out.println( "ESTAS ACA2511?"+mods[i] ); //Aca tengo los estados sabado	
				
				}
				jListModsNovesModel.addElement(nomModalitat); //este es el que pasa el nombre para el otro lado
					System.out.println( "Este es el proceso2511"+nomModalitat );
					proceso=nomModalitat;
					//gestor.SetearProceso(proceso);
				 alnomModalitats.add(nomModalitat);
				 
				 
				 alagrupacionsModals.add(mods);
				 //	System.out.println( "a ver que hay"+jTextFieldNomModalitatNova.getText() );
				jTextFieldNomVarNova.setText(""); // este limpia el campo de texto
				jPanelNomVarNova.add(jTextFieldNomVarNova);
				
				modelo=new DefaultListModel();
				jScrollPaneModalitatsSelecc.setViewportView(jListModsSelecc);//
				jListModsSelecc.setModel(modelo);
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
	System.out.println( "ale sabado 18" );
	
	try{
			Object ob=jListModsSelecc.getSelectedValue();
	    	jTextFieldNomModalitatNova.setText((String)ob);	     		
	 		int imod=0;
	 		boolean b=false;
			System.out.println( "alnomModalitats está vacio sabado18= "+alnomModalitats.size());
	 		for(int i=0;i<alnomModalitats.size() && !b;i++){
	 			String saux=(String)alnomModalitats.get(i);
					System.out.println( "que hay en alnomModalitatsmio sabado18 "+saux);
	 			if(saux.compareTo((String)ob)==0){
	 				imod=i;
	 				b=true;
	 			}
	 		}
	 		String[] modsSelec=(String[])alagrupacionsModals.get(imod);
	 	//	jListModsSeleccModel.clear();
			
	 		for(int j=0;j<modsSelec.length;j++){
	 			jListModsSeleccModel.insertElementAt(modsSelec[j],j);
				System.out.println( "que hay en modsSelecmio domi18 bb "+	modsSelec[j]);
	 		}
	 	//	modelo.removeElement(imod); //borra el nombre del estado
	 	System.out.println( "cuanto vale imod "+ imod);

	 	 modelo.removeElementAt(imod);
			alagrupacionsModals.remove(imod);
	 		alnomModalitats.remove(imod);
			
	 		frPare.actualitzarBarraEstat("",false);
		}catch(IndexOutOfBoundsException ex){
			frPare.actualitzarBarraEstat("Ale No se ha seleccionado nada",true);
		}		
	
	}
	/**
	 * Afegeix les modalitats seleccionades de la llista <code>Llista de modalitats</code> al llistat <code>Agrupació de modalitats</code> i les
	 * elimina de la <code>Llista de modalitats</code> 
	 * @param e, event que detecta que s'ha premut el botó <code>Afegir modalitats(mà cap a la dreta)</code>
	 */
	protected void jButtonAfegirMod_actionPerformed(ActionEvent e) {
	System.out.println( "hola3");
		String nomModalitat=jTextFieldNomVarNova2.getText();
		//este los lleva a la derecha
			if(nomModalitat.compareTo("")==0||nomModalitat==null){
			frPare.actualitzarBarraEstat("No s'ha introduït un nom de modalitat", true);}
else{
		
		///
		/*	int numMods=jListModsSeleccModel.getSize();
				String[] mods=new String[numMods];
				for(int i=0;i<numMods;i++){
					mods[i]=(String)jListModsSeleccModel.getElementAt(i);
					System.out.println( "A VER"+mods[i] );
					}*/	
   	 	 	      
			
		//	System.out.println( "nommodalitat"+nomModalitat );	
		
			System.out.println( "Este es el que los lleva  ala derecha");
	     
			jTextFieldNomVarNova2.setText(""); // este es el que limpia el campo de texto
			
			int numMods2=jListModsSeleccModel.getSize();
				String[] mods=new String[numMods2];
				for(int i=0;i<numMods2;i++){
					mods[i]=(String)jListModsSeleccModel.getElementAt(i);
					System.out.println( "A VERlunes"+mods[i] );
					}	

			
			 
			 boolean hay_mas_vars = true;
    int n = 0;
	
    do {
      try {
		// System.out.println( "que hay en vars[0] antes?"+vars[0]);
        jListModsSeleccModel.removeElement(mods[n]);
		  // jListVarsModel.addElement(vars[n]);
       
		  // System.out.println( "que hay en vars[0] despues?"+vars[0]);

        n++;
      }
      catch (Exception exc) { hay_mas_vars = false; }
    } while (hay_mas_vars);
  

			
         //	jListModsNovesModel.addElement(nomModalitat);
					//	jListModsSelecc.setModel(jListModsSeleccModel);
      /*	
			jListModsNovesModel.addElement(nomModalitat);//  este es el que lleva el nombre a la derecha
			alnomModalitats.add(nomModalitat);
			alagrupacionsModals.add(mods);*/
			
			/*	jListModsSeleccModel=new DefaultListModel();
				jScrollPaneModalitatsSelecc.setViewportView(jListModsSelecc);// estos 3 limpian las variables
				jListModsSelecc.setModel(jListModsSeleccModel);*/

		modelo.addElement(nomModalitat);
		alnomModalitats.add(nomModalitat);
			alagrupacionsModals.add(mods);
			
 /*	modelo = new DefaultListModel();
	jScrollPaneModalitatsSelecc.setViewportView(jListModsSelecc);
   	jListModsSelecc.setModel(modelo);*/

	
	
		}
		
	


		
							
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
						jTextFieldNomVarNova2.setText(nom);
	   			
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
		//this.canviarPropietat();
		 System.out.println( "a ver ale quien es este?");

		Object[] vars = jListVarsN.getSelectedValues();
    boolean hay_mas_vars = true;
    int n = 0;
	 System.out.println( "entre en222 jButtonafegirModboludo");
	 System.out.println( "holaaa que hay en vars[0] aca?"+vars[0]);
    do {
      try {
		// System.out.println( "que hay en vars[0] antes?"+vars[0]);
        jListModsSeleccModel.addElement(vars[n]);
        jListVarsModel.removeElement(vars[n]);
		  // System.out.println( "que hay en vars[0] despues?"+vars[0]);

        n++;
      }
      catch (Exception exc) { hay_mas_vars = false; }
    } while (hay_mas_vars);
  }
  
  
 	protected void jButtonDreta2_actionPerformed(ActionEvent e) {
		//this.canviarPropietat();
		 System.out.println( "ale este las saca nop?");

		Object[] vars = jListVarsSeleccionades.getSelectedValues();
    boolean hay_mas_vars = true;
    int n = 0;
	
    do {
      try {
		// System.out.println( "que hay en vars[0] antes?"+vars[0]);
        jListModsSeleccModel.removeElement(vars[n]);
		   jListVarsModel.addElement(vars[n]);
       
		  // System.out.println( "que hay en vars[0] despues?"+vars[0]);

        n++;
      }
      catch (Exception exc) { hay_mas_vars = false; }
    } while (hay_mas_vars);
  }
 
  
  

}

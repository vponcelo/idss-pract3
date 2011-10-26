package jklass.iu;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import jklass.nucli.GestorKlass;
/** Classe que dibuixa el submenú per modificar les modalitats i tipologia d'una variable categòrica concreta
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
public class DlgValorsCatMetadades extends javax.swing.JDialog {
	private JPanel jPanelNomProp;
	private JButton jButtonModifica;
	private JButton jButtonAmunt;
	private JButton jButtonAvall;
	private JPanel jPanel4;	
	private JRadioButton jRadioButtonCateg= new JRadioButton();
	private JRadioButton jRadioButtonNominal= new JRadioButton();
	private JRadioButton jRadioButtonOrdinal= new JRadioButton();
	private JPanel jPanelTipusCat;
	private JTextField jTextFieldNovaMod=new JTextField();
	private JPanel jPanelNovaMod;
	private JPanel jPanel3;
	private JButton jButtonEsquerra;
	private JPanel jPanel2;
	private JList jListModalitats;
	private JScrollPane jScrollPaneModalitats;
	private JButton jButtonCancel;
	private JPanel jPanelBotons;
	private JPanel jPanel1;
	private JTextField jTextFieldNomProp=new JTextField();
	private JLabel jLabelPropietat;
	boolean OKpulsat;
	FrPrincipal frPare;
	GestorKlass gestor;
	String nomPropietat;
	ArrayList llistaModalitats=new ArrayList();
	DefaultListModel jListPropietatsModel = new DefaultListModel();
	ButtonGroup group = new ButtonGroup();
	int tipologia;//0=categorica, 1=ordinal i 2=nominal
	
	/***
	 * Constructor
	 * @param fr, finestra pare
	 * @param gk,GestorKlass
	 * @param nomProp, nom de la propietat categòrica a modificar
	 * @param llistaMods, llista de modalitats de la variable seleccionada
	 * @param tipus, tipologia de la variable segons la següent codificació:
	 * tipus==0, categòrica
	 * tipus==1, ordinal
	 * tipus==2, nominal
	 */	
	public DlgValorsCatMetadades(FrPrincipal fr,GestorKlass gk,String nomProp,String[] llistaMods,int tipus) {
		super(fr,"Metainformació de la variable",true);
		frPare = fr;
		gestor = gk;	
		nomPropietat=nomProp;
		OKpulsat=false;
		tipologia=tipus;
		if(tipus==0)jRadioButtonCateg.setSelected(true);
		else if(tipus==1)jRadioButtonOrdinal.setSelected(true);
		else jRadioButtonNominal.setSelected(true);
		for(int i=0;i<llistaMods.length;i++){
			llistaModalitats.add(llistaMods[i]);
			jListPropietatsModel.addElement(llistaMods[i]);
		}
			
		jTextFieldNomProp.setText(nomProp);
		try{
			initGUI();
		}catch(Exception ex){
			ex.getMessage();
		}	
	}
	/***
	 * Dibuixa el menú
	 *
	 */
	private void initGUI() {
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.0, 0.0, 0.1};
			thisLayout.rowHeights = new int[] {31, 201, 7};
			thisLayout.columnWeights = new double[] {0.0};
			thisLayout.columnWidths = new int[] {480};
			getContentPane().setLayout(thisLayout);
			{
				jPanelNomProp = new JPanel();
				getContentPane().add(jPanelNomProp, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jPanelNomProp.setLayout(null);
				{
					jLabelPropietat = new JLabel();
					jPanelNomProp.add(jLabelPropietat);
					jLabelPropietat.setText("Propietat: ");
					jLabelPropietat.setBounds(14, 7, 58, 21);
					jLabelPropietat.setFont(new java.awt.Font("Tahoma",1,10));
				}
				{
					
					jPanelNomProp.add(jTextFieldNomProp);
					jTextFieldNomProp.setBounds(84, 7, 392, 21);
				}
			}
			{
				jPanel1 = new JPanel();
				GridBagLayout jPanel1Layout = new GridBagLayout();
				jPanel1Layout.rowWeights = new double[] {0.1};
				jPanel1Layout.rowHeights = new int[] {7};
				jPanel1Layout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1};
				jPanel1Layout.columnWidths = new int[] {65, 137, 78, 7};
				jPanel1.setLayout(jPanel1Layout);
				getContentPane().add(jPanel1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED), "Modalitats de la variable", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
				{
					jScrollPaneModalitats = new JScrollPane();
					jScrollPaneModalitats.setAutoscrolls(true);
					jPanel1.add(jScrollPaneModalitats, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jScrollPaneModalitats.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Llista modalitats", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
					{
						jListModalitats = new JList();
						jScrollPaneModalitats.setViewportView(jListModalitats);
						jListModalitats.setModel(jListPropietatsModel);
						jListModalitats.setPreferredSize(new java.awt.Dimension(379, 399));
					}
				}
				{
					jPanel2 = new JPanel();
					jPanel1.add(jPanel2, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanel2.setLayout(null);
					{
						jButtonEsquerra = new JButton();
						jPanel2.add(jButtonEsquerra);
						jButtonEsquerra.setIcon(new ImageIcon(getClass()
							.getClassLoader()
							.getResource("images/esquerra.gif")));
						jButtonEsquerra.setBounds(7, 14, 63, 35);
						jButtonEsquerra.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e){
						    	  jButtonEsquerra_actionPerformed(e);
						      }
						});
					}
				}
				{
					jPanel3 = new JPanel();
					GridBagLayout jPanel3Layout = new GridBagLayout();
					jPanel3Layout.rowWeights = new double[] {0.0, 0.1};
					jPanel3Layout.rowHeights = new int[] {57, 7};
					jPanel3Layout.columnWeights = new double[] {0.1};
					jPanel3Layout.columnWidths = new int[] {7};
					jPanel3.setLayout(jPanel3Layout);
					jPanel1.add(jPanel3, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						jPanelNovaMod = new JPanel();
						jPanel3.add(jPanelNovaMod, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPanelNovaMod.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Nova modalitat", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
						jPanelNovaMod.setLayout(null);
						{
							
							jPanelNovaMod.add(jTextFieldNovaMod);
							jTextFieldNovaMod.setBounds(7, 21, 175, 21);
						}
					}
					{
						jPanelTipusCat = new JPanel();
						jPanel3.add(jPanelTipusCat, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jPanelTipusCat.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED), "Tipologia var. categòrica", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
						jPanelTipusCat.setLayout(null);
						{
							
							jPanelTipusCat.add(jRadioButtonOrdinal);
							jRadioButtonOrdinal.setText("Ordinal");
							jRadioButtonOrdinal.setBounds(21, 28, 126, 21);
							group.add(jRadioButtonOrdinal);
						}
						{
							
							jPanelTipusCat.add(jRadioButtonNominal);
							jRadioButtonNominal.setText("Nominal");
							jRadioButtonNominal.setBounds(21, 49, 105, 21);
							group.add(jRadioButtonNominal);
						}
						{
							
							jPanelTipusCat.add(jRadioButtonCateg);
							jRadioButtonCateg.setText("Categòrica");
							jRadioButtonCateg.setBounds(21, 70, 98, 21);
							group.add(jRadioButtonCateg);
						}
					}
				}
				{
					jPanel4 = new JPanel();
					jPanel1.add(jPanel4, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanel4.setLayout(null);
					jPanel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED), "Reordena", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
					jPanel4.setPreferredSize(new java.awt.Dimension(62, 175));
					{
						jButtonAvall = new JButton();
						jPanel4.add(jButtonAvall);
						jButtonAvall.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/avall.GIF")));
						jButtonAvall.setBounds(14, 91, 35, 28);
						jButtonAvall.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e){
						    	  jButtonAvall_actionPerformed(e);
						      }
						});
					}
					{
						jButtonAmunt = new JButton();
						jPanel4.add(jButtonAmunt);
						jButtonAmunt.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/amunt.GIF")));
						jButtonAmunt.setBounds(14, 56, 35, 28);
						jButtonAmunt.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e){
						    	  jButtonAmunt_actionPerformed(e);
						      }
						});
					}
				}
			}
			{
				jPanelBotons = new JPanel();
				getContentPane().add(jPanelBotons, new GridBagConstraints(0, 2, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					jButtonModifica = new JButton();
					jPanelBotons.add(jButtonModifica);
					jButtonModifica.setText("Modifica");
					jButtonModifica.setPreferredSize(new java.awt.Dimension(78, 28));
					jButtonModifica.addActionListener(new java.awt.event.ActionListener() {
					      public void actionPerformed(ActionEvent e){
					    	  jButtonModifica_actionPerformed(e);
					      }
					});
				}
				{
					jButtonCancel = new JButton();
					jPanelBotons.add(jButtonCancel);
					jButtonCancel.setText("Cancel.la");
					jButtonCancel.setPreferredSize(new java.awt.Dimension(78, 28));
					jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
					      public void actionPerformed(ActionEvent e){
					    	  jButtonCancel_actionPerformed(e);
					      }
					});
				}
			}
			this.setSize(509, 305);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/****
	 * Tanca la finestra
	 * @param e, event que detecta que s'ha premut el botó <code>Cancel.la</code>
	 */
	protected void jButtonCancel_actionPerformed(ActionEvent e) {
		this.setVisible(false);	
		
	}
	/**
	 * Guarda els canvis efectuats a la variable i tanca la finestra
	 * @param e, event que detecta que s'ha premut el botó <code>Modifica</code>
	 */
	protected void jButtonModifica_actionPerformed(ActionEvent e) {
		OKpulsat=true;
		if(jRadioButtonCateg.isSelected())tipologia=0;
		else if(jRadioButtonNominal.isSelected())tipologia=2;
		else tipologia=1;
		nomPropietat=jTextFieldNomProp.getText();
		this.setVisible(false);			
	}
	/**
	 * Puja la modalitat seleccionada a la llista de modalitats
	 * @param e, event que detecta que s'ha premut el botó de pujar una modalitat
	 */
	protected void jButtonAmunt_actionPerformed(ActionEvent e) {
		int pos=jListModalitats.getSelectedIndex();
		if(pos>0){
			String sant=(String)llistaModalitats.get(pos-1);
			String sact=(String)llistaModalitats.get(pos);
			llistaModalitats.set(pos-1,sact);
			llistaModalitats.set(pos,sant);
			jListPropietatsModel.clear();
			for(int i=0;i<llistaModalitats.size();i++){
				jListPropietatsModel.addElement(llistaModalitats.get(i));
			}
			jListModalitats.setSelectedIndex(pos-1);
		}		
	}
	/**
	 * Baixa la modalitat seleccionada a la llista de modalitats
	 * @param e, event que detecta que s'ha premut el botó de baixar una modalitat
	 */
	protected void jButtonAvall_actionPerformed(ActionEvent e) {
		int pos=jListModalitats.getSelectedIndex();
		if(pos<=llistaModalitats.size()-2){
			String spost=(String)llistaModalitats.get(pos+1);
			String sact=(String)llistaModalitats.get(pos);
			llistaModalitats.set(pos+1,sact);
			llistaModalitats.set(pos,spost);
			jListPropietatsModel.clear();
			for(int i=0;i<llistaModalitats.size();i++){
				jListPropietatsModel.addElement(llistaModalitats.get(i));
			}
			jListModalitats.setSelectedIndex(pos+1);
		}	
	}
	/**
	 * Afegeix com a nova modalitat el text introduït en el camp <code>Nova modalitat</code>
	 * @param e, event que detecta que s'ha premut el botó d'afegir una nova modalitat
	 */
	protected void jButtonEsquerra_actionPerformed(ActionEvent e) {
		String novaMod=jTextFieldNovaMod.getText();
		if(novaMod.compareTo("")==0||novaMod==null){}
		else{
			if(jaExisteix(novaMod))frPare.actualitzarBarraEstat("Ja existeix aquesta modalitat",true);
			else{
				llistaModalitats.add(novaMod);		
				jListPropietatsModel.addElement(novaMod);
			}
		}		
	}
	/***
	 * Indica si ja existeix una modalitat amb el nom que ens passen com a paràmetre a la llista de modalitats
	 * @param snom, nom de modalitat a consultar
	 * @return true, si ja existeix una modalitat de nom <code>snom</code>, false altrament.
	 */
	public boolean jaExisteix(String snom){
		boolean resultat=false;
		int max=jListPropietatsModel.getSize();
		for(int i=0;i<max && !resultat;i++){
			String mod=(String)jListPropietatsModel.getElementAt(i);
			if(mod.compareTo(snom)==0)resultat=true;
		}
		return resultat;
	}
	/**
	 * Obté la tipologia de la variable que s'ha modificat segons la codificació següent:
	 * tipologia==0, categòrica
	 * tipologia==1, ordinal
	 * tipologia==2, nominal
	 * @return
	 */
	public int obteTipologia(){
		return tipologia;
	}
	/**
	 * Obté la llista de modalitats modificada de la variable
	 * @return llista de modalitats
	 */
	public ArrayList obteModalitats(){
		return llistaModalitats;
	}
	/**
	 * Indica si s'ha pulsat el botó <code>Modifica</code>
	 * @return true si el botó <code>Modifica</code> ha estat premut, false altrament.
	 */
	public boolean OKPremut(){
		return OKpulsat;
	}
	/**
	 * Obté el nom de la variable modificada.
	 * @return nom de la variable.
	 */
	public String obteNomProp(){
		return nomPropietat;
	}

}

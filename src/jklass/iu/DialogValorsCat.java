package jklass.iu;
import java.awt.BorderLayout;
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
import javax.swing.JButton;
import javax.swing.JComponent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import jklass.nucli.GestorKlass;
/** Classe que dibuixa el submenú per consultar i seleccionar les modalitats d'una variable categòrica concreta
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
public class DialogValorsCat extends javax.swing.JDialog {
	
	private JPanel jPanelModalitats;
	private JTextField jTextFieldNomPropietat=new JTextField();
	private JButton jButtonOK;
	private JButton jButtonTanca;
	private JLabel jLabelNomPropietat;
	private JPanel jPanelBoto;
	private JPanel jPanel2;
	FrPrincipal frPare;
	GestorKlass gestor;
	String nomPropietat;
	String[] llistaModalitats;
	String valorSeleccionat;
	private JList jListPropietats;
	private JScrollPane jScrollPanePropietats;
	boolean OKpulsat;//indica si el OK ha estat premut
	ListModel jListPropietatsModel = new DefaultComboBoxModel();
	/**
	 * Recull l'event de que s'ha fet doble clic sobre el nom d'una variable i retorna a la finestra pare.
	 * 
	 */
	MouseListener mouseProp =new MouseAdapter(){
		   public void mouseClicked(MouseEvent e) {
		         if (e.getClickCount() == 2) {		
		        	 OKpulsat=true;
		     		Object ob=jListPropietats.getSelectedValue();
		     		valorSeleccionat=(String)ob;		     		
		     		setVisible(false);		
		         }
		   }
	 };
	
	/***
	 * Constructor
	 * @param fr, és la finestra pare
	 * @param gk, GestorKlass
	 * @param nomProp, nom de la propietat categòrica de la que es volen consultar els valors.
	 * @param llistaMods, llista de modalitats de la variable categòrica.
	 */
	public DialogValorsCat(FrPrincipal fr,GestorKlass gk,String nomProp,String[] llistaMods) {
		super(fr,"Valors de la variable",true);
		frPare = fr;
		gestor = gk;	
		nomPropietat=nomProp;
		jTextFieldNomPropietat.setText(nomProp);
		OKpulsat=false;
		llistaModalitats=llistaMods;
		jListPropietatsModel = new DefaultComboBoxModel(llistaModalitats);
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
			{
				jPanelModalitats = new JPanel();
				jPanelModalitats.setLayout(null);
				getContentPane().add(jPanelModalitats, BorderLayout.CENTER);
				jPanelModalitats.setPreferredSize(new java.awt.Dimension(366, 265));
				{
					jPanel2 = new JPanel();
					jPanelModalitats.add(jPanel2);
					GridBagLayout jPanel2Layout = new GridBagLayout();
					jPanel2Layout.rowWeights = new double[] {0.1};
					jPanel2Layout.rowHeights = new int[] {7};
					jPanel2Layout.columnWeights = new double[] {0.0, 0.1};
					jPanel2Layout.columnWidths = new int[] {71, 7};
					jPanel2.setLayout(jPanel2Layout);
					jPanel2.setBounds(0, 0, 343, 28);
					{
						jLabelNomPropietat = new JLabel();
						jPanel2.add(jLabelNomPropietat, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabelNomPropietat.setText("Propietat:");
						jLabelNomPropietat.setPreferredSize(new java.awt.Dimension(63, 14));
						jLabelNomPropietat.setFont(new java.awt.Font("Tahoma",1,10));
					}
					{
						
						jPanel2.add(jTextFieldNomPropietat, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jTextFieldNomPropietat.setPreferredSize(new java.awt.Dimension(255, 20));
					}
				}
				{
					jPanelBoto = new JPanel();
					jPanelModalitats.add(jPanelBoto);
					jPanelBoto.setLayout(null);
					jPanelBoto.setBounds(0, 201, 343, 37);
					{
						jButtonTanca = new JButton();
						jPanelBoto.add(jButtonTanca);
						jButtonTanca.setText("Tanca");
						jButtonTanca.setBounds(210, 7, 84, 21);
						jButtonTanca.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e){
						    	  jButtonTanca_actionPerformed(e);
						      }
						});
					}
					{
						jButtonOK = new JButton();
						jPanelBoto.add(jButtonOK);
						jButtonOK.setText("D'acord");
						jButtonOK.setBounds(56, 7, 84, 21);
						jButtonOK.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e){
						    	  jButtonOK_actionPerformed(e);
						      }
						});
					}
				}
				{
					jScrollPanePropietats = new JScrollPane();
					jScrollPanePropietats.setAutoscrolls(true);
					jPanelModalitats.add(jScrollPanePropietats);
					jScrollPanePropietats.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Llista modalitats", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
					jScrollPanePropietats.setBounds(42, 28, 259, 175);
					{
						
						jListPropietats = new JList();
						jScrollPanePropietats.setViewportView(jListPropietats);
						jListPropietats.setModel(jListPropietatsModel);
						jListPropietats.setPreferredSize(new java.awt.Dimension(456, 301));
						jListPropietats.addMouseListener(mouseProp);
					}
				}
			}
			this.setSize(351, 272);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Guarda la modalitat seleccionda i tanca la finestra.
	 * @param e, event que indica que s'ha premut el botó <code>D'acord</code>
	 */
	protected void jButtonOK_actionPerformed(ActionEvent e) {
		OKpulsat=true;
		Object ob=jListPropietats.getSelectedValue();
		valorSeleccionat=(String)ob;		
		this.setVisible(false);		
	}
	/***
	 * Tanca la finestra
	 * @param e,event que indica que s'ha premut el botó <code>Tanca</code>
	 */
	protected void jButtonTanca_actionPerformed(ActionEvent e) {
		this.setVisible(false);		
		
	}
	/**
	 * Obté la modalitat seleccionada
	 * @return String, modalitat seleccionada
	 */
	public String getValorSeleccionat() {
		return valorSeleccionat;
	}
	/***
	 * Guarda la nova modalitat seleccionada
	 * @param valorSeleccionat, modalitat seleccionada
	 */
	public void setValorSeleccionat(String valorSeleccionat) {
		this.valorSeleccionat = valorSeleccionat;
	}
	/**
	 * Indica si el botó <code>D'acord</code> s'ha premut.
	 * @return true si el botó <code>D'acord</code> ha estat premut, false altrament.
	 */
	public boolean OKPremut(){
		return OKpulsat;
	}

}

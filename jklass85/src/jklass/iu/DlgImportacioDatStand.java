package jklass.iu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import jklass.nucli.GestorKlass;
/** Classe que dibuixa el submenú per indicar si el fitxer .dat estàndard conté
 * una primera fila amb els noms de les variables i/o una primera columna amb els identificadors dels objectes. 
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
public class DlgImportacioDatStand extends javax.swing.JDialog {
	private JPanel jPanel1;
	private JButton jButtonCancel;
	private JCheckBox jCheckBox1= new JCheckBox();
	private JCheckBox jCheckBox2= new JCheckBox();
	private JButton jButtonOK;
	private JPanel jPanel2;
	FrPrincipal frPare;
	GestorKlass gestor;
	boolean OKpulsat;
	int opcions;
	
	/***
	 * Constructor
	 * @param fr, finestra pare
	 * @param gk, GestorKlass
	 */
	public DlgImportacioDatStand(FrPrincipal fr,GestorKlass gk) {
		super(fr,"Importació de fitxer de dades estàndard",true);
		frPare = fr;
		gestor = gk;	
		OKpulsat=false;
		opcions=0;
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
			this.setPreferredSize(new java.awt.Dimension(400, 119));
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.0, 0.0};
			thisLayout.rowHeights = new int[] {58, 30};
			thisLayout.columnWeights = new double[] {0.1};
			thisLayout.columnWidths = new int[] {7};
			getContentPane().setLayout(thisLayout);
			this.setSize(400, 119);

			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jPanel1.setPreferredSize(new java.awt.Dimension(319, 56));
				{
					
					jPanel1.add(jCheckBox1);
					jCheckBox1.setText("Primera fila amb identificadors de variables");
					jCheckBox1.setPreferredSize(new java.awt.Dimension(255, 18));
				}
				{
					
					jPanel1.add(jCheckBox2);
					jCheckBox2
						.setText("Primera columna amb identificadors d'objectes");
					jCheckBox2.setPreferredSize(new java.awt.Dimension(255, 18));
				}
			}
			{
				jPanel2 = new JPanel();
				getContentPane().add(jPanel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					jButtonOK = new JButton();
					jPanel2.add(jButtonOK);
					jButtonOK.setText("D'acord");
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
	/***
	 * Tanca la finestra
	 * @param e,event que indica que s'ha premut el botó <code>Cancel.la</code>
	 */
	protected void jButtonCancel_actionPerformed(ActionEvent e) {
		this.setVisible(false);			
	}
	/***
	 * Tanca la finestra i indica que s'ha pulsat el botó <code>D'acord</code>
	 * @param e,event que indica que s'ha premut el botó <code>D'acord</code>
	 */
	protected void jButtonOK_actionPerformed(ActionEvent e) {
		OKpulsat=true;
		this.setVisible(false);		
	}
	/***
	 * Indica si s'ha premut el botó <code>D'acord</code>
	 * @return true si el botó <code>D'acord</code> ha estat premut, false altrament.
	 */
	public boolean OKPremut(){
		return OKpulsat;
	}
	
	/**
	 * Retorna les opcions seleccionades segons la següent codificació:
	 * opcions==0, no s'introdueixen files ni columnes amb descriptors
	 * opcions==1,la primera fila contindrà els descriptors de les variables
	 * opcions==2,la primera columna contindrà els descriptors dels objectes
	 * opcions==3,la primera fila contindrà els descriptors de les variables i la primera columna contindrà els descriptors dels objectes
	 * @return opcions
	 */
	public int obtenirOpcions(){
		if(jCheckBox1.isSelected()){
			if(jCheckBox2.isSelected())opcions=3;
			else opcions=1;
		}else if(jCheckBox2.isSelected())opcions=2;
		return opcions;
	}

}

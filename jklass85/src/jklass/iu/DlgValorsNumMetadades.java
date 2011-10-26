package jklass.iu;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import jklass.nucli.GestorKlass;
import jklass.nucli.Propietat;
/** Classe que dibuixa el submenú per modificar els valors màxim i mínim d'una variable numèrica concreta.
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
public class DlgValorsNumMetadades extends javax.swing.JDialog {
	private JPanel jPanel1;
	private JTextField jTextFieldMaxim= new JTextField();
	private JLabel jLabelMaxim;
	private JTextField jTextFieldMinim = new JTextField();
	private JLabel jLabelMinim;
	private JPanel jPanelMinim;
	private JPanel jPanelMaxim;
	private JTextField jTextFieldPropietat= new JTextField();
	private JLabel jLabelPropietat;
	private JButton jButtonTanca;
	private JPanel jPanelTanca;
	private JButton jButtonModificar;
	private JPanel jPanelValors;
	private JPanel jPanelNomVariable;
	FrPrincipal frPare;
	GestorKlass gestor;
	String valorMax;
	String valorMin;
	String nomPropietat;
	boolean OKpulsat;
		
	/***
	 * Constructor
	 * @param fr,finestra pare
	 * @param gk, GestorKlass
	 * @param max, valor màxim de la variable
	 * @param min, valor mínim de la variable
	 * @param nomProp, nom de la variable numèrica a modificar
	 */
	public DlgValorsNumMetadades(FrPrincipal fr,GestorKlass gk,float max,float min,String nomProp) {
		super(fr,"Metainformació de la variable",true);
		Float aux=new Float(max);
		valorMax=aux.toString();
		aux=new Float(min);
		valorMin=aux.toString();
		OKpulsat=false;
		frPare=fr;
		gestor=gk;
		nomPropietat=nomProp;
		jTextFieldPropietat.setText(nomPropietat);
		jTextFieldMaxim.setText(valorMax);
		jTextFieldMinim.setText(valorMin);
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
				jPanel1 = new JPanel();
				GridBagLayout jPanel1Layout = new GridBagLayout();
				jPanel1Layout.rowWeights = new double[] {0.0, 0.0, 0.1};
				jPanel1Layout.rowHeights = new int[] {34, 120, 7};
				jPanel1Layout.columnWeights = new double[] {0.1};
				jPanel1Layout.columnWidths = new int[] {7};
				jPanel1.setLayout(jPanel1Layout);
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				jPanel1.setPreferredSize(new java.awt.Dimension(321, 215));
				{
					jPanelNomVariable = new JPanel();
					GridBagLayout jPanelNomVariableLayout = new GridBagLayout();
					jPanelNomVariableLayout.rowWeights = new double[] {0.1};
					jPanelNomVariableLayout.rowHeights = new int[] {7};
					jPanelNomVariableLayout.columnWeights = new double[] {0.0, 0.1};
					jPanelNomVariableLayout.columnWidths = new int[] {78, 7};
					jPanelNomVariable.setLayout(jPanelNomVariableLayout);
					jPanel1.add(jPanelNomVariable, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						jLabelPropietat = new JLabel();
						jPanelNomVariable.add(jLabelPropietat, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jLabelPropietat.setText("Propietat:");
						jLabelPropietat.setPreferredSize(new java.awt.Dimension(60, 14));
						jLabelPropietat.setFont(new java.awt.Font("Tahoma",1,10));
					}
					{
						
						jPanelNomVariable.add(jTextFieldPropietat, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						jTextFieldPropietat.setPreferredSize(new java.awt.Dimension(189, 20));
					}
				}
				{
					jPanelValors = new JPanel();
					GridBagLayout jPanelValorsLayout = new GridBagLayout();
					jPanelValorsLayout.rowWeights = new double[] {0.1, 0.1};
					jPanelValorsLayout.rowHeights = new int[] {7, 7};
					jPanelValorsLayout.columnWeights = new double[] {0.1};
					jPanelValorsLayout.columnWidths = new int[] {7};
					jPanelValors.setLayout(jPanelValorsLayout);
					jPanel1.add(jPanelValors, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanelValors.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED), "Valors de la variable numèrica", TitledBorder.LEADING, TitledBorder.TOP));
					{
						jPanelMaxim = new JPanel();
						GridBagLayout jPanelMaximLayout = new GridBagLayout();
						jPanelMaximLayout.rowWeights = new double[] {0.1};
						jPanelMaximLayout.rowHeights = new int[] {7};
						jPanelMaximLayout.columnWeights = new double[] {0.0, 0.1};
						jPanelMaximLayout.columnWidths = new int[] {81, 7};
						jPanelMaxim.setLayout(jPanelMaximLayout);
						jPanelValors.add(jPanelMaxim, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						{
							jLabelMaxim = new JLabel();
							jPanelMaxim.add(jLabelMaxim, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelMaxim.setText("Màxim:");
							jLabelMaxim.setPreferredSize(new java.awt.Dimension(57, 14));
							jLabelMaxim.setFont(new java.awt.Font("Tahoma",1,10));
						}
						{
							
							jPanelMaxim.add(jTextFieldMaxim, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jTextFieldMaxim.setPreferredSize(new java.awt.Dimension(185, 20));
						}
					}
					{
						jPanelMinim = new JPanel();
						GridBagLayout jPanelMinimLayout = new GridBagLayout();
						jPanelMinimLayout.rowWeights = new double[] {0.1};
						jPanelMinimLayout.rowHeights = new int[] {7};
						jPanelMinimLayout.columnWeights = new double[] {0.0, 0.1};
						jPanelMinimLayout.columnWidths = new int[] {80, 7};
						jPanelMinim.setLayout(jPanelMinimLayout);
						jPanelValors.add(jPanelMinim, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						{
							jLabelMinim = new JLabel();
							jPanelMinim.add(jLabelMinim, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jLabelMinim.setText("Mínim:");
							jLabelMinim.setPreferredSize(new java.awt.Dimension(56, 16));
							jLabelMinim.setFont(new java.awt.Font("Tahoma",1,10));
						}
						{
							
							jPanelMinim.add(jTextFieldMinim, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							jTextFieldMinim.setPreferredSize(new java.awt.Dimension(187, 20));
						}
					}
				}
				{
					jPanelTanca = new JPanel();
					jPanel1.add(jPanelTanca, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						jButtonModificar = new JButton();
						jPanelTanca.add(jButtonModificar);
						jButtonModificar.setText("Modifica");
						jButtonModificar.setPreferredSize(new java.awt.Dimension(78, 27));
						jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e){
						    	  jButtonModificar_actionPerformed(e);
						      }
						});
					}
					{
						jButtonTanca = new JButton();
						jPanelTanca.add(jButtonTanca);
						jButtonTanca.setText("Cancel.la");
						jButtonTanca.setPreferredSize(new java.awt.Dimension(78, 27));
						jButtonTanca.addActionListener(new java.awt.event.ActionListener() {
						      public void actionPerformed(ActionEvent e){
						    	  jButtonTanca_actionPerformed(e);
						      }
						});
					}
				}
			}
			this.setSize(330, 230);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Modifica els valors mínim i màxim de la variable
	 * @param e, event que detecta que s'ha premut el botó <code>Modificar</code>
	 * @throws Excepció si els valors introduïts són incorrectes.
	 */
	protected void jButtonModificar_actionPerformed(ActionEvent e) {	
		frPare.actualitzarBarraEstat("",false);
		valorMin=jTextFieldMinim.getText();
		valorMax=jTextFieldMaxim.getText();
		nomPropietat=jTextFieldPropietat.getText();
		try{
			Float fmin=new Float(valorMin);
			Float fmax=new Float(valorMax);
			if(fmin.floatValue()>fmax.floatValue())throw new Exception("El mínim no pot ser més gran que el màxim");
			else{
				Propietat p=gestor.obtenirPropietat(nomPropietat);
				int res=gestor.minMaxCorrectes(fmin.floatValue(),fmax.floatValue(),p);
				if(res==1)throw new Exception("El valor mínim excedeix dels rangs permesos");
				else if(res==2)throw new Exception("El valor màxim excedeix dels rangs permesos");
				else{
					OKpulsat=true;
				}
			}
			this.setVisible(false);	
		}catch(NumberFormatException ex){
			frPare.actualitzarBarraEstat("El valor introduït no és un real",true);
		}catch(Exception ex){
			OKpulsat=false;
			frPare.actualitzarBarraEstat(ex.getMessage(),true);
		}
			
	}
	/***
	 * Tanca la finestra
	 * @param e, event que detecta que s'ha premut el botó <code>Cancel.la</code>
	 */
	protected void jButtonTanca_actionPerformed(ActionEvent e) {
		this.setVisible(false);				
	}
	/**
	 * Obté el nou valor mínim de la variable
	 * @return valor mínim de la variable modificada
	 */
	public String obteMinim() {
		return valorMin;
	}
	/***
	 * Obté el nou valor màxim de la variable
	 * @return valor màxim de la variable modificada
	 */
	public String obteMaxim() {
		return valorMax;
	}
	/***
	 * Indica si s'ha premut el botó <code>Modifica</code>
	 * @return true si s'ha premut el botó <code>Modifica</code>, false altrament
	 */
	public boolean OKPremut(){
		return OKpulsat;
	}
	/***
	 * Obté el nom de la propietat
	 * @return nom de la variable modifcada
	 */
	public String obteNomProp(){
		return nomPropietat;
	}

}

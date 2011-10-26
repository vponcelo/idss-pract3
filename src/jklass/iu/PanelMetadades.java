package jklass.iu;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.border.*;

import jklass.nucli.EstadisticsCateg;
import jklass.nucli.GestorKlass;
import jklass.nucli.LlistaPropietats;
import jklass.nucli.PropCategorica;
import jklass.nucli.PropNominal;
import jklass.nucli.PropNumerica;
import jklass.nucli.PropOrdinal;
import jklass.nucli.Propietat;

/** Classe que dibuixa el submenú per modificar la metainformació de variables de la matriu
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

public class PanelMetadades extends JPanel {
  FrPrincipal frPare;
  GestorKlass gestor;
  String nomFitxer = null;

GridBagLayout flowLayout1 = new GridBagLayout();
  JPanel jPanelVars = new JPanel();
  JButton jBttnAfegirN = new JButton();
  private JButton jButtonConsValoCateg;
  private JButton jButtonConsValorsNum;
  DefaultListModel listModelSN = new DefaultListModel();
  JList jListSelcN = new JList(listModelSN);
  JScrollPane jSPSelecN = new JScrollPane(jListSelcN);
  BorderLayout borderLayout4 = new BorderLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  DefaultListModel listModelVN = new DefaultListModel();
  JList jListVarsN = new JList(listModelVN);
  JScrollPane jSPVarsN = new JScrollPane(jListVarsN);
  JPanel jPanelSelecNum = new JPanel();
  JPanel jPanelBtnsN = new JPanel();
  JPanel jPanelBotones = new JPanel();
  FlowLayout flowLayout2 = new FlowLayout();
  BorderLayout borderLayout1 = new BorderLayout();
  Border border1;
  TitledBorder titledBorder1;
  Border border2;
  TitledBorder titledBorder2;
  Border border3;
  TitledBorder titledBorder3;
  JPanel jPanelTancar = new JPanel();
  JButton jBttnCancel = new JButton();
  JButton jBttnOK = new JButton();
  LlistaPropietats llpropietats;
  /**
   * Constructor
   * @param fr, finestra pare
   * @param gk, GestorKlass
   */
  public PanelMetadades(FrPrincipal fr,GestorKlass gk) {
    String[][] llProps;
    int j,lon;
    llpropietats=gk.obtenirCopiaLlistaPropietats();
    frPare = fr;
    gestor = gk;
    nomFitxer = frPare.obtenirNomDades();
    llProps = gestor.obtenirLlistaIDsProps();
    j = 0;
    lon = llProps[0].length;
    for (int i = 0; i < lon; i++) {
      listModelVN.insertElementAt(llProps[0][i], j);
      j++;
    }
    lon = llProps[1].length;
    for (int i = 0; i < lon; i++) {    	
      listModelSN.addElement(llProps[1][i]);      
      j++;
    }
    lon = llProps[2].length;
    for (int i = 0; i < lon; i++) {
      listModelSN.addElement(llProps[2][i]);
      j++;
    }
    lon = llProps[3].length;
    for (int i = 0; i < lon; i++) {
      listModelSN.addElement(llProps[3][i]);
      j++;
    }
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * Dibuixa el menú
   * @throws Exception
   */
  private void jbInit() throws Exception {
    frPare.setSize(550,340);
    frPare.centrar();
    border1 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158));
    titledBorder1 = new TitledBorder(border1,"Editor de metadades");
    border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158));
    titledBorder2 = new TitledBorder(border2,"Llistat numèriques");
    border3 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158));
    titledBorder3 = new TitledBorder(border3,"Llistat categòriques");
    jPanelVars.setLayout(borderLayout5);
    jBttnAfegirN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
    jBttnAfegirN.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnAfegirN_actionPerformed(e);
      }
    });
    jSPSelecN.setAutoscrolls(true);
    jSPSelecN.setBorder(titledBorder3);
    jSPSelecN.setPreferredSize(new Dimension(115, 153));
    jSPVarsN.getViewport().setBackground(Color.white);
    jSPVarsN.setAutoscrolls(true);
    jSPVarsN.setBorder(titledBorder2);
    jSPVarsN.setPreferredSize(new Dimension(100, 153));
    jPanelSelecNum.setLayout(flowLayout1);
    jPanelBtnsN.setLayout(borderLayout4);
    jPanelBotones.setLayout(flowLayout2);
    this.setLayout(borderLayout1);
    this.setBorder(titledBorder1);
    this.setPreferredSize(new java.awt.Dimension(492, 270));
    jBttnCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnCancel_actionPerformed(e);
      }
    });
    jBttnCancel.setText("Cancel·la");
    jBttnOK.setText("D'acord");
    jBttnOK.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnOK_actionPerformed(e);
      }
    });
    flowLayout2.setHgap(75);
    jPanelVars.add(jPanelSelecNum, BorderLayout.NORTH);
    jPanelSelecNum.setPreferredSize(new java.awt.Dimension(450, 202));
    flowLayout1.rowWeights = new double[] {0.1, 0.1};
    flowLayout1.rowHeights = new int[] {7, 7};
    flowLayout1.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.1};
    flowLayout1.columnWidths = new int[] {46, 150, 87, 155, 7};
    jPanelSelecNum.add(jSPVarsN, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanelSelecNum.add(jPanelBtnsN, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanelBtnsN.add(jBttnAfegirN, BorderLayout.NORTH);
    jPanelSelecNum.add(jSPSelecN, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	{
		jButtonConsValorsNum = new JButton();
		jPanelSelecNum.add(jButtonConsValorsNum, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		jButtonConsValorsNum.setText("Consultar valors");
		jButtonConsValorsNum.setBounds(77, 7, 112, 21);
		jButtonConsValorsNum
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jButtonConsValorsNum_actionPerformed(e);
				}
			});
	}
	{
		jButtonConsValoCateg = new JButton();
		jPanelSelecNum.add(jButtonConsValoCateg, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		jButtonConsValoCateg.setText("Consultar valors");
		jButtonConsValoCateg.setBounds(259, 7, 112, 21);
		jButtonConsValoCateg
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jButtonConsValoCateg_actionPerformed(e);
				}
			});
	}
    jPanelVars.setPreferredSize(new java.awt.Dimension(450, 196));
    this.add(jPanelBotones,  BorderLayout.SOUTH);
    this.add(jPanelVars, BorderLayout.CENTER);
    jPanelBotones.add(jPanelTancar);
    jPanelTancar.add(jBttnOK, null);
    jPanelTancar.add(jBttnCancel, null);
  }
  /**
   * Obre la finestra per modificar la metainformació de la variable categòrica seleccionada
   * @param e, event que detecta que s'ha premut el botó <code>Consultar valors</code>
   */
  protected void jButtonConsValoCateg_actionPerformed(ActionEvent e) {
	  try {
		Object ob=jListSelcN.getSelectedValue();
			Propietat prop=llpropietats.obtenirPropietat((String)ob);		
			String[] llista = new String[1];
			int pos = llpropietats.obtenirIndex((String)ob);
			String[] alModalitats=(String[])((PropCategorica)llpropietats.obtenirPropietat(pos)).getLlModalitats().toArray(llista);
			int tipus=0;//categorica
			if(prop instanceof PropOrdinal)tipus=1;
			if(prop instanceof PropNominal)tipus=2;
			DlgValorsCatMetadades inst = new DlgValorsCatMetadades(frPare,gestor,(String)ob,alModalitats,tipus);
			inst.setLocationRelativeTo(this);
			inst.setVisible(true);
			if(inst.OKPremut()){
				int tipologia=inst.obteTipologia();
				ArrayList llModalitats=inst.obteModalitats();
				String snom=inst.obteNomProp();
				if(snom.compareTo("")==0||snom==null)frPare.actualitzarBarraEstat("Hi ha algun nom de variable buit",true);
				else{
					if(snom.compareTo((String)ob)==0){
						PropCategorica propC=(PropCategorica)prop;
						if(tipologia==tipus){//no ha canviat de tipus
							propC.setLlModalitats(llModalitats);
							propC.setLlModsOrdre(llModalitats);
							EstadisticsCateg estC=new EstadisticsCateg(llModalitats);
							propC.setEstadistics(estC);
							gestor.calcularEstadistics(propC);
						}else{
							if(tipologia==1)propC=new PropOrdinal(propC.obtenirId(),llModalitats);
							else propC=new PropNominal(propC.obtenirId(),llModalitats);
							gestor.calcularEstadistics(propC);					
						}
						llpropietats.substituirProp(propC,pos);
					}
					else{
						PropCategorica propC=(PropCategorica)prop;
						if(tipologia==tipus){//no ha canviat de tipus
							propC.setLlModalitats(llModalitats);
							propC.setLlModsOrdre(llModalitats);
							EstadisticsCateg estC=new EstadisticsCateg(llModalitats);
							propC.setEstadistics(estC);
							gestor.calcularEstadistics(propC);
							//propC.setIdentificador(snom);
						}else{
							if(tipologia==1)propC=new PropOrdinal(propC.obtenirId(),llModalitats);
							else propC=new PropNominal(propC.obtenirId(),llModalitats);
							gestor.calcularEstadistics(propC);
							//propC.setIdentificador(snom);
						}
						llpropietats.substituirProp(propC,pos);						
					}
					frPare.actualitzarBarraEstat("S'ha dut a terme la modificació correctament",false);
				}				
			}else frPare.actualitzarBarraEstat("",false);
				
	} catch (Exception e1) {
		frPare.actualitzarBarraEstat("No s'ha pogut dur a terme la modificació: "+e1.getMessage(),true);
		e1.printStackTrace();
	}
	
  }
  /**
   * Obre la finestra per modificar la metainformació d'una variable numèrica
   * @param e, event que detecta que s'ha premut el botó <code>Consultar valors</code> d'una variable numèrica
   */
  protected void jButtonConsValorsNum_actionPerformed(ActionEvent e) {
	try {
		Object ob=jListVarsN.getSelectedValue();
		Propietat prop=llpropietats.obtenirPropietat((String)ob);	
			PropNumerica aux=(PropNumerica)prop;
			float max=aux.obtenirRangMax();
			float min=aux.obtenirRangMin();
			DlgValorsNumMetadades inst = new DlgValorsNumMetadades(frPare,gestor,max,min,(String)ob);
			inst.setLocationRelativeTo(this);
			inst.setVisible(true);
			if(inst.OKPremut()){
				String svalorMin=inst.obteMinim();
				String svalorMax=inst.obteMaxim();
				String snom=inst.obteNomProp();
				PropNumerica propN=(PropNumerica)prop;
				propN.reset();
				Float fmin=new Float(svalorMin);
				Float fmax=new Float(svalorMax);
				propN.setRangMax(fmax.floatValue());
				propN.setRangMin(fmin.floatValue());
				gestor.calcularEstadistics(propN);
				frPare.actualitzarBarraEstat("S'ha dut a terme la modificació correctament",false);
				//hem de tornar a calcular els estadístics depenen de si han canviat els valors
			}else frPare.actualitzarBarraEstat("",false);
			
	}catch(NumberFormatException ex){
		frPare.actualitzarBarraEstat("El valor introduït no és un real",true);
	} catch (Exception e1) {
		frPare.actualitzarBarraEstat("No s'ha pogut dur a terme la modificació: "+e1.getMessage(),true);
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
  }
  /**
   * Afegeix la variable numèrica seleccionada a la llista de variables categòriques i l'elimina de la llista
   * de variables numèriques.
   * Crea una variable categòrica que com a modalitats conté tots els valors observats per la variable numèrica seleccionada i
   * substitueix la variable numèrica per la nova categòrica
   * @param e, event que detecta que s'ha premut el botó de passar una variable numèrica a categòrica ( mà cap a la dreta)
   */
  void jBttnAfegirN_actionPerformed(ActionEvent e) {
    Object[] vars = jListVarsN.getSelectedValues();
    boolean hay_mas_vars = true;
    int n = 0;
    do {
      try {
    	  Object ob=vars[n];
    	  int pos = llpropietats.obtenirIndex((String)ob);
    	  Propietat prop=llpropietats.obtenirPropietat((String)ob);	
    	  PropNumerica aux=(PropNumerica)prop;
    	  Float fmax=new Float(aux.obtenirRangMax());
    	  Float fmin=new Float(aux.obtenirRangMin());
    	  ArrayList llistaModalitats=new ArrayList();
    	  llistaModalitats.add(fmin.toString());
    	  llistaModalitats.add(fmax.toString());
    	  prop=new PropCategorica(prop.obtenirId(),llistaModalitats);
    	  gestor.calcularEstadistics(prop);	
    	  llpropietats.substituirProp(prop,pos);    	  
    	  listModelSN.addElement(vars[n]);
    	  listModelVN.removeElement(vars[n]);
    	  n++;
      }
      catch (Exception exc) { hay_mas_vars = false; }
    } while (hay_mas_vars);
    frPare.actualitzarBarraEstat("Les variables seleccionades han passat a ser categòriques",false);
  }

 
/**
 * Tanca la finestra
 * @param e, event que detecta que s'ha premut el botó <code>Cancel.la</code>
 */
  void jBttnCancel_actionPerformed(ActionEvent e) {
	  int answer=JOptionPane.showConfirmDialog(frPare,"Es perdran els canvis efectuats(si desitja que els canvis siguin permanents ha de prémer el botó D'acord).Desitja continuar?","Edició metadades",JOptionPane.YES_NO_OPTION);
	  if (answer == JOptionPane.YES_OPTION) {
		  frPare.actualitzarBarraEstat("S'han desfet els canvis",false);
		    frPare.remove(this);
		    frPare.validate();
		    frPare.repaint();
	  }
	  
  }
/**
 * Guarda de forma permanent les modificacions efectuades
 * @param e, event que detecta que s'ha premut el botó <code>D'acord</code>
 */
  void jBttnOK_actionPerformed(ActionEvent e) {
	  int answer=JOptionPane.showConfirmDialog(frPare,"Aquesta acció farà les modificacions permanents.Desitja continuar?","Edició metadades",JOptionPane.YES_NO_OPTION);
	  if (answer == JOptionPane.YES_OPTION) {
		  gestor.setLlistaPropietats(this.llpropietats);
		  frPare.actualitzarBarraEstat("Els canvis s'han fet persistents",false);
	  }else{
		  frPare.actualitzarBarraEstat("S'han desfet els canvis",false);
	  }
	  frPare.remove(this);
	    frPare.validate();
	    frPare.repaint();
  }

  

}
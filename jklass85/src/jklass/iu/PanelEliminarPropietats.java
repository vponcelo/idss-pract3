package jklass.iu;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.border.*;

import jklass.nucli.GestorKlass;

/** Classe que dibuixa el submenú per eliminar variables de la matriu
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

public class PanelEliminarPropietats extends JPanel {
  FrPrincipal frPare;
  GestorKlass gestor;
  String nomFitxer = null;

  FlowLayout flowLayout1 = new FlowLayout();
  JPanel jPanelVars = new JPanel();
  JButton jBttnAfegirN = new JButton();
  DefaultListModel listModelSN = new DefaultListModel();
  JList jListSelcN = new JList(listModelSN);
  JScrollPane jSPSelecN = new JScrollPane(jListSelcN);
  BorderLayout borderLayout4 = new BorderLayout();
  JButton jBttnTreureN = new JButton();
  BorderLayout borderLayout5 = new BorderLayout();
  DefaultListModel listModelVN = new DefaultListModel();
  JList jListVarsN = new JList(listModelVN);
  // JList jListVarsN2 = new JList(listModelVN);
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
  JButton jBttnAjuda = new JButton();
  ArrayList alProps=new ArrayList();
  /**
   * Constructor
   * @param fr, finestra pare
   * @param gk, GestorKlass
   */
  public PanelEliminarPropietats(FrPrincipal fr,GestorKlass gk) {
    String[][] llProps;
    int j,lon;

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
      listModelVN.insertElementAt(llProps[1][i], j);
      j++;
    }
    lon = llProps[2].length;
    for (int i = 0; i < lon; i++) {
      listModelVN.insertElementAt(llProps[2][i], j);
      j++;
    }
    lon = llProps[3].length;
    for (int i = 0; i < lon; i++) {
      listModelVN.insertElementAt(llProps[3][i], j);
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
   * 
   */
  private void jbInit() throws Exception {
    frPare.setSize(550,330);
    frPare.centrar();
    border1 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158));
    titledBorder1 = new TitledBorder(border1,"Elimina propietats");
    border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158));
    titledBorder2 = new TitledBorder(border2,"Llista variables");
    border3 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158));
    titledBorder3 = new TitledBorder(border3,"Variables eliminades");
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
    jBttnTreureN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
    jBttnTreureN.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnTreureN_actionPerformed(e);
      }
    });
    jSPVarsN.getViewport().setBackground(Color.white);
    jSPVarsN.setAutoscrolls(true);
    jSPVarsN.setBorder(titledBorder2);
    jSPVarsN.setPreferredSize(new Dimension(100, 153));
    jPanelSelecNum.setLayout(flowLayout1);
    jPanelBtnsN.setLayout(borderLayout4);
    jPanelBotones.setLayout(flowLayout2);
    this.setLayout(borderLayout1);
    this.setBorder(titledBorder1);
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
    jBttnAjuda.setText("Eliminar");
    jBttnAjuda.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnEliminar_actionPerformed(e);
      }
    });
    flowLayout2.setHgap(75);
    jPanelVars.add(jPanelSelecNum, BorderLayout.NORTH);
    jPanelSelecNum.add(jSPVarsN, null);
    jPanelSelecNum.add(jPanelBtnsN, null);
    jPanelBtnsN.add(jBttnAfegirN, BorderLayout.NORTH);
    jPanelBtnsN.add(jBttnTreureN, BorderLayout.SOUTH);
    jPanelSelecNum.add(jSPSelecN, null);
    this.add(jPanelVars, BorderLayout.CENTER);
    this.add(jPanelBotones,  BorderLayout.SOUTH);
    jPanelBotones.add(jBttnAjuda);
    jPanelBotones.add(jPanelTancar, null);
    jPanelTancar.add(jBttnOK, null);
    jPanelTancar.add(jBttnCancel, null);
  }
  /**
   * Afegeix les variables seleccionades a la llista de <code>Llista variables</code> a la llista de variables a eliminar
   * i les elimina de la llista de <code>Llista variables</code>
   * @param e, event que detecta que s'ha premut el botó <code>Seleccionar variables(mà cap a la dreta)</code>
   */
  void jBttnAfegirN_actionPerformed(ActionEvent e) {
    Object[] vars = jListVarsN.getSelectedValues();
    boolean hay_mas_vars = true;
    int n = 0;
	 System.out.println( "este quien es2??");
	 System.out.println( "que hay en vars[0] antes?"+vars[0]);
    do {
      try {
        listModelSN.addElement(vars[n]);
        listModelVN.removeElement(vars[n]);
		  System.out.println( "que hay en vars[0]?"+vars[0]);
        n++;
      }
      catch (Exception exc) { hay_mas_vars = false; }
    } while (hay_mas_vars);
  }
  /**
   * Afegeix les variables seleccionades a la llista de <code>Variables eliminades</code> i les elimina de la llista de variables
   * @param e, event que detecta que s'ha premut el botó <code>Deseleccionar variables(mà cap a l'esquerra)</code>
   */
  void jBttnTreureN_actionPerformed(ActionEvent e) {
    Object[] vars = jListSelcN.getSelectedValues();
    boolean hay_mas_vars = true;
    int n = 0;
	 System.out.println( "se ejecutará estoo????");
    do {
      try {
        listModelVN.addElement(vars[n]);
        listModelSN.removeElement(vars[n]);
        n++;
      }
      catch (Exception exc) { hay_mas_vars = false; }
    } while (hay_mas_vars);
  }
  /**
   * Tanca la finestra
   * @param e, event que detecta que s'ha premut el botó <code>Cancel.la</code>
   */
  void jBttnCancel_actionPerformed(ActionEvent e) {	  
    frPare.remove(this);
    frPare.validate();
    frPare.repaint();
  }
/**
 * Elimina definitivament les variables que s'han indicat com eliminades
 * @param e, event que detecta que s'ha premut el botó <code>D'acord</code>
 */
  void jBttnOK_actionPerformed(ActionEvent e) {
	  int answer=JOptionPane.showConfirmDialog(frPare,"Aquests canvis seran irreversibles.Desitja continuar?","Eliminar propietats",JOptionPane.YES_NO_OPTION);
	  if (answer == JOptionPane.YES_OPTION) {
		  if(alProps.size()==0){
				frPare.actualitzarBarraEstat("Per eliminar cal haver premut prèvimant el botó Eliminar.El botó D'acord llavors farà permanents els canvis",false);			
			}
			else{
				
				try{
					gestor.eliminarPropietats(alProps);
					listModelSN.clear();
					frPare.actualitzarBarraEstat("S'han eliminat les propietats definitivament",false);
				}catch(Exception ex){
					ex.printStackTrace();
					frPare.actualitzarBarraEstat("No s'han pogut eliminar les propietats de forma definitiva: "+ex.getMessage(),true);
				}
			}		
	  }	
  }
/**
 * Elimina temporalment les variables que es troben a la llista de <code>Variables eliminades</code>
 * @param e, event que detecta que s'ha premut el botó <code>Eliminar</code>
 */
  void jBttnEliminar_actionPerformed(ActionEvent e) {
	  Object[] vars = listModelSN.toArray();		
		if(vars.length==0){
			frPare.actualitzarBarraEstat("S'ha d'escollir almenys una propietat",true);			
		}
		else{			
			for(int i=0;i<vars.length;i++){
				alProps.add(vars[i]);
			}
			listModelSN.clear();
			frPare.actualitzarBarraEstat("S'han eliminat les propietats correctament",false);
		}		
  }
  
  

}
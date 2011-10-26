package jklass.iu;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.*;

import jklass.nucli.CreacioMatriuException;
import jklass.nucli.CreacioPropietatsException;
import jklass.nucli.GestorKlass;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: Pantalla que permet realitzar la divisió de la Base de Dades segons una de les variables categòriques de la matriu de dades.</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p> </p>
 * @author Andreu Raya
 * @version v.5
 */

public class PanelDivideixBDD extends JPanel /*implements IConstants */{
	FrPrincipal frPare;
	GestorKlass gestor;
	String nomFitxer = null;

	FlowLayout flowLayout1 = new FlowLayout();
	JPanel jPanelVars = new JPanel();
	BorderLayout borderLayout5 = new BorderLayout();
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
	JPanel jPanelVar = new JPanel();
	JLabel jLblVar = new JLabel();
	JComboBox jCmbBVar = new JComboBox();
	JCheckBox jCBGuardarDisc = new JCheckBox();
	JPanel jPanelTancar = new JPanel();
	JButton jBttnAjuda = new JButton();
	JButton jBttnCancel = new JButton();
	JButton jBttnOK = new JButton();
	Border border4;

	/**
	 *  Constructor
	 *  
	 * @param fr es la finestra pare
	 * @param gk es el gestor d'on penja la matriu
	 */
	public PanelDivideixBDD(FrPrincipal fr, GestorKlass gk) {
	  String[][] llProps;
	  int lon;
	  String[] llMods;

	  frPare = fr;
	  gestor = gk;
	
	  frPare.centrar();

	  nomFitxer = frPare.obtenirNomDades();
	  llProps = gestor.obtenirLlistaIDsProps();
	  lon = llProps[1].length;
	  for (int i = 0; i < lon; i++) {
		jCmbBVar.addItem(llProps[1][i]);
	  }
	  lon = llProps[2].length;
	  for (int i = 0; i < lon; i++) {
		jCmbBVar.addItem(llProps[2][i]);
	  }
	  lon = llProps[3].length;
	  for (int i = 0; i < lon; i++) {
		jCmbBVar.addItem(llProps[3][i]);
	  }

	  try {
		jbInit();
	  }
	  catch (Exception e) {
		e.printStackTrace();
	  }
	}

  /**
   * Funció per a inicialitzar i dibuixar els elements de pantalla de divisió de la base de dades.
   * 
   * @throws Exception
   */
  private void jbInit() throws Exception {
	  border4 = BorderFactory.createCompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158)),"Divisió de la Base de Dades"),BorderFactory.createEmptyBorder(0,15,0,15));
	  frPare.setSize(540,250);
	  border1 = new EtchedBorder(EtchedBorder.RAISED, Color.white,
								 new Color(156, 156, 158));
	  titledBorder1 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158)),"Divisió de la Base de Dades");
	  border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,
												Color.white, Color.white,
												new Color(109, 109, 110),
												new Color(156, 156, 158));
	  titledBorder2 = new TitledBorder(BorderFactory.createBevelBorder(
		  BevelBorder.LOWERED, Color.white, Color.white, new Color(109, 109, 110),
		  new Color(156, 156, 158)), "Llista general");
	  border3 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,
												Color.white, Color.white,
												new Color(109, 109, 110),
												new Color(156, 156, 158));
	  titledBorder3 = new TitledBorder(border3, "Llista ordenada");
	  jPanelVars.setLayout(borderLayout5);
	  jPanelBotones.setLayout(flowLayout2);
	  this.setLayout(borderLayout1);
	  this.setBorder(border4);
	  jLblVar.setText("Variable:");
	  jCmbBVar.setMinimumSize(new Dimension(26, 21));
	  jCmbBVar.setPreferredSize(new Dimension(66, 21));
	  jCmbBVar.setMaximumRowCount(20);
/*	  jCmbBVar.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(ActionEvent e) {
		  jCmbBVar_actionPerformed(e);
		}
	  });*/
	  jBttnAjuda.setText("Ajuda");
	  jBttnAjuda.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(ActionEvent e) {
		  jBttnAjuda_actionPerformed(e);
		}
	  });
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
	  jPanelVar.setAlignmentX((float) 1.5);
	  jPanelVar.setAlignmentY((float) 0.5);
	  borderLayout1.setHgap(0);
	  this.add(jPanelVars, BorderLayout.EAST);
	  this.add(jPanelBotones, BorderLayout.SOUTH);
	  jPanelBotones.add(jPanelTancar, null);
	  jPanelTancar.add(jBttnOK, null);
	  jPanelTancar.add(jBttnCancel, null);
	  jPanelBotones.add(jBttnAjuda, null);
	  this.add(jPanelVar, BorderLayout.WEST);
	  jPanelVar.add(jLblVar, null);
	  jPanelVar.add(jCmbBVar, null);
	  
	  jCBGuardarDisc.setText("Guardar a disc");
	  jCBGuardarDisc.setSelected(true);
	  jPanelVar.add(jCBGuardarDisc, null);
  }

  /**
   * Funció que controla l'esdeveniment associat al botó de cancelació d'operació.
   */
  void jBttnCancel_actionPerformed(ActionEvent e) {
	  frPare.remove(this);
	  frPare.validate();
	  frPare.repaint();
	}


    /**
     * Funció que s'encarrega d'inserir cada matriu de la taula de hash que es rep
     * com a paràmetre a la llista de matrius.
     * 
     * @param hashMatrix taula de hash que conté les matrius de dades a inserir
     */
	private void insertDivisionMatrix (Hashtable<Integer, String> hashMatrix) {
		Enumeration<Integer> e = hashMatrix.keys();
		while (e.hasMoreElements()) {
			Integer key = e.nextElement();
			String value = hashMatrix.get(key);
	        frPare.matrius.insertarMatriu(value, key); // hook into FileHistory class
		}
	}
	
	/**
	 * Funció encarregada de gestionar l'esdeveniment associat al botó de <b>D'acord</b>
	 * de pantalla.
	 * 
	 * @param e esdeveniment
	 */
	void jBttnOK_actionPerformed(ActionEvent e) {
		String[] llMods = gestor.obtenirLlistaMods( (String) jCmbBVar.getSelectedItem());
System.out.println("Longitud de la llista de Modalitats: "+llMods.length);
for (int i = 0; i < llMods.length; i++) {
System.out.println("Mod["+i+"]: "+llMods[i]);
}
	try {
		frPare.actualitzarBarraEstat("Dividint dades.", false);
		/*int[] posMatrixAry = */
		Hashtable<Integer, String> hashMatrix = gestor.dividirBDD((String)jCmbBVar.getSelectedItem(), jCBGuardarDisc.isSelected());
		insertDivisionMatrix(hashMatrix);
		frPare.actualitzarBarraEstat("Dades dividides.", false);
	} catch (CreacioMatriuException mcExc) {
		// Pendent de gestionar !!!
		mcExc.printStackTrace();
		frPare.actualitzarBarraEstat("Error a la divisió creant matriu:" + mcExc.getLocalizedMessage(), true);
	} catch (CreacioPropietatsException pcExc) {
		// Pendent de gestionar !!!
		pcExc.printStackTrace();
		frPare.actualitzarBarraEstat("Error a la divisió creant matriu:" + pcExc.getLocalizedMessage(), true);
	} catch (IOException ioExc) {
		// Pendent de gestionar !!!
		ioExc.printStackTrace();
		frPare.actualitzarBarraEstat("Error a la divisió d'entrada/sortida:" + ioExc.getLocalizedMessage(), true);
	}
		
	}

	/**
	 * Funció que mostra un text d'ajuda quan es pitja el botó corresponent.
	 * @param e esdeveniment
	 */
	void jBttnAjuda_actionPerformed(ActionEvent e) {
	  JOptionPane.showMessageDialog(this,"Variable utilitzada pera la división de la Base de Datos.\nEl resultado se puede guardar a disco o en memoria", "Variable per a la divisió de la Base de Dades.\nEl resultat es pot guardar a disc o a memòria.", JOptionPane.INFORMATION_MESSAGE);
	}


	}
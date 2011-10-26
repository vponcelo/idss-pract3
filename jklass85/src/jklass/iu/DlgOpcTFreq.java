package jklass.iu;

import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.Hashtable;

import jklass.util.Opcions;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas,Laia Riera Guerra
 * @version v.4
 */

public class DlgOpcTFreq extends JDialog {
  Opcions opc;
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  FlowLayout flowLayout2 = new FlowLayout();
  JButton jBttnDefecte = new JButton();
  JPanel jPanelBotones = new JPanel();
  JButton jBttnCancel = new JButton();
  JPanel jPanTancar = new JPanel();
  JButton jBttnOK = new JButton();
  JPanel jPanGral = new JPanel();
  JPanel jPanOrd = new JPanel();
  JCheckBox jChBAbs = new JCheckBox();
  JCheckBox jChBRel = new JCheckBox();
  JCheckBox jChBAbsAcum = new JCheckBox();
  JCheckBox jChBRelAcum = new JCheckBox();
  GridLayout gridLayout1 = new GridLayout();
  GridLayout gridLayout2 = new GridLayout();
  Border border1;
  TitledBorder titledBorder1;
  Border border2;
  TitledBorder titledBorder2;
  Border border3;
  Border border4;

  public DlgOpcTFreq(Frame frame, String title, boolean modal, Opcions opcions) {
    super(frame, title, modal);
    opc = opcions;
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  
  /**
   * 
   * @param frame, finestra pare
   * @param title, títol de la finestra
   * @param modal, modalitat
   * @param opcions
   * @param bc, indica si han d'estar desactivades les opcions de les freqüències acumulades o no
   * @author Laia Riera Guerra
   */
  public DlgOpcTFreq(Frame frame, String title, boolean modal, Opcions opcions,boolean bc) {
	    super(frame, title, modal);
	    if(bc){
	    	jChBAbsAcum.setSelected(false);
	    	jChBRelAcum.setSelected(false);
	    	jChBAbsAcum.setEnabled(false);
		    jChBRelAcum.setEnabled(false);
	    }	    
	    opc = opcions;
	    try {
	      jbInit();
	      pack();
	    }
	    catch(Exception ex) {
	      ex.printStackTrace();
	    }
	  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
    titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158)),"General");
    border2 = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
    titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158)),"Ordinals");
    border3 = BorderFactory.createCompoundBorder(titledBorder1,BorderFactory.createEmptyBorder(0,10,0,0));
    border4 = BorderFactory.createCompoundBorder(titledBorder2,BorderFactory.createEmptyBorder(0,10,0,0));
    panel1.setLayout(borderLayout1);
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    flowLayout2.setAlignment(FlowLayout.CENTER);
    flowLayout2.setHgap(25);
    jBttnDefecte.setHorizontalAlignment(SwingConstants.LEFT);
    jBttnDefecte.setText("Per defecte");
    jBttnDefecte.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnDefecte_actionPerformed(e);
      }
    });
    jPanelBotones.setLayout(flowLayout2);
    jPanelBotones.setMaximumSize(new Dimension(32767, 32767));
    jBttnCancel.setText("Cancel·la");
    jBttnCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnCancel_actionPerformed(e);
      }
    });
    jPanTancar.setLayout(flowLayout1);
    jBttnOK.setText("D'acord");
    jBttnOK.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnOK_actionPerformed(e);
      }
    });
    jChBAbs.setEnabled(false);
    jChBAbs.setAlignmentX((float) 10.0);
    jChBAbs.setHorizontalAlignment(SwingConstants.LEFT);
//    jChBAbs.setRolloverEnabled(false);
    jChBAbs.setText("Freqüències absolutes");
    jChBRel.setAlignmentX((float) 10.0);
    jChBRel.setHorizontalAlignment(SwingConstants.LEFT);
//    jChBRel.setRolloverEnabled(false);
    jChBRel.setText("Freqüències relatives");
    jChBAbsAcum.setAlignmentX((float) 10.0);
    jChBAbsAcum.setPreferredSize(new Dimension(83, 23));
    jChBAbsAcum.setHorizontalAlignment(SwingConstants.LEFT);
    jChBAbsAcum.setText("Freqüències absolutes acumulades");
    jChBRelAcum.setAlignmentX((float) 10.0);
    jChBRelAcum.setPreferredSize(new Dimension(83, 23));
    jChBRelAcum.setHorizontalAlignment(SwingConstants.LEFT);
    jChBRelAcum.setText("Freqüències relatives acumulades");
    jPanGral.setLayout(gridLayout1);
    gridLayout1.setColumns(1);
    gridLayout1.setRows(2);
    jPanOrd.setLayout(gridLayout2);
    gridLayout2.setColumns(1);
    gridLayout2.setRows(2);
    jPanGral.setBorder(border3);
    jPanOrd.setBorder(border4);
    borderLayout1.setHgap(5);
    borderLayout1.setVgap(0);
    getContentPane().add(panel1);
    panel1.add(jPanGral,  BorderLayout.NORTH);
    panel1.add(jPanOrd, BorderLayout.SOUTH);
    this.getContentPane().add(jPanelBotones,  BorderLayout.SOUTH);
    jPanelBotones.add(jBttnDefecte, null);
    jPanelBotones.add(jPanTancar, null);
    jPanTancar.add(jBttnOK, null);
    jPanTancar.add(jBttnCancel, null);
    jPanGral.add(jChBAbs, null);
    jPanGral.add(jChBRel, null);
    jPanOrd.add(jChBAbsAcum, null);
    jPanOrd.add(jChBRelAcum, null);
    // marquem les opcions que calgui
    posarOpcions();
  }

  private void posarOpcions() {
    Hashtable taula;

    taula = opc.obtenirOpcionsEstad(Opcions.TAULA_FREQS);
    jChBAbs.setSelected( ( (Boolean) taula.get("abs")).booleanValue());
    jChBRel.setSelected( ( (Boolean) taula.get("rel")).booleanValue());
    jChBAbsAcum.setSelected( ( (Boolean) taula.get("abs_acum")).booleanValue());
    jChBRelAcum.setSelected( ( (Boolean) taula.get("rel_acum")).booleanValue());
  }

  void jBttnCancel_actionPerformed(ActionEvent e) {
    dispose();
  }

  void jBttnOK_actionPerformed(ActionEvent e) {
    Hashtable taula = new Hashtable();

    taula.put("abs", new Boolean(jChBAbs.isSelected()));
    taula.put("rel", new Boolean(jChBRel.isSelected()));
    taula.put("abs_acum", new Boolean(jChBAbsAcum.isSelected()));
    taula.put("rel_acum", new Boolean(jChBRelAcum.isSelected()));

    opc.afegirOpcions(Opcions.TAULA_FREQS, taula);

    dispose();
  }

  void jBttnDefecte_actionPerformed(ActionEvent e) {
    jChBAbs.setSelected(true);
    jChBRel.setSelected(true);
    jChBAbsAcum.setSelected(true);
    jChBRelAcum.setSelected(true);
  }

}
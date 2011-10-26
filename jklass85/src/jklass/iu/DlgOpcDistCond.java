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
 * @author Mª del Mar Colillas
 * @version v.0
 */

public class DlgOpcDistCond extends JDialog {
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
  GridLayout gridLayout1 = new GridLayout();
  JRadioButton jRBHistoDiagAbs = new JRadioButton();
  JRadioButton jRBHistoDiagFreq = new JRadioButton();
  JRadioButton jRBBoxpDiagAbs = new JRadioButton();
  JRadioButton jRBBoxpDiagFreq = new JRadioButton();
  ButtonGroup BGOpcio = new ButtonGroup();
  JCheckBox jChBVista = new JCheckBox();

  public DlgOpcDistCond(Frame frame, String title, boolean modal, Opcions opcions) {
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

  private void jbInit() throws Exception {
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
    jPanGral.setLayout(gridLayout1);
    gridLayout1.setColumns(1);
    gridLayout1.setRows(4);
    borderLayout1.setHgap(5);
    borderLayout1.setVgap(8);
    jRBHistoDiagAbs.setAlignmentX((float) 1.0);
    jRBHistoDiagAbs.setText("Histograma i diagrama de barres d\'efectius absoluts");
    jPanGral.setBorder(null);
    jRBHistoDiagFreq.setAlignmentX((float) 1.0);
    jRBHistoDiagFreq.setText("Histograma i diagrama de barres de freq. relatives");
    jRBBoxpDiagAbs.setText("Boxplot i diagrama de barres d\'efectius absoluts");
    jRBBoxpDiagFreq.setAlignmentX((float) 1.0);
    jRBBoxpDiagFreq.setText("Boxplot i diagrama de barres de freq. relatives");
    jChBVista.setAlignmentX((float) 4.0);
    jChBVista.setAlignmentY((float) 0.5);
    jChBVista.setText("Class-panel graph");
    getContentPane().add(panel1);
    panel1.add(jPanGral,  BorderLayout.NORTH);
    this.getContentPane().add(jPanelBotones,  BorderLayout.SOUTH);
    jPanelBotones.add(jBttnDefecte, null);
    jPanelBotones.add(jPanTancar, null);
    jPanTancar.add(jBttnOK, null);
    jPanTancar.add(jBttnCancel, null);
    jPanGral.add(jRBHistoDiagAbs, null);
    jPanGral.add(jRBHistoDiagFreq, null);
    jPanGral.add(jRBBoxpDiagAbs, null);
    jPanGral.add(jRBBoxpDiagFreq, null);
    panel1.add(jChBVista, BorderLayout.SOUTH);
    BGOpcio.add(jRBHistoDiagAbs);
    BGOpcio.add(jRBHistoDiagFreq);
    BGOpcio.add(jRBBoxpDiagAbs);
    BGOpcio.add(jRBBoxpDiagFreq);
    posarOpcions();
  }

  private void posarOpcions() {
    Hashtable taula;
    String str;

    taula = opc.obtenirOpcionsEstad(Opcions.HISTO_MULT);
    if (taula != null) {
      str = (String) taula.get("tipus");
      if (str.compareTo("freqs") == 0) {
        jRBHistoDiagFreq.setSelected(true);
      }
      else {
        jRBHistoDiagAbs.setSelected(true);
      }
      jChBVista.setSelected(((String)taula.get("graduaX")).equals("vista"));
    } else {
      taula = opc.obtenirOpcionsEstad(Opcions.D_BAR_MULT);
      str = (String) taula.get("tipus");
      if (str.compareTo("freqs") == 0) {
        jRBBoxpDiagFreq.setSelected(true);
      }
      else {
        jRBBoxpDiagAbs.setSelected(true);
      }
      jChBVista.setSelected(((String)taula.get("graduaX")).equals("vista"));
    }
  }

  void jBttnCancel_actionPerformed(ActionEvent e) {
    dispose();
  }

  void jBttnOK_actionPerformed(ActionEvent e) {
    Hashtable taula = null;
    boolean vista;

    vista = jChBVista.isSelected();
    //opc.posarPerDefecte(Opcions.D_BAR_MULT);
    taula = opc.obtenirOpcionsEstad(Opcions.D_BAR_MULT);
    if (vista) {
      taula.put("graduaX", "vista");
      taula.put("graduaY", "no");
    } else {
      taula.put("graduaX", "si");
      taula.put("graduaY", "marques");
      taula.put("nummarq", "3");
    }
    opc.afegirOpcions(Opcions.D_BAR_MULT, taula);
    if (jRBHistoDiagAbs.isSelected()) {
      opc.eliminarOpcions(Opcions.BOXP_MULT);
      opc.posarPerDefecte(Opcions.HISTO_MULT);
      taula = opc.obtenirOpcionsEstad(Opcions.HISTO_MULT);
      taula.put("tipus", "efectius");
      if (vista) {
        taula.put("graduaX", "vista");
        taula.put("graduaY", "no");
      } else {
        taula.put("graduaX", "marques");
        taula.put("nummarqX", "3");
        taula.put("graduaY", "si");
        taula.put("nummarqY", "3");
      }
      opc.afegirOpcions(Opcions.HISTO_MULT, taula);
      taula = opc.obtenirOpcionsEstad(Opcions.D_BAR_MULT);
      taula.put("tipus", "efectius");
      opc.afegirOpcions(Opcions.D_BAR_MULT, taula);
    }
    else if (jRBHistoDiagFreq.isSelected()) {
      opc.eliminarOpcions(Opcions.BOXP_MULT);
      opc.posarPerDefecte(Opcions.HISTO_MULT);
      taula = opc.obtenirOpcionsEstad(Opcions.HISTO_MULT);
      taula.put("tipus", "freqs");
      if (vista) {
        taula.put("graduaX", "vista");
        taula.put("graduaY", "no");
      } else {
        taula.put("graduaX", "marques");
        taula.put("nummarqX", "3");
        taula.put("graduaY", "si");
        taula.put("nummarqY", "3");
      }
      opc.afegirOpcions(Opcions.HISTO_MULT, taula);
      taula = opc.obtenirOpcionsEstad(Opcions.D_BAR_MULT);
      taula.put("tipus", "freqs");
      opc.afegirOpcions(Opcions.D_BAR_MULT, taula);
    }
    else if (jRBBoxpDiagAbs.isSelected()) {
      opc.eliminarOpcions(Opcions.HISTO_MULT);
      opc.posarPerDefecte(Opcions.BOXP_MULT);
      taula = opc.obtenirOpcionsEstad(Opcions.BOXP_MULT);
      if (vista) {
        taula.put("gradua", "vista");
      } else {
        taula.put("gradua", "marques");
        taula.put("num", "3");
      }
      opc.afegirOpcions(Opcions.BOXP_MULT, taula);
      taula = opc.obtenirOpcionsEstad(Opcions.D_BAR_MULT);
      taula.put("tipus", "efectius");
      opc.afegirOpcions(Opcions.D_BAR_MULT, taula);
    }
    else if (jRBBoxpDiagFreq.isSelected()) {
      opc.eliminarOpcions(Opcions.HISTO_MULT);
      opc.posarPerDefecte(Opcions.BOXP_MULT);
      taula = opc.obtenirOpcionsEstad(Opcions.BOXP_MULT);
      if (vista) {
        taula.put("gradua", "vista");
      } else {
        taula.put("gradua", "marques");
        taula.put("num", "3");
      }
      opc.afegirOpcions(Opcions.BOXP_MULT, taula);
      taula = opc.obtenirOpcionsEstad(Opcions.D_BAR_MULT);
      taula.put("tipus", "freqs");
      opc.afegirOpcions(Opcions.D_BAR_MULT, taula);
    }

    dispose();
  }

  void jBttnDefecte_actionPerformed(ActionEvent e) {
    opc.posarPerDefecte(Opcions.HISTO_MULT);
    opc.posarPerDefecte(Opcions.D_BAR_MULT);
    // s'actualitzen les opcions mostrades
    posarOpcions();
  }

}
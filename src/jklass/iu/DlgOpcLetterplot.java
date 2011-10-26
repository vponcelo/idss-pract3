package jklass.iu;

import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.border.*;

import jklass.util.Opcions;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

public class DlgOpcLetterplot extends JDialog {
  final static String B_N = "B/N";
  final static String COLOR = "Color";
  final static String OBSERVAT_Y = "Rang observat d'Y";
  final static String TEORIC_Y = "Rang teòric d'Y";
  final static String DEF_Y = "Definir";
  final static String OBSERVAT_X = "Rang observat d'X";
  final static String TEORIC_X = "Rang teòric d'X";
  final static String DEF_X = "Definir";
  Opcions opc;
  JPanel panel1 = new JPanel();
  JPanel jPanelBotones = new JPanel();
  JPanel jPanEixX = new JPanel();
  JPanel jPanEixY = new JPanel();
  Border border1;
  TitledBorder titledBorder1;
  Border border2;
  TitledBorder titledBorder2;
  JPanel jPanLimitsX = new JPanel();
  JLabel jLblLimitX = new JLabel();
  String[] TpRangX = {OBSERVAT_X, TEORIC_X, DEF_X};
  JComboBox jCmbBLimitX = new JComboBox(TpRangX);
  JPanel jPanMarqX = new JPanel();
  JPanel jPanMarqY = new JPanel();
  JTextField jTFNumMarqY = new JTextField(2);
  JLabel jLblLimitY = new JLabel();
  JPanel jPanRangY = new JPanel();
  String[] TpRangY = {OBSERVAT_Y, TEORIC_Y, DEF_Y};
  JComboBox jCmbBLimitY = new JComboBox(TpRangY);
  JLabel jLblGraduaX = new JLabel();
  JTextField jTFNumMarqX = new JTextField(2);
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  ButtonGroup BGrClass = new ButtonGroup();
  ButtonGroup BGrGradX = new ButtonGroup();
  Border border3;
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  JLabel jLblGraduaY = new JLabel();
  JPanel jPanDefY = new JPanel();
  JLabel jLblMinY = new JLabel();
  JPanel jPanAuto = new JPanel();
  JLabel jLblMaxY = new JLabel();
  JPanel jPanComplet = new JPanel();
  JPanel jPanCardsY = new JPanel();
  CardLayout cardLayout1 = new CardLayout();
  JTextField jTFMinY = new JTextField();
  JTextField jTFMaxY = new JTextField();
  GridLayout gridLayout1 = new GridLayout();
  GridLayout gridLayout3 = new GridLayout();
  JLabel jLblMaxX = new JLabel();
  JPanel jPanDefX = new JPanel();
  CardLayout cardLayout2 = new CardLayout();
  JTextField jTFMaxX = new JTextField();
  JPanel jPanTeoric = new JPanel();
  JLabel jLblMinX = new JLabel();
  JPanel jPanCardsX = new JPanel();
  JTextField jTFMinX = new JTextField();
  JPanel jPanObs = new JPanel();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  JLabel jLblMarquesY = new JLabel();
  TitledBorder titledBorder3;
  JPanel jPanTancar = new JPanel();
  FlowLayout flowLayout2 = new FlowLayout();
  JButton jBttnCancel = new JButton();
  JButton jBttnOK = new JButton();
  JButton jBttnDefecte = new JButton();
  FlowLayout flowLayout3 = new FlowLayout();
  JLabel jLblMarquesX = new JLabel();
  JPanel jPanTpLPlot = new JPanel();
  JLabel jLblTipus = new JLabel();
  String[] TpLPlot = {COLOR, B_N};
  JComboBox jCmbBDad = new JComboBox(TpLPlot);

  public DlgOpcLetterplot(Frame frame, String title, boolean modal, Opcions opcions) {
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
    border1 = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
    titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158)),"Eix X");
    border2 = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
    titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158)),"Eix Y");
    border3 = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.white,1),BorderFactory.createEmptyBorder(4,4,4,4));
    panel1.setLayout(gridBagLayout1);
    jPanEixX.setBorder(titledBorder1);
    jPanEixX.setLayout(gridBagLayout3);
    jPanEixY.setBorder(titledBorder2);
    jPanEixY.setLayout(gridBagLayout2);
    jLblLimitX.setText("Límits:");
    jTFNumMarqY.setColumns(3);
    jLblLimitY.setText("Límits:");
    jLblGraduaX.setText("Graduació:");
    jTFNumMarqX.setColumns(3);
    jCmbBLimitY.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jCmbBLimitY_itemStateChanged(e);
      }
    });
    jLblGraduaY.setText("Graduació:");
    jPanDefY.setBorder(border3);
    jPanDefY.setLayout(gridLayout1);
    jLblMinY.setHorizontalAlignment(SwingConstants.RIGHT);
    jLblMinY.setText("Mín =");
    jLblMaxY.setHorizontalAlignment(SwingConstants.RIGHT);
    jLblMaxY.setText("Màx =");
    jPanCardsY.setLayout(cardLayout1);
    jTFMaxY.setText("");
    jTFMaxY.setColumns(8);
    jTFMinY.setText("");
    jTFMinY.setColumns(8);
    gridLayout1.setColumns(2);
    gridLayout1.setHgap(4);
    gridLayout1.setRows(2);
    gridLayout1.setVgap(4);
    gridLayout3.setHgap(4);
    gridLayout3.setRows(2);
    gridLayout3.setVgap(4);
    gridLayout3.setColumns(2);
    jLblMaxX.setText("Màx =");
    jLblMaxX.setHorizontalAlignment(SwingConstants.RIGHT);
    jPanDefX.setLayout(gridLayout3);
    jPanDefX.setBorder(border3);
    jTFMaxX.setText("");
    jTFMaxX.setColumns(8);
    jLblMinX.setHorizontalAlignment(SwingConstants.RIGHT);
    jLblMinX.setText("Mín =");
    jPanCardsX.setLayout(cardLayout2);
    jTFMinX.setText("");
    jTFMinX.setColumns(8);
    jCmbBLimitX.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jCmbBLimitX_itemStateChanged(e);
      }
    });
    jLblMarquesY.setText("Nombre de marques =");
    jPanTancar.setLayout(flowLayout2);
    jBttnCancel.setText("Cancel·la");
    jBttnCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnCancel_actionPerformed(e);
      }
    });
    jBttnOK.setText("D'acord");
    jBttnOK.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnOK_actionPerformed(e);
      }
    });
    jBttnDefecte.setHorizontalAlignment(SwingConstants.LEFT);
    jBttnDefecte.setText("Per defecte");
    jBttnDefecte.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnDefecte_actionPerformed(e);
      }
    });
    jPanelBotones.setLayout(flowLayout3);
    flowLayout3.setHgap(25);
    jLblMarquesX.setText("Nombre de marques =");
    jLblTipus.setText("Tipus de letterplot:");
    jPanCardsX.add(jPanObs, OBSERVAT_X);
    jPanCardsX.add(jPanTeoric, TEORIC_X);
    jPanCardsX.add(jPanDefX, DEF_X);
    jPanDefX.add(jLblMinX, null);
    jPanDefX.add(jTFMinX, null);
    jPanDefX.add(jLblMaxX, null);
    jPanDefX.add(jTFMaxX, null);
    jPanEixX.add(jPanLimitsX, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
    jPanMarqY.add(jLblMarquesY, null);
    jPanMarqY.add(jTFNumMarqY, null);
    this.getContentPane().add(jPanelBotones, BorderLayout.SOUTH);
    jPanEixY.add(jLblGraduaY,              new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 10, 0, 0), 0, 0));
    jPanEixY.add(jPanCardsY,      new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 2, 0, 0), 0, 0));
    jPanCardsY.add(jPanComplet, OBSERVAT_Y);
    jPanCardsY.add(jPanAuto, TEORIC_Y);
    jPanCardsY.add(jPanDefY, DEF_Y);
    jPanDefY.add(jLblMinY, null);
    jPanDefY.add(jTFMinY, null);
    jPanDefY.add(jLblMaxY, null);
    jPanDefY.add(jTFMaxY, null);
    jPanEixY.add(jPanRangY, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
    jPanMarqX.add(jLblMarquesX, null);
    jPanMarqX.add(jTFNumMarqX, null);
    panel1.add(jPanTpLPlot,       new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 0, 0), 0, 0));
    jPanTpLPlot.add(jLblTipus, null);
    jPanTpLPlot.add(jCmbBDad, null);
    panel1.add(jPanEixY,            new GridBagConstraints(1, 1, 3, 3, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 5, 5), 0, 0));
    jPanRangY.add(jLblLimitY, null);
    jPanRangY.add(jCmbBLimitY, null);
    jPanEixY.add(jPanMarqY, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, -60, 0, 0), 0, 0));
    panel1.add(jPanEixX,                                          new GridBagConstraints(0, 1, 1, 2, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 5, 5, 2), 0, 0));
    jPanelBotones.add(jBttnDefecte, null);
    jPanelBotones.add(jPanTancar, null);
    jPanTancar.add(jBttnOK, null);
    jPanTancar.add(jBttnCancel, null);
    this.getContentPane().add(panel1, BorderLayout.NORTH);
    jPanEixX.add(jPanCardsX, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    jPanLimitsX.add(jLblLimitX, null);
    jPanLimitsX.add(jCmbBLimitX, null);
    jPanEixX.add(jLblGraduaX,    new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 10, 0, 0), 0, 0));
    jPanEixX.add(jPanMarqX,       new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, -60, 0, 0), 0, 0));
    posarOpcions();
  }

  private void posarOpcions() {
    Hashtable taula;
    String str;

    taula = opc.obtenirOpcionsEstad(Opcions.LETTERPLOT);

    str = (String)taula.get("tipus");
    if (str.equals("color")) {
      jCmbBDad.setSelectedItem(COLOR);
    } else {
      jCmbBDad.setSelectedItem(B_N);
    }

    str = (String)taula.get("limitsX");
    if (str.equals("observat")) {
      jCmbBLimitX.setSelectedItem(OBSERVAT_X);
    } else if (str.equals("teoric")) {
      jCmbBLimitX.setSelectedItem(TEORIC_X);
    } else {
      jCmbBLimitX.setSelectedItem(DEF_X);
      str = (String)taula.get("minX");
      if (str != null){
        jTFMinX.setText(str);
      }
      str = (String) taula.get("maxX");
      if (str != null) {
        jTFMaxX.setText(str);
      }
    }
    str = (String) taula.get("nummarqX");
    if (str != null) {
      jTFNumMarqX.setText(str);
    }
    str = (String)taula.get("limitsY");
    if (str.equals("observat")) {
      jCmbBLimitY.setSelectedItem(OBSERVAT_Y);
    } else if (str.equals("teoric")) {
      jCmbBLimitY.setSelectedItem(TEORIC_Y);
    } else {
      jCmbBLimitY.setSelectedItem(DEF_Y);
      str = (String)taula.get("minY");
      if (str != null){
        jTFMinY.setText(str);
      }
      str = (String) taula.get("maxY");
      if (str != null) {
        jTFMaxY.setText(str);
      }
    }
    str = (String)taula.get("nummarqY");
      if (str != null){
        jTFNumMarqY.setText(str);
      }
  }

  void jBttnCancel_actionPerformed(ActionEvent e) {
    dispose();
  }

  void jBttnOK_actionPerformed(ActionEvent e) {
    Hashtable taula = new Hashtable();

    if (((String)jCmbBDad.getSelectedItem()).compareTo(B_N) == 0) {
      taula.put("tipus", "bn");
    }
    else if ( ( (String) jCmbBDad.getSelectedItem()).compareTo(COLOR) == 0) {
      taula.put("tipus", "color");
    }

    if (((String)jCmbBLimitX.getSelectedItem()).compareTo(OBSERVAT_X) == 0) { // Rang observat
      taula.put("limitsX", "observat");
    }
    else if (((String)jCmbBLimitX.getSelectedItem()).compareTo(TEORIC_X) == 0) { // Rang teoric
      taula.put("limitsX", "teoric");
    }
    if (((String)jCmbBLimitX.getSelectedItem()).compareTo(DEF_X) == 0) { // Definit
      taula.put("limitsX", "def");
      taula.put("minX", jTFMinX.getText());
      taula.put("maxX", jTFMaxX.getText());
    }
    taula.put("graduaX", "si");
    taula.put("nummarqX", jTFNumMarqX.getText());

    if (((String)jCmbBLimitY.getSelectedItem()).compareTo(OBSERVAT_Y) == 0) { // Rang observat
      taula.put("limitsY", "observat");
    }
    else if (((String)jCmbBLimitY.getSelectedItem()).compareTo(TEORIC_Y) == 0) { // Rang teoric
      taula.put("limitsY", "teoric");
    }
    if (((String)jCmbBLimitY.getSelectedItem()).compareTo(DEF_Y) == 0) { // Definit
      taula.put("limitsY", "def");
      taula.put("minY", jTFMinY.getText());
      taula.put("maxY", jTFMaxY.getText());
    }
    taula.put("graduaY", "si");
    taula.put("nummarqY", jTFNumMarqY.getText());

    opc.afegirOpcions(Opcions.LETTERPLOT, taula);

    dispose();
  }

  void jCmbBLimitY_itemStateChanged(ItemEvent e) {
    cardLayout1.show(jPanCardsY, (String)e.getItem());
  }

  void jCmbBLimitX_itemStateChanged(ItemEvent e) {
    cardLayout2.show(jPanCardsX, (String)e.getItem());
  }

  void jBttnDefecte_actionPerformed(ActionEvent e) {
    opc.posarPerDefecte(Opcions.LETTERPLOT);
    // s'actualitzen les opcions mostrades
    posarOpcions();
  }

}
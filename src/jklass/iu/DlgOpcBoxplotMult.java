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

public class DlgOpcBoxplotMult extends JDialog {
  final static String OBSERVAT = "Rang observat d'X";
  final static String TEORIC = "Rang teòric d'X";
  final static String DEF_X = "Definir";
  Opcions opc;
  JPanel jPanelBotones = new JPanel();
  Border border1;
  TitledBorder titledBorder1;
  JTextField jTFNumMarq = new JTextField(2);
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  JTextField jTFMaxX = new JTextField();
  JPanel jPanTeoric = new JPanel();
  GridLayout gridLayout3 = new GridLayout();
  JLabel jLblMinX = new JLabel();
  JPanel jPanObs = new JPanel();
  JTextField jTFMinX = new JTextField();
  JPanel jPanEixX = new JPanel();
  CardLayout cardLayout2 = new CardLayout();
  JPanel jPanLimitsX = new JPanel();
  JLabel jLblLimitX = new JLabel();
  JPanel jPanMarq = new JPanel();
  JLabel jLblMaxX = new JLabel();
  JPanel jPanDefX = new JPanel();
  JLabel jLblGraduaX = new JLabel();
  JPanel jPanCardsX = new JPanel();
  String[] TpRangX = {OBSERVAT, TEORIC, DEF_X};
  JComboBox jCmbBLimitX = new JComboBox(TpRangX);
  Border border2;
  ButtonGroup BGrGradX = new ButtonGroup();
  JPanel jPanTancar = new JPanel();
  JButton jBttnCancel = new JButton();
  JButton jBttnOK = new JButton();
  JButton jBttnDefecte = new JButton();
  FlowLayout flowLayout2 = new FlowLayout();
  JLabel jLblMarquesX = new JLabel();

  public DlgOpcBoxplotMult(Frame frame, String title, boolean modal, Opcions opcions) {
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
    border2 = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.white,1),BorderFactory.createEmptyBorder(4,4,4,4));
    setSize(new Dimension(329, 193));
    border1 = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
    titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158)),"Eix X");
    jTFNumMarq.setColumns(3);
    jTFMaxX.setText("");
    jTFMaxX.setColumns(8);
    gridLayout3.setHgap(4);
    gridLayout3.setRows(2);
    gridLayout3.setVgap(4);
    gridLayout3.setColumns(2);
    jLblMinX.setHorizontalAlignment(SwingConstants.RIGHT);
    jLblMinX.setText("Mín =");
    jTFMinX.setText("");
    jTFMinX.setColumns(8);
    jPanEixX.setBorder(titledBorder1);
    jPanEixX.setLayout(gridBagLayout3);
    jLblLimitX.setText("Límits:");
    jLblMaxX.setText("Màx =");
    jLblMaxX.setHorizontalAlignment(SwingConstants.RIGHT);
    jPanDefX.setLayout(gridLayout3);
    jLblGraduaX.setText("Graduació:");
    jPanCardsX.setLayout(cardLayout2);
    jCmbBLimitX.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jCmbBLimitX_itemStateChanged(e);
      }
    });
    jPanDefX.setBorder(border2);
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
    jPanelBotones.setLayout(flowLayout2);
    flowLayout2.setHgap(25);
    jLblMarquesX.setText("Nombre de marques =");
    jPanelBotones.add(jBttnDefecte, null);
    this.getContentPane().add(jPanelBotones,  BorderLayout.SOUTH);
    jPanelBotones.add(jPanTancar, null);
    this.getContentPane().add(jPanEixX,  BorderLayout.CENTER);
    jPanCardsX.add(jPanObs, OBSERVAT);
    jPanCardsX.add(jPanTeoric, TEORIC);
    jPanCardsX.add(jPanDefX, DEF_X);
    jPanDefX.add(jLblMinX, null);
    jPanDefX.add(jTFMinX, null);
    jPanDefX.add(jLblMaxX, null);
    jPanDefX.add(jTFMaxX, null);
    jPanEixX.add(jPanLimitsX,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
    jPanEixX.add(jPanCardsX,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    jPanLimitsX.add(jLblLimitX, null);
    jPanLimitsX.add(jCmbBLimitX, null);
    jPanEixX.add(jLblGraduaX,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 10, 0, 0), 0, 0));
    jPanEixX.add(jPanMarq,  new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, -70, 0, 0), 0, 0));
    jPanMarq.add(jLblMarquesX, null);
    jPanMarq.add(jTFNumMarq, null);
    jPanTancar.add(jBttnOK, null);
    jPanTancar.add(jBttnCancel, null);
    posarOpcions();
  }

  private void posarOpcions() {
    Hashtable taula;
    String str;

    taula = opc.obtenirOpcionsEstad(Opcions.BOXP_MULT);
    str = (String) taula.get("limits");
    if (str.equals("observat")) {
      jCmbBLimitX.setSelectedItem(OBSERVAT);
    }
    else if (str.equals("teoric")) {
      jCmbBLimitX.setSelectedItem(TEORIC);
    }
    else {
      jCmbBLimitX.setSelectedItem(DEF_X);
      str = (String) taula.get("min");
      if (str != null) {
        jTFMinX.setText(str);
      }
      str = (String) taula.get("max");
      if (str != null) {
        jTFMaxX.setText(str);
      }
    }
    str = (String) taula.get("num");
    if (str != null) {
      jTFNumMarq.setText(str);
    }
  }

  void jBttnCancel_actionPerformed(ActionEvent e) {
    dispose();
  }

  void jBttnOK_actionPerformed(ActionEvent e) {
    Hashtable taula = new Hashtable();
    if (jCmbBLimitX.getSelectedIndex()== 0) { // Rang observat
      taula.put("limits", "observat");
    } else if (jCmbBLimitX.getSelectedIndex()== 1) { // Rang teoric
      taula.put("limits", "teoric");
    } if (jCmbBLimitX.getSelectedIndex()== 2) { // Definit
      taula.put("limits", "def");
      taula.put("min", jTFMinX.getText());
      taula.put("max", jTFMaxX.getText());
    }
    taula.put("gradua", "marques");
    taula.put("num", jTFNumMarq.getText());

    opc.afegirOpcions(Opcions.BOXP_MULT, taula);

    dispose();
  }

  void jCmbBLimitX_itemStateChanged(ItemEvent e) {
    cardLayout2.show(jPanCardsX, (String)e.getItem());
  }

  void jBttnDefecte_actionPerformed(ActionEvent e) {
    opc.posarPerDefecte(Opcions.BOXP_MULT);
    // s'actualitzen les opcions mostrades
    posarOpcions();
  }

}
package jklass.iu;

import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.border.*;

import jklass.util.Opcions;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

public class DlgOpcDiagBar extends JDialog {
  final static String EFECTIUS = "Efectius absoluts";
  final static String FREQS = "Freqüències relatives";
  final static String COMPLET = "Eix complet";
  final static String AUTO = "Automàtic";
  final static String DEF_Y = "Definir";
  Opcions opc;
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanelBotones = new JPanel();
  JPanel jPFreqs = new JPanel();
  String[] TpFreqs = {EFECTIUS, FREQS};
  JComboBox jCmbBFreqs = new JComboBox(TpFreqs);
  JLabel jLblTipus = new JLabel();
  String[] TpRangY = {COMPLET, AUTO, DEF_Y};
  Border border1;
  TitledBorder titledBorder1;
  JLabel jLblLimitY = new JLabel();
  JComboBox jCmbBLimitY = new JComboBox(TpRangY);
  JPanel jPanAuto = new JPanel();
  JLabel jLblMinY = new JLabel();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  JPanel jPanRangY = new JPanel();
  JTextField jTFMinY = new JTextField();
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLblGraduaY = new JLabel();
  JLabel jLblMaxY = new JLabel();
  JPanel jPanDefY = new JPanel();
  CardLayout cardLayout1 = new CardLayout();
  JPanel jPanEixY = new JPanel();
  JTextField jTFMaxY = new JTextField();
  JPanel jPanel2 = new JPanel();
  JPanel jPanCardsY = new JPanel();
  JTextField jTextField5 = new JTextField(2);
  JPanel jPanComplet = new JPanel();
  Border border2;
  TitledBorder titledBorder2;
  Border border3;
  JLabel jLblMarquesY = new JLabel();
  JPanel jPanTancar = new JPanel();
  JButton jBttnCancel = new JButton();
  JButton jBttnOK = new JButton();
  JButton jBttnDefecte = new JButton();
  FlowLayout flowLayout1 = new FlowLayout();

  public DlgOpcDiagBar(Frame frame, String title, boolean modal, Opcions opcions) {
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
    titledBorder1 = new TitledBorder(border1,"Eix X");
    border2 = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
    titledBorder2 = new TitledBorder(border2,"Eix Y");
    border3 = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.white,1),BorderFactory.createEmptyBorder(4,4,4,4));
    setSize(new Dimension(314, 192));
    panel1.setLayout(borderLayout1);
    jLblTipus.setText("Tipus de freqüències:");
    jLblLimitY.setText("Límits:");
    jCmbBLimitY.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jCmbBLimitY_itemStateChanged(e);
      }
    });
    jLblMinY.setHorizontalAlignment(SwingConstants.RIGHT);
    jLblMinY.setText("Mín =");
    jTFMinY.setText("");
    jTFMinY.setColumns(8);
    gridLayout1.setColumns(2);
    gridLayout1.setHgap(4);
    gridLayout1.setRows(2);
    gridLayout1.setVgap(4);
    jLblGraduaY.setText("Graduació:");
    jLblMaxY.setToolTipText("");
    jLblMaxY.setHorizontalAlignment(SwingConstants.RIGHT);
    jLblMaxY.setText("Màx =");
    jPanDefY.setLayout(gridLayout1);
    jPanEixY.setLayout(gridBagLayout2);
    jTFMaxY.setText("");
    jTFMaxY.setColumns(8);
    jPanCardsY.setLayout(cardLayout1);
//    jTextField5.setText("4");
    jTextField5.setColumns(3);
    jPanEixY.setBorder(titledBorder2);
    jPanDefY.setBorder(border3);
    jLblMarquesY.setText("Nombre de marques =");
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
    jPanelBotones.setLayout(flowLayout1);
    flowLayout1.setHgap(25);
    getContentPane().add(panel1, BorderLayout.CENTER);
    panel1.add(jPFreqs,  BorderLayout.NORTH);
    this.getContentPane().add(jPanelBotones,  BorderLayout.SOUTH);
    jPanelBotones.add(jBttnDefecte, null);
    jPanelBotones.add(jPanTancar, null);
    jPFreqs.add(jLblTipus, null);
    jPFreqs.add(jCmbBFreqs, null);
    panel1.add(jPanEixY, BorderLayout.SOUTH);
    jPanEixY.add(jLblGraduaY, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 10, 0, 0), 0, 0));
    jPanEixY.add(jPanCardsY, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 2, 0, 0), 0, 0));
    jPanCardsY.add(jPanComplet, COMPLET);
    jPanCardsY.add(jPanAuto, AUTO);
    jPanCardsY.add(jPanDefY, DEF_Y);
    jPanDefY.add(jLblMinY, null);
    jPanDefY.add(jTFMinY, null);
    jPanDefY.add(jLblMaxY, null);
    jPanDefY.add(jTFMaxY, null);
    jPanEixY.add(jPanRangY, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
    jPanRangY.add(jLblLimitY, null);
    jPanRangY.add(jCmbBLimitY, null);
    jPanEixY.add(jPanel2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, -60, 0, 0), 0, 0));
    jPanel2.add(jLblMarquesY, null);
    jPanel2.add(jTextField5, null);
    jPanTancar.add(jBttnOK, null);
    jPanTancar.add(jBttnCancel, null);
    posarOpcions();
  }

  private void posarOpcions() {
    Hashtable taula;
    String str;

    taula = opc.obtenirOpcionsEstad(Opcions.DIAGR_BARRES);

    if (((String)taula.get("tipus")).equals("efectius")) {
      jCmbBFreqs.setSelectedItem(EFECTIUS);
    } else {
      jCmbBFreqs.setSelectedItem(FREQS);
    }

    str = (String)taula.get("limits");
    if (str.equals("complet")) {
      jCmbBLimitY.setSelectedItem(COMPLET);
    } else if (str.equals("auto")) {
      jCmbBLimitY.setSelectedItem(AUTO);
    } else {
      jCmbBLimitY.setSelectedItem(DEF_Y);
      str = (String)taula.get("min");
      if (str != null){
        jTFMinY.setText(str);
      }
      str = (String) taula.get("max");
      if (str != null) {
        jTFMaxY.setText(str);
      }
    }
    str = (String) taula.get("nummarq");
    if (str != null) {
      jTextField5.setText(str);
    }
  }

  void jBttnCancel_actionPerformed(ActionEvent e) {
    dispose();
  }

  void jBttnOK_actionPerformed(ActionEvent e) {
    Hashtable taula = new Hashtable();

    if ( ( (String) jCmbBFreqs.getSelectedItem()).compareTo(EFECTIUS) == 0) {
      taula.put("tipus", "efectius");
    }
    else if ( ( (String) jCmbBFreqs.getSelectedItem()).compareTo(FREQS) == 0) {
      taula.put("tipus", "freqs");
    }
    if (((String)jCmbBLimitY.getSelectedItem()).compareTo(COMPLET) == 0) { // Eix complet
      taula.put("limits", "complet");
    }
    else if (((String)jCmbBLimitY.getSelectedItem()).compareTo(AUTO) == 0) { // Automatic
      taula.put("limits", "auto");
    }
    if (((String)jCmbBLimitY.getSelectedItem()).compareTo(DEF_Y) == 0) { // Definit
      taula.put("limits", "def");
      taula.put("min", jTFMinY.getText());
      taula.put("max", jTFMaxY.getText());
    }
    taula.put("graduaY", "marques");
    taula.put("nummarq", jTextField5.getText());
    taula.put("graduaX", "si");

    opc.afegirOpcions(Opcions.DIAGR_BARRES, taula);

    dispose();
  }

  void jCmbBLimitY_itemStateChanged(ItemEvent e) {
    cardLayout1.show(jPanCardsY, (String)e.getItem());
  }

  void jBttnDefecte_actionPerformed(ActionEvent e) {
    opc.posarPerDefecte(Opcions.DIAGR_BARRES);
    // s'actualitzen les opcions mostrades
    posarOpcions();

  }

}
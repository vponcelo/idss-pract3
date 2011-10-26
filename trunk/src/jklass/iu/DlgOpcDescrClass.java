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

public class DlgOpcDescrClass extends JDialog {
  Opcions opc;
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanelBotones = new JPanel();
  JPanel jPanTancar = new JPanel();
  JButton jBttnCancel = new JButton();
  JButton jBttnOK = new JButton();
  JButton jBttnDefecte = new JButton();
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel jPanEstads = new JPanel();
  JTextField jTFMaxCateg = new JTextField(2);
  JLabel jLblMaxCateg = new JLabel();
  JPanel jPanCateg = new JPanel();
  JLabel jLblMaxNum = new JLabel();
  JPanel jPanNum = new JPanel();
  JTextField jTFMaxNum = new JTextField(2);
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  Border border4;
  TitledBorder titledBorder4;
  JPanel jPanBasics = new JPanel();
  JCheckBox jCBMitj = new JCheckBox();
  JCheckBox jCBMax = new JCheckBox();
  JCheckBox jCBMin = new JCheckBox();
  JCheckBox jCBNumObjs = new JCheckBox();
  JPanel jPanDisper = new JPanel();
  JPanel jPanRobust = new JPanel();
  JCheckBox jCBQDesv = new JCheckBox();
  JCheckBox jCBQVar = new JCheckBox();
  JCheckBox jCBDesv = new JCheckBox();
  JCheckBox jCBVar = new JCheckBox();
  JCheckBox jCBCoef = new JCheckBox();
  JCheckBox jCBAmp = new JCheckBox();
  JCheckBox jCBDistInterQ = new JCheckBox();
  JCheckBox jCBQuart = new JCheckBox();
  JCheckBox jCBMe = new JCheckBox();
  Border border1;
  TitledBorder titledBorder1;
  Border border2;
  TitledBorder titledBorder2;
  Border border3;
  TitledBorder titledBorder3;
  GridLayout gridLayout1 = new GridLayout();
  GridLayout gridLayout2 = new GridLayout();
  GridLayout gridLayout3 = new GridLayout();
  Border border5;
  FlowLayout flowLayout2 = new FlowLayout();

  public DlgOpcDescrClass(Frame frame, String title, boolean modal, Opcions opcions) {
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
    border1 = BorderFactory.createEmptyBorder();
    titledBorder1 = new TitledBorder(border1, "Bàsics");
    border5 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158)),"Estadístics (vars. numèriques)");
    titledBorder1.setTitleFont(new java.awt.Font("SansSerif", 1, 12));
    border2 = BorderFactory.createEmptyBorder();
    titledBorder2 = new TitledBorder(border2, "Robustos");
    titledBorder2.setTitleFont(new java.awt.Font("SansSerif", 1, 12));
    border3 = BorderFactory.createEmptyBorder();
    titledBorder3 = new TitledBorder(border3, "Mesures de dispersió");
    titledBorder3.setTitleFont(new java.awt.Font("SansSerif", 1, 12));
    border4 = BorderFactory.createEtchedBorder(Color.white,
                                               new Color(156, 156, 158));
    titledBorder4 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158)),"Estadístics (vars. numèriques)");
    setSize(new Dimension(407, 394));
    panel1.setLayout(borderLayout1);
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
    jTFMaxCateg.setColumns(3);
    jLblMaxCateg.setText("Nombre màx. de classes per pàg. per vars. categòriques =");
    jLblMaxNum.setText("Nombre màx. de classes per pàg. per vars. numèriques =");
    jTFMaxNum.setColumns(3);
    jPanNum.setLayout(flowLayout2);
    jPanEstads.setLayout(gridBagLayout1);
    jPanEstads.setFont(new java.awt.Font("SansSerif", 3, 11));
    jPanEstads.setBorder(border5);
    jCBNumObjs.setEnabled(false);
    jCBNumObjs.setSelected(true);
    jCBNumObjs.setText("Nombre d\'objectes");
    jCBMitj.setText("Mitjana");
    jCBMax.setText("Màxim");
    jCBMin.setText("Mínim");
    jCBQDesv.setText("Quasi-desviació típica");
    jCBQVar.setText("Quasi-variància");
    jCBDesv.setText("Desviació típica ");
    jCBVar.setText("Variància");
    jCBCoef.setText("Coeficient de variació");
    jCBAmp.setText("Amplitud");
    jCBDistInterQ.setText("Distància interquartíl·lica");
    jCBQuart.setText("Quartils (Q1 i Q3)");
    jCBMe.setText("Mediana");
    jPanBasics.setBorder(titledBorder1);
    jPanBasics.setLayout(gridLayout1);
    jPanRobust.setBorder(titledBorder2);
    jPanRobust.setLayout(gridLayout2);
    jPanDisper.setBorder(titledBorder3);
    jPanDisper.setLayout(gridLayout3);
    gridLayout1.setColumns(1);
    gridLayout1.setRows(4);
    gridLayout2.setColumns(1);
    gridLayout2.setRows(3);
    gridLayout3.setColumns(1);
    gridLayout3.setRows(6);

    getContentPane().add(panel1, BorderLayout.CENTER);
    this.getContentPane().add(jPanelBotones,  BorderLayout.SOUTH);
    jPanelBotones.add(jBttnDefecte, null);
    jPanelBotones.add(jPanTancar, null);
    jPanTancar.add(jBttnOK, null);
    jPanTancar.add(jBttnCancel, null);

    panel1.add(jPanEstads, BorderLayout.SOUTH);
    panel1.add(jPanCateg, BorderLayout.CENTER);
    jPanCateg.add(jLblMaxCateg, null);
    jPanCateg.add(jTFMaxCateg, null);
    panel1.add(jPanNum, BorderLayout.NORTH);
    jPanNum.add(jLblMaxNum, null);
    jPanNum.add(jTFMaxNum, null);
    jPanEstads.add(jPanBasics, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(10, 10, 5, 5), 0, 0));
    jPanEstads.add(jPanRobust, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(5, 10, 10, 5), 0, 0));
    jPanEstads.add(jPanDisper, new GridBagConstraints(1, 0, 1, 2, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(10, 5, 10, 10), 0, 0));
    jPanBasics.add(jCBNumObjs, null);
    jPanBasics.add(jCBMin, null);
    jPanBasics.add(jCBMax, null);
    jPanBasics.add(jCBMitj, null);
    jPanDisper.add(jCBAmp, null);
    jPanDisper.add(jCBVar, null);
    jPanDisper.add(jCBDesv, null);
    jPanDisper.add(jCBQVar, null);
    jPanDisper.add(jCBQDesv, null);
    jPanDisper.add(jCBCoef, null);
    jPanRobust.add(jCBMe, null);
    jPanRobust.add(jCBQuart, null);
    jPanRobust.add(jCBDistInterQ, null);
    jPanTancar.add(jBttnOK, null);
    jPanTancar.add(jBttnCancel, null);
    // marquem les opcions que calgui
    posarOpcions();
  }

  private void posarOpcions() {
    Hashtable taula;
    String str;

    taula = opc.obtenirOpcionsEstad(Opcions.DESCR_CLASS);
    str = (String) taula.get("maxnum");
    if (str != null) {
      jTFMaxNum.setText(str);
    }
    str = (String) taula.get("maxcateg");
    if (str != null) {
      jTFMaxCateg.setText(str);
    }

    jCBMin.setSelected( ( (Boolean) taula.get("min")).booleanValue());
    jCBMax.setSelected( ( (Boolean) taula.get("max")).booleanValue());
    jCBMitj.setSelected( ( (Boolean) taula.get("mitj")).booleanValue());
    jCBMe.setSelected( ( (Boolean) taula.get("me")).booleanValue());
    jCBQuart.setSelected( ( (Boolean) taula.get("q")).booleanValue());
    jCBDistInterQ.setSelected( ( (Boolean) taula.get("dist_iq")).booleanValue());
    jCBAmp.setSelected( ( (Boolean) taula.get("amp")).booleanValue());
    jCBVar.setSelected( ( (Boolean) taula.get("var")).booleanValue());
    jCBDesv.setSelected( ( (Boolean) taula.get("desv")).booleanValue());
    jCBQVar.setSelected( ( (Boolean) taula.get("qvar")).booleanValue());
    jCBQDesv.setSelected( ( (Boolean) taula.get("qdesv")).booleanValue());
    jCBCoef.setSelected( ( (Boolean) taula.get("coef")).booleanValue());

  }

  void jBttnCancel_actionPerformed(ActionEvent e) {
    dispose();
  }

  void jBttnOK_actionPerformed(ActionEvent e) {
    Hashtable taula = new Hashtable();

    taula.put("maxnum", jTFMaxNum.getText());
    taula.put("maxcateg", jTFMaxCateg.getText());

    taula.put("min", new Boolean(jCBMin.isSelected()));
    taula.put("max", new Boolean(jCBMax.isSelected()));
    taula.put("mitj", new Boolean(jCBMitj.isSelected()));
    taula.put("me", new Boolean(jCBMe.isSelected()));
    taula.put("q", new Boolean(jCBQuart.isSelected()));
    taula.put("dist_iq", new Boolean(jCBDistInterQ.isSelected()));
    taula.put("amp", new Boolean(jCBAmp.isSelected()));
    taula.put("var", new Boolean(jCBVar.isSelected()));
    taula.put("desv", new Boolean(jCBDesv.isSelected()));
    taula.put("qvar", new Boolean(jCBQVar.isSelected()));
    taula.put("qdesv", new Boolean(jCBQDesv.isSelected()));
    taula.put("coef", new Boolean(jCBCoef.isSelected()));

    opc.afegirOpcions(Opcions.DESCR_CLASS, taula);

    dispose();
  }

  void jBttnDefecte_actionPerformed(ActionEvent e) {
    opc.posarPerDefecte(Opcions.DESCR_CLASS);
    // s'actualitzen les opcions mostrades
    posarOpcions();

  }

}
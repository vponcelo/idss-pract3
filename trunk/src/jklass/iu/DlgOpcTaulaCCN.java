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

public class DlgOpcTaulaCCN extends JDialog {
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
  JLabel jLblMaxNum = new JLabel();
  JPanel jPanNum = new JPanel();
  JTextField jTFMaxNum = new JTextField(2);
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  Border border4;
  TitledBorder titledBorder4;
  JPanel jPanBasics = new JPanel();
  JRadioButton jRBMitj = new JRadioButton();
  JRadioButton jRBMax = new JRadioButton();
  JRadioButton jRBMin = new JRadioButton();
  JRadioButton jRBMiss = new JRadioButton();
  JPanel jPanDisper = new JPanel();
  JPanel jPanRobust = new JPanel();
  JRadioButton jRBQDesv = new JRadioButton();
  JRadioButton jRBQVar = new JRadioButton();
  JRadioButton jRBDesv = new JRadioButton();
  JRadioButton jRBVar = new JRadioButton();
  JRadioButton jRBCoef = new JRadioButton();
  JRadioButton jRBAmp = new JRadioButton();
  JRadioButton jRBDistInterQ = new JRadioButton();
  JRadioButton jRBQ1 = new JRadioButton();
  JRadioButton jRBMe = new JRadioButton();
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
  JRadioButton jRBQ3 = new JRadioButton();
  ButtonGroup BGOpcio = new ButtonGroup();

  public DlgOpcTaulaCCN(Frame frame, String title, boolean modal, Opcions opcions) {
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
    border5 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158)),"Estadístic (var. numèrica)");
    titledBorder1.setTitleFont(new java.awt.Font("SansSerif", 1, 12));
    border2 = BorderFactory.createEmptyBorder();
    titledBorder2 = new TitledBorder(border2, "Robustos");
    titledBorder2.setTitleFont(new java.awt.Font("SansSerif", 1, 12));
    border3 = BorderFactory.createEmptyBorder();
    titledBorder3 = new TitledBorder(border3, "Mesures de dispersió");
    titledBorder3.setTitleFont(new java.awt.Font("SansSerif", 1, 12));
    setSize(new Dimension(406, 376));
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
    jLblMaxNum.setText("Nombre màx. de modalitats en horitzontal per pàg.  =");
    jTFMaxNum.setColumns(3);
    jPanNum.setLayout(flowLayout2);
    jPanEstads.setLayout(gridBagLayout1);
    jPanEstads.setFont(new java.awt.Font("SansSerif", 3, 11));
    jPanEstads.setBorder(border5);
    jRBMiss.setEnabled(true);
    jRBMiss.setText("Percentatge de mancants");
    jRBMitj.setText("Mitjana");
    jRBMax.setText("Màxim");
    jRBMin.setText("Mínim");
    jRBQDesv.setText("Quasi-desviació típica");
    jRBQVar.setText("Quasi-variància");
    jRBDesv.setText("Desviació típica ");
    jRBVar.setText("Variància");
    jRBCoef.setText("Coeficient de variació");
    jRBAmp.setText("Amplitud");
    jRBDistInterQ.setText("Distància interquartíl·lica");
    jRBQ1.setText("Q1");
    jRBMe.setText("Mediana");
    jPanBasics.setBorder(titledBorder1);
    jPanBasics.setLayout(gridLayout1);
    jPanRobust.setBorder(titledBorder2);
    jPanRobust.setLayout(gridLayout2);
    jPanDisper.setBorder(titledBorder3);
    jPanDisper.setLayout(gridLayout3);
    gridLayout1.setColumns(1);
    gridLayout1.setRows(4);
    gridLayout2.setColumns(1);
    gridLayout2.setRows(4);
    gridLayout3.setColumns(1);
    gridLayout3.setRows(6);

    jRBQ3.setText("Q3");
    getContentPane().add(panel1, BorderLayout.CENTER);
    this.getContentPane().add(jPanelBotones,  BorderLayout.SOUTH);
    jPanelBotones.add(jBttnDefecte, null);
    jPanelBotones.add(jPanTancar, null);
    jPanTancar.add(jBttnOK, null);
    jPanTancar.add(jBttnCancel, null);

    panel1.add(jPanEstads, BorderLayout.SOUTH);
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
    jPanBasics.add(jRBMiss, null);
    jPanBasics.add(jRBMin, null);
    jPanBasics.add(jRBMax, null);
    jPanBasics.add(jRBMitj, null);
    jPanDisper.add(jRBAmp, null);
    jPanDisper.add(jRBVar, null);
    jPanDisper.add(jRBDesv, null);
    jPanDisper.add(jRBQVar, null);
    jPanDisper.add(jRBQDesv, null);
    jPanDisper.add(jRBCoef, null);
    jPanRobust.add(jRBMe, null);
    jPanRobust.add(jRBQ1, null);
    jPanRobust.add(jRBQ3, null);
    jPanRobust.add(jRBDistInterQ, null);
    jPanTancar.add(jBttnOK, null);
    jPanTancar.add(jBttnCancel, null);
    BGOpcio.add(jRBMiss);
    BGOpcio.add(jRBMin);
    BGOpcio.add(jRBMax);
    BGOpcio.add(jRBMitj);
    BGOpcio.add(jRBAmp);
    BGOpcio.add(jRBVar);
    BGOpcio.add(jRBDesv);
    BGOpcio.add(jRBQVar);
    BGOpcio.add(jRBQDesv);
    BGOpcio.add(jRBCoef);
    BGOpcio.add(jRBMe);
    BGOpcio.add(jRBQ1);
    BGOpcio.add(jRBQ3);
    BGOpcio.add(jRBDistInterQ);
    // marquem les opcions que calgui
    posarOpcions();
  }

  private void posarOpcions() {
    Hashtable taula;
    String str;

    taula = opc.obtenirOpcionsEstad(Opcions.TAULA_CCN);
    str = (String) taula.get("nmods");
    if (str != null) {
      jTFMaxNum.setText(str);
    }

    jRBMiss.setSelected( ( (Boolean) taula.get("miss")).booleanValue());
    jRBMin.setSelected( ( (Boolean) taula.get("min")).booleanValue());
    jRBMax.setSelected( ( (Boolean) taula.get("max")).booleanValue());
    jRBMitj.setSelected( ( (Boolean) taula.get("mitj")).booleanValue());
    jRBMe.setSelected( ( (Boolean) taula.get("me")).booleanValue());
    jRBQ1.setSelected( ( (Boolean) taula.get("q1")).booleanValue());
    jRBQ3.setSelected( ( (Boolean) taula.get("q3")).booleanValue());
    jRBDistInterQ.setSelected( ( (Boolean) taula.get("dist_iq")).booleanValue());
    jRBAmp.setSelected( ( (Boolean) taula.get("amp")).booleanValue());
    jRBVar.setSelected( ( (Boolean) taula.get("var")).booleanValue());
    jRBDesv.setSelected( ( (Boolean) taula.get("desv")).booleanValue());
    jRBQVar.setSelected( ( (Boolean) taula.get("qvar")).booleanValue());
    jRBQDesv.setSelected( ( (Boolean) taula.get("qdesv")).booleanValue());
    jRBCoef.setSelected( ( (Boolean) taula.get("coef")).booleanValue());

  }

  void jBttnCancel_actionPerformed(ActionEvent e) {
    dispose();
  }

  void jBttnOK_actionPerformed(ActionEvent e) {
    Hashtable taula = new Hashtable();

    taula.put("nmods", jTFMaxNum.getText());

    taula.put("miss", new Boolean(jRBMiss.isSelected()));
    taula.put("min", new Boolean(jRBMin.isSelected()));
    taula.put("max", new Boolean(jRBMax.isSelected()));
    taula.put("mitj", new Boolean(jRBMitj.isSelected()));
    taula.put("me", new Boolean(jRBMe.isSelected()));
    taula.put("q1", new Boolean(jRBQ1.isSelected()));
    taula.put("q3", new Boolean(jRBQ3.isSelected()));
    taula.put("dist_iq", new Boolean(jRBDistInterQ.isSelected()));
    taula.put("amp", new Boolean(jRBAmp.isSelected()));
    taula.put("var", new Boolean(jRBVar.isSelected()));
    taula.put("desv", new Boolean(jRBDesv.isSelected()));
    taula.put("qvar", new Boolean(jRBQVar.isSelected()));
    taula.put("qdesv", new Boolean(jRBQDesv.isSelected()));
    taula.put("coef", new Boolean(jRBCoef.isSelected()));

    opc.afegirOpcions(Opcions.TAULA_CCN, taula);

    dispose();
  }

  void jBttnDefecte_actionPerformed(ActionEvent e) {
    opc.posarPerDefecte(Opcions.TAULA_CCN);
    // s'actualitzen les opcions mostrades
    posarOpcions();

  }

}
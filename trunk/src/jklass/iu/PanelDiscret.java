package jklass.iu;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.lang.InterruptedException;
import java.util.logging.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.ImageIcon;

import jklass.nucli.GestorKlass;
import jklass.util.Opcions;
import jklass.util.SO;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

public class PanelDiscret extends JPanel {
  private static Logger logger=Logger.getLogger(PanelDiscret.class.getName());
  BorderLayout borderLayout1 = new BorderLayout();
  FrPrincipal frPare;
  GestorKlass gestor;
  TitledBorder titledBorder1;
  JPanel jPanelBotones = new JPanel();
  Border border4;
  TitledBorder titledBorder3;
  Border border5;
  TitledBorder titledBorder4;
  Border border6;
  TitledBorder titledBorder5;
  DefaultListModel listModelVN = new DefaultListModel();
  JList jListVarsN = new JList(listModelVN);
  DefaultListModel listModelSN = new DefaultListModel();
  JList jListSelcN = new JList(listModelSN);
  Border border7;
  TitledBorder titledBorder6;
  DefaultListModel listModelVC= new DefaultListModel();
  JList jListVarsC = new JList(listModelVC);
  DefaultListModel listModelSC= new DefaultListModel();
  JList jListSelcC = new JList(listModelSC);
  FlowLayout flowLayout2 = new FlowLayout();
  JButton jBttnDefecte = new JButton();
  JButton jBttnCancel = new JButton();
  JPanel jPanTancar = new JPanel();
  JButton jBttnOK = new JButton();
  FlowLayout flowLayout3 = new FlowLayout();
  String nomFitxer = null;
  Border border8;
  TitledBorder titledBorder7;
  Border border9;
  TitledBorder titledBorder8;
  JPanel jPanVars = new JPanel();
  Border border10;
  DefaultListModel listModelSCl= new DefaultListModel();
  JList jListSelcCl = new JList(listModelSCl);
  Border border11;
  TitledBorder titledBorder10;
  JCheckBox jCBFitxers = new JCheckBox();
  JPanel jPanelCalculs = new JPanel();
  FlowLayout flowLayout1 = new FlowLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  FlowLayout flowLayout4 = new FlowLayout();
  JPanel jPanelSelecNum = new JPanel();
  JScrollPane jSPSelecC = new JScrollPane(jListSelcC);
  JPanel jPanelSelecCat = new JPanel();
  JPanel jPanSelec = new JPanel();
  JScrollPane jSPVarsN = new JScrollPane(jListVarsN);
  BorderLayout borderLayout5 = new BorderLayout();
  JPanel jPanLlistes = new JPanel();
  JScrollPane jSPVarsC = new JScrollPane(jListVarsC);
  JScrollPane jSPSelecN = new JScrollPane(jListSelcN);
  BorderLayout borderLayout4 = new BorderLayout();
  JButton jBttnAfegirN = new JButton();
  JButton jBttnTreureN = new JButton();
  JPanel jPanelBtnsN = new JPanel();
  JButton jBttnAfegirC = new JButton();
  JButton jBttnTreureC = new JButton();
  JPanel jPanelBtnsC = new JPanel();
  BorderLayout borderLayout6 = new BorderLayout();
  FlowLayout flowLayout5 = new FlowLayout();

  public PanelDiscret(FrPrincipal fr,GestorKlass gk) {
    String[][] llProps;
    int lon;

    frPare = fr;
    gestor = gk;
    nomFitxer = frPare.obtenirNomDades();
    llProps = gestor.obtenirLlistaIDsProps();
    lon = llProps[0].length;
    for (int i = 0; i < lon; i++) {
      listModelVN.insertElementAt(llProps[0][i], i);
    }
    lon = llProps[1].length;
    for (int i = 0; i < lon; i++) {
      listModelVC.insertElementAt(llProps[1][i], i);
    }
    lon = llProps[2].length;
    for (int i = 0; i < lon; i++) {
      listModelVC.insertElementAt(llProps[2][i], i);
    }
    lon = llProps[3].length;
    for (int i = 0; i < lon; i++) {
      listModelVC.insertElementAt(llProps[3][i], i);
    }

    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    border8 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158));
    titledBorder7 = new TitledBorder(border8,"Vars. Categ.");
    border9 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158));
    titledBorder8 = new TitledBorder(border9,"Vars. categ. selecc.");
    border10 = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
    titledBorder10 = new TitledBorder(border11,"Vars. de classe");
    frPare.setSize(650,550);
    titledBorder1 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158)),"Discretització 'boxplot-based'");
    border4 = BorderFactory.createEmptyBorder();
    titledBorder3 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158)),"Vars. numèriques");
    border5 = BorderFactory.createEmptyBorder();
    titledBorder4 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158)),"Vars. categòriques");
    border6 = BorderFactory.createLineBorder(Color.white,1);
    titledBorder5 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158)),"Vars. Num.");
    border7 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158));
    titledBorder6 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158)),"Vars. num. selecc.");
    this.setLayout(borderLayout1);
    jListVarsN.setBorder(null);
    jListVarsC.setBorder(null);
    this.setBorder(titledBorder1);
    this.setDebugGraphicsOptions(0);
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    jBttnDefecte.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnDefecte_actionPerformed(e);
      }
    });
    jBttnDefecte.setText("Per defecte");
    jBttnDefecte.setHorizontalAlignment(SwingConstants.LEFT);
    jBttnCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnCancel_actionPerformed(e);
      }
    });
    jBttnCancel.setText("Cancel·la");
    jPanTancar.setLayout(flowLayout2);
    jBttnOK.setText("D'acord");
    jBttnOK.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnOK_actionPerformed(e);
      }
    });
    jPanelBotones.setLayout(flowLayout3);
    flowLayout3.setHgap(75);
    jCBFitxers.setEnabled(false);
    jCBFitxers.setActionCommand("");
    jCBFitxers.setSelected(true);
    jCBFitxers.setText("Guardar els fitxers mk i zk");
    jCBFitxers.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jCBFitxers_itemStateChanged(e);
      }
    });
    jPanelCalculs.setLayout(flowLayout5);
    jPanelSelecNum.setLayout(flowLayout1);
    jSPSelecC.setBorder(titledBorder8);
    jSPSelecC.setPreferredSize(new Dimension(115, 153));
    jSPSelecC.setAutoscrolls(true);
    jPanelSelecCat.setLayout(flowLayout4);
    jPanSelec.setLayout(borderLayout2);
    jPanSelec.setBorder(null);
    jSPVarsN.getViewport().setBackground(Color.white);
    jSPVarsN.setAutoscrolls(true);
    jSPVarsN.setBorder(titledBorder5);
    jSPVarsN.setPreferredSize(new Dimension(115, 153));
    borderLayout5.setVgap(6);
    jPanLlistes.setLayout(borderLayout5);
    jPanLlistes.setBorder(null);
    jSPVarsC.setBorder(titledBorder7);
    jSPVarsC.setOpaque(true);
    jSPVarsC.setPreferredSize(new Dimension(115, 153));
    jSPVarsC.setRequestFocusEnabled(true);
    jSPVarsC.setAutoscrolls(true);
    jSPSelecN.setAutoscrolls(true);
    jSPSelecN.setBorder(titledBorder6);
    jSPSelecN.setPreferredSize(new Dimension(115, 153));
    jBttnAfegirN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
    jBttnAfegirN.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnAfegirN_actionPerformed(e);
      }
    });
    jBttnTreureN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
    jBttnTreureN.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnTreureN_actionPerformed(e);
      }
    });
    jPanelBtnsN.setLayout(borderLayout4);
    jBttnAfegirC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
    jBttnAfegirC.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnAfegirC_actionPerformed(e);
      }
    });
    jBttnTreureC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
    jBttnTreureC.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnTreureC_actionPerformed(e);
      }
    });
    jPanelBtnsC.setLayout(borderLayout6);
    jPanelCalculs.setBorder(null);
    flowLayout5.setVgap(20);
    this.add(jPanelBotones,  BorderLayout.SOUTH);
    jPanTancar.add(jBttnOK, null);
    jPanTancar.add(jBttnCancel, null);
    this.add(jPanVars,  BorderLayout.NORTH);
    jPanelBotones.add(jBttnDefecte, null);
    jPanelBotones.add(jPanTancar, null);
    this.add(jPanVars, BorderLayout.NORTH);
    this.add(jPanelCalculs, BorderLayout.CENTER);
    jPanelCalculs.add(jCBFitxers, null);
    jPanelBtnsN.add(jBttnAfegirN, BorderLayout.NORTH);
    jPanelBtnsN.add(jBttnTreureN, BorderLayout.SOUTH);
    jPanelSelecNum.add(jPanelBtnsN, null);
    jPanelSelecNum.add(jSPSelecN, null);
    jPanSelec.add(jPanelSelecCat, BorderLayout.CENTER);
    jPanSelec.add(jPanelSelecNum, BorderLayout.SOUTH);
    jPanelBtnsC.add(jBttnAfegirC, BorderLayout.NORTH);
    jPanelBtnsC.add(jBttnTreureC, BorderLayout.SOUTH);
    jPanelSelecCat.add(jPanelBtnsC, null);
    jPanelSelecCat.add(jSPSelecC, null);
    jPanLlistes.add(jSPVarsN, BorderLayout.SOUTH);
    jPanLlistes.add(jSPVarsC, BorderLayout.NORTH);
    jPanVars.add(jPanLlistes, null);
    jPanVars.add(jPanSelec, null);
    //frPare.actualitzarBarraEstat("Altura " + frPare.getHeight()+ " Anchura " + frPare.getWidth())
  }

  void jBttnOK_actionPerformed(ActionEvent e) {
    boolean ok, fitxers = true;
    /* llistaVars[0][] - Conté la llista de vars numeriques X de NC
     * llistaVars[1][] - Conté la llista de vars categoriques Y de NC */
    String[][] llistaVars = new String[2][];
    // llistat dels estadistics que s'han de calcular
    int nvarsN, nvarsC, nvarsCl, i, err;
    String cmd;
    Process proc;

    nvarsN = listModelSN.getSize();
    nvarsC = listModelSC.getSize();
    if ( (nvarsC == 0) || (nvarsN == 0)) {
      frPare.actualitzarBarraEstat(
          "S'ha de seleccionar alguna variable numèrica i categòrica", true);
    }
    else {
      llistaVars[0] = new String[nvarsN];
      llistaVars[1] = new String[nvarsC];
      for (i = 0; i < nvarsN; i++) {
        llistaVars[0][i] = (String) listModelSN.get(i);
      }
      for (i = 0; i < nvarsC; i++) {
        llistaVars[1][i] = (String) listModelSC.get(i);
      }
      ok = gestor.discretitzar(llistaVars, fitxers);

      if (ok) {
        String nom = new File(nomFitxer).getName();
        frPare.actualitzarBarraEstat(
            "S'ha realitzat la discretització correctament i s'han generat els fitxers .mk i .zk corresponents per cada parell de variables.", false);
      }
      else {
        frPare.actualitzarBarraEstat(
            "No ha estat possible realitzar la discretització.", true);
      }
      frPare.remove(this);
      frPare.validate();
      frPare.repaint();
    }
  }

  void jBttnDefecte_actionPerformed(ActionEvent e) {
    // Es demana confirmació abans de posar opcions per defecte per tot
    int n = JOptionPane.showConfirmDialog(
                            this, "Aquesta opció selecciona les opcions per defecte. Segur que vols continuar?",
                            "Assignació d'opcions per defecte",
                            JOptionPane.YES_NO_OPTION);

    if (n == JOptionPane.YES_OPTION) {
      jCBFitxers.setSelected(true);
    }
  }

  void jBttnCancel_actionPerformed(ActionEvent e) {
    frPare.remove(this);
    frPare.validate();
    frPare.repaint();
  }


  void jBttnAfegirN_actionPerformed(ActionEvent e) {
    Object[] vars = jListVarsN.getSelectedValues();
    boolean hay_mas_vars = true;
    int n = 0;
	 System.out.println( "a ver si es este?");
    do {
      try {
        listModelSN.addElement(vars[n]);
        listModelVN.removeElement(vars[n]);
        n++;
      }
      catch (Exception exc) { hay_mas_vars = false; }

    } while (hay_mas_vars);

  }

  void jBttnTreureN_actionPerformed(ActionEvent e) {
    Object[] vars = jListSelcN.getSelectedValues();
    boolean hay_mas_vars = true;
    int n = 0;
    do {
      try {
        listModelVN.addElement(vars[n]);
        listModelSN.removeElement(vars[n]);
        n++;
      }
      catch (Exception exc) { hay_mas_vars = false; }
    } while (hay_mas_vars);
  }

  void jBttnAfegirC_actionPerformed(ActionEvent e) {
    Object[] vars = jListVarsC.getSelectedValues();
    boolean hay_mas_vars = true;
    int n = 0;
    do {
      try {
        listModelSC.addElement(vars[n]);
        listModelVC.removeElement(vars[n]);
        n++;
      }
      catch (Exception exc) { hay_mas_vars = false; }
    } while (hay_mas_vars);
  }

  void jBttnTreureC_actionPerformed(ActionEvent e) {
    Object[] vars = jListSelcC.getSelectedValues();
    boolean hay_mas_vars = true;
    int n = 0;
    do {
      try {
        listModelVC.addElement(vars[n]);
        listModelSC.removeElement(vars[n]);
        n++;
      }
      catch (Exception exc) { hay_mas_vars = false; }
    } while (hay_mas_vars);
  }

  void jCBFitxers_itemStateChanged(ItemEvent e) {
    boolean selec;

    selec = jCBFitxers.isSelected();
  }

}

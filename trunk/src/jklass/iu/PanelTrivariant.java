package jklass.iu;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.lang.InterruptedException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.util.logging.*;
import java.util.Vector;
import java.util.ArrayList;

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

public class PanelTrivariant extends JPanel {
  private static Logger logger=Logger.getLogger(PanelTrivariant.class.getName());
  Opcions opcTriv = new Opcions(Opcions.TRIVARIANT);
  FrPrincipal frPare;
  GestorKlass gestor;
  TitledBorder titledBorder1;
  JPanel jPanelVars = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanelBotones = new JPanel();
  Border border1;
  TitledBorder titledBorder2;
  Border border2;
  FlowLayout flowLayout2 = new FlowLayout();
  JButton jBttnDefecte = new JButton();
  JButton jBttnCancel = new JButton();
  JPanel jPanTancar = new JPanel();
  JButton jBttnOK = new JButton();
  FlowLayout flowLayout1 = new FlowLayout();
  DefaultListModel listModelVN = new DefaultListModel();
  JList jListVarsN = new JList(listModelVN);
  DefaultListModel listModelVC = new DefaultListModel();
  JList jListVarsC = new JList(listModelVC);
  DefaultListModel listModelLin = new DefaultListModel();
  Border border3;
  TitledBorder titledBorder3;
  int files = 25, columnes = 3;
  Object[][] varsNNC =  new Object [files][columnes];
  Object[][] varsCCN = new Object[files][columnes];
  String[] eixosNNC = { " X (Num.)", " Y (Num.) " , " Z (Categ.) "};
  String[] eixosCCN = {" X (Categ.)", " Y (Categ.) ", " X (Num.) "};
  JTable jTblVarNNC = new JTable(varsNNC, eixosNNC);
  JTable jTblVarCCN = new JTable(varsCCN, eixosCCN);
  JList rowHeaderNNC = new JList(listModelLin);
  JList rowHeaderCCN = new JList(listModelLin);
  JPanel jPanLlistes = new JPanel();
  JScrollPane jSPVarsN = new JScrollPane(jListVarsN);
  JScrollPane jSPVarsC = new JScrollPane(jListVarsC);
  GridLayout gridLayout1 = new GridLayout();
  Border border4;
  TitledBorder titledBorder4;
  JPanel jPanSelec = new JPanel();
  JPanel jPanTitolSelNNC = new JPanel();
  JLabel jLblNNC = new JLabel();
  JSeparator jSepNNC1 = new JSeparator(SwingConstants.HORIZONTAL);
  JSeparator jSepNNC2 = new JSeparator(SwingConstants.HORIZONTAL);
  JScrollPane jSPTaulaVarNNC = new JScrollPane(jTblVarNNC);
  FlowLayout flowLayout3 = new FlowLayout();
  JButton jBOpcLPlot = new JButton();
  JPanel jPanSelNNC = new JPanel();
  JCheckBox jCBLPlot = new JCheckBox();
  JButton jBttnTreureNNC = new JButton();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  BorderLayout borderLayout6 = new BorderLayout();
  JPanel jPanEstadsNNC = new JPanel();
  JButton jBttnAfegirNNC = new JButton();
  JPanel jPanelBtnsNNC = new JPanel();
  JPanel jPanTitolSelCCN = new JPanel();
  JLabel jLblCCN = new JLabel();
  JSeparator jSepCCN1 = new JSeparator(SwingConstants.HORIZONTAL);
  JSeparator jSepCCN2 = new JSeparator(SwingConstants.HORIZONTAL);
  JButton jBttnTreureCCN = new JButton();
  JPanel jPanSelCCN = new JPanel();
  JButton jBttnAfegirCCN = new JButton();
  JPanel jPanelBtnsCCN = new JPanel();
  JScrollPane jSPTaulaVarCCN = new JScrollPane(jTblVarCCN);
  JPanel jPanEstadsCCN = new JPanel();
  JCheckBox jCBTauCCN = new JCheckBox();
  JButton jBOpcTauCCN = new JButton();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  GridBagLayout gridBagLayout4 = new GridBagLayout();
  String nomFitxer = null;

  public PanelTrivariant(FrPrincipal fr,GestorKlass gk) {
    String[][] llProps;
    int lon;

    frPare = fr;
    gestor = gk;
    nomFitxer = frPare.obtenirNomDades();
	  ArrayList activias=new ArrayList();
	  activias= gestor.matriusCarregades[gestor.obteniractual()].obtenirActivas();
 if (gestor.consultasel()){
 llProps = gestor.obtenirLlistaIDsPropsActivas(activias);}
 else
     {llProps = gestor.obtenirLlistaIDsProps();}

	 
    //llProps = gestor.obtenirLlistaIDsProps();
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
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    frPare.setSize(730,630);
    for (int i = 0; i < files; i++) {
      listModelLin.insertElementAt(new Integer(i+1), i);
    }
    titledBorder1 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158)),"Anàlisi descriptiva trivariant");
    border1 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(178, 178, 178));
    titledBorder2 = new TitledBorder(border1,"Llista de variables");
    border2 = BorderFactory.createCompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(178, 178, 178)),"Llista de variables"),BorderFactory.createEmptyBorder(2,2,2,2));
    border3 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158));
    titledBorder3 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158)),"Vars. Num.");
    border4 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158));
    titledBorder4 = new TitledBorder(border4,"Vars. Categ.");
    this.setBorder(titledBorder1);
    this.setPreferredSize(new Dimension(624, 487));
    this.setLayout(borderLayout1);
    jListVarsN.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jListVarsC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    jBttnDefecte.setHorizontalAlignment(SwingConstants.LEFT);
    jBttnDefecte.setText("Per defecte");
    jBttnDefecte.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnDefecte_actionPerformed(e);
      }
    });
    jBttnCancel.setText("Cancel·la");
    jBttnCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnCancel_actionPerformed(e);
      }
    });
    jPanTancar.setLayout(flowLayout2);
    jBttnOK.setText("D'acord");
    jBttnOK.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnOK_actionPerformed(e);
      }
    });
    jPanelBotones.setLayout(flowLayout1);
    flowLayout1.setHgap(75);
    jSPVarsN.getViewport().setBackground(Color.white);
    jSPVarsN.setAlignmentY((float) 1.5);
    jSPVarsN.setAutoscrolls(true);
    jSPVarsN.setBorder(titledBorder3);
    jSPVarsN.setMinimumSize(new Dimension(37, 75));
    jSPVarsN.setPreferredSize(new Dimension(100, 210));
    jSPVarsC.getViewport().setBackground(Color.white);
    jSPVarsC.setAutoscrolls(true);
    jSPVarsC.setBorder(titledBorder4);
    jSPVarsC.setMinimumSize(new Dimension(37, 75));
    jSPVarsC.setPreferredSize(new Dimension(100, 210));
    jPanLlistes.setLayout(gridLayout1);
    gridLayout1.setColumns(1);
    gridLayout1.setRows(2);
    jPanSelec.setLayout(gridBagLayout3);
    jLblNNC.setFont(new java.awt.Font("Dialog", 2, 11));
    jLblNNC.setText("Parell de variables numèriques vs.categòrica");
    jSepNNC1.setPreferredSize(new Dimension(60, 1));
    jSepNNC2.setOpaque(false);
    jSepNNC2.setPreferredSize(new Dimension(60, 1));
    jSepNNC2.setRequestFocusEnabled(true);
    jLblCCN.setFont(new java.awt.Font("Dialog", 2, 11));
    jLblCCN.setText("Parell de variables categòriques vs. numèrica");
    jSepCCN1.setPreferredSize(new Dimension(60, 1));
    jSepCCN2.setOpaque(false);
    jSepCCN2.setPreferredSize(new Dimension(60, 1));
    jSepCCN2.setRequestFocusEnabled(true);
    jPanTitolSelNNC.setLayout(flowLayout3);
    jPanTitolSelCCN.setLayout(flowLayout3);
    jTblVarNNC.setMinimumSize(new Dimension(45, 420));
    jTblVarNNC.setPreferredSize(new Dimension(225, 420));
    jTblVarNNC.setCellSelectionEnabled(true);

    rowHeaderNNC.setFixedCellHeight(jTblVarNNC.getRowHeight());
    //                        + jTblVarNN.getRowMargin());
    //+ jTblVarNN.getIntercellSpacing().height);
    rowHeaderNNC.setCellRenderer(new RowHeaderRenderer(jTblVarNNC));
    jSPTaulaVarNNC.setRowHeaderView(rowHeaderNNC);
    jSPTaulaVarNNC.setEnabled(true);
    jSPTaulaVarNNC.setMaximumSize(new Dimension(32767, 32767));
    jSPTaulaVarNNC.setPreferredSize(new Dimension(240, 100));
    jSPTaulaVarNNC.setRequestFocusEnabled(true);
    jSPTaulaVarNNC.setToolTipText("");
    jTblVarCCN.setMinimumSize(new Dimension(45, 410));
    jTblVarCCN.setPreferredSize(new Dimension(225, 420));
    jTblVarCCN.setCellSelectionEnabled(true);
    rowHeaderCCN.setFixedCellHeight(jTblVarCCN.getRowHeight());
//                        + jTblVarNN.getRowMargin());
//+ jTblVarNN.getIntercellSpacing().height);
    rowHeaderCCN.setCellRenderer(new RowHeaderRenderer(jTblVarCCN));
    jSPTaulaVarCCN.setRowHeaderView(rowHeaderCCN);
    jSPTaulaVarCCN.setEnabled(true);
    jSPTaulaVarCCN.setMaximumSize(new Dimension(32767, 32767));
    jSPTaulaVarCCN.setPreferredSize(new Dimension(240, 100));
    jSPTaulaVarCCN.setToolTipText("");
    jPanEstadsNNC.setBorder(null);
    jPanEstadsNNC.setMinimumSize(new Dimension(212, 86));
    jPanEstadsNNC.setPreferredSize(new Dimension(210, 86));
    jPanEstadsNNC.setVerifyInputWhenFocusTarget(true);
    jPanEstadsNNC.setLayout(gridBagLayout2);
    jPanEstadsCCN.setBorder(null);
    jPanEstadsCCN.setMinimumSize(new Dimension(212, 86));
    jPanEstadsCCN.setPreferredSize(new Dimension(210, 86));
    jPanEstadsCCN.setVerifyInputWhenFocusTarget(true);
    jPanEstadsCCN.setLayout(gridBagLayout4);
    jCBLPlot.setText("Letterplot");
    jCBLPlot.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jCBLPlot_itemStateChanged(e);
      }
    });
    jBttnTreureNNC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
    jBttnTreureNNC.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnTreureNNC_actionPerformed(e);
      }
    });
    jBttnAfegirNNC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
    jBttnAfegirNNC.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnAfegirNNC_actionPerformed(e);
      }
    });
    jPanelBtnsNNC.setLayout(borderLayout5);
    jBOpcLPlot.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBOpcLPlot_actionPerformed(e);
      }
    });
    jBOpcLPlot.setText("Opcions");
    jBOpcLPlot.setRolloverEnabled(false);
    jBOpcLPlot.setMnemonic('0');
    jBOpcLPlot.setPreferredSize(new Dimension(75, 25));
    jBOpcLPlot.setEnabled(false);

    jPanelVars.setMinimumSize(new Dimension(410, 410));
    jPanelVars.setPreferredSize(new Dimension(512, 415));
    jPanelVars.setRequestFocusEnabled(true);
    jCBTauCCN.setText("Taula");
    jCBTauCCN.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jCBTauCCN_itemStateChanged(e);
      }
    });
    jBttnTreureCCN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
    jBttnTreureCCN.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnTreureCCN_actionPerformed(e);
      }
    });
    jBOpcTauCCN.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBOpcTauCCN_actionPerformed(e);
      }
    });
    jBOpcTauCCN.setText("Opcions");
    jBOpcTauCCN.setRolloverEnabled(false);
    jBOpcTauCCN.setMnemonic('0');
    jBOpcTauCCN.setEnabled(false);
    jBOpcTauCCN.setPreferredSize(new Dimension(75, 25));

    jBttnAfegirCCN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
    jBttnAfegirCCN.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnAfegirCCN_actionPerformed(e);
      }
    });
    jPanelBtnsCCN.setLayout(borderLayout6);

    jPanLlistes.setAlignmentY((float) 1.5);
    this.add(jPanelVars, BorderLayout.CENTER);
    jPanelVars.add(jPanLlistes, null);
    //jSPTaulaVarNN.add(jTblVarNN, null);
    this.add(jPanelBotones,  BorderLayout.SOUTH);
    jPanTancar.add(jBttnOK, null);
    jPanTancar.add(jBttnCancel, null);
    jPanelBotones.add(jBttnDefecte, null);
    jPanelBotones.add(jPanTancar, null);
    jPanLlistes.add(jSPVarsN, null);
    jPanLlistes.add(jSPVarsC, null);
    jPanelVars.add(jPanSelec, null);
    jPanSelec.add(jPanTitolSelNNC,    new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 213, 0));
    jPanSelec.add(jPanSelNNC,       new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
    jPanSelec.add(jPanTitolSelCCN,   new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 0), 213, 0));
    jPanSelec.add(jPanSelCCN,  new GridBagConstraints(0, 4, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
    jPanTitolSelNNC.add(jSepNNC1, null);
    jPanTitolSelNNC.add(jLblNNC, null);
    jPanTitolSelNNC.add(jSepNNC2, null);
    jPanTitolSelCCN.add(jSepCCN1, null);
    jPanTitolSelCCN.add(jLblCCN, null);
    jPanTitolSelCCN.add(jSepCCN2, null);
    jPanelBtnsNNC.add(jBttnAfegirNNC, BorderLayout.NORTH);
    jPanelBtnsNNC.add(jBttnTreureNNC, BorderLayout.SOUTH);
    jPanelBtnsCCN.add(jBttnAfegirCCN, BorderLayout.NORTH);
    jPanelBtnsCCN.add(jBttnTreureCCN, BorderLayout.SOUTH);
    jPanEstadsNNC.add(jBOpcLPlot,  new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanEstadsNNC.add(jCBLPlot,  new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 10, 5), 0, 0));
    jPanEstadsCCN.add(jCBTauCCN,           new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 10, 5), 0, 0));
    jPanEstadsCCN.add(jBOpcTauCCN,  new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanSelNNC.add(jPanelBtnsNNC, null);
    jPanSelNNC.add(jSPTaulaVarNNC, null);
    jPanSelNNC.add(jPanEstadsNNC, null);
    jPanSelCCN.add(jPanelBtnsCCN, null);
    jPanSelCCN.add(jSPTaulaVarCCN, null);
    jPanSelCCN.add(jPanEstadsCCN, null);
  }

  void jBttnOK_actionPerformed(ActionEvent e) {
    boolean ok;
    int j, k, l, nFiles, err;
    // llistaVars[0][] - Conté la llista de vars numeriques X de NNC
    // llistaVars[1][] - Conté la llista de vars numeriques Y de NNC
    // llistaVars[2][] - Conté la llista de vars categoriques Z de NNC
    // llistaVars[3][] - Conté la llista de vars categoriques X de CCN
    // llistaVars[4][] - Conté la llista de vars categoriques Y de CCN
    // llistaVars[5][] - Conté la llista de vars numeriques Z de CCN
    String[][] llistaVars = new String[6][];
    // llistat dels estadistics que s'han de calcular
    // llistaEstads[0] - Conté la llista d'estad. NNC
    // llistaEstads[1] - Conté la llista d'estad. CCN
    Vector[] llistaEstads = new Vector[2];
    String cmd;
    Process proc;

    j = 0;
    k = 0;
    l = 0;
    nFiles = varsNNC.length;
    llistaVars[0] = new String[nFiles];
    llistaVars[1] = new String[nFiles];
    llistaVars[2] = new String[nFiles];
    nFiles = varsCCN.length;
    llistaVars[3] = new String[nFiles];
    llistaVars[4] = new String[nFiles];
    llistaVars[5] = new String[nFiles];
    for (int i=0; i<files; i++) {
      if ( (varsNNC[i][0] != null) && (varsNNC[i][1] != null) && (varsNNC[i][2] != null)) {
        llistaVars[0][j] = varsNNC[i][0].toString(); // Var Num. X
        llistaVars[1][j] = varsNNC[i][1].toString(); // Var Num. Y
        llistaVars[2][j] = varsNNC[i][2].toString(); // Var Categ. Z
        j++;
      }
      if ( (varsCCN[i][0] != null) && (varsCCN[i][1] != null) && (varsCCN[i][2] != null)) {
        llistaVars[3][j] = varsCCN[i][0].toString(); // Var Categ. X
        llistaVars[4][j] = varsCCN[i][1].toString(); // Var Categ. Y
        llistaVars[5][j] = varsCCN[i][2].toString(); // Var Categ. Z
        j++;
      }
    }
    llistaEstads[0] = new Vector(); // Estads per NNC
    llistaEstads[1] = new Vector(); // Estads per CCN
    if (jCBLPlot.isSelected())
      llistaEstads[0].add(new Integer(Opcions.LETTERPLOT));
    if (jCBTauCCN.isSelected())
      llistaEstads[1].add(new Integer(Opcions.TAULA_CCN));

    ok = gestor.ferDescrip(llistaVars, llistaEstads, opcTriv);

    if (ok) {
      String nom = new File(nomFitxer).getName();
      frPare.actualitzarBarraEstat(
          "S'ha realitzat l'anàlisi descriptiva trivariant correctament. (Resultats al fitxer: " +
          nom + "Triv.tex)", false);
      try {
        String pathResult = gestor.obtenirDirResultats();
        File dirResult = new File(pathResult);
        pathResult = pathResult + nom + "Triv";

        cmd = SO.obtenirCmdCompilaLtx( pathResult + ".tex");
        if (cmd == null) {
          /** @todo També es podria obrir una finestra */
          frPare.actualitzarBarraEstat(
              "No es pot compilar ni visualitzar automàticament el fitxer Latex generat per no haver-se reconegut el sistema operatiu.", true);
        }
        else {
          logger.finer("Comanda exec: " + cmd);
          proc = Runtime.getRuntime().exec(cmd, null, dirResult);
          try {
            // error?
            StreamGobbler errorGobbler = new
                StreamGobbler(proc.getErrorStream(), "ERROR (exec)");

            // output?
            StreamGobbler outputGobbler = new
                StreamGobbler(proc.getInputStream(), "OUTPUT (exec)");

            errorGobbler.start();
            outputGobbler.start();

            err = proc.waitFor();
            if (err == 0) {
              cmd = SO.obtenirCmdVisorLtx( pathResult + ".dvi");
              Runtime.getRuntime().exec(cmd, null, dirResult);
            }
            else {
              frPare.actualitzarBarraEstat(
                  "No s'ha pogut compilar correctament el fitxer Latex generat.", true);
            }
          }
          catch (InterruptedException exc) {
            frPare.actualitzarBarraEstat("S'ha interrumput el procés de compilació del fitxer Latex ( InterruptedException " +
                                         exc.getMessage() + " )", true);
          }
        }
      }
      catch (IOException exc) {
        frPare.actualitzarBarraEstat(
            "Error al generar i visualitzar el latex ( IOException " +
            exc.getMessage() + " )", true);
      }
    }
    else {
      frPare.actualitzarBarraEstat(
          "No ha estat possible realitzar l'anàlisi descriptiva trivariant.", true);
    }

    frPare.remove(this);
    frPare.validate();
    frPare.repaint();
  }

  void jBttnDefecte_actionPerformed(ActionEvent e) {
    // Es demana confirmació abans de posar opcions per defecte per tot
    int n = JOptionPane.showConfirmDialog(
                            this, "Aquesta opció selecciona tots els estadístics posant a TOTS les seves opcions per defecte. Segur que vols continuar?",
                            "Assignació d'opcions per defecte",
                            JOptionPane.YES_NO_OPTION);

    if (n == JOptionPane.YES_OPTION) {
      jCBLPlot.setSelected(true);
      opcTriv.posarPerDefecte(Opcions.LETTERPLOT);
      jCBTauCCN.setSelected(true);
      opcTriv.posarPerDefecte(Opcions.TAULA_CCN);
    }
  }

  void jBttnCancel_actionPerformed(ActionEvent e) {
    frPare.remove(this);
    frPare.validate();
    frPare.repaint();
  }

  void jBOpcLPlot_actionPerformed(ActionEvent e) {
    DlgOpcLetterplot dlg = new DlgOpcLetterplot(frPare, "Opcions per al letterplot",true, opcTriv);
    dlg.setLocationRelativeTo(this);
    dlg.show();
  }

  void jBttnTreureNNC_actionPerformed(ActionEvent e) {
    int fil = jTblVarNNC.getSelectedRow();
    int col = jTblVarNNC.getSelectedColumn();
    varsNNC[fil][col] = null;
    jTblVarNNC.repaint();
  }

  void jBttnAfegirNNC_actionPerformed(ActionEvent e) {
    Object vars = jListVarsN.getSelectedValue();
    int fil = jTblVarNNC.getSelectedRow();
    int col = jTblVarNNC.getSelectedColumn();
    if ((fil == -1) || (col == -1)) { fil = 0; col = 0; }
    if (col == 2) {
      varsNNC[fil][col] = jListVarsC.getSelectedValue();
    }
    else {
      varsNNC[fil][col] = vars;
    }
    jTblVarNNC.repaint();
  }

  void jCBLPlot_itemStateChanged(ItemEvent e) {
    boolean selec;

    selec = jCBLPlot.isSelected();
    if (selec){
      opcTriv.posarPerDefecte(Opcions.LETTERPLOT);
    } else {
      opcTriv.eliminarOpcions(Opcions.LETTERPLOT);
    }
    jBOpcLPlot.setEnabled(selec);
  }

  void jBttnAfegirCCN_actionPerformed(ActionEvent e) {
    Object vars = jListVarsC.getSelectedValue();
    int fil = jTblVarCCN.getSelectedRow();
    int col = jTblVarCCN.getSelectedColumn();
    if ((fil == -1) || (col == -1)) { fil = 0; col = 0; }
    if (col == 2) {
      varsCCN[fil][col] = jListVarsN.getSelectedValue();
    } else {
      varsCCN[fil][col] = vars;
    }
    jTblVarCCN.repaint();
  }

  void jBttnTreureCCN_actionPerformed(ActionEvent e) {
    int fil = jTblVarCCN.getSelectedRow();
    int col = jTblVarCCN.getSelectedColumn();
    varsCCN[fil][col] = null;
    jTblVarCCN.repaint();
  }

  void jCBTauCCN_itemStateChanged(ItemEvent e) {
    boolean selec;

    selec = jCBTauCCN.isSelected();
    if (selec){
      opcTriv.posarPerDefecte(Opcions.TAULA_CCN);
    } else {
      opcTriv.eliminarOpcions(Opcions.TAULA_CCN);
    }
    jBOpcTauCCN.setEnabled(selec);
  }

  void jBOpcTauCCN_actionPerformed(ActionEvent e) {
    DlgOpcTaulaCCN dlg = new DlgOpcTaulaCCN(frPare, "Opcions per a la taula cat x cat x num",true, opcTriv);
    dlg.setLocationRelativeTo(this);
    dlg.show();
  }

}
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

public class PanelBivariant extends JPanel {
  private static Logger logger=Logger.getLogger(PanelBivariant.class.getName());
  Opcions opcBiv = new Opcions(Opcions.BIVARIANT);
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
  int files = 25, columnes = 2;
  Object[][] varsNN =  new Object [files][columnes];
  Object[][] varsNC = new Object[files][columnes];
  Object[][] varsCC = new Object[files][columnes];
  String[] eixosNN = { " X (Num.)", " Y (Num.) " };
  String[] eixosNC = {" X (Num.)", " Y (Categ.) "};
  String[] eixosCC = {" X (Categ.)", " Y (Categ.) "};
  JTable jTblVarNN = new JTable(varsNN, eixosNN);
  JTable jTblVarNC = new JTable(varsNC, eixosNC);
  JTable jTblVarCC = new JTable(varsCC, eixosCC);
  JList rowHeaderNN = new JList(listModelLin);
  JList rowHeaderNC = new JList(listModelLin);
  JList rowHeaderCC = new JList(listModelLin);
  JPanel jPanLlistes = new JPanel();
  JScrollPane jSPVarsN = new JScrollPane(jListVarsN);
  JScrollPane jSPVarsC = new JScrollPane(jListVarsC);
  GridLayout gridLayout1 = new GridLayout();
  Border border4;
  TitledBorder titledBorder4;
  JPanel jPanSelec = new JPanel();
  JPanel jPanTitolSelNN = new JPanel();
  JLabel jLblNN = new JLabel();
  JSeparator jSepNN1 = new JSeparator(SwingConstants.HORIZONTAL);
  JSeparator jSepNN2 = new JSeparator(SwingConstants.HORIZONTAL);
  JScrollPane jSPTaulaVarNN = new JScrollPane(jTblVarNN);
  JButton jBttnTreureNC = new JButton();
  JPanel jPanSelNC = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  JButton jBttnAfegirNC = new JButton();
  JPanel jPanelBtnsNC = new JPanel();
  JScrollPane jSPTaulaVarNC = new JScrollPane(jTblVarNC);
  FlowLayout flowLayout3 = new FlowLayout();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JCheckBox jCBHistoMult = new JCheckBox();
  JPanel jPanEstadsNC = new JPanel();
  JButton jBOpcBoxpMult = new JButton();
  JButton jBOpcHistoMult = new JButton();
  JCheckBox jCBBoxpMult = new JCheckBox();
  JButton jBOpcPlot = new JButton();
  JPanel jPanSelNN = new JPanel();
  JCheckBox jCBPlot = new JCheckBox();
  JCheckBox jCBCorr = new JCheckBox();
  JButton jBttnTreureNN = new JButton();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  BorderLayout borderLayout6 = new BorderLayout();
  JPanel jPanEstadsNN = new JPanel();
  JButton jBttnAfegirNN = new JButton();
  JPanel jPanelBtnsNN = new JPanel();
  JSeparator jSepNC1 = new JSeparator(SwingConstants.HORIZONTAL);
  JLabel jLblNC = new JLabel();
  JSeparator jSepNC2 = new JSeparator(SwingConstants.HORIZONTAL);
  FlowLayout flowLayout4 = new FlowLayout();
  JPanel jPanTitolSelNC = new JPanel();
  JPanel jPanTitolSelCC = new JPanel();
  JLabel jLblCC = new JLabel();
  JSeparator jSepCC1 = new JSeparator(SwingConstants.HORIZONTAL);
  JSeparator jSepCC2 = new JSeparator(SwingConstants.HORIZONTAL);
  JButton jBttnTreureCC = new JButton();
  JPanel jPanSelCC = new JPanel();
  JButton jBttnAfegirCC = new JButton();
  JPanel jPanelBtnsCC = new JPanel();
  JScrollPane jSPTaulaVarCC = new JScrollPane(jTblVarCC);
  JPanel jPanEstadsCC = new JPanel();
  JCheckBox jCBTauCont = new JCheckBox();
  JCheckBox jCBDiagBarM = new JCheckBox();
  JButton jBOpcTauCont = new JButton();
  JButton jBOpcDiagBarM = new JButton();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  GridBagLayout gridBagLayout4 = new GridBagLayout();
  String nomFitxer = null;
  JCheckBox jCBDescr = new JCheckBox();
  JButton jBOpcDescr = new JButton();

  public PanelBivariant(FrPrincipal fr,GestorKlass gk) {
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

	 
   // llProps = gestor.obtenirLlistaIDsProps();
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
    frPare.setSize(677,650);
    for (int i = 0; i < files; i++) {
      listModelLin.insertElementAt(new Integer(i+1), i);
    }
    titledBorder1 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158)),"Anàlisi descriptiva bivariant");
    border1 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(178, 178, 178));
    titledBorder2 = new TitledBorder(border1,"Llista de variables");
    border2 = BorderFactory.createCompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(178, 178, 178)),"Llista de variables"),BorderFactory.createEmptyBorder(2,2,2,2));
    border3 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158));
    titledBorder3 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158)),"Vars. Num.");
    border4 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158));
    titledBorder4 = new TitledBorder(border4,"Vars. Categ.");
    this.setBorder(titledBorder1);
    this.setLayout(borderLayout1);
    
    // JORDI >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    // Se obtienen las dimensiones en pixels de la pantalla.         
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    
    // Se obtienen las dimensiones en pixels de la ventana.
    Dimension ventana = frPare.getSize();
    
    // Un calculo para situar la ventana en el centro de la pantalla.
    frPare.setLocation((pantalla.width - ventana.width) / 2,(pantalla.height - ventana.height) / 2);
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
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
    jLblNN.setFont(new java.awt.Font("Dialog", 2, 11));
    jLblNN.setText("Parell de variables numèriques");
    jSepNN1.setPreferredSize(new Dimension(60, 1));
    jSepNN2.setOpaque(false);
    jSepNN2.setPreferredSize(new Dimension(60, 1));
    jSepNN2.setRequestFocusEnabled(true);
    jLblNC.setFont(new java.awt.Font("Dialog", 2, 11));
    jLblNC.setText("Cas mixte (numèrica vs. categòrica)");
    jSepNC1.setPreferredSize(new Dimension(60, 1));
    jSepNC2.setOpaque(false);
    jSepNC2.setPreferredSize(new Dimension(60, 1));
    jSepNC2.setRequestFocusEnabled(true);
    jLblCC.setFont(new java.awt.Font("Dialog", 2, 11));
    jLblCC.setText("Parell de variables categòriques");
    jSepCC1.setPreferredSize(new Dimension(60, 1));
    jSepCC2.setOpaque(false);
    jSepCC2.setPreferredSize(new Dimension(60, 1));
    jSepCC2.setRequestFocusEnabled(true);
    jPanTitolSelNN.setLayout(flowLayout3);
    jPanTitolSelNC.setLayout(flowLayout4);
    jPanTitolSelNC.setPreferredSize(new Dimension(254, 25));
    jPanTitolSelCC.setLayout(flowLayout3);
    jTblVarNN.setCellSelectionEnabled(true);

    rowHeaderNN.setFixedCellHeight(jTblVarNN.getRowHeight());
    //                        + jTblVarNN.getRowMargin());
    //+ jTblVarNN.getIntercellSpacing().height);
    rowHeaderNN.setCellRenderer(new RowHeaderRenderer(jTblVarNN));
    jSPTaulaVarNN.setRowHeaderView(rowHeaderNN);
    jSPTaulaVarNN.setEnabled(true);
    jSPTaulaVarNN.setMaximumSize(new Dimension(32767, 32767));
    jSPTaulaVarNN.setPreferredSize(new Dimension(200, 100));
    jSPTaulaVarNN.setToolTipText("");
    rowHeaderNC.setFixedCellHeight(jTblVarNC.getRowHeight());
//                        + jTblVarNC.getRowMargin());
//+ jTblVarNC.getIntercellSpacing().height);
    rowHeaderNC.setCellRenderer(new RowHeaderRenderer(jTblVarNC));
    jSPTaulaVarNC.setRowHeaderView(rowHeaderNC);
    jSPTaulaVarNC.setEnabled(true);
    jSPTaulaVarNC.setMaximumSize(new Dimension(32767, 32767));
    jSPTaulaVarNC.setPreferredSize(new Dimension(200, 100));
    jSPTaulaVarNC.setToolTipText("");
    jTblVarCC.setCellSelectionEnabled(true);
    rowHeaderCC.setFixedCellHeight(jTblVarCC.getRowHeight());
//                        + jTblVarNN.getRowMargin());
//+ jTblVarNN.getIntercellSpacing().height);
    rowHeaderCC.setCellRenderer(new RowHeaderRenderer(jTblVarCC));
    jSPTaulaVarCC.setRowHeaderView(rowHeaderCC);
    jSPTaulaVarCC.setEnabled(true);
    jSPTaulaVarCC.setMaximumSize(new Dimension(32767, 32767));
    jSPTaulaVarCC.setPreferredSize(new Dimension(200, 100));
    jSPTaulaVarCC.setToolTipText("");
    jPanEstadsNN.setBorder(null);
    jPanEstadsNN.setMinimumSize(new Dimension(212, 86));
    jPanEstadsNN.setPreferredSize(new Dimension(255, 86));
    jPanEstadsNN.setVerifyInputWhenFocusTarget(true);
    jPanEstadsNN.setLayout(gridBagLayout2);
    jPanEstadsNC.setBorder(null);
    jPanEstadsNC.setPreferredSize(new Dimension(255, 99));
    jPanEstadsNC.setLayout(gridBagLayout1);
    jPanEstadsCC.setBorder(null);
    jPanEstadsCC.setMinimumSize(new Dimension(212, 86));
    jPanEstadsCC.setPreferredSize(new Dimension(255, 86));
    jPanEstadsCC.setVerifyInputWhenFocusTarget(true);
    jPanEstadsCC.setLayout(gridBagLayout4);

    jCBPlot.setText("Plot");
    jCBPlot.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jCBPlot_itemStateChanged(e);
      }
    });
    jCBCorr.setText("Correlació");
    jCBCorr.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jCBCorr_itemStateChanged(e);
      }
    });
    jBttnTreureNN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
    jBttnTreureNN.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnTreureNN_actionPerformed(e);
      }
    });

    jBttnAfegirNN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
    jBttnAfegirNN.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnAfegirNN_actionPerformed(e);
      }
    });
    jPanelBtnsNN.setLayout(borderLayout5);
    jBOpcPlot.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBOpcPlot_actionPerformed(e);
      }
    });
    jBOpcPlot.setText("Opcions");
    jBOpcPlot.setRolloverEnabled(false);
    jBOpcPlot.setMnemonic('0');
    jBOpcPlot.setPreferredSize(new Dimension(75, 25));
    jBOpcPlot.setEnabled(false);

    jBttnTreureNC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
    jBttnTreureNC.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnTreureNC_actionPerformed(e);
      }
    });
    jBttnAfegirNC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
    jBttnAfegirNC.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnAfegirNC_actionPerformed(e);
      }
    });
    jPanelBtnsNC.setLayout(borderLayout4);

    jCBHistoMult.setText("Histograma múltiple");
    jCBHistoMult.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jCBHistoMult_itemStateChanged(e);
      }
    });

    jBOpcBoxpMult.setEnabled(false);
    jBOpcBoxpMult.setPreferredSize(new Dimension(75, 25));
    jBOpcBoxpMult.setMnemonic('0');
    jBOpcBoxpMult.setRolloverEnabled(false);
    jBOpcBoxpMult.setText("Opcions");
    jBOpcBoxpMult.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBOpcBoxpMult_actionPerformed(e);
      }
    });
    jBOpcHistoMult.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBOpcHistoMult_actionPerformed(e);
      }
    });
    jBOpcHistoMult.setText("Opcions");
    jBOpcHistoMult.setRolloverEnabled(false);
    jBOpcHistoMult.setMnemonic('0');
    jBOpcHistoMult.setEnabled(false);
    jBOpcHistoMult.setPreferredSize(new Dimension(75, 25));
    jCBBoxpMult.setText("Boxplot múltiple");
    jCBBoxpMult.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jCBBoxpMult_itemStateChanged(e);
      }
    });

    jPanelVars.setMinimumSize(new Dimension(410, 410));
    jPanelVars.setPreferredSize(new Dimension(500, 415));
    jPanelVars.setRequestFocusEnabled(true);
    jCBDescr.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jCBDescr_itemStateChanged(e);
      }
    });
    jCBDescr.setText("Descr. per grups");
    jBOpcDescr.setPreferredSize(new Dimension(75, 25));
    jBOpcDescr.setEnabled(false);
    jBOpcDescr.setMnemonic('0');
    jBOpcDescr.setRolloverEnabled(false);
    jBOpcDescr.setText("Opcions");
    jBOpcDescr.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBOpcDescr_actionPerformed(e);
      }
    });
    jCBTauCont.setText("Taula de contingència");
    jCBTauCont.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jCBTauCont_itemStateChanged(e);
      }
    });
    jCBDiagBarM.setText("Diagrama de barres múltiple");
    jCBDiagBarM.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jCBDiagBarM_itemStateChanged(e);
      }
    });
    jBttnTreureCC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
    jBttnTreureCC.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnTreureCC_actionPerformed(e);
      }
    });
    jBOpcTauCont.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBOpcTauCont_actionPerformed(e);
      }
    });
    jBOpcTauCont.setText("Opcions");
    jBOpcTauCont.setRolloverEnabled(false);
    jBOpcTauCont.setMnemonic('0');
    jBOpcTauCont.setEnabled(false);
    jBOpcTauCont.setPreferredSize(new Dimension(75, 25));

    jBOpcDiagBarM.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBOpcDiagBarM_actionPerformed(e);
      }
    });
    jBOpcDiagBarM.setText("Opcions");
    jBOpcDiagBarM.setRolloverEnabled(false);
    jBOpcDiagBarM.setMnemonic('0');
    jBOpcDiagBarM.setEnabled(false);
    jBOpcDiagBarM.setPreferredSize(new Dimension(75, 25));
    jBttnAfegirCC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
    jBttnAfegirCC.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnAfegirCC_actionPerformed(e);
      }
    });
    jPanelBtnsCC.setLayout(borderLayout6);

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
    jPanSelec.add(jPanTitolSelNN,   new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 213, 0));
    jPanSelec.add(jPanSelNN,      new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
    jPanSelec.add(jPanTitolSelNC, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 0), 213, 0));
    jPanSelec.add(jPanSelNC, new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0
        ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
    jPanSelec.add(jPanTitolSelCC,  new GridBagConstraints(0, 4, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 0), 213, 0));
    jPanSelec.add(jPanSelCC, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
    jPanTitolSelNN.add(jSepNN1, null);
    jPanTitolSelNN.add(jLblNN, null);
    jPanTitolSelNN.add(jSepNN2, null);
    jPanTitolSelNC.add(jSepNC1, null);
    jPanTitolSelNC.add(jLblNC, null);
    jPanTitolSelNC.add(jSepNC2, null);
    jPanTitolSelCC.add(jSepCC1, null);
    jPanTitolSelCC.add(jLblCC, null);
    jPanTitolSelCC.add(jSepCC2, null);
    jPanelBtnsNN.add(jBttnAfegirNN, BorderLayout.NORTH);
    jPanelBtnsNN.add(jBttnTreureNN, BorderLayout.SOUTH);
    jPanelBtnsNC.add(jBttnAfegirNC, BorderLayout.NORTH);
    jPanelBtnsNC.add(jBttnTreureNC, BorderLayout.SOUTH);
    jPanelBtnsCC.add(jBttnAfegirCC, BorderLayout.NORTH);
    jPanelBtnsCC.add(jBttnTreureCC, BorderLayout.SOUTH);
    jPanEstadsNN.add(jCBPlot, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
    jPanEstadsNN.add(jBOpcPlot,  new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanEstadsNN.add(jCBCorr,    new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 10, 42), 0, 0));
    jPanEstadsNC.add(jCBHistoMult,       new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 10, 5, 10), 0, 0));
    jPanEstadsNC.add(jBOpcHistoMult,        new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanEstadsNC.add(jCBBoxpMult,       new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 10, 5, 10), 0, 0));
    jPanEstadsNC.add(jBOpcBoxpMult,       new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanEstadsNC.add(jCBDescr,    new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 10, 5, 10), 0, 0));
    jPanEstadsNC.add(jBOpcDescr,    new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanEstadsCC.add(jCBDiagBarM, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                    ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
    jPanEstadsCC.add(jBOpcDiagBarM,  new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0
                ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanEstadsCC.add(jCBTauCont,    new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
                ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 10, 42), 0, 0));
    jPanEstadsCC.add(jBOpcTauCont,  new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanSelNN.add(jPanelBtnsNN, null);
    jPanSelNN.add(jSPTaulaVarNN, null);
    jPanSelNN.add(jPanEstadsNN, null);
    jPanSelNC.add(jPanelBtnsNC, null);
    jPanSelNC.add(jSPTaulaVarNC, null);
    jPanSelNC.add(jPanEstadsNC, null);
    jPanSelCC.add(jPanelBtnsCC, null);
    jPanSelCC.add(jSPTaulaVarCC, null);
    jPanSelCC.add(jPanEstadsCC, null);
  }

  void jBttnOK_actionPerformed(ActionEvent e) {
    boolean ok;
    int j, k, l, nFiles, err;
    // llistaVars[0][] - Conté la llista de vars numeriques X de NN
    // llistaVars[1][] - Conté la llista de vars numeriques Y de NN
    // llistaVars[2][] - Conté la llista de vars numeriques X de NC
    // llistaVars[3][] - Conté la llista de vars categoriques Y de NC
    // llistaVars[4][] - Conté la llista de vars numeriques X de CC
    // llistaVars[5][] - Conté la llista de vars categoriques Y de CC
    String[][] llistaVars = new String[6][];
    // llistat dels estadistics que s'han de calcular
    // llistaEstads[0] - Conté la llista d'estad. NN
    // llistaEstads[1] - Conté la llista d'estad. NC
    // llistaEstads[2] - Conté la llista d'estad. CC
    Vector[] llistaEstads = new Vector[3];
    String cmd;
    Process proc;

    j = 0;
    k = 0;
    l = 0;
    nFiles = varsNN.length;
    llistaVars[0] = new String[nFiles];
    llistaVars[1] = new String[nFiles];
    nFiles = varsNC.length;
    llistaVars[2] = new String[nFiles];
    llistaVars[3] = new String[nFiles];
    nFiles = varsCC.length;
    llistaVars[4] = new String[nFiles];
    llistaVars[5] = new String[nFiles];
    for (int i=0; i<files; i++) {
      if ( (varsNN[i][0] != null) && (varsNN[i][1] != null)) {
        llistaVars[0][j] = varsNN[i][0].toString(); // Var Num. X
        llistaVars[1][j] = varsNN[i][1].toString(); // Var Num. Y
        j++;
      }
      if ( (varsNC[i][0] != null) && (varsNC[i][1] != null)) {
        llistaVars[2][k] = varsNC[i][0].toString(); // Var Num. X
        llistaVars[3][k] = varsNC[i][1].toString(); // Var Categ. Y
        k++;
      }
      if ( (varsCC[i][0] != null) && (varsCC[i][1] != null)) {
        llistaVars[4][j] = varsCC[i][0].toString(); // Var Categ. X
        llistaVars[5][j] = varsCC[i][1].toString(); // Var Categ. Y
        j++;
      }
    }
    llistaEstads[0] = new Vector(); // Estads per NN
    llistaEstads[1] = new Vector(); // Estads per NC
    llistaEstads[2] = new Vector(); // Estads per CC
    if (jCBPlot.isSelected())
      llistaEstads[0].add(new Integer(Opcions.PLOT));
    if (jCBCorr.isSelected())
      llistaEstads[0].add(new Integer(Opcions.CORREL));
    if (jCBHistoMult.isSelected())
      llistaEstads[1].add(new Integer(Opcions.HISTO_MULT));
    if (jCBBoxpMult.isSelected())
      llistaEstads[1].add(new Integer(Opcions.BOXP_MULT));
    if (jCBDescr.isSelected())
      llistaEstads[1].add(new Integer(Opcions.DESCR_GR));
    if (jCBDiagBarM.isSelected())
      llistaEstads[2].add(new Integer(Opcions.D_BAR_MULT));
    if (jCBTauCont.isSelected())
      llistaEstads[2].add(new Integer(Opcions.T_CONTINGENCIA));

    ok = gestor.ferDescrip(llistaVars, llistaEstads, opcBiv);

    if (ok) {
      String nom = new File(nomFitxer).getName();
      frPare.actualitzarBarraEstat(
          "S'ha realitzat l'anàlisi descriptiva bivariant correctament. (Resultats al fitxer: " +
          nom + "Biv.tex)", false);
      try {
        String pathResult = gestor.obtenirDirResultats();
        File dirResult = new File(pathResult);
        pathResult = pathResult + nom + "Biv";

        cmd = SO.obtenirCmdCompilaLtx(pathResult + ".tex");
        if (cmd == null) {
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
              cmd = SO.obtenirCmdVisorLtx(pathResult + ".dvi");
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
          "No ha estat possible realitzar l'anàlisi descriptiva bivariant.", true);
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
      jCBCorr.setSelected(true);
      opcBiv.posarPerDefecte(Opcions.CORREL);
      jCBPlot.setSelected(true);
      opcBiv.posarPerDefecte(Opcions.PLOT);
      jCBDescr.setSelected(true);
      opcBiv.posarPerDefecte(Opcions.DESCR_GR);
      jCBHistoMult.setSelected(true);
      opcBiv.posarPerDefecte(Opcions.HISTO_MULT);
      jCBBoxpMult.setSelected(true);
      opcBiv.posarPerDefecte(Opcions.BOXP_MULT);
      jCBTauCont.setSelected(true);
      opcBiv.posarPerDefecte(Opcions.T_CONTINGENCIA);
      jCBDiagBarM.setSelected(true);
      opcBiv.posarPerDefecte(Opcions.D_BAR_MULT);

    }

  }
  void jBttnCancel_actionPerformed(ActionEvent e) {
    frPare.remove(this);
    frPare.validate();
    frPare.repaint();
  }

  void jBttnAfegirNC_actionPerformed(ActionEvent e) {
    int fil = jTblVarNC.getSelectedRow();
    int col = jTblVarNC.getSelectedColumn();
    if ((fil == -1) || (col == -1)) { fil = 0; col = 0; }
    if (col == 0) {
      varsNC[fil][col] = jListVarsN.getSelectedValue();
    } else { // col == 1
      varsNC[fil][col] = jListVarsC.getSelectedValue();
    }
    jTblVarNC.repaint();
  }

  void jBttnTreureNC_actionPerformed(ActionEvent e) {
    int fil = jTblVarNC.getSelectedRow();
    int col = jTblVarNC.getSelectedColumn();
    varsNC[fil][col] = null;
    jTblVarNC.repaint();
  }

  void jCBHistoMult_itemStateChanged(ItemEvent e) {
    boolean selec;

    selec = jCBHistoMult.isSelected();
    if (selec){
      opcBiv.posarPerDefecte(Opcions.HISTO_MULT);
    } else {
      opcBiv.eliminarOpcions(Opcions.HISTO_MULT);
    }
    jBOpcHistoMult.setEnabled(selec);
  }

  void jBOpcBoxpMult_actionPerformed(ActionEvent e) {
    DlgOpcBoxplotMult dlg = new DlgOpcBoxplotMult(frPare, "Opcions per al boxplot múltiple",true, opcBiv);
    dlg.setLocationRelativeTo(this);
    dlg.show();
  }

  void jBOpcHistoMult_actionPerformed(ActionEvent e) {
    DlgOpcHistoMult dlg = new DlgOpcHistoMult(frPare, "Opcions per al histograma múltiple",true, opcBiv);
    dlg.setLocationRelativeTo(this);
    dlg.show();

  }

  void jBOpcPlot_actionPerformed(ActionEvent e) {
    DlgOpcPlot dlg = new DlgOpcPlot(frPare, "Opcions per al plot",true, opcBiv);
    dlg.setLocationRelativeTo(this);
    dlg.show();

  }
  void jCBCorr_itemStateChanged(ItemEvent e) {
    boolean selec;

    selec = jCBCorr.isSelected();
    if (selec) {
      opcBiv.posarPerDefecte(Opcions.CORREL);
    }
    else {
      opcBiv.eliminarOpcions(Opcions.CORREL);
    }

  }
  void jBttnTreureNN_actionPerformed(ActionEvent e) {
    int fil = jTblVarNN.getSelectedRow();
    int col = jTblVarNN.getSelectedColumn();
    varsNN[fil][col] = null;
    jTblVarNN.repaint();

  }

  void jBttnAfegirNN_actionPerformed(ActionEvent e) {
    Object vars = jListVarsN.getSelectedValue();
    int fil = jTblVarNN.getSelectedRow();
    int col = jTblVarNN.getSelectedColumn();
    if ((fil == -1) || (col == -1)) { fil = 0; col = 0; }
    varsNN[fil][col] = vars;
    jTblVarNN.repaint();

  }

  void jBOpcDescr_actionPerformed(ActionEvent e) {
    DlgOpcDescrGrups dlg = new DlgOpcDescrGrups(frPare, "Opcions per a la descriptiva per grups",true, opcBiv);
    dlg.setLocationRelativeTo(this);
    dlg.show();

  }

  void jCBPlot_itemStateChanged(ItemEvent e) {
    boolean selec;

    selec = jCBPlot.isSelected();
    if (selec){
      opcBiv.posarPerDefecte(Opcions.PLOT);
    } else {
      opcBiv.eliminarOpcions(Opcions.PLOT);
    }
    jBOpcPlot.setEnabled(selec);

  }

  void jCBBoxpMult_itemStateChanged(ItemEvent e) {
    boolean selec;

    selec = jCBBoxpMult.isSelected();
    if (selec){
      opcBiv.posarPerDefecte(Opcions.BOXP_MULT);
    } else {
      opcBiv.eliminarOpcions(Opcions.BOXP_MULT);
    }
    jBOpcBoxpMult.setEnabled(selec);
  }

  void jCBDescr_itemStateChanged(ItemEvent e) {
    boolean selec;

    selec = jCBDescr.isSelected();
    if (selec) {
      opcBiv.posarPerDefecte(Opcions.DESCR_GR);
    }
    else {
      opcBiv.eliminarOpcions(Opcions.DESCR_GR);
    }
    jBOpcDescr.setEnabled(selec);

  }
  void jBttnAfegirCC_actionPerformed(ActionEvent e) {
    Object vars = jListVarsC.getSelectedValue();
    int fil = jTblVarCC.getSelectedRow();
    int col = jTblVarCC.getSelectedColumn();
    if ((fil == -1) || (col == -1)) { fil = 0; col = 0; }
    varsCC[fil][col] = vars;
    jTblVarCC.repaint();

  }
  void jBttnTreureCC_actionPerformed(ActionEvent e) {
    int fil = jTblVarCC.getSelectedRow();
    int col = jTblVarCC.getSelectedColumn();
    varsCC[fil][col] = null;
    jTblVarCC.repaint();

  }

  void jCBTauCont_itemStateChanged(ItemEvent e) {
    boolean selec;

    selec = jCBTauCont.isSelected();
    if (selec){
      opcBiv.posarPerDefecte(Opcions.T_CONTINGENCIA);
    } else {
      opcBiv.eliminarOpcions(Opcions.T_CONTINGENCIA);
    }
    jBOpcTauCont.setEnabled(selec);

  }

  void jBOpcTauCont_actionPerformed(ActionEvent e) {
    DlgOpcTaulaCont dlg = new DlgOpcTaulaCont(frPare, "Opcions per a la taula de contingència",true, opcBiv);
    dlg.setLocationRelativeTo(this);
    dlg.show();

  }

  void jCBDiagBarM_itemStateChanged(ItemEvent e) {
    boolean selec;

    selec = jCBDiagBarM.isSelected();
    if (selec) {
      opcBiv.posarPerDefecte(Opcions.D_BAR_MULT);
    }
    else {
      opcBiv.eliminarOpcions(Opcions.D_BAR_MULT);
    }
    jBOpcDiagBarM.setEnabled(selec);

  }

  void jBOpcDiagBarM_actionPerformed(ActionEvent e) {
    DlgOpcDiagBarMult dlg = new DlgOpcDiagBarMult(frPare, "Opcions per al diagrama de barres múltiple",true, opcBiv);
    dlg.setLocationRelativeTo(this);
    dlg.show();

  }

}



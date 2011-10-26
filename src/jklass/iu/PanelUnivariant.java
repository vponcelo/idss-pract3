package jklass.iu;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.lang.InterruptedException;
import java.util.logging.*;
import java.util.Vector;
import java.util.ArrayList;
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
 * @author not attributable
 * @version v.0
 */
public class PanelUnivariant extends JPanel {
  private static Logger logger=Logger.getLogger(PanelUnivariant.class.getName());

  Opcions opcUniv = new Opcions(Opcions.UNIVARIANT);
  BorderLayout borderLayout1 = new BorderLayout();
  FrPrincipal frPare;
  GestorKlass gestor;
  Border border1;
  TitledBorder titledBorder1;
  JPanel jPanelBotones = new JPanel();
  JPanel jPanelVarNum = new JPanel();
  Border border2;
  TitledBorder titledBorder2;
  Border border3;
  JPanel jPanelVarCat = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  Border border4;
  TitledBorder titledBorder3;
  JPanel jPanelEstadsCat = new JPanel();
  Border border5;
  TitledBorder titledBorder4;
  Border border6;
  TitledBorder titledBorder5;
  JPanel jPanelSelecNum = new JPanel();
  JPanel jPanelEstadsNum = new JPanel();
  DefaultListModel listModelVN = new DefaultListModel();
  JList jListVarsN = new JList(listModelVN);
  JScrollPane jSPVarsN = new JScrollPane(jListVarsN);
  DefaultListModel listModelSN = new DefaultListModel();
  JList jListSelcN = new JList(listModelSN);
  JScrollPane jSPSelecN = new JScrollPane(jListSelcN);
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanelBtnsN = new JPanel();
  JButton jBttnAfegirN = new JButton();
  JButton jBttnTreureN = new JButton();
  Border border7;
  TitledBorder titledBorder6;
  BorderLayout borderLayout6 = new BorderLayout();
  DefaultListModel listModelVC= new DefaultListModel();
  JList jListVarsC = new JList(listModelVC);
  JScrollPane jSPVarsC = new JScrollPane(jListVarsC);
  DefaultListModel listModelSC= new DefaultListModel();
  JList jListSelcC = new JList(listModelSC);
  JScrollPane jSPSelecC = new JScrollPane(jListSelcC);
  JPanel jPanelSelecCat = new JPanel();
  JPanel jPanelBtnsC = new JPanel();
  JButton jBttnAfegirC = new JButton();
  JButton jBttnTreureC = new JButton();
  FlowLayout flowLayout1 = new FlowLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  JButton jBOpcDiagBar = new JButton();
  JCheckBox jCBDiagBar = new JCheckBox();
  JCheckBox jCBTaulaFreq = new JCheckBox();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JButton jBOpcBoxp = new JButton();
  JCheckBox jCBBoxp = new JCheckBox();
  JButton jBOpcHisto = new JButton();
  JCheckBox jCBHisto = new JCheckBox();
  JCheckBox jCBResum5 = new JCheckBox();
  JButton jBOpcEstSum = new JButton();
  JCheckBox jCBEstadSum = new JCheckBox();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  JButton jBOpcTFreq = new JButton();
  FlowLayout flowLayout2 = new FlowLayout();
  JButton jBttnDefecte = new JButton();
  JButton jBttnCancel = new JButton();
  JPanel jPanTancar = new JPanel();
  JButton jBttnOK = new JButton();
  FlowLayout flowLayout3 = new FlowLayout();
  String nomFitxer = null;

  public PanelUnivariant(FrPrincipal fr,GestorKlass gk) {
    String[][] llProps;
    int lon;

    frPare = fr;
    gestor = gk;
    nomFitxer = frPare.obtenirNomDades();
	 // System.out.println( "que vale??aaaaaaaa?"+gestor.activasel());
	 
	// if (gestor.activasel=false){
	// System.out.println( "estoy en activas false");
	 ArrayList activias=new ArrayList();
	  activias= gestor.matriusCarregades[gestor.obteniractual()].obtenirActivas();
 if ((gestor.consultasel())|| (gestor.estamarcado())){
 llProps = gestor.obtenirLlistaIDsPropsActivas(activias);}
 else
     {llProps = gestor.obtenirLlistaIDsProps();}
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
	 
	// }//fin de mi if
	 
	// else
	 // System.out.println( "estoy en activas true");
	 

    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
    frPare.setSize(650,450);
    border1 = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
    titledBorder1 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158)),"Anàlisi descriptiva univariant");
    border2 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(178, 178, 178));
    titledBorder2 = new TitledBorder(border2,"Llista de variables");
    border3 = BorderFactory.createCompoundBorder(titledBorder2,BorderFactory.createEmptyBorder(2,2,2,2));
    border4 = BorderFactory.createEmptyBorder();
    titledBorder3 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158)),"Vars. numèriques");
    border5 = BorderFactory.createEmptyBorder();
    titledBorder4 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158)),"Vars. categòriques");
    border6 = BorderFactory.createLineBorder(Color.white,1);
    titledBorder5 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158)),"Llista de vars.");
    border7 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158));
    titledBorder6 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158)),"Vars. seleccionades");
    this.setLayout(borderLayout1);
    jPanelVarNum.setLayout(borderLayout5);
    jPanelVarCat.setLayout(borderLayout2);
    jPanelEstadsNum.setLayout(gridBagLayout2);
    jPanelVarNum.setBorder(titledBorder3);
    jPanelEstadsNum.setBorder(null);
    jSPSelecN.setAutoscrolls(true);
    jSPSelecN.setBorder(titledBorder6);
    jSPSelecN.setPreferredSize(new Dimension(115, 153));
    jBttnTreureN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
    jBttnTreureN.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnTreureN_actionPerformed(e);
      }
    });
    jPanelBtnsN.setLayout(borderLayout4);
    jBttnAfegirN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
    jBttnAfegirN.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnAfegirN_actionPerformed(e);
      }
    });
    jSPVarsN.getViewport().setBackground(Color.white);
    jSPVarsN.setAutoscrolls(true);
    jSPVarsN.setBorder(titledBorder5);
    jSPVarsN.setPreferredSize(new Dimension(100, 153));
    jListVarsN.setBorder(null);
    jBttnTreureC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
    jBttnTreureC.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnTreureC_actionPerformed(e);
      }
    });
    jListVarsC.setBorder(null);
    jSPVarsC.setBorder(titledBorder5);
    jSPVarsC.setPreferredSize(new Dimension(100, 153));
    jSPVarsC.setAutoscrolls(true);
    jSPSelecC.setBorder(titledBorder6);
    jSPSelecC.setPreferredSize(new Dimension(115, 153));
    jSPSelecC.setAutoscrolls(true);
    jPanelBtnsC.setLayout(borderLayout6);
    jBttnAfegirC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
    jBttnAfegirC.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnAfegirC_actionPerformed(e);
      }
    });
    jPanelVarCat.setBorder(titledBorder4);
    jPanelEstadsCat.setBorder(null);
    jPanelEstadsCat.setLayout(gridBagLayout1);
    jPanelSelecNum.setLayout(flowLayout1);
    jBOpcDiagBar.setEnabled(false);
    jBOpcDiagBar.setPreferredSize(new Dimension(75, 25));
    jBOpcDiagBar.setMnemonic('0');
    jBOpcDiagBar.setRolloverEnabled(false);
    jBOpcDiagBar.setText("Opcions");
    jBOpcDiagBar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBOpcDiagBar_actionPerformed(e);
      }
    });
    jCBDiagBar.setText("Diagrama de barres");
    jCBDiagBar.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jCBDiagBar_itemStateChanged(e);
      }
    });
    jCBTaulaFreq.setText("Taula de freqüències");
    jCBTaulaFreq.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jCBTaulaFreq_itemStateChanged(e);
      }
    });
    this.setBorder(titledBorder1);
    this.setDebugGraphicsOptions(0);
    jBOpcBoxp.setEnabled(false);
    jBOpcBoxp.setText("Opcions");
    jBOpcBoxp.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBOpcBoxp_actionPerformed(e);
      }
    });
    jCBBoxp.setText("Boxplot");
    jCBBoxp.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jCBBoxp_itemStateChanged(e);
      }
    });
    jBOpcHisto.setEnabled(false);
    jBOpcHisto.setText("Opcions");
    jBOpcHisto.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBOpcHisto_actionPerformed(e);
      }
    });
    jCBHisto.setEnabled(true);
    jCBHisto.setText("Histograma");
    jCBHisto.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jCBHisto_itemStateChanged(e);
      }
    });
    jCBResum5.setText("Resum en 5 números");
    jBOpcEstSum.setEnabled(false);
    jBOpcEstSum.setPreferredSize(new Dimension(75, 25));
    jBOpcEstSum.setActionCommand("Opcions");
    jBOpcEstSum.setText("Opcions");
    jBOpcEstSum.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBOpcEstSum_actionPerformed(e);
      }
    });
    jCBEstadSum.setText("Estadístics sumaris");
    jCBEstadSum.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jCBEstadSum_itemStateChanged(e);
      }
    });
    jBOpcTFreq.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBOpcTFreq_actionPerformed(e);
      }
    });
    jBOpcTFreq.setText("Opcions");
    jBOpcTFreq.setRolloverEnabled(false);
    jBOpcTFreq.setMnemonic('0');
    jBOpcTFreq.setEnabled(false);
    jBOpcTFreq.setPreferredSize(new Dimension(75, 25));
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
    jPanelSelecNum.add(jSPVarsN, null);
    jPanelSelecNum.add(jPanelBtnsN, null);
    jPanelBtnsN.add(jBttnAfegirN, BorderLayout.NORTH);
    jPanelBtnsN.add(jBttnTreureN, BorderLayout.SOUTH);
    jPanelSelecNum.add(jSPSelecN, null);
    jPanelVarNum.add(jPanelEstadsNum, BorderLayout.CENTER);
    jPanelEstadsNum.add(jCBHisto,    new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 0, 0));
    jPanelEstadsNum.add(jBOpcHisto,    new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0
                ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanelEstadsNum.add(jCBBoxp,      new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 0, 0));
    jPanelEstadsNum.add(jBOpcBoxp,     new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanelEstadsNum.add(jCBEstadSum,       new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0
                ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 0, 0));
    jPanelEstadsNum.add(jBOpcEstSum,    new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0
                ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanelEstadsNum.add(jCBResum5,    new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0
                ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 0, 0));
    jPanelVarNum.add(jPanelSelecNum,  BorderLayout.NORTH);
    this.add(jPanelBotones,  BorderLayout.SOUTH);
    jPanTancar.add(jBttnOK, null);
    jPanTancar.add(jBttnCancel, null);
    jPanelBotones.add(jBttnDefecte, null);
    jPanelBotones.add(jPanTancar, null);
    this.add(jPanelVarNum,  BorderLayout.WEST);
    this.add(jPanelVarCat,  BorderLayout.EAST);
    jPanelVarCat.add(jPanelEstadsCat,  BorderLayout.CENTER);
    jPanelEstadsCat.add(jCBDiagBar,         new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 0, 0));
    jPanelEstadsCat.add(jBOpcDiagBar,        new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanelEstadsCat.add(jCBTaulaFreq,       new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
                ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 0, 0));
    jPanelEstadsCat.add(jBOpcTFreq,      new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanelSelecCat.add(jSPVarsC, null);
    jPanelSelecCat.add(jPanelBtnsC, null);
    jPanelBtnsC.add(jBttnAfegirC, BorderLayout.NORTH);
    jPanelBtnsC.add(jBttnTreureC, BorderLayout.SOUTH);
    jPanelSelecCat.add(jSPSelecC, null);
    jPanelVarCat.add(jPanelSelecCat, BorderLayout.NORTH);
    //frPare.actualitzarBarraEstat("Altura " + frPare.getHeight()+ " Anchura " + frPare.getWidth());
  }

  void jBttnOK_actionPerformed(ActionEvent e) {
    boolean ok;
    // llistaVars[0][] - Conté la llista de vars numeriques
    // llistaVars[1][] - Conté la llista de vars categoriques
    String[][] llistaVars = new String[2][];
    // llistat dels estadistics que s'han de calcular
    // llistaEstads[0] - Conté la llista d'estad. numerics
    // llistaEstads[1] - Conté la llista d'estad. categorics
    Vector[] llistaEstads = new Vector[2];
    int nvarsN, nvarsC, i, err;
    String cmd;
    Process proc;

    nvarsN = listModelSN.getSize();
    nvarsC = listModelSC.getSize();
    if ((nvarsC == 0) && (nvarsN == 0)) {
      frPare.actualitzarBarraEstat("S'ha de seleccionar alguna variable per cada tipus d'estadístic seleccionat", true);
    } else {
      llistaVars[0] = new String[nvarsN];
      for (i = 0; i < nvarsN; i++){
        llistaVars[0][i] = (String)listModelSN.get(i);
      }
      llistaVars[1] = new String[nvarsC];
      for (i = 0; i < nvarsC; i++){
        llistaVars[1][i] = (String)listModelSC.get(i);
      }
      llistaEstads[0] = new Vector();
      llistaEstads[1] = new Vector();
      if ( jCBHisto.isSelected() )
        llistaEstads[0].add(new Integer(Opcions.HISTO));
      if ( jCBBoxp.isSelected() )
        llistaEstads[0].add(new Integer(Opcions.BOXPLOT));
      if ( jCBEstadSum.isSelected() )
        llistaEstads[0].add(new Integer(Opcions.ESTAD_SUM));
      if ( jCBResum5.isSelected() )
        llistaEstads[0].add(new Integer(Opcions.RESUM_5));
      if ( jCBDiagBar.isSelected())
        llistaEstads[1].add(new Integer(Opcions.DIAGR_BARRES));
      if ( jCBTaulaFreq.isSelected() )
        llistaEstads[1].add(new Integer(Opcions.TAULA_FREQS));

      if(((llistaEstads[0].size()>0) && (llistaVars[0].length == 0)) || ((llistaEstads[1].size()>0) && (llistaVars[1].length == 0))) {
        frPare.actualitzarBarraEstat("S'ha de seleccionar alguna variable per cada tipus d'estadístic seleccionat", true);
      }  else {
        ok = gestor.ferDescrip(llistaVars, llistaEstads, opcUniv);

        if (ok) {
          String nom = new File(nomFitxer).getName();
          frPare.actualitzarBarraEstat(
              "S'ha realitzat l'anàlisi descriptiva univariant correctament. (Resultats al fitxer: " + nom + "Univ.tex)", false);
          try {
            String pathResult = gestor.obtenirDirResultats();
            File dirResult = new File(pathResult);
            pathResult = pathResult + nom + "Univ";

            cmd = SO.obtenirCmdCompilaLtx(pathResult + ".tex");
            if (cmd == null) {
              /** @todo També es podria obrir una finestra */
              frPare.actualitzarBarraEstat(
                  "No es pot compilar ni visualitzar automàticament el fitxer Latex generat per no haver-se reconegut el sistema operatiu.", true);
            }
            else {
              logger.finer("Comanda compilació (exec): " + cmd);
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
                  logger.finer("Comanda visor (exec): " + cmd);
                  Runtime.getRuntime().exec(cmd, null, dirResult);
                }
                else {
                  frPare.actualitzarBarraEstat(
                      "No s'ha pogut compilar correctament el fitxer Latex generat. (Error " + err + ")" , true);
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
              "No ha estat possible realitzar l'anàlisi descriptiva univariant.", true);
        }
        frPare.remove(this);
        frPare.validate();
        frPare.repaint();
      }
    }
  }

  void jBttnDefecte_actionPerformed(ActionEvent e) {
    // Es demana confirmació abans de posar opcions per defecte per tot
    int n = JOptionPane.showConfirmDialog(
                            this, "Aquesta opció selecciona tots els estadístics posant a TOTS les seves opcions per defecte. Segur que vols continuar?",
                            "Assignació d'opcions per defecte",
                            JOptionPane.YES_NO_OPTION);

    if (n == JOptionPane.YES_OPTION) {
      jCBEstadSum.setSelected(true);
      opcUniv.posarPerDefecte(Opcions.ESTAD_SUM);
      jCBResum5.setSelected(true);
      jCBHisto.setSelected(true);
      opcUniv.posarPerDefecte(Opcions.HISTO);
      jCBBoxp.setSelected(true);
      opcUniv.posarPerDefecte(Opcions.BOXPLOT);
      jCBTaulaFreq.setSelected(true);
      opcUniv.posarPerDefecte(Opcions.TAULA_FREQS);
      jCBDiagBar.setSelected(true);
      opcUniv.posarPerDefecte(Opcions.DIAGR_BARRES);
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

  void jBOpcEstSum_actionPerformed(ActionEvent e) {
    DlgOpcEstadSum dlg = new DlgOpcEstadSum(frPare,"Opcions per als estadístics sumaris", true, opcUniv);
    dlg.setLocationRelativeTo(this);
    dlg.show();
  }

  void jBOpcDiagBar_actionPerformed(ActionEvent e) {
    DlgOpcDiagBar dlg = new DlgOpcDiagBar(frPare, "Opcions per al diagrama de barres",true, opcUniv);
    dlg.setLocationRelativeTo(this);
    dlg.show();
  }

  void jBOpcBoxp_actionPerformed(ActionEvent e) {
    DlgOpcBoxplot dlg = new DlgOpcBoxplot(frPare, "Opcions per al boxplot",true, opcUniv);
    dlg.setLocationRelativeTo(this);
    dlg.show();
  }

  void jBOpcHisto_actionPerformed(ActionEvent e) {
    DlgOpcHisto dlg = new DlgOpcHisto(frPare, "Opcions per al histograma",true, opcUniv);
    dlg.setLocationRelativeTo(this);
    dlg.show();
  }

  void jBOpcTFreq_actionPerformed(ActionEvent e) {
    DlgOpcTFreq dlg = new DlgOpcTFreq(frPare, "Opcions per a la taula de freqüències",true, opcUniv);
    dlg.setLocationRelativeTo(this);
    dlg.show();
  }

  void jCBEstadSum_itemStateChanged(ItemEvent e) {
    boolean selec;

    selec = jCBEstadSum.isSelected();
    if (selec){
      opcUniv.posarPerDefecte(Opcions.ESTAD_SUM);
    } else {
      opcUniv.eliminarOpcions(Opcions.ESTAD_SUM);
    }
    jBOpcEstSum.setEnabled(selec);
  }

  void jCBHisto_itemStateChanged(ItemEvent e) {
    boolean selec;

    selec = jCBHisto.isSelected();
    if (selec){
      opcUniv.posarPerDefecte(Opcions.HISTO);
    } else {
      opcUniv.eliminarOpcions(Opcions.HISTO);
    }
    jBOpcHisto.setEnabled(selec);
  }

  void jCBBoxp_itemStateChanged(ItemEvent e) {
    boolean selec;

    selec = jCBBoxp.isSelected();
    if (selec){
      opcUniv.posarPerDefecte(Opcions.BOXPLOT);
    } else {
      opcUniv.eliminarOpcions(Opcions.BOXPLOT);
    }
    jBOpcBoxp.setEnabled(selec);
  }

  void jCBTaulaFreq_itemStateChanged(ItemEvent e) {
    boolean selec;

    selec = jCBTaulaFreq.isSelected();
    if (selec){
      opcUniv.posarPerDefecte(Opcions.TAULA_FREQS);
    } else {
      opcUniv.eliminarOpcions(Opcions.TAULA_FREQS);
    }
    jBOpcTFreq.setEnabled(selec);
  }

  void jCBDiagBar_itemStateChanged(ItemEvent e) {
    boolean selec;

    selec = jCBDiagBar.isSelected();
    if (selec){
      opcUniv.posarPerDefecte(Opcions.DIAGR_BARRES);
    } else {
      opcUniv.eliminarOpcions(Opcions.DIAGR_BARRES);
    }

    jBOpcDiagBar.setEnabled(selec);
  }

}


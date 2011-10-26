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

public class DlgOpcTaulaCont extends JDialog {
  final static String EFECTIUS = "Efectius absoluts";
  final static String FREQSFILES = "Freqs. condicionades a files";
  final static String FREQSCOLS = "Freqs. condicionades a columnes";
  Opcions opc;
  JPanel panel1 = new JPanel();
  JPanel jPanelBotones = new JPanel();
  JPanel jPanTancar = new JPanel();
  JButton jBttnCancel = new JButton();
  JButton jBttnOK = new JButton();
  JButton jBttnDefecte = new JButton();
  FlowLayout flowLayout1 = new FlowLayout();
  JLabel jLblNumMods = new JLabel();
  JPanel jPanelMods = new JPanel();
  JTextField jTextField = new JTextField(2);
  String[] TpFreqs = {EFECTIUS, FREQSFILES, FREQSCOLS};
  JComboBox jCmbBFreqs = new JComboBox(TpFreqs);
  JLabel jLblTipus = new JLabel();
  JPanel jPFreqs = new JPanel();
  JPanel jPFitxer = new JPanel();
  JCheckBox jCBFitxer = new JCheckBox();
  GridBagLayout gridBagLayout1 = new GridBagLayout();


  public DlgOpcTaulaCont(Frame frame, String title, boolean modal, Opcions opcions) {
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
    setSize(new Dimension(336, 185));
    panel1.setLayout(gridBagLayout1);
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
    jLblNumMods.setText("Nombre màx. de modalitats en horitzontal per pàg. =");
    jTextField.setColumns(3);
    jLblTipus.setText("Contingut de la cassella:");
    jCBFitxer.setEnabled(true);
    jCBFitxer.setActionCommand("");
    jCBFitxer.setSelected(false);
    jCBFitxer.setText("Genera el fitxer tc");
    getContentPane().add(panel1, BorderLayout.CENTER);
    this.getContentPane().add(jPanelBotones,  BorderLayout.SOUTH);
    jPanelBotones.add(jBttnDefecte, null);
    jPanelBotones.add(jPanTancar, null);
    panel1.add(jPanelMods,    new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 15, 0, 0), 0, 0));
    jPanelMods.add(jLblNumMods, null);
    jPanelMods.add(jTextField, null);
    panel1.add(jPFreqs,     new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 15, 0, 0), 0, 0));
    jPFreqs.add(jLblTipus, null);
    jPFreqs.add(jCmbBFreqs, null);
    panel1.add(jPFitxer,          new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(15, 15, 0, 0), 0, 0));
    jPFitxer.add(jCBFitxer, null);
    jPanTancar.add(jBttnOK, null);
    jPanTancar.add(jBttnCancel, null);
    posarOpcions();
  }

  private void posarOpcions() {
    Hashtable taula;
    String str;

    taula = opc.obtenirOpcionsEstad(Opcions.T_CONTINGENCIA);
    str = (String) taula.get("tipus");
    if ( str.equals("efectius")) {
      jCmbBFreqs.setSelectedItem(EFECTIUS);
    }
    else if (str.equals("freqsFil")){
      jCmbBFreqs.setSelectedItem(FREQSFILES);
    } if (str.equals("freqsCol")){
      jCmbBFreqs.setSelectedItem(FREQSCOLS);
    }
    str = (String) taula.get("nmods");
    if (str != null) {
      jTextField.setText(str);
    }
    jCBFitxer.setSelected( ( (Boolean) taula.get("fitxer")).booleanValue());
  }

  void jBttnCancel_actionPerformed(ActionEvent e) {
    dispose();
  }

  void jBttnOK_actionPerformed(ActionEvent e) {
    Hashtable taula = new Hashtable();

    taula.put("nmods", jTextField.getText());
    if ( ( (String) jCmbBFreqs.getSelectedItem()).compareTo(EFECTIUS) == 0) {
      taula.put("tipus", "efectius");
    }
    else if ( ((String) jCmbBFreqs.getSelectedItem()).compareTo(FREQSFILES) == 0) {
      taula.put("tipus", "freqsFil");
    }
    else if ( ((String) jCmbBFreqs.getSelectedItem()).compareTo(FREQSCOLS) == 0) {
      taula.put("tipus", "freqsCol");
    }
    taula.put("fitxer", new Boolean(jCBFitxer.isSelected()));


    opc.afegirOpcions(Opcions.T_CONTINGENCIA, taula);

    dispose();
  }

  void jBttnDefecte_actionPerformed(ActionEvent e) {
    opc.posarPerDefecte(Opcions.T_CONTINGENCIA);
    // s'actualitzen les opcions mostrades
    posarOpcions();

  }

}
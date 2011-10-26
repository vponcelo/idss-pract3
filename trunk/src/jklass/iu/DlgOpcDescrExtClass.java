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
 * @author Mª del Mar Colillas,Laia Riera Guerra
 * @version v.0
 */

public class DlgOpcDescrExtClass extends JDialog {
  Opcions opc;
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanelBotones = new JPanel();
  JPanel jPanTancar = new JPanel();
  JButton jBttnCancel = new JButton();
  JButton jBttnOK = new JButton();
  JButton jBttnDefecte = new JButton();
  FlowLayout flowLayout1 = new FlowLayout();
  JLabel jLblInfo = new JLabel();
  JPanel jPanOpc = new JPanel();
  JCheckBox jCBTabular = new JCheckBox();
  BorderLayout borderLayout2 = new BorderLayout();
  boolean b=false;
  public DlgOpcDescrExtClass(Frame frame, String title, boolean modal, Opcions opcions) {
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
  
  /**
   * Constructor
   * @param frame, finestra pare
   * @param title, títol de la finestra
   * @param modal
   * @param opcions
   * @param bc, indica si s'ha de seleccionar l'opció tabular o no
   * @author Laia Riera
   */
  public DlgOpcDescrExtClass(Frame frame, String title, boolean modal, Opcions opcions,boolean bc) {
	    super(frame, title, modal);
	    opc = opcions;
	    b=bc;
	    if(bc){
	    	jCBTabular.setSelected(false);
	    }
	    try {
	      jbInit();
	      pack();
	    }
	    catch(Exception ex) {
	      ex.printStackTrace();
	    }
	  }

  private void jbInit() throws Exception {
    jCBTabular.setAlignmentX((float) 0.5);
    jCBTabular.setAlignmentY((float) 0.5);
    jCBTabular.setActionCommand("Representació tabulada");
    setSize(new Dimension(365, 105));
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
    jLblInfo.setRequestFocusEnabled(true);
    jLblInfo.setHorizontalAlignment(SwingConstants.CENTER);
    jLblInfo.setHorizontalTextPosition(SwingConstants.CENTER);
    jLblInfo.setText("(Si el document latex generat produeix errors desactiva aquesta opció.)");
    jLblInfo.setVerticalAlignment(SwingConstants.TOP);
    jLblInfo.setVerticalTextPosition(SwingConstants.TOP);

    jCBTabular.setText("Representació tabular");
    jPanOpc.setLayout(borderLayout2);
    panel1.setAlignmentY((float) 0.5);
    jPanOpc.setAlignmentX((float) 1.5);
    jPanOpc.setAlignmentY((float) 1.5);
    jPanOpc.add(jCBTabular, BorderLayout.NORTH);
    jPanOpc.add(jLblInfo, BorderLayout.CENTER);
    getContentPane().add(panel1, BorderLayout.CENTER);
    this.getContentPane().add(jPanelBotones,  BorderLayout.SOUTH);
    jPanelBotones.add(jBttnDefecte, null);
    jPanelBotones.add(jPanTancar, null);
    jPanTancar.add(jBttnOK, null);
    jPanTancar.add(jBttnCancel, null);
    panel1.add(jPanOpc, BorderLayout.CENTER);
    jPanTancar.add(jBttnOK, null);
    jPanTancar.add(jBttnCancel, null);
    // marquem les opcions que calgui
    posarOpcions();
  }

  private void posarOpcions() {
    Hashtable taula;
    String str;

    taula = opc.obtenirOpcionsEstad(Opcions.DESCR_EXTENSIONAL);

    jCBTabular.setSelected( ( (Boolean) taula.get("tabular")).booleanValue());
  }

  void jBttnCancel_actionPerformed(ActionEvent e) {
    dispose();
  }

  void jBttnOK_actionPerformed(ActionEvent e) {
    Hashtable taula = new Hashtable();

    taula.put("tabular", new Boolean(jCBTabular.isSelected()));

    opc.afegirOpcions(Opcions.DESCR_EXTENSIONAL, taula);

    dispose();
  }

  void jBttnDefecte_actionPerformed(ActionEvent e) {
    opc.posarPerDefecte(Opcions.DESCR_EXTENSIONAL);
    // s'actualitzen les opcions mostrades
    posarOpcions();

  }

}

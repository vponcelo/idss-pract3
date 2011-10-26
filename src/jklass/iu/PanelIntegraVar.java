package jklass.iu;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;

import jklass.nucli.GestorKlass;
import jklass.nucli.*;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

public class PanelIntegraVar extends JPanel {
  private static Logger logger=Logger.getLogger(PanelIntegraClas.class.getName());
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanelBotones = new JPanel();
  JButton jBttnOK = new JButton();
  TitledBorder titledBorder1;
  FrPrincipal frPare;
  GestorKlass gestor;
  JButton jBttnCancel = new JButton();
  JFileChooser chooser = new JFileChooser();
  JPanel jPanel1 = new JPanel();
  JLabel jLblFitxer = new JLabel();
  JPanel jPanelFitxer = new JPanel();
  JTextField jTFFitxer = new JTextField();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JButton jBttnSelec = new JButton();
  JPanel jPanelClas = new JPanel();
  JTextField jTFClas = new JTextField();
  JLabel jLblClas = new JLabel();
  JLabel jLblClas2 = new JLabel();
  JCheckBox jNum = new JCheckBox();
  JCheckBox jCateg = new JCheckBox();
  

  
  JPanel jPanelMat = new JPanel();
  JTextField jTFMat = new JTextField();
  JLabel jLblMat = new JLabel();
  JLabel jCBMat = new JLabel();

  public PanelIntegraVar(FrPrincipal fr, GestorKlass gk) {
    frPare = fr;
    gestor = gk;
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
   System.out.println( "entre al integraclasificiccion");
 		

  
  
    titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158)),"Integració d'una variable");
    this.setLayout(borderLayout1);
    jBttnOK.setText("D'acord");
    jBttnOK.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnOK_actionPerformed(e);
      }
    });
    this.setBorder(titledBorder1);
    jBttnCancel.setText("Cancel·la");
    jBttnCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnCancel_actionPerformed(e);
      }
    });

    // definimos todo lo necesario para el seleccionador de ficheros
    //Rober
    //chooser.setCurrentDirectory(new File("./dades"));
    chooser.setCurrentDirectory(frPare.obtenirDirActual ());

    ExtensionFileFilter filter = new ExtensionFileFilter();
    filter.addExtension("var");
    filter.setDescription("Fitxer de classificació (.var)");
    chooser.setFileFilter(filter);

    jLblFitxer.setVerticalTextPosition(SwingConstants.CENTER);
    jLblFitxer.setText("Fitxer .VAR a integrar:");
    jLblFitxer.setHorizontalTextPosition(SwingConstants.CENTER);
    jLblFitxer.setHorizontalAlignment(SwingConstants.LEFT);
    jTFFitxer.setMinimumSize(new Dimension(6, 21));
    jTFFitxer.setPreferredSize(new Dimension(140, 21));
    jTFFitxer.setMargin(new Insets(1, 1, 1, 1));
    jTFFitxer.setHorizontalAlignment(SwingConstants.LEADING);
    jPanel1.setLayout(gridBagLayout1);
    jBttnSelec.setText("Selecciona");
    jBttnSelec.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnSelec_actionPerformed(e);
      }
    });
    jTFClas.setOpaque(true);
    jTFClas.setPreferredSize(new Dimension(105, 21));
    jTFClas.setRequestFocusEnabled(true);
    jTFClas.setText("Classe");
    jLblClas.setText("Nom de la variable de classe:");
	 //jLblClas2.setText("Tipus de Variable");
    jTFMat.setEnabled(false);
    jTFMat.setMinimumSize(new Dimension(6, 21));
    jTFMat.setPreferredSize(new Dimension(145, 21));
    jTFMat.setText(new File(frPare.obtenirNomDades()).getName()+ "Bis");
    jLblMat.setEnabled(false);
    jLblMat.setText("Nom de la matriu:");
   // jCBMat.setActionCommand("Genera matriu de dades ampliada:");
    jCBMat.setText(" Tipus de variable");
	 jNum.setText("Numérica");
    jNum.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jNum_actionPerformed(e);
      }
    });

 jCateg.setText("Categorica Ordinal");
    jCateg.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCateg_actionPerformed(e);
      }
    });
	 
	 
	 
    this.add(jPanel1, BorderLayout.CENTER);
    jPanelFitxer.add(jLblFitxer, null);
    jPanelFitxer.add(jTFFitxer, null);
    jPanel1.add(jCBMat,         new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(8, 0, 0, 0), 0, 0));
	//
	 jPanel1.add(jNum,         new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(8, 110, 0, 0), 0, 0));
							
 							
 
	 jPanel1.add(jPanelClas,           new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 2), 0, 0));
    jPanelClas.add(jLblClas, null);
	
	 
    jPanelClas.add(jTFClas, null);
	 
    jPanel1.add(jPanelMat,                     new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 18, 0, 2), 0, 0));
    jPanel1.add(jBttnSelec,               new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jPanelFitxer,                   new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 2), 0, 0));
				
				
    this.add(jPanelBotones,  BorderLayout.SOUTH);	 
    jPanelBotones.add(jBttnOK, null);
    jPanelBotones.add(jBttnCancel, null);
    //jPanelMat.add(jNum, null);
   // jPanelMat.add(jTFMat, null);
  }

  void jBttnOK_actionPerformed(ActionEvent e) {
    int id = -1; //identificador intern de la matriu de dades (ha de ser positiu)
    String nomCls, nomClas, nomMatriu;
    boolean novaMatriu;
	 boolean Numerica;

    nomCls = jTFFitxer.getText();
    nomClas = jTFClas.getText();
    //novaMatriu = jCBMat.isSelected();
	 Numerica=jNum.isSelected();
	 
	 novaMatriu=true;
    nomMatriu= novaMatriu ? jTFMat.getText() : frPare.obtenirNomDades();
    if (nomCls != null) {
      String dir = new File(nomCls).getParent();
      logger.finer("Directori: " + dir);
      if (dir == null) {
        nomCls = new File("dades/", nomCls).getAbsolutePath();
        logger.finer("Nom arreglat: " + nomCls);
      }
      try {
		 if (!Numerica){
        id = gestor.carregarVar(nomCls, nomClas, novaMatriu, nomMatriu);
        }
		  else{
		  id = gestor.carregarVarNum(nomCls, nomClas, novaMatriu, nomMatriu);
         }
		  
		   if (id != -1) {
           if (novaMatriu){
             frPare.actualitzarMatriuGuardada(false);
             frPare.actualitzarTitolFinestra(nomMatriu);
             frPare.habilitarOpcionsMenu(true, false);
             frPare.actualitzarBarraEstat(
                 "S'ha integrat la variable.", false);
             frPare.matrius.insertarMatriu(nomMatriu, id);
           } else {
             frPare.actualitzarMatriuGuardada(false);
             frPare.actualitzarBarraEstat(
               "S'ha integrat la classificació.", false);
           }
        }
        else {
          frPare.actualitzarBarraEstat(
              "No s'ha integrat la classificació a la matriu de dades.", true);
        }
      }
      catch (Exception ex) {
        frPare.actualitzarBarraEstat(
            "No s'ha integrat la classificació a la matriu de dades. [" +
            ex.getMessage() + "]", true);
      }
    } else {
      frPare.actualitzarBarraEstat(
          "Cal indicar el fitxer a integrar! ", true);
    }

    frPare.remove(this);
    frPare.validate();
    frPare.repaint();
  }

  void jBttnCancel_actionPerformed(ActionEvent e) {
    frPare.remove(this);
    frPare.validate();
    frPare.repaint();
  }

  void jBttnSelec_actionPerformed(ActionEvent e) {
    int returnVal = chooser.showOpenDialog(PanelIntegraVar.this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      String path = file.getPath();
      int i = path.lastIndexOf('.');
      String matriu = path.substring(0,i);
      jTFFitxer.setText(matriu);
    }
    else {
      frPare.actualitzarBarraEstat("No s'ha seleccionat cap fitxer.", false);
    }
  }

  void jNum_actionPerformed(ActionEvent e) {
  //  jLblMat.setEnabled(jCBMat.isSelected());
   // jTFMat.setEnabled(jCBMat.isSelected());
  }
  
  void jCateg_actionPerformed(ActionEvent e) {
  //  jLblMat.setEnabled(jCBMat.isSelected());
   // jTFMat.setEnabled(jCBMat.isSelected());
  }
 

}

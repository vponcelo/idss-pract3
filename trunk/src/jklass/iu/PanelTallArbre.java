package jklass.iu;

import java.util.logging.*;

import jklass.nucli.GestorKlass;
import jklass.util.SO;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.lang.InterruptedException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

/**
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */
public class PanelTallArbre extends JPanel {
  private static Logger logger = Logger.getLogger(PanelDiscret.class.getName());
  FrPrincipal frPare;
  GestorKlass gestor;
  String nomArbre;

  FlowLayout flowLayout3 = new FlowLayout();
  FlowLayout flowLayout2 = new FlowLayout();
  JButton jBttnDefecte = new JButton();
  JPanel jPanelBotones = new JPanel();
  JButton jBttnCancel = new JButton();
  JPanel jPanTancar = new JPanel();
  JButton jBttnOK = new JButton();
  BorderLayout borderLayout1 = new BorderLayout();
  Border border1;
  TitledBorder titledBorder1;
  JPanel jPanelParam = new JPanel();
  GridLayout gridLayout3 = new GridLayout();
  JPanel jPanelResult = new JPanel();
  JPanel jPanClass = new JPanel();
  FlowLayout flowLayout1 = new FlowLayout();
  JTextField jTFClass = new JTextField(2);
  GridLayout gridLayout2 = new GridLayout();
  JRadioButton jRBClass = new JRadioButton();
  JPanel jPanTipus = new JPanel();
  Border border2;
  TitledBorder titledBorder2;
  JCheckBox jCBVisual = new JCheckBox();
  JCheckBox jCBGen = new JCheckBox();

  //Rober
  JCheckBox jCBGenPar = new JCheckBox();
  
  JCheckBox jCBGenAfe = new JCheckBox();

  JCheckBox jCBEtiq = new JCheckBox();
 
  JCheckBox jCBEtiq2 = new JCheckBox(); //ale
  JCheckBox jCBEtiq3 = new JCheckBox(); //ale
   JTextField jTFIdn2 = new JTextField(2); //ale

  
  ButtonGroup BGrTipus = new ButtonGroup();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanIdn = new JPanel();
  JRadioButton jRBIdn = new JRadioButton();
  FlowLayout flowLayout4 = new FlowLayout();
  FlowLayout flowLayout44 = new FlowLayout(40);//ale
  JTextField jTFIdn = new JTextField(2);
  JLabel jLblNom = new JLabel();
  JTextField jTFNom = new JTextField();
  JPanel jPanNomTall = new JPanel();
  JPanel jPanNomEtiq = new JPanel();
  FlowLayout flowLayout5 = new FlowLayout();
  Border border3;
  JLabel jLblIdn = new JLabel();
   JLabel jLblIdn2 = new JLabel();

  JLabel jLblClass = new JLabel();
  Border border4;
  Border border5;
  Border border6;


  public PanelTallArbre(FrPrincipal fr,GestorKlass gk) {
    frPare = fr;
    gestor = gk;
    nomArbre = gestor.obtenirNomArbre();
    try {
      jbInit();
      generarNomTall();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    String idn = gestor.obtenirIdnArrelArbre();
    int maxClas = gestor.obtenirNumNodesInternsArbre();

    border3 = BorderFactory.createEmptyBorder(0,-275,0,0);
    border4 = BorderFactory.createEmptyBorder(0,10,0,0);
    border5 = BorderFactory.createEmptyBorder(0,10,0,0);
    border6 = BorderFactory.createEmptyBorder(0,10,0,0);
    frPare.setSize(530,450);
    border1 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158));
    titledBorder1 = new TitledBorder(border1,"Tall de l'arbre");
    border2 = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
    titledBorder2 = new TitledBorder(border2,"Tipus de tall");
    jPanelBotones.setLayout(flowLayout3);
    jBttnDefecte.setHorizontalAlignment(SwingConstants.LEFT);
    jBttnDefecte.setText("Per defecte");
    jBttnDefecte.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnDefecte_actionPerformed(e);
      }
    });
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    flowLayout3.setHgap(75);
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
    this.setLayout(borderLayout1);
    this.setBorder(titledBorder1);
    jPanelParam.setLayout(borderLayout2);
    gridLayout3.setColumns(1);

    //Rober
    //gridLayout3.setRows(3);
    //gridLayout3.setRows(4); ale
   gridLayout3.setRows(6); //ale
    jPanelResult.setLayout(gridLayout3);
    jPanClass.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    flowLayout1.setHgap(0);
    flowLayout1.setVgap(0);
    jTFClass.setMinimumSize(new Dimension(40, 21));
    jTFClass.setPreferredSize(new Dimension(25, 21)); //cambié aca ale
    jTFClass.setCaretPosition(0);
    jTFClass.setText("3");
    jTFClass.setColumns(3);
    jTFClass.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTFClass_focusLost(e);
      }
    });
    jTFClass.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jTFClass_actionPerformed(e);
      }
    });
    gridLayout2.setColumns(1);
    gridLayout2.setRows(2);
    jRBClass.setMnemonic('0');
    jRBClass.setSelected(true);
    jRBClass.setText("per nombre de classes:");
    jRBClass.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        jRBClass_stateChanged(e);
      }
    });
    jPanTipus.setLayout(gridLayout2);
    jPanTipus.setBorder(titledBorder2);
    jPanTipus.setMinimumSize(new Dimension(291, 70));
    jPanTipus.setPreferredSize(new Dimension(470, 85));
    jPanTipus.setToolTipText("");
    jCBVisual.setText("Visualitza tall");
    jCBVisual.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        jCBVisual_stateChanged(e);
      }
    });
    jCBGen.setSelected(true);
    jCBGen.setText("Genera fitxer .par");

    //Rober

    jCBGenPar.setSelected(false);
    jCBGenPar.setText("Genera fitxer .cls");
    jCBGenPar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCBGenPar_actionPerformed(e);
      }
    });

  jCBGenAfe.setSelected(false);
  jCBGenAfe.setText("Afegir a matriu de dades");
//NUEVO ALBERTO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
  jCBGenAfe.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCBGenAfe_actionPerformed(e);
      }
    });
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    jCBEtiq.setEnabled(false);
    jCBEtiq.setMargin(new Insets(2, 22, 2, 2));
    jCBEtiq.setText("Etiquetat restringit");
	 
	  jCBEtiq2.setEnabled(true);
    jCBEtiq2.setMargin(new Insets(2, 22, 2, 2));
    jCBEtiq2.setText("Etiquetat amb classes induidas per les regles");

 jCBEtiq3.setEnabled(false);
    jCBEtiq3.setMargin(new Insets(2, 22, 2, 2));
    jCBEtiq3.setText("Etiquetat per nivell");

	 
    jPanIdn.setLayout(flowLayout4);
    jRBIdn.setDebugGraphicsOptions(0);
    jRBIdn.setMnemonic('0');
    jRBIdn.setText("per índex de nivell:");
    flowLayout4.setAlignment(FlowLayout.LEFT);
    flowLayout4.setHgap(0);
    flowLayout4.setVgap(0);
	 
	 //flowLayout44.setHgap(15);
    //flowLayout44.setAlignment(FlowLayout.LEADING);
	 
    jTFIdn.setMinimumSize(new Dimension(40, 21));
    jTFIdn.setPreferredSize(new Dimension(75, 21));
    jTFIdn.setCaretPosition(0);
    jTFIdn.setText("");
    jTFIdn.setColumns(10);
    jTFIdn.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseExited(MouseEvent e) {
        jTFIdn_mouseExited(e);
      }
    });
    jTFIdn.setEnabled(false);
    jTFIdn.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTFIdn_focusLost(e);
      }
    });
    jTFIdn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jTFIdn_actionPerformed(e);
      }
    });
	 
/////////ale
 jTFIdn2.setMinimumSize(new Dimension(15, 21));
    jTFIdn2.setPreferredSize(new Dimension(15, 21));
    jTFIdn2.setCaretPosition(0);
    jTFIdn2.setText("");
    jTFIdn2.setColumns(3);
    jTFIdn2.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseExited(MouseEvent e) {
        jTFIdn_mouseExited(e);
      }
    });
    jTFIdn2.setEnabled(false);
    jTFIdn2.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTFIdn_focusLost(e);
      }
    });
    jTFIdn2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jTFIdn_actionPerformed(e);
      }
    });
	 
	 
////////////ale	 
   borderLayout2.setHgap(0);
    borderLayout2.setVgap(0);
    jLblNom.setRequestFocusEnabled(true);
	// jLblNom.setSize(new Dimension(10, 21));
    jLblNom.setText("                                                            Nom del tall:");
    jTFNom.setMinimumSize(new Dimension(60, 21));
    jTFNom.setPreferredSize(new Dimension(60, 21));
    jTFNom.setRequestFocusEnabled(true);
    jTFNom.setText("");
    jTFNom.setColumns(15);
    jPanNomTall.setBorder(border3);
    jPanNomTall.setMinimumSize(new Dimension(188, 35));
    jPanNomTall.setPreferredSize(new Dimension(188, 35));
    jPanNomTall.setLayout(flowLayout5);
	 
	 // jPanNomEtiq.setBorder(border3); //ale
   // jPanNomEtiq.setMinimumSize(new Dimension(288, 35));
    //jPanNomEtiq.setPreferredSize(new Dimension(288, 35));
   // flowLayout44.setHgap(5);
	// flowLayout44.setAlignment(FlowLayout.LEFT);
	 jPanNomEtiq.setLayout(flowLayout1);

    jLblIdn2.setFont(new java.awt.Font("Dialog", 2, 10));
    jLblIdn2.setAlignmentX((float) 0.5);
    jLblIdn2.setBorder(border5);
    jLblIdn2.setHorizontalAlignment(SwingConstants.LEADING);
    jLblIdn2.setLabelFor(jPanNomEtiq);
    jLblIdn2.setText("(enter mayor o igual que 2)");


	 
    jLblIdn.setFont(new java.awt.Font("Dialog", 2, 10));
    jLblIdn.setAlignmentX((float) 0.5);
    jLblIdn.setBorder(border5);
    jLblIdn.setHorizontalAlignment(SwingConstants.LEADING);
   // jLblIdn.setLabelFor(jPanIdn);
    jLblIdn.setText("(real entre 0 i " + idn +")");
    jLblClass.setFont(new java.awt.Font("Dialog", 2, 10));
    jLblClass.setAlignmentX((float) 0.5);
    jLblClass.setAlignmentY((float) 0.5);
    jLblClass.setBorder(border6);
    jLblClass.setHorizontalAlignment(SwingConstants.LEFT);
    jLblClass.setHorizontalTextPosition(SwingConstants.LEFT);
    jLblClass.setLabelFor(jPanClass);
    jLblClass.setText("(enter entre 2 i "+ maxClas +")");
    jPanelParam.setBorder(border4);
    jPanTipus.add(jPanIdn, null);
    jPanIdn.add(jRBIdn, null);  //en jPanIdn pone juntos el readio button, el textfield y el label
    jPanIdn.add(jTFIdn, null);
    jPanIdn.add(jLblIdn, null);
    jPanelBotones.add(jBttnDefecte, null);
    jPanelBotones.add(jPanTancar, null);
    jPanTancar.add(jBttnOK, null);
    jPanTancar.add(jBttnCancel, null);
    this.add(jPanelParam, BorderLayout.WEST);
    this.add(jPanelBotones,  BorderLayout.SOUTH);
    jPanelParam.add(jPanTipus,  BorderLayout.NORTH);
    jPanTipus.add(jPanClass, null);
    jPanClass.add(jRBClass, null);
    jPanClass.add(jTFClass, null);
    jPanClass.add(jLblClass, null);
    jPanelParam.add(jPanelResult,  BorderLayout.CENTER);
    jPanelResult.add(jCBVisual, null);
    jPanelResult.add(jCBEtiq, null);
	  jPanelResult.add(jCBEtiq2, null); //ale
	 // jPanelResult.add(jTFIdn2, null); //ale
	// jPanelResult.add(jCBEtiq3, null);
	 jPanelResult.add(jPanNomEtiq, null);
 
    jPanelResult.add(jCBGen, null);

    //Rober
    jPanelResult.add(jCBGenPar, null);
	 
  //jPanelResult.add(jCBGenAfe, null);
    //jPanelResult.add(jPanNomEtiq, BorderLayout.LEFT);//ale a ver esto
    jPanNomEtiq.add(jCBEtiq3,null);
    jPanNomEtiq.add(jTFIdn2,null);
	  jPanNomEtiq.add(jLblIdn2,null);
	 
    jPanelParam.add(jPanNomTall, BorderLayout.SOUTH);
    jPanNomTall.add(jLblNom, null);
    jPanNomTall.add(jTFNom, null);
    BGrTipus.add(jRBClass);
    BGrTipus.add(jRBIdn);
	 jPanNomTall.add(jCBGenAfe, null);
  }

  void jBttnDefecte_actionPerformed(ActionEvent e) {
    // Es demana confirmació abans de posar opcions per defecte per tot
    int n = JOptionPane.showConfirmDialog(
        this,
        "Aquesta opció selecciona les opcions per defecte. Segur que vols continuar?",
        "Assignació d'opcions per defecte",
        JOptionPane.YES_NO_OPTION);

    if (n == JOptionPane.YES_OPTION) {
      jRBClass.setSelected(true);
      jTFClass.setEnabled(true);
      jTFIdn.setEnabled(false);
      jCBVisual.setSelected(false);
      jCBEtiq.setSelected(false);
      jCBEtiq.setEnabled(false);
      jCBGen.setSelected(true);

      //Rober
      jCBGenPar.setSelected(false);
      generarNomTall();

    }
  }

  void jBttnCancel_actionPerformed(ActionEvent e) {
    frPare.remove(this);
    frPare.validate();
    frPare.repaint();
  }

  void jBttnOK_actionPerformed(ActionEvent e) {
    boolean ok = false, visual;
    char tipus;
    String num;
    String cmd;
    Process proc;
    int err;

    if (jRBIdn.isSelected()) {
      tipus ='I';
      num = jTFIdn.getText();
    } else {
      tipus='C';
      num = jTFClass.getText();
    }
    visual = jCBVisual.isSelected();
    ok = gestor.tallaArbre(tipus, num, visual, jCBEtiq.isSelected(),jCBEtiq2.isSelected(),
                           jCBGen.isSelected(), jTFNom.getText());

    //Rober


    if (jCBGenPar.isSelected()){
      String s = jTFNom.getText();
      File file = frPare.obtenirDirActual();
      String path = file.getPath()+file.separator+"resultats"+file.separator+s;

        try {
          int id = gestor.transformarParticio(path);
          if (id != -1) {
            frPare.actualitzarBarraEstat("El fitxer de classificació (.cls) s'ha generat.", false);
          }
          else {
            frPare.actualitzarBarraEstat("El fitxer de classificació (.cls) no s'ha generat.", true);
          }
        }
        catch (Exception ex) {
          frPare.actualitzarBarraEstat("El fitxer de classificació (.cls) no s'ha generat. [" +
                                       ex.getMessage() + "]", true);
        }

    }


    if (ok) {
      frPare.actualitzarBarraEstat(
          "S'ha realitzat el tall de l'arbre correctament.", false);
      if (visual) {
        try {
          String pathResult = gestor.obtenirDirResultats();
          File dirResult = new File(pathResult);
          pathResult = pathResult + jTFNom.getText() + "Dendo";

          cmd = SO.obtenirCmdCompilaLtx(pathResult + ".tex");
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
    }
    else {
      frPare.actualitzarBarraEstat(
          "No ha estat possible realitzar el tall de l'arbre", true);
    }
	 ///aqui agrego el integra clasficacion (sólo si generó el cls jCBGenPar  )
	 if (jCBGenAfe.isSelected()){
 
	  int id = -1;
	  String nomCls=jTFNom.getText() ;
	  String nomCls2=jTFNom.getText() ;
	  String nomMatriu= new File(frPare.obtenirNomDades()).getName()+ "Bis";
	  System.out.println("que hay en nomMatriu eh ale?"+nomMatriu);
	  if (nomCls != null) {
      String dir = new File(nomCls).getParent();
      logger.finer("Directori: " + dir);
      if (dir == null) {
        nomCls = new File("dades/resultats/", nomCls).getAbsolutePath();
        logger.finer("Nom arreglat: " + nomCls);
      }
		else {
      frPare.actualitzarBarraEstat(
          "Cal indicar el fitxer a integrar! ", true);
     }
	  }
	  System.out.println("a ver que tieen como nombre de fichero"+nomCls);
	try {
        id = gestor.carregarClas(nomCls, nomCls2, false, nomMatriu);
         if (id != -1) {
			  /*   frPare.actualitzarMatriuGuardada(false);
             frPare.actualitzarTitolFinestra(nomMatriu);
             frPare.habilitarOpcionsMenu(true, false);
             frPare.actualitzarBarraEstat(
                 "S'ha creat una nova matriu de dades amb la classificació.", false);
             frPare.matrius.insertarMatriu(nomMatriu, id);*/
				 //Nunca crea matriz nueva
                frPare.actualitzarMatriuGuardada(false);
               frPare.actualitzarBarraEstat(
               "S'ha integrat la classificació.", false);
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
    }
	 ///
    frPare.remove(this);
    frPare.validate();
    frPare.repaint();
  }

  void jCBVisual_stateChanged(ChangeEvent e) {
    boolean select = jCBVisual.isSelected();
    jCBEtiq.setEnabled(select);
    jCBEtiq.setSelected(select);
  }

  void jRBClass_stateChanged(ChangeEvent e) {
    if (jRBClass.isSelected()){
      jTFClass.setEnabled(true);
      jTFIdn.setEnabled(false);
    } else {
      jTFClass.setEnabled(false);
      jTFIdn.setEnabled(true);
    }
    generarNomTall();
  }

  private void generarNomTall(){
    String str;

    if (jRBClass.isSelected()){
      str = nomArbre +"K"+ jTFClass.getText().trim();
    } else {
      str = nomArbre +"IDN"+ jTFIdn.getText().trim();
    }
    jTFNom.setText(str);
  }

  void jTFIdn_actionPerformed(ActionEvent e) {
    generarNomTall();
  }

  void jTFIdn_focusLost(FocusEvent e) {
    generarNomTall();
  }

  void jTFClass_actionPerformed(ActionEvent e) {
    generarNomTall();
  }

  void jTFClass_focusLost(FocusEvent e) {
    generarNomTall();
  }

  void jTFIdn_mouseExited(MouseEvent e) {
    generarNomTall();
  }

//ROBER
  public void jCBGenPar_actionPerformed(ActionEvent e) {
    if ( jCBGenPar.isSelected()){
      jCBGen.setEnabled(false);
      jCBGen.setSelected(true);
    }
    else {
      jCBGen.setEnabled(true);
    }
  }
  
//NUEVO ALBERTO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
  	public void jCBGenAfe_actionPerformed(ActionEvent e){
		if(jCBGenAfe.isSelected()){
			jCBGenPar.setEnabled(false);
			jCBGenPar.setSelected(true);
		} else {
			jCBGenPar.setEnabled(true);
		}
		jCBGenPar_actionPerformed(e);
	}
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
}

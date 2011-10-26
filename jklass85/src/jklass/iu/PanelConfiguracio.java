package jklass.iu;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import jklass.util.Opcions;
import jklass.util.Parametres;
import java.util.*;
import jklass.nucli.*;
/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

public class PanelConfiguracio extends JPanel {
  Parametres params;
  BorderLayout borderLayout1 = new BorderLayout();
  Frame frPare;
 // JLabel jLblNSelec = new JLabel();//agregué ale
  Opcions opcUniv = new Opcions(Opcions.UNIVARIANT);
 //  private  ResourceBundle res;
 private  ResourceBundle res;//agregueeeeeeeeeeee

//	 res = ResourceBundle.getBundle("jklass.iu.Resources");
 //Locale.setDefault(new Locale("ca"));
public GestorKlass gestor;
  Border border1;
  TitledBorder titledBorder1;
  FlowLayout flowLayout3 = new FlowLayout();
  FlowLayout flowLayout2 = new FlowLayout();
  JButton jBttnDefecte = new JButton();
  JPanel jPanelBotones = new JPanel();
  JButton jBttnCancel = new JButton();
  JPanel jPanTancar = new JPanel();
  JButton jBttnOK = new JButton();
  JButton jBttnLen = new JButton();//ale
  JPanel jPanelConfig = new JPanel();
  JPanel jPanVisLtx = new JPanel();
  JPanel jPanMatrius = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  Border border2;
  TitledBorder titledBorder2;
  Border border3;
  TitledBorder titledBorder3;
  JLabel jLblNMatr = new JLabel();
  JTextField jTFMaxMat = new JTextField();
  JTextField jTFArgsVis = new JTextField();
  JPanel jPanArgVis = new JPanel();
  JLabel jLblArgVis = new JLabel();
  JLabel jLblExecVis = new JLabel();
  JPanel jPanExecVis = new JPanel();
  JTextField jTFExecVis = new JTextField();
  GridLayout gridLayout2 = new GridLayout();
  JPanel jPanDVI = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanCompLtx = new JPanel();
  JPanel jPanArgExec = new JPanel();
  JTextField jTFArgsComp = new JTextField();
  JLabel jLblArgComp = new JLabel();
  JLabel jLblExecComp = new JLabel();
  JPanel jPanExecComp = new JPanel();
  JTextField jTFExecComp = new JTextField();
  GridLayout gridLayout3 = new GridLayout();
  GridLayout gridLayout4 = new GridLayout();
  JPanel jPanPDF = new JPanel();
  JPanel jPanExecCP = new JPanel();
  JLabel jLblExecCP = new JLabel();
  JTextField jTFArgsCP = new JTextField();
  JPanel jPanCompPDFLtx = new JPanel();
  JPanel jPanArgExecCP = new JPanel();
  JLabel jLblArgCP = new JLabel();
  GridLayout gridLayout5 = new GridLayout();
  JTextField jTFExecCP = new JTextField();
  JTextField jTFArgsVP = new JTextField();
  JTextField jTFExecVP = new JTextField();
  JLabel jLblExecVisP = new JLabel();
  JLabel jLblArgVP = new JLabel();
  JPanel jPanArgVisP = new JPanel();
  JPanel jPanExecVisP = new JPanel();
  JPanel jPanVisPDFLtx = new JPanel();
  GridLayout gridLayout6 = new GridLayout();
  Border border4;
  TitledBorder titledBorder4;
  Border border5;
  TitledBorder titledBorder5;

  public PanelConfiguracio(Frame fr) {
    frPare = fr;
    params = new Parametres("jklass.properties","Propiedades de configuracion de Java-Klass");
    try {
      jbInit( );
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
  
  //	jPanMatrius.add(jLblNSelec, null);//agreguéeeeeeeeeeeeeeee
 res = ResourceBundle.getBundle("jklass.iu.Resources");
  
    border4 = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
    titledBorder4 = new TitledBorder(border4,"Compilador de LaTeX-PDF");
    border5 = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
    titledBorder5 = new TitledBorder(border5,"Visor de LaTeX-PDF");
    frPare.setSize(600,375);
    border1 = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
    titledBorder1 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158)),"Configuració");
    border2 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158));
    titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158)),"Compilador de LaTeX-DVI");
    border3 = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
    titledBorder3 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158)),"Visor de LaTeX-DVI");
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
    this.setBorder(titledBorder1);
    this.setLayout(borderLayout1);
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
	 
	  jBttnLen.setText("Seleccionar Idioma"); //ale
    jBttnLen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnLen_actionPerformed(e);
      }
    });

	 
	 
	 
    jPanelConfig.setLayout(borderLayout2);
    jPanVisLtx.setBorder(titledBorder3);
    jPanVisLtx.setLayout(gridLayout2);
	
    jLblNMatr.setRequestFocusEnabled(true);
    jLblNMatr.setText("Màxim nombre de matrius carregades:");
	 
	 	 
	 
    jTFMaxMat.setText("");
    jTFMaxMat.setColumns(3);
	 
	 // jLblNSelec.setRequestFocusEnabled(true);//ale
	 // jLblNSelec.setText("Seleccioni_Llengua");//ale

	 
    jTFArgsVis.setColumns(25);
    jTFArgsVis.setText("");
    jLblArgVis.setText("Arguments:");
    jLblExecVis.setText("Executable:");
    jTFExecVis.setText("");
    jTFExecVis.setColumns(25);
    gridLayout2.setRows(2);
    gridLayout1.setRows(2);
    jPanCompLtx.setBorder(titledBorder2);
    jPanCompLtx.setLayout(gridLayout1);
    jTFArgsComp.setText(params.obtenirArgsCompilaLaTeX());
    jTFArgsComp.setColumns(25);
    jLblArgComp.setText("Arguments:");
    jLblExecComp.setText("Executable:");
    jTFExecComp.setCaretPosition(0);
    jTFExecComp.setText(params.obtenirCmdCompilaLaTeX());
    jTFExecComp.setColumns(25);
    jPanDVI.setLayout(gridLayout3);
    gridLayout3.setRows(2);
    gridLayout4.setRows(2);
    jPanPDF.setLayout(gridLayout4);
    jLblExecCP.setText("Executable:");
    jTFArgsCP.setColumns(25);
    jTFArgsCP.setText(params.obtenirArgsCompilaLaTeX());
    jPanCompPDFLtx.setBorder(titledBorder4);
    jPanCompPDFLtx.setLayout(gridLayout5);
    jLblArgCP.setText("Arguments:");
    gridLayout5.setRows(2);
    jTFExecCP.setCaretPosition(0);
    jTFExecCP.setText(params.obtenirCmdCompilaLaTeX());
    jTFExecCP.setColumns(25);
    jTFArgsVP.setText(params.obtenirArgsVisorLaTeX());
    jTFArgsVP.setColumns(25);
    jTFExecVP.setText(params.obtenirCmdVisorLaTeX());
    jTFExecVP.setColumns(25);
    jLblExecVisP.setText("Executable:");
    jLblArgVP.setText("Arguments:");
    jPanVisPDFLtx.setLayout(gridLayout6);
    jPanVisPDFLtx.setBorder(titledBorder5);
    gridLayout6.setRows(2);
    jPanelConfig.add(jPanMatrius,  BorderLayout.NORTH);
    jPanelBotones.add(jBttnDefecte, null);
    jPanelBotones.add(jPanTancar, null);
    jPanTancar.add(jBttnOK, null);
    jPanTancar.add(jBttnCancel, null);
    this.add(jPanelConfig, BorderLayout.CENTER);
    this.add(jPanelBotones,  BorderLayout.SOUTH);
	 
	 	 
    jPanMatrius.add(jLblNMatr, null);
    jPanMatrius.add(jTFMaxMat, null);
	  jPanMatrius.add(jBttnLen, null); //ale

	 
    jPanArgVis.add(jLblArgVis, null);
    jPanArgVis.add(jTFArgsVis, null);
    jPanelConfig.add(jPanPDF,  BorderLayout.EAST);
    jPanelConfig.add(jPanDVI, BorderLayout.CENTER);
    jPanArgExecCP.add(jLblArgCP, null);
    jPanArgExecCP.add(jTFArgsCP, null);
    jPanPDF.add(jPanCompPDFLtx, null);
    jPanPDF.add(jPanVisPDFLtx, null);
    jPanExecVisP.add(jLblExecVisP, null);
    jPanExecVisP.add(jTFExecVP, null);
    jPanVisPDFLtx.add(jPanExecVisP, null);
    jPanVisPDFLtx.add(jPanArgVisP, null);
    jPanCompPDFLtx.add(jPanExecCP, null);
    jPanCompPDFLtx.add(jPanArgExecCP, null);
    jPanArgVisP.add(jLblArgVP, null);
    jPanArgVisP.add(jTFArgsVP, null);
    jPanExecCP.add(jLblExecCP, null);
    jPanExecCP.add(jTFExecCP, null);
    jPanArgExec.add(jLblArgComp, null);
    jPanArgExec.add(jTFArgsComp, null);
    jPanCompLtx.add(jPanExecComp, null);
    jPanCompLtx.add(jPanArgExec, null);
    jPanExecComp.add(jLblExecComp, null);
    jPanExecComp.add(jTFExecComp, null);
    jPanDVI.add(jPanCompLtx, null);
    jPanDVI.add(jPanVisLtx, null);
    jPanExecVis.add(jLblExecVis, null);
    jPanExecVis.add(jTFExecVis, null);
    jPanVisLtx.add(jPanExecVis, null);
    jPanVisLtx.add(jPanArgVis, null);
    posarValorParams();
  }

  void posarValorParams() {
    jTFMaxMat.setText(params.obtenirMaximMatrius());
    jTFExecComp.setText(params.obtenirCmdCompilaLaTeX());
    jTFArgsComp.setText(params.obtenirArgsCompilaLaTeX());
    jTFExecVis.setText(params.obtenirCmdVisorLaTeX());
    jTFArgsVis.setText(params.obtenirArgsVisorLaTeX());
    jTFExecCP.setText(params.obtenirCmdCompilaPDF());
    jTFArgsCP.setText(params.obtenirArgsCompilaPDF());
    jTFExecVP.setText(params.obtenirCmdVisorPDF());
    jTFArgsVP.setText(params.obtenirArgsVisorPDF());
  }

  void jBttnDefecte_actionPerformed(ActionEvent e) {
    // Es demana confirmació abans de posar opcions per defecte per tot
    int n = JOptionPane.showConfirmDialog(
        this, "Aquesta opció posa a TOTS els paràmetres de configuració els seus valors per defecte. Segur que vols continuar?",
        "Assignació de valors de configuració per defecte",
        JOptionPane.YES_NO_OPTION);

    if (n == JOptionPane.YES_OPTION) {
      //Es posen els valors per defecte per tot
      Parametres paramsDef = new Parametres("jklass_defecte.properties",
          "Propiedades por defecto de configuración de Java-Klass");
      jTFMaxMat.setText(paramsDef.obtenirMaximMatrius());
      jTFExecComp.setText(paramsDef.obtenirCmdCompilaLaTeX());
      jTFArgsComp.setText(paramsDef.obtenirArgsCompilaLaTeX());
      jTFExecVis.setText(paramsDef.obtenirCmdVisorLaTeX());
      jTFArgsVis.setText(paramsDef.obtenirArgsVisorLaTeX());
      jTFExecCP.setText(paramsDef.obtenirCmdCompilaPDF());
      jTFArgsCP.setText(paramsDef.obtenirArgsCompilaPDF());
      jTFExecVP.setText(paramsDef.obtenirCmdVisorPDF());
      jTFArgsVP.setText(paramsDef.obtenirArgsVisorPDF());
    }
  }

  void jBttnOK_actionPerformed(ActionEvent e) {
    params.modificarMaxMAtrius(jTFMaxMat.getText());
    params.modificarCmdCompilaLaTeX(jTFExecComp.getText());
    params.modificarArgsCompilaLaTeX(jTFArgsComp.getText());
    params.modificarCmdVisorLaTeX(jTFExecVis.getText());
    params.modificarArgsVisorLaTeX(jTFArgsVis.getText());
    params.modificarCmdCompilaPDF(jTFExecCP.getText());
    params.modificarArgsCompilaPDF(jTFArgsCP.getText());
    params.modificarCmdVisorPDF(jTFExecVP.getText());
    params.modificarArgsVisorPDF(jTFArgsVP.getText());

    params.salvarParams();

    frPare.remove(this);
    frPare.validate();
    frPare.repaint();
  }
  
 void jBttnLen_actionPerformed(ActionEvent e) {
   
  // DlgOpcDiagBar sel = new DlgOpcDiagBar(frPare, "Opcions per al diagrama de barres",true, opcUniv);
 //	DlgOpcIdioma dlg = new DlgOpcIdioma( "Seleccioni l'idioma",true,gestor);
   //   dlg.setLocationRelativeTo(this);
   //	dlg.setVisible(true);
	 // res = ResourceBundle.getBundle("jklass.iu.Resources");
		 System.out.println("ente a menu conf action performed");
	//	while (actualice==false){;
	//	}
		      // updateStrings();
				 
		 	
	  }
 
  
  

  void jBttnCancel_actionPerformed(ActionEvent e) {
    frPare.remove(this);
    frPare.validate();
    frPare.repaint();
  }
  
  
  
  
  
}


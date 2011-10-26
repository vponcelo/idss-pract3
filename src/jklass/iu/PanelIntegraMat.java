package jklass.iu;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;
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

public class PanelIntegraMat extends JPanel {
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
  JPanel jPanelMat = new JPanel();
  JTextField jTFMat = new JTextField();
  JLabel jLblMat = new JLabel();
  JCheckBox jCBMat = new JCheckBox();
  ArrayList todas=new ArrayList();
MenuMatCarregades matrius;

  public PanelIntegraMat(FrPrincipal fr, GestorKlass gk) {
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
   System.out.println( "entre al integramatriz");
 		

  
  
    titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158)),"Integració d'una matriz");
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
    filter.addExtension("dat");
    filter.setDescription("Fitxer de classificació (.dat)");
    chooser.setFileFilter(filter);

    jLblFitxer.setVerticalTextPosition(SwingConstants.CENTER);
    jLblFitxer.setText("Fitxer .DAT a fusionar:");
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
   // jTFMat.setEnabled(false);
   // jTFMat.setMinimumSize(new Dimension(6, 21));
   // jTFMat.setPreferredSize(new Dimension(145, 21));
    //jTFMat.setText(new File(frPare.obtenirNomDades()).getName()+ "Bis");
	// jTFMat.setText(new File(frPare.obtenirNomDades()).getName());
    jLblMat.setEnabled(false);
    jLblMat.setText("Nom de la matriu:");
   // jCBMat.setActionCommand("Genera matriu de dades ampliada:");
    jCBMat.setText("Genera matriu de dades ampliada nova");
    jCBMat.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCBMat_actionPerformed(e);
      }
    });
    this.add(jPanel1, BorderLayout.CENTER);
    jPanelFitxer.add(jLblFitxer, null);
    jPanelFitxer.add(jTFFitxer, null);
   // jPanel1.add(jCBMat,         new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
     //       ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(8, 0, 0, 0), 0, 0));
    jPanel1.add(jPanelClas,           new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 2), 0, 0));
   // jPanelClas.add(jLblClas, null);
   // jPanelClas.add(jTFClas, null);
    jPanel1.add(jPanelMat,                     new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 18, 0, 2), 0, 0));
    jPanel1.add(jBttnSelec,               new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jPanelFitxer,                   new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 2), 0, 0));
    this.add(jPanelBotones,  BorderLayout.SOUTH);
    jPanelBotones.add(jBttnOK, null);
    jPanelBotones.add(jBttnCancel, null);
   // jPanelMat.add(jLblMat, null);
   // jPanelMat.add(jTFMat, null);
  }

  void jBttnOK_actionPerformed(ActionEvent e) {
    int id = -1; //identificador intern de la matriu de dades (ha de ser positiu)
    String nomCls, nomClas, nomMatriu;
    boolean novaMatriu;

    nomCls = jTFFitxer.getText();
    nomClas = jTFClas.getText();
    //novaMatriu = jCBMat.isSelected();
	 novaMatriu=true;
    //nomMatriu= novaMatriu ? jTFMat.getText() : frPare.obtenirNomDades();
    if (nomCls != null) {
      String dir = new File(nomCls).getParent();
      logger.finer("Directori: " + dir);
      if (dir == null) {
        nomCls = new File("dades/", nomCls).getAbsolutePath();
        logger.finer("Nom arreglat: " + nomCls);
      }
		 System.out.println("la matriz nueva es"+nomCls);
   //empieza el try para obtener matriz  
	   try {
        //id = gestor.carregarMat(nomCls, nomClas, novaMatriu, nomMatriu);
		 id= gestor.carregarMatriu(nomCls);
	//	String coldada="Display2";
	//	id = gestor.carregarMat(nomCls);
         if (id != -1) {
           if (novaMatriu){
             frPare.actualitzarMatriuGuardada(false);
            // frPare.actualitzarTitolFinestra(nomMatriu);
             frPare.habilitarOpcionsMenu(true, false);
             frPare.actualitzarBarraEstat(
                 "S'ha integrat la variable.", false);
           //  frPare.matrius.insertarMatriu(nomMatriu, id);
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
		
////////// termina el catch para obtener matriz aca empieza el otro try
todas= gestor.matriusCarregades[gestor.obteniractual()].obtenirTots();
 String [] arrtodas = new String [todas.size()];
 //alejandro 
         for (int d = 0; d < todas.size(); d++) {
         //    ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
         // System.out.println( "tengo las activas en el arrayList????"+  activas.get(r));
            arrtodas[d]=(String)todas.get(d);
          // System.out.println( "a ver arrtodas aleboludo????"+  arrtodas[d]);      
         }



String col;
String coldada="RAM2";
Dada[] co=null;
//co= gestor.obtenirColumna(coldada);
Propietat prop=null;
String[] propsmias={"Display2","ROM2","RAM2"};

//ACA EMPIEZA EL FOR
for (int j = 0;j<arrtodas.length;j++){

try{
prop= gestor.obtenirPropietat(arrtodas[j]);
co= gestor.obtenirColumna(arrtodas[j]);
}
catch (Exception ex) {
        frPare.actualitzarBarraEstat(
            "No s'ha integrat des. [" +
            ex.getMessage() + "]", true);
      }


// for (int r = 0; r < 10; r++) {
         
  //  System.out.println( "tengoactivas en array????"+  co[r].obtenirValor());
//}


/////////// aca termina el otro catch */


///////agrego columna
try{

gestor.afegirColumna(prop,co);

}
catch (Exception ex) {
        frPare.actualitzarBarraEstat(
            "No s'ha integrat des. [" +
            ex.getMessage() + "]", true);
      }

/////////fin dela grego columna
}
String matriu;
//ACA TERMINA EL FOR

//gestor.eliminarMatriu(gestor.obteniractual());
 //matriu = gestor.tancaMatriuActual();
 System.out.println("la matriz anterior es"+ gestor.obtenirNomMatriuAnt());
  System.out.println("la matriz actual es"+gestor.obtenirNomMatriu());
 
 //matrius.eliminarMatriu("microBis", matriu);
matriu=gestor.tancaMatriuActual();
//matrius.eliminarMatriu(gestor.obtenirNomMatriuAnt(), matriu);
 //System.out.println( "cual es la actual"+  actual);	
 	
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
    int returnVal = chooser.showOpenDialog(PanelIntegraMat.this);
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

  void jCBMat_actionPerformed(ActionEvent e) {
    jLblMat.setEnabled(jCBMat.isSelected());
   // jTFMat.setEnabled(jCBMat.isSelected());
  }

}

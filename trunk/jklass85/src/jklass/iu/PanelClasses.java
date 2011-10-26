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

    public class PanelClasses extends JPanel {
      private static Logger logger=Logger.getLogger(PanelClasses.class.getName());
     // private JRadioButton jRadioButtonLLExplicita=new JRadioButton();//alejandro
   
   
      Opcions opcClass = new Opcions(Opcions.PER_CLASSES);
      BorderLayout borderLayout1 = new BorderLayout();
      FrPrincipal frPare;
      GestorKlass gestor;
      TitledBorder titledBorder1;
      JPanel jPanelBotones = new JPanel();
      Border border2;
      TitledBorder titledBorder2;
      Border border3;
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
      JCheckBox jCBDescrExt = new JCheckBox();
      JButton jBOpcDescrExt = new JButton();
      JButton jBOpcDist = new JButton();
      JCheckBox jCBDist = new JCheckBox();
      JPanel jPanelCalculs = new JPanel();
      GridBagLayout gridBagLayout2 = new GridBagLayout();
      JCheckBox jCBDescr = new JCheckBox();
      JButton jBOpcDescr = new JButton();
      JButton jBttnAfegirC = new JButton();
      BorderLayout borderLayout2 = new BorderLayout();
      JButton jBttnTreureC = new JButton();
      JPanel jPanelBtnsC = new JPanel();
      JScrollPane jSPSelecC = new JScrollPane(jListSelcC);
      BorderLayout borderLayout6 = new BorderLayout();
      JPanel jPanelSelecCat = new JPanel();
      JPanel jPanSelec = new JPanel();
      BorderLayout borderLayout5 = new BorderLayout();
      JPanel jPanLlistes = new JPanel();
      JScrollPane jSPVarsN = new JScrollPane(jListVarsN);
      JScrollPane jSPVarsC = new JScrollPane(jListVarsC);
      FlowLayout flowLayout1 = new FlowLayout();
      JPanel jPanelSelecNum = new JPanel();
      JPanel jPanelSelecClass = new JPanel();
      JScrollPane jSPSelecN = new JScrollPane(jListSelcN);
      BorderLayout borderLayout4 = new BorderLayout();
      JButton jBttnAfegirN = new JButton();
      JButton jBttnTreureN = new JButton();
      JPanel jPanelBtnsN = new JPanel();
      Border border10;
      TitledBorder titledBorder9;
      FlowLayout flowLayout4 = new FlowLayout();
      FlowLayout flowLayout5 = new FlowLayout();
      DefaultListModel listModelSCl= new DefaultListModel();
      JList jListSelcCl = new JList(listModelSCl);
      JScrollPane jSPSelecCl = new JScrollPane(jListSelcCl);
      JButton jBttnTreureCl = new JButton();
      JButton jBttnAfegirCl = new JButton();
      JPanel jPanelBtnsCl = new JPanel();
      BorderLayout borderLayout7 = new BorderLayout();
      Border border11;
      TitledBorder titledBorder10;
      String[][] llProps;
      ArrayList activasPr = new ArrayList();
      boolean soloactivas = false; 
       public PanelClasses(FrPrincipal fr,GestorKlass gk) {
      //String[][] llProps;
      // ArrayList activasPr = new ArrayList();
         int lon;
        // jRadioButtonLLExplicita.setSelected(true);//alejandro
         String[] listact=null;
      
         frPare = fr;
         gestor = gk;
         nomFitxer = frPare.obtenirNomDades();
			
		  ArrayList activias=new ArrayList();
	  activias= gestor.matriusCarregades[gestor.obteniractual()].obtenirActivas();
 if (gestor.consultasel()){
 llProps = gestor.obtenirLlistaIDsPropsActivas(activias);}
 else
     {llProps = gestor.obtenirLlistaIDsProps();}

// llProps =gestor.obtenirLlistaIDsProps();			
      
      // llProps = gestor.obtenirLlistaIDsProps();
      //System.out.println( "entra a mi ferActiv del panel clases!!!!!!");
      
      //   activasPr = gestor.ferActiv();
      
      
      
      
      // System.out.println( "entra al  gestor.obtenirActivas(listact)");
      
      // activas = gestor.obtenirActivas(listact); //crear un obtenirLlistaProps y pasarle el ArrayList activas
      //System.out.println( "sale del gestor.obtenirActivas(listact)");
      
        
      
      //alejandro 
        // for (int i = 0; i <  activasPr.size(); i++) {
         //    ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
          //  System.out.println( "activas en paneldescriptivaperclasses?"+   activasPr.get(i));
        // }
      
      //	llProps =gestor.obtenirLlistaIDsPropsActivas(activasPr);	
      
      
      /* lon = activas.size(); 
      for (int i = 0; i < lon; i++) {
      listModelVC.insertElementAt(activas.get(i), i);
      	System.out.println("imprimo activas[i]");
      System.out.println(activas.get(i));
      
      }	*/ 
      
      
         lon = llProps[0].length; ////////////////////////////////7aca usar solo las activas!!!!!!!!!
        // System.out.println("ale20/11cuanto vale el primer lon llProps[0][i]" + lon);
      
         for (int i = 0; i < lon; i++) {
            listModelVN.insertElementAt(llProps[0][i], i);
          //  System.out.println("aleimprimo llProps[0][i]"+i);
           // System.out.println(llProps[0][i]);
         
         }
         lon = llProps[1].length; 
         for (int i = 0; i < lon; i++) {
            listModelVC.insertElementAt(llProps[1][i], i);
            //System.out.println("aleimprimo llProps[1][i]");
            //System.out.println(llProps[1][i]);
         
         }
      
         //System.out.println("antes de llegar al lon");
         lon = llProps[2].length; 
         //System.out.println("imprimo lon"+lon);
        // for (int i = 0; i < lon; i++) {      
          //  System.out.println("aleimprimo  llProps[2][i]");
           // System.out.println(llProps[2][i]);
         
        // }
      
      
         lon = llProps[3].length; 
        // for (int i = 0; i < lon; i++) {      
          //  System.out.println("aleimprimo llProps[3][i]");
           // System.out.println(llProps[3][i]);
         
         //}
      
      
      /*   lon = llProps[2].length;
      for (int i = 0; i < lon; i++) {
      listModelVC.insertElementAt(llProps[2][i], i);
      }
      lon = llProps[3].length;
      for (int i = 0; i < lon; i++) {
      listModelVC.insertElementAt(llProps[3][i], i);
      }*/
      
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
         titledBorder9 = new TitledBorder(border10,"Càlculs a realitzar");
         border11 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158));
         titledBorder10 = new TitledBorder(border11,"Vars. de classe");
         frPare.setSize(650,650);
         titledBorder1 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158)),"Anàlisi descriptiva per classes");
         border2 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(178, 178, 178));
         titledBorder2 = new TitledBorder(border2,"Llista de variables");
         border3 = BorderFactory.createCompoundBorder(titledBorder2,BorderFactory.createEmptyBorder(2,2,2,2));
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
         
         // JORDI >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
         // Se obtienen las dimensiones en pixels de la pantalla.         
         Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
         
         // Se obtienen las dimensiones en pixels de la ventana.
         Dimension ventana = frPare.getSize();
         
         // Un calculo para situar la ventana en el centro de la pantalla.
         frPare.setLocation((pantalla.width - ventana.width) / 2,(pantalla.height - ventana.height) / 2);
         // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
      
         jBttnDefecte.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnDefecte_actionPerformed(e);
                  }
               });
         jBttnDefecte.setText("Per defecte");
         jBttnDefecte.setHorizontalAlignment(SwingConstants.LEFT);
         jBttnCancel.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnCancel_actionPerformed(e);
                  }
               });
         jBttnCancel.setText("Cancel·la");
         jPanTancar.setLayout(flowLayout2);
         jBttnOK.setText("D'acord");
         jBttnOK.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnOK_actionPerformed(e);
                  }
               });
      
         jPanelBotones.setLayout(flowLayout3);
         flowLayout3.setHgap(75);
         jBOpcDist.setEnabled(false);
         jBOpcDist.setText("Opcions");
         jBOpcDist.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBOpcDist_actionPerformed(e);
                  }
               });
         jBOpcDescrExt.setEnabled(false);
         jBOpcDescrExt.setText("Opcions");
         jBOpcDescrExt.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBOpcDescrExt_actionPerformed(e);
                  }
               });
     
         jCBDescrExt.setEnabled(true);
         jCBDescrExt.setText("Descripció extensional");
         jCBDescrExt.addItemListener(
                new java.awt.event.ItemListener() {
                   public void itemStateChanged(ItemEvent e) {
                     jCBDescrExt_itemStateChanged(e);
                  }
               });
      
         jCBDist.setEnabled(true);
         jCBDist.setText("Distribucions condicionades");
         jCBDist.addItemListener(
                new java.awt.event.ItemListener() {
                   public void itemStateChanged(ItemEvent e) {
                     jCBDist_itemStateChanged(e);
                  }
               });
         jPanelCalculs.setLayout(gridBagLayout2);
         jPanelCalculs.setBorder(titledBorder9);
         jBOpcDescr.setEnabled(false);
         jBOpcDescr.setText("Opcions");
         jBOpcDescr.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBOpcDescr_actionPerformed(e);
                  }
               });
         jCBDescr.setText("Descriptiva de les classes");
         jCBDescr.addItemListener(
                new java.awt.event.ItemListener() {
                   public void itemStateChanged(ItemEvent e) {
                     jCBDescr_itemStateChanged(e);
                  }
               });
         jBttnAfegirC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
         jBttnAfegirC.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnAfegirC_actionPerformed(e);
                  }
               });
         jBttnTreureC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
         jBttnTreureC.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnTreureC_actionPerformed(e);
                  }
               });
      
         jPanLlistes.setLayout(borderLayout5);
         jPanLlistes.setBorder(null);
      
         jPanSelec.setLayout(borderLayout2);
         jSPVarsN.getViewport().setBackground(Color.white);
         jSPVarsN.setAutoscrolls(true);
         jSPVarsN.setBorder(titledBorder5);
         jSPVarsN.setPreferredSize(new Dimension(100, 160));
         jSPVarsC.setBorder(titledBorder7);
         jSPVarsC.setOpaque(true);
         jSPVarsC.setPreferredSize(new Dimension(100, 278));
         jSPVarsC.setRequestFocusEnabled(true);
         jSPVarsC.setAutoscrolls(true);
         jPanelSelecNum.setLayout(flowLayout1);
         jSPSelecN.setAutoscrolls(true);
         jSPSelecN.setBorder(titledBorder6);
         jSPSelecN.setPreferredSize(new Dimension(115, 153));
         jPanelBtnsC.setLayout(borderLayout6);
         jSPSelecC.setBorder(titledBorder8);
         jSPSelecC.setPreferredSize(new Dimension(115, 153));
         jSPSelecC.setAutoscrolls(true);
         jSPSelecCl.setBorder(titledBorder10);
         jSPSelecCl.setPreferredSize(new Dimension(115, 120));
         jSPSelecCl.setAutoscrolls(true);
         jPanSelec.setBorder(null);
         jPanelSelecCat.setLayout(flowLayout4);
         jPanelSelecClass.setLayout(flowLayout5);
         jBttnAfegirN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
         jBttnAfegirN.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnAfegirN_actionPerformed(e);
                  }
               });
         jBttnTreureN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
         jBttnTreureN.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnTreureN_actionPerformed(e);
                  }
               });
         jPanelBtnsN.setLayout(borderLayout4);
         jBttnTreureCl.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnTreureCl_actionPerformed(e);
                  }
               });
         jBttnTreureCl.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
         jBttnAfegirCl.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnAfegirCl_actionPerformed(e);
                  }
               });
      
         jBttnAfegirCl.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
         jPanelBtnsCl.setLayout(borderLayout7);
         borderLayout5.setVgap(6);
     
         jPanelBtnsCl.add(jBttnAfegirCl, BorderLayout.NORTH);
         jPanelBtnsCl.add(jBttnTreureCl, BorderLayout.SOUTH);
         jPanelSelecClass.add(jPanelBtnsCl, null);
         jPanelSelecClass.add(jSPSelecCl, null);
         jPanelSelecNum.add(jPanelBtnsN, null);
         jPanelBtnsN.add(jBttnAfegirN, BorderLayout.NORTH);
         jPanelBtnsN.add(jBttnTreureN, BorderLayout.SOUTH);
         this.add(jPanelBotones,  BorderLayout.SOUTH);
     
         jPanTancar.add(jBttnOK, null);
         jPanTancar.add(jBttnCancel, null);
         jPanelBotones.add(jBttnDefecte, null);
         jPanelBotones.add(jPanTancar, null);
         this.add(jPanVars, BorderLayout.NORTH);
         this.add(jPanVars, BorderLayout.NORTH);
     
         jPanelCalculs.add(jCBDescrExt,    new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
         jPanelCalculs.add(jCBDist,    new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
         jPanelCalculs.add(jCBDescr,    new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
         jPanelCalculs.add(jBOpcDescrExt,    new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
         jPanelCalculs.add(jBOpcDist,    new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
         jPanelCalculs.add(jBOpcDescr,    new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
         jPanelSelecCat.add(jPanelBtnsC, null);
         jPanelBtnsC.add(jBttnAfegirC, BorderLayout.NORTH);
         jPanelBtnsC.add(jBttnTreureC, BorderLayout.SOUTH);
         jPanelSelecCat.add(jSPSelecC, null);
      
      
         jPanSelec.add(jPanelSelecCat, BorderLayout.CENTER);
         jPanSelec.add(jPanelSelecNum, BorderLayout.SOUTH);
         jPanSelec.add(jPanelSelecClass, BorderLayout.NORTH);
     
         jPanelSelecNum.add(jSPSelecN, null);
     
         jPanLlistes.add(jSPVarsN, BorderLayout.SOUTH);
         jPanLlistes.add(jSPVarsC, BorderLayout.NORTH);
         jPanVars.add(jPanLlistes, null);
         jPanVars.add(jPanSelec, null);
         jPanVars.add(jPanelCalculs, null);
      //frPare.actualitzarBarraEstat("Altura " + frPare.getHeight()+ " Anchura " + frPare.getWidth())
        
/*		  	 jRadioButtonLLExplicita.setText("Només variables actives");
	 this.add(jRadioButtonLLExplicita);//alejandro
	  jRadioButtonLLExplicita.addActionListener(
                   new java.awt.event.ActionListener() {
                      public void actionPerformed(ActionEvent e) {
                        jRadioButtonLLExplicita_actionPerformed(e);
                     }
                  });  
      }
   
   */
   
   /*    protected void jRadioButtonLLExplicita_actionPerformed(ActionEvent e) {
         if(jRadioButtonLLExplicita.isSelected()){
                    soloactivas = true;
            activarActivas();
         }
         else{
            soloactivas = false;
            activarTodas();
         
         }
      
      }*/
   
  /*     public void activarActivas(){
              	 
         listModelVN.clear();
         listModelVC.clear();
      
      	
         int lon;
         llProps = null;
         llProps =gestor.obtenirLlistaIDsPropsActivas(activasPr);
         lon = llProps[0].length; 
      
      
         for (int i = 0; i < lon; i++) {
            listModelVN.insertElementAt(llProps[0][i], i);
         
         }
         lon = llProps[1].length; 
         for (int i = 0; i < lon; i++) {
            listModelVC.insertElementAt(llProps[1][i], i);
         
         }
        // System.out.println("ACTIVAR SoooOLO ACTIVAS");	
      }
		
   	
       public void activarTodas(){
         listModelVN.clear();
         listModelVC.clear();
         int lon;
         llProps = null;
         llProps = gestor.obtenirLlistaIDsProps();
         lon = llProps[0].length; ////////////////////////////////7aca usar solo las activas!!!!!!!!!
      //System.out.println("cuanto vale el primer lon llProps[0][i]" + lon);
      
         for (int i = 0; i < lon; i++) {
            listModelVN.insertElementAt(llProps[0][i], i);
         //	System.out.println("imprimo llProps[0][i]"+i);
         //System.out.println(llProps[0][i]);
         
         }
         lon = llProps[1].length; 
         for (int i = 0; i < lon; i++) {
            listModelVC.insertElementAt(llProps[1][i], i);
         //	System.out.println("imprimo llProps[1][i]");
         //System.out.println(llProps[1][i]);
           // System.out.println("ACTIVAR TOOODAAAAAAS");	
         
         }
      
      	
      	
      	
      	
      }
   
   
   */
   
   };
   
   
       void jBttnOK_actionPerformed(ActionEvent e) {
         boolean ok;
      // llistaVars[0][] - Conté la llista de vars categ de classe
      // llistaVars[1][] - Conté la llista de vars numeriques
      // llistaVars[2][] - Conté la llista de vars categoriques
         String[][] llistaVars = new String[3][];
      // llistat dels estadistics que s'han de calcular
      // llistaEstads[0] - Conté la llista d'estad. de var. de classe
      // llistaEstads[1] - Conté la llista d'estad. NC
      // llistaEstads[2] - Conté la llista d'estad. CC
         Vector[] llistaEstads = new Vector[3];
         int nvarsN, nvarsC, nvarsCl, i, err;
         String cmd;
         Process proc;
       	 	 
      
      
         nvarsCl = listModelSCl.getSize();
         if (nvarsCl == 0){
            frPare.actualitzarBarraEstat("S'ha de seleccionar alguna variable de classe", true);
         } 
         else {
            nvarsN = listModelSN.getSize();
            nvarsC = listModelSC.getSize();
            if ( (nvarsC == 0) && (nvarsN == 0) && (jCBDist.isSelected() || jCBDescr.isSelected())) {
               frPare.actualitzarBarraEstat(
                  "S'ha de seleccionar alguna variable numèrica o categòrica", true);
            }
            else {
               llistaVars[0] = new String[nvarsCl];
               llistaVars[1] = new String[nvarsN];
               llistaVars[2] = new String[nvarsC];
               for (i = 0; i < nvarsCl; i++) {
                  llistaVars[0][i] = (String) listModelSCl.get(i);
                 // System.out.println("alejandrosale[0][i]");
                 // System.out.println(llistaVars[0][i]);
               }
               for (i = 0; i < nvarsN; i++) {
                  llistaVars[1][i] = (String) listModelSN.get(i);
                 // System.out.println("alejandrosale[1][i]");
                 // System.out.println(llistaVars[1][i]);
               
               }
               for (i = 0; i < nvarsC; i++) {
                  llistaVars[2][i] = (String) listModelSC.get(i);
                 // System.out.println("alejandroales[2][i]");
                 // System.out.println(llistaVars[2][i]);
               
               }
               llistaEstads[0] = new Vector(1);
               llistaEstads[1] = new Vector(1);
               llistaEstads[2] = new Vector(1);
               if (jCBDescrExt.isSelected()) {
                  llistaEstads[0].add(new Integer(Opcions.DESCR_EXTENSIONAL));
               }
               if (jCBDist.isSelected()) {
                  if (opcClass.estadisticAfegit(Opcions.HISTO_MULT)) {
                     llistaEstads[1].add(new Integer(Opcions.HISTO_MULT));
                  }
                  else {
                     llistaEstads[1].add(new Integer(Opcions.BOXP_MULT));
                  }
                  llistaEstads[2].add(new Integer(Opcions.D_BAR_MULT));
               }
               if (jCBDescr.isSelected()) {
                  llistaEstads[1].add(new Integer(Opcions.DESCR_CLASS));
                  llistaEstads[2].add(new Integer(Opcions.DESCR_CLASS));
               }
            
               if ( (llistaEstads[0].size() == 0) && (llistaEstads[1].size() == 0) && (llistaEstads[2].size()== 0) ) {
                  frPare.actualitzarBarraEstat(
                     "S'ha de seleccionar algun càlcul", true);
               }
               else {
               ok = gestor.ferDescrip(llistaVars, llistaEstads, opcClass);
                 // ok = gestor.ferDescrip(llistaVars, llistaEstads, opcClass,soloactivas);	
                  if (ok) {
                     String nom = new File(nomFitxer).getName();
                     frPare.actualitzarBarraEstat(
                        "S'ha realitzat l'anàlisi descriptiva per classes correctament. (Resultats al fitxer: " +
                        nom + "Class.tex)", false);
                     try {
                        String pathResult = gestor.obtenirDirResultats();
                        File dirResult = new File(pathResult);
                        pathResult = pathResult + nom + "Class";
                     
                        cmd = SO.obtenirCmdCompilaLtx( pathResult + ".tex");
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
                        "No ha estat possible realitzar l'anàlisi descriptiva per classes.", true);
                  }
                  frPare.remove(this);
                  frPare.validate();
                  frPare.repaint();
               }
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
            jCBDescrExt.setSelected(true);
            opcClass.posarPerDefecte(Opcions.DESCR_EXTENSIONAL);
            jCBDist.setSelected(true);
            opcClass.posarPerDefecte(Opcions.HISTO_MULT);
            opcClass.posarPerDefecte(Opcions.D_BAR_MULT);
            jCBDescr.setSelected(true);
            opcClass.posarPerDefecte(Opcions.DESCR_CLASS);
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
   
   
       void jBOpcDist_actionPerformed(ActionEvent e) {
         DlgOpcDistCond dlg = new DlgOpcDistCond(frPare, "Opcions per a les distribucions condicionades",true, opcClass);
         dlg.setLocationRelativeTo(this);
         dlg.show();
      }
   
       void jCBDist_itemStateChanged(ItemEvent e) {
         boolean selec;
      
         selec = jCBDist.isSelected();
         if (selec){
            opcClass.posarPerDefecte(Opcions.HISTO_MULT);
            opcClass.posarPerDefecte(Opcions.D_BAR_MULT);
         } 
         else {
            opcClass.eliminarOpcions(Opcions.HISTO_MULT);
            opcClass.eliminarOpcions(Opcions.D_BAR_MULT);
         }
         jBOpcDist.setEnabled(selec);
      }
   
       void jBOpcDescr_actionPerformed(ActionEvent e) {
         DlgOpcDescrClass dlg = new DlgOpcDescrClass(frPare, "Opcions per a la descriptiva de les classes",true, opcClass);
         dlg.setLocationRelativeTo(this);
         dlg.show();
      }
   
       void jCBDescr_itemStateChanged(ItemEvent e) {
         boolean selec;
      
         selec = jCBDescr.isSelected();
         if (selec){
            opcClass.posarPerDefecte(Opcions.DESCR_CLASS);
         } 
         else {
            opcClass.eliminarOpcions(Opcions.DESCR_CLASS);
         }
         jBOpcDescr.setEnabled(selec);
      }
   
       void jBttnAfegirCl_actionPerformed(ActionEvent e) {
         Object[] vars = jListVarsC.getSelectedValues();
         boolean hay_mas_vars = true;
         int n = 0;
         do {
            try {
               listModelSCl.addElement(vars[n]);
               listModelVC.removeElement(vars[n]);
               n++;
            }
                catch (Exception exc) { hay_mas_vars = false; }
         
         } while (hay_mas_vars);
      }
   
       void jBttnTreureCl_actionPerformed(ActionEvent e) {
         Object[] vars = jListSelcCl.getSelectedValues();
         boolean hay_mas_vars = true;
         int n = 0;
         do {
            try {
               listModelVC.addElement(vars[n]);
               listModelSCl.removeElement(vars[n]);
               n++;
            }
                catch (Exception exc) { hay_mas_vars = false; }
         
         } while (hay_mas_vars);
      }
   
       void jCBDescrExt_itemStateChanged(ItemEvent e) {
         boolean selec;
      
         selec = jCBDescrExt.isSelected();
         if (selec){
            opcClass.posarPerDefecte(Opcions.DESCR_EXTENSIONAL);
         } 
         else {
            opcClass.eliminarOpcions(Opcions.DESCR_EXTENSIONAL);
         }
         jBOpcDescrExt.setEnabled(selec);
      }
   
       void jBOpcDescrExt_actionPerformed(ActionEvent e) {
         DlgOpcDescrExtClass dlg = new DlgOpcDescrExtClass(frPare, "Opcions per a la descripció extensional",true, opcClass);
         dlg.setLocationRelativeTo(this);
         dlg.show();
      }
   
   }
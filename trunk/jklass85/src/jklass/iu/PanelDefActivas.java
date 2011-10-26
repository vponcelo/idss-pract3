   package jklass.iu;

   import java.awt.*;
   import javax.swing.*;
   import java.awt.event.*;
   import javax.swing.border.*;
   import javax.swing.JLabel;
   import java.util.ArrayList;
   import java.io.File;
   import jklass.nucli.GestorKlass;

/**
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.1
 */
    public class PanelDefActivas extends JPanel {
      FrPrincipal frPare;
      GestorKlass gestor;
      String nomFitxer = null;
      String nom5;
      ArrayList todas=new ArrayList();
   // boolean	primera=true;
   
      FlowLayout flowLayout1 = new FlowLayout();
      JPanel jPanelVars = new JPanel();
      JButton jBttnAfegirN = new JButton();
      DefaultListModel listModelSN = new DefaultListModel();
      JList jListSelcN = new JList(listModelSN);
      JScrollPane jSPSelecN = new JScrollPane(jListSelcN);
      BorderLayout borderLayout4 = new BorderLayout();
      JButton jBttnTreureN = new JButton();
      BorderLayout borderLayout5 = new BorderLayout();
      DefaultListModel listModelVN = new DefaultListModel();
      JList jListVarsN = new JList(listModelVN);
      JScrollPane jSPVarsN = new JScrollPane(jListVarsN);
      JPanel jPanelSelecNum = new JPanel();
      JPanel jPanelBtnsN = new JPanel();
      JPanel jPanelBotones = new JPanel();
      JPanel jPanelBoEx = new JPanel();//ale
      JPanel jPanelBoSel = new JPanel();//ale
   
      JPanel jPanelBoEx2 = new JPanel();//ale
      JPanel jPanelBoEx3 = new JPanel();//ale
      JButton Seleccionar = new JButton();
   
   		
      private JLabel jLabelNomBC;//ale
      private JTextField jTextFieldNomBC=new JTextField("alejandropablo ",10);
   // jTextFieldNomBC.setPreferredSize(Dimension(25, 21));
   
   
   
   	
      private JTextField jTextFieldNomBC2=new JTextField(10);
    
   
      FlowLayout flowLayout2 = new FlowLayout();
      FlowLayout flowLayout4 = new FlowLayout();//ale
      FlowLayout flowLayout41 = new FlowLayout();//ale para etiqueta activas
   
   
      BorderLayout borderLayout1 = new BorderLayout();
      Border border1;
      TitledBorder titledBorder1;
      Border border2;
      TitledBorder titledBorder2;
      Border border3;
      TitledBorder titledBorder3;
      private GridLayout gridLayout1Rows = new GridLayout(1,1);//ale
   	
      JPanel jPanelTancar = new JPanel();
      JButton jBttnCancel = new JButton();
      JButton jBttnOK = new JButton();
      JButton jBttnAjuda = new JButton();
   
      private JCheckBox soloactivas = new JCheckBox();
      private JCheckBox soloactivas2 = new JCheckBox();
   
      private JCheckBox importar = new JCheckBox();
   
       private void setTamano( JComponent comp, int x, int y ) {
         Dimension tam = new Dimension( x, y );
         comp.setMaximumSize( tam );
         comp.setPreferredSize( tam );
      }
   
    
   
   
       public PanelDefActivas(FrPrincipal fr,GestorKlass gk) {
         String[][] llProps;
         String[][] llPropsa=null;
         String[][] llPropsd=null;
         int j,lon;
      
         frPare = fr;
         gestor = gk;
         nomFitxer = frPare.obtenirNomDades();
         ArrayList activias=new ArrayList();
         ArrayList diferencia=new ArrayList();
      		
      	
      //	activias= gestor.matriusCarregades[gestor.obteniractual()].obtenirActive();
      
         activias= gestor.matriusCarregades[gestor.obteniractual()].obtenirActivas();
         diferencia= gestor.matriusCarregades[gestor.obteniractual()].dife();
         todas= gestor.matriusCarregades[gestor.obteniractual()].obtenirTots();
      	
      	
         for (int x = 0; x< activias.size(); x++){
            System.out.println( "Quien hay activiaaas="+ activias.get(x));
         }
      
         String [] arrtodas = new String [todas.size()];
      
       //alejandro 
         for (int r = 0; r < todas.size(); r++) {
         //    ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
         // System.out.println( "tengo las activas en el arrayList????"+  activas.get(r));
            arrtodas[r]=(String)todas.get(r);
         //  System.out.println( "a ver arrtodas????"+  arrtodas[r]);      
         }
      
      
        
      
      
      
      
      
      //	if (gestor.LenActiveProps()==0){
         llPropsa = gestor.obtenirLlistaIDsPropsActivas(activias);
         llPropsd = gestor.obtenirLlistaIDsPropsActivas(diferencia);
      
      	
      //	}
      	
         // llProps = gestor.obtenirLlistaIDsProps();
      //	}
      //	else{
      	// llProps = gestor.obtenirLlistaIDsPropsActivas(activias);
      //	}
      
	//	 if (gestor.LenActiveProps()>0){
		//   System.out.println( "tengo tengo actias");
		//	gestor.marcar();
		 
	//	} 
		
      
         if (gestor.LenActiveProps()==0){
         
            llProps = gestor.obtenirLlistaIDsProps();	
         	
         
            j = 0;
            lon = llProps[0].length;
            for (int i = 0; i < lon; i++) {
               listModelVN.insertElementAt(llProps[0][i], j);
               System.out.println( "quiero ver llProps[0]"+  llProps[0][i]);
               j++;
            }
            lon = llProps[1].length;
            for (int i = 0; i < lon; i++) {
               listModelVN.insertElementAt(llProps[1][i], j);
               System.out.println( "quiero ver llProps[1]"+  llProps[1][i]);
               j++;
            }
            lon = llProps[2].length;
            for (int i = 0; i < lon; i++) {
               listModelVN.insertElementAt(llProps[2][i], j);
               System.out.println( "quiero ver llProps[2]"+  llProps[2][i]);
               j++;
            }
            lon = llProps[3].length;
            for (int i = 0; i < lon; i++) {
               listModelVN.insertElementAt(llProps[3][i], j);
               System.out.println( "quiero ver llProps[3]"+  llProps[3][i]);
               j++;
            }
         
         }
      	
      	//agrego para activas
         j = 0;
         lon = llPropsa[0].length;
         for (int i = 0; i < lon; i++) {
            listModelSN.insertElementAt(llPropsa[0][i], j);
            System.out.println( "quiero ver llPropsa[0]"+  llPropsa[0][i]);
         
            j++;
         }
         lon = llPropsa[1].length;
         for (int i = 0; i < lon; i++) {
            listModelSN.insertElementAt(llPropsa[1][i], j);
            System.out.println( "quiero ver llPropsa[1]"+  llPropsa[1][i]);
            j++;
         }
         lon = llPropsa[2].length;
         for (int i = 0; i < lon; i++) {
            listModelSN.insertElementAt(llPropsa[2][i], j);
            System.out.println( "quiero ver llPropsa[2]"+  llPropsa[2][i]);
            j++;
         }
         lon = llPropsa[3].length;
         for (int i = 0; i < lon; i++) {
            listModelSN.insertElementAt(llPropsa[3][i], j);
            System.out.println( "quiero ver llPropsa[3]"+  llPropsa[3][i]);
            j++;
         }
       /////////fin del agrego para activas
      	//agrego para diferencia
         j = 0;
         lon = llPropsd[0].length;
         for (int i = 0; i < lon; i++) {
            listModelVN.insertElementAt(llPropsd[0][i], j);
            j++;
         }
         lon = llPropsd[1].length;
         for (int i = 0; i < lon; i++) {
            listModelVN.insertElementAt(llPropsd[1][i], j);
            j++;
         }
         lon = llPropsd[2].length;
         for (int i = 0; i < lon; i++) {
            listModelVN.insertElementAt(llPropsd[2][i], j);
            j++;
         }
         lon = llPropsd[3].length;
         for (int i = 0; i < lon; i++) {
            listModelVN.insertElementAt(llPropsd[3][i], j);
            j++;
         }
      
      	
      	
      	
      	
      	
      	
         try {
            jTextFieldNomBC.setText("alejandropablo");
            jbInit();
         }
             catch(Exception e) {
               e.printStackTrace();
            }
      }
   	
   	
   
       private void jbInit() throws Exception {
        // frPare.setSize(520,330);ale comento
         frPare.setSize(640,420);
         jTextFieldNomBC.setText("alejandropablo");
         jTextFieldNomBC.setMinimumSize(new Dimension(25, 21));
      
         jTextFieldNomBC.setPreferredSize(new Dimension(25, 21));
         jTextFieldNomBC.setOpaque(true);
      
         jTextFieldNomBC.setRequestFocusEnabled(true);
         jTextFieldNomBC.setText("Classe");
      
         Seleccionar.setEnabled(false);
         jTextFieldNomBC2.setEnabled(false);
         jTextFieldNomBC2.setMinimumSize(new Dimension(25, 21));
      
         jTextFieldNomBC2.setPreferredSize(new Dimension(25, 21));
      
      
         border1 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158));
         titledBorder1 = new TitledBorder(border1,"Definir Actives");
         border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158));
         titledBorder2 = new TitledBorder(border2,"Llista variables");
         border3 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158));
         titledBorder3 = new TitledBorder(border3,"Llista actives");
         jPanelVars.setLayout(borderLayout5);
         jBttnAfegirN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
         jBttnAfegirN.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnAfegirN_actionPerformed(e);
                  }
               });
      
         jSPSelecN.setAutoscrolls(true);
         jSPSelecN.setBorder(titledBorder3);
         jSPSelecN.setPreferredSize(new Dimension(115, 153));
         jBttnTreureN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
         jBttnTreureN.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnTreureN_actionPerformed(e);
                  }
               });
         jSPVarsN.getViewport().setBackground(Color.white);
         jSPVarsN.setAutoscrolls(true);
         jSPVarsN.setBorder(titledBorder2);
         jSPVarsN.setPreferredSize(new Dimension(100, 153));
         jPanelSelecNum.setLayout(flowLayout1);
         jPanelBtnsN.setLayout(borderLayout4);
         jPanelBotones.setLayout(flowLayout2);
      	
         this.setLayout(borderLayout1);
         this.setBorder(titledBorder1);
         jBttnCancel.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnCancel_actionPerformed(e);
                  }
               });
         jBttnCancel.setText("Cancel·la");
         jBttnOK.setText("D'acord");
         jBttnOK.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnOK_actionPerformed(e);
                  }
               });
         jBttnAjuda.setText("Ajuda");
         jBttnAjuda.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnAjuda_actionPerformed(e);
                  }
               });
      			
      //	 jBttnExpo.setText("Exportar");
      //     jBttnExpo.addActionListener(
       //         new java.awt.event.ActionListener() {
         //          public void actionPerformed(ActionEvent e) {
           //          jBttnExpo_actionPerformed(e);
             //     }
             //  });
      		 
       //	jPanelBoEx.add(soloactivas);
       // jPanelBoEx.add(lEspaiactivas);
        
         soloactivas.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                    // gestionarActivas();
                    
                  }
               
               });
      
         importar.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                    // gestionarActivas();
                  }
               });
      
         Seleccionar.setText("Selecciona");
         jBttnAjuda.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnAjuda_actionPerformed(e);
                  }
               });
      			
         soloactivas2.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     soloactivas2_actionPerformed(e);
                  }
               });
      
      
         Seleccionar.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     Seleccionar_actionPerformed(e);
                  }
               });
      
      
      			
      	 
      		 
         jTextFieldNomBC.setText(new File(frPare.obtenirNomDades()).getName());
      //  nom5 = jTextFieldNomBC.getText();
      	
        // flowLayout2.setHgap(75);//no es mio
      	 
         flowLayout4.setHgap(184);//este esta bien
      	 
      	// flowLayout41.setHgap(14);
      	// flowLayout41.setVgap(40);
      
         jPanelVars.add(jPanelSelecNum, BorderLayout.NORTH);
         jPanelVars.add(jPanelBoEx,BorderLayout.LINE_START);//aleeeeeeeeeee
      	
         jPanelVars.add(jPanelBoSel);//aleeeeeeeeeee
        // jPanelVars.add(jPanelBoEx2,BorderLayout.PAGE_END );
      //jPanelBoEx.setPreferredSize(new Dimension(50, 25)); 
      
      //jPanelBoEx.setMaximumSize(new Dimension(100, 100));
      
      // jPanelBoEx.setLayout(BoxLayout);
         jPanelBoEx.setLayout(new BoxLayout(jPanelBoEx, BoxLayout.PAGE_AXIS )); 
       
      // jPanelBoEx.add(Box.createHorizontalStrut(0));
      
      // jPanelBoEx.setMaximumSize(new Dimension(100, 10));
      
         jPanelBoSel.setLayout(null); 
      
      
         jPanelBoEx.setBorder(BorderFactory.createEmptyBorder(0, 160, 0, 0)); 
      
      //jTextFieldNomBC.setBorder(BorderFactory.createEmptyBorder(0, 110, 0, 0));
      //jTextFieldNomBC.setBorder(BorderFactory.createEmptyBorder(0, 110, 0, 0));
      //jPanelBoEx.add(Box.createRigidArea(new Dimension(15,0))); 
      
      //////////BORDE ROJO
      // jPanelBoSel.setBorder(BorderFactory.createCompoundBorder(
      //                BorderFactory.createLineBorder(Color.red),
      //              jPanelBoEx.getBorder()));
      
      
         jPanelSelecNum.add(jSPVarsN, null);
         jPanelSelecNum.add(jPanelBtnsN, null);
         jPanelBtnsN.add(jBttnAfegirN, BorderLayout.NORTH);
         jPanelBtnsN.add(jBttnTreureN, BorderLayout.SOUTH);
         jPanelSelecNum.add(jSPSelecN, null);
         this.add(jPanelVars, BorderLayout.CENTER);
      
      
         this.add(jPanelBotones,  BorderLayout.SOUTH);
      
         jPanelBotones.add(jPanelTancar, null);
         jPanelTancar.add(jBttnOK, null);
         jPanelTancar.add(jBttnCancel, null);
         jPanelBotones.add(jBttnAjuda, null);
      
         jPanelBoSel.add(Seleccionar,null);
         Seleccionar.setBounds(14, 60, 100, 26);   //(posx,posy,largo,ancho)
         soloactivas.setText("Genera fitxer .act");
       
      
         soloactivas2.setText("Importa fitxer .act");	
      //setTamano( jTextFieldNomBC, 20,20);
      //jTextFieldNomBC.setBounds(77, 112,0,0);
      // soloactivas.setAlignmentX( Component.LEFT_ALIGNMENT);
      //soloactivas2.setAlignmentX( Component.LEFT_ALIGNMENT );
      
         jPanelBoEx.add(soloactivas);//ale
      //jPanelBoEx.add(Box.createHorizontalStrut(150));
      
      
      
         jPanelBoEx.add(jTextFieldNomBC,null);
      
      
      
         jPanelBoEx.add(soloactivas2);//ale
      //jTextFieldNomBC.setAlignmentX(Component.LEFT_ALIGNMENT);
      
      
      //setTamano( jTextFieldNomBC2, 20,20);
      //jTextFieldNomBC2.setBounds(77, 112, 133, 21);
         jPanelBoEx.add(jTextFieldNomBC2);
         soloactivas.setAlignmentX(.2f);
         jTextFieldNomBC.setAlignmentX(0);
         soloactivas2.setAlignmentX(.2f);
         jTextFieldNomBC2.setAlignmentX(0);
      
      
      // jPanelVars.add(Seleccionar);
      //Seleccionar.setBounds(14, 21, 63, 42);
      
      
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
   
       void jBttnCancel_actionPerformed(ActionEvent e) {
         frPare.remove(this);
         frPare.validate();
         frPare.repaint();
      }
   	
   ////////////////////ale	soloactivas2	
       void soloactivas2_actionPerformed(ActionEvent e) {
         if (soloactivas2.isSelected()){
            Seleccionar.setEnabled(true);
            jTextFieldNomBC2.setEnabled(true);
         }
         else{
            Seleccionar.setEnabled(false);
            jTextFieldNomBC2.setEnabled(false);}
      
      // jTFMat.setEnabled(jCBMat.isSelected());
      }
   
   //////////////////////7		
       void Seleccionar_actionPerformed(ActionEvent e) {
          // System.out.println("Doy click en el Seleccionar");
         String[] activas;
         String[] difere;
         ArrayList activs=new ArrayList();
      
      	  ///////empieza el abrir browser
      	   
         frPare.actualitzarBarraEstat(" ", false);
      		//contentPane.remove(panelCentral);
         int id = -1; // identificador intern de la matriu de dades (ha de ser
      		// positiu)
         String matriu;
      	
         JFileChooser chooser = new JFileChooser();		
         chooser.setCurrentDirectory(frPare.obtenirDirActual ());
         chooser.setDialogTitle("Càrrega actives");
      
      
      		
         ExtensionFileFilter filter = new ExtensionFileFilter();
         filter.addExtension("act");
         filter.setDescription("Fitxers de actives");
         chooser.setFileFilter(filter);
      
         int returnVal = chooser.showOpenDialog(frPare);
      	//	if (returnVal == JFileChooser.APPROVE_OPTION) {
         File file = chooser.getSelectedFile();
         String snom=file.getName().substring(0,file.getName().lastIndexOf("."));
      		//	 System.out.println("cargue el archivo de nombre!!!!!!"+snom);
         jTextFieldNomBC2.setText(snom);
      
      	
         String path = file.getPath();
      		//	System.out.println("cual es el path"+path);
      		//	int i = path.lastIndexOf('.');
      			// Per si s'introdueix el nom del fitxer sense extensió
      		//	i = (i == -1) ? path.length() : i;
      		//	matriu = path.substring(0, i);
         try {
         	//	frPare.actualitzarBarraEstat("Carregant les regles " +matriu
         				//	+ "...", false);
            activas=gestor.carregarAct(path);
         	//	 for(int i=0;i<ale.length;i++){
              // 	System.out.println("a ver que estoy trayendo"+ale[i]);
         
                // }
            for(int i=0;i<activas.length;i++){
               activs.add(i,activas[i]);
            }
         		  
         	//	  for (int x = 0; x< activs.size(); x++){
            //  System.out.println( "Quien hay ACTIVAAAS="+ activs.get(x));
            //	}
         		
         		
         	//		 for (int x = 0; x< todas.size(); x++){
         // System.out.println( "Quien hay TOTTTAS="+ todas.get(x));
         //	}
         
         ///////////////////aca hago la diferencia entre todas y activs/////////////					
            ArrayList d =new ArrayList();	
            if (activs.size()>0){
            
               for (int p = 0; p< todas.size(); p++){
               // System.out.println("que devuelve"+activeProps.contains(ordreProps.get(p)));
               
                  if ((!activs.contains(todas.get(p)))){
                     d.add(todas.get(p));
                  }
               }
            
            //for ( int x = 0; x< d.size(); x++){
            //System.out.println( "Quien hay en d="+ d.get(x));
            //}
            }
         
         			  
         ////////////////////fin de la diferencia entre todas y activs						
         	//	System.out.println("a ver que estoy trayendo");
         		//listModelSN.addElement(ale);
         		
         	//	String[] nombres= {"pedro", "juan", "diego"};
         	
         	
         /////////////ahora paso la diferencia a un array				
            String [] noactivas = new String [d.size()];
         
         //alejandro 
            for (int r = 0; r < d.size(); r++) {
            //    ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
            // System.out.println( "tengo las activas en el arrayList????"+  activas.get(r));
               noactivas[r]=(String)d.get(r);
            // System.out.println( "a ver arrtodas????"+  noactivas[r]);      
            }
         //////////////////////pase la diferencia a un array				
         	
         	
         	
         	
         	
            listModelSN.clear();
            for(int i=0;i<activas.length-1;i++){
               if (activas[i]!=null)
                  listModelSN.addElement(activas[i]);
            }
         
            listModelVN.clear();
            for(int i=0;i<noactivas.length;i++){
               if (noactivas[i]!=null)
                  listModelVN.addElement(noactivas[i]);
            }
         
         
         
         //	listModelVN.addElement("MP");
         					
         		//	habilitarOpcionsMenuConeixement();
            frPare.actualitzarBarraEstat("Les actives s'han carregat.", false);				
         } 
             catch (Exception ex) {
            		/*gestor.eliminarBC(bc.getNomBC());*/
               ex.printStackTrace();
               frPare.actualitzarBarraEstat("Les actives no s'han carregat. "
                  		+ ex.getMessage(), true);
            }
      	//	}
         validate();
         repaint();
      	  
      
      }
   
   
   	
   	
   	
   ////////////////////		
   
       void jBttnOK_actionPerformed(ActionEvent e) {
         String[] llistaOrdre,llistaActive;
         int i, nMods;
         boolean ok, ok1, ok2;
      
         //if (listModelVN.isEmpty()) {
         nMods = listModelVN.getSize();
         llistaOrdre = new String[nMods];
         for (i = 0; i < nMods; i++) {
            llistaOrdre[i] = (String) listModelVN.get(i);
              // System.out.println("elemento"+llistaOrdre[i]);
         }
      		
         nMods = listModelSN.getSize();
      	// System.out.println("cuanto vale nMods"+nMods);
         llistaActive = new String[nMods];
         for (i = 0; i < nMods; i++) {
            llistaActive[i] = (String) listModelSN.get(i);
              // System.out.println("elemento"+llistaOrdre[i]);
         }
      
      //	ArrayList EliProps=new ArrayList();	
      //	EliProps.clear();
      //      for (i = 0; i<llistaEli.length; i++){
      //        EliProps.add(i,llistaEli[i]); }
      		 
      // for (int j = 0; j < EliProps.size(); j++) {
      //        System.out.println( "LAS QUE ELIMINOOOO"+  EliProps.get(j));
      	// }
      
      		
      		
            //ok = gestor.establirOrdreVars(llistaOrdre);
      					//	ok = gestor.eliminarInactivas(llistaOrdre);
      //ok=gestor.activasdefinidas(false);
      
         ArrayList alProps=new ArrayList();			
         alProps.clear();
         for (i = 0; i<llistaOrdre.length; i++){
            alProps.add(i,llistaOrdre[i]);
         	// System.out.println( "LAS QUE ELIMINOOOO"+  alProps.get(i));
         }
      		  
         ArrayList ActiveProps=new ArrayList();			
         ActiveProps.clear();
         for (i = 0; i<llistaActive.length; i++){
            ActiveProps.add(i,llistaActive[i]);
           // System.out.println( "LAS Activaaaaaas"+  ActiveProps.get(i));
         }
      		  
      		  
      
         ok = gestor.establirActiveVars(llistaActive);
      
      	   System.out.println( "tengo tengo MARCOO");
			gestor.marcar();

      
      //	try{		
      //    gestor.crearHija();
      //  ok1=true;
      //	}
      //	catch(Exception ex){
      //	ok1=false;
      		//	ex.printStackTrace();
      	//		frPare.actualitzarBarraEstat("No s'han pogut crear matriz hija: "+ex.getMessage(),true);
      	//	}
      		 
      		 
      //	try{		 
      //	 gestor.eliminarPropietats(alProps);
      	// ok2=true;
       //  }
      	
      //	catch(Exception ex){
      	//		ex.printStackTrace();
      //		ok2=false;
      	//		frPare.actualitzarBarraEstat("No s'han pogut eliminar les propietats de forma definitiva: "+ex.getMessage(),true);
      //		}
      
      
      
      
      //       if ((ok1) & (ok2)) {
           //          frPare.actualitzarBarraEstat(
         //         "Se han creado matriz activas " , false);
           // }
           // else {
      	          //     frPare.actualitzarBarraEstat(
              //    "No ha estat possible crear matriz activas", true);
           // }
      	  
      	  ////////////generar archivo .act
         String nom4 = 	jTextFieldNomBC.getText();
         if (soloactivas.isSelected()){
         // String s = jTFNom.getText();
            String nom = 	jTextFieldNomBC.getText();
         //String nomant= jTextFieldNomBC.getText();
         
         // String nom = gestor.obtenirNomMatriu();
         //	String matriu="prueba";
           // System.out.println( "EL NOMBRE DE LA MATRIZZZ"+  nom);
            File file = frPare.obtenirDirActual();
            String path = file.getPath()+file.separator+nom;
         // String path = nom;
            try {
               frPare.actualitzarBarraEstat("Desant les actives " + path
                  	+ "...", false);
            //	if(bc!=null)
               gestor.desarActivas(path, gestor.matriusCarregades[gestor.obteniractual()].obtenirActivas());
            //	else throw new Exception("BaseConeixement buida");
            	
               frPare.actualitzarTitolFinestra(path);
               frPare.actualitzarBarraEstat("Les actives s'han desat.", false);
            	
            } 
                catch (Exception ex) {
                  frPare.actualitzarBarraEstat("Les actives no s'han desat. "
                     + ex.getMessage(), true);
               }
         	
         	
         
         	
         
         }  
      	  
      	  ////////fin generar archivo .act
      		
      		
         frPare.actualitzarBarraEstat( "S'han definit les actives " , false);
        // System.out.println( "antes del set text");
      	 
      //System.out.println( "que hay en nom4???????????????"+nom4);
      //System.out.println( "que hay en nom5???????????????"+nom5);
      
        // jTextFieldNomBC.setText(nom5);
      //jTextFieldNomBC.setText("aleeeeeeeeeeeeeeeeeeeeeeeeee");	
      	
      //	System.out.println( "que hay en nom4???????????????"+nom4);
      //    System.out.println( "despues del set text");
      	
      //	String nom2 = 	jTextFieldNomBC.getText();
      	//	System.out.println( "despues del set text"+nomant);
      
        // frPare.remove(this);
         frPare.validate();
         frPare.repaint();
      }
   	
   	
   	
   	
   	
   	
   
       void jBttnAjuda_actionPerformed(ActionEvent e) {
         JOptionPane.showMessageDialog(this,"Ordre que s'utilitzará per presentar qualsevol resultat de l'aplicació.", "Ajuda per la ordenació de les variables", JOptionPane.INFORMATION_MESSAGE);
      }
   	
   	
       void jBttnExpo_actionPerformed(ActionEvent e){	
      //	BaseConeixement bc=gestor.obtenirBC(s);
         JFileChooser chooser = new JFileChooser();
         int id = -1; // identificador intern de la matriu de dades (ha de ser
      					// positiu)
         String matriu;
      	
      	// definimos todo lo necesario para el seleccionador de ficheros
         chooser.setCurrentDirectory(frPare.obtenirDirActual ());
         chooser.setDialogTitle("Desem actives");
         ExtensionFileFilter filter = new ExtensionFileFilter();
         filter.addExtension("act");
      	
         filter.setDescription("Fitxers de regles JavaKLASS");
         chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      	//chooser.setFileFilter(filter);
         int returnVal = chooser.showSaveDialog(frPare);
         if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String path = file.getPath();
            int i = path.lastIndexOf('.');
               // Per si s'introdueix el nom del fitxer sense extensió
            i = (i==-1)? path.length(): i;
            String extensio=path.substring(i);
            if(extensio.compareTo(".reg")==0){
               System.out.println("Entrem aqui "+extensio);
               matriu = path.substring(0,i);
            }
            else matriu=path;
         	
         	
            try {
               frPare.actualitzarBarraEstat("Desant les actives " + path
                  	+ "...", false);
            //	if(bc!=null)
               gestor.desarActivas(matriu, gestor.matriusCarregades[gestor.obteniractual()].obtenirActivas());
            //	else throw new Exception("BaseConeixement buida");
            	
               frPare.actualitzarTitolFinestra(path);
               frPare.actualitzarBarraEstat("Les actives s'han desat.", false);
            	
            } 
                catch (Exception ex) {
                  frPare.actualitzarBarraEstat("Les actives no s'han desat. "
                     + ex.getMessage(), true);
               }
         
         }
      }	
   	
   ///aca termina el exportar		
   	
   	
   	
   	
   	
   	
   	
   	
   	
   	
   	
   
   }
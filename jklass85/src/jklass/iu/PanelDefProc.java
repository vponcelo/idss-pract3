   package jklass.iu;

   import java.awt.Dimension;
   import java.awt.GridBagConstraints;
   import java.awt.GridBagLayout;
   import java.awt.Insets;
   import java.awt.event.ActionEvent;
   import java.awt.event.MouseAdapter;
   import java.awt.event.MouseEvent;
   import java.awt.event.MouseListener;
   import java.util.ArrayList;

   import javax.swing.BorderFactory;
   import javax.swing.DefaultComboBoxModel;
   import javax.swing.ImageIcon;
   import javax.swing.JButton;

   import javax.swing.WindowConstants;
   import javax.swing.border.BevelBorder;
   import javax.swing.border.TitledBorder;
   import javax.swing.DefaultListModel;
   import javax.swing.JFrame;
   import javax.swing.JLabel;
   import javax.swing.JList;
   import javax.swing.JOptionPane;
   import javax.swing.JPanel;
   import javax.swing.JScrollPane;
   import javax.swing.JTextField;
   import javax.swing.ListModel;

   import jklass.nucli.GestorKlass;
   import jklass.nucli.LlistaPropietats;
/** Classe que dibuixa el submenú per definir la recodificació de variables categòriques
*
* @author Laia Riera Guerra
* @version v.4 22/6/07
*/
/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
    public class PanelDefProc extends javax.swing.JPanel {
      private JPanel jPanel1;
      private JButton jButtonCancel;
      private JPanel jPanelDreta;
      private JButton jButtonTreureNovaModalitat;
      private JButton jButtonTreureNovaModalitat2;
   
      private JList jListModsNoves;
      private JScrollPane jScrollPaneModsNoves;
      private JScrollPane jScrollPaneModsNoves2;
   
      private JPanel jPanel6;
      private JPanel jPanel7;
      private JTextField jTextFieldNomVarNova = new JTextField();
      private JTextField jTextFieldNomVarNovaProc = new JTextField();
      private JButton jButtonAfegirModalitatNova;
      private JButton jButtonAfegirModalitatNova2;
      private JButton jButtonAfegirModalitatNova3;
   private JButton jButtonAfegirModalitatNova4;
      private JPanel jPanelAfegirModalitat;
      private JPanel jPanelAfegirModalitat2;
   
      private JList jListModsSelecc;
      private JScrollPane jScrollPaneModalitatsSelecc;
      private JPanel jPanelNomModalitat;
      private JPanel jPanel5;
      JTextField jTextFieldNomModalitatNova = new JTextField();
      private JLabel jLabelNomVar;
      private JLabel jLabelNomVar2;
      private JLabel jLabelNomProc;
      private JLabel jLabelProcesos;
   
      private JPanel jPanel4;
      private JPanel jPanelNomVarNova;
      private JPanel jPanelNomVarNova2;
      private JPanel jPanel3;
      private JButton jButtonTreureMod;
      private JButton jButtonAfegirMod;
      private JPanel jPanelButtons;
      private JList jListVarsSeleccionades;
      private JScrollPane jScrollPaneModalitats;
      private JTextField jTextFieldNomVarSelecc=new JTextField();
      private JPanel jPanelNomVarSelecc;
      private JPanel jPanelVarsSelecc;
      private JButton jButtonDreta;
      private JList jListVars;
      private JScrollPane jScrollPaneVars;
      private JButton jButtonOK;
      private JPanel jPanel2;
   //private JList jListVarsNumeriques=new JList();
      FrPrincipal frPare;
      GestorKlass gestor;	
   
      DefaultListModel listModelSN = new DefaultListModel();////ale
      JList jListSelcN = new JList(listModelSN);////ale
      DefaultListModel listModelVN = new DefaultListModel();//ale
   // JList jListVarsN = new JList(listModelVN);//ale
   
   
      DefaultListModel jListVarsModel=new DefaultListModel();
   
      JList jListVarsN = new JList(listModelVN);
   
   
      DefaultListModel jListVarsSeleccionadesModel=new DefaultListModel();
      DefaultListModel jListModsSeleccModel= new DefaultListModel();
      DefaultListModel jListModsNovesModel=new DefaultListModel();
      private boolean IS_OK;//ens indica si s'han guardat els canvis
      ArrayList alnomModalitats=new ArrayList();//cada posició es correspon amb un posició de l'array agrupacionsModals i conté el nom de la nova modalitat
      ArrayList alagrupacionsModals=new ArrayList();//a cada posició conté un vector de modalitats
   
      MouseListener mouseRegla =
          new MouseAdapter(){
         /**
         * Mostra les modalitats agrupades sota la nova modalitat seleccionada a la llista <code>Agrupació de modalitats</code>.
         * La modalitat seleccionada s'elimina de la llista <code>Modalitats de la nova variable</code>
         * @param e, event que detecta que s'ha fet doble clic sobre un element de la llista <code>Modalitats de la nova variable</code>
         */
             public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() == 2) {
                  int index = jListModsNoves.locationToIndex(e.getPoint());
                  Object ob=jListModsNoves.getSelectedValue();
                  jTextFieldNomModalitatNova.setText((String)ob);	     		
                  int imod=0;
                  boolean b=false;
                  for(int i=0;i<alnomModalitats.size() && !b;i++){
                     String saux=(String)alnomModalitats.get(i);
                     if(saux.compareTo((String)ob)==0){
                        imod=i;
                        b=true;
                     }
                  }
                  String[] modsSelec=(String[])alagrupacionsModals.get(imod);
                  jListModsSeleccModel.clear();
                  for(int j=0;j<modsSelec.length;j++){
                     jListModsSeleccModel.insertElementAt(modsSelec[j],j);
                  }
                  jListModsNovesModel.removeElementAt(imod);
                  alagrupacionsModals.remove(imod);
                  alnomModalitats.remove(imod);
               }
            }
         };
    
      MouseListener mouseSeleccionarVar=
          new MouseAdapter(){
         /**
         * Selecciona la nova variable a recodificar, posant les seves modalitats a la <code>Llista de modalitats</code> i el seu nom al camp
         * de <code>Variable seleccionada</code>
         * Si ja hi havia un altre variable seleccionada i s'havien efectuat canvis que no s'havien guardat, es mostra una finestra indicant que els canvis es perdran.
         * @param e, event que detecta que s'ha fet doble clic sobre un element de la llista <code>Variables categòriques</code>
         */
             public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() == 2) {
                  canviarPropietat();
               
               }
            }
         };
   
      MouseListener mouseAfegir=
          new MouseAdapter(){
         /**
         * Afegeix una nova modalitat a la llista de <code>Agrupació de modalitats</code> i l'elimina de la <code>Llista de modalitats</code>
         * @param e, event que detecta que s'ha fet doble clic sobre un element de la <code>Llista de modalitats</code>
         */
             public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() == 2) {
                  Object vars = jListVarsSeleccionades.getSelectedValue();	 		    
                  jListModsSeleccModel.addElement(vars);
                  jListVarsSeleccionadesModel.removeElement(vars);
                  IS_OK=false; 
               }
            }
         };
   
      MouseListener mouseTreure=
          new MouseAdapter(){
         /**
         * Afegeix una la modalitat seleccionada a la llista de <code>Agrupació de modalitats</code>a la <code>Llista de modalitats</code> i
         * l'elimina de la llista <code>Agrupació de modalitats</code>
         * @param e, event que detecta que s'ha fet doble clic sobre un element de la llista<code>Agrupació de modalitats</code>
         */
             public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() == 2) {
                  Object vars = jListModsSelecc.getSelectedValue();	 		    
                  jListVarsSeleccionadesModel.addElement(vars);
                  jListModsSeleccModel.removeElement(vars);
                  IS_OK=false; 
               }
            }
         };
    
    /**
     * Constructor
     * @param fr, finestra pare
     * @param gk, GestorKlass
     */
       public PanelDefProc(FrPrincipal fr,GestorKlass gk) {
         super();
         frPare = fr;
         gestor = gk;	
         IS_OK=true;
         jTextFieldNomModalitatNova.setText("");
         String s=gestor.nomPropietatPerDefecte("EST");
      
         jTextFieldNomVarNova.setText(s);
         jTextFieldNomVarNovaProc.setText("PROC0");
         String[][] llProps;
         int lon;
         llProps = gestor.obtenirLlistaIDsProps();
         lon = llProps[1].length;//la posició 0 conté les propietats numèriques
         for (int i = 0; i < lon; i++) {		    	
            jListVarsModel.insertElementAt(llProps[1][i], i);
         }
      	
         int lon2;	
         lon2 = llProps[0].length;//la posició 0 conté les propietats numèriques
         for (int i = 0; i < lon2; i++) {		    	
            jListVarsModel.insertElementAt(llProps[0][i], i);
         }
      
         try{
            initGUI();
         }
             catch(Exception ex){
               ex.printStackTrace();
            }
      }
   /**
    * Dibuixa el menú
    *
    */
       private void initGUI() {
         try {
            frPare.setSize(730, 510);
            frPare.centrar();
            this.setPreferredSize(new java.awt.Dimension(730, 430));
            GridBagLayout thisLayout = new GridBagLayout();
            thisLayout.rowWeights = new double[] {0.0, 0.1};
            thisLayout.rowHeights = new int[] {363, 7};
            thisLayout.columnWeights = new double[] {0.1};
            thisLayout.columnWidths = new int[] {7};
            this.setLayout(thisLayout);
            this.setBorder(BorderFactory.createTitledBorder(null, "Definir Estados ale", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
            {
               jPanel1 = new JPanel();
               GridBagLayout jPanel1Layout = new GridBagLayout();
               jPanel1Layout.rowWeights = new double[] {0.1};
               jPanel1Layout.rowHeights = new int[] {7};
               jPanel1Layout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.1};
               jPanel1Layout.columnWidths = new int[] {127,5, 1, 75, 7}; //ale cambié el segundo puse 5
               jPanel1.setLayout(jPanel1Layout);
               this.add(jPanel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
               {
                  jScrollPaneVars = new JScrollPane();
                  jScrollPaneVars.setAutoscrolls(true);
                  jPanel1.add(jScrollPaneVars, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                  jScrollPaneVars.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Llista Variables", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
                  {
                  /*ListModel jListVarsModel = new DefaultComboBoxModel(
                  	new String[] { "Item One", "Item Two" });*/
                     jListVars = new JList();
                     jScrollPaneVars.setViewportView(jListVarsN);/////////ale boludo aca carga el jListVarsN
                     jListVarsN.setModel(jListVarsModel);
                  //	jListVars.setPreferredSize(new java.awt.Dimension(239, 531));
                     jListVars.addMouseListener(mouseSeleccionarVar);
                  }
               }
               {//esta es la mano que estaba despues de Variables categoriques
                  jPanelDreta = new JPanel();
               //	jPanel1.add(jPanelDreta, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                  jPanelDreta.setLayout(null);
                  {
                     jButtonDreta = new JButton();
                     jPanelDreta.add(jButtonDreta); 
                     jButtonDreta.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
                     jButtonDreta.setBounds(7, 14, 56, 35);
                     jButtonDreta.addActionListener(
                            new java.awt.event.ActionListener() {
                               public void actionPerformed(ActionEvent e){
                                 jButtonDreta_actionPerformed(e);
                              }
                           });
                  }
               }
               {
                  jPanelVarsSelecc = new JPanel();
                  GridBagLayout jPanelVarsSeleccLayout = new GridBagLayout();
                  jPanelVarsSeleccLayout.rowWeights = new double[] {0.0, 0.1};
                  jPanelVarsSeleccLayout.rowHeights = new int[] {1, 7};
                  jPanelVarsSeleccLayout.columnWeights = new double[] {0.1};
                  jPanelVarsSeleccLayout.columnWidths = new int[] {1};
                  jPanelVarsSelecc.setLayout(jPanelVarsSeleccLayout);
                  jPanel1.add(jPanelVarsSelecc, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
               //alejandro comenté esto
                  {
                  //jPanelNomVarSelecc = new JPanel(); //desde aca esta comentado
                  //	jPanelVarsSelecc.add(jPanelNomVarSelecc, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                  //	jPanelNomVarSelecc.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Variable seleccionada", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
                  //	{
                  	/*jTextFieldNomVarSelecc = new JTextField();*/
                  //	jPanelNomVarSelecc.add(jTextFieldNomVarSelecc);
                  //	jTextFieldNomVarSelecc.setPreferredSize(new java.awt.Dimension(126, 20));
                  //	jTextFieldNomVarSelecc.setEditable(false);
                  //	} //hasta aca estaba comentado
                  }
                  {
                     jScrollPaneModalitats = new JScrollPane();
                  //	jScrollPaneModalitats.setAutoscrolls(true);
                  //	jPanelVarsSelecc.add(jScrollPaneModalitats, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                  //	jScrollPaneModalitats.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Llista", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
                     {
                     /*ListModel jListVarsSeleccionadesModel = new DefaultComboBoxModel(
                     	new String[] { "Item One", "Item Two" });*/
                        jListVarsSeleccionades = new JList();
                        jScrollPaneModalitats
                           .setViewportView(jListVarsSeleccionades);
                        jListVarsSeleccionades
                           .setModel(jListVarsSeleccionadesModel);
                        jListVarsSeleccionades.setPreferredSize(new java.awt.Dimension(237, 526));
                        jListVarsSeleccionades.addMouseListener(mouseAfegir);
                     }
                  }
               }
               {
                  jPanelButtons = new JPanel();
                  jPanel1.add(jPanelButtons, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                  jPanelButtons.setLayout(null);
                  {
                     jButtonAfegirMod = new JButton();
                     jPanelButtons.add(jButtonAfegirMod);//Primer mano pone de izq a derecha
                     jButtonAfegirMod.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
                     jButtonAfegirMod.setBounds(7, 54, 63, 42);
                     jButtonAfegirMod.addActionListener(
                            new java.awt.event.ActionListener() {
                               public void actionPerformed(ActionEvent e){
                                 jButtonAfegirMod_actionPerformed(e); /////////este es 
                              }
                           });
                  }
                  {
                     jButtonTreureMod = new JButton();
                     jPanelButtons.add(jButtonTreureMod); //Primer mano saca
                     jButtonTreureMod.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
                     jButtonTreureMod.setBounds(1, 245, 63, 42);
                     jButtonTreureMod.addActionListener(
                            new java.awt.event.ActionListener() {
                               public void actionPerformed(ActionEvent e){
                                 jButtonTreureMod_actionPerformed(e);
                              }
                           });
                  }
               }
              
               { //ACA EMPIEZA JPANEL3
                  jPanel3 = new JPanel();
                  GridBagLayout jPanel3Layout = new GridBagLayout();
                  jPanel3Layout.rowWeights = new double[] {0.0,0.1};
                  jPanel3Layout.rowHeights = new int[] {34,7};
                  jPanel3Layout.columnWeights = new double[] {0.1};
                  jPanel3Layout.columnWidths = new int[] {7};
                  jPanel3.setLayout(jPanel3Layout);
                  jPanel1.add(jPanel3, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                  jPanel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED), "Resultadoss", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
                  {
                     jPanelNomVarNova = new JPanel();
                     jPanel3.add(jPanelNomVarNova, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                     jPanelNomVarNova.setLayout(null);
                     {
                        jLabelNomVar = new JLabel();
                        jLabelNomProc = new JLabel();
                        jLabelProcesos = new JLabel();
                        jPanelNomVarNova.add(jLabelNomVar);
                        jPanelNomVarNova.add(jLabelNomProc);
                   
                        jLabelNomVar.setText("Estat:");
                        jLabelNomProc.setText("Procés:");
                        jLabelProcesos.setText("Procesos");
                        jLabelNomVar.setBounds(7, 10, 70, 14);
                        jLabelNomProc.setBounds(270, 10, 70, 14);
                        jLabelProcesos.setBounds(650, 10, 70, 14);
                     }
                     {
                     /*jTextFieldNomVarNova = new JTextField();*/
                        jPanelNomVarNova.add(jTextFieldNomVarNova);
                        jTextFieldNomVarNova.setBounds(50, 7, 80, 21);
                        jPanelNomVarNova.add(jTextFieldNomVarNovaProc);
                        jTextFieldNomVarNovaProc.setBounds(320, 7, 80, 21);
                     
                     }
                  }
                  {
                     jPanel4 = new JPanel();
                     GridBagLayout jPanel4Layout = new GridBagLayout();
                     jPanel4Layout.rowWeights = new double[] {0.1};
                     jPanel4Layout.rowHeights = new int[] {7};
                     jPanel4Layout.columnWeights = new double[] {0.0, 0.0};
                     jPanel4Layout.columnWidths = new int[] {150, 80,200};
                     jPanel4.setLayout(jPanel4Layout);
                     jPanel3.add(jPanel4, new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                     {
                        jPanel5 = new JPanel();
                        GridBagLayout jPanel5Layout = new GridBagLayout();
                        jPanel5Layout.rowWeights = new double[] {0.0, 0.1};
                        jPanel5Layout.rowHeights = new int[] {4, 7};
                        jPanel5Layout.columnWeights = new double[] {0.1};
                        jPanel5Layout.columnWidths = new int[] {7};
                        jPanel5.setLayout(jPanel5Layout);
                        jPanel4.add(jPanel5, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                     
                        {
                           jScrollPaneModalitatsSelecc = new JScrollPane();
                           jScrollPaneModalitatsSelecc.setAutoscrolls(true);
                           jPanel5.add(jScrollPaneModalitatsSelecc, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                           jScrollPaneModalitatsSelecc.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Variables d'estat", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
                           jScrollPaneModalitatsSelecc.setPreferredSize(new java.awt.Dimension(170, 247));
                           {
                           /*ListModel jListModsSeleccModel = new DefaultComboBoxModel(
                           	new String[] { "Item One", "Item Two" });*/
                              jListModsSelecc = new JList();
                              jScrollPaneModalitatsSelecc.setViewportView(jListModsSelecc);
                           
                              jListModsSelecc.setModel(jListModsSeleccModel);// alejandro
                           //	jListModsSelecc.setModel(jListVarsModel);// alejan
                              jListModsSelecc.setPreferredSize(new java.awt.Dimension(219, 438));
                              jListModsSelecc.addMouseListener(mouseTreure);
                           }
                        }           	
                     }//Cierra el panel5
                  	///////////aca empieza con el primer boton
                    
                     {//abre bloque botones
                        jPanelAfegirModalitat = new JPanel();
                        jPanel4.add(jPanelAfegirModalitat, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                        jPanelAfegirModalitat.setLayout(null);
                        {//abre bloque boton1
                           jButtonAfegirModalitatNova = new JButton();//segunda mano de izq a derecha
                           jPanelAfegirModalitat.add(jButtonAfegirModalitatNova);
                           jButtonAfegirModalitatNova.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
                           jButtonAfegirModalitatNova.setBounds(14, 60, 63, 35);
                           jButtonAfegirModalitatNova.addActionListener(
                                  new java.awt.event.ActionListener() {
                                     public void actionPerformed(ActionEvent e){
                                       jButtonAfegirModalitatNova_actionPerformed(e);
                                    }
                                 });
                        }//cierra bloque boton1
                        {//abre bloque boton 2
                           jButtonTreureNovaModalitat = new JButton();  //segunda mano de derecha a izquierda
                           jPanelAfegirModalitat.add(jButtonTreureNovaModalitat);
                           jButtonTreureNovaModalitat.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
                           jButtonTreureNovaModalitat.setBounds(14, 156, 63, 35);
                           jButtonTreureNovaModalitat.addActionListener(
                                  new java.awt.event.ActionListener() {
                                     public void actionPerformed(ActionEvent e){
                                       jButtonTreureNovaModalitat_actionPerformed(e);
                                    }
                                 });
                        }//cieera bloque boton2
                     
                     }//cierra bloque botones
                  	
                  	
                                    	
                    
                     {//empieza el panel6
                        jPanel6 = new JPanel();
                        GridBagLayout jPanel6Layout = new GridBagLayout();
                        jPanel6Layout.rowWeights = new double[] {0.0};
                        jPanel6Layout.rowHeights = new int[] {300};
                        jPanel6Layout.columnWeights = new double[] {0.1};
                        jPanel6Layout.columnWidths = new int[] {7};
                        jPanel6.setLayout(jPanel6Layout);
                        jPanel4.add(jPanel6, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                        { 
                           jScrollPaneModsNoves = new JScrollPane();
                           jScrollPaneModsNoves.setAutoscrolls(true);
                           //jPanel6.add(jScrollPaneModsNoves, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                           jPanel6.add(jScrollPaneModsNoves, new GridBagConstraints(0, 0, 2, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

									jScrollPaneModsNoves.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Llista d'estats", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog",0,10)));
                           jScrollPaneModsNoves.setPreferredSize(new java.awt.Dimension(185, 243)); //panel lista estados
                        
                           jListModsNoves = new JList();
                           jScrollPaneModsNoves.setViewportView(jListModsNoves);
                           jListModsNoves.setModel(jListModsNovesModel);
                           jListModsNoves.setPreferredSize(new java.awt.Dimension(331, 250));
                           jListModsNoves.addMouseListener(mouseRegla);
                        
                                               
                          /* jButtonAfegirModalitatNova3 = new JButton();//tercera mano de izq a derecha      
                           jPanel4.add(jButtonAfegirModalitatNova3);          
                           jButtonAfegirModalitatNova3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
                           jButtonAfegirModalitatNova3.setBounds(0, 15, 63, 35);*/
								/////
								
								 {//abre bloque botones
                      //  jPanelAfegirModalitat2 = new JPanel();
                       // jPanel4.add(jPanelAfegirModalitat2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                       // jPanelAfegirModalitat2.setLayout(null);
                        {//abre bloque boton1
                           jButtonAfegirModalitatNova2 = new JButton();//segunda mano de izq a derecha
                           jPanel4.add(jButtonAfegirModalitatNova2);
                           jButtonAfegirModalitatNova2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
                           jButtonAfegirModalitatNova2.setBounds(254, 10, 63, 35);
      
                         }//cierra bloque boton1
								 
								 
								 
								 
								}
		
								/////	
								
									    
                        
                        } //
                     		
                     }//termina el panel6
							
													
                  }
               
               } //ACA TERMINA JPANEL3
            
            }
         						
         	
            {
            //
            /* jButtonAfegirModalitatNova3 = new JButton();//tercera mano de izq a derecha      
             jPanel4.add(jButtonAfegirModalitatNova3);          
             jButtonAfegirModalitatNova3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
             jButtonAfegirModalitatNova3.setBounds(260, 7, 63, 35); */ 
                       
            /////////
				
				
				
				////////
            
               jPanel2 = new JPanel();
               this.add(jPanel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
               jPanel2.setLayout(null);
               {
                  jButtonOK = new JButton();
                  jPanel2.add(jButtonOK);
                  jButtonOK.setText("D'acord");
                  jButtonOK.setBounds(152, 7, 77, 28);
                  jButtonOK.addActionListener(
                         new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e){
                              jButtonOK_actionPerformed(e);
                           }
                        });
               }
               {
                  jButtonCancel = new JButton();
                  jPanel2.add(jButtonCancel);
                  jButtonCancel.setText("Cancel.la");
                  jButtonCancel.setBounds(546, 7, 98, 28);
                  jButtonCancel.addActionListener(
                         new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e){
                              jButtonCancel_actionPerformed(e);
                           }
                        });
               }
            }
         } 
             catch (Exception e) {
               e.printStackTrace();
            }
      }
   /**
    * Mostra les modalitats agrupades sota la nova modalitat seleccionada a la llista <code>Agrupació de modalitats</code>.
    * La modalitat seleccionada s'elimina de la llista <code>Modalitats de la nova variable</code>
    * @param e, event que detecta que s'ha premut sobre el botó <code>modificar una modalitat ja creada( mà cap a l'esquerra)</code>
    */
       protected void jButtonTreureNovaModalitat_actionPerformed(ActionEvent e) {
         System.out.println( "este las lleva a la izquierda eh?");
         try{
            Object ob=jListModsNoves.getSelectedValue();
            jTextFieldNomModalitatNova.setText((String)ob);	     		
            int imod=0;
            boolean b=false;
            System.out.println( "alnomModalitats está vacio= "+alnomModalitats.size());
            for(int i=0;i<alnomModalitats.size() && !b;i++){
               String saux=(String)alnomModalitats.get(i);
               System.out.println( "que hay en alnomModalitatsmio "+saux);
               if(saux.compareTo((String)ob)==0){
                  imod=i;
                  b=true;
               }
            }
            String[] modsSelec=(String[])alagrupacionsModals.get(imod);
         //	jListModsSeleccModel.clear();
         
            for(int j=0;j<modsSelec.length;j++){
               jListModsSeleccModel.insertElementAt(modsSelec[j],j);
               System.out.println( "que hay en modsSelecmio "+	modsSelec[j]);
            }
            jListModsNovesModel.removeElementAt(imod); //borra el nombre del estado
            alagrupacionsModals.remove(imod);
            alnomModalitats.remove(imod);
         
            frPare.actualitzarBarraEstat("",false);
         }
             catch(IndexOutOfBoundsException ex){
               frPare.actualitzarBarraEstat("Ale No se ha seleccionado nada",true);
            }		
      }
   /**
    * Tanca la finestra
    * @param e, event que detecta que s'ha premut el botó <code>Cancel.la</code>
    */
       protected void jButtonCancel_actionPerformed(ActionEvent e) {
         frPare.remove(this);
         frPare.validate();
         frPare.repaint();		
      }
   
   /**
    * Crea la nova variable recodificada
    * @throws Exception
    */
       public void crearVariable()throws Exception{
         String nomPropNova=jTextFieldNomVarNova.getText();
         String nomPropAntiga=jTextFieldNomVarSelecc.getText();		
         if(!jListVarsSeleccionadesModel.isEmpty()){
            int answer=JOptionPane.showConfirmDialog(frPare,"La variable resultant contindrà dades mancats.Desitja continuar?","La variable resultant contindrà dades mancats.Desitja continuar?",JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
               if(nomPropNova.compareTo("")==0||nomPropNova==null){
                  throw new Exception("No s'ha introduït un nom de variable");
               }
               else{
                  try{
                     gestor.crearNovaPropietat(nomPropNova,nomPropAntiga,alnomModalitats,alagrupacionsModals);
                     IS_OK=true;
                     frPare.actualitzarBarraEstat("S'ha creat la nova variable correctament",false);
                  }
                      catch(Exception ex){
                        frPare.actualitzarBarraEstat("No s'ha pogut crear la nova variable: "+ex.getMessage(),true);
                        ex.printStackTrace();
                     }
               }
            }
         }
         else{
            if(nomPropNova.compareTo("")==0||nomPropNova==null){
               frPare.actualitzarBarraEstat("No s'ha introduït un nom de variable", true);
            }
            else{
               try{
                  gestor.crearNovaPropietat(nomPropNova,nomPropAntiga,alnomModalitats,alagrupacionsModals);
                  IS_OK=true;
                  frPare.actualitzarBarraEstat("S'ha creat la nova variable correctament",false);
               }
                   catch(Exception ex){
                     frPare.actualitzarBarraEstat("No s'ha pogut crear la nova variable: "+ex.getMessage(),true);
                     ex.printStackTrace();
                  }
            }
         }		
      }
   
   /**
    * Crea la nova variable recodificada
    * @param e, event que detecta que s'ha premut el botó <code>D'acord</code>
    */
       protected void jButtonOK_actionPerformed(ActionEvent e) {
         try{
            crearVariable();	
            String nom=gestor.nomPropietatPerDefecte("VAR");
            jTextFieldNomVarNova.setText(nom);
         }
             catch(Exception ex){
               frPare.actualitzarBarraEstat(ex.getMessage(),true);
            }
      	
      }
   /**
    * Afegeix la modalitat del camp <code>Nom de la nova modalitat</code> al llistat </code>Modalitats de la nova variable</code>
    * i buida la llista <code>Agrupació de modalitats</code> i el camp <code>Nom de la nova modalitat</code>
    * @param e, event que detecta que s'ha premut el botó <code>Crear nova modalitat(mà cap a la dreta)</code>
    */
   
       protected void jButtonAfegirModalitatNova_actionPerformed(ActionEvent e) {		
         String nomModalitat=jTextFieldNomVarNova.getText();
      //este los lleva a la derecha
         if(nomModalitat.compareTo("")==0||nomModalitat==null){
            frPare.actualitzarBarraEstat("No s'ha introduït un nom de modalitat", true);}
         else{
         
         ///
            int numMods=jListModsSeleccModel.getSize();
            String[] mods=new String[numMods];
            for(int i=0;i<numMods;i++){
               mods[i]=(String)jListModsSeleccModel.getElementAt(i);
               System.out.println( "ESTAS ACA?"+mods[i] );
            }	
          	      
         
         //	System.out.println( "nommodalitat"+nomModalitat );	
         
            System.out.println( "Este es el que los lleva  ala derecha");
         
            jTextFieldNomVarNova.setText(""); // este es el que limpia el campo de texto
         
         	//	jPanelNomModalitat.add(jTextFieldNomVarNova);
         
         
            jListModsNovesModel.addElement(nomModalitat);//  este es el que lleva el nombre a la derecha
            alnomModalitats.add(nomModalitat);
            alagrupacionsModals.add(mods);
         
            jListModsSeleccModel=new DefaultListModel();
            jScrollPaneModalitatsSelecc.setViewportView(jListModsSelecc);// estos 3 limpian las variables
            jListModsSelecc.setModel(jListModsSeleccModel);
         
         }
      
      }	
   /**
    * Indica si ja existeix al llistat <code>Modalitats de la nova variable</code> una modalitat de nom <code>smod</code>
    * @param smod, nom de la modalitat a comprovar
    * @return -1 si no existeix, la posició de la modalitat dins la llista altrament
    */
       public int existeixNomModalitat(String smod){
         int res=-1;
         for(int i=0;i<alnomModalitats.size()&& res==-1;i++){
            String s=(String)alnomModalitats.get(i);
            if(s.compareTo(smod)==0)res=i;
         }
         return res;
      }
   /**
    * Afegeix les modalitats seleccionades de la llista <code>Agrupació de modalitats</code> a la llista
    * <code>Llista de modalitats</code> iles elimina del llistat <code>Agrupació de modalitats</code>
    * @param e, event que detecta que s'ha premut el botó <code>Treure modalitats ( mà cap a l'esquerra) </code>
    */
       protected void jButtonTreureMod_actionPerformed(ActionEvent e) {
         IS_OK=false;
      
         Object[] vars = jListModsSelecc.getSelectedValues();
         boolean hay_mas_vars = true;
         int n = 0;
         System.out.println( "este es el sacar bolas?");
         do {
            try {
            	
               jListModsSeleccModel.removeElement(vars[n]);
               jListVarsModel.addElement(vars[n]);
               n++;
            }
                catch (Exception exc) { hay_mas_vars = false; }
         } while (hay_mas_vars);
      
      }
   /**
    * Afegeix les modalitats seleccionades de la llista <code>Llista de modalitats</code> al llistat <code>Agrupació de modalitats</code> i les
    * elimina de la <code>Llista de modalitats</code> 
    * @param e, event que detecta que s'ha premut el botó <code>Afegir modalitats(mà cap a la dreta)</code>
    */
       protected void jButtonAfegirMod_actionPerformed(ActionEvent e) {
         Object[] vars = jListVarsN.getSelectedValues();
         boolean hay_mas_vars = true;
         int n = 0;
         System.out.println( "entre en222 jButtonafegirModboludo");
         System.out.println( "holaaa que hay en vars[0] aca?"+vars[0]);
         do {
            try {
            // System.out.println( "que hay en vars[0] antes?"+vars[0]);
               jListModsSeleccModel.addElement(vars[n]);
               jListVarsModel.removeElement(vars[n]);
            // System.out.println( "que hay en vars[0] despues?"+vars[0]);
            
               n++;
            }
                catch (Exception exc) { hay_mas_vars = false; }
         } while (hay_mas_vars);
      }
   /**
    * Canvia la propietat que s'estava recodificant per la nova variable seleccionada
    * Si els canvis no s'havien guardat, es mostra un missatge indicant que les modificacions es perdran
    * 
    */
       public void canviarPropietat(){
         if(!IS_OK){
            int answer=JOptionPane.showConfirmDialog(frPare,"Es perdran els canvis efectuats a la variable, desitja crear la nova variable?","Es perdran els canvis efectuats a la variable, desitja crear la nova variable?",JOptionPane.YES_NO_CANCEL_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
               try{   			 
                  crearVariable();	
                  alagrupacionsModals=new ArrayList();
                  alnomModalitats=new ArrayList();
                  Object obj=jListVars.getSelectedValue();
                  jTextFieldNomVarSelecc.setText((String)obj);
                  jPanelNomVarSelecc.add(jTextFieldNomVarSelecc);
                  String[] modalitats=gestor.obtenirLlistaMods((String)obj);
                  jListVarsSeleccionadesModel.clear();
                  for(int i=0;i<modalitats.length;i++){
                     jListVarsSeleccionadesModel.insertElementAt(modalitats[i],i);
                  }
                  jScrollPaneModalitats.setViewportView(jListVarsSeleccionades);
                  jListVarsSeleccionades.setModel(jListVarsSeleccionadesModel);
                  String nom=gestor.nomPropietatPerDefecte("VAR");
                  jTextFieldNomVarNova.setText(nom);
               
                  jTextFieldNomModalitatNova.setText("");
               
                  jListModsSeleccModel=new DefaultListModel();
                  jScrollPaneModalitatsSelecc.setViewportView(jListModsSelecc);
                  jListModsSelecc.setModel(jListModsSeleccModel);
                  jListModsNovesModel=new DefaultListModel();
                  jScrollPaneModsNoves.setViewportView(jListModsNoves);
                  jListModsNoves.setModel(jListModsNovesModel);
               
               }
                   catch(Exception ex){
                     frPare.actualitzarBarraEstat(ex.getMessage(),true);
                  }
            	
            } 
            else if (answer == JOptionPane.NO_OPTION) {
                	
               alagrupacionsModals=new ArrayList();
               alnomModalitats=new ArrayList();
               Object obj=jListVars.getSelectedValue();
               jTextFieldNomVarSelecc.setText((String)obj);
               jPanelNomVarSelecc.add(jTextFieldNomVarSelecc);
               String[] modalitats=gestor.obtenirLlistaMods((String)obj);
               jListVarsSeleccionadesModel.clear();
               for(int i=0;i<modalitats.length;i++){
                  jListVarsSeleccionadesModel.insertElementAt(modalitats[i],i);
               }
               jScrollPaneModalitats.setViewportView(jListVarsSeleccionades);
               jListVarsSeleccionades.setModel(jListVarsSeleccionadesModel);
               String nom=gestor.nomPropietatPerDefecte("VAR");
               jTextFieldNomVarNova.setText(nom);
               jPanelNomVarNova.add(jTextFieldNomVarNova);
               jTextFieldNomModalitatNova.setText("");
               jPanelNomModalitat.add(jTextFieldNomModalitatNova);
               jListModsSeleccModel=new DefaultListModel();
               jScrollPaneModalitatsSelecc.setViewportView(jListModsSelecc);
               jListModsSelecc.setModel(jListModsSeleccModel);
               jListModsNovesModel=new DefaultListModel();
               jScrollPaneModsNoves.setViewportView(jListModsNoves);
               jListModsNoves.setModel(jListModsNovesModel);
            		
            } 
         }
         else{
            alagrupacionsModals=new ArrayList();
            alnomModalitats=new ArrayList();
            Object obj=jListVars.getSelectedValue();
            jTextFieldNomVarSelecc.setText((String)obj);
            jPanelNomVarSelecc.add(jTextFieldNomVarSelecc);
            String[] modalitats=gestor.obtenirLlistaMods((String)obj);
            jListVarsSeleccionadesModel.clear();
            for(int i=0;i<modalitats.length;i++){
               jListVarsSeleccionadesModel.insertElementAt(modalitats[i],i);
            }
            jScrollPaneModalitats.setViewportView(jListVarsSeleccionades);
            jListVarsSeleccionades.setModel(jListVarsSeleccionadesModel);
            String nom=gestor.nomPropietatPerDefecte("VAR");
            jTextFieldNomVarNova.setText(nom);
            jPanelNomVarNova.add(jTextFieldNomVarNova);
            jTextFieldNomModalitatNova.setText("");
            jPanelNomModalitat.add(jTextFieldNomModalitatNova);
            jListModsSeleccModel=new DefaultListModel();
            jScrollPaneModalitatsSelecc.setViewportView(jListModsSelecc);
            jListModsSelecc.setModel(jListModsSeleccModel);
            jListModsNovesModel=new DefaultListModel();
            jScrollPaneModsNoves.setViewportView(jListModsNoves);
            jListModsNoves.setModel(jListModsNovesModel);
         	
         }
      	
      }
   
   /**
     * Selecciona la nova variable a recodificar, posant les seves modalitats a la <code>Llista de modalitats</code> i el seu nom al camp
     * de <code>Variable seleccionada</code>
     * Si ja hi havia un altre variable seleccionada i s'havien efectuat canvis que no s'havien guardat, es mostra una finestra indicant que els canvis es perdran.
     * @param e, event que detecta que s'ha premut el botó <code>Seleccionar la variable a recodificar(mà cap a la dreta)</code>
     */
       protected void jButtonDreta_actionPerformed(ActionEvent e) {
      //	this.canviarPropietat();
         System.out.println( "entre en el ButtonDreta!!!ale");
      
      
      }
   
   
   
   
   
   
   }

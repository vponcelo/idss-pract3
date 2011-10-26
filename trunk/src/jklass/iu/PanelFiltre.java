   package jklass.iu;

   import java.awt.*;
   import java.awt.event.*;
   import java.io.File;
   import java.io.IOException;
   import java.lang.InterruptedException;
   import java.util.logging.*;
   import java.util.ArrayList;
   import java.util.Vector;
   import javax.swing.*;
   import javax.swing.border.*;
   import javax.swing.ImageIcon;

   import jklass.nucli.BaseConeixement;
   import jklass.nucli.Dada;
   import jklass.nucli.ExpressioBooleana;
   import jklass.nucli.GestorKlass;
   import jklass.nucli.PropNumerica;
   import jklass.nucli.Propietat;
   import jklass.util.Opcions;
   import jklass.util.SO;
   import java.util.StringTokenizer;
   import jklass.nucli.LlistaObjectes;


/** Classe que dibuixa el submenú per seleccionar els objectes i variables d'una nova submatriu
*
* @author Laia Riera Guerra i Jose I Mateos
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


    public class PanelFiltre extends JPanel {
   
      private static Logger logger=Logger.getLogger(PanelFiltre.class.getName());
      private BorderLayout borderLayout1 = new BorderLayout();
      private BorderLayout borderLayout2 = new BorderLayout();
      private BorderLayout borderLayout3 = new BorderLayout();
      private BorderLayout borderLayout4 = new BorderLayout();
      private BorderLayout borderLayout5 = new BorderLayout();
      private BorderLayout borderLayout6 = new BorderLayout();
      private BorderLayout borderLayout7 = new BorderLayout();
      private BorderLayout borderLayout8 = new BorderLayout();
      private FrPrincipal frPare;
      private GestorKlass gestor;
      private Border border1;
      private Border border2;
      private TitledBorder titledBorder1;
      private TitledBorder titledBorder3;
      private TitledBorder titledBorder4;
      private TitledBorder titledBorder5;
      private TitledBorder titledBorder6;
      private TitledBorder titledBorder7;
      private TitledBorder titledBorder8;
      private TitledBorder titledBorder9;
      private TitledBorder titledBorder10;
      private TitledBorder titledBorder11;
      private JPanel jpanelVarNum = new JPanel();
      private JPanel jpanelVarCat = new JPanel();
      private JPanel jpanelSelecNum = new JPanel();
      private JPanel jpanelSelecCat = new JPanel();
      private JPanel jpanelBtnsN = new JPanel();
      private JPanel jpanelBtnsC = new JPanel();
      private JPanel jpanelObj = new JPanel();
      private JPanel jpanelSelecO = new JPanel();
      private JPanel jpanelBtnsO = new JPanel();
      private JPanel panReset =new JPanel();
      private JPanel panBoton =new JPanel();
      private JPanel panDown =new JPanel();
      private JPanel panIz =new JPanel();
      private DefaultListModel listModelVN = new DefaultListModel();
      private DefaultListModel listModelSN = new DefaultListModel();
      private DefaultListModel listModelVC= new DefaultListModel();
      private DefaultListModel listModelSC= new DefaultListModel();
      private DefaultListModel listModelObj= new DefaultListModel();
      private DefaultListModel listModelSObj= new DefaultListModel();
      private JList jListVarsN = new JList(listModelVN);
      private JList jListSelcN = new JList(listModelSN);
      private JList jListVarsC = new JList(listModelVC);
      private JList jListSelcC = new JList(listModelSC);
      private JList jListObj = new JList(listModelObj);
      private JList jListSelcObj = new JList(listModelSObj);
      private JScrollPane jSPObj = new JScrollPane(jListObj);
      private JScrollPane jSPSelecObj = new JScrollPane(jListSelcObj);
      private JScrollPane jSPVarsN = new JScrollPane(jListVarsN);
      private JScrollPane jSPSelecN = new JScrollPane(jListSelcN);
      private JScrollPane jSPVarsC = new JScrollPane(jListVarsC);
      private JScrollPane jSPSelecC = new JScrollPane(jListSelcC);
      private JScrollPane jScrollPaneExpressio=new JScrollPane();
      private JButton jBttnAfegirN = new JButton();
      private JSeparator jSeparator1;
      private JButton jButtonClear=new JButton();
      private JButton jButtonPertany=new JButton();
      private JButton jButtonTancarClaudator=new JButton();
      private JButton jButtonObrirClaudator=new JButton();
      private JButton jButtonOR=new JButton();
      private JButton jButtonNOT=new JButton();
      private JButton jButtonAND=new JButton();
      private JButton jButtonTancarParentesis=new JButton();
      private JButton jButtonDiferent=new JButton();
      private JButton jButtonMajorIgual=new JButton();
      private JButton jButtonMenorIgual=new JButton();
      private JButton jButtonObrirParentesis=new JButton();
      private JButton jButtonIgual=new JButton();
      private JButton jButtonMajor=new JButton();
      private JButton jButtonMenor=new JButton();
      private JPanel jPanelBotons;
      private JTextArea jTextAreaExpressio=new JTextArea();
   
      private JPanel jPanelEditor;
      private JButton jButtonDreta=new JButton();
      private JPanel jPanelDreta;
      private JButton jButtonConsultarValors=new JButton();
      private DefaultListModel jListVarsModel = new DefaultListModel();
      private JList jListPropietats=new JList(jListVarsModel);
      private JScrollPane jScrollPaneVars = new JScrollPane(jListPropietats);
      private JPanel jPanelVars;
      private JPanel jPanelCondicions;
      private JRadioButton jRadioButtonCondicions=new JRadioButton();
      private JRadioButton jRadioButtonLLExplicita=new JRadioButton();
      private JPanel jPanel1;
      private JButton jBttnTreureN = new JButton();
      private JButton jBttnAfegirC = new JButton();
      private JButton jBttnTreureC = new JButton();
      private JButton jBttnAfegirO = new JButton();
      private JButton jBttnTreureO = new JButton();
      private JButton bAcep = new JButton();
      private JButton bCancel = new JButton();
      private JButton bReset = new JButton();
      private FlowLayout flowLayout1 = new FlowLayout();
      GridBagLayout flowLayout2 = new GridBagLayout();
      private String nomFitxer = null;
      private int matriuCarregada;
      private int matriuActual;
      BaseConeixement bc;
   
   /**
   * Contructor
   *
   * @param fr és la finestra pare
   * @param gk és el gestor d'on penja la matriu
   */
       public PanelFiltre(FrPrincipal fr,GestorKlass gk) {
      
         frPare = fr;
         gestor = gk;
         String[][] llProps;
         jRadioButtonLLExplicita.setSelected(true);
         jRadioButtonCondicions.setSelected(false);
      
         desactivarCondicions();
         activarExplicita();
      
      
         try {
            jbInit();
         }
             catch(Exception ex) {
               ex.printStackTrace();
            }
      }
   /**Creació del menú
   * @throws Exception
   */
       private void jbInit() throws Exception {
      
         matriuCarregada=gestor.idMatriuActual();
      
         frPare.setSize(870,600);
         frPare.centrar();
         llistarVariables();
         border1 = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
         titledBorder1 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158)),"Selecciò d'una submatriu");
         titledBorder3 = new TitledBorder(border1,"Vars. numèriques");
         titledBorder4 = new TitledBorder(border1,"Vars. categòriques");
         border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158));
         titledBorder5 = new TitledBorder(border2,"Llista de vars.");
         titledBorder6 = new TitledBorder(border2,"Vars. seleccionades");
         titledBorder7 = new TitledBorder(border1,"Objectes");
         titledBorder8 = new TitledBorder(border2,"Llista d'objectes");
         titledBorder9 = new TitledBorder(border2,"Objectes seleccionats");
         titledBorder10=new TitledBorder(border2,"Llista vars.");
         titledBorder11=new TitledBorder(border2,"Expressió lògica");
         this.setLayout(borderLayout1);
         panIz.setLayout(borderLayout8);
         jpanelObj.setLayout(borderLayout6);
         jpanelObj.setBorder(titledBorder7);
         jSPSelecObj.setAutoscrolls(true);
         jSPSelecObj.setBorder(titledBorder9);
      
      
         jSPSelecObj.setPreferredSize(new Dimension(115, 153));
         jBttnTreureO.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
         jBttnTreureO.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnTreureO_actionPerformed(e);
                  }
               });
         jpanelBtnsO.setLayout(borderLayout7);
         jBttnAfegirO.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
         jBttnAfegirO.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnAfegirO_actionPerformed(e);
                  }
               });
         jSPObj.getViewport().setBackground(Color.white);
         jSPObj.setAutoscrolls(true);
         jSPObj.setBorder(titledBorder8);
         jSPObj.setPreferredSize(new java.awt.Dimension(115, 153));
         jListObj.setBorder(null);
         jpanelVarNum.setLayout(borderLayout5);
         jpanelVarCat.setLayout(borderLayout2);
         jpanelVarNum.setBorder(titledBorder3);
         jSPSelecN.setAutoscrolls(true);
         jSPSelecN.setBorder(titledBorder6);
         jSPSelecN.setPreferredSize(new java.awt.Dimension(115, 161));
         jBttnTreureN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
         jBttnTreureN.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnTreureN_actionPerformed(e);
                  }
               });
         jpanelBtnsN.setLayout(borderLayout4);
         jBttnAfegirN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
         jBttnAfegirN.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnAfegirN_actionPerformed(e);
                  }
               });
         jSPVarsN.getViewport().setBackground(Color.white);
         jSPVarsN.setAutoscrolls(true);
         jSPVarsN.setBorder(titledBorder5);
         jSPVarsN.setPreferredSize(new java.awt.Dimension(100, 158));
         jListVarsN.setBorder(null);
         jBttnTreureC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
         jBttnTreureC.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnTreureC_actionPerformed(e);
                  }
               });
         jListVarsC.setBorder(null);
         jSPVarsC.setBorder(titledBorder5);
         jSPVarsC.setPreferredSize(new java.awt.Dimension(100, 197));
         jSPVarsC.setAutoscrolls(true);
         jSPSelecC.setBorder(titledBorder6);
         jSPSelecC.setPreferredSize(new java.awt.Dimension(115, 193));
         jSPSelecC.setAutoscrolls(true);
         jpanelBtnsC.setLayout(borderLayout3);
         jBttnAfegirC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
         jBttnAfegirC.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnAfegirC_actionPerformed(e);
                  }
               });
         jpanelVarCat.setBorder(titledBorder4);
         jpanelSelecNum.setLayout(flowLayout1);
         jpanelSelecO.setLayout(flowLayout2);
         this.setBorder(titledBorder1);
         this.setDebugGraphicsOptions(0);
         this.setPreferredSize(new java.awt.Dimension(852, 501));
      
         jpanelSelecO.add(jSPObj, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
         jpanelSelecO.add(jpanelBtnsO, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
         jpanelBtnsO.add(jBttnAfegirO, BorderLayout.NORTH);
         jpanelBtnsO.add(jBttnTreureO, BorderLayout.SOUTH);
         jpanelSelecO.add(jSPSelecObj, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
         {
         
            jpanelSelecO.add(jRadioButtonLLExplicita, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
            jRadioButtonLLExplicita.setText("Llista explícita");
            jRadioButtonLLExplicita.addActionListener(
                   new java.awt.event.ActionListener() {
                      public void actionPerformed(ActionEvent e) {
                        jRadioButtonLLExplicita_actionPerformed(e);
                     }
                  });
         }
         jpanelSelecO.setPreferredSize(new java.awt.Dimension(531, 199));
         {
            jPanel1 = new JPanel();
            GridBagLayout jPanel1Layout = new GridBagLayout();
            jPanel1Layout.rowWeights = new double[] {0.0, 0.1};
            jPanel1Layout.rowHeights = new int[] {30, 7};
            jPanel1Layout.columnWeights = new double[] {0.1, 0.1};
            jPanel1Layout.columnWidths = new int[] {20, 7};
            jPanel1.setLayout(jPanel1Layout);
            jpanelObj.add(jPanel1, BorderLayout.CENTER);
            jpanelObj.add(jpanelSelecO, BorderLayout.NORTH);
            {
            
               jPanel1.add(jRadioButtonCondicions, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
               jRadioButtonCondicions.setText("Definir condicions");
               jRadioButtonCondicions.setLayout(null);
               {
                  jSeparator1 = new JSeparator();
                  jRadioButtonCondicions.add(jSeparator1);
                  jSeparator1.setBounds(91, 0, 343, 28);
                  jRadioButtonCondicions.addActionListener(
                         new java.awt.event.ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                              jRadioButtonCondicions_actionPerformed(e);
                           }
                        });
               }
            }
            {
               jPanelCondicions = new JPanel();
               GridBagLayout jPanelCondicionsLayout = new GridBagLayout();
               jPanelCondicionsLayout.rowWeights = new double[] {0.1};
               jPanelCondicionsLayout.rowHeights = new int[] {7};
               jPanelCondicionsLayout.columnWeights = new double[] {0.0, 0.0, 0.1};
               jPanelCondicionsLayout.columnWidths = new int[] {161, 97, 7};
               jPanelCondicions.setLayout(jPanelCondicionsLayout);
               jPanel1.add(jPanelCondicions, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
               {
                  jPanelVars = new JPanel();
                  GridBagLayout jPanelVarsLayout = new GridBagLayout();
                  jPanelVarsLayout.rowWeights = new double[] {0.0, 0.1};
                  jPanelVarsLayout.rowHeights = new int[] {173, 7};
                  jPanelVarsLayout.columnWeights = new double[] {0.1};
                  jPanelVarsLayout.columnWidths = new int[] {7};
                  jPanelVars.setLayout(jPanelVarsLayout);
                  jPanelCondicions.add(jPanelVars, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                  {
//NUEVO ALBERTO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> LO UNICO QUE HE HECHO HA SIDO COMENTAR ESAS LINEAS, YA FUNCIONA TODO.
                 //    jScrollPaneVars = new JScrollPane();
                     jPanelVars.add(jScrollPaneVars, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                     jScrollPaneVars.setBorder(titledBorder10);
                     jScrollPaneVars.setAutoscrolls(true);
                     jScrollPaneVars.setPreferredSize(new java.awt.Dimension(100, 153));
                   //  jScrollPaneVars.setSize(100, 153);
                     jScrollPaneVars.setFont(new java.awt.Font("Dialog",0,10));
                  //   {
                     
                     /*jListPropietats = new JList();*/
                   //     jScrollPaneVars.setViewportView(jListPropietats);
                //        jListPropietats.setModel(jListVarsModel);
                 //       jListPropietats.setPreferredSize(new java.awt.Dimension(137, 110));
                //     }
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                  }
                  {
                  
                     jPanelVars.add(
                        jButtonConsultarValors,
                        new GridBagConstraints(
                        0,
                        1,
                        1,
                        1,
                        0.0,
                        0.0,
                        GridBagConstraints.CENTER,
                        GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 0),
                        0,
                        0));
                     jButtonConsultarValors.setText("Consultar valors");
                     jButtonConsultarValors.addActionListener(
                            new java.awt.event.ActionListener() {
                               public void actionPerformed(ActionEvent e) {
                                 jButtonConsultarValors_actionPerformed(e);
                              }
                           });
                  }
               }
               {
                  jPanelDreta = new JPanel();
                  jPanelCondicions.add(jPanelDreta, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
                  jPanelDreta.setPreferredSize(new java.awt.Dimension(66, 40));
                  jPanelDreta.setSize(66, 40);
                  {
                  
                     jPanelDreta.add(jButtonDreta);
                     jButtonDreta.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
                     jButtonDreta.setPreferredSize(new java.awt.Dimension(66, 39));
                     jButtonDreta.setSize(66, 40);
                     jButtonDreta.addActionListener(
                            new java.awt.event.ActionListener() {
                               public void actionPerformed(ActionEvent e) {
                                 jButtonDreta_actionPerformed(e);
                              }
                           });
                  }
               }
               {
                  jPanelEditor = new JPanel();
                  GridBagLayout jPanelEditorLayout = new GridBagLayout();
                  jPanelEditorLayout.rowWeights = new double[] {0.0, 0.1};
                  jPanelEditorLayout.rowHeights = new int[] {77, 7};
                  jPanelEditorLayout.columnWeights = new double[] {0.1};
                  jPanelEditorLayout.columnWidths = new int[] {7};
                  jPanelEditor.setLayout(jPanelEditorLayout);
                  jPanelCondicions.add(jPanelEditor, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                  {
                     jScrollPaneExpressio = new JScrollPane();
                     jPanelEditor.add(jScrollPaneExpressio, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                  
                     {
                     
                        jScrollPaneExpressio.setViewportView(jTextAreaExpressio);
                        jScrollPaneExpressio.setBorder(titledBorder11);
                        jScrollPaneExpressio.setAutoscrolls(true);
                     }
                  }
                  {
                     jPanelBotons = new JPanel();
                     GridBagLayout jPanelBotonsLayout = new GridBagLayout();
                     jPanelBotonsLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
                     jPanelBotonsLayout.rowHeights = new int[] {7, 7, 7, 7};
                     jPanelBotonsLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
                     jPanelBotonsLayout.columnWidths = new int[] {7, 7, 7, 7};
                     jPanelBotons.setLayout(jPanelBotonsLayout);
                     jPanelEditor.add(jPanelBotons, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                     {
                     
                        jPanelBotons.add(jButtonMenor, new GridBagConstraints(
                           0,
                           0,
                           1,
                           1,
                           0.0,
                           0.0,
                           GridBagConstraints.CENTER,
                           GridBagConstraints.NONE,
                           new Insets(0, 0, 0, 0),
                           0,
                           0));
                        jButtonMenor.setText("<");
                        jButtonMenor.setSize(59, 23);
                        jButtonMenor.setPreferredSize(new java.awt.Dimension(59, 23));
                        jButtonMenor.addActionListener(
                               new java.awt.event.ActionListener() {
                                  public void actionPerformed(ActionEvent e) {
                                    jButtonMenor_actionPerformed(e);
                                 }
                              });
                     }
                     {
                     
                        jPanelBotons.add(jButtonMajor, new GridBagConstraints(
                           1,
                           0,
                           1,
                           1,
                           0.0,
                           0.0,
                           GridBagConstraints.CENTER,
                           GridBagConstraints.NONE,
                           new Insets(0, 0, 0, 0),
                           0,
                           0));
                        jButtonMajor.setText(">");
                        jButtonMajor.setSize(59, 23);
                        jButtonMajor.setPreferredSize(new java.awt.Dimension(59, 23));
                        jButtonMajor.addActionListener(
                               new java.awt.event.ActionListener() {
                                  public void actionPerformed(ActionEvent e) {
                                    jButtonMajor_actionPerformed(e);
                                 }
                              });
                     }
                     {
                     
                        jPanelBotons.add(jButtonIgual, new GridBagConstraints(
                           2,
                           0,
                           1,
                           1,
                           0.0,
                           0.0,
                           GridBagConstraints.CENTER,
                           GridBagConstraints.NONE,
                           new Insets(0, 0, 0, 0),
                           0,
                           0));
                        jButtonIgual.setText("=");
                        jButtonIgual.setSize(59, 23);
                        jButtonIgual.setPreferredSize(new java.awt.Dimension(59, 23));
                        jButtonIgual.addActionListener(
                               new java.awt.event.ActionListener() {
                                  public void actionPerformed(ActionEvent e) {
                                    jButtonIgual_actionPerformed(e);
                                 }
                              });
                     }
                     {
                     
                        jPanelBotons.add(
                           jButtonObrirParentesis,
                           new GridBagConstraints(
                           3,
                           0,
                           1,
                           1,
                           0.0,
                           0.0,
                           GridBagConstraints.CENTER,
                           GridBagConstraints.NONE,
                           new Insets(0, 0, 0, 0),
                           0,
                           0));
                        jButtonObrirParentesis.setText("(");
                        jButtonObrirParentesis.setSize(38, 23);
                        jButtonObrirParentesis.setPreferredSize(new java.awt.Dimension(38, 23));
                        jButtonObrirParentesis.addActionListener(
                               new java.awt.event.ActionListener() {
                                  public void actionPerformed(ActionEvent e) {
                                    jButtonObrirParentesis_actionPerformed(e);
                                 }
                              });
                     }
                     {
                     
                        jPanelBotons.add(
                           jButtonMenorIgual,
                           new GridBagConstraints(
                           0,
                           1,
                           1,
                           1,
                           0.0,
                           0.0,
                           GridBagConstraints.CENTER,
                           GridBagConstraints.NONE,
                           new Insets(0, 0, 0, 0),
                           0,
                           0));
                        jButtonMenorIgual.setText("<=");
                        jButtonMenorIgual.setSize(59, 23);
                        jButtonMenorIgual.setPreferredSize(new java.awt.Dimension(59, 23));
                        jButtonMenorIgual.addActionListener(
                               new java.awt.event.ActionListener() {
                                  public void actionPerformed(ActionEvent e) {
                                    jButtonMenorIgual_actionPerformed(e);
                                 }
                              });
                     }
                     {
                     
                        jPanelBotons.add(
                           jButtonMajorIgual,
                           new GridBagConstraints(
                           1,
                           1,
                           1,
                           1,
                           0.0,
                           0.0,
                           GridBagConstraints.CENTER,
                           GridBagConstraints.NONE,
                           new Insets(0, 0, 0, 0),
                           0,
                           0));
                        jButtonMajorIgual.setText(">=");
                        jButtonMajorIgual.setSize(59, 23);
                        jButtonMajorIgual.setPreferredSize(new java.awt.Dimension(59, 23));
                        jButtonMajorIgual.addActionListener(
                               new java.awt.event.ActionListener() {
                                  public void actionPerformed(ActionEvent e) {
                                    jButtonMajorIgual_actionPerformed(e);
                                 }
                              });
                     }
                     {
                     
                        jPanelBotons.add(
                           jButtonDiferent,
                           new GridBagConstraints(
                           2,
                           1,
                           1,
                           1,
                           0.0,
                           0.0,
                           GridBagConstraints.CENTER,
                           GridBagConstraints.NONE,
                           new Insets(0, 0, 0, 0),
                           0,
                           0));
                        jButtonDiferent.setText("!=");
                        jButtonDiferent.setSize(59, 23);
                        jButtonDiferent.setPreferredSize(new java.awt.Dimension(59, 23));
                        jButtonDiferent.addActionListener(
                               new java.awt.event.ActionListener() {
                                  public void actionPerformed(ActionEvent e) {
                                    jButtonDiferent_actionPerformed(e);
                                 }
                              });
                     }
                     {
                     
                        jPanelBotons.add(
                           jButtonTancarParentesis,
                           new GridBagConstraints(
                           3,
                           1,
                           1,
                           1,
                           0.0,
                           0.0,
                           GridBagConstraints.CENTER,
                           GridBagConstraints.NONE,
                           new Insets(0, 0, 0, 0),
                           0,
                           0));
                        jButtonTancarParentesis.setText(")");
                        jButtonTancarParentesis.setPreferredSize(new java.awt.Dimension(38, 23));
                        jButtonTancarParentesis.addActionListener(
                               new java.awt.event.ActionListener() {
                                  public void actionPerformed(ActionEvent e) {
                                    jButtonTancarParentesis_actionPerformed(e);
                                 }
                              });
                     }
                     {
                     
                        jPanelBotons.add(jButtonAND, new GridBagConstraints(
                           0,
                           2,
                           1,
                           1,
                           0.0,
                           0.0,
                           GridBagConstraints.CENTER,
                           GridBagConstraints.NONE,
                           new Insets(0, 0, 0, 0),
                           0,
                           0));
                        jButtonAND.setText("AND");
                        jButtonAND.setPreferredSize(new java.awt.Dimension(59, 23));
                        jButtonAND.setSize(59, 23);
                        jButtonAND.addActionListener(
                               new java.awt.event.ActionListener() {
                                  public void actionPerformed(ActionEvent e) {
                                    jButtonAND_actionPerformed(e);
                                 }
                              });
                     }
                     {
                     
                        jPanelBotons.add(jButtonNOT, new GridBagConstraints(
                           1,
                           2,
                           1,
                           1,
                           0.0,
                           0.0,
                           GridBagConstraints.CENTER,
                           GridBagConstraints.NONE,
                           new Insets(0, 0, 0, 0),
                           0,
                           0));
                        jButtonNOT.setText("NOT");
                        jButtonNOT.setPreferredSize(new java.awt.Dimension(59, 23));
                        jButtonNOT.addActionListener(
                               new java.awt.event.ActionListener() {
                                  public void actionPerformed(ActionEvent e) {
                                    jButtonNOT_actionPerformed(e);
                                 }
                              });
                     }
                     {
                     
                        jPanelBotons.add(jButtonOR, new GridBagConstraints(
                           2,
                           2,
                           1,
                           1,
                           0.0,
                           0.0,
                           GridBagConstraints.CENTER,
                           GridBagConstraints.NONE,
                           new Insets(0, 0, 0, 0),
                           0,
                           0));
                        jButtonOR.setText("OR");
                        jButtonOR.setSize(59, 23);
                        jButtonOR.setPreferredSize(new java.awt.Dimension(59, 23));
                        jButtonOR.addActionListener(
                               new java.awt.event.ActionListener() {
                                  public void actionPerformed(ActionEvent e) {
                                    jButtonOR_actionPerformed(e);
                                 }
                              });
                     }
                     {
                     
                        jPanelBotons.add(jButtonObrirClaudator, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                        jButtonObrirClaudator.setText("{");
                        jButtonObrirClaudator.setSize(38, 23);
                        jButtonObrirClaudator.setPreferredSize(new java.awt.Dimension(38, 23));
                        jButtonObrirClaudator.setFont(new java.awt.Font("Tahoma",0,9));
                        jButtonObrirClaudator.addActionListener(
                               new java.awt.event.ActionListener() {
                                  public void actionPerformed(ActionEvent e) {
                                    jButtonObrirClaudator_actionPerformed(e);
                                 }
                              });
                     }
                  }
                  {
                  
                     jPanelBotons.add(jButtonTancarClaudator, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                     jButtonTancarClaudator.setText("}");
                     jButtonTancarClaudator.setSize(38, 23);
                     jButtonTancarClaudator.setPreferredSize(new java.awt.Dimension(38, 23));
                     jButtonTancarClaudator.setFont(new java.awt.Font("Tahoma",0,9));
                     jButtonTancarClaudator.addActionListener(
                            new java.awt.event.ActionListener() {
                               public void actionPerformed(ActionEvent e) {
                                 jButtonTancarClaudator_actionPerformed(e);
                              }
                           });
                     {
                     
                        jPanelBotons.add(
                           jButtonPertany,
                           new GridBagConstraints(
                           0,
                           3,
                           1,
                           1,
                           0.0,
                           0.0,
                           GridBagConstraints.CENTER,
                           GridBagConstraints.NONE,
                           new Insets(0, 0, 0, 0),
                           0,
                           0));
                        jButtonPertany.setText("\u0404");
                        jButtonPertany.setSize(59, 23);
                        jButtonPertany.setPreferredSize(new java.awt.Dimension(59, 23));
                        jButtonPertany.addActionListener(
                               new java.awt.event.ActionListener() {
                                  public void actionPerformed(ActionEvent e) {
                                    jButtonPertany_actionPerformed(e);
                                 }
                              });
                     }
                     {
                     
                        jPanelBotons.add(jButtonClear, new GridBagConstraints(
                           1,
                           3,
                           1,
                           1,
                           0.0,
                           0.0,
                           GridBagConstraints.CENTER,
                           GridBagConstraints.NONE,
                           new Insets(0, 0, 0, 0),
                           0,
                           0));
                        jButtonClear.setText("Clear");
                        jButtonClear.setSize(59, 23);
                        jButtonClear.setPreferredSize(new java.awt.Dimension(59, 23));
                        jButtonClear.setFont(new java.awt.Font("Arial",0,9));
                        jButtonClear.addActionListener(
                               new java.awt.event.ActionListener() {
                                  public void actionPerformed(ActionEvent e) {
                                    jButtonClear_actionPerformed(e);
                                 }
                              });
                     }
                  }
               }
            }
            flowLayout2.rowWeights = new double[] {0.0, 0.1};
            flowLayout2.rowHeights = new int[] {23, 7};
            flowLayout2.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.1};
            flowLayout2.columnWidths = new int[] {19, 147, 68, 130, 7};
         }
         jpanelSelecNum.add(jSPVarsN, null);
         jpanelSelecNum.add(jpanelBtnsN, null);
         jpanelBtnsN.add(jBttnAfegirN, BorderLayout.NORTH);
         jpanelBtnsN.add(jBttnTreureN, BorderLayout.SOUTH);
         jpanelSelecNum.add(jSPSelecN, null);
         jpanelSelecNum.setPreferredSize(new java.awt.Dimension(301, 169));
         jpanelSelecCat.add(jSPVarsC, null);
         jpanelSelecCat.add(jpanelBtnsC, null);
         jpanelBtnsC.add(jBttnAfegirC, BorderLayout.NORTH);
         jpanelBtnsC.add(jBttnTreureC, BorderLayout.SOUTH);
         jpanelSelecCat.add(jSPSelecC, null);
         jpanelVarCat.setAutoscrolls(true);
         jpanelVarCat.add(jpanelSelecCat, BorderLayout.NORTH);
         jpanelSelecCat.setPreferredSize(new java.awt.Dimension(301, 178));

         bAcep.setText("D'acord");
         bAcep.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     bAcep_actionPerformed(e);
                  }
               });
         bCancel.setText("Cancel·la");
         bCancel.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     bCancel_actionPerformed(e);
                  }
               });
         bReset.setText("Netejar");
         bReset.setEnabled(true);
         bReset.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     bReset_actionPerformed(e);
                  }
               });
         panBoton.add(bAcep);
         panBoton.add(bCancel);
         panDown.add(panReset);
         panDown.add(bReset);
         bReset.setPreferredSize(new java.awt.Dimension(70, 22));
         panDown.add(panBoton);
         panBoton.setPreferredSize(new java.awt.Dimension(307, 32));
         jpanelVarNum.setPreferredSize(new java.awt.Dimension(313, 224));
         panIz.add(jpanelVarCat,BorderLayout.SOUTH);
         jpanelVarCat.setPreferredSize(new java.awt.Dimension(313, 229));
         panIz.add(jpanelVarNum, BorderLayout.NORTH);
         jpanelVarNum.add(jpanelSelecNum, BorderLayout.SOUTH);
         this.add(panIz,  BorderLayout.WEST);
         this.add(panDown, BorderLayout.SOUTH);
         this.add(jpanelObj, BorderLayout.EAST);
         jpanelObj.setPreferredSize(new java.awt.Dimension(523, 447));
      
      }
   /**
   * Mostra els valors característics de la variable seleccionada a la llista <code>Llista vars.</code>
   * del menú Definir condicions
   * @param e, event que detecta que s'ha premut el botó <code>Consultar valors</code>
   */
       protected void jButtonConsultarValors_actionPerformed(ActionEvent e) {
      //	if(variable categoria)
         try {
            Object ob=jListPropietats.getSelectedValue();
            Propietat prop=gestor.obtenirPropietat((String)ob);
            if(prop instanceof PropNumerica){
               PropNumerica aux=(PropNumerica)prop;
               float max=aux.obtenirRangMax();
               float min=aux.obtenirRangMin();
               DialogValorsNum inst = new DialogValorsNum(frPare,gestor,max,min,(String)ob);
               inst.setLocationRelativeTo(this);
               inst.setVisible(true);
            }
            else{
               String[] alModalitats=gestor.obtenirLlistaMods((String)ob);
               System.out.println("Propietat = "+ob+" Nombre de modalitats = "+alModalitats.length+"modalitat 0 = "+alModalitats[0]);
               DialogValorsCat inst = new DialogValorsCat(frPare,gestor,(String)ob,alModalitats);
               inst.setLocationRelativeTo(this);
               inst.setVisible(true);
               if(inst.OKPremut()){
                  String valor=inst.getValorSeleccionat();
                  System.out.println("Valor = "+valor);
                  jTextAreaExpressio.insert(valor, jTextAreaExpressio.getCaretPosition());	
               }
            }
         } 
             catch (Exception e1) {
               frPare.actualitzarBarraEstat("No es poden consultar els valors: "+e1.getMessage(),true);
            }			
      
      }
   /**
   * Afegeix a l'àrea d'edició del filtre lògic la variable seleccionada a la llista de variables
   * @param e, event que detecta que s'ha premut el botó <code>Afegir variable a l'àrea de text( mà cap a la dreta)</code>
   */
       protected void jButtonDreta_actionPerformed(ActionEvent e) {
         Object vars = jListPropietats.getSelectedValue();	 
         int i=jTextAreaExpressio.getCaretPosition();
         vars="("+vars+")";
         jTextAreaExpressio.insert((String)vars, i);
      
      }
   /**
   * Mostra seleccionada l'opció <code>Llista explícita</code> i activa el menú de la Llista explícitai desactiva el menú 
   * de Definir condicions.
   * @param e, event que detecta que s'ha seleccionat l'opció <code>Llista explícita</code>
   */
       protected void jRadioButtonLLExplicita_actionPerformed(ActionEvent e) {
         if(jRadioButtonLLExplicita.isSelected()){
            jRadioButtonCondicions.setSelected(false);
            desactivarCondicions();
            activarExplicita();
         }
         else{
            desactivarExplicita();
         }
      
      }
   /**
   * Desactiva el menú de la Llista explícita
   *
   */
       public void desactivarExplicita(){
      
         jListObj.setEnabled(false);
         jListSelcObj.setEnabled(false);
         jBttnAfegirO.setEnabled(false);
         jBttnTreureO.setEnabled(false);
      }
   /**
   * Activa el menú de la Llista explícita
   *
   */
       public void activarExplicita(){
         jListObj.setEnabled(true);
         jListSelcObj.setEnabled(true);
         jBttnAfegirO.setEnabled(true);
         jBttnTreureO.setEnabled(true);
      }
   /**
   * Desactiva el menú de Definir condicions
   *
   */
       public void desactivarCondicions(){
         jListPropietats.setEnabled(false);
         jButtonDreta.setEnabled(false);
         jTextAreaExpressio.setEnabled(false);
         jButtonIgual.setEnabled(false);
         jButtonDiferent.setEnabled(false);
         jButtonMenor.setEnabled(false);
         jButtonMajor.setEnabled(false);
         jButtonMenorIgual.setEnabled(false);
         jButtonMajorIgual.setEnabled(false);
         jButtonAND.setEnabled(false);
         jButtonOR.setEnabled(false);
         jButtonNOT.setEnabled(false);
         jButtonObrirClaudator.setEnabled(false);
         jButtonTancarClaudator.setEnabled(false);
         jButtonObrirParentesis.setEnabled(false);
         jButtonTancarParentesis.setEnabled(false);
         jButtonPertany.setEnabled(false);
         jButtonClear.setEnabled(false);
         jButtonConsultarValors.setEnabled(false);
      }
   /**
   * Activa el menú de Definir condicions
   *
   */
       public void activarCondicions(){
         jListPropietats.setEnabled(true);
         jButtonDreta.setEnabled(true);
         jTextAreaExpressio.setEnabled(true);
         jButtonIgual.setEnabled(true);
         jButtonDiferent.setEnabled(true);
         jButtonMenor.setEnabled(true);
         jButtonMajor.setEnabled(true);
         jButtonMenorIgual.setEnabled(true);
         jButtonMajorIgual.setEnabled(true);
         jButtonAND.setEnabled(true);
         jButtonOR.setEnabled(true);
         jButtonNOT.setEnabled(true);
         jButtonObrirClaudator.setEnabled(true);
         jButtonTancarClaudator.setEnabled(true);
         jButtonObrirParentesis.setEnabled(true);
         jButtonTancarParentesis.setEnabled(true);
         jButtonPertany.setEnabled(true);
         jButtonClear.setEnabled(true);
         jButtonConsultarValors.setEnabled(true);
      }
   
       protected void jRadioButtonCondicions_actionPerformed(ActionEvent e) {
         if(jRadioButtonCondicions.isSelected()){
            jRadioButtonLLExplicita.setSelected(false);
            desactivarExplicita();
            activarCondicions();
         }
         else{
            desactivarCondicions();
         }
      
      }
   /**Tanca la finestra
   * @param e event de seleccionar la opc
   */
       private void bCancel_actionPerformed(ActionEvent e) {
      
         if(matriuCarregada!=matriuActual){
            gestor.eliminarMatriu(matriuActual);
            gestor.selecIdMatriuActual(matriuCarregada);
         }
         frPare.remove(this);
         frPare.validate();
         frPare.repaint();
      }
   
       private void bAcep_actionPerformed(ActionEvent e) { 
      
         Object[]llObjs; 
         String subNom; 
         Object[] llPropsN; 
         Object[] llPropsC; 
         String[] prop; 
         String[] obj; 
         int id = -1; 
         int lon1; 
         int lon2; 
         int lon3; 
      
         subNom=nomFitxer+"bis"; 
         llPropsN=listModelSN.toArray(); 
         llPropsC=listModelSC.toArray(); 
         lon1=llPropsN.length; 
         lon2=llPropsC.length; 
         prop=new String[lon1+lon2]; 
         for (int i=0;i<lon1;i++){ 
            prop[i]=(String) llPropsN[i]; 
         } 
         for (int j=0;j<lon2;j++){ 
            prop[j+lon1]=(String) llPropsC[j]; 
         }
         try{
            if(jRadioButtonLLExplicita.isSelected()){
               llObjs=listModelSObj.toArray(); 
               lon3=llObjs.length; 
               obj=new String[lon3]; 
               for (int k=0;k<lon3;k++){ 
                  obj[k]=(String) llObjs[k]; 
               } 
            }
            else{
               String snom=gestor.nomBCPerDefecte();
               bc=gestor.crearBC(snom);
               String sregla=jTextAreaExpressio.getText();
               bc.llegirReglaNormal(sregla,"T",bc.seguentNomRegla());
               ArrayList al=bc.avaluaReglaARegla(true);			
               Propietat p=(Propietat)al.get(0);
               Dada[] d = gestor.obtenirColumna(p.obtenirId());
               String[]llObjs2=gestor.obtenirLlistaIDsObjs();
               ArrayList alaux=new ArrayList();
               for(int i=0;i<d.length;i++){
                  String svalor=(String)d[i].obtenirValor();
                  if(svalor.compareTo("1")==0){
                     alaux.add(llObjs2[i]);
                  }
               }
               obj=new String[alaux.size()];
               for (int k=0;k<alaux.size();k++){
                  obj[k]=(String) alaux.get(k);
               }
               gestor.eliminarBC(bc.getNomBC());
               gestor.eliminarColumnes(1);
            }
         
            matriuActual=gestor.idMatriuActual(); 
            if (prop.length>0){ 
               if (obj.length>0){ 
                  try { 
                     int n = JOptionPane.showConfirmDialog( 
                        this, "Aquesta opció crea una nova submatriu i la deixa com a l'actual. Segur que vols continuar?", 
                         "Selecciona Submatriu", 
                         JOptionPane.YES_NO_OPTION); 
                     if (n == JOptionPane.YES_OPTION) { 
                        if(frPare.matrius.existeix(subNom)){ 
                           n = JOptionPane.showConfirmDialog( 
                              this, "Ja existeix una matriu carregada al sistema amb aquest nom. Segur que vols continuar?", 
                              "Selecciona Submatriu", 
                              JOptionPane.YES_NO_OPTION); 
                        } 
                        if (n == JOptionPane.YES_OPTION) { 
                           ArrayList BCIncomplertes=gestor.comprovarLlistaBC(matriuActual,prop);
                           id = gestor.carregarSubMatriu(subNom,matriuActual,prop,obj); 
                           if (id != -1) { 
                              frPare.actualitzarMatriuGuardada(false); 
                              frPare.actualitzarTitolFinestra(subNom); 
                              frPare.matrius.insertarMatriu(subNom, id); // hook into FileHistory class 
                           } 
                        
                           if(!BCIncomplertes.isEmpty()){
                              String missatge="Regles amb propietats no existents a la submatriu:\n";
                              for(int r=0;r<BCIncomplertes.size();r++){
                                 ArrayList bc=(ArrayList)BCIncomplertes.get(r);
                                 String misBC="Base de coneixement: "+bc.get(0)+"\n";
                                 ArrayList regles=(ArrayList)bc.get(1);
                                 for(int f=0;f<regles.size();f++){	            			  
                                    String misReg="   Regla: "+regles.get(f)+"\n";
                                    misBC=misBC+misReg;
                                 }
                                 missatge=missatge+misBC;
                              }
                              JOptionPane.showMessageDialog(this, missatge);
                           }
                           frPare.actualitzarBarraEstat("S´ha creat la submatriu amb els objectes seleccionats", false); 
                           llistarVariables(); 
                           if(matriuCarregada!=matriuActual){ 
                              gestor.eliminarMatriu(matriuActual); 
                           }
                           matriuActual=gestor.idMatriuActual(); 
                        }
                     }
                  } 
                      catch (Exception ex) { 
                        ex.printStackTrace();
                        frPare.actualitzarBarraEstat("No s'ha pogut crear la submatriu.(Nombre màxim de matrius carregades al sistema.)", true); 
                     } 
               } 
               else{ 
                  frPare.actualitzarBarraEstat("S´ha de seleccionar un objecte com a minim", true); 
               } 
            } 
            else{ 
               frPare.actualitzarBarraEstat("S´ha de seleccionar una variable com a minim", true); 
            }
         }
             catch (Exception ex) {
               frPare.actualitzarBarraEstat("No s'ha pogut crear la submatriu.(Nombre màxim de matrius carregades al sistema.)", true);
            }
      }
   
   /** Elimina la selecio que teniem i deixa el menu como estaba inicalment
   * @param e event de seleccionar la opció
   */
       private void bReset_actionPerformed(ActionEvent e) {
      
         llistarVariables();
      }
   /**Carrega les llistes de les variabels de la matriu dins els cuadres
   * @param e event de seleccionar la opció
   */
       private void llistarVariables(){
      
         String[][] llProps;
         String[] llObjs;
         int lon;
      
         listModelVN.clear();
         listModelSN.clear();
         listModelVC.clear();
         listModelSC.clear();
         listModelObj.clear();
         listModelSObj.clear();
         jListVarsModel.clear();
         jTextAreaExpressio = new JTextArea();
         jScrollPaneExpressio.setViewportView(jTextAreaExpressio);	
         nomFitxer = frPare.obtenirNomDades();
         llObjs=gestor.obtenirLlistaIDsObjs();
         lon = llObjs.length;
         for (int i = 0; i < lon; i++) {
            listModelObj.insertElementAt(llObjs[i], i);
         }
        // llProps = gestor.obtenirLlistaIDsProps();
			
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
      
         lon = llProps[0].length;
         for (int i = 0; i < lon; i++) {
            jListVarsModel.insertElementAt(llProps[0][i], i);
         }
         lon = llProps[1].length;
         for (int i = 0; i < lon; i++) {
            jListVarsModel.insertElementAt(llProps[1][i], i);
         }
         lon = llProps[2].length;
         for (int i = 0; i < lon; i++) {
            jListVarsModel.insertElementAt(llProps[2][i], i);
         }
         lon = llProps[3].length;
         for (int i = 0; i < lon; i++) {
            jListVarsModel.insertElementAt(llProps[3][i], i);
         }
      }
   /**Carrega la variable numerica dins la llista de les seleccionades
   * @param e event de seleccionar la opció
   */
       private void jBttnAfegirN_actionPerformed(ActionEvent e) {
      
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
   /**Treu de la llista de variables numeriques seleccionades la variable en seleccio
   * @param e event de seleccionar la opció
   */
       private void jBttnTreureN_actionPerformed(ActionEvent e) {
      
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
   /**Carrega la variable categorica dins la llista de les seleccionades
   * @param e event de seleccionar la opció
   */
       private void jBttnAfegirC_actionPerformed(ActionEvent e) {
      
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
   /**Treu de la llista de variables categoriques seleccionades la variable en seleccio
   * @param e event de seleccionar la opció
   */
       private void jBttnTreureC_actionPerformed(ActionEvent e) {
      
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
   /**Carrega l'objecte dins la llista dels seleccionats
   * @param e event de seleccionar la opció
   */
       private void jBttnAfegirO_actionPerformed(ActionEvent e) {
      
         Object[] vars = jListObj.getSelectedValues();
         boolean hay_mas_vars = true;
         int n = 0;
      
         do {
            try {
               listModelSObj.addElement(vars[n]);
               listModelObj.removeElement(vars[n]);
               n++;
            }
                catch (Exception exc) { hay_mas_vars = false; }
         
         } while (hay_mas_vars);
      }
   /**Treu de la llista dels objectes seleccionats l'objecte en seleccio
   * @param e event de seleccionar la opció
   */
       private void jBttnTreureO_actionPerformed(ActionEvent e) {
      
         Object[] vars = jListSelcObj.getSelectedValues();
         boolean hay_mas_vars = true;
         int n = 0;
      
         do {
            try {
               listModelObj.addElement(vars[n]);
               listModelSObj.removeElement(vars[n]);
               n++;
            }
                catch (Exception exc) { hay_mas_vars = false; }
         
         } while (hay_mas_vars);
      }
   /**
   * Buida l'àrea d'edició del filtre lògic
   * @param e, event que detecta que s'ha premut el botó <code>Clear</code>
   */
       protected void jButtonClear_actionPerformed(ActionEvent e) {
         jTextAreaExpressio = new JTextArea();
         jScrollPaneExpressio.setViewportView(jTextAreaExpressio);		
      }
   /**
    * Insereix el símbol de pertany a l'àrea d'edició de l'expressió lògica
    * @param e, event que detecta que s'ha premut el botó <code>Pertany</code>
    */
       protected void jButtonPertany_actionPerformed(ActionEvent e) {
         jTextAreaExpressio.insert(" "+ExpressioBooleana.getPERTANY()+" ", jTextAreaExpressio.getCaretPosition());				
      
      }
   /**
    * Insereix el símbol de tancar claus } a l'àrea d'edició de l'expressió lògica
    * @param e, event que detecta que s'ha premut el botó <code>}</code>
    */
       protected void jButtonTancarClaudator_actionPerformed(ActionEvent e) {
         jTextAreaExpressio.insert("}", jTextAreaExpressio.getCaretPosition());		
      
      }
   /**
    * Insereix el símbol d'obrir claus { a l'àrea d'edició de l'expressió lògica
    * @param e, event que detecta que s'ha premut el botó <code>{</code>
    */
       protected void jButtonObrirClaudator_actionPerformed(ActionEvent e) {
         jTextAreaExpressio.insert("{", jTextAreaExpressio.getCaretPosition());		
      
      }
   /**
    * Insereix el símbol de tancar parèntesis ) a l'àrea d'edició de l'expressió lògica
    * @param e, event que detecta que s'ha premut el botó <code>)</code>
    */
       protected void jButtonTancarParentesis_actionPerformed(ActionEvent e) {
         jTextAreaExpressio.insert(")", jTextAreaExpressio.getCaretPosition());		
      
      }
   /**
    * Insereix el símbol d'OR or a l'àrea d'edició de l'expressió lògica
    * @param e, event que detecta que s'ha premut el botó <code>OR</code>
    */
       protected void jButtonOR_actionPerformed(ActionEvent e) {
         jTextAreaExpressio.insert(" "+ExpressioBooleana.getOR()+" ", jTextAreaExpressio.getCaretPosition());		
      
      }
   /**
    * Insereix el símbol de NOT not a l'àrea d'edició de l'expressió lògica
    * @param e, event que detecta que s'ha premut el botó <code>NOT</code>
    */
       protected void jButtonNOT_actionPerformed(ActionEvent e) {
         jTextAreaExpressio.insert(" "+ExpressioBooleana.getNOT()+" ", jTextAreaExpressio.getCaretPosition());		
      
      }
   /**
    * Insereix el símbol d'AND and a l'àrea d'edició de l'expressió lògica
    * @param e, event que detecta que s'ha premut el botó <code>AND</code>
    */
       protected void jButtonAND_actionPerformed(ActionEvent e) {
         jTextAreaExpressio.insert(" "+ExpressioBooleana.getAND()+" ", jTextAreaExpressio.getCaretPosition());			
      
      }
   /**
    * Insereix el símbol d'obrir parèntesis a l'àrea d'edició de l'expressió lògica
    * @param e, event que detecta que s'ha premut el botó <code>(</code>
    */
       protected void jButtonObrirParentesis_actionPerformed(ActionEvent e) {
         jTextAreaExpressio.insert("(", jTextAreaExpressio.getCaretPosition());		
      
      }
   /**
    * Insereix el símbol de diferent != a l'àrea d'edició de l'expressió lògica
    * @param e, event que detecta que s'ha premut el botó <code>!=</code>
    */
       protected void jButtonDiferent_actionPerformed(ActionEvent e) {
         jTextAreaExpressio.insert(" "+ExpressioBooleana.getDIFERENT()+" ", jTextAreaExpressio.getCaretPosition());		
      
      }
   /**
    * Insereix el símbol de major igual >= a l'àrea d'edició de l'expressió lògica
    * @param e, event que detecta que s'ha premut el botó <code>>=</code>
    */
       protected void jButtonMajorIgual_actionPerformed(ActionEvent e) {
         jTextAreaExpressio.insert(" "+ExpressioBooleana.getMAJORIGUAL()+" ", jTextAreaExpressio.getCaretPosition());		
      
      }
   /**
    * Insereix el símbol de menor igual <= a l'àrea d'edició de l'expressió lògica
    * @param e, event que detecta que s'ha premut el botó <= 
    */
       protected void jButtonMenorIgual_actionPerformed(ActionEvent e) {
         jTextAreaExpressio.insert(" "+ExpressioBooleana.getMENORIGUAL()+" ", jTextAreaExpressio.getCaretPosition());		;	
      
      }
   /**
    * Insereix el símbol d'igual = a l'àrea d'edició de l'expressió lògica
    * @param e, event que detecta que s'ha premut el botó <code>=</code> 
    */
       protected void jButtonIgual_actionPerformed(ActionEvent e) {
      
         jTextAreaExpressio.insert(" "+ExpressioBooleana.getIGUAL()+" ", jTextAreaExpressio.getCaretPosition());		
      }
   /**
    * Insereix el símbol de major > a l'àrea d'edició de l'expressió lògica
    * @param e, event que detecta que s'ha premut el botó <code>></code> 
    */
       protected void jButtonMajor_actionPerformed(ActionEvent e) {
      
         jTextAreaExpressio.insert(" "+ExpressioBooleana.getMAJOR()+" ", jTextAreaExpressio.getCaretPosition());		
      }
   /**
    * Insereix el símbol de menor < a l'àrea d'edició de l'expressió lògica
    * @param e, event que detecta que s'ha premut el botó < 
    */
       protected void jButtonMenor_actionPerformed(ActionEvent e) {
         jTextAreaExpressio.insert(" "+ExpressioBooleana.getMENOR()+" ", jTextAreaExpressio.getCaretPosition());		
      }
   
   }
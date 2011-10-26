   package jklass.iu;

   import java.awt.*;
   import javax.swing.*;
   import java.awt.event.*;
   import javax.swing.border.*;
   import jklass.nucli.GestorKlass;
   import java.util.*;


 // DISTANCIES*********************************************************************
 /** Classe que dibuixa el submenu de les opcions per substituir els mancants
 *
 * @author Jose I Mateos
 * @version v.0 6/8/06
 */

    public class DlgOpcIdioma extends JDialog {
   
   
      private GridLayout miss = new GridLayout(3,1);
      private GridBagLayout taula1= new GridBagLayout();
      private GridBagConstraints c = new GridBagConstraints();
      private Border cuadre;
      private TitledBorder tMiss;
      private JPanel panMiss =new JPanel();
      private JPanel panBoton =new JPanel();
      private JPanel panDefec =new JPanel();
      private JPanel panOpc =new JPanel();
      private JButton bAcep = new JButton();
      private JButton bCancel = new JButton();
      private JButton bDefec = new JButton();
      private JRadioButton r0 = new JRadioButton();
      private JRadioButton rMit = new JRadioButton();
      private JRadioButton r2 = new JRadioButton();
      private GestorKlass gestor;
      private FrPrincipal frPare;
   
      private final static String[] LANG_STRINGS = { "Spanish", "French", "Dutch", "Catalan", "English" };
      private final static String[] LOCALES      = { "es",      "fr",     "nl",    "ca",      "en"};
   
   
   /**
   * Constructor
   *
   * @param frame es la finestra pare
   * @param title es el titol de la submennu
   * @param modal indica si la finestra pare no es accesible desde el submenu
   * @param gk es el gestor d'on penja la matriu, o es null si es de valors introduits per l'usuari
   */
       public DlgOpcIdioma(FrPrincipal frame, String title, boolean modal,GestorKlass gk) {
         super(frame, title, modal);
         try {
            frPare=frame;
            gestor=gk;
            jbInit();
            pack();
         }
             catch(Exception ex) {
               ex.printStackTrace();
            }
      }
   /**Dibuixa el menu
   * @throws Exception
   */
       private void jbInit() throws Exception {
      
         panOpc.setLayout(taula1);
         panMiss.setLayout(miss);
         cuadre = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
         panDefec.add(bDefec);
         panBoton.add(panDefec);
         panBoton.add(bAcep);
         panBoton.add(bCancel);
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
         tMiss = new TitledBorder(cuadre,"Idioma / Language ");
         panMiss.setBorder(tMiss);
         r0.setText("Catalan              ");
         r0.setSelected(true);
         r0.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     r0_actionPerformed(e);
                  }
               });
         bDefec.setText("Per defecte");
         bDefec.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     bDefec_actionPerformed(e);
                  }
               });
         rMit.setText("English           ");
         rMit.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     rMit_actionPerformed(e);
                  }
               });
         r2.setText("Español             ");
         r2.setSelected(false);
         r2.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     r2_actionPerformed(e);
                  }
               });
      
      
      
         panMiss.add(r0);
         panMiss.add(rMit);
         panMiss.add(r2);
         c.weightx = 1;
         c.weighty = 1;
         c.gridwidth = GridBagConstraints.REMAINDER;
         this.getContentPane().add(panOpc);
         panOpc.add(panMiss,c);
         panOpc.add(panBoton,c);
      }
   /**Posa les opcions per defecte
   * @param e event de seleccionar la opció
   */
       private void bDefec_actionPerformed(ActionEvent e) {
         int n = JOptionPane.showConfirmDialog(
            this, "Aquesta opció posa tots els paràmetres de la classificació amb els seus valors per defecte. Segur que vols continuar?",
            "Assignació d'opcions per defecte",
            JOptionPane.YES_NO_OPTION);
         if (n == JOptionPane.YES_OPTION) {
            r0.setSelected(true);
            r0_actionPerformed(null);
         }
      }
   
   /**Cancel·la el menu
   * @param e event de seleccionar la opció
   */
       private void bCancel_actionPerformed(ActionEvent e) {
         dispose();
      }
   /**Substituiex els missings pel valor seleccionat
   * @param e event de seleccionar la opció
   */
       private void bAcep_actionPerformed(ActionEvent e) {
      
         int n = JOptionPane.showConfirmDialog(
            this, "Aquesta opció modifica l'idioma de tota la aplicació. Segur que vols continuar?",
            "Seleccioni l'idioma",
            JOptionPane.YES_NO_OPTION);
         if (n == JOptionPane.YES_OPTION) {
            if (r0.isSelected()){
            //	gestor.subs(0);
               Locale.setDefault(new Locale(LOCALES[3]));
               frPare.actualitzarBarraEstat("Idioma Catalá ", false);
            }
            if (rMit.isSelected()){
            //	gestor.subs(-1);
               Locale.setDefault(new Locale(LOCALES[4]));
               frPare.actualitzarBarraEstat("English language ", false);
            }
            if (r2.isSelected()){
            //	gestor.subs(-1);
               Locale.setDefault(new Locale(LOCALES[0]));
               frPare.actualitzarBarraEstat("Idioma Español ", false);
            }
         
         
            dispose();
         }
      }
   /**Seleciona substituir per 0
   * @param e event de seleccionar la opció
   */
       private void r0_actionPerformed(ActionEvent e){
         rMit.setSelected(false);
      // Locale.setDefault(new Locale(LOCALES[0]));
         r2.setSelected(false);
      }
   /**Seleciona substituir per la mitjana
   * @param e event de seleccionar la opció
   */
       private void r2_actionPerformed(ActionEvent e){
         rMit.setSelected(false);
      // Locale.setDefault(new Locale(LOCALES[0]));
         r0.setSelected(false);
      }
   
   
   
   
       private void rMit_actionPerformed(ActionEvent e){
         r0.setSelected(false);
         r2.setSelected(false);
      }
   
   }
package jklass.iu;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import jklass.util.OpcionsDis;
import jklass.nucli.GestorKlass;

 // DISTANCIES*********************************************************************
 /** Classe que dibuixa el submenu de les opcions de les distància Mixta Gibert
 *
 * @author Jose I Mateos
 * @version v.0 6/8/06
 */

public class DlgOpcDistanMGibert extends JDialog {

  private GridLayout dis;
  private GridBagLayout taula1= new GridBagLayout();
  private GridBagConstraints c = new GridBagConstraints();
  private Border cuadre;
  private TitledBorder tDistan;
  private JPanel panDis =new JPanel();
  private JPanel panBoton =new JPanel();
  private JPanel panOpc =new JPanel();
  private JPanel panAlfa =new JPanel();
  private JPanel panBeta =new JPanel();
  private JPanel panDefec =new JPanel();
  private JPanel panCheck =new JPanel();
  private JPanel panAuto =new JPanel();
  private JButton bAcep = new JButton();
  private JButton bCancel = new JButton();
  private JButton bDefec = new JButton();
  private JLabel lAlfa = new JLabel();
  private JTextField tAlfa = new JTextField(10);
  private JLabel lBeta = new JLabel();
  private JTextField tBeta = new JTextField(10);
  private JCheckBox cb1 = new JCheckBox();
  private JLabel lBuit1 = new JLabel();
  private JLabel lBuit2 = new JLabel();
  private FrPrincipal frPare;
  private OpcionsDis opc;
  private GestorKlass gestor;
  private boolean matriu;
  private JCheckBox cb2 = new JCheckBox();
  private JLabel lBuit3 = new JLabel();
  private JLabel lBuit4 = new JLabel();

  /**
  * Constructor
  *
  * @param frame es la finestra pare
  * @param title es el titol de la submennu
  * @param modal indica si la finestra pare no es accesible desde el submenu
  * @param opcions es el valor de les opcions de les distancies
  * @param m indica si es una distància de una matriu o de valors introduits per l'usuari
  * @param gk es el gestor d'on penja la matriu, o es null si es de valors introduits per l'usuari
  */
  public DlgOpcDistanMGibert(FrPrincipal frame, String title, boolean modal, OpcionsDis opcions,boolean m,GestorKlass gk) {

	super(frame, title, modal);
	frPare=frame;
    opc = opcions;
    gestor=gk;
    matriu=m;
    try {
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
	//System.out.println("ESTOY EN MIXTA GIBERT");
	panOpc.setLayout(taula1);
	cuadre = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
    panDefec.add(bDefec);
	panBoton.add(panDefec);
	panBoton.add(bAcep);
    panBoton.add(bCancel);
	  boolean porests;
		 // porests = PanelClassificaEst.jCBGuardarDisc.isSelected();
		 // System.out.println( "como esta por estados"+  porests);

	 
	 
	bDefec.setText("Per defecte");
    bDefec.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(ActionEvent e) {
    		bDefec_actionPerformed(e);
    	}
    });
    bAcep.setText("D'acord");
    bAcep.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(ActionEvent e) {
    		bAcep_actionPerformed(e);
    	}
    });
    bCancel.setText("Cancel·la");
    bCancel.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
        	bCancel_actionPerformed(e);
        }
    });

    tDistan = new TitledBorder(cuadre,"Mixta Gibert");
	panDis.setBorder(tDistan);
	lAlfa.setText("\u0251:");
	lBeta.setText("\u03b2:");
	lBuit1.setText("           real entre 0 i 1               ");
	lBuit2.setText("           real entre 0 i 1               ");
	lBuit3.setText("                ");
	lBuit4.setText("                   \u0251 + \u03b2 = 1                        ");
	
	lBuit1.setFont(new java.awt.Font("Dialog", 2, 10));
	lBuit2.setFont(new java.awt.Font("Dialog", 2, 10));
	lBuit3.setFont(new java.awt.Font("Dialog", 2, 10));
	lBuit4.setFont(new java.awt.Font("Dialog", 2, 10));
	
	tAlfa.setText("");
	tBeta.setText("");
	if ( opc.getCateg()!='\u0000'){
		tAlfa.setText(String.valueOf(opc.getAlfa()));
	  	tBeta.setText(String.valueOf(opc.getBeta()));
	}
	if (opc.getCateg()==OpcionsDis.AUTO){
		tAlfa.setEnabled(false);
	  	tBeta.setEnabled(false);
	  	cb1.setSelected(true);
  	}
	
	cb1.setText("Automàtics");
 	cb1.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
      		cbAuto_actionPerformed(e);
       	}
    });
  	dis = new GridLayout(3,2);
	panAuto.add(cb1);
 	panAuto.add(lBuit4);
 	panDis.add(panAuto);
 	
	cb2.setEnabled(true);
    cb2.setText("Treballar amb distàncies ponderades");
    cb2.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
      		cb2_actionPerformed(e);
       	}
    });

    
    panCheck.add(cb2);
   	panCheck.add(lBuit3);
   	
	panDis.setLayout(dis);
  	panAlfa.add(lAlfa);
	panAlfa.add(tAlfa);
	panAlfa.add(lBuit1);
	panBeta.add(lBeta);
	panBeta.add(tBeta);
	panBeta.add(lBuit2);
	panDis.add(panAlfa);
	panDis.add(panBeta);

	c.weightx = 1;
    c.weighty = 1;
	c.gridwidth = GridBagConstraints.REMAINDER;
	this.getContentPane().add(panOpc);
	panOpc.add(panDis,c);
	panOpc.add(panCheck,c);
	panOpc.add(panBoton,c);
	
	cb2.setSelected(opc.getPond());
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
		cb1.setSelected(true);
		cbAuto_actionPerformed(null);
	}
  }
  /**Cancel·la el menu
   * @param e event de seleccionar la opció
   */
  private void bCancel_actionPerformed(ActionEvent e) {
	  frPare.actualitzarBarraEstat(" ", false);
	  dispose();
  }
  /**Actualiza opc amb els valors seleccionats
   * @param e event de seleccionar la opció
   */
  private void bAcep_actionPerformed(ActionEvent e) {

	  float alfa;
	  float beta;

	  try{
		  if(!cb1.isSelected()){
			  if(tAlfa.getText().compareTo("")!=0 && tBeta.getText().compareTo("")!=0){
				  alfa=Float.parseFloat(tAlfa.getText());
				  beta=Float.parseFloat(tBeta.getText());
				  if (((alfa+beta)==1)&& (alfa>=0) && (beta>=0)){
					  opc.setAlfa(alfa);
					  opc.setBeta(beta);
					  opc.setCateg(OpcionsDis.NOAUTO);
					  frPare.actualitzarBarraEstat(" ", false);
					  dispose();
				  }
	   			  else{
 			  		  frPare.actualitzarBarraEstat("Valors introduits erronis, \u0251 + \u03b2 = 1, i ni \u0251 ni \u03b2 poden ser negatius", true);
		  	  	  }
 		  	  }
  			  else{
	  		  	frPare.actualitzarBarraEstat("Valors introduits erronis, \u0251 + \u03b2 = 1, i ni \u0251 ni \u03b2 poden ser negatius", true);
		  	  }
  	  	  }
	  	  else {
		  	  opc.setCateg(OpcionsDis.AUTO);
		  	  frPare.actualitzarBarraEstat(" ", false);
	  	  	  dispose();
		  }
		  opc.setPond(cb2.isSelected());
  	  }
  	  catch (Exception ex) {
		       	frPare.actualitzarBarraEstat("Els valors introduits no son correctes", true);
	  }
  }
  /**Calcula i mostra els valors alfa i beta si esta seleccionat,sino permet insertarlos manualment
   * @param e event de seleccionar la opció
   */
  private void cbAuto_actionPerformed(ActionEvent e){
  	if(cb1.isSelected()){
	  	if (matriu){
		  	if(!gestor.obtenirMiss()){
				tAlfa.setEnabled(false);
	  			tBeta.setEnabled(false);
	  			opc.setPond(cb2.isSelected());
					System.out.println("voy a entrar al obtenirAlfaBeta");
	  			
				
				gestor.obtenirAlfaBeta(opc);
					System.out.println("sali del obtenirAlfaBeta lo que me sorprenderia mucho");
				tAlfa.setText(String.valueOf(opc.getAlfa()));
	  			tBeta.setText(String.valueOf(opc.getBeta()));
	  		}
	  		else{
			  	cb1.setSelected(false);
		  		frPare.actualitzarBarraEstat("En la distància Mixta Gibert,la matriu de dades no pot contenir missings en les variables numeriques", true);
			}
		}
		else{
			tAlfa.setEnabled(false);
	  		tBeta.setEnabled(false);
	  		frPare.actualitzarBarraEstat("Els valors de \u0251 i \u03b2 no es precalculen ja que encara es poden omplir files i columnes de la matriu", false);
  		}

	}
	else{
		tAlfa.setEnabled(true);
	  	tBeta.setEnabled(true);
  	}
  }

  /**Calcula i mostra els valors alfa i beta recalculats amb la ponderacio d'objectes
   * @param e event de seleccionar la opció
   */
  
  private void cb2_actionPerformed(ActionEvent e){
	  cbAuto_actionPerformed(null);		 
	  }

}
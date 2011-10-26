package jklass.iu;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import jklass.util.OpcionsDis;
import java.util.logging.*;


 //DISTANCIES*********************************************************************
 /** Classe que dibuixa el submenu de les opcions de les distància Euclidiana
 *
 * @author Jose I Mateos
 * @version v.0 20/12/06
 */

public class DlgOpcDistanEucli extends JDialog {
  private static Logger logger=Logger.getLogger(DlgOpcDistanEucli.class.getName());
  private OpcionsDis opc;
  private FrPrincipal frPare;
  private GridLayout dis = new GridLayout(3,2);
  private GridBagLayout taula1= new GridBagLayout();
  private GridBagConstraints c = new GridBagConstraints();
  private Border cuadre;
  private TitledBorder tDistan;
  private JPanel panDis =new JPanel();
  private JPanel panBoton =new JPanel();
  private JPanel panDefec = new JPanel();
  private JPanel panCheck1 =new JPanel();
  private JPanel panCheck2 =new JPanel();
  private JPanel panOpc =new JPanel();
  private JPanel panR1 = new JPanel();
  private JPanel panR2 =new JPanel();
  private JPanel panR3 =new JPanel();
  private JButton bAcep = new JButton();
  private JButton bCancel = new JButton();
  private JButton bDefec = new JButton();
  private JRadioButton r1 = new JRadioButton();
  private JRadioButton r2 = new JRadioButton();
  private JRadioButton r3 = new JRadioButton();
  private JCheckBox cb1 = new JCheckBox();
  private JCheckBox cb2 = new JCheckBox();
  private JLabel lBuit1 = new JLabel();
  private JLabel lBuit2 = new JLabel();
  private JLabel lBuit3 = new JLabel();
  private JLabel lBuit4 = new JLabel();
  private JLabel lBuit5 = new JLabel();
  private char opcio;

  /**
  * Constructor
  *
  * @param frame es la finestra pare
  * @param title es el titol de la submennu
  * @param modal indica si la finestra pare no es accesible desde el submenu
  * @param opcions es el valor de les opcions de les distancies
  */
  public DlgOpcDistanEucli(FrPrincipal frame, String title, boolean modal, OpcionsDis opcions) {
	super(frame, title, modal);
    frPare=frame;
    opc = opcions;
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  /**
   * Dibuixa el menu
   * @throws Exception
   */
  private void jbInit() throws Exception {

    lBuit1.setText("                                              ");
    lBuit2.setText("                ");
    lBuit3.setText("                                     ");
    lBuit4.setText("              ");
    lBuit5.setText("           ");
	panOpc.setLayout(taula1);
	panDis.setLayout(dis);
	cuadre = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
    panDefec.add(bDefec);
    panBoton.add(panDefec);
    panBoton.add(bAcep);
    panBoton.add(bCancel);
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

    tDistan = new TitledBorder(cuadre,"Euclìdia");
	panDis.setBorder(tDistan);
	r1.setText("No normalitzar");
	r1.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
      		rENoNor_actionPerformed(e);
       	}
    });
    r2.setText("Normalitzar per la desviació típica");
    r2.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(ActionEvent e) {
       		rESk_actionPerformed(e);
       	}
    });
    r3.setText("Normalitzar pel rang");
	r3.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       		rERang_actionPerformed(e);
       	}
    });
    cb1.setEnabled(true);
    cb1.setText("Treballar amb distàncies al quadrat");
    
    cb2.setEnabled(true);
    cb2.setText("Treballar amb distàncies ponderades");
    

    panR1.add(r1);
    panR1.add(lBuit1);
	panR2.add(r2);
	panR2.add(lBuit2);
	panR3.add(r3);
	panR3.add(lBuit3);

	panDis.add(panR1);
    panDis.add(panR2);
	panDis.add(panR3);
	panCheck1.add(cb1);
	panCheck1.add(lBuit4);
	panCheck2.add(cb2);
	panCheck2.add(lBuit5);

    c.weightx = 1;
    c.weighty = 1;
	c.gridwidth = GridBagConstraints.REMAINDER;
	this.getContentPane().add(panOpc);
	panOpc.add(panDis,c);
	panOpc.add(panCheck1,c);
	panOpc.add(panCheck2,c);
	panOpc.add(panBoton,c);
	
	switch(opc.getCateg()){
		
		case OpcionsDis.SK:
			rESk_actionPerformed(null);
		break;
		case OpcionsDis.RANG:
			rERang_actionPerformed(null);
		break;
		default:
			rENoNor_actionPerformed(null);
		break;
	}
	cb1.setSelected(opc.getQuad());
	cb2.setSelected(opc.getPond());
  }
  /**
   * Posa les opcions per defecte
   * @param e event de seleccionar la opció
   */
  private void bDefec_actionPerformed(ActionEvent e) {
  	int n = JOptionPane.showConfirmDialog(
    this, "Aquesta opció posa tots els paràmetres de la classificació amb els seus valors per defecte. Segur que vols continuar?",
    "Assignació d'opcions per defecte",
    JOptionPane.YES_NO_OPTION);
	if (n == JOptionPane.YES_OPTION) {
		rENoNor_actionPerformed(null);
	}
  }
  /**Cancel·la el menu
   * @param e event de seleccionar la opció
   */
  private void bCancel_actionPerformed(ActionEvent e) {
	frPare.actualitzarBarraEstat(" ", false);
  	dispose();
  }
  /**Actualitza opc amb els valors seleccionats
   * @param e event de seleccionar la opció
   */
  private void bAcep_actionPerformed(ActionEvent e) {
 System.out.println("Le di al aceptar" );

	if (opcio==OpcionsDis.NONOR || opcio==OpcionsDis.SK || opcio==OpcionsDis.RANG){
		opc.setCateg(opcio);
    	opc.setQuad(cb1.isSelected());
    	opc.setPond(cb2.isSelected());
    	frPare.actualitzarBarraEstat(" ", false);
    	dispose();
	}
	else{
		frPare.actualitzarBarraEstat("S'ha d'elegir una de les opcions de distància", true);
	}

  }
  /**Seleciona la distància No normalitzada
   * @param e event de seleccionar la opció
   */
  private void rENoNor_actionPerformed(ActionEvent e){
  	r1.setSelected(true);
	r2.setSelected(false);
  	r3.setSelected(false);
  	opcio=OpcionsDis.NONOR;
  }
  /**Seleciona la distància normalitzada per la Sk
   * @param e event de seleccionar la opció
   */
  private void rESk_actionPerformed(ActionEvent e){
  	r1.setSelected(false);
  	r2.setSelected(true);
  	r3.setSelected(false);
   	opcio=OpcionsDis.SK;
  }
  /**Seleciona la distància normalitzada pel Rang
   * @param e event de seleccionar la opció
   */
  private void rERang_actionPerformed(ActionEvent e){
  	r1.setSelected(false);
  	r2.setSelected(false);
  	r3.setSelected(true);
  	opcio=OpcionsDis.RANG;
  }

}
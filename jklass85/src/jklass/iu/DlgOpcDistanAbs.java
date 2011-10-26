package jklass.iu;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import jklass.util.OpcionsDis;

 //DISTANCIES*********************************************************************
 /** Classe que dibuixa el submenu de les opcions de la distància en Valor Absolut
 *
 * @author Jose I Mateos
 * @version v.0 11/8/06
 */

public class DlgOpcDistanAbs extends JDialog {

  private OpcionsDis opc;
  private GridBagLayout taula1= new GridBagLayout();
  private GridBagConstraints c = new GridBagConstraints();
  private Border cuadre;
  private TitledBorder tDistan;
  private JPanel panDis =new JPanel();
  private JPanel panDefec = new JPanel();
  private JPanel panBoton =new JPanel();
  private JPanel panOpc =new JPanel();
   private JPanel panCheck =new JPanel();
  private JButton bAcep = new JButton();
  private JButton bCancel = new JButton();
  private JButton bDefec = new JButton();
  private JRadioButton r1 = new JRadioButton();
  private JLabel lBuit1 = new JLabel();
  private JCheckBox cb2 = new JCheckBox();
  
  private char opcio;

  /** Constructor
  *
  * @param frame es la finestra pare
  * @param title es el titol de la submennu
  * @param modal indica si la finestra pare no es accesible desde el submenu
  * @param opcions es el valor de les opcions de les distancies
  */
  public DlgOpcDistanAbs(Frame frame, String title,boolean modal, OpcionsDis opcions) {
    super(frame, title,modal);
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

	lBuit1.setText("                                             ");
	panOpc.setLayout(taula1);
	cuadre = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
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
    bCancel.	addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
        	bCancel_actionPerformed(e);
        }
    });
    
    cb2.setEnabled(true);
    cb2.setText("Treballar amb distàncies ponderades");
	
    
    panCheck.add(cb2);
  
    
    

    tDistan = new TitledBorder(cuadre,"Valor Absolut");
	panDis.setBorder(tDistan);
	r1.setText("Normalitzar pel rang");
    panDis.add(r1);
    panDis.add(lBuit1);
	panDefec.add(bDefec);
	panBoton.add(panDefec);
    panBoton.add(bAcep);
    panBoton.add(bCancel);
	c.weightx = 1;
    c.weighty = 1;
	c.anchor = GridBagConstraints.FIRST_LINE_START;
    c.gridwidth = GridBagConstraints.REMAINDER;
	this.getContentPane().add(panOpc);
	panOpc.add(panDis,c);
	panOpc.add(panCheck,c);
    c.anchor = GridBagConstraints.CENTER;
	panOpc.add(panBoton,c);
	
	
	r1.setSelected(opc.getCateg()==OpcionsDis.RANG || opc.getCateg()=='\u0000');

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
		r1.setSelected(true);
	}
  }
  /**
   * Cancel·la el menu
   * @param e event de seleccionar la opció
   */
  private void bCancel_actionPerformed(ActionEvent e) {
  	dispose();
  }
  /**Actualitza les opcions opc ,depenent de si el boto esta o no seleccionat
   * @param e event de seleccionar la opció
   */
  private void bAcep_actionPerformed(ActionEvent e) {
  	if (r1.isSelected()){
	  	opc.setCateg(OpcionsDis.RANG);
	}
	else{
		opc.setCateg(OpcionsDis.NONOR);
	}
	opc.setPond(cb2.isSelected());
    dispose();
  }


}
package jklass.iu;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;


 // DISTANCIES*********************************************************************
 /** Classe que dibuixa el submenu per escollir el numero de columnes a visualitzar en latex
 *
 * @author Jose I Mateos
 * @version v.0 23/8/06
 */

public class DlgColum extends JDialog {

  private FrPrincipal frPare;
  private GridBagLayout taula1= new GridBagLayout();
  private GridBagConstraints c = new GridBagConstraints();
  private Border cuadre;
  private TitledBorder tColumn;
  private JPanel panColum =new JPanel();
  private JPanel panDefec = new JPanel();
  private JPanel panBoton =new JPanel();
  private JPanel panOpc =new JPanel();
  private JButton bAcep = new JButton();
  private JButton bCancel = new JButton();
  private JButton bDefec = new JButton();
  private JLabel lColum = new JLabel();
  private JTextField tColum = new JTextField(10);
  private JLabel eColum = new JLabel();
  private int col;



  /**
  * Constructor
  *
  * @param frame es la finestra pare
  * @param title es el titol de la submennu
  * @param modal indica si la finestra pare no es accesible desde el submenu
  */
  public DlgColum(FrPrincipal frame, String title, boolean modal) {
    super(frame, title, modal);
    frPare=frame;

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

	eColum.setText("");
   	lColum.setText("Numero de columnes per pagina:");
   	tColum.setText("5");
	panOpc.setLayout(taula1);
	cuadre = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
    panBoton.add(panDefec);
	panBoton.add(bAcep);
    panBoton.add(bCancel);
    panDefec.add(bDefec);

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
    tColumn = new TitledBorder(cuadre,"Numero de columnes a mostrar");

	panColum.setBorder(tColumn);
	panColum.add(lColum);
	panColum.add(tColum);
	panColum.add(eColum);


	c.weightx = 1;
    c.weighty = 1;
	c.gridwidth = GridBagConstraints.REMAINDER;
	panOpc.add(panColum,c);
	panOpc.add(panBoton,c);

	this.getContentPane().add(panOpc);


  }
  /**Posa les opcions per defecte
   * @param e event de seleccionar la opció
   */
  private void bDefec_actionPerformed(ActionEvent e) {

	  col=5;
	  tColum.setText(String.valueOf(col));

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
	if (tColum.getText().compareTo("")!=0){
		if (Integer.parseInt(tColum.getText())>0){
			try{
		    	col=(Integer.parseInt(tColum.getText()));
		    	frPare.actualitzarBarraEstat(" ", false);
		    	dispose();
	    	}
	    	catch (Exception ex) {
		       	frPare.actualitzarBarraEstat("Els valors introduits no son correctes", true);
	  		}
  		}
	    else {
		     	frPare.actualitzarBarraEstat("El numero de columnes ha de ser > 0", true);
	    }
	}
    else{
	    frPare.actualitzarBarraEstat("El numero de columnes ha de ser > 0", true);
    }
  }

  int columna(){
	  return col;
  }

}
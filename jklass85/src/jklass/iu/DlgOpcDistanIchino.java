package jklass.iu;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import jklass.util.OpcionsDis;


 // DISTANCIES*********************************************************************
 /** Classe que dibuixa el submenu de les opcions de les distància Ichino-Yaguchi
 *
 * @author Jose I Mateos
 * @version v.0 23/8/06
 */

public class DlgOpcDistanIchino extends JDialog {

  private OpcionsDis opc;
  private FrPrincipal frPare;
  private GridBagLayout taula1= new GridBagLayout();
  private GridBagConstraints c = new GridBagConstraints();
  private GridLayout dis = new GridLayout(2,1);
  private Border cuadre;
  private TitledBorder tDistan;
  private JPanel panDis =new JPanel();
  private JPanel panP =new JPanel();
  private JPanel panGamma =new JPanel();
  private JPanel panBoton =new JPanel();
  private JPanel panOpc =new JPanel();
  private JPanel panDefec = new JPanel();
  private JButton bAcep = new JButton();
  private JButton bCancel = new JButton();
  private JButton bDefec = new JButton();
  private JLabel lP = new JLabel();
  private JTextField tP = new JTextField(10);
  private JLabel eP = new JLabel();
  private JLabel eGamma = new JLabel();
  private JLabel lGamma = new JLabel();
  private JTextField tGamma = new JTextField(10);


  /**
  * Constructor
  *
  * @param frame es la finestra pare
  * @param title es el titol de la submennu
  * @param modal indica si la finestra pare no es accesible desde el submenu
  * @param opcions es el valor de les opcions de les distancies
  */
  public DlgOpcDistanIchino(FrPrincipal frame, String title, boolean modal, OpcionsDis opcions) {
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
  /**Dibuixa el menu
   * @throws Exception
   */
  private void jbInit() throws Exception {

	eP.setText("           enter > 0                        ");
    eGamma.setText("          real entre 0 i 0.5              ");
    eP.setFont(new java.awt.Font("Dialog", 2, 10));
    eGamma.setFont(new java.awt.Font("Dialog", 2, 10));
	lP.setText("p:");
	tP.setText(String.valueOf(opc.getP()));
	lGamma.setText("\u03d2:");
	tGamma.setText(String.valueOf(opc.getGamma()));
	panDis.setLayout(dis);
	panOpc.setLayout(taula1);
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
    tDistan = new TitledBorder(cuadre,"Ichino-Yaguchi");
	panDis.setBorder(tDistan);
	panP.add(lP);
	panP.add(tP);
	panP.add(eP);
	panGamma.add(lGamma);
	panGamma.add(tGamma);
	panGamma.add(eGamma);

	panDis.add(panP);
	panDis.add(panGamma);

	c.weightx = 1;
    c.weighty = 1;
	c.gridwidth = GridBagConstraints.REMAINDER;
	panOpc.add(panDis,c);
	panOpc.add(panBoton,c);

	this.getContentPane().add(panOpc);


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
		tP.setText("2");
		tGamma.setText("0.5");
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
	if (tP.getText().compareTo("")!=0 && tGamma.getText().compareTo("")!=0){
	  	try{
		    if ((Integer.parseInt(tP.getText())>0) && (Float.parseFloat(tGamma.getText())>=0) && (Float.parseFloat(tGamma.getText())<=0.5)){
			    opc.setP(Integer.parseInt(tP.getText()));
		    	opc.setGamma(Float.parseFloat(tGamma.getText()));
		    	frPare.actualitzarBarraEstat(" ", false);
		    	opc.setCateg(OpcionsDis.NOAUTO);
		    	dispose();
	    	}
	    	else{
		    	frPare.actualitzarBarraEstat("Els valors introduits no son correctes", true);
	    	}
	    }
   	    catch (Exception ex) {
		       	frPare.actualitzarBarraEstat("Els valors introduits no son correctes", true);
	  }
    }
    else{
	    frPare.actualitzarBarraEstat("S'han d'omplir els valors de p i \u03d2", true);
    }
  }

}
package jklass.iu;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import jklass.util.OpcionsDis;


 // DISTANCIES*********************************************************************
 /** Classe que dibuixa el submenu de les opcions de les distancies
 *
 * @author Jose I Mateos
 * @version v.0 1/7/06
 */

public class DlgOpcDistanMinko extends JDialog {

  private OpcionsDis opc;
  private FrPrincipal frPare;
  private GridLayout dis = new GridLayout(2,1);
  private GridBagLayout taula1= new GridBagLayout();
  private GridBagConstraints c = new GridBagConstraints();
  private Border cuadre;
  private TitledBorder tDistan;
  private JPanel panRang =new JPanel();
  private JPanel panP =new JPanel();
  private JPanel panBoton =new JPanel();
  private JPanel panDefec =new JPanel();
  private JPanel panOpc =new JPanel();
  private JButton bAcep = new JButton();
  private JButton bCancel = new JButton();
  private JButton bDefec = new JButton();
  private JRadioButton r1 = new JRadioButton();
  private JLabel lP = new JLabel();
  private JLabel eP = new JLabel();
  private JTextField tP = new JTextField(10);



  /**
  * Constructor
  *
  * @param frame es la finestra pare
  * @param title es el titol de la submennu
  * @param modal indica si la finestra pare no es accesible desde el submenu
  * @param opcions es el valor de les opcions de les distancies
  */
  public DlgOpcDistanMinko(FrPrincipal frame, String title, boolean modal, OpcionsDis opcions) {
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

	lP.setText("p:");
	eP.setText("           enter > 0              ");
	eP.setFont(new java.awt.Font("Dialog", 2, 10));
    tP.setText(String.valueOf(opc.getP()));
	panRang.setLayout(dis);
	panOpc.setLayout(taula1);
	cuadre = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
    panBoton.add(bDefec);
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
    bCancel.	addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
        	bCancel_actionPerformed(e);
        }
    });

    tDistan = new TitledBorder(cuadre,"Minkovski");
	panRang.setBorder(tDistan);
	r1.setText("Normalitzar pel rang");

    panRang.add(r1);
    panP.add(lP);
	panP.add(tP);
	panP.add(eP);

	panRang.add(panP);

    c.weightx = 1;
    c.weighty = 1;
	c.gridwidth = GridBagConstraints.REMAINDER;
	this.getContentPane().add(panOpc);

	panOpc.add(panRang,c);
	panOpc.add(panBoton,c);

	r1.setSelected(opc.getCateg()==OpcionsDis.RANG || opc.getCateg()=='\u0000');
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
		r1.setSelected(true);
		tP.setText("2");
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
	if (r1.isSelected()){
	  	opc.setCateg(OpcionsDis.RANG);
  	}
  	else
  	{
	  	opc.setCateg(OpcionsDis.NONOR);
  	}
  	if (tP.getText().compareTo("")!=0){
	  	try{
		    if (Integer.parseInt(tP.getText())>0)
		    {
			    opc.setP(Integer.parseInt(tP.getText()));
			    frPare.actualitzarBarraEstat(" ", false);
			    dispose();
		    }
		    else{
				frPare.actualitzarBarraEstat("El valor de p no es correcte", true);
		    }
	    }
   	    catch (Exception ex) {
		       	frPare.actualitzarBarraEstat("El valor de p no es correcte", true);
	  }
    }
    else
    {
	    frPare.actualitzarBarraEstat("El valor de p s'ha d'omplir", true);
	}

  }

}
package jklass.iu;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import jklass.util.OpcionsDis;
import jklass.nucli.GestorKlass;

 // DISTANCIES*********************************************************************
 /** Classe que dibuixa el submenu de les opcions de la distància de Ralambondrainy
 *
 * @author Jose I Mateos
 * @version v.0 6/8/06
 */

public class DlgOpcDistanRalam extends JDialog {

  private OpcionsDis opc;
  private FrPrincipal frPare;
  private GridLayout dis = new GridLayout(2,2);
  private GridLayout rpi = new GridLayout(2,1);
  private GridBagLayout taula1= new GridBagLayout();
  private GridBagConstraints c = new GridBagConstraints();
  private Border cuadre;
  private TitledBorder tDistan;
  private JPanel panDis =new JPanel();
  private JPanel panRes =new JPanel();
  private JPanel panRes1 =new JPanel();
  private JPanel panRes2 =new JPanel();
  private JPanel panBoton =new JPanel();
  private JPanel panDefec =new JPanel();
  private JPanel panOpc =new JPanel();
  private JPanel panCheck =new JPanel();
   private JPanel panCheck2 =new JPanel();
  private JButton bAcep = new JButton();
  private JButton bCancel = new JButton();
  private JButton bDefec = new JButton();
  private JRadioButton r1 = new JRadioButton();
  private JRadioButton r2 = new JRadioButton();
  private JLabel lpi1 = new JLabel();
  private JLabel lpi2 = new JLabel();
  private JLabel lBuit1 = new JLabel();
  private JLabel lBuit2 = new JLabel();
  private JTextField tpi1 = new JTextField(10);
  private JTextField tpi2 = new JTextField(10);
  private JCheckBox cb2 = new JCheckBox();
  private JLabel lBuit = new JLabel();
  private char opcio;
  private GestorKlass gestor;
  private int numer;
  private boolean matriu;
  private int modal;
  private float correl;


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
  public DlgOpcDistanRalam(FrPrincipal frame, String title, boolean modal, OpcionsDis opcions,boolean m,GestorKlass gk) {
    super(frame, title, modal);
    opc = opcions;
    gestor=gk;
    matriu=m;
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

	String[][] llProps;


	if (matriu){//nomes es mostren els valors per una matriu
		modal=gestor.valorModal();
		correl=gestor.valorCorrelacio();
 	 	llProps=gestor.obtenirLlistaIDsProps();
		numer=llProps[0].length;
		lpi1.setText("\u03a0 1:");
		lpi2.setText("\u03a0 2:");
	  	tpi1.setEnabled(false);
		tpi2.setEnabled(false);
	}

	panOpc.setLayout(taula1);
	panDis.setLayout(dis);
	panRes.setLayout(rpi);
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

    tDistan = new TitledBorder(cuadre,"Ralambondrainy");
	panDis.setBorder(tDistan);
	r1.setText("Inercia");
	r2.setText("Norma");
	if (!matriu){
		lBuit1.setText("                                          ");
		lBuit2.setText("");
	}
	else{
		lBuit1.setText("                 ");
		lBuit2.setText("");
	}

	cb2.setEnabled(true);
    cb2.setText("Treballar amb distàncies ponderades");
	lBuit.setText("                ");






	r1.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       		rInercia_actionPerformed(e);
       	}
    });

	r2.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       		rNorma_actionPerformed(e);
       	}
    });
    panDis.add(r1);
    panDis.add(lBuit1);
    panDis.add(r2);
    panDis.add(lBuit2);


	if (matriu){
		panRes1.add(lpi1);
		panRes1.add(tpi1);
		panRes2.add(lpi2);
		panRes2.add(tpi2);
		panRes.add(panRes1);
		panRes.add(panRes2);
	}

	c.weightx = 1;
    c.weighty = 1;
    if (!matriu){
	    c.gridwidth = GridBagConstraints.REMAINDER;
    }
	panOpc.add(panDis,c);
	if (matriu){
		c.gridwidth = GridBagConstraints.REMAINDER;
		panOpc.add(panRes,c);
	}

	panCheck2.add(cb2);
	panCheck2.add(lBuit);



	panOpc.add(panCheck,c);
	//panOpc.add(panCheck2,c);
	panOpc.add(panBoton,c);

	this.getContentPane().add(panOpc);

	switch(opc.getCateg()){

		case OpcionsDis.NORMA:
			rNorma_actionPerformed(null);
		break;
		default:
			rInercia_actionPerformed(null);
		break;
	}

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
		rInercia_actionPerformed(null);
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
  	opc.setCateg(opcio);
  	opc.setPond(cb2.isSelected());
	frPare.actualitzarBarraEstat(" ", false);
  	dispose();
  }
  /**Seleciona la distància Ralambondrainy per la Inercia
   * @param e event de seleccionar la opció
   */
  private void rInercia_actionPerformed(ActionEvent e){
  	opcio=OpcionsDis.INER;
  	r1.setSelected(true);
  	r2.setSelected(false);
  	if (matriu){
	  	if (numer==0){
			tpi1.setText("0");
		}
		else{
			tpi1.setText(String.valueOf((float)1/numer));
		}
		if (modal==0){
			tpi2.setText("0");
		}
		else{
			tpi2.setText(String.valueOf((float)1 /(modal-1)));
		}
	}
  }
  /**Seleciona la distància Ralambondrainy per la Norma
   * @param e event de seleccionar la opció
   */
  private void rNorma_actionPerformed(ActionEvent e){
  	opcio=OpcionsDis.NORMA;
  	r1.setSelected(false);
  	r2.setSelected(true);
  	if (matriu){
	  	if (correl==0){
			tpi1.setText("0");
		}
		else{
			tpi1.setText(String.valueOf((float)1/(float)Math.sqrt(correl)));
		}
		if (modal==0){
			tpi2.setText("0");
		}
		else{
			tpi2.setText(String.valueOf((float)Math.sqrt(modal-1)));
		}
	}
  }




}
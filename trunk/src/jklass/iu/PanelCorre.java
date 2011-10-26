package jklass.iu;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.lang.InterruptedException;
import java.util.logging.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;
import jklass.nucli.GestorKlass;
import jklass.util.SO;


//DISTANCIES*********************************************************************
 /** Classe que crea el menu per mostrar la matriu de correlacions o covaràncies
 * de la matriu carregada
 *
 * @author Jose I Mateos
 * @version v.0 20/8/06
 */
public class PanelCorre extends JPanel {

  //Atributs per crear el menu

  private static Logger logger = Logger.getLogger(PanelCorre.class.getName());
  private FrPrincipal frPare;
  private GestorKlass gestor;
  private Object[][] result;
  private JTable tResult;
  private JScrollPane tScrollR;
  private DefaultListModel listR;
  private JList capça;
  private TitledBorder tbResult;
  private Border cuadre;
  private GridBagLayout taula1= new GridBagLayout();
  private GridBagConstraints c = new GridBagConstraints();
  private JPanel panResult =new JPanel();
  private JPanel panBoton =new JPanel();
  private JPanel panDown =new JPanel();
  private JPanel panReset =new JPanel();

  private JPanel panGuard =new JPanel();
  private JPanel panQuad =new JPanel();
  private JPanel panCov =new JPanel();
  private JLabel lBuit1 = new JLabel();
  private JLabel lBuit2 = new JLabel();
  private JLabel lBuit3 = new JLabel();


  private JButton bAcep = new JButton();
  private JButton bCancel = new JButton();
  private JButton bReset = new JButton();
  private JCheckBox guardar = new JCheckBox();
  private JCheckBox quad = new JCheckBox();
  private JCheckBox cov = new JCheckBox();
  private String nomFitxer = null;
  private int props;
  private String[][]llProps;


  /**
  * Constructor
  *
  * @param fr es la finestra pare
  * @param gk es el gestor d'on penja la matriu, o es null si es de valors introduits per l'usuari
  */
  public PanelCorre(FrPrincipal fr,GestorKlass gk) {

    frPare = fr;
    gestor = gk;
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  /**Dibuixa el menu de les correlacions per les propietats numeriques de la matriu
   * @throws Exception
   */
  private void jbInit() throws Exception {

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
    bReset.setText("Netejar");
    bReset.setEnabled(true);
    bReset.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       		bReset_actionPerformed(e);
    	}
    });
	listR = new DefaultListModel();
	llProps = gestor.obtenirLlistaIDsProps();
	props = llProps[0].length;
	if (props>0)
	{
		if (!gestor.obtenirMiss()){
			for (int i = 0; i < props; i++) {
				listR.insertElementAt(llProps[0][i],i);
			}
			result = new Object [props][props];
			tResult = new JTable(result, llProps[0]);
			tScrollR = new JScrollPane(tResult);
			tResult.setPreferredScrollableViewportSize(new Dimension(450, 300));
			capça = new JList(listR);
			tScrollR.setRowHeaderView(capça);
			capça.setCellRenderer(new RowHeaderRenderer (tResult));
			capça.setFixedCellHeight(tResult.getRowHeight());
			tResult.setCellSelectionEnabled(false);
			tResult.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			frPare.setSize(570,600);
			this.setLayout(taula1);
			cuadre = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
			tbResult = new TitledBorder(cuadre,"Matriu de Correlacions");
			this.setBorder(tbResult);
			panResult.add(tScrollR);
			c.weightx = 1;
			c.weighty = 1;
			c.gridwidth = GridBagConstraints.REMAINDER;
			this.add(panResult,c);
			panReset.add(bReset);
			panBoton.add(bAcep);
   			panDown.add(panBoton);
   			panBoton.add(bCancel);
   			panDown.add(panReset);
   			panDown.add(panBoton);

   			guardar.setEnabled(true);
    		guardar.setSelected(false);
   			guardar.setText("Salvar l'arxiu .co");
   			quad.setEnabled(true);
    		quad.setSelected(false);
   			quad.setText("Correlació al quadrat");
   			cov.setEnabled(true);
    		cov.setSelected(false);
   			cov.setText("Covariancia");

   			lBuit1.setText("                                                                                                                              ");
   			lBuit2.setText("                                                                                                                       ");
   			lBuit3.setText("                                                                                                                                      ");

   			panGuard.add(guardar);
   			panGuard.add(lBuit1);
   			panQuad.add(quad);
   			panQuad.add(lBuit2);
   			panCov.add(cov);
   			panCov.add(lBuit3);





   			c.anchor = GridBagConstraints.CENTER;

			this.add(panGuard,c);
			this.add(panQuad,c);
			this.add(panCov,c);
  			this.add(panDown,c);
		}
		else{
			frPare.actualitzarBarraEstat("No es pot calcular la Matriu de Correlacions/Covariances amb missings en les variables numeriques", true);
		}
	}
	else{
		frPare.actualitzarBarraEstat("No es pot calcular la Matriu de Correlacions/Covariances sobre una matriu sense variables numeriques", true);
	}
	frPare.centrar();
}
  /**Fa el calcul de la matriu de correlacions y salva l'arxiu .cor si ha estat seleccuionat
   * @param e event de seleccionar la opció
   */
  private void bAcep_actionPerformed(ActionEvent e) {

   	String[][] cor;
   	String opc="";
   	int ele=1;

   	if (quad.isSelected()){
		ele=2;
		opc="al quadrat " ;
	};
	if (cov.isSelected()){

		cor=gestor.covarianza();
		for (int i = 0; i < props; i++) {
			for (int j = 0; j < props; j++) {
				result[j][i]=cor[j][i];
			}
		}
		opc=opc+ "de covariàncies";
	}
	else{
	  	cor=gestor.correlacio(ele);
		for (int i = 0; i < props; i++) {
			for (int j = 0; j < props; j++) {
				result[j][i]=cor[j][i];
			}
		}
		opc=opc+ "de correlacions";
	}
	tResult.repaint();
	frPare.actualitzarBarraEstat("S'ha fet el càlcul de la matriu" + opc , false);
	if (guardar.isSelected()){
		gestor.escriuMatriuCo(result,opc);
	};

  }
  /** Cancel·la el menu de les distancies per valors de la matriu
   * @param e event de seleccionar la opció
   */
  private void bCancel_actionPerformed(ActionEvent e) {

	frPare.setSize(new Dimension(550, 300));
	frPare.actualitzarBarraEstat(" ", false);
	frPare.remove(this);
	frPare.validate();
	frPare.repaint();
  }
  /** Borra els valors de la matriu de resultats
   * @param e event de seleccionar la opció
   */
  private void bReset_actionPerformed(ActionEvent e) {

	for (int i = 0; i < props; i++) {
	    for (int j = 0; j < props; j++) {
	 	    result[j][i]="";
	    }
    }
	tResult.repaint();
	guardar.setSelected(false);
	quad.setSelected(false);
  }
}
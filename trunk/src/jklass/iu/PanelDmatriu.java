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
import jklass.util.OpcionsDis;



// DISTANCIES*********************************************************************
 /** Classe que crea el menu per seleecionar el tipus de distància a calcular pels valors
 * de la matriu carregada
 *
 * @author Jose I Mateos
 * @version v.0 20/5/06
 */
public class PanelDmatriu extends JPanel {

  //Atributs per crear el menu
  private PanelD pDistan;
  private static Logger logger = Logger.getLogger(PanelDmatriu.class.getName());
  private FrPrincipal frPare;
  private GestorKlass gestor;
  private OpcionsDis opc =new OpcionsDis();
  private Object[][] result;
  private JTable tResult;
  private JScrollPane tScrollR;
  private DefaultListModel listR;
  private JList capça;
  private TitledBorder tDistan;
  private TitledBorder tbResult;
  private Border cuadre;
  private GridBagLayout taula1= new GridBagLayout();
  private GridBagConstraints c = new GridBagConstraints();
  private JPanel panResult =new JPanel();
  private JPanel panBoton =new JPanel();
  private JPanel panDown =new JPanel();
  private JPanel panReset =new JPanel();
  private JButton bAcep = new JButton();
  private JButton bCancel = new JButton();
  private JButton bReset = new JButton();
  private JButton bDefec = new JButton();
  private String nomFitxer = null;
  private int objec;
  private JCheckBox guardar = new JCheckBox();
  private JCheckBox guardarLatex = new JCheckBox();
  private String[]llObjs;


  /**
  * Constructor
  *
  * @param fr es la finestra pare
  * @param gk es el gestor d'on penja la matriu, o es null si es de valors introduits per l'usuari
  */
  public PanelDmatriu(FrPrincipal fr,GestorKlass gk) {

    frPare = fr;
    gestor = gk;
    pDistan=new PanelD(frPare,opc,gestor,true);
    nomFitxer = frPare.obtenirNomDades();
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  /**Dibuixa el menu de les distacies per valors de la matriu
   * @throws Exception
   */
  private void jbInit() throws Exception {

	listR = new DefaultListModel();
	llObjs = gestor.obtenirLlistaIDsObjs();

	objec = llObjs.length;
  	for (int i = 0; i < objec; i++) {
	    listR.insertElementAt(llObjs[i],i);
	}
	result =  new Object [objec][objec];
	tResult = new JTable(result, llObjs);
	tScrollR = new JScrollPane(tResult);
	tResult.setPreferredScrollableViewportSize(new Dimension(285,285));
	capça = new JList(listR);
	tScrollR.setRowHeaderView(capça);
	capça.setCellRenderer(new RowHeaderRenderer (tResult));
	capça.setFixedCellHeight(tResult.getRowHeight());
	tResult.setCellSelectionEnabled(false);
	tResult.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	frPare.setSize(755,570);
	this.setLayout(taula1);
	cuadre = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
	tDistan = new TitledBorder(cuadre,"Distàncies");
	tbResult = new TitledBorder(cuadre,"Matriu de Resultats");
	this.setBorder(tDistan);
	panResult.setBorder(tbResult);

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
    bDefec.setText("Per defecte");
    bDefec.setEnabled(true);
    bDefec.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       		bDefec_actionPerformed(e);
    	}
    });
    panResult.add(tScrollR);
	panReset.add(bReset);
	panReset.add(bDefec);
	panBoton.add(bAcep);
   	panBoton.add(bCancel);
   	panDown.add(panReset);
   	panDown.add(panBoton);
   	guardar.setEnabled(true);
    guardar.setSelected(false);
   	guardar.setText("Salvar l'arxiu .dis");
   	guardarLatex.setEnabled(true);
    guardarLatex.setSelected(false);
   	guardarLatex.setText("Salvar l'arxiu Dis.tex");





   	c.weightx = 1;
	c.weighty = 1;
	c.anchor = GridBagConstraints.FIRST_LINE_START;
	this.add(pDistan,c);
	c.gridwidth = GridBagConstraints.REMAINDER;
    this.add(panResult,c);
    c.anchor = GridBagConstraints.WEST;
    this.add(guardar,c);
    this.add(guardarLatex,c);
    c.anchor = GridBagConstraints.CENTER;
	this.add(panDown,c);
	frPare.centrar();
  }
  /** Cancel·la el menu de les distancies per valors de la matriu
   * @param e event de seleccionar la opció
   */
  private void bCancel_actionPerformed(ActionEvent e) {

	frPare.actualitzarBarraEstat(" ", false);
	frPare.remove(this);
	frPare.validate();
	frPare.repaint();
	frPare.setSize(new Dimension(550, 300));
  }
  /** Borra els valors de la matriu de resultats
   * @param e event de seleccionar la opció
   */
  private void bReset_actionPerformed(ActionEvent e) {

	for (int i = 0; i < objec; i++) {
	    for (int j = 0; j < objec; j++) {
	 	    result[j][i]="";
	    }
    }
    pDistan.netejar();
	tResult.repaint();
  }
  /** Posa la distància per defecte depenen de la matriu donada
   * @param e event de seleccionar la opció
   */
  private void bDefec_actionPerformed(ActionEvent e) {
	String[][] llProps;
	int lonprop0;
	int lonprop1;
	int lonprop2;
	int lonprop3;

	llProps=gestor.obtenirLlistaIDsProps();
	lonprop0=llProps[0].length;
	lonprop1=llProps[1].length;
	lonprop2=llProps[2].length;
	lonprop3=llProps[3].length;

	int n = JOptionPane.showConfirmDialog(
    this, "Aquesta opció posa tots els paràmetres de la classificació amb els seus valors per defecte. Segur que vols continuar?",
    "Assignació d'opcions per defecte",
    JOptionPane.YES_NO_OPTION);
	if (n == JOptionPane.YES_OPTION) {
		if (lonprop0==0){
			pDistan.rChi2_actionPerformed(null);
			pDistan.opc.setCateg(OpcionsDis.NOAUTO);
		}
		if (lonprop1+lonprop2+lonprop3==0){
			pDistan.rEucli_actionPerformed(null);
			pDistan.opc.setCateg(OpcionsDis.NONOR);
		}
		if (lonprop0>0 && lonprop1+lonprop2+lonprop3>0){
			pDistan.rMGibert_actionPerformed(null);
			pDistan.opc.setCateg(OpcionsDis.AUTO);

		}
	}
  }
  /** Comprova que la matriu carregada estigui dins els parametres
  * de la distància seleccionada
  */
  private boolean comprovarDades() {

	boolean ok=true;
	String[][] llProps;
	int lonprop0;
	int lonprop1;
	int lonprop2;
	int lonprop3;

	llProps=gestor.obtenirLlistaIDsProps();
	lonprop0=llProps[0].length;
	lonprop1=llProps[1].length;
	lonprop2=llProps[2].length;
	lonprop3=llProps[3].length;
	switch(opc.getTipus()){

		case OpcionsDis.EUCLI:
		 	if ((lonprop1+lonprop2+lonprop3)>0){
			 	frPare.actualitzarBarraEstat("En la distància Euclidea, la matriu de dades no pot contenir variables categoriques", true);
				ok=false;
	 		}
	 		else{
		 		if (gestor.obtenirMiss()){
		 			frPare.actualitzarBarraEstat("En la distància Euclidea,la matriu de dades no pot contenir missings", true);
		 			ok=false;
	 			}
 			}
	 	 	break;
	 	case OpcionsDis.ABS:
		 	if ((lonprop1+lonprop2+lonprop3)>0){
			 	frPare.actualitzarBarraEstat("En la distància Absoluta,la matriu de dades no pot contenir variables categoriques", true);
				ok=false;
	 		}
	 		else{
		 		if (gestor.obtenirMiss()){
		 			frPare.actualitzarBarraEstat("En la distància Absoluta,la matriu de dades no pot contenir missings", true);
		 			ok=false;
	 			}
 			}
	 	break;
	 	case OpcionsDis.HAMM:
		 	if ((lonprop0)>0){
			 	frPare.actualitzarBarraEstat("En la distància Hamming,la matriu de dades no pot contenir variables numèriques", true);
				ok=false;
	 		};
	 	break;
	 	case OpcionsDis.CHI2:
		 	if ((lonprop0)>0){
			 	frPare.actualitzarBarraEstat("En la distància CHI^2,la matriu de dades no pot contenir variables numèriques", true);
				ok=false;
	 		};
	 	break;
	 	case OpcionsDis.MIXTA:
		 	if (gestor.obtenirMiss()){
		 		frPare.actualitzarBarraEstat("En la distància Mixta Gibert,la matriu de dades no pot contenir missings en les variables numèriques", true);
		 		ok=false;
	 		}
	 	break;
	 	case OpcionsDis.GOWDA:
		 	if (gestor.obtenirMiss()){
		 		frPare.actualitzarBarraEstat("En la distància Gowda-Diday,la matriu de dades no pot contenir missings en les variables numèriques", true);
		 		ok=false;
	 		}
	 	break;
	 	case OpcionsDis.RALAM:
		 	if (gestor.obtenirMiss()){
		 		frPare.actualitzarBarraEstat("En la distància Ralambondrainy,la matriu de dades no pot contenir missings en les variables numèriques", true);
		 		ok=false;
	 		}
	 	break;
		case OpcionsDis.MINKO:
		 	if ((lonprop1+lonprop2+lonprop3)>0){
			 	frPare.actualitzarBarraEstat("En la distància Minkovski, la matriu de dades no pot contenir variables categoriques", true);
				ok=false;
	 		}
	 		else{
		 		if (gestor.obtenirMiss()){
		 			frPare.actualitzarBarraEstat("En la distància Minkovski,la matriu de dades no pot contenir missings", true);
		 			ok=false;
	 			}
 			}
 		break;
		case OpcionsDis.ICHINO:
			if (gestor.obtenirMiss()){
				frPare.actualitzarBarraEstat("En la distància Ichino-Yaguchi,la matriu de dades no pot contenir missings en les variables numèriques", true);
		 		ok=false;
	 		}
 		break;

	}
 	return ok;
  }
  /** Fa el calcul de la matriu selecionada per la distància seleccionada
   * @param e event de seleccionar la opció
   */
  private void bAcep_actionPerformed(ActionEvent e) {

	String[][] distancies;
	int col=0;
	String pathResult = gestor.obtenirDirResultats();
    File dirResult = new File(pathResult);
    String cmd;
    Process proc;
    int err;


 	opc=pDistan.getOpcions();
 	if (comprovarDades()) {
	 	if (opc.getTipus()=='\u0000' ){
			frPare.actualitzarBarraEstat("S'ha d'elegir un tipus de distància", true);
			gestor.prova();
		}
		else{
			if (opc.getCateg()=='\u0000' && (opc.getTipus()==OpcionsDis.EUCLI || opc.getTipus()==OpcionsDis.ABS ||
			opc.getTipus()==OpcionsDis.MINKO || opc.getTipus()==OpcionsDis.MIXTA || opc.getTipus()==OpcionsDis.ICHINO
			|| opc.getTipus()==OpcionsDis.RALAM || opc.getTipus()==OpcionsDis.CHI2)){
				frPare.actualitzarBarraEstat("S'ha d'elegir/omplir les opcions de la distància seleccionada", true);
			}
			else{
				if (guardarLatex.isSelected()) {
					DlgColum dlg = new DlgColum(frPare, "Opcions del fitxer resultat",true);
					dlg.setLocationRelativeTo(this);
					dlg.show();
					if (dlg.columna()==0){
						guardarLatex.setSelected(false);
					}
					else
					{
						col=dlg.columna();
					}
				}
	 			distancies = gestor.obtenirDistancies(opc);
 	    		for (int i = 0; i < objec; i++) {
				    for (int j = 0; j < objec; j++) {
			    		if(j==i){
						    result[j][i]="0";
						}
						if (j<i){
							result[j][i]=distancies[j][i];
						}
						if (j>i){
							result[j][i]=distancies[i][j];
						}
					}
				}
				tResult.repaint();
				frPare.actualitzarBarraEstat("S'ha fet el càlcul de la distància seleccionada", false);
				if (guardar.isSelected()) {
					gestor.escriuMatriuDis(result,opc);
				}
				if (guardarLatex.isSelected()) {
					String nom = new File(nomFitxer).getName();
					gestor.escriuMatriuDisTex(result,col,opc);
					try {
						pathResult = pathResult + nom + "Dis";

						cmd = SO.obtenirCmdCompilaLtx(pathResult + ".tex");
                        if (cmd == null) {
              /** @todo També es podria obrir una finestra */
              				frPare.actualitzarBarraEstat("No es pot compilar ni visualitzar automàticament el fitxer Latex generat per no haver-se reconegut el sistema operatiu.", true);
              			}
            			else {
	            			logger.finer("Comanda compilació (exec): " + cmd);
	            			proc = Runtime.getRuntime().exec(cmd, null, dirResult);
	            			try {
		            			// error?
		            			StreamGobbler errorGobbler = new
		            			StreamGobbler(proc.getErrorStream(), "ERROR (exec)");

               					// output?
                				StreamGobbler outputGobbler = new
                    			StreamGobbler(proc.getInputStream(), "OUTPUT (exec)");

                				errorGobbler.start();
                				outputGobbler.start();

                				err = proc.waitFor();
                				if (err == 0) {
	                				cmd = SO.obtenirCmdVisorLtx(pathResult + ".dvi");
	                				logger.finer("Comanda visor (exec): " + cmd);
	                				Runtime.getRuntime().exec(cmd, null, dirResult);
	                			}
                				else {
                  						frPare.actualitzarBarraEstat("No s'ha pogut compilar correctament el fitxer Latex generat. (Error " + err + ")" , true);
                				}
              				}
              				catch (InterruptedException exc) {
	              				frPare.actualitzarBarraEstat("S'ha interrumput el procés de compilació del fitxer Latex ( InterruptedException " + exc.getMessage() + " )", true);
              				}
            			}
          			}
          			catch (IOException exc) {
            			frPare.actualitzarBarraEstat("Error al generar i visualitzar el latex ( IOException " + exc.getMessage() + " )", true);
          			}
       			}
			}
		}
	}
  }

  /** Mostra un dialog per escollir el numero de columnes a visualitzar en latex
   *
   */
  private int dialogColum() {
	  DlgColum dlg = new DlgColum(frPare, "Opcions del fitxer resultat",true);
	  dlg.setLocationRelativeTo(this);
	  dlg.show();
	  return dlg.columna();
  }
}
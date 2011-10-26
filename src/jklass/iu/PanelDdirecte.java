package jklass.iu;

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
import javax.swing.event.*;
import javax.swing.table.*;

 // DISTANCIES*********************************************************************
 /** Classe que crea el menu per seleecionar el tipus de distància a calcular pels valors
 * introduits per l'usuari
 *
 * @author Jose I Mateos
 * @version v.0 10/6/06
 */

public class PanelDdirecte extends JPanel {

  //Atributs per crear el menu
  private PanelD pDistan;
  private static Logger logger = Logger.getLogger(PanelDdirecte.class.getName());
  private FrPrincipal frPare;
  private   GestorKlass gestor;
  private OpcionsDis opc =new OpcionsDis();
  private JLabel lResul = new JLabel();
  private JLabel lEspai = new JLabel();
  private JTextField tResul = new JTextField(17);
  private String[] eixosNN = { "i", "i'" };
  private int files = 25, columnes = 2;
  private Object[][] dadesN =  new Object [files][columnes];
  private Object[][] dadesC =  new Object [files][columnes];
  private JTable tDadesN = new JTable(dadesN, eixosNN);
  private JTable tDadesC = new JTable(dadesC, eixosNN);
  private JScrollPane tScrollN = new JScrollPane(tDadesN);
  private JScrollPane tScrollC = new JScrollPane(tDadesC);
  private DefaultListModel listN = new DefaultListModel();
  private DefaultListModel listC = new DefaultListModel();
  private JList capçaN = new JList(listN);
  private JList capçaC = new JList(listC);
  private TitledBorder tDistan;
  private TitledBorder ttDadesN;
  private TitledBorder ttDadesC;
  private Border cuadre;
  private GridBagLayout taula1= new GridBagLayout();
  private GridBagConstraints c = new GridBagConstraints();
  private GridLayout dad = new GridLayout(2,1);
  private JPanel panReset =new JPanel();
  private JPanel panLlista =new JPanel();
  private JPanel panDadesN =new JPanel();
  private JPanel panDadesC =new JPanel();
  private JPanel panResult =new JPanel();
  private JPanel panBoton =new JPanel();
  private JPanel panDown =new JPanel();
  private JButton bAcep = new JButton();
  private JButton bCancel = new JButton();
  private JButton bReset = new JButton();
  private JCheckBox guardar = new JCheckBox();
  private int filesN;
  private int filesC;
  private double min;
  private double max;
  private int mCarregada;
  private JCheckBox guardarLatex = new JCheckBox();  
  private String[]llObjs={"X1","X2"};

  /**
  * Constructor
  *
  * @param fr es la finestra pare
  * @param gk es el gestor d'on penja la matriu, o es null si es de valors introduits per l'usuari
  */
  public PanelDdirecte(FrPrincipal fr,GestorKlass gk) {
    int lon;

    frPare = fr;
    gestor = gk;
    pDistan=new PanelD(frPare,opc,gestor,false);
	mCarregada=gestor.idMatriuActual();
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  /**Dibuixa el menu de les distacies per valors introduits
   * @throws Exception
   */
  private void jbInit() throws Exception {

	frPare.actualitzarBarraEstat("Es fa el càlcul amb els valors introduïts fins la primera fila buida", false);
    for (int i = 0; i < files; i++) {
	    listN.insertElementAt("X"+ new Integer(i+1), i);
	    }
	tScrollN.setRowHeaderView(capçaN);
	tScrollN.setMaximumSize(new Dimension(32767, 32767));
	tScrollN.setPreferredSize(new Dimension(200, 153));
	capçaN.setCellRenderer(new RowHeaderRenderer (tDadesN));
	capçaN.setFixedCellHeight(tDadesN.getRowHeight());
	tDadesN.setCellSelectionEnabled(true);
	tDadesN.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	for (int i = 0; i < files; i++) {
	    listC.insertElementAt("X"+ new Integer(i+1), i);
	    }
	tScrollC.setRowHeaderView(capçaC);
	tScrollC.setMaximumSize(new Dimension(32767, 32767));
	tScrollC.setPreferredSize(new Dimension(200, 153));
	capçaC.setCellRenderer(new RowHeaderRenderer (tDadesC));
	capçaC.setFixedCellHeight(tDadesC.getRowHeight());
	tDadesC.setCellSelectionEnabled(true);
	tDadesC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	frPare.setSize(540,600);
	this.setLayout(taula1);
	cuadre = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
	tDistan = new TitledBorder(cuadre,"Distàncies");
	ttDadesN = new TitledBorder(cuadre,"Dades numèriques");
	ttDadesC = new TitledBorder(cuadre,"Dades Categòriques");
	this.setBorder(tDistan);
	panLlista.setLayout(dad);
	panDadesN.setBorder(ttDadesN);
	panDadesC.setBorder(ttDadesC);

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
	bReset.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       			bReset_actionPerformed(e);
    	}
    });

	lResul.setText("Resultat:");
	lEspai.setText("         ");
	tResul.setEditable(false);
    tResul.setHorizontalAlignment(JTextField.CENTER);
    panDadesN.add(tScrollN);
    panDadesC.add(tScrollC);
    panLlista.add(panDadesN);
    panLlista.add(panDadesC);
   	panReset.add(bReset);
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
   	
   	
    panResult.add(lResul);
   	panResult.add(tResul);
   	panResult.add(lEspai);
   	

   	c.weightx = 1;
	c.weighty = 1;
	c.gridheight = 1;
	c.anchor = GridBagConstraints.FIRST_LINE_START;
	this.add(panLlista,c);
	c.gridheight = 1;
	c.gridwidth = GridBagConstraints.REMAINDER;
	this.add(pDistan,c);
    c.anchor = GridBagConstraints.WEST;
	this.add(guardar,c);
	this.add(guardarLatex,c);
	c.anchor = GridBagConstraints.CENTER;
	this.add(panResult,c);
	this.add(panDown,c);

    ListSelectionModel filaN = tDadesN.getSelectionModel();
	filaN.addListSelectionListener(new ListSelectionListener(){
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting()) return;
			ListSelectionModel lms =(ListSelectionModel)e.getSource();
			if(!lms.isSelectionEmpty()){
				int colum;
				int fila;
				colum=tDadesN.getSelectedColumn();
				fila=tDadesN.getSelectedRow();
				dadesN[fila][colum]=null;
				tDadesN.repaint();
			}
		}
	});
	ListSelectionModel colN = tDadesN.getColumnModel().getSelectionModel();
	colN.addListSelectionListener(new ListSelectionListener(){
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting()) return;
			ListSelectionModel lms =(ListSelectionModel)e.getSource();
			if(!lms.isSelectionEmpty()){
				int colum;
				int fila;
				colum=tDadesN.getSelectedColumn();
				fila=tDadesN.getSelectedRow();
				dadesN[fila][colum]=null;
			    tDadesN.repaint();
			}
		}
	});
	ListSelectionModel filaC = tDadesC.getSelectionModel();
	filaC.addListSelectionListener(new ListSelectionListener(){
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting()) return;
			ListSelectionModel lms =(ListSelectionModel)e.getSource();
			if(!lms.isSelectionEmpty()){
				int colum;
				int fila;
				colum=tDadesC.getSelectedColumn();
				fila=tDadesC.getSelectedRow();
				dadesC[fila][colum]=null;
				tDadesC.repaint();
			}
		}
	});
	ListSelectionModel col = tDadesC.getColumnModel().getSelectionModel();
	col.addListSelectionListener(new ListSelectionListener(){
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting()) return;
			ListSelectionModel lms =(ListSelectionModel)e.getSource();
			if(!lms.isSelectionEmpty()){
				int colum;
				int fila;
				colum=tDadesC.getSelectedColumn();
				fila=tDadesC.getSelectedRow();
				dadesC[fila][colum]=null;
			    tDadesC.repaint();
			}
		}
	});
	frPare.centrar();
  }
  /** Borra els valors de les variables
   * @param e event de seleccionar la opció
   */
  private void bReset_actionPerformed(ActionEvent e) {

	if (tDadesN.isEditing()){
		TableCellEditor celN = tDadesN.getCellEditor();
		celN.stopCellEditing();
	}
    if (tDadesC.isEditing()){
		TableCellEditor celC = tDadesC.getCellEditor();
		celC.stopCellEditing();
	}
    for (int i = 0; i < files; i++) {
	    dadesN[i][0]=null;
	    dadesN[i][1]=null;
	    dadesC[i][0]=null;
	    dadesC[i][1]=null;
	}
	pDistan.netejar();
	tDadesN.repaint();
	tDadesC.repaint();
	tResul.setText("");
  }
  /** Cancel·la el menu de les distancies per valors de la matriu
   * @param e event de seleccionar la opció
   */
  private void bCancel_actionPerformed(ActionEvent e) {

	frPare.remove(this);
  	frPare.validate();
  	frPare.repaint();
  }
  /** Comprova que les dades introduides per l'usuari siguin correctes
   * @param e event de seleccionar la opció
   */
  private boolean comprovarDades() {

	min=0;
	max=0;
	filesN=0;
	int okN=0;
	filesC=0;
	int okC=0;
	String val1;
	String val2;


	if (tDadesN.isEditing()){
		TableCellEditor celN = tDadesN.getCellEditor();
		celN.stopCellEditing();
	}

	while (filesN < files && okN==0) {
	    if (dadesN[filesN][0]!=null && dadesN[filesN][1]!=null){
		    try {
	       		val1=String.valueOf(dadesN[filesN][0]);
		    	val2=String.valueOf(dadesN[filesN][1]);
 			    if (val1.compareTo("?")!=0){
					if(min>Double.parseDouble(val1)){
						min=Double.parseDouble(val1);
					}
					if(max<Double.parseDouble(val1)){
					max=Double.parseDouble(val1);
					}
				}
				if (val2.compareTo("?")!=0){
					if(min>Double.parseDouble(val2)){
					min=Double.parseDouble(val2);
					}
					if(max<Double.parseDouble(val2)){
					max=Double.parseDouble(val2);
					}
				}
		 	}
      		catch (Exception ex) {
		       	frPare.actualitzarBarraEstat("Els valors introduits no son correctes", true);
	      		okN=-1;
		  	}
		    filesN++;
		}
		else if (dadesN[filesN][0]==null && dadesN[filesN][1]==null){
			okN=1;
		}
		else{
			frPare.actualitzarBarraEstat("No hi ha la mateixa quantitat de valors en Xi que en Xi' en les variables numèriques", true);
			okN=-1;
		}
	}
	if (tDadesC.isEditing()) {
		TableCellEditor celC = tDadesC.getCellEditor();
		celC.stopCellEditing();
	}
	while (filesC < files && okC==0) {
	    if (dadesC[filesC][0]!=null && dadesC[filesC][1]!=null){
		    filesC++;
		}
		else if (dadesC[filesC][0]==null && dadesC[filesC][1]==null){
			okC=1;
		}
		else{
			frPare.actualitzarBarraEstat("No hi ha la mateixa quantitat de valors en Xi que en Xi' en les variables categòriques", true);
			okC=-1;
		}
	}
	switch(opc.getTipus()){

		case OpcionsDis.EUCLI:
		 	if (filesC>0){
			 	frPare.actualitzarBarraEstat("La distància Euclidea no pot contenir components categòriques", true);
				okC=-1;
	 		};
	 	break;
	 	case OpcionsDis.ABS:
		 	if (filesC>0){
			 	frPare.actualitzarBarraEstat("La distància Absoluta no pot contenir components categòriques", true);
				okC=-1;
	 		};
	 	break;
	  	case OpcionsDis.HAMM:
		 	if (filesN>0){
			 	frPare.actualitzarBarraEstat("La distància de Hamming no pot contenir components numèriques", true);
				okC=-1;
	 		};
	 	break;
	 	case OpcionsDis.CHI2:
		 	if (filesN>0){
			 	frPare.actualitzarBarraEstat("La distància de CHI^2 no pot contenir components numèriques", true);
				okC=-1;
	 		};
	 	break;
	 	case OpcionsDis.MINKO:
	 		if (filesC>0){
		 	 	frPare.actualitzarBarraEstat("La distància Minkovski, no pot contenir components categòriques", true);
				okC=-1;
			}
 		break;

	}
	if (filesN==0 && filesC==0){
	 	frPare.actualitzarBarraEstat("S'ha d'omplir alguna de les variables", true);
	 	okN=-1;
	}
	return (okN>=0 && okC>=0);
  }
  /** Fa el calcul de la distància selecionada per els valors introduits
   * @param e event de seleccionar la opció
   */
  private void bAcep_actionPerformed(ActionEvent e) {

	opc=pDistan.getOpcions();
 	int id=-1;
	String distància;
	String[][] distancies=new String[2][2];
	int col=0;
	File f =new File(".\\dades\\usuari");

 	if (comprovarDades()) {
		if (opc.getTipus()=='\u0000' ){
			frPare.actualitzarBarraEstat("S'ha d'elegir un tipus de distància", true);
		}
		else{
			if (opc.getCateg()=='\u0000' && (opc.getTipus()==OpcionsDis.EUCLI || opc.getTipus()==OpcionsDis.ABS ||
			opc.getTipus()==OpcionsDis.MINKO || opc.getTipus()==OpcionsDis.MIXTA || opc.getTipus()==OpcionsDis.ICHINO
			|| opc.getTipus()==OpcionsDis.RALAM || opc.getTipus()==OpcionsDis.CHI2)){
				frPare.actualitzarBarraEstat("S'ha d'elegir/omplir les opcions de la distància seleccionada", true);
			}
			else {
				frPare.actualitzarBarraEstat("Es creen els arxius usuari.dat usuari.obj i usuari. pro de la matriu dins el directori .\\dades", false);
	 			gestor.crearMatriuDat(dadesN,dadesC,filesN,filesC);
				gestor.crearMatriuObj(eixosNN);
				gestor.crearMatriuPro(min,max,filesN,filesC,dadesC);
				if (guardarLatex.isSelected()) {
					 col=2;
	 			}
		   		try {
		       		id = gestor.carregarMatriu(f.getAbsolutePath());
	       			distància = gestor.obtenirDis2Obj(0,1,opc);
       				tResul.setText(distància);
	       			distancies[0][1]=distància;
	       			distancies[1][0]=distància;
	       			distancies[1][1]="0";
	       			distancies[0][0]="0";
	       			frPare.actualitzarBarraEstat("S'ha fet el càlcul de la distància seleccionada", false);
	       			
	            	if (guardar.isSelected()) {
						gestor.escriuMatriuDis(distancies,opc);
						
					}
					if (guardarLatex.isSelected()) {
						gestor.escriuMatriuDisTex(distancies,col,opc);
						try {
							String pathResult = gestor.obtenirDirResultats();
							File dirResult = new File(pathResult);
    						String cmd;
    						Process proc;
						    int err;
						    				    
							pathResult = pathResult + "usuariDis";
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
					gestor.eliminarMatriu(id);
					gestor.selecIdMatriuActual(mCarregada);
 					}
      			catch (Exception ex) {
	      			frPare.actualitzarBarraEstat("No s'ha pogut fer el càlcul.(Nombre màxim de matrius carregades al sistema.)", true);
				}
			}
		}
  	}
 }
 
}
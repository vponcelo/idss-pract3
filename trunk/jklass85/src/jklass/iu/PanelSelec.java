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
import javax.swing.ImageIcon;
import jklass.nucli.GestorKlass;
import jklass.util.Opcions;
import jklass.util.SO;
import java.util.StringTokenizer;
import jklass.nucli.LlistaObjectes;

// DISTANCIES*********************************************************************
 /** Creacio del Menu per elegir el una submatriu de la matriu carregada
 *
 * @author Jose I Mateos
 * @version v.0 13/6/06
 */

public class PanelSelec extends JPanel {

  private static Logger logger=Logger.getLogger(PanelSelec.class.getName());
  private BorderLayout borderLayout1 = new BorderLayout();
  private BorderLayout borderLayout2 = new BorderLayout();
  private BorderLayout borderLayout3 = new BorderLayout();
  private BorderLayout borderLayout4 = new BorderLayout();
  private BorderLayout borderLayout5 = new BorderLayout();
  private BorderLayout borderLayout6 = new BorderLayout();
  private BorderLayout borderLayout7 = new BorderLayout();
  private BorderLayout borderLayout8 = new BorderLayout();
  private FrPrincipal frPare;
  private GestorKlass gestor;
  private Border border1;
  private Border border2;
  private TitledBorder titledBorder1;
  private TitledBorder titledBorder3;
  private TitledBorder titledBorder4;
  private TitledBorder titledBorder5;
  private TitledBorder titledBorder6;
  private TitledBorder titledBorder7;
  private TitledBorder titledBorder8;
  private TitledBorder titledBorder9;
  private JPanel jpanelVarNum = new JPanel();
  private JPanel jpanelVarCat = new JPanel();
  private JPanel jpanelSelecNum = new JPanel();
  private JPanel jpanelSelecCat = new JPanel();
  private JPanel jpanelBtnsN = new JPanel();
  private JPanel jpanelBtnsC = new JPanel();
  private JPanel jpanelObj = new JPanel();
  private JPanel jpanelSelecO = new JPanel();
  private JPanel jpanelBtnsO = new JPanel();
  private JPanel panReset =new JPanel();
  private JPanel panBoton =new JPanel();
  private JPanel panDown =new JPanel();
  private JPanel panIz =new JPanel();
  private DefaultListModel listModelVN = new DefaultListModel();
  private DefaultListModel listModelSN = new DefaultListModel();
  private DefaultListModel listModelVC= new DefaultListModel();
  private DefaultListModel listModelSC= new DefaultListModel();
  private DefaultListModel listModelObj= new DefaultListModel();
  private DefaultListModel listModelSObj= new DefaultListModel();
  private JList jListVarsN = new JList(listModelVN);
  private JList jListSelcN = new JList(listModelSN);
  private JList jListVarsC = new JList(listModelVC);
  private JList jListSelcC = new JList(listModelSC);
  private JList jListObj = new JList(listModelObj);
  private JList jListSelcObj = new JList(listModelSObj);
  private JScrollPane jSPObj = new JScrollPane(jListObj);
  private JScrollPane jSPSelecObj = new JScrollPane(jListSelcObj);
  private JScrollPane jSPVarsN = new JScrollPane(jListVarsN);
  private JScrollPane jSPSelecN = new JScrollPane(jListSelcN);
  private JScrollPane jSPVarsC = new JScrollPane(jListVarsC);
  private JScrollPane jSPSelecC = new JScrollPane(jListSelcC);
  private JButton jBttnAfegirN = new JButton();
  private JButton jBttnTreureN = new JButton();
  private JButton jBttnAfegirC = new JButton();
  private JButton jBttnTreureC = new JButton();
  private JButton jBttnAfegirO = new JButton();
  private JButton jBttnTreureO = new JButton();
  private JButton bAcep = new JButton();
  private JButton bCancel = new JButton();
  private JButton bReset = new JButton();
  private FlowLayout flowLayout1 = new FlowLayout();
  private FlowLayout flowLayout2 = new FlowLayout();
  private String nomFitxer = null;
  private int matriuCarregada;
  private int matriuActual;

  /**
  * Constructor
  *
  * @param fr es la finestra pare
  * @param gk es el gestor d'on penja la matriu, o es null si es de valors introduits per l'usuari
  */
  public PanelSelec(FrPrincipal fr,GestorKlass gk) {

    frPare = fr;
    gestor = gk;
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  /**Creacio del menu
   * @throws Exception
   */
  private void jbInit() throws Exception {

	matriuCarregada=gestor.idMatriuActual();

	frPare.setSize(650,525);
    llistarVariables();
    border1 = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
    titledBorder1 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158)),"Selecciò d'una subhola");
    titledBorder3 = new TitledBorder(border1,"Vars. numèriques");
    titledBorder4 = new TitledBorder(border1,"Vars. categòriques");
    border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158));
    titledBorder5 = new TitledBorder(border2,"Llista de vars.");
    titledBorder6 = new TitledBorder(border2,"Vars. seleccionades");
    titledBorder7 = new TitledBorder(border1,"Objectes");
    titledBorder8 = new TitledBorder(border2,"Llista d'objectes");
    titledBorder9 = new TitledBorder(border2,"Objectes seleccionats");
    this.setLayout(borderLayout1);
    panIz.setLayout(borderLayout8);
    jpanelObj.setLayout(borderLayout6);
    jpanelObj.setBorder(titledBorder7);
    jSPSelecObj.setAutoscrolls(true);
    jSPSelecObj.setBorder(titledBorder9);
    jSPSelecObj.setPreferredSize(new Dimension(115, 153));
    jBttnTreureO.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
    jBttnTreureO.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnTreureO_actionPerformed(e);
      }
    });
    jpanelBtnsO.setLayout(borderLayout7);
    jBttnAfegirO.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
    jBttnAfegirO.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnAfegirO_actionPerformed(e);
      }
    });
    jSPObj.getViewport().setBackground(Color.white);
    jSPObj.setAutoscrolls(true);
    jSPObj.setBorder(titledBorder8);
    jSPObj.setPreferredSize(new Dimension(100, 153));
    jListObj.setBorder(null);
    jpanelVarNum.setLayout(borderLayout5);
    jpanelVarCat.setLayout(borderLayout2);
    jpanelVarNum.setBorder(titledBorder3);
    jSPSelecN.setAutoscrolls(true);
    jSPSelecN.setBorder(titledBorder6);
    jSPSelecN.setPreferredSize(new Dimension(115, 153));
    jBttnTreureN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
    jBttnTreureN.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnTreureN_actionPerformed(e);
      }
    });
    jpanelBtnsN.setLayout(borderLayout4);
    jBttnAfegirN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
    jBttnAfegirN.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnAfegirN_actionPerformed(e);
      }
    });
    jSPVarsN.getViewport().setBackground(Color.white);
    jSPVarsN.setAutoscrolls(true);
    jSPVarsN.setBorder(titledBorder5);
    jSPVarsN.setPreferredSize(new Dimension(100, 153));
    jListVarsN.setBorder(null);
    jBttnTreureC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
    jBttnTreureC.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnTreureC_actionPerformed(e);
      }
    });
    jListVarsC.setBorder(null);
    jSPVarsC.setBorder(titledBorder5);
    jSPVarsC.setPreferredSize(new Dimension(100, 153));
    jSPVarsC.setAutoscrolls(true);
    jSPSelecC.setBorder(titledBorder6);
    jSPSelecC.setPreferredSize(new Dimension(115, 153));
    jSPSelecC.setAutoscrolls(true);
    jpanelBtnsC.setLayout(borderLayout3);
    jBttnAfegirC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
    jBttnAfegirC.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnAfegirC_actionPerformed(e);
      }
    });
    jpanelVarCat.setBorder(titledBorder4);
    jpanelSelecNum.setLayout(flowLayout1);
    jpanelSelecO.setLayout(flowLayout2);
    this.setBorder(titledBorder1);
    this.setDebugGraphicsOptions(0);

    jpanelSelecO.add(jSPObj, null);
    jpanelSelecO.add(jpanelBtnsO, null);
    jpanelBtnsO.add(jBttnAfegirO, BorderLayout.NORTH);
    jpanelBtnsO.add(jBttnTreureO, BorderLayout.SOUTH);
    jpanelSelecO.add(jSPSelecObj, null);
    jpanelObj.add(jpanelSelecO,  BorderLayout.NORTH);
    jpanelSelecNum.add(jSPVarsN, null);
    jpanelSelecNum.add(jpanelBtnsN, null);
    jpanelBtnsN.add(jBttnAfegirN, BorderLayout.NORTH);
    jpanelBtnsN.add(jBttnTreureN, BorderLayout.SOUTH);
    jpanelSelecNum.add(jSPSelecN, null);
    jpanelVarNum.add(jpanelSelecNum,  BorderLayout.NORTH);
    jpanelSelecCat.add(jSPVarsC, null);
    jpanelSelecCat.add(jpanelBtnsC, null);
    jpanelBtnsC.add(jBttnAfegirC, BorderLayout.NORTH);
    jpanelBtnsC.add(jBttnTreureC, BorderLayout.SOUTH);
    jpanelSelecCat.add(jSPSelecC, null);
    jpanelVarCat.add(jpanelSelecCat, BorderLayout.NORTH);
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
    panReset.add(bReset);
	panBoton.add(bAcep);
   	panBoton.add(bCancel);
   	panDown.add(panReset);
   	panDown.add(panBoton);
	panIz.add(jpanelVarNum, BorderLayout.NORTH);
	panIz.add(jpanelVarCat,BorderLayout.SOUTH);
   	this.add(panIz,  BorderLayout.WEST);
    this.add(jpanelObj,  BorderLayout.EAST);
    this.add(panDown, BorderLayout.SOUTH);
	frPare.centrar();
  }
  /**Cancel·la el menu
   * @param e event de seleccionar la opció
   */
  private void bCancel_actionPerformed(ActionEvent e) {

	if(matriuCarregada!=matriuActual){
		gestor.eliminarMatriu(matriuActual);
		gestor.selecIdMatriuActual(matriuCarregada);
	}
	frPare.remove(this);
  	frPare.validate();
  	frPare.repaint();
  }
  /**Crea la subamatriu de la sel.lecio de la matriu
   * @param e event de seleccionar la opció
   */
  private void bAcep_actionPerformed(ActionEvent e) {

	Object[]llObjs;
	String subNom;
	Object[] llPropsN;
	Object[] llPropsC;
	String[] prop;
	String[] obj;
	int id = -1;
	int lon1;
	int lon2;
	int lon3;

   	subNom=nomFitxer+"bis";
	llPropsN=listModelSN.toArray();
	llPropsC=listModelSC.toArray();
	lon1=llPropsN.length;
	lon2=llPropsC.length;
	prop=new String[lon1+lon2];
	for (int i=0;i<lon1;i++){
		prop[i]=(String) llPropsN[i];
	}
	for (int j=0;j<lon2;j++){
		prop[j+lon1]=(String) llPropsC[j];
	}
	llObjs=listModelSObj.toArray();
	lon3=llObjs.length;
	obj=new String[lon3];
	for (int k=0;k<lon3;k++){
		obj[k]=(String) llObjs[k];
	}
	matriuActual=gestor.idMatriuActual();
    if (prop.length>0){
	    if (obj.length>0){
	        try {
       				int n = JOptionPane.showConfirmDialog(
	       				this, "Aquesta opció crea una nova submatriu i la deixa com a l'actual. Segur que vols continuar?",
                    	"Selecciona Submatriu",
                    	JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						if(frPare.matrius.existeix(subNom)){
							n = JOptionPane.showConfirmDialog(
	       					this, "Ja hi ha una matriu en el sistema amb aquest nom que es perdra. Segur que vols continuar?",
                    		"Selecciona Submatriu",
                   		 	JOptionPane.YES_NO_OPTION);
               		 	}
						if (n == JOptionPane.YES_OPTION) {
	 						id = gestor.carregarSubMatriu(subNom,matriuActual,prop,obj);
	       					if (id != -1) {
			       				frPare.actualitzarMatriuGuardada(false);
			       				frPare.actualitzarTitolFinestra(subNom);
			       				frPare.matrius.insertarMatriu(subNom, id); // hook into FileHistory class
       						}
	       					frPare.actualitzarBarraEstat("S´ha creat la submatriu amb els objectes seleccionats", false);
		       				llistarVariables();
		       				if(matriuCarregada!=matriuActual){
								gestor.eliminarMatriu(matriuActual);
							}
		       				matriuActual=gestor.idMatriuActual();
   						}
					}
       			}
       		catch (Exception ex) {
                         frPare.actualitzarBarraEstat("No s'ha pogut crear la submatriu.(Nombre màxim de matrius carregades al sistema.)", true);
   			}
		}
		else{
		 frPare.actualitzarBarraEstat("S´ha de seleccionar un objecte com a minim", true);
	 	}
	}
	else{
		 frPare.actualitzarBarraEstat("S´ha de seleccionar una variable com a minim", true);
	 }
 }
  /** Elimina la selecio que teniem i deixa el menu como estaba inicalment
   * @param e event de seleccionar la opció
   */
  private void bReset_actionPerformed(ActionEvent e) {

	llistarVariables();
  }
  /**Carrega les llistes de les variables de la matriu dins la llista
   * @param e event de seleccionar la opció
   */
  private void llistarVariables(){

	String[][] llProps;
    String[] llObjs;
	int lon;

	listModelVN.clear();
    listModelSN.clear();
  	listModelVC.clear();
    listModelSC.clear();
    listModelObj.clear();
    listModelSObj.clear();
	nomFitxer = frPare.obtenirNomDades();
    llObjs=gestor.obtenirLlistaIDsObjs();
	lon = llObjs.length;
	for (int i = 0; i < lon; i++) {
      listModelObj.insertElementAt(llObjs[i], i);
    }
	llProps = gestor.obtenirLlistaIDsProps();
    lon = llProps[0].length;
    for (int i = 0; i < lon; i++) {
      listModelVN.insertElementAt(llProps[0][i], i);
    }
    lon = llProps[1].length;
    for (int i = 0; i < lon; i++) {
      listModelVC.insertElementAt(llProps[1][i], i);
    }
    lon = llProps[2].length;
    for (int i = 0; i < lon; i++) {
      listModelVC.insertElementAt(llProps[2][i], i);
    }
    lon = llProps[3].length;
    for (int i = 0; i < lon; i++) {
      listModelVC.insertElementAt(llProps[3][i], i);
    }
  }
  /**Carrega la variable numerica dins la llista de les seleccionades
   * @param e event de seleccionar la opció
   */
  private void jBttnAfegirN_actionPerformed(ActionEvent e) {

	Object[] vars = jListVarsN.getSelectedValues();
    boolean hay_mas_vars = true;
    int n = 0;

    do {
      try {
        listModelSN.addElement(vars[n]);
        listModelVN.removeElement(vars[n]);
        n++;
      }
      catch (Exception exc) { hay_mas_vars = false; }

    } while (hay_mas_vars);

  }
  /**Treu de la llista de variables numeriques seleccionades la variable en seleccio
   * @param e event de seleccionar la opció
   */
  private void jBttnTreureN_actionPerformed(ActionEvent e) {

	Object[] vars = jListSelcN.getSelectedValues();
    boolean hay_mas_vars = true;
    int n = 0;

    do {
      try {
        listModelVN.addElement(vars[n]);
        listModelSN.removeElement(vars[n]);
        n++;
      }
      catch (Exception exc) { hay_mas_vars = false; }

    } while (hay_mas_vars);
  }
  /**Carrega la variable categorica dins la llista de les seleccionades
   * @param e event de seleccionar la opció
   */
  private void jBttnAfegirC_actionPerformed(ActionEvent e) {

	Object[] vars = jListVarsC.getSelectedValues();
    boolean hay_mas_vars = true;
    int n = 0;

    do {
      try {
        listModelSC.addElement(vars[n]);
        listModelVC.removeElement(vars[n]);
        n++;
      }
      catch (Exception exc) { hay_mas_vars = false; }

    } while (hay_mas_vars);
  }
  /**Treu de la llista de variables categoriques seleccionades la variable en seleccio
   * @param e event de seleccionar la opció
   */
  private void jBttnTreureC_actionPerformed(ActionEvent e) {

	Object[] vars = jListSelcC.getSelectedValues();
    boolean hay_mas_vars = true;
    int n = 0;

    do {
      try {
        listModelVC.addElement(vars[n]);
        listModelSC.removeElement(vars[n]);
        n++;
      }
      catch (Exception exc) { hay_mas_vars = false; }

    } while (hay_mas_vars);
  }
 /**Carrega l'objecte dins la llista dels seleccionats
  * @param e event de seleccionar la opció
  */
  private void jBttnAfegirO_actionPerformed(ActionEvent e) {

	Object[] vars = jListObj.getSelectedValues();
    boolean hay_mas_vars = true;
    int n = 0;

    do {
      try {
        listModelSObj.addElement(vars[n]);
        listModelObj.removeElement(vars[n]);
        n++;
      }
      catch (Exception exc) { hay_mas_vars = false; }

    } while (hay_mas_vars);
  }
  /**Treu de la llista dels objectes seleccionats l'objecte en seleccio
   * @param e event de seleccionar la opció
   */
  private void jBttnTreureO_actionPerformed(ActionEvent e) {

	Object[] vars = jListSelcObj.getSelectedValues();
    boolean hay_mas_vars = true;
    int n = 0;

    do {
      try {
        listModelObj.addElement(vars[n]);
        listModelSObj.removeElement(vars[n]);
        n++;
      }
      catch (Exception exc) { hay_mas_vars = false; }

    } while (hay_mas_vars);
  }

}
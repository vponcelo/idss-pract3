//ROBER

package jklass.iu;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.ImageIcon;

import jklass.nucli.GestorKlass;
import jklass.nucli.GestorClasificacion;
//import jklass.nucli.CalcDis;


public class PanelAgregarClase extends JPanel {
  private static Logger logger=Logger.getLogger(PanelAgregarClase.class.getName());

  FrPrincipal frPare;
  GestorKlass gestor;
  String nomFitxer = null;
  GestorClasificacion gestorClasifica ;

  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();

  GridBagLayout gridBagLayout1 = new GridBagLayout();

  GridLayout gridLayout1 = new GridLayout(2,1);
  GridLayout gridLayout2 = new GridLayout(3,1);

  FlowLayout flowLayout1 = new FlowLayout();
  FlowLayout flowLayout2 = new FlowLayout();

  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  TitledBorder titledBorder3;
  TitledBorder titledBorder4;

  JPanel jPanelObj = new JPanel();
  JPanel jPanelObjSelec = new JPanel();
  JPanel jPanelBtnsN = new JPanel();
  JPanel jPanelBotones = new JPanel();
  JPanel panelClase = new JPanel();
  JPanel panelOpciones = new JPanel();
  JPanel panelBotones = new JPanel();
  JPanel panelOp = new JPanel();
  JLabel etiqueta1 = new JLabel();
  JLabel etiqueta2 = new JLabel();
  JLabel etiqueta3 = new JLabel();

  JTextField texto = new JTextField();

  JRadioButton radioModa = new JRadioButton();;
  JRadioButton radioGibert = new JRadioButton();;
  ButtonGroup jbgVariables = new ButtonGroup();


  DefaultListModel listModelObj = new DefaultListModel();
  DefaultListModel listModelObjSelec = new DefaultListModel();

  JList jListObj = new JList(listModelObj);
  JList jListObjSelec = new JList(listModelObjSelec);

  JScrollPane jSPObj = new JScrollPane(jListObj);
  JScrollPane jSPObjSelec = new JScrollPane(jListObjSelec);

  JButton jBttnAfegirN = new JButton();
  JButton jBttnTreureN = new JButton();
  JButton jBttnCancel = new JButton();
  JButton jBttnAgregar = new JButton();
  JButton jBttnActualizar = new JButton();

// Atributos para la Tabla

  BorderLayout borderLayout5 = new BorderLayout();

  TitledBorder titledBorder5;
  TitledBorder titledBorder6;
  TitledBorder titledBorder7;

  JPanel jPanelVars = new JPanel();
  JPanel jPanSelec = new JPanel();
  JPanel jPanSelDatos = new JPanel();
  JPanel jPanSelAtributos = new JPanel();

  Object[][] varsAtributos =  new Object [3][5];
  String[] eixosAtributos = {"Index", "Identificador", "Pes", "fillEsq", "fillDret"};
  JTable jTblVarAtributos = new JTable(varsAtributos, eixosAtributos);

  Object[][] varsObjetos;
  String[] eixosObjetos;
  JTable jTblVarObjetos;

  JScrollPane jSPTaulaVarAtributos = new JScrollPane(jTblVarAtributos);
  JScrollPane jSPTaulaVarObjetos;

  DefaultListModel listR;
  JList caja;


  public PanelAgregarClase(FrPrincipal fr,GestorKlass gk) {

    frPare = fr;
    gestor = gk;
    nomFitxer = frPare.obtenirNomDades();
    frPare.repaint();

    try {
      gestorClasifica = gestor.crearGestorClasificacion();
      inicializarTabla();
      jbInit();

    }
    catch(Exception e) {
      e.printStackTrace();
    }

  }

  void jbInit() {
    frPare.setSize(750,500);
    pintarPanelListas();
    pintarPanelBotones();
    pintarPanelTablas();
    cargarLista ();
 }

 void cargarLista () {
   String[] llObjs;
   int lon;
   int j = 0;

   listModelObj.removeAllElements();
   listModelObjSelec.removeAllElements();

   lon = gestorClasifica.obtenirListaObj().length;
   llObjs = new String [lon];
   llObjs = gestorClasifica.obtenirListaObj();


     for (int i = 0; i < lon; i++) {
       if ( ! gestorClasifica.obtenirAgregados()[ i ] ){
         listModelObj.insertElementAt(llObjs[i], j);
         j++;
       }
   }
 }

 void inicializarTabla (){
   eixosObjetos = new String []{"Propietats","Objecte1","Objecte2","Classe Agregada"};
   varsObjetos = new Object[gestorClasifica.obtenirListaPropiedades().length][eixosObjetos.length];
   jTblVarObjetos = new JTable(varsObjetos, eixosObjetos);
   jSPTaulaVarObjetos = new JScrollPane(jTblVarObjetos);
 }


 void pintarPanelListas () {
   titledBorder1 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158)),"Agregar Clases");
   titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158)),"");
   titledBorder3 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158)),"Llista Objs.");
   titledBorder4 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158)),"Objs. selec.");

   this.setBorder(titledBorder1);
   this.setMinimumSize(new Dimension(212, 150));
   this.setLayout(borderLayout1);

   jBttnTreureN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
   jBttnTreureN.addActionListener(new java.awt.event.ActionListener() {
     public void actionPerformed(ActionEvent e) {
       jBttnTreureN_actionPerformed(e);
     }
   });

   jBttnAfegirN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
   jBttnAfegirN.addActionListener(new java.awt.event.ActionListener() {
     public void actionPerformed(ActionEvent e) {
       jBttnAfegirN_actionPerformed(e);
     }
   });

   jSPObj.setAutoscrolls(true);
   jSPObj.setBorder(titledBorder3);
   jSPObj.setMinimumSize(new Dimension(35, 150));
   jSPObj.setPreferredSize(new Dimension(80, 223));

   jSPObjSelec.setAutoscrolls(true);
   jSPObjSelec.setBorder(titledBorder4);
   jSPObjSelec.setPreferredSize(new Dimension(80, 223));

   jPanelBtnsN.setLayout(borderLayout2);
   jPanelBtnsN.add(jBttnAfegirN, BorderLayout.NORTH);
   jPanelBtnsN.add(jBttnTreureN, BorderLayout.SOUTH);

   jPanelObjSelec.setToolTipText("");
   jPanelObjSelec.add(jSPObj, null);
   jPanelObjSelec.add(jPanelBtnsN, null);
   jPanelObjSelec.add(jSPObjSelec, null);

   etiqueta1.setText("Prefix classe:");
   texto.setText("Classe");
   panelClase.setLayout(gridLayout1);
   panelClase.add(etiqueta1);
   panelClase.add(texto);

   etiqueta3.setText("");
   panelClase.add(etiqueta3);
   panelClase.add(etiqueta3);


   etiqueta2.setText("Representant de classe :");
   radioGibert.setSelected(true);
   radioGibert.setText("Objectes extesos de Gibert");
   radioModa.setText("Centre de gravetat i Moda");
   jbgVariables.add(radioGibert);
   jbgVariables.add(radioModa);

   panelOpciones.setLayout(gridLayout2);
   panelOpciones.add(etiqueta2);
   panelOpciones.add(radioGibert);
   panelOpciones.add(radioModa);

   panelOp.setLayout(borderLayout3);
   panelOp.add(panelClase,BorderLayout.NORTH);
   panelOp.add(panelOpciones,BorderLayout.SOUTH);

   jPanelObj.setLayout(borderLayout4);
   jPanelObj.add(jPanelObjSelec, BorderLayout.NORTH);
   jPanelObj.add(panelOp, BorderLayout.SOUTH);
   this.add(jPanelObj,  BorderLayout.WEST);

 }

 void pintarPanelBotones () {
   jBttnCancel.setText("Cancel·la");
   jBttnCancel.addActionListener(new java.awt.event.ActionListener() {
     public void actionPerformed(ActionEvent e) {
       jBttnCancel_actionPerformed(e);
     }
   });

   jBttnAgregar.setText("Agregar");
   jBttnAgregar.addActionListener(new java.awt.event.ActionListener() {
     public void actionPerformed(ActionEvent e) {
       jBttnAgregar_actionPerformed(e);
     }
   });

   jBttnActualizar.setText("Actualitzar");
   jBttnActualizar.addActionListener(new java.awt.event.ActionListener() {
     public void actionPerformed(ActionEvent e) {
       jBttnActualizar_actionPerformed(e);
     }
   });

   jPanelBotones.add(jBttnAgregar, null);
   jPanelBotones.add(jBttnActualizar, null);
   jPanelBotones.add(jBttnCancel, null);

   panelBotones.add(jPanelBotones);

   this.add(panelBotones, BorderLayout.SOUTH);
 }

 void pintarPanelTablas () {
   titledBorder7 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158)),"Atributs d'objectes i clase");
   titledBorder6 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158)),"Dades dels objectes");

   jSPTaulaVarAtributos.setAutoscrolls(true);
   jSPTaulaVarAtributos.setBorder(titledBorder7);
   jSPTaulaVarAtributos.setPreferredSize(new Dimension(400, 100));

   jSPTaulaVarObjetos.setAutoscrolls(true);
   jSPTaulaVarObjetos.setBorder(titledBorder6);
   jSPTaulaVarObjetos.setPreferredSize(new Dimension(400,170));

   jPanSelAtributos.add(jSPTaulaVarAtributos, null);
   jPanSelDatos.add(jSPTaulaVarObjetos, null);

   jPanSelec.setLayout(borderLayout5);
   jPanSelec.add(jPanSelAtributos, BorderLayout.NORTH);
   jPanSelec.add(jPanSelDatos, BorderLayout.SOUTH);

   jPanelVars.add(jPanSelec, BorderLayout.EAST);
   this.add(jPanelVars,  BorderLayout.EAST);

 }

 void rellenarTablaObjetos(String identif1, String identif2, String identif){
   varsAtributos[0][0] = Integer.toString (gestorClasifica.obtenirIndex(identif1));
   varsAtributos[1][0] = Integer.toString (gestorClasifica.obtenirIndex(identif2));
   varsAtributos[2][0] = Integer.toString (gestorClasifica.obtenirIndex(identif));

   varsAtributos[0][1] = identif1;
   varsAtributos[1][1] = identif2;
   varsAtributos[2][1] = identif;

   varsAtributos[0][2] = Float.toString(gestorClasifica.obtenirPes(identif1));
   varsAtributos[1][2] = Float.toString(gestorClasifica.obtenirPes(identif2));
   varsAtributos[2][2] = Float.toString(gestorClasifica.obtenirPes(identif));

   varsAtributos[0][3] = gestorClasifica.obtenirFillEsquerre(identif1);
   varsAtributos[1][3] = gestorClasifica.obtenirFillEsquerre(identif2);
   varsAtributos[2][3] = gestorClasifica.obtenirFillEsquerre(identif);

   varsAtributos[0][4] = gestorClasifica.obtenirFillDret(identif1);
   varsAtributos[1][4] = gestorClasifica.obtenirFillDret(identif2);
   varsAtributos[2][4] = gestorClasifica.obtenirFillDret(identif);

   jTblVarAtributos.repaint();

 }


 void rellenarTablaClases(String identif1, String identif2, String identif){
   String [] lista = new String [gestorClasifica.obtenirListaPropiedades().length];
   String [] objetos1 = new String[lista.length];
   String [] objetos2 = new String[lista.length];
   String [] objetoClase = new String[lista.length];

   varsObjetos [0][0]= "ListaPropietats";
   varsObjetos [0][1]= identif1;
   varsObjetos [0][2]= identif2;
   varsObjetos [0][3]= identif;


   lista = gestorClasifica.obtenirListaPropiedades();
   objetos1 = gestorClasifica.obtenirFila(identif1);
   objetos2 = gestorClasifica.obtenirFila(identif2);
   objetoClase = gestorClasifica.obtenirFila(identif);

   for ( int i=0; i< lista.length; i++ ) {
     varsObjetos [i][0]= lista[i];
     varsObjetos [i][1]= objetos1[i];
     varsObjetos [i][2]= objetos2[i];
     varsObjetos [i][3]= objetoClase[i];
   }

   jTblVarObjetos.repaint();


 }

  void jBttnAgregar_actionPerformed(ActionEvent e) {
    String identif,identif1,identif2,idClase, tipus;
    int sizeList;

      idClase = texto.getText();
      if ( radioModa.isSelected()){
        tipus = "Moda";
      }
      else {
        tipus = "Gibert";
      }

      if ( gestorClasifica.comprobarIdClase(idClase) ) {


        sizeList = listModelObjSelec.size();
        if (  sizeList < 2 ) {

          frPare.actualitzarBarraEstat(" Ha de sel.leccionar dos objectes. ", true);
        }
        else {
          texto.setEnabled(false);
          radioModa.setEnabled(false);
          radioGibert.setEnabled(false);
          frPare.actualitzarBarraEstat("  ", false);


          identif1 = listModelObjSelec.elementAt(0).toString();
          for ( int i = 1; i < sizeList; i++) {
            identif2 = listModelObjSelec.elementAt(i).toString();

            gestorClasifica.modifIdClasse(idClase);
            identif = gestorClasifica.agregarClase(tipus,identif1,identif2,0);
            rellenarTablaObjetos(identif1,identif2,identif);
            rellenarTablaClases(identif1,identif2,identif);

            identif1 = identif;
          }

        }



      }
      else {
        frPare.actualitzarBarraEstat(" L'identificador de classe no es correcte. ", true);
      }

    cargarLista();
  }

  void jBttnCancel_actionPerformed(ActionEvent e) {

    frPare.actualitzarBarraEstat(" ", false);
    frPare.remove(this);
    frPare.validate();
    frPare.repaint();
    frPare.setSize(new Dimension(550, 300));

  }

  void jBttnActualizar_actionPerformed(ActionEvent e) {
    int id;
    String nom = nomFitxer + "bis";
    try {
      id = gestor.carregarMatriuAgregada(nom,gestorClasifica);

      if (id != -1) {
        frPare.actualitzarMatriuGuardada(false);
        frPare.actualitzarTitolFinestra(nom);
        frPare.habilitarOpcionsMenu(true, false);
        frPare.actualitzarBarraEstat("Les dades s'han carregat.", false);
        frPare.matrius.insertarMatriu(nom, id); // hook into FileHistory class
      }
      else {
        frPare.actualitzarBarraEstat("Les dades no s'han carregat.", true);
      }

      frPare.jMenuAgregarClase_actionPerformed(e);

    }
    catch(Exception ex) {
      ex.printStackTrace();
    }

  }



  void jBttnAfegirN_actionPerformed(ActionEvent e) {
    Object[] vars = jListObj.getSelectedValues();
    boolean hay_mas_vars = true;
    int n = 0;
    do {
      try {
        listModelObjSelec.addElement(vars[n]);
        listModelObj.removeElement(vars[n]);
        n++;
      }
      catch (Exception exc) { hay_mas_vars = false; }

    } while (hay_mas_vars);
  }

  void jBttnTreureN_actionPerformed(ActionEvent e) {
    Object[] vars = jListObjSelec.getSelectedValues();
    boolean hay_mas_vars = true;
    int n = 0;
    do {
      try {
        listModelObj.addElement(vars[n]);
        listModelObjSelec.removeElement(vars[n]);
        n++;
      }
      catch (Exception exc) { hay_mas_vars = false; }

    } while (hay_mas_vars);
  }


}


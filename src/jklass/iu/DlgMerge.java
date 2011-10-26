package jklass.iu;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;

import jklass.nucli.GestorKlass;
import jklass.nucli.ArbreClassif;
import jklass.nucli.NodeBinari;
import  jklass.nucli.Dada;
 /** 
  * Classe que mostra el diàleg per a canviar l'arbre actual de treball de la matriu actual.
  *
  * @author Andreu Raya
  * @version v.5
  */
public class DlgMerge extends JDialog {


//  private GridLayout miss = new GridLayout(1,1);
  private JScrollPane jScrollPaneDendo;
  private JList jListDendo;
  private GestorKlass gestor;
  private FrPrincipal frPare;
  private TitledBorder tLlistaArbres;
  private Border cuadre;

  /**
  * Contructor
  *
  * @param frame es la finestra pare
  * @param gk es el gestor d'on penja la matriu
  */
  public DlgMerge(FrPrincipal frame, GestorKlass gk) {
    //super(frame, "Canviar dendograma", true); :
	super(frame, /*"CDA", */true);
    try {
	  frPare=frame;
	  gestor=gk;
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Funció per a inicialitzar i dibuixar els elements de pantalla.
   * 
   * @throws Exception
   */
  private void jbInit() throws Exception {
	  this.setSize(140,150);

		jScrollPaneDendo = new JScrollPane();
		//jScrollPaneDendo.setLayout(miss);
		this.getContentPane().add(jScrollPaneDendo);
		jListDendo = new JList();
		jListDendo.addMouseListener(mouseListener);
		jScrollPaneDendo.setViewportView(jListDendo);
		cuadre = BorderFactory.createEtchedBorder(Color.white, new Color(156, 156, 158));
		tLlistaArbres = new TitledBorder(cuadre,"Merge");
		jScrollPaneDendo.setBorder(tLlistaArbres);
		ompleMacroArb();
  }

  /**
   * Objecte MouseListener encarregat de recollir la selecció de l'arbre per part de l'usuari mitjançant 
   * l'esdeveniment de doble click sobre un element de la llista de la pantalla.
   */
  MouseListener mouseListener = new MouseAdapter() {
	  public void mouseClicked(MouseEvent e) {
		  if (e.getClickCount() == 2) {
			  //int index = jListDendo.locationToIndex(e.getPoint());
				//System.out.println("Double clicked on Item " + index);
				String arbre=(String)jListDendo.getSelectedValue();
				gestor.modificaArbreActual(arbre);
				dispose();
				frPare.actualitzarBarraEstat("S'ha canviat el dendograma actual.", false);
		  }
	  }
  };

  /**
   * Funció encarregada d'omplir la llista d'arbres de la matriu actual que es mostraran per pantalla.
   */
  private void ompleMacroArb() {
		String[] llArbres = gestor.obtenirLlistaArbres();
		ArbreClassif arbol, arbre1,arbre2;
		 String[] arbres = new String[3];
      arbre1=null; arbre2=null;
		NodeBinari arrel;
		NodeBinari nodeAux;
		NodeBinari raiz1;
		NodeBinari raiz2;
		String raiz="ale";
		 ArrayList llNodesIdn1;
		 ArrayList llNodesIdn2;
		  ArrayList llNodesIdntot;
		 String [] lista=null;
		 int filas=0, numObjetos;
		 double idn1,idn2,maxidn=0.0,maxidntot=0.0;
		 String identificador;
		///////
		 try {
	  arbre1= (ArbreClassif) gestor.obtenirLlistArb().get(0);
	  
	  }
    catch(Exception ex) {
      ex.printStackTrace();
    };
	 
	  try {
	  
	  arbre2= (ArbreClassif) gestor.obtenirLlistArb().get(1); }
    catch(Exception ex) {
      ex.printStackTrace();
    };

  
	 
	 arbres[0]=((ArbreClassif)arbre1).obtenirNom();
	 arbres[1]=((ArbreClassif)arbre2).obtenirNom();
	
	 ///////
	 
	  try {
	  
	  lista = gestor.obtenerGM().obtenirLlistaObjs().llistarIDsObjs(); }
    catch(Exception ex) {
      ex.printStackTrace();
    };
	 
	 
	  try {
	  
	  filas = gestor.obtenerGM().obtenirLlistaObjs().obtenirLong(); }
    catch(Exception ex) {
      ex.printStackTrace();
    };
	 
	  numObjetos = filas;

 raiz1= arbre1.obtenirArrel();
 idn1= raiz1.obtenirIndexNivell();
 System.out.println("idn1="+idn1);

 raiz2= arbre2.obtenirArrel();
 idn2= raiz2.obtenirIndexNivell();
 System.out.println("idn2="+idn2);

 if (idn1 > idn2){
  maxidn=idn1;
  }
  else{
   maxidn=idn2;
  }
 maxidntot=maxidn*2.0;
 System.out.println("maxidntot="+maxidntot);
	 
	 // lista = gestor.obtenerGM().obtenirLlistaObjs().llistarIDsObjs();

	 /////
	 llNodesIdn1= arbre1.obtenirLlNodesIdn();
	 llNodesIdn2=arbre2.obtenirLlNodesIdn();
	 System.out.println("holaaaaaaaaaa cual es el size??"+llNodesIdn1.size());
	 System.out.println("holaaaaaaaaaa cual es el size2??"+llNodesIdn2.size());
	 
	  arrel= new NodeBinari("pruebak");
	 //arrel= new NodeBinari("prueba", maxidntot, raiz1, raiz2);
	

	
	
	//  arbol = new ArbreClassif ( arrel, "tres" ,"cuatro" );
	  
	 
	  arbol = new ArbreClassif ( arrel,"sinpar" );
	 
	  
	NodeBinari fillEsq = raiz1;
	arrel.modifFillEsq(fillEsq);
	System.out.println("que hay en raiz1??"+raiz1.etiq);  
  arbol.afegirNodesArbre(fillEsq);
  NodeBinari FillDret = raiz2;
  arrel.modifFillDret(FillDret);

  	System.out.println("que hay en raiz2??"+raiz2.etiq);
  arbol.afegirNodesArbre(FillDret);	  
 arrel.modifIndexNivell(maxidntot);
	
	 
/*
NodeBinari fullaResidual = arbreGM.obtenirNode(idSubarbre);

NodeBinari fillEsq = arrelSubarbre.obtenirFillEsq();
         fullaResidual.modifFillEsq(fillEsq);
         arbreGM.afegirNodesArbre(fillEsq);
         NodeBinari fillDret = arrelSubarbre.obtenirFillDret();
         fullaResidual.modifFillDret(fillDret);
         arbreGM.afegirNodesArbre(fillDret);
         fullaResidual.modifIndexNivell(arrelSubarbre.obtenirIndexNivell());
      



*/	




 
  llNodesIdntot= arbol.obtenirLlNodesIdn();

  
	
  /* llNodesIdn1.addAll(llNodesIdn2);
	 arbol.llNodesIdn=llNodesIdn1;

	  arbol.afegirALlistaIdn(arrel);*/
	  
	  	   System.out.println("hola final cuantos nodos?"+llNodesIdntot.size());
	  
	  
	  /////
	   try {
	  
	  gestor.obtenerGM().agregarArbol(arbol); }
    catch(Exception ex) {
      ex.printStackTrace();
    };

	 
	  ////////

  
	  
  arbres[2]=((ArbreClassif)arbol).obtenirNom();
	 
		ListModel jListArbreModel = new DefaultComboBoxModel(arbres);
		jListDendo.setModel(jListArbreModel);
  }

}
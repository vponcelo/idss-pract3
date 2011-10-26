package jklass.iu;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import jklass.nucli.GestorKlass;

 /** 
  * Classe que mostra el diàleg per a canviar l'arbre actual de treball de la matriu actual.
  *
  * @author Andreu Raya
  * @version v.5
  */
public class DlgCanviArbre extends JDialog {


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
  public DlgCanviArbre(FrPrincipal frame, GestorKlass gk) {
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
		tLlistaArbres = new TitledBorder(cuadre,"Llista d'arbres");
		jScrollPaneDendo.setBorder(tLlistaArbres);
		ompleComboBC();
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
  private void ompleComboBC() {
		String[] llArbres = gestor.obtenirLlistaArbres();
		ListModel jListArbreModel = new DefaultComboBoxModel(llArbres);
		jListDendo.setModel(jListArbreModel);
  }

}
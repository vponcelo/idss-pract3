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
import jklass.util.SO;
import jklass.util.OpcionsDis;
import jklass.nucli.GestorKlass;

// DISTANCIES*********************************************************************
 /** Classe que crea el menu de la part de les distancies
 *
 * @author Jose I Mateos
 * @version v.0 20/5/06
 */

public class PanelD extends JPanel {

  private static Logger logger = Logger.getLogger(PanelD.class.getName());
  private TitledBorder tTipus;
  private Border cuadre;
  private GridBagLayout izq= new GridBagLayout();
  private GridBagConstraints izqui = new GridBagConstraints();
  private GridLayout gdis = new GridLayout(13,1);
  private GridLayout gopc = new GridLayout(13,1);
  private JPanel panNum =new JPanel();
  private JPanel panMix =new JPanel();
  private JPanel panCateg =new JPanel();
  private JPanel panDistan =new JPanel();
  private JPanel panDis =new JPanel();
  private JPanel panOpc =new JPanel();
  private JPanel panEsp1 =new JPanel();
  private JPanel panEsp2 =new JPanel();
  private JPanel panEsp3 =new JPanel();
  private JPanel panEsp4 =new JPanel();
  private JPanel panEsp5 =new JPanel();
  private JButton bOpcEucli = new JButton();
  private JButton bOpcAbs = new JButton();
  private JButton bOpcMinko = new JButton();
  private JButton bOpcMGibert = new JButton();
  private JButton bOpcRalam = new JButton();
  private JButton bOpcIchino = new JButton();
  private JButton bOpcChi = new JButton();
  private JRadioButton rEucli = new JRadioButton();
  private JRadioButton rAbs = new JRadioButton();
  private JRadioButton rMinko = new JRadioButton();
  private JRadioButton rGower = new JRadioButton();
  private JRadioButton rHamm = new JRadioButton();
  private JRadioButton rGowdaDiday = new JRadioButton();
  private JRadioButton rIchino = new JRadioButton();
  private JRadioButton rRalam = new JRadioButton();
  private JRadioButton rMGibert = new JRadioButton();
  private JRadioButton rChi2 = new JRadioButton();
  private JLabel lNumer = new JLabel();
  private JLabel lMixta = new JLabel();
  private JLabel lCateg = new JLabel();
  private JLabel lEspai1 = new JLabel();
  private JLabel lEspai2 = new JLabel();
  private JSeparator sNum1 = new JSeparator(SwingConstants.HORIZONTAL);
  private JSeparator sNum2 = new JSeparator(SwingConstants.HORIZONTAL);
  private JSeparator sMix1 = new JSeparator(SwingConstants.HORIZONTAL);
  private JSeparator sMix2 = new JSeparator(SwingConstants.HORIZONTAL);
  private JSeparator sCateg1 = new JSeparator(SwingConstants.HORIZONTAL);
  private JSeparator sCateg2 = new JSeparator(SwingConstants.HORIZONTAL);
  private FrPrincipal frPare;
  public OpcionsDis opc;
  private GestorKlass gestor;
  private boolean matriu;

  /**
  * Constructor
  *
  * @param fr es la finestra pare
  * @param opcions es el valor de les opcions de les distancies
  * @param gk es el gestor d'on penja la matriu, o es null si es de valors introduits per l'usuari
  * @param m indica si es una distància de una matriu o de valors introduits per l'usuari
  */
  public PanelD(FrPrincipal fr,OpcionsDis opcions,GestorKlass gk,boolean m) {

    matriu=m;
    gestor=gk;
	opc=opcions;
	frPare = fr;
    frPare.setSize(700,450);
    panDis.setLayout(gdis);
	panOpc.setLayout(gopc);
	this.setLayout(izq);
	cuadre = BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158));
	tTipus = new TitledBorder(cuadre,"Distància");
	panDistan.setBorder(tTipus);
	sNum1.setPreferredSize(new Dimension(26, 1));
	sNum2.setPreferredSize(new Dimension(26, 1));
	sMix1.setPreferredSize(new Dimension(20, 1));
	sMix2.setPreferredSize(new Dimension(20, 1));
	sCateg1.setPreferredSize(new Dimension(24, 1));
	sCateg2.setPreferredSize(new Dimension(24, 1));
	lNumer.setFont(new java.awt.Font("Dialog", 2, 11));
    lNumer.setText("Variables numèriques");
    lEspai1.setFont(new java.awt.Font("Dialog", 2, 11));
    lEspai1.setText(" ");
    lEspai2.setFont(new java.awt.Font("Dialog", 2, 11));
    lEspai2.setText(" ");
    lMixta.setFont(new java.awt.Font("Dialog", 2, 11));
    lMixta.setText("Mixtes (vars. num. i cat.)");
	lCateg.setFont(new java.awt.Font("Dialog", 2, 11));
    lCateg.setText("Variables categoriques");
    bOpcEucli.setText("Opcions");
    bOpcEucli.setEnabled(false);
    bOpcEucli.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       		bOpcEucli_actionPerformed(e);
    	}
    });
    bOpcAbs.setText("Opcions");
    bOpcAbs.setEnabled(false);
    bOpcAbs.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       		bOpcAbs_actionPerformed(e);
    	}
    });
    bOpcMinko.setText("Opcions");
    bOpcMinko.setEnabled(false);
    bOpcMinko.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       		bOpcMinko_actionPerformed(e);
    	}
    });
    bOpcMGibert.setText("Opcions");
    bOpcMGibert.setEnabled(false);
    bOpcMGibert.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       		bOpcMGibert_actionPerformed(e);
    	}
    });

    bOpcRalam.setText("Opcions");
    bOpcRalam.setEnabled(false);
    bOpcRalam.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       		bOpcRalam_actionPerformed(e);
    	}
    });
    bOpcIchino.setText("Opcions");
    bOpcIchino.setEnabled(false);
    bOpcIchino.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       		bOpcIchino_actionPerformed(e);
    	}
    });

    bOpcChi.setText("Opcions");
    bOpcChi.setEnabled(false);
    bOpcChi.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       		bOpcChi_actionPerformed(e);
    	}
    });

    rEucli.setText("Euclídia");
    rEucli.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       		rEucli_actionPerformed(e);
    	}
    });
 	rAbs.setText("Valor Absolut");
    rAbs.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       		rAbs_actionPerformed(e);
    	}
    });
	rMinko.setText("Minkovski");
    rMinko.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       		rMinko_actionPerformed(e);
    	}
    });
    rHamm.setText("Hamming Generalitzat");
    rHamm.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       		rHamm_actionPerformed(e);
    	}
    });
    rGower.setText("Gower");
    rGower.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       		rGower_actionPerformed(e);
    	}
    });
    rGowdaDiday.setText("Gowda-Diday");
    rGowdaDiday.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
      		rGowdaDiday_actionPerformed(e);
    	}
    });
    rIchino.setText("Ichino-Yaguchi");
    rIchino.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    	  	rIchino_actionPerformed(e);
    	}
   	});
   	rRalam.setText("Ralambondrainy");
   	rRalam.addActionListener(new java.awt.event.ActionListener() {
   		public void actionPerformed(ActionEvent e) {
      		rRalam_actionPerformed(e);
   		}
  	});
   	rMGibert.setText("Mixta Gibert");
   	rMGibert.addActionListener(new java.awt.event.ActionListener() {
   		public void actionPerformed(ActionEvent e) {
      		rMGibert_actionPerformed(e);
  	  	}
  	});
   	rChi2.setText("Chi2");
   	rChi2.addActionListener(new java.awt.event.ActionListener() {
   		public void actionPerformed(ActionEvent e) {
       		rChi2_actionPerformed(e);
      		}
   	});

   	panNum.add(sNum1);
   	panNum.add(lNumer);
   	panNum.add(sNum2);
   	panMix.add(sMix1);
   	panMix.add(lMixta);
   	panMix.add(sMix2);
   	panCateg.add(sCateg1);
   	panCateg.add(lCateg);
   	panCateg.add(sCateg2);

   	panDis.add(panNum);
   	panDis.add(rEucli);
	panDis.add(rAbs);
	panDis.add(rMinko);

	panDis.add(panMix);
	panDis.add(rMGibert);
	panDis.add(rRalam);
	panDis.add(rGower);
	panDis.add(rGowdaDiday);
	panDis.add(rIchino);

	panDis.add(panCateg);
	panDis.add(rChi2);
	panDis.add(rHamm);

   	panEsp1.add(lEspai1);
   	panEsp2.add(lEspai2);

   	panOpc.add(panEsp1);
	panOpc.add(bOpcEucli);
	panOpc.add(bOpcAbs);
	panOpc.add(bOpcMinko);
	panOpc.add(panEsp2);
	panOpc.add(bOpcMGibert);
	panOpc.add(bOpcRalam);
	panOpc.add(panEsp3);
	panOpc.add(panEsp4);
	panOpc.add(bOpcIchino);
	panOpc.add(panEsp5);
	panOpc.add(bOpcChi);

	panDistan.add(panDis);
	panDistan.add(panOpc);

  	izqui.weightx = 1;
	izqui.weighty = 1;
	izqui.anchor = GridBagConstraints.FIRST_LINE_START;
	izqui.gridwidth = GridBagConstraints.REMAINDER;
	this.add(panDistan,izqui);
  }
  /**Selecciona la distància Euclidea
   * @param e event de seleccionar la opció
   */
  public void rEucli_actionPerformed(ActionEvent e) {

	opc.setTipus(OpcionsDis.EUCLI);
	opc.setCateg('\u0000');
	opc.setQuad(false);
	opc.setPond(false);

	bOpcEucli.setEnabled(true);
	bOpcAbs.setEnabled(false);
	bOpcMinko.setEnabled(false);
	bOpcMGibert.setEnabled(false);
	bOpcRalam.setEnabled(false);
	bOpcIchino.setEnabled(false);
	bOpcChi.setEnabled(false);

	rEucli.setSelected(true);
 	rAbs.setSelected(false);
 	rMinko.setSelected(false);
	rHamm.setSelected(false);
 	rGower.setSelected(false);
	rGowdaDiday.setSelected(false);
	rIchino.setSelected(false);
	rRalam.setSelected(false);
	rMGibert.setSelected(false);
	rChi2.setSelected(false);
        //  Rober
        frPare.actualitzarBarraEstat(" ", false);

  }
  /** Selecciona la distància en valor absolut
   * @param e event de seleccionar la opció
   */
  private void rAbs_actionPerformed(ActionEvent e) {

	opc.setTipus(OpcionsDis.ABS);
	opc.setCateg('\u0000');
	opc.setQuad(false);
	opc.setPond(false);


	bOpcEucli.setEnabled(false);
	bOpcAbs.setEnabled(true);
	bOpcMinko.setEnabled(false);
	bOpcMGibert.setEnabled(false);
	bOpcRalam.setEnabled(false);
	bOpcIchino.setEnabled(false);
	bOpcChi.setEnabled(false);



	rEucli.setSelected(false);
 	rAbs.setSelected(true);
 	rMinko.setSelected(false);
	rHamm.setSelected(false);
 	rGower.setSelected(false);
    rGowdaDiday.setSelected(false);
    rIchino.setSelected(false);
    rRalam.setSelected(false);
	rMGibert.setSelected(false);
	rChi2.setSelected(false);
        //  Rober
        frPare.actualitzarBarraEstat(" ", false);

  }
  /**Selecciona la distància de Minkovski
   * @param e event de seleccionar la opció
   */
  private void rMinko_actionPerformed(ActionEvent e) {

	opc.setTipus(OpcionsDis.MINKO);
	opc.setCateg('\u0000');
	opc.setQuad(false);
	opc.setPond(false);


	bOpcEucli.setEnabled(false);
	bOpcAbs.setEnabled(false);
	bOpcMinko.setEnabled(true);
	bOpcMGibert.setEnabled(false);
	bOpcRalam.setEnabled(false);
	bOpcIchino.setEnabled(false);
	bOpcChi.setEnabled(false);


	rEucli.setSelected(false);
 	rAbs.setSelected(false);
 	rMinko.setSelected(true);
 	rHamm.setSelected(false);
 	rGower.setSelected(false);
    rGowdaDiday.setSelected(false);
    rIchino.setSelected(false);
    rRalam.setSelected(false);
	rMGibert.setSelected(false);
	rChi2.setSelected(false);
        //  Rober
        frPare.actualitzarBarraEstat(" ", false);

  }
  /**Selecciona la distància de Hamming
   * @param e event de seleccionar la opció
   */
  private void rHamm_actionPerformed(ActionEvent e) {

	opc.setTipus(OpcionsDis.HAMM);
	opc.setQuad(false);
	opc.setPond(false);


	bOpcEucli.setEnabled(false);
	bOpcAbs.setEnabled(false);
	bOpcMinko.setEnabled(false);
	bOpcMGibert.setEnabled(false);
	bOpcRalam.setEnabled(false);
	bOpcIchino.setEnabled(false);
	bOpcChi.setEnabled(false);


	rEucli.setSelected(false);
 	rAbs.setSelected(false);
 	rMinko.setSelected(false);
	rHamm.setSelected(true);
 	rGower.setSelected(false);
    rGowdaDiday.setSelected(false);
    rIchino.setSelected(false);
   	rRalam.setSelected(false);
	rMGibert.setSelected(false);
	rChi2.setSelected(false);

        //  Rober
        frPare.actualitzarBarraEstat(" ", false);

  }
  /**Selecciona la distància de Gower
   * @param e event de seleccionar la opció
   */
  private void rGower_actionPerformed(ActionEvent e) {

	opc.setTipus(OpcionsDis.GOWER);
	opc.setQuad(false);
	opc.setPond(false);


	bOpcEucli.setEnabled(false);
	bOpcAbs.setEnabled(false);
	bOpcMinko.setEnabled(false);
	bOpcMGibert.setEnabled(false);
	bOpcRalam.setEnabled(false);
	bOpcIchino.setEnabled(false);
	bOpcChi.setEnabled(false);


	rGower.setSelected(true);
	rAbs.setSelected(false);
	rMinko.setSelected(false);
	rHamm.setSelected(false);
	rEucli.setSelected(false);
	rGowdaDiday.setSelected(false);
   	rIchino.setSelected(false);
   	rRalam.setSelected(false);
   	rMGibert.setSelected(false);
   	rChi2.setSelected(false);

           //  Rober
           frPare.actualitzarBarraEstat(" ", false);

  }
  /**Selecciona la distància de Gowda-Diday
   * @param e event de seleccionar la opció
   */
  private void rGowdaDiday_actionPerformed(ActionEvent e) {

	opc.setTipus(OpcionsDis.GOWDA);
	opc.setQuad(false);
	opc.setPond(false);


	bOpcEucli.setEnabled(false);
	bOpcAbs.setEnabled(false);
	bOpcMinko.setEnabled(false);
	bOpcMGibert.setEnabled(false);
	bOpcRalam.setEnabled(false);
	bOpcIchino.setEnabled(false);
	bOpcChi.setEnabled(false);

	rGowdaDiday.setSelected(true);
	rAbs.setSelected(false);
	rMinko.setSelected(false);
	rHamm.setSelected(false);
	rGower.setSelected(false);
 	rEucli.setSelected(false);
    rIchino.setSelected(false);
    rRalam.setSelected(false);
    rMGibert.setSelected(false);
    rChi2.setSelected(false);

    //  Rober
    frPare.actualitzarBarraEstat(" ", false);

  }
  /**Selecciona la distància de Ichino-Yaguchi
   * @param e event de seleccionar la opció
   */
  private void rIchino_actionPerformed(ActionEvent e) {

	opc.setTipus(OpcionsDis.ICHINO);
	opc.setCateg('\u0000');
	opc.setQuad(false);
	opc.setPond(false);


	bOpcEucli.setEnabled(false);
	bOpcAbs.setEnabled(false);
	bOpcMinko.setEnabled(false);
	bOpcMGibert.setEnabled(false);
	bOpcRalam.setEnabled(false);
	bOpcIchino.setEnabled(true);
	bOpcChi.setEnabled(false);


   	rIchino.setSelected(true);
   	rAbs.setSelected(false);
   	rMinko.setSelected(false);
	rHamm.setSelected(false);
	rGower.setSelected(false);
	rGowdaDiday.setSelected(false);
	rEucli.setSelected(false);
	rRalam.setSelected(false);
	rMGibert.setSelected(false);
	rChi2.setSelected(false);

        //  Rober
        frPare.actualitzarBarraEstat(" ", false);

  }
  /**Selecciona la distància de Ralambondrainy
   * @param e event de seleccionar la opció
   */
  private void rRalam_actionPerformed(ActionEvent e) {

	opc.setTipus(OpcionsDis.RALAM);
	opc.setCateg('\u0000');
	opc.setQuad(false);
	opc.setPond(false);


	bOpcEucli.setEnabled(false);
	bOpcAbs.setEnabled(false);
	bOpcMinko.setEnabled(false);
	bOpcMGibert.setEnabled(false);
	bOpcRalam.setEnabled(true);
	bOpcIchino.setEnabled(false);
	bOpcChi.setEnabled(false);


	rRalam.setSelected(true);
	rAbs.setSelected(false);
	rMinko.setSelected(false);
	rHamm.setSelected(false);
	rGower.setSelected(false);
	rEucli.setSelected(false);
	rIchino.setSelected(false);
    rGowdaDiday.setSelected(false);
    rMGibert.setSelected(false);
    rChi2.setSelected(false);

    //  Rober
    frPare.actualitzarBarraEstat(" ", false);

  }
  /**Selecciona la distància de Mixta Gibert
   * @param e event de seleccionar la opció
   */
  public void rMGibert_actionPerformed(ActionEvent e) {

	opc.setTipus(OpcionsDis.MIXTA);
	opc.setCateg('\u0000');
	opc.setQuad(false);
	opc.setPond(false);


	bOpcEucli.setEnabled(false);
	bOpcAbs.setEnabled(false);
	bOpcMinko.setEnabled(false);
	bOpcMGibert.setEnabled(true);
	bOpcRalam.setEnabled(false);
	bOpcIchino.setEnabled(false);
	bOpcChi.setEnabled(false);


	rMGibert.setSelected(true);
	rAbs.setSelected(false);
	rMinko.setSelected(false);
	rHamm.setSelected(false);
	rGower.setSelected(false);
	rEucli.setSelected(false);
	rIchino.setSelected(false);
	rRalam.setSelected(false);
	rGowdaDiday.setSelected(false);
  	rChi2.setSelected(false);

          //  Rober
          frPare.actualitzarBarraEstat(" ", false);

  }
  /**Selecciona la distància de Chi2
   * @param e event de seleccionar la opció
   */
  public void rChi2_actionPerformed(ActionEvent e) {

	opc.setTipus(OpcionsDis.CHI2);
	opc.setCateg('\u0000');
	opc.setQuad(false);
	opc.setPond(false);


	bOpcEucli.setEnabled(false);
	bOpcAbs.setEnabled(false);
	bOpcMinko.setEnabled(false);
	bOpcMGibert.setEnabled(false);
	bOpcRalam.setEnabled(false);
	bOpcIchino.setEnabled(false);
	bOpcChi.setEnabled(true);


	rAbs.setSelected(false);
	rMinko.setSelected(false);
	rHamm.setSelected(false);
	rMGibert.setSelected(false);
	rGower.setSelected(false);
	rEucli.setSelected(false);
	rIchino.setSelected(false);
	rRalam.setSelected(false);
	rGowdaDiday.setSelected(false);
   	rChi2.setSelected(true);

           //  Rober
           frPare.actualitzarBarraEstat(" ", false);

  }
  /**Carrega un nou menu amb les opcions disponibles per la distància euclidea
   * @param e event de seleccionar la opció
   */
  private void bOpcEucli_actionPerformed(ActionEvent e) {
	DlgOpcDistanEucli dlg = new DlgOpcDistanEucli(frPare, "Opcions Distància",true, opc);
    dlg.setLocationRelativeTo(this);
   	dlg.show();
  }
  /**Carrega un nou menu amb les opcions disponibles per la distància en valor absolut
   * @param e event de seleccionar la opció
   */
  private void bOpcAbs_actionPerformed(ActionEvent e) {
	DlgOpcDistanAbs dlg = new DlgOpcDistanAbs(frPare, "Opcions Distància",true, opc);
    dlg.setLocationRelativeTo(this);
   	dlg.show();
  }
  /**Carrega un nou menu amb les opcions disponibles per la distància de Minkovski
   * @param e event de seleccionar la opció
   */
  private void bOpcMinko_actionPerformed(ActionEvent e) {
	DlgOpcDistanMinko dlg = new DlgOpcDistanMinko(frPare, "Opcions Distància",true, opc);
	dlg.setLocationRelativeTo(this);
   	dlg.show();
  }
  /**Carrega un nou menu amb les opcions disponibles per la distància Mixta Gibert
   * @param e event de seleccionar la opció
   */
  public void bOpcMGibert_actionPerformed(ActionEvent e) {
	DlgOpcDistanMGibert dlg = new DlgOpcDistanMGibert(frPare, "Opcions Distància",true, opc,matriu,gestor);
    dlg.setLocationRelativeTo(this);
   	dlg.show();
  }
  /**Carrega un nou menu amb les opcions disponibles per la distància Ralambondrainy
   * @param e event de seleccionar la opció
   */
  private void bOpcRalam_actionPerformed(ActionEvent e) {
	DlgOpcDistanRalam dlg = new DlgOpcDistanRalam(frPare, "Opcions Distància",true, opc,matriu,gestor);
    dlg.setLocationRelativeTo(this);
   	dlg.show();
  }
  /**Carrega un nou menu amb les opcions disponibles per la distància Ichino-Yaguchi
   * @param e event de seleccionar la opció
   */
  private void bOpcIchino_actionPerformed(ActionEvent e) {
	DlgOpcDistanIchino dlg = new DlgOpcDistanIchino(frPare, "Opcions Distància",true, opc);
    dlg.setLocationRelativeTo(this);
   	dlg.show();
  }
   /**Carrega un nou menu amb les opcions disponibles per la distància Chi2
   * @param e event de seleccionar la opció
   */
  private void bOpcChi_actionPerformed(ActionEvent e) {
	DlgOpcDistanChi dlg = new DlgOpcDistanChi(frPare, "Opcions Distància",true, opc);
    dlg.setLocationRelativeTo(this);
   	dlg.show();
  }
   /**Retorna el valor de les opcions i parametres selecionats
   * @return opc conte les OpcionsDis seleccionades
   */
  OpcionsDis getOpcions() {
	return opc;
  }
  /**Desselecciona les opcions de les distàncies*/
  public void netejar(){
	opc=new OpcionsDis();
	rAbs.setSelected(false);
	rMinko.setSelected(false);
	rHamm.setSelected(false);
	rMGibert.setSelected(false);
	rGower.setSelected(false);
	rEucli.setSelected(false);
	rIchino.setSelected(false);
	rRalam.setSelected(false);
	rGowdaDiday.setSelected(false);
    rChi2.setSelected(false);
    bOpcEucli.setEnabled(false);
	bOpcAbs.setEnabled(false);
	bOpcMinko.setEnabled(false);
	bOpcMGibert.setEnabled(false);
	bOpcRalam.setEnabled(false);
	bOpcIchino.setEnabled(false);
  }


}

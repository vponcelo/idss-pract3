//ROBER

package jklass.iu;

import jklass.nucli.GestorKlass;

import jklass.util.OpcionsDis;
import jklass.util.SO;

import java.util.logging.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 * Title: Java-KLASS
 * </p>
 * <p>
 * Description: Pantalla que permet realitzar la classificació de la matriu de
 * dades, ja sigui la clàssica segons el mètode de veïns recíprocs encadenats,
 * com la classificació condicionada o la basada en regles.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * <p>
 * </p>
 * 
 * @author Roberto Tuda i Andreu Raya
 * @version v.5
 */
public class PanelClassifica extends JPanel {
	private final static String WARD = "Ward";
	private final static String VEINS = "Veïns recíprocs encadenados";
	private final static String CONDICIONADA = "Classificació condicionada";
	private final static String PER_REGLES = "Classificació basada en regles";
	private final static String EXOGENA = "Classificació basada en regles exogena";

	private JRadioButton jRadioButtonLLExplicita = new JRadioButton();// alejandro
	private boolean activas = true;;

	private static Logger logger = Logger.getLogger(PanelClassifica.class
			.getName());

	private FrPrincipal frPare;
	private GestorKlass gestor;

	private int id, hija, padre;

	private OpcionsDis opc = new OpcionsDis();
	private PanelD pClasifica;

	private JPanel jPanelGral = new JPanel();
	private JPanel jPanelEsq = new JPanel();
	private JPanel jPanelDret = new JPanel();

	private JPanel jPanelMetode = new JPanel();
	// private JPanel jPanelMetEsq = new JPanel();
	// private JPanel jPanelMetDret = new JPanel();
	private TitledBorder tMetode;
	// private TitledBorder tMetodeDepenents;
	private Border cuadre;

	private JButton jBttnDefecte = new JButton();
	private JPanel jPanelBotones = new JPanel();
	private JButton jBttnCancel = new JButton();
	private JPanel jPanTancar = new JPanel();
	private JButton jBttnOK = new JButton();

	private TitledBorder titledBorder1;
	// private Border border1;
	private BorderLayout borderLayout1 = new BorderLayout();

	private GridLayout gridLayout2Cols = new GridLayout(1, 2);
	// private GridLayout gridLayout3Per2 = new GridLayout(3,2);
	// private GridLayout gridLayout3Cols = new GridLayout(1,5);
	private GridLayout gridLayout3Cols = new GridLayout(1, 3);
	// private GridLayout gridLayout3Rows = new GridLayout(3,1);//alejandro lo
	// comentó
	// private GridLayout gridLayout3Rows = new GridLayout(4,1);// alejandro lo
	// volvio a comentar
	private GridLayout gridLayout3Rows = new GridLayout(5, 1);
	/*
	 * private GridLayout gridLayoutEsq = new GridLayout(12,1); private
	 * GridLayout gridLayoutDret = new GridLayout(12,1); :
	 */
	private GridLayout gridLayout14Rows = new GridLayout(14, 1);

	private FlowLayout flowLayout1 = new FlowLayout();
	private FlowLayout flowLayout2 = new FlowLayout();

	private JLabel etiquetaIdentificadorClase = new JLabel();
	private JTextField textoIdentificadorClase = new JTextField();

	private JLabel etiquetaFicheroHis = new JLabel();
	private JTextField textoFicheroHis = new JTextField();
	private JCheckBox guardarFicheroHis = new JCheckBox();
	private JLabel lEspaiHis = new JLabel();

	private JLabel etiquetaFicheroDrm = new JLabel();
	private JTextField textoFicheroDrm = new JTextField();
	private JCheckBox guardarFicheroDrm = new JCheckBox();
	private JLabel lEspaiDrm = new JLabel();

	private JCheckBox visualizar = new JCheckBox();
	private JLabel lEspaiVisualitzar = new JLabel();

	private JSeparator separadorEsq = new JSeparator(SwingConstants.HORIZONTAL);
	private JSeparator separadorDret = new JSeparator(SwingConstants.HORIZONTAL);
	private JLabel lEspaiEsq = new JLabel();
	private JLabel lEspaiDret = new JLabel();
	private JPanel pSepEsq = new JPanel();
	private JPanel pSepDret = new JPanel();

	// private JLabel jLblMetod = new JLabel();
	// private String[] TpMetodes = {VEINS, CONDICIONADA, PER_REGLES};
	// private JComboBox jCmbBMetod = new JComboBox(TpMetodes);
	private ButtonGroup jbgMetode = new ButtonGroup();

	// private JCheckBox soloactivas = new JCheckBox();
	private JRadioButton radioMetodeVRE = new JRadioButton();
	private JRadioButton radioMetodeClassCond = new JRadioButton();
	private JRadioButton radioMetodeClassBR = new JRadioButton();

	private JRadioButton radioMetodeClassBRale = new JRadioButton();// alejandro

	private String[] TpMCriteri = { "Ward", "Centroide" };
	private JComboBox jCmbBCriteri = new JComboBox(TpMCriteri);
	private JLabel jLblCriteri = new JLabel();

	private JCheckBox jCBInversionsControl = new JCheckBox();
	private JLabel lEspaiCI = new JLabel();

	private JSeparator sepCIPrefixEsq = new JSeparator(
			SwingConstants.HORIZONTAL);
	private JSeparator sepCIPrefixDret = new JSeparator(
			SwingConstants.HORIZONTAL);

	// private JLabel lEspaiAdalt = new JLabel();
	// private JLabel lEspaiAbaix = new JLabel();

	// private JSeparator sepHorCond1 = new
	// JSeparator(SwingConstants.HORIZONTAL);
	// private JSeparator sepHorCond2 = new
	// JSeparator(SwingConstants.HORIZONTAL);
	// private JPanel pHorCondicionament1 = new JPanel();
	// private JPanel pHorCondicionament2 = new JPanel();

	private JLabel jLblCondicionament = new JLabel();
	private JComboBox jCmbBCondicionament = new JComboBox();
	// private JLabel lSepCondicionament = new JLabel();
	// private JSeparator sepVertCond1 = new
	// JSeparator(SwingConstants.VERTICAL);
	// private JSeparator sepVertCond2 = new
	// JSeparator(SwingConstants.VERTICAL);
	/*
	 * private JPanel pVertCondicionament1 = new JPanel(); private JPanel
	 * pVertCondicionament2 = new JPanel(); :
	 */
	private JPanel pCondicionament = new JPanel();
	private JPanel pVeinsEncadenats = new JPanel();
	private JPanel pActivas = new JPanel();

	// private JLabel lEspaiMetode1 = new JLabel();
	// private JLabel lEspaiMetode2 = new JLabel();
	// private JPanel pEspaiMetode1 = new JPanel();
	// private JPanel pEspaiMetode2 = new JPanel();

	/*
	 * private JLabel lBConeixement1 = new JLabel(); private JLabel
	 * lBConeixement2 = new JLabel(); private JSeparator sepHorBCon1 = new
	 * JSeparator(SwingConstants.HORIZONTAL); private JSeparator sepHorBCon2 =
	 * new JSeparator(SwingConstants.HORIZONTAL); private JPanel pBConeixement1
	 * = new JPanel(); private JPanel pBConeixement2 = new JPanel();
	 */

	private JLabel jLblBConeixement = new JLabel();
	private JLabel jLblBConeixementale = new JLabel();

	private JComboBox jCmbBBConeixement = new JComboBox();

	private JComboBox jCmbBBConeixementale = new JComboBox();

	/*
	 * private JLabel lSepConeixement = new JLabel(); private JSeparator
	 * sepVertCon1 = new JSeparator(SwingConstants.VERTICAL); private JSeparator
	 * sepVertCon2 = new JSeparator(SwingConstants.VERTICAL); private JPanel
	 * pVertConeixement1 = new JPanel(); private JPanel pVertConeixement2 = new
	 * JPanel(); :
	 */
	private JPanel pConeixement = new JPanel();

	private JPanel pConeixementale = new JPanel();// alejandro

	/*
	 * private JSeparator closeSepEsq = new
	 * JSeparator(SwingConstants.HORIZONTAL); private JSeparator closeSepDret =
	 * new JSeparator(SwingConstants.HORIZONTAL); private JPanel closeBCEsq =
	 * new JPanel(); private JPanel closeBCDret = new JPanel();
	 */
	private JLabel lEspaiBeginEsq = new JLabel();
	private JLabel lEspaiBeginDret = new JLabel();
	private JLabel lEspaiEndEsq = new JLabel();
	private JLabel lEspaiEndDret = new JLabel();
	// private JSeparator sepBCYCriteriEsq = new
	// JSeparator(SwingConstants.HORIZONTAL);
	// private JSeparator sepBCYCriteriDret = new
	// JSeparator(SwingConstants.HORIZONTAL);

	private JRadioButton radioModa = new JRadioButton();;
	private JRadioButton radioGibert = new JRadioButton();;
	private ButtonGroup jbgVariables = new ButtonGroup();
	private JLabel etiquetaRadio = new JLabel();
	private JLabel lEspaiRadio = new JLabel();

	/**
	 * Constructor
	 * 
	 * @param fr
	 *            es la finestra pare
	 * @param gk
	 *            es el gestor d'on penja la matriu
	 */
	public PanelClassifica(FrPrincipal fr, GestorKlass gk) {
		frPare = fr;
		gestor = gk;

		frPare.centrar();
		// alejandro ver esto
		// frPare.setLocation(350,150);

		pClasifica = new PanelD(frPare, opc, gestor, true);
		// pClasifica.inhabilitarPonderacion();
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Funció per a inicialitzar i dibuixar els elements de pantalla
	 * 
	 * @throws Exception
	 */
	private void jbInit() throws Exception {
		// frPare.setSize(618, 615);//alejandro cambio tamaño
		frPare.setSize(680, 715);
		titledBorder1 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,
				Color.white, new Color(156, 156, 158)), "Classificació");
		// border1 =
		// BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.white,1),BorderFactory.createEmptyBorder(4,4,4,4));
		this.setBorder(titledBorder1);
		this.setLayout(borderLayout1);
		flowLayout1.setHgap(75);
		// JORDI >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// Se obtienen las dimensiones en pixels de la pantalla.
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

		// Se obtienen las dimensiones en pixels de la ventana.
		Dimension ventana = frPare.getSize();

		// Un calculo para situar la ventana en el centro de la pantalla.
		frPare.setLocation((pantalla.width - ventana.width) / 2,
				(pantalla.height - ventana.height) / 2);
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		// Botones
		jBttnDefecte.setHorizontalAlignment(SwingConstants.LEFT);
		jBttnDefecte.setText("Per defecte");
		jBttnDefecte.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBttnDefecte_actionPerformed(e);
			}
		});
		jPanelBotones.setLayout(flowLayout1);
		jBttnCancel.setText("Cancel·la");
		jBttnCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBttnCancel_actionPerformed(e);
			}
		});
		jPanTancar.setLayout(flowLayout2);
		jBttnOK.setText("D'acord");
		jBttnOK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBttnOK_actionPerformed(e);
			}
		});

		jPanelBotones.add(jBttnDefecte, null);
		jPanelBotones.add(jPanTancar, null);
		jPanTancar.add(jBttnOK, null);
		jPanTancar.add(jBttnCancel, null);

		// Pintem el Panell del Mètode, el de dalt

		cuadre = BorderFactory.createEtchedBorder(Color.white, new Color(156,
				156, 158));
		tMetode = new TitledBorder(cuadre, "Mètode");
		jPanelMetode.setBorder(tMetode);

		jPanelMetode.setLayout(gridLayout3Rows);
		// jPanelMetode.setLayout(gridLayout3Rows);
		// pVeinsEncadenats.setLayout(gridLayout3Rows);
		// pCondicionament.setLayout(gridLayout3Per2);
		// pConeixement.setLayout(gridLayout3Rows);

		hija = gestor.obtenirocupadas() - 1;
		padre = hija - 1;

		// if (gestor. LenActiveProps()>0){
		// soloactivas.setSelected(true);
		// System.out.println("holaaa entre en activasdefinidas TRUEEEEEEE con largo"
		// + gestor.LenActiveProps());
		// }
		// else {
		// soloactivas.setSelected(false);
		// System.out.println("holaaa entre en activasdefinidas FASLEEEEE");
		// }

		// soloactivas.addActionListener(
		// new java.awt.event.ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// gestionarActivas();
		// }
		// });

		radioMetodeVRE.setSelected(true);
		radioMetodeVRE.setText(VEINS);
		radioMetodeVRE.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestionarParametritzador();
			}
		});
		radioMetodeClassCond.setText(CONDICIONADA);
		radioMetodeClassCond
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
						gestionarParametritzador();
					}
				});
		radioMetodeClassBR.setText(PER_REGLES);
		radioMetodeClassBR
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
						gestionarParametritzador();
					}
				});

		radioMetodeClassBRale.setText(EXOGENA);
		radioMetodeClassBRale
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
						gestionarParametritzador();
					}
				});

		jbgMetode.add(radioMetodeVRE);
		jbgMetode.add(radioMetodeClassCond);
		jbgMetode.add(radioMetodeClassBR);
		jbgMetode.add(radioMetodeClassBRale);

		// pActivas.add(soloactivas);
		// JLabel lEspaiactivas = new JLabel();
		// lEspaiactivas.setFont(new Font("Dialog", 2, 13));
		// lEspaiactivas.setText("Només variables actives");
		// pActivas.add(lEspaiactivas);

		pVeinsEncadenats.add(radioMetodeVRE);
		JLabel lEspaiMetode1 = new JLabel();
		lEspaiMetode1.setFont(new Font("Dialog", 2, 13));
		lEspaiMetode1
				.setText("                                                                                                   ");
		pVeinsEncadenats.add(lEspaiMetode1);

		omplirCombos();
		gestionarParametritzador();

		jLblCondicionament
				.setText("                                                          Variable de condició:  ");
		jLblCondicionament.setFont(new Font("Dialog", Font.ITALIC, 11));
		jCmbBCondicionament.setPreferredSize(new Dimension(125, 20));

		pCondicionament.add(radioMetodeClassCond);
		pCondicionament.add(jLblCondicionament);
		pCondicionament.add(jCmbBCondicionament);

		jLblBConeixement
				.setText("                                                   Base de coneixement:");
		jLblBConeixement.setFont(new Font("Dialog", Font.ITALIC, 11));
		jCmbBBConeixement.setPreferredSize(new Dimension(125, 20));
		pConeixement.add(radioMetodeClassBR);
		pConeixement.add(jLblBConeixement);
		pConeixement.add(jCmbBBConeixement);

		// alejandro
		jLblBConeixementale
				.setText("                                  Base de coneixement:");
		jLblBConeixementale.setFont(new Font("Dialog", Font.ITALIC, 11));
		jCmbBBConeixementale.setPreferredSize(new Dimension(125, 20));
		pConeixementale.add(radioMetodeClassBRale);
		pConeixementale.add(jLblBConeixementale);
		pConeixementale.add(jCmbBBConeixementale);

		// jPanelMetode.add(pActivas); //ale
		jPanelMetode.add(pVeinsEncadenats);
		jPanelMetode.add(pCondicionament);
		jPanelMetode.add(pConeixement);
		jPanelMetode.add(pConeixementale);// alejandro

		// jPanelMetode.setPreferredSize(new Dimension(600, 105));
		// jPanelMetode.setPreferredSize(new Dimension(600, 145));//alejandro

		jPanelMetode.setPreferredSize(new Dimension(600, 160));// alejandro2

		// Pintamos el Panel General, el de la izquierda

		jPanelGral.setLayout(gridLayout2Cols);
		jPanelGral.add(jPanelEsq);
		jPanelGral.add(jPanelDret);

		jPanelEsq.setLayout(gridLayout14Rows);
		jPanelDret.setLayout(gridLayout14Rows);

		/*
		 * closeSepEsq.setPreferredSize(new Dimension(250, 1));
		 * closeBCEsq.add(closeSepEsq);
		 * 
		 * closeSepDret.setPreferredSize(new Dimension(250, 1));
		 * closeBCDret.add(closeSepDret);
		 */
		// jRadioButtonLLExplicita.setText("Només variabless actives");
		// this.add(jRadioButtonLLExplicita);//alejandro

		lEspaiBeginEsq.setFont(new Font("Dialog", 2, 11));
		lEspaiBeginEsq.setText(" ");
		lEspaiBeginDret.setFont(new Font("Dialog", 2, 11));
		lEspaiBeginDret.setText(" ");
		lEspaiEndEsq.setFont(new Font("Dialog", 2, 11));
		lEspaiEndEsq.setText(" ");
		lEspaiEndDret.setFont(new Font("Dialog", 2, 11));
		lEspaiEndDret.setText(" ");
		/*
		 * sepBCYCriteriEsq.setPreferredSize(new Dimension(100, 1));
		 * sepBCYCriteriDret.setPreferredSize(new Dimension(100, 1));
		 * sepBCYCriteriEsq.setVisible(false);
		 * sepBCYCriteriDret.setVisible(false);
		 */

		jLblCriteri.setText(" Criteri d\'agregació:");
		jCmbBCriteri.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				activarControlInversionsSegonsCriteri();
			}
		});

		// jRadioButtonLLExplicita.setText("Només variabless actives");
		// this.add(jRadioButtonLLExplicita);//alejandro

		jCBInversionsControl.setText("Control d'inversions");
		lEspaiCI.setFont(new Font("Dialog", 2, 11));
		lEspaiCI.setText(" ");

		sepCIPrefixEsq.setVisible(false);
		sepCIPrefixDret.setVisible(false);

		etiquetaIdentificadorClase.setText(" Prefix de classe: ");
		textoIdentificadorClase.setText("C");

		etiquetaRadio.setText(" Representant de classe :");
		lEspaiRadio.setFont(new Font("Dialog", 2, 11));
		lEspaiRadio.setText(" ");
		radioGibert.setSelected(true);
		radioGibert.setText("Objectes extesos de Gibert");
		radioModa.setText("Centre de gravetat i Moda");
		jbgVariables.add(radioGibert);
		jbgVariables.add(radioModa);

		JLabel lEspaiEsqRes = new JLabel();
		lEspaiEsqRes.setFont(new Font("Dialog", 2, 11));
		lEspaiEsqRes.setText("              ");
		// separadorEsq.setPreferredSize(new Dimension(100, 1)); :
		separadorEsq.setPreferredSize(new Dimension(50, 1));
		lEspaiEsq.setFont(new Font("Dialog", 2, 11));
		lEspaiEsq.setText("Fitxers de");
		pSepEsq.add(lEspaiEsqRes);
		pSepEsq.add(separadorEsq);
		pSepEsq.add(lEspaiEsq);

		JLabel lEspaiDretRes = new JLabel();
		lEspaiDretRes.setFont(new Font("Dialog", 2, 11));
		lEspaiDretRes.setText("               ");
		// separadorDret.setPreferredSize(new Dimension(100, 1)); :
		separadorDret.setPreferredSize(new Dimension(50, 1));
		lEspaiDret.setFont(new Font("Dialog", 2, 11));
		lEspaiDret.setText("Resultats");
		pSepDret.add(lEspaiDret);
		pSepDret.add(separadorDret);
		pSepDret.add(lEspaiDretRes);

		visualizar.setEnabled(true);
		visualizar.setSelected(true);
		visualizar.setText("Visualitza dendograma");
		lEspaiVisualitzar.setFont(new Font("Dialog", 2, 11));
		lEspaiVisualitzar.setText(" ");

		etiquetaFicheroHis.setText("       Fitxer d'historia: ");
		textoFicheroHis.setText(gestor.obtenirNom());
		textoFicheroHis.setEnabled(false);

		guardarFicheroHis.setEnabled(true);
		guardarFicheroHis.setSelected(false);
		guardarFicheroHis.setText("Salvar l'arxiu .his");
		guardarFicheroHis
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guardarFicheroHis_actionPerformed(e);
					}
				});
		lEspaiHis.setFont(new java.awt.Font("Dialog", 2, 11));
		lEspaiHis.setText(" ");

		etiquetaFicheroDrm.setText("       Fitxer del dendograma: ");
		textoFicheroDrm.setText(gestor.obtenirNom());
		textoFicheroDrm.setEnabled(false);

		guardarFicheroDrm.setEnabled(true);
		guardarFicheroDrm.setSelected(false);
		guardarFicheroDrm.setText("Salvar l'arxiu .drm");
		guardarFicheroDrm
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guardarFicheroDrm_actionPerformed(e);
					}
				});
		lEspaiDrm.setFont(new java.awt.Font("Dialog", 2, 11));
		lEspaiDrm.setText(" ");

		// jPanelEsq.add(jLblMetod);
		/*
		 * jPanelEsq.add(pCondicionament1); jPanelEsq.add(jLblCondicionament); :
		 */
		/*
		 * jPanelEsq.add(pHorCondicionament1);
		 * jPanelEsq.add(pVertCondicionament1); jPanelEsq.add(pBConeixement1);
		 */
		// jPanelEsq.add(jLblBConeixement);
		/*
		 * jPanelEsq.add(pVertConeixement1); jPanelEsq.add(closeBCEsq);
		 */
		jPanelEsq.add(lEspaiBeginEsq);
		// jPanelEsq.add(sepBCYCriteriEsq);
		jPanelEsq.add(jLblCriteri);
		jPanelEsq.add(jCBInversionsControl);
		jPanelEsq.add(sepCIPrefixEsq);
		jPanelEsq.add(etiquetaIdentificadorClase);
		jPanelEsq.add(etiquetaRadio);
		jPanelEsq.add(lEspaiRadio);
		// jPanelEsq.add(separadorEsq); :
		jPanelEsq.add(pSepEsq);
		jPanelEsq.add(guardarFicheroHis);
		jPanelEsq.add(etiquetaFicheroHis);
		jPanelEsq.add(guardarFicheroDrm);
		jPanelEsq.add(etiquetaFicheroDrm);
		jPanelEsq.add(visualizar);

		// jPanelEsq.add(jRadioButtonLLExplicita );//ale

		jPanelEsq.add(lEspaiEndEsq);

		// jPanelDret.add(jCmbBMetod);
		/*
		 * jPanelDret.add(pCondicionament2);
		 * jPanelDret.add(jCmbBCondicionament); :
		 */
		/*
		 * jPanelDret.add(pHorCondicionament2);
		 * jPanelDret.add(pVertCondicionament2); jPanelDret.add(pBConeixement2);
		 */
		// jPanelDret.add(jCmbBBConeixement); :
		/*
		 * jPanelDret.add(pVertConeixement2); jPanelDret.add(closeBCDret);
		 */
		jPanelDret.add(lEspaiBeginDret);
		// jPanelDret.add(sepBCYCriteriDret);
		jPanelDret.add(jCmbBCriteri);
		jPanelDret.add(lEspaiCI);
		jPanelDret.add(sepCIPrefixDret);
		jPanelDret.add(textoIdentificadorClase);
		jPanelDret.add(radioGibert);
		jPanelDret.add(radioModa);
		// jPanelDret.add(separadorDret);
		jPanelDret.add(pSepDret);
		jPanelDret.add(lEspaiHis);
		jPanelDret.add(textoFicheroHis);
		jPanelDret.add(lEspaiDrm);
		jPanelDret.add(textoFicheroDrm);
		jPanelDret.add(lEspaiVisualitzar);
		jPanelDret.add(lEspaiEndDret);

		jPanelGral.setLayout(gridLayout2Cols);
		jPanelGral.add(jPanelEsq);
		jPanelGral.add(jPanelDret);

		jPanelGral.setPreferredSize(new Dimension(315, 250));

		this.add(jPanelMetode, BorderLayout.NORTH);
		this.add(jPanelGral, BorderLayout.WEST);
		this.add(pClasifica, BorderLayout.EAST); // alejandro

		// pClasifica.setLocation(350,150);
		// this.add(pClasifica, BorderLayout.SOUTH);
		this.add(jPanelBotones, BorderLayout.SOUTH);

	}

	/**
	 * Funció relacionada a l'esdeveniment de cancel·lació d'operació,
	 * 
	 * @param e
	 *            esdeveniment
	 */
	void jBttnCancel_actionPerformed(ActionEvent e) {

		frPare.actualitzarBarraEstat(" ", false);
		frPare.remove(this);
		frPare.validate();
		frPare.repaint();
		frPare.setSize(new Dimension(550, 300));

	}

	/**
	 * Funció relacionada amb l'esdeveniment associat al botó de <b>D'Acord</b>,
	 * que realitza,doncs, la classificació de la matriu de dades segons els
	 * paràmetres introduïts per l'usuari.
	 * 
	 * @param e
	 *            esdeveniment
	 */
	void jBttnDefecte_actionPerformed(ActionEvent e) {
		String[][] llProps;
		int lonprop0;
		int lonprop1;
		int lonprop2;
		int lonprop3;

		llProps = gestor.obtenirLlistaIDsProps();
		lonprop0 = llProps[0].length;
		lonprop1 = llProps[1].length;
		lonprop2 = llProps[2].length;
		lonprop3 = llProps[3].length;

		int n = JOptionPane
				.showConfirmDialog(
						this,
						"Aquesta opció posa tots els paràmetres de la classificació amb els seus valors per defecte. Segur que vols continuar?",
						"Assignació d'opcions per defecte",
						JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			if (lonprop0 == 0) {
				pClasifica.rChi2_actionPerformed(null);
			}
			if (lonprop1 + lonprop2 + lonprop3 == 0) {
				pClasifica.rEucli_actionPerformed(null);
				pClasifica.opc.setCateg(OpcionsDis.NONOR);
			}
			if (lonprop0 > 0 && lonprop1 + lonprop2 + lonprop3 > 0) {
				pClasifica.rMGibert_actionPerformed(null);
				pClasifica.opc.setCateg(OpcionsDis.AUTO);

			}
		}

	}

	/**
	 * Funció que valida la correctesa de les dades associades a la distància.
	 * 
	 * @return si les dades són correctes
	 */
	private boolean comprovarDades() {

		boolean ok = true;
		String[][] llProps;
		int lonprop0;
		int lonprop1;
		int lonprop2;
		int lonprop3;

		llProps = gestor.obtenirLlistaIDsProps();
		lonprop0 = llProps[0].length;
		lonprop1 = llProps[1].length;
		lonprop2 = llProps[2].length;
		lonprop3 = llProps[3].length;

		// int llindar =
		// CONDICIONADA.equals((String)jCmbBMetod.getSelectedItem())? 1 : 0; :
		int llindar = radioMetodeClassCond.isSelected() ? 1 : 0;
		switch (opc.getTipus()) {

		case OpcionsDis.EUCLI:
			if ((lonprop1 + lonprop2 + lonprop3) > llindar) {
				frPare.actualitzarBarraEstat(
						"En la distància Euclidea, la matriu de dades no pot contenir variables categoriques",
						true);
				ok = false;
			} else {
				if (gestor.obtenirMiss()) {
					frPare.actualitzarBarraEstat(
							"En la distància Euclidea,la matriu de dades no pot contenir missings",
							true);
					ok = false;
				}
			}
			break;
		case OpcionsDis.ABS:
			if ((lonprop1 + lonprop2 + lonprop3) > llindar) {
				frPare.actualitzarBarraEstat(
						"En la distància Absoluta,la matriu de dades no pot contenir variables categoriques",
						true);
				ok = false;
			} else {
				if (gestor.obtenirMiss()) {
					frPare.actualitzarBarraEstat(
							"En la distància Absoluta,la matriu de dades no pot contenir missings",
							true);
					ok = false;
				}
			}
			break;
		case OpcionsDis.HAMM:
			if ((lonprop0) > llindar) {
				frPare.actualitzarBarraEstat(
						"En la distància Hamming,la matriu de dades no pot contenir variables numèriques",
						true);
				ok = false;
			}
			;
			break;
		case OpcionsDis.CHI2:
			if ((lonprop0) > llindar) {
				frPare.actualitzarBarraEstat(
						"En la distància CHI^2,la matriu de dades no pot contenir variables numèriques",
						true);
				ok = false;
			}
			;
			break;
		case OpcionsDis.MIXTA:
			if (gestor.obtenirMiss()) {
				frPare.actualitzarBarraEstat(
						"En la distància Mixta Gibert,la matriu de dades no pot contenir missings en les variables numèriques",
						true);
				ok = false;
			}
			break;
		case OpcionsDis.GOWDA:
			if (gestor.obtenirMiss()) {
				frPare.actualitzarBarraEstat(
						"En la distància Gowda-Diday,la matriu de dades no pot contenir missings en les variables numèriques",
						true);
				ok = false;
			}
			break;
		case OpcionsDis.RALAM:
			if (gestor.obtenirMiss()) {
				frPare.actualitzarBarraEstat(
						"En la distància Ralambondrainy,la matriu de dades no pot contenir missings en les variables numèriques",
						true);
				ok = false;
			}
			break;
		case OpcionsDis.MINKO:
			if ((lonprop1 + lonprop2 + lonprop3) > llindar) {
				frPare.actualitzarBarraEstat(
						"En la distància Minkovski, la matriu de dades no pot contenir variables categoriques",
						true);
				ok = false;
			} else {
				if (gestor.obtenirMiss()) {
					frPare.actualitzarBarraEstat(
							"En la distància Minkovski,la matriu de dades no pot contenir missings",
							true);
					ok = false;
				}
			}
			break;
		case OpcionsDis.ICHINO:
			if (gestor.obtenirMiss()) {
				frPare.actualitzarBarraEstat(
						"En la distància Ichino-Yaguchi,la matriu de dades no pot contenir missings",
						true);
				ok = false;
			}
			break;

		}
		return ok;
	}

	/**
	 * Funció associada al esdeveniment de pitjar el botó de <b>D'Acord</b>.
	 * Així doncs, després de comprovar la correctesa de totes les dades, es
	 * faran les crides pertinents per a realitzar la classificació sol·licitada
	 * 
	 * @param e
	 *            esdeveniment
	 */
	void jBttnOK_actionPerformed(ActionEvent e) {
		String /* metode, */criteri, identificador, archivo1, archivo2;
		String rutaFichero = "";
		boolean clasificada = false;
		boolean ficheroHis = false;
		boolean ficheroDrm = false;
		boolean continuar = true;
		boolean activassolo = true;
		String tipus = "Gibert";

		// activassolo = soloactivas.isSelected();

		opc = pClasifica.getOpcions();

		// //////////defino el array de activas
		ArrayList activias = new ArrayList();
		ArrayList validas = new ArrayList();
		activias = gestor.matriusCarregades[gestor.obteniractual()]
				.obtenirActivas();
		String[] arractivas = new String[activias.size()];

		// alejandro
		for (int p = 0; p < activias.size(); p++) {
			// ordreProps.add(i, propietats.obtenirPropietat(i).obtenirId());
			// System.out.println( "ALEEE tengo las activas aca????"+
			// activas.get(p));
			arractivas[p] = (String) activias.get(p);
			System.out.println("tengoactivas en array????martes2/9"
					+ arractivas[p]);

		}

		// metode = (String)jCmbBMetod.getSelectedItem();
		criteri = (String) jCmbBCriteri.getSelectedItem();

		rutaFichero = gestor.obtenirDirResultats();

		identificador = textoIdentificadorClase.getText();
		archivo1 = textoFicheroHis.getText();
		if (guardarFicheroHis.isSelected()) {
			ficheroHis = true;

			if ((new File(rutaFichero + "/" + archivo1 + ".his").exists())) {
				int n = JOptionPane.showConfirmDialog(this, "El fitxer "
						+ archivo1 + ".his ja existeix, \n"
						+ "¿estàs segur que vols sobrescriure'l?",
						"Fitxer ja existeix", JOptionPane.YES_NO_OPTION);

				if (n == JOptionPane.NO_OPTION) {
					// System.out.println("Digo que no ");
					continuar = false;
				}
			}
		}

		archivo2 = textoFicheroDrm.getText();
		if (guardarFicheroDrm.isSelected()) {
			ficheroDrm = true;

			if ((new File(rutaFichero + "/" + archivo2 + ".drm").exists())) {
				int n = JOptionPane.showConfirmDialog(this, "El fitxer "
						+ archivo2 + ".drm ja existeix, \n"
						+ "¿estàs segur que vols sobrescriure'l?",
						"Fitxer ja existeix", JOptionPane.YES_NO_OPTION);

				if (n == JOptionPane.NO_OPTION) {
					// System.out.println("Digo que no ");
					continuar = false;
				}
			}

		}

		if (radioModa.isSelected()) {
			tipus = "Moda";
		}

		if (continuar) {
			// opc=pClasifica.getOpcions();
			if (comprovarDades()) {
				if (opc.getTipus() == '\u0000') {
					frPare.actualitzarBarraEstat(
							"S'ha d'elegir un tipus de distancia", true);
				} else {
					if (opc.getCateg() == '\u0000'
							&& (opc.getTipus() == OpcionsDis.EUCLI
									|| opc.getTipus() == OpcionsDis.ABS
									|| opc.getTipus() == OpcionsDis.MINKO
									|| opc.getTipus() == OpcionsDis.MIXTA
									|| opc.getTipus() == OpcionsDis.ICHINO || opc
									.getTipus() == OpcionsDis.RALAM)) {
						frPare.actualitzarBarraEstat(
								"S'ha d'elegir/omplir les opcions de la distancia seleccionada",
								true);
					} else {
						try {
							// if (VEINS.equals(metode)) { :
							if (radioMetodeVRE.isSelected()) {

								// System.out.println("entre a vecinos reciprocos ");
								// System.out.println("maxmatri"+gestor.obtenirocupadas());
								// System.out.println("actual"+gestor.obteniractual());
								// if(clasificada =
								// gestor.classificarMatriu2(/*metode,
								// */criteri, opc, identificador, tipus,
								// archivo1, archivo2, ficheroHis,
								// ficheroDrm,activas/*,
								// jCBInversionsControl.isSelected()*/)) {
								System.out.println("criterio" + criteri);
								System.out.println("opc" + opc.getCateg());
								System.out.println("identificador"
										+ identificador);
								System.out.println("tipus" + tipus);
								System.out.println("archivo1" + archivo1);
								System.out.println("archivo2" + archivo2);
								System.out.println("ficheroHis" + ficheroHis);
								System.out.println("ficheroDrm" + ficheroDrm);

								// for (int i=0;i<3;i++){

								if (clasificada = gestor.classificarMatriu(
										/* metode, */criteri, opc,
										identificador, tipus, archivo1,
										archivo2, ficheroHis, ficheroDrm/*
																		 * ,
																		 * jCBInversionsControl
																		 * .
																		 * isSelected
																		 * ()
																		 */)) {

									// Informar el nom de l'arbre
									/* String nomArbre = */gestor
											.informarIdArbre("vre", criteri,
													opc);
									frPare.actualitzarBarraEstat(
											" Classificació realitzada correctament. ",
											false);
									frPare.habilitarOpcionsMenu(true, true);
								} else {
									// frPare.actualitzarBarraEstat(" No s'ha pogut realitzar la classificació. ",
									// true);
									frPare.actualitzarBarraEstat(
											" L'identificador de classe no es correcte. ",
											true);
								}
								// }//este es el del for
							} else {
								boolean controlInversions = jCBInversionsControl
										.isSelected();
								// if (CONDICIONADA.equals(metode)) { :
								if (radioMetodeClassCond.isSelected()) {
									String condicionant = (String) jCmbBCondicionament
											.getSelectedItem();
									if (null == condicionant) {
										frPare.actualitzarBarraEstat(
												"No es pot fer una classificació basada condicionada si no hi ha cap variable categòrica.",
												true);
									} else if (clasificada = gestor
											.classificarDivisioBDD(
													condicionant, criteri,
													identificador, opc, tipus,
													controlInversions,
													archivo1, archivo2,
													ficheroHis, ficheroDrm)) {
										// Informar el nom de l'arbre
										/* String nomArbre = */gestor
												.informarIdArbre("cc", criteri,
														opc, condicionant);
										frPare.actualitzarBarraEstat(
												" Classificació realitzada correctament. ",
												false);
										frPare.habilitarOpcionsMenu(true, true);
									} else {
										// frPare.actualitzarBarraEstat(" No s'ha pogut realitzar la classificació. ",
										// true);
										frPare.actualitzarBarraEstat(
												" L'identificador de classe no es correcte. ",
												true);
									}

									// Exogena
								}
								// //////////////agrego ale exogena
								else if (radioMetodeClassBRale.isSelected()) {
									// System.out.println("entre a clasificar en exogenaaaaaaaaaa");

									String pad;

									// System.out.println("maxmatri"+gestor.obtenirocupadas());

									ArrayList lista, eliminadas;

									eliminadas = gestor.diferencia();

									if (eliminadas.size() == 0) {
										frPare.actualitzarBarraEstat(
												"No es pot fer una classificació exógena si no hi ha cap variables actives",
												true);
									}
									// else if ( soloactivas.isSelected()){
									// frPare.actualitzarBarraEstat("Se debe hacer con todas",
									// true);
									// }

									else {

										String bConeixement = (String) jCmbBBConeixementale
												.getSelectedItem();
										String condicionant;
										condicionant = gestor.avaluaBCexo(
												bConeixement, criteri,
												identificador, opc, tipus,
												controlInversions, archivo1,
												archivo2, ficheroHis,
												ficheroDrm);
										// System.out.println("cuanto es el condicionant"+condicionant);

										try {
											// System.out.println("voy a eliminar props");
											gestor.eliminarPropietats(eliminadas);
											// System.out.println("eliminé las props");
										} catch (Exception ex) {
											ex.printStackTrace();
											frPare.actualitzarBarraEstat(
													"No s'han pogut eliminar les propietats de forma definitiva: "
															+ ex.getMessage(),
													true);
										}
										if (null == condicionant) {
											frPare.actualitzarBarraEstat(
													"No es pot fer una classificació  condicionada si no hi ha cap variable categòrica.",
													true);
										}

										else if (clasificada = gestor
												.classificarDivisioBDD(
														condicionant, criteri,
														identificador, opc,
														tipus,
														controlInversions,
														archivo1, archivo2,
														ficheroHis, ficheroDrm)) {
											// System.out.println("sali de la condicioanda de la exogena con clasificada="+clasificada);

											/* String nomArbre = */gestor
													.informarIdArbre("cc",
															criteri, opc,
															condicionant);
											String nomArbre = gestor
													.obtenirNomArbre();
											// System.out.println("con que nom arbre sale"+nomArbre);

											frPare.actualitzarBarraEstat(
													" Hice bien la exogena!!!. ",
													false);
											frPare.habilitarOpcionsMenu(true,
													true);
										}

										// /////////
										// if(lista.size() == 0){ // Informar el
										// nom de l'arbre
										// /*String nomArbre =
										// */gestor.informarIdArbre("vre",
										// criteri, opc);
										// frPare.actualitzarBarraEstat(" Classificació realitzada correctament. ",
										// false);
										// frPare.habilitarOpcionsMenu(true,
										// true);
										// }
										else {
											frPare.actualitzarBarraEstat(
													" No s'ha pogut realitzar la classificació. ",
													true);
											frPare.actualitzarBarraEstat(
													" L'identificador de classe no es correcte. ",
													true);
										}
									}
									// System.out.println("termine la exogena con matriuactual"+gestor.
									// obteniractual());
									// String matriu=" ";

									// matriu = gestor.tancaMatriuActual();
									// ////fin EXOGENA TERMINA

								} else if (radioMetodeClassBR.isSelected()) {
									String bConeixement = (String) jCmbBBConeixement
											.getSelectedItem();
									if (null == bConeixement) {
										frPare.actualitzarBarraEstat(
												"No es pot fer una classificació basada en regles si no hi ha cap base de coneixement",
												true);
									}
									if (null != bConeixement) {
										System.out
												.println("hola entre al comprobar");
										validas = gestor.ComprobarProps(
												bConeixement, arractivas);

										for (int p = 0; p < validas.size(); p++) {

											System.out
													.println("que hay en validas"
															+ validas.get(p));

										}

										System.out
												.println("hola sali del comprobar");

									}
									for (int r = 0; r < arractivas.length; r++) {
										// ordreProps.add(i,
										// propietats.obtenirPropietat(i).obtenirId());
										System.out
												.println("tengo las activas en el arrayale????"
														+ arractivas[r]);

									}

									// }
									// ///////////////agrego para validar
									// activas
									/*
									 * if ( validas.size()>0){
									 * frPare.actualitzarBarraEstat(
									 * "Las propiedades de las reglas no estan definidas como activas "
									 * , true); }
									 */

									// ////////////fin del agrego

									if (clasificada = null != gestor
											.avaluaBCIClassifica(bConeixement,
													criteri, identificador,
													opc, tipus,
													controlInversions,
													archivo1, archivo2,
													ficheroHis, ficheroDrm)) {
										frPare.actualitzarBarraEstat(
												" Classificació realitzada correctament. ",
												false);
										frPare.habilitarOpcionsMenu(true, true);
									} else {
										// frPare.actualitzarBarraEstat(" No s'ha pogut realitzar la classificació. ",
										// true);
										frPare.actualitzarBarraEstat(
												" L'identificador de classe no es correcte. ",
												true);
									}
								}
							}
							/*
							 * if (clasificada) { frPare.actualitzarBarraEstat(
							 * " Classificació realitzada correctament. ",
							 * false); } else { //frPare.actualitzarBarraEstat(
							 * " No s'ha pogut realitzar la classificació. ",
							 * true); frPare.actualitzarBarraEstat(
							 * " L'identificador de classe no es correcte. ",
							 * true); }
							 */
						} catch (Exception ex) {
							// ex.printStackTrace();
							frPare.actualitzarBarraEstat(
									" Error: " + ex.getLocalizedMessage(), true);

						}
					}
				}
			}
		}
		// if (visualizar.isSelected() && clasificada) {
		/**
		 * New by Marco Villegas. 14-01-2012. The final classification performed
		 * must be independent that if you want or not the visualization.
		 */
		if (clasificada) {
			String cmd = new String();
			Process proc;
			int err;
			boolean ok = false;

			try {
				ok = gestor.visualitzaArbre();
			} catch (Exception ex) {
				frPare.actualitzarBarraEstat(
						"Hi han hagut problemes per visualitzar el dendograma. ("
								+ ex.getMessage() + ")", true);
			}

			if (ok) {
				String nomRes = null;
				// String nomRes = new
				// File(frPare.obtenirNomDades()).getName();ale sabado
				try {
					nomRes = new File(gestor.nomActual(activas)).getName();
				}

				catch (Exception ex) {
					logger.warning(ex.toString() + ": " + ex.getMessage());
				}

				// System.out.println("imprimo nomRes"+ nomRes);
				frPare.actualitzarBarraEstat(
						"S'ha realitzat la visualització del dendograma correctament. (Resultats al fitxer: "
								+ nomRes + "InfAuto.tex)", false);
				try {
					if (visualizar.isSelected()) {
						String pathResult = gestor.obtenirDirResultats();
						System.out.println("imprimo pathResult" + pathResult);
						File dirResult = new File(pathResult);
						pathResult = pathResult + nomRes + "Dendo";
						// System.out.println("imprimo segundo pathResult"+pathResult);

						cmd = SO.obtenirCmdCompilaLtx(pathResult + ".tex");
						// System.out.println("VIERNES QUE HAY EN COMAND???"+cmd);

						if (cmd == null) {
							frPare.actualitzarBarraEstat(
									"No es pot compilar ni visualitzar automàticament el fitxer Latex generat per no haver-se reconegut el sistema operatiu.",
									true);
						} else {
							logger.finer("Comanda exec: " + cmd);
							proc = Runtime.getRuntime().exec(cmd, null,
									dirResult);
							// System.out.println("QUE HAY EN PROC???"+proc);

							try {
								// error?
								StreamGobbler errorGobbler = new StreamGobbler(
										proc.getErrorStream(), "ERROR (exec)");

								// output?
								StreamGobbler outputGobbler = new StreamGobbler(
										proc.getInputStream(), "OUTPUT (exec)");

								errorGobbler.start();
								outputGobbler.start();

								err = proc.waitFor();
								if (err == 0) {
									// System.out.println("entro aca error = 0");
									cmd = SO.obtenirCmdVisorLtx(pathResult
											+ ".dvi");
									System.out.println("cmd" + cmd);
									Runtime.getRuntime().exec(cmd, null,
											dirResult);
								} else {
									// System.out.println("error debe valer distinto de cero="+err);
									frPare.actualitzarBarraEstat(
											"ALE4No s'ha pogut compilar correctament el fitxer Latex generat.",
											true);
								}
							} catch (InterruptedException exc) {
								frPare.actualitzarBarraEstat(
										"S'ha interrumput el procés de compilació del fitxer Latex ( InterruptedException "
												+ exc.getMessage() + " )", true);
							}
						}
					}
				} catch (IOException exc) {
					frPare.actualitzarBarraEstat(
							"Error al generar i visualitzar el latex ( IOException "
									+ exc.getMessage() + " )", true);
				}
			}
			validate();
			repaint();
			// alejandro cierro matriz temporal
			if (radioMetodeClassBRale.isSelected()) {
				// System.out.println("maxmatri es el del final antes"+gestor.obtenirocupadas());
				// gestor.canviarMatriuActual(0);
				// String matriu=" ",mat=" ";
				// matriu = gestor.tancaMatriuActual();dejarlo comentado siempre

				int ide;
				// String bCo = (String)jCmbBBConeixementale.getSelectedItem();

				// try{
				// ide = gestor.carregarMatriu0(gestor.obtenirmatriz());
				// gestor.carregarRegles(gestor.obtenirmatriz(),bCo);

				// }
				// catch (Exception ex){
				// logger.warning(ex.toString() + ": " + ex.getMessage());
				// }

				// System.out.println("maxmatri es el del final despues"+gestor.obtenirocupadas());

				// matriu = gestor.tancaMatriuActual();

			}

		}
	}

	/**
	 * Funció que guarda memòria de si l'usuari ha seleccionat guarda els fitxer
	 * d'història a disc
	 * 
	 * @param e
	 *            esdeveniment
	 */
	public void guardarFicheroHis_actionPerformed(ActionEvent e) {
		if (guardarFicheroHis.isSelected()) {
			textoFicheroHis.setEnabled(true);
		} else {
			textoFicheroHis.setEnabled(false);
		}
	}

	/**
	 * Funció que guarda memòria de si l'usuari ha seleccionat guarda els fitxer
	 * drm a disc
	 * 
	 * @param e
	 *            esdeveniment
	 */
	public void guardarFicheroDrm_actionPerformed(ActionEvent e) {
		if (guardarFicheroDrm.isSelected()) {
			textoFicheroDrm.setEnabled(true);
		} else {
			textoFicheroDrm.setEnabled(false);
		}
	}

	/**
	 * Funció que gestiona la disponibilitat i l'estat de selecció per defecte
	 * del check de control d'inversions segons el mètode i criteri
	 * seleccionats.
	 */
	private void activarControlInversionsSegonsCriteri() {
		boolean isWard = WARD.equals(jCmbBCriteri.getSelectedItem());
		// boolean parametritzada = !VEINS.equals(jCmbBMetod.getSelectedItem());
		// :
		boolean parametritzada = !radioMetodeVRE.isSelected();
		boolean active = isWard && parametritzada;
		jCBInversionsControl.setEnabled(active);
		jCBInversionsControl.setSelected(active);
	}

	/**
	 * Funció que,segons el mètode seleccionat, activa la caixa de selecció de
	 * les variables categòriques (condicionada) o de les Bases de Coneixement
	 * (basada en regles)
	 * 
	 */
	private void gestionarParametritzador() {
		// Object metode = jCmbBMetod.getSelectedItem();
		// boolean condicionada = CONDICIONADA.equals(metode); :
		// boolean activas = soloactivas.isSelected();
		boolean condicionada = radioMetodeClassCond.isSelected();
		// boolean perRegles = PER_REGLES.equals(metode); :
		boolean perRegles = radioMetodeClassBR.isSelected();
		boolean exogena = radioMetodeClassBRale.isSelected();
		boolean isWard = WARD.equals(jCmbBCriteri.getSelectedItem());
		boolean controlInversions = (condicionada || perRegles) && isWard;
		jCmbBCondicionament.setEnabled(condicionada);
		jCmbBBConeixement.setEnabled(perRegles);
		jCmbBBConeixementale.setEnabled(exogena); // alejandro
		jCBInversionsControl.setEnabled(controlInversions);
		jCBInversionsControl.setSelected(controlInversions);
	}

	/*
	 * private void gestionarActivas() {
	 * 
	 * //actual=gestor.obteniractual(){ //hija=gestor.obtenirocupadas()-1;
	 * 
	 * 
	 * // System.out.println( " El gestionar activas no hace nada ahora" );
	 * if(soloactivas.isSelected()){ activas = true;
	 * 
	 * 
	 * // gestor.canviarMatriuActual(gestor.obtenirocupadas()-1 ); //
	 * gestor.canviarMatriuActual(1 ); System.out.println(
	 * " ACtivas true cambie a "+(gestor.obtenirocupadas()-2) );
	 * System.out.println( " que carajo vale la actual "+ gestor.obteniractual()
	 * ); gestor.canviarMatriuActual(1 ); } else{ activas = false; //
	 * activarTodas(); gestor.canviarMatriuActual(0); //
	 * gestor.canviarMatriuActual(0); System.out.println(
	 * "activas false cambie a "+(gestor.obtenirocupadas()-1) );
	 * System.out.println(
	 * " que carajo vale la actual en false "+gestor.obteniractual() ); }
	 * 
	 * }
	 */

	/**
	 * Funció encarregada d'omplir les caixes de selecció amb les variables
	 * categòriques per al condicionament i la llista de bases de coneixement.
	 * 
	 */
	private void omplirCombos() {
		// String[][] llProps = gestor.obtenirLlistaIDsProps();ale para
		// condicionada
		String[][] llProps;
		ArrayList activias = new ArrayList();
		activias = gestor.matriusCarregades[gestor.obteniractual()]
				.obtenirActivas();
		if (gestor.consultasel()) {
			llProps = gestor.obtenirLlistaIDsPropsActivas(activias);
		} else {
			llProps = gestor.obtenirLlistaIDsProps();
		}

		int lon = llProps[1].length;
		for (int i = 0; i < lon; i++) {
			jCmbBCondicionament.addItem(llProps[1][i]);
		}
		lon = llProps[2].length;
		for (int i = 0; i < lon; i++) {
			jCmbBCondicionament.addItem(llProps[2][i]);
		}
		lon = llProps[3].length;
		for (int i = 0; i < lon; i++) {
			jCmbBCondicionament.addItem(llProps[3][i]);
		}
		String[] llBC = gestor.obtenirLlistaNomsBC();
		lon = llBC.length;
		for (int i = 0; i < lon; i++) {
			jCmbBBConeixement.addItem(llBC[i]);
			jCmbBBConeixementale.addItem(llBC[i]);

		}
	}
}

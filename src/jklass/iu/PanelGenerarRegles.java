package jklass.iu;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.lang.InterruptedException;
import java.util.logging.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.ImageIcon;

import jklass.nucli.BaseConeixement;
import jklass.nucli.GestorKlass;
import jklass.nucli.PropCategorica;
import jklass.nucli.Propietat;
import jklass.nucli.TaulaQualitat;
import jklass.util.Opcions;
import jklass.util.SO;

/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
/**
 * <p>
 * Title: Panel generador de regles
 * </p>
 * <p>
 * Description: Panel que permet generar regles
 * </p>
 * @author Alfons Bosch, Patrícia Garcia
 */
public class PanelGenerarRegles extends JPanel {
	private static Logger logger = Logger.getLogger(PanelClasses.class.getName());

	Opcions opcClass = new Opcions(Opcions.PER_CLASSES);

	BorderLayout borderLayout1 = new BorderLayout();

	FrPrincipal frPare;

	GestorKlass gestor;

	TitledBorder titledBorder1;

	JPanel jPanelBotones = new JPanel();

	Border border2;

	TitledBorder titledBorder2;

	Border border3;

	Border border4;

	TitledBorder titledBorder3;

	Border border5;

	TitledBorder titledBorder4;

	Border border6;

	TitledBorder titledBorder5;

	DefaultListModel listModelVN = new DefaultListModel();

	JList jListVarsN = new JList(listModelVN);

	DefaultListModel listModelSN = new DefaultListModel();

	JList jListSelcN = new JList(listModelSN);

	Border border7;

	TitledBorder titledBorder6;

	DefaultListModel listModelVC = new DefaultListModel();

	JList jListVarsC = new JList(listModelVC);

	DefaultListModel listModelSC = new DefaultListModel();

	JList jListSelcC = new JList(listModelSC);

	FlowLayout flowLayout2 = new FlowLayout();

	JButton jBttnDefecte = new JButton();

	JButton jBttnCancel = new JButton();

	JPanel jPanTancar = new JPanel();

	JButton jBttnOK = new JButton();

	FlowLayout flowLayout3 = new FlowLayout();

	String nomFitxer = null;

	Border border8;

	TitledBorder titledBorder7;

	Border border9;

	TitledBorder titledBorder8;

	JPanel jPanVars = new JPanel();

	JButton jBttnAfegirC = new JButton();

	BorderLayout borderLayout2 = new BorderLayout();

	JButton jBttnTreureC = new JButton();

	JPanel jPanelBtnsC = new JPanel();

	JScrollPane jSPSelecC = new JScrollPane(jListSelcC);

	BorderLayout borderLayout6 = new BorderLayout();

	JPanel jPanelSelecCat = new JPanel();

	JPanel jPanSelec = new JPanel();

	BorderLayout borderLayout5 = new BorderLayout();

	JPanel jPanLlistes = new JPanel();

	JScrollPane jSPVarsN = new JScrollPane(jListVarsN);

	JScrollPane jSPVarsC = new JScrollPane(jListVarsC);

	FlowLayout flowLayout1 = new FlowLayout();

	JPanel jPanelSelecNum = new JPanel();

	JPanel jPanelSelecClass = new JPanel();

	JScrollPane jSPSelecN = new JScrollPane(jListSelcN);

	BorderLayout borderLayout4 = new BorderLayout();

	JButton jBttnAfegirN = new JButton();

	private JCheckBox jCheckBoxRevisat;

	private JCheckBox jCheckBoxGenerarMKMZ;

	private JCheckBox jCheckBoxDescriptivaBC;

	private JPanel jPanel1;

	JButton jBttnTreureN = new JButton();

	JPanel jPanelBtnsN = new JPanel();

	Border border10;

	TitledBorder titledBorder9;

	FlowLayout flowLayout4 = new FlowLayout();

	FlowLayout flowLayout5 = new FlowLayout();

	DefaultListModel listModelSCl = new DefaultListModel();

	JList jListSelcCl = new JList(listModelSCl);

	JScrollPane jSPSelecCl = new JScrollPane(jListSelcCl);

	JButton jBttnTreureCl = new JButton();

	JButton jBttnAfegirCl = new JButton();

	JPanel jPanelBtnsCl = new JPanel();

	BorderLayout borderLayout7 = new BorderLayout();

	Border border11;

	TitledBorder titledBorder10;

	public PanelGenerarRegles(FrPrincipal fr, GestorKlass gk) {
		String[][] llProps;
		int lon;

		frPare = fr;
		gestor = gk;
		nomFitxer = frPare.obtenirNomDades();
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

		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void jbInit() throws Exception {
		border8 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white, new Color(109, 109,
				110), new Color(156, 156, 158));
		titledBorder7 = new TitledBorder(border8, "Vars. Categ.");
		border9 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white, new Color(109, 109,
				110), new Color(156, 156, 158));
		titledBorder8 = new TitledBorder(border9, "Vars. categ. selecc.");
		border10 = BorderFactory.createEtchedBorder(Color.white, new Color(156, 156, 158));
		titledBorder9 = new TitledBorder(border10, "Càlculs a realitzar");
		border11 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white, new Color(109, 109,
				110), new Color(156, 156, 158));
		titledBorder10 = new TitledBorder(border11, "Vars. de classe");
		frPare.setSize(620, 720);
		frPare.centrar();
		titledBorder1 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, Color.white, new Color(156, 156, 158)),
				"Inducció de regles 'boxplot-based'");
		border2 = new EtchedBorder(EtchedBorder.RAISED, Color.white, new Color(178, 178, 178));
		titledBorder2 = new TitledBorder(border2, "Llista de variables");
		border3 = BorderFactory.createCompoundBorder(titledBorder2, BorderFactory.createEmptyBorder(2, 2, 2, 2));
		border4 = BorderFactory.createEmptyBorder();
		titledBorder3 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(156, 156, 158)),
				"Vars. numèriques");
		border5 = BorderFactory.createEmptyBorder();
		titledBorder4 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(156, 156, 158)),
				"Vars. categòriques");
		border6 = BorderFactory.createLineBorder(Color.white, 1);
		titledBorder5 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white,
				new Color(109, 109, 110), new Color(156, 156, 158)), "Vars. Num.");
		border7 = new EtchedBorder(EtchedBorder.RAISED, Color.white, new Color(156, 156, 158));
		titledBorder6 = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white,
				new Color(109, 109, 110), new Color(156, 156, 158)), "Vars. num. selecc.");
		this.setLayout(borderLayout1);
		jListVarsN.setBorder(null);
		jListVarsC.setBorder(null);
		this.setBorder(titledBorder1);
		this.setDebugGraphicsOptions(0);
		this.setPreferredSize(new java.awt.Dimension(532, 567));
		this.setEnabled(false);
		{
			jPanel1 = new JPanel();
			this.add(jPanel1, BorderLayout.CENTER);
			jPanel1.setPreferredSize(new java.awt.Dimension(418, 49));
			jPanel1.setLayout(null);
			jPanel1.add(jPanelBotones);
		}
		flowLayout2.setAlignment(FlowLayout.RIGHT);

		jBttnDefecte.setText("Per defecte");
		jBttnDefecte.setHorizontalAlignment(SwingConstants.LEFT);
		jBttnCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBttnCancel_actionPerformed(e);
			}
		});
		jBttnCancel.setText("Cancel·la");
		jPanTancar.setLayout(flowLayout2);
		jBttnOK.setText("D'acord");
		jBttnOK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBttnOK_actionPerformed(e);
			}
		});
		jPanelBotones.setLayout(flowLayout3);
		flowLayout3.setHgap(75);

		jBttnAfegirC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
		jBttnAfegirC.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBttnAfegirC_actionPerformed(e);
			}
		});
		jBttnTreureC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
		jBttnTreureC.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBttnTreureC_actionPerformed(e);
			}
		});
		jPanLlistes.setLayout(borderLayout5);
		jPanLlistes.setBorder(null);
		jPanSelec.setLayout(borderLayout2);
		jSPVarsN.getViewport().setBackground(Color.white);
		jSPVarsN.setAutoscrolls(true);
		jSPVarsN.setBorder(titledBorder5);
		jSPVarsN.setPreferredSize(new Dimension(100, 160));
		jSPVarsC.setBorder(titledBorder7);
		jSPVarsC.setOpaque(true);
		jSPVarsC.setPreferredSize(new Dimension(100, 278));
		jSPVarsC.setRequestFocusEnabled(true);
		jSPVarsC.setAutoscrolls(true);
		jPanelSelecNum.setLayout(flowLayout1);
		jSPSelecN.setAutoscrolls(true);
		jSPSelecN.setBorder(titledBorder6);
		jSPSelecN.setPreferredSize(new Dimension(115, 153));
		jPanelBtnsC.setLayout(borderLayout6);
		jSPSelecC.setBorder(titledBorder8);
		jSPSelecC.setPreferredSize(new Dimension(115, 153));
		jSPSelecC.setAutoscrolls(true);
		jSPSelecCl.setBorder(titledBorder10);
		jSPSelecCl.setPreferredSize(new Dimension(115, 120));
		jSPSelecCl.setAutoscrolls(true);
		jPanSelec.setBorder(null);
		jPanelSelecCat.setLayout(flowLayout4);
		jPanelSelecClass.setLayout(flowLayout5);
		jBttnAfegirN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
		jBttnAfegirN.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBttnAfegirN_actionPerformed(e);
			}
		});
		jBttnTreureN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
		jBttnTreureN.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBttnTreureN_actionPerformed(e);
			}
		});
		jPanelBtnsN.setLayout(borderLayout4);
		jBttnTreureCl.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBttnTreureCl_actionPerformed(e);
			}
		});
		jBttnTreureCl.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
		jBttnAfegirCl.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBttnAfegirCl_actionPerformed(e);
			}
		});
		jBttnAfegirCl.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
		jPanelBtnsCl.setLayout(borderLayout7);
		borderLayout5.setVgap(6);
		jPanelBtnsCl.add(jBttnAfegirCl, BorderLayout.NORTH);
		jPanelBtnsCl.add(jBttnTreureCl, BorderLayout.SOUTH);
		jPanelSelecClass.add(jPanelBtnsCl, null);
		jPanelSelecClass.add(jSPSelecCl, null);
		jPanelSelecNum.add(jPanelBtnsN, null);
		jPanelBtnsN.add(jBttnAfegirN, BorderLayout.NORTH);
		jPanelBtnsN.add(jBttnTreureN, BorderLayout.SOUTH);
		jPanelBotones.setBounds(58, 58, 413, 39);
		{
			jCheckBoxRevisat = new JCheckBox();
			jPanel1.add(jCheckBoxRevisat);
			jCheckBoxRevisat.setText("Mètode revisat (centre tancat-centre obert). Només variables binàries");
			jCheckBoxRevisat.setBounds(149, 18, 400, 16);
			jCheckBoxRevisat.setEnabled(false);
		}
		{
			jCheckBoxDescriptivaBC = new JCheckBox();
			jPanel1.add(jCheckBoxDescriptivaBC);
			jCheckBoxDescriptivaBC.setText("Mostrar descriptiva BC");
			jCheckBoxDescriptivaBC.setBounds(149, 0, 205, 15);
		}
		{
			jCheckBoxGenerarMKMZ = new JCheckBox();
			jPanel1.add(jCheckBoxGenerarMKMZ);
			jCheckBoxGenerarMKMZ.setText("Generar fitxers .mk i .zk");
			jCheckBoxGenerarMKMZ.setBounds(149, 37, 168, 18);
			jCheckBoxGenerarMKMZ.setEnabled(false);
		}
		jPanTancar.add(jBttnOK, null);
		jPanTancar.add(jBttnCancel);
		jPanelBotones.add(jBttnDefecte, null);
		jPanelBotones.add(jPanTancar, null);
		this.add(jPanVars, BorderLayout.NORTH);
		this.add(jPanVars, BorderLayout.NORTH);
		jPanelSelecCat.add(jPanelBtnsC, null);
		jPanelBtnsC.add(jBttnAfegirC, BorderLayout.NORTH);
		jPanelBtnsC.add(jBttnTreureC, BorderLayout.SOUTH);
		jPanelSelecCat.add(jSPSelecC, null);
		jPanSelec.add(jPanelSelecCat, BorderLayout.CENTER);
		jPanSelec.add(jPanelSelecNum, BorderLayout.SOUTH);
		jPanSelec.add(jPanelSelecClass, BorderLayout.NORTH);
		jPanelSelecNum.add(jSPSelecN, null);
		jPanLlistes.add(jSPVarsN, BorderLayout.SOUTH);
		jPanLlistes.add(jSPVarsC, BorderLayout.NORTH);
		jPanVars.add(jPanLlistes, null);
		jPanVars.add(jPanSelec, null);
		// frPare.actualitzarBarraEstat("Altura " + frPare.getHeight()+ "
		// Anchura " + frPare.getWidth())
	}

	/**
	 * Mètode que s'activa al realitzar l'acció
	 * @param e
	 */
	void jBttnOK_actionPerformed(ActionEvent e) {
		boolean ok = true;
		// llistaVars[0][] - Conté la llista de vars categ de classe
		// llistaVars[1][] - Conté la llista de vars numeriques
		// llistaVars[2][] - Conté la llista de vars categoriques
		String[][] llistaVars = new String[3][];
		int nvarsN, nvarsC, nvarsCl, i, err;
		nvarsCl = listModelSCl.getSize();
		if (nvarsCl == 0) {
			frPare.actualitzarBarraEstat("S'ha de seleccionar alguna variable de classe", true);
		} else {
			nvarsN = listModelSN.getSize();
			nvarsC = listModelSC.getSize();
			if ((nvarsC == 0) && (nvarsN == 0)) {
				frPare.actualitzarBarraEstat("S'ha de seleccionar alguna variable numèrica o categòrica", true);
			} else {
				llistaVars[0] = new String[nvarsCl];
				llistaVars[1] = new String[nvarsN];
				llistaVars[2] = new String[nvarsC];
				for (i = 0; i < nvarsCl; i++) {
					llistaVars[0][i] = (String) listModelSCl.get(i);
				}
				for (i = 0; i < nvarsN; i++) {
					llistaVars[1][i] = (String) listModelSN.get(i);
				}
				for (i = 0; i < nvarsC; i++) {
					llistaVars[2][i] = (String) listModelSC.get(i);
				}

				BaseConeixement bc = null;
				try {
					bc = gestor.generacioReglesBoxPlot(llistaVars, jCheckBoxDescriptivaBC.isSelected(),
							jCheckBoxGenerarMKMZ.isSelected(), jCheckBoxRevisat.isSelected());
				} catch (Exception e1) {
					frPare
							.actualitzarBarraEstat("No s'ha dut a terme la generació de regles: " + e1.getMessage(),
									true);
					ok = false;
				}
				if (ok) {
					frPare.actualitzarBarraEstat("S'ha generat la base de coneixement correctament.", false);
					
					//A partir d'aquí només si s'ha demanat l'anàlisi descriptiva
					if (jCheckBoxDescriptivaBC.isSelected() && bc != null) {
						String nomBC = bc.getNomBC();
						Vector<Integer>[] llistaEstads = new Vector[2];
						Vector<Integer>[] llistaEstadsC = new Vector[3];
						llistaEstads[0] = new Vector<Integer>();
						llistaEstads[1] = new Vector<Integer>();
						llistaEstadsC[0] = new Vector<Integer>();
						llistaEstadsC[1] = new Vector<Integer>();
						llistaEstadsC[2] = new Vector<Integer>();

						llistaEstads[1].add(new Integer(Opcions.DIAGR_BARRES));
						llistaEstads[1].add(new Integer(Opcions.TAULA_FREQS));
						llistaEstadsC[0].add(new Integer(Opcions.DESCR_EXTENSIONAL));

						int iNumProps = 0;
						ArrayList llistaBC = new ArrayList();
						ArrayList llistaVariables = new ArrayList();

						ArrayList llistaProp;
						try {
							llistaProp = gestor.avaluaReglaARegla(nomBC, false);

							iNumProps = iNumProps + llistaProp.size()*2;
							llistaBC.add(nomBC);
							String[][] llistaVarsDescr = new String[2][];
							llistaVarsDescr[0] = new String[0];
							llistaVarsDescr[1] = new String[llistaProp.size()];
							String[][] llistaVarsCDescr = new String[3][];
							llistaVarsCDescr[0] = new String[llistaProp.size()];
							llistaVarsCDescr[1] = new String[0];
							llistaVarsCDescr[2] = new String[0];
							for (int j = 0; j < llistaProp.size(); j++) {
								Propietat paux = (Propietat) llistaProp.get(j);
								llistaVarsDescr[1][j] = paux.obtenirId();
								llistaVarsCDescr[0][j] = paux.obtenirId();
							}
							ArrayList al = new ArrayList(2);
							al.add(llistaVarsDescr);
							al.add(llistaVarsCDescr);
							llistaVariables.add(al);
							Opcions opcUniv = new Opcions(Opcions.UNIVARIANT);
							opcUniv.posarPerDefecteBC(Opcions.TAULA_FREQS);
							opcUniv.posarPerDefecteBC(Opcions.DIAGR_BARRES);

							Opcions opcClass = new Opcions(Opcions.PER_CLASSES);
							opcClass.posarPerDefecteBC(Opcions.DESCR_EXTENSIONAL);
							
							// TEMP >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> !!
							gestor.ferQualitatBC(nomBC, true, true, true, true, true, true, 1, "Intpp"); // TEMP!!
							
							// JORDI >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
							//gestor.ferDescripBC(llistaBC, llistaVariables, llistaEstads, llistaEstadsC, opcUniv,opcClass, 1, 2, 0, true, "Intpp");
							gestor.ferDescripBC(llistaBC, llistaVariables, llistaEstads, llistaEstadsC, opcUniv,
									opcClass, 1, 3, 3, true, "Intpp");
							// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
							try {
								gestor.eliminarColumnes(iNumProps);
							} catch (Exception e1) {
								e1.printStackTrace();
								frPare.actualitzarBarraEstat("Error al generar i visualitzar el latex ( IOException "
										+ e1.getMessage() + " )", true);
							}

							int matriuActual = gestor.idMatriuActual();
							String nomFitxer = frPare.obtenirNomDades();
							String nom = new File(nomFitxer).getName();

							String cmd;
							Process proc;
							try {
								String pathResult = gestor.obtenirDirResultats();
								File dirResult = new File(pathResult);
								// JORDI >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
								//pathResult = pathResult + nom + "dreg";
								pathResult = pathResult + nom + "Intpp";
								// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

								cmd = SO.obtenirCmdCompilaLtx(pathResult + ".tex");
								if (cmd == null) {
									/** @todo També es podria obrir una finestra */
									frPare.actualitzarBarraEstat(
											"No es pot compilar ni visualitzar automàticament el fitxer Latex generat per no haver-se reconegut el sistema operatiu.",
											true);
								} else {
									logger.finer("Comanda compilació (exec): " + cmd);
									proc = Runtime.getRuntime().exec(cmd, null, dirResult);
									try {
										// error?
										StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(),
												"ERROR (exec)");

										// output?
										StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(),
												"OUTPUT (exec)");

										errorGobbler.start();
										outputGobbler.start();

										err = proc.waitFor();
										if (err == 0) {
											cmd = SO.obtenirCmdVisorLtx(pathResult + ".dvi");
											logger.finer("Comanda visor (exec): " + cmd);
											Runtime.getRuntime().exec(cmd, null, dirResult);
										} else {
											frPare.actualitzarBarraEstat(
													"No s'ha pogut compilar correctament el fitxer Latex generat. (Error "
															+ err + ")", true);
										}
									} catch (InterruptedException exc) {
										frPare.actualitzarBarraEstat(
												"S'ha interrumput el procés de compilació del fitxer Latex ( InterruptedException "
														+ exc.getMessage() + " )", true);
									}
								}
							} catch (IOException exc) {
								frPare.actualitzarBarraEstat("Error al generar i visualitzar el latex ( IOException "
										+ exc.getMessage() + " )", true);
							}
						} catch (Exception e1) {
							e1.printStackTrace();
							frPare.actualitzarBarraEstat("Error al generar i visualitzar el latex ( IOException "
									+ e1.getMessage() + " )", true);
						}
					}
				} else {
					frPare.actualitzarBarraEstat("No ha estat possible generar les regles.", true);
				}

				frPare.remove(this);
				frPare.validate();
				frPare.repaint();
			}
		}

	}

	void jBttnCancel_actionPerformed(ActionEvent e) {
		frPare.remove(this);
		frPare.validate();
		frPare.repaint();
	}

	void jBttnAfegirN_actionPerformed(ActionEvent e) {
		Object[] vars = jListVarsN.getSelectedValues();
		boolean hay_mas_vars = true;
		int n = 0;
		do {
			try {
				listModelSN.addElement(vars[n]);
				listModelVN.removeElement(vars[n]);
				n++;
			} catch (Exception exc) {
				hay_mas_vars = false;
			}

		} while (hay_mas_vars);
		comprobarCheckboxes();

	}

	void jBttnTreureN_actionPerformed(ActionEvent e) {
		Object[] vars = jListSelcN.getSelectedValues();
		boolean hay_mas_vars = true;
		int n = 0;
		do {
			try {
				listModelVN.addElement(vars[n]);
				listModelSN.removeElement(vars[n]);
				n++;
			} catch (Exception exc) {
				hay_mas_vars = false;
			}

		} while (hay_mas_vars);
		comprobarCheckboxes();
	}

	void jBttnAfegirC_actionPerformed(ActionEvent e) {
		Object[] vars = jListVarsC.getSelectedValues();
		boolean hay_mas_vars = true;
		int n = 0;
		do {
			try {
				listModelSC.addElement(vars[n]);
				listModelVC.removeElement(vars[n]);
				n++;
			} catch (Exception exc) {
				hay_mas_vars = false;
			}

		} while (hay_mas_vars);
	}

	void jBttnTreureC_actionPerformed(ActionEvent e) {
		Object[] vars = jListSelcC.getSelectedValues();
		boolean hay_mas_vars = true;
		int n = 0;
		do {
			try {
				listModelVC.addElement(vars[n]);
				listModelSC.removeElement(vars[n]);
				n++;
			} catch (Exception exc) {
				hay_mas_vars = false;
			}

		} while (hay_mas_vars);
	}

	void jBttnAfegirCl_actionPerformed(ActionEvent e) {
		Object[] vars = jListVarsC.getSelectedValues();
		boolean hay_mas_vars = true;
		int n = 0;
		do {
			try {
				listModelSCl.addElement(vars[n]);
				listModelVC.removeElement(vars[n]);
				n++;
			} catch (Exception exc) {
				hay_mas_vars = false;
			}
		} while (hay_mas_vars);
		comprobarCheckboxes();
	}

	void jBttnTreureCl_actionPerformed(ActionEvent e) {
		Object[] vars = jListSelcCl.getSelectedValues();
		boolean hay_mas_vars = true;
		int n = 0;
		do {
			try {
				listModelVC.addElement(vars[n]);
				listModelSCl.removeElement(vars[n]);
				n++;
			} catch (Exception exc) {
				hay_mas_vars = false;
			}
		} while (hay_mas_vars);
		comprobarCheckboxes();
	}

	void jBOpcDescrExt_actionPerformed(ActionEvent e) {
		DlgOpcDescrExtClass dlg = new DlgOpcDescrExtClass(frPare, "Opcions per a la descripció extensional", true,
				opcClass);
		dlg.setLocationRelativeTo(this);
		dlg.show();
	}

	void comprobarCheckboxes() {
		if (listModelSN.isEmpty() || listModelSCl.isEmpty()) {
			jCheckBoxGenerarMKMZ.setEnabled(false);
			jCheckBoxGenerarMKMZ.setSelected(false);
			jCheckBoxRevisat.setEnabled(false);
			jCheckBoxRevisat.setSelected(false);
		} else {
			jCheckBoxGenerarMKMZ.setEnabled(true);

			boolean claseDosModalitats = false;
			String claseActual;
			for (int i = 0; i < listModelSCl.size(); i++) {
				claseActual = (String) listModelSCl.get(i);
				PropCategorica propClase;
				try {
					propClase = (PropCategorica) gestor.obtenirPropietat(claseActual);
					if (propClase.getLlModalitats().size() == 2) {
						claseDosModalitats = true;
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					frPare.actualitzarBarraEstat(e.getMessage(), true);
				}

			}
			jCheckBoxRevisat.setEnabled(claseDosModalitats);
			if (!claseDosModalitats)
				jCheckBoxRevisat.setSelected(false);
		}
	}

}
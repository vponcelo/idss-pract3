package jklass.iu;


import java.io.FileNotFoundException;
import java.text.ParseException;
import java.io.File;
import java.io.IOException;
import java.lang.InterruptedException;





import java.io.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import jklass.nucli.*;

import java.util.logging.*;
import jklass.util.SO;
import jklass.util.Parametres;
import jklass.iu.*;
import java.util.*;


/**
 * <p>Title: Java-Klass</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas,Laia Riera Guerra
 * @version v4
 */

public class FrPrincipal extends JFrame
 implements MenuMatCarregades.IMenuMatCarregades{
  private static Logger logger=Logger.getLogger(FrPrincipal.class.getName());
  FinestraInicial inici;
  private GestorKlass gestor;
  private  ResourceBundle res;
 // FrPrincipal_AboutBox inici;


  private JPanel contentPane;
  private JMenuBar jMenuBar1 = new JMenuBar();
  private JMenu jMenuFile = new JMenu();
  private JMenuItem jMenuFileExit = new JMenuItem();
  private BorderLayout borderLayout1 = new BorderLayout();
  private JPanel panelCentral = new JPanel();
  private JLabel statusBar = new JLabel();
  private TitledBorder titledBorder1;
  private JMenuItem jMenuTancar = new JMenuItem();
  private JMenuItem jMenuAnomDes = new JMenuItem();
  private JMenuItem jMenuExportar = new JMenuItem();
  private JMenuItem jMenuImportar = new JMenuItem();
  private JMenuItem jMenuConf = new JMenuItem();
  private JMenu jMenuDades = new JMenu();
  private JMenu jMenuProcesos = new JMenu();//alejandro;
  private JMenuItem jMenuDefProc = new JMenuItem();
   private JMenuItem jMenuVisProc = new JMenuItem();

  private JMenu jMenuDescrip = new JMenu();
  private JMenuItem jMenuUnivariant = new JMenuItem();
  private JMenuItem jMenuTrivariant = new JMenuItem();
  private JMenuItem jMenuBivariant = new JMenuItem();
  private JMenu jMenuCanvi = new JMenu();
  private JMenuItem jMenuItem1 = new JMenuItem();
  private JMenuItem jMenuCanviArbre = new JMenuItem();
  private JMenuItem jMenuMerge = new JMenuItem();

  private String dadesActual = null;
  private boolean guardada = false;
  private int idDadesActual;
  private Border border1;
  private JMenuItem jMenuClasses = new JMenuItem();
  private JMenuItem jMenuClas = new JMenuItem();
  private JMenuItem jMenuDiv = new JMenuItem();
  private JMenu jMenuClassif = new JMenu();
  private JMenu jMenuInforme = new JMenu();
  private JMenuItem jMenuInfPersonal = new JMenuItem();
  private JMenuItem jMenuInfAuto = new JMenuItem();
  //DISTANCIES***************************************************************
  private JMenuItem jMenuVisu = new JMenuItem();
  //
  private JMenuItem jMenuCIADEC = new JMenuItem();
  private JMenu jMenuConnexio = new JMenu();
  private JMenuItem jMenuColumbus = new JMenuItem();
  private JMenuItem jMenuHelpAbout = new JMenuItem();
  private JMenu jMenuHelp = new JMenu();
  private JMenuItem jMenuMan = new JMenuItem();
  
   private JMenuItem jMenuIdioma = new JMenuItem();////////agregué

  private JMenu jMenuObrir = new JMenu();
  private JMenuItem jMenuODadesReg = new JMenuItem();
  private JMenuItem jMenuODades = new JMenuItem();
  private JMenuItem jMenuResult = new JMenuItem();
  private JMenu jMenuOrdre = new JMenu();
  private JMenuItem jMenuOrdVar = new JMenuItem();
  private JMenuItem jMenuOrdMod = new JMenuItem();
  private JMenu jMenuInterpret = new JMenu();
  private JMenuItem jMitPodarRegles = new JMenuItem();
  private JMenuItem jMitGenerarReglesBoxPlot = new JMenuItem();
  private JMenuItem jMenuOHis = new JMenuItem();
   private JMenuItem jMitConceptJerarq = new JMenuItem(); //Added by Esther
  private JMenuItem jMenuClassifica = new JMenuItem();
  private JMenuItem jMenuClassificaEst = new JMenuItem();
  private JMenu jMenuDendo = new JMenu();
  private JMenuItem jMenuVisual = new JMenuItem();
  private JMenuItem jMenuProd = new JMenuItem();
  private JMenuItem jMenuOLni = new JMenuItem();
  private JMenuItem jMenuTalla = new JMenuItem();
  MenuMatCarregades matrius;

  /**
   * Variables utilitzades per crear els menús de: Coneixement, submenús: EditarBC, Combinar BC, Avaluar BC, Descriptiva BC
   * submenús: Calculadora, Matriu de dades, Obrir Regles, Exportar Regles
   * @author Laia Riera Guerra
   * @version v.0
   * Data: 05/05/2007
   */
  //Coneixement*****************************************************************
  	private JMenu jMenuConeixement = new JMenu();

	private JMenuItem jMenuCombinarBC=new JMenuItem();

	private JMenuItem jMenuEditarBC = new JMenuItem();

	private JMenuItem jMenuAvaluarBC = new JMenuItem();

	private JMenuItem jMenuDescriptivaBC = new JMenuItem();
	
		private JMenuItem jMenuQualitatBC = new JMenuItem();	//Added by Esther


	private JMenuItem jMenuEditarFiltre=new JMenuItem();

	JMenuItem jMenuORegles = new JMenuItem();
	JMenuItem jMenuExpRegles=new JMenuItem();

	private JMenuItem jMenuMatriuDades=new JMenuItem();

	private BaseConeixement bc;
	
	private JMenuItem jMenuRecodificar=new JMenuItem();
	private JMenuItem jMenuDefinirFiltre=new JMenuItem();
	private JMenuItem jMenuDiscretitzador=new JMenuItem();
	private JMenuItem jMenuEliminarPropietats=new JMenuItem();
	private JMenuItem jMenuModifMetadades=new JMenuItem();
	
   private JMenuItem jMenuDefActivas= new JMenuItem(); // alejandro garcia 
	
    private JMenuItem jMenuIntegVar= new JMenuItem(); // alejandro garcia
	 
	  private JMenuItem jMenuIntegMat= new JMenuItem(); // alejandro garcia
 
  private JMenuItem jMenuDefEst= new JMenuItem(); // alejandro garcia 

// DISTANCIES*********************************************************************
  /* Variables utilitzades per crear el menus de: submatrius,distancies,correlacions i missings
  *
  * @author Jose I Mateos
  * @version v.0
  * Data: 25/3/06
  */
  private JMenuItem jMenuSelec = new JMenuItem();
  private JMenuItem jMenuMiss = new JMenuItem();
  private JMenuItem jMenuCorre = new JMenuItem();
  private JMenu jMenuDistancies = new JMenu();
  private JMenuItem jMenuDdirecte = new JMenuItem();
  private JMenuItem jMenuDmatriu = new JMenuItem();
  //


  //ROBER.  Declaració de les variables per inicialitzar el menu AgregarClase y Classificació
  private JMenuItem jMenuAgregarClase = new JMenuItem();
  private File directorioActual = new File("./dades");
  private JMenuItem jMenuLocalitzacio = new JMenuItem();

  //Construct the frame
  public FrPrincipal() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    // es mostra una finestra inicial mentre es carrega la finestra principal
    inici = new FinestraInicial(this,2500);
	
/*	inici=new FrPrincipal_AboutBox(this);
	inici.centerScreen();
	       inici.pack();
    inici.show();*/
	
	  Locale.setDefault(new Locale("ca"));
	 res = ResourceBundle.getBundle("jklass.iu.Resources");
//updateStrings();
	 
    try {
	 updateStrings();
      jbInit();
		updateStrings();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  
  
  
  /**
   * Inicialiització del component
   * @throws Exception
   */
  private void jbInit() throws Exception  {

//    logger = Logger.getLogger("AplKlass");
//    logger.config("Creat el logger AplKlass");
updateStrings();
//laia
jMenuMatriuDades.setEnabled(false);
		jMenuMatriuDades.setMnemonic('M');
		jMenuMatriuDades.setText("Matriu de dades");
		jMenuMatriuDades.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jMenuMatriuDades_actionPerformed(e);
			}
		});

//




	Parametres param = new Parametres("jklass.properties","Propiedades de configuracion de Java-Klass");
    gestor = GestorKlass.obtenirInstanciaUnica(Integer.parseInt(param.obtenirMaximMatrius()));
	//prova
     matrius = new MenuMatCarregades(this); // init FileHistory
    //matrius.initFileMenuHistory();


    //setIconImage(Toolkit.getDefaultToolkit().createImage(FrPrincipal.class.getResource("[Your Icon]")));
    contentPane = (JPanel) this.getContentPane();
    border1 = BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158)),BorderFactory.createEmptyBorder(2,4,2,4));
    this.setSize(new Dimension(690, 300));
    this.setTitle("Java-KLASS");
    jMenuFile.setMnemonic('F');
    jMenuFile.setText(res.getString("Arxiu"));

    jMenuTancar.setMnemonic('T');
    jMenuTancar.setText("Tanca");
    jMenuTancar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuTancar_actionPerformed(e);
      }
    });
    jMenuTancar.setEnabled(false);
    jMenuAnomDes.setMnemonic('A');
    jMenuAnomDes.setText("Anomena i desa");
    jMenuAnomDes.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuAnomDes_actionPerformed(e);
      }
    });
    jMenuAnomDes.setEnabled(false);
    jMenuExportar.setEnabled(true);
    jMenuExportar.setMnemonic('E');
    jMenuExportar.setText("Exporta");
    jMenuExportar.setEnabled(false);
    jMenuFileExit.setMnemonic('S');
    jMenuFileExit.setText("Surt");
    jMenuFileExit.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        jMenuFileExit_actionPerformed(e);
      }
    });
    contentPane.setLayout(borderLayout1);
    statusBar.setBorder(border1);
    statusBar.setDebugGraphicsOptions(0);
    //statusBar.setDisplayedMnemonic('0');
    statusBar.setOpaque(true);
    statusBar.setText(" ");
    jMenuConf.setMnemonic('C');
    jMenuConf.setText("Configura");
    jMenuConf.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuConf_actionPerformed(e);
      }
    });
     /** DISTANCIES*********************************************************************
  	* Creacio del menu de: submatrius,distancies,correlacions i missings
  	*
  	* @author Jose I Mateos
  	* @version v.0
  	* Data: 20/3/06
  	*/
    jMenuSelec.setEnabled(false);
    jMenuSelec.setMnemonic('S');
    jMenuSelec.setText("Selecciona submatriu");
    jMenuSelec.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
        jMenuSelec_actionPerformed(e);
 	   }
	});
	jMenuMiss.setEnabled(false);
    jMenuMiss.setMnemonic('M');
    jMenuMiss.setText("Tractament de mancants");
    jMenuMiss.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
        jMenuMiss_actionPerformed(e);
 	   }
	});
	jMenuCorre.setEnabled(false);
    jMenuCorre.setMnemonic('M');
    jMenuCorre.setText("Matriu de correlacions/covariàncies");
    jMenuCorre.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
        jMenuCorre_actionPerformed(e);
 	   }
	});
    jMenuDistancies.setEnabled(true);
    jMenuDistancies.setMnemonic('N');
    jMenuDdirecte.setEnabled(true);
    jMenuDdirecte.setMnemonic('D');
	jMenuDdirecte.setText("Càlcul Directe");
    jMenuDdirecte.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(ActionEvent e) {
        jMenuDdirecte_actionPerformed(e);
 	   }
	});
    jMenuDmatriu.setEnabled(false);
    jMenuDmatriu.setMnemonic('M');
	jMenuDmatriu.setText("Càlcul Matriu");
    jMenuDmatriu.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(ActionEvent e) {
        jMenuDmatriu_actionPerformed(e);
 	   }
	});
    //
    jMenuDades.setEnabled(false);
    jMenuDades.setMnemonic('D');
	 
	 jMenuProcesos.setEnabled(false); 
    jMenuProcesos.setMnemonic('D');
	 jMenuProcesos.setText("Procesos");
	 	jMenuDefProc.setText("Definir Procesos");
	jMenuDefProc.setEnabled(true);
	jMenuDefProc.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			jMenuDefProc_actionPerformed(e);
		}
	});
	
	jMenuVisProc.setText("Visualizar Procesos");
	jMenuVisProc.setEnabled(true);
	jMenuVisProc.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			jMenuEditarBC_actionPerformed(e);
		}
	});
	

	 
	 
	 
	 
	 
	 
	 
    jMenuDescrip.setEnabled(false);
    jMenuDescrip.setMnemonic('E');
    jMenuUnivariant.setEnabled(false);
    jMenuUnivariant.setDoubleBuffered(false);
    jMenuUnivariant.setMnemonic('U');
    jMenuUnivariant.setText("Univariant");
    jMenuUnivariant.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuUnivariant_actionPerformed(e);
      }
    });
    jMenuBivariant.setEnabled(false);
    jMenuBivariant.setMnemonic('B');
    jMenuBivariant.setText("Bivariant");
    jMenuBivariant.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuBivariant_actionPerformed(e);
      }
    });
    jMenuTrivariant.setEnabled(false);
    jMenuTrivariant.setMnemonic('T');
    jMenuTrivariant.setText("Trivariant");
    jMenuTrivariant.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuTrivariant_actionPerformed(e);
      }
    });
    jMenuCIADEC.setEnabled(true);
    jMenuColumbus.setEnabled(true);
    jMenuCanvi.setMnemonic('C');
    jMenuCanvi.setText("Canvia matriu actual");
	//jMenuCanviArbre.setEnabled(false);
	jMenuCanviArbre.setMnemonic('C');
	jMenuCanviArbre.setText("Canvia dendograma actual");
	jMenuCanviArbre.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jMenuCanviArbre_actionPerformed(e);
	  }
	});
	
	jMenuMerge.setMnemonic('C');
	jMenuMerge.setText("Merge");
	jMenuMerge.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jMenuMerge_actionPerformed(e);
	  }
	});

	

    jMenuClasses.setEnabled(false);
    jMenuClasses.setMnemonic('C');
    jMenuClasses.setText("D. per classes");
    jMenuClasses.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuClasses_actionPerformed(e);
      }
    });
    jMenuClas.setEnabled(false);
    jMenuClas.setMnemonic('I');
    jMenuClas.setText("Integra classificació");
    jMenuClas.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuClas_actionPerformed(e);
      }
    });
	jMenuDiv.setEnabled(false);
	jMenuDiv.setMnemonic('V');
	jMenuDiv.setText("Divideix BDD");
	jMenuDiv.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
		jMenuDiv_actionPerformed(e);
	  }
	});
    jMenuClassif.setEnabled(false);
    jMenuClassif.setMnemonic('C');
    jMenuInterpret.setEnabled(false);
    jMenuInterpret.setMnemonic('t');
	 
	//added Esther
    jMitConceptJerarq.setEnabled(false);
    jMitConceptJerarq.setMnemonic('j');
    jMitConceptJerarq.setText("Conceptualització Jeràrquica (CCEC)");
    jMitConceptJerarq.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e) {
    		jMIConceptJerarq_actionPerformed(e);
    	}
    });
    //
 
	 
    
    jMitGenerarReglesBoxPlot.setEnabled(false);
    jMitGenerarReglesBoxPlot.setMnemonic('g');
    jMitGenerarReglesBoxPlot.setText("Inducció de regles 'boxplot-based'");
    jMitGenerarReglesBoxPlot.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			jMIBoxPlotBased_actionPerformed(e);
		}
	});
    jMitPodarRegles.setEnabled(false);
    jMitPodarRegles.setMnemonic('p');
    jMitPodarRegles.setText("Podar BC");
    jMitPodarRegles.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			jMIPodarRegles_actionPerformed(e);
			
		}
    	
    });
//    jMItDiscret.setEnabled(false);
//    jMItDiscret.setMnemonic('d');
//    jMItDiscret.setText("Discretització 'boxplot-based'");
//    jMItDiscret.addActionListener(new java.awt.event.ActionListener() {
//      public void actionPerformed(ActionEvent e) {
//        jMItDiscret_actionPerformed(e);
//      }
//    });
    jMenuInforme.setEnabled(true);
    jMenuInforme.setMnemonic('I');
    jMenuInfPersonal.setEnabled(false);
    jMenuInfPersonal.setMnemonic('P');
    jMenuInfPersonal.setText("Informe personlitzat");
    jMenuInfAuto.setEnabled(false);
    jMenuInfAuto.setMnemonic('A');
    jMenuInfAuto.setText("Informe automàtic");
    jMenuInfAuto.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuInfAuto_actionPerformed(e);
      }
    });
    //DISTANCIES**********************************************
    jMenuVisu.setEnabled(true);
    jMenuVisu.setMnemonic('V');
    jMenuVisu.setText("Visualitzar Latex");
    jMenuVisu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuVisu_actionPerformed(e);
      }
    });
    //



    //jMenuCIADEC.setEnabled(false);
    jMenuCIADEC.setMnemonic('I');
    jMenuCIADEC.setText("CIADEC");
    jMenuCIADEC.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuCIADEC_actionPerformed(e);
      }
    });
    jMenuConnexio.setMnemonic('S');
   
    //jMenuColumbus.setEnabled(false);
    jMenuColumbus.setMnemonic('O');
    jMenuColumbus.setText("Columbus");
    jMenuColumbus.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuColumbus_actionPerformed(e);
      }
    });
    jMenuHelpAbout.setMnemonic('Q');
    jMenuHelpAbout.setText("Quant al Java-KLASS...");
    jMenuHelpAbout.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        jMenuHelpAbout_actionPerformed(e);
      }
    });
    jMenuHelp.setToolTipText("");
    jMenuHelp.setActionCommand("Ajuda");
    jMenuHelp.setMnemonic('A');
   // jMenuHelp.setText("Ajuda");
    jMenuMan.setMnemonic('M');
    jMenuMan.setText("Manual");
    jMenuMan.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuMan_actionPerformed(e);
      }
    });
    jMenuObrir.setMnemonic('O');
   // jMenuObrir.setText("Obre");
    jMenuODadesReg.setEnabled(false);
    jMenuODadesReg.setMnemonic('A');
   // jMenuODadesReg.setText("Dades amb regles...");
    jMenuODades.setMnemonic('D');
   // jMenuODades.setText("Dades sense regles...");
    jMenuODades.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuODades_actionPerformed(e);
      }
    });
  
    jMenuOHis.setEnabled(false);
    jMenuOHis.setMnemonic('H');
   // jMenuOHis.setText("Història...");
    jMenuOHis.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuOHis_actionPerformed(e);
      }
    });

    //Rober
    jMenuLocalitzacio.setEnabled(true);
    jMenuLocalitzacio.setMnemonic('F');
    jMenuLocalitzacio.setText("Localització de fitxers");
    jMenuLocalitzacio.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuLocalitzacio_actionPerformed(e);
      }
    });


    jMenuResult.setEnabled(false);
    jMenuResult.setMnemonic('L');
    jMenuResult.setText("Localització de resultats");
    jMenuResult.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuResult_actionPerformed(e);
      }
    });
    jMenuOrdre.setEnabled(false);
    jMenuOrdre.setText("Defineix ordenacions");
    jMenuOrdVar.setEnabled(false);
    jMenuOrdVar.setText("de variables");
    jMenuOrdVar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuOrdVar_actionPerformed(e);
      }
    });
    jMenuOrdMod.setEnabled(false);
    jMenuOrdMod.setText("de modalitats o classes");
    jMenuOrdMod.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuOrdMod_actionPerformed(e);
      }
    });
    jMenuClassifica.setEnabled(false);
    jMenuClassifica.setText("Classifica");
    jMenuClassifica.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuClassifica_actionPerformed(e);
      }
    });
	 
 jMenuClassificaEst.setEnabled(true);
    jMenuClassificaEst.setText("Classifica per Stats");
    jMenuClassificaEst.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuClassificaEst_actionPerformed(e);
      }
    });
	 
	 
	 
	  jMenuIdioma.setMnemonic('M');
    jMenuIdioma.setText("Idioma");
    jMenuIdioma.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuIdioma_actionPerformed(e);
      }
    });

	 
    jMenuDendo.setEnabled(false);
    jMenuDendo.setToolTipText("");
    jMenuDendo.setMnemonic('D');
   // jMenuDendo.setText("Dendograma");
    jMenuVisual.setEnabled(false);
    jMenuVisual.setMnemonic('V');
    jMenuVisual.setRolloverEnabled(false);
    jMenuVisual.setText("Visualitza");
    jMenuVisual.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuVisual_actionPerformed(e);
      }
    });
    jMenuProd.setEnabled(false);
    jMenuProd.setMnemonic('P');
    jMenuProd.setText("Produeix classificació...");
    jMenuProd.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuProd_actionPerformed(e);
      }
    });
    jMenuOLni.setEnabled(false);
    jMenuOLni.setMnemonic('D');
   // jMenuOLni.setText("Dendograma...");
    jMenuOLni.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuOLni_actionPerformed(e);
      }
    });
    jMenuTalla.setEnabled(false);
    jMenuTalla.setToolTipText("");
    jMenuTalla.setMnemonic('T');
    jMenuTalla.setText("Talla dendograma");
    jMenuTalla.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuTalla_actionPerformed(e);
      }
    });
    
    /**
     * Bases de Coneixement
     * @author Laia Riera Guerra
     * @version v.4
     * Data: 05/05/2007
     */
//Coneixement******************************************************************
    
    jMenuImportar.setEnabled(true);
    jMenuImportar.setMnemonic('I');
    jMenuImportar.setText("Importa");
    jMenuImportar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	 jMenuImportar_actionPerformed(e);
        }
      });
   
	//jMenuConeixement.setText("Coneixement");
	jMenuConeixement.setEnabled(false);

	jMenuEditarBC.setText("Editar BC");
	jMenuEditarBC.setEnabled(false);
	jMenuEditarBC.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			jMenuEditarBC_actionPerformed(e);
		}
	});

	jMenuCombinarBC.setText("Combinar BC");
	jMenuCombinarBC.setEnabled(false);
	jMenuCombinarBC.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			jMenuCombinarBC_actionPerformed(e);
		}
	});

	jMenuAvaluarBC.setText("Avaluar BC");
	jMenuAvaluarBC.setEnabled(false);
	jMenuAvaluarBC.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			jMenuAvaluarBC_actionPerformed(e);
		}
	});
	jMenuDescriptivaBC.setText("Descriptiva BC");
	jMenuDescriptivaBC.setEnabled(false);
	jMenuDescriptivaBC.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			jMenuDescriptivaBC_actionPerformed(e);
		}
	});
	
		//Added by Esther
	jMenuQualitatBC.setText("Qualitat BC");
	jMenuQualitatBC.setEnabled(false);
	jMenuQualitatBC.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			jMenuQualitatBC_actionPerformed(e);
		}
	});

	

	jMenuEditarFiltre.setText("Calculadora");
	jMenuEditarFiltre.setEnabled(false);
	jMenuEditarFiltre.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			jMenuEditarFiltre_actionPerformed(e);
		}
	});

	jMenuExpRegles.setText("Exporta BC");
	jMenuExpRegles.setEnabled(false);
	jMenuExpRegles.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			jMenuExpRegles_actionPerformed(e);
		}
	});

       	jMenuODadesReg.setEnabled(true);
	jMenuODadesReg.setMnemonic('A');
	jMenuODadesReg.setText("Dades amb regles...");
	jMenuODadesReg.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(ActionEvent e) {
			jMenuODadesRegles_actionPerformed(e);
		}
	});

	jMenuORegles.setEnabled(false);
	jMenuORegles.setMnemonic('R');
	//jMenuORegles.setText("Regles...");
	jMenuORegles.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			jMenuORegles_actionPerformed(e);
		}
	});

	jMenuMatriuDades.setEnabled(false);
	jMenuMatriuDades.setMnemonic('M');
	jMenuMatriuDades.setText("Matriu de dades");
	jMenuMatriuDades.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			jMenuMatriuDades_actionPerformed(e);
		}
	});

	jMenuRecodificar.setEnabled(false);
	jMenuRecodificar.setMnemonic('R');
	jMenuRecodificar.setText("Recodificaale");
	jMenuRecodificar.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			jMenuRecodificar_actionPerformed(e);
		}
	});
	
	jMenuDefinirFiltre.setEnabled(false);
	jMenuDefinirFiltre.setMnemonic('F');
	jMenuDefinirFiltre.setText("Selecciona Submatriu");
	jMenuDefinirFiltre.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			jMenuDefinirFiltre_actionPerformed(e);
		}
	});
	
	jMenuDiscretitzador.setEnabled(false);
	jMenuDiscretitzador.setMnemonic('D');
	jMenuDiscretitzador.setText("Discretitza");
	jMenuDiscretitzador.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			jMenuDiscretitzador_actionPerformed(e);
		}
	});
	
	///////////alejandro
	jMenuDefActivas.setEnabled(false);
	jMenuDefActivas.setMnemonic('D');
	jMenuDefActivas.setText("Definir Actives");
	jMenuDefActivas.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			 jMenuVarActivas_actionPerformed(e);
		}
	});
	
	///////////alejandro
	jMenuDefEst.setEnabled(false);
	jMenuDefEst.setMnemonic('D');
	jMenuDefEst.setText("Definir Estados");
	jMenuDefEst.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			 jMenuDefEst_actionPerformed(e);
		}
	});



	jMenuIntegVar.setEnabled(false);
	jMenuIntegVar.setMnemonic('D');
	jMenuIntegVar.setText("Integra Variable");
	jMenuIntegVar.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			 jMenuIntegVar_actionPerformed(e);
		}
	});


	jMenuIntegMat.setEnabled(false);
	jMenuIntegMat.setMnemonic('D');
	jMenuIntegMat.setText("Integra Matriz");
	jMenuIntegMat.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			 jMenuIntegMat_actionPerformed(e);
		}
	});


	
	
	jMenuEliminarPropietats.setEnabled(false);
	jMenuEliminarPropietats.setMnemonic('P');
	jMenuEliminarPropietats.setText("Elimina propietats");
	jMenuEliminarPropietats.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			jMenuEliminarPropietats_actionPerformed(e);
		}
	});
	
	jMenuModifMetadades.setEnabled(false);
	jMenuModifMetadades.setMnemonic('M');
	jMenuModifMetadades.setText("Edita metadades");
	jMenuModifMetadades.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			jMenuModifMetadades_actionPerformed(e);
		}
	});
//***************************************************************************
    jMenuFile.add(jMenuObrir);
    jMenuFile.add(jMenuTancar);
    jMenuFile.add(jMenuAnomDes);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuImportar);
    jMenuFile.add(jMenuExportar);
    /////////////////////////LAIA////////////////////// 
    jMenuFile.add(jMenuExpRegles);
    /////////////////////////////////////////////////
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuConf);
    jMenuFile.addSeparator();

    //Rober
    jMenuFile.add(jMenuLocalitzacio);
    jMenuFile.add(jMenuResult);
    jMenuFile.addSeparator();




    jMenuFile.add(jMenuFileExit);
	 
	  jMenuHelp.add(jMenuIdioma); /////////////agregué

	 
	 
    // Rober los coloco todos al final
    //jMenuBar1.add(jMenuFile);
    //jMenuBar1.add(jMenuDades);
    //jMenuBar1.add(jMenuDescrip);
    //jMenuBar1.add(jMenuClassif);
//  /////////////////LAIA/////////////////////////////////
   jMenuProcesos.add(jMenuDefProc);
	jMenuProcesos.add(jMenuVisProc);  
    
	jMenuConeixement.add(jMenuEditarBC);
	jMenuConeixement.add(jMenuCombinarBC);
	jMenuConeixement.add(jMenuAvaluarBC);
	jMenuConeixement.add(jMenuDescriptivaBC);
		jMenuConeixement.add(jMenuQualitatBC);	//Added by Esther

	jMenuConeixement.add(jMitPodarRegles);
	
	// /////////////////////////////////////////////////////
    this.setJMenuBar(jMenuBar1);
    contentPane.add(panelCentral,  BorderLayout.CENTER);
    contentPane.remove(panelCentral);
    contentPane.add(statusBar,  BorderLayout.SOUTH);
    // DISTANCIES*********************************************************************
    /* Posar el Menu de submatrius dins el menu principal
    * @author Jose I Mateos
    * @version v.0
    * Data: 7/5/06
    */
	jMenuDades.add(jMenuMatriuDades);
	jMenuDades.addSeparator();
    jMenuDades.add(jMenuCanvi);
	jMenuDades.add(jMenuCanviArbre);
	jMenuDades.add(jMenuMerge);
    /////////////////////////LAIA///////////////////////////
    jMenuDades.addSeparator();
    jMenuDades.add(jMenuModifMetadades);
    jMenuDades.add(jMenuEliminarPropietats);
    jMenuDades.add(jMenuDefinirFiltre);
    //////////////////////////////////////////////////////
	jMenuDades.add(jMenuDiv);
    jMenuDades.addSeparator();
    jMenuDades.add(jMenuMiss);
    jMenuDades.addSeparator();
    //
    jMenuDades.add(jMenuClas);
    /*
     * Posar els menús Recodificar,Discretitzar,Calculadora i Matriu de dades dins el menú Dades
     * @author Laia Riera Guerra
     * @version v.4
     * Data: 23/06/2007
     */
	 	jMenuDades.add(jMenuIntegVar);//alejandro garcia
		jMenuDades.add(jMenuIntegMat);//alejandro garcia

 jMenuDades.addSeparator();//alejandro garcia
	jMenuDades.add(jMenuEditarFiltre);
	jMenuDades.add(jMenuRecodificar);
	jMenuDades.add(jMenuDiscretitzador);
    jMenuDades.addSeparator();
    jMenuDades.add(jMenuOrdre);
    jMenuDades.addSeparator();//alejandro garcia
	jMenuDades.add(jMenuDefActivas);//alejandro garcia
	jMenuDades.add(jMenuDefEst);//alejandro garcia



    jMenuCanvi.add(jMenuItem1);
    jMenuDescrip.add(jMenuUnivariant);
    // DISTANCIES*********************************************************************
    /* Posar el Menu de matriu de correlacions dins el menu descriptiva
    * @author Jose I Mateos
    * @version v.0
    * Data: 7/5/06
    */
	 
	 
	 
	 
    jMenuDescrip.addSeparator();
    jMenuDescrip.add(jMenuBivariant);
    jMenuDescrip.add(jMenuCorre);
    jMenuDescrip.addSeparator();
    jMenuDescrip.add(jMenuTrivariant);
    jMenuDescrip.addSeparator();
    jMenuDescrip.add(jMenuClasses);
     // DISTANCIES*********************************************************************
    /* Posar el Menu Distancies dins el menu principal
    * @author Jose I Mateos
    * @version v.0
    * Data: 7/5/06
     */

    // Rober los coloco todos al final
    //jMenuBar1.add(jMenuDistancies);
    jMenuDistancies.add(jMenuDdirecte);
    jMenuDistancies.add(jMenuDmatriu);
    //

    //  Rober lo pongo al final
    //jMenuClassif.add(jMenuClassifica);
    //jMenuClassif.add(jMenuDendo);

    //jMenuInterpret.add(jMItDiscret);
    jMenuInterpret.add(jMitGenerarReglesBoxPlot);
	 
	  //add Esther
    jMenuInterpret.add(jMitConceptJerarq);


    // Rober los coloco todos al final
    //jMenuBar1.add(jMenuInterpret);

    jMenuInforme.add(jMenuInfAuto);
    jMenuInforme.add(jMenuInfPersonal);
    jMenuInforme.add(jMenuVisu);

    // Rober los coloco todos al final
    //jMenuBar1.add(jMenuInforme);
    //jMenuBar1.add(jMenuConnexio);
    //jMenuBar1.add(jMenuHelp);

    jMenuConnexio.add(jMenuCIADEC);
    jMenuConnexio.add(jMenuColumbus);
    jMenuHelp.add(jMenuMan);
    jMenuHelp.add(jMenuHelpAbout);
    jMenuObrir.add(jMenuODades);
    jMenuObrir.add(jMenuODadesReg);
///////////////////////////////LAIA///////////////////////////////
	jMenuObrir.add(jMenuORegles);	
	//////////////////////////////////////////////////////////// 
    jMenuObrir.add(jMenuOLni);
    jMenuObrir.add(jMenuOHis);
    jMenuOrdre.add(jMenuOrdVar);
    jMenuOrdre.add(jMenuOrdMod);
    jMenuDendo.add(jMenuVisual);
    jMenuDendo.add(jMenuTalla);
    jMenuDendo.add(jMenuProd);


    //ROBER  declaración del menú AgregarClase en el grupo correspondiente
    jMenuAgregarClase.setEnabled(false);
    jMenuAgregarClase.setText("Agregar classe");
    jMenuAgregarClase.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuAgregarClase_actionPerformed(e);
      }
    });

    jMenuClassif.add(jMenuAgregarClase);
    jMenuClassif.add(jMenuClassifica);
	  jMenuClassif.add(jMenuClassificaEst);
    jMenuClassif.add(jMenuDendo);

    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenuDades);
	 jMenuBar1.add(jMenuProcesos);
    jMenuBar1.add(jMenuConeixement);
    jMenuBar1.add(jMenuDescrip);
    jMenuBar1.add(jMenuClassif);
    jMenuBar1.add(jMenuInterpret);
    jMenuBar1.add(jMenuDistancies);
    jMenuBar1.add(jMenuInforme);
    jMenuBar1.add(jMenuConnexio);
    jMenuBar1.add(jMenuHelp);

  }
/**Actualitza el titol de la finestra
* @param str es el titol que es vol mostrar

   */
  public void actualitzarTitolFinestra(String str){
    if (( str == null ) || (str == "")){
      this.setTitle("Java-KLASS");
    }
    else{
      this.setTitle("Java-KLASS [" + str + "]");
      dadesActual = str;
    }
  }
  
  
public void updateStrings()
   {    
      jMenuFile.setText(res.getString("Arxiu"));		
		jMenuDistancies.setText(res.getString("Distàncies"));		
     jMenuDades.setText(res.getString("Dades"));
	  jMenuConeixement.setText(res.getString("Coneixement"));
	   jMenuDescrip.setText(res.getString("Descriptiva"));
		 jMenuClassif.setText(res.getString("Classificació"));
		 jMenuInterpret.setText(res.getString("Interpretació"));
		jMenuDistancies.setText(res.getString("Distàncies"));
		 jMenuInforme.setText(res.getString("Informes"));
		 jMenuConnexio.setText(res.getString("Sàtel·lits"));
	    jMenuHelp.setText(res.getString("Ajuda"));	 
		jMenuObrir.setText(res.getString("Obre")); 
		jMenuODadesReg.setText(res.getString("Dades_amb_regles"));
		jMenuODades.setText(res.getString("Dades_sense_regles..."));
		jMenuORegles.setText(res.getString("Regles..."));
		jMenuDendo.setText(res.getString("Dendograma..."));
		jMenuOHis.setText(res.getString("Història..."));
		jMenuOLni.setText(res.getString("Dendograma..."));
		
	}  
  
  
  
  
  

  /**
   * Retorna el nom de la matriu de dades amb la que s'està treballant actualment
   * @return nom de la matriu de dades
   */
  public String obtenirNomDades(){
    return dadesActual;
  }

   /**
   * Acció que es realitza per l'opció del menú Arxiu | Sortir
   * @param e event del menú
   */
  public void jMenuFileExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }

  /**
   * Acció que es realitza per l'opció del menú Ajuda | Quant al Java-KLASS
   * @param e event del menú
   */
  public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
    FrPrincipal_AboutBox dlg = new FrPrincipal_AboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.pack();
    dlg.show();
  }

  /**
   * Permet habilitar o deshabilitar diverses opcions dels menus en funció dels dos parametres
   * @param habilita si true s'habiliten les opcions general dels menus
   * @param arbres si true s'habiliten les opcions dels menus relacionades amb els arbres (dendogrames)
   */
  public void habilitarOpcionsMenu(boolean habilita, boolean arbres){
    jMenuTancar.setEnabled(habilita);
    jMenuAnomDes.setEnabled(habilita);
    //jMenuExportar.setEnabled(habilita);
    jMenuResult.setEnabled(habilita);
    jMenuOLni.setEnabled(habilita);

    jMenuDades.setEnabled(habilita);
	 jMenuProcesos.setEnabled(habilita);
	jMenuDiv.setEnabled(habilita);
    jMenuSelec.setEnabled(habilita);

    jMenuClas.setEnabled(habilita);
    jMenuOrdre.setEnabled(habilita);
    jMenuOrdVar.setEnabled(habilita);
    jMenuOrdMod.setEnabled(habilita);

    jMenuDescrip.setEnabled(habilita);
    jMenuUnivariant.setEnabled(habilita);
    jMenuBivariant.setEnabled(habilita);
    jMenuTrivariant.setEnabled(habilita);
    jMenuClasses.setEnabled(habilita);
    jMenuClassif.setEnabled(habilita);
    //jMenuClassifica.setEnabled(true);
    jMenuDendo.setEnabled(habilita);
    jMenuProd.setEnabled(habilita);
    jMenuInterpret.setEnabled(habilita);
	   jMitConceptJerarq.setEnabled(habilita);		//add Esther

    //jMItDiscret.setEnabled(habilita);
    jMitGenerarReglesBoxPlot.setEnabled(habilita);
    jMitPodarRegles.setEnabled(habilita);
    jMenuInforme.setEnabled(habilita);
    jMenuInfAuto.setEnabled(habilita);
    //jMenuInfPersonal.setEnabled(true);

    // s'han d'habilitar les opcions relacionades amb arbres de classificació o dendogrames
    jMenuVisual.setEnabled(arbres);
    jMenuTalla.setEnabled(arbres);



    // DISTANCIES*********************************************************************
    /* Actualitzar el menu una vegada s'ha carregat alguna matriu
    * @author Jose I Mateos
    * @version v.0
    * Data: 15/4/06
    */
    jMenuDmatriu.setEnabled(habilita);
    jMenuSelec.setEnabled(habilita);
    jMenuMiss.setEnabled(habilita);
    jMenuCorre.setEnabled(habilita);
     //

     //ROBER.  Actualització del menu AgregarClase y Classificació una vegada s'ha carregat alguna matriu
     jMenuClassifica.setEnabled(habilita);
     jMenuAgregarClase.setEnabled(habilita);
     jMenuOHis.setEnabled(habilita);
     
     ///////////////////////LAIA/////////////////////////////
     jMenuORegles.setEnabled(habilita);
 	jMenuEditarBC.setEnabled(habilita);
 	jMenuConeixement.setEnabled(habilita);
 	jMenuMatriuDades.setEnabled(habilita);
 	jMenuRecodificar.setEnabled(habilita);
 	jMenuExpRegles.setEnabled(habilita);
 	jMenuDefinirFiltre.setEnabled(habilita);
 	jMenuDiscretitzador.setEnabled(habilita);
 	jMenuEliminarPropietats.setEnabled(habilita);
 	jMenuEditarFiltre.setEnabled(habilita);
 	jMenuImportar.setEnabled(habilita);
 	jMenuAvaluarBC.setEnabled(habilita);
 	jMenuDescriptivaBC.setEnabled(habilita);
		jMenuQualitatBC.setEnabled(habilita);		//added by Esther
 	jMenuModifMetadades.setEnabled(habilita);

	//////////////////alejandro///////////
  	jMenuDefActivas.setEnabled(habilita);
jMenuIntegVar.setEnabled(habilita);
jMenuIntegMat.setEnabled(habilita);
	jMenuDefEst.setEnabled(habilita);
  }
  
  
  
  
  private void jMenuCanviArbre_actionPerformed(ActionEvent e) {
	actualitzarBarraEstat(" ", false);
	contentPane.remove(panelCentral);

	DlgCanviArbre dlg = new DlgCanviArbre(this, gestor);
	dlg.setLocationRelativeTo(this);
	dlg.show();
  }
  
  
  private void jMenuMerge_actionPerformed(ActionEvent e) {
	actualitzarBarraEstat(" ", false);
	contentPane.remove(panelCentral);

	DlgMerge dlg = new DlgMerge(this, gestor);
	dlg.setLocationRelativeTo(this);
	dlg.show();
  }
 

/**Actualitza la barra d'estst
* @param str es el misatje que es vol mostrar
* @param err es cert si es un error i fals si no ho es

   */
  public void actualitzarBarraEstat(String str, boolean err){
    if (err) {
      statusBar.setForeground(Color.WHITE);
      statusBar.setBackground(Color.RED);
    } else {
      statusBar.setForeground(Color.BLACK);
      statusBar.setBackground(this.getBackground());
    }
    statusBar.setText(str);
  }

  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      jMenuFileExit_actionPerformed(null);
    }
  }

  void jMenuODades_actionPerformed(ActionEvent e) {
	int n = JOptionPane.YES_OPTION;
	jMenuMatriuDades.setEnabled(true);
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);

    JFileChooser chooser = new JFileChooser();
    int id = -1; //identificador intern de la matriu de dades (ha de ser positiu)
    String matriu;

    // definimos todo lo necesario para el seleccionador de ficheros
    //Rober
    //chooser.setCurrentDirectory(new File("./dades"));
    chooser.setCurrentDirectory(obtenirDirActual ());

    chooser.setDialogTitle("Càrrega de dades sense regles");
    ExtensionFileFilter filter = new ExtensionFileFilter();
    filter.addExtension("dat");
    filter.addExtension("pro");
    filter.addExtension("obj");
    filter.setDescription("Fitxers de dades JavaKLASS");
    chooser.setFileFilter(filter);

    int returnVal = chooser.showOpenDialog(FrPrincipal.this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      // Eliminem l'extensió del fitxer per obtenir el nom de la matriu de dades
      // (conjunt de fitxers que la representen seran aquest nom i l'extensió .pro, .obj i .dat)
      String path = file.getPath();
      int i = path.lastIndexOf('.');
      // Per si s'introdueix el nom del fitxer sense extensió
      i = (i==-1)? path.length(): i;
      matriu = path.substring(0,i);
      try {
        actualitzarBarraEstat("Carregant la matriu de dades " + matriu
                                     + "...", false);

        if(matrius.existeix(matriu)){
	        n = JOptionPane.showConfirmDialog(
	       		this, "Ja hi ha una matriu en el sistema amb aquest nom que es perdra. Segur que vols continuar?",
                "Càrrega de dades sense regles",
                JOptionPane.YES_NO_OPTION);
         }
         if (n == JOptionPane.YES_OPTION){
	        id = gestor.carregarMatriu(matriu);
        	if (id != -1) {
          		actualitzarMatriuGuardada(false);
          		actualitzarTitolFinestra(matriu);
          		habilitarOpcionsMenu(true, false);
          		actualitzarBarraEstat("Les dades s'han carregat.", false);
          		matrius.insertarMatriu(matriu, id); // hook into FileHistory class
        	}
        	else {
          		actualitzarBarraEstat("Les dades no s'han carregat.", true);
        	}
    	}
      }
      catch (Exception ex) {
        actualitzarBarraEstat("Les dades no s'han carregat. (" +
                                     ex.getMessage() + ")", true);
      }
    }
    validate();
    repaint();
  }


  //Rober

  void jMenuLocalitzacio_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);

    int n = JOptionPane.showConfirmDialog(
                     this, "Actualment els fitxers de l'aplicació es carreguen del directori: \n" +
                     obtenirDirActual() + "\n ¿vols canviar el directori de fitxers?",
                     "Localització dels fitxers",
                     JOptionPane.YES_NO_OPTION);


    if (n == JOptionPane.YES_OPTION) {
      JFileChooser chooser = new JFileChooser();
      //Rober
      //chooser.setCurrentDirectory(new File("./dades"));
      chooser.setCurrentDirectory(obtenirDirActual ());
      chooser.setDialogTitle("Localització dels fitxers");
      chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      int returnVal = chooser.showOpenDialog(FrPrincipal.this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = chooser.getSelectedFile();
        String path = file.getPath();
        //Rober
        this.modifDirActual ( file );
        if (true) {
          actualitzarBarraEstat("S'ha actualitzat el directori de fitxers associat a: " + path, false);
        }
        else {
          actualitzarBarraEstat("No ha estat possible actualitzar el directori de fitxers associat a: " + path, true);
        }
      }
    }
  }

  void jMenuResult_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);

    String dir = gestor.obtenirDirResultats();
    int n = JOptionPane.showConfirmDialog(
                            this, "Actualment els resultats de l'aplicació s'escriuen al directori: \n" +
                            new File(dir).getAbsolutePath() + "\n ¿vols canviar el directori de resultats?",
                            "Localització de resultats",
                            JOptionPane.YES_NO_OPTION);

    if (n == JOptionPane.YES_OPTION) {
      JFileChooser chooser = new JFileChooser();
      // definimos todo lo necesario para el seleccionador de ficheros
      chooser.setCurrentDirectory(new File(dir));
      chooser.setDialogTitle("Localització de resultats");
      chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      int returnVal = chooser.showOpenDialog(FrPrincipal.this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = chooser.getSelectedFile();
        String path = file.getPath();
        boolean ok = gestor.actualitzarDirResultats(path);
        if (ok) {
          actualitzarBarraEstat(
              "S'ha actualitzat el directori de resultats associat a: " + path, false);
        }
        else {
          actualitzarBarraEstat(
              "No ha estat possible actualitzat el directori de resultats associat a: " + path, true);
        }
      }
    }
  }

  void jMenuConf_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    panelCentral = new PanelConfiguracio(this);
    contentPane.add(panelCentral,  BorderLayout.CENTER);
    validate();
    repaint();
	  res = ResourceBundle.getBundle("jklass.iu.Resources");
		 System.out.println("ente a menu conf action performed");
	//	while (actualice==false){;
	//	}
		       updateStrings();
 validate();
    repaint();

	 
	 
  }

  void jMenuUnivariant_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    panelCentral = new PanelUnivariant(this,gestor);
    contentPane.add(panelCentral,  BorderLayout.CENTER);
    validate();
    repaint();
  }

  void jMenuBivariant_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    panelCentral = new PanelBivariant(this,gestor);
    contentPane.add(panelCentral,  BorderLayout.CENTER);
    validate();
    repaint();
  }

  void jMenuTrivariant_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    panelCentral = new PanelTrivariant(this,gestor);
    contentPane.add(panelCentral,  BorderLayout.CENTER);
    validate();
    repaint();

  }

  void jMenuClasses_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    panelCentral = new PanelClasses(this,gestor);
    contentPane.add(panelCentral,  BorderLayout.CENTER);
    validate();
    repaint();
  }

  void jMenuInfAuto_actionPerformed(ActionEvent e) {
    String cmd = new String();
    Process proc;
    int err;
    boolean ok=false;

    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    validate();
    repaint();
    try {
      ok = gestor.generarInformeAuto();
    }
    catch (Exception ex) {
      actualitzarBarraEstat(
    "Hi han hagut problemes per generar l'informe automàtic. (" + ex.getMessage() + ")", true);
    }

    if (ok) {
      String nomRes = new File(dadesActual).getName();
      actualitzarBarraEstat(
          "S'ha realitzat l'informe automàtic correctament. (Resultats al fitxer: " +
          nomRes + "InfAuto.tex)", false);

      try {
//      cmd = SO.obtenirCmdCompilaLtxPdf(".\\resultats\\" + dadesActual + "InfAuto.tex", ".\\resultats\\");
        String pathResult = gestor.obtenirDirResultats();
        File dirResult = new File(pathResult);
        pathResult = pathResult + nomRes + "InfAuto";

        cmd = SO.obtenirCmdCompilaLtxPdf(pathResult + ".tex");
        if (cmd == null) {
          actualitzarBarraEstat(
              "No es pot compilar ni visualitzar automàticament el fitxer Latex generat per no haver-se reconegut el sistema operatiu.", true);
        }
        else {
          logger.finer("Comanda exec: " + cmd);
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
//            cmd = SO.obtenirCmdVisorPdf(".\\resultats\\" + dadesActual + "InfAuto.pdf");
              cmd = SO.obtenirCmdVisorPdf(pathResult + ".pdf");
              Runtime.getRuntime().exec(cmd, null, dirResult);
            }
            else {
              actualitzarBarraEstat(
                  "No s'ha pogut compilar correctament el fitxer Latex generat.", true);
            }
          }
          catch (InterruptedException exc) {
            actualitzarBarraEstat("S'ha interrumput el procés de compilació del fitxer Latex ( InterruptedException " +
                                  exc.getMessage() + " )", true);
          }
        }
      }
      catch (IOException exc) {
        actualitzarBarraEstat(
            "Error al generar i visualitzar el latex ( IOException " +
            exc.getMessage() + " )", true);
      }
    }
    validate();
    repaint();

  }

  void jMenuCIADEC_actionPerformed(ActionEvent e) {
    String cmd;
    Process proc;

    actualitzarBarraEstat(" ", false);
    try {
      cmd = SO.obtenirCmdCIADEC();
      logger.finer("Comanda exec: " + cmd);
      proc = Runtime.getRuntime().exec(cmd, null, null);
    } catch (IOException exc) {
      actualitzarBarraEstat("Error al arrancar Ciadec ( Exception " + exc.getMessage() + " )", true);
    }
    contentPane.remove(panelCentral);
    validate();
    repaint();
  }

  void jMenuColumbus_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    try {
      synchronized(this){
          //FUNCIONA TB pero no necesitamos ventana linea comando: Runtime.getRuntime().exec("java -cp satelits\\BEAN.jar BEAN");
          Runtime.getRuntime().exec("javaw -jar satelits\\Columbus\\BEAN.jar");
     }
    } catch (Exception exc) {
      actualitzarBarraEstat("Error al arrancar Columbus ( Exception " + exc.getMessage() + " )", true);
    }

  }

  void actualitzarMatriuGuardada(boolean guardada){
    this.guardada = guardada;
  }
  
 /* 

  void jMenuMan_actionPerformed(ActionEvent e) {
    String cmd;

    cmd = SO.obtenirCmdVisorLtx("manuals.dvi");
    try {
      Runtime.getRuntime().exec(cmd, null, new File("./docs"));
    }
    catch (IOException exc) {
      actualitzarBarraEstat(
          "Error al generar i visualitzar el latex ( IOException " +
          exc.getMessage() + " )", true);
    }
  }
  
  */
  
  void jMenuMan_actionPerformed(ActionEvent e) {
   // String cmd;
		String wd = System.getProperty("user.dir");
	//System.out.println("USERDIR"+ wd);
  
  String camino = wd + "\\manuals\\usuari\\usuari.pdf";
  
	//System.out.println("CAMINOO"+ camino);

   try                                     
        {
    //  Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "c:\\usuari.pdf");
	Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + camino);  
	//Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "c:\\Documents and Settings\\alejandro\\Escritorio\\JavaKlassv62developer\\manuals\\usuari\\usuari.pdf");
				
        }
		  
		  catch (IOException exc) {
      actualitzarBarraEstat(
          "Error al generar i visualitzar el latex ( IOException " +
          exc.getMessage() + " )", true);
    }
    } 
   
  
 
 void jMenuIntegVar_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    panelCentral = new PanelIntegraVar(this,gestor);
    contentPane.add(panelCentral,  BorderLayout.CENTER);
    validate();
    repaint();
  }
  
  //Integrar matriz
 void jMenuIntegMat_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    panelCentral = new PanelIntegraMat(this,gestor);
    contentPane.add(panelCentral,  BorderLayout.CENTER);
    validate();
    repaint();
  }
  

   
  

  void jMenuClas_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    panelCentral = new PanelIntegraClas(this,gestor);
    contentPane.add(panelCentral,  BorderLayout.CENTER);
    validate();
    repaint();
  }

  void jMenuDiv_actionPerformed(ActionEvent e) {
	actualitzarBarraEstat(" ", false);
	contentPane.remove(panelCentral);
	panelCentral = new PanelDivideixBDD(this,gestor);
	contentPane.add(panelCentral,  BorderLayout.CENTER);
	validate();
	repaint();
  }

  void jMenuProd_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    JFileChooser chooser = new JFileChooser();

    // definimos todo lo necesario para el seleccionador de ficheros
    //Rober
    //chooser.setCurrentDirectory(new File("./dades"));
    chooser.setCurrentDirectory(obtenirDirActual ());

    chooser.setDialogTitle("Produeix classificació");
    ExtensionFileFilter filter = new ExtensionFileFilter();
    filter.addExtension("par");
    filter.setDescription("Fitxer de partició (.par)");
    chooser.setFileFilter(filter);
    int returnVal = chooser.showOpenDialog(FrPrincipal.this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      String path = file.getPath();
      int i = path.lastIndexOf('.');
      String matriu = path.substring(0, i);
      if (matriu != null) {
        try {
          int id = gestor.transformarParticio(matriu);
          if (id != -1) {
            actualitzarBarraEstat(
                "El fitxer de classificació (.cls) s'ha generat.", false);
          }
          else {
            actualitzarBarraEstat(
                "El fitxer de classificació (.cls) no s'ha generat.", true);
          }
        }
        catch (Exception ex) {
          actualitzarBarraEstat(
              "El fitxer de classificació (.cls) no s'ha generat. [" +
              ex.getMessage() + "]", true);
        }
      }
    }

    validate();
    repaint();
  }


//ROBER.  Acció que llença el menú Classificació i obre la finestra corresponent.
  void jMenuClassifica_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    panelCentral = new PanelClassifica(this,gestor);
    //habilitarOpcionsMenu(true, true);
    contentPane.add(panelCentral,  BorderLayout.CENTER);
    validate();
    repaint();
  }
  
  void jMenuClassificaEst_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    panelCentral = new PanelClassificaEst(this,gestor);
    //habilitarOpcionsMenu(true, true);
    contentPane.add(panelCentral,  BorderLayout.CENTER);
    validate();
    repaint();
  }
 
  
  

//ROBER.  Acció que llença el menú AgregarClase i obre la finestra corresponent.
  void jMenuAgregarClase_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    panelCentral = new PanelAgregarClase(this,gestor);
    contentPane.add(panelCentral,  BorderLayout.CENTER);
    validate();
    repaint();
 }

  void jMenuOrdVar_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    panelCentral = new PanelOrdreVar(this,gestor);
    contentPane.add(panelCentral,  BorderLayout.CENTER);
    validate();
    repaint();
  }
  
//alejandro
void jMenuVarActivas_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    panelCentral = new PanelDefActivas(this,gestor);
    contentPane.add(panelCentral,  BorderLayout.CENTER);
    validate();
    repaint();
  }
  
 void jMenuDefEst_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    panelCentral = new PanelRecodificarEst(this,gestor);
    contentPane.add(panelCentral,  BorderLayout.CENTER);
    validate();
    repaint();
  }
 
 void jMenuDefProc_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    panelCentral = new PanelRecodificarBC2(this,gestor);
    contentPane.add(panelCentral,  BorderLayout.CENTER);
    validate();
    repaint();
  }
 
  
  

   void jMenuIdioma_actionPerformed(ActionEvent e) {
 // actualice = false;
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    //panelCentrald = new Selector(this);
	 	DlgOpcIdioma dlg = new DlgOpcIdioma(this, "Seleccioni l'idioma",true,gestor);
      dlg.setLocationRelativeTo(this);
   	dlg.setVisible(true);

	// boolean termino = false;
	 //panelCentral = new PanelConfiguracio(this);
	 //JPanel panel = getMyPanel();
    //JDialog dialog = new JDialog();
   // dialog.setContentPane(panel);
   // panelCentrald.setContentPane(panelCentral);
	// contentPane = (JPanel) this.getContentPane();
	//updateStrings();
	
  // contentPane.add(panelCentrald,  BorderLayout.CENTER);
    validate();
    repaint();
	 res = ResourceBundle.getBundle("jklass.iu.Resources");
		 System.out.println("ente a menu conf action performed");
	//	while (actualice==false){;
	//	}
		       updateStrings();
				 
	//	System.out.println("Imprimo actualice" +actualice);  
		
  }
  

  
  
  

  void jMenuOrdMod_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    panelCentral = new PanelOrdreMod(this,gestor);
    contentPane.add(panelCentral,  BorderLayout.CENTER);
    validate();
    repaint();

  }

  void jMenuAnomDes_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);

    JFileChooser chooser = new JFileChooser();
    int returnVal = JFileChooser.ERROR_OPTION;
    boolean fi = false;
    int id = -1; //identificador intern de la matriu de dades (ha de ser positiu)
    String matriu = "" ;

    // definimos todo lo necesario para el seleccionador de ficheros
    //Rober
    //chooser.setCurrentDirectory(new File("./dades"));
    chooser.setCurrentDirectory(obtenirDirActual ());

    chooser.setDialogTitle("Anomena i desa");
    ExtensionFileFilter filter = new ExtensionFileFilter();
    filter.addExtension("dat");
    filter.addExtension("pro");
    filter.addExtension("obj");
    filter.setDescription("Fitxers de dades JavaKLASS");
    chooser.setFileFilter(filter);

    while (!fi) {
      returnVal = chooser.showSaveDialog(FrPrincipal.this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = chooser.getSelectedFile();
        // Eliminem l'extensió del fitxer per obtenir el nom de la matriu de dades
        // (conjunt de fitxers que la representen seran aquest nom i l'extensió .pro, .obj i .dat)
        String path = file.getPath();
        int i = path.lastIndexOf('.');
        // Per si s'introdueix el nom del fitxer sense extensió
        i = (i == -1) ? path.length() : i;
        matriu = path.substring(0, i);
        // Es comprova si ja existeix un grup de fitxers amb aquest nom i
        // s'avisa a l'usuari
        if ( (new File(matriu + ".pro").exists()) ||
            new File(matriu + ".obj").exists() ||
            new File(matriu + ".dat").exists()) {
          int n = JOptionPane.showConfirmDialog(this,
                                                "El conjunt de fitxers " +
                                                matriu +
                                                " ja existeix, \n" +
              "¿estàs segur que vols sobreescriure els fitxers?",
              "Anomena i desa",
              JOptionPane.YES_NO_OPTION);
          if (n == JOptionPane.YES_OPTION) {
            fi = true;
          }
        }
        else {
          fi = true;
        }
      }
      else if (returnVal == JFileChooser.CANCEL_OPTION) {
        fi = true;
      }
    }
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      try {
        actualitzarBarraEstat("Desant la matriu de dades " + matriu
                              + "...", false);
        id = gestor.desarMatriu(matriu);
        if (id != -1) {
          actualitzarTitolFinestra(matriu);
          actualitzarBarraEstat("Les dades s'han desat.", false);
          guardada = true;
        }
        else {
          actualitzarBarraEstat("Les dades no s'han desat.", true);
        }
      }
      catch (Exception ex) {
        actualitzarBarraEstat("Les dades no s'han desat. " +
                              ex.getMessage(), true);
      }
    }
    validate();
    repaint();

  }

  void jMenuTancar_actionPerformed(ActionEvent e) {
    boolean ok = false;
    String matriu = "" ;

    if (!guardada) {
      int n = JOptionPane.showConfirmDialog(this,
          "La matriu de dades no ha estat desada. \n" +
          "¿estàs segur que vols tancarla?",
          "Tanca",
          JOptionPane.YES_NO_OPTION);
      if (n == JOptionPane.YES_OPTION) {
        ok = true;
      }
    } else{
      ok = true;
    }
    if (ok) {
      try {
        actualitzarBarraEstat("Tancant la matriu de dades " + matriu
                              + "...", false);
        matriu = gestor.tancaMatriuActual();
        matrius.eliminarMatriu(obtenirNomDades(), matriu);
        actualitzarTitolFinestra(matriu);
        actualitzarBarraEstat("La matriu de dades s'han tancat.", false);
        guardada = false;
        if (matriu ==""){ //no queda cap matriu carregada al sistema
          habilitarOpcionsMenu(false, false);
        } else {
          habilitarOpcionsMenu(true, false);
        }
      }
      catch (Exception ex) {
        actualitzarBarraEstat("La matriu de dades no s'ha tancat. " +
                              ex.getMessage(), true);
      }
    }
    contentPane.remove(panelCentral);
	validate();
	repaint();
	this.setSize(new Dimension(550, 300));
  }

  void jMItDiscret_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    panelCentral = new PanelDiscret(this,gestor);
    contentPane.add(panelCentral,  BorderLayout.CENTER);
    validate();
    repaint();
  }

  void jMenuOHis_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);

    JFileChooser chooser = new JFileChooser();
    int id = -1; //identificador intern de la matriu de dades (ha de ser positiu)
    String matriu;

    // definimos todo lo necesario para el seleccionador de ficheros
    //Rober
    //chooser.setCurrentDirectory(new File("./dades"));
    chooser.setCurrentDirectory(obtenirDirActual ());

    chooser.setDialogTitle("Càrrega d'un fitxer d'història");
    ExtensionFileFilter filter = new ExtensionFileFilter();
    filter.addExtension("his");
    filter.setDescription("Fitxers d'història JavaKLASS");
    chooser.setFileFilter(filter);

    int returnVal = chooser.showOpenDialog(FrPrincipal.this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      // Eliminem l'extensió del fitxer per obtenir el nom de la matriu de dades
      // (conjunt de fitxers que la representen seran aquest nom i l'extensió .pro, .obj i .dat)
      String path = file.getPath();
      int i = path.lastIndexOf('.');
      // Per si s'introdueix el nom del fitxer sense extensió
      i = (i==-1)? path.length(): i;
      matriu = path.substring(0,i);
      try {
        actualitzarBarraEstat("Carregant la història " + matriu
                                     + "...", false);
        id = gestor.carregarHistoria(matriu);
        if (id != -1) {
         /* Ara de moment només es carrega l'arbre de classificació
           Quan també es carregui una matriu de dades caldrà fer:
          actualitzarMatriuGuardada(false);
          actualitzarTitolFinestra(matriu);
          matrius.insertPathname(matriu, id);
         */
          habilitarOpcionsMenu(true, true);
          actualitzarBarraEstat("Les dades de la història s'han carregat.", false);
        }
        else {
          actualitzarBarraEstat("Les dades de la història no s'han carregat.", true);
        }
      }
      catch (Exception ex) {
        actualitzarBarraEstat("Les dades de la història no s'han carregat. (" +
                                     ex.getMessage() + ")", true);
      }
    }
    validate();
    repaint();
  }

  void jMenuVisual_actionPerformed(ActionEvent e) {
         System.out.println("Aca tiene que entrar");
		  String cmd = new String();
        Process proc;
        int err;
        boolean ok=false;

        actualitzarBarraEstat(" ", false);
        contentPane.remove(panelCentral);
        validate();
        repaint();
        try {
          ok = gestor.visualitzaArbre();
        }
        catch (Exception ex) {
          actualitzarBarraEstat(
        "Hi han hagut problemes per visualitzar el dendograma. (" + ex.getMessage() + ")", true);
        }

        if (ok) {
          String nomRes = new File(dadesActual).getName();
          actualitzarBarraEstat(
              "S'ha realitzat la visualització del dendograma correctament. (Resultats al fitxer: " +
              nomRes + "InfAuto.tex)", false);

          try {
            String pathResult = gestor.obtenirDirResultats();
            File dirResult = new File(pathResult);
            pathResult = pathResult + nomRes + "Dendo";

            cmd = SO.obtenirCmdCompilaLtx(pathResult + ".tex");
            if (cmd == null) {
              actualitzarBarraEstat(
                  "No es pot compilar ni visualitzar automàticament el fitxer Latex generat per no haver-se reconegut el sistema operatiu.", true);
            }
            else {
              logger.finer("Comanda exec: " + cmd);
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
                  Runtime.getRuntime().exec(cmd, null, dirResult);
                }
                else {
                  actualitzarBarraEstat(
                      "No s'ha pogut compilar correctament el fitxer Latex generat.", true);
                }
              }
              catch (InterruptedException exc) {
                actualitzarBarraEstat("S'ha interrumput el procés de compilació del fitxer Latex ( InterruptedException " +
                                      exc.getMessage() + " )", true);
              }
            }
          }
          catch (IOException exc) {
            actualitzarBarraEstat(
                "Error al generar i visualitzar el latex ( IOException " +
                exc.getMessage() + " )", true);
          }
        }
        validate();
        repaint();
  }

  void jMenuOLni_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);

    JFileChooser chooser = new JFileChooser();
    int id = -1; //identificador intern de la matriu de dades (ha de ser positiu)
    String matriu;


    // definimos todo lo necesario para el seleccionador de ficheros
    //Rober
    //chooser.setCurrentDirectory(new File("./dades"));
    chooser.setCurrentDirectory(obtenirDirActual ());

    chooser.setDialogTitle("Càrrega d'un fitxer de dendograma");
    ExtensionFileFilter filter = new ExtensionFileFilter();
    /**@todo Més endavant es llegiran fitxers només lni
     */

    //ROBER cambiamos la extension del fitxer Dendo
    //filter.addExtension("his");
    filter.addExtension("drm");

    filter.setDescription("Fitxers de dendograma de JavaKLASS");
    chooser.setFileFilter(filter);

    int returnVal = chooser.showOpenDialog(FrPrincipal.this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      // Eliminem l'extensió del fitxer per obtenir el nom de la matriu de dades
      // (conjunt de fitxers que la representen seran aquest nom i l'extensió .pro, .obj i .dat)
      String path = file.getPath();
      int i = path.lastIndexOf('.');
      // Per si s'introdueix el nom del fitxer sense extensió
      i = (i==-1)? path.length(): i;
      matriu = path.substring(0,i);
      try {
        actualitzarBarraEstat("Carregant el dendograma " + matriu
                                     + "...", false);
        id = gestor.carregarLni(matriu);
        if (id != -1) {
          /* Ara de moment només es carrega l'arbre de classificació
           Quan també es carregui una matriu de dades caldrà fer:
          actualitzarMatriuGuardada(false);
          actualitzarTitolFinestra(matriu);
          matrius.insertPathname(matriu, id);
          */
          habilitarOpcionsMenu(true, true);
          actualitzarBarraEstat("Les dades del dendograma s'han carregat.", false);
        }
        else {
          actualitzarBarraEstat("Les dades del dendograma no s'han carregat.", true);
        }
      }
      catch (Exception ex) {
        actualitzarBarraEstat("Les dades de la història no s'han carregat. (" +
                                     ex.getMessage() + ")", true);
      }
    }
    validate();
    repaint();
  }

  void jMIBoxPlotBased_actionPerformed(ActionEvent e){
	  actualitzarBarraEstat("", false);
	  contentPane.remove(panelCentral);
	  panelCentral = new PanelGenerarRegles(this,gestor);
	  contentPane.add(panelCentral, BorderLayout.CENTER);
	  validate();
	  repaint();
  }
  
  void jMIPodarRegles_actionPerformed(ActionEvent e){
	  actualitzarBarraEstat("", false);
	  contentPane.remove(panelCentral);
	  panelCentral = new PanelPodarRegles(this,gestor);
	  contentPane.add(panelCentral, BorderLayout.CENTER);
	  validate();
	  repaint();
  }
  
  
 /**
   * This action opens the menu to make the hierrarchical conceptualization of a dendogram
   * @author Esther Lozano
   * @param e
   */
  void jMIConceptJerarq_actionPerformed(ActionEvent e){
	  actualitzarBarraEstat("", false);
	  contentPane.remove(panelCentral);
	  panelCentral = new PanelConceptJerarq(this,gestor);
	  contentPane.add(panelCentral, BorderLayout.CENTER);
	  validate();
	  repaint();
  }
 
  
  
  void jMenuTalla_actionPerformed(ActionEvent e) {
    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    panelCentral = new PanelTallArbre(this,gestor);
    contentPane.add(panelCentral,  BorderLayout.CENTER);
    validate();
    repaint();
  }

   /* --- Implementació de la Interfície   MenuMatCarregades.IMenuMatCarregades -----*/

  /**
   * Obté el menú pare d'on penjara el menú de matrius carregades
   * @return menú
   */
  public JMenu obtenirMenuPare() {
      return jMenuCanvi;
  }

  public void actualitzarMatriuActual(int index, String matriu){
      if (gestor.canviarMatriuActual(index)) {
        actualitzarMatriuGuardada(false);
        actualitzarTitolFinestra(matriu);
        actualitzarBarraEstat("S'ha canviat la matriu actual.", false);
        contentPane.remove(panelCentral);
      }
      else {
        actualitzarBarraEstat("No ha estat possible canviar la matriu actual.", true);
      }
  }

// DISTANCIES*********************************************************************
  /** Accio que llança el menu per calcular
  * les distancies a partit de valors introduits
  *
  * @param e event de seleccionar la opció
  *
  * @author Jose I Mateos
  * @version v.0 23/4/06
  */
  private void jMenuDdirecte_actionPerformed(ActionEvent e) {
   	actualitzarBarraEstat(" ", false);
   	contentPane.remove(panelCentral);
   	panelCentral = new PanelDdirecte(this,gestor);
   	contentPane.add(panelCentral,  BorderLayout.CENTER);
   	validate();
   	repaint();
  }
  //DISTANCIES*********************************************************************
  /** Accions que llança el menu per calcular
  * les distancies a partit de valors de la matriu
  *
  * @param e event de seleccionar la opció
  *
  * @author Jose I Mateos
  * @version v.0 23/4/06
  */
  private void jMenuDmatriu_actionPerformed(ActionEvent e) {
   	actualitzarBarraEstat(" ", false);
   	contentPane.remove(panelCentral);
  	panelCentral = new PanelDmatriu(this,gestor);
   	contentPane.add(panelCentral,  BorderLayout.CENTER);
   	validate();
   	repaint();
  }
  // DISTANCIES*********************************************************************
  /** Accio que llança el menu per selecciona la submatriu
  *
  * @param e event de seleccionar la opció
  *
  * @author Jose I Mateos
  * @version v.0 25/6/06
  */
  private void jMenuSelec_actionPerformed(ActionEvent e) {
   	actualitzarBarraEstat(" ", false);
   	contentPane.remove(panelCentral);
   	panelCentral = new PanelSelec(this,gestor);
   	contentPane.add(panelCentral,  BorderLayout.CENTER);
   	validate();
   	repaint();
  }
  // DISTANCIES*********************************************************************
  /** Accio que llança el menu per substituir els missings de la matriu
   *
   * @param e event de seleccionar la opció
   * @author Jose I Mateos
  * @version v.0 25/6/06
  */
  private void jMenuMiss_actionPerformed(ActionEvent e) {
 	DlgOpcMiss dlg = new DlgOpcMiss(this, "Tractament de mancants",true,gestor);
    dlg.setLocationRelativeTo(this);
   	dlg.show();
  }
  // DISTANCIES*********************************************************************
  /** Accio que llança el menu per mostrar la matriu de correlacions
  * @author Jose I Mateos
  *
  * @param e event de seleccionar la opció
  *
  * @version v.0 23/8/06
  */
  private void jMenuCorre_actionPerformed(ActionEvent e) {
 	actualitzarBarraEstat(" ", false);
   	contentPane.remove(panelCentral);
  	panelCentral = new PanelCorre(this,gestor);
   	contentPane.add(panelCentral,  BorderLayout.CENTER);
   	validate();
   	repaint();
  }
  //DISTANCIES*********************************************************************
  /** Calcula l'identificador de la Matriu carregada actualment
  *
  * @return l'identificador de la matriu
  *
  * @author Jose I Mateos
  * @version v.0 25/6/06
  */
  int idMatriuActual(){
    return idDadesActual;
  }

  //DISTANCIES*********************************************************************
  /** Visualitza fitxers Latex
  *
  *
  * @author Jose I Mateos
  * @version v.0 4/4/07
  */
  void jMenuVisu_actionPerformed(ActionEvent e) {

    actualitzarBarraEstat(" ", false);
    contentPane.remove(panelCentral);
    JFileChooser chooser = new JFileChooser();
    File dirResult = new File("./dades/resultats");
    String latex;
    String cmd;
    Process proc;
    int err;

    // definimos todo lo necesario para el seleccionador de ficheros
    //Rober
    //    chooser.setCurrentDirectory(new File("./dades/resultats"));
    String dir = obtenirDirActual().getName()+"/resultats";
    chooser.setCurrentDirectory(new File (dir));

    chooser.setDialogTitle("Visualitzar fitxers Latex");
    ExtensionFileFilter filter = new ExtensionFileFilter();
    filter.addExtension("tex");
    filter.setDescription("Fitxers Latex de JavaKLASS");
    chooser.setFileFilter(filter);

    int returnVal = chooser.showOpenDialog(FrPrincipal.this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      // Eliminem l'extensió del fitxer per obtenir el nom de la matriu de dades
      // (conjunt de fitxers que la representen seran aquest nom i l'extensió .pro, .obj i .dat)
      String path = file.getPath();
      int i = path.lastIndexOf('.');
      // Per si s'introdueix el nom del fitxer sense extensió
      i = (i==-1)? path.length(): i;
      latex = path.substring(0,i);
      try {
        actualitzarBarraEstat("Carregant el fitser latex" + latex + "...", false);
        cmd = SO.obtenirCmdCompilaLtx(latex + ".tex");
        if (cmd == null) {
        /** @todo També es podria obrir una finestra */
        	actualitzarBarraEstat("No es pot compilar ni visualitzar automàticament el fitxer Latex generat per no haver-se reconegut el sistema operatiu.", true);
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
	            	cmd = SO.obtenirCmdVisorLtx(latex + ".dvi");
	                logger.finer("Comanda visor (exec): " + cmd);
	                Runtime.getRuntime().exec(cmd, null, dirResult);
	            }
                else {
                	actualitzarBarraEstat("No s'ha pogut compilar correctament el fitxer Latex generat. (Error " + err + ")" , true);
                }
             }
             catch (InterruptedException exc) {
	         	actualitzarBarraEstat("S'ha interrumput el procés de compilació del fitxer Latex ( InterruptedException " + exc.getMessage() + " )", true);
             }
     	}
      }
      catch (IOException exc) {
        	actualitzarBarraEstat("Error al generar i visualitzar el latex ( IOException " + exc.getMessage() + " )", true);
      }
   }
   validate();
   repaint();
  }
 /** Accio per cenrtar el menu principal
  *
  *
  * @author Jose I Mateos
  * @version v.0 25/6/06
  */
public void centrar(){
  Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ( (d.getWidth() - this.getWidth()) / 2);
    int y = (int) ( (d.getHeight() - this.getHeight()) / 2);
    this.setLocation(x, y);
}


 
        //  Rober nuevas funciones para el valor del parámetro  (File) directorioActual


        public void modifDirActual ( File f ) {
          directorioActual = f;
        }

        public File obtenirDirActual (){

          return directorioActual;
        }
        
        /**
         * Habilita les opcions del menú Coneixement
         * @author Laia Riera Guerra
         * @version v.4 23/06/07
         */
//////////////////////////////////////      LAIA/////////////////////////////////////

        public void habilitarOpcionsMenuConeixement() {		
      		jMenuDades.setEnabled(true);
      		jMenuMatriuDades.setEnabled(true);		
      		jMenuConeixement.setEnabled(true);		
      		jMenuEditarBC.setEnabled(true);		
      		jMenuAvaluarBC.setEnabled(true);		
      		jMenuDescriptivaBC.setEnabled(true);					
      		jMenuRecodificar.setEnabled(true);
      		jMenuDefinirFiltre.setEnabled(true);
      	}
        
        /**
         * Obre la finestra d'exportació de bases de coneixement
         * @author Laia Riera Guerra
         * @version v.4 23/06/07
         */
      	protected void jMenuExpRegles_actionPerformed(ActionEvent e) {
      		actualitzarBarraEstat(" ", false);
      		contentPane.remove(panelCentral);
      		panelCentral=new PanelExportarBases(this,gestor);
      		contentPane.add(panelCentral, BorderLayout.CENTER);
      		validate();
      		repaint();
      		
      	}
      	
      	
      	/**
      	 * Obre la finestra del menú Avaluar bases de coneixement
      	 * @author Laia Riera Guerra
      	 * @version v.4 23/06/07
      	 */
      	protected void jMenuAvaluarBC_actionPerformed(ActionEvent e) {
      		actualitzarBarraEstat(" ", false);
      		contentPane.remove(panelCentral);
      		panelCentral=new PanelAvaluadorBC(this,gestor);
      		contentPane.add(panelCentral, BorderLayout.CENTER);
      		validate();
      		repaint();		
      	}
      	/**
      	 *Acció que obre el menú per Editar bases de coneixement
      	 * @author Laia Riera Guerra
      	 * @version v.4 23/06/07
      	 */
      	protected void jMenuEditarBC_actionPerformed(ActionEvent e) {
      		actualitzarBarraEstat(" ", false);
      		contentPane.remove(panelCentral);
      		panelCentral = new PanelEditorRegles(this,gestor);
      		contentPane.add(panelCentral, BorderLayout.CENTER);
      		validate();
      		repaint();		
      	}
      	/**
      	 * Acció que obre el menú per efectuar càlculs aritmètics
      	 * @author Laia Riera Guerra
      	 * @version v.4 23/06/07
      	 */
      	protected void jMenuEditarFiltre_actionPerformed(ActionEvent e) {
      		actualitzarBarraEstat(" ", false);
      		contentPane.remove(panelCentral);
      		panelCentral = new PanelCalculador(this,gestor);
      		contentPane.add(panelCentral, BorderLayout.CENTER);
      		validate();
      		repaint();		
      	}
      	/**
      	 * Importa la família de fitxers de dades i el fitxer de regles del mateix nom
      	 * @author Laia Riera Guerra
      	 * @version v.4 23/06/07
      	 */
      	void jMenuODadesRegles_actionPerformed(ActionEvent e) {
      		 int n = JOptionPane.YES_OPTION;
      		// jMenuDades.setEnabled(true);
      		// jMenuMatriuDades.setEnabled(true);
      		    actualitzarBarraEstat(" ", false);
      		    contentPane.remove(panelCentral);
      		 
      		    JFileChooser chooser = new JFileChooser();
      		    int id = -1; //identificador intern de la matriu de dades (ha de ser positiu)
      		    String matriu;
      		 
      		    // definimos todo lo necesario para el seleccionador de ficheros
      		    chooser.setCurrentDirectory(obtenirDirActual ());

      		    chooser.setDialogTitle("Càrrega de dades amb regles");
      		    ExtensionFileFilter filter = new ExtensionFileFilter();
      		    filter.addExtension("dat");
      		    filter.addExtension("pro");
      		    filter.addExtension("obj");
      		    filter.addExtension("reg");
      		    filter.setDescription("Fitxers de dades JavaKLASS");
      		    chooser.setFileFilter(filter);
      		 
      		    int returnVal = chooser.showOpenDialog(FrPrincipal.this);
      		    if (returnVal == JFileChooser.APPROVE_OPTION) {
      		      File file = chooser.getSelectedFile();
      		      String snom=file.getName().substring(0,file.getName().lastIndexOf("."));
      		      // Eliminem l'extensió del fitxer per obtenir el nom de la matriu de dades
      		      // (conjunt de fitxers que la representen seran aquest nom i l'extensió .pro, .obj i dat)
      		      String path = file.getPath();
      		      int i = path.lastIndexOf('.');
      		      // Per si s'introdueix el nom del fitxer sense extensió
      		      i = (i==-1)? path.length(): i;
      		      matriu = path.substring(0,i);
      		      try {
      		        actualitzarBarraEstat("Carregant la matriu de dades " + matriu
      		                                     + "...", false);
      		       
      		        if(matrius.existeix(matriu)){
      		         n = JOptionPane.showConfirmDialog(
      		        		 this,"Ja hi ha una matriu en el sistema amb aquest nom que es perdrà. Segur que vols continuar?",
      		                   "Càrrega de dades amb regles",
      		                   JOptionPane.YES_NO_OPTION);                  
      		         }  
      		         if (n == JOptionPane.YES_OPTION){
      		         id = gestor.carregarMatriu(matriu);
      		         bc=gestor.carregarRegles(matriu,snom);
      		         if (id != -1) {
      		            actualitzarMatriuGuardada(false);
      		            actualitzarTitolFinestra(matriu);
								////////alejandro cambio de laia
								 jMenuDades.setEnabled(true);
      		             jMenuMatriuDades.setEnabled(true);
                      ////////alejandro cambio de laia
      		            habilitarOpcionsMenuConeixement();
      		            habilitarOpcionsMenu(true, false);
      		            actualitzarBarraEstat("Les dades s'han carregat.", false);
      		            matrius.insertarMatriu(matriu, id); // hook into FileHistory class
      		         }
      		         else {
      		            actualitzarBarraEstat("Les dades no s'han carregat.", true);
      		         }
      		     }
      		      }
      		      catch (Exception ex) {
      		        actualitzarBarraEstat("Les dades no s'han carregat: " +
      		                                     ex.getMessage(), true);
      		      }
      		    }
      		    validate();
      		    repaint();
      		  }
      	/**
      	 * Importa un fitxer de regles .reg
      	 * @author Laia Riera Guerra
      	 * @version v.4 23/06/07
      	 */
      	public void jMenuORegles_actionPerformed(ActionEvent e) {
      	
      		
      		actualitzarBarraEstat(" ", false);
      		contentPane.remove(panelCentral);
      		int id = -1; // identificador intern de la matriu de dades (ha de ser
      		// positiu)
      		String matriu;
      	
      		JFileChooser chooser = new JFileChooser();		
      	    chooser.setCurrentDirectory(obtenirDirActual ());

      		chooser.setDialogTitle("Càrrega de regles");
      		ExtensionFileFilter filter = new ExtensionFileFilter();
      		filter.addExtension("reg");
      		filter.setDescription("Fitxers de regles JavaKLASS");
      		chooser.setFileFilter(filter);

      		int returnVal = chooser.showOpenDialog(FrPrincipal.this);
      		if (returnVal == JFileChooser.APPROVE_OPTION) {
      			File file = chooser.getSelectedFile();
      			String snom=file.getName().substring(0,file.getName().lastIndexOf("."));			
      			String path = file.getPath();
      			int i = path.lastIndexOf('.');
      			// Per si s'introdueix el nom del fitxer sense extensió
      			i = (i == -1) ? path.length() : i;
      			matriu = path.substring(0, i);
      			try {
      				actualitzarBarraEstat("Carregant les regles " +matriu
      						+ "...", false);
      				bc=gestor.carregarRegles(matriu,snom);				
      				habilitarOpcionsMenuConeixement();
      				actualitzarBarraEstat("Les regles s'han carregat.", false);				
      			} catch (Exception ex) {
      				/*gestor.eliminarBC(bc.getNomBC());*/
      				ex.printStackTrace();
      				actualitzarBarraEstat("Les regles no s'han carregat. "
      						+ ex.getMessage(), true);
      			}
      		}
      		validate();
      		repaint();
      	}
      	
      	
      	/**
      	 * Acció que obre el menú per visualitzar la matriu de dades
      	 * @author Laia Riera Guerra
      	 * @version v.4 23/06/07
      	 */
      	protected void jMenuMatriuDades_actionPerformed(ActionEvent e) {
      		actualitzarBarraEstat(" ", false);
      		contentPane.remove(panelCentral);
      		panelCentral = new PanelMatriu(this,gestor);
      		contentPane.add(panelCentral, BorderLayout.CENTER);
      		validate();
      		repaint();		
      		
      	}
      	/**
      	 * Acció que obre el menú per efectuar la descriptiva d'una base de coneixement
      	 * @author Laia Riera Guerra
      	 * @version v.4 23/06/07
      	 */
      	 protected void jMenuDescriptivaBC_actionPerformed(ActionEvent e) {
      		 actualitzarBarraEstat(" ", false);
      		 contentPane.remove(panelCentral);
      		 panelCentral = new PanelDescriptivaBC(this,gestor);
      		 contentPane.add(panelCentral, BorderLayout.CENTER);
      		 validate();
      		 repaint();			
      	  }
			  
			  
		/**
       	 * This action opens the menu to measure the quality of a knowledge base
       	 * @author Esther Lozano 
       	 */
       	 protected void jMenuQualitatBC_actionPerformed(ActionEvent e) {
       		 actualitzarBarraEstat(" ", false);
       		 contentPane.remove(panelCentral);
       		 panelCentral = new PanelQualitatBC(this,gestor);
       		 contentPane.add(panelCentral, BorderLayout.CENTER);
       		 validate();
       		 repaint();			
       	  }
	  
			  
      	
      	 /**
      		 * Acció que obre el menú per recodificar variables
      		 * @author Laia Riera Guerra
      		 * @version v.4 23/06/07
      		 */
      	 protected void jMenuRecodificar_actionPerformed(ActionEvent e) {
      		 actualitzarBarraEstat(" ", false);
      		 contentPane.remove(panelCentral);
      		 panelCentral = new PanelRecodificarBC(this,gestor);
      		 contentPane.add(panelCentral, BorderLayout.CENTER);
      		 validate();
      		 repaint();		
      			
      		}
      	 /**
      		 * Acció que obre el menú per seleccionar una submatriu
      		 * @author Laia Riera Guerra
      		 * @version v.4 23/06/07
      		 */
      	 protected void jMenuDefinirFiltre_actionPerformed(ActionEvent e) {
      			actualitzarBarraEstat(" ", false);
      		   	contentPane.remove(panelCentral);
      		   	panelCentral = new PanelFiltre(this,gestor);
      		   	contentPane.add(panelCentral,  BorderLayout.CENTER);
      		   	validate();
      		   	repaint();
      			
      		}
      	 /**
      		 * Acció que obre el menú per discretitzar variables
      		 * @author Laia Riera Guerra
      		 * @version v.4 23/06/07
      		 */
      	 protected void jMenuDiscretitzador_actionPerformed(ActionEvent e) {
      		 actualitzarBarraEstat(" ", false);
      		   	contentPane.remove(panelCentral);
      		   	panelCentral = new PanelDiscretitzador(this,gestor);
      		   	contentPane.add(panelCentral,  BorderLayout.CENTER);
      		   	validate();
      		   	repaint();
      			
      		}
      	 /**
      		 * Acció que obre el menú per eliminar variables de la matriu
      		 * @author Laia Riera Guerra
      		 * @version v.4 23/06/07
      		 */
      	 protected void jMenuEliminarPropietats_actionPerformed(ActionEvent e) {
      		 actualitzarBarraEstat(" ", false);
      		   	contentPane.remove(panelCentral);
      		   	panelCentral = new PanelEliminarPropietats(this,gestor);
      		   	contentPane.add(panelCentral,  BorderLayout.CENTER);
      		   	validate();
      		   	repaint();
      			
      		}
      	 
      	 /**
      		 * Importa una matriu de dades en format .dat estàndard
      		 * @author Laia Riera Guerra
      		 * @version v.4 23/06/07
      		 */
      	 protected void jMenuImportar_actionPerformed(ActionEvent e) {
      		 actualitzarBarraEstat(" ", false);
      			contentPane.remove(panelCentral);
      			int id = -1; // identificador intern de la matriu de dades (ha de ser
      			// positiu)
      			String matriu;
      			int n = JOptionPane.YES_OPTION;
      			JFileChooser chooser = new JFileChooser();		
      		    chooser.setCurrentDirectory(obtenirDirActual ());

      			chooser.setDialogTitle("Càrrega de fitxer de dades estàndard");
      			ExtensionFileFilter filter = new ExtensionFileFilter();
      			filter.addExtension("dat");
      			filter.setDescription("Fitxers de dades estàndard JavaKLASS");
      			chooser.setFileFilter(filter);

      			int returnVal = chooser.showOpenDialog(FrPrincipal.this);
      			if (returnVal == JFileChooser.APPROVE_OPTION) {
      				File file = chooser.getSelectedFile();
      				String snom=file.getName().substring(0,file.getName().lastIndexOf("."));				
      				String path = file.getPath();
      				int i = path.lastIndexOf('.');
      				// Per si s'introdueix el nom del fitxer sense extensió
      				i = (i == -1) ? path.length() : i;
      				matriu = path.substring(0, i);
      				try {
      					actualitzarBarraEstat("Carregant les dades " +matriu
      							+ "...", false);
      					if(matrius.existeix(matriu)){
      			            n = JOptionPane.showConfirmDialog(
      			            		 this,"Ja hi ha una matriu en el sistema amb aquest nom que es perdrà. Segur que vols continuar?",
      			                     "Càrrega de dades estàndard",
      			                     JOptionPane.YES_NO_OPTION);
      			            }
      					if (n == JOptionPane.YES_OPTION){
      						DlgImportacioDatStand inst = new DlgImportacioDatStand(this,gestor);
      						inst.setLocationRelativeTo(this);
      						inst.setVisible(true);
      						int opc=0;
      						if(inst.OKPremut()){
      							opc=inst.obtenirOpcions();
      						}
      						switch(opc){
      						case 0: 
      							id=gestor.carregarMatriuEstandard(matriu,false,false);
      							break;
      						case 1:
      							id=gestor.carregarMatriuEstandard(matriu,true,false);
      							break;
      						case 2:
      							id=gestor.carregarMatriuEstandard(matriu,false,true);
      							break;
      						case 3:
      							id=gestor.carregarMatriuEstandard(matriu,true,true);
      							break;
      						
      						}								
      						habilitarOpcionsMenuConeixement();
      						if (id != -1) {
      				               actualitzarMatriuGuardada(false);
      				               actualitzarTitolFinestra(matriu);
      				               habilitarOpcionsMenu(true, false);
      				               actualitzarBarraEstat("Les dades s'han carregat.", false);
      				               matrius.insertarMatriu(matriu, id); // hook into FileHistory class

      				           }
      				            else {
      				               actualitzarBarraEstat("Les dades no s'han carregat.", true);
      				            }				
      					}				
      				} catch (Exception ex) {					
      					ex.printStackTrace();
      					actualitzarBarraEstat("Les dades no s'han carregat. "
      							+ ex.getMessage(), true);
      				}
      			}
      			validate();
      			repaint();
      			
      		}
      	 	/**
      		 * Acció que obre el menú per editar les metadades de les variables de la matriu
      		 * @author Laia Riera Guerra
      		 * @version v.4 23/06/07
      		 */
      	 protected void jMenuModifMetadades_actionPerformed(ActionEvent e) {
      		 	actualitzarBarraEstat(" ", false);
      		   	contentPane.remove(panelCentral);
      		   	panelCentral = new PanelMetadades(this,gestor);
      		   	contentPane.add(panelCentral,  BorderLayout.CENTER);
      		   	validate();
      		   	repaint();
      			
      			
      		}

      	protected void jMenuCombinarBC_actionPerformed(ActionEvent e) {
      		
      		
      	}

}

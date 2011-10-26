package jklass.iu;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;

import javax.swing.border.*;

import jklass.nucli.BaseConeixement;
import jklass.nucli.GestorKlass;
/** Classe que dibuixa el submenú per seleccionar la base de coneixement a exportar
*
* @author Laia Riera Guerra
* @version v.4 22/6/07
*/

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

public class PanelExportarBases extends JPanel {
  FrPrincipal frPare;
  GestorKlass gestor;
  String nomFitxer = null;

  FlowLayout flowLayout1 = new FlowLayout();
  JPanel jPanelVars = new JPanel();
  JButton jBttnAfegirN = new JButton();
  private JTextField jTextFieldBC=new JTextField(); 
  private JPanel jPanelBC;  
  BorderLayout borderLayout4 = new BorderLayout();
  JButton jBttnTreureN = new JButton();
  BorderLayout borderLayout5 = new BorderLayout();
  DefaultListModel listModelVN = new DefaultListModel();
  JList jListVarsN = new JList(listModelVN);
  JScrollPane jSPVarsN = new JScrollPane(jListVarsN);
  JPanel jPanelSelecNum = new JPanel();
  JPanel jPanelBtnsN = new JPanel();
  JPanel jPanelBotones = new JPanel();
  FlowLayout flowLayout2 = new FlowLayout();
  BorderLayout borderLayout1 = new BorderLayout();
  Border border1;
  TitledBorder titledBorder1;
  Border border2;
  TitledBorder titledBorder2;
  Border border3;
  TitledBorder titledBorder3;
  JPanel jPanelTancar = new JPanel();
  JButton jBttnCancel = new JButton();
  JButton jBttnOK = new JButton();
  /**
   * Constructor
   * @param fr, finestra pare
   * @param gk, GestorKlass
   */
  public PanelExportarBases(FrPrincipal fr,GestorKlass gk) {
    String[][] llProps;
    int j,lon;
    jTextFieldBC.setEditable(false);
    frPare = fr;
    gestor = gk;
    nomFitxer = frPare.obtenirNomDades();
    String[] idBC=gestor.obtenirLlistaNomsBC();		
	 for(int i=0;i<idBC.length;i++){
		 listModelVN.insertElementAt(idBC[i], i);		
	 }    
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * Dibuixa el menú
   * @throws Exception
   */
  private void jbInit() throws Exception {
  	{
  	}
    frPare.setSize(550,330);
    frPare.centrar();
    border1 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158));
    titledBorder1 = new TitledBorder(border1,"Exporta Bases de Coneixement");
    border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158));
    titledBorder2 = new TitledBorder(border2,"Llista BC");
    border3 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158));
    titledBorder3 = new TitledBorder(border3,"BC a exportar");
    jPanelVars.setLayout(borderLayout5);
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
    jSPVarsN.getViewport().setBackground(Color.white);
    jSPVarsN.setAutoscrolls(true);
    jSPVarsN.setBorder(titledBorder2);
    jSPVarsN.setPreferredSize(new Dimension(100, 153));
    jPanelSelecNum.setLayout(flowLayout1);
    jPanelBtnsN.setLayout(borderLayout4);
    jPanelBotones.setLayout(flowLayout2);
    this.setLayout(borderLayout1);
    this.setBorder(titledBorder1);
    jBttnCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnCancel_actionPerformed(e);
      }
    });
    jBttnCancel.setText("Cancel·la");
    jBttnOK.setText("D'acord");
    jBttnOK.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jBttnOK_actionPerformed(e);
      }
    });
    flowLayout2.setHgap(75);
    jPanelVars.add(jPanelSelecNum, BorderLayout.NORTH);
    jPanelSelecNum.add(jSPVarsN, null);
    jPanelSelecNum.add(jPanelBtnsN, null);
	{
		jPanelBC = new JPanel();
		jPanelSelecNum.add(jPanelBC);
		jPanelBC.setPreferredSize(new java.awt.Dimension(127, 89));
		jPanelBC.setBorder(titledBorder3);
		{
			
			jPanelBC.add(jTextFieldBC);
			jTextFieldBC.setPreferredSize(new java.awt.Dimension(113, 20));
		}
	}
    jPanelBtnsN.add(jBttnAfegirN, BorderLayout.NORTH);
    jPanelBtnsN.add(jBttnTreureN, BorderLayout.SOUTH);
    this.add(jPanelVars, BorderLayout.CENTER);
    this.add(jPanelBotones,  BorderLayout.SOUTH);
    jPanelBotones.add(jPanelTancar, null);
    jPanelTancar.add(jBttnOK, null);
    jPanelTancar.add(jBttnCancel, null);
  }
  /**
   * Afegeix al camp <code>BC a exportar</code> la base de coneixement seleccionada a la llista
   * 
   * @param e, event que detecta que s'ha premut el botó <code>Seleccionar base de coneixement(mà cap a la dreta)</code>
   */
  void jBttnAfegirN_actionPerformed(ActionEvent e) {
	  Object ob=jListVarsN.getSelectedValue();
		String s=jTextFieldBC.getText();
		if(s.compareTo("")!=0 && s!=null){
			listModelVN.addElement(s);
		}
		jTextFieldBC.setText((String)ob);
		listModelVN.removeElement(ob);    
  }

/**
 * Afegeix a la llista de bases de coneixement la base de coneixement que es troba al camp <code>BC a exportar</code>
 * @param e, event que detecta que s'ha premut el botó <code>Deseleccionar base de coneixement(mà cap a l'esquerra)</code>
 */  void jBttnTreureN_actionPerformed(ActionEvent e) {
	  String s=jTextFieldBC.getText();
	  if(s.compareTo("")==0||s==null){}
	  else{
		  listModelVN.addElement(s);
		  jTextFieldBC.setText("");
	  }   
  }
 /**
  * Tanca la finestra
  * @param e, event que detecta que s'ha premut el botó <code>Cancel.la</code>
  */
  void jBttnCancel_actionPerformed(ActionEvent e) {
    frPare.remove(this);
    frPare.validate();
    frPare.repaint();
  }
  /**
   * Crea un fitxer .reg en el directori indicat, que conté les regles que componen la base de coneixement seleccionada
   * @param e, event que detecta que s'ha premut el botó <code>D'acord</code>
   */
  void jBttnOK_actionPerformed(ActionEvent e) {
	  String s=jTextFieldBC.getText();
	  
	  if(s.compareTo("")==0||s==null)frPare.actualitzarBarraEstat("No s'ha seleccionat una base de coneixement",true);
		else{
			BaseConeixement bc=gestor.obtenirBC(s);
			JFileChooser chooser = new JFileChooser();
			int id = -1; // identificador intern de la matriu de dades (ha de ser
							// positiu)
			String matriu;
			
			// definimos todo lo necesario para el seleccionador de ficheros
		    chooser.setCurrentDirectory(frPare.obtenirDirActual ());
		    chooser.setDialogTitle("Desem regles");
			ExtensionFileFilter filter = new ExtensionFileFilter();
			filter.addExtension("reg");
			
			filter.setDescription("Fitxers de regles JavaKLASS");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			//chooser.setFileFilter(filter);
			int returnVal = chooser.showSaveDialog(frPare);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				String path = file.getPath();
		         int i = path.lastIndexOf('.');
		         // Per si s'introdueix el nom del fitxer sense extensió
		         i = (i==-1)? path.length(): i;
		         String extensio=path.substring(i);
		         if(extensio.compareTo(".reg")==0){
		        	 System.out.println("Entrem aqui "+extensio);
		        	 matriu = path.substring(0,i);
		         }
		         else matriu=path;
				
				
				try {
					frPare.actualitzarBarraEstat("Desant la base de coneixement " + path
							+ "...", false);
					if(bc!=null)gestor.desarRegles(matriu,bc);
					else throw new Exception("BaseConeixement buida");
					
						frPare.actualitzarTitolFinestra(path);
						frPare.actualitzarBarraEstat("Les regles s'han desat.", false);
					
				} catch (Exception ex) {
					frPare.actualitzarBarraEstat("Les regles no s'han desat. "
							+ ex.getMessage(), true);
				}
				jTextFieldBC.setText("");
				listModelVN.addElement(s);
			}

		}
  }

  

}
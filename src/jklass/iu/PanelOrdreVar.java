   package jklass.iu;

   import java.awt.*;
   import javax.swing.*;
   import java.awt.event.*;
   import javax.swing.border.*;

   import jklass.nucli.GestorKlass;

/**
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.1
 */
    public class PanelOrdreVar extends JPanel {
      FrPrincipal frPare;
      GestorKlass gestor;
      String nomFitxer = null;
   
      FlowLayout flowLayout1 = new FlowLayout();
      JPanel jPanelVars = new JPanel();
      JButton jBttnAfegirN = new JButton();
      DefaultListModel listModelSN = new DefaultListModel();
      JList jListSelcN = new JList(listModelSN);
      JScrollPane jSPSelecN = new JScrollPane(jListSelcN);
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
      JButton jBttnAjuda = new JButton();
   
       public PanelOrdreVar(FrPrincipal fr,GestorKlass gk) {
         String[][] llProps;
         int j,lon;
      
         frPare = fr;
         gestor = gk;
         nomFitxer = frPare.obtenirNomDades();
         llProps = gestor.obtenirLlistaIDsProps();
         j = 0;
         lon = llProps[0].length;
         //BEGIN [06M] 20100310
         for (int i = 0; i < lon; i++) {
        	listModelVN.insertElementAt(llProps[0][i], j);
        	j++;
         }
         //END [06M] 20100310
         lon = llProps[1].length;
         for (int i = 0; i < lon; i++) {
            listModelVN.insertElementAt(llProps[1][i], j);
            j++;
         }
         lon = llProps[2].length;
         for (int i = 0; i < lon; i++) {
            listModelVN.insertElementAt(llProps[2][i], j);
            j++;
         }
         lon = llProps[3].length;
         for (int i = 0; i < lon; i++) {
            listModelVN.insertElementAt(llProps[3][i], j);
            j++;
         }
         try {
            jbInit();
         }
             catch(Exception e) {
               e.printStackTrace();
            }
      }
   
       private void jbInit() throws Exception {
         frPare.setSize(520,330);
         border1 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(156, 156, 158));
         titledBorder1 = new TitledBorder(border1,"Definició de la ordenació de les variables");
         border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158));
         titledBorder2 = new TitledBorder(border2,"Llista variables");
         border3 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158));
         titledBorder3 = new TitledBorder(border3,"Llista ordenada");
         jPanelVars.setLayout(borderLayout5);
         jBttnAfegirN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dreta.gif")));
         jBttnAfegirN.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnAfegirN_actionPerformed(e);
                  }
               });
         jSPSelecN.setAutoscrolls(true);
         jSPSelecN.setBorder(titledBorder3);
         jSPSelecN.setPreferredSize(new Dimension(115, 153));
         jBttnTreureN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/esquerra.gif")));
         jBttnTreureN.addActionListener(
                new java.awt.event.ActionListener() {
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
         jBttnCancel.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnCancel_actionPerformed(e);
                  }
               });
         jBttnCancel.setText("Cancel·la");
         jBttnOK.setText("D'acord");
         jBttnOK.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnOK_actionPerformed(e);
                  }
               });
         jBttnAjuda.setText("Ajuda");
         jBttnAjuda.addActionListener(
                new java.awt.event.ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     jBttnAjuda_actionPerformed(e);
                  }
               });
         flowLayout2.setHgap(75);
         jPanelVars.add(jPanelSelecNum, BorderLayout.NORTH);
         jPanelSelecNum.add(jSPVarsN, null);
         jPanelSelecNum.add(jPanelBtnsN, null);
         jPanelBtnsN.add(jBttnAfegirN, BorderLayout.NORTH);
         jPanelBtnsN.add(jBttnTreureN, BorderLayout.SOUTH);
         jPanelSelecNum.add(jSPSelecN, null);
         this.add(jPanelVars, BorderLayout.CENTER);
         this.add(jPanelBotones,  BorderLayout.SOUTH);
         jPanelBotones.add(jPanelTancar, null);
         jPanelTancar.add(jBttnOK, null);
         jPanelTancar.add(jBttnCancel, null);
         jPanelBotones.add(jBttnAjuda, null);
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
            }
                catch (Exception exc) { hay_mas_vars = false; }
         } while (hay_mas_vars);
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
            }
                catch (Exception exc) { hay_mas_vars = false; }
         } while (hay_mas_vars);
      }
   
       void jBttnCancel_actionPerformed(ActionEvent e) {
         frPare.remove(this);
         frPare.validate();
         frPare.repaint();
      }
   
       void jBttnOK_actionPerformed(ActionEvent e) {
         String[] llistaOrdre;
         int i, nMods;
         boolean ok;
      
         //if (listModelVN.isEmpty()) {
            nMods = listModelSN.getSize();
            llistaOrdre = new String[nMods];
            for (i = 0; i < nMods; i++) {
               llistaOrdre[i] = (String) listModelSN.get(i);
               System.out.println("elemento"+llistaOrdre[i]);
            }
            ok = gestor.establirOrdreVars(llistaOrdre);
            if (ok) {
               frPare.actualitzarBarraEstat(
                  "S'ha establert l'ordre indicat. " , false);
            }
            else {
               frPare.actualitzarBarraEstat(
                  "No ha estat possible establir l'ordre indicat.", true);
            }
            frPare.remove(this);
         //} 
         //else {
            //frPare.actualitzarBarraEstat("Cal definir l'ordre per totes les variables.", true);
         //}
         frPare.validate();
         frPare.repaint();
      }
   
       void jBttnAjuda_actionPerformed(ActionEvent e) {
         JOptionPane.showMessageDialog(this,"Ordre que s'utilitzará per presentar qualsevol resultat de l'aplicació.", "Ajuda per la ordenació de les variables", JOptionPane.INFORMATION_MESSAGE);
      }
   
   }
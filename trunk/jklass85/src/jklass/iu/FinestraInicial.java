package jklass.iu;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas,Laia Riera
 * @version v.4
 */

public class FinestraInicial extends JWindow {
  private JPanel main = new JPanel(new BorderLayout());
  JLabel jLabel1 = new JLabel();
  JPanel jPanel1 = new JPanel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel10 = new JLabel();
  JLabel jLabel11 = new JLabel();
  JLabel jLabel12 = new JLabel(); 
  JLabel jLabel13 = new JLabel();
  JLabel jLabel14 = new JLabel();
  JLabel jLabel15 = new JLabel();
  JLabel jLabel16 = new JLabel();
  GridLayout gridLayout1 = new GridLayout(14,1);
  BorderLayout borderLayout1 = new BorderLayout();
  Border border1;
  Border border2;
  Border border3;
  /**
   * Constructor
   * @param fr, finestra pare
   * @param time
   */
  public FinestraInicial(Frame fr, int time) {
    super(fr);
    getContentPane().add(main);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
   //Se muestra la ventana
   pack();
   centerScreen();
   show();
   try
   {
     Thread.sleep(time);
     dispose();
   } catch (InterruptedException ie) {}

   fr.addWindowListener(new WindowListener());
  }

// Metodo para centrar en la pantalla
  private void centerScreen() {
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ( (d.getWidth() - getWidth()) / 2);
    int y = (int) ( (d.getHeight() - getHeight()) / 2);
    setLocation(x, y);
  }
  /**
   * Dibuixa la finestra
   * @throws Exception
   */
  private void jbInit() throws Exception {
    border1 = BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.white,Color.white,new Color(109, 109, 110),new Color(156, 156, 158)),BorderFactory.createEmptyBorder(20,20,20,20));
    border2 = BorderFactory.createEmptyBorder(10,0,0,0);
    border3 = BorderFactory.createEmptyBorder(10,0,0,0);
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 20));
    jLabel1.setRequestFocusEnabled(true);
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText("Java-KLASS v8.4 (2011)");
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 16));
    jLabel2.setBorder(border3);
    jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel2.setText("Copyright (c)");

    jLabel3.setFont(new java.awt.Font("Dialog", 1, 16));
    jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel3.setText("Karina Gibert");
    
    jLabel9.setFont(new java.awt.Font("Dialog", 0, 14));
    jLabel9.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel9.setText("Alejandro García");

    jLabel7.setFont(new java.awt.Font("Dialog", 0, 14));
    jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel7.setText("Esther Lozano");

    jLabel4.setFont(new java.awt.Font("Dialog", 0, 14));
    jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel4.setText("Mª del Mar Colillas");

    jLabel5.setFont(new java.awt.Font("Dialog", 0, 14));
    jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel5.setText("Jose I Mateos");

    jLabel6.setFont(new java.awt.Font("Dialog", 0, 14));
    jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel6.setText("Laia Riera");
    
    jLabel8.setFont(new java.awt.Font("Dialog", 0, 14));
    jLabel8.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel8.setText("&");
	 
	  jLabel10.setFont(new java.awt.Font("Dialog", 0, 14));
    jLabel10.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel10.setText("Andreu Raya");

	  jLabel11.setFont(new java.awt.Font("Dialog", 0, 14));
    jLabel11.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel11.setText("Roberto Tuda");

    jLabel12.setFont(new java.awt.Font("Dialog", 0, 14));
    jLabel12.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel12.setText("Alfons Bosch Sansa");

    jLabel13.setFont(new java.awt.Font("Dialog", 0, 14));
    jLabel13.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel13.setText("Patricia García Giménez");

    jLabel14.setFont(new java.awt.Font("Dialog", 0, 14));
    jLabel14.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel14.setText("Narcís Margall");
    
    jLabel15.setFont(new java.awt.Font("Dialog", 0, 14));
    jLabel15.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel15.setText("Alberto Martínez");
    
    jLabel16.setFont(new java.awt.Font("Dialog", 0, 14));
    jLabel16.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel16.setText("Jordi Pascual");    

    jPanel1.setLayout(gridLayout1);
    this.getContentPane().setLayout(borderLayout1);
    borderLayout1.setHgap(10);
    borderLayout1.setVgap(10);
    main.setBorder(border1);
    this.setContentPane(main);
    jPanel1.setAlignmentX((float) 0.5);
    jPanel1.setAlignmentY((float) 0.5);
    jPanel1.setBorder(border2);
    this.getContentPane().add(jLabel1, BorderLayout.NORTH);
    this.getContentPane().add(jLabel2, BorderLayout.CENTER);
    this.getContentPane().add(jPanel1, BorderLayout.SOUTH);
    //jPanel1.add(jLabel5,  BorderLayout.SOUTH);
    //jPanel1.add(jLabel3,  BorderLayout.NORTH);
    //jPanel1.add(jLabel4,  BorderLayout.CENTER);
    jPanel1.add(jLabel3);
    jPanel1.add(jLabel8);
    jPanel1.add(jLabel9);
	jPanel1.add(jLabel14);
    jPanel1.add(jLabel7);
    jPanel1.add(jLabel4);
    jPanel1.add(jLabel5);
    jPanel1.add(jLabel6);
    jPanel1.add(jLabel10);
    jPanel1.add(jLabel11);
    jPanel1.add(jLabel15);
    jPanel1.add(jLabel16);
    jPanel1.add(jLabel12);
    jPanel1.add(jLabel13);
  }

  class WindowListener extends WindowAdapter {
    public void windowActivated(WindowEvent we) {
      setVisible(false);
      dispose();
      }
    }

}

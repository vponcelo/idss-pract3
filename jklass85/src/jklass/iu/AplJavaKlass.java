package jklass.iu;

import javax.swing.UIManager;
import java.awt.*;

/**
 * Classe principal de l'aplicació
 * <p>Title: Java-Klass</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version 0
 */

public class AplJavaKlass {
  private boolean packFrame = false;

  /**Construeix l'aplicació
   *
   */
  public AplJavaKlass() {
	  
    FrPrincipal frame;

    frame = new FrPrincipal();
    //Validate frames that have preset sizes
    //Pack frames that have useful preferred size info, e.g. from their layout
    if (packFrame) {
      frame.pack();
    }
    else {
      frame.validate();
    }
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
  }

  /**
   * Métode main de l'aplicació. A la versión actual de Java-KLASS no es passa cap argument.
   * @param args
   */
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    new AplJavaKlass();
  }
}
package jklass.iu;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.logging.*;

import jklass.util.Parametres;

/**
 * Classe que contrueix i actualitza el menú de matrius carregades a l'aplicació.
 * Permet canviar la matriu activa (matriu actual) de l'aplicació
 *
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.1
 */
public class MenuMatCarregades{
  private static Logger logger=Logger.getLogger(MenuMatCarregades.class.getName());

   private static final int MAX_LONG_ETIQ = 50;
   private static final String FILE_SEPARATOR_STR = System.getProperty("file.separator");
   private static int maxMenuItems;
   private static ArrayList LlistaEtiqItems = new ArrayList(maxMenuItems);
   private static ArrayList LlistaMatrius = new ArrayList(maxMenuItems);
   private static ArrayList LlistaIndexos = new ArrayList(maxMenuItems);
   private IMenuMatCarregades pare;
   private JMenu menuPare;

   /**-----------------------------------------------------------------------
    * Interfície que ha d'implementar el 'frame' de IU de l'aplicació quan es
    * vol utilitzar la classe MenuMatCarregades
    * <p>Title: Java-KLASS</p>
    * <p>Description: Paquet estadístic</p>
    * <p>Copyright: Copyright (c) 2006</p>
    * <p> </p>
    * @author Mª del Mar Colillas
    * @version v.1
    */
   public static interface IMenuMatCarregades {

      /**
       * Obté el menú pare d'on penjara el menú de matrius carregades
       * @return menú
       */
      public JMenu obtenirMenuPare();
      /**
       * Actualitza propietats del 'frame' de IU
       * @param index
       * @param matriu
       */
      public void actualitzarMatriuActual(int index, String matriu);

   }

   /*------------------------------------------------------------------------*/

   /**
    * Constructor
    * @param menú pare d'on penjarà el menú de matrius carregades
    */
   public MenuMatCarregades(IMenuMatCarregades pare) {
     this.pare = pare;
     Parametres params = new Parametres("jklass.properties",
         "Propiedades de configuracion de Java-Klass");
     try {
       maxMenuItems = Integer.parseInt(params.obtenirMaximMatrius());

       if (maxMenuItems < 1) {
         maxMenuItems = 9;
       }
       menuPare = pare.obtenirMenuPare();
     }
     catch (NumberFormatException e) {
       logger.warning("Error al recuperar el nombre màxim nombre de matrius del fitxer de configuració(" +
                      e + ")");
     }
   }

   /**
   * Inserta al menú la matriu indicada si no està insertada ja.
   * Hi ha un màxim nombre de matrius que es poden insertar al menú (maxMenuItems)
   * Cada Item del menú començarà amb "<i>: ", on <i> està dins del rang [1..maxMenuItems].
   * La matriu insertada serà l'item 1 del menú
   * @param matriu nom de la matriu de dades a insertar
   * @param index
   */
   public final void insertarMatriu(String matriu, int index) {
      for (int k=0; k<LlistaMatrius.size();k++) {
         if (((String)LlistaMatrius.get(k)).equals(matriu)) {
            int pos = menuPare.getItemCount() - LlistaEtiqItems.size() + k;
            menuPare.remove(pos);
            LlistaMatrius.remove(k);
            LlistaEtiqItems.remove(k);
            LlistaIndexos.remove(k);
            insertarMatriu(matriu, index);
            return;
         }
      }
      if (!LlistaEtiqItems.isEmpty()){
        //s'eliminen tots els items del menú per reconstruir-lo
         for (int i=menuPare.getItemCount()-1, j=0; j < LlistaEtiqItems.size(); i--, j++) {
            menuPare.remove(i);
         }
      }

      LlistaEtiqItems.add(0, obtenirEtiq(matriu));
      LlistaMatrius.add(0, matriu);
      LlistaIndexos.add(0, new Integer(index));
      for (int i=0; i<LlistaEtiqItems.size(); i++) {
         MenuItemAmpliat item = new MenuItemAmpliat((i+1)+": "
                                            +(String)LlistaEtiqItems.get(i), ((Integer)LlistaIndexos.get(i)).intValue());
         item.setToolTipText((String)LlistaMatrius.get(i));
         item.addActionListener(new ItemListener(i));
         menuPare.add(item);
      }
   }

   /**
    * Actualitza el menú eliminant la matriu indicada per matriuElim del menú
    * i posant com actual la matriu indicada per matriuNova
    * @param matriuElim matriu que s'elimina del menú
    * @param matriuNova matriu que serà la número 1 al menú
    */
   public final void eliminarMatriu(String matriuElim, String matriuNova){
     int k = 0;
     boolean ok = false;

     while (!ok && (k<LlistaMatrius.size())){
        if (((String)LlistaMatrius.get(k)).equals(matriuElim)) {
           int index = menuPare.getItemCount() - LlistaEtiqItems.size() + k;
           menuPare.remove(index);
           LlistaMatrius.remove(k);
           LlistaEtiqItems.remove(k);
           LlistaIndexos.remove(k);
           ok = true;
        }
        k++;
     }
     if (matriuNova!="") { //Queden matrius carregades, cal buscar on està la nova
       k = 0;
       ok = false;
       while (!ok && (k<LlistaMatrius.size())){
         if (((String)LlistaMatrius.get(k)).equals(matriuNova)) {
           int pos = ((Integer)LlistaIndexos.get(k)).intValue();
           ok = true;
           insertarMatriu(matriuNova, pos);
         }
         k++;
       }
    }
   }

  /**
   * Retorna la etiqueta a posar al item a afegir al menú
   * @param matriu nom de la matriu afegida
   * @return etiqueta pel menú corresponent a la matriu indicada
   */
   protected String obtenirEtiq(String matriu) {
      final char FILE_SEPARATOR = FILE_SEPARATOR_STR.charAt(0);
      final int nomLong = matriu.length();
      // si el nom és prou curt es retorna tot sencer
      if (nomLong <= MAX_LONG_ETIQ) {
         return matriu;
      }
      // si nomes conté un directori es retorna tot sencer
      if (matriu.indexOf(FILE_SEPARATOR_STR) == matriu.lastIndexOf(FILE_SEPARATOR_STR)) {
         return matriu;
      }
      else {
         // escurcem el nom semblant a com ho fa Windows
         final int ABBREVIATED_PREFIX_LEN = 6; // e.g.: C:\..\
         final int MAX_PATHNAME_LEN = MAX_LONG_ETIQ - ABBREVIATED_PREFIX_LEN;
         int firstFileSeparatorIndex = 0;
         for (int i=nomLong-1; i>=(nomLong-MAX_PATHNAME_LEN); i--) {
            if (matriu.charAt(i) == FILE_SEPARATOR) {
               firstFileSeparatorIndex = i;
            }
         }
         if (firstFileSeparatorIndex > 0) {
            return matriu.substring(0, 3)
            + ".."
               + matriu.substring(firstFileSeparatorIndex, nomLong);
         }
         else {
            return matriu.substring(0, 3)
            + ".."
               + FILE_SEPARATOR_STR
               + ".."
               + matriu.substring(nomLong-MAX_PATHNAME_LEN, nomLong);
         }
      }
   }

 /**
   * Classe que afegeix al MenuItem un tooltip i també li associa un index intern
   * <p>Title: Java-KLASS</p>
   * <p>Description: Paquet estadístic</p>
   * <p>Copyright: Copyright (c) 2006</p>
   * <p> </p>
   * @author Mª del Mar Colillas
   * @version v.1
   */
   private final class MenuItemAmpliat extends JMenuItem {
     int pos;

      public MenuItemAmpliat(String text, int i) {
         super(text);
         pos = i;
      }

      public Point getToolTipLocation(MouseEvent e) {
         Graphics g = getGraphics();
         FontMetrics metrics = g.getFontMetrics(g.getFont());
         String prefix = LlistaEtiqItems.size() <= 9 ? "8: " : "88: ";
         int prefixWidth = metrics.stringWidth(prefix);
         int x = JButton.TRAILING + JButton.LEADING -1 + prefixWidth;
         return new Point(x, 0);
      }
   }

   private final class ItemListener implements ActionListener {
      int itemNum;

      ItemListener(int itemNum) {
         this.itemNum = itemNum;
      }

   public void actionPerformed(ActionEvent e) {
        MenuItemAmpliat item = (MenuItemAmpliat)e.getSource();
        // el nom sencer de la matriu està al Tooltip
        pare.actualitzarMatriuActual(item.pos, item.getToolTipText());
        MenuMatCarregades.this.insertarMatriu(item.getToolTipText(), item.pos);
      }
   }

   //Distancies******************************************************************
   
   
   public boolean existeix(String matriu){
   
	   boolean exis=false;
		for (int k=0; k<LlistaMatrius.size();k++) {
        	if (((String)LlistaMatrius.get(k)).equals(matriu)){
	        	exis=true;
        	}
   		}
   	    return exis;
	 }

   
   
}

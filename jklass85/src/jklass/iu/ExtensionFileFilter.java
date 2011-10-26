package jklass.iu;

import java.util.Enumeration;
import java.io.File;
import java.util.ArrayList;
import javax.swing.filechooser.*;
import javax.swing.*;

/**
 * Fa el tractament de les extensions dels fitxers
 * @author Laia Riera Guerra,Mª del Mar Colillas
 * @version v4
 * 
 */
public class ExtensionFileFilter extends FileFilter
{
  private String description;
  private ArrayList extensions;

  /**
   * Creates a file filter. If no filters are added, then all
   * files are accepted.
   */
  public ExtensionFileFilter() {
    description = "";
    extensions = new ArrayList();
  }

   /**
      Adds an extension that this file filter recognizes.
      @param extension a file extension (such as ".txt" or "txt")
   */
   public void addExtension(String extension)
   {
      if (!extension.startsWith("."))
         extension = "." + extension;
      extensions.add(extension.toLowerCase());
   }

   /**
      Sets a description for the file set that this file filter
      recognizes.
      @param aDescription a description for the file set
   */
   public void setDescription(String aDescription)
   {
      description = aDescription;
   }

   /**
      Returns a description for the file set that this file
      filter recognizes.
      @return a description for the file set
   */
   public String getDescription()
   {
      return description;
   }


   
   /**
    * Comprova que el fitxer acabi amb les extensions especificades
    * @param f, fitxer
    * 
    * @author Laia Riera Guerra
    * @version v4
    */
   public boolean accept(File f)
   {
      if (f.isDirectory()) return true;
      String name = f.getName().toLowerCase();

      // check if the file name ends with any of the extensions
      for (int i = 0; i < extensions.size(); i++){
         if (name.endsWith((String)extensions.get(i))){
        	 String path = f.getPath();
             int k = path.lastIndexOf('.');
             // Per si s'introdueix el nom del fitxer sense extensió
             k = (k==-1)? path.length(): k;
             String nomSenseExtensio = path.substring(0,k);            
             boolean b=true;
             for(int j=0;j<extensions.size() && b;j++){
            	 String nomaux=nomSenseExtensio+extensions.get(j);            	
            	 File fitxer=new File(nomaux);
            	 if(!fitxer.exists())b=false;
             }
             if(b==false)return false;
             else return true;
         }
      }            
      return false;
   }


}
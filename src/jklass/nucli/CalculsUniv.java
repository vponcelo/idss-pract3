package jklass.nucli;

/**
 * <p>Title: Java-KLASS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Mª del Mar Colillas
 * @version v.0
 */

/** Estructura auxiliar per guardar els càlculs intermitjos
 *
 */

public class CalculsUniv extends Calculs {
  protected  int nObj, nMissings, nUtil;

  public CalculsUniv() {
    super();
  }

  public void afegirNumObjs(int n){
    nObj = n;
  }

  public void afegirNumMancants(int n){
    nMissings = n;
  }

  public void afegirNumUtils(int n){
    nUtil = n;
  }

  public int obtenirNumObjs(){
    return nObj;
  }

  public int obtenirNumMancants(){
    return nMissings;
  }

  public int obtenirNumUtils(){
    return nUtil;
  }

}
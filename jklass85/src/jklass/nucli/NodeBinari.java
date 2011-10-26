package jklass.nucli;

import java.util.logging.*;

/**
 * Classe que implementa el node d'un arbre binari
 * <p>Title: Java-KLASS</p>
 * <p>Description: Paquet estadístic</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p> </p>
 * @author Mª del Mar Colillas
 * @version v.1
 */
public class NodeBinari {
  private static Logger logger=Logger.getLogger(NodeBinari.class.getName());
  /**
   * etiqueta del node
   */
  public String etiq;
  /**
   * index de nivell del node
   */
  private double idn;
  
  /**
   * etiqueta inducida por reglas
   */
  private boolean reglas=false; 
  /**
   * apuntadors al nodes fill esquerre i fill dret
   */
  private NodeBinari esq, dret;


public  NodeBinari(String etiqueta) {
     this(etiqueta, 0, null, null);
  }

  NodeBinari(String etiqueta, double index){
      this(etiqueta, index, null, null);
  }

 public NodeBinari(String etiqueta, double index, NodeBinari fillEsq, NodeBinari fillDret){
    etiq = etiqueta;
    idn = index;
	 reglas = false;
    esq = fillEsq;
    dret = fillDret;
  }

  boolean esFulla(){
    return ((esq==null) && (dret ==null));
  }

 public void modifFillEsq(NodeBinari node){
    esq = node;
  }

public  void modifFillDret(NodeBinari node){
    dret = node;
  }

 public void modifIndexNivell(double index){
    idn = index;
  }
  
  void modifReglas(boolean reg){
    reglas = reg;
  }
 
  

public  NodeBinari obtenirFillEsq(){
    return esq;
  }

public  NodeBinari obtenirFillDret(){
    return dret;
  }

  String obtenirEtiqueta(){
    return etiq;
  }

 public double obtenirIndexNivell(){
    return idn;
  }
  
 boolean obtenirReglas(){
    return reglas;
  }
 

}
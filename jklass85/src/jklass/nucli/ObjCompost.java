package jklass.nucli;

class ObjCompost extends Objecte {
  private int totalObj;
  public ObjSimple llObjSimple[];

  ObjCompost(String id, int pes) {
    super(0, id,pes);
  }

  Objecte clon(){
		//Object o = super.clon();
		ObjCompost o = new ObjCompost(obtenirId(), (int)obtenirPes());
		for (int i = 0; i < llObjSimple.length; i++) {
			o.llObjSimple[i] = (ObjSimple)llObjSimple[i].clon();
		}
		return o;
  }

}

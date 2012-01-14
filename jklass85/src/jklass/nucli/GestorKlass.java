package jklass.nucli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.util.Iterator;

import jklass.util.Opcions;
import jklass.util.OpcionsDis;

/**
 * Classe que permet accedir a tota la funcionalitat proporcionada pel
 * jklass.nucli de Java-KLASS.
 * <p>
 * Title: Java-KLASS
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Mª del Mar Colillas,Laia Riera Guerra
 * @version v.4
 */

public class GestorKlass {

	/**
	 * Indexs de metriques de regles
	 * 
	 * @author Grup SISPD QT 2009-2010
	 */
	private static final int CONFIDENCE_IDX = 0;
	private static final int COVERINGR_IDX = 1;
	private static final int SUPPORT_IDX = 2;
	private static final int MAX_RULE_METRICS = 3;

	/**
	 * Knowledge Integration Methods of CCEC
	 * 
	 * @author Grup SISPD QT 2009-2010
	 */
	public static final int CCEC_BLC_NoCWA_Simple = 0;// Best Local Concept and
														// No Close-World
														// Assumption Simple
	public static final int CCEC_BLC_NoCWA = 1;// Best Local Concept and No
												// Close-World Assumption
	public static final int CCEC_BLC_CWA = 2;// Best Local Concept and
												// Close-World Assumption
	public static final int CCEC_BGC_NoCWA = 3;// Best Global Concept and No
												// Close-World Assumption
	public static final int CCEC_BGC_CWA = 4;// Best Global Concept and
												// Close-World Assumption
	public static final int CCEC_BLC_pCWA = 5;// Best Local Concept and
												// partial-Close-World
												// Assumption
	public static final int CCEC_BLGC_CWA = 6;// Best Local-Global Concept and
												// Close-World Assumption
	// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**
	 * Referència a la instancia única de la classe
	 */
	private static GestorKlass instancia;
	/**
	 * Vector on es tenen matrius de dades carregades al sistema amb les que pot
	 * treballar el jklass.nucli
	 */
	public GestorMatriu matriusCarregades[];
	/**
	 * Apuntador a la posició dins del vector matriusCarregades on es troba la
	 * matriu de treball del sistema
	 */
	private int matriuActual;
	/**
	 * Nombre màxim de matrius que es poden tenir carregades al sistema
	 */
	private int maxMatrius;
	/**
	 * Nombre de posicions ocupades per matrius al vector
	 * <code> matriusCarregades </code>
	 */
	private int numMatriusOcupades;
	private static Logger logger = Logger
			.getLogger(GestorKlass.class.getName());

	/**
	 * Seqüenciador per a identificar els arbres emmagatzemats.
	 */
	private int numArbre;

	// public boolean activasel=false;
	// public boolean marcado=false;

	/**
	 * Constructor privat per defecte que inicialitza els atributs de la classe
	 * 
	 * @param max
	 *            màxim nombre de matrius que es poden carregar
	 */
	private GestorKlass(int max) {
		maxMatrius = max;
		numMatriusOcupades = 0;
		matriusCarregades = new GestorMatriu[maxMatrius];
		logger.finer("Creada e inicialitzada la instancia de GestorKlass.");
	}

	/**
	 * Obté la instancia de la classe GestorKlass
	 * 
	 * @param maxMats
	 *            màxim nombre de matrius que es poden carregar
	 * @return jklass.nucli.GestorKlass
	 */
	public static synchronized GestorKlass obtenirInstanciaUnica(int maxMats) {
		if (instancia == null)
			instancia = new GestorKlass(maxMats);
		return instancia;
	}

	/**
	 * Carrega la matriu de dades identificada pel paràmetre d'entrada a partir
	 * dels seus fitxers .obj, .pro i .dat i retorna la posició ocupada per la
	 * matriu
	 * 
	 * @param nom
	 *            nom sense extensió dels fitxers (.obj, .pro, .dat) a carregar
	 * @return int >0, posició on es guarda la matriu si s'ha carregat
	 *         correctament <br>
	 *         -1, si no es pot carregar la matriu
	 */
	public int carregarMatriu(String nom) throws CreacioMatriuException,
			CreacioPropietatsException, CreacioObjectesException {
		int i = -1;

		if (numMatriusOcupades < maxMatrius - 1) {
			// Es busca la primera posició lliure del array
			i = numMatriusOcupades;
			while (matriusCarregades[i] != null) {
				i = (i + 1) % maxMatrius;
			}
			matriusCarregades[i] = new GestorMatriu(nom, i);
			if (matriusCarregades[i] != null) {
				matriuActual = i;
				numMatriusOcupades++;
			}
		} else {
			logger.warning("Nombre màxim de matrius carregades al sistema.");
			throw new CreacioMatriuException(
					"Nombre màxim de matrius carregades al sistema.");
		}
		return i;
	}

	public int carregarMatriu0(String nom) throws CreacioMatriuException,
			CreacioPropietatsException, CreacioObjectesException {
		int i = -1;

		if (numMatriusOcupades < maxMatrius - 1) {
			// Es busca la primera posició lliure del array
			i = numMatriusOcupades;
			while (matriusCarregades[i] != null) {
				i = (i + 1) % maxMatrius;
			}
			matriusCarregades[0] = new GestorMatriu(nom, 0);
			if (matriusCarregades[i] != null) {
				matriuActual = i;
				numMatriusOcupades++;
			}
		} else {
			logger.warning("Nombre màxim de matrius carregades al sistema.");
			throw new CreacioMatriuException(
					"Nombre màxim de matrius carregades al sistema.");
		}
		return i;
	}

	// alejandro activas
	// public int carregarMatriuActivas(String nom,String[] llMods) throws
	// CreacioMatriuException, CreacioPropietatsException,
	// CreacioObjectesException{
	// int i = -1;
	// boolean activas=true;
	// System.out.println("Estoy en gestorklass carregarMatriuact");

	// for (int r = 0; r < llMods.length; r++) {

	// System.out.println("estoy en carregarMatriu gestor klass="+llMods[r]);
	// }

	// if (numMatriusOcupades < maxMatrius-1) {
	// Es busca la primera posició lliure del array
	// i = numMatriusOcupades;
	// while (matriusCarregades[i]!= null){
	// i = (i+1)% maxMatrius;
	// }
	// matriusCarregades[i] = new GestorMatriu(nom,i,llMods);
	// if (matriusCarregades[i] != null) {
	// matriuActual = i;
	// numMatriusOcupades++;
	// }
	// } else {
	// logger.warning("Nombre màxim de matrius carregades al sistema.");
	// throw new
	// CreacioMatriuException("Nombre màxim de matrius carregades al sistema.");
	// }
	// return i;
	// }

	/**
	 * Ara de moment només es carrega l'arbre de classificació però tambè haurà
	 * de carregar la info de la matriu dades associada. De moment es considerà
	 * que només tindrem objectes simples.
	 * 
	 * @param nom
	 *            nom sense extensió del fitxer .his a carregar
	 * @return
	 * @throws CreacioMatriuException
	 * @throws CreacioPropietatsException
	 * @throws CreacioObjectesException
	 */
	public int carregarHistoria(String nom) throws FileNotFoundException,
			FormatIncorrecteException, IOException {
		int i = -1;
		/*
		 * Quan carreguem la info de la matriu dades associada... if
		 * (numMatriusOcupades < maxMatrius-1) { // Es busca la primera posició
		 * lliure del array i = numMatriusOcupades; while
		 * (matriusCarregades[i]!= null){ i = (i+1)% maxMatrius; }
		 * matriusCarregades[i] = new GestorMatriu(nom,i); if
		 * (matriusCarregades[i] != null) { matriuActual = i;
		 * numMatriusOcupades++; } } else {
		 * logger.warning("Nombre màxim de matrius carregades al sistema.");
		 * throw new
		 * CreacioMatriuException("Nombre màxim de matrius carregades al sistema."
		 * ); }
		 */
		matriusCarregades[matriuActual].carregarHis(nom);
		i = 0;
		return i;
	}

	/**
	 * Ara de moment es carrega l'arbre de classificació del his però haurà de
	 * carregar-lo del .lni més endavant. De moment es considerà que només
	 * tindrem objectes simples.
	 * 
	 * @param nom
	 *            nom sense extensió del fitxer .his a carregar
	 * @return
	 * @throws CreacioMatriuException
	 * @throws CreacioPropietatsException
	 * @throws CreacioObjectesException
	 */
	public int carregarLni(String nom) throws FileNotFoundException,
			FormatIncorrecteException, IOException {
		int i = -1;
		/* Més endavant es carregarà d'un .lni */
		// Rober
		// matriusCarregades[matriuActual].carregarHis(nom);
		matriusCarregades[matriuActual].carregarLni(nom);

		i = 0;
		return i;
	}

	/**
	 * Genera a disc tot el conjunt de fitxers amb el nom indicat en format
	 * JavaKLASS corresponent a la matriu de dades actual
	 * 
	 * @param nom
	 * @return
	 * @throws IOException
	 */
	public int desarMatriu(String nom) throws IOException {
		int i = -1;

		matriusCarregades[matriuActual].desarMatriu(nom);
		i = 0;
		return i;
	}

	/**
	 * Es treu la matriu de dades actual del sistema
	 * 
	 * @return nom de la nova matriu actual, si hi havia més d'una matriu de
	 *         dades carregada al sistema
	 */
	public String tancaMatriuActual() {
		String nomMatriu = "";
		int i;

		// la posició de la matriuActual queda lliure
		matriusCarregades[matriuActual] = null;
		if (numMatriusOcupades > 1) {
			// Com a mín hi ha una altra matriu carregada
			numMatriusOcupades--;
			// Es busca una matriu carregada anteriorment al sistema per a que
			// passi a ser l'actual
			i = matriuActual;
			while (matriusCarregades[i] == null) {
				i = i - 1;
				if (i == -1)
					i = maxMatrius - 1;
			}
			matriuActual = i;
			nomMatriu = matriusCarregades[matriuActual].obtenirNomMatriu();
		} else {
			numMatriusOcupades = 0;
		}
		return nomMatriu;
	}

	/**
	 * Carrega la classificació en una variable de classe a la matriu actual o a
	 * una matriu nova, còpia de la matriu actual
	 * 
	 * @param nomCls
	 *            fitxer .cls que conté la classificació a incorporar
	 * @param nomClas
	 *            nom de la variable de classe a incorporar
	 * @param novaMatriu
	 *            indica si s'ha de crear una nova matriu amb la classificació o
	 *            afegir la classificació a la matriu actual
	 * @param nomMatriu
	 *            nom de la nova matriu si cal crear-la
	 * @return
	 * @throws FileNotFoundException
	 * @throws FormatIncorrecteException
	 * @throws IOException
	 * @throws CreacioMatriuException
	 * @throws CreacioPropietatsException
	 */
	public int carregarClas(String nomCls, String nomClas, boolean novaMatriu,
			String nomMatriu) throws Exception, FileNotFoundException,
			FormatIncorrecteException, IOException, CreacioMatriuException,
			CreacioPropietatsException {
		int i = -1, anterior = -1;
		// System.out.println("holaaaaaaaaaa entre a carregar clas");
		try {
			if (novaMatriu) {
				if (numMatriusOcupades < maxMatrius - 1) {
					// Es busca la primera posició lliure del array
					i = numMatriusOcupades;
					while (matriusCarregades[i] != null) {
						i = (i + 1) % maxMatrius;
					}
					matriusCarregades[i] = matriusCarregades[matriuActual]
							.obtenirCopia(nomMatriu, i);
					logger.fine("Creada una matriu de dades còpia de l'actual amb nom "
							+ nomMatriu);
					if (matriusCarregades[i] != null) {
						anterior = matriuActual;
						matriuActual = i;
						numMatriusOcupades++;
					}
				} else {
					logger.warning("Nombre màxim de matrius carregades al sistema.");
					throw new CreacioMatriuException(
							"Nombre màxim de matrius carregades al sistema.");
				}
			}
			// System.out.println("antes del vacioooooooooooooooooooooooooooooo");
			boolean vacio;

			matriusCarregades[matriuActual]
					.afegirClassificacio(nomClas, nomCls);

			i = matriuActual;
		} catch (Exception e) {
			// S'ha produit un error i no s'ha pogut carregar del tot la
			// classificació
			// Quan calgui es desfa la nova matriu
			if (anterior > -1) {
				matriuActual = anterior;
				numMatriusOcupades--;
			}
			throw e;
		}

		return i;
	}

	public void afegirColumna(Propietat prop, Dada[] Mdades)
			throws CreacioPropietatsException, CreacioMatriuException {
		matriusCarregades[matriuActual - 1].afegirColumna(prop, Mdades);
	}

	public int carregarVar(String nomCls, String nomClas, boolean novaMatriu,
			String nomMatriu) throws Exception, FileNotFoundException,
			FormatIncorrecteException, IOException, CreacioMatriuException,
			CreacioPropietatsException {
		int i = -1, anterior = -1;
		// System.out.println("holaaaaaaaaaa entre a carregar clas");
		try {
			if (novaMatriu) {
				if (numMatriusOcupades < maxMatrius - 1) {
					// Es busca la primera posició lliure del array
					i = numMatriusOcupades;
					while (matriusCarregades[i] != null) {
						i = (i + 1) % maxMatrius;
					}
					matriusCarregades[i] = matriusCarregades[matriuActual]
							.obtenirCopia(nomMatriu, i);
					logger.fine("Creada una matriu de dades còpia de l'actual amb nom "
							+ nomMatriu);
					if (matriusCarregades[i] != null) {
						anterior = matriuActual;
						matriuActual = i;
						numMatriusOcupades++;
					}
				} else {
					logger.warning("Nombre màxim de matrius carregades al sistema.");
					throw new CreacioMatriuException(
							"Nombre màxim de matrius carregades al sistema.");
				}
			}
			// System.out.println("antes del vacioooooooooooooooooooooooooooooo");
			boolean vacio;

			matriusCarregades[matriuActual].afegirVar(nomClas, nomCls);
			i = matriuActual;
		} catch (Exception e) {
			// S'ha produit un error i no s'ha pogut carregar del tot la
			// classificació
			// Quan calgui es desfa la nova matriu
			if (anterior > -1) {
				matriuActual = anterior;
				numMatriusOcupades--;
			}
			throw e;
		}

		return i;
	}

	// /Carregar Numericoa
	public int carregarVarNum(String nomCls, String nomClas,
			boolean novaMatriu, String nomMatriu) throws Exception,
			FileNotFoundException, FormatIncorrecteException, IOException,
			CreacioMatriuException, CreacioPropietatsException {
		int i = -1, anterior = -1;
		// System.out.println("holaaaaaaaaaa entre a carregar clas");
		try {
			if (novaMatriu) {
				if (numMatriusOcupades < maxMatrius - 1) {
					// Es busca la primera posició lliure del array
					i = numMatriusOcupades;
					while (matriusCarregades[i] != null) {
						i = (i + 1) % maxMatrius;
					}
					matriusCarregades[i] = matriusCarregades[matriuActual]
							.obtenirCopia(nomMatriu, i);
					logger.fine("Creada una matriu de dades còpia de l'actual amb nom "
							+ nomMatriu);
					if (matriusCarregades[i] != null) {
						anterior = matriuActual;
						matriuActual = i;
						numMatriusOcupades++;
					}
				} else {
					logger.warning("Nombre màxim de matrius carregades al sistema.");
					throw new CreacioMatriuException(
							"Nombre màxim de matrius carregades al sistema.");
				}
			}
			// System.out.println("antes del vacioooooooooooooooooooooooooooooo");
			boolean vacio;

			matriusCarregades[matriuActual].afegirVarNum(nomClas, nomCls);
			i = matriuActual;
		} catch (Exception e) {
			// S'ha produit un error i no s'ha pogut carregar del tot la
			// classificació
			// Quan calgui es desfa la nova matriu
			if (anterior > -1) {
				matriuActual = anterior;
				numMatriusOcupades--;
			}
			throw e;
		}

		return i;
	}

	// alejandro este es el carregarMat del Panel IntegraMat
	// public int carregarMat(String nomCls, String nomClas, boolean novaMatriu,
	// String nomMatriu)
	public int carregarMat(String nomCls) throws Exception,

	FileNotFoundException, FormatIncorrecteException, IOException,
			CreacioMatriuException, CreacioPropietatsException {
		int i = -1, anterior = -1;

		System.out.println("pelotudoooo estouy acaaaaaaaa");
		try {
			if (true) {
				if (numMatriusOcupades < maxMatrius - 1) {
					// Es busca la primera posició lliure del array
					i = numMatriusOcupades;
					while (matriusCarregades[i] != null) {
						i = (i + 1) % maxMatrius;
					}
					matriusCarregades[i] = matriusCarregades[matriuActual]
							.obtenirCopia("nomMatriu", i);
					logger.fine("Creada una matriu de dades còpia de l'actual amb nom "
							+ "nomMatriu");
					if (matriusCarregades[i] != null) {
						anterior = matriuActual;
						matriuActual = i;
						numMatriusOcupades++;
					}
				} else {
					logger.warning("Nombre màxim de matrius carregades al sistema.");
					throw new CreacioMatriuException(
							"Nombre màxim de matrius carregades al sistema.");
				}
			}
			// System.out.println("antes del vacioooooooooooooooooooooooooooooo");
			boolean vacio;
			System.out.println("antes del afegirMathola");
			int id;
			String nombre;
			id = matriusCarregades[matriuActual].obtenirId();
			System.out.println("identificador matriz actual" + id);
			nombre = matriusCarregades[matriuActual].obtenirNomMatriu();
			System.out.println("nombre matriz actual" + nombre);

			matriusCarregades[matriuActual].afegirMat(nomCls);
			i = matriuActual;
		} catch (Exception e) {
			// S'ha produit un error i no s'ha pogut carregar del tot la
			// classificació
			// Quan calgui es desfa la nova matriu
			if (anterior > -1) {
				matriuActual = anterior;
				numMatriusOcupades--;
			}
			throw e;
		}

		return i;
	}

	// alejandro creando hija
	public int crearHija() throws Exception, FileNotFoundException,
			FormatIncorrecteException, IOException, CreacioMatriuException,
			CreacioPropietatsException {
		int i = -1, anterior = -1;
		String nomMatriu = "";
		// System.out.println("hola entre a crear hija");
		nomMatriu = matriusCarregades[matriuActual].obtenirNomMatriu();
		// System.out.println("nomMatriu es"+nomMatriu);

		try {
			// if (novaMatriu) {
			if (numMatriusOcupades < maxMatrius - 1) {
				// Es busca la primera posició lliure del array
				i = numMatriusOcupades;
				// System.out.println("cuanto es i antes"+i);
				while (matriusCarregades[i] != null) {
					i = (i + 1) % maxMatrius;
				}
				nomMatriu = nomMatriu + "hija";
				matriusCarregades[i] = matriusCarregades[matriuActual]
						.obtenirCopia(nomMatriu, i);
				logger.fine("Creada una matriu de dades còpia de l'actual amb nom "
						+ nomMatriu);
				if (matriusCarregades[i] != null) {
					anterior = matriuActual;
					matriuActual = i;
					numMatriusOcupades++;
				}
			} else {
				logger.warning("Nombre màxim de matrius carregades al sistema.");
				throw new CreacioMatriuException(
						"Nombre màxim de matrius carregades al sistema.");
			}
			// }
			// System.out.println("antes del vacioooooooooooooooooooooooooooooo");

		} catch (Exception e) {
			// S'ha produit un error i no s'ha pogut carregar del tot la
			// classificació
			// Quan calgui es desfa la nova matriu
			if (anterior > -1) {
				matriuActual = anterior;
				numMatriusOcupades--;
			}
			throw e;
		}
		// System.out.println("Matriz acutal"+i);

		return i;
	}

	public int crearTemp() throws Exception, FileNotFoundException,
			FormatIncorrecteException, IOException, CreacioMatriuException,
			CreacioPropietatsException {
		int i = -1, anterior = -1;
		String nomMatriu = "";
		// System.out.println("hola entre a crear tempppppp");
		nomMatriu = matriusCarregades[matriuActual].obtenirNomMatriu();
		// System.out.println("nomMatriu es"+nomMatriu);

		try {
			// if (novaMatriu) {
			if (numMatriusOcupades < maxMatrius - 1) {
				// Es busca la primera posició lliure del array
				i = numMatriusOcupades;
				// System.out.println("cuanto es i antes"+i);
				while (matriusCarregades[i] != null) {
					i = (i + 1) % maxMatrius;
				}
				// System.out.println("cuanto es i despus de este while"+i);

				nomMatriu = nomMatriu + "temp";
				matriusCarregades[i] = matriusCarregades[matriuActual]
						.obtenirCopia(nomMatriu, i);
				logger.fine("Creada una matriu de dades còpia de l'actual amb nom "
						+ nomMatriu);
				if (matriusCarregades[i] != null) {
					anterior = matriuActual;
					matriuActual = i;
					numMatriusOcupades++;
				}
			} else {
				logger.warning("Nombre màxim de matrius carregades al sistema.");
				throw new CreacioMatriuException(
						"Nombre màxim de matrius carregades al sistema.");
			}
			// }
			// System.out.println("antes del vacioooooooooooooooooooooooooooooo");

		} catch (Exception e) {
			// S'ha produit un error i no s'ha pogut carregar del tot la
			// classificació
			// Quan calgui es desfa la nova matriu
			if (anterior > -1) {
				matriuActual = anterior;
				numMatriusOcupades--;
			}
			throw e;
		}
		// System.out.println("Matriz acutal"+i);

		return i;
	}

	public int crearHija2(String nom) throws Exception, FileNotFoundException,
			FormatIncorrecteException, IOException, CreacioMatriuException,
			CreacioPropietatsException {
		int i = -1, anterior = -1;
		// String nomMatriu="";
		// System.out.println("hola entre a crear hija");
		// nomMatriu = matriusCarregades[matriuActual].obtenirNomMatriu();
		// System.out.println("nomMatriu es"+nomMatriu);

		try {
			// if (novaMatriu) {
			if (numMatriusOcupades < maxMatrius - 1) {
				// Es busca la primera posició lliure del array
				i = numMatriusOcupades;
				// System.out.println("cuanto es i antes"+i);
				while (matriusCarregades[i] != null) {
					i = (i + 1) % maxMatrius;
				}
				// nomMatriu=nomMatriu+"hija";
				matriusCarregades[2] = matriusCarregades[0]
						.obtenirCopia(nom, 2);
				// System.out.println("hola en crearhija2 cree la copia"+nom);

				logger.fine("Creada una matriu de dades còpia de l'actual amb nom "
						+ nom);

				if (matriusCarregades[2] != null) {
					anterior = matriuActual;
					matriuActual = 2;
					numMatriusOcupades++;
				}
			} else {
				logger.warning("Nombre màxim de matrius carregades al sistema.");
				throw new CreacioMatriuException(
						"Nombre màxim de matrius carregades al sistema.");
			}
			// }
			// System.out.println("antes del vacioooooooooooooooooooooooooooooo");

		} catch (Exception e) {
			// S'ha produit un error i no s'ha pogut carregar del tot la
			// classificació
			// Quan calgui es desfa la nova matriu
			if (anterior > -1) {
				matriuActual = anterior;
				numMatriusOcupades--;
			}
			throw e;
		}
		// System.out.println("Matriz acutal"+i);

		return 2;
	}

	public String obtenirmatriz() {
		String nomMatriu = "";
		// System.out.println("hola entre a obtenir matriz");
		nomMatriu = matriusCarregades[0].obtenirNomMatriu();
		return nomMatriu;
	}

	// public int obteniractual(){
	// return matriuActual;
	// }

	public boolean activasdefinidas(boolean ok) {
		if (ok)
			return true;
		else
			return false;
	}

	public ArrayList obtenireliminadas(ArrayList alProps) {
		return alProps;
	}

	/**
	 * 
	 * @param Crida
	 *            a matriusCarregades[matriuActual].transformarParticio(nomfitx)
	 *            . Permet transformar un fitxer .par que indica una partició
	 *            llistant els objectes que conté cada classe en un fitxer .cls
	 *            que indica per cada objecte a quina classe pertany
	 * @return
	 * @throws FileNotFoundException
	 * @throws FormatIncorrecteException
	 * @throws IOException
	 * @throws ParamIncorrecteException
	 */
	public int transformarParticio(String nomFitx)
			throws FileNotFoundException, FormatIncorrecteException,
			IOException, ParamIncorrecteException {
		int i = -1;

		matriusCarregades[matriuActual].transformarParticio(nomFitx);
		i = 0;
		return i;
	}

	public ArrayList diferencia() {

		return matriusCarregades[matriuActual].dife();
	}

	public void eliminarultima() throws Exception {
		matriusCarregades[matriuActual].eliminarDarreraPropietat();
	};

	/**
	 * Canvia la matriu actual amb la que treballa el jklass.nucli per la que es
	 * troba a la posició indicada per <code> pos </code>
	 * 
	 * @param pos
	 * @return true, si hi ha una matriu carregada a la posició indicada; false,
	 *         altrament
	 */
	public boolean canviarMatriuActual(int pos) {
		boolean fet = false;

		if (matriusCarregades[pos] != null) {
			matriuActual = pos;
			fet = true;
		}
		// System.out.println( "estoy en canviarMatriuActual pos"+pos );
		return fet;
	}

	/**
	 * 
	 * @return
	 * @throws CreacioFitxerException
	 * @throws OpcioIncorrectaException
	 * @throws ParamIncorrecteException
	 * @throws CalculException
	 */
	public boolean generarInformeAuto() throws CreacioFitxerException,
			OpcioIncorrectaException, ParamIncorrecteException, CalculException {
		boolean fet = false;
		matriusCarregades[matriuActual].ferInformeAuto();
		fet = true;
		return fet;
	}

	/**
	 * 
	 * @param llistaVars
	 * @param llistaEstads
	 * @param opc
	 * @return
	 */
	public boolean ferDescrip(String[][] llistaVars, Vector[] llistaEstads,
			Opcions opc) {
		boolean fet = false;
		try {
			matriusCarregades[matriuActual].ferDescrip(llistaVars,
					llistaEstads, opc);
			fet = true;
		} catch (Exception e) {
			logger.warning(e.toString() + ": " + e.getMessage());
		}
		return fet;
	}

	public ArrayList ferActiv() {
		boolean fet = false;
		ArrayList activasPro = new ArrayList();
		try {
			activasPro = matriusCarregades[matriuActual].ferActiv();

			fet = true;
		} catch (Exception e) {
			logger.warning(e.toString() + ": " + e.getMessage());
		}
		return activasPro;
	}

	/**
	 * 
	 * @param dir
	 * @return
	 */
	public boolean actualitzarDirResultats(String dir) {
		return matriusCarregades[matriuActual].actualitzarDirResultats(dir);
	}

	/**
	 * 
	 * @return
	 */
	public String obtenirDirResultats() {
		return matriusCarregades[matriuActual].obtenirDirResultats();
	}

	/**
	 * 
	 * @return
	 */
	public String[][] obtenirLlistaIDsProps() {
		return matriusCarregades[matriuActual].obtenirLlistaProps()
				.llistarIDsPropietats();
	}

	public String[][] obtenirLlistaIDsPropsActivas(ArrayList activas) {
		return matriusCarregades[matriuActual].obtenirLlistaProps()
				.llistarIDsPropietatsEnBasicsA(activas);
	}

	/**
	 * 
	 * @param nomVar
	 * @return
	 */
	public String[] obtenirLlistaMods(String nomVar) {
		return matriusCarregades[matriuActual].obtenirLlistaMods(nomVar);
	}

	/**
	 * 
	 * @param nomVar
	 * @param llMods
	 * @return
	 */
	public boolean establirOrdreMods(String nomVar, String[] llMods) {
		boolean fet = false;
		try {
			matriusCarregades[matriuActual].establirOrdreMods(nomVar, llMods);
			fet = true;
		} catch (Exception e) {
			logger.warning(e.toString() + ": " + e.getMessage());
		}
		return fet;
	}

	public String informarIdArbre(String metode, String criteri, OpcionsDis opc) {
		return informarIdArbre(metode, criteri, opc, "");
	}

	public String informarIdArbreEst(String metode, String criteri,
			OpcionsDis opc, String est) {
		return informarIdArbre(metode, criteri, opc, est);
	}

	// public String informarIdArbre2(String metode, String criteri, OpcionsDis
	// opc, boolean activas) {
	// return informarIdArbre2(metode, criteri, opc, "",activas);
	// }

	public String informarIdArbre(String metode, String criteri,
			OpcionsDis opc, String optionalText) {

		String nouNom = obtenirIdArbre(metode, criteri, opc, optionalText);

		matriusCarregades[matriuActual].obtenirArbre().modificarNom(nouNom);
		// System.out.println("sale dee este informarIdArbre con nouNom "+nouNom);
		return nouNom;
	}

	// public String informarIdArbre2(String metode, String criteri, OpcionsDis
	// opc, String optionalText,boolean activas) {
	// String nouNom = obtenirIdArbre(metode, criteri, opc, optionalText);
	// if (activas = true){
	// System.out.println("entre en informarIdArbre2 con activas TRUE con matriz= "+
	// matriusCarregades[matriuActual].obtenirNomMatriuRelatiu());
	// matriusCarregades[matriuActual].obtenirArbre().modificarNom(nouNom);
	// }

	// else {
	// System.out.println("entre con activas FALSE en informarIdArbre2 con matriz= "+
	// matriusCarregades[matriuActual+1].obtenirNomMatriuRelatiu());
	// matriusCarregades[matriuActual+1].obtenirArbre().modificarNom(nouNom);
	// }

	// return nouNom;
	// }

	private static final String COLUMNA_AVALUACIO = "AvalInt";
	private int numEvalCol = 0;

	/*
	 * public String obtenirPropietatAvaluacio() { return COLUMNA_AVALUACIO +
	 * numEvalCol; }
	 */
	public String avaluaBCIClassifica(String nomBC, String criteri,
			String identificador, OpcionsDis opc, String tipus,
			boolean controlInversions, String arxiuHis, String arxiuDrm,
			boolean ficheroHis, boolean ficheroDrm/* , String propietat */)
			throws Exception {
		/*
		 * if (null == propietat || "".equals(propietat)) { propietat =
		 * COLUMNA_AVALUACIO + numEvalCol++; } else if
		 * (propietat.equals(COLUMNA_AVALUACIO + numEvalCol)) { numEvalCol++; }
		 * :
		 */
		String propietat = COLUMNA_AVALUACIO + numEvalCol;
		// matriusCarregades[matriuActual].avaluaReglaARegla(nomBC, false); :
		matriusCarregades[matriuActual].avaluaIntegrat(nomBC, false, propietat);
		String res = null;
		if (classificarDivisioBDD(propietat, criteri, identificador, opc,
				tipus, controlInversions, arxiuHis, arxiuDrm, ficheroHis,
				ficheroDrm)) {
			res = informarIdArbre("clbr", criteri, opc, nomBC);
		}
		matriusCarregades[matriuActual].eliminarDarreraPropietat();
		return res;
	}

	public String avaluaBCexo(String nomBC, String criteri,
			String identificador, OpcionsDis opc, String tipus,
			boolean controlInversions, String arxiuHis, String arxiuDrm,
			boolean ficheroHis, boolean ficheroDrm/* , String propietat */)
			throws Exception {
		/*
		 * if (null == propietat || "".equals(propietat)) { propietat =
		 * COLUMNA_AVALUACIO + numEvalCol++; } else if
		 * (propietat.equals(COLUMNA_AVALUACIO + numEvalCol)) { numEvalCol++; }
		 * :
		 */
		// System.out.println("entre en avaluaBCexo");
		String propietat = COLUMNA_AVALUACIO + numEvalCol;
		ArrayList lista;
		// matriusCarregades[matriuActual].avaluaReglaARegla(nomBC, false); :
		lista = matriusCarregades[matriuActual].avaluaIntegrat(nomBC, false,
				propietat);
		// String res = propietat;
		// if (classificarDivisioBDD(propietat , criteri, identificador, opc,
		// tipus, controlInversions, arxiuHis, arxiuDrm, ficheroHis,
		// ficheroDrm)) {
		// res = informarIdArbre("cc", criteri, opc, nomBC);
		// }
		// matriusCarregades[matriuActual].eliminarDarreraPropietat();
		// for(int i=0;i<lista.size();i++){
		// String s=(String)ordreProps.get(i);
		// System.out.println(" "+lista.get(i));

		// }

		return propietat;

	}

	private String obtenirIdArbre(String metode, String criteri,
			OpcionsDis opc, String optionalText) {
		// Mètode
		StringBuffer res = new StringBuffer(metode);
		// Criteri
		res.append(criteri.substring(0, 4));
		// Mètrica
		switch (opc.getTipus()) {
		case OpcionsDis.EUCLI:
			res.append("Euc");
			break;
		case OpcionsDis.ABS:
			res.append("Vabs");
			break;
		case OpcionsDis.MINKO:
			res.append("Min");
			break;
		case OpcionsDis.CHI2:
			res.append("Chi2");
			break;
		case OpcionsDis.HAMM:
			res.append("Ham");
			break;
		case OpcionsDis.GOWDA:
			res.append("GoDi");
			break;
		case OpcionsDis.ICHINO:
			res.append("Ichi");
			break;
		case OpcionsDis.GOWER:
			res.append("Gow");
			break;
		case OpcionsDis.RALAM:
			res.append("Ralam");
			break;
		case OpcionsDis.MIXTA:
			res.append("Gib");
			break;
		}
		// Part opcional
		// optionalText="alejandro";

		res.append(optionalText);
		// Seqüencial
		res.append(numArbre++);
		// System.out.println("sali de informar id arbre="+res);

		return res.toString();
	}

	public boolean classificarDivisioBDD(String nomVar, String criteri,
			String identificador, OpcionsDis opc, String tipus,
			boolean controlInversions, String arxiuHis, String arxiuDrm,
			boolean ficheroHis, boolean ficheroDrm) throws IOException,
			CreacioPropietatsException, CreacioObjectesException,
			CreacioMatriuException, FormatIncorrecteException {
		boolean resultat = false;
		if (numMatriusOcupades < maxMatrius) {
			// Dividir BDD
			// System.out.println("entre a clasificar divisiobdd con matrizactual="+matriuActual);
			GestorMatriuDividida[] matrius = matriusCarregades[matriuActual]
					.dividirBDD(nomVar, true);
			// Classificar particions no residuals
			int residualClass = GestorMatriuDividida.NO_RESIDUAL;
			int l = matrius.length;
			for (int i = 0; i < l; i++) {
				if (null != matrius[i]) {
					LlistaObjectes llObj = matrius[i].obtenirLlistaObjs();
					if (llObj.obtenirLong() > 0) {
						if (matrius[i].isResidual()) {
							residualClass = i;
						} else if (matrius[i].obtenirLlistaObjs().obtenirLong() > 1) {
							// String valorDominiVariable =
							// matrius[i].obtenirValorVariable(nomVar); :
							String valorDominiVariable = matrius[i]
									.obtenirValorDivisio();

							System.out
									.println("esta en clasificarDivisioBDD con valorDominiVariable"
											+ valorDominiVariable);
							matrius[i].classificarMatriu(criteri, opc,
									valorDominiVariable, tipus, null, null,
									false, false);
						}
					}
				}
			}
			// Gestionar partició residual
			GestorMatriu matriuResidual;
			if (residualClass == GestorMatriuDividida.NO_RESIDUAL) {
				matriuResidual = matriusCarregades[matriuActual]
						.obtenirResidual(l, nomVar);
			} else {
				// if (residualClass >= 0) {
				matriuResidual = matrius[residualClass];
				matriuResidual.resizeNumDades(l - 1);
				/*
				 * } else { matriuResidual =
				 * matriusCarregades[matriuActual].obtenirResidual(l);
				 */
			}
			// Afegir les arrels i els elements de particions unàries
			for (int curMatrix = 0; curMatrix < l; curMatrix++) {
				if (curMatrix != residualClass && null != matrius[curMatrix]) {
					if (1 == matrius[curMatrix].obtenirLlistaObjs()
							.obtenirLong()) {
						Objecte obj = matrius[curMatrix].obtenirLlistaObjs()
								.obtenirObjecte(0);
						Dada[] dades = matrius[curMatrix].obtenirDades()
								.obtenirFila(0);
						matriuResidual.afegirObjecte(obj, dades);
					} else {
						ArbreClassif arbre = matrius[curMatrix].obtenirArbre();
						String ident = arbre.obtenirArrel().obtenirEtiqueta();
						System.out.println("a ver ident ale" + ident);

						float pes = arbre.obtenirPesArrel();
						Dada[] dades = arbre.obtenirDadesArrel();
						// arbre.obtenirArrel().obtenirIndexNivell();
						matriuResidual.afegirClass(ident, pes, dades);
					}
				}
			}
			// Classificar la matriu residual
			/*
			 * String valorDominiVariable =
			 * matriuResidual.obtenirValorVariable(nomVar);
			 * matriuResidual.classificarMatriu(metode, criteri, opc,
			 * valorDominiVariable); :
			 */
			resultat = matriuResidual.classificarMatriu(criteri, opc,
					identificador, tipus, null, null, false, false);

			// Penjar de les arrels dels subarbres el corresponent subarbre
			for (int curMatrix = 0; curMatrix < l; curMatrix++) {
				if (!(curMatrix == residualClass || null == matrius[curMatrix] || 1 == matrius[curMatrix]
						.obtenirLlistaObjs().obtenirLong())) {
					matriuResidual.canviarNodePerArbre(
							matrius[curMatrix].obtenirArbre(),
							controlInversions);
				}
			}
			// Penjar l'arbre resultant a la matriu actual
			matriusCarregades[matriuActual].agregarArbol(matriuResidual
					.obtenirArbre());
			// Guardar l'arbre a disc si s'escau
			if (ficheroHis || ficheroDrm) {
				GestorClasificacion gc = new GestorClasificacion(matriuResidual);
				if (ficheroHis) {
					gc.modifFicheroResultadosHis(arxiuHis);
					gc.crearFitxerHis(criteri, opc);
				}
				if (ficheroDrm) {
					gc.modifFicheroResultadosDrm(arxiuDrm);
					String nomArbre = matriusCarregades[matriuActual]
							.obtenirDirResultats() + arxiuDrm;
					gc.obtenirArbol().crearFitxerDendo(nomArbre);
				}
			}
		} else {
			logger.warning("No es pot dividir la BDD perque s'ha escedit el nombre màxim de matrius.");
			throw new CreacioMatriuException(
					"No es pot dividir la BDD perque s'ha escedit el nombre màxim de matrius.");
		}
		// System.out.println("sali del classificarDivisioBDD");
		return resultat;
	}

	public Hashtable<Integer, String> dividirBDD(String nomVar,
			boolean guardarADisc) throws CreacioMatriuException,
			CreacioPropietatsException, IOException {
		GestorMatriu[] matrius = matriusCarregades[matriuActual]
				.dividirBDD(nomVar);
		int l = matrius.length;
		Hashtable<Integer, String> res = new Hashtable<Integer, String>();
		if ((guardarADisc && numMatriusOcupades < maxMatrius)
				|| numMatriusOcupades < maxMatrius - l) {
			int p = matriuActual + 1;
			for (int i = 0; i < l; i++) {
				if (null != matrius[i]) {
					while (matriusCarregades[p] != null) {
						p = ++p % maxMatrius;
					}
					matriusCarregades[p] = matrius[i];
					LlistaObjectes llObj = matriusCarregades[p]
							.obtenirLlistaObjs();
					if (llObj.obtenirLong() > 0) {
						String domVarValue = matriusCarregades[p]
								.obtenirValorVariable(nomVar);
						if (guardarADisc) {
							String fileName = matriusCarregades[p]
									.obtenirNomMatriu() + "_" + domVarValue;
							File tmpFile = new File(fileName);
							fileName = tmpFile.getParent()
									+ matriusCarregades[p]
											.obtenirDirResultats()
									+ tmpFile.getName();
							matriusCarregades[p].desarMatriu(fileName);
							matriusCarregades[p] = null;
						} else {
							String matrixName = matriusCarregades[p]
									.obtenirNomMatriuRelatiu()
									+ "_"
									+ domVarValue;
							res.put(p, matrixName);
							p++;
							numMatriusOcupades += 1;
						}
					}
				}
			}
		} else {
			logger.warning("No es pot dividir la BDD perque s'ha escedit el nombre màxim de matrius.");
			throw new CreacioMatriuException(
					"No es pot dividir la BDD perque s'ha escedit el nombre màxim de matrius.");
		}
		return res;

	}

	/**
	 * 
	 * @param llMods
	 * @return
	 */
	public boolean establirOrdreVars(String[] llMods) {
		boolean fet = false;
		try {
			matriusCarregades[matriuActual].establirOrdreVars(llMods);
			fet = true;
		} catch (Exception e) {
			logger.warning(e.toString() + ": " + e.getMessage());
		}
		return fet;
	}

	/**
	 * 
	 * @param llMods
	 * @return
	 */
	public boolean establirActiveVars(String[] llMods) {
		boolean fet = false;
		try {
			matriusCarregades[matriuActual].establirActiveVars(llMods);
			fet = true;
		} catch (Exception e) {
			logger.warning(e.toString() + ": " + e.getMessage());
		}
		return fet;
	}

	public int LenActiveProps() {
		System.out.println(matriusCarregades[matriuActual].nom);
		return matriusCarregades[matriuActual].LenActiveProps();

	}

	// public boolean establirActiveVars(String[] llMods){
	// boolean fet = false;
	// String nom="activas";
	// for (int i = 0; i < llMods.length; i++) {

	// System.out.println("estoy en GestorKlass="+llMods[i]);
	// }

	// try{
	// eliminarInactivas(llMods);
	// fet = true;
	// } catch (Exception e){
	// logger.warning(e.toString() + "establirActiveaca " + e.getMessage());
	// }

	// return fet;
	// }

	// public boolean eliminarInactivas(String[] llMods) {
	// boolean fet = false;
	// try {
	// matriusCarregades[matriuActual].eliminarinac(llMods);
	// fet = true;
	// }
	// catch (Exception e) {
	// logger.warning(e.toString() + ": " + e.getMessage());
	// }
	// return fet;
	// }

	/**
	 * 
	 * @param llVars
	 * @return
	 * @throws IOException
	 * @throws CreacioMatriuException
	 */
	public int crearSubmatriuVars(String[] llVars) throws IOException,
			CreacioMatriuException {
		int i = -1;

		if (numMatriusOcupades < maxMatrius - 1) {
			// Es busca la primera posició lliure del array
			i = numMatriusOcupades;
			while (matriusCarregades[i] != null) {
				i = (i + 1) % maxMatrius;
			}
			/**
			 * @todo Cal acabar la implementació d'aquesta funcionalitat:
			 *       matriusCarregades[i] =
			 *       matriusCarregades[matriuActual].crearSubmatriu(llVars,
			 *       null);
			 */
			matriusCarregades[i] = null;
			i = -1;
			if (matriusCarregades[i] != null) {
				matriuActual = i;
				numMatriusOcupades++;
			}
		} else {
			logger.warning("Nombre màxim de matrius carregades al sistema.");
			throw new CreacioMatriuException(
					"Nombre màxim de matrius carregades al sistema.");
		}
		return i;
	}

	/**
	 * 
	 * @param llistaVars
	 * @param fitxers
	 * @return
	 */
	public boolean discretitzar(String[][] llistaVars, boolean fitxers) {
		boolean fet = false;
		try {
			matriusCarregades[matriuActual].discretitzar(llistaVars, fitxers);
			fet = true;
		} catch (Exception e) {
			logger.warning(e.toString() + ": " + e.getMessage());
		}
		return fet;
	}

	/**
	 * 
	 * @return
	 * @throws CreacioFitxerException
	 */
	public boolean visualitzaArbre() throws CreacioFitxerException {
		FitxerTex fitxer;
		return matriusCarregades[matriuActual].visualitzaArbre();
	}

	public boolean visualitzaArbre2(boolean activas)
			throws CreacioFitxerException {
		FitxerTex fitxer;
		if (activas = true) {
			// System.out.println("entre VISUALITZA ARBRE con activas TRUE con matriz= "+
			// matriusCarregades[matriuActual].obtenirNomMatriuRelatiu());
			return matriusCarregades[matriuActual].visualitzaArbre();
		}

		else {
			// System.out.println("entre con activas FALSE con matriz= "+
			// matriusCarregades[matriuActual+1].obtenirNomMatriuRelatiu());
			return matriusCarregades[matriuActual + 1].visualitzaArbre();
		}
	}

	public String nomActual(boolean activas) throws CreacioFitxerException {
		FitxerTex fitxer;
		if (activas = true) {
			// System.out.println("entre VISUALITZA ARBRE con activas TRUE con matriz= "+
			// matriusCarregades[matriuActual].obtenirNomMatriuRelatiu());
			return matriusCarregades[matriuActual].obtenirNomMatriu();
		}

		else {
			// System.out.println("entre con activas FALSE con matriz= "+
			// matriusCarregades[matriuActual].obtenirNomMatriuRelatiu());
			return matriusCarregades[matriuActual].obtenirNomMatriu();
		}
	}

	/**
	 * 
	 * @param tipus
	 * @param numTall
	 * @param visual
	 * @param etiqRest
	 * @param fitxers
	 * @param nomTall
	 * @return
	 */
	public boolean tallaArbre(char tipus, String numTall, boolean visual,
			boolean etiqRest, boolean reglas, boolean fitxers, String nomTall) {
		boolean fet = false;
		try {
			matriusCarregades[matriuActual].tallaArbre(tipus, numTall, visual,
					etiqRest, reglas, fitxers, nomTall);
			fet = true;
		} catch (Exception e) {
			logger.warning(e.toString() + ": " + e.getMessage());
		}
		return fet;
	}

	/**
	 * ¡¡¡ De moment només es té un arbre associat a la matriu de dades!!!
	 * 
	 * @return
	 */
	public String obtenirIdnArrelArbre() {
		return matriusCarregades[matriuActual].obtenirIdnArrelArbre(0);
	}

	/**
	 * ¡¡¡ De moment només es té un arbre associat a la matriu de dades!!!
	 * 
	 * @return
	 */
	public int obtenirNumNodesInternsArbre() {
		return matriusCarregades[matriuActual].obtenirNumNodesInternsArbre(0);
	}

	/**
	 * ¡¡¡ De moment només es té un arbre associat a la matriu de dades!!!
	 * 
	 * @return
	 */
	public String obtenirNomArbre() {
		// System.out.println("ESTOY en obtenirNomArbre con matriz actual="+matriuActual);

		return matriusCarregades[matriuActual].obtenirNomArbre(0);

	}

	// DISTANCIES*********************************************************************
	/**
	 * Eliminar la matriu amb identificador i
	 * 
	 * @param i
	 *            identificador
	 * 
	 * @author Jose I Mateos
	 * @version v.0 20/6/06
	 */
	public void eliminarMatriu(int i) {
		matriusCarregades[i] = null;
		numMatriusOcupades--;
	}

	/**
	 * Calcula si hi ha o no missings a la matriu actual
	 * 
	 * @return cert o fals depenent si hi ha o no missings a la matriu
	 * 
	 * @author Jose I Mateos
	 * @version v.0 Data: 20/6/06
	 */
	public boolean obtenirMiss() {
		return matriusCarregades[matriuActual].obtenirMiss();
	}

	/**
	 * Llança obtenir les distancies de la matriu actual
	 * 
	 * @param opc
	 *            conte la distancia seleccionada
	 * 
	 * @return la matriu de distancies
	 * 
	 * @author Jose I Mateos
	 * @version v.0 10/6/06
	 */
	public String[][] obtenirDistancies(OpcionsDis opc) {
		return matriusCarregades[matriuActual].obtenirDistancies(opc);
	}

	/**
	 * Llança obtenir les distancies de 2 objectes de la matriu actual
	 * 
	 * @param i1
	 *            es l'objecte1 i i2 objecte2
	 * @param i2
	 *            es l'objecte2 i i2 objecte2
	 * @param opc
	 *            conte la distancia seleccionada
	 * 
	 * @return la matriu de distancies
	 * 
	 * @author Jose I Mateos
	 * @version v.0 10/6/06
	 */
	public String obtenirDis2Obj(int i1, int i2, OpcionsDis opc) {
		return matriusCarregades[matriuActual].obtenirDis2Obj(i1, i2, opc);
	}

	/**
	 * Llança obtenir la llista dels objectes de la matriu actual
	 * 
	 * @return la llista d'objectes
	 * 
	 * @author Jose I Mateos
	 * @version v.0 10/6/06
	 */
	public String[] obtenirLlistaIDsObjs() {
		return matriusCarregades[matriuActual].obtenirLlistaObjs()
				.llistarIDsObjs();
	}

	/**
	 * Llança guardar els l'arxius de distancies
	 * 
	 * @param distan
	 *            es la martiu de les dades a escriure
	 * 
	 * @author Jose I Mateos
	 * @version v.0 10/6/06
	 */
	public void escriuMatriuDis(Object[][] distan, OpcionsDis opc) {
		matriusCarregades[matriuActual].escriuMatriuDis(distan, opc);
	}

	/**
	 * Llança guardar els l'arxius de distancies en latex
	 * 
	 * @param distan
	 *            es la martiu de les dades a escriure
	 * 
	 * @author Jose I Mateos
	 * @version v.0 16/2/07
	 */
	public void escriuMatriuDisTex(Object[][] distan, int colum, OpcionsDis opc) {
		matriusCarregades[matriuActual].escriuMatriuDisTex(distan, colum, opc);
	}

	/**
	 * Llança guardar els l'arxius de correlacio
	 * 
	 * @param cor
	 *            es la martiu de les dades a escriure
	 * 
	 * @author Jose I Mateos
	 * @version v.0 10/6/06
	 */
	public void escriuMatriuCo(Object[][] cor, String opc) {
		matriusCarregades[matriuActual].escriuMatriuCo(cor, opc);
	}

	/**
	 * Crea l'arxiu .dat per les distancies calculades per dades introduides per
	 * l'usuari
	 * 
	 * @param num
	 *            son les dades numeriques de la matriz
	 * @param categ
	 *            son les dades categoriques de la matriu
	 * @param filesN
	 *            indica quates files de variables numeriques estan plenes
	 * @param filesC
	 *            indica quates files de variables categoriques estan plenes
	 * 
	 * @author Jose I Mateos
	 * @version v.0 10/6/06
	 */
	public void crearMatriuDat(Object[][] num, Object[][] categ, int filesN,
			int filesC) {

		FitxerUsuDat f = new FitxerUsuDat(".\\dades\\usuari");
		String linea;
		try {
			f.obrirPerEscriure(false);
			linea = "((";
			for (int i = 0; i < filesN; i++) {
				linea = linea + " " + num[i][0];
			}
			for (int i = 0; i < filesC; i++) {
				linea = linea + " " + categ[i][0];
			}
			linea = linea + "))";
			f.escriureLin(linea);

			linea = "((";
			for (int i = 0; i < filesN; i++) {
				linea = linea + " " + num[i][1];
			}
			for (int i = 0; i < filesC; i++) {
				linea = linea + " " + categ[i][1];
			}
			linea = linea + "))";
			f.escriureLin(linea);

			f.tancarEsc();
		} catch (IOException e) {
		}
	}

	/**
	 * Crea l'arxiu .obj per les distancies calculades per dades introduides per
	 * l'usuari
	 * 
	 * @param llObjs
	 *            es una vector ambs els noms del objectes
	 * 
	 * @author Jose I Mateos
	 * @version v.0 10/6/06
	 */
	public void crearMatriuObj(String[] llObjs) {

		FitxerUsuObj f = new FitxerUsuObj(".\\dades\\usuari");
		String linea;
		int lon = llObjs.length;
		try {
			f.obrirPerEscriure(false);
			for (int i = 0; i < lon; i++) {
				linea = "((" + i + " " + llObjs[i] + ") nil)";
				f.escriureLin(linea);
			}
			f.tancarEsc();
		} catch (IOException e) {
		}
	}

	/**
	 * Crea l'arxiu .dat per les distancies calculades per dades introduides per
	 * l'usuari
	 * 
	 * @param min
	 *            conte el valor minin observat necesari per crear l'arxiu de
	 *            propietats
	 * @param max
	 *            conte el valor maxim observat necesari per crear l'arxiu de
	 *            propietats
	 * @param filesN
	 *            indica quates files de variables numeriques estan plenes
	 * @param filesC
	 *            indica quates files de variables categoriques estan plenes
	 * @param categ
	 *            es un vector de vectors en que cada propietat categorica conte
	 *            els valors observats necesaris per crear l'arxiu de propietats
	 * 
	 * @author Jose I Mateos
	 * @version v.0 10/6/06
	 */
	public void crearMatriuPro(double min, double max, int filesN, int filesC,
			Object[][] categ) {

		int i;
		int j;
		FitxerUsuPro f = new FitxerUsuPro(".\\dades\\usuari");
		String linea;

		try {
			f.obrirPerEscriure(false);
			for (i = 0; i < filesN; i++) {
				linea = "(( " + i + " " + "X" + (i + 1) + " )" + "((" + min
						+ " " + max + ") 1 2 C NIL))";
				f.escriureLin(linea);
			}
			for (j = 0; j < filesC; j++) {
				linea = "(( " + (i + j) + " " + "X" + (i + j + 1) + " ) "
						+ "((NIL 1 2 Q (";
				linea = linea + categ[j][0] + " " + categ[j][1];
				linea = linea + ")))";
				f.escriureLin(linea);
			}
			f.tancarEsc();
		} catch (IOException e) {
		}
	}

	/**
	 * Retorna l'identificador de la matriu actual
	 * 
	 * @return identificador
	 * 
	 * @author Jose I Mateos
	 * @version v.0 10/6/06
	 */
	public int idMatriuActual() {
		return matriuActual;
	}

	/**
	 * Deixa com a mariu actual la l'identificador i
	 * 
	 * @param i
	 *            es l'identificar de la matriu dins el gestor
	 * 
	 * @author Jose I Mateos
	 * @version v.0 10/6/06
	 */
	public void selecIdMatriuActual(int i) {
		matriuActual = i;
	}

	/**
	 * Crea una submatriu a partir del nom de la matriu pare, de l'identificador
	 * del pare i les porpietats propSelec y objectes objSelec que son un
	 * subconjunt de la matriu pare
	 * 
	 * @param nom
	 *            es el nom de l'arxiu
	 * @param idMatriuPare
	 *            es l'identificador de la matriu pare de la que treiem la
	 *            submatriu
	 * @param propSelec
	 *            es la llista de propietats seleccionades
	 * @param objSelec
	 *            es la llista d¡objectes seleccionats
	 * 
	 * @throws CreacioMatriuException
	 * @throws CreacioPropietatsException
	 * @throws CreacioObjectesException
	 * 
	 * @return l'identificador de la nova matriu
	 * 
	 * @author Jose I Mateos
	 * @version v.0 6/8//06
	 */
	public int carregarSubMatriu(String nom, int idMatriuPare,
			String[] propSelec, String[] objSelec)
			throws CreacioMatriuException, CreacioPropietatsException,
			CreacioObjectesException {

		int i = -1;

		if (numMatriusOcupades < maxMatrius - 1) {
			// Es busca la primera posició lliure del array
			i = numMatriusOcupades;
			while (matriusCarregades[i] != null) {
				i = (i + 1) % maxMatrius;
			}
			matriusCarregades[i] = new GestorMatriu(nom, i,
					matriusCarregades[idMatriuPare], propSelec, objSelec);
			if (matriusCarregades[i] != null) {
				matriuActual = i;
				numMatriusOcupades++;
			}
		} else {
			logger.warning("Nombre màxim de matrius carregades al sistema.");
			throw new CreacioMatriuException(
					"Nombre màxim de matrius carregades al sistema.");
		}
		return i;
	}

	public int loadSubMatrix(String name, int idParentMatrix,
			String[] prop_num, String[] prop_cat, String next_cut_property,
			String discr_property, String value) throws CreacioMatriuException,
			CreacioPropietatsException, CreacioObjectesException {
		Vector<String> v = new Vector<String>();
		for (String s : prop_num) {
			v.add(s);
		}
		for (String s : prop_cat) {
			v.add(s);
		}
		v.add(next_cut_property);
		String[] properties = new String[v.size()];
		v.toArray(properties);

		Dada[] column = obtenirColumna(discr_property);
		Vector<String> obj = new Vector<String>();
		int i, newMatrix;
		for (i = 0; i < column.length; i++) {
			Dada d = column[i];
			if (value.compareTo((String) d.obtenirValor()) == 0) {
				obj.add(obtenirLlistaIDsObjectes()[i]);
			}
		}
		String[] obj2 = new String[obj.size()];
		obj.copyInto(obj2);
		newMatrix = carregarSubMatriu(name, idParentMatrix, properties, obj2);
		return newMatrix;
	}

	/**
	 * Llança l'eliminacio dels missings de la matriu actual per el valor i
	 * 
	 * @param i
	 *            indica si es 0 o es per la mitjana la substitucio
	 * 
	 * @author Jose I Mateos
	 * @version v.0 6/8//06
	 */
	public void subs(int i) {
		matriusCarregades[matriuActual].subs(i);
	}

	/**
	 * Llança obtenir el calcul de Alfa i Beta de la matriu
	 * 
	 * @param opc
	 *            conte la distancia seleccionada
	 * 
	 * @author Jose I Mateos
	 * @version v.0 10/8/06
	 */
	public void obtenirAlfaBeta(OpcionsDis opc) {
		matriusCarregades[matriuActual].obtenirAlfaBeta(opc);
	}

	/**
	 * Llança obtenir la matriu de correlacions de la matriu actual
	 * 
	 * @param e
	 *            factor que eleva la corelació
	 * 
	 * @return la matriu de correlacions
	 * 
	 * @author Jose I Mateos
	 * @version v.0 10/8/06
	 */
	public String[][] correlacio(int e) {
		return matriusCarregades[matriuActual].correlacio(e);
	}

	/**
	 * Llança obtenir la matriu de covariances de la matriu actual
	 * 
	 * 
	 * @return la matriu de covariances
	 * 
	 * @author Jose I Mateos
	 * @version v.0 12/2/07
	 */
	public String[][] covarianza() {
		return matriusCarregades[matriuActual].covarianza();
	}

	/**
	 * Llança obtenir la suma de les modalitats de la matriu actual
	 * 
	 * @return la suma de les diferents modalitats
	 * 
	 * @author Jose I Mateos
	 * @version v.0 10/8/06
	 */
	public int valorModal() {
		return matriusCarregades[matriuActual].valorModal();
	}

	/**
	 * Llança obtenir la suma de les correlacions de la matriu actual
	 * 
	 * @return la suma de la matriu de correlacions
	 * 
	 * @author Jose I Mateos
	 * @version v.0 10/8/06
	 */
	public float valorCorrelacio() {
		return matriusCarregades[matriuActual].valorCorrelacio();
	}

	public void prova() {

		matriusCarregades[matriuActual].prova();
	}

	// Funciones que necesito -- Rober
	/**
	 * Funcio que realitza la classificacio de la Matriu Actual
	 * 
	 * @ param metode. "Veïns recíprocs encadenats". @ param criteri.
	 * "Centroide" y "Ward". @ param opc. String que conte les opcions de
	 * classificació. @ param tipus. "Gibert" y "Moda". @ param archivoHis.
	 * String que indica el nom del Fitxer *.his. @ param archivoDrm. String que
	 * indica el nom del Fitxer *.drm. @ param ficheroHis. Boolea que indica si
	 * s'ha de crear el Fitxer *.his. @ param ficheroDrm. Boolea que indica si
	 * s'ha de crear el Fitxer *.drm. @ return
	 * 
	 * <p>
	 * Title: Java-KLASS
	 * </p>
	 * <p>
	 * Description: Paquet estadístic
	 * </p>
	 * <p>
	 * Copyright: Copyright (c) 2006
	 * </p>
	 * <p>
	 * </p>
	 * 
	 * @author Rober CLASSIFICACIÖ
	 * @version v.0
	 */

	/*
	 * public boolean classificarMatriu( String metode, String criteri,
	 * OpcionsDis opc, String identificador, String tipus, String archivoHis,
	 * String archivoDrm, boolean ficheroHis, boolean ficheroDrm) throws
	 * CreacioMatriuException, CreacioPropietatsException,
	 * CreacioObjectesException{ boolean clasificada;
	 * 
	 * clasificada = matriusCarregades[matriuActual].classificarMatriu(
	 * metode,criteri,opc, identificador, tipus, archivoHis, archivoDrm,
	 * ficheroHis, ficheroDrm); return clasificada; } :
	 */
	public boolean classificarMatriu(/* String metode, */String criteri,
			OpcionsDis opc, String identificador, String tipus,
			String archivoHis, String archivoDrm, boolean ficheroHis,
			boolean ficheroDrm/* , boolean controlInversions */)
			throws CreacioMatriuException, CreacioPropietatsException,
			CreacioObjectesException {
		return matriusCarregades[matriuActual].classificarMatriu(
		/* metode, */criteri, opc, identificador, tipus, archivoHis,
				archivoDrm, ficheroHis, ficheroDrm);
	}

	public boolean classificarMatriuEst(/* String metode, */String est,
			String[] varsest, String criteri, OpcionsDis opc,
			String identificador, String tipus, String archivoHis,
			String archivoDrm, boolean ficheroHis, boolean ficheroDrm/*
																	 * , boolean
																	 * controlInversions
																	 */)
			throws CreacioMatriuException, CreacioPropietatsException,
			CreacioObjectesException {
		return matriusCarregades[matriuActual].classificarMatriuEst(
		/* metode, */est, varsest, criteri, opc, identificador, tipus,
				archivoHis, archivoDrm, ficheroHis, ficheroDrm);
	}

	public boolean classificarMatriu2(/* String metode, */String criteri,
			OpcionsDis opc, String identificador, String tipus,
			String archivoHis, String archivoDrm, boolean ficheroHis,
			boolean ficheroDrm, boolean activas /* , boolean controlInversions */)
			throws CreacioMatriuException, CreacioPropietatsException,
			CreacioObjectesException {
		if (activas = true) {
			// System.out.println("entre con activas TRUE con matriz= "+
			// matriusCarregades[matriuActual].obtenirNomMatriuRelatiu());
			return matriusCarregades[matriuActual].classificarMatriu(
			/* metode, */criteri, opc, identificador, tipus, archivoHis,
					archivoDrm, ficheroHis, ficheroDrm);
		}

		else {
			// System.out.println("entre con activas FALSE con matriz= "+
			// matriusCarregades[matriuActual+1].obtenirNomMatriuRelatiu());
			return matriusCarregades[matriuActual + 1].classificarMatriu(
			/* metode, */criteri, opc, identificador, tipus, archivoHis,
					archivoDrm, ficheroHis, ficheroDrm);
		}
	}

	/**
	 * Funcio que genera un objecte GestorClasificacion
	 * 
	 * @ param @ return gc. Retorna un objecte GestorClasificacio amb les dades
	 * del GestorMatriu actual
	 * 
	 * <p>
	 * Title: Java-KLASS
	 * </p>
	 * <p>
	 * Description: Paquet estadístic
	 * </p>
	 * <p>
	 * Copyright: Copyright (c) 2006
	 * </p>
	 * <p>
	 * </p>
	 * 
	 * @author Rober CLASSIFICACIÖ
	 * @version v.0
	 */

	public GestorClasificacion crearGestorClasificacion()
			throws FileNotFoundException, FormatIncorrecteException,
			IOException {
		GestorClasificacion gc;

		gc = matriusCarregades[matriuActual].crearGestorClasif();
		return gc;
	}

	/**
	 * Funcio que carrega una nova matriu de Dades a partir de un
	 * gestorClasificacio
	 * 
	 * @ param nom. Te el nom de la matriu actual @ param gestorClasifica. Te el
	 * objecte a partir del cual es creara el nou GestorMatriu @ return i.
	 * Retorna la posicio de la matriu actual
	 * 
	 * <p>
	 * Title: Java-KLASS
	 * </p>
	 * <p>
	 * Description: Paquet estadístic
	 * </p>
	 * <p>
	 * Copyright: Copyright (c) 2006
	 * </p>
	 * <p>
	 * </p>
	 * 
	 * @author Rober CLASSIFICACIÖ
	 * @version v.0
	 */

	public int carregarMatriuAgregada(String nom,
			GestorClasificacion gestorClasifica) throws CreacioMatriuException,
			CreacioPropietatsException, CreacioObjectesException {
		int i = -1;

		if (numMatriusOcupades < maxMatrius - 1) {
			// Es busca la primera posició lliure del array
			i = numMatriusOcupades;
			while (matriusCarregades[i] != null) {
				i = (i + 1) % maxMatrius;
			}
			matriusCarregades[i] = new GestorMatriu(nom, i, gestorClasifica);

			if (matriusCarregades[i] != null) {
				matriuActual = i;
				numMatriusOcupades++;
			}
		} else {
			logger.warning("Nombre màxim de matrius carregades al sistema.");
			throw new CreacioMatriuException(
					"Nombre màxim de matrius carregades al sistema.");
		}
		return i;
	}

	/**
	 * Funció que retrona el nom de la matriu sense els direcctoris on es
	 * guardat el fitxer.
	 * 
	 * @ param @ return String amb el nom de la matriu sense els direcctoris on
	 * es guardat el fitxer.
	 * 
	 * <p>
	 * Title: Java-KLASS
	 * </p>
	 * <p>
	 * Description: Paquet estadístic
	 * </p>
	 * <p>
	 * Copyright: Copyright (c) 2006
	 * </p>
	 * <p>
	 * </p>
	 * 
	 * @author Rober CLASSIFICACIÖ
	 * @version v.0
	 */

	public String obtenirNom() {
		String nom;

		nom = matriusCarregades[matriuActual].obtenirNomMatriuRelatiu();
		return nom;
	}

	// LAIA*******************************************************************************
	/**
	 * Retorna la Base de coneixement creada a partir del fitxer
	 * <code>spath</code>
	 * 
	 * @return BaseConeixement
	 * @author Laia Riera Guerra
	 */
	public BaseConeixement carregarRegles(String spath, String snom)
			throws Exception {
		// System.out.println("Estoy en carregarREgles ale con spath"+spath+"y snom"+snom);
		System.out.println("Antes de crearBC");
		BaseConeixement bc = matriusCarregades[matriuActual].crearBC(snom);
		System.out.println("Despues de crearBC");
		try {
			System.out.println("Antes de importarbc");
			bc.importarBaseConeixement(spath);
			System.out.println("Despues de importarBC");
		}

		catch (Exception e) {
			// System.out.println("Catch");
			matriusCarregades[matriuActual].eliminarBC(snom);
			throw new Exception(e.getMessage());
		}
		return bc;
	}

	// //////////////////////////aleeeeeeeeeeeeeeee
	public String[] importarActivas(String spath) throws Exception {
		String slin = null;
		String res[] = new String[1000];
		int j = 0;
		// System.out.println("Estoy en importarActivas con sNom"+spath);
		try {

			FitxerAct fAct = new FitxerAct(spath);
			fAct.obrirPerLlegir();
			String slinia = fAct.llegirLinia();
			// System.out.println("lei la primer linia"+slinia);

			if (slinia.length() == 0)
				throw new Exception(
						"La base de coneixement està buida o la primera línia està en blanc");
			else {
				while (slinia != null) {// suposem format correcte,no deixa
										// espais
					// al principi del fitxer ni entre línies.
					// Un cop es llegeix una línia en blanc es para de llegir
					slinia = slinia.trim();
					res[j] = slinia;
					j++;
					slin = slin + slinia;
					slinia = fAct.llegirLinia();

				}// endwhile el while termina acaaaaaaaaaaaaaaaa
			}// endif
				// System.out.println("sali del while con esto"+slin);
			fAct.tancarLec();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return res;
	}// endofmethod

	// //////////////////////alejandro
	public String[] carregarAct(String spath) throws Exception {
		// System.out.println("Estoy en carregarAct ale con spath"+spath);
		// BaseConeixement bc=matriusCarregades[matriuActual].crearBC(snom);
		String[] res;
		try {
			res = importarActivas(spath);
		} catch (Exception e) {
			// System.out.println("Catch");
			// matriusCarregades[matriuActual].eliminarBC(snom);
			throw new Exception(e.getMessage());
		}
		return res;
	}

	// /////////////////alejandro

	/**
	 * Exporta la Base de coneixement <code>bc</code> al fitxer de nom
	 * <code>spath</code>
	 * 
	 * @param <code>spath</code>, nom del fitxer on s'exportarà la Base de
	 *        Coneixement
	 * @param <code>bc</code>, Base de Coneixement que es vol exportar
	 * @throws Exception
	 *             , si es produeix algun error durant el procés d'exportació
	 * @author Laia Riera Guerra
	 */
	public void desarRegles(String spath, BaseConeixement bc) throws Exception {
		bc.exportarBaseConeixement(spath, bc.obtenirLlista());
	}

	/**
	 * Obté un vector amb els noms de les bases de coneixement associades a la
	 * matriu de dades
	 * 
	 * @return String[] amb els noms de les bases de coneixement
	 * @author Laia Riera Guerra
	 */
	public String[] obtenirLlistaNomsBC() {
		String[] idBC;
		ArrayList al = matriusCarregades[matriuActual].obtenirNomsBC();
		idBC = new String[al.size()];
		for (int i = 0; i < al.size(); i++) {
			idBC[i] = al.get(i).toString();
		}
		return idBC;
	}

	public String[] obtenirLlistaArbres() {
		return matriusCarregades[matriuActual].obtenirLlistaNomArbres();
	}

	/**
	 * Obté un vector amb els noms de les regles pertanyents a la base de
	 * coneixement <code>nomBC</code>
	 * 
	 * @param nomBC
	 *            , BaseConeixement de la qual es volen obtenir els noms de les
	 *            regles
	 * @return String[] amb els noms de les regles de la base de coneixement
	 * @author Laia Riera Guerra
	 */
	public String[] obtenirNomsRegles(BaseConeixement nomBC) {
		String[] Regles;
		ArrayList al = matriusCarregades[matriuActual].obtenirReglesBC(nomBC);
		Regles = new String[al.size()];
		for (int i = 0; i < al.size(); i++) {
			Regles[i] = al.get(i).toString();
		}
		return Regles;
	}

	/**
	 * Obté la llista d'identificadors de les bases de coneixement
	 * 
	 * @return String[], vector que conté els identificadors de les bases de
	 *         coneixement existents
	 * @author Laia Riera Guerra
	 */
	public String[] obtenirLlistaIDsBC() {
		String[] idBC;
		ArrayList al = matriusCarregades[matriuActual].obtenirLlistaIdsBC();
		idBC = new String[al.size()];
		for (int i = 0; i < al.size(); i++) {
			idBC[i] = al.get(i).toString();
		}
		return idBC;
	}

	/**
	 * Obté la llista de les bases de coneixement
	 * 
	 * @return LlistaBC, llista de bases de coneixement associada a la matriu
	 *         activa
	 * @author Laia Riera Guerra
	 */
	public LlistaBC obtenirLlistaBC() {
		return matriusCarregades[matriuActual].obtenirLlistaBC();
	}

	/**
	 * Crea una nova base de coneixement que és la còpia de la base de
	 * coneixement que s'identifica pel nom <code>BaseC</code>
	 * 
	 * @param BaseC
	 *            , nom amb que s'identifica la base de coneixement a copiar
	 * @return BaseConeixement còpia
	 * @author Laia Riera Guerra
	 */
	public BaseConeixement copiarBaseConeixement(String BaseC) {
		return matriusCarregades[matriuActual].copiarBC(BaseC);
	}

	/**
	 * Guarda els canvis efectuats sobre la base de coneixement <code>bc</code>
	 * 
	 * @param bc
	 *            , base de coneixement sobre la que es volen guardar els
	 *            canvis. En realitat bc és una còpia de la base de coneixement
	 *            original. La bc ja conté els canvis efectuats, que s'han de
	 *            passar a la base de coneixement original.
	 * @author Laia Riera Guerra
	 */
	public void guardarCanvis(BaseConeixement bc) {
		matriusCarregades[matriuActual].guardarCanvis(bc);
	}

	/**
	 * Obté el valor del contador de regles de la base de coneixement que
	 * s'identifica pel nom <code>bc</code>
	 * 
	 * @param bc
	 *            , nom amb què s'identifica la base de coneixement
	 * @return enter que es correspon amb el valor del contador de regles de la
	 *         base de coneixement de nom <code>bc</code>
	 * @author Laia Riera Guerra
	 */
	public int idReglaPerDefecte(String bc) {
		return matriusCarregades[matriuActual].idReglaPerDefecte(bc);
	}

	/**
	 * Obté l'string que representa la regla, que s'identifica pel nom
	 * <code>ob</code>, en format d'inordre
	 * 
	 * @param ob
	 *            , nom amb que s'identifica la regla
	 * @param bc
	 *            , base de coneixement a la qual pertany la regla
	 *            <code>ob</code>
	 * @return String que representa la regla de nom <code>ob</code> escrita en
	 *         inordre
	 * @throws Exception
	 * @author Laia Riera Guerra
	 */
	public String escriureRegla(String ob, BaseConeixement bc) throws Exception {
		return matriusCarregades[matriuActual].escriureRegla(ob, bc);
	}

	/**
	 * Obté el conseqüent de la regla que s'identifica pel nom <code>ob</code>
	 * 
	 * @param ob
	 *            , nom amb què s'identifica la regla
	 * @param bc
	 *            , base de coneixement a la qual pertany la regla
	 * @return Conseqüent de la regla de nom <code>ob</code>
	 * @throws Exception
	 * @author Laia Riera Guerra
	 */
	public String obtenirConsequent(String ob, BaseConeixement bc)
			throws Exception {
		return matriusCarregades[matriuActual].obtenirConsequent(ob, bc);
	}

	/**
	 * Elimina la regla de nom <code>ob</code> del llista de regles de la base
	 * de coneixement <code>bc</code>
	 * 
	 * @param bc
	 *            , base de coneixement de la qual es vol eliminar la regla
	 * @param ob
	 *            , nom de la regla a eliminar
	 * @throws Exception
	 * @author Laia Riera Guerra
	 */
	public void eliminarRegla(BaseConeixement bc, String ob) throws Exception {
		matriusCarregades[matriuActual].eliminarRegla(bc, ob);
	}

	/**
	 * Obté la propietat que s'identifica pel nom <code>sid</code>
	 * 
	 * @param sid
	 *            , nom amb què s'identifica la propietat a obtenir
	 * @return Propietat
	 * @author Laia Riera Guerra
	 */
	public Propietat obtenirPropietat(String sid) throws Exception {
		return matriusCarregades[matriuActual].obtenirPropietat(sid);
	}

	/**
	 * Obté la base de coneixement que s'identifica pel nom <code>nomBC</code>
	 * 
	 * @param nomBC
	 *            , nom de la base de coneixement a obtenir
	 * @return BaseConeixement
	 * @author Laia Riera Guerra
	 */
	public BaseConeixement obtenirBC(String nomBC) {
		return matriusCarregades[matriuActual].obtenirBC(nomBC);
	}

	/**
	 * Eliminar la base de coneixement que s'identifica pel nom
	 * <code>nomBC</code> de la llista de bases de coneixement del gestor matriu
	 * en curs
	 * 
	 * @param nomBC
	 *            , nom amb què s'identifica la base de coneixement a eliminar
	 * @author Laia Riera Guerra
	 */
	public void eliminarBC(String nomBC) {
		matriusCarregades[matriuActual].eliminarBC(nomBC);
	}

	/**
	 * Crea una nova base de coneixement amb el nom <code>snom</code> i
	 * l'associa a la matriu de dades actual
	 * 
	 * @param snom
	 *            , nom de la nova base de coneixement
	 * @return BaseConeixement creada
	 * @throws Exception
	 * @author Laia Riera Guerra
	 */
	public BaseConeixement crearBC(String snom) throws Exception {
		return matriusCarregades[matriuActual].crearBC(snom);
	}

	/**
	 * Obté la d'identificadors dels objectes de la matriu de dades actual
	 * (files)
	 * 
	 * @return String[] amb els identificadors d'objectes de la matriu
	 * @author Laia Riera Guerra
	 */
	public String[] obtenirLlistaIDsObjectes() {
		return matriusCarregades[matriuActual].obtenirLlistaObjs()
				.llistarIDsObjectes();
	}

	/**
	 * Obté els valors de la matriu de dades
	 * 
	 * @return Object[][] tupla que conté els valors de la matriu de dades
	 *         actual
	 * @author Laia Riera Guerra
	 */
	public Object[][] obtenirMatriuDades() {
		return matriusCarregades[matriuActual].obtenirMatriuDades();
	}

	/**
	 * Obté un vector amb els identificadors de les propietats que pertanyen a
	 * la matriu actual
	 * 
	 * @return String[] amb els identificadors de les propietats
	 * @author Laia Riera Guerra
	 */
	public String[] obtenirLlistaIDsPropsSenseDistincio() {
		return matriusCarregades[matriuActual].obtenirLlistaProps()
				.llistarIDsPropietatsSenseDistincio();
	}

	public String[] obtenirLlistaIDsPropsSenseDistincioA(ArrayList activas) {
		return matriusCarregades[matriuActual].obtenirLlistaProps()
				.llistarIDsPropietatsSenseDistincioA(activas);
	}

	/**
	 * Avalua regla a regla les regles de la base de coneixement que
	 * s'identifica pel nom <code>nomBC</code> Es crearan a la matriu de dades
	 * tantes noves propietats com regles contingui la base de coneixement
	 * avaluada. Les noves propietats s'identificaran pels noms amb que
	 * s'identifiquen les regles.Si ja existís a la matriu alguna propietat del
	 * mateix nom, la nova propietat es passarà a anomenar nomRegla-id, on id és
	 * un enter
	 * 
	 * @param nomBC
	 *            , nom amb que s'indentifica la base de coneixement a avaluar
	 * @param notacioBinaria
	 *            , booleà que ens indica si els resultats a obtenir han de ser
	 *            binaris o no
	 * @return Llista de les propietats categòriques creades.
	 * @throws Exception
	 * @author Laia Riera Guerra
	 */
	public ArrayList avaluaReglaARegla(String nomBC, boolean notacioBinaria)
			throws Exception {
		return matriusCarregades[matriuActual].avaluaReglaARegla(nomBC,
				notacioBinaria);
	}

	/**
	 * Avalua per conseqüents la base de coneixement que s'identifica pel nom
	 * <code>nomBC</code>. Es crearan a la matriu tantes noves propietats com
	 * conseqüents diferents continguin les regles de la base de coneixement
	 * avaluada. Les noves propietats s'identificaran pels noms dels conseqüents
	 * corresponents. Si ja existien propietats amb el mateix nom, llavors el
	 * nom passa a ser Conseqüent-id, on id és un enter.
	 * 
	 * @param nomBC
	 *            , nom amb que s'identifica la base de coneixement a avaluar
	 * @param notacioBinaria
	 *            , booleà que ens indica si el resultat a obtenir ha de ser
	 *            binari o no.
	 * @return Llista de les propietats categòriques creades.
	 * @throws Exception
	 * @author Laia Riera Guerra
	 */
	public ArrayList avaluaConsequent(String nomBC, boolean notacioBinaria)
			throws Exception {
		return matriusCarregades[matriuActual].avaluaConsequent(nomBC,
				notacioBinaria);
	}

	/**
	 * Avalua de forma integrada la base de coneixement que s'identifica pel nom
	 * <code>nomBC</code>. Es crea una nova propietat de la matriu de nom =
	 * <code>nomFinal</code> si aquest no és null, i si no es crea amb el nom de
	 * la base de coneixement avaluada L'avaluació es fa enriquida, si el booleà
	 * <code>enriquit</code> és cert, si no el resultat obtingut no serà
	 * enriquit Si ja existia una propietat amb el mateix nom a la matriu, la
	 * nova propietat s'anomenarà com nom-id, on id és un enter
	 * 
	 * @param nomBC
	 *            , nom amb que s'identifica la base de coneixement a avaluar
	 * @param enriquit
	 *            , ens indica si el resultat ha obtenir serà enriquit o no
	 * @param nomFinal
	 *            , nom amb que s'indentificarà la nova propietat de la matriu (
	 *            resultat d'avaluar la base de coneixement). Si
	 *            <code>nomFinal</code> és null, la nova propietat
	 *            s'identificarà pel nom de la base de coneixement
	 * @return Llista que conté les propietats categòriques que contenen el
	 *         resultat de l'avaluació.( en aquest cas 1 propietat )
	 * @throws Exception
	 * @author Laia Riera Guerra
	 */
	public ArrayList avaluaIntegrat(String nomBC, boolean Enriquit,
			String nomFinal) throws Exception {
		return matriusCarregades[matriuActual].avaluaIntegrat(nomBC, Enriquit,
				nomFinal);
	}

	/**
	 * Avalua l'operació que es passa com a paràmetre <code>soperacio</code>
	 * sobre la matriu de dades i crea una nova propietat de nom
	 * <code>snom</code> i contingut el resultat de l'avaluació de l'operació
	 * 
	 * @param soperacio
	 *            String que representa l'operació a calcular
	 * @param snom
	 *            nom de la nova propietat que es crearà
	 * @throws Exception
	 * @author Laia Riera Guerra
	 */
	public void calcular(String soperacio, String snom) throws Exception {
		matriusCarregades[matriuActual].calcular(soperacio, snom);
	}

	/**
	 * Crea una nova propietat de nom = <code>nomPropNova</code> i llista de
	 * modalitats=
	 * <code> alnomModalitats com a conseqüència de la recodificació de la variable <code>nomPropAntiga</code>
	 * 
	 * @param nomPropNova
	 *            , nom de la nova propietat
	 * @param nomPropAntiga
	 *            , nom de la propietat sobre la que s'ha efectuat la
	 *            recodificació
	 * @param alnomModalitats
	 *            ,noms de les modalitats de la nova variable
	 * @param alagrupacionsModals
	 *            , per cada posició de la llista conté un llistat amb les
	 *            modalitat de la variable recodificada que es corresponen amb
	 *            l'agrupació d'aquestes sota la modalitat de la nova variable
	 *            que ocupa la mateixa posició relativa dins el vector
	 *            <code>alnomModalitats</code>
	 * @throws Exception
	 * @author Laia Riera Guerra
	 */
	public void crearNovaPropietat(String nomPropNova, String nomPropAntiga,
			ArrayList alnomModalitats, ArrayList alagrupacionsModals)
			throws Exception {
		matriusCarregades[matriuActual].crearNovaPropietat(nomPropNova,
				nomPropAntiga, alnomModalitats, alagrupacionsModals);
	}

	/**
	 * Realitza l'Anàlisi Descriptiva de tipus pertinent (Univariant o Per
	 * Classes) indicat a les opcions amb els estadístics indicats a les llistes
	 * d'estadístics i per les variables indicades a les llistes.
	 * 
	 * @param nomsBC
	 *            , llista que conté els noms de les bases de coneixement
	 *            avaluades
	 * @param llistaVariables
	 *            , llista que conté llistes, que la seva vegada contenen dos
	 *            vectors de propietats ( un vector en el format de variables
	 *            univariant i l'altre per classes) Pel cas Univariant: <br>
	 *            llistaVars[0][] - Conté la llista de vars numeriques <br>
	 *            llistaVars[1][] - Conté la llista de vars categoriques<br>
	 *            Pel cas Per Classe: <br>
	 *            llistaVarsC[0][] - Conté la llista de vars categ de classe <br>
	 *            llistaVarsC[1][] - Conté la llista de vars numeriques<br>
	 *            llistaVarsC[2][] - Conté la llista de vars categoriques<br>
	 * @param llistaEstads
	 *            , llista Estadístics pel cas Univariant llistaEstads[0] -
	 *            Conté la llista d'estad. numerics <br>
	 *            llistaEstads[1] - Conté la llista d'estad. categorics<br>
	 * @param llistaEstadsC
	 *            , llista Estadístics pel cas Per classes llistaEstadsC[0] -
	 *            Conté la llista d'estad. NN<br>
	 *            llistaEstadsC[1] - Conté la llista d'estad. NC<br>
	 *            llistaEstadsC[2] - Conté la llista d'estad. CC<br>
	 * @param opcUniv
	 *            , opcions dels tipus dels estadístics de tipus d'anàlisi
	 *            descriptiva a realitzar per Univariant
	 * @param opcClass
	 *            , opcions dels tipus dels estadístics de tipus d'anàlisi
	 *            descriptiva a realitzar per Classes
	 * @param avaluacio
	 *            , enter que indica quin tipus d'avaluació de les bases de
	 *            coneixement s'ha efectuat 1-->Regla a regla<br>
	 *            2-->Per conseqüent<br>
	 *            3-->Integrada
	 * @param tipusDescriptiva
	 *            , enter que indica quin tipus d'ànalisi es fa: 1-->Univariant
	 *            2-->Per classes 3-->Univariant i per classes
	 * @param esPart
	 *            , enter que indica si s'ha d'escriure o no el començament i
	 *            final del fitxer 0-->Escriure començament i final (el fitxer
	 *            només té una part) 1-->Escriure només el començament (aquesta
	 *            és la primera part del fitxer) 2-->No escriure res (aquesta és
	 *            una part del mig del fitxer) 3-->Escriure només el final
	 *            (aquesta és la última part del fitxer)
	 * @param printRegles
	 *            , indica si cal escriure les regles a l'informe final
	 * @return true, si la descriptiva s'ha realitzat correctament, false
	 *         altrament
	 * @author Laia Riera Guerra
	 */
	   public boolean ferDescripBC(ArrayList nomsBC,ArrayList llistaVariables,Vector[] llistaEstads,
			   Vector[] llistaEstadsC,Opcions opcUniv,Opcions opcClass,int avaluacio,
			   int tipusDescriptiva,int esPart,boolean printRegles, String subfix) {
	         boolean fet = false;
	         try{
	            matriusCarregades[matriuActual].ferDescripBC(nomsBC,llistaVariables,llistaEstads,llistaEstadsC,
	            		opcUniv,opcClass,avaluacio,tipusDescriptiva,esPart,printRegles,subfix);
	            fet = true;
	         } 
	             catch (Exception e){
	               logger.warning(e.toString() + ": " + e.getMessage());
	               e.printStackTrace();
	            }
	         return fet;
	      }
	   /**
	    * Perform the BC-description for the BCs specified in <code> nomsBC</code>
	    * corresponding to the matrices (id) specified in <code> matrixId</code>
	    * @param nomsBC
	    * @param matrixId
	    * @param llistaVariables
	    * @param llistaEstads
	    * @param llistaEstadsC
	    * @param opcUniv
	    * @param opcClass
	    * @param avaluacio
	    * @param tipusDescriptiva
	    * @param esPart
	    * @param printRegles
	    * @param subfix
	    * @return
	    */
	public boolean ferDescripBC(ArrayList<String> nomsBC, ArrayList<Integer> matrixId, ArrayList llistaVariables,
			Vector[] llistaEstads, Vector[] llistaEstadsC, Opcions opcUniv,
			Opcions opcClass, int avaluacio, int tipusDescriptiva, int esPart,
			boolean printRegles, String subfix) {
		boolean fet = false;
		try {
			for (int i=0; i<nomsBC.size();i++){
				FitxerTex fitxer;
		        String nomBase, nomFitxer;
		        GeneradorTex gen;
		        String dirResultats = "./resultats/";
		        String nom = "DescipBC";
				nomBase = dirResultats + new File(nom).getName();
				nomFitxer = nomBase + subfix;
				fitxer = new FitxerTex(nomFitxer);
				gen = new GeneradorTex(fitxer);
				/**
				 * 27-12-2011
				 * Marco Villegas.
				 * Now we  only process one BC at time
				 */
		             
				ArrayList ar = new ArrayList();
				ar.add(nomsBC.get(i));
				matriusCarregades[matrixId.get(i)].ferDescripBC(ar,
					llistaVariables, llistaEstads, llistaEstadsC, opcUniv,
					opcClass, avaluacio, tipusDescriptiva, esPart, printRegles,
					subfix, gen);
			}
			fet = true;
		} catch (Exception e) {
			logger.warning(e.toString() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return fet;
	}

	public void modificaArbreActual(String nomArbre) {
		matriusCarregades[matriuActual].modificaArbreActual(nomArbre);
	}

	/**
	 * Obté la columna de valors de la matriu de dades corresponent a la
	 * propietat de nom = <code>id</code>
	 * 
	 * @param id
	 *            , nom de la propietat
	 * @return Dada[], vector dels valors de la matriu de dades corresponents a
	 *         la columna de la propietat <code>id</code>
	 * @author Laia Riera Guerra
	 */
	public Dada[] obtenirColumna(String id) {
		return matriusCarregades[matriuActual].obtenirColumna(id);
	}

	/**
	 * Obté el nom de base de coneixement per defecte que no existeixi encara al
	 * llistat de bases de coneixement associat a la matriu actual Seguint el
	 * format següent:BCi on i és un número enter
	 * 
	 * @return nom de la base de coneixement per defecte
	 * @author Laia Riera Guerra
	 * @version v4
	 */
	public String nomBCPerDefecte() {
		return matriusCarregades[matriuActual].nomBCPerDefecte();
	}

	/**
	 * Obté el següent nom de la propietat encara no existent a la matriu actual
	 * seguint el format següent: <code>s</code>i on i és un número enter
	 * 
	 * @param s
	 *            , nom base de la propietat
	 * @return <code>s</code>i
	 * @author Laia Riera Guerra
	 */
	public String nomPropietatPerDefecte(String s) {
		return matriusCarregades[matriuActual].nomPropietatPerDefecte(s);
	}

	/**
	 * Posa com a nova llista de bases de coneixment la LlistaBC
	 * <code>listBC</code>
	 * 
	 * @param listBC
	 *            , nova LlistaBC de la matriu actual
	 * @throws Exception
	 * @author Laia Riera Guerra
	 */
	public void setLlistaBCMatriu(LlistaBC listBC) throws Exception {
		matriusCarregades[matriuActual].setLlistaBC(listBC);
	}

	/**
	 * Elimina les últimes <code>nCols</code> columnes i propietats de la matriu
	 * de dades actual
	 * 
	 * @param nCols
	 *            , número de columnes a eliminar
	 * @throws Exception
	 * @author Laia Riera Guerra
	 */
	public void eliminarColumnes(int nCols) throws Exception {
		matriusCarregades[matriuActual].eliminarColumnes(nCols);
	}

	/**
	 * Elimina les propietats identificades pels noms continguts a la llista
	 * <code>alCols</code>
	 * 
	 * @param alCols
	 *            , llista que conté els noms de les variables a eliminar
	 * @throws Exception
	 *             , si alguna de les propietats no existeix ja
	 * @author Laia Riera Guerra
	 */
	public void eliminarPropietats(ArrayList alCols) throws Exception {
		// System.out.println("entre a este de donde las elimina"+matriuActual);
		matriusCarregades[matriuActual].eliminarPropietats(alCols);
	}

	/**
	 * 
	 * @param llistaVars
	 *            llistaVar[0][] - Llista de variables de classe (categòriques)
	 *            llistaVar[1][] - Llista de variables numèriques que es
	 *            discretitzaran
	 * @param fitxers
	 *            - indica si s'ha de generar el fitxer latex o no
	 * @param descriptiva
	 *            - indica si s'ha de generar l'informe de la base de
	 *            coneixement que es crearà
	 * @param mkmz
	 *            - indica si s'han de generar els fitxers mk i zk
	 * @param revisat
	 *            - indica si s'ha d'aplicar la versió revisado de la
	 *            discretizatció BoxPlotBased
	 * @return - true, si s'ha pogut fer la generació de regles
	 * @throws Exception
	 * @author Alfons Bosch, Patricia Garcia
	 */
	public BaseConeixement generacioReglesBoxPlot(String[][] llistaVars,
			boolean descriptiva, boolean mkmz, boolean revisat)
			throws Exception {

		try {
			return matriusCarregades[matriuActual].generacioReglesBoxPlot(
					llistaVars, descriptiva, mkmz, revisat);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warning(e.toString() + ": " + e.getMessage());
			throw new Exception(e.getMessage());
		}

	}

	/**
	 * Crea una base de coneixement prob a partir d'una ja existent (podant)
	 * 
	 */
	public BaseConeixement crearBCProb(float llindarMinim, float llindarMaxim,
			String nomBC) throws Exception {
		return matriusCarregades[matriuActual].crearBCProb(llindarMinim,
				llindarMaxim, nomBC);
	}

	/**
	 * Discretitza mitjançant el mètode BoxPlot variables numèriques en
	 * combinació amb categòriques.
	 * 
	 * @param llistaVar
	 *            : llistaVar[0][] - Conté la llista de vars numeriques X de NC
	 *            llistaVar[1][] - Conté la llista de vars categoriques Y de NC
	 * @param fitxers
	 *            , indica si s'ha de generar el fitxer latex o no
	 * @param revisedBoxPlot
	 *            - indica si s'ha d'aplicar la versió revisada del
	 *            "boxplot based discretization" en quant a les variables
	 *            categòriques formades per dues classes.
	 * @param generarMkZk
	 *            - indica si s'han de generar els fitxers zk i mk
	 * @return true, si la discretització s'ha dut a terme correctament, false
	 *         altrament
	 * @throws Exception
	 * @author Laia Riera Guerra, Alfons Bosch, Patrícia Garcia
	 */
	public boolean discretitzarBoxPlot(String[][] llistaVars, boolean fitxers,
			boolean revisedBoxPlot, boolean generarMkZk) throws Exception {
		boolean fet = false;
		try {
			matriusCarregades[matriuActual].discretitzarBoxPlot(llistaVars,
					fitxers, revisedBoxPlot, generarMkZk);
			fet = true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.warning(e.toString() + ": " + e.getMessage());
			throw new Exception(e.getMessage());

		}
		return fet;
	}

	/**
	 * Discretitza mijançant la discretització uniforme variables numèriques
	 * 
	 * @param llistaVar
	 *            : llistaVar[0][] - Conté la llista de vars numeriques X de NC
	 *            llistaVar[1][] - Conté la llista de vars categoriques Y de NC
	 * @param numIntervals
	 *            , número d'intervals en que s'ha de dividir el rang de les
	 *            variables numèriques, equival al número de modalitats que
	 *            tindran les noves variables discretitzades
	 * @param fitxers
	 *            , indica si s'ha de generar el fitxer latex o no
	 * @return true, si la discretització s'ha efectuat correctament
	 * @throws Exception
	 * @author Laia Riera Guerra
	 */
	public boolean discretitzarUniforme(String[][] llistaVars,
			int numIntervals, boolean fitxers) throws Exception {
		boolean fet = false;
		try {
			matriusCarregades[matriuActual].discretitzarUniforme(llistaVars,
					numIntervals, fitxers);
			fet = true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.warning(e.toString() + ": " + e.getMessage());
			throw new Exception(e.getMessage());

		}
		return fet;
	}

	/**
	 * Discretitza una variable numèrica segons els valors definits per l'usuari
	 * 
	 * @param dadesAux
	 *            , conté el llistat de valors ordenats de menor a major que es
	 *            corresponen amb els punts de tall dels intervals
	 * @param llistaModalitats
	 *            , llistat amb els noms de les modalitats
	 * @param snomPropietat
	 *            , nom de la nova propietat discretitzada
	 * @param fitxers
	 *            , indica si s'ha de generar el fitxer latex o no
	 * @return true, si la discretització s'ha efectuat correctament, false
	 *         altrament
	 * @throws Exception
	 * @author Laia Riera Guerra
	 */
	public boolean discretitzarManual(float[] dadesAux,
			ArrayList llistaModalitats, String snomPropietat, boolean fitxers)
			throws Exception {
		boolean fet = false;
		try {
			matriusCarregades[matriuActual].discretitzarManual(dadesAux,
					llistaModalitats, snomPropietat, fitxers);
			fet = true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.warning(e.toString() + ": " + e.getMessage());
			throw new Exception(e.getMessage());

		}
		return fet;
	}

	/**
	 * Obté un llistat que conté dos llistats. Un d'aquests conté els noms de
	 * les bases de coneixement que contenen propietats a les regles que no
	 * existeixen a la matriu de dades <code>idMatriuPare</code>. L'altre
	 * llistat conté per cada posició un llistat amb els noms de les regles que
	 * contenten propietats que no existeixen a la matriu
	 * <code>idMatriuPare</code>
	 * 
	 * @param idMatriuPare
	 *            , matriu sobre la que es vol comprovar
	 * @param propSelec
	 *            , llistat de propietats de la matriu nova
	 * @return llistat amb els noms de les bases de coneixement i regles que
	 *         contenen propietats que no estan a <code>propSelec</code>
	 * @throws Exception
	 * @author Laia Riera Guerra
	 */
	public ArrayList comprovarLlistaBC(int idMatriuPare, String[] propSelec)
			throws Exception {
		ArrayList resultat = new ArrayList();
		return matriusCarregades[idMatriuPare].comprovarLlistaBC(propSelec);

	}

	/**
	 * Obté la llista de propietats de la matriu actual
	 * 
	 * @return LlistaPropietats
	 * @author Laia Riera Guerra
	 */
	public LlistaPropietats obtenirLListaPropietats() {
		return matriusCarregades[matriuActual].obtenirLlistaProps();
	}

	/**
	 * Carrega una matriu de dades a partir d'un fitxer .dat estàndard
	 * 
	 * @param nom
	 *            , nom del fitxer sense extensió
	 * @param fila
	 *            , indica si la primera fila de dades conté els identificadors
	 *            de les variables
	 * @param col
	 *            , indica si la primera columna de dades conté els
	 *            identificadors dels objectes
	 * @return int >0, posició on es guarda la matriu si s'ha carregat
	 *         correctament <br>
	 *         -1, si no es pot carregar la matriu
	 * @throws CreacioMatriuException
	 * @throws CreacioPropietatsException
	 * @throws CreacioObjectesException
	 * @author Laia Riera Guerra
	 */
	public int carregarMatriuEstandard(String nom, boolean fila, boolean col)
			throws CreacioMatriuException, CreacioPropietatsException,
			CreacioObjectesException {
		int i = -1;
		if (numMatriusOcupades < maxMatrius - 1) {
			// Es busca la primera posició lliure del array
			i = numMatriusOcupades;
			while (matriusCarregades[i] != null) {
				i = (i + 1) % maxMatrius;
			}
			matriusCarregades[i] = new GestorMatriu(nom, i, fila, col);
			if (matriusCarregades[i] != null) {
				matriuActual = i;
				numMatriusOcupades++;
			}
		} else {
			logger.warning("Nombre màxim de matrius carregades al sistema.");
			throw new CreacioMatriuException(
					"Nombre màxim de matrius carregades al sistema.");
		}
		return i;
	}

	/**
	 * Calula els estadístics de la propietat <code>propC</code>
	 * 
	 * @param propC
	 *            , propietat de la qual volem calcular els estadístics
	 * @throws CreacioPropietatsException
	 * @author Laia Riera Guerra
	 */
	public void calcularEstadistics(Propietat propC)
			throws CreacioPropietatsException {
		matriusCarregades[matriuActual].calcularEstadistics(propC);
	}

	/**
	 * Obté una còpia de la LListaPropietats de la matriu actual
	 * 
	 * @return LListaPropietats
	 * @author Laia Riera Guerra
	 */
	public LlistaPropietats obtenirCopiaLlistaPropietats() {
		return matriusCarregades[matriuActual].obtenirCopiaLlistaPropietats();
	}

	/**
	 * Posa com a nova LListaPropietats de la matriu actual la LListaPropietats
	 * <code>llprop</code>
	 * 
	 * @param llprop
	 *            , nova LlistaPropietats de la matriu actual
	 * @author Laia Riera Guerra
	 */
	public void setLlistaPropietats(LlistaPropietats llprop) {
		matriusCarregades[matriuActual].setPropietats(llprop);
	}

	/**
	 * Comprova que els nous valors mínim i màxim de la propietat
	 * <code>propC</code> incloguin tots els valors observats per la variable
	 * 
	 * @param minim
	 *            ,nou valor mínim de la propietat
	 * @param maxim
	 *            , nou valor màxim de la propietat
	 * @param propC
	 *            , propietat
	 * @return 0-->correcte, 1-->mínim incorrecte, 2-->màxim incorrecte
	 * @author Laia Riera Guerra
	 */
	public int minMaxCorrectes(float minim, float maxim, Propietat propC) {
		return matriusCarregades[matriuActual].minMaxCorrectes(minim, maxim,
				propC);
	}

	public int obteniractual() {
		return matriuActual;
	}

	public int obtenirocupadas() {
		return numMatriusOcupades;
	}

	public void desarActivas(String spath, ArrayList act) throws Exception {
		matriusCarregades[matriuActual].exportarActivas(spath,
				matriusCarregades[matriuActual].obtenirActive());
	}

	public int obtenirIdProp(String prop) {
		return matriusCarregades[matriuActual].obtenirLlistaProps().indiceProp(
				prop);
	}

	public String obtenirNomMatriu() {
		return matriusCarregades[matriuActual].obtenirNomMatriu();
	}

	public String obtenirNomMatriuAnt() {
		return matriusCarregades[matriuActual - 1].obtenirNomMatriu();
	}

	public boolean estamarcado() {
		return matriusCarregades[matriuActual].marcado;
	}

	public boolean marcar() {
		return matriusCarregades[matriuActual].marcado = true;
	}

	public boolean desmarcar() {
		return matriusCarregades[matriuActual].marcado = false;
	}

	public void activasel() {
		matriusCarregades[matriuActual].activasel = true;
	}

	public void activasdesel() {

		matriusCarregades[matriuActual].activasel = false;
	}

	public boolean consultasel() {
		return matriusCarregades[matriuActual].activasel;
	}

	public ArrayList ComprobarProps(String bConeixement, String[] propSelec)
			throws Exception {
		return matriusCarregades[matriuActual].ComprobarProps(bConeixement,
				propSelec);

	}

	public void ConfirmaEsts(ArrayList estados, ArrayList variables) {
		matriusCarregades[matriuActual].ConfirmaEsts(estados, variables);
	}

	public ArrayList obtenirLlistaEst() throws Exception {
		return matriusCarregades[matriuActual].llEstados;
	}

	// public void SetearEstadoActual(String var)throws Exception{
	// matriusCarregades[matriuActual].estadosel=var ;
	// }

	public void SetearEstadoActual(String var) throws Exception {
		matriusCarregades[matriuActual].estadoactual = var;
	}

	public void PorEstados(boolean ests) throws Exception {
		matriusCarregades[matriuActual].estadosel = ests;
	}

	public void SetearProceso(String proc) throws Exception {
		matriusCarregades[matriuActual].proceso = proc;
	}

	public String obtenerProceso() throws Exception {
		return matriusCarregades[matriuActual].proceso;
	}

	public GestorMatriu obtenerGM() throws Exception {
		return matriusCarregades[matriuActual];
	}

	public ArrayList obtenirLlistArb() throws Exception {
		return matriusCarregades[matriuActual].llArbres;
	}

	/**
	 * A wrapper for the concepts generated in the method
	 * <code>obtenirConceptJerarq</code>
	 * 
	 * @author marco
	 * 
	 */
	class NodeConcept {
		public ExpressioBooleana ebool_pre;
		public Regla rule;
		public String ruleName;
		public int iteration; // this is necessary to access the corresponding
								// qualityTable.
		public BaseConeixement knowledgeBase;
		public NodeConcept father, son1, son2;
		public String clsFile;
		public String property;
		public String modality;
		public static final String ROOT = "b5d5a0718564a78f332fa96fb92d6fa7";

		public NodeConcept(ExpressioBooleana ebooleana, int iteration,
				BaseConeixement knowledgeBase, NodeConcept father,
				NodeConcept son1, NodeConcept son2, String clsFile,
				String property, String modality, String ruleName) {
			super();
			this.ebool_pre = ebooleana;
			this.iteration = iteration;
			this.knowledgeBase = knowledgeBase;
			this.father = father;
			this.son1 = son1;
			this.son2 = son2;
			this.clsFile = clsFile;
			this.property = property;
			this.modality = modality;
			this.rule = null;
			this.ruleName = ruleName;
		}

		private Boolean isNodeLeaf() {
			return (this.son1 == null && this.son2 == null);
		}

		/**
		 * Receive a vector of classes (the result of a dendrogram cut), and
		 * return the father class (the one which is not included in
		 * <code>vector_mods</code>)
		 * 
		 * @param vector_mods
		 * @return
		 */
		public NodeConcept searchFatherConcept(Vector<String> vector_mods) {
			// it must be an leaf node.
			logger.info("SEARCH_FATHER_CONCEPT on " + this.modality + ":"
					+ vector_mods.toString());
			if (this.son1 == null && this.son2 == null
					&& this.property.compareTo(this.ROOT) == 0) {
				return this;
			}
			Iterator<String> it = vector_mods.iterator();
			System.out.println("   this.modality: " + this.modality);
			if (this.isNodeLeaf()) {
				Boolean sw = false;
				while (it.hasNext()) {
					String s = it.next();
					System.out.println(s);
					if (this.modality.compareTo(s) == 0) {
						sw = true;
						it.remove();
						logger.info("   it's not the parent!! " + vector_mods);
						return null;
					}
				}
				if (!sw) {
					logger.info("   Father found:" + this.modality);
					return this;
				} else
					return null;
			} else { // is not an leaf node
				while (it.hasNext()) {
					if (this.modality == it.next()) {
						it.remove();
						logger.info("   it's not the parent!! " + vector_mods);
						return null;
					}
				}
				// We visit the whole tree to properly clean vector_mods array
				NodeConcept nc1 = null, nc2 = null;
				nc1 = this.son1.searchFatherConcept(vector_mods);
				nc2 = this.son2.searchFatherConcept(vector_mods);
				return (nc1 == null ? nc2 : nc1);
			}
		}

		/**
		 * Search and return a nodeConcept of the concepts tree generated during
		 * the CCEC iterations.
		 * 
		 * @param conc
		 *            - the name class to look for
		 * @param concept
		 *            - the root node which you want you search from
		 * @return
		 */
		public NodeConcept searchNode(String conc) {
			NodeConcept caux = null;
			if (this.modality == conc) {
				return this;
			}
			if (this.son1 != null)
				caux = this.son1.searchNode(conc);
			if (caux != null) {
				return caux;
			}
			if (this.son2 != null)
				caux = this.son2.searchNode(conc);
			if (caux != null) {
				return caux;
			}
			return null;
		}

		/**
		 * Performs the chaining of the concepts: the sons inherit their
		 * parent's concept
		 * 
		 * @throws Exception
		 */
		public void conceptChaining(GestorMatriu g, Vector<Regla> rules)
				throws Exception {
			if (this.property != this.ROOT) {
				String and = ExpressioBooleana.AND;
				ExpressioBooleana eb = new ExpressioBooleana(
						matriusCarregades[matriuActual]);
				String aux_b = "", aux_n = "";
				if (this.father.property != this.ROOT) {
					aux_b = "(" + this.father.ebool_pre.escriureNormal() + ")"
							+ and;
					aux_n = "-" + this.father.ruleName;
				}
				eb = eb.llegirBCNormal(aux_b + "("
						+ this.ebool_pre.escriureNormal() + ")");
				this.rule = new Regla(eb, this.modality, 1, g, aux_n
						+ this.ruleName);
			}
			if (this.isNodeLeaf()) {
				rules.add(this.rule);
			} else {
				this.son1.conceptChaining(g, rules);
				this.son2.conceptChaining(g, rules);
			}
		}

	}

	/**
	 * This method save the results of an CECC iteration, in the following way:
	 * - Identify the spliting class (the father concept) - Identify the new
	 * clasess (child concepts) - Create two new <code>Concept</code>s to each
	 * of the new classes and store them as derived concept of the father. - Set
	 * the corresponding knowledge base. - Set the corresponding quality rule
	 * table.
	 * 
	 * @param vector_mods
	 *            - contains all the classes returned by the performed dendogram
	 *            cut
	 * @param property
	 * @param fileCls
	 * @param iteration
	 * @param concepts
	 * @throws Exception
	 * @author Marco Villegas Garcia - 2012
	 */
	public void saveCECCIteration(Vector<String> vector_mods, String property,
			String fileCls, int iteration, NodeConcept concepts,
			Boolean reviewedMethod, String[][] varListSubMatrix, int kiMethod,
			ArrayList llistaNomsBc, Boolean interKB, Boolean generar, GeneradorTex gen)
			throws Exception {
		NodeConcept father = null;
		String mod;
		int currentMatrix = this.matriuActual;
		
		Boolean sw_first = (concepts.isNodeLeaf());
		logger.info("MODALITIES:" + vector_mods.toString());
		Vector<String> vector_mods_aux = new Vector<String>(vector_mods);
		// We search the father concept
		father = concepts.searchFatherConcept(vector_mods_aux);
		// Now we have in vector_mods_aux the 2 new classes, and father is the
		// father-concept
		if (vector_mods_aux.size() != 2) {
			logger.warning("WE HAVE PROBLEMS!!, SEARCH_FATHER_CONCEPT  DOES NOT RETURN TWO CLASSES");
			System.out.println(vector_mods_aux);
			System.exit(1);
		}
		// CHANGING TO THE NEW MATRIX
		int idMat;
		System.out.println("MATRIZ ACTUAL ANTES DE CAMBIAR "
				+ this.matriuActual + " " + this.idMatriuActual());
		if (father.property == NodeConcept.ROOT) { // this is the root node
			idMat = currentMatrix;
		} else {
			logger.info("LOADING SUBMATRIX");
			logger.info("   prop:" + father.property);
			logger.info("   mod:" + father.modality);
			idMat = this.loadSubMatrix(father.property + "_" + father.modality,
					currentMatrix, varListSubMatrix[1], varListSubMatrix[2],
					property,
					// Marco Villegas 21-12-11. Llamamos solo con las variables
					// que intervienen en la interpretacion.
					// currentMatrix, obtenirLlistaIDsPropsSenseDistincio(),
					father.property, father.modality);
		}

		this.canviarMatriuActual(idMat);
//		System.out.println("MATRIZ ACTUAL DESPUES DE CAMBIAR "
//				+ this.matriuActual + " " + this.idMatriuActual());
//		// GENERATING RULES
//		logger.info("Building rules for property: " + property);
		varListSubMatrix[0][0] = obtenirPropietat(property).obtenirId();
//		logger.info(" CURRENT MATRIX: "
//				+ this.matriusCarregades[matriuActual].nom);
//		logger.info("           ROWS: "
//				+ this.matriusCarregades[matriuActual].obtenirDades()
//						.obtenirNumFiles());
//		logger.info("           COLS: "
//				+ this.matriusCarregades[matriuActual].obtenirDades()
//						.obtenirNumColumnes());

		// verificar
//		System.out.println("******* MODALIDADES **************");				
		ArrayList ar = new ArrayList();
		for (String s: vector_mods_aux){
			ar.add(s);
		}		
		/**
		 * Correcting the list of modalities.
		 * By Marco Villegas. 26-12-2011.
		 */
		((PropCategorica) this.matriusCarregades[this.matriuActual].obtenirPropietat(property)).setLlModalitats(ar);
		((PropCategorica) this.matriusCarregades[this.matriuActual].obtenirPropietat(property)).setLlModsOrdre(ar);		
		BaseConeixement bc = generacioReglesBoxPlot(varListSubMatrix, false,
				generar, reviewedMethod);
//		bc.setNomBC("Iteration "+iteration);
//		this.matriusCarregades[matriuActual].obtenirLlistaBC().afegirBC(bc);
//		this.matriusCarregades[matriuActual].obtenirLlistaBC().setM(this.matriusCarregades[matriuActual]);
		
		Iterator noms = bc.getAlNomsRegles().iterator();
//		int j = 0;
//		while (noms.hasNext()) {
//			j++;
//			String sn = (String) noms.next();
//			Regla r = bc.obtenirRegla(sn);
//			System.out.println("   " + sn + "->" + r.toStringRegla());
//			if (j == 0)
//				break;
//		}
		noms = bc.getAlNomsRegles().iterator();

		String[] mods = new String[2];
		mods[0] = vector_mods_aux.get(0);
		mods[1] = vector_mods_aux.get(1);
//		logger.info("   bc ->" + bc.numRegles());
//		logger.info("   noms ->" + bc.getAlNomsRegles().toString());
//		logger.info("   mods ->" + vector_mods_aux.toString());
//		logger.info("   km ->" + kiMethod);
		Regla[] bestRules = bestRules(bc, noms, mods, kiMethod);
		for (int i = 0; i < 2; i++) {
			Regla aux = bestRules[i];
			if (aux.getConseguent().indexOf(vector_mods_aux.get(0)) != -1)
				mod = vector_mods_aux.get(0);
			else
				mod = vector_mods_aux.get(1);
			NodeConcept nc = new NodeConcept(aux.getExpressio(), iteration, bc,
					father, null, null, fileCls, property, mod, aux.nomRegla);
			if (father.son1 == null) {
				father.son1 = nc;
			} else {
				if (father.son2 == null) {
					father.son2 = nc;
				} else {
					logger.warning("PROBLEMS!!, IMPOSSIBLE TO SET MORE THAN TWO CHILD CLASSES");
					System.exit(1);
				}
			}
		}
		int esPart;
		if(interKB)
		{
			//if this is the first step of the loop, we have to include the opening
			if (sw_first) {
				esPart = 1;
			} else {
				esPart = 2;
			}
			
			//ferQualitatBC(bc.nomBC,true,true,true,true,true,true,esPart, "ConceptJerarq");
			ferQualitatBC(bc.nomBC,true,true,true,true,true,true,esPart, "CCEC", gen);	
			((ArrayList)llistaNomsBc.get(0)).add(bc.getNomBC());
			((ArrayList)llistaNomsBc.get(1)).add(this.matriuActual);			
		}
		//ferQualitatBC(bc.nomBC, true, true, true, true, true, true, 2, "CCEC");
		this.canviarMatriuActual(currentMatrix);
	}

	/**
	 * Method that perform the hierarchical conceptualitzation. This consists on
	 * doing cuts in the dendogram, integrating the resulting classification in
	 * the data matrix and selecting the best rules from the whole set of
	 * inducted rules. Every new iteration makes the cut with a higher number of
	 * classes until reach the number given by the user.
	 * 
	 * @param numClass
	 *            - String with the number of classes we want to obtain
	 * @param nomTall
	 *            - Name of the tree cut
	 * @param file
	 *            - Desire location of generated files
	 * @param nomMatriu
	 *            - Name of the current data matrix
	 * @param indexClass
	 *            - index of the class variable
	 * @param kiMethod
	 *            - Knowledge Interpretation Method.
	 * @param generar
	 *            Nota Karina: Sembla que indica si cal generar la descriptiva
	 *            de les BC intermedies o no
	 * @param revisat
	 * @return
	 * 
	 * @author Esther Lozano
	 * @author Grup SISPD QT 2009-2010
	 */
	public boolean obtenirConceptJerarq(String[][] llistaVars, String numClass,
			String nomTall, File file, String nomMatriu, String nomClase,
			boolean generar, boolean revisat, Vector results,
			Vector<Regla> rules, boolean intermediesBC, int kiMethod,
			ArrayList llistaNomsBc, String nameFileTex) {
		boolean ok = true;
		String nomCls;
		int numCls = Integer.parseInt(numClass);
		String nomClass="";
		String propertyName = null;
		String matrixName = null;
		NodeConcept concept = new NodeConcept(null, 0, null, null, null, null,
				"", NodeConcept.ROOT, NodeConcept.ROOT, "");
		try {
			FitxerTex fitxer;
			String nomBase, nomFitxer;
			

			nomBase = obtenirDirResultats() + nameFileTex;

			// nomFitxer = nomBase + "dreg";
			String subfix = "CCEC";
			nomFitxer = nomBase + subfix;

			fitxer = new FitxerTex(nomFitxer);
			GeneradorTex gen = new GeneradorTex(fitxer);						
			for (int n = 2; n <= numCls; n++) {
				matrixName = "Iteration" + Integer.toString(n);
				nomCls = nomTall + Integer.toString(n - 1); // we change the cut
															// name in each
															// iteration
				nomClass = nomClase + Integer.toString(n);
				ok = tallaArbre('C', Integer.toString(n), false, false, false,
						true, nomCls);
				String path_par = file.getPath() + file.separator + "resultats"
						+ file.separator + nomCls;
				// we generate the .cls file that will be used to integrate
				// classification
				logger.info("PATH PAR_FILE: " + path_par);
				transformarParticio(path_par);
				String fileCls = new File(path_par).getAbsolutePath();
				carregarClas(fileCls, nomClass, false, matrixName);
				// we delete the .par and .cls files since they're not needed
				// anymore
				File par = new File(path_par + ".par");
				File cls = new File(fileCls + ".cls");

				// PROPERTY IDENTIFIER
				propertyName = obtenirPropietat(nomClass).obtenirId();

				// MODALITIES (CLASSES)
				String[] array_mods = obtenirLlistaMods(nomClass);
				Vector<String> vector_mods = new Vector<String>(
						Arrays.asList(array_mods));

				// SAVING NEW MODALITIES IN concepts VECTOR
				this.saveCECCIteration(vector_mods, propertyName, fileCls,
						n - 1, concept, revisat, llistaVars, kiMethod, llistaNomsBc, intermediesBC, generar, gen);
			}// end of main loop

			// Now we go trought the tree to build the final rules.
			concept.conceptChaining(this.matriusCarregades[this.matriuActual],
					rules);
			/**
			 * We integrate the generated concept in a new bc.
			 */
			BaseConeixement bc = this.crearBC("CCEC"+nomClase);    		
    		for(int i = 0; i < rules.size(); i++)
    		{
    			Regla r = rules.get(i);
    			r.setConseguent("("+nomClass+")"+r.getConseguent());
    			r.setNomRegla("r"+((int)i+1)+".BC"+i+"."+r.getNomRegla());
    			r.setGestor(this.matriusCarregades[this.obteniractual()]);
    			bc.afegirRegla(r);
    		}
    		
    		((ArrayList)llistaNomsBc.get(0)).add(bc.getNomBC()); 
    		((ArrayList)llistaNomsBc.get(1)).add(this.idMatriuActual()); 
    		
    		Boolean sw = ferQualitatBC(bc.nomBC,true,true,true,true,true,true,3, "CCEC", gen);
    		nameFileTex= new String(nomFitxer);
//			for (Object r : rules.toArray()) {
//				Regla rule = (Regla) r;
//				System.out.println(rule.toStringRegla());
//			}
		} catch (Exception e) {
			e.printStackTrace();
			ok = false;
		}

		return ok;
	}

	/**
	 * This function apply the blc&pcwa: we clean the redundant variables,
	 * keeping the highest confidence and coverage.
	 * 
	 * @param rules1
	 * @param rules2
	 * @return Vector<Regla>[] corresponding to the arguments.
	 * @author Marco Villegas
	 * @throws Exception
	 */
	public Object[] cleanRules(Vector<Regla> rules1, Vector<Regla> rules2)
			throws Exception {
		Vector<Regla> fRules1 = new Vector<Regla>();
		Vector<Regla> fRules2 = new Vector<Regla>();
		Object[] returnRules = new Object[2];
		Iterator<Regla> it1 = rules1.iterator();
		Iterator<Regla> it2 = rules2.iterator();
		HashSet<String> pg1 = getRulesProperties(rules1);
		HashSet<String> pg2 = getRulesProperties(rules2);
		HashSet<String> inters = pg1, rest1 = pg1, rest2 = pg2;
		inters.retainAll(pg2);
		float cov1 = 0, cov2 = 0, conf1 = 0, conf2 = 0;
		if (inters.isEmpty()) {
			// There is no intersection
			logger.info("No intersection");
			returnRules[0] = rules1;
			returnRules[1] = rules2;
		} else {
			logger.info("There are intersections!!" + inters.toString());
			Iterator<String> iti = inters.iterator();
			Regla r1 = null, r2 = null;
			while (iti.hasNext()) {

				String str = iti.next();
				logger.info("   " + str);
				// Seek the rule in vector rules1

				it1 = rules1.iterator();
				while (it1.hasNext()) {
					r1 = it1.next();
					if (r1.expressio.getPropietats().contains(str)) {
						conf1 = r1.confidence();
						cov1 = r1.coveringR();
						break;
					}
				}
				// Seek the rule in vector rules2
				it2 = rules2.iterator();
				while (it2.hasNext()) {
					r2 = it2.next();
					if (r2.expressio.getPropietats().contains(str)) {
						conf2 = r2.confidence();
						cov2 = r2.coveringR();
						break;
					}
				}
				logger.info("   Cov1=" + cov1 + " Cov2=" + cov2);
				logger.info("Rule1: " + r1.toStringRegla());
				logger.info("Rule2: " + r2.toStringRegla());
				if (cov1 > cov2) {
					fRules1.add(r1);
					Regla aux_r1 = new Regla(r1.expressio, r2.getConseguent(),
							r1.identificador, r1.getGestor(), null);
					aux_r1.setConseguent(r2.getConseguent());

					logger.info("Rule aux: " + aux_r1.toStringRegla());
					logger.info("Rule aux neg: "
							+ this.negateRule(aux_r1).toStringRegla());
					fRules2.add(this.negateRule(aux_r1));
				} else if (cov2 > cov1) {
					fRules2.add(r2);
					Regla aux_r2 = new Regla(matriuActual);
					aux_r2.setConseguent(r1.getConseguent());
					fRules1.add(this.negateRule(aux_r2));
				} else {
					fRules2.add(r2);
					fRules1.add(r1);
				}
			}
			it1 = fRules1.iterator();
			while (it1.hasNext()) {
				r1 = it1.next();
				logger.info("RULES1 " + r1.toStringRegla());
			}
			it2 = fRules2.iterator();
			while (it2.hasNext()) {
				r2 = it2.next();
				logger.info("RULES2 " + r2.toStringRegla());
			}

			// Now we add the non-intersected rules
			it1 = rules1.iterator();
			while (it1.hasNext()) {
				r1 = it1.next();
				rest1 = r1.expressio.getPropietats();
				rest1.retainAll(inters);
				if (rest1.isEmpty()) {
					fRules1.add(r1);
					break;
				}
			}
			// rules2
			it2 = rules2.iterator();
			while (it2.hasNext()) {
				r2 = it2.next();
				rest2 = r2.expressio.getPropietats();
				rest2.retainAll(inters);
				if (rest2.isEmpty()) {
					fRules2.add(r2);
					break;
				}
			}
			returnRules[0] = fRules1;
			returnRules[1] = fRules2;
		}
		return returnRules;
	}

	/**
	 * Return a subset of rules which do not contains any variable especified in
	 * properties
	 * 
	 * @param rules
	 * @param properties
	 * @return subset of rules Vector<Regla>
	 */
	public Vector<Regla> cleanRules(Vector<Regla> rules,
			HashSet<String> properties) {
		// logger.info("CLEAN_RULES "+rules.toString()+"--"+properties.toString());
		Vector<Regla> finalRules = new Vector<Regla>();
		if (rules.size() == 0) {
			return null;
		}
		Iterator<Regla> it = rules.iterator();
		HashSet<String> pcopy = new HashSet<String>(properties);
		// logger.info("   " + properties.toString());
		while (it.hasNext()) {
			pcopy = new HashSet<String>(properties);
			Regla r1 = it.next();
			// logger.info("   RULE:" + r1.toStringRegla());
			pcopy.retainAll(r1.expressio.getPropietats()); // INTERSECTION set
			if (pcopy.isEmpty()) { // if the intersection is empty
				finalRules.add(r1);
				// logger.info("     -> Adding rule");
			} else {
				// logger.info("     -> Intersection found in variables "
				// + pcopy.toString());
			}
		}
		return finalRules;
	}

	/**
	 * Return a set with the properties (variables) referenced in every rules of
	 * rules
	 * 
	 * @param rules
	 * @return hashset
	 * @author Marco Villegas Garcia
	 */
	public HashSet<String> getRulesProperties(Vector<Regla> rules) {
		HashSet<String> prop1 = new HashSet<String>();
		HashSet<String> aux_prop;
		Iterator<Regla> it1 = rules.iterator();
		float conf = 0;
		while (it1.hasNext()) {
			Regla r1 = it1.next();
			try {
				conf = r1.confidence();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			aux_prop = r1.expressio.getPropietats();
			System.out.println("RULE CONFIDENCE: " + Float.toString(conf) + " "
					+ aux_prop);
			prop1.addAll(aux_prop);
		}
		return prop1;
	}

	/**
	 * Execute the method BL_pCWA on the arguments.
	 * 
	 * @param r1
	 *            Vector<Regla>
	 * @param r2
	 *            Vector<Regla>
	 * @return Object[] of two positions, corresponding to the arguments
	 * @throws Exception
	 */
	public Regla[] BL_pCWA(Vector<Regla> tmpMaxRegla1_Regles,
			Vector<Regla> tmpMaxRegla2_Regles, float[] max1, float[] max2)
			throws Exception {
		/**
		 * @new by Marco Villegas. He copiado el case "CCEC_BLC_CWA" y lo he
		 *      adaptado al caso "CCEC_BLC_pCWA"
		 */
		HashSet<String> prop1;
		HashSet<String> prop2;
		Vector<Regla> cleanTmpMaxReglas1, cleanTmpMaxReglas2;
		Regla cleanBestRule0, cleanBestRule1;
		Regla[] bestRules = new Regla[2];

		Regla tmpBestRule0, tmpBestRule1;
		Vector<Regla> tmpBLCWAFirst = new Vector<Regla>();
		Vector<Regla> tmpBLCWASecond = new Vector<Regla>();

		prop1 = getRulesProperties(tmpMaxRegla1_Regles); // properties of rules1
		prop2 = getRulesProperties(tmpMaxRegla2_Regles); // properties of rules1
		// Now we build the set of rules not having intersection of properties
		// (variables)
		cleanTmpMaxReglas1 = cleanRules(tmpMaxRegla1_Regles, prop2);
		cleanTmpMaxReglas2 = cleanRules(tmpMaxRegla2_Regles, prop1);

		if (max1[COVERINGR_IDX] == 1) { // si ConvR = 1 (pg 113 de Thesis Ale)
			tmpBestRule0 = this.composeRule(tmpMaxRegla1_Regles, new AND());
			cleanBestRule0 = this.composeRule(cleanTmpMaxReglas1, new AND());
		} else {
			tmpBestRule0 = this.composeRule(tmpMaxRegla1_Regles, new OR());
			cleanBestRule0 = this.composeRule(cleanTmpMaxReglas1, new OR());
		}
		if (max2[COVERINGR_IDX] == 1) { // si ConvR = 1 (pg 113 de Thesis Ale)
			tmpBestRule1 = this.composeRule(tmpMaxRegla2_Regles, new AND());
			cleanBestRule1 = this.composeRule(cleanTmpMaxReglas2, new AND());
		} else {
			tmpBestRule1 = this.composeRule(tmpMaxRegla2_Regles, new OR());
			cleanBestRule1 = this.composeRule(cleanTmpMaxReglas2, new OR());
		}
		tmpBLCWAFirst.add(tmpBestRule0);
		if (cleanBestRule1 != null)
			tmpBLCWAFirst.add(this.negateRule(cleanBestRule1));
		bestRules[0] = this.composeRule(tmpBLCWAFirst, new OR());

		tmpBLCWASecond.add(tmpBestRule1);
		if (cleanBestRule0 != null)
			tmpBLCWASecond.add(this.negateRule(cleanBestRule0));
		bestRules[1] = this.composeRule(tmpBLCWASecond, new OR());
		return bestRules;
	}

	/**
	 * Return an 2-rules array.
	 * 
	 * @param bc
	 *            -
	 * @param noms
	 *            -
	 * @param mods
	 *            -
	 * @param kiMethod
	 *            - Knowledge Interpretation Method.
	 * 
	 * @return
	 * 
	 * @author Esther Lozano
	 * @author Grup SISPD QT 2009-2010
	 */
	public Regla[] bestRules(BaseConeixement bc, Iterator noms, String[] mods,
			int kiMethod) throws Exception {
		Regla[] bestRules = new Regla[2];

		Vector<String> maxRegla1 = new Vector<String>(); // Nom de les max
															// regles de la
															// modality 0
		Vector<String> maxRegla2 = new Vector<String>(); // Nom de les max
															// regles de la
															// modality 1
		float[] max1 = new float[MAX_RULE_METRICS]; // Valors (suport,
													// confianca, covertura) de
													// la max regla per la
													// modality 0
		float[] max2 = new float[MAX_RULE_METRICS];// Valors (suport, confianca,
													// covertura) de la max
													// regla per la modality 1
		while (noms.hasNext()) {
			String nomRegla = (String) noms.next();
			Regla r = bc.obtenirRegla(nomRegla);
			int pos = r.conseguent.indexOf(")");
			String subCons = r.conseguent.substring(pos + 1);

			// es mira si el consequent de la regla es de la modality 0 o 1
			// (primera o segona classe)

			if (subCons.compareTo(mods[0]) == 0) // rule of the first class
			{
				this.compareRules(maxRegla1, r, max1, kiMethod);
			} else {
				this.compareRules(maxRegla2, r, max2, kiMethod);
			}
		}
		logger.info("Rules class 0:" + maxRegla1.size() + "\nRules class 1:"
				+ maxRegla2.size());

		Vector<Regla> tmpMaxRegla1_Regles = this
				.getRulesFromList(bc, maxRegla1);
		Vector<Regla> tmpMaxRegla2_Regles = this
				.getRulesFromList(bc, maxRegla2);
		// NUEVO ALBERTO
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		Regla tmpBestRule0, tmpBestRule1;
		Vector<Regla> tmpBLCWAFirst = new Vector<Regla>();
		Vector<Regla> tmpBLCWASecond = new Vector<Regla>();
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<3<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		Vector<Regla> m5Rules1, m5Rules2;

		switch (kiMethod) {
		case CCEC_BLC_NoCWA_Simple: // Best Local Concept and No Close-World
									// Assumption Simple
			bestRules[0] = tmpMaxRegla1_Regles.get(0);
			bestRules[1] = tmpMaxRegla2_Regles.get(0);
			break;
		case CCEC_BLC_NoCWA: // Best Local Concept and No Close-World Assumption
			if (max1[COVERINGR_IDX] == 1) { // si ConvR = 1 (pg 113 de Thesis
											// Ale)
				bestRules[0] = this.composeRule(tmpMaxRegla1_Regles, new AND());
			} else {
				bestRules[0] = this.composeRule(tmpMaxRegla1_Regles, new OR());
			}

			if (max2[COVERINGR_IDX] == 1) { // si ConvR = 1 (pg 113 de Thesis
											// Ale)
				bestRules[1] = this.composeRule(tmpMaxRegla2_Regles, new AND());
			} else {
				bestRules[1] = this.composeRule(tmpMaxRegla2_Regles, new OR());
			}

			// if(max1[COVERINGR_IDX] == 1) //si ConvR = 1 (pg 113 de Thesis
			// Ale)
			// {
			// bestRules[0] = this.composeRule(bc, maxRegla1,
			// ExpressioBooleana.AND);
			// bestRules[1] = this.composeRule(bc, maxRegla1,
			// ExpressioBooleana.AND);
			// }
			// else
			// {
			// bestRules[0] = this.composeRule(bc, maxRegla2,
			// ExpressioBooleana.OR);
			// bestRules[1] = this.composeRule(bc, maxRegla2,
			// ExpressioBooleana.OR);
			// }
			break;
		case CCEC_BLC_CWA: // Best Local Concept and Close-World Assumption
			// NUEVO ALBERTO
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			// Regla tmpBestRule0, tmpBestRule1;
			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			if (max1[COVERINGR_IDX] == 1) { // si ConvR = 1 (pg 113 de Thesis
											// Ale)
				tmpBestRule0 = this.composeRule(tmpMaxRegla1_Regles, new AND());
			} else {
				tmpBestRule0 = this.composeRule(tmpMaxRegla1_Regles, new OR());
			}

			if (max2[COVERINGR_IDX] == 1) { // si ConvR = 1 (pg 113 de Thesis
											// Ale)
				tmpBestRule1 = this.composeRule(tmpMaxRegla2_Regles, new AND());
			} else {
				tmpBestRule1 = this.composeRule(tmpMaxRegla2_Regles, new OR());
			}
			// NUEVO ALBERTO
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			// Vector<Regla> tmpBLCWAFirst = new Vector<Regla>();
			// Vector<Regla> tmpBLCWASecond = new Vector<Regla>();
			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

			tmpBLCWAFirst.add(tmpBestRule0);
			tmpBLCWAFirst.add(this.negateRule(tmpBestRule1));
			bestRules[0] = this.composeRule(tmpBLCWAFirst, new OR());

			tmpBLCWASecond.add(tmpBestRule1);
			tmpBLCWASecond.add(this.negateRule(tmpBestRule0));
			bestRules[1] = this.composeRule(tmpBLCWASecond, new OR());
			break;
		case CCEC_BGC_NoCWA: // Best Global Concept and No Close-World
								// Assumption
			break;
		case CCEC_BGC_CWA: // Best Global Concept and Close-World Assumption

			boolean tmpIsFirstBest = this.isBestGlobalFirstSet(maxRegla1,
					maxRegla2, max1, max2);
			if (tmpIsFirstBest) {
				this.buildBGCWArules(bestRules, 0, max1, tmpMaxRegla1_Regles,
						tmpMaxRegla2_Regles.get(0).conseguent); // crea la
																// bestrule[0] i
																// posa el
																// bestrules[1]
																// =
																// bestrules[0]
			} else {
				this.buildBGCWArules(bestRules, 1, max2, tmpMaxRegla2_Regles,
						tmpMaxRegla1_Regles.get(0).conseguent); // crea la
																// bestrule[1] i
																// posa el
																// bestrules[0]
																// =
																// bestrules[1]
			}
			break;
		case CCEC_BLC_pCWA:
			/**
			 * @new by Marco Villegas.
			 */
			bestRules = BL_pCWA(tmpMaxRegla1_Regles, tmpMaxRegla2_Regles, max1,
					max2);
			break;
		case CCEC_BLGC_CWA:
			Object[] obj = cleanRules(tmpMaxRegla1_Regles, tmpMaxRegla2_Regles);
			m5Rules1 = (Vector<Regla>) obj[0];
			m5Rules2 = (Vector<Regla>) obj[1];
			bestRules = BL_pCWA(m5Rules1, m5Rules2, max1, max2);
			break;
		}

		return bestRules;
	}

	/**
	 * Method that generates a subset of fromHere, of those rules that have no
	 * common variable with any of those in notInHere, storing it in
	 * andStoreHere.
	 * 
	 * @param fromHere
	 * @param notInHere
	 * @param andStoreHere
	 * @throws Exception
	 * @author Alberto Martinez Gonzalez
	 */
	private Vector<Regla> generateValidSet(Vector<Regla> fromHere,
			Vector<Regla> notInHere, Vector<Regla> andStoreHere)
			throws Exception {
		andStoreHere.clear();
		Iterator<Regla> it = fromHere.iterator();
		Iterator<Regla> notIt = notInHere.iterator();

		while (it.hasNext()) {
			Regla currentRule = it.next();
			while (notIt.hasNext())
				if (!currentRule.equalAntecedents(notIt.next()))
					andStoreHere.add(currentRule);
		}
		return andStoreHere;
	}

	private Vector<Regla> generateSubset(Vector<Regla> fromHere,
			float withHigherCRThanThis, Vector<Regla> andStoreHere)
			throws Exception {
		andStoreHere.clear();
		Iterator<Regla> it = fromHere.iterator();

		while (it.hasNext()) {
			Regla currentRule = it.next();
			if (currentRule.coveringR() > withHigherCRThanThis)
				andStoreHere.add(currentRule);
		}
		return andStoreHere;
	}

	// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**
	 * Crea les dos regles de best global and close world assumtion
	 * 
	 * @param bestRules
	 * @param theRuleIndex
	 * @param max1
	 * @param tmpMaxRegla1_Regles
	 * @throws Exception
	 * @author Grup SISPD QT 2009-2010
	 */
	private void buildBGCWArules(Regla[] bestRules, int theRuleIndex,
			float[] max, Vector<Regla> tmpMaxRegla_Regles,
			String theOtherConsequent) throws Exception {
		if (max[COVERINGR_IDX] == 1) {
			bestRules[theRuleIndex] = this.composeRule(tmpMaxRegla_Regles,
					new AND());
		} else {
			bestRules[theRuleIndex] = this.composeRule(tmpMaxRegla_Regles,
					new OR());
		}
		Regla tmpNegatedRule = this.negateRule(bestRules[theRuleIndex]);
		tmpNegatedRule.conseguent = theOtherConsequent;
		bestRules[(theRuleIndex + 1) % 2] = tmpNegatedRule;
	}

	/**
	 * Retorna si la millor regla global és la primera
	 * 
	 * @param maxRegla1
	 * @param maxRegla2
	 * @param max1
	 * @param max2
	 * @author Grup SISPD QT 2009-2010
	 */
	private boolean isBestGlobalFirstSet(Vector<String> maxRegla1,
			Vector<String> maxRegla2, float[] max1, float[] max2) {
		// 1. determinar la classe millor caracteritzada
		if (max1[CONFIDENCE_IDX] > max2[CONFIDENCE_IDX]) {
			return true; // max1 guanya
		} else if (max1[CONFIDENCE_IDX] < max2[CONFIDENCE_IDX]) {
			// max2 guanya
			return false;
		} else { // (max1[CONFIDENCE_IDX] == max2[CONFIDENCE_IDX])
			if (max1[COVERINGR_IDX] > max2[COVERINGR_IDX]) {
				// max1 guanya
				return true;
			} else if (max1[COVERINGR_IDX] < max2[COVERINGR_IDX]) {
				// max2 guanya
				return false;
			} else // (max1[COVERINGR_IDX] == max2[COVERINGR_IDX])
			{
				if (maxRegla1.size() >= maxRegla2.size()) {
					// maxregla1
					return true;
				} else {
					// maxregla2
					return false;
				}
			}
		}
	}

	/**
	 * Obtenir el conjunt de regles negades corresponent al indicat com a
	 * parametre
	 * 
	 * @param reglesAnegar
	 * @return
	 * @throws Exception
	 * @author Grup SISPD QT 2009-2010
	 */
	private Vector<Regla> negacioRegles(Vector<Regla> reglesAnegar)
			throws Exception {
		Vector<Regla> tmpReglesNegades = new Vector<Regla>();
		Iterator<Regla> tmpIterator = reglesAnegar.iterator();
		while (tmpIterator.hasNext()) {
			Regla tmpRuleToNegate = tmpIterator.next();
			tmpReglesNegades.add(this.negateRule(tmpRuleToNegate));
		}
		return tmpReglesNegades;
	}

	/**
	 * Method that, given two rules belonging to the same class, applies the
	 * selected knowledge integration criteria and returns the best one
	 * 
	 * @param maxRegla
	 *            -
	 * @param r
	 *            -
	 * @param max
	 *            -
	 * @param kiMethod
	 *            - Knowledge Interpretation Method.
	 * 
	 * @author Esther Lozano
	 * @author Grup SISPD QT 2009-2010
	 */
	public void compareRules(Vector<String> maxRegla, Regla r, float[] max,
			int kiMethod) throws Exception {
		float supp = r.support();
		float cover = r.coveringR();
		float conf = r.confidence();

		switch (kiMethod) {
		case CCEC_BLC_NoCWA_Simple: // Best Local Concept and No Close-World
									// Assumption Simple
			if (conf > max[CONFIDENCE_IDX]) {
				maxRegla.clear();
				maxRegla.add(r.nomRegla);
				max[CONFIDENCE_IDX] = conf;
				max[COVERINGR_IDX] = cover;
				max[SUPPORT_IDX] = supp;
			} else if (conf == max[CONFIDENCE_IDX]) {
				// We compare support of the rule, if they have the same value,
				// we compare by covering
				if (cover > max[COVERINGR_IDX]) {
					maxRegla.clear();
					maxRegla.add(r.nomRegla);
					max[CONFIDENCE_IDX] = conf;
					max[COVERINGR_IDX] = cover;
					max[SUPPORT_IDX] = supp;
				} else {
					if (cover == max[COVERINGR_IDX] && supp > max[SUPPORT_IDX]) {
						maxRegla.clear();
						maxRegla.add(r.nomRegla);
						max[CONFIDENCE_IDX] = conf;
						max[COVERINGR_IDX] = cover;
						max[SUPPORT_IDX] = supp;
					}
				}
			}
			break;
		case CCEC_BLC_NoCWA: // Best Local Concept and No Close-World Assumption
		case CCEC_BLC_CWA: // Best Local Concept and Close-World Assumption
		case CCEC_BGC_CWA: // Best Global Concept and Close-World Assumption
			// NUEVO ALBERTO
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		case CCEC_BLC_pCWA:
		case CCEC_BLGC_CWA:
			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			// We maximize the confident
			if (conf > max[CONFIDENCE_IDX]) {
				maxRegla.clear();
				maxRegla.add(r.nomRegla);
				max[CONFIDENCE_IDX] = conf;
				max[COVERINGR_IDX] = cover;
				max[SUPPORT_IDX] = supp;
			} else if (conf == max[CONFIDENCE_IDX]) {
				// We maximize the relative covering
				if (cover > max[COVERINGR_IDX]) {
					maxRegla.clear();
					maxRegla.add(r.nomRegla);
					max[COVERINGR_IDX] = cover;
				} else {
					if (cover == max[COVERINGR_IDX]) {
						maxRegla.add(r.nomRegla);
					}
				}
			}
			break;
		case CCEC_BGC_NoCWA: // Best Global Concept and No Close-World
								// Assumption
			break;
		}
	}

	/**
	 * Composa un conjunt de regles amb una operacio booleana
	 * 
	 * @param regles
	 * @param op
	 * @return
	 * @throws Exception
	 * 
	 * @author Grup SISPD QT 2009-2010
	 */
	public Regla composeRule(Vector<Regla> regles, EBLogica op)
			throws Exception {
		if (regles == null) {
			return null;
		}
		if (regles.size() == 0) {
			return null;
		}
		Iterator<Regla> it = regles.iterator();

		String nom = "";
		String cons = "";
		ExpressioBooleana eb = null;
		GestorMatriu g = null;
		int id = 0;
		boolean first = true;

		while (it.hasNext()) {
			Regla currentRule = it.next();
			if (first) {
				nom = currentRule.nomRegla;
				cons = currentRule.conseguent;
				g = currentRule.gestor;
				id = currentRule.identificador;

				eb = currentRule.expressio;
				op.afegeixFill(currentRule.expressio);
				first = false;
			} else {
				nom += "_" + currentRule.nomRegla;

				op.afegeixFill(currentRule.expressio);

				eb = new ExpressioBooleana(matriusCarregades[matriuActual]);
				eb = eb.llegirBCNormal(op.escriureNormal());
				op.getAlFills().clear();
				op.afegeixFill(eb);

			}
		}

		return new Regla(eb, cons, id, g, nom);
	}

	/**
	 * 
	 * @param bc
	 *            - Knowledge base
	 * @param maxRegla
	 *            -
	 * @param op
	 *            -
	 * 
	 * @return
	 * 
	 * @author Esther Lozano
	 */
	public Regla composeRule(BaseConeixement bc, Vector<String> maxRegla,
			String op) throws Exception {
		Iterator it = maxRegla.iterator();
		String e = "";
		String nom = "";
		String cons = "";
		GestorMatriu g = null;
		int id = 0;
		boolean first = true;

		while (it.hasNext()) {
			if (first) {
				Regla aux = bc.obtenirRegla((String) it.next());
				e = aux.expressio.escriureNormal();

				nom = aux.nomRegla;
				cons = aux.conseguent;
				g = aux.gestor;
				id = aux.identificador;

				first = false;
			} else {
				Regla aux = bc.obtenirRegla((String) it.next());
				e += op + aux.expressio.escriureNormal();

				nom += "_" + aux.nomRegla;
			}
		}

		ExpressioBooleana eb = new ExpressioBooleana(
				matriusCarregades[matriuActual]);
		eb = eb.llegirBCNormal(e);

		Regla r = new Regla(eb, cons, id, g, nom);

		return r;
	}

	/**
	 * Obté les regles corresponents als noms indicats
	 * 
	 * @param bc
	 * @param ruleNames
	 * @return
	 * @throws Exception
	 * 
	 * @author Grup SISPD QT 2009-2010
	 */
	private Vector<Regla> getRulesFromList(BaseConeixement bc,
			Vector<String> ruleNames) throws Exception {
		Iterator<String> it = ruleNames.iterator();
		Vector<Regla> tmpResult = new Vector<Regla>();
		while (it.hasNext()) {
			tmpResult.add(bc.obtenirRegla(it.next()));
		}
		logger.info("Listresult " + tmpResult.size());
		return tmpResult;
	}

	/**
	 * Donada una regla, generem una nova regla amb el negat de l'antecedent
	 * 
	 * @param bc
	 *            - Knowledge base
	 * @param nomRegla
	 *            - Nom de la regla a negar *
	 * @return Regla - negada
	 * 
	 * @author Grup SISPD QT 2009-2010
	 */
	public Regla negateRule(Regla reglaBase) throws Exception {
		if (reglaBase == null) {
			return null;
		}
		String outNom = ExpressioBooleana.NOT + "(" + reglaBase.getNomRegla()
				+ ")"; // not_nomregla

		NOT tmpNotExpression = new NOT();
		tmpNotExpression.afegeixFill(reglaBase.expressio);

		ExpressioBooleana eb = new ExpressioBooleana(
				matriusCarregades[matriuActual]);
		eb = eb.llegirBCNormal(tmpNotExpression.escriureNormal());

		Regla r = new Regla(eb, reglaBase.conseguent, reglaBase.identificador,
				reglaBase.gestor, outNom);

		return r;
	}

	/**
	 * Method that generates a TEX file with values of quality of the knowledge
	 * base
	 * 
	 * @param nomBC
	 *            - name of the knowledge base
	 * @param bConf
	 *            - indicates whether calculate or not the confidence of the
	 *            rules
	 * @param bSupp
	 *            - indicates whether calculate or not the support of the rules
	 * @param bCovR
	 *            - indicates whether calculate or not the relative covering of
	 *            the rules
	 * @param bCovG
	 *            - indicates whether calculate or not the global covering of
	 *            the rules
	 * @param bSuppT
	 *            - indicates whether calculate or not the total support of the
	 *            knowledge base
	 * @param bConfM
	 *            - indicates whether calculate or not the mean confidence of
	 *            the knowledge base
	 * @param esPart
	 *            - indicates the options to create the tex file: 0 -> print
	 *            opening and ending of the file (the file has just one part) 1
	 *            -> print opening of the file (this is the first part of the
	 *            file) 2 -> not print anything (this is a middle part of the
	 *            file) 3 -> print the ending of the file (this is the last part
	 *            of the file)
	 * 
	 * @return true if the process is succeed, false otherwise
	 * 
	 * @author Esther Lozano
	 */
	public boolean ferQualitatBC(String nomBC, boolean bConf, boolean bSupp,
			boolean bCovR, boolean bCovG, boolean bSuppT, boolean bConfM,
			int esPart, String subfix){
		FitxerTex fitxer;
		String nomBase, nomFitxer;
		GeneradorTex gen;

		nomBase = obtenirDirResultats() + new File(obtenirNom()).getName();

		// nomFitxer = nomBase + "dreg";
		nomFitxer = nomBase + subfix;

		fitxer = new FitxerTex(nomFitxer);
		gen = new GeneradorTex(fitxer);
		return ferQualitatBC(nomBC, bConf, bSupp, bCovR, bCovG, bSuppT, bConfM, esPart, subfix, gen);
	}
	public boolean ferQualitatBC(String nomBC, boolean bConf, boolean bSupp,
			boolean bCovR, boolean bCovG, boolean bSuppT, boolean bConfM,
			int esPart, String subfix, GeneradorTex gen) {
		boolean ok = true;
		

		BaseConeixement bc = obtenirBC(nomBC);
		int n = bc.alNomsRegles.size();
		float[] conf;
		float[] supp;
		float[] covR;
		// float[] covG;
		float covG = 0;
		float suppT = 0;
		float mConf = 0;

		Vector<float[]> values = new Vector<float[]>();

		try {
			if (bConf) {
				conf = new float[n];
				conf = bc.getConfidenceValues();
				values.add(conf);
			}

			if (bSupp) {
				supp = new float[n];
				supp = bc.getSupportValues();
				values.add(supp);
			}

			if (bCovR) {
				covR = new float[n];
				covR = bc.getCoveringRValues();
				values.add(covR);
			}

			if (bCovG) {
				// covG = new float[n];
				covG = bc.coveringG();
				// values.add(covG);
			}

			if (bSuppT) {
				suppT = bc.totalSupport();
			}

			if (bConfM) {
				mConf = bc.meanConfidence();
			}

			ArrayList alNoms = new ArrayList();
			ArrayList alReglesNorm = matriusCarregades[matriuActual]
					.escriureReglaNormal(nomBC);

			alNoms = bc.getAlNomsRegles();

			ArrayList alRegles = new ArrayList();

			for (int i = 0; i < bc.numRegles(); i++) {
				alRegles.add(bc.obtenirRegla((String) alNoms.get(i)));
			}

			boolean[] columns = new boolean[6];
			columns[0] = bConf;
			columns[1] = bSupp;
			columns[2] = bCovR;
			columns[3] = bCovG;
			columns[4] = bSuppT;
			columns[5] = bConfM;

			gen.generarLtxQualitatBC(nomBC, alReglesNorm, alRegles, columns,
					values, suppT, mConf, covG, esPart);

		} catch (Exception e) {
			ok = false;
			e.printStackTrace();
		}

		return ok;
	}

	/**
	 * This function generates the descriptive file for the given knowledge
	 * base. It returns the name of the generated file.
	 * 
	 * @param nomBC
	 *            - name of the knowledge base
	 * 
	 * @return true if the process is succeed, false otherwise
	 * 
	 * @author Esther Lozano
	 */
	public boolean generarDescrp(String nomBC, int esPart) {
		Opcions opcUniv = new Opcions(Opcions.UNIVARIANT);
		opcUniv.posarPerDefecteBC(Opcions.TAULA_FREQS);
		opcUniv.posarPerDefecteBC(Opcions.DIAGR_BARRES);

		Opcions opcClass = new Opcions(Opcions.PER_CLASSES);
		opcClass.posarPerDefecteBC(Opcions.DESCR_EXTENSIONAL);

		ArrayList llistaBC = new ArrayList();// conté els noms de les BC's
												// avaluades
		ArrayList llistaVariables = new ArrayList();// conté llistes, que alhora
													// contenen els vectors de
													// variables
		ArrayList llistaProp = new ArrayList();
		ArrayList al = new ArrayList();
		int tipusDescriptiva = 0;// 0-->Univariant, ,1-->Per classes,
									// 2-->Univariant i per classes
		boolean ok = true;
		String descrpFile = null;

		// llistaVars[0][] - It contains the list of numerical variables
		// (univariant)
		// llistaVars[1][] - It contains the list of categorical variables
		// (univariant)
		String[][] llistaVars = new String[2][];
		// llistaVars[0][] - Conté la llista de vars categ de classe per desc.
		// classes
		// llistaVars[1][] - Conté la llista de vars numeriques per desc.
		// classes
		// llistaVars[2][] - Conté la llista de vars categoriques per desc.
		// classes
		String[][] llistaVarsC = new String[3][];
		// llistat dels estadistics que s'han de calcular per univariant
		// llistaEstads[0] - Conté la llista d'estad. numerics per univariant
		// llistaEstads[1] - Conté la llista d'estad. categorics per univariant
		Vector[] llistaEstads = new Vector[2];
		// llistaEstads[0] - Conté la llista d'estad. NN per desc. classes
		// llistaEstads[1] - Conté la llista d'estad. NC per desc. classes
		// llistaEstads[2] - Conté la llista d'estad. CC per desc. classes
		Vector[] llistaEstadsC = new Vector[3];
		int err, iNumProps = 0;// nvarsC és el nombre de bases de coneixement
								// seleccionades
		String cmd;
		Process proc;
		llistaEstads[0] = new Vector();
		llistaEstads[1] = new Vector();
		llistaEstadsC[0] = new Vector();
		llistaEstadsC[1] = new Vector();
		llistaEstadsC[2] = new Vector();
		llistaEstads[1].add(new Integer(Opcions.DIAGR_BARRES));
		llistaEstads[1].add(new Integer(Opcions.TAULA_FREQS));
		llistaEstadsC[0].add(new Integer(Opcions.DESCR_EXTENSIONAL));
		tipusDescriptiva = 3;

		try {
			// Evaluation must be done rule by rule
			llistaProp = avaluaReglaARegla(nomBC, false);
			iNumProps = iNumProps + llistaProp.size();

			llistaBC.add(nomBC);
			llistaVars[0] = new String[0];
			llistaVars[1] = new String[llistaProp.size()];

			llistaVarsC[0] = new String[llistaProp.size()];
			llistaVarsC[1] = new String[0];
			llistaVarsC[2] = new String[0];

			for (int j = 0; j < llistaProp.size(); j++) {
				Propietat paux = (Propietat) llistaProp.get(j);
				llistaVars[1][j] = paux.obtenirId();
				llistaVarsC[0][j] = paux.obtenirId();
			}

			al = new ArrayList(2);
			al.add(llistaVars);
			al.add(llistaVarsC);
			llistaVariables.add(al);

			// ok =
			// ferDescripBC(llistaBC,llistaVariables,llistaEstads,llistaEstadsC,opcUniv,opcClass,1,tipusDescriptiva,esPart,false,
			// "generarDescrp");
			ok = ferDescripBC(llistaBC, llistaVariables, llistaEstads,
					llistaEstadsC, opcUniv, opcClass, 1, tipusDescriptiva,
					esPart, false, "CCEC");

		} catch (ArrayIndexOutOfBoundsException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ok;
	}
}

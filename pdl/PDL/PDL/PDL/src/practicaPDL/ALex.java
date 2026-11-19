package practicaPDL;

//import java.io.BufferedWriter;
//
//import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileWriter;
import java.io.IOException;

/*
 * Implementa el analizador lexico del compilador.
 * Lee un archivo fuente caracter a caracter, reconoce lexemas
 * y genera los tokens correspondientes.
 */

public class ALex {

	public LectorFichero lector;
//	private static String rutaArchivo = "C:\\Users\\mmart\\OneDrive - Universidad Politécnica de Madrid\\FI Sexto\\PDL\\";
//	public static String nombreArchivo="PIdG90.txt";
	public final Tokens tokens = new Tokens();
//	public static int indexTokens=0;
	public static int caracter;
	private char car;
//	private TPR palRes;
	boolean primero;
	public boolean eof = false;
	private ASem aSem;
	String aux = "";


	// Constructor
	public ALex(LectorFichero lector) throws FileNotFoundException {
		this.lector = lector;
		lector.inicializarLector();
//		System.out.println(lector == null);
//		this.palRes = new TPR();
//		palRes.inicializar();
		primero = false;
	}

    public void setASem(ASem aSem) {
        this.aSem = aSem;
    }
	
    /**
     * Ejecuta el analisis lexico del archivo fuente.
     * Lee caracter a caracter, aplica las transiciones del AFD,
     * construye lexemas y genera tokens segun las acciones del automata.
     *
     * throws IOException Si hay problemas al leer el archivo.
     * throws Error Si se detecta un error lexico (caracter no valido, numero fuera de rango, etc..)
     */
	public Token AL() throws IOException, Error {
		Token res = null;
		Integer estado=0;
		String aux = "";
		if (!primero) {
			leer();
			primero = true;
		}
		StringBuilder lex= new StringBuilder();
		int num = 0;	 // Valor numerico temporal
		int dec = 0;	 // Contador de decimales
		int cont=0;		  // Contador para longitud de cadenas
		while (estado != null && !cerrado()) {
		    if (cerrado()) break; // Evita seguir si el archivo ya se cerro

//		    System.out.println(car);
			// Control de lineas
		    if (lector != null && lector.c == '\n') {
		        lector.line++;
		    }
//			System.out.println("estado" + estado);			
		    if (estado != null && estado >= 11) {
		        estado = 0;
		    }
//			System.out.println("estado" + estado);
			String accion = AFD.accion(estado, lector.c);
			aux = accion;
			estado = AFD.estado(estado, lector.c);
//			System.out.println(estado+" "+"Accion" +accion);

			// Distintas acciones semánticas del Analizador Léxico
			switch (accion) {
			case "L" : // Leer sin acumular
				leer();
				break;
			case "C" : // Acumular caracter en lexema

				lex.append(lector.c);
//				System.out.println(lector.c);
				leer();
//				System.out.println(lex);
				break;
			case "C'" :	// Acumular caracter y contar longitud			
				lex.append(lector.c);
				cont++;
//				System.out.println(car);
//				System.out.println(lex);				
				leer();
//				System.out.println(car);
				break;
			case "C1" :  // Variante de acumulacion
				lex.append(lector.c);
				leer();
				break;
			case "C1'" :
				lex.append(lector.c);
				leer();
				break;
			case "N":
			    num = (lector.c - '0');  // inicializa el numero
			    leer();
			    break;

			case "N'": // Continuacion de numero entero
			    num = num * 10 + (lector.c - '0'); // anade nuevo digito
			    leer();
			    break;
			case "R" : // Parte decimal de numero real
			    dec++;
			    num = num * 10 + (lector.c - '0');
			    leer();
			    break;

				// --- Generacion de tokens para signos y delimitadores ---

			case "G1" :
				res = new Token("coma","");
//				tokens.add(res);
				leer();
				return res;
			
			case "G2" :

				res = new Token("puntoComa","");
//				tokens.add(res);
				leer();
//				System.out.println("Tokens:" +tokens.toString());
//				System.out.println("Lista de Tokens: " + tokens.toString() );
				return res;
			case "G3" :

				res = new Token("dosPuntos","");
//				tokens.add(res);
				leer();
//				System.out.println("Lista de Tokens: " + tokens.toString() );
				return res;
				
			case "G4" :

				res = new Token("abPar","");
//				tokens.add(res);
				leer();
				return res;
				
			case "G5" :

				res = new Token("cePar","");
//				tokens.add(res);
				leer();
				return res;
				
			case "G6" :

				res = new Token("abLlave","");
//				tokens.add(res);
				leer();
				
				return res;
				
			case "G7" :

				res = new Token("ceLlave","");
//				tokens.add(res);
				leer();
				
				return res;
				
			case "G8" :
				res = new Token("suma","");
//				tokens.add(res);
				leer();
				
//				System.out.println("Lista de Tokens: " + tokens.toString() );
				
				return res;
			case "G9" :

				res = new Token("asig","");
//				tokens.add(res);
				leer();
				
//				System.out.println("Lista de Tokens: " + tokens.toString() );
			
				return res;
			case "G10" :
				res = new Token("and","");
//				tokens.add(res);
				leer();
				return res;
			case "G11" :

				res = new Token("asigMult","");
//				tokens.add(res);
				leer();
//				indexTokens++;
				return res;
			case "G12" :
				res = new Token("mayor", "");
//				tokens.add(res);
				leer();

				return res;

				// --- Cadena de caracteres ---

			case "G13" :
//				System.out.println(lex);
				if(cont <= 64) {
					lex.append(lector.c);
					res = new Token("cad",lex.toString());
//					tokens.add(res);
					lex.setLength(0);
					leer();
					cont = 0;
//					System.out.println(lex + " vacio?");
				}
				else {
//					ficheroTokens();
					throw new Error("Error en linea: " + lector.line + " : longitud de cadena tiene tiene una longitud superior a 64 caracteres");
				}

				

				return res;

				// --- Constante entera ---

			case "G14" :

				if (num <= 32767) {
					res = new Token("cInt", num);
//					tokens.add(res);
					 num = 0;
				

				}
				else {
//					ficheroTokens();
					throw new Error("Error en linea: " + lector.line + " : el valor de una numero entero no puede ser superior a 32767");
				}
				return res;
				
				// --- Constante real ---

			case "G15":
				double decimales;
				decimales = (double)num * Math.pow(10, (-dec));
//				System.out.println(decimales);
				if(decimales <= 117549436.0) {
					res = new Token("cReal", decimales);
//					tokens.add(res);
					num = 0;
					dec = 0;
				}
				else {
//					ficheroTokens();
					throw new Error("Error en linea: " + lector.line + " : el valor de una numero real no puede ser superior a 117549436.0");
				}
				
				return res;

				 // --- Identificadores y palabras reservadas ---

			case "G16":
				boolean esPalRes = aSem.esPalRes(lex.toString());
//				System.out.println(esPalRes);
				if (!esPalRes) { // if controlador palabra reservada
					if (aSem.getDeclaracion()){ // if controlador zona declaracion
						if(aSem.comprobarTS()) { //if comprobador TS Global
							int pos = aSem.gestor.getEntradaTSGlobal(lex.toString());
							if(pos != 0) { // if controlador esta en TS Global
								throw new Error("Error en linea: " + lector.line + " : el id ya estaba declarado");
							} //cierre if controlador esta en TS Global
							else { // else añadir a TS Global
								pos=aSem.gestor.addEntradaTSGlobal(lex.toString());
								if(pos==0){ // if error al añadir
									System.out.println("Error al añadir un identificador a la tabla de símbolos global");
									System.exit(1);
								} //cerrar if error al añadir
								res = new Token("id",pos);
//								tokens.add(res);
							} // cerrar else añadir a TS Global
						} // cerrar if comprobador TS Global
						else { // else comprobador TS Local
							int pos = aSem.gestor.getEntradaTSLocal(lex.toString());
							if(pos != 0) { // if controlador esta en TS Local
								throw new Error("Error en linea: " + lector.line + " : el id ya estaba declarado");
							} // cerrar if controlador TS Local
							else { // else añadir a TS Local
								pos=aSem.gestor.addEntradaTSLocal(lex.toString());
								if(pos==0){ // if error al añadir
									System.out.println("Error al añadir un identificador a la tabla de símbolos local");
									System.exit(1);
								} // cerrar if error al añadir
								res = new Token("id",pos);
//								tokens.add(res);
							}// cerrar else añadir a TS Local	
						}// cerrar else comprobador TS Local
					}// cerrar if controlador zona declaracion
					else { //else fuera de declaracion
						int pos = aSem.gestor.getEntradaTS(lex.toString());
						if(pos == 0) { //if esta en Alguna Tabla
							pos = aSem.gestor.addEntradaTSGlobal(lex.toString());
							res = new Token("id",pos);
						}// cerrar if esta en alguna TS	
						else{
							res = new Token("id", pos);
						}
					}// cerrar else fuera de declaracion
					ultimoLexema(lex.toString());
					lex.setLength(0);
					return res;
				}
				else{
					// cerrar if controlador Palabra reservada
				
				res = new Token(lex.toString(),"");
//				tokens.add(res);			
			    lex.setLength(0);
				return res;
				}
			}
		}

		 // --- Manejo de errores lexicos ---
		if (estado == null) {
			int err = Integer.parseInt(aux);
			if (err== 51 || err == 144) {
//				System.out.println((int)car);;
//				ficheroTokens();
				throw new Error("Error léxico " + aux + " en linea "+ lector.line + ": caracter " + car +  " no valido fuera de cadena o comentario");
			}
			if (err == 50) {
//				ficheroTokens();
				throw new Error("Error léxico " + aux + " en linea "+ lector.line + ": punto fuera de cadena o comentario solo puede ir entre digitos");
			}
			if ((err >= 53 && err<=73)|| err==140 || err == 145) {
//				ficheroTokens();
				throw new Error("Error léxico " + aux + " en linea "+ lector.line + ": despues de & solo puede ir otro &");
			}
			if ((err >= 74 && err<=94)|| err==141 || err == 146){
//				ficheroTokens();
				throw new Error("Error léxico " + aux + " en linea "+ lector.line +  ": despues de * solo puede ir un =");
			}
			if ((err == 142 || err == 143 || err== 147)) {
//				ficheroTokens();
				throw new Error("Error léxico " + aux + " en linea "+ lector.line +  ": caracter " + car + " no valido dentro de cadena");
			}
			if ((err >= 96 && err<=116)|| err==148 || err == 149){
//				ficheroTokens();
				throw new Error("Error léxico " + aux + " en linea "+ lector.line +  ": se necesita un dígito despues del .");
			}
			if ((err >= 117 && err<=137)|| err==150 || err == 151){
//				ficheroTokens();
				throw new Error("Error léxico " + aux + " en linea "+ lector.line +  ": despues de / solo puede ir / para formar un comentario");
			}
		}
		if (!eof) {
		res = new Token("eof", "");
//		tokens.add(res);
		eof = true;
	    cerrar();
		}
		else {
			res = new Token("eof", "");
		}
	    return res;
	}
	
	/*
     * Lee el siguiente caracter del archivo y actualiza el estado interno.
     * Si llega al final del archivo, genera el token "eof" y cierra el lector.
     */

//	public void leer() throws IOException {
//	    if (lector == null || lector.esEOF()) return; // no leer si ya esta cerrado o EOF
//	    car = lector.leerCaracter(); // lee realmente el siguiente caracter
//
//	    if (lector.esEOF()) {
//	        cerrar(); // cierra solo al final real del archivo
//	    }
//	}
	public void leer() throws IOException {
	    if (lector == null || lector.esEOF()) return;
	    car = lector.leerCaracter(); 
	}

	/*
     * Cierra el lector de fichero y libera los recursos.
     */

	public void cerrar() throws IOException {
	    if (lector != null) {
	        lector.cerrar();
	        lector = null;
	    }
	}

	// Comprobacion de estado del lector

	public boolean cerrado() {
	    return lector == null || lector.eof;
	}

	public void ultimoLexema(String lex) {
		aux = lex;
	}
	
	public String getAux() {
		return aux;
	}
	/*
     * Genera un archivo "tokens.txt" con la lista de tokens reconocidos
     * durante el analisis lexico.
     */

//	public void ficheroTokens () {
//		String nomArchivo = "tokens.txt";
//		try {
//			File archivo = new File(rutaArchivo+nomArchivo);
//			if (!archivo.exists()) {
//				archivo.createNewFile();
//				
//			}
//			FileWriter escritorArchivo = new FileWriter(archivo, false);
//			BufferedWriter bufferEscritor = new BufferedWriter(escritorArchivo);
//			
//			bufferEscritor.write(tokens.toString());
//			bufferEscritor.newLine();
//			
//			bufferEscritor.close();
//		}
//		catch (IOException e) {
//			System.out.println("Error al escribir en el archivo: " + e.getMessage());
//		}
//	}

	/**
     * Devuelve la longitud en bytes de un archivo dado.
     *
     * recibe el nombre del archivo a comprobar.
     * devuelve la longitud del archivo en bytes, o -1 si no existe.
     */

//	public long obtenerLongitudArchivo(String nombreArchivo) {
//		File archivo = new File(rutaArchivo + nombreArchivo);
//		if (archivo.exists()) {
//			return archivo.length(); // Devuelve la longitud en bytes
//		} else {
//			System.out.println("El archivo no existe.");
//			return -1;
//		}
//	}


}
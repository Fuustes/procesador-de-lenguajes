package practicaPDL;

public class TPR {
	// inicializaos ArrayString de palabras reservadas
	public static String[] palRes = new String[15];
	// contador de indice
	static int index=0;

	public void inicializar() {
		// inicializamos las palabras reservadas a tener en cuenta e introducimos en el array
		insertar("boolean");
		insertar("break");
		insertar("case");
		insertar("float");
		insertar("function");
		insertar("if");
		insertar("int");
		insertar("let");
		insertar("read");
		insertar("return");
		insertar("string");
		insertar("switch");
		insertar("void");
		insertar("write");
		insertar("default");
	}

	// funcion insertar manejando errores
	private static void insertar (String pR) {
		if (index<palRes.length && palRes[index]==null) {
			palRes[index]=pR;
			index++;
		}
	}
   // metodo toString de TPR
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<palRes.length; i++) {
			sb.append(palRes[i]);
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}

	// metodo esPalRes: devuele true si el lexema es una palabra reservada
	public static boolean esPalRes (String lex) {
		boolean encontrado = false;
		int i=0;
		while(i<palRes.length && !encontrado) {
			if (palRes[i].equals(lex)) {
				encontrado = true;
			}
			else {
				i++;
			}
		}
		return encontrado;
	}




}


package practicaPDL;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import practicaPDL.TS_Gestor.Tabla;

public class ASin {
	public LectorFichero lector;
//	private static String rutaArchivo = "C:\\Users\\mmart\\OneDrive - Universidad Polit�cnica de Madrid\\FI Sexto\\PDL\\";
//	public static String nombreArchivo="PIdG90.txt";
	private ALex aLex;
	public Token prox;
	private static ArrayList<String> parse;
	private ASem aSem;
	private int cont = 0;
	private int posFunc = 0;
	private int aux = 0;
	private String lex = "";
	public final Tokens tokens = new Tokens();
	private String[] listaArg;
	private int numArg = 0;

	public ASin(LectorFichero lector, ALex aLex) throws FileNotFoundException {
		this.aLex = aLex;
		this.lector = aLex.lector;
		ASin.parse = new ArrayList<>();
	}

	public void setASem(ASem aSem) {
		this.aSem = aSem;
	}

	public void proximo() throws IOException, Error {

		prox = aLex.AL();
//		System.out.println(prox);

	}
	public void equipara(String codigo) throws IOException, Error {
		if (!prox.getCode().equals(codigo)) {
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + ". El tipo del token recibido es distinto al esperado: Tipo token: " + prox.getCode() + " Tipo esperado: " + codigo);
		}
		tokens.add(prox);
		proximo();
	}

	public void ficheroParse () {
		String nomArchivo = "parse.txt";
		try {
			File archivo = new File(nomArchivo);
			if (!archivo.exists()) {
				archivo.createNewFile();

			}
			FileWriter escritorArchivo = new FileWriter(archivo, false); 
			BufferedWriter bufferEscritor = new BufferedWriter(escritorArchivo);
			bufferEscritor.write(parsetoString(parse)); 
			bufferEscritor.newLine();
			bufferEscritor.close();
		}
		catch (IOException e) {
			System.out.println("Error al escribir en el archivo: " + e.getMessage());
		}
	}

	public void AS() throws IOException, Error {
		aLex.setASem(aSem);
		parse.add("D");
		proximo();

		P();
		ficheroParse();
	}

	public void P() throws IOException, Error {

		if(prox.getCode().equals("if")||prox.getCode().equals("let")||prox.getCode().equals("switch")||prox.getCode().equals("write")||prox.getCode().equals("read")||prox.getCode().equals("return")||prox.getCode().equals("id")||prox.getCode().equals("break")) {
			parse.add("1");
			B();
			P();
		}
		else if(prox.getCode().equals("function")) { // esto deber a de ser function
			parse.add("2");
			F();
			P();
		}
		else if(prox.getCode().equals("eof")) {
			parse.add("3");
			tokens.add(prox);
			//			int res = aSem.gestor.write(Tabla.GLOBAL);
			//			if(res!=0){
			//				System.out.println("Error al intentar escribir la tabla global en el fichero");
			//				System.exit(1);
			//			}
			//			res=aSem.gestor.destroy(Tabla.GLOBAL);
			//			if(res!=0){
			//				System.out.println("Error al intentar destruir la tabla global");
			//				System.exit(1);
			//			}
			//			res=aSem.gestor.destroy(Tabla.PALRES);
			//			if(res!=0){
			//				System.out.println("Error al intentar destruir la tabla de palabras reservadas");
			//				System.exit(1);
			//			}
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}
	public void F() throws IOException, Error {
		if(prox.getCode().equals("function")) {
			parse.add("4");
			equipara("function");
			aSem.dentroFuncion();
			aSem.zonaDeclaracion();
			H();
			String iden = aLex.getAux();
			posFunc = (int)prox.getAtrib();
			equipara("id");
			if(!aSem.comprobarTS()) {
				throw new Error("Error Sem�ntico l�nea: " + lector.line + " . No se puede declarar una funci�n dentro de otra funci�n.");
			}
			int res = aSem.gestor.setTipo(aSem.gestor.getEntradaTSGlobal(iden), "funci�n");
						if(res!=0){
							System.out.println("Error al intentar dar valor al tipo de un identificador");
							System.exit(1);
						}
//			res = aSem.gestor.setValorAtributoEnt(posFunc, "posici�n", posFunc);
//			System.out.println(res);
//						if(res!=0){
//							System.out.println("Error al intentar dar valor a un atributo de un identificador");
//							System.exit(1);
//						}
			res=aSem.gestor.setValorAtributoCad(aSem.gestor.getEntradaTSGlobal(iden), "tipo de retorno", aSem.getTipo("H"));
						if(res!=0){
							System.out.println("Error al intentar dar valor a un atributo de un identificador");
							System.exit(1);
						}
			equipara("abPar");
			res=aSem.gestor.createTSLocal();
			if(res!=0){
				System.out.println("Error al intentar crear una tabla de s�mbolos local");
				System.exit(1);
			}
			aSem.pasarALocal();
			A();
			if(cont != 0) {
				aSem.initListaArgs(cont);
				aSem.addArg();
				aSem.addFuncYArgs(posFunc, aSem.getListaArgs());
			}
			aSem.volverAGlobal();
			if(cont != 0) {
				res = aSem.gestor.setValorAtributoLista(aSem.gestor.getEntradaTSGlobal(iden), "tipo de par�metros", aSem.getListaArgs());
				if(res!=0){
					System.out.println("Error al intentar dar valor a un atributo de un identificador");
					System.exit(1);
				}
			}
			res = aSem.gestor.setValorAtributoEnt(aSem.gestor.getEntradaTSGlobal(iden), "n�mero de par�metros", cont);
			if(res!=0){
				System.out.println("Error al intentar dar valor a un atributo de un identificador");
				System.exit(1);
			}
			aSem.pasarALocal();
			aSem.desDeclaracion();
			equipara("cePar");

			equipara("abLlave");
			C();
			aSem.volverAGlobal();
			cont = 0;
			aSem.vaciarTiposArgs();
			res=aSem.gestor.write(Tabla.LOCAL);
			if(res!=0){
				System.out.println("Error al intentar escribir la tabla local en el fichero");
				System.exit(1);
			}
			res=aSem.gestor.destroy(Tabla.LOCAL);
			if(res!=0){
				System.out.println("Error al intentar destruir la tabla local");
				System.exit(1);
			}
			aSem.reiniciarDesplLocal();
//			System.out.println(aSem.desplGlobal + " Y Local: " + aSem.desplLocal);
			equipara("ceLlave");	
			aSem.fueraFuncion();

		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}
	public void H() throws IOException, Error {
		if (prox.getCode().equals("int")||prox.getCode().equals("boolean")||prox.getCode().equals("float")||prox.getCode().equals("string")) {
			parse.add("5");
			T();
			aSem.putTipo("H", aSem.getTipo("T"));
		}
		else if(prox.getCode().equals("void")) {
			parse.add("6");
			equipara("void");
			aSem.putTipo("H", "void");
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}
	public void A() throws IOException, Error {
		if (prox.getCode().equals("int")||prox.getCode().equals("boolean")||prox.getCode().equals("float")||prox.getCode().equals("string")) {
			parse.add("7");
			T();
			aSem.addTipoArg(aSem.getTipo("T"), cont);
			cont++;
			aSem.gestor.setTipo((int)prox.getAtrib(), aSem.getTipo("T"));
			aSem.gestor.setValorAtributoEnt((int)prox.getAtrib(), "posici�n", aSem.calcularDesplLocal(aSem.getTipo("T")));
			aSem.gestor.setValorAtributoEnt((int)prox.getAtrib(), "par�metro", 1);
			equipara("id");
			K();
		}
		else if(prox.getCode().equals("void")) { //en documento es void
			parse.add("8");
			equipara("void");
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}

	public void K() throws IOException, Error {
		if(prox.getCode().equals("coma")) {
			parse.add("9");
			equipara("coma");
			T();
			aSem.addTipoArg(aSem.getTipo("T"), cont);
			cont++;
			aSem.gestor.setTipo((int)prox.getAtrib(), aSem.getTipo("T"));
			aSem.gestor.setValorAtributoEnt((int)prox.getAtrib(), "posici�n", aSem.calcularDesplLocal(aSem.getTipo("T")));
			aSem.gestor.setValorAtributoEnt((int)prox.getAtrib(), "par�metro", 1);
			equipara("id");
			K();
		}
		else if(prox.getCode().equals("cePar")) {
			parse.add("10");
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}

	public void C() throws IOException, Error {
		if(prox.getCode().equals("if")||prox.getCode().equals("let")||prox.getCode().equals("switch")||prox.getCode().equals("write")||prox.getCode().equals("read")||prox.getCode().equals("return")||prox.getCode().equals("id")||prox.getCode().equals("break")) {
			parse.add("11");
			B();
			C();
		}
		else if(prox.getCode().equals("ceLlave")||prox.getCode().equals("case")||prox.getCode().equals("default")) {
			parse.add("12");
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}

	public void B() throws IOException, Error {
		if(prox.getCode().equals("if")) {
			parse.add("13");
			equipara("if");
			equipara("abPar");
			E();
			if(aSem.comprobacionTipoProd("l�gico", aSem.getTipo("E"))) {
				equipara("cePar");
				S();
			}
			else {
				throw new Error("Error Sem�ntico l�nea: " + lector.line + " . If solo puede evaluar una expresi�n de tipo booleano.");

			}
		}	
		else if(prox.getCode().equals("let")) {
			parse.add("14");
			equipara("let");
			aSem.zonaDeclaracion();
			T();
//			System.out.println(aSem.getTipo("T"));
			aSem.gestor.setTipo((int)prox.getAtrib(), aSem.getTipo("T"));
			if((int)prox.getAtrib() < 0) {
				aSem.gestor.setValorAtributoEnt((int)prox.getAtrib(), "posici�n", aSem.calcularDesplLocal(aSem.getTipo("T")));
			}
			else {
				aSem.gestor.setValorAtributoEnt((int)prox.getAtrib(), "posici�n", aSem.calcularDesplGlobal(aSem.getTipo("T")));
			}
			aSem.gestor.setValorAtributoEnt((int)prox.getAtrib(), "par�metro", 0);
			equipara("id");
			aSem.desDeclaracion();
			equipara("puntoComa");
		}
		else if(prox.getCode().equals("write")||prox.getCode().equals("read")||prox.getCode().equals("return")||prox.getCode().equals("id")||prox.getCode().equals("break")) {
			parse.add("15");
			S();
		}
		else if(prox.getCode().equals("switch")) {
			parse.add("16");
			equipara("switch");
			equipara("abPar");
			E();	// no sabemos si deber�a ser solo id.
			if(aSem.comprobacionTipoProd("entero", aSem.getTipo("E"))) {
				equipara("cePar");
				equipara("abLlave");
				W();
				equipara("ceLlave");
			}
			else {
				throw new Error("Error Sem�ntico l�nea: " + lector.line + " . Switch solo puede evaluar expresiones de tipo entero.");
			}
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}

	public void W() throws IOException, Error {
		if(prox.getCode().equals("case")) {
			parse.add("17");
			equipara("case");
			aSem.dentroCase();
			aSem.comprobacionEtiqSwitch((Integer) prox.getAtrib());
			equipara("cInt");
			equipara("dosPuntos");
			C();
			aSem.fueraCase();
			W_();
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}

	public void W_() throws IOException, Error {
		if(prox.getCode().equals("case")) {
			parse.add("18");
			W();
		}
		else if(prox.getCode().equals("default")) {
			parse.add("19");
			equipara("default");
			aSem.dentroCase();
			equipara("dosPuntos");
			C();
			aSem.fueraCase();
		}
		else if(prox.getCode().equals("ceLlave")) {
			parse.add("20");
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}

	public void T() throws IOException, Error {
		if(prox.getCode().equals("int")) {
			parse.add("21");
			equipara("int");
			aSem.putTipo("T", "entero");
		}
		else if(prox.getCode().equals("boolean")) {
			parse.add("23");
			equipara("boolean");
			aSem.putTipo("T", "l�gico");
		}
		else if(prox.getCode().equals("float")) {
			parse.add("22");
			equipara("float");
			aSem.putTipo("T", "real");
		}
		else if(prox.getCode().equals("string")) {
			parse.add("24");
			equipara("string");
			aSem.putTipo("T", "cadena");
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}

	public void S() throws IOException, Error {
		if(prox.getCode().equals("write")) {
			parse.add("25");
			equipara("write");
			E();
			if((aSem.comprobacionTipoProd("cadena", aSem.getTipo("E")) || aSem.comprobacionTipoProd("entero", aSem.getTipo("E")))||
					aSem.comprobacionTipoProd("real", aSem.getTipo("E"))) {
				equipara("puntoComa");
			}
			else {
				throw new Error("Error Sem�ntico l�nea: " + lector.line + " . Las expresiones que siguen a write deben de ser del tipo cadena, entero o real. ");
			}
		}
		else if(prox.getCode().equals("read")) {
			parse.add("26");
			equipara("read");
			lex = aLex.getAux();
			int pos  = aSem.gestor.getEntradaTS(lex);
			String tipo = aSem.gestor.getTipo(pos);
			if((aSem.comprobacionTipoProd("cadena", tipo) || aSem.comprobacionTipoProd("entero", tipo))||
					aSem.comprobacionTipoProd("real", tipo)) {
				equipara("id");
				equipara("puntoComa");
			}
			else {
				throw new Error("Error Sem�ntico l�nea: " + lector.line + " . Las expresiones que siguen a read deben de ser del tipo cadena, entero o real. ");
			}
		}
		else if(prox.getCode().equals("return")) {
			parse.add("27");
			aSem.comprobacionReturn();
			equipara("return");
			X();
			equipara("puntoComa");
		}
		else if(prox.getCode().equals("id")) {
			parse.add("28");
			lex = aLex.getAux();
//			System.out.println("Soy lex " + lex);
			aux  = aSem.gestor.getEntradaTS(lex);
//			System.out.println("Soy aux de verdad " + aux);
			equipara("id");
			S_();
			//			else if(!(aSem.comprobacionTipoProd(aux, aSem.getTipo("S_")))){
			//				throw new Error("Error en l�nea: " + lector.line + " . Las  ");
			//			}
		}
		else if(prox.getCode().equals("break")) {
			parse.add("29");
			aSem.comprobacionBreak();
			equipara("break");
			equipara("puntoComa");
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}

	public void S_() throws IOException, Error {
		if(prox.getCode().equals("asig")) {
			parse.add("30");
			equipara("asig");
			E();
			aSem.putTipo("S_", aSem.getTipo("E"));
			if(aSem.enFuncion && (aux > 0)) {	
				if(aSem.gestor.getTipo(aux) == null) {
					aSem.fueraFuncion();
					aSem.gestor.setTipo(aux, aSem.getTipo("S_"));
					aSem.gestor.setValorAtributoEnt(aux, "posici�n", aSem.calcularDesplGlobal(aSem.getTipo("S_")));
					aSem.gestor.setValorAtributoEnt(aux, "par�metro", 0);
					aSem.dentroFuncion();	
				}
				else if(!(aSem.comprobacionTipoProd(aSem.gestor.getTipo(aux), aSem.getTipo("S_")))) {
					throw new Error("Error Sem�ntico l�nea: " + lector.line + " . Incompatibilidad de tipos en la sentencia de asignaci�n " + aSem.gestor.getTipo(aux)+ " " + aSem.getTipo("S_"));
				}
			}
			else {
				if(aSem.gestor.getTipo(aux) == null) {
					aSem.gestor.setTipo((int)prox.getAtrib(), aSem.getTipo("S_"));
					aSem.gestor.setValorAtributoEnt(aux, "posici�n", aSem.calcularDesplLocal(aSem.getTipo("S_")));
					aSem.gestor.setValorAtributoEnt(aux, "par�metro", 0);
				}
				else if(!(aSem.comprobacionTipoProd(aSem.gestor.getTipo(aux), aSem.getTipo("S_")))) {
					throw new Error("Error Sem�ntico l�nea: " + lector.line + " . Incompatibilidad de tipos en la sentencia de asignaci�n " + aSem.gestor.getTipo(aux)+ " " + aSem.getTipo("S_"));
				}
			}
			equipara("puntoComa");
		}
		else if(prox.getCode().equals("asigMult")) {
			parse.add("31");
			equipara("asigMult");
			E();
			if(aSem.comprobacionTipoProd("entero", aSem.getTipo("E")) && (aSem.comprobacionTipoProd(aSem.gestor.getTipo(aux), "entero"))) {
				equipara("puntoComa");
			}
			else {
				throw new Error("Error Sem�ntico l�nea: " + lector.line + " . La sentencia asigMult solo es compatible con enteros ");
			}
		}
		else if(prox.getCode().equals("abPar")) {
			parse.add("32");
			if(aSem.comprobacionTipoProd("funci�n", aSem.gestor.getTipo(aux))) {
				equipara("abPar");
//				System.out.println("soy auuuuux " + aux);
				L();
				equipara("cePar");
				equipara("puntoComa");
			}
			else {
				throw new Error("Error Sem�ntico l�nea: " + lector.line + " . Llamada a funcion con un nombre de funci�n inexistente");
			}
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}

	public void L() throws IOException, Error {
		if(prox.getCode().equals("id")||prox.getCode().equals("abPar")||prox.getCode().equals("cInt")||prox.getCode().equals("cReal")||prox.getCode().equals("cad")) {
			parse.add("33");
			E();
//			System.out.println("soy aux " + aux);
			listaArg = aSem.gestor.getValorAtributoLista(aux, "tipo de par�metros");
			if (listaArg != null) {
				if(listaArg[0].equals(aSem.getTipo("E"))) {
					numArg++;
					Q();
					numArg = 0;
				}

				else {
					throw new Error("Error Sem�ntico l�nea: " + lector.line + " . Tipo de argumentos incorrectos para la llamada a la funci�n");
				}
			}
			else {
				throw new Error("Error Sem�ntico l�nea: " + lector.line + " . La funci�n a la que se est� intentando llamar no tiene Argumentos");
			}
		}
		else if(prox.getCode().equals("cePar")) {
			parse.add("34");
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}

	public void Q() throws IOException, Error {
		if(prox.getCode().equals("coma")) {
			parse.add("35");
			equipara("coma");
			E();
			if(listaArg.length == numArg) {
				throw new Error("Error Sem�ntico l�nea: " + lector.line + " . Demasiados argumentos para la llamada a la funci�n");
			}
			else {
				if(listaArg[numArg].equals(aSem.getTipo("E"))) {
					numArg++;
					Q();
				}
				else {
					throw new Error("Error Sem�ntico l�nea: " + lector.line + " . Tipo de argumentos incorrectos para la llamada a la funci�n");
				}
			}	
		}
		else if(prox.getCode().equals("cePar")) {
			parse.add("36");
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}

	public void X() throws IOException, Error {
		if(prox.getCode().equals("id")||prox.getCode().equals("abPar")||prox.getCode().equals("cInt")||prox.getCode().equals("cReal")||prox.getCode().equals("cad")) {
			parse.add("37");
			E();
			aSem.putTipo("X", aSem.getTipo("E"));
			if(!(aSem.comprobacionTipoProd(aSem.gestor.getValorAtributoCad(posFunc, "tipo de retorno"), aSem.getTipo("X")))) {
				throw new Error("Error Sem�ntico l�nea: " + lector.line + " . Tipo del valor de retorno no coincide con el definidio en la declaraci�n de la funci�n");
			}
		}
		else if(prox.getCode().equals("puntoComa")) {
			parse.add("38");
			if(!(aSem.comprobacionTipoProd("void", aSem.gestor.getValorAtributoCad(posFunc, "tipo de retorno")))) {
				throw new Error("Error Sem�ntico l�nea: " + lector.line + " . La sentencia return solo puede ir vac�a si el tipo de retorno de la funci�n es void");
			}
			//este caso es si el tipo de retorno de la funci�n es void
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}

	public void E() throws IOException, Error {
		if(prox.getCode().equals("id")||prox.getCode().equals("abPar")||prox.getCode().equals("cInt")||prox.getCode().equals("cReal")||prox.getCode().equals("cad")) {
			parse.add("39");
			R();
			E_();
			if(aSem.comprobacionTipoProd("tipo_ok", aSem.getTipo("E_"))) {
				aSem.putTipo("E", aSem.getTipo("R"));
			}
			else {
				aSem.putTipo("E", "l�gico");
			}
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}

	public void E_() throws IOException, Error {
		if(prox.getCode().equals("and")) {
			parse.add("40");
			equipara("and");
			R();
			E_();
			if(!aSem.comprobacionTipoProd("l�gico", aSem.getTipo("R"))) {
				throw new Error("Error Sem�ntico l�nea: " + lector.line + ". La expresion && solo puede evaluar booleanos");			
			}
			aSem.putTipo("E_", "l�gico");
		}
		else if(prox.getCode().equals("cePar")||prox.getCode().equals("puntoComa")||prox.getCode().equals("coma")) {
			parse.add("41");
			aSem.putTipo("E_", "tipo_ok");
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}

	public void R() throws IOException, Error {
		if(prox.getCode().equals("id")||prox.getCode().equals("abPar")||prox.getCode().equals("cInt")||prox.getCode().equals("cReal")||prox.getCode().equals("cad")) {
			parse.add("42");
			U();
			R_();
			if(aSem.comprobacionTipoProd("tipo_ok", aSem.getTipo("R_"))){
				aSem.putTipo("R", aSem.getTipo("U"));
			}
			else {
				aSem.putTipo("R", "l�gico");
			}
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}

	public void R_() throws IOException, Error {
		if(prox.getCode().equals("mayor")) {
			parse.add("43");
			equipara("mayor");
			U();
			R_();
			if((aSem.comprobacionTipoProd("entero", aSem.getTipo("U")) || aSem.comprobacionTipoProd("real", aSem.getTipo("U")))) {
				aSem.putTipo("R_", "l�gico");
			}
			else {
				throw new Error("Error Sem�ntico l�nea: " + lector.line + ". Solo se pueden utilizar operadores de relaci�n entre datos num�ricos del mismo tipo");
			}
		}
		else if(prox.getCode().equals("and")||prox.getCode().equals("cePar")||prox.getCode().equals("puntoComa")||prox.getCode().equals("coma")) {
			parse.add("44");
			aSem.putTipo("R_", "tipo_ok");

		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}
	public void U() throws IOException, Error {
		if(prox.getCode().equals("id")||prox.getCode().equals("abPar")||prox.getCode().equals("cInt")||prox.getCode().equals("cReal")||prox.getCode().equals("cad")) {
			parse.add("45");
			V();
			U_();
			if(aSem.comprobacionTipoProd("tipo_ok", aSem.getTipo("U_"))){
				aSem.putTipo("U", aSem.getTipo("V"));
			}
			else {
				aSem.putTipo("U", aSem.getTipo("U_"));
			}
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + ". Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}
	public void U_() throws IOException, Error {
		if(prox.getCode().equals("suma")) {
			parse.add("46");
			equipara("suma");
			V();
			String tipoV = aSem.getTipo("V");
			U_();
			String tipoUPrima = aSem.getTipo("U_");
			if (tipoUPrima.equals("tipo_ok")) {
				if (tipoV.equals("entero") || tipoV.equals("real")) {
					aSem.putTipo("U_", tipoV);
				} else {
					throw new Error("Error Sem�ntico l�nea: " + lector.line + ". Solo se pueden sumar tipos numericos");
				}
			}
			// Caso recursivo: tipos deben coincidir
			else if (tipoV.equals(tipoUPrima) &&
					(tipoV.equals("entero") || tipoV.equals("real"))) {
				aSem.putTipo("U_", tipoV);
			}
			else {
				throw new Error("Error Sem�ntico l�nea: " + lector.line + ". No se permiten sumas entre tipos num�ricos distintos");
			}
		}
		else if(prox.getCode().equals("mayor")||prox.getCode().equals("and")||prox.getCode().equals("cePar")||prox.getCode().equals("puntoComa")||prox.getCode().equals("coma")) {
			parse.add("47");
			aSem.putTipo("U_", "tipo_ok");

		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}

	public void V() throws IOException, Error {
		if(prox.getCode().equals("id")) {
			parse.add("48");
			lex = aLex.getAux();
			int temp = aux;
			aux  = aSem.gestor.getEntradaTS(lex);
			aSem.putTipo("V", aSem.gestor.getTipo(aux));
			equipara("id");
			V_();
			aux = temp;
			//El tipo que se queda es el de la id
		}
		else if(prox.getCode().equals("abPar")) {
			parse.add("49");
			equipara("abPar");
			E();
			equipara("cePar");
			aSem.putTipo("V", aSem.getTipo("E"));
		}
		else if(prox.getCode().equals("cInt")) {
			parse.add("50");
			equipara("cInt");
			aSem.putTipo("V", "entero");

		}

		else if(prox.getCode().equals("cad")) {
			parse.add("51");
			equipara("cad");
			aSem.putTipo("V", "cadena");
		}

		else if(prox.getCode().equals("cReal")) {
			parse.add("52");
			equipara("cReal");
			aSem.putTipo("V", "real");
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}

	public void V_() throws IOException, Error{
		if(prox.getCode().equals("abPar")) {
			parse.add("54");
			if(aSem.comprobacionTipoProd("funci�n", aSem.gestor.getTipo(aux))) {
				equipara("abPar");
				L();
				equipara("cePar");
				aSem.putTipo("V", aSem.gestor.getValorAtributoCad(aux, "tipo de retorno"));
			}
			else {
				throw new Error("Error Sem�ntico l�nea: " + lector.line + " . Llamada a funcion con un nombre de funci�n inexistente");
			}
		}
		else if(prox.getCode().equals("suma")||prox.getCode().equals("mayor")||prox.getCode().equals("and")||prox.getCode().equals("cePar")||prox.getCode().equals("puntoComa")||prox.getCode().equals("coma")) {
			parse.add("53");
		}
		else {
			ficheroParse();
			throw new Error("Error Sint�ctico en l�nea: " + lector.line + " . Token recibido inesperado para la producci�n actual " + prox.getCode());
		}
	}

	public static <T> String parsetoString(ArrayList<T> lista) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lista.size(); i++) {
			if (i > 0) sb.append(" ");
			sb.append(lista.get(i));
		}
		return sb.toString();
	}

	public void ficheroTokens () {
		String nomArchivo = "tokens.txt";
		try {
			File archivo = new File(nomArchivo);
			if (!archivo.exists()) {
				archivo.createNewFile();

			}
			FileWriter escritorArchivo = new FileWriter(archivo, false);
			BufferedWriter bufferEscritor = new BufferedWriter(escritorArchivo);

			bufferEscritor.write(tokens.toString());
			bufferEscritor.newLine();

			bufferEscritor.close();
		}
		catch (IOException e) {
			System.out.println("Error al escribir en el archivo: " + e.getMessage());
		}
	}



}
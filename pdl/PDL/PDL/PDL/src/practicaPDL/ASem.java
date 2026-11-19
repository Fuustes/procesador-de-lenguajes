package practicaPDL;

//import java.io.BufferedWriter;

//import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import practicaPDL.TS_Gestor.DescripcionAtributo;
import practicaPDL.TS_Gestor.Tabla;
import practicaPDL.TS_Gestor.TipoDatoAtributo;

public class ASem {
	
	boolean enFuncion = false;
	boolean enCase = false;
	public LectorFichero lector;
	TS_Gestor gestor;
//	private ALex aLex;
	private HashMap<Integer, Integer> cases = new HashMap<>();
	private HashMap<String, String> tipo = new HashMap<>();
	private HashMap<String, Integer> posID = new HashMap<>();
	private HashMap<Integer, String[]> funcArg = new HashMap<>();
	int param = 0;
	private ASin aSin;
	private boolean zonaDecl = false;
	private boolean tsGlobal = true;
	private String[] listaArgs;
	private ArrayList<String> tiposArgs = new ArrayList<>();
	public int desplLocal = 0;
	public int desplGlobal = 0;
	
	public ASem(LectorFichero lector, ALex aLex, ASin aSin) throws FileNotFoundException {
		//this.aLex = aLex;
		this.lector = aLex.lector;
		this.gestor = new TS_Gestor("TS.txt");
//		this.aLex = aLex;
		this.aSin = aSin;
	}
	
	public void ASm() throws IOException, Error{
		int res = gestor.createTPalabrasReservadas();
		if(res!=0){
			System.out.println("Error en la creaci�n de la tabla de palabras reservadas");
			System.exit(1);
		}
		res=gestor.addEntradaTPalabrasReservadas("boolean");
		if(res==0){
			System.out.println("Error: a�adir palabra reservada a la tabla de palabras reservadas");
			System.exit(1);
		}
		res=gestor.addEntradaTPalabrasReservadas("break");
		if(res==0){
			System.out.println("Error: a�adir palabra reservada a la tabla de palabras reservadas");
			System.exit(1);
		}
		res=gestor.addEntradaTPalabrasReservadas("case");
		if(res==0){
			System.out.println("Error: a�adir palabra reservada a la tabla de palabras reservadas");
			System.exit(1);
		}
		res=gestor.addEntradaTPalabrasReservadas("float");
		if(res==0){
			System.out.println("Error: a�adir palabra reservada a la tabla de palabras reservadas");
			System.exit(1);
		}
		res=gestor.addEntradaTPalabrasReservadas("function");
		if(res==0){
			System.out.println("Error: a�adir palabra reservada a la tabla de palabras reservadas");
			System.exit(1);
		}
		res=gestor.addEntradaTPalabrasReservadas("if");
		if(res==0){
			System.out.println("Error: a�adir palabra reservada a la tabla de palabras reservadas");
			System.exit(1);
		}
		res=gestor.addEntradaTPalabrasReservadas("int");
		if(res==0){
			System.out.println("Error: a�adir palabra reservada a la tabla de palabras reservadas");
			System.exit(1);
		}
		res=gestor.addEntradaTPalabrasReservadas("let");
		if(res==0){
			System.out.println("Error: a�adir palabra reservada a la tabla de palabras reservadas");
			System.exit(1);
		}
		res=gestor.addEntradaTPalabrasReservadas("read");
		if(res==0){
			System.out.println("Error: a�adir palabra reservada a la tabla de palabras reservadas");
			System.exit(1);
		}
		res=gestor.addEntradaTPalabrasReservadas("return");
		if(res==0){
			System.out.println("Error: a�adir palabra reservada a la tabla de palabras reservadas");
			System.exit(1);
		}
		res=gestor.addEntradaTPalabrasReservadas("string");
		if(res==0){
			System.out.println("Error: a�adir palabra reservada a la tabla de palabras reservadas");
			System.exit(1);
		}
		res=gestor.addEntradaTPalabrasReservadas("switch");
		if(res==0){
			System.out.println("Error: a�adir palabra reservada a la tabla de palabras reservadas");
			System.exit(1);
		}
		res=gestor.addEntradaTPalabrasReservadas("void");
		if(res==0){
			System.out.println("Error: a�adir palabra reservada a la tabla de palabras reservadas");
			System.exit(1);
		}
		res=gestor.addEntradaTPalabrasReservadas("write");
		if(res==0){
			System.out.println("Error: a�adir palabra reservada a la tabla de palabras reservadas");
			System.exit(1);
		}
		res=gestor.addEntradaTPalabrasReservadas("default");
		if(res==0){
			System.out.println("Error: a�adir palabra reservada a la tabla de palabras reservadas");
			System.exit(1);
		}
		
		res=gestor.createAtributo("posici�n", DescripcionAtributo.DIR, TipoDatoAtributo.ENTERO);
		if(res!=0){
			System.out.println("Error al definir un atributo.");
			System.exit(1);
		}
		res=gestor.createAtributo("n�mero de par�metros", DescripcionAtributo.NUM_PARAM,
				TipoDatoAtributo.ENTERO);
		if(res!=0){
			System.out.println("Error al definir un atributo.");
			System.exit(1);
		}
		res=gestor.createAtributo("tipo de par�metros", DescripcionAtributo.TIPO_PARAM,
				TipoDatoAtributo.LISTA);
		if(res!=0){
			System.out.println("Error al definir un atributo.");
			System.exit(1);
		}
		res=gestor.createAtributo("tipo de retorno", DescripcionAtributo.TIPO_RET,
				TipoDatoAtributo.CADENA);
		if(res!=0){
			System.out.println("Error al definir un atributo.");
			System.exit(1);
		}
//		res=gestor.createAtributo("etiqueta", DescripcionAtributo.ETIQUETA, TipoDatoAtributo.CADENA);
//		if(res!=0){
//		System.out.println("Error al definir un atributo.");
//		System.exit(1);
//		}
		res=gestor.createAtributo("par�metro", DescripcionAtributo.PARAM, TipoDatoAtributo.ENTERO);
		if(res!=0){
		System.out.println("Error al definir un atributo.");
		System.exit(1);
		}
		
		res=gestor.createTSGlobal();
			if(res!=0){
				System.out.println("Error al intentar crear la tabla de s�mbolos global");
			}
		
			aSin.setASem(this);
			aSin.AS();
//			res = gestor.write(Tabla.GLOBAL);
//			if(res!=0){
//				System.out.println("Error al intentar escribir la tabla global en el fichero");
//				System.exit(1);
//			}
//			res = gestor.destroy(Tabla.GLOBAL);
//			if(res!=0){
//				System.out.println("Error al intentar destruir la tabla global");
//				System.exit(1);
//			}
//			res = gestor.destroy(Tabla.PALRES);
//			if(res!=0){
//				System.out.println("Error al intentar destruir la tabla de palabras reservadas");
//				System.exit(1);
//			}
//			aSin.ficheroTokens();
//			aSin.ficheroParse();
	}
	
	
	public void imprimirTS() {
		int res = gestor.write(Tabla.GLOBAL);
		if(res!=0){
			System.out.println("Error al intentar escribir la tabla global en el fichero");
			System.exit(1);
		}
		res = gestor.destroy(Tabla.GLOBAL);
		if(res!=0){
			System.out.println("Error al intentar destruir la tabla global");
			System.exit(1);
		}
		res = gestor.destroy(Tabla.PALRES);
		if(res!=0){
			System.out.println("Error al intentar destruir la tabla de palabras reservadas");
			System.exit(1);
		}
	}
	
	public void dentroFuncion() {
		enFuncion = true;
	}
	
	public void dentroCase() {
		enCase = true;
	}
	public void fueraFuncion() {
		enFuncion = false;
	}
	public void fueraCase() {
		enCase = false;
	}
	
	public void comprobacionReturn() throws Error {
		if(!enFuncion) {
			throw new Error("Error Sem�ntico en l�nea: " + lector.line + ". return solo puede ir dentro de una funcion");
		}
	}
	
	public void comprobacionBreak() throws Error {
		if(!enCase) {
			throw new Error("Error Sem�ntico en l�nea: " + lector.line + ". break solo puede ir dentro de un case");
		}
	}
	
	public void addFuncYArgs(int pos, String[] args) {
		funcArg.put(pos, args);
	}
	
	public String[] getArgs(int pos) {
		return funcArg.get(pos);
	}
	
//	public void comprobacionTipoTS(int pos, String tipo) throws Error {
//		if(!(tipo.equals(gestor.getTipo(pos)))) {
//			throw new Error("Error Sem�ntico en l�nea: " + lector.line + ". Los tipos empleados en la expersi�n no coinciden: Tipo necesario para la expresi�n: " + tipo);
//		}
//	}
	
	public boolean comprobacionTipoProd(String type1, String type2) throws Error {
		if(!(type1.equals(type2))) {
//			throw new Error("Error en l�nea: " + lector.line + ". Los tipos empleados en la expersi�n no coinciden: Tipo necesario para la expresi�n: " + type1);
			//El tipo correcto es el 1
			return false;
		}
		else {
			return true;
		}
	}
	
	public void zonaDeclaracion() {
		zonaDecl = true;
	}
	
	public void desDeclaracion() {
		zonaDecl = false;
	}
	
	
	public boolean getDeclaracion() {
		return zonaDecl;
	}
	
	public boolean getDeclaracionFuncion() {
		return zonaDecl;
	}
	
	public void comprobacionEtiqSwitch(Integer caso) throws Error {
		if(cases.containsKey(caso)) {
			throw new Error("Error Sem�ntico en l�nea: " + lector.line + ". Los case no pueden compartir la misma etiqueta. " + "Etiqueta: " + caso);
		}
		else {
			cases.put(caso, null);
		}
	}
	
	public void putTipo(String Prod, String type){
		tipo.put(Prod, type);
	}
	
	public String getTipo(String Prod) {
		return tipo.get(Prod);
	}
	
	
	public void initListaArgs(int cont) {
	    listaArgs = new String[cont];
	}

	public void addArg() {
	    for(int i = 0; i < tiposArgs.size(); i++) {
	    	listaArgs[i] = tiposArgs.get(i);
	    }
	}
	
	public void addTipoArg(String tipo, int cont) {
	    tiposArgs.add(cont, tipo);
	}


	public String[] getListaArgs() {
	    return listaArgs;
	}
	
	public void vaciarTiposArgs() {
		tiposArgs.clear();
	}
	
	public void putID(String lex, int pos) {
		posID.putIfAbsent(lex, pos);
	}
	
	public String getTipoID(String lex) {
		int pos = posID.get(lex);
		return gestor.getTipo(pos);
	}
	
	public void pasarALocal() {
		tsGlobal = false;
	}
	
	public void volverAGlobal() {
		tsGlobal = true;
	}
	
	public boolean comprobarTS() {
		return tsGlobal;
	}
	
	public boolean esPalRes(String lex){
		if(gestor.getEntradaTPalabrasReservadas(lex) != 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void reiniciarDesplLocal() {
		desplLocal = 0;
	}
	

	public int calcularDesplLocal(String tipo) {
		int aux1 = desplLocal;
		switch(tipo) {
		case "entero":
			desplLocal += 4;
			return aux1;
		case "cadena":
			desplLocal += 128;
			return aux1;
		case "real":
			desplLocal += 8;
			return aux1;
		case "l�gico":
			desplLocal += 4;
			return aux1;
		default:
			return aux1;
		}
	}

	public int calcularDesplGlobal(String tipo) {
		int aux2 = desplGlobal;
		switch(tipo) {
		case "entero":
			desplGlobal += 4;
			return aux2;
		case "cadena":
			desplGlobal += 128;
			return aux2;
		case "real":
			desplGlobal += 8;
			return aux2;
		case "l�gico":
			desplGlobal += 4;
			return aux2;
		default:
			return aux2;
		}
	}
	
}
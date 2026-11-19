package practicaPDL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import practicaPDL.TS_Gestor.Tabla;

public class Main {
	public static void main(String[] args) throws IOException, Error {
		//inicializacion de matriz

		AFD.inicializarMatriz();

		// aqui colocamos la ruta del archivo a leer PIdG90
		String ruta = "C:\\Users\\mario\\OneDrive\\Escritorio\\pdl bien\\pdl\\pdl\\Casos de Prueba\\Casos de Prueba\\Casos Correctos\\Prueba1.txt";
		File archivo = new File(ruta);
		LectorFichero lector = null;
		ALex aLex = null;
		ASin aSin = null;
		ASem aSem = null;

		// manejamos errores

		try {
			//Crear lector
			lector = new LectorFichero(archivo);

			//Inicializar ALex con ese lector
			aLex = new ALex(lector);
			aSin = new ASin(lector, aLex);
			aSem = new ASem(lector, aLex, aSin);

//			System.out.println("ALex ha sido inicializado con el archivo.");


			// Verificado analisis de archivo
			//			while (aLex.eof==false) {
			//				aLex.AL();
			//			}


			//	System.out.println(aLex.AL());
			System.out.println("Analizando...\n");
			aSem.ASm();
			aSem.imprimirTS();
			aSin.ficheroTokens();
			System.out.println("Analizado Correctamente");
			//aLex.ficheroTokens();
			//TS.ficheroTS(100);
			

			// Guardar tokens y tabla de símbolos


		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado: " + e.getMessage());
		}
		catch(Error e) {
			System.err.println(e.getMessage());


			if (aSin != null) {
				aSin.ficheroParse();
			}


			if (aSin != null) {
				aSin.ficheroTokens();
			}

			if (aSem != null) {
				if(aSem.enFuncion) {
					aSem.gestor.write(Tabla.LOCAL);
					aSem.gestor.destroy(Tabla.LOCAL);
				}
				aSem.imprimirTS();
			}

			System.err.println("Ejecución finalizada por error.");
			System.exit(1);
		}
	}
}






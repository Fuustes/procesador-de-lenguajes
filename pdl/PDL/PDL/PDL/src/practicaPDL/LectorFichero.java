package practicaPDL;

import java.io.*;

public class LectorFichero {
	public File f;
	public FileReader fr;
	public BufferedReader br;
	public int line = 1;
	public boolean eof = false;
	public char c;

	public LectorFichero(File f) {
		this.f = f;
	}
	// inicializamos el Lector
	public void inicializarLector() throws FileNotFoundException {
		this.fr = new FileReader(f);
		this.br = new BufferedReader(fr);
	}

	// Solo comprueba si llegamos al EOF, sin consumir caracter
	public boolean esEOF() {
		return eof;
	}

	// Lee realmente un caracter del archivo
	public char leerCaracter() throws IOException {
		int i = br.read();
		if (i == -1) {
			eof = true;
			return (char) -1;
		} else {
			c = (char) i;
			return c;
		}
	}
    // metodo para cerrar el buffer y el lector de archivo
	public void cerrar() throws IOException {
		if (br != null) br.close();
		if (fr != null) fr.close();
		eof = true;
	}
}


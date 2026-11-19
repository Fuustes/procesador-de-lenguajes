package practicaPDL;

import java.util.ArrayList;
import java.util.List;

public class Tokens {


	private List<Token> tokens;
	// private int size;

	// Constructor que inicializa la lista de tokens
	public Tokens() {
		this.tokens = new ArrayList<>();


	}

	// Método para agregar un token a la lista
	public void add(Token token) {
//		if (!estaToken(token)) { // Verifica si el token ya existe antes de agregarlo
			tokens.add(token);
//		}
	}

//	private boolean estaToken(Token token) {
//		for (Token t : tokens) {
//			// Compara según el tipo de token (atributos)
//			if (t.getCode().equals(token.getCode()) && t.getAtrib().equals(token.getAtrib())) {
//				return true; // Token ya existe
//			}
//		}
//		return false; // Token no encontrado
//	}


	// metodo toString de Tokens

	public String toString() {

		StringBuilder lista = new StringBuilder();
		for (Token token : tokens) {
			lista.append(token.toString()).append("\n");
		}
		return lista.toString();
	}


	public List<Token> getTokens() {
		return tokens;
	}






}

package practicaPDL;



public class Token {
	private String codigo;
	private Object atributo;

	// constructor
	public Token(String codigo, Object atributo) {
		this.codigo = codigo;
		this.atributo = atributo;
	}
	//getters de codigo y atributo

	public String getCode() {
		return codigo;
	}

	public Object getAtrib() {
		return atributo;
	}

	// metodo to string Token

	public String toString() {
		// Si el atributo es null o cadena vacia, dejamos el espacio despues de la coma
		String atrib = (atributo == null) ? "" : atributo.toString();
		return "<" + codigo + "," + atrib + ">";
	}

}


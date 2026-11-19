package practicaPDL;

// clase error
public class Error extends Exception {
	public Error(String s) { // Recibe un parametro String s que representa un mensaje descriptivo del error
        super(s); // Llama al constructor de la clase padre almacenando el mensaje en el objeto de excepcion.
    }
}
package practicaPDL;

// clase Estado-Accion
public class EstadoAccion {
	
	private Integer estado; 
    private String accion;  
// constructor
    public EstadoAccion(Integer estado, String accion) {
        this.estado = estado;
        this.accion = accion;
    }

   
// getters y setters
	public Integer getEstado() {
        return estado;
    }

    public String getAccion() {
        return accion;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

}


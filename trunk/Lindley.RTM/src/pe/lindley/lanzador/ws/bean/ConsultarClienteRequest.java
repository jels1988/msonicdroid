package pe.lindley.lanzador.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ConsultarClienteRequest {

	@SerializedName("Ruc")
	private String ruc;
	
	@SerializedName("Dni")
	private String dni;
	
	@SerializedName("Codigo")
	private String codigo;

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}

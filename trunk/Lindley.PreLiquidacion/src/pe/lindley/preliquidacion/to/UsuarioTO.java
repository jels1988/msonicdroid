package pe.lindley.preliquidacion.to;

import com.google.gson.annotations.SerializedName;

public class UsuarioTO {

	@SerializedName("USU")
	private String usuario;
	
	@SerializedName("OCA")
	private String ordenCarga;
	
	@SerializedName("NOM")
	private String descripcion;
	
	@SerializedName("EST")
	private String estado;

	private String deposito;
	
	public String getDeposito() {
		return deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getOrdenCarga() {
		return ordenCarga;
	}

	public void setOrdenCarga(String ordenCarga) {
		this.ordenCarga = ordenCarga;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	

}

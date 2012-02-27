package pe.lindley.ficha.to;

import com.google.gson.annotations.SerializedName;

public class ClienteTO {
	
	@SerializedName("CLI")
	private String codigo;

	@SerializedName("RZS")
	private String razonSocial;

	@SerializedName("DIR")
	private String direccion;


	@SerializedName("DIS")
	private String distrito;

	@SerializedName("RZC")
	private String razonComercial;

	@SerializedName("RUC")
	private String ruc;

	@SerializedName("REF")
	private String referencia;

	@SerializedName("TLF")
	private String telefono;

	@SerializedName("FEC")
	private String fechAniv;

	@SerializedName("OBS")
	private String observaciones;

	@SerializedName("URG")
	private String usuario;
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getRazonComercial() {
		return razonComercial;
	}

	public void setRazonComercial(String razonComercial) {
		this.razonComercial = razonComercial;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFechAniv() {
		return fechAniv;
	}

	public void setFechAniv(String fechAniv) {
		this.fechAniv = fechAniv;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}



	
}

package pe.lindley.ficha.to;

import com.google.gson.annotations.SerializedName;

public class ContactoTO {
	
	@SerializedName("CLI")
	private String codigo;

	@SerializedName("COD")
	private String codigoContacto;
		        
	@SerializedName("CTO")
	private String nombrecontacto;

	@SerializedName("FNC")
	private String fechaNacimiento;

	@SerializedName("TEL")
	private String telefono;

	@SerializedName("TCO")
	private String tipoContacto;

	@SerializedName("DCO")
	private String descripcionContacto;

	@SerializedName("MAIL")
	private String email;

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

	public String getCodigoContacto() {
		return codigoContacto;
	}

	public void setCodigoContacto(String codigoContacto) {
		this.codigoContacto = codigoContacto;
	}

	public String getNombrecontacto() {
		return nombrecontacto;
	}

	public void setNombrecontacto(String nombrecontacto) {
		this.nombrecontacto = nombrecontacto;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(String tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	public String getDescripcionContacto() {
		return descripcionContacto;
	}

	public void setDescripcionContacto(String descripcionContacto) {
		this.descripcionContacto = descripcionContacto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}


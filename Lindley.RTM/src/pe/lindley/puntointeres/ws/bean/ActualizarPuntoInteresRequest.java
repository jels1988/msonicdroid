package pe.lindley.puntointeres.ws.bean;

import java.util.List;

import pe.lindley.puntointeres.to.SubGiroTO;

import com.google.gson.annotations.SerializedName;

public class ActualizarPuntoInteresRequest {

	@SerializedName("COD")
	private String codCliente;

	@SerializedName("PIT")
	private String codPunto;

	@SerializedName("GIR")
	private String codGiro;

	@SerializedName("SGRS")
	private List<SubGiroTO> listSubGiro;

	@SerializedName("NOM")
	private String nombre;

	@SerializedName("DRC")
	private String direccion;

	@SerializedName("UBG")
	private String codUbigeo;

	@SerializedName("DES")
	private String descripcion;

	@SerializedName("LAT")
	private String latitud;

	@SerializedName("LNG")
	private String longitud;

	@SerializedName("LTD")
	private double latitudDec;

	@SerializedName("LGD")
	private double longitudDec;
	        
	@SerializedName("URG")
	private String Usuario;

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getCodPunto() {
		return codPunto;
	}

	public void setCodPunto(String codPunto) {
		this.codPunto = codPunto;
	}

	public String getCodGiro() {
		return codGiro;
	}

	public void setCodGiro(String codGiro) {
		this.codGiro = codGiro;
	}

	public List<SubGiroTO> getListSubGiro() {
		return listSubGiro;
	}

	public void setListSubGiro(List<SubGiroTO> listSubGiro) {
		this.listSubGiro = listSubGiro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodUbigeo() {
		return codUbigeo;
	}

	public void setCodUbigeo(String codUbigeo) {
		this.codUbigeo = codUbigeo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public double getLatitudDec() {
		return latitudDec;
	}

	public void setLatitudDec(double latitudDec) {
		this.latitudDec = latitudDec;
	}

	public double getLongitudDec() {
		return longitudDec;
	}

	public void setLongitudDec(double longitudDec) {
		this.longitudDec = longitudDec;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	
}

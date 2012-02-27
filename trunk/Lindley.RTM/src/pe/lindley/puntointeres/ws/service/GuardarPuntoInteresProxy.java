package pe.lindley.puntointeres.ws.service;

import pe.lindley.puntointeres.ws.bean.GuardarPuntoInteresResponse;
import pe.lindley.puntointeres.ws.bean.GuardarPuntoInteresRequest;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class GuardarPuntoInteresProxy extends ProxyBase<GuardarPuntoInteresResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsPINT)protected String urlWS;
	
	private String codCliente;
	private String codPunto;
	private String codGiro;
	private String tipoGiro;
	private String nombre;
	private String direccion;
	private String codUbigeo;
	private String descripcion;
	private String latitud;
	private String longitud;
	private double latitudDec;
	private double longitudDec;	        
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

	public String getTipoGiro() {
		return tipoGiro;
	}

	public void setTipoGiro(String tipoGiro) {
		this.tipoGiro = tipoGiro;
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

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/GuardarPtosInteres";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		GuardarPuntoInteresRequest guardarPuntoInteresRequest = new GuardarPuntoInteresRequest();
		guardarPuntoInteresRequest.setCodCliente(codCliente);
		guardarPuntoInteresRequest.setCodGiro(codGiro);
		guardarPuntoInteresRequest.setCodPunto(codPunto);
		guardarPuntoInteresRequest.setCodUbigeo(codUbigeo);
		guardarPuntoInteresRequest.setDescripcion(descripcion);
		guardarPuntoInteresRequest.setDireccion(direccion);
		guardarPuntoInteresRequest.setLatitud(latitud);
		guardarPuntoInteresRequest.setLongitud(longitud);
		guardarPuntoInteresRequest.setLatitudDec(latitudDec);
		guardarPuntoInteresRequest.setLongitudDec(longitudDec);
		guardarPuntoInteresRequest.setNombre(nombre);
		guardarPuntoInteresRequest.setTipoGiro(tipoGiro);
		guardarPuntoInteresRequest.setUsuario(Usuario);
		String request = JSONHelper.serializar(guardarPuntoInteresRequest);
		return request;
	}

	@Override
	protected GuardarPuntoInteresResponse responseText(String json) {
		// TODO Auto-generated method stub
		GuardarPuntoInteresResponse guardarPuntoInteresResponse = JSONHelper.desSerializar(json, GuardarPuntoInteresResponse.class);
		return guardarPuntoInteresResponse;
	}
}
package pe.lindley.puntointeres.ws.service;

import java.util.List;

import pe.lindley.puntointeres.to.SubGiroTO;
import pe.lindley.puntointeres.ws.bean.ActualizarPuntoInteresRequest;
import pe.lindley.puntointeres.ws.bean.ActualizarPuntoInteresResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ActualizarPuntoInteresProxy extends ProxyBase<ActualizarPuntoInteresResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsPINT)protected String urlWS;
	
	private String codCliente;
	private String codPunto;
	private String codGiro;
	private List<SubGiroTO> listSubGiro;
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

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ActualizarPtosInteres";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ActualizarPuntoInteresRequest actualizarPuntoInteresRequest = new ActualizarPuntoInteresRequest();
		actualizarPuntoInteresRequest.setCodCliente(codCliente);
		actualizarPuntoInteresRequest.setCodGiro(codGiro);
		actualizarPuntoInteresRequest.setCodPunto(codPunto);
		actualizarPuntoInteresRequest.setCodUbigeo(codUbigeo);
		actualizarPuntoInteresRequest.setDescripcion(descripcion);
		actualizarPuntoInteresRequest.setDireccion(direccion);
		actualizarPuntoInteresRequest.setLatitud(latitud);
		actualizarPuntoInteresRequest.setLongitud(longitud);
		actualizarPuntoInteresRequest.setLatitudDec(latitudDec);
		actualizarPuntoInteresRequest.setLongitudDec(longitudDec);
		actualizarPuntoInteresRequest.setNombre(nombre);
		actualizarPuntoInteresRequest.setListSubGiro(listSubGiro);
		actualizarPuntoInteresRequest.setUsuario(Usuario);
		String request = JSONHelper.serializar(actualizarPuntoInteresRequest);
		return request;
	}

	@Override
	protected ActualizarPuntoInteresResponse responseText(String json) {
		// TODO Auto-generated method stub
		ActualizarPuntoInteresResponse actualizarPuntoInteresResponse = JSONHelper.desSerializar(json, ActualizarPuntoInteresResponse.class);
		return actualizarPuntoInteresResponse;
	}
}

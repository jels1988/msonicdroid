package pe.lindley.ficha.ws.service;

import pe.lindley.ficha.ws.bean.GuardarContactoRequest;
import pe.lindley.ficha.ws.bean.GuardarContactoResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class GuardarContactoProxy extends ProxyBase<GuardarContactoResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsFichaContacto)protected String urlWS;
	
	private String codigo;
	private String codigoContacto;
	private String nombreContacto;
	private String fechaNacimiento;
	private String telefono;
	private String tipoContacto;
	private String email;
	private String usuario;

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

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/GuardarContacto";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		GuardarContactoRequest actualizarContactoRequest = new GuardarContactoRequest();
		actualizarContactoRequest.setCodigo(codigo);
		actualizarContactoRequest.setCodigoContacto(codigoContacto);
		actualizarContactoRequest.setEmail(email);
		actualizarContactoRequest.setFechaNacimiento(fechaNacimiento);
		actualizarContactoRequest.setNombreContacto(nombreContacto);
		actualizarContactoRequest.setTelefono(telefono);
		actualizarContactoRequest.setTipoContacto(tipoContacto);
		actualizarContactoRequest.setUsuario(usuario);
		String request = JSONHelper.serializar(actualizarContactoRequest);
		return request;
	}

	@Override
	protected GuardarContactoResponse responseText(String json) {
		// TODO Auto-generated method stub
		GuardarContactoResponse actualizarContactoResponse = JSONHelper.desSerializar(json, GuardarContactoResponse.class);
		return actualizarContactoResponse;
	}
}

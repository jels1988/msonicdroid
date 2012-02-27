package pe.lindley.ventacero.ws.service;

import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import pe.lindley.ventacero.ws.bean.ObtenerVentaCeroResponse;
import pe.lindley.ventacero.ws.bean.ObtenerVentaCeroRequest;
import roboguice.inject.InjectResource;

public class ObtenerVentaCeroProxy extends ProxyBase<ObtenerVentaCeroResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsVtaCero)protected String urlWS;
	
	private String codDeposito;
	private String Anio;
	private String Mes;
	private String Semana;
	private String codSap;
	private String codRuta;
	private String Segmento;
	private String codCliente;
	private String NombreCliente;
	private String Ruc;
	private String Dni;

	public String getCodDeposito() {
		return codDeposito;
	}

	public void setCodDeposito(String codDeposito) {
		this.codDeposito = codDeposito;
	}

	public String getAnio() {
		return Anio;
	}

	public void setAnio(String anio) {
		Anio = anio;
	}

	public String getMes() {
		return Mes;
	}

	public void setMes(String mes) {
		Mes = mes;
	}

	public String getSemana() {
		return Semana;
	}

	public void setSemana(String semana) {
		Semana = semana;
	}

	public String getCodSap() {
		return codSap;
	}

	public void setCodSap(String codSap) {
		this.codSap = codSap;
	}

	public String getCodRuta() {
		return codRuta;
	}

	public void setCodRuta(String codRuta) {
		this.codRuta = codRuta;
	}

	public String getSegmento() {
		return Segmento;
	}

	public void setSegmento(String segmento) {
		Segmento = segmento;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getNombreCliente() {
		return NombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		NombreCliente = nombreCliente;
	}
	
	public String getRuc() {
		return Ruc;
	}

	public void setRuc(String ruc) {
		Ruc = ruc;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarVentaCero";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ObtenerVentaCeroRequest obtenerVentaCeroRequest = new ObtenerVentaCeroRequest();
		obtenerVentaCeroRequest.setAnio(Anio);
		obtenerVentaCeroRequest.setCodCliente(codCliente);
		obtenerVentaCeroRequest.setCodDeposito(codDeposito);
		obtenerVentaCeroRequest.setCodRuta(codRuta);
		obtenerVentaCeroRequest.setCodSap(codSap);
		obtenerVentaCeroRequest.setDni(Dni);
		obtenerVentaCeroRequest.setMes(Mes);
		obtenerVentaCeroRequest.setNombreCliente(NombreCliente);
		obtenerVentaCeroRequest.setRuc(Ruc);
		obtenerVentaCeroRequest.setSegmento(Segmento);
		obtenerVentaCeroRequest.setSemana(Semana);
		String request = JSONHelper.serializar(obtenerVentaCeroRequest);
		return request;
	}

	@Override
	protected ObtenerVentaCeroResponse responseText(String json) {
		// TODO Auto-generated method stub
		ObtenerVentaCeroResponse obtenerVentaCeroResponse = JSONHelper.desSerializar(json, ObtenerVentaCeroResponse.class);
		return obtenerVentaCeroResponse;
	}
}

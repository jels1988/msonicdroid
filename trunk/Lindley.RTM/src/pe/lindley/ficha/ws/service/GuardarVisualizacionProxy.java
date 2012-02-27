package pe.lindley.ficha.ws.service;

import pe.lindley.ficha.ws.bean.GuardarVisualizacionRequest;
import pe.lindley.ficha.ws.bean.GuardarVisualizacionResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class GuardarVisualizacionProxy extends ProxyBase<GuardarVisualizacionResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsFichaVisualizacion)protected String urlWS;
	
	private String cliente;
	private String codigoImg;
	private String fechaToma;
	private String urlFoto;
	private String estado;


	public String getUrlWS() {
		return urlWS;
	}

	public void setUrlWS(String urlWS) {
		this.urlWS = urlWS;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getCodigoImg() {
		return codigoImg;
	}

	public void setCodigoImg(String codigoImg) {
		this.codigoImg = codigoImg;
	}

	public String getFechaToma() {
		return fechaToma;
	}

	public void setFechaToma(String fechaToma) {
		this.fechaToma = fechaToma;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/guardarVisualizacion";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		GuardarVisualizacionRequest actualizarVisualizacionRequest = new GuardarVisualizacionRequest();
		actualizarVisualizacionRequest.setCodigo(cliente);
		actualizarVisualizacionRequest.setCodigoImagen(codigoImg);
		actualizarVisualizacionRequest.setFechaToma(fechaToma);		
		actualizarVisualizacionRequest.setUrl(urlFoto);
		String request = JSONHelper.serializar(actualizarVisualizacionRequest);
		return request;
	}

	@Override
	protected GuardarVisualizacionResponse responseText(String json) {
		// TODO Auto-generated method stub
		GuardarVisualizacionResponse actualizarVisualizacionResponse = JSONHelper.desSerializar(json, GuardarVisualizacionResponse.class);
		return actualizarVisualizacionResponse;
	}
}
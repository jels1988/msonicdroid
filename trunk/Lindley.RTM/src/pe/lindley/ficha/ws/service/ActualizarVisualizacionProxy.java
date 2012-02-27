package pe.lindley.ficha.ws.service;

import pe.lindley.ficha.ws.bean.GuardarVisualizacionResponse;
import pe.lindley.ficha.ws.bean.GuardarVisualizacionRequest;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ActualizarVisualizacionProxy extends ProxyBase<GuardarVisualizacionResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsFichaVisualizacion)protected String urlWS;
	
	private String codigo;
	private String codigoImagen;
	private String fechaToma;
	private String url;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoImagen() {
		return codigoImagen;
	}

	public void setCodigoImagen(String codigoImagen) {
		this.codigoImagen = codigoImagen;
	}

	public String getFechaToma() {
		return fechaToma;
	}

	public void setFechaToma(String fechaToma) {
		this.fechaToma = fechaToma;
	}

	public String geturl() {
		return url;
	}

	public void seturl(String url) {
		this.url = url;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/actualizarVisualizacion";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		GuardarVisualizacionRequest actualizarVisualizacionRequest = new GuardarVisualizacionRequest();
		actualizarVisualizacionRequest.setCodigo(codigo);
		actualizarVisualizacionRequest.setCodigoImagen(codigoImagen);
		actualizarVisualizacionRequest.setFechaToma(fechaToma);
		actualizarVisualizacionRequest.setUrl(url);
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

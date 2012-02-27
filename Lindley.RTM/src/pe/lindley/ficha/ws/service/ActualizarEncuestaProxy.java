package pe.lindley.ficha.ws.service;

import java.util.ArrayList;
import pe.lindley.ficha.to.SeccionTO;
import pe.lindley.ficha.ws.bean.GuardarEncuestaRequest;
import pe.lindley.ficha.ws.bean.GuardarEncuestaResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ActualizarEncuestaProxy  extends ProxyBase<GuardarEncuestaResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsFichaEncuesta)protected String urlWS;
		
	private String codigo;
	private String codigoEncuesta;
	private String usuario;
	private ArrayList<SeccionTO> detalleSeccion;
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCodigoEncuesta() {
		return codigoEncuesta;
	}
	
	public void setCodigoEncuesta(String codigoEncuesta) {
		this.codigoEncuesta = codigoEncuesta;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public ArrayList<SeccionTO> getDetalleSeccion() {
		return detalleSeccion;
	}
	
	public void setDetalleSeccion(ArrayList<SeccionTO> detalleSeccion) {
		this.detalleSeccion = detalleSeccion;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/actualizarEncuesta";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		GuardarEncuestaRequest guardarEncuestaRequest = new GuardarEncuestaRequest();
		guardarEncuestaRequest.setCodigo(codigo);
		guardarEncuestaRequest.setCodigoEncuesta(codigoEncuesta);
		guardarEncuestaRequest.setDetalleSeccion(detalleSeccion);
		guardarEncuestaRequest.setUsuario(usuario);
		String request = JSONHelper.serializar(guardarEncuestaRequest);
		return request;
	}

	@Override
	protected GuardarEncuestaResponse responseText(String json) {
		// TODO Auto-generated method stub
		GuardarEncuestaResponse guardarEncuestaResponse = JSONHelper.desSerializar(json, GuardarEncuestaResponse.class); 
		return guardarEncuestaResponse;
	}
}
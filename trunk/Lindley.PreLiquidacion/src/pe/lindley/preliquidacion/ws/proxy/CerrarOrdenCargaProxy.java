package pe.lindley.preliquidacion.ws.proxy;

import pe.lindley.preliquidacion.R;
import pe.lindley.preliquidacion.ws.bean.CerrarOrdenCargaRequest;
import pe.lindley.preliquidacion.ws.bean.CerrarOrdenCargaResponse;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class CerrarOrdenCargaProxy extends ProxyBase<CerrarOrdenCargaResponse> {

	@InjectResource(R.string.urlwsCierre)protected String urlWS;
		
	private String deposito;
		
	private String usuario;
		
	private String ordenCarga;

	public String getDeposito() {
		return deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getOrdenCarga() {
		return ordenCarga;
	}

	public void setOrdenCarga(String ordenCarga) {
		this.ordenCarga = ordenCarga;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/CerrarCarga";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		CerrarOrdenCargaRequest cerrarOrdenCargaRequest = new CerrarOrdenCargaRequest();
		cerrarOrdenCargaRequest.setDeposito(this.deposito);
		cerrarOrdenCargaRequest.setOrdenCarga(this.ordenCarga);
		cerrarOrdenCargaRequest.setUsuario(this.usuario);
		String request = JSONHelper.serializar(cerrarOrdenCargaRequest);		
		return request;
	}

	@Override
	protected CerrarOrdenCargaResponse responseText(String json) {
		// TODO Auto-generated method stub
		CerrarOrdenCargaResponse cerrarOrdenCargaResponse = JSONHelper.desSerializar(json,CerrarOrdenCargaResponse.class);
		return cerrarOrdenCargaResponse;
	}

}

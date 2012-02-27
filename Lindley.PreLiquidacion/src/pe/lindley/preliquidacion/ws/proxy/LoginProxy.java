package pe.lindley.preliquidacion.ws.proxy;

import pe.lindley.preliquidacion.R;
import pe.lindley.preliquidacion.ws.bean.ValidarUsuarioRequest;
import pe.lindley.preliquidacion.ws.bean.ValidarUsuarioResponse;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class LoginProxy extends ProxyBase<ValidarUsuarioResponse> {

	
	@InjectResource(R.string.urlwsLogin)protected String urlWS;
	
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
		return urlWS + "/ValidarUsuario";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		
		ValidarUsuarioRequest validarUsuarioRequest = new ValidarUsuarioRequest();
		validarUsuarioRequest.setDeposito(deposito);
		validarUsuarioRequest.setUsuario(usuario);
		validarUsuarioRequest.setOrdenCarga(ordenCarga);
		
		String request = JSONHelper.serializar(validarUsuarioRequest);
		return request;
	}

	@Override
	protected ValidarUsuarioResponse responseText(String json) {
		// TODO Auto-generated method stub
		ValidarUsuarioResponse response = JSONHelper.desSerializar(json,ValidarUsuarioResponse.class);
		return response;
	}

}

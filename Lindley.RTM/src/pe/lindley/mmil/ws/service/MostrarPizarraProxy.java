package pe.lindley.mmil.ws.service;

import pe.lindley.mmil.ws.bean.MostrarPizarraCdaRequest;
import pe.lindley.mmil.ws.bean.MostrarPizarraRequest;
import pe.lindley.mmil.ws.bean.MostrarPizarraResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

import com.google.gson.annotations.SerializedName;

public class MostrarPizarraProxy extends ProxyBase<MostrarPizarraResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsMMILPizarra)protected String urlWS;	
	
	@SerializedName("COD")
	private String codigoDeposito;

	public String getCodigoDeposito() {
		return codigoDeposito;
	}

	public void setCodigoDeposito(String codigoDeposito) {
		this.codigoDeposito = codigoDeposito;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarPizarra";
		//return urlWS + "/ListarPizarraCda";
		//return urlWS + "/ListarCdaxRegion";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		MostrarPizarraRequest mostrarPizarraRequest = new MostrarPizarraRequest();
		mostrarPizarraRequest.setCodigoDeposito(codigoDeposito);
		String request = JSONHelper.serializar(mostrarPizarraRequest);
		return request;
	}

	@Override
	protected MostrarPizarraResponse responseText(String json) {
		// TODO Auto-generated method stub
		MostrarPizarraResponse mostrarPizarraResponse = JSONHelper.desSerializar(json, MostrarPizarraResponse.class);
		return mostrarPizarraResponse;
	}
	

}

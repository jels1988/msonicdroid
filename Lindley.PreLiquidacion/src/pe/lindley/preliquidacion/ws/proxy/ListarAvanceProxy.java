package pe.lindley.preliquidacion.ws.proxy;

import pe.lindley.preliquidacion.R;
import pe.lindley.preliquidacion.ws.bean.ListarAvanceRequest;
import pe.lindley.preliquidacion.ws.bean.ListarAvanceResponse;
import roboguice.inject.InjectResource;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class ListarAvanceProxy extends ProxyBase<ListarAvanceResponse> {

	@InjectResource(R.string.urlwsAvance)protected String urlWS;
	
	@SerializedName("DEP")
	private String desposito;
	
	@SerializedName("OCA")
	private String numeroCarga;

	public String getDesposito() {
		return desposito;
	}

	public void setDesposito(String desposito) {
		this.desposito = desposito;
	}

	public String getNumeroCarga() {
		return numeroCarga;
	}

	public void setNumeroCarga(String numeroCarga) {
		this.numeroCarga = numeroCarga;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/Avance";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ListarAvanceRequest avanceRequest = new ListarAvanceRequest();
		avanceRequest.setDesposito(desposito);
		avanceRequest.setNumeroCarga(numeroCarga);
		String request = JSONHelper.serializar(avanceRequest);		
		return request;
	}

	@Override
	protected ListarAvanceResponse responseText(String json) {
		// TODO Auto-generated method stub
		ListarAvanceResponse avanceResponse = JSONHelper.desSerializar(json,ListarAvanceResponse.class);
		return avanceResponse;
	}

}

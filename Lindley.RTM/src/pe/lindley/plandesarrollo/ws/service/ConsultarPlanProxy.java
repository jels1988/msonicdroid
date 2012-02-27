package pe.lindley.plandesarrollo.ws.service;

import com.google.gson.annotations.SerializedName;

import pe.lindley.plandesarrollo.ws.bean.ConsultarPlanRequest;
import pe.lindley.plandesarrollo.ws.bean.ConsultarPlanResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarPlanProxy extends ProxyBase<ConsultarPlanResponse> {
	
	@InjectResource(pe.lindley.activity.R.string.urlwsPDPrincipal)protected String urlWS;
	
	@SerializedName("COD")
	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarPlanDesarrollo";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarPlanRequest consultarPlanRequest = new ConsultarPlanRequest();
		consultarPlanRequest.setCodigo(codigo);
		String request = JSONHelper.serializar(consultarPlanRequest);
		return request;
	}

	@Override
	protected ConsultarPlanResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarPlanResponse consultarPlanResponse = JSONHelper.desSerializar(json, ConsultarPlanResponse.class);
		return consultarPlanResponse;
	}

}

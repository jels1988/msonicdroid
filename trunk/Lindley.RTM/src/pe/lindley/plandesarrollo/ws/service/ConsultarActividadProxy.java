package pe.lindley.plandesarrollo.ws.service;

import com.google.gson.annotations.SerializedName;

import pe.lindley.plandesarrollo.ws.bean.ConsultarActividadRequest;
import pe.lindley.plandesarrollo.ws.bean.ConsultarActividadResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarActividadProxy extends ProxyBase<ConsultarActividadResponse> {
	
	@InjectResource(pe.lindley.activity.R.string.urlwsPDActividad)protected String urlWS;	

	@SerializedName("CLI")
	private String codigoCliente;

	@SerializedName("PLAN")
	private String codigoPlan;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoPlan() {
		return codigoPlan;
	}

	public void setCodigoPlan(String codigoPlan) {
		this.codigoPlan = codigoPlan;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarActividad";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarActividadRequest consultarActividadRequest = new ConsultarActividadRequest();
		consultarActividadRequest.setCodigoCliente(codigoCliente);
		consultarActividadRequest.setCodigoPlan(codigoPlan);
		String request = JSONHelper.serializar(consultarActividadRequest);
		return request;
	}

	@Override
	protected ConsultarActividadResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarActividadResponse consultarActividadResponse = JSONHelper.desSerializar(json, ConsultarActividadResponse.class);
		return consultarActividadResponse;
	}

}

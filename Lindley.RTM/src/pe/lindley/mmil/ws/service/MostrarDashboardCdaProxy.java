package pe.lindley.mmil.ws.service;

import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import pe.lindley.mmil.ws.bean.MostrarDashboardCdaRequest;
import pe.lindley.mmil.ws.bean.MostrarDashboardResponse;
import roboguice.inject.InjectResource;

public class MostrarDashboardCdaProxy extends ProxyBase<MostrarDashboardResponse>{
	
	@InjectResource(pe.lindley.activity.R.string.urlwsMMILDashBoard)protected String urlWS;

	private String codigo;
	private String grafico;
	private int tipo;
	private int supervisor;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getGrafico() {
		return grafico;
	}

	public void setGrafico(String grafico) {
		this.grafico = grafico;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(int supervisor) {
		this.supervisor = supervisor;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarDashBoardCDA";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		MostrarDashboardCdaRequest mostrarDashboardCdaRequest = new MostrarDashboardCdaRequest();
		mostrarDashboardCdaRequest.setCodigo(codigo);
		mostrarDashboardCdaRequest.setGrafico(grafico);
		mostrarDashboardCdaRequest.setSupervisor(supervisor);
		mostrarDashboardCdaRequest.setTipo(tipo);
		String request = JSONHelper.serializar(mostrarDashboardCdaRequest);
		return request;
	}

	@Override
	protected MostrarDashboardResponse responseText(String json) {
		// TODO Auto-generated method stub
		MostrarDashboardResponse mostrarDashboardResponse = JSONHelper.desSerializar(json, MostrarDashboardResponse.class);
		return mostrarDashboardResponse;
	}

}

package pe.lindley.mmil.ws.service;

import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import pe.lindley.mmil.ws.bean.MostrarDashboardVendedorRequest;
import pe.lindley.mmil.ws.bean.MostrarDashboardResponse;
import roboguice.inject.InjectResource;

public class MostrarDashboardVendedorProxy extends ProxyBase<MostrarDashboardResponse>{

	@InjectResource(pe.lindley.activity.R.string.urlwsMMILDashBoard)protected String urlWS;
	
	private String Codigo;
	private String Fecha;
	private int Tipo;
	private int Supervisor;

	public String getCodigo() {
		return Codigo;
	}

	public void setCodigo(String codigo) {
		Codigo = codigo;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public int getTipo() {
		return Tipo;
	}

	public void setTipo(int tipo) {
		Tipo = tipo;
	}

	public int getSupervisor() {
		return Supervisor;
	}

	public void setSupervisor(int supervisor) {
		Supervisor = supervisor;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarDashBoard";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		MostrarDashboardVendedorRequest mostrarDashboardVendedorRequest = new MostrarDashboardVendedorRequest();
		mostrarDashboardVendedorRequest.setCodigo(Codigo);
		mostrarDashboardVendedorRequest.setFecha(Fecha);
		mostrarDashboardVendedorRequest.setSupervisor(Supervisor);
		mostrarDashboardVendedorRequest.setTipo(Tipo);
		String request = JSONHelper.serializar(mostrarDashboardVendedorRequest);  
		return request;
	}

	@Override
	protected MostrarDashboardResponse responseText(String json) {
		// TODO Auto-generated method stub
		MostrarDashboardResponse mostrarDashboardResponse = JSONHelper.desSerializar(json, MostrarDashboardResponse.class);
		return mostrarDashboardResponse;
	}

}

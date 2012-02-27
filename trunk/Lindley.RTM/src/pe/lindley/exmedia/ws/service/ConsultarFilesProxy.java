package pe.lindley.exmedia.ws.service;

import pe.lindley.exmedia.ws.bean.ConsultarFilesRequest;
import pe.lindley.exmedia.ws.bean.ConsultarFilesResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import roboguice.inject.InjectResource;

public class ConsultarFilesProxy extends ProxyBase<ConsultarFilesResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsExMulti)protected String urlWS;
	
	private String fileName;
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	private int tipo;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/ListarArchivos";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		ConsultarFilesRequest consultarFilesRequest = new ConsultarFilesRequest();
		consultarFilesRequest.setFileName(fileName);
		consultarFilesRequest.setTipo(tipo);
		
		String request = JSONHelper.serializar(consultarFilesRequest);
		return request;
	}

	@Override
	protected ConsultarFilesResponse responseText(String json) {
		// TODO Auto-generated method stub
		ConsultarFilesResponse consultarFilesResponse = JSONHelper.desSerializar(json,ConsultarFilesResponse.class);
		return consultarFilesResponse;
	}

}

package pe.lindley.prospector.ws.service;

import pe.lindley.prospector.to.FileTO;
import pe.lindley.prospector.ws.bean.UploadFileRequest;
import pe.lindley.prospector.ws.bean.UploadFileResponse;
import pe.lindley.util.JSONHelper;
import pe.lindley.util.ProxyBase;
import pe.lindley.util.UploadFileUtil;
import roboguice.inject.InjectResource;

public class UploadFileProxy extends ProxyBase<UploadFileResponse> {

	@InjectResource(pe.lindley.activity.R.string.urlwsCliente)protected String urlWS;
	
	
	private int servidorId;
	private int tipoDocumentoId;
	public int getServidorId() {
		return servidorId;
	}

	public void setServidorId(int servidorId) {
		this.servidorId = servidorId;
	}

	public int getTipoDocumentoId() {
		return tipoDocumentoId;
	}

	public void setTipoDocumentoId(int tipoDocumentoId) {
		this.tipoDocumentoId = tipoDocumentoId;
	}

	private String fileName;
	private String filePath;
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/UploadFile";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		
		UploadFileRequest uploadFileRequest = new UploadFileRequest();
		
		
		FileTO file = new FileTO();
		file.setServidorId(servidorId);
		file.setTipoDocumento(tipoDocumentoId);
		file.setFile(UploadFileUtil.FileToByteArray(getFilePath()));
		file.setNombre(fileName);
		
		uploadFileRequest.setFile(file);
		
		String request = JSONHelper.serializar(uploadFileRequest);
		return request;
	}

	@Override
	protected UploadFileResponse responseText(String json) {
		// TODO Auto-generated method stub
		UploadFileResponse response =  JSONHelper.desSerializar(json,UploadFileResponse.class);
		return response;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}

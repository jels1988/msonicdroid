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
	
	

	public String fileName;
	public String filePath;
	
	
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/UploadFile";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		FileTO file = new FileTO();
		file.file = UploadFileUtil.FileToByteArray(filePath);
		file.nombre = fileName;
		
		
		
		UploadFileRequest uploadFileRequest = new UploadFileRequest();
		uploadFileRequest.file = file;
		
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

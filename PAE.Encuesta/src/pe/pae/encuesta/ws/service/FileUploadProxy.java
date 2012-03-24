package pe.pae.encuesta.ws.service;


import pe.pae.encuesta.R;
import pe.pae.encuesta.to.FileTO;
import pe.pae.encuesta.ws.bean.FileUploadRequest;
import pe.pae.encuesta.ws.bean.FileUploadResponse;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;
import net.msonic.lib.UploadFileUtil;

public class FileUploadProxy extends ProxyBase<FileUploadResponse> {

	@InjectResource(R.string.urlLoginWS)protected String urlWS;
	
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
		
		FileUploadRequest fileUploadRequest = new FileUploadRequest();
		fileUploadRequest.file = file;
		
		String request = JSONHelper.serializar(fileUploadRequest);
		return request;
	}

	@Override
	protected FileUploadResponse responseText(String json) {
		// TODO Auto-generated method stub
		FileUploadResponse response =  JSONHelper.desSerializar(json,FileUploadResponse.class);
		return response;
	}

}

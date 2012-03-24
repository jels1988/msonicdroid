package lindley.desarrolloxcliente.ws.service;


import roboguice.inject.InjectResource;
import lindley.desarrolloxcliente.R;
import lindley.desarrolloxcliente.to.FileTO;
import lindley.desarrolloxcliente.ws.bean.FileUploadRequest;
import lindley.desarrolloxcliente.ws.bean.FileUploadResponse;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;
import net.msonic.lib.UploadFileUtil;

public class FileUploadProxy extends ProxyBase<FileUploadResponse> {

	@InjectResource(R.string.urlwsDesarrolloxCliente)protected String urlWS;
	

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

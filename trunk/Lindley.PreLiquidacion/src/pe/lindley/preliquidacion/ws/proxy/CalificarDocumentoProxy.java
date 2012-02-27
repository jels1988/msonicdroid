package pe.lindley.preliquidacion.ws.proxy;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import pe.lindley.preliquidacion.R;
import pe.lindley.preliquidacion.to.DocumentoRequestTO;
import pe.lindley.preliquidacion.ws.bean.CalificarDocumentoRequest;
import pe.lindley.preliquidacion.ws.bean.CalificarDocumentoResponse;
import roboguice.inject.InjectResource;
import net.msonic.lib.JSONHelper;
import net.msonic.lib.ProxyBase;

public class CalificarDocumentoProxy extends ProxyBase<CalificarDocumentoResponse> {
	
	@InjectResource(R.string.urlwsCalificar)protected String urlWS;

	@SerializedName("DOC")
	private ArrayList<DocumentoRequestTO> documentos;

	public ArrayList<DocumentoRequestTO> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(ArrayList<DocumentoRequestTO> documentos) {
		this.documentos = documentos;
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return urlWS + "/CalificarDocumento";
	}

	@Override
	protected String requestText() {
		// TODO Auto-generated method stub
		CalificarDocumentoRequest calificarDocumentoRequest = new CalificarDocumentoRequest();
		calificarDocumentoRequest.setDocumentos(this.documentos);
		String request = JSONHelper.serializar(calificarDocumentoRequest);		
		return request;
	}

	@Override
	protected CalificarDocumentoResponse responseText(String json) {
		// TODO Auto-generated method stub
		CalificarDocumentoResponse calificarDocumentoResponse = JSONHelper.desSerializar(json,CalificarDocumentoResponse.class);
		return calificarDocumentoResponse;
	}

}

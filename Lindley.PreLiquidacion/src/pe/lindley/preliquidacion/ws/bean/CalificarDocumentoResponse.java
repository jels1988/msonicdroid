package pe.lindley.preliquidacion.ws.bean;

import java.util.ArrayList;

import pe.lindley.preliquidacion.to.DocumentoResponseTO;

import com.google.gson.annotations.SerializedName;
import net.msonic.lib.ResponseBase;

public class CalificarDocumentoResponse extends ResponseBase{

	@SerializedName("DOC")
	private ArrayList<DocumentoResponseTO> documentos;

	public ArrayList<DocumentoResponseTO> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(ArrayList<DocumentoResponseTO> documentos) {
		this.documentos = documentos;
	}
	
	
}

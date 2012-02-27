package pe.lindley.preliquidacion.ws.bean;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import pe.lindley.preliquidacion.to.DocumentoRequestTO;

public class CalificarDocumentoRequest {

	@SerializedName("DOC")
	private ArrayList<DocumentoRequestTO> documentos;

	public ArrayList<DocumentoRequestTO> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(ArrayList<DocumentoRequestTO> documentos) {
		this.documentos = documentos;
	}
}

package pe.lindley.preliquidacion.ws.bean;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import pe.lindley.preliquidacion.to.DocumentoTO;
import pe.lindley.preliquidacion.to.MotivoTO;

import net.msonic.lib.ResponseBase;

public class ListarDocumentoResponse extends ResponseBase {
	
	@SerializedName("DOC")
	private ArrayList<DocumentoTO> documentos;
	
	
	@SerializedName("MOT")
	private ArrayList<MotivoTO> motivos;


	public ArrayList<DocumentoTO> getDocumentos() {
		return documentos;
	}


	public void setDocumentos(ArrayList<DocumentoTO> documentos) {
		this.documentos = documentos;
	}


	public ArrayList<MotivoTO> getMotivos() {
		return motivos;
	}


	public void setMotivos(ArrayList<MotivoTO> motivos) {
		this.motivos = motivos;
	}
	
	
	
}
